/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2024 by
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
import com.google.common.io.ByteStreams
import com.google.inject.Inject
import com.google.inject.Injector
import de.cau.cs.kieler.klighd.IAction
import de.cau.cs.kieler.klighd.IAction.ActionContext
import de.cau.cs.kieler.klighd.Klighd
import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.model.CheckImagesAction
import de.cau.cs.kieler.klighd.lsp.model.CheckedImagesAction
import de.cau.cs.kieler.klighd.lsp.model.ClientColorPreferencesAction
import de.cau.cs.kieler.klighd.lsp.model.DisplayedActionUIData
import de.cau.cs.kieler.klighd.lsp.model.LayoutOptionUIData
import de.cau.cs.kieler.klighd.lsp.model.PerformActionAction
import de.cau.cs.kieler.klighd.lsp.model.RefreshDiagramAction
import de.cau.cs.kieler.klighd.lsp.model.RefreshLayoutAction
import de.cau.cs.kieler.klighd.lsp.model.RequestDiagramPieceAction
import de.cau.cs.kieler.klighd.lsp.model.SKGraph
import de.cau.cs.kieler.klighd.lsp.model.SetDiagramPieceAction
import de.cau.cs.kieler.klighd.lsp.model.SetSynthesisAction
import de.cau.cs.kieler.klighd.lsp.model.StoreImagesAction
import de.cau.cs.kieler.klighd.lsp.model.UpdateDiagramOptionsAction
import de.cau.cs.kieler.klighd.lsp.model.ValuedSynthesisOption
import de.cau.cs.kieler.klighd.lsp.utils.KRenderingIdGenerator
import de.cau.cs.kieler.klighd.util.ColorPreferences
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.ArrayList
import java.util.Base64
import java.util.Collection
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.ServiceLoader
import java.util.Set
import org.eclipse.core.runtime.Platform
import org.eclipse.elk.core.data.LayoutMetaDataService
import org.eclipse.elk.core.data.LayoutOptionData
import org.eclipse.elk.core.data.LayoutOptionData.Visibility
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.emf.ecore.EObject
import org.eclipse.sprotty.Action
import org.eclipse.sprotty.ActionMessage
import org.eclipse.sprotty.RejectAction
import org.eclipse.sprotty.SModelElement
import org.eclipse.sprotty.SModelRoot
import org.eclipse.sprotty.SelectAction
import org.eclipse.sprotty.SelectAllAction
import org.eclipse.sprotty.SetModelAction
import org.eclipse.sprotty.UpdateModelAction
import org.eclipse.sprotty.xtext.LanguageAwareDiagramServer
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Diagram server extension adding functionality to special actions needed for handling KGraphs.
 * 
 * @author nre
 */
class KGraphDiagramServer extends LanguageAwareDiagramServer {
    
    @Inject protected Injector injector
    
    Map<String, ISprottyActionHandler> handlers = new HashMap
    
    @Inject 
    protected KGraphDiagramState diagramState
    
    @Inject
    INotificationHandler notificationHandler
    
    /**
     * Indicates if the stored model is a completely new model and should therefore cause a SetModelAction instead of 
     * an eventual UpdateModelAction.
     */
    protected boolean newModel
    
    /**
     * The current root element of the {@link SKGraph}. Stored here during communication with the client.
     */
    protected SModelRoot currentRoot
    
    /**
     * Flag indicating if the images of the current root are updated on the client.
     */
    protected boolean imagesUpdated = false
    
    @Accessors(PUBLIC_GETTER)
    protected Object modelLock = new Object
    
    /**
     * Needed for KeithUpdateModelAction
     * 
     * FIXME Remove this if UpdateModelAction has a cause.
     */
    new() {
        super()
        // Create map of registered action kinds and handlers.
        ServiceLoader.load(ISprottyActionHandler, KlighdDataManager.getClassLoader()).forEach[handler | 
            val Set<String> kindsSupported = handler.supportedMessages.keySet
            for (kind : kindsSupported) {
                handlers.put(kind, handler);
            }
        ]
    }
    
