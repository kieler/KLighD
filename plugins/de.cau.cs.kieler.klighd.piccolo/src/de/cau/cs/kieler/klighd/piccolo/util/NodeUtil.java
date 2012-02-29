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
package de.cau.cs.kieler.klighd.piccolo.util;

import java.awt.geom.Point2D;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A utility class for handling common Piccolo node related tasks.
 * 
 * @author mri
 */
public final class NodeUtil {

    /**
     * A private constructor to prevent instantiation.
     */
    private NodeUtil() {
        // do nothing
    }

    /**
     * Applies the bounds to the given node using the nodes translation and the its bounds.
     * 
     * @param node
     *            the node
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param width
     *            the width
     * @param height
     *            the height
     */
    public static void applySmartBounds(final PNode node, final float x, final float y,
            final float width, final float height) {
        // get the old translation
        PAffineTransform transform = node.getTransformReference(true);
        double oldX = transform.getTranslateX();
        double oldY = transform.getTranslateY();

        // apply the layout
        node.translate(x - oldX, y - oldY);
        node.setBounds(0, 0, width, height);
    }

    /**
     * Applies the bounds to the given node using the nodes translation and the its bounds.
     * 
     * @param node
     *            the node
     * @param bounds
     *            the bounds
     */
    public static void applySmartBounds(final PNode node, final PBounds bounds) {
        applySmartBounds(node, (float) bounds.x, (float) bounds.y, (float) bounds.width,
                (float) bounds.height);
    }

    /**
     * Applies the translation to the given node.
     * 
     * @param node
     *            the node
     * @param x
     *            the x-translation
     * @param y
     *            the y-translation
     */
    public static void applyTranslation(final PNode node, final float x, final float y) {
        // get the old translation
        PAffineTransform transform = node.getTransformReference(true);
        double oldX = transform.getTranslateX();
        double oldY = transform.getTranslateY();

        // apply the translation
        node.translate(x - oldX, y - oldY);
    }

    /**
     * Applies the translation to the given node.
     * 
     * @param node
     *            the node
     * @param translation
     *            the translation
     */
    public static void applyTranslation(final PNode node, final Point2D translation) {
        // get the old translation
        PAffineTransform transform = node.getTransformReference(true);
        double oldX = transform.getTranslateX();
        double oldY = transform.getTranslateY();

        // apply the translation
        node.translate(translation.getX() - oldX, translation.getY() - oldY);
    }

}
