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
/**
 * 
 */
package de.cau.cs.kieler.klighd.piccolo.nodes;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A Piccolo node that specifies a z-index for its children which determines drawing order with the
 * lowest z-index drawn first. Children without a z-index are considered to have the lowest z-index.
 * 
 * @author mri
 */
public class PZIndexNode extends PEmptyNode {

    private static final long serialVersionUID = -4461472478238694348L;

    private static final Object Z_INDEX_KEY = new Object();
    
    /** the lists of nodes in the z-layers. */
    private List<LinkedList<PNode>> zLayers;

    private boolean autoAssignLayer = true;

    /**
     * Constructs a Piccolo z-index node.
     * 
     * @param zs
     *            the number of z-layers
     */
    public PZIndexNode(final int zs) {
        zLayers = Lists.newArrayListWithCapacity(zs);
        for (int i = 0; i < zs; ++i) {
            zLayers.add(new LinkedList<PNode>());
        }
    }

    /**
     * Adds a child node with a given z-index.
     * 
     * @param child
     *            the child node
     * @param z
     *            the z-index
     */
    public void addChild(final PNode child, final int z) {
        zLayers.get(z).add(child);
        child.addAttribute(Z_INDEX_KEY, z);
        autoAssignLayer = false;
        addChild(child);
        autoAssignLayer = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChild(final int index, final PNode child) {
        if (autoAssignLayer) {
            zLayers.get(0).add(child);
        }
        super.addChild(index, child);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PNode removeChild(final int index) {
        PNode child = getChild(index);
        Integer z = child.getIntegerAttribute(Z_INDEX_KEY, 0);
        zLayers.get(z).remove(child);
        return super.removeChild(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllChildren() {
        for (LinkedList<PNode> layer : zLayers) {
            layer.clear();
        }
        super.removeAllChildren();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fullPaint(final PPaintContext paintContext) {
        if (getVisible() && fullIntersects(paintContext.getLocalClip())) {
            paintContext.pushTransform(getTransformReference(true));
            paintContext.pushTransparency(getTransparency());

            if (!getOccluded()) {
                paint(paintContext);
            }

            for (LinkedList<PNode> layer : zLayers) {
                for (PNode node : layer) {
                    node.fullPaint(paintContext);
                }
            }

            paintAfterChildren(paintContext);

            paintContext.popTransparency(getTransparency());
            paintContext.popTransform(getTransformReference(true));
        }
    }
    
    // TODO adjust pick path (?)

}
