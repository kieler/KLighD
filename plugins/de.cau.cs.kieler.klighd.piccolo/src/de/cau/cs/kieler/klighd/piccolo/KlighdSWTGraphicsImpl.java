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
package de.cau.cs.kieler.klighd.piccolo;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.TextLayout;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.util.RGBGradient;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolox.swt.SWTGraphics2D;
import edu.umd.cs.piccolox.swt.SWTShapeManager;

/**
 * Standard implementation of {@link KlighdSWTGraphics} based on Piccolo2D's {@link SWTGraphics2D}.
 * It's aim is to rewind the supplements added to the Piccolo implementation.
 * 
 * @author chsch
 */
public class KlighdSWTGraphicsImpl extends SWTGraphics2D implements KlighdSWTGraphics {

    /**
     * Constructor for SWTGraphics2D.
     * 
     * @param gc
     *            The Eclipse Graphics Context onto which all Graphics2D operations are
     *            delegating
     * @param device
     *            Device onto which ultimately all gc operations are drawn onto
     */
    public KlighdSWTGraphicsImpl(final GC gc, final Device device) {
        super(gc, device);
    }

    /**
     * Constructor for SWTGraphics2D.
     * 
     * @param gc
     *            The Eclipse Graphics Context onto which all Graphics2D operations are
     *            delegating
     * @param tl
     *            The Eclipse Graphics Context onto which all Graphics2D operations are
     *            delegating
     * @param device
     *            Device onto which ultimately all {@link GC} operations are drawn onto
     */
    public KlighdSWTGraphicsImpl(final GC gc, final TextLayout tl, final Device device) {
        super(gc, tl, device);
    }

    private static final Rectangle2D.Float TEMP_RECT = new Rectangle2D.Float();

    /**
     * {@inheritDoc}
     */
    public void setDevice(final Device theDevice) {
        this.device = theDevice;
    }

    /**
     * {@inheritDoc}
     */
    public void setGC(final GC theGc) {
        this.gc = theGc;
        this.gc.setAntialias(SWT.ON);
    }

    /**
     * {@inheritDoc}
     */
    public void setAlpha(final int alpha) {
        super.setAlpha(alpha);
    }

    /**
     * {@inheritDoc}
     */
    public void setPattern(final RGBGradient gradient, final Rectangle2D bounds) {
        TEMP_RECT.setRect(bounds);
        SWTShapeManager.transform(TEMP_RECT, this.getTransform());

        Point2D[] points = computePatternPoints(TEMP_RECT, Math.toRadians(gradient.getAngle()));

        final float curAlpha = (float) this.getAlpha();
        final int alpha1 = (int) (gradient.getAlpha1() * (curAlpha / KlighdConstants.ALPHA_FULL_OPAQUE));
        final int alpha2 = (int) (gradient.getAlpha2() * (curAlpha / KlighdConstants.ALPHA_FULL_OPAQUE));

        this.setPattern(new Pattern(this.getGraphicsContext().getDevice(), (float) points[0]
                .getX(), (float) points[0].getY(), (float) points[1].getX(), (float) points[1]
                .getY(), this.getColor(gradient.getColor1()), alpha1, this.getColor(gradient
                .getColor2()), alpha2));
    }

    /**
     * {@inheritDoc}
     */
    public void setBackgroundPattern(final RGBGradient gradient, final Rectangle2D bounds) {
        TEMP_RECT.setRect(bounds);
        SWTShapeManager.transform(TEMP_RECT, this.getTransform());

        Point2D[] points = computePatternPoints(TEMP_RECT, Math.toRadians(gradient.getAngle()));

        final float curAlpha = (float) this.getAlpha();
        final int alpha1 = (int) (gradient.getAlpha1() * (curAlpha / KlighdConstants.ALPHA_FULL_OPAQUE));
        final int alpha2 = (int) (gradient.getAlpha2() * (curAlpha / KlighdConstants.ALPHA_FULL_OPAQUE));

        this.setBackgoundPattern(new Pattern(this.getGraphicsContext().getDevice(),
                (float) points[0].getX(), (float) points[0].getY(), (float) points[1].getX(),
                (float) points[1].getY(), this.getColor(gradient.getColor1()), alpha1, this
                        .getColor(gradient.getColor2()), alpha2));
    }
    
    /*-----------------------------*/
    /* overrides of legacy methods */
    /*-----------------------------*/
    
    /**
     * {@inheritDoc}
     * @deprecated see {@link KlighdSWTGraphics#draw(Shape)}.
     */
    public void draw(final Shape shape) {
        final String msg = "KLighD: Invocation of KlighdSWTGraphics#draw(Shape) is not supported as"
                + " the position and size data cannot be scaled properly for general shapes.";
        throw new UnsupportedOperationException(msg); 
    }
    
    /**
     * {@inheritDoc}
     * @deprecated see {@link KlighdSWTGraphics#fill(Shape)}.
     */
    public void fill(final Shape shape) {
        final String msg = "KLighD: Invocation of KlighdSWTGraphics#fill(Shape) is not supported as"
                + " the position and size data cannot be scaled properly for general shapes.";
        throw new UnsupportedOperationException(msg); 
    }
    
    /*-------------------------*/
    /* internal helper methods */
    /*-------------------------*/

    private Point2D[] computePatternPoints(final Rectangle2D bounds, final double angle) {

        PAffineTransform t = new PAffineTransform();
        t.rotate(-angle, bounds.getCenterX(), bounds.getCenterY());
        t.transform(bounds, bounds);

        Rectangle2D.Float incBounds = (Rectangle2D.Float) bounds.getBounds2D();
        Point2D p1 = new Point2D.Double(incBounds.getMinX(), incBounds.getCenterY());
        Point2D p2 = new Point2D.Double(incBounds.getMaxX(), incBounds.getCenterY());

        t.inverseTransform(p1, p1);
        t.inverseTransform(p2, p2);

        return new Point2D[] { p1, p2 };
    }
}
