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

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.krendering.controller.RenderingContextData;
import de.cau.cs.kieler.klighd.piccolo.nodes.PEmptyNode;

/**
 * The Piccolo node for representing the top-level {@code KNode}.
 * 
 * @author mri
 */
public class KNodeTopNode extends PEmptyNode implements INode {

    private static final long serialVersionUID = 8395163186723344696L;

    /** the encapsulated {@code KNode}. */
    private KNode node;

    /** the Piccolo node representing the child area. */
    private KChildAreaNode childArea;

    /**
     * Constructs a Piccolo node for representing the top-level {@code KNode}.
     * 
     * @param node
     *            the KNode
     */
    public KNodeTopNode(final KNode node) {
        this.node = node;
        childArea = new KChildAreaNode(this);
        childArea.setPickable(false);
        childArea.setClip(false);
        addChild(childArea);
        setPickable(false);
        RenderingContextData.get(node).setProperty(NODE_REP, this);
    }

    /**
     * {@inheritDoc}
     */
    public KNode getGraphElement() {
        return node;
    }

    /**
     * {@inheritDoc}
     */
    public KChildAreaNode getChildArea() {
        return childArea;
    }

}
