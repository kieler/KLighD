/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.viewer;

import java.awt.Color;
import java.awt.geom.Point2D;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Strings;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdBasicInputEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IInternalKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPaths;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PStack;

/**
 * The class realizes a tooltip for the
 * {@link de.cau.cs.kieler.klighd.piccolo.internal.KlighdCanvas KlighdCanvas}. Tooltips are
 * either retrieved from a {@link edu.umd.cs.piccolo.PNode PNode}'s root {@link KRendering} or, if
 * this is not available, from the corresponding {@link KNode}'s {@link KShapeLayout}.
 * 
 * TODO: Evaluate the realization of the tooltip by means of an SWT Widget, too, although that won't
 * be available for the web-based approach.
 * 
 * @author uru
 * @author chsch
 */
public class PiccoloTooltip {

    private PCamera camera;

    // graphic elements representing the tooltip
    private KlighdPath root;
    private KlighdStyledText tooltip;

    // configuration constants
    private static final int ROUNDNESS = 5;
    private static final int OPACITY = 220;
    private static final int INSETS = 5;

    /**
     * @param display
     *            the current display of the canvas displaying the corresponding diagram.
     * @param camera
     *            the main camera observing the corresponding diagram.
     */
    public PiccoloTooltip(final Display display, final PCamera camera) {
        this.camera = camera;

        // create the text element for the tooltip
        tooltip = new KlighdStyledText("");
        tooltip.setPickable(false);
        tooltip.setFont(new FontData(KlighdConstants.DEFAULT_FONT_NAME,
                KlighdConstants.DEFAULT_TOOL_TIP_FONT_SIZE, KlighdConstants.DEFAULT_FONT_STYLE));

        // create a rounded box where the tooltip will be displayed
        root = KlighdPaths.createRoundRectangle(0, 0, 0, 0, ROUNDNESS, ROUNDNESS);
        root.setVisible(false);
        root.setPaint(Color.WHITE);
        root.setPaintAlpha(OPACITY);
        root.addChild(tooltip);

        // add to the camera
        camera.addChild(root);
        camera.addInputEventListener(new TooltipListener());

    }

    
    /**
     * Listens to the {@link PCamera} and reacts to mouse hover & move events in order to display a
     * tooltip where available.
     */
    private class TooltipListener extends KlighdBasicInputEventHandler {
        
        /** The last mouseover's KNode (only used if no rendering is available). */
        private KGraphElement kge;
        
        /** Position at which the tooltip is displayed. */
        private Point2D mousePos;
        
        /**
         * Constructor.
         */
        public TooltipListener() {
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseMoved(final PInputEvent event) {
            root.setVisible(false);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseDragged(final PInputEvent event) {
            root.setVisible(false);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseHovered(final PInputEvent event) {
            event.setHandled(true);

            // determine the IGraphElement the mouse is over
            final PStack nodeStack = event.getPath().getNodeStackReference();

            Object n = nodeStack.pop();
            while (!nodeStack.isEmpty() && !(n instanceof IInternalKGraphElementNode<?>)) {
                n = nodeStack.pop();
            }

            final IInternalKGraphElementNode<?> graphElement;

            if (n instanceof KNodeTopNode) {
                return;
            } else if (n instanceof IInternalKGraphElementNode<?>) {
                 graphElement = (IInternalKGraphElementNode<?>) n;
            } else {
                return;
            }

            final AbstractKGERenderingController<?, ?> ctr = graphElement.getRenderingController();
            final KRendering rendering = ctr.getCurrentRendering();

            // fall-back to the KGraphElement if no rendering is specified
            kge = graphElement.getViewModelElement();

            // only start the timer if we retrieved an element
            if (rendering == null && kge == null) {
                return;
            }

            // get the mouse position
            mousePos = event.getCanvasPosition();
            event.getPath().canvasToLocal(mousePos, camera);

            // retrieve the tooltip
            String tooltipText = null;
            if (rendering != null) {
                tooltipText = rendering.getProperty(KlighdProperties.TOOLTIP);
            } 

            if (tooltipText == null && kge != null) {
                KLayoutData l = kge.getData(KLayoutData.class);
                tooltipText = l.getProperty(KlighdProperties.TOOLTIP);
            }

            // return if no tooltip was assembled
            if (Strings.isNullOrEmpty(tooltipText)) {
                return;
            }

            // prepare the tooltip element
            tooltip.setText(tooltipText);
            root.setOffset(mousePos.getX() + 2 * INSETS, mousePos.getY() + 2 * INSETS);

            // adapt bounds to the text
            final PBounds tooltipBounds = tooltip.getBounds();
            root.setPathToRoundRectangle((float) tooltipBounds.x - INSETS,
                    (float) tooltipBounds.y - INSETS, (float) tooltipBounds.width + 2 * INSETS,
                    (float) tooltipBounds.height + 2 * INSETS, ROUNDNESS, ROUNDNESS);

            // set visible and repaint
            root.setVisible(true);
        }
    }
}
