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
package de.cau.cs.kieler.klighd.lsp.model

import de.cau.cs.kieler.klighd.kgraph.KGraphData
import io.typefox.sprotty.api.SEdge
import io.typefox.sprotty.api.SGraph
import io.typefox.sprotty.api.SLabel
import io.typefox.sprotty.api.SNode
import io.typefox.sprotty.api.SPort
import io.typefox.sprotty.server.xtext.tracing.Traceable
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Sprotty node with additional fields needed by the translation from a
 * {@link de.cau.cs.kieler.klighd.kgraph.KNode KNode}.
 */
@Accessors
public class SKNode extends SNode implements Traceable {
    private String trace
    private List<KGraphData> data
}

/**
 * Sprotty label with additional fields needed by the translation from a
 * {@link de.cau.cs.kieler.klighd.kgraph.KLabel KLabel}.
 */
@Accessors
public class SKLabel extends SLabel implements Traceable {
    private String trace
    private List<KGraphData> data
}

/**
 * Sprotty edge with additional fields needed by the translation from a
 * {@link de.cau.cs.kieler.klighd.kgraph.KEdge KEdge}.
 */
@Accessors
public class SKEdge extends SEdge implements Traceable {
    private String trace
    private List<KGraphData> data
}

/**
 * Sprotty port with additional fields needed by the translation from a
 * {@link de.cau.cs.kieler.klighd.kgraph.KPort KPort}.
 */
@Accessors
public class SKPort extends SPort implements Traceable {
    private String trace
    private List<KGraphData> data
}

/**
 * Sprotty graph with additional fields needed by the translation from a parent 
 * {@link de.cau.cs.kieler.klighd.kgraph.KNode KNode}.
 */
@Accessors
public class SKGraph extends SGraph {
    private List<KGraphData> data
}