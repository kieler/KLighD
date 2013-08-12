/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.draw2d;

import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.internal.util.PolylineUtil;

/**
 * A Draw2D graphics object bridging to a {@link de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics
 * KlighdSWTGraphics} graphics.
 * 
 * @author msp, chsch
 */
public class GraphicsAdapter extends Graphics {
    
    /** The state data class. */
    private class State {
        private Shape clip;
        private AffineTransform transform;
        private int alpha;
        private org.eclipse.swt.graphics.Color foreground;
        private org.eclipse.swt.graphics.Color background;
        private Pattern foregroundPattern;
        private Pattern backgroundPattern;
        private Font font;
        private LineAttributes lineAttributes;
        
        /**
         * Stores the state data of the given graphics into a new state.
         * 
         * @param g an SWT graphics wrapper
         */
        State(final KlighdSWTGraphics g) {
            this.clip = g.getClip();
            this.transform = g.getTransform();
            this.alpha = g.getAlpha();
            this.foreground = g.getGC().getForeground();
            this.background = g.getGC().getBackground();
            this.foregroundPattern = g.getGC().getForegroundPattern();
            this.backgroundPattern = g.getGC().getBackgroundPattern();
            this.font = g.getGC().getFont();
            this.lineAttributes = g.getLineAttributes();
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

    /**
     * Transform the given Draw2D rectangle to an AWT rectangle.
     * 
     * @param r a Draw2D rectangle
     * @return an AWT rectangle
     */
    public static Shape toShape(final org.eclipse.draw2d.geometry.Rectangle r) {
        return new java.awt.Rectangle(r.x, r.y, r.width, r.height);
    }
    
    /**
     * Transform the given AWT shape to a Draw2D rectangle.
     * 
     * @param s an AWT shape
     * @return a Draw2D rectangle
     */
    public static org.eclipse.draw2d.geometry.Rectangle toRectangle(final Shape s) {
        java.awt.Rectangle bounds = s.getBounds();
        return new org.eclipse.draw2d.geometry.Rectangle(bounds.x, bounds.y,
                bounds.width, bounds.height);
    }
    
    /**
     * Transform the given SWT color into an AWT color.
     * 
     * @param color an SWT color
     * @return an AWT color
     */
    public static java.awt.Color toAWTColor(final org.eclipse.swt.graphics.Color color) {
        return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    /**
     * Transform the given SWT line attributes into an AWT stroke.
     * 
     * @param la line attributes
     * @return an AWT stroke
     */
    public static Stroke toStroke(final LineAttributes la) {
        return new BasicStroke(la.width, la.cap, la.join, la.miterLimit, la.dash, la.dashOffset);
    }
    
    /**
     * The specialized AWT {@link java.awt.Graphics2D Graphics2D} graphics facading SWT's
     * {@link org.eclipse.swt.graphics.GC GC}.
     */
    private KlighdSWTGraphics pg;
    
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
    void setKlighdSWTGraphics(final KlighdSWTGraphics thePg) {
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
        pg.drawImage(srcImage, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawImage(final Image srcImage, final int x1, final int y1, final int w1, final int h1,
            final int x2, final int y2, final int w2, final int h2) {
        // SUPPRESS CHECKSTYLE PREVIOUS 2 Parameter: This is API!
        throw new UnsupportedOperationException();
//        pg.drawImage(srcImage, x1, y1, w1, h1, x2, y2, w2, h2);
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
        throw new UnsupportedOperationException();
//        pg.fillGradientRectangle(x, y, w, h, vertical);
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
        throw new UnsupportedOperationException();
//        org.eclipse.swt.graphics.Point extent = pg.stringExtent(s);
//        pg.fillRect(x, y, extent.x, extent.y);
//        pg.drawString(s, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillText(final String s, final int x, final int y) {
        throw new UnsupportedOperationException();
//        org.eclipse.swt.graphics.Point extent = pg.textExtent(s);
//        pg.fillRect(x, y, extent.x, extent.y);
//        pg.drawText(s, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.draw2d.geometry.Rectangle getClip(
            final org.eclipse.draw2d.geometry.Rectangle rect) {
        Rectangle clip = pg.getClip().getBounds();
        rect.setBounds(clip.x, clip.y, clip.width, clip.height);
        return rect; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineWidth() {
        return (int) pg.getLineWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getLineWidthFloat() {
        return (float) pg.getLineWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font getFont() {
        return pg.getGC().getFont();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontMetrics getFontMetrics() {
        return pg.getGC().getFontMetrics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.swt.graphics.Color getForegroundColor() {
        return pg.getGC().getForeground();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundColor(final org.eclipse.swt.graphics.Color rgb) {
        pg.getGC().setForeground(rgb);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundPattern(final Pattern pattern) {
        pg.getGC().setForegroundPattern(pattern);
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.swt.graphics.Color getBackgroundColor() {
        return pg.getGC().getBackground();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundColor(final org.eclipse.swt.graphics.Color rgb) {
        pg.getGC().setBackground(rgb);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundPattern(final Pattern pattern) {
        pg.getGC().setBackgroundPattern(pattern);
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
        pg.setClip(new Rectangle(r.x, r.y, r.width, r.height));
    }
    
    /**
     * {@inheritDoc}
     */
    public void clipRect(final org.eclipse.draw2d.geometry.Rectangle r) {
        pg.clip(new Rectangle(r.x, r.y, r.width, r.height));
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
        updateClip();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidth(final int width) {
        pg.setLineWidth(width);
        updateClip();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidthFloat(final float width) {
        pg.setLineWidth(width);
        updateClip();
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
        // TODO not yet implemented
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAdvanced(final boolean advanced) {
        // TODO not yet implemented
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
        return pg.getGC().getAntialias();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAntialias(final int value) {
        pg.getGC().setAntialias(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getXORMode() {
        return pg.getGC().getXORMode();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("deprecation")
    @Override
    public void setXORMode(final boolean b) {
        pg.getGC().setXORMode(b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineStyle() {
        return pg.getGC().getLineStyle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineStyle(final int style) {
        pg.getGC().setLineStyle(style);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LineAttributes getLineAttributes() {
       return pg.getGC().getLineAttributes(); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineCap() {
        return pg.getGC().getLineCap();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineCap(final int cap) {
        pg.getGC().setLineCap(cap);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineDash(final int[] dash) {
        pg.getGC().setLineDash(dash);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineDash(final float[] value) {
        pg.getGC().getGCData().lineDashes = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineDashOffset(final float value) {
        pg.getGC().getGCData().lineDashesOffset = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineJoin() {
        return pg.getGC().getLineJoin();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineJoin(final int join) {
        pg.getGC().setLineJoin(join);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getLineMiterLimit() {
        return pg.getGC().getGCData().lineMiterLimit;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineMiterLimit(final float miterLimit) {
        pg.getGC().getGCData().lineMiterLimit = miterLimit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pushState() {
        stack.push(new State(pg));
//        Rectangle2D clip =
//                stack.peek().transform.createTransformedShape(stack.peek().clip).getBounds2D();
//        System.out.println("Push :" + clip);
//        String s = Joiner.on("\n  ").join(
//                Lists.transform(Lists.newArrayList(stack), new Function<State, Shape>() {
//                    public Shape apply(final State s) {
//                        return s.clip;
//                    }
//                }));
//        System.out.println(s);
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
            pg.setClip(lastState.clip.getBounds2D());
            pg.setAlpha(lastState.alpha);
            pg.getGC().setForeground(lastState.foreground);
            pg.getGC().setForegroundPattern(lastState.foregroundPattern);
            pg.getGC().setBackgroundPattern(lastState.backgroundPattern);
            pg.getGC().setBackground(lastState.background);
            pg.getGC().setFont(lastState.font);
            pg.getGC().setLineAttributes(lastState.lineAttributes);
        }
    }

    /**
     * This method widens the currently set clipping mask. It supposed to be called after changing
     * the line width, if necessary.<br>
     * <br>
     * Rational: At least on OSX GC#setClipping(...) appears to treat the pixel line forming the
     * bounding box multiplied with the currently set line width that has been corrected by the zoom
     * factor as part of the clipped area. Hence, for some non-empty rectangle 'someRect' the
     * following code
     * 
     * <pre>
     * gc.drawRect(someRect);
     * gc.setClipping(someRect);
     * gc.drawRect(someRect);
     * </pre>
     * 
     * results in two differently sized rectangles on the screen with the second one not covering
     * the first one completely (which I however expected). Thus the method takes the last
     * configured clipping area (which will also be kept in mind in the @code{setClip(...)} &&
     * @code{clip} methods) and increases that bounding box by a surrounding line of the
     * (zoom factor correct) line width.
     * 
     * @author chsch
     */
    public void updateClip() {
        Rectangle2D clip = pg.getClip().getBounds2D();
        final float lineWidth = pg.getLineWidth();
        final float hLineWidth = lineWidth / 2;
        clip.setRect(clip.getX() - hLineWidth, clip.getY() - hLineWidth,
                     clip.getWidth() + lineWidth, clip.getHeight() + lineWidth);
        pg.setClip(clip);
    }
    
    @SuppressWarnings("unused")
    private void drawClip(final org.eclipse.swt.graphics.Rectangle clip) {
        final GC gc = pg.getGC();
        final Color c = gc.getForeground();
        gc.setClipping((org.eclipse.swt.graphics.Rectangle) null);
        gc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_BLUE));
        gc.drawRectangle(clip);
        gc.setForeground(c);
    }
}
