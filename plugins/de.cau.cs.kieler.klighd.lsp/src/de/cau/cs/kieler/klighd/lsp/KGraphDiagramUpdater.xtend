/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019, 2020, 2021 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.lsp

import com.google.common.base.Throwables
import com.google.gson.JsonObject
import com.google.inject.Inject
import com.google.inject.Provider
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart
import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.ide.model.MessageModel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.launch.AbstractLanguageServer
import de.cau.cs.kieler.klighd.lsp.model.RequestDiagramPieceAction
import de.cau.cs.kieler.klighd.lsp.model.SKGraph
import de.cau.cs.kieler.klighd.lsp.utils.KGraphMappingUtil
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.concurrent.CompletableFuture
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.sprotty.IDiagramServer
import org.eclipse.sprotty.SGraph
import org.eclipse.sprotty.SModelElement
import org.eclipse.sprotty.xtext.ILanguageAwareDiagramServer
import org.eclipse.sprotty.xtext.ls.DiagramLanguageServer
import org.eclipse.sprotty.xtext.ls.DiagramUpdater
import org.eclipse.xtext.util.CancelIndicator

/**
 * Connection between {@link IDiagramServer} and the {@link DiagramLanguageServer}. With this singleton diagram updater,
 * any diagram server can update its diagrams via this diagramUpdater directly to the language server.
 * 
 * @author nre
 */
class KGraphDiagramUpdater extends DiagramUpdater {
    /**
     * The {@link Provider} to call an injected {@link KGraphDiagramGenerator} to generate {@link KNode KGraphs} and 
     * {@link SKGraph}s from that.
     */
    @Inject
    Provider<KGraphDiagramGenerator> diagramGeneratorProvider
    
    /**
     * The {@link Provider} to call an injected {@link KGraphIncrementalDiagramGenerator} to generate {@link KNode KGraphs} and 
     * {@link SKGraph}s from that using the incremental top-down approach.
     */
    @Inject
    Provider<KGraphIncrementalDiagramGenerator> incrementalDiagramGeneratorProvider

    /**
     * The language server using this diagram updater. Double of the private languageServer field of the DiagramUpdater
     * class, with protected instead of private field accessibility here.
     */
    protected DiagramLanguageServer languageServer

    /**
     * Stores data for the generation of diagrams.
     */
    @Inject
    KGraphDiagramState diagramState
    
    /**
     * The handler for sending info, warnings, or errors to the user.
     */
    @Inject
    INotificationHandler notificationHandler
    
    /**
     * The key of the client options under that the synthesis options are stored.
     */
    public static String SYNTHESIS_OPTION = 'synthesis'

    override initialize(DiagramLanguageServer languageServer) {
        this.languageServer = languageServer
        super.initialize(languageServer)
    }

    /**
     * Updates the diagram without re-generating the underlying KGraph.
     * So this will use the stored KGraph saved for this diagramServer and create a new SGraph for that and call the
     * layout.
     * 
     * @param diagramServer The diagram server that should update its layout.
     */
    def updateLayout(KGraphDiagramServer diagramServer) {
        return CompletableFuture.completedFuture(doUpdateLayout(diagramServer))
    }

    /**
     * Updates the layout for the diagramServer, see {@see #updateLayout(KGraphDiagramServer)}.
     * Does this later as a completable future.
     * 
     * @param diagramServer The diagram server that should update its layout.
     */
    protected def CompletableFuture<Void> doUpdateLayout(KGraphDiagramServer diagramServer) {
        val uri = diagramServer.sourceUri
        return (languageServer as KGraphLanguageServerExtension).doRead(uri) [ resource, ci |
            // Just update the SGraph from the already existing KGraph.
            var ViewContext viewContext = null
            synchronized(diagramState) {
                viewContext = diagramState.getKGraphContext(uri)
            }
            
            return diagramServer -> createModel(viewContext, uri, ci)
        ].thenAccept [
            if (value !== null) {
                key.prepareUpdateModel(value)
            } else {
                // The value is null if the layout was cancelled.
                // null should never be saved as a model therefore nothing is done.
            }
            
        ].exceptionally [ throwable |
            notificationHandler.sendError(Throwables.getStackTraceAsString(throwable))
            throwable.printStackTrace
            return null
        ]
    }

