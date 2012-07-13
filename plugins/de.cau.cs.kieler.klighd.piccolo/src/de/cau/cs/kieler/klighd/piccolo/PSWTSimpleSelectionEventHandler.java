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
package de.cau.cs.kieler.klighd.piccolo;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath;

import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventFilter;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PNodeFilter;

/**
 * This handler provides simple interaction for node selection. Clicking selects the object under
 * the cursor and dragging with control pressed offers marquee selection. This handler does not
 * modify the selected nodes in any way, it just provides selection functionality. Much of the
 * implementation is based on {@code PSelectionEventHandler}.
 * 
 * @author mri
 */
public class PSWTSimpleSelectionEventHandler extends PDragSequenceEventHandler {

    /** the camera this handler references. */
    private PCamera camera;
    /** the marquee parent. */
    private PNode marqueeParent;
    /** the listeners on the selection status. */
    private List<INodeSelectionListener> listeners = new LinkedList<INodeSelectionListener>();

    /** the marquee selection rectangle. */
    private PSWTAdvancedPath marquee = null;
    /** the point on the canvas the last press occurred. */
    private Point2D canvasPoint;
    /** the point in transformed coordinates where the last press occurred. */
    private Point2D point;
    /** the node the last press occurred on. */
    private PNode clickNode;
    /** the current marquee bounds. */
    private PBounds marqueeBounds = new PBounds();
    /** whether a marquee selection is currently performed. */
    private boolean marqueeSelection = false;

    /** the currently selected nodes. */
    private Set<PNode> selectedNodes = new LinkedHashSet<PNode>();
    /** the temporary list used for storing the nodes in the marquee. */
    private Set<PNode> marqueeNodes = new LinkedHashSet<PNode>();

    /**
     * Constructs a simple selection event handler.
     * 
     * @param camera
     *            the camera this handler references
     * @param marqueeParent
     *            the parent node to that the handler adds the temporary marquee selection rectangle
     */
    public PSWTSimpleSelectionEventHandler(final PCamera camera, final PNode marqueeParent) {
        this.camera = camera;
        this.marqueeParent = marqueeParent;
        setEventFilter(new PInputEventFilter(InputEvent.BUTTON1_MASK));
    }

    /**
     * Adds the given node to the selection.
     * 
     * @param node
     *            the node
     * @return true if the node was newly added to the selection; false else
     */
    public boolean select(final PNode node) {
        // chsch: implementation changed
        boolean selected = internalSelect(node);
        if (selected) {
            notifyListenersSelection();
        }
        return selected;
    }

    private boolean internalSelect(final PNode node) {
        if (node == null || !node.getPickable()) {
            return false;
        }
        if (selectedNodes.add(node)) {
            notifyListenersSelected(node);
            return true;
        }
        return false;
    }

    /**
     * Adds the given nodes to the selection.
     * 
     * @param nodes
     *            the nodes
     */
    public void select(final Collection<PNode> nodes) {
        boolean selected = false;
        for (PNode node : nodes) {
            if (internalSelect(node)) {
                selected = true;
            }
        }
        if (selected) {
            notifyListenersSelection();
        }
    }

    /**
     * Removes the given node from the selection.
     * 
     * @param node
     *            the node
     * @return true if the node has been removed from the selection; false else
     */
    public boolean unselect(final PNode node) {
        // chsch: implementation changed
        boolean unselected = internalUnselect(node);
        if (unselected) {
            notifyListenersSelection();
        }
        return unselected;
    }

    private boolean internalUnselect(final PNode node) {
        if (selectedNodes.remove(node)) {
            notifyListenersUnselected(node);
            return true;
        }
        return false;
    }

    /**
     * Removes the given nodes from the selection.
     * 
     * @param nodes
     *            the nodes
     */
    public void unselect(final Collection<PNode> nodes) {
        boolean unselected = false;
        for (PNode node : nodes) {
            if (internalUnselect(node)) {
                unselected = true;
            }
        }
        if (unselected) {
            notifyListenersSelection();
        }
    }

    /**
     * Removes all nodes from the selection.
     */
    public void unselectAll() {
        unselect(getSelection());
    }

    /**
     * Returns whether the given node is currently selected.
     * 
     * @param node
     *            the node
     * @return true if the node is currently selected; false else
     */
    public boolean isSelected(final PNode node) {
        return node != null && selectedNodes.contains(node);
    }

    /**
     * Returns a copy of the current selection.
     * 
     * @return the current selection
     */
    public Set<PNode> getSelection() {
        return Sets.newLinkedHashSet(selectedNodes);
    }
    
    /**
     * Returns the current selection.
     * 
     * @return the current selection
     */
    public Set<PNode> getSelectionReference() {
        return Collections.unmodifiableSet(selectedNodes);
    }

