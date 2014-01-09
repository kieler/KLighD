/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.awt.geom.Rectangle2D;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
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

    private KNodeTopNode topNode;
    private KNode focusNode = null;


    /**
     * Constructor.
     * 
     * @param theTopNode
     *            the employed {@link KNodeTopNode}
     * @param theCanvasCamera
     *            the employed {@link KlighdMainCamera}
     */
    public DiagramZoomController(final KNodeTopNode theTopNode, final KlighdMainCamera theCanvasCamera) {
        this.canvasCamera = theCanvasCamera;
        this.topNode = theTopNode;
    }

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
     * @param style
     *            the desired style
     * @param duration
     *            time to animate
     */
    public void zoom(final ZoomStyle style, final int duration) {
        switch (style) {
        case ZOOM_TO_FIT:
            zoomToFit(duration);
            break;
        case ZOOM_TO_FOCUS:
            KNode focus = focusNode != null ? focusNode : topNode.getGraphElement();
            zoomToFocus(focus, duration);
            break;
        default:
            // nothing
        }
    }
    
    /**
     * @param duration
     *            time to animate
     */
    private void zoomToFit(final int duration) {
        final KNode displayedKNode = this.canvasCamera.getDisplayedINode().getGraphElement(); 
        final KShapeLayout topNodeLayout = displayedKNode.getData(KShapeLayout.class);
        
        if (topNodeLayout == null) {
            String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to fit' as the topNode's layout data are unavailable. "
                    + "This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }
        
        final PBounds newBounds = new PBounds(topNodeLayout.getXpos(), topNodeLayout.getYpos(),
                            topNodeLayout.getWidth(), topNodeLayout.getHeight());

        includePortAndLabelBounds(newBounds, displayedKNode);
        
        if (this.canvasCamera.getBoundsReference().isEmpty()) {
            // this case occurs while initializing the DiagramEditorPart
            //  since the whole diagram building and layout is performed within 'createPartControl()'
            // at that time, the (widget) layout of the KlighdCanvas has not been performed and,
            //  thus, the root pnodes' bounds are empty
            // this setting will be replaced by 'setBounds()' in KlighdCanvas (inherited)  
            this.canvasCamera.setBounds(newBounds);
        } else {
            this.canvasCamera.animateViewToCenterBounds(newBounds, true, duration);
        }
    }
    
    /**
     * This method checks for ports and labels of the given <code>node</code> and increases the
     * given <code>nodeBounds</code> accordingly.
     */
    private Rectangle2D includePortAndLabelBounds(final Rectangle2D nodeBounds, final KNode node) {
        double maxX = nodeBounds.getWidth();
        double maxY = nodeBounds.getHeight();
        
        // these min values are <= 0 at all times!
        double minX = 0;
        double minY = 0;

        boolean includedElement = false;
        
        for (KGraphElement element : Iterables.concat(node.getPorts(), node.getLabels())) {
            final KShapeLayout pL = element.getData(KShapeLayout.class);
            if (pL.getXpos() < minX) {
                minX = pL.getXpos();
            }
            if (pL.getYpos() < minY) {
                minY = pL.getYpos();
            }
            if (pL.getXpos() + pL.getWidth() > maxX) {
                maxX = pL.getXpos() + pL.getWidth();
            }
            if (pL.getYpos() + pL.getHeight() > maxY) {
                maxY = pL.getYpos() + pL.getHeight();
            }
            includedElement = true;
        }
        
        if (includedElement) {
            nodeBounds.setRect(nodeBounds.getX() + minX, nodeBounds.getY() + minY, maxX - minX,
                    maxY - minY);
        } else {
            final KInsets insets = node.getData(KShapeLayout.class).getInsets();
            nodeBounds.setRect(nodeBounds.getX() + insets.getLeft(),
                    nodeBounds.getY() + insets.getTop(),
                    maxX - insets.getLeft() - insets.getRight(),
                    maxY - insets.getTop() - insets.getBottom());
        }

        return nodeBounds;
    }
    
    /**
     * 
     * @param focus
     *            the desired focus bounds
     * @param duration
     *            duration of the animation
     */
    private void zoomToFocus(final KNode focus, final int duration) {
        final KShapeLayout shapeLayout = focus.getData(KShapeLayout.class);
        final PBounds newBounds = new PBounds(shapeLayout.getXpos(), shapeLayout.getYpos(),
                shapeLayout.getWidth(), shapeLayout.getHeight());

        // we need the bounds in view coordinates (absolute), hence for
        // a knode add the translations of all parent nodes
        KNode parent = focus.getParent();
        while (parent != null) {
            KShapeLayout parentLayout = parent.getData(KShapeLayout.class);
            newBounds.moveBy(parentLayout.getXpos(), parentLayout.getYpos());
            parent = parent.getParent();
        }

        zoomToFocus(newBounds, duration);
    }

    /**
     * 
     * @param focus
     *            the desired focus bounds
     * @param duration
     *            duration of the animation
     */
    private void zoomToFocus(final PBounds focus, final int duration) {
        final PBounds viewBounds = canvasCamera.getViewBounds();

        // check if we need to scale the view in order for the view to
        // contain the whole focus
        boolean scale = viewBounds.getWidth() < focus.getWidth()
                || viewBounds.getHeight() < focus.getHeight();

        // fetch bounds of the whole diagram
        final KShapeLayout topNodeLayout = topNode.getGraphElement().getData(KShapeLayout.class);
        
        final PBounds newBounds = new PBounds(topNodeLayout.getXpos(), topNodeLayout.getYpos(),
                topNodeLayout.getWidth(), topNodeLayout.getHeight());
        
        boolean fullyContains = viewBounds.getWidth() > newBounds.getWidth()
                && viewBounds.getHeight() > newBounds.getHeight();

        // if the viewport can fully accomodate the diagram, we perform zoom to fit 
        if (fullyContains) {
            canvasCamera.animateViewToCenterBounds(newBounds, true, duration);
        } else {
            canvasCamera.animateViewToCenterBounds(focus, scale, duration);
        }
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
        final KShapeLayout topNodeLayout = topNode.getGraphElement().getData(KShapeLayout.class);

        if (topNodeLayout == null) {
            final String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to one' as the topNode's layout data are unavailable. "
                    + "This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }

        final PBounds nodeBounds = new PBounds(topNodeLayout.getXpos(), topNodeLayout.getYpos(),
                        topNodeLayout.getWidth(), topNodeLayout.getHeight());

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
}
