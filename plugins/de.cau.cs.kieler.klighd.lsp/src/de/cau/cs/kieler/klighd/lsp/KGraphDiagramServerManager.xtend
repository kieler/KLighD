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

import java.util.List
import org.eclipse.sprotty.xtext.IDiagramServerFactory
import org.eclipse.sprotty.xtext.ls.DiagramServerManager
import java.util.ArrayList

/**
 * A Manager that provides diagram servers.
 * 
 * @author nre
 */
class KGraphDiagramServerManager extends DiagramServerManager {
    
    /**
     * A list of all diagram server factories that can be used to create diagram servers.
     */
    protected List<IDiagramServerFactory> diagramServerFactories
    
    override getDiagramServerFactories() {
        // If the diagram server factories are not initialized yet, initialize the list of all factories with the
        // factories provided by the resource providers in the language registry from the super implementation
        // and add our language-unspecific KGraphDiagramServerFactory to that list.
        // On every other call, just return that already generated list.
        if (diagramServerFactories === null) {
            diagramServerFactories = new ArrayList<IDiagramServerFactory>
            diagramServerFactories.addAll(super.diagramServerFactories)
            diagramServerFactories.add(new KGraphDiagramServerFactory)
        }
        return diagramServerFactories
    }
}