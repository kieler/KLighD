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
package de.cau.cs.kieler.klighd.piccolo.internal.util;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IGraphElement;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.activities.PActivity;
import edu.umd.cs.piccolo.activities.PActivity.PActivityDelegate;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A utility class for handling common Piccolo2D node related tasks.<br>
 * <br>
 * In the context of this utility class the term 'smart bounds' refers to a bounds instance, which
 * origin is the translation of an associated node instead of a static offset.
 * 
 * @author mri
 * @author chsch
 */
public final class NodeUtil {
    
    /**
     * A {@link PNode} property change event key indicating dispose
     * {@link org.eclipse.swt.graphics.Device}-dependent SWT objects.
     */
    public static final String DISPOSE = "dispose";
    
    /**
     * The property change event code related to {@link #DISPOSE} events.<br>
     * It is set to zero since this code is only used for deciding whether to propagate such events
     * to the parent nodes, too. This, however, is not necessary and even not valid in context of
     * {@link #DISPOSE} events. (see {@link PNode#firePropertyChange(int, String, Object, Object)}
     * for details).
     */
    public static final int DISPOSE_CODE = 0;

    /** the attribute key for the activity. */
    private static final Object ACTIVITY_KEY = "activity";

    /**
     * A private constructor to prevent instantiation.
     */
    private NodeUtil() {
        // do nothing
    }
    
    /**
     * Casts a custom {@link PNode} object that implements {@link IGraphElement} to
     * {@link IGraphElement}, the <b>type check is omitted for performance reasons</b>.
     * 
     * @param node
     *            a custom {@link PNode} implementing {@link IGraphElement}
     * @return node typed as {@link IGraphElement}
     */
    public static IGraphElement<KGraphElement> asIGraphElement(final PNode node) {
        @SuppressWarnings("unchecked")
        final IGraphElement<KGraphElement> graphNode = (IGraphElement<KGraphElement>) node;
        return graphNode;
    }

    /**
     * Applies the bounds to the given node using the node's translation and its bounds.
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
     * Applies the bounds to the given node using the node's translation and its bounds.
     * 
     * @param node
     *            the node
     * @param bounds
     *            the bounds
     */
    public static void applySmartBounds(final PNode node, final Bounds bounds) {
        applySmartBounds(node, bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Applies the bounds to the given node using the node's translation and its bounds.
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
     * Removes a formerly scheduled primary activity of the given from the schedule if any exists.
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
    
    /**
     * Recursively concatenates the {@link AffineTransform AffineTransforms} of all {@link PNode
     * PNodes} in the containment hierarchy starting with the <code>ancestor</code>'s transform. If
     * <code>ancestor</code> is actually not an ancestor of <code>child</code>, this method
     * concatenates all transforms of <code>child</code>' ancestors and that of<code>child</code>.
     * 
     * @param child
     *            the child {@link PNode}
     * @param ancestor
     *            the ancestor {@link PNode}
     * @return an {@link AffineTransform} being formed by the concatenation of the required
     *         transforms
     */
    public static AffineTransform localToParent(final PNode child, final PNode ancestor) {
        if (child == null) {
            return new AffineTransform();
        }
        
        if (child == ancestor) {
            return child.getTransform();
        }

        final PNode childsParent = child.getParent();
        final AffineTransform transform;

        if (childsParent != null) {
            transform = localToParent(childsParent, ancestor);
        } else {
            return child.getTransform(); 
        }

        transform.concatenate(child.getTransformReference(true));
        return transform;
    }
    
    /**
     * This method simply wraps {@link AffineTransform#createInverse()} in order to encapsulate its
     * required try-catch-block. <br>
     * The method can be used without any doubts if the provided <code>transform</code>
     * only consists of <i>translate</i> data. <b>Otherwise be careful!</b>
     * 
     * @param transform
     *            the transform to invert
     * @return the inverted transform, or an <b>empty transform if <code>transform</code> is not
     *         invertible</b>
     */
    public static AffineTransform inverse(final AffineTransform transform) {
        try {
            return transform.createInverse();
        } catch (NoninvertibleTransformException e) {
            return new AffineTransform();
        }
    }

}
