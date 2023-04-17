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
package de.cau.cs.kieler.klighd.lsp.structuredProgramming

import java.util.Map
import org.eclipse.sprotty.Action

/**
 * Used to provide a map of kind and corresponding action to register the actions in the KGraphDiagramServer
 * @author felixj
 *
 */
interface IStructuredActions {
    
     /**
     * A List of all properties that should be preserved.
     * List<Class<? implements MyInterface>>
     * @return The preserved properties.
     */
    def Map<String, Class<? extends Action>> getKindAndActions();
}