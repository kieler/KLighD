/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
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
import org.eclipse.swt.graphics.Point;

import com.google.common.base.Strings;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdBasicInputEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IInternalKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPaths;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PStack;

/**
 * The class realizes a tooltip for the {@link KlighdCanvas}. Tooltips are either retrieved from a
 * {@link de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKGraphElementNode IKGraphElementNode}'s
 * displayed root {@link KRendering} or, if this is not available, from the corresponding
 * {@link KGraphElement}'s {@link KLayoutData}.
 *
 * TODO: Evaluate the realization of the tooltip by means of an SWT Widget, too, although that won't
 * be available for the web-based approach.
 *
 * @author uru
 * @author chsch
 */
public class PiccoloTooltip {

    private final KlighdCanvas canvas;

    // graphic elements representing the tooltip
    private final KlighdPath root;
    private final KlighdStyledText tooltip;

    // configuration constants
    private static final int ROUNDNESS = 5;
    private static final int OPACITY = 220;
    private static final int INSETS = 5;
    private static final int OFFSET = 10;

    /**
     * Constructor.
     *
     * @param canvas
     *            the {@link KlighdCanvas} this tooltip provider is attached to
     */
    public PiccoloTooltip(final KlighdCanvas canvas) {
        this.canvas = canvas;

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
        final KlighdMainCamera camera = canvas.getCamera();
        camera.addChild(root);
        camera.addInputEventListener(new TooltipListener());

    }


    /**
     * Listens to the diagram's {@link KlighdMainCamera} and reacts to mouse hover & move events in
     * order to display a tooltip where available.
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
            event.getPath().canvasToLocal(mousePos, canvas.getCamera());

            // retrieve the tooltip
            String tooltipText = null;
            if (rendering != null) {
                tooltipText = rendering.getProperty(KlighdProperties.TOOLTIP);
            }

            if (tooltipText == null && kge != null) {
                final KLayoutData l = kge.getData(KLayoutData.class);
                tooltipText = l.getProperty(KlighdProperties.TOOLTIP);
            }

            // return if no tooltip was assembled
            if (Strings.isNullOrEmpty(tooltipText)) {
                return;
            }

            // prepare the tooltip element
            tooltip.setText(tooltipText);

            // adapt bounds to the text
            final PBounds tooltipBounds = tooltip.getBounds();
            root.setPathToRoundRectangle(
                    (float) tooltipBounds.x - INSETS, (float) tooltipBounds.y - INSETS,
                    (float) tooltipBounds.width + 2 * INSETS, (float) tooltipBounds.height + 2 * INSETS,
                    ROUNDNESS, ROUNDNESS);

            final Point canvasSize = canvas.getSize();

            // determine the position of the tooltip text
            //  if the tooltip is larger the canvas, put it to coordinate 0
            //  if the tooltip + 2 * OFFSET is larger than the canvas, align it centrally
            //  if the mousePos + tooltip + OFFSET if larger than the canvas,
            //   align the tooltip at the right/bottom border preserving OFFSET
            //  otherwise put it to mousePos + OFFSET

            final double x;
            if (canvasSize.x <= tooltipBounds.width) {
                x = 0;
            } else if (canvasSize.x < tooltipBounds.width + 2 * OFFSET) {
                x = (canvasSize.x - tooltipBounds.width) / 2;
            } else if (canvasSize.x < mousePos.getX() + tooltipBounds.width + OFFSET) {
                x = canvasSize.x - tooltipBounds.width - OFFSET;
            } else {
                x = mousePos.getX() + OFFSET;
            }

            final double y;
            if (canvasSize.y <= tooltipBounds.height) {
                y = 0;
            } else if (canvasSize.y < tooltipBounds.height + 2 * OFFSET) {
                y = (canvasSize.y - tooltipBounds.height) / 2;
            } else if (canvasSize.y < mousePos.getY() + tooltipBounds.height + OFFSET) {
                y = canvasSize.y - tooltipBounds.height - OFFSET;
            } else {
                y = mousePos.getY() + OFFSET;
            }

            root.setOffset(x, y);

            // set visible and repaint
            root.setVisible(true);
        }
    }
}
