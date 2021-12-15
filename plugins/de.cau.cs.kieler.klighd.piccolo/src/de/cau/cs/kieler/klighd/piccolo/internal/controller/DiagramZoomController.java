/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.Spacing;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.klighd.KlighdPreferences;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KInsets;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PDimension;

/**
 * A dedicated controller encapsulating all the zoom related stuff.
 *
 * @author chsch
 */
public class DiagramZoomController {

    /** the main camera that determines the actually drawn picture. */
    private final KlighdMainCamera canvasCamera;

    private final DiagramController diagramController;

    private final DiagramZoomControllerBoundsComputer boundsComputer;

    private final Spacing defaultZoomToFitContentSpacing;

    /**
     * Constructor.
     *
     * @param theTopNode
     *            the employed {@link KNodeTopNode}
     * @param theCanvasCamera
     *            the employed {@link KlighdMainCamera}
     * @param theDiagramController
     *            the employed {@link DiagramController}
     * @param defaultZoomToFitContentSpacing
     *            default spacing to be applied if {@link ZoomStyle#ZOOM_TO_FIT_CONTENT} is
     *            demanded, see also
     *            {@link de.cau.cs.kieler.klighd.util.KlighdProperties#ZOOM_TO_FIT_CONTENT_SPACING},
     *            may be <code>null</code>.
     */
    public DiagramZoomController(final KNodeTopNode theTopNode,
            final KlighdMainCamera theCanvasCamera, final DiagramController theDiagramController,
            final Spacing defaultZoomToFitContentSpacing) {
        this.canvasCamera = theCanvasCamera;
        this.diagramController = theDiagramController;
        this.defaultZoomToFitContentSpacing = defaultZoomToFitContentSpacing;
        this.boundsComputer = new DiagramZoomControllerBoundsComputer(
                input -> diagramController.isDisplayed(input, false));
    }

    private KNode focusNode = null;

    /**
     * Sets the node to be focused during next 'zoom to focus' run.
     *
     * @param node
     *            the node to be focused during next 'zoom to focus' run
     */
    public void setFocusNode(final KNode node) {
        this.focusNode = node;
    }
    
    /**
     * Performs a zooming depending on the specified style.
     *
     * @param zoomStyle
     *            the desired style
     * @param desiredFocusElement
     *            the {@link KGraphElement} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS} or {@link ZoomStyle#ZOOM_TO_STAY}, is ignored otherwise
     * @param duration
     *            time to animate
     */
    public void zoom(final ZoomStyle zoomStyle, final KGraphElement desiredFocusElement, final int duration) {
        zoom(zoomStyle, desiredFocusElement, null, duration);
    }

    /**
     * Performs a zooming depending on the specified style.
     *
     * @param zoomStyle
     *            the desired style
     * @param desiredFocusElement
     *            the {@link KGraphElement} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS} or {@link ZoomStyle#ZOOM_TO_STAY}, is ignored otherwise
     * @param previousPosition
     *            the position the focused element had in the previous layout run.
     *            Is ignored if the <code>zoomStyle</code> is {@link ZoomStyle#ZOOM_TO_STAY}.
     * @param duration
     *            time to animate
     */
    public void zoom(final ZoomStyle zoomStyle, final KGraphElement desiredFocusElement,
            final KVector previousPosition, final int duration) {
        final KNode focus;

        switch (zoomStyle) {
        case ZOOM_TO_ACTUAL_SIZE:
            zoomToActualSize(duration);
            break;

        case ZOOM_TO_FIT:
            zoomToFit(duration, false, null);
            break;

        case ZOOM_TO_FIT_CONTENT:
            zoomToFit(duration, true, defaultZoomToFitContentSpacing);
            break;

        case ZOOM_TO_FOCUS:
            focus = desiredFocusElement instanceof KNode ? (KNode) desiredFocusElement : focusNode != null
                    ? focusNode : diagramController.getClip();
            zoomToFocus(focus, duration, false);
            break;

        case ZOOM_TO_FOCUS_OR_INCREASE_TO_FIT:
            focus = desiredFocusElement instanceof KNode ? (KNode) desiredFocusElement : focusNode != null
                    ? focusNode : diagramController.getClip();
            zoomToFocus(focus, duration, true);
            break;

        case ZOOM_TO_STAY:
            if (previousPosition != null) {
                zoomToStay(desiredFocusElement, previousPosition, duration);
            }
            break;

        default:
            // nothing
        }
    }

