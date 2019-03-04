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
import de.cau.cs.kieler.klighd.IAction
import de.cau.cs.kieler.klighd.IAction.ActionContext
import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.KText
import de.cau.cs.kieler.klighd.lsp.model.ComputedTextBoundsAction
import de.cau.cs.kieler.klighd.lsp.model.PerformActionAction
import de.cau.cs.kieler.klighd.lsp.model.RequestTextBoundsAction
import de.cau.cs.kieler.klighd.lsp.model.SKGraph
import de.cau.cs.kieler.klighd.lsp.utils.KGraphElementIDGenerator
import de.cau.cs.kieler.klighd.lsp.utils.KRenderingIDGenerator
import de.cau.cs.kieler.klighd.microlayout.Bounds
import de.cau.cs.kieler.klighd.util.KlighdProperties
import io.typefox.sprotty.api.Action
import io.typefox.sprotty.api.ActionMessage
import io.typefox.sprotty.api.SModelRoot
import io.typefox.sprotty.api.SetModelAction
import io.typefox.sprotty.api.UpdateModelAction
import io.typefox.sprotty.server.xtext.LanguageAwareDiagramServer
import java.util.Map
import org.apache.log4j.Logger

/**
 * Diagram server extension adding functionality to special actions needed for handling KGraphs.
 * 
 * @author nir
 */
public class KGraphDiagramServer extends LanguageAwareDiagramServer {
    private static val LOG = Logger.getLogger(KGraphDiagramServer)
    
    @Inject 
    protected KGraphDiagramState diagramState
    
    /**
     * The current root element of the {@link SKGraph}. Stored here during communication with the client.
     */
    protected var SModelRoot currentRoot
    
    /**
     * requests the calculation of text sizes by the client via the {@link RequestTextBoundsAction}. After receiving
     * the result back, updates the model with default sprotty behavior via the {@link #updateModel} function.
     * 
     * @param newRoot the diagram to request the text sizes for.
     */
    protected synchronized def requestTextSizesAndUpdateModel(SModelRoot newRoot) {
        currentRoot = newRoot
        if (newRoot !== null) {
            val texts = diagramState.getTexts(newRoot.id)
            if (texts === null) {
                throw new NullPointerException("The id of the SGraph was not found in the diagramState")
            } else if (texts.empty) {
                updateModel(newRoot)
                return
            }
            val textDiagram = KGraphDiagramGenerator.generateTextDiagram(texts, newRoot.id)
            dispatch(new RequestTextBoundsAction(textDiagram))
            // the updateModel is then executed after the client returns with its ComputedTextBoundsAction
        } else {
            updateModel(newRoot)
        }
    }
    
    override void accept(ActionMessage message) {
        val clientId = getClientId();
        if (clientId !== null && clientId.equals(message.getClientId())) {
            // call the handle function for the ComputedTextBoundsAction or forward the call to the super implementation
            val Action action = message.getAction();
            if (action.getKind() === ComputedTextBoundsAction.KIND) {
                handle(action as ComputedTextBoundsAction)
            } else if (action.getKind() === PerformActionAction.KIND) {
                handle(action as PerformActionAction)
            } else {
                super.accept(message)
            }
        }
    }
    
    /**
     * Called when a {@link ComputedTextBoundsAction} is received.
     * Maps the bounds for all texts referenced in the action back to their corresponding {@link KText} elements
     * and updates the model on the client.
     */
    protected def handle(ComputedTextBoundsAction action) {
        // assume the model is still stored in 'currentRoot', since the ComputedTextBoundsAction only gets issued
        // after a RequestTextBoundsAction, where it got stored before.
        
        val textMapping = diagramState.getTextMapping(currentRoot.id)
        for (elementAndBound : action.bounds) {
            val elementId = elementAndBound.elementId
            val newBounds = elementAndBound.newBounds
            if (newBounds === null) {
                throw new NullPointerException("Estimated Bounds for a KText are null!")
            }
            val newBounds_klighd = new Bounds(newBounds.x as float, newBounds.y as float,
                newBounds.width as float, newBounds.height as float)
            val kText = textMapping.get(elementId)
            if (kText === null) {
                LOG.info("The textMapping does not contain the referenced Text anymore. The model has changed before" + 
                    "completion of the request. Terminating this request.")
                return
            }
            kText.properties.put(KlighdProperties.CALCULATED_TEXT_BOUNDS, newBounds_klighd)
        }
        updateModel(currentRoot)
    }
    
    /**
     * Called when a {@link PerformActionAction} is received.
     * Invokes the contained KlighD {@link IAction} on the current {@link KNode KGraph}.
     * May cause a {@link UpdateModelAction} to be sent back to the client with an updated model.
     */
    protected def handle(PerformActionAction action) {
        synchronized(diagramState) {
            
            val sourceUrl = diagramState.getURIString(clientId)
            val k2sMap = diagramState.getKGraphToSModelElementMap(sourceUrl)
            val kGraphElement = KGraphElementIDGenerator.findElementById(k2sMap, action.KGraphElementId)
            val kRendering = KRenderingIDGenerator.findRenderingById(kGraphElement, action.KRenderingId)
            
            val klighdAction = KlighdDataManager.getInstance().getActionById(action.actionId)
            val viewer = diagramState.getViewer(/*diagramState.getURIString(clientId)*/)
            val actionContext = new ActionContext(viewer, null, kGraphElement, kRendering)
            val shouldUpdate = klighdAction.execute(actionContext)
            if (shouldUpdate.actionPerformed) {
                languageServerExtension.updateDiagram(this)
            }
        }
    }
    
    /**
     * Initializes the stored options in this diagram server. Usable if a diagram server is created by something other
     * than a {@link SetModelAction}.
     * 
     * @param options The options to be initialized on this diagram server.
     */
    public def initializeOptions(Map<String, String> options) {
        if (getOptions.isEmpty) {
            setOptions(options)
        }
    }
}