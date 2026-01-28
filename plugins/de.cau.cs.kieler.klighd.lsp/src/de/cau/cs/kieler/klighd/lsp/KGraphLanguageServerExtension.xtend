/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2026 by
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
import com.google.gson.internal.LazilyParsedNumber
import com.google.inject.Inject
import com.google.inject.Provider
import com.google.inject.Singleton
import de.cau.cs.kieler.klighd.IAction.ActionContext
import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.lsp.launch.AbstractLanguageServer
import de.cau.cs.kieler.klighd.lsp.model.PerformActionParam
import de.cau.cs.kieler.klighd.lsp.model.SetLayoutOptionsParam
import de.cau.cs.kieler.klighd.lsp.model.SetSynthesesAction
import de.cau.cs.kieler.klighd.lsp.model.SetSynthesesActionData
import de.cau.cs.kieler.klighd.lsp.model.SetSynthesisOptionsParam
import de.cau.cs.kieler.klighd.syntheses.ReinitializingDiagramSynthesisProxy
import java.net.URLDecoder
import java.util.ArrayList
import java.util.List
import java.util.Map
import java.util.concurrent.CompletableFuture
import org.eclipse.elk.core.LayoutConfigurator
import org.eclipse.elk.core.data.LayoutMetaDataService
import org.eclipse.elk.core.data.LayoutOptionData
import org.eclipse.elk.core.data.LayoutOptionData.Type
import org.eclipse.elk.graph.ElkGraphElement
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.lsp4j.DocumentHighlight
import org.eclipse.lsp4j.DocumentHighlightParams
import org.eclipse.lsp4j.InitializeParams
import org.eclipse.lsp4j.MessageParams
import org.eclipse.lsp4j.MessageType
import org.eclipse.lsp4j.services.LanguageClient
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
    implements IDiagramOptionsLanguageServerExtension, INotificationHandler, IPreferencesExtension {
        
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
     * Option to indicate if an incremental diagram generator should be used.
     */
    @Accessors
    boolean incrementalDiagramGenerator = false;
    
    /**
     * Stores data for the generation of diagrams.
     */
    @Inject
    KGraphDiagramState diagramState
    
    @Inject extension UriExtensions
    
    KGraphLanguageClient kgraphLanguageClient
    
    /**
     * The property of the {@link InitializeParams} containing the client-defined diagram options.
     */
    public static String CLIENT_DIAGRAM_OPTIONS_PROPERTY = "clientDiagramOptions"
    
    public static String CLIENT_COLOR_PREFERNENCES = "clientColorPreferences"
    
    override initialize(InitializeParams params) {
        // Close all diagram servers still open from a previous session.
        val oldClientIds = diagramServerManager.diagramServers.map[ clientId ].toList // toList to avoid lazy evaluation
        oldClientIds.forEach[ didClose ]
        val initializationOptions = params.initializationOptions
        if (initializationOptions instanceof JsonObject) {
            synchronized (diagramState) {
                diagramState.clientOptions = initializationOptions.get(CLIENT_DIAGRAM_OPTIONS_PROPERTY)
                diagramState.colorPreferences = LSPUtil.parseColorPreferences(initializationOptions.get(CLIENT_COLOR_PREFERNENCES))
            }
        }
        return super.initialize(params)
    }
    
    // Fixes a NPE during initialization caused by an Xtext issue when initializing with no baseURI and no workspaceFolders.
    // Should be removed again once https://github.com/eclipse-xtext/xtext/issues/3391 is resolved in a future Xtext release.
    override clientSupportsWorkspaceFolders() {
        return true
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
            val diagramType = action.options.get(DiagramOptions.OPTION_DIAGRAM_TYPE)
            val server = diagramServerManager.getDiagramServer(diagramType, message.clientId)
            // If a diagram server is requested for the same client, but a different source file, then close the old server.
            // If the server does not have options yet, the server has not been used yet and does not need to be relaunched.
            if (!server.options.empty
                && !action.options.get(DiagramOptions.OPTION_SOURCE_URI)
                    .equals(server.options.get(DiagramOptions.OPTION_SOURCE_URI))) {

                didClose(message.clientId)
            }
            // After a new server has been initialized, also send the available syntheses to the client.
            val path = action.options.get(DiagramOptions.OPTION_SOURCE_URI)
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
                val currentModelClass = resource?.contents?.head?.class
                if (currentModelClass !== null) {
                    val availableSynthesesData = getAvailableSynthesesData(currentModelClass)
                    
                    server.dispatch(new SetSynthesesAction(availableSynthesesData))
                }
                return null
            ]
        }
    }
    
    /**
     * Calculates ready-to-send data for the {@link SetSynthesesAction}.
     * 
     * @param currentModelClass The class for which the available syntheses should be evaluated.
     * @return A list of the IDs and displayable names of all available syntheses.
     */
    def List<SetSynthesesActionData> getAvailableSynthesesData(Class<?> currentModelClass) {
        synchronized (diagramState) {
            val KlighdDataManager kdm = KlighdDataManager.instance
            return kdm.getAvailableSyntheses(currentModelClass).map [
                val synthesisId = kdm.getSynthesisID(it)
                var displayedName = ""
                if (it instanceof ReinitializingDiagramSynthesisProxy) {
                    displayedName = it.delegate.class.simpleName
                } else {
                    displayedName = it.class.simpleName
                }
                return new SetSynthesesActionData(synthesisId, displayedName)
            ].toList
        }
    }
    
    override setSynthesisOptions(SetSynthesisOptionsParam param) {
        val decodedUri = URLDecoder.decode(param.uri, "UTF-8")
        doRead(decodedUri) [ resource, ci |
            synchronized (diagramState) {
                val ViewContext viewContext = diagramState.getKGraphContext(decodedUri)
                if (viewContext === null) {
                    sendErrorAndThrow(new IllegalStateException("The diagram has already been closed."))
                }
                val synthesisOptions = viewContext.displayedSynthesisOptions
                
                // Indicates if the diagram should be updated with this new synthesis options.
                // If only categories are updated, do not update the diagram.
                var update = false 
                for (paramSynthesisOption : param.synthesisOptions) {
                    // The options in the parameter are a newly generated object, so it needs to be matched to the 
                    // option of the viewContext.
                    val synthesisOption = synthesisOptions.findFirst [
                        // TODO: replace this with the option ID
                        System.identityHashCode(it) === paramSynthesisOption.sourceHash
                    ]
                    if (synthesisOption === null) {
                        throw new IllegalStateException("A changed option cannot be found.")
                    }
                    else {
                        configureOption(synthesisOption, paramSynthesisOption.currentValue, viewContext)
                        if (!synthesisOption.isCategory) {
                            update = true
                        }
                    }
                }
                // Update the diagram.
                if (diagramUpdater instanceof KGraphDiagramUpdater) {
                    if (update) {
                        AbstractLanguageServer.addToMainThreadQueue([
                            (diagramUpdater as KGraphDiagramUpdater).updateDiagrams2(#[_uriExtensions.toUri(decodedUri)])
                        ])
                    } 
                    return null
                }
                throw new IllegalStateException("The diagramUpdater is not setup correctly.")
            }
        ]
    }
    
    override setLayoutOptions(SetLayoutOptionsParam param) {
        val decodedUri = URLDecoder.decode(param.uri, "UTF-8")
        doRead(decodedUri) [ resource, ci |
            synchronized (diagramState) {
                val LayoutConfigurator layoutConfig = diagramState.getLayoutConfig(decodedUri)
                if (layoutConfig === null) {
                    throw new IllegalStateException("The diagram has already been closed")
                }
                
                // Set the new option.
                for (layoutOption : param.layoutOptions) {
                    val LayoutOptionData optionData = LayoutMetaDataService.instance.getOptionData(layoutOption.optionId)
                    if (optionData.type === Type.ENUM) {
                        // Sending it to the server via Json, the Enum will be transformed to a number, which is always
                        // interpreted as a Double back in Java. So convert it back to its enum here.
                        val value = optionData.getEnumValue((layoutOption.value as Double).intValue)
                        layoutConfig.configure(ElkGraphElement).setProperty(optionData, value);
                    } else if (optionData.type === Type.DOUBLE) {
                        layoutConfig.configure(ElkGraphElement).setProperty(optionData,
                            Double.parseDouble(layoutOption.value.toString))
                    } else if (optionData.type === Type.BOOLEAN) {
                        layoutConfig.configure(ElkGraphElement).setProperty(optionData, layoutOption.value)
                    } else {
                        layoutConfig.configure(ElkGraphElement).setProperty(optionData, layoutOption.value);
                    }
                }
                diagramState.putLayoutConfig(decodedUri, layoutConfig)
                
                // Update the layout of the diagram.
                val diagramServer = this.diagramServerManager.findDiagramServersByUri(decodedUri).head
                if (diagramUpdater instanceof KGraphDiagramUpdater && diagramServer instanceof KGraphDiagramServer) {
                    AbstractLanguageServer.addToMainThreadQueue([
                        (diagramUpdater as KGraphDiagramUpdater).updateLayout(diagramServer as KGraphDiagramServer)
                    ])
                } else {
                    throw new IllegalStateException("The diagram server or diagram updater are not set up correctly.")
                }
            }
            return null
        ]
    }
    
    override performAction(PerformActionParam param) {
        val decodedUri = URLDecoder.decode(param.uri, "UTF-8")
        try {
            synchronized (diagramState) {
                // Find the action and execute it.
                val klighdAction = KlighdDataManager.instance.getActionById(param.actionId)
                val viewer = diagramState.viewer
                val actionContext = new ActionContext(viewer, null, null, null)
                val actionResult = klighdAction.execute(actionContext)
                if (actionResult.needsSynthesis) {
                    // If the action requires a synthesis re-run, do that by invoking the diagram updater.
                    if (diagramUpdater instanceof KGraphDiagramUpdater) {
                        val diagramServer = this.diagramServerManager.findDiagramServersByUri(decodedUri)
                            .filter(KGraphDiagramServer).head
                        if (diagramServer !== null) {
                            AbstractLanguageServer.addToMainThreadQueue([
                                diagramUpdater.updateDiagram(diagramServer)
                            ])
                        } else {
                            throw new IllegalStateException("The diagram server is not set up correctly.")
                        }
                    } else {
                        throw new IllegalStateException("The diagram updater is not set up correctly.")
                    }
                } else if (actionResult.actionPerformed) {
                    // If the action does not require a new synthesis, but only a new layout, do that again by invoking the
                    // diagram updater.
                    if (diagramUpdater instanceof KGraphDiagramUpdater) {
                        val diagramServer = this.diagramServerManager.findDiagramServersByUri(decodedUri)
                            .filter(KGraphDiagramServer).head
                        if (diagramServer !== null) {
                            AbstractLanguageServer.addToMainThreadQueue([
                                (diagramUpdater as KGraphDiagramUpdater).updateLayout(diagramServer)
                            ])
                        } else {
                            throw new IllegalStateException("The diagram server is not set up correctly.")
                        }
                    } else {
                        throw new IllegalStateException("The diagram updater is not set up correctly.")
                    }
                }
            }
        } catch (Exception e) {
            sendErrorAndThrow(e)
        }
    }
    
    /**
     * Configures the {@code option} of the {@code viewContext} with the new {@code value} while regarding some special
     * cases that arise when using an arbitrary Object as the value.
     * 
     * @param option The synthesis option
     * @param value The value for that option
     * @param viewContext The current {@code ViewContext} in which the option is configured
     */
    static def void configureOption(SynthesisOption option, Object value, ViewContext viewContext) {
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
                if (value instanceof Double) {
                    viewContext.configureOption(option, Math.round(value).intValue)
                } else if (value instanceof LazilyParsedNumber) {
                    viewContext.configureOption(option, value.intValue)
                } else {
                    viewContext.configureOption(option, Integer.parseInt(value as String))
                }
                return
            } else {
                // The option contains a Float
                if (value instanceof Double) {
                    viewContext.configureOption(option, value.floatValue)
                } else if (value instanceof LazilyParsedNumber) {
                    viewContext.configureOption(option, value.floatValue)
                } else {
                    viewContext.configureOption(option, Float.parseFloat(value as String))
                }
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
     * @param uri The uri of the original model file
     * @param clientId The id of the corresponding diagram.
     * @param cancelIndicator The cancel indicator
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
                synchronized (diagramState) {
                    (diagramUpdater as KGraphDiagramUpdater).prepareModel(diagramServer, model, uri)
                    AbstractLanguageServer.addToMainThreadQueue([
                        (diagramUpdater as KGraphDiagramUpdater).updateLayout(diagramServer)
                    ])
                }
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
    
    override documentHighlight(DocumentHighlightParams params) {
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
                case "diagram.incrementalDiagramGenerator": {
                    if (value instanceof Boolean) {
                        this.incrementalDiagramGenerator = value
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
     * Similar to the {@code doRead(String, Function)} function in the private {@code LanguageServerImpl.access} field,
     * just without full read access to the document.
     * 
     * @param path The path to the file that should be read.
     * @param work What should be done with the Resource.
     * 
     * @return The result of {@code work} as a completable future.
     */
    def <T> CompletableFuture<T> doRead(String path, (Resource, CancelIndicator)=>T work) {
        return requestManager.runRead [ ci |
            try {
                val resource = getResource(_uriExtensions.toUri(path))
                return work.apply(resource, ci)
            } catch (Exception e) {
                sendErrorAndThrow(e)
            }
        ]
    }
    
    /**
     * Returns the resource in this workspace that is located by the given URI.
     * 
     * @param uri The URI.
     * @return The resource created by the file located by the URI.
     */
    def Resource getResource(URI uri) {
        val ws = this.workspaceManager as KeithWorkspaceManager
        return ws.getResource(uri)
    }
    
    /**
     * Returns the resource in this workspace that is located by the given URI path.
     * 
     * @param uriPath The path of the URI.
     * @return The resource created by the file located by the URI's path.
     */
    def Resource getResource(String uriPath) {
        return getResource(_uriExtensions.toUri(uriPath))
    }
    
    /**
     * Connect the language client. Allows to send KGraph specific notification to the client if the given client is
     * indeed a {@code KGraphLanguageClient}.
     * 
     * @param client The language client.
     */
    override connect(LanguageClient client) {
        if (client instanceof KGraphLanguageClient) {
            this.kgraphLanguageClient = client as KGraphLanguageClient
        }
        super.connect(client)
    }
    
    override <T> T sendErrorAndThrow(Throwable t) throws Throwable {
        sendError(Throwables.getStackTraceAsString(t))
        throw t
    }
    
    override sendError(String message) {
        if (this.kgraphLanguageClient !== null) {
            this.kgraphLanguageClient.showMessage(new MessageParams(MessageType.Error, LSPUtil.escapeHtml(message)))
            return true
        }
        return false
    }
    
    override sendWarning(String message) {
        if (this.kgraphLanguageClient !== null) {
            this.kgraphLanguageClient.showMessage(new MessageParams(MessageType.Warning, LSPUtil.escapeHtml(message)))
            return true
        }
        return false
    }
    
    override sendInfo(String message) {
        if (this.kgraphLanguageClient !== null) {
            this.kgraphLanguageClient.showMessage(new MessageParams(MessageType.Info, LSPUtil.escapeHtml(message)))
            return true
        }
        return false
    }
}
