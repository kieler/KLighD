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
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Pattern;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.util.PolylineUtil;

/**
 * A Draw2D graphics object bridging to a {@link de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics
 * KlighdSWTGraphics} graphics.
 * 
 * @author msp, chsch
 */
public class GraphicsAdapter extends Graphics {
    
    /** The state data class. */
    private static class State {
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
        State(final KlighdSWTGraphicsImpl g) {
            this.clip = g.getClip();
            this.transform = g.getTransform();
            this.alpha = g.getGraphicsContext().getAlpha();
            this.foreground = g.getGraphicsContext().getForeground();
            this.background = g.getGraphicsContext().getBackground();
            this.foregroundPattern = g.getGraphicsContext().getForegroundPattern();
            this.backgroundPattern = g.getGraphicsContext().getBackgroundPattern();
            this.font = g.getSWTFont();
            this.lineAttributes = g.getLineAttributes();
        }
    }
    
    private static final GeneralPath SINGLETON_PATH = new GeneralPath();
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
    private KlighdSWTGraphicsImpl pg;
    
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
    void setKlighdSWTGraphics(final KlighdSWTGraphicsImpl thePg) {
        this.pg = thePg;
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
        pg.drawImage(srcImage, x1, y1, w1, h1, x2, y2, w2, h2);
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
    @SuppressWarnings("deprecation")
    @Override
    public void drawPath(final Path path) {
        pg.drawPath(path);
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
        pg.drawString(s, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawText(final String s, final int x, final int y) {
        pg.drawText(s, x, y);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawText(final String s, final int x, final int y, final int style) {
        pg.drawText(s, x, y, style);
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
        pg.fillGradientRectangle(x, y, w, h, vertical);
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
    @SuppressWarnings("deprecation")
    @Override
    public void fillPath(final Path path) {
        pg.fillPath(path);
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
        org.eclipse.swt.graphics.Point extent = pg.stringExtent(s);
        pg.fillRect(x, y, extent.x, extent.y);
        pg.drawString(s, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillText(final String s, final int x, final int y) {
        org.eclipse.swt.graphics.Point extent = pg.textExtent(s);
        pg.fillRect(x, y, extent.x, extent.y);
        pg.drawText(s, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.draw2d.geometry.Rectangle getClip(
            final org.eclipse.draw2d.geometry.Rectangle rect) {
        return rect.setBounds(toRectangle(pg.getClip()));
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
        return pg.getSWTFont();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontMetrics getFontMetrics() {
        return pg.getSWTFontMetrics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.swt.graphics.Color getForegroundColor() {
        return pg.getGraphicsContext().getForeground();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundColor(final org.eclipse.swt.graphics.Color rgb) {
        pg.setColor(rgb);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundPattern(final Pattern pattern) {
        pg.setPattern(pattern);
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.swt.graphics.Color getBackgroundColor() {
        return pg.getGraphicsContext().getBackground();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundColor(final org.eclipse.swt.graphics.Color rgb) {
        pg.setBackground(rgb);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundPattern(final Pattern pattern) {
        pg.setBackgoundPattern(pattern);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotate(final float degrees) {
        pg.rotate(degrees);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scale(final double amount) {
        pg.scale(amount, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClip(final org.eclipse.draw2d.geometry.Rectangle r) {
        pg.setClip(toShape(r));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clipRect(final org.eclipse.draw2d.geometry.Rectangle r) {
        setClip(r);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFont(final Font f) {
        pg.setFont(f);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineAttributes(final LineAttributes attributes) {
        pg.setLineAttributes(attributes);
        pg.updateClip();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidth(final int width) {
        pg.setLineWidth(width);
        pg.updateClip();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidthFloat(final float width) {
        pg.setLineWidth(width);
        pg.updateClip();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void translate(final int dx, final int dy) {
        pg.translate(dx, dy);
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
        return pg.getGraphicsContext().getAntialias();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAntialias(final int value) {
        pg.getGraphicsContext().setAntialias(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getXORMode() {
        return pg.getGraphicsContext().getXORMode();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("deprecation")
    @Override
    public void setXORMode(final boolean b) {
        pg.getGraphicsContext().setXORMode(b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineStyle() {
        return pg.getGraphicsContext().getLineStyle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineStyle(final int style) {
        pg.getGraphicsContext().setLineStyle(style);
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
        return pg.getGraphicsContext().getLineCap();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineCap(final int cap) {
        pg.getGraphicsContext().setLineCap(cap);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineDash(final int[] dash) {
        pg.getGraphicsContext().setLineDash(dash);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineDash(final float[] value) {
        pg.getGraphicsContext().getGCData().lineDashes = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineDashOffset(final float value) {
        pg.getGraphicsContext().getGCData().lineDashesOffset = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineJoin() {
        return pg.getGraphicsContext().getLineJoin();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineJoin(final int join) {
        pg.getGraphicsContext().setLineJoin(join);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getLineMiterLimit() {
        return pg.getGraphicsContext().getGCData().lineMiterLimit;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineMiterLimit(final float miterLimit) {
        pg.getGraphicsContext().getGCData().lineMiterLimit = miterLimit;
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
        State lastState = stack.peek();
        if (lastState != null) {
            pg.setClip(lastState.clip);
            pg.setTransform(lastState.transform);
            pg.setAlpha(lastState.alpha);
            pg.setColor(lastState.foreground);
            pg.setPattern(lastState.foregroundPattern);
            pg.setBackgoundPattern(lastState.backgroundPattern);
            pg.setBackground(lastState.background);
            pg.setFont(lastState.font);
            pg.setLineAttributes(lastState.lineAttributes);
        }
    }
}
