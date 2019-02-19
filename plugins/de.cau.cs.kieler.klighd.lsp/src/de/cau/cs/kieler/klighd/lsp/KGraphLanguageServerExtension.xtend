/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import com.google.inject.Inject
import com.google.inject.Provider
import com.google.inject.Singleton
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.incremental.IncrementalUpdateStrategy
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.model.GetOptionParam
import de.cau.cs.kieler.klighd.lsp.model.SKGraph
import de.cau.cs.kieler.klighd.lsp.model.SetOptionParam
import de.cau.cs.kieler.klighd.lsp.model.ValuedSynthesisOption
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import io.typefox.sprotty.api.ActionMessage
import io.typefox.sprotty.api.IDiagramServer
import io.typefox.sprotty.api.RequestModelAction
import io.typefox.sprotty.server.xtext.ILanguageAwareDiagramServer
import io.typefox.sprotty.server.xtext.LanguageAwareDiagramServer
import io.typefox.sprotty.server.xtext.ide.IdeLanguageServerExtension
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.concurrent.CompletableFuture
import org.eclipse.xtext.util.CancelIndicator

import static io.typefox.sprotty.api.ServerStatus.Severity.*

/**
 * Language server extension that implements functionality for the generation of diagrams and handling of their diagram
 * servers.
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nir
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangLanguageServerExtension.xtend">
 *      YangLanguageServerExtension</a>
 */
