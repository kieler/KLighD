/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.krendering;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.util.IWrapper;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.klighd.piccolo.nodes.PEmptyNode;

/**
 * The Piccolo node for representing a {@code KEdge}.
 * 
 * @author mri
 */
public class KEdgeNode extends PEmptyNode implements IWrapper<KEdge> {

    private static final long serialVersionUID = -1867615197736299487L;

    /** the encapsulated {@code KEdge}. */
    private KEdge edge;
    /** the edge layout associated with this node. */
    private KEdgeLayout edgeLayout = null;

    /** the rendering controller. */
    private RenderingController renderingController;

    /**
     * {@inheritDoc}
     */
    public KEdge getWrapped() {
        return edge;
    }

}