    /**
     * Prepares the client side update of the model by processing the potentially needed images on the client. Checks
     * for client-side cached images with the {@link CheckImagesAction}. If the corresponding response to the 
     * {@link CheckImagesAction} requires images to be sent, a {@link StoreImagesAction} is sent first. After receiving
     * the result back, updates the model with default Sprotty behavior via the {@link #updateModel} function.
     * Also handles updating the diagram options on the client.
     * 
     * @param newRoot the diagram to request the images for.
     */
    protected def prepareUpdateModel(SModelRoot newRoot) {
        synchronized (modelLock) {
            currentRoot = newRoot
            if (newRoot !== null) {
                imagesUpdated =  false
                
                // image handling
                val imageData = diagramState.getImageData(newRoot.id)
                if (imageData === null) {
                    throw new NullPointerException("The id of the SGraph was not found in the diagramState")
                } else if (imageData.empty) {
                    imagesUpdated = true
                } else {
                    dispatch(new CheckImagesAction(imageData))
                    // the setOrUpdateModel is then executed after the client confirms it has all images cached.
                }
                
                // Update the diagram options with the current used values on the client.
                updateDiagramOptions(newRoot.id)
            } else {
                setOrUpdateModel
            }
            // If the images are already updated, the model is ready to be sent to the client.
            if (imagesUpdated) {
                setOrUpdateModel
            }
        }
    }
    
    /**
     * Updates the diagram options on the client with the values from the currently used view context found under the
     * given URI.
     * 
     * @param uri The URI of the model to take the options from.
     */
    def void updateDiagramOptions(String uri) {
        synchronized (diagramState) {
            val ViewContext viewContext = diagramState.getKGraphContext(uri)
            if (viewContext !== null) {
                val synthesisOptions = new ArrayList<ValuedSynthesisOption>
                for (option : viewContext.displayedSynthesisOptions) {
                    var currentValue = viewContext.getOptionValue(option)
                    if (currentValue instanceof Enum) {
                        currentValue = currentValue.toString
                    }
                    synthesisOptions.add(new ValuedSynthesisOption(option, currentValue))
                }
                val layoutOptionUIData = calculateLayoutOptionUIData(viewContext.displayedLayoutOptions)
                val actionData = viewContext.displayedActions.map[new DisplayedActionUIData(it)]
                dispatch(new UpdateDiagramOptionsAction(synthesisOptions, layoutOptionUIData, actionData, uri))
            }
        }
    }
    
    /**
     * Packs all data needed to display the layout options in any user interface into a single list of
     * {@link LayoutOptionUIData}.
     * 
     * @param displayedLayoutOptions The layout options that should be displayed. 
     */
    def calculateLayoutOptionUIData(List<org.eclipse.elk.core.util.Pair<IProperty<?>, List<?>>> displayedLayoutOptions) {
        val List<LayoutOptionUIData> layoutOptionUIData = new ArrayList
        for (pair : displayedLayoutOptions) {
            var Object first
            var Object second
            if (pair.second instanceof Collection) {
                val iterator = (pair.second as Collection<?>).iterator
                first = if (iterator.hasNext) iterator.next else null
                second = if (iterator.hasNext) iterator.next else null
            }

            val LayoutOptionData optionData = LayoutMetaDataService.instance.getOptionData(pair.first.id)
            if (optionData.visibility !== Visibility.HIDDEN) {
                if (first instanceof Number && second instanceof Number) {
                    layoutOptionUIData.add(new LayoutOptionUIData(optionData, (first as Number).floatValue,
                        (second as Number).floatValue, null))
                } else {
                    layoutOptionUIData.add(new LayoutOptionUIData(optionData, null, null, pair.second))
                }
            }
        }
        return layoutOptionUIData
    }
    
    override void accept(ActionMessage message) {
        try {
            val clientId = getClientId();
            if (clientId !== null && clientId.equals(message.getClientId())) {
                val Action action = message.getAction();
                if (action.getKind() === PerformActionAction.KIND) {
                    handle(action as PerformActionAction)
                } else if (action.getKind() === SetSynthesisAction.KIND) {
                    handle(action as SetSynthesisAction)
                } else if (action.getKind === CheckedImagesAction.KIND) {
                    handle(action as CheckedImagesAction)
                } else if (action.getKind === ClientColorPreferencesAction.KIND) {
                    handle(action as ClientColorPreferencesAction)
                } else if (action.getKind === RefreshDiagramAction.KIND) {
                    handle(action as RefreshDiagramAction)
                } else if (action.getKind === RefreshLayoutAction.KIND) {
                    handle(action as RefreshLayoutAction)
                } else if (action.getKind === RequestDiagramPieceAction.KIND) {
                    handle(action as RequestDiagramPieceAction)
                } else {
                    val handlerInstance = handlers.get(action.kind)
                    if (handlerInstance !== null) {
                        // Even though we have an instance, it is not yet populated with all injected things.
                        val handler = injector.getInstance(handlerInstance.class)
                        handler.handle(action, clientId, this)
                    } else {
                        // If no handler is registered for this message. Let the default super class handle it.
                        super.accept(message)  
                    }
                }
            }
        } catch (Exception e) {
            // Any exception that happened during some request is displayed to the user on the client.
            notificationHandler.sendErrorAndThrow(e)
        }
    }
    
