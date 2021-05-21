/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.sprotty.SModelElement
import java.util.LinkedList
import java.util.Queue
import de.cau.cs.kieler.klighd.lsp.model.RequestDiagramPieceAction

/**
 * Class to keep track of incoming piece requests and responsible for managing the piece generation, ensuring the 
 * requested pieces are retrieved if they already exist or generated as soon as possible if not. The updater is then
 * responsible for sending the pieces to the client.
 * @author max
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
        if (diagramGenerator.idToKGraphElementMap.containsKey(request.modelElementId)){
            val kGraphElement = diagramGenerator.idToKGraphElementMap.get(request.modelElementId)
            val sModelElement = diagramGenerator.KGraphToSModelElementMap.get(kGraphElement)
            return sModelElement // TODO: make copy and replace children with stubs
        } else {
            // put request to back of queue
            requests.add(request)
            // Generate another piece
            diagramGenerator.generateNextDiagramPiece
            // Try serving next request in queue
            return this.nextDiagramPiece()
        }
    }
}