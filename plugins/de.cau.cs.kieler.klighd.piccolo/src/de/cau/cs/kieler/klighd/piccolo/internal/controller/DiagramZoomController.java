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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.KlighdPreferences;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
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

    private final Predicate<KGraphElement> isDisplayedFilter = new Predicate<KGraphElement>() {

        public boolean apply(final KGraphElement input) {
            return diagramController.isDisplayed(input, false);
        }
    };

    /**
     * Constructor.
     *
     * @param theTopNode
     *            the employed {@link KNodeTopNode}
     * @param theCanvasCamera
     *            the employed {@link KlighdMainCamera}
     * @param theDiagramController
     *            the employed {@link DiagramController}
     */
    public DiagramZoomController(final KNodeTopNode theTopNode,
            final KlighdMainCamera theCanvasCamera, final DiagramController theDiagramController) {
        this.canvasCamera = theCanvasCamera;
        this.diagramController = theDiagramController;
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
     * @param desiredFocusNode
     *            the {@link KNode} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS}, is ignored otherwise
     * @param duration
     *            time to animate
     */
    public void zoom(final ZoomStyle zoomStyle, final KNode desiredFocusNode, final int duration) {
        final KNode focus;

        switch (zoomStyle) {
        case ZOOM_TO_ACTUAL_SIZE:
            zoomToActualSize(duration);
            break;

        case ZOOM_TO_FIT:
            zoomToFit(duration);
            break;

        case ZOOM_TO_FOCUS:
            focus = desiredFocusNode != null ? desiredFocusNode : focusNode != null
                    ? focusNode : diagramController.getClip();
            zoomToFocus(focus, duration, false);
            break;

        case ZOOM_TO_FOCUS_OR_INCREASE_TO_FIT:
            focus = desiredFocusNode != null ? desiredFocusNode : focusNode != null
                    ? focusNode : diagramController.getClip();
            zoomToFocus(focus, duration, true);
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
        final KNode displayedKNode = this.canvasCamera.getDisplayedKNodeNode().getViewModelElement();
        final KShapeLayout nodeLayout = displayedKNode.getData(KShapeLayout.class);

        if (nodeLayout == null) {
            final String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to actual size' as the displayed node's layout data are "
                    + "unavailable. This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }

        final PBounds newBounds = toPBoundsIncludingPortsAndLabels(displayedKNode);

        this.canvasCamera.animateViewToTransform(
                PAffineTransform.getTranslateInstance(-newBounds.x, -newBounds.y), duration);
    }

    /**
     * @param duration
     *            time to animate in ms
     */
    private void zoomToFit(final int duration) {
        final KNode displayedKNode = this.canvasCamera.getDisplayedKNodeNode().getViewModelElement();
        final KShapeLayout nodeLayout = displayedKNode.getData(KShapeLayout.class);

        if (nodeLayout == null) {
            final String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to fit' as the displayed node's layout data are "
                    + "unavailable. This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }

        final PBounds newBounds = toPBoundsIncludingPortsAndLabels(displayedKNode);

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
        final KNode displayedKNode = this.canvasCamera.getDisplayedKNodeNode().getViewModelElement();

        // fetch bounds of the whole visible diagram
        final KShapeLayout nodeLayout = displayedKNode.getData(KShapeLayout.class);
        if (nodeLayout == null) {
            final String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to focus' as the displayed node's layout data are "
                    + "unavailable. This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }

        final PBounds focusBounds = toPBoundsIncludingPortsAndLabels(focus);

        // we need the bounds in view coordinates (absolute), hence for
        // a KNode add the translations of all parent nodes

        if (focus != displayedKNode) {
            KNode parent = focus.getParent();

            while (parent != null && parent != displayedKNode.getParent()) {
                final KShapeLayout parentLayout = parent.getData(KShapeLayout.class);
                final double scale = parentLayout.getProperty(LayoutOptions.SCALE_FACTOR).doubleValue();

                focusBounds.setSize(scale * focusBounds.width, scale * focusBounds.height);
                focusBounds.setOrigin(scale * focusBounds.x, scale * focusBounds.y);

                final KInsets insets = parentLayout.getInsets();
                focusBounds.moveBy(insets.getLeft(), insets.getTop());

                focusBounds.moveBy(parentLayout.getXpos(), parentLayout.getYpos());
                parent = parent.getParent();
            }
        }

        final PBounds viewBounds = canvasCamera.getViewBounds();

        if (increaseToFit) {
            final PBounds diagramBounds = toPBoundsIncludingPortsAndLabels(displayedKNode);
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
        final KNode displayedKNode = this.canvasCamera.getDisplayedKNodeNode().getViewModelElement();
        final KShapeLayout topNodeLayout = displayedKNode.getData(KShapeLayout.class);

        if (topNodeLayout == null) {
            final String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to level' as the displayed node's layout data are "
                    + "unavailable. This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }

        final PBounds nodeBounds = toPBoundsIncludingPortsAndLabels(displayedKNode);

        // it would be possible to use PCamera#scaleViewAboutPoint(scale, x, y),
        // however this method does not allow for animation

        // calculate the bound as they would be if scaled by the new factor
        final PBounds origBounds = canvasCamera.getViewBounds();
        final double oldZoomLevel = canvasCamera.getViewTransformReference().getScale();

        final PBounds newBounds = new PBounds(origBounds.x, origBounds.y,
                origBounds.width * oldZoomLevel / newZoomLevel,
                origBounds.height * oldZoomLevel / newZoomLevel);

        // add the necessary translation
        final double normalizedWidth = origBounds.width * oldZoomLevel;
        final double normalizedHeight = origBounds.height * oldZoomLevel;
        final double transX = (origBounds.width - normalizedWidth / newZoomLevel) / 2f;
        final double transY = (origBounds.height - normalizedHeight / newZoomLevel) / 2f;

        newBounds.moveBy(transX, transY);

        // make sure at least some of the diagram is visible after zooming to scale 1
        final PDimension dim = newBounds.deltaRequiredToContain(nodeBounds);
        newBounds.moveBy(dim.width, dim.height);

        // perform the animation
        canvasCamera.animateViewToCenterBounds(newBounds, true, duration);
    }


    /**
     * Converts <code>node</code>'s layout data into {@link PBounds}, respects an attached
     * {@link LayoutOptions#SCALE_FACTOR}.
     *
     * @param node
     *            the node
     * @return the corresponding {@link PBounds}
     */
    public static PBounds toPBounds(final KNode node) {
        final KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        final float scale = nodeLayout.getProperty(LayoutOptions.SCALE_FACTOR);
        return new PBounds(nodeLayout.getXpos(), nodeLayout.getYpos(),
                nodeLayout.getWidth() * scale, nodeLayout.getHeight() * scale);
    }

    /**
     * Converts <code>node</code>'s layout data into {@link PBounds} s.t. <code>node</code>'s ports
     * and labels are included, respects an attached {@link LayoutOptions#SCALE_FACTOR}.
     *
     * @param node
     *            the node
     * @return the corresponding {@link PBounds}
     */
    public PBounds toPBoundsIncludingPortsAndLabels(final KNode node) {
        return includePortAndLabelBounds(toPBounds(node), node);
    }

    /**
     * This method checks for ports and labels of the given <code>node</code> and increases the
     * given <code>nodeBounds</code> accordingly.<br>
     *
     * @param nodeBounds
     *            the bounds of the
     *            {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeAbstractNode
     *            KNodeAbstractNode} representing {@link KNode} <code>node</code>
     * @param node
     *            the {@link KNode} to be evaluated for ports and labels
     * @return the updated <code>nodeBounds</code> for convenience
     */
    private PBounds includePortAndLabelBounds(final PBounds nodeBounds, final KNode node) {
        double maxX = nodeBounds.getWidth();
        double maxY = nodeBounds.getHeight();
        final float scale = node.getData(KShapeLayout.class).getProperty(LayoutOptions.SCALE_FACTOR);

        // these min values are <= 0 at all times!
        double minX = 0;
        double minY = 0;

        boolean includedElement = false;

        // incorporate only those contained ports & labels that are actually visible
        //  others may not have reasonable positions
        for (final KGraphElement element : Iterables.filter(
                Iterables.concat(node.getPorts(), node.getLabels()), isDisplayedFilter)) {
            final KShapeLayout pL = element.getData(KShapeLayout.class);
            float val;

            val = pL.getXpos() * scale;
            if (val < minX) {
                minX = val;
            }

            val = pL.getYpos() * scale;
            if (val < minY) {
                minY = val;
            }

            val = pL.getXpos() * scale + pL.getWidth() * scale;
            if (val > maxX) {
                maxX = val;
            }

            val = pL.getYpos() * scale + pL.getHeight() * scale;
            if (val > maxY) {
                maxY = val;
            }

            includedElement = true;
        }

        if (includedElement) {
            nodeBounds.setRect(nodeBounds.getX() + minX, nodeBounds.getY() + minY,
                    maxX - minX, maxY - minY);
        } else {
            final KInsets insets = node.getData(KShapeLayout.class).getInsets();
            nodeBounds.setRect(nodeBounds.getX() + insets.getLeft() * scale,
                    nodeBounds.getY() + insets.getTop() * scale,
                    maxX - insets.getLeft() - insets.getRight() * scale,
                    maxY - insets.getTop() - insets.getBottom() * scale);
        }

        return nodeBounds;
    }
}