@Singleton
class KGraphLanguageServerExtension extends IdeLanguageServerExtension 
    implements IDiagramOptionsLanguageServerExtension {
    
    /**
     * The {@link Provider} to call an injected {@link KGraphDiagramGenerator} to generate {@link KNode KGraphs} and 
     * {@link SKGraph}s from that.
     */
    @Inject
    Provider<KGraphDiagramGenerator> diagramGeneratorProvider
    
    /**
     * Stores data for the generation of diagrams.
     */
    @Inject
    KGraphDiagramState diagramState
	
	override protected initializeDiagramServer(IDiagramServer server) {
		super.initializeDiagramServer(server)
		val kGraphAware = server as KGraphAwareDiagramServer
		kGraphAware.needsServerLayout = true
		kGraphAware.needsClientLayout = false
		LOG.info("Created diagram server for " + server.clientId)
	}
	
	override didClose(String clientId) {
	    // clear the diagramState of this client id additional to the default use of this method.
	    synchronized (diagramState) {
	        diagramState.remove(clientId)
	    }
		super.didClose(clientId)
		LOG.info("Removed diagram server for " + clientId)
	}
	
	override accept(ActionMessage message) {
        val server = getDiagramServer(message.clientId)
        // if a diagram server is requested for the same client, but a different source file, then close the old server.
        if (message.action instanceof RequestModelAction
            && !server.options.empty // if the server does not have options yet, the server has not been used yet and
            // does not need to be relaunched.
            && !(message.action as RequestModelAction).options.get("sourceUri")
                .equals(server.options.get("sourceUri"))) {
                didClose(message.clientId)
            }
        super.accept(message)
    }
	
	override protected doUpdateDiagrams(String path, List<? extends ILanguageAwareDiagramServer> diagramServers) {
        if (diagramServers.empty) {
            return CompletableFuture.completedFuture(null)
        }
        return path.doRead [ context |
            val status = context.resource.shouldGenerate(context.cancelChecker)
            val uri = context.resource.URI.toString
            var Object snapshotModel = null
            synchronized(diagramState) {
                snapshotModel = diagramState.getSnapshotModel(path)
            }
            val model = if (snapshotModel === null) {
                            context.resource.contents.head
                        } else {
                            snapshotModel
                        }
            val cancelChecker = context.cancelChecker
            
            return (diagramServers as List<KGraphAwareDiagramServer>).map [ server |
                server -> {
                    server.status = status
                    if (status.severity !== ERROR) {
                        createModel(server, model, uri, cancelChecker)
                    } else {
                        null
                    }
                }
            ]
        ].thenAccept [ resultList |
            // call the text size estimation on the diagram server for which a new diagram got created.
            resultList.filter[value !== null].forEach[key.requestTextSizesAndUpdateModel(value)]
        ].exceptionally [ throwable |
            LOG.error('Error while processing build results', throwable)
            return null
        ]
    }
    
    protected def createModel(KGraphAwareDiagramServer server, Object model, String id, CancelIndicator cancelChecker) {
        
        val properties = new KlighdSynthesisProperties()
        var SprottyViewer viewer = null
        synchronized (diagramState) {
            val iViewer = diagramState.getViewer()
            if (iViewer instanceof SprottyViewer) {
                viewer = iViewer
            }
        }
        // TODO: get synthesis described by the user on the client
        // see DiagramView#doUpdateDiagram
//        val synthesisId = 

        // Save previous synthesis options to restore later
         storeCurrentSynthesisOptions()
        // configure options
        var Map<SynthesisOption, Object> recentSynthesisOptions = null
        synchronized (diagramState) {
            recentSynthesisOptions = diagramState.recentSynthesisOptions
        }
        properties.configureSynthesisOptionValues(recentSynthesisOptions)
        
        // Indicated if the model type changed against the current model
        var modelTypeChanged = false
        var ViewContext viewContext = null
        
        if (viewer === null || viewer.viewContext === null) {
            // if viewer or context does not exist always init view
            modelTypeChanged = true
        } else {
            viewContext = viewer.viewContext // TODO: is this the old VC?
            if (viewContext.inputModel === null || viewContext.inputModel.class !== model.class) {
                modelTypeChanged = true
            }
            // TODO:
//            if (!KlighdDataManager.instance.getSynthesisID(viewContext.getDiagramSynthesis()).equals(synthesisID)) {
//                // In case the synthesis changed the sidebar should be updated
//                modelTypeChanged = true
//            }
        }
        
        // If the type changed the view must be reinitialized to provide a correct ViewContext
        // otherwise the ViewContext can be simply updated
        if (modelTypeChanged) {
            // Configure the ViewContext and the Klighd synthesis to generate the KGraph model correctly.
            properties.useViewer(SprottyViewer.ID)
                .useUpdateStrategy(IncrementalUpdateStrategy.ID)
            // needs to be a IDiagramWorkbenchPart, as it calls the standard constructor.
            // TODO: The ViewContext should have a default constructor for non-SWT-based viewer.
            viewContext = new ViewContext(null as IDiagramWorkbenchPart, model).configure(properties)
            viewer = viewContext.createViewer(null, null) as SprottyViewer
            viewer.viewContext = viewContext
            // Update the model and with that call the diagram synthesis.
            viewContext.update(model)
        } else {
            viewContext.copyProperties(properties)
            viewContext.update(model)
        }

        synchronized (diagramState) {
            diagramState.putURIString(server.clientId, id)
            diagramState.putKGraphContext(id, viewContext)
            if (viewer !== null) {
                diagramState.putViewer(viewer)
            }
        }
        
        // generate the SGraph model from the KGraph model and store every later relevant part in the
        // diagram state.
        val diagramGenerator = diagramGeneratorProvider.get
        val sGraph = diagramGenerator.toSGraph(viewContext.viewModel, id, cancelChecker)
        synchronized (diagramState) {
            diagramState.putKGraphToSModelElementMap(id, diagramGenerator.getKGraphToSModelElementMap)
            diagramState.putTexts(id, diagramGenerator.getModelLabels)
            diagramState.putTextMapping(id, diagramGenerator.getTextMapping)
        }
        // finally, match the diagram server with the generated SGraph by returning the SGraph.
        sGraph
    }
    
    /**
     * Stores the current synthesisOptions configured in the current {@link ViewContext}.
     * Similar to storing the options in Eclipse UI.
     * 
     * @see de.cau.cs.kieler.klighd.ui.view.DiagramView#storeCurrentSynthesisOptions
     */
    def storeCurrentSynthesisOptions() {
        synchronized(diagramState) {
            val viewer = diagramState.viewer
            if (viewer !== null && viewer.viewContext !== null) {
                val viewContext = viewer.viewContext
                val allUsedSynthesisOptions = new HashSet<SynthesisOption>
                val usedRootSynthesis = viewContext.diagramSynthesis
                
                // Save used syntheses
                diagramState.addUsedSynthesis(usedRootSynthesis)
                
                // Find all available synthesis options for the currently used syntheses
                allUsedSynthesisOptions.addAll(usedRootSynthesis.displayedSynthesisOptions)
                for (childVC : viewContext.getChildViewContexts(true)) {
                    diagramState.addUsedSynthesis(childVC.diagramSynthesis)
                    allUsedSynthesisOptions.addAll(childVC.diagramSynthesis.displayedSynthesisOptions)
                }
                
                // Save used options
                for (option : allUsedSynthesisOptions) {
                    diagramState.putRecentSynthesisOption(option, viewContext.getOptionValue(option))
                }
            }
        }
    }
    
    override getOptions(GetOptionParam param) {
        return param.uri.doRead [ context |
            synchronized (diagramState) {
                val ViewContext viewContext = diagramState.getKGraphContext(context.resource.URI.toString)
                if (viewContext === null) {
                    // A diagram for this file is currently not opened, so no options can be shown.
                    return null
                }
                // TODO: what about the displayedLayoutOptions and the displayedActions?
                val synthesisOptions = new ArrayList<ValuedSynthesisOption>
                val recentSynthesisOptions = diagramState.recentSynthesisOptions
                for (option : viewContext.displayedSynthesisOptions) {
                    val currentValue = recentSynthesisOptions.get(option)
                    synthesisOptions.add(new ValuedSynthesisOption(option, currentValue))
                }
                return synthesisOptions
            }
        ]
    }
    
    override setOptions(SetOptionParam param) {
        return param.uri.doRead [ context |
            synchronized(diagramState) {
                val ViewContext viewContext = diagramState.getKGraphContext(context.resource.URI.toString)
                if (viewContext === null) {
                    // The diagram has already been closed
                    return "ERR"
                }
                val synthesisOptions = viewContext.displayedSynthesisOptions
                for (paramSynthesisOption : param.synthesisOptions) {
                    // The options in the parameter are a newly generated object, so it needs to be matched to the 
                    // option of the viewContext.
                    val synthesisOption = synthesisOptions.findFirst [
                        System.identityHashCode(it) === paramSynthesisOption.sourceHash
                    ]
                    if (synthesisOption === null) {
                        // A changed option cannot be found.
                        return "ERR"
                    }
                    else {
                        configureOption(synthesisOption, paramSynthesisOption.currentValue, viewContext)
                    }
                }
                this.doUpdateDiagrams(#[context.resource.URI])
                return "OK"
            }
        ]
    }
    
    /**
     * configures the {@code option} of the {@code viewContext} with the new {@code value} while regarding some special
     * cases that arise when using an arbitrary Object as the value.
     */
    def void configureOption(SynthesisOption option, Object value, ViewContext viewContext) {
        if (option.isChoiceOption) {
            // Choice options are predefined with non-primitive types, so try to match the
            // paramSynthesisOption with one of the options available for this choice.
            if (option.values.contains(value)) {
                // If the synthesis option values directly contain the new one from the parameter, just
                // overwrite it.
                viewContext.configureOption(option, value)
                return
            }
            // If the string representation matches between an option value and the new value, use that.
            var newOption = option.values.findFirst[toString.equals(value.toString)]
            if (option.values.contains(newOption)) {
                viewContext.configureOption(option, newOption)
                return
            }
            if (newOption instanceof String) {
                println("Here")
            }
            // Every number (including int) will be represented as a double in a possible JavaScript server.
            // Because of that, try to match the new value to an int.
            if (value instanceof Double
                && Math.rint(value as Double) == value
            ) {
                val intValue = Math.round(value as Double)
                newOption = option.values.findFirst[toString.equals(intValue.toString)]
                if (option.values.contains(newOption)) {
                    viewContext.configureOption(option, newOption)
                    return
                }
                // try to view the option as an Enum. If the ordinal matches, take that option.
                for (optionValue: option.values) {
                    if (optionValue instanceof Enum<?>
                        && (optionValue as Enum<?>).ordinal == intValue
                    ) {
                        viewContext.configureOption(option, optionValue)
                        return
                    }
                }
            }
        } else if (option.isRangeOption) {
            val lowerBound = option.range.first
            val upperBound = option.range.second
            val stepSize = option.stepSize
            val initialValue = option.initialValue as Number
            if (lowerBound.equals(lowerBound.intValue())
                && upperBound.equals(upperBound.intValue())
                && stepSize.equals(stepSize.intValue())
                && initialValue.equals(initialValue.intValue())) {
                // The option contains an Integer
                viewContext.configureOption(option, Integer.parseInt(value as String))
                return
            } else {
                // The option contains a Float
                viewContext.configureOption(option, Float.parseFloat(value as String))
                return
            }
        } else {
            viewContext.configureOption(option, value)
            return
        }
    }
    
    /**
     * Creates and sends the diagram for an arbitrary snapshot object for any source model to the client.
     */
    def showSnapshot(String uri, String clientId, Object model, CancelIndicator cancelIndicator, boolean update) {
        if (!update) {
            // check if some diagram server already has a diagram for this uri.
            val closeClientIds = new ArrayList
            diagramServers.forEach[ cId, diagramServer |
                if (clientId.equals(cId)) {
                    closeClientIds.add(cId)
                }
            ]
            // if there is one, close it and open the diagram with a new, initialized diagram server
            closeClientIds.forEach[this.didClose(it)]
        }
        
        val diagramServer = this.getDiagramServer(clientId)
        if (diagramServer instanceof KGraphAwareDiagramServer) {
            synchronized(diagramState) {
                diagramState.putSnapshotModel(uri, model)
            }
            diagramServer.initializeOptions(#{
                LanguageAwareDiagramServer.OPTION_SOURCE_URI -> uri
            })
            // with that new diagram server, do a similar procedure to generate a diagram as for usual diagrams (except,
            // use the 'model' as its model.
            val sGraph = this.createModel(diagramServer, model, uri, cancelIndicator)
            if (sGraph !== null) {
                diagramServer.requestTextSizesAndUpdateModel(sGraph)
            }
            return "OK"
        }
        return "ERR"
    }
}
