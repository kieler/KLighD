/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2019 by
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
import de.cau.cs.kieler.klighd.IAction.ActionContext
import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.lsp.model.GetOptionParam
import de.cau.cs.kieler.klighd.lsp.model.GetOptionsResult
import de.cau.cs.kieler.klighd.lsp.model.LayoutOptionUIData
import de.cau.cs.kieler.klighd.lsp.model.PerformActionParam
import de.cau.cs.kieler.klighd.lsp.model.SetLayoutOptionsParam
import de.cau.cs.kieler.klighd.lsp.model.SetSynthesesAction
import de.cau.cs.kieler.klighd.lsp.model.SetSynthesesActionData
import de.cau.cs.kieler.klighd.lsp.model.SetSynthesisOptionsParam
import de.cau.cs.kieler.klighd.lsp.model.ValuedSynthesisOption
import java.net.URLDecoder
import java.util.ArrayList
import java.util.Collection
import java.util.List
import java.util.Map
import java.util.concurrent.CompletableFuture
import org.eclipse.elk.core.LayoutConfigurator
import org.eclipse.elk.core.data.LayoutMetaDataService
import org.eclipse.elk.core.data.LayoutOptionData
import org.eclipse.elk.core.data.LayoutOptionData.Type
import org.eclipse.elk.core.data.LayoutOptionData.Visibility
import org.eclipse.elk.core.util.Pair
import org.eclipse.elk.graph.ElkGraphElement
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.lsp4j.DocumentHighlight
import org.eclipse.lsp4j.InitializeParams
import org.eclipse.lsp4j.TextDocumentPositionParams
import org.eclipse.sprotty.ActionMessage
import org.eclipse.sprotty.DiagramOptions
import org.eclipse.sprotty.IDiagramServer
import org.eclipse.sprotty.RequestModelAction
import org.eclipse.sprotty.xtext.DiagramHighlightService
import org.eclipse.sprotty.xtext.ls.SyncDiagramLanguageServer
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.UriExtensions
import org.eclipse.xtext.ide.server.occurrences.IDocumentHighlightService
import org.eclipse.xtext.util.CancelIndicator

/**
 * Language server extension that implements functionality for the generation of diagrams and handling of their diagram
 * servers.
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nre
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangLanguageServerExtension.xtend">
 *      YangLanguageServerExtension</a>
 */
