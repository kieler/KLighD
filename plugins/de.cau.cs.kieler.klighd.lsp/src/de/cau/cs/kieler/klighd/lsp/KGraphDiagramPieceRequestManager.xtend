/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2021 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import de.cau.cs.kieler.klighd.lsp.model.RequestDiagramPieceAction
import de.cau.cs.kieler.klighd.lsp.model.SKEdge
import de.cau.cs.kieler.klighd.lsp.model.SKLabel
import de.cau.cs.kieler.klighd.lsp.model.SKNode
import de.cau.cs.kieler.klighd.lsp.model.SKPort
import java.util.LinkedList
import java.util.Queue
import org.eclipse.sprotty.SModelElement
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Class to keep track of incoming piece requests and responsible for managing the piece generation, ensuring the 
 * requested pieces are retrieved if they already exist or generated as soon as possible if not. The updater is then
 * responsible for sending the pieces to the client.
 * @author mka
 */
class KGraphDiagramPieceRequestManager {
    
    /**
     * Incremental diagram generator used to construct the SGraph from the KGraph.
     */
    @Accessors(PUBLIC_GETTER)
    KGraphIncrementalDiagramGenerator diagramGenerator
    
    /**
     * Queue used for remembering which pieces have been requested until they are ready to be sent.
     */
    Queue<RequestDiagramPieceAction> requests
    
    new(KGraphIncrementalDiagramGenerator diagramGenerator){
        this.diagramGenerator = diagramGenerator
        this.requests = new LinkedList
    }
    
    /**
     * For each received request some diagram piece shall be returned.
     */
    def processRequest(RequestDiagramPieceAction request) {
        this.requestDiagramPiece(request)
        return this.nextDiagramPiece()
    }
    
    /**
     * Add a request action to the internal queue for processing.
     */
    private def requestDiagramPiece(RequestDiagramPieceAction request) {
        this.requests.add(request)
    }
    
    /**
     * Retrieves the next available diagram piece with stubs as children.
     */
    private def SModelElement nextDiagramPiece() {
        val request = requests.remove()
        // If diagram piece has previously been generated, get it
        // additionally there must be no children left in the childrenToProcess Queue whose parent 
        // is the one being requested, because only after they have been processed do their stubs exist
        // and only then can the client ask for them
        var generateNextFirst = false
        if (diagramGenerator.idToKGraphElementMap.containsKey(request.modelElementId)) {
            val kGraphElement = diagramGenerator.idToKGraphElementMap.get(request.modelElementId)
            
            if (diagramGenerator.nodeChildrenAllProcessed(kGraphElement)) {
                val sModelElement = diagramGenerator.KGraphToSModelElementMap.get(kGraphElement)
                return copyAndTrimChildren(sModelElement) 
            } else {
                generateNextFirst = true
            }
        } else {
            generateNextFirst = true
        } 
        if (generateNextFirst) {
            // put request to back of queue
            requests.add(request)
            // Generate another piece
            diagramGenerator.generateNextDiagramPiece
            // Try serving next request in queue
            return this.nextDiagramPiece()
        }
    }
    
    /**
     * This method currently does nothing, but the idea behind it is to further decrease the
     * network payload when sending diagram pieces. What can happen in theory
     * is that a piece may only be requested after some of its children have also already been generated.
     * This doesn't happen in the current implementation, because the client will only request pieces
     * in a strict top-down order, however in the case in the future that the client should have some
     * more advanced piece management capabilities including removal of pieces that are not required 
     * at that time to save space for example in the case of very large graphs, it becomes relevant to
     * actually only send the requested piece and not an entire graph that is dangling from it as well.
     * The cost of this lies on the server (copying and trimming data) and remains to be analyzed and implemented.
     */
    private def SModelElement copyAndTrimChildren(SModelElement original) {
        // TODO: make copy and replace children with stubs
        if (original instanceof SKEdge) {
            // copy edge
        } else if (original instanceof SKNode) {
            // copy node and remove children
        } else if (original instanceof SKPort) {
            // copy port
        } else if (original instanceof SKLabel) {
            // copy label
        }
        return original
    }
}