    /**
     * @param duration
     *            time to animate in ms
     */
    private void zoomToActualSize(final int duration) {
        final PBounds newBounds = toPBoundsIncludingPortsAndLabelsOfDisplayedKNode();

        this.canvasCamera.animateViewToTransform(
                PAffineTransform.getTranslateInstance(-newBounds.x, -newBounds.y), duration);
    }

    /**
     * @param duration
     *            time to animate in ms
     * @param narrowDownToContents
     *            set to <code>true</code> yields the bounding box of the nested diagram's content
     *            including <code>node</code>'s ports and labels if visible, with <code>false</code>
     *            the bounds of the given <code>node</code> including its port and labels if visible
     *            are returned
     * @param defaultZoomToFitContentSpacing
     *            default spacing to be applied if <code>narrowDownToContents</code> is
     *            <code>true</code>, see also
     *            {@link de.cau.cs.kieler.klighd.util.KlighdProperties#ZOOM_TO_FIT_CONTENT_SPACING},
     *            may be <code>null</code>.
     */
    private void zoomToFit(final int duration, boolean narrowDownToContents,
            final Spacing defaultZoomToFitContentSpacing) {
        final PBounds newBounds = toPBoundsIncludingPortsAndLabelsOfDisplayedKNode(
                narrowDownToContents, defaultZoomToFitContentSpacing);

        if (this.canvasCamera.getBoundsReference().isEmpty()) {
            // this case occurs while initializing the DiagramEditorPart
            //  since the whole diagram building and layout is performed within 'createPartControl()'
            // at that time, the (widget) layout of the KlighdCanvas has not been performed and,
            //  thus, the root pnode's bounds are empty
            // this setting will be replaced by 'setBounds()' in KlighdCanvas (inherited)
            this.canvasCamera.setBounds(newBounds);
        } else {
            this.canvasCamera.animateViewToCenterBounds(newBounds, true, duration);
        }
    }

    /**
     * Performs a 'zoom to focus' on the diagram to focus {@code focus}.
     *
     * @param focus
     *            the desired focus {@link KNode}
     * @param duration
     *            duration of the animation
     */
    public void zoomToFocus(final KNode focus, final int duration) {
        zoomToFocus(focus, duration,
                KlighdPreferences.getZoomToFocusStyle() == ZoomStyle.ZOOM_TO_FOCUS_OR_INCREASE_TO_FIT);
    }

    /**
     * @param focus
     *            the desired focus bounds
     * @param duration
     *            duration of the animation
     * @param increaseToFit
     *            apply 'zoom to fit' on the (clipped) diagram in case that diagram fits into the
     *            diagram canvas area, see also {@link ZoomStyle#ZOOM_TO_FOCUS_OR_INCREASE_TO_FIT}
     */
    private void zoomToFocus(final KNode focus, final int duration, final boolean increaseToFit) {
        final PBounds diagramBounds = toPBoundsIncludingPortsAndLabelsOfDisplayedKNode();
        final KNode displayedKNode = this.canvasCamera.getDisplayedKNodeNode().getViewModelElement();

        // fetch bounds of the whole visible diagram
        final PBounds focusBounds;

        if (focus == displayedKNode) {
            focusBounds = diagramBounds;

        } else {
            focusBounds = toPBoundsIncludingPortsAndLabels(focus);
            KNode parent = focus.getParent();

            // we need the bounds in view coordinates (absolute), hence for
            // a KNode add the translations of all parent nodes
            
            while (parent != null && parent != displayedKNode) {
                final double scale = parent.getProperty(CoreOptions.SCALE_FACTOR).doubleValue();

                focusBounds.setSize(scale * focusBounds.width, scale * focusBounds.height);
                focusBounds.setOrigin(scale * focusBounds.x, scale * focusBounds.y);

                final KInsets insets = parent.getInsets();
                focusBounds.moveBy(insets.getLeft(), insets.getTop());

                focusBounds.moveBy(parent.getXpos(), parent.getYpos());
                parent = parent.getParent();
            }
        }

        final PBounds viewBounds = canvasCamera.getViewBounds();

        if (increaseToFit) {
            final boolean fullyContains = viewBounds.getWidth() > diagramBounds.getWidth()
                    && viewBounds.getHeight() > diagramBounds.getHeight();

            // if the viewport can fully accommodate the diagram, we perform zoom to fit
            if (fullyContains) {
                canvasCamera.animateViewToCenterBounds(diagramBounds, true, duration);

                return;
            }
        }

        // check if we need to scale the view in order for the view to contain the whole focus
        final boolean scale = viewBounds.getWidth() < focusBounds.getWidth()
                || viewBounds.getHeight() < focusBounds.getHeight();

        canvasCamera.animateViewToCenterBounds(focusBounds, scale, duration);
    }

