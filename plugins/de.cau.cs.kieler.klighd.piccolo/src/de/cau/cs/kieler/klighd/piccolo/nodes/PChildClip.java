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
package de.cau.cs.kieler.klighd.piccolo.nodes;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A Piccolo node that clips a given node at the boundary of the first child node.
 * 
 * @author mri
 */
public class PChildClip extends PNode {

    private static final long serialVersionUID = -2651702857927623226L;

    /** the encapsulated node. */
    private PNode node = null;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addChild(final int index, final PNode child) {
        if (node == null) {
            node = child;
        }
        super.addChild(index, child);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PNode removeChild(final int index) {
        if (node == getChild(index)) {
            node = null;
        }
        return super.removeChild(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllChildren() {
        node = null;
        super.removeAllChildren();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fullPaint(final PPaintContext paintContext) {
        if (getVisible() && fullIntersects(paintContext.getLocalClip())) {
            paintContext.pushTransform(getTransformReference(false));
            paintContext.pushTransparency(getTransparency());
            if (node != null) {
                paintContext.pushClip(node.getBoundsReference());
                node.fullPaint(paintContext);
                paintContext.popClip(node.getBoundsReference());
            }
            paintContext.popTransparency(getTransparency());
            paintContext.popTransform(getTransformReference(false));
        }
    }

}
