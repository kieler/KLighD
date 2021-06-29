/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018,2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.model

import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphData
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import java.util.HashMap
import java.util.List
import org.eclipse.elk.core.math.KVectorChain
import org.eclipse.elk.core.options.Direction
import org.eclipse.sprotty.SEdge
import org.eclipse.sprotty.SGraph
import org.eclipse.sprotty.SLabel
import org.eclipse.sprotty.SNode
import org.eclipse.sprotty.SPort
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Sprotty node with additional fields needed by the translation from a
 * {@link KNode KNode}.
 * 
 * @author nre
 */
@Accessors
class SKNode extends SNode {
    List<KGraphData> data
    HashMap<String, Object> properties = newHashMap
    Direction direction
    String tooltip
}

/**
 * Sprotty label with additional fields needed by the translation from a
 * {@link KLabel KLabel}.
 * 
 * @author nre
 */
@Accessors
class SKLabel extends SLabel {
    List<KGraphData> data
    String tooltip
}

/**
 * Sprotty edge with additional fields needed by the translation from a
 * {@link KEdge KEdge}.
 * 
 * @author nre
 */
@Accessors
class SKEdge extends SEdge {
    List<KGraphData> data
    String tooltip
    KVectorChain junctionPoints
    Double renderScale
}

/**
 * Sprotty port with additional fields needed by the translation from a
 * {@link KPort KPort}.
 * 
 * @author nre
 */
@Accessors
class SKPort extends SPort {
    List<KGraphData> data
    String tooltip
}

/**
 * Sprotty graph with additional fields needed by the translation from a parent 
 * {@link KNode KNode}.
 * 
 * @author nre
 */
@Accessors
class SKGraph extends SGraph {
    List<KGraphData> data
}
