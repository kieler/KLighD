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

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;

import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPaths;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

/**
 *
 * Class for handling zooming to an selected part of a klighd diagram.
 * Use ctrl + drag to define area to zoom to.
 * 
 * @author ckru
 *
 */
public class KlighdSelectiveZoomEventHandler extends PDragSequenceEventHandler {
    
    /**
     * Starting point of the drag operation.
     */
    private Point start;

    /**
     * The rectangle that acts as a preview for the area to zoom to. 
     */
    private KlighdPath previewRectangle = null;

    @Override
    protected void startDrag(final PInputEvent event) {
        super.startDrag(event);
        if ((event.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {

            start =
                    new Point((int) event.getPosition().getX(), (int) event.getPosition()
                            .getY());
            Point2D local = event.getTopCamera().viewToLocal(new Point(start.x, start.y));
            previewRectangle =
                    KlighdPaths.createRectangle((int) local.getX(), (int) local.getY(),
                            0, 0);
            previewRectangle.setStrokeColor(new RGB(0, 0, 0));
            previewRectangle.setPaintAlpha(50);
            event.getTopCamera().addChild(previewRectangle);

        }

    }

    @Override
    protected void drag(final PInputEvent event) {
        super.drag(event);
        Point2D local = event.getTopCamera().viewToLocal(new Point(start.x, start.y));
        Point2D local2 =
                event.getTopCamera().viewToLocal(
                        new Point((int) event.getPosition().getX(), (int) event
                                .getPosition().getY()));
        Rectangle rec = this.getRectangle(local, local2);
        previewRectangle.setPathToRectangle(rec.x, rec.y, rec.width, rec.height);
    }

    /**
     * Duration of the animation of the zoom.
     */
    private static int zoomAnimationTime = 400;
    
    @Override
    protected void endDrag(final PInputEvent event) {
        super.endDrag(event);

        event.getTopCamera().removeChild(previewRectangle);
                
        event.getTopCamera().animateViewToCenterBounds(this.getRectangle(start, event.getPosition()),
                true, zoomAnimationTime);
    }

    @Override
    protected boolean shouldStartDragInteraction(final PInputEvent event) {
        return super.shouldStartDragInteraction(event)
                && ((event.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK);
    }
    
    private Rectangle getRectangle(final Point2D startPoint, final Point2D endPoint) {
        Point topLeft = new Point((int) Math.min(startPoint.getX(), endPoint.getX()), 
                (int) Math.min(startPoint.getY(), endPoint.getY()));
        Point bottomRight = new Point((int) Math.max(startPoint.getX(), endPoint.getX()), 
                (int) Math.max(startPoint.getY(), endPoint.getY()));
        Dimension dim = new Dimension((int) (bottomRight.getX() - topLeft.getX()), 
                (int) (bottomRight.getY() - topLeft.getY())); 
        return new Rectangle(topLeft, dim);
    }
    
}