    /**
     * Called when a {@link PerformActionAction} is received.
     * Invokes the contained KlighD {@link IAction} on the current {@link KNode KGraph}.
     * May cause a {@link UpdateModelAction} to be sent back to the client with an updated model.
     */
    protected def handle(PerformActionAction action) {
        synchronized (diagramState) {
            if (currentRoot.getRevision() !== action.revision) {
                return
            }
            
            val sourceUri = diagramState.getURIString(clientId)
            val kGraphElement = diagramState.getIdToKGraphMap(sourceUri).get(action.KGraphElementId)
            val kRendering = KRenderingIdGenerator.findRenderingById(kGraphElement, action.KRenderingId)
            
            val klighdAction = KlighdDataManager.instance.getActionById(action.actionId)
            val viewer = diagramState.viewer
            val actionContext = new ActionContext(viewer, null, kGraphElement, kRendering)
            val actionResult = klighdAction.execute(actionContext)
            if (actionResult.needsSynthesis) {
                updateDiagram()
            } else if (actionResult.actionPerformed) {
                updateLayout()
            }
        }
    }
    
    /**
     * Called when a {@link SetSynthesisAction} is received.
     * Configures this diagram server to use the synthesis given in the message in future runs to generate a diagram
     * and invokes an update.
     */
    protected def handle(SetSynthesisAction action) {
        synchronized (diagramState) {
            val uri = diagramState.getURIString(clientId)
            diagramState.putSynthesisId(uri, action.id)
            this.newModel = true
            updateDiagram()
        }
    }
    
    /**
     * Called when a {@link CheckedImagesAction} is received.
     * Tells the server that the images on the client have been checked if they are cached and requests any non-cached
     * images to be sent.
     * The requested images are then sent to the client.
     */
    protected def handle(CheckedImagesAction action) {
        synchronized (modelLock) {
            if (action.notCached.empty) {
                imagesUpdated = true
            } else {
                val images = new ArrayList<Pair<Pair<String, String>, String>>
                val platformIsRunning = Klighd.IS_PLATFORM_RUNNING;
                for (notCached : action.notCached) {
                    try {
                        val bundle = notCached.key
                        val path = notCached.value
                        val InputStream imageStream =
                            if (platformIsRunning) {
                                // If the platform is running, the image can be found in the bundle under the resource path.
                                Platform.getBundle(bundle)
                                    ?.getResource(path)
                                    ?.openStream
                            } else {
                                // In the jar or plain Java application case, the bundle is ignored and the file path
                                // is searched on the classpath directly
                                this.class.getResourceAsStream("/" + path)
                            }
                        if (imageStream !== null) {
                            try {
                                val imageBytes = ByteStreams.toByteArray(imageStream)
                                val imageString = Base64.encoder.encodeToString(imageBytes)
                                images.add(notCached -> imageString)
                            } finally {
                                // Always close the stream.
                                imageStream.close
                            }
                        } else {
                            throw new FileNotFoundException("The image for bundle "
                                + bundle
                                + " and path "
                                + path
                                + " has not been found.")
                        }
                    } catch (Exception e) {
                        // Do not re-throw the exception here, we can still show the diagram, just without the images.
                        // Do notify the user about the exception, though.
                        notificationHandler.sendError(Throwables.getStackTraceAsString(e))
                        e.printStackTrace
                    }
                }
                dispatch(new StoreImagesAction(images))
                imagesUpdated = true
            }
            setOrUpdateModel
        }
    }
    
