/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.draw2d;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.internal.util.PolylineUtil;

/**
 * A Draw2d {@link Graphics} bridging to a {@link de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics
 * KlighdSWTGraphics}. Although most of the required methods are implemented, some of them are not
 * supported. This is mainly due to the fact that the underlying
 * {@link de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics KlighdSWTGraphics} might by a non-SWT
 * one, e.g. one realizing an SVG export. Thus, methods like {@link Graphics#getForegroundColor()}
 * can't be realized safely.<br>
 * <br>
 * Wrt. drawing on an SWT-based graphics implementation the clipping appears to be a costly
 * operation. In case of performance bottle necks the implementations of
 * {@link #clipRect(org.eclipse.draw2d.geometry.Rectangle) #clipRect(Rectangle)} and
 * {@link #setClip(org.eclipse.draw2d.geometry.Rectangle)  #setClip(Rectangle)} could be deactivated.<br>
 * <br>
 * ... which is now done since the clipping simply seems to be not necessary. (chsch)
 * 
 * @author msp
 * @author chsch
 */
public class GraphicsAdapter extends Graphics {
    
    /** The state data class. */
    private class State {
        // private Shape clip;
        private AffineTransform transform;
        private int alpha;
        private RGB foreground;
        private RGB background;
        private Pattern foregroundPattern;
        private Pattern backgroundPattern;
        private FontData font;
        private LineAttributes lineAttributes;
        
        /**
         * Stores the state data of the given graphics into a new state.
         * 
         * @param g an SWT graphics wrapper
         */
        State(final KlighdSWTGraphicsEx g) {
            // this.clip = g.getClip();
            this.transform = g.getTransform();
            this.alpha = g.getAlpha();
            this.font = g.getFontData();
            this.lineAttributes = g.getLineAttributes();
            this.foreground = g.getStrokeColor();
            this.background = g.getFillColor();
            
            final GC gc = g.getGC();
            if (gc != null) {
                this.foregroundPattern = gc.getForegroundPattern();
                this.backgroundPattern = gc.getBackgroundPattern();
            } else { // SUPPRESS CHECKSTYLE Empty
                // leave 'null' value in pattern fields
            }
        }
    }
    
    private AffineTransform awtTransform = new AffineTransform();
    
    private static final Path2D.Float SINGLETON_PATH = new Path2D.Float();
    private static final Line2D.Float SINGLETON_LINE = new Line2D.Float();
    private static final Arc2D.Float SINGLETON_ARC = new Arc2D.Float(); 
    private static final Ellipse2D.Float SINGLETON_ELLIPSE = new Ellipse2D.Float(); 
    private static final Rectangle2D.Float SINGLETON_RECTANGLE = new Rectangle2D.Float(); 
    private static final RoundRectangle2D.Float SINGLETON_ROUND_RECTANGLE
                                                            = new RoundRectangle2D.Float(); 

    private static final String UNSUPPORTED_OPERATION_MSG_ESCAPE = "<<method>>";
    private static final String UNSUPPORTED_OPERATION_MSG = "KLighD Draw2d wrapper: Method "
            + GraphicsAdapter.class.getSimpleName() + UNSUPPORTED_OPERATION_MSG_ESCAPE
            + " required by one of the employed diagram figures to be displayed is currently"
            + "not implemented. Please implement or file a bug report to the development team.";
    
    /**
     * The specialized AWT {@link java.awt.Graphics2D Graphics2D} graphics facading SWT's
     * {@link org.eclipse.swt.graphics.GC GC}.
     */
    private KlighdSWTGraphicsEx pg;
    
    /** The stack of graphics states. */
    private LinkedList<State> stack = new LinkedList<State>();
    
    
    /**
     * Creates a Draw2D graphics adapter. An {@link edu.umd.cs.piccolox.swt.SWTGraphics2D
     * SWTGraphics2D} must be provided by means of a setter!
     * 
     * @author chsch
     */
    public GraphicsAdapter() {
    }

    /**
     * Creates a Draw2D graphics adapter.
     * 
     * @param graphics
     *            a specialized AWT {@link java.awt.Graphics2D Graphics2D} graphics facading SWT's
     *            {@link org.eclipse.swt.graphics.GC GC}
     */
    public GraphicsAdapter(final KlighdSWTGraphicsImpl graphics) {
        this.pg = graphics;
    }
    
