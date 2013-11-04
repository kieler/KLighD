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

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdBasicInputEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IGraphElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPaths;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * The class realizes a tooltip for the
 * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas KlighdCanvas}. Tooltips are
 * either retrieved from a {@link PNode}'s root {@link KRendering} or, if this is not available,
 * from the corresponding {@link KNode}'s {@link KShapeLayout}.
 * 
 * TODO: Evaluate the realization of the tooltip by means of an SWT Widget, too, although that won't
 *  be available for the web-based approach.
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
     *            the current display of the {@link de.cau.cs.kieler.klighd.viewers.KlighdViewer
     *            KlighdViewer}.
     * @param camera
     *            the camera of the current {@link de.cau.cs.kieler.klighd.viewers.KlighdViewer
     *            KlighdViewer}'s canvas.
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
        private KNode knode;
        
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
            // retrieve the mouse we are over
            PNode n = event.getPickedNode();

            if (n instanceof IGraphElement<?>) {
                IGraphElement<?> graphElement = (IGraphElement<?>) n;
                AbstractKGERenderingController<?, ?> ctr = graphElement.getRenderingController();

                final KRendering rendering = ctr.getCurrentRendering();

                // fallback to the KNode if no rendering is specified
                if (rendering == null) {
                    Object ge = graphElement.getGraphElement();
                    if (ge instanceof KNode) {
                        knode = (KNode) ge;
                    }
                }

                // only start the timer if we retrieved an element
                if (rendering == null && knode == null) {
                    return;
                }
                // get the mouse position
                mousePos = event.getCanvasPosition();
                event.getPath().canvasToLocal(mousePos, camera);

                // retrieve the tooltip
                String tooltipText = "";
                if (rendering != null) {
                    tooltipText = rendering.getProperty(KlighdProperties.TOOLTIP);
                } else if (knode != null) {
                    KShapeLayout l = knode.getData(KShapeLayout.class);
                    tooltipText = l.getProperty(KlighdProperties.TOOLTIP);
                } else {
                    return;
                }

                // return if no tooltip was assembled
                if (Strings.isNullOrEmpty(tooltipText)) {
                    return;
                }

                // prepare the tooltip element
                tooltip.setText(tooltipText);
                root.setOffset(mousePos.getX() + 2 * INSETS, mousePos.getY() + 2 * INSETS);

                // adapt bounds to the text
                PBounds tooltipBounds = tooltip.getBounds();
                root.setPathToRoundRectangle((float) tooltipBounds.x - INSETS,
                        (float) tooltipBounds.y - INSETS, (float) tooltipBounds.width + 2 * INSETS,
                        (float) tooltipBounds.height + 2 * INSETS, ROUNDNESS, ROUNDNESS);

                // set visible and repaint
                root.setVisible(true);
            }
        }
    }
}