    /**
     * Sets the zoom level to {@code newZoomLevel}. A value below 1 results in smaller elements than
     * in the original diagram, a value greater than 1 in a bigger elements than in the original.
     *
     * The method tries retain the center point, i.e., to center over the currently centered point,
     * however, it is assured that at least some parts of the underlying diagram are visible.
     *
     * @param newZoomLevel
     *            the new zoom level
     * @param duration
     *            time to animate
     */
    public void zoomToLevel(final float newZoomLevel, final int duration) {
        final PBounds nodeBounds = toPBoundsIncludingPortsAndLabelsOfDisplayedKNode();

        // it would be possible to use PCamera#scaleViewAboutPoint(scale, x, y),
        // however this method does not allow for animation

        // calculate the bound as they would be if scaled by the new factor
        final PBounds origBounds = canvasCamera.getViewBounds();
        final double oldZoomLevel = canvasCamera.getViewTransformReference().getScale();

        final PBounds newBounds = new PBounds(origBounds.x, origBounds.y,
                origBounds.width * oldZoomLevel / newZoomLevel,
                origBounds.height * oldZoomLevel / newZoomLevel);

        // add the necessary translation
        newBounds.moveBy(
                (origBounds.width - newBounds.width) / 2f,
                (origBounds.height - newBounds.height) / 2f);

        // make sure at least some of the diagram is visible after zooming to scale 1
        final PDimension dim = newBounds.deltaRequiredToContain(nodeBounds);
        newBounds.moveBy(dim.width, dim.height);

        // perform the animation
        canvasCamera.animateViewToCenterBounds(newBounds, true, duration);
    }
    
    /**
     * Moves the camera as an animation with the focused element, such that that element stays at the same
     * zoom level and camera position as it has been before with all other elements animating to their new positions
     * smoothly around the focused node.
     * 
     * @param focusElement The element that should not move in the animation.
     * @param previousPosition The position the focused element had in the previous layout run.
     * @param duration The time to animate.
     */
    public void zoomToStay(final EObject focusElement, final KVector previousPosition, final int duration) {
        final KShapeLayout focus;
        if (focusElement instanceof KShapeLayout) {
            focus = (KShapeLayout) focusElement;
        } else {
            // TODO: what about if a KText was focused?
            return;
        }
        final KNode displayedKNode = this.canvasCamera.getDisplayedKNodeNode().getViewModelElement();

        // Fetch bounds of the focused element.
        final PBounds focusBounds;

        if (focus == displayedKNode) {
            focusBounds = toPBoundsIncludingPortsAndLabelsOfDisplayedKNode();

        } else {
            focusBounds = boundsComputer.toPBounds(focus);
            EObject parent = focus.eContainer();

            // We need the bounds in view coordinates (absolute), hence for
            // an element add the translations of all parent elements.

            while (parent != null && parent != displayedKNode.getParent()) {
                while (!(parent instanceof KShapeLayout) && !(parent instanceof EMapPropertyHolder) && parent != null) {
                    parent = parent.eContainer();
                }
                // We know that the parent is a KShapeLayout and an EMapPropertyHolder now, if its not null.
                if (parent == null) {
                    continue;
                }
                
                final double scale = ((EMapPropertyHolder) parent).getProperty(CoreOptions.SCALE_FACTOR).doubleValue();
                
                focusBounds.setSize(scale * focusBounds.width, scale * focusBounds.height);
                focusBounds.setOrigin(scale * focusBounds.x, scale * focusBounds.y);

                final KInsets insets = ((KShapeLayout) parent).getInsets();
                focusBounds.moveBy(insets.getLeft(), insets.getTop());

                focusBounds.moveBy(((KShapeLayout) parent).getXpos(), ((KShapeLayout) parent).getYpos());
                parent = parent.eContainer();
            }
        }
        
        final PAffineTransform transform = canvasCamera.getViewTransform();
        transform.translate(previousPosition.x - focusBounds.x, previousPosition.y - focusBounds.y);

        canvasCamera.animateViewToTransform(transform, duration);
    }

    /**
     * Converts the current diagram clip KNode's layout data into {@link PBounds} s.t. its ports
     * and labels are included, while its scale and offset are excluded.
     *
     * @return the requested bounding box in form of a {@link PBounds}
     */
    private PBounds toPBoundsIncludingPortsAndLabelsOfDisplayedKNode() {
        return toPBoundsIncludingPortsAndLabelsOfDisplayedKNode(false, null);
    }