    /**
     * Setter of the 'SWTGraphics2D' to be used. Must be invoked in combination with using the
     * default constructor. Is flagged 'package protected' (no modifier) by intention.
     * 
     * @author chsch
     * 
     * @param thePg
     *            a specialized AWT {@link java.awt.Graphics2D Graphics2D} graphics facading SWT's
     *            {@link org.eclipse.swt.graphics.GC GC}
     */
    void setKlighdSWTGraphics(final KlighdSWTGraphicsEx thePg) {
        this.pg = thePg;
        this.stack.clear();
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        stack.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pushState() {
        stack.push(new State(pg));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void popState() {
        if (!stack.isEmpty()) {
            restoreState();
            stack.pop();
        }
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void restoreState() {
        final State lastState = stack.peek();
        if (lastState != null) {
            pg.setTransform(lastState.transform);
            // pg.setClip(lastState.clip);
            pg.setAlpha(lastState.alpha);
            pg.setFont(lastState.font);
            pg.setLineAttributes(lastState.lineAttributes);
            pg.setStrokeColor(lastState.foreground);
            pg.setFillColor(lastState.background);
            final GC gc = pg.getGC();
            if (gc != null) {
                if (lastState.foregroundPattern != null) {
                    gc.setForegroundPattern(lastState.foregroundPattern);
                }
                if (lastState.backgroundPattern != null) {
                    gc.setBackgroundPattern(lastState.backgroundPattern);
                }
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawArc(final int x, final int y, final int w, final int h, final int offset,
            final int length) {
        SINGLETON_ARC.setArc(x, y, w, h, offset, length, Arc2D.OPEN);
        pg.draw(SINGLETON_ARC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawImage(final Image srcImage, final int x, final int y) {
        // since our KlighdSWTGraphics interface requires x/y offsets
        //  to be performed by means of KlighdSWTGraphics#translate(...)
        //  create a corresponding AffineTransform and do it this way
        final AffineTransform transform = new AffineTransform();
        transform.translate(x, y);
        pg.transform(transform);

        final org.eclipse.swt.graphics.Rectangle bounds = srcImage.getBounds();
        pg.drawImage(srcImage, bounds.width, bounds.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawImage(final Image srcImage, final int x1, final int y1, final int w1, final int h1,
            final int x2, final int y2, final int w2, final int h2) {
        // SUPPRESS CHECKSTYLE PREVIOUS 2 Parameter: This is API!
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MSG.replace(
                UNSUPPORTED_OPERATION_MSG_ESCAPE,
                "\'#drawImage(final Image srcImage, final int x1, final int y1, final int w1, "
                + "final int h1, final int x2, final int y2, final int w2, final int h2)\'"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawLine(final int x1, final int y1, final int x2, final int y2) {
        SINGLETON_LINE.setLine(x1, y1, x2, y2);
        pg.draw(SINGLETON_LINE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOval(final int x, final int y, final int w, final int h) {
        SINGLETON_ELLIPSE.setFrame(x, y, w, h);
        pg.draw(SINGLETON_ELLIPSE);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPath(final Path path) {
        pg.draw(path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPolygon(final PointList points) {
        pg.draw(PolylineUtil.createPolygonPath(SINGLETON_PATH, points.toIntArray()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPolyline(final PointList points) {
        pg.draw(PolylineUtil.createPolylinePath(SINGLETON_PATH, points.toIntArray()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRectangle(final int x, final int y, final int width, final int height) {
        SINGLETON_RECTANGLE.setRect(x, y, width, height);
        pg.draw(SINGLETON_RECTANGLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRoundRectangle(final org.eclipse.draw2d.geometry.Rectangle r,
            final int arcWidth, final int arcHeight) {
        SINGLETON_ROUND_RECTANGLE.setRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
        pg.draw(SINGLETON_ROUND_RECTANGLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawString(final String s, final int x, final int y) {
        translate(x, y);
        pg.drawText(s);
        translate(-x, -y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawText(final String s, final int x, final int y) {
        translate(x, y);
        pg.drawText(s);
        translate(-x, -y);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawText(final String s, final int x, final int y, final int style) {
        translate(x, y);
        pg.drawText(s);
        translate(-x, -y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillArc(final int x, final int y, final int w, final int h, final int offset,
            final int length) {
        SINGLETON_ARC.setArc(x, y, w, h, offset, length, Arc2D.OPEN);
        pg.fill(SINGLETON_ARC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillGradient(final int x, final int y, final int w, final int h,
            final boolean vertical) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MSG.replace(
                UNSUPPORTED_OPERATION_MSG_ESCAPE, "\'#fillGradient(final int x,"
                        + "final int y, final int w, final int h, final boolean vertical)"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillOval(final int x, final int y, final int w, final int h) {
        SINGLETON_ELLIPSE.setFrame(x, y, w, h);
        pg.fill(SINGLETON_ELLIPSE);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void fillPath(final Path path) {
        pg.fill(path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillPolygon(final PointList points) {
        pg.fill(PolylineUtil.createPolygonPath(SINGLETON_PATH, points.toIntArray()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRectangle(final int x, final int y, final int width, final int height) {
        SINGLETON_RECTANGLE.setRect(x, y, width, height);
        pg.fill(SINGLETON_RECTANGLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRoundRectangle(final org.eclipse.draw2d.geometry.Rectangle r,
            final int arcWidth, final int arcHeight) {
        SINGLETON_ROUND_RECTANGLE.setRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
        pg.fill(SINGLETON_ROUND_RECTANGLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillString(final String s, final int x, final int y) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MSG.replace(
                UNSUPPORTED_OPERATION_MSG_ESCAPE,
                "\'#fillString(final String s, final int x, final int y)"));
        // org.eclipse.swt.graphics.Point extent = pg.stringExtent(s);
        // pg.fillRect(x, y, extent.x, extent.y);
        // pg.drawString(s, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillText(final String s, final int x, final int y) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MSG.replace(
                UNSUPPORTED_OPERATION_MSG_ESCAPE,
                "\'#fillText(final String s, final int x, final int y)"));
        // org.eclipse.swt.graphics.Point extent = pg.textExtent(s);
        // pg.fillRect(x, y, extent.x, extent.y);
        // pg.drawText(s, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.draw2d.geometry.Rectangle getClip(
            final org.eclipse.draw2d.geometry.Rectangle rect) {
        final Shape clip = pg.getClip();
        if (clip == null) {
            // since {@link Figure#paintChildren(Graphics)} requires reasonable bounds
            //  return the "maximal possible clip"
            rect.setBounds(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        } else {
            Rectangle bounds = clip.getBounds();
            rect.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        return rect; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineWidth() {
        return Math.round(this.getLineWidth());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getLineWidthFloat() {
        return pg.getLineAttributes().width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font getFont() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MSG.replace(
                UNSUPPORTED_OPERATION_MSG_ESCAPE, "\'#getFont()"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontMetrics getFontMetrics() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MSG.replace(
                UNSUPPORTED_OPERATION_MSG_ESCAPE, "\'#getFontMetrics()"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.swt.graphics.Color getForegroundColor() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MSG.replace(
                UNSUPPORTED_OPERATION_MSG_ESCAPE, "\'#getForegroundColor()"));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundColor(final org.eclipse.swt.graphics.Color rgb) {
        pg.setStrokeColor(rgb.getRGB());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundPattern(final Pattern pattern) {
        final GC gc = pg.getGC();
        if (gc != null) {
            gc.setForegroundPattern(pattern);
        }
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.swt.graphics.Color getBackgroundColor() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MSG.replace(
                UNSUPPORTED_OPERATION_MSG_ESCAPE, "\'#getBackgroundColor()"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundColor(final org.eclipse.swt.graphics.Color rgb) {
        pg.setFillColor(rgb.getRGB());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundPattern(final Pattern pattern) {
        final GC gc = pg.getGC();
        if (gc != null) {
            gc.setBackgroundPattern(pattern);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotate(final float degrees) {
        this.awtTransform.setToIdentity();
        this.awtTransform.rotate(degrees);
        pg.transform(this.awtTransform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scale(final double amount) {
        this.awtTransform.setToIdentity();
        this.awtTransform.scale(amount, amount);
        pg.transform(this.awtTransform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClip(final org.eclipse.draw2d.geometry.Rectangle r) {
        // pg.setClip(new Rectangle(r.x, r.y, r.width, r.height));
    }
    
    /**
     * {@inheritDoc}
     */
    public void clipRect(final org.eclipse.draw2d.geometry.Rectangle r) {
        // pg.clip(new Rectangle(r.x, r.y, r.width, r.height));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFont(final Font f) {
        pg.setFont(f.getFontData()[0]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineAttributes(final LineAttributes attributes) {
        pg.setLineAttributes(attributes);
        updateClip(attributes.width);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidth(final int width) {
        this.setLineWidthFloat((float) width);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidthFloat(final float width) {
        final LineAttributes la = pg.getLineAttributes();
        la.width = width;
        pg.setLineAttributes(la);
        updateClip(width);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void translate(final int dx, final int dy) {
        this.awtTransform.setToIdentity();
        this.awtTransform.translate(dx, dy);
        pg.transform(this.awtTransform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawFocus(final int x, final int y, final int w, final int h) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MSG.replace(
                UNSUPPORTED_OPERATION_MSG_ESCAPE,
                "\'#drawFocus(final int x, final int y, final int w, final int h)\'"));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAdvanced(final boolean advanced) {
        // we're always in advanced mode
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlpha(final int alpha) {
        pg.setAlpha(alpha);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getAntialias() {
        return SWT.ON;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAntialias(final int value) {
        // we're always in SWT.ON mode
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getXORMode() {
        // XORMode is deprecated, see GC#setXORMode(boolean).
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setXORMode(final boolean b) {
        // XORMode is deprecated, see GC#setXORMode(boolean).
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineStyle() {
        return pg.getLineAttributes().style;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineStyle(final int style) {
        LineAttributes la = pg.getLineAttributes();
        la.style = style;
        pg.setLineAttributes(la);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LineAttributes getLineAttributes() {
       return pg.getLineAttributes(); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineCap() {
        return pg.getLineAttributes().cap;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineCap(final int cap) {
        LineAttributes la = pg.getLineAttributes();
        la.cap = cap;
        pg.setLineAttributes(la);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineDash(final int[] dash) {
        float[] fdash = new float[dash.length];
        for (int i = 0; i < dash.length; i++) {
            fdash[i] = (float) dash[i];
        }

        LineAttributes la = pg.getLineAttributes();
        la.dash = fdash;
        pg.setLineAttributes(la);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineDash(final float[] dash) {
        LineAttributes la = pg.getLineAttributes();
        la.dash = dash;
        pg.setLineAttributes(la);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineDashOffset(final float dashOffset) {
        LineAttributes la = pg.getLineAttributes();
        la.dashOffset = dashOffset;
        pg.setLineAttributes(la);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineJoin() {
        return pg.getLineAttributes().join;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineJoin(final int join) {
        LineAttributes la = pg.getLineAttributes();
        la.join = join;
        pg.setLineAttributes(la);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getLineMiterLimit() {
        return pg.getLineAttributes().miterLimit;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineMiterLimit(final float miterLimit) {
        LineAttributes la = pg.getLineAttributes();
        la.miterLimit = miterLimit;
        pg.setLineAttributes(la);
    }

    /**
     * This method widens the currently set clipping mask. It supposed to be called after changing
     * the line width, if necessary.<br>
     * <br>
     * Rational: strokes of line path (e.g. rectangular shapes) with a given line width 'l' grow
     * equally to both sides of the imaginary line connecting two adjacent path points. This means
     * that in case of a rectangle half of the line width is placed within the rectangle, the other
     * half exceeds the bounds. Hence, if the clip area is set to fit the bounds half of the
     * surrounding stroke gets lost.<br>
     * <br>
     * For example:
     * 
     * <pre>
     * gc.drawRect(someRect);
     * gc.setClipping(someRect);
     * gc.drawRect(someRect);
     * </pre>
     * 
     * results in two differently sized rectangles on the screen with the second one not covering
     * the first one completely (which I however expected). Thus the method reveals the last
     * configured clipping area and increases that clip area by half of the line width on each side.<br>
     * Note: revealing and setting the clip incorporates the zoom factor already, so we don't need
     * to care on that here.
     * 
     * @author chsch
     * 
     * @param lineWidth
     *            the lineWidth to incorporate
     */
    public void updateClip(final float lineWidth) {
        // Rectangle2D clip = pg.getClip().getBounds2D();
        // final float hLineWidth = lineWidth / 2;
        // clip.setRect(clip.getX() - hLineWidth, clip.getY() - hLineWidth,
        //              clip.getWidth() + lineWidth, clip.getHeight() + lineWidth);
        // pg.setClip(clip);
    }
}
