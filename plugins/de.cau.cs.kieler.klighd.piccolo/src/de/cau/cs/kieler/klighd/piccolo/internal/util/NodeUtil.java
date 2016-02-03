/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.util;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.PNodeController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IInternalKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;
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
     * Casts a custom {@link PNode} object that implements {@link IInternalKGraphElementNode} to
     * {@link IInternalKGraphElementNode}, the <b>type check is omitted for performance reasons</b>.
     *
     * @param node
     *            a custom {@link PNode} implementing {@link IInternalKGraphElementNode}
     * @return node typed as {@link IInternalKGraphElementNode}
     */
    public static IInternalKGraphElementNode<?> asKGENode(final PNode node) {
        final IInternalKGraphElementNode<?> graphNode = (IInternalKGraphElementNode<?>) node;
        return graphNode;
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
    public static void applyTranslation(final PNode node, final double x, final double y) {
        final AffineTransform t = node.getTransformReference(true);

        if (t.getTranslateX() != x || t.getTranslateY() != y) {
            // apply the translation
            node.setOffset(x, y);
        }
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
     */
    public static void applyBounds(final PNode node, final double x, final double y,
            final double width, final double height) {
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
        controller.getTransformedPNode().setBounds(0, 0,  bounds.getWidth(), bounds.getHeight());

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
        final PNode transformedNode = controller.getTransformedPNode();
        final PAffineTransform transform = transformedNode.getTransformReference(true);

        if (transform.getTranslateX() == bounds.getX()
                && transform.getTranslateY() == bounds.getY()) {
            return;
        }

        // reset the current affine transform,
        //  this is required to ignore the previously configured rotation
        transform.setToIdentity();

        // apply the translation
        // relying on #applyTranslation(PNode, float, float) doesn't work for (x,y) == (0,0)
        //  because of the above transform reset (which by intention doesn't cause a notification)
        transformedNode.setOffset(bounds.getX(), bounds.getY());

        // (re-)apply the rotation,
        //  'controller' is in charge of caching the required angle and anchor data
        //  invalidation of 'transformedNodes's current bounds already done in applyTranslation
        controller.applyRotation();
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
     * @param root
     *            the diagram's {@link PRoot} in order to avoid traversing to the root for each node
     *            (see usage of this method)
     * @param activity
     *            the primary activity
     */
    public static void schedulePrimaryActivity(final PNode node, final PRoot root,
            final PActivity activity) {

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
        root.addActivity(activity);
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
    public static PBounds clipRelativeGlobalBoundsOf(final PNode node, final IKNodeNode clipNode) {
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
     * {@link IKNodeNode}'s children sub tree.
     *
     * @param node
     *            the PNode to be tested
     * @param camera
     *            the camera the test is based on
     * @return <code>true</code> if node is contained in <code>camera</code>'s displayed
     *         {@link IKNodeNode}'s deep children, <code>false</code> otherwise.
     */
    public static boolean isDisplayed(final PNode node, final KlighdMainCamera camera) {
        if (camera == null) {
            throw new IllegalArgumentException(
                    "KLighD: 'camera' in NodeUtil.isDisplayed(...) must not be 'null'");
        }
        final PLayer displayedLayer = camera.getDisplayedKNodeNode();

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

        ParentIterator(final PNode child, final boolean includingSelf) {
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
     * Creates an iterator traversing along the 'parentNode' chain of the given {@link IKNodeNode}
     * {@code node}.
     *
     * @param node
     *            the {@link IKNodeNode} to start with
     * @return the an {@link Iterator} all parents of {@code node}.
     */
    public static Iterator<IKNodeNode> parentINodeIterator(final IKNodeNode node) {
        return new ParentINodeIterator(node, false);
    }

    /**
     * A simple implementation of the {@link Iterator} interface allowing to traverse the
     * 'parent' chain of {@link IKNodeNode IKNodeNodes}.
     *
     * @author chsch
     */
    private static class ParentINodeIterator implements Iterator<IKNodeNode> {

        private IKNodeNode node;

        ParentINodeIterator(final IKNodeNode child, final boolean includingSelf) {
            if (child == null) {
                throw new IllegalArgumentException("Class ParentINodeIterator:"
                        + "Constructor of ParentINodeIterator requires a non-null input.");
            }
            this.node = includingSelf ? child : child.getParentKNodeNode();
        }

        public boolean hasNext() {
            return node != null;
        }

        public IKNodeNode next() {
            if (node == null) {
                throw new IllegalStateException("Class ParentINodeIterator: There is no more element.");
            }
            final IKNodeNode res = node;
            node = node.getParentKNodeNode();
            return res;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "Removing elements from a INode's parent hierarchy is not allowed!");
        }
    }

    /**
     * Reveals the closest common ancestor {@link IKNodeNode} of {@code node0} and {@code node1},
     * assuming each of {@code node0} and {@code1} is contained in the diagram's figure tree
     * if it is unequal to {@code null}.
     *
     * @param node0
     *            an {@link IKNodeNode} being contained in the diagrams figure tree
     * @param node1
     *            an {@link IKNodeNode} being contained in the diagrams figure tree
     * @return the closest common ancestor {@link IKNodeNode}, {@code null} if {@code node0 == null} or
     *         {@code node1 == null} or {@code node0.getParentNode() == null} or
     *         {@code node1.getParentNode() == null}, or {@code node0.getParentNode()} if
     *         {@code node0 == node1}
     */
    public static IKNodeNode getCommonAncestor(final IKNodeNode node0, final IKNodeNode node1) {

        // start with some trivial cases ...
        if (node0 == null || node1 == null) {
            return null;

        } else if (node0 == node1) {
            return node0.getParentKNodeNode();

        }

        final IKNodeNode node0parent = node0.getParentKNodeNode();
        final IKNodeNode node1parent = node1.getParentKNodeNode();

        // and some not so trivial cases...
        if (node0parent == null || node1parent == null) {
            // implies 'node0 instanceof KNodeTopNode' xor 'node1 instance KNodeTopNode'
            return null;

        } else if (node0parent == node1parent) {
            // implies 'node0' and 'node1' are siblings
            return node0parent;
        }

        // now apply the big artillery ...
        // build lists containing the parents for both 'node0' and 'node1'
        //  and create iterators starting with the (assumed) common root of 'node0' and 'node1'
        final List<IKNodeNode> node0parents = Lists.newArrayList(parentINodeIterator(node0));
        final ListIterator<IKNodeNode> node0parentIt = node0parents.listIterator(node0parents.size());

        final List<IKNodeNode> node1parents = Lists.newArrayList(parentINodeIterator(node1));
        final ListIterator<IKNodeNode> node1parentIt = node1parents.listIterator(node1parents.size());

        IKNodeNode result = null;

        // now simultaneously traverse the elements ...
        while (node0parentIt.hasPrevious() && node1parentIt.hasPrevious()) {
            final IKNodeNode inode0 = node0parentIt.previous();
            final IKNodeNode inode1 = node1parentIt.previous();

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


    // Some convenient children iterator factory methods

    /**
     * Returns an {@link TreeIterator} providing {@code node}'s direct and recursively children.
     *
     * @param node
     *            the root {@link PNode}
     * @return the requested {@link Iterator}
     */
    public static TreeIterator<PNode> getDeepChildrenIterator(final PNode node) {
        return getDeepChildrenIterator(node, false);
    }

    /**
     * Returns an {@link TreeIterator} providing {@code node}'s direct and recursively children.
     *
     * @param node
     *            the root {@link PNode}
     * @param includeRoot
     *            if <code>true</code> {@code node} is provided, too
     * @return the requested {@link Iterator}
     */
    public static TreeIterator<PNode> getDeepChildrenIterator(final PNode node,
            final boolean includeRoot) {
        return getSubtreeFilteredDeepChildrenIterator(node, includeRoot, null);
    }

    /**
     * Returns an {@link TreeIterator} providing {@code node}'s direct and recursively children.<br>
     * <b>Caution:</b> If {@code filter(x)} returns <code>false</code> for a {@link PNode} {@code x}
     * the children of {@code x} are not visited!
     *
     * @param node
     *            the root {@link PNode}
     * @param includeRoot
     *            if <code>true</code> {@code node} is provided, too
     * @param filter
     *            the filter {@link Predicate} to apply to each child subtree, if equal to
     *            <code>null</code> no filtering is performed
     * @return the requested {@link Iterator}
     */
    public static TreeIterator<PNode> getSubtreeFilteredDeepChildrenIterator(
            final PNode node, final boolean includeRoot, final Predicate<PNode> filter) {

        return new AbstractTreeIterator<PNode>(node) {
            private static final long serialVersionUID = -7784774420574849372L;

            @Override
            protected Iterator<PNode> getChildren(final Object object) {
                @SuppressWarnings("unchecked")
                final Iterator<PNode> res = ((PNode) object).getChildrenIterator();

                if (filter == null) {
                    return res;
                } else {
                    return Iterators.filter(res, filter);
                }
            }
        };
    }
}