    override protected doUpdateDiagrams(String uri, List<? extends ILanguageAwareDiagramServer> diagramServers) {
        if (diagramServers.empty) {
            return CompletableFuture.completedFuture(null)
        }
        return (languageServer as KGraphLanguageServerExtension).doRead(uri) [ resource, ci |
            var Object snapshotModel = null
            synchronized (diagramState) {
                snapshotModel = diagramState.getSnapshotModel(uri)
            }
            var Object model = null
            if (snapshotModel === null) {
                if (resource === null) {
                    model = new MessageModel("No model in editor")
                } else {
                    model = resource.contents.head
                }
            } else {
                model = snapshotModel
            }
            // Check if the model has errors. If the model has errors and no diagram has been shown before, show an
            // error message as the model. Otherwise if there are errors, ignore the diagram update and keep the old
            // one. If there are no errors, just generate the diagram.
            val hasErrors = model instanceof EObject && (model as EObject).eResource !== null
                && !(model as EObject).eResource.errors.isEmpty
            if (hasErrors) {
                // prettify the error message to be better readable line by line
                val errors = (model as EObject).eResource.errors.fold(
                    new StringBuilder, [builder, error | builder.append("\n" + error)]).toString
                model = new MessageModel("The model contains errors:\n" + errors)
            }
            val model_ = model;
            (diagramServers as List<KGraphDiagramServer>).forEach [ KGraphDiagramServer server |
                // Only update an erroneous model if there was no diagram shown before.
                if (!hasErrors || server.currentRoot.type == "NONE") {
                    prepareModel(server, model_, uri)
                    updateLayout(server)
                }
            ]
            return null as Void
        ]
    }

    /**
     * Prepares the DiagramState and the diagram server to generate an SGraph for the given model the next time the 
     * createModel is called.
     * 
     * @param server The diagramServer for that the diagram state should be updated.
     * @param model The new model that should be shown for the server.
     * @param uri The identifying URI to access the diagram state maps.
     */
    synchronized def void prepareModel(KGraphDiagramServer server, Object model, String uri) {

        val properties = new KlighdSynthesisProperties()
        var SprottyViewer viewer = null
        var String synthesisId
        synchronized (diagramState) {
            val iViewer = diagramState.getViewer()
            if (iViewer instanceof SprottyViewer) {
                viewer = iViewer
            }

            synthesisId = diagramState.getSynthesisId(uri)
        }

        // Set properties.
        if (synthesisId !== null) {
            // If the synthesisId is null, KLighD will use the a default synthesis defined for this model.
            properties.setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS, synthesisId)
        }

        // Indicated if the model type changed against the current model
        var modelTypeChanged = false
        var ViewContext viewContext = null