@Singleton
class KGraphLanguageServerExtension extends SyncDiagramLanguageServer
    implements IDiagramOptionsLanguageServerExtension, IPreferencesExtension {
    @Inject
    Provider<DiagramHighlightService> diagramHighlightServiceProvider
    
    /**
     * Option to indicate if selected elements in the text should also automatically select and focus the elements in
     * the diagram. Default value is false, if not requested differently by the client.
     */
    @Accessors
    boolean shouldSelectDiagram = false
    
    /**
     * Option to indicate if selected elements in the diagram should also automatically select the elements in the text.
     * Default value is false, if not requested differently by the client.
     */
    @Accessors
    boolean shouldSelectText = false
    
    /**
     * Stores data for the generation of diagrams.
     */
    @Inject
    KGraphDiagramState diagramState
    
    @Inject extension UriExtensions
    
    override initialize(InitializeParams params) {
        // Close all diagram servers still open from a previous session.
        val oldClientIds = diagramServerManager.diagramServers.map[ clientId ].toList // toList to avoid lazy evaluation
        oldClientIds.forEach[ didClose ]
        return super.initialize(params)
    }
    
    override didClose(String clientId) {
        // Clear the diagramState of this client id additional to the default use of this method.
        synchronized (diagramState) {
            diagramState.remove(clientId)
        }
        super.didClose(clientId)
    }
    
    override accept(ActionMessage message) {
        if (message.action instanceof RequestModelAction) {
            val action = message.action as RequestModelAction
            val diagramType = action.diagramType
                    ?: action.options.get(DiagramOptions.OPTION_DIAGRAM_TYPE)
            val server = diagramServerManager.getDiagramServer(diagramType, message.clientId)
            // If a diagram server is requested for the same client, but a different source file, then close the old server.
            if (!server.options.empty // If the server does not have options yet, the server has not been used yet and
                // does not need to be relaunched.
                && !action.options.get("sourceUri")
                    .equals(server.options.get("sourceUri"))) {

                didClose(message.clientId)
            }
            // After a new server has been initialized, also send the available syntheses to the client.
            val path = action.options.get("sourceUri")
            val newServer = diagramServerManager.getDiagramServer(diagramType, message.clientId)
            sendAvailableSyntheses(path, newServer)
        }
        super.accept(message)
    }
    
    /**
     * Sends the available syntheses for the document pointed to by {@code path} via the the given diagram server.
     * 
     * @param path The uri pointing towards the document that represents a model with available syntheses
     * @param server The diagram server that belongs to the document.
     */
    def void sendAvailableSyntheses(String path, IDiagramServer server) {
        if (path !== null) {
            doRead(path) [ resource, ci |
                val availableSynthesesData = getAvailableSynthesesData(resource.contents.head?.class)
                
                server.dispatch(new SetSynthesesAction(availableSynthesesData))
                return null
            ]
        }
    }
    
    /**
     * Calculates ready-to-send data for the {@link SetSynthesesAction}.
     * 
     * @param currentModelClass the class for which the available syntheses should be evaluated.
     */
    def List<SetSynthesesActionData> getAvailableSynthesesData(Class<?> currentModelClass) {
        val KlighdDataManager kdm = KlighdDataManager.instance
        return kdm.getAvailableSyntheses(currentModelClass).map [
            val id = kdm.getSynthesisID(it)
            val displayedName = if (id.contains(".") && !id.endsWith(".")) {
                    id.substring(id.lastIndexOf('.') + 1);
                } else {
                    id
                }
            return new SetSynthesesActionData(id, displayedName)
        ].toList
    }
    
    override getOptions(GetOptionParam param) {
        return doRead(param.uri) [ resource, ci |
            synchronized (diagramState) {
                val ViewContext viewContext = diagramState.getKGraphContext(resource.URI.toString)
                if (viewContext === null) {
                    // A diagram for this file is currently not opened, so no options can be shown.
                    return null
                }
                val synthesisOptions = new ArrayList<ValuedSynthesisOption>
                val recentSynthesisOptions = diagramState.recentSynthesisOptions
                for (option : viewContext.displayedSynthesisOptions) {
                    val currentValue = recentSynthesisOptions.get(option)
                    synthesisOptions.add(new ValuedSynthesisOption(option, currentValue))
                }
                val layoutOptionUIData = calculateLayoutOptionUIData(viewContext.displayedLayoutOptions)
                
                return new GetOptionsResult(synthesisOptions, layoutOptionUIData,
                    viewContext.displayedActions)
            }
        ]
    }
    
    def calculateLayoutOptionUIData(List<Pair<IProperty<?>, List<?>>> displayedLayoutOptions) {
        val List<LayoutOptionUIData> layoutOptionUIData = new ArrayList
        for (pair : displayedLayoutOptions) {
            var Object first
            var Object second
            if (pair.second instanceof Collection) {
                val iterator = (pair.second as Collection<?>).iterator
                first = if (iterator.hasNext) iterator.next else null
                second = if (iterator.hasNext) iterator.next else null
            } else {
                first = null
                second = null
            }

            val LayoutOptionData optionData = LayoutMetaDataService.instance.getOptionData(pair.first.id)
            if (optionData.visibility !== Visibility.HIDDEN) {
                if (first instanceof Number && second instanceof Number) {
                    layoutOptionUIData.add(new LayoutOptionUIData(optionData, (first as Number).floatValue,
                        (second as Number).floatValue, null))
                } else if (pair.second === null) {
                    layoutOptionUIData.add(new LayoutOptionUIData(optionData, null, null, null))
                } else {
                    layoutOptionUIData.add(new LayoutOptionUIData(optionData, null, null, pair.second))
                }
            }
        }
        return layoutOptionUIData
    }
    
    override setSynthesisOptions(SetSynthesisOptionsParam param) {
        return doRead(param.uri) [ resource, ci |
            synchronized (diagramState) {
                val ViewContext viewContext = diagramState.getKGraphContext(resource.URI.toString)
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
                // Update the diagram.
                if (diagramUpdater instanceof KGraphDiagramUpdater) {
                    (diagramUpdater as KGraphDiagramUpdater).updateDiagrams2(#[resource.URI])
                    return "OK"
                }
                return "ERR"
            }
        ]
    }
    
    override setLayoutOptions(SetLayoutOptionsParam param) {
        return doRead(param.uri) [ resource, ci |
            synchronized (diagramState) {
                val key = resource.URI.toString
                val LayoutConfigurator layoutConfig = diagramState.getLayoutConfig(key)
                if (layoutConfig === null) {
                    // The diagram has already been closed
                    return "ERR"
                }
                
                // Set the new option.
                for (layoutOption : param.layoutOptions) {
                    val LayoutOptionData optionData = LayoutMetaDataService.instance.getOptionData(layoutOption.optionId)
                    if (optionData.type === Type.ENUM || optionData.type === Type.ENUMSET) {
                        // Sending it to the server via Json, the Enum will be transformed to a number, which is always
                        // interpreted as a Double back in Java. So convert it back to its enum here.
                        val value = optionData.getEnumValue((layoutOption.value as Double).intValue)
                        layoutConfig.configure(ElkGraphElement).setProperty(optionData, value);
                    } else if (optionData.type === Type.DOUBLE) {
                        layoutConfig.configure(ElkGraphElement).setProperty(optionData,
                            Double.parseDouble(layoutOption.value.toString))
                    } else if (optionData.type === Type.BOOLEAN) {
                        layoutConfig.configure(ElkGraphElement).setProperty(optionData, layoutOption.value)
                    } else { // TODO: implement the other cases, if necessary.
                        layoutConfig.configure(ElkGraphElement).setProperty(optionData, layoutOption.value);
                    }
                }
                diagramState.putLayoutConfig(key, layoutConfig)
                
                // Update the layout of the diagram.
                val diagramServer = this.diagramServerManager.findDiagramServersByUri(key).head
                if (diagramUpdater instanceof KGraphDiagramUpdater && diagramServer instanceof KGraphDiagramServer) {
                    (diagramUpdater as KGraphDiagramUpdater).updateLayout(diagramServer as KGraphDiagramServer)
                } else {
                    return "ERR"
                }
            }
            return "OK"
        ]
    }
    
    override performAction(PerformActionParam param) {
        synchronized (diagramState) {
            val klighdAction = KlighdDataManager.instance.getActionById(param.actionId)
            val viewer = diagramState.viewer
            val actionContext = new ActionContext(viewer, null, null, null)
            val actionResult = klighdAction.execute(actionContext)
            if (actionResult.needsSynthesis) {
                if (diagramUpdater instanceof KGraphDiagramUpdater) {
                    val diagramServer = this.diagramServerManager.findDiagramServersByUri(param.uri)
                        .filter(KGraphDiagramServer).head
                    if (diagramServer !== null) {
                        (diagramUpdater as KGraphDiagramUpdater).updateDiagram(diagramServer)
                    } else {
                        return CompletableFuture.completedFuture("ERR")
                    }
                } else {
                    return CompletableFuture.completedFuture("ERR")
                }
            } else if (actionResult.actionPerformed) {
                if (diagramUpdater instanceof KGraphDiagramUpdater) {
                    val diagramServer = this.diagramServerManager.findDiagramServersByUri(param.uri)
                        .filter(KGraphDiagramServer).head
                    if (diagramServer !== null) {
                        (diagramUpdater as KGraphDiagramUpdater).updateLayout(diagramServer)
                    } else {
                        return CompletableFuture.completedFuture("ERR")
                    }
                } else {
                    return CompletableFuture.completedFuture("ERR")
                }
            }
        }
        return CompletableFuture.completedFuture("OK")
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
     * 
     * @param update Indicates if this call should update the same snapshot model.
     */
    def showSnapshot(String uri, String clientId, Object model, CancelIndicator cancelIndicator, boolean update) {
        if (!update) {
            // check if some diagram server already has a diagram for this uri.
            val closeClientIds = new ArrayList
            diagramServerManager.diagramServers.forEach[ diagramServer |
                if (clientId.equals(diagramServer.clientId)) {
                    closeClientIds.add(diagramServer.clientId)
                }
            ]
            // if there is one, close it and open the diagram with a new, initialized diagram server
            closeClientIds.forEach[this.didClose(it)]
        }
        
        val diagramServer = diagramServerManager.getDiagramServer('keith-diagram', clientId)
        if (diagramServer instanceof KGraphDiagramServer) {
            synchronized (diagramState) {
                diagramState.putSnapshotModel(uri, model)
            }
            diagramServer.initializeOptions(#{
                DiagramOptions.OPTION_SOURCE_URI -> uri,
                DiagramOptions.OPTION_NEEDS_CLIENT_LAYOUT -> "false",
                DiagramOptions.OPTION_NEEDS_SERVER_LAYOUT -> "true"
            })
            // With that new diagram server, do a similar procedure to generate a diagram as for usual diagrams (except,
            // use the 'model' as its model.
            if (diagramUpdater instanceof KGraphDiagramUpdater) {
                (diagramUpdater as KGraphDiagramUpdater).prepareModel(diagramServer, model, uri)
                (diagramUpdater as KGraphDiagramUpdater).updateLayout(diagramServer)
                // Also, update the syntheses available for the given diagram.
                if (!update) {
                    val availableSynthesesData = getAvailableSynthesesData(model.class)
                
                    diagramServer.dispatch(new SetSynthesesAction(availableSynthesesData))
                }
                return "OK"
            }
        }
        return "ERR"
    }
    
    override documentHighlight(TextDocumentPositionParams params) {
        // This skips the direct super implementation because we do not want the DiagramHighlightService to only look in
        // the language registry for an instance, but use the default injector used for this class to provide the
        // service, as all languages are registered in this injector. Prevents a NullPointerException in the super
        // implementation when no diagramHighlightService can be found.
        
        // this is from super.super.documentHighlight
        val CompletableFuture<List<? extends DocumentHighlight>> result = requestManager.runRead [ cancelIndicator |
            val uri = params.textDocument.uri.toUri;
            val serviceProvider = languagesRegistry
                .getResourceServiceProvider(uri)
            val service = serviceProvider?.get(IDocumentHighlightService);
            if (service === null)
                return emptyList
            
            return workspaceManager.doRead(uri) [doc, resource |
                service.getDocumentHighlights(doc, resource, params, cancelIndicator)
            ]
        ];
        if (shouldSelectDiagram) {
            val URI uri = params.textDocument.uri.toUri
            workspaceManager.doRead(uri) [ doc, resource |
                val diagramHighlightService = languagesRegistry
                    .getResourceServiceProvider(uri)
                    .get(DiagramHighlightService)
                    ?: diagramHighlightServiceProvider.get
                val offset = doc.getOffSet(params.position)
                diagramServerManager.findDiagramServersByUri(uri.toString).forEach [ server |
                    diagramHighlightService.selectElementFor(server, resource, offset)
                ]
                null
            ]
        }
        result
    }
    
    override setPreferences(Map<String, Object> prefs) {
        prefs.forEach[ key, value | 
            switch (key) {
                case "diagram.shouldSelectDiagram": {
                    if (value instanceof Boolean) {
                        this.shouldSelectDiagram = value
                    }
                }
                case "diagram.shouldSelectText": {
                    if (value instanceof Boolean) {
                        this.shouldSelectText = value
                    }
                }
            }
        ]
    }
    
    /**
     * Updates the layout of the diagram given by the uri and sends it to the client.
     * 
     * @param uri The uri of the model file.
     */
    def void updateLayout(String uri) {
        if (diagramUpdater instanceof KGraphDiagramUpdater) {
            (diagramUpdater as KGraphDiagramUpdater).updateLayout(
                diagramServerManager.findDiagramServersByUri(
                        URLDecoder.decode(uri, "UTF-8")
                    )?.head as KGraphDiagramServer
                )
        }
    }
    
    /**
     * Updates the diagram by calling a new synthesis and calculating the layout of the diagram given by the uri.
     * Sends the new diagram to the client.
     * 
     * @param uri The uri of the model file.
     */
    def void updateDiagram(String uri) {
        if (diagramUpdater instanceof KGraphDiagramUpdater) {
            (diagramUpdater as KGraphDiagramUpdater).updateDiagrams2(#[URI.createURI(URLDecoder.decode(uri, "UTF-8"))])
        }
    }
    
    /**
     * Provides read access to fully resolved resource, even if it is not an XTextResource.
     * Similar to {@link LanguageServerImpl.access#doRead(String, Function)} just without full read access to the
     * document.
     * 
     * @param path The path to the file that should be read.
     * @param work What should be done with the Resource.
     * 
     * @return The result of {@code work} as a completable future.
     */
    def <T> CompletableFuture<T> doRead(String path, (Resource, CancelIndicator)=>T work) {
        return requestManager.runRead [ ci |
            val resource = getResource(_uriExtensions.toUri(path))
            work.apply(resource, ci)
        ]
    }
    
    /**
     * Returns the resource in this workspace that is located by the given URI.
     * 
     * @param uri The URI.
     * @return The resource created by the file located by the URI
     */
    def Resource getResource(URI uri) {
        val ws = this.workspaceManager as KeithWorkspaceManager
        return ws.getResource(uri)
    }
}
