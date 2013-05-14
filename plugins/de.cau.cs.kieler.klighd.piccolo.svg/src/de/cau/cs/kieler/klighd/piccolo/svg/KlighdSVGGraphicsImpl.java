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
package de.cau.cs.kieler.klighd.piccolo.svg;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.StringWriter;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.RGB;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.util.RGBGradient;

/**
 * @author uru
 * 
 */
public class KlighdSVGGraphicsImpl extends Graphics2D implements KlighdSWTGraphics {

    private SVGGraphics2D graphics;

    /**
     * 
     */
    public KlighdSVGGraphicsImpl() {
        // Get a DOMImplementation.
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        this.graphics = new SVGGraphics2D(document);
        graphics.setColor(Color.WHITE);
        graphics.setBackground(Color.WHITE);

    }

    public String getSVG() {
        StringWriter sw = new StringWriter();
        try {
            graphics.stream(sw, true);

            // System.out.println(sw.toString());

        } catch (SVGGraphics2DIOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    private Color rgb2Color(final RGB color) {
        return new Color(color.red, color.green, color.blue);
    }

    private GradientPaint rgb2Pattern(final RGBGradient gradient, Rectangle2D bounds) {
        GradientPaint gp = new GradientPaint((float)bounds.getMinX(), 
                (float)bounds.getMinY(), 
                rgb2Color(gradient.getColor1()),
                (float)bounds.getMaxX(), 
                (float)bounds.getMaxY(), 
                rgb2Color(gradient.getColor2()));
        
        return gp;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setDevice(Device theDevice) {
        // nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGC(GC theGc) {
        // nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LineAttributes getLineAttributes() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineAttributes(LineAttributes attributes) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getLineWidth() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidth(float lineWidth) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAlpha() {
        return graphics.getColor().getAlpha();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlpha(int alpha) {
        Color c = graphics.getColor();
        Color c2 = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
        graphics.setColor(c2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(RGB color) {
        graphics.setColor(rgb2Color(color));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPattern(RGBGradient gradient, Rectangle2D bounds) {
        graphics.setPaint(rgb2Pattern(gradient, bounds));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackground(RGB backgroundColor) {
        graphics.setBackground(rgb2Color(backgroundColor));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundPattern(RGBGradient backgroundGradient, Rectangle2D bounds) {
        graphics.setPaint(rgb2Pattern(backgroundGradient, bounds));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AffineTransform getTransform() {
        return graphics.getTransform();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(AffineTransform transform) {
        graphics.transform(transform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTransform(AffineTransform transform) {
        graphics.setTransform(transform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRect(double x, double y, double width, double height) {
        graphics.drawRect((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRect(double x, double y, double width, double height) {
        graphics.fillRect((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOval(double x, double y, double width, double height) {
        graphics.drawOval((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillOval(double x, double y, double width, double height) {
        graphics.fillOval((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawArc(double x, double y, double width, double height, double startAngle,
            double extent) {
        graphics.drawArc((int) x, (int) y, (int) width, (int) height, (int) startAngle,
                (int) extent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillArc(double x, double y, double width, double height, double startAngle,
            double extent) {
        graphics.fillArc((int) x, (int) y, (int) width, (int) height, (int) startAngle,
                (int) extent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRoundRect(double x, double y, double width, double height, double arcWidth,
            double arcHeight) {
        graphics.drawRect((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRoundRect(double x, double y, double width, double height, double arcWidth,
            double arcHeight) {
        graphics.fillRoundRect((int) x, (int) y, (int) width, (int) height, (int) arcWidth,
                (int) arcHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPolygon(double[] pts) {
        graphics.drawPolygon(getPolygon(pts));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillPolygon(double[] pts) {
        graphics.fillPolygon(getPolygon(pts));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPolyline(double[] pts) {
        int[] xs = new int[pts.length / 2];
        int[] ys = new int[pts.length / 2];
        for (int i = 0; i < pts.length; i += 2) {
            xs[i / 2] = (int) pts[i];
            ys[i / 2] = (int) pts[i + 1];
        }

        graphics.drawPolyline(xs, ys, pts.length / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawGeneralPath(GeneralPath gp) {
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Shape s) {
        graphics.draw(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(Shape s) {
        graphics.fill(s);
    }

    /**
     * 
     * @param pts
     *            alternating x and y coordinates
     * @return
     */
    private Polygon getPolygon(final double[] pts) {
        Polygon p = new Polygon();
        for (int i = 0; i < pts.length; i += 2) {
            p.addPoint((int) pts[i], (int) pts[i + 1]);
        }
        return p;
    }

    private void unsupported() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRenderingHints(Map<?, ?> hints) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clip(Shape s) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawGlyphVector(GlyphVector g, float x, float y) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawString(String str, int x, int y) {
        graphics.drawString(str, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawString(String str, float x, float y) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawString(AttributedCharacterIterator iterator, float x, float y) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getBackground() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Composite getComposite() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicsConfiguration getDeviceConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontRenderContext getFontRenderContext() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Paint getPaint() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getRenderingHint(Key hintKey) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RenderingHints getRenderingHints() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stroke getStroke() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotate(double theta) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotate(double theta, double x, double y) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scale(double sx, double sy) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackground(Color color) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setComposite(Composite comp) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPaint(Paint paint) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRenderingHint(Key hintKey, Object hintValue) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRenderingHints(Map<?, ?> hints) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStroke(Stroke s) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shear(double shx, double shy) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void translate(int x, int y) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void translate(double tx, double ty) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearRect(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clipRect(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void copyArea(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Graphics create() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, ImageObserver arg3) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, Color arg3, ImageObserver arg4) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, ImageObserver arg5) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, Color arg5,
            ImageObserver arg6) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
            int arg6, int arg7, int arg8, ImageObserver arg9) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
            int arg6, int arg7, int arg8, Color arg9, ImageObserver arg10) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawLine(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOval(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPolygon(int[] arg0, int[] arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPolyline(int[] arg0, int[] arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillOval(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillPolygon(int[] arg0, int[] arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRect(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shape getClip() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getClipBounds() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font getFont() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontMetrics getFontMetrics(Font arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClip(Shape arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClip(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(Color arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFont(Font arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPaintMode() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setXORMode(Color arg0) {
        // TODO Auto-generated method stub

    }
}