        if (viewer === null || viewer.viewContext === null) {
            // if viewer or context does not exist always init view
            modelTypeChanged = true
        } else {
            viewContext = viewer.viewContext
            if (viewContext.inputModel === null || viewContext.inputModel.class !== model.class) {
                modelTypeChanged = true
                // If the model is a different type of what is given in the viewContext, also reset the synthesis.
                properties.setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS, null)
            }
            if (viewContext.getDiagramSynthesis() !== null
                && !KlighdDataManager.instance.getSynthesisID(viewContext.getDiagramSynthesis()).equals(synthesisId)) {
                // In case the synthesis changed the sidebar should be updated
                modelTypeChanged = true
            }
        }

        // If the type changed the view must be reinitialized to provide a correct ViewContext
        // otherwise the ViewContext can be simply updated.
        if (modelTypeChanged) {
            // Configure the ViewContext and the KlighD synthesis to generate the KGraph model correctly.
            // needs to be a IDiagramWorkbenchPart, as it calls the standard constructor.
            viewContext = new ViewContext(null as IDiagramWorkbenchPart, model).configure(properties)
            viewer = viewContext.createViewer(null, null) as SprottyViewer
            viewer.diagramServer = server as KGraphDiagramServer
            viewer.viewContext = viewContext
        }

        // Save recent synthesis options to restore later.
        storeCurrentSynthesisOptions()
        // Update the recent synthesis options with all synthesis options configured on the client.
        configureSynthesisOptions(viewContext)
        var Map<SynthesisOption, Object> recentSynthesisOptions = null
        synchronized (diagramState) {
            recentSynthesisOptions = diagramState.recentSynthesisOptions
        }
        properties.configureSynthesisOptionValues(recentSynthesisOptions)
        
        viewContext.copyProperties(properties)
        // Manually set the options in the view context, as it already has been initially configured before.
        if (recentSynthesisOptions !== null) {
            for (Map.Entry<SynthesisOption, Object> entry : recentSynthesisOptions.entrySet) {
                viewContext.configureOption(entry.key, entry.value)
            }
        }
        
        val vc = viewContext
        // Update the model and with that call the diagram synthesis.
        AbstractLanguageServer.addToMainThreadQueue([
            vc.update(model)
        ])

        synchronized (diagramState) {
            diagramState.putURIString(server.clientId, uri)
            diagramState.putKGraphContext(uri, viewContext)
            diagramState.putSynthesisId(uri, KlighdDataManager.instance.getSynthesisID(viewContext.diagramSynthesis))
            if (viewer !== null) {
                diagramState.setViewer(viewer)
            }
        }
    }

    /**
     * Generates an {@link SGraph} from the given {@link ViewContext} and the id under which it should be remembered in
     * the {@link KGraphDiagramState}.
     * 
     * @param viewContext The viewContext containing the {@link KNode KGraph} view model that should be translated to
     * an {@link SGraph} model.
     * @param uri The identifying URI of the source model used in the SGraph model generation and that is used to store
     * diagram generation relevant data in the {@link KGraphDiagramState}.
     * @param cancelIndicator The {@link CancelIndicator} used to tell the diagram translation to stop.
     * @return The generated SGraph
     */
    synchronized def SGraph createModel(ViewContext viewContext, String uri, CancelIndicator cancelIndicator) {
        // Generate the SGraph model from the KGraph model and store every later relevant part in the
        // diagram state.
        
        // FIXME: currently collapsing and expanding all regions breaks the diagram somehow, apparently not always
        //        maybe when hierarchy is low so some elements aren't rendered and "get lost" when collapsing and 
        //        expanding, not sure why though, must investigate further
        
        var incrementalDiagramGenerator = false
        if (languageServer instanceof KGraphLanguageServerExtension) {
            incrementalDiagramGenerator = languageServer.isIncrementalDiagramGenerator
        }
        
        // FIXME: extract this to some other strategy manager that can handle passing the options
        //        idea: abstract superclass KGraphDiagramGenerator
        var diagramGenerator = incrementalDiagramGenerator
            ? incrementalDiagramGeneratorProvider.get
            : diagramGeneratorProvider.get
        
        val sGraph = diagramGenerator.toSGraph(viewContext.viewModel, uri, cancelIndicator)
        
        if (incrementalDiagramGenerator) {
            val requestManager = new KGraphDiagramPieceRequestManager(diagramGenerator as KGraphIncrementalDiagramGenerator)
            synchronized (diagramState) {
                diagramState.putDiagramPieceRequestManager(uri, requestManager)
            }
        }
        
        synchronized (diagramState) {
            diagramState.putKGraphToSModelElementMap(uri, diagramGenerator.getKGraphToSModelElementMap)
            diagramState.putIdToKGraphElementMap(uri, diagramGenerator.idToKGraphElementMap)
            diagramState.putImageData(uri, diagramGenerator.images)
        }
        
        return sGraph
        
    }

    /**
     * Stores the current synthesisOptions configured in the current {@link ViewContext}.
     * Similar to storing the options in Eclipse UI.
     * 
     * @see de.cau.cs.kieler.klighd.ui.view.DiagramView#storeCurrentSynthesisOptions
     */
    def storeCurrentSynthesisOptions() {
        synchronized (diagramState) {
            val viewer = diagramState.viewer
            if (viewer !== null && viewer.viewContext !== null) {
                val viewContext = viewer.viewContext
                val allUsedSynthesisOptions = new HashSet<SynthesisOption>
                val usedRootSynthesis = viewContext.diagramSynthesis

                // Save used syntheses.
                diagramState.addUsedSynthesis(usedRootSynthesis)

                // Find all available synthesis options for the currently used syntheses.
                if (usedRootSynthesis !== null) {
                    allUsedSynthesisOptions.addAll(usedRootSynthesis.displayedSynthesisOptions)
                }
                for (childVC : viewContext.getChildViewContexts(true)) {
                    diagramState.addUsedSynthesis(childVC.diagramSynthesis)
                    allUsedSynthesisOptions.addAll(childVC.diagramSynthesis.displayedSynthesisOptions)
                }

                // Save used options.
                for (option : allUsedSynthesisOptions) {
                    diagramState.addRecentSynthesisOption(option, viewContext.getOptionValue(option))
                }
            }
        }
    }
    
    /**
     * Updates the recent synthesis options with all synthesis options configured on the client.
     * 
     * @param viewContext The view context to compare the synthesis options against.
     */
    def void configureSynthesisOptions(ViewContext viewContext) {
        var Map<SynthesisOption, Object> recentSynthesisOptions = null
        synchronized (diagramState) {
            recentSynthesisOptions = diagramState.recentSynthesisOptions
        }
        try {
            var JsonObject synthesisOptions
            synchronized (diagramState) {
                // Use an empty JSON object if the client does not specify synthesis options during initialization.
                synthesisOptions = diagramState.clientOptions?.asJsonObject?.get(SYNTHESIS_OPTION)?.asJsonObject
                    ?: new JsonObject
            }
            val List<String> configuredOptions = newArrayList
            for (option : synthesisOptions.entrySet) {
                val optionId = option.key
                if (option.value.isJsonPrimitive){
                    val optionValue = option.value.asJsonPrimitive
                    // Search an option with the same ID in the view context and configure it with the new value.
                    val availableOptions = viewContext.displayedSynthesisOptions
                    val matchedOption = availableOptions.findFirst [ it.id == optionId ]
                    if (matchedOption !== null) {
                        val Object optionValueObject = optionValue.isBoolean ? optionValue.asBoolean
                                                     : optionValue.isNumber  ? optionValue.asNumber
                                                     : optionValue.isString  ? optionValue.asString
                        KGraphLanguageServerExtension.configureOption(matchedOption, optionValueObject, viewContext)
                        // Store the option with the new value in the recent options.
                        recentSynthesisOptions.put(matchedOption, viewContext.getOptionValue(matchedOption))
                        configuredOptions.add(optionId)
                    }
                } else {
                   println("Not a JSON Primitive: " + option.value)
                }
            }
            // These options now already have been configured here, so remove them from being configured again.
            for (configuredOption : configuredOptions) {
                synthesisOptions.remove(configuredOption)
            }
        } catch (Exception e) {
            println("Could not load client-side synthesis options.")
            e.printStackTrace
        }
    }

    /**
     * Makes the protected updateDiagram(List<URI>) method accessible from the outside.
     */
    def updateDiagrams2(List<URI> uris) {
        updateDiagrams(uris)
    }
    
    /**
     * Gets the next diagram piece requested by the client.
     */
    def SModelElement getNextDiagramPiece(KGraphDiagramServer server, RequestDiagramPieceAction request) {
        synchronized (diagramState) {
            val requestManager = diagramState.getDiagramPieceRequestManager(server.sourceUri)
            val diagramGenerator = requestManager.diagramGenerator
            val piece = requestManager.processRequest(request)
            
            // TODO: diagram state now contains redundant info, because through the request manager and the generator
            //       stuff is stored twice
            diagramState.putKGraphToSModelElementMap(server.sourceUri, diagramGenerator.getKGraphToSModelElementMap)
            diagramState.putIdToKGraphElementMap(server.sourceUri, diagramGenerator.idToKGraphElementMap)
            diagramState.putImageData(server.sourceUri, diagramGenerator.images)
            // map layout info onto new piece
            // TODO: don't actually need to do this on everything every time, should be replaced by method to map only to 
            //       elements which haven't yet received their info
            KGraphMappingUtil.mapLayout(diagramState.getKGraphToSModelElementMap(server.sourceUri))
            
            return piece
        }
        
    }
    
}