    /**
     * Called when a {@link ClientColorPreferencesAction} is received.
     * Tells the server that the client has new color preferences available that should be considered.
     */
    protected def handle(ClientColorPreferencesAction action) {
        val kind = action.clientColorPreferences.kind
        val foregroundColor = LSPUtil.parseColor(action.clientColorPreferences.foreground)
        val backgroundColor = LSPUtil.parseColor(action.clientColorPreferences.background)
        val highlightColor = LSPUtil.parseColor(action.clientColorPreferences.highlight)
        
        val colorPreferences = new ColorPreferences(kind, foregroundColor, backgroundColor, highlightColor)
        synchronized (diagramState) {
            diagramState.colorPreferences = colorPreferences
        }
        updateDiagram()
    }

    /**
     * Called when a {@link RefreshDiagramAction} is received.
     * Tells the server that the diagram should be refreshed.
     */
    protected def handle(RefreshDiagramAction action) {
        if (action.options !== null) {
            getOptions().putAll(action.options)
        }
        updateDiagram()
        return
    }
    
    /**
     * Called when a {@link RefreshLayoutAction} is received.
     * Tells the server that the diagram layout should be refreshed.
     */
    protected def handle(RefreshLayoutAction action) {
        updateLayout()
        return
    }
    
    protected def handle(RequestDiagramPieceAction action) {  
        
        synchronized (diagramState) {
            if (diagramState.getDiagramPieceRequestManager(this.sourceUri) === null) {
                // can't handle these requests if we are using recursive generation method
                dispatch(new RejectAction())
                return
            }
            
            // TODO: handle images incrementally
            val diagramUpdater = diagramLanguageServer.diagramUpdater
            if (diagramUpdater instanceof KGraphDiagramUpdater) {
                val piece = diagramUpdater.getNextDiagramPiece(this, action)
                if (piece !== null) {
                    dispatch(new SetDiagramPieceAction(piece))
                } else {
                    dispatch(new RejectAction())
                }
                
            } else {
                throw new IllegalStateException("The diagramUpdater was not initialized correctly")
            }        
        }
    }
    
    /**
     * Selects the SGraph elements mapped by the given selectable KGraph elements.
     * 
     * @param toBeSelected The elements that will be selected in the diagram, if they are selectable. 
     */
    def void selectElements(List<EObject> toBeSelected) {
        val toBeSelectedSModelElementIDs = newArrayList
        
        synchronized(diagramState) {
            val map = diagramState.getKGraphToSModelElementMap(diagramState.getURIString(clientId))
            toBeSelected.forEach [
                val sModelElement = map.get(it)
                if (sModelElement instanceof SModelElement) {
                    toBeSelectedSModelElementIDs.add(sModelElement.id)
                }
                
            ]
        }
        // Deselect all elements first, so only the new elements are selected now.
        val deselectAllAction = new SelectAllAction
        deselectAllAction.select = false
        dispatch(deselectAllAction)
        
        val selectAction = new SelectAction
        selectAction.selectedElementsIDs = toBeSelectedSModelElementIDs
        dispatch(selectAction)
    }
    
    /**
     * Updates the current diagram.
     */
    def updateDiagram() {
        synchronized (diagramState) {
            val diagramUpdater = diagramLanguageServer.diagramUpdater
            if (diagramUpdater instanceof KGraphDiagramUpdater) {
                diagramUpdater.updateDiagram(this)
            } else {
                throw new IllegalStateException("The diagramUpdater was not initialized correctly")
            }
        }
    }
    
    /**
     * Updates the layout of the current diagram.
     */
    def updateLayout() {
        synchronized (diagramState) {
            val diagramUpdater = diagramLanguageServer.diagramUpdater
            if (diagramUpdater instanceof KGraphDiagramUpdater) {
                diagramUpdater.updateLayout(this)
            } else {
                throw new IllegalStateException("The diagramUpdater was not initialized correctly")
            }
        }
    }
    
    /**
     * Initializes the stored options in this diagram server. Usable if a diagram server is created by something other
     * than a {@link SetModelAction}.
     * 
     * @param options The options to be initialized on this diagram server.
     */
    def initializeOptions(Map<String, String> options) {
        if (getOptions.isEmpty) {
            setOptions(options)
        }
    }
    
    /** Sets or updates the {@code currentRoot} as the model. */
    protected def void setOrUpdateModel() {
        if (newModel) {
            setModel(currentRoot)                
        } else {
            updateModel(currentRoot)
        }
        newModel = false
    }
}
