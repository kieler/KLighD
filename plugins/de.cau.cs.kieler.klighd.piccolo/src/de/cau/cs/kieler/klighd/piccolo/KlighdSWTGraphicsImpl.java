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
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextLayout;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.util.RGBGradient;
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

    /**
     * {@inheritDoc}
     */
    public Device getDevice() {
        return this.device;
    }

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
    public void setColor(final RGB foregroundColor) {
        super.setColor(foregroundColor);
    }

    /**
     * {@inheritDoc}
     */
    public LineAttributes getLineAttributes() {
        return gc.getLineAttributes();
    }
    
    /**
     * {@inheritDoc}
     */
    public void setLineAttributes(final LineAttributes attributes) {
        gc.setLineAttributes(attributes);
        // the following line keeps the lineWidth in mind in order to
        //  be able to adjust it according to the zoom factor
        this.setLineWidth(attributes.width);
    }

    /**
     * {@inheritDoc}
     */
    public float getLineWidth() {
        return super.getLineWidth();
    }
    
    private Pattern lastPattern = null;

    /**
     * {@inheritDoc}
     */
    public void setPattern(final RGBGradient gradient, final Rectangle2D bounds) {
        final Point2D[] points = computePatternPoints(bounds, Math.toRadians(gradient.getAngle()));

        final float curAlpha = (float) this.getAlpha();
        final int alpha1 = (int) (gradient.getAlpha1() * (curAlpha / KlighdConstants.ALPHA_FULL_OPAQUE));
        final int alpha2 = (int) (gradient.getAlpha2() * (curAlpha / KlighdConstants.ALPHA_FULL_OPAQUE));

        if (this.lastPattern != null) {
            this.lastPattern.dispose();
        }

        this.lastPattern = new Pattern(this.getGraphicsContext().getDevice(),
                (float) points[0].getX(), (float) points[0].getY(), (float) points[1].getX(),
                (float) points[1].getY(), this.getColor(gradient.getColor1()), alpha1,
                this.getColor(gradient.getColor2()), alpha2);
        this.setPattern(this.lastPattern);
    }

    /**
     * {@inheritDoc}
     */
    public void setBackgroundPattern(final RGBGradient gradient, final Rectangle2D bounds) {
        final Point2D[] points = computePatternPoints(bounds, Math.toRadians(gradient.getAngle()));

        final float curAlpha = (float) this.getAlpha();
        final int alpha1 = (int) (gradient.getAlpha1() * (curAlpha / KlighdConstants.ALPHA_FULL_OPAQUE));
        final int alpha2 = (int) (gradient.getAlpha2() * (curAlpha / KlighdConstants.ALPHA_FULL_OPAQUE));

        if (this.lastPattern != null) {
            this.lastPattern.dispose();
        }
        
        this.lastPattern = new Pattern(this.getGraphicsContext().getDevice(),
                (float) points[0].getX(), (float) points[0].getY(), (float) points[1].getX(),
                (float) points[1].getY(), this.getColor(gradient.getColor1()), alpha1,
                this.getColor(gradient.getColor2()), alpha2);
        this.setBackgoundPattern(this.lastPattern);
    }


    /*-----------------*/
    /* drawing methods */
    /*-----------------*/

    /**
     * {@inheritDoc}
     */
    public void draw(final Shape shape) {
        int alpha = getAlpha();
        antiAliase();

        final Path path = pathIterator2Path(shape.getPathIterator(null));

        gc.setTransform(swtTransform);
        gc.drawPath(path);

        path.dispose();

        setAlpha(alpha);
    }
    
    /**
     * {@inheritDoc}
     */
    public void draw(final Path path) {
        gc.setTransform(swtTransform);
        gc.drawPath(path);
    }
    
    /**
     * {@inheritDoc}
     */
    public void fill(final Shape shape) {
        final Path path = pathIterator2Path(shape.getPathIterator(null));

        gc.setTransform(swtTransform);
        gc.fillPath(path);
        
        path.dispose();
    }

    /**
     * {@inheritDoc}
     */
    public void fill(final Path path) {
        gc.setTransform(swtTransform);
        gc.fillPath(path);
    }
    

    /**
     * {@inheritDoc}
     */
    public void drawImage(final org.eclipse.swt.graphics.Image image, final double width,
            final double height) {
        final org.eclipse.swt.graphics.Rectangle bounds = image.getBounds();
        gc.setTransform(null);
        drawImage(image, 0, 0, bounds.width, bounds.height, 0, 0, width, height);
    }

    /**
     * {@inheritDoc}
     */
    public void drawText(final String text) {
        drawText(text, 0, 0, SWT.DRAW_DELIMITER | SWT.DRAW_TRANSPARENT);
    }

    /*-------------------------*/
    /* internal helper methods */
    /*-------------------------*/

    /**
     * This method realizes a poor man anti aliasing if the adjusted line width drawn on the screen
     * is less than two.
     */
    protected void antiAliase() {
        final int alpha = getAlpha();
        final float lineWidth = this.getTransformedLineWidthFloat();
        final int factor = 100;
        
        if (lineWidth < 2) {
            double adjustedAlpha = 
                    alpha * (KlighdConstants.ALPHA_FULL_OPAQUE - (2 - lineWidth) * factor)
                            / KlighdConstants.ALPHA_FULL_OPAQUE;
            this.setAlpha((int) adjustedAlpha);
        }
    }
    
    /** A rectangle object used to adjust custom line dash configurations wrt. the current transform. */
    private static final Rectangle2D.Float TEMP_DASH_RECT = new Rectangle2D.Float(); 

    /**
     * A helper function that adjusts custom dash patterns and dash offset according the given transform,
     * i.e. the zoom factor
     * 
     */
    @SuppressWarnings("unused")
    private void updateCustomLineStyle() {
        if (this.gc.getGCData().lineStyle == SWT.LINE_CUSTOM) {
            
            // adjust the pattern
            float[] dashPattern = this.gc.getGCData().lineDashes;
            for (int i = 0; i < dashPattern.length; i++) {
                TEMP_DASH_RECT.setRect(0, 0, dashPattern[i], dashPattern[i]);
                SWTShapeManager.transform(TEMP_DASH_RECT, transform);
                dashPattern[i] = TEMP_DASH_RECT.width;
            }
            
            // adjust the offset
            float dashOffset = this.gc.getGCData().lineDashesOffset;
            TEMP_DASH_RECT.setRect(0, 0, dashOffset, dashOffset);
            SWTShapeManager.transform(TEMP_DASH_RECT, transform);
            this.gc.getGCData().lineDashesOffset = TEMP_DASH_RECT.width;
        }
    }
    
    // the following field with singleton values
    //  that serve the purpose of avoiding unnecessary object creation and dismiss 
    private final PAffineTransform rotation = new PAffineTransform();
    private final Rectangle2D transformedBounds = new Rectangle2D.Double();
    private final Point2D[] patternPoints = new Point2D[] {
            new Point2D.Double(), new Point2D.Double()
    };

    /**
     * Compute the pattern points required by
     * {@link Pattern#Pattern(Device, float, float, float, float, Color, int, Color, int)}.
     * 
     * @param bounds
     *            the bounds of the shape to be drawn with a gradient
     * @param angle
     *            the gradients angle
     * @return the {@link #patternPoints} array providing the desired gradient points
     */
    private Point2D[] computePatternPoints(final Rectangle2D bounds, final double angle) {

        if (angle != 0d) {
            // set 'rotation' to rotate its input counterclockwise around the center of 'bounds'
            this.rotation.rotate(-angle, bounds.getCenterX(), bounds.getCenterY());
            
            // create a copy of 'bounds' and apply 't' to that copy, i.e. rotate the copy
            //  counterclockwise according to 'angle';
            // note that transforming a rectangle will lead to a new rectangle resembling
            //  the bounding box of the imaginary rotated original one;
            // there will be no Path2D created containing the rotated corner points!
            this.rotation.transform(bounds, this.transformedBounds);
        } else {
            this.transformedBounds.setRect(bounds);
        }
        
        // now determine two points forming a horizontal line through the center of 'bounds'
        //  (which is equal to the center of 'transformedBounds' as we rotated around the center) 
        //  from 'transformedBounds''s left most 'x' value to its right most one
        this.patternPoints[0].setLocation(this.transformedBounds.getMinX(), bounds.getCenterY());
        this.patternPoints[1].setLocation(this.transformedBounds.getMaxX(), bounds.getCenterY());

        // the gradient will be realized along that imaginary line,
        //  lines of points of equal color will run orthogonally to that line
        if (!this.rotation.isIdentity()) {
            // in order to let the imaginary line respect our desired angle,
            //  simply rotate the points back :-)
            this.rotation.inverseTransform(this.patternPoints[0], this.patternPoints[0]);
            this.rotation.inverseTransform(this.patternPoints[1], this.patternPoints[1]);
            this.rotation.setToIdentity();
        }
        
        return this.patternPoints;
    }
}