    /**
     * Converts the current diagram clip KNode's layout data into {@link PBounds} s.t. its ports
     * and labels are included, while its scale and offset are excluded.
     *
     * @param doComputeSubDiagramSize
     *            set to <code>true</code> yields the bounding box of the nested diagram's content
     *            including <code>node</code>'s ports and labels if visible, with <code>false</code>
     *            the bounds of the given <code>node</code> including its port and labels if visible
     *            are returned
     * @param defaultZoomToFitContentSpacing
     *            default spacing to be applied if <code>narrowDownToContents</code> is
     *            <code>true</code>, see also
     *            {@link de.cau.cs.kieler.klighd.util.KlighdProperties#ZOOM_TO_FIT_CONTENT_SPACING},
     *            may be <code>null</code>.
     * @return the requested bounding box in form of a {@link PBounds}
     */
    private PBounds toPBoundsIncludingPortsAndLabelsOfDisplayedKNode(
            final boolean doComputeSubDiagramSize, final Spacing defaultZoomToFitContentSpacing) {
        final KNode displayedKNode = this.canvasCamera.getDisplayedKNodeNode().getViewModelElement();

        final PBounds bounds = toPBoundsIncludingPortsAndLabels(displayedKNode,
                doComputeSubDiagramSize, defaultZoomToFitContentSpacing);
        return eliminateOffsetAndScale(bounds, displayedKNode);
    }

    /**
     * Converts <code>node</code>'s layout data into {@link PBounds} s.t. <code>node</code>'s ports
     * and labels are included, respects an attached {@link LayoutOptions#SCALE_FACTOR}.
     *
     * @param node
     *            the node
     * @return the requested bounding box in form of a {@link PBounds}
     */
    private PBounds toPBoundsIncludingPortsAndLabels(final KNode node) {
        return toPBoundsIncludingPortsAndLabels(node, false, null);
    }

    /**
     * Converts <code>node</code>'s layout data into {@link PBounds} s.t. <code>node</code>'s ports
     * and labels are included, respects an attached {@link LayoutOptions#SCALE_FACTOR}.
     *
     * @param node
     *            the node
     * @param doComputeSubDiagramSize
     *            set to <code>true</code> yields the bounding box of the nested diagram's content
     *            including <code>node</code>'s ports and labels if visible, with <code>false</code>
     *            the bounds of the given <code>node</code> including its port and labels if visible
     *            are returned
     * @param defaultZoomToFitContentSpacing
     *            default spacing to be applied if <code>narrowDownToContents</code> is
     *            <code>true</code>, see also
     *            {@link de.cau.cs.kieler.klighd.util.KlighdProperties#ZOOM_TO_FIT_CONTENT_SPACING},
     *            may be <code>null</code>.
     * @return the requested bounding box in form of a {@link PBounds}
     */
    protected PBounds toPBoundsIncludingPortsAndLabels(final KNode node,
            final boolean doComputeSubDiagramSize, final Spacing defaultZoomToFitContentSpacing) {
        return boundsComputer.toPBoundsIncludingPortsAndLabels(
                node, doComputeSubDiagramSize, defaultZoomToFitContentSpacing, true);
    }

    /**
     * Reduces the given <code>bounds</code> by <code>node</code>'s translation and scale.
     * It's required for computing the visible diagram's bounding box as clipped diagrams are drawn
     * without applying the clip node's transform (containing the scale and offset), see
     * KNodeNode#fullPaint(PPaintContext), and scaling and translating the KNodeTopNode (root node)
     * is invalid.
     *
     * Note that <code>bounds</code>' (x,y) translation may differ from <code>node</code>'s (x,y)
     * translation because of labels, ports, and port labels having negative x and y coordinates,
     * i.e., are placed in left/above <code>node</code>. In that case <code>bounds</code> will than
     * have negative x and/or y coordinates, which is on purpose.
     *
     * @param bounds
     *            full bounds of some {@link KNode} including its ports and labels, scaled by
     *            <code>node</code>'s scale and translated by <code>node</code>'s (x,y)
     * @param node
     *            the {@link KNode} containing the reference scale and offset
     * @return <code>bounds</code> being adjusted as described above (within the argument object)
     */
    protected PBounds eliminateOffsetAndScale(final PBounds bounds, final KNode node) {

        bounds.moveBy(-node.getXpos(), -node.getYpos());

        final float scaleInverse = 1 / node.getProperty(CoreOptions.SCALE_FACTOR).floatValue();
        bounds.setRect(
                bounds.getX()     * scaleInverse, bounds.getY()      * scaleInverse,
                bounds.getWidth() * scaleInverse, bounds.getHeight() * scaleInverse);

        return bounds;
    }
}
