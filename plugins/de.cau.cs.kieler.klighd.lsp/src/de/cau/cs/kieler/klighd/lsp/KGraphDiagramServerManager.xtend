/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import java.util.ArrayList
import java.util.List
import javax.inject.Inject
import javax.inject.Provider
import org.eclipse.sprotty.xtext.IDiagramServerFactory
import org.eclipse.sprotty.xtext.ls.DiagramServerManager

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
    
    @Inject Provider<IDiagramServerFactory> diagramServerFactoryProvider
    
    override getDiagramServerFactories() {
        // If the diagram server factories are not initialized yet, initialize the list of all factories with the
        // factories provided by the resource providers in the language registry from the super implementation
        // and add any language-unspecific IDiagramServerFactory to that list.
        // Otherwise, just return that already generated list.
        if (diagramServerFactories === null) {
            diagramServerFactories = new ArrayList<IDiagramServerFactory>
            diagramServerFactories.addAll(super.diagramServerFactories)
            diagramServerFactories.add(diagramServerFactoryProvider.get())
        }
        return diagramServerFactories
    }
}