/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.interactive.rectpack

import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveLayout
import org.eclipse.elk.alg.packing.rectangles.options.RectPackingOptions

/**
 * @author sdo
 *
 */
class RectPackInteractiveUtil {
     /**
     * Sets the required options for the non interactive layout run.
     */
    public static def void setRequiredInteractiveOptions(KNode root) {
        root.setProperty(RectPackingOptions.INTERACTIVE, true)
        for (n : root.children) {
            if (!n.children.empty) {
                InteractiveLayout.setRequiredInteractiveOptions(n)
            }
        }
        return
    }
}