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
package de.cau.cs.kieler.klighd.lsp.constraints

import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLayoutEngine
import org.eclipse.xtext.ide.server.ILanguageServerAccess.Context

/**
 * @author jet, cos
 * 
 */
class InteractiveLayout {

    public def static calcLayout(KGraphDiagramServer diagramServer, Context context, KGraphLayoutEngine layoutE,
        KGraphDiagramState diagramState) {

        // layout
        var ViewContext viewContext = null
        val id = context.resource.URI.toString
        synchronized (diagramState) {
            viewContext = diagramState.getKGraphContext(id)
        }
        var root = viewContext.viewModel
        // initiales layout
        //​ neue layout Methode machen
        //layoutE.onlyLayoutOnKGraph(id)
        // Koordinaten der Knoten anpassen
//setCoordinates()
        // interactive strategies aktivieren
//setInteractiveStrats()
        //nochmal layout oder einfach weiterlaufen lassen?
        
    }
}
