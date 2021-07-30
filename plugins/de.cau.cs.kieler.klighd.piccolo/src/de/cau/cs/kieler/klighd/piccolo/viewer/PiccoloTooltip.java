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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.piccolo.viewer;

import java.awt.Color;
import java.awt.geom.Point2D;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;

import com.google.common.base.Strings;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.piccolo.KlighdNode;
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
 * The class realizes a tooltip for the {@link KlighdCanvas}. Tooltips are retrieved from a
 * {@link de.cau.cs.kieler.klighd.piccolo.KlighdNode.KlighdFigureNode KlighdFigureNode}'s
 * displayed {@link KRendering},
 * a {@link de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKGraphElementNode IKGraphElementNode} or,
 * if this is not available, from the corresponding {@link KGraphElement}'s {@link KLayoutData}.
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
        TooltipListener() {
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

            // Determine the IInternalKGraphElementNode and the KlighdNode.KlighdFigureNode the mouse is over.
            final PStack nodeStack = event.getPath().getNodeStackReference();

            // The topmost graph element and rendering element from the stack.
            IInternalKGraphElementNode<?> graphElement = null;
            KRendering rendering = null;
            
            Object n = nodeStack.pop();
            // Continue if the stack is empty or a graph element is found.
            while (!(nodeStack.isEmpty() || (graphElement instanceof IInternalKGraphElementNode<?>))) {
                if (n instanceof IInternalKGraphElementNode<?> && graphElement == null) {
                    graphElement = (IInternalKGraphElementNode<?>) n;
                }
                if (n instanceof KlighdNode.KlighdFigureNode<?> && rendering == null) {
                    rendering = ((KlighdNode.KlighdFigureNode<?>) n).getViewModelElement();
                }
                n = nodeStack.pop();
            }


            if (graphElement instanceof KNodeTopNode || graphElement == null) {
                return;
            }

            if (rendering == null) {
                final AbstractKGERenderingController<?, ?> ctr = graphElement.getRenderingController();
                rendering = ctr.getCurrentRendering();
            }

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
            while (tooltipText == null && rendering instanceof KRendering) {
                tooltipText = rendering.getProperty(KlighdProperties.TOOLTIP);
                rendering = rendering.getParent();
            }

            if (tooltipText == null && kge != null) {
                tooltipText = kge.getProperty(KlighdProperties.TOOLTIP);
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
