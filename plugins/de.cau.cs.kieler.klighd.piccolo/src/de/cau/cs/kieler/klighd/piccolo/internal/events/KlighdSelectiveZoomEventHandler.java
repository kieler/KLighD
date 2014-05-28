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
package de.cau.cs.kieler.klighd.piccolo.internal.events;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

/**
 * Input event handler contributing a rubber band like selection of the diagram area to zoom to.
 * Use ctrl/cmd + drag to define the desired view port area.
 * 
 * @author ckru
 * @author chsch
 */
public class KlighdSelectiveZoomEventHandler extends PDragSequenceEventHandler {

    /** The rectangle that acts as a preview for the area to zoom to. */
    private KlighdPath previewRectangle = new KlighdPath();

    /** Starting point of the drag operation. */
    private Point2D.Float canvasStart;

    /** Transparency value used for the preview rectangle. */
    private static final int PREVIEW_RECTANGLE_ALPHA = 50;

    @Override
    protected boolean shouldStartDragInteraction(final PInputEvent event) {
        return event.isControlDown() && super.shouldStartDragInteraction(event);
    }

    @Override
    protected void startDrag(final PInputEvent event) {
        super.startDrag(event);

        final Point2D.Double pos = (Point2D.Double) event.getCanvasPosition();
        canvasStart = new Point2D.Float((float) pos.x, (float) pos.y);

        previewRectangle.setPathToRectangle(canvasStart.x, canvasStart.y, 0, 0);
        previewRectangle.setPaintAlpha(PREVIEW_RECTANGLE_ALPHA);

        event.getTopCamera().addChild(previewRectangle);
        event.setHandled(true);
    }

    @Override
    protected void drag(final PInputEvent event) {
        super.drag(event);

        final Point2D.Double pos = (Point2D.Double) event.getCanvasPosition();

        final Rectangle2D.Float bounds = new Rectangle2D.Float();
        bounds.setFrameFromDiagonal(canvasStart, pos);
        previewRectangle.setPathToRectangle(bounds.x, bounds.y, bounds.width, bounds.height);

        event.setHandled(true);
    }

    @Override
    protected void endDrag(final PInputEvent event) {
        super.endDrag(event);

        final PCamera topCamera = event.getTopCamera();
        topCamera.removeChild(previewRectangle);

        final Rectangle2D.Float bounds = new Rectangle2D.Float();
        bounds.setFrameFromDiagonal(topCamera.localToView(canvasStart), event.getPosition());

        topCamera.animateViewToCenterBounds(bounds, true, KlighdConstants.DEFAULT_ANIMATION_TIME);

        event.setHandled(true);
    }
}
