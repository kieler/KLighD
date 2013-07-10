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
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.StringWriter;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.RGB;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.util.RGBGradient;

/**
 * @author uru
 * 
 */
public class KlighdSVGGraphicsImpl extends KlighdSWTGraphicsImpl implements KlighdSWTGraphics {

    private SVGGraphics2D graphics;
    private Document document;

    // private KlighdSWTGraphicsImpl origGraphics;

    /**
     * 
     */
    public KlighdSVGGraphicsImpl(Device d) {
        super(null, d);
        // origGraphics = og;
        // Get a DOMImplementation.
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        document = domImpl.createDocument(svgNS, "svg", null);
        // Create an instance of the SVG Generator.
        this.graphics = new SVGGraphics2D(document);
        graphics.setColor(Color.WHITE);
        graphics.setBackground(Color.WHITE);
        graphics.setPaint(Color.white);

    }

    /**
     * @return the graphics
     */
    public SVGGraphics2D getSVGGraphics() {
        return graphics;
    }

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }

    public String getSVG() {
        StringWriter sw = new StringWriter();
        try {
            graphics.stream(sw, true);

//             FIXME otherwise ids are not generated
//             TransformerFactory transfac = TransformerFactory.newInstance();
//             Transformer trans = transfac.newTransformer();
//             trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//             trans.setOutputProperty(OutputKeys.INDENT, "yes");
//            
//             //create string from xml tree
//             // StringWriter sw = new StringWriter();
//             sw = new StringWriter();
//             StreamResult result = new StreamResult(sw);
//            
//             Node parent = graphics.getRoot();
//             while(parent.getParentNode() != null) {
//             parent = parent.getParentNode();
//            
//             }
//            
//             DOMSource source = new DOMSource(parent);
//             trans.transform(source, result);
//             String xmlString = sw.toString();

//             System.out.println(sw.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    private Color rgb2Color(final RGB color) {
        return new Color(color.red, color.green, color.blue);
    }

    private GradientPaint rgb2Pattern(final RGBGradient gradient, Rectangle2D bounds) {
        GradientPaint gp =
                new GradientPaint((float) bounds.getMinX(), (float) bounds.getMinY(),
                        rgb2Color(gradient.getColor1()), (float) bounds.getMaxX(),
                        (float) bounds.getMaxY(), rgb2Color(gradient.getColor2()));

        return gp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDevice(Device theDevice) {
        super.setDevice(theDevice);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGC(GC theGc) {
        super.gc = theGc;
        super.setGC(theGc);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LineAttributes getLineAttributes() {
        // TODO Auto-generated method stub
        // unsupported();
        return super.getLineAttributes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineAttributes(LineAttributes attributes) {
        // TODO Auto-generated method stub
        // unsupported();
        super.setLineAttributes(attributes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getLineWidth() {
        return super.getLineWidth();
        // return strokeWidth;
    }

    private int strokeWidth = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidth(float lineWidth) {
        super.setLineWidth(lineWidth);
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
        super.setAlpha(alpha);

        Color c = graphics.getColor();
        Color c2 = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
        graphics.setColor(c2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(RGB color) {
        super.setColor(color);

        graphics.setColor(rgb2Color(color));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPattern(RGBGradient gradient, Rectangle2D bounds) {
        super.setPattern(gradient, bounds);

        graphics.setPaint(rgb2Pattern(gradient, bounds));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackground(RGB backgroundColor) {
        super.setBackground(backgroundColor);
        graphics.setBackground(rgb2Color(backgroundColor));

        // FIXME why?? It seems, that batik ignores the background color.
        setColor(backgroundColor);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundPattern(RGBGradient backgroundGradient, Rectangle2D bounds) {
        super.setBackgroundPattern(backgroundGradient, bounds);

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
        super.transform(transform);

        graphics.transform(transform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTransform(AffineTransform transform) {
        super.setTransform(transform);

        graphics.setTransform(transform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawText(String str, double x, double y) {
        super.drawText(str, x, y);

        graphics.drawString(str, (float) x, (float) y);
    }

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void drawRect(double x, double y, double width, double height) {
    // graphics.drawRect((int) x, (int) y, (int) width, (int) height);
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void fillRect(double x, double y, double width, double height) {
    // graphics.fillRect((int) x, (int) y, (int) width, (int) height);
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void drawOval(double x, double y, double width, double height) {
    // graphics.drawOval((int) x, (int) y, (int) width, (int) height);
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void fillOval(double x, double y, double width, double height) {
    // graphics.fillOval((int) x, (int) y, (int) width, (int) height);
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void drawArc(double x, double y, double width, double height, double startAngle,
    // double extent) {
    // graphics.drawArc((int) x, (int) y, (int) width, (int) height, (int) startAngle,
    // (int) extent);
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void fillArc(double x, double y, double width, double height, double startAngle,
    // double extent) {
    // graphics.fillArc((int) x, (int) y, (int) width, (int) height, (int) startAngle,
    // (int) extent);
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void drawRoundRect(double x, double y, double width, double height, double arcWidth,
    // double arcHeight) {
    // graphics.drawRect((int) x, (int) y, (int) width, (int) height);
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void fillRoundRect(double x, double y, double width, double height, double arcWidth,
    // double arcHeight) {
    // graphics.fillRoundRect((int) x, (int) y, (int) width, (int) height, (int) arcWidth,
    // (int) arcHeight);
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void drawPolygon(double[] pts) {
    // graphics.drawPolygon(getPolygon(pts));
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void fillPolygon(double[] pts) {
    // graphics.fillPolygon(getPolygon(pts));
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void drawPolyline(double[] pts) {
    // int[] xs = new int[pts.length / 2];
    // int[] ys = new int[pts.length / 2];
    // for (int i = 0; i < pts.length; i += 2) {
    // xs[i / 2] = (int) pts[i];
    // ys[i / 2] = (int) pts[i + 1];
    // }
    //
    // graphics.drawPolyline(xs, ys, pts.length / 2);
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void drawGeneralPath(GeneralPath gp) {
    // graphics.draw(gp);
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Shape s) {
        super.draw(s);
        graphics.draw(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(Shape s) {

        if (s instanceof Rectangle2D) {
            // System.out.println(super.getColor() + " " + super.getAlpha() + " " + s);
            // System.out.println(graphics.getColor() + " " + graphics.getColor().getAlpha() + " " +
            // s);
            // System.out.println();
            // setColor(Color.WHITE);

            if (((Rectangle2D) s).getHeight() == 39 || ((Rectangle2D) s).getHeight() == 35) {
                // return;
            }
        }

        super.fill(s);
        graphics.fill(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRect(double x, double y, double width, double height) {
        super.drawRect(x, y, width, height);
        graphics.drawRect((int) x, (int) y, (int)width, (int)height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRect(int x, int y, int width, int height) {
        super.drawRect(x, y, width, height);
        graphics.drawRect(x, y, width, height);
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
        try {
            throw new UnsupportedOperationException();
        } catch (UnsupportedOperationException ue) {
            ue.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFont(final FontData font) {
        super.setFont(font);

        graphics.setFont(new Font(font.getName(), font.getStyle(), font.getHeight()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clip(Shape s) {
        super.clip(s);
        graphics.clip(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
        // TODO Auto-generated method stub
        System.out.println("di");
        unsupported();
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
        // TODO Auto-generated method stub
        System.out.println("di");
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
        // TODO Auto-generated method stub
        System.out.println("di");
        unsupported();
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
        graphics.drawString(str, (int) x, (int) y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getBackground() {
        return graphics.getBackground();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Composite getComposite() {
        return graphics.getComposite();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackground(Color color) {
        super.setBackground(color);
        graphics.setBackground(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setComposite(Composite comp) {
        super.setComposite(comp);
        // unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRenderingHint(Key hintKey, Object hintValue) {
        super.setRenderingHint(hintKey, hintValue);
        graphics.setRenderingHint(hintKey, hintValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRenderingHints(Map<?, ?> hints) {
        super.setRenderingHints(hints);
        graphics.setRenderingHints(hints);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shear(double shx, double shy) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void translate(int x, int y) {
        super.translate(x, y);

        // FIXME different translation ?!
        graphics.translate(x - 20, y + 20);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void translate(double tx, double ty) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearRect(int x, int y, int width, int height) {
        super.clearRect(x, y, width, height);
        graphics.clearRect(x, y, width, height);
        // unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clipRect(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void copyArea(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Graphics create() {
        return this.create();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        // unsupported();
        super.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, ImageObserver arg3) {
        System.out.println("Dri");
        unsupported();
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, Color arg3, ImageObserver arg4) {
        // TODO Auto-generated method stub
        System.out.println("Dri");
        unsupported();
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, ImageObserver arg5) {
        // TODO Auto-generated method stub
        System.out.println("Dri");
        unsupported();
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, Color arg5,
            ImageObserver arg6) {
        // TODO Auto-generated method stub
        System.out.println("Dri");
        unsupported();
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
            int arg6, int arg7, int arg8, ImageObserver arg9) {
        // TODO Auto-generated method stub
        System.out.println("Dri");
        unsupported();
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
            int arg6, int arg7, int arg8, Color arg9, ImageObserver arg10) {
        // TODO Auto-generated method stub
        System.out.println("Dri");
        unsupported();
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawLine(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOval(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPolygon(int[] arg0, int[] arg1, int arg2) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPolyline(int[] arg0, int[] arg1, int arg2) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillOval(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillPolygon(int[] arg0, int[] arg1, int arg2) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRect(int x, int y, int width, int height) {
        super.fillRect(x, y, width, height);
        graphics.fillRect(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shape getClip() {
        return graphics.getClip();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClip(Shape clip) {
        super.setClip(clip);
        graphics.setClip(clip);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClip(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(Color color) {
        super.setColor(color);
        graphics.setColor(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPaintMode() {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setXORMode(Color arg0) {
        // TODO Auto-generated method stub
        unsupported();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Device getDevice() {
        // TODO Auto-generated method stub
        return null;
    }
}
