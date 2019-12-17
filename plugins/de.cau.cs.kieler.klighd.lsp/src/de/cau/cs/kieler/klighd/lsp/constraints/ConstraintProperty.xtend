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
package de.cau.cs.kieler.klighd.lsp.constraints

import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.elk.graph.properties.IProperty
import de.cau.cs.kieler.klighd.kgraph.KNode

/**
 * @author sdo
 *
 */
@Data
class ConstraintProperty {
    KNode kNode
    IProperty<Integer> property
    Integer value
}