    /**
     * Adds a listener on the current selection.
     * 
     * @param listener
     *            the listener
     */
    public void addSelectionListener(final INodeSelectionListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener on the current selection.
     * 
     * @param listener
     *            the listener
     */
    public void removeSelectionListener(final INodeSelectionListener listener) {
        listeners.remove(listener);
    }

    private void notifyListenersSelection() {
        for (INodeSelectionListener listener : listeners) {
            listener.selection(this, getSelection());
        }
    }
    
    private void notifyListenersSelected(final PNode selectedNode) {
        for (INodeSelectionListener listener : listeners) {
            listener.selected(this, selectedNode);
        }
    }

    private void notifyListenersUnselected(final PNode unselectedNode) {
        for (INodeSelectionListener listener : listeners) {
            listener.unselected(this, unselectedNode);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void startDrag(final PInputEvent event) {
        super.startDrag(event);
        initializeSelection(event);
        if (isMarqueeSelection(event)) {
            marqueeSelection = true;
            initializeMarquee(event);
            startMarqueeSelection(event);
        } else {
            marqueeSelection = false;
            startSelection(event);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void drag(final PInputEvent event) {
        super.drag(event);
        if (marqueeSelection) {
            if (isMarqueeSelection(event)) {
                updateMarquee(event);
            } else {
                endDrag(event);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void endDrag(final PInputEvent event) {
        super.endDrag(event);
        if (marqueeSelection) {
            if (isMarqueeSelection(event)) {
                computeMarqueeSelection(event);
                endMarqueeSelection(event);
            } else {
                endMarqueeSelection(event);
            }
        } else {
            endSelection(event);
        }
    }

    private void initializeSelection(final PInputEvent event) {
        canvasPoint = event.getCanvasPosition();
        point = event.getPosition();
        clickNode = event.getPath().getPickedNode();
        if (clickNode instanceof PCamera) {
            clickNode = null;
        }
    }

    private void initializeMarquee(final PInputEvent event) {
        marquee =
                PSWTAdvancedPath.createRectangle((float) point.getX(), (float) point.getY(), 0, 0);
        marquee.setLineStyle(PSWTAdvancedPath.LineStyle.DASH);
        marquee.setStrokeColor(Color.black);
        marquee.setPaint(null);
        marquee.setPickable(false);
        marqueeParent.addChild(marquee);
        marqueeBounds.reset();
        marqueeBounds.add(point);
    }

    private void startSelection(final PInputEvent event) {
        // clicking an already selected node does nothing
        if (isSelected(clickNode)) {
            return;
        }
        unselectAll();
        // select the node
        if (clickNode != null) {
            select(clickNode);
        }
    }

    private void startMarqueeSelection(final PInputEvent event) {
        unselectAll();
    }

    private void updateMarquee(final PInputEvent event) {
        // update the marquee visuals
        marqueeBounds.reset();
        if (marqueeParent instanceof PCamera) {
            marqueeBounds.add(canvasPoint);
            marqueeBounds.add(event.getCanvasPosition());
        } else {
            marqueeBounds.add(point);
            marqueeBounds.add(event.getPosition());
        }
        marquee.globalToLocal(marqueeBounds);
        marquee.setPathToRectangle((float) marqueeBounds.x, (float) marqueeBounds.y,
                (float) marqueeBounds.width, (float) marqueeBounds.height);
        marqueeBounds.reset();
        marqueeBounds.add(point);
        marqueeBounds.add(event.getPosition());
    }

    @SuppressWarnings("unchecked")
    private void updateMarqueeSelection() {
        // update the selected items
        marqueeNodes.clear();
        final PNodeFilter filter = new BoundsFilter(marqueeBounds);
        for (PLayer layer : (List<PLayer>) camera.getLayersReference()) {
            layer.getAllNodes(filter, marqueeNodes);
        }
    }

    private void computeMarqueeSelection(final PInputEvent event) {
        updateMarqueeSelection();
        // unselect all nodes not in the marquee selection
        List<PNode> unselectNodes = new LinkedList<PNode>();
        for (PNode node : selectedNodes) {
            if (!marqueeNodes.contains(node)) {
                unselectNodes.add(node);
            }
        }
        unselect(unselectNodes);
        // select all nodes not yet selected
        select(marqueeNodes);
    }

    private void endSelection(final PInputEvent event) {
        clickNode = null;
    }

    private void endMarqueeSelection(final PInputEvent event) {
        marqueeNodes.clear();
        marquee.removeFromParent();
        marquee = null;
    }

    private boolean isMarqueeSelection(final PInputEvent event) {
        return clickNode == null && event.isControlDown();
    }

    /**
     * Class used to filter nodes that intersect with the marquee's bounds.
     */
    protected class BoundsFilter implements PNodeFilter {

        private final PBounds localBounds = new PBounds();
        private final PBounds bounds;

        /**
         * Constructs a bounds filter for the given bounds.
         * 
         * @param bounds
         *            bounds to be used when testing nodes for intersection
         */
        protected BoundsFilter(final PBounds bounds) {
            this.bounds = bounds;
        }

        /**
         * {@inheritDoc}
         */
        public boolean accept(final PNode node) {
            localBounds.setRect(bounds);
            node.globalToLocal(localBounds);
            final boolean boundsIntersects = node.intersects(localBounds);
            return node.getPickable() && boundsIntersects && node != marquee;
        }

        /**
         * {@inheritDoc}
         */
        public boolean acceptChildrenOf(final PNode node) {
            // always consider all nodes in the marquee
            return true;
        }

    }

}
