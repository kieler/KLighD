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
import edu.umd.cs.piccolo.activities.PActivity;
import edu.umd.cs.piccolo.activities.PActivity.PActivityDelegate;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A utility class for handling common Piccolo node related tasks.<br>
 * <br>
 * In the context of this utility class the term 'smart bounds' refers to a bounds instance, which
 * origin is the translation of an associated node instead of a static offset.
 * 
 * @author mri
 */
public final class NodeUtil {

    /** the attribute key for the activity. */
    private static final Object ACTIVITY_KEY = "activity";

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
     *            
     * @author mri, chsch
     */
    public static void applySmartBounds(final PNode node, final double x, final double y,
            final double width, final double height) {
        // apply the layout;
        // positions the node at the given coordinates (taken from the related shape layout)
        node.setOffset(x, y);

        // sets the size of the node, does not influence the above determined position
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
        applySmartBounds(node, bounds.x, bounds.y, bounds.width, bounds.height);
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
     *            
     * @author mri, chsch
     */
    public static void applyTranslation(final PNode node, final double x, final double y) {
        // apply the translation
        node.setOffset(x, y);
    }

    /**
     * Applies the translation to the given node.
     * 
     * @param node
     *            the node
     * @param translation
     *            the translation
     *            
     * @author mri, chsch
     */
    public static void applyTranslation(final PNode node, final Point2D translation) {
        // apply the translation
        applyTranslation(node, translation.getX(), translation.getY());
    }

    /**
     * Determines the smart bounds of the given node.
     * 
     * @param node
     *            the node
     * @return the smart bounds
     */
    public static PBounds determineSmartBounds(final PNode node) {
        PBounds bounds = node.getBounds();

        // get the translation
        PAffineTransform transform = node.getTransformReference(true);
        bounds.setOrigin(transform.getTranslateX(), transform.getTranslateY());

        return bounds;
    }

    /**
     * Schedules a primary activity for the given node. This method assures that there is only one
     * primary activity for any node at any given time.<br>
     * <br>
     * This method uses the activities delegate slot.
     * 
     * @param node
     *            the node
     * @param activity
     *            the primary activity
     */
    public static void schedulePrimaryActivity(final PNode node, final PActivity activity) {
        Object attribute = node.getAttribute(ACTIVITY_KEY);
        if (attribute instanceof PActivity) {
            PActivity oldActivity = (PActivity) attribute;
            oldActivity.terminate();
        }
        node.addAttribute(ACTIVITY_KEY, activity);
        activity.setDelegate(new PActivityDelegate() {
            public void activityStepped(final PActivity activity) {
                // do nothing
            }

            public void activityStarted(final PActivity activity) {
                // do nothing
            }

            public void activityFinished(final PActivity activity) {
                node.addAttribute(ACTIVITY_KEY, null);
            }
        });
        node.addActivity(activity);
    }

    /**
     * Unschedules a primary activity of the given node if any.
     * 
     * @param node
     *            the node
     */
    public static void unschedulePrimaryActivity(final PNode node) {
        Object attribute = node.getAttribute(ACTIVITY_KEY);
        if (attribute instanceof PActivity) {
            PActivity oldActivity = (PActivity) attribute;
            oldActivity.terminate();
        }
    }

}
