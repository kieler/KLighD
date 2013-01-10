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
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Path;

import edu.umd.cs.piccolox.swt.SWTGraphics2D;

/**
 * A Draw2D graphics object that wraps a Piccolo graphics.
 *
 * @author msp, chsch
 */
public class GraphicsAdapter extends Graphics {
    
    /** The state data class. */
    private static class State {
        private Shape clip;
        private AffineTransform transform;
        private org.eclipse.swt.graphics.Color foreground;
        private org.eclipse.swt.graphics.Color background;
        private Font font;
        private double lineWidth;
        private int lineStyle;
        private int lineCap;
        
        /**
         * Stores the state data of the given graphics into a new state.
         * 
         * @param g an SWT graphics wrapper
         */
        State(final SWTGraphics2D g) {
            this.clip = g.getClip();
            this.transform = g.getTransform();
            this.foreground = g.getGraphicsContext().getForeground();
            this.background = g.getGraphicsContext().getBackground();
            this.font = g.getSWTFont();
            this.lineWidth = g.getLineWidth();
            this.lineStyle = g.getLineStyle();
            this.lineCap = g.getLineCap();
        }
    }
    
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
     * Transform the given Draw2D point list to an AWT polygon.
     * 
     * @param pl a Draw2D point list
     * @return an AWT polygon
     */
    public static Polygon toPolygon(final PointList pl) {
        Polygon result = new Polygon();
        for (int i = 0; i < pl.size(); i++) {
            org.eclipse.draw2d.geometry.Point p = pl.getPoint(i);
            result.addPoint(p.x, p.y);
        }
        return result;
    }
    
    /**
     * Transform the given Draw2D point list into an array of coordinates.
     * 
     * @param pl a Draw2D point list
     * @param lineWidth the line width to be used
     * @return a double precision array
     */
    public static double[] toArray(final PointList pl, final double lineWidth) {
        int[] points = pl.toIntArray();
        double[] result = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            result[i] = points[i];
        }
        return result;
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
    

    /** the Piccolo wrapper for SWT graphics. */
    private SWTGraphics2D pg;
    
    /** the stack of graphics states. */
    private LinkedList<State> stack = new LinkedList<State>();
    
    
    /**
     * Creates a Draw2D graphics adapter.
     * An {@link SWTGraphics2D} must be provided by means of a setter!
     * 
     * @author chsch
     */
    public GraphicsAdapter() {
    }

    /**
     * Creates a Draw2D graphics adapter.
     * 
     * @param graphics the Piccolo wrapper for SWT graphics
     */
    public GraphicsAdapter(final SWTGraphics2D graphics) {
        this.pg = graphics;
    }
    
    /**
     * Setter of the 'SWTGraphics2D' to be used.
     * Must be invoked in combination with using the default constructor.
     * Is flagged 'package protected' (no modifier) by intention.
     * 
     * @author chsch
     * 
     * @param thePg the pg to set
     */
    void setSWTGraphics2D(final SWTGraphics2D thePg) {
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
        pg.drawArc(x, y, w, h, offset, length);
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
        pg.drawLine(x1, y1, x2, y2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOval(final int x, final int y, final int w, final int h) {
        pg.drawOval(x, y, w, h);
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
        pg.drawPolygon(toArray(points, pg.getLineWidth()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPolyline(final PointList points) {
        pg.drawPolyline(toArray(points, pg.getLineWidth()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRectangle(final int x, final int y, final int width, final int height) {
        pg.drawRect(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRoundRectangle(final org.eclipse.draw2d.geometry.Rectangle r,
            final int arcWidth, final int arcHeight) {
        pg.drawRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
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
        pg.fillArc(x, y, w, h, offset, length);
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
        pg.fillOval(x, y, w, h);
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
        pg.fillPolygon(toArray(points, pg.getLineWidth()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRectangle(final int x, final int y, final int width, final int height) {
        pg.fillRect(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRoundRectangle(final org.eclipse.draw2d.geometry.Rectangle r,
            final int arcWidth, final int arcHeight) {
        pg.fillRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
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
    public org.eclipse.swt.graphics.Color getBackgroundColor() {
        return pg.getGraphicsContext().getBackground();
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
    public void setBackgroundColor(final org.eclipse.swt.graphics.Color rgb) {
        pg.setBackground(rgb);
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
    public void setForegroundColor(final org.eclipse.swt.graphics.Color rgb) {
        pg.setColor(rgb);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineAttributes(final LineAttributes attributes) {
        pg.setLineWidth(attributes.width);
        pg.setLineStyle(attributes.style);
        pg.setLineCap(attributes.cap);
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
        // TODO not yet implemented
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getAntialias() {
        // TODO not yet implemented
        return SWT.DEFAULT;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAntialias(final int value) {
        // TODO not yet implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getXORMode() {
        // TODO not yet implemented
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setXORMode(final boolean b) {
        // TODO not yet implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineStyle() {
        return pg.getLineStyle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineStyle(final int style) {
        pg.setLineStyle(style);
    }

    /**
     * {@inheritDoc}
     */
    public int getLineCap() {
        return pg.getLineCap();
    }
    
    /**
     * {@inheritDoc}
     */
    public void setLineCap(final int cap) {
        pg.setLineCap(cap);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineMiterLimit(final float miterLimit) {
        // TODO not yet implemented
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
            pg.setColor(lastState.foreground);
            pg.setBackground(lastState.background);
            pg.setFont(lastState.font);
            pg.setLineWidth(lastState.lineWidth);
            pg.setLineStyle(lastState.lineStyle);
            pg.setLineCap(lastState.lineCap);
        }
    }

}
