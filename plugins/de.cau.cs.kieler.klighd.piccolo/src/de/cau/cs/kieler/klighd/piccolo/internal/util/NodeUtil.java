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
import java.util.Iterator;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.PNodeController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IGraphElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.INode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PLayer;
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
    public static IGraphElement<?> asIGraphElement(final PNode node) {
        final IGraphElement<?> graphNode = (IGraphElement<?>) node;
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
    public static void applyBounds(final PNode node, final double x, final double y,
            final double width, final double height) {
        // apply the layout;
        // sets the size of the node, does not influence the above determined position
        node.setBounds(0, 0, width, height);

        // positions the node at the given coordinates (taken from the related shape layout)
        applyTranslation(node, x, y);
    }

    /**
     * Applies the bounds to the given node using the node's translation and its bounds.
     *
     * @param node
     *            the node
     * @param bounds
     *            the bounds
     */
    public static void applyBounds(final PNode node, final PBounds bounds) {
        applyBounds(node, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    /**
     * Applies the bounds to the given node using the node's translation and its bounds.
     *
     * @param node
     *            the node
     * @param bounds
     *            the bounds
     */
    public static void applyBounds(final PNode node, final Bounds bounds) {
        applyBounds(node, bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Applies the bounds to the given node using the node's translation and its bounds.
     *
     * @param node
     *            the node
     * @param bounds
     *            the bounds
     */
    public static void applyBounds(final PNode node, final KShapeLayout bounds) {
        applyBounds(node, bounds.getXpos(), bounds.getYpos(), bounds.getWidth(), bounds.getHeight());
    }

    /**
     * (Re-)Applies the translation (x,y) and size (w, h) given in <code>bounds</code> and last
     * rotation configuration to the node controller by <code>controller</code>.
     *
     * @param controller
     *            the {@link PNodeController}
     * @param bounds
     *            the {@link Bounds}
     */
    public static void applyBounds(final PNodeController<?> controller, final Bounds bounds) {
        controller.getNode().setBounds(0, 0,  bounds.getWidth(), bounds.getHeight());

        applyTranslation(controller, bounds);
    }

    /**
     * (Re-)Applies the translation (x,y) given in <code>bounds</code> and last rotation
     * configuration to the node controller by <code>controller</code>.
     *
     * @param controller
     *            the {@link PNodeController}
     * @param bounds
     *            the {@link Bounds}
     */
    public static void applyTranslation(final PNodeController<?> controller, final Bounds bounds) {
        // apply the translation
        final PNode node = controller.getNode();
        node.getTransformReference(true).setToIdentity();

        controller.applyRotation();

        node.translate(bounds.getX(), bounds.getY());
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
        final AffineTransform t = node.getTransformReference(true);

        if (t.getTranslateX() != x || t.getTranslateY() != y) {
            // apply the translation
            node.setOffset(x, y);
        }
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
    public static PBounds determineBounds(final PNode node) {
        final PBounds bounds = node.getBounds();

        // get the translation
        final PAffineTransform transform = node.getTransformReference(true);
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
        final Object attribute = node.getAttribute(ACTIVITY_KEY);
        if (attribute instanceof PActivity) {
            final PActivity oldActivity = (PActivity) attribute;
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
        final Object attribute = node.getAttribute(ACTIVITY_KEY);
        if (attribute instanceof PActivity) {
            final PActivity oldActivity = (PActivity) attribute;
            oldActivity.terminate();
        }
    }

    /**
     * Recursively concatenates the {@link AffineTransform AffineTransforms} of all {@link PNode
     * PNodes} in the containment hierarchy between <code>ancestor</code> and <code>child</code>
     * starting with the transform of <code>ancestor</code>'s first child in that containment path (
     * <code>ancestor</code>'s transform is not considered).<br>
     * If <code>ancestor</code> is actually not an ancestor of <code>child</code>, this method
     * concatenates all transforms of <code>child</code>' ancestors and that of<code>child</code>.
     *
     * @param child
     *            the child {@link PNode}, if {@code null} an identity transform will be returned
     * @param ancestor
     *            the ancestor {@link PNode}, if {@code null} the {@code child}'s global transform
     *            will be returned
     * @return an {@link AffineTransform} being formed by the concatenation of the required
     *         transforms
     */
    public static PAffineTransform localToParent(final PNode child, final PNode ancestor) {
        // note: this method is defined recursively

        // 1st stopping criterion:
        if (child == null || child == ancestor) {
            return new PAffineTransform();
        }

        final PNode childsParent = child.getParent();

        // 2nd stopping criterion:
        if (childsParent == null) {
            return child.getTransform();

        } else {
            // recursive case:
            //  get the transform from 'childsParent' to 'ancestor',
            //  concatenate 'child's transform, and return the result;
            // this way the transforms of the nodes between 'child' and 'ancestor' are
            //  accumulated starting with the direct child of 'ancestor' (due to 1st
            //  stopping case an identity transform is taken for 'ancestor' itself)

            final PAffineTransform transform = localToParent(childsParent, ancestor);
            transform.concatenate(child.getTransformReference(true));
            return transform;
        }
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
    public static AffineTransform invert(final AffineTransform transform) {
        try {
            return transform.createInverse();
        } catch (final NoninvertibleTransformException e) {
            return new AffineTransform();
        }
    }

    /**
     * Advancement of {@link PNode#getGlobalBounds()} respecting the current clip that may be set to
     * a non-root {@link de.cau.cs.kieler.core.kgraph.KNode KNode}.<br>
     * <br>
     * It is used in {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController
     * DiagramController#isVisible(de.cau.cs.kieler.core.kgraph.KGraphElement)}, for example.
     *
     * @param node
     *            the {@link PNode} to compute the bounds for
     * @param clipNode
     *            the INode the diagram is currently clipped to (for convenience)
     * @return <code>node's</code> global bounds relative to the current clip, or <code>null</code>
     *         if <code>node</code> is not a (recursive) child of <code>clipNode</code>
     */
    public static PBounds clipRelativeGlobalBoundsOf(final PNode node, final INode clipNode) {
        // determine the closure of node's bounds and all recursively contained children's bounds
        final PBounds nodeBounds = node.getFullBounds();

        if (node == clipNode) {
            return nodeBounds;
        }

        // since the fullBounds are already adjusted wrt. to node's transform
        //  start with node's parent here!
        PNode p = node.getParent();
        while (p != null && p.getParent() != null) {
            p.localToParent(nodeBounds);
            if (p == clipNode) {
                return nodeBounds;
            }
            p = p.getParent();
        }

        // node seems not to be (recursively) contained by clipNode, so ...
        return null;
    }

    /**
     * Tests whether the given <code>node</code> is contained in the <code>camera</code>'s displayed
     * {@link INode}'s children sub tree.
     *
     * @param node
     *            the PNode to be tested
     * @param camera
     *            the camera the test is based on
     * @return <code>true</code> if node is contained in <code>camera</code>'s displayed
     *         {@link INode}'s deep children, <code>false</code> otherwise.
     */
    public static boolean isDisplayed(final PNode node, final KlighdMainCamera camera) {
        if (camera == null) {
            throw new IllegalArgumentException(
                    "KLighD: 'camera' in NodeUtil.isDisplayed(...) must not be 'null'");
        }
        final PLayer displayedLayer = camera.getDisplayedLayer();

        PNode parent = node;

        while (parent != null) {
            if (parent == displayedLayer) {
                return true;
            } else {
                parent = parent.getParent();
            }
        }
        return false;
    }

    /**
     * Creates an iterator traversing along the 'parent' chain of the given {@link PNode} {@code node}.
     *
     * @param node
     *            the {@link PNode} to start with
     * @return the an {@link Iterator} all parents of {@code node}.
     */
    public static Iterator<PNode> parentIterator(final PNode node) {
        return new ParentIterator(node, false);
    }

    /**
     * A simple implementation of the {@link Iterator} interface allowing to traverse the
     * 'parent' chain of {@link PNode PNodes}.
     *
     * @author chsch
     */
    private static class ParentIterator implements Iterator<PNode> {

        private PNode node;

        public ParentIterator(final PNode child, final boolean includingSelf) {
            if (child == null) {
                throw new IllegalArgumentException("Class ParentIterator:"
                        + "Constructor of ParentIterator requires a non-null input.");
            }
            this.node = includingSelf ? child : child.getParent();
        }

        public boolean hasNext() {
            return node != null;
        }

        public PNode next() {
            if (node == null) {
                throw new IllegalStateException("Class ParentIterator: There is no more element.");
            }
            final PNode res = node;
            node = node.getParent();
            return res;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "Removing elements from a PNode's parent hierarchy is not allowed!");
        }
    }

    /**
     * Creates an iterator traversing along the 'parentNode' chain of the given {@link INode}
     * {@code node}.
     *
     * @param node
     *            the {@link INode} to start with
     * @return the an {@link Iterator} all parents of {@code node}.
     */
    public static Iterator<INode> parentINodeIterator(final INode node) {
        return new ParentINodeIterator(node, false);
    }

    /**
     * A simple implementation of the {@link Iterator} interface allowing to traverse the
     * 'parent' chain of {@link INode INodes}.
     *
     * @author chsch
     */
    private static class ParentINodeIterator implements Iterator<INode> {

        private INode node;

        public ParentINodeIterator(final INode child, final boolean includingSelf) {
            if (child == null) {
                throw new IllegalArgumentException("Class ParentINodeIterator:"
                        + "Constructor of ParentINodeIterator requires a non-null input.");
            }
            this.node = includingSelf ? child : child.getParentNode();
        }

        public boolean hasNext() {
            return node != null;
        }

        public INode next() {
            if (node == null) {
                throw new IllegalStateException("Class ParentINodeIterator: There is no more element.");
            }
            final INode res = node;
            node = node.getParentNode();
            return res;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "Removing elements from a INode's parent hierarchy is not allowed!");
        }
    }

    /**
     * Reveals the closest common ancestor {@link INode} of {@code node0} and {@code node1},
     * assuming each of {@code node0} and {@code1} is contained in the diagram's figure tree
     * if it is unequal to {@code null}.
     *
     * @param node0
     *            an {@link INode} being contained in the diagrams figure tree
     * @param node1
     *            an {@link INode} being contained in the diagrams figure tree
     * @return the closest common ancestor {@link INode}, {@code null} if {@code node0 == null} or
     *         {@code node1 == null} or {@code node0.getParentNode() == null} or
     *         {@code node1.getParentNode() == null}, or {@code node0.getParentNode()} if
     *         {@code node0 == node1}
     */
    public static INode getCommonAncestor(final INode node0, final INode node1) {

        // start with some trivial cases ...
        if (node0 == null || node1 == null) {
            return null;

        } else if (node0 == node1) {
            return node0.getParentNode();

        }

        final INode node0parent = node0.getParentNode();
        final INode node1parent = node1.getParentNode();

        // and some not so trivial cases...
        if (node0parent == null || node1parent == null) {
            // implies 'node0 instanceof KNodeTopNode' xor 'node1 instance KNodeTopNode'
            return null;

        } else if (node0parent == node1parent) {
            // implies 'node0' and 'node1' are siblings
            return node0parent;
        }

        // now apply the big artillery ...
        // build lists containing the parents for both 'node0' and 'node1',
        //  create reverse views on theses lists, and iterators traversing theses views
        final Iterator<INode> node0parents =
                Lists.reverse(Lists.newArrayList(parentINodeIterator(node0))).listIterator();
        final Iterator<INode> node1parents =
                Lists.reverse(Lists.newArrayList(parentINodeIterator(node1))).listIterator();

        INode result = null;

        // now simultaneously traverse the elements ...
        while (node0parents.hasNext() && node1parents.hasNext()) {
            final INode inode0 = node0parents.next();
            final INode inode1 = node1parents.next();

            if (inode0 == inode1) {
                // ... keep the last element found in both lists, ...
                result = node0;
            } else {
                // ... and stop if differing elements occur
                break;
            }
        }

        return result;
    }
}
