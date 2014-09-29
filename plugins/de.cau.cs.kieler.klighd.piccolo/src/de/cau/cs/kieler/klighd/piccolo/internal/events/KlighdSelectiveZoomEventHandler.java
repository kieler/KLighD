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

import org.eclipse.swt.SWT;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdKeyEventListener.KlighdKeyEvent;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

/**
 * Input event handler contributing a rubber band like selection of the diagram area to zoom to.
 * Use ctrl/cmd + drag to define the desired view port area.
 * 
 * @author chsch
 * @author ckru
 */
public class KlighdSelectiveZoomEventHandler extends PDragSequenceEventHandler {

    /** Transparency value used for the preview rectangle. */
    private static final int PREVIEW_RECTANGLE_ALPHA = 100;

    /** Minimal drag distance required for initiating the selective zooming. */
    private static final int MINIMAL_DRAG_DISTANCE = 5;

    private final PiccoloViewer viewer;
    
    /** The rectangle that acts as a preview for the area to zoom to. */
    private final KlighdPath previewRectangle;

    /**
     * Constructor.
     * 
     * @param theViewer
     *            the {@link PiccoloViewer} it is attached to
     */
    public KlighdSelectiveZoomEventHandler(final PiccoloViewer theViewer) {
        setMinDragStartDistance(MINIMAL_DRAG_DISTANCE);
        viewer = theViewer;
        previewRectangle = new KlighdPath();
        previewRectangle.setPaintAlpha(PREVIEW_RECTANGLE_ALPHA);
        previewRectangle.setPaint(KlighdConstants.WHITE);
    }

    @Override
    protected boolean shouldStartDragInteraction(final PInputEvent event) {
        return event.isControlDown() && !viewer.isMagnificationLensVisible()
                && super.shouldStartDragInteraction(event);
    }

    @Override
    public void keyPressed(final PInputEvent event) {
        super.keyPressed(event);

        if (isDragging() && ((KlighdKeyEvent) event.getSourceSwingEvent()).getKeyCode() == SWT.ESC) {
            super.endDrag(event);
            event.getTopCamera().removeChild(previewRectangle);
            event.setHandled(true);
        }
    }

    @Override
    protected void startDrag(final PInputEvent event) {
        super.startDrag(event);

        final Point2D.Double canvasStart = (Point2D.Double) this.getMousePressedCanvasPoint();
        previewRectangle.setPathToRectangle((float) canvasStart.x, (float) canvasStart.y, 0, 0);

        event.getTopCamera().addChild(previewRectangle);
        event.setHandled(true);
    }

    @Override
    protected void drag(final PInputEvent event) {
        super.drag(event);

        final Rectangle2D.Float bounds = new Rectangle2D.Float();
        bounds.setFrameFromDiagonal(this.getMousePressedCanvasPoint(), event.getCanvasPosition());
        previewRectangle.setPathToRectangle(bounds);

        event.setHandled(true);
    }

    @Override
    protected void endDrag(final PInputEvent event) {
        super.endDrag(event);

        final PCamera topCamera = event.getTopCamera();
        final Rectangle2D bounds = new Rectangle2D.Double();
        bounds.setFrameFromDiagonal(
                topCamera.localToView(this.getMousePressedCanvasPoint()), event.getPosition());

        topCamera.removeChild(previewRectangle);
        topCamera.animateViewToCenterBounds(bounds, true, KlighdConstants.DEFAULT_ANIMATION_TIME);

        event.setHandled(true);
    }
}