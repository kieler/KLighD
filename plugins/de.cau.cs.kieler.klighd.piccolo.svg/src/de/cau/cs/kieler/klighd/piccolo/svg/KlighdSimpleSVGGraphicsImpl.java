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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.IndexColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.RenderableImage;
import java.io.StringWriter;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.RGB;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.krendering.KTextUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;
import de.cau.cs.kieler.klighd.piccolo.internal.util.RGBGradient;

/**
 * A wrapper for batik's {@link SVGGraphics2D} svg generator. Allows to render the contents of
 * KlighD views as SVGs.
 * 
 * After painting to the graphics object, the {@link #getSVG()} method can be used to retrieve the
 * SVG as String.
 * 
 * @author uru
 */
public class KlighdSimpleSVGGraphicsImpl extends Graphics2D implements KlighdSWTGraphicsEx {

    // The svg generator element
    private SVGGraphics2D graphics;
    private Document document;

    // Internal attributes
    private LineAttributes lineAttributes = new LineAttributes(1f);
    private int alpha = KlighdConstants.ALPHA_FULL_OPAQUE;
    private RGB fillColor = null;
    private Pair<RGBGradient, Rectangle2D> fillPattern = null;
    private FontData fontData = null;

    private static final String SVG_NS = "http://www.w3.org/2000/svg";

    /**
     * 
     */
    public KlighdSimpleSVGGraphicsImpl() {
        this(false);
    }

    /**
     * @param textAsShapes
     *            whether text should be rendered as shapes
     */
    public KlighdSimpleSVGGraphicsImpl(final boolean textAsShapes) {

        // Get a DOMImplementation.
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        document = domImpl.createDocument(SVG_NS, "svg", null);

        // assemble context
        SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);
        ctx.setEmbeddedFontsOn(true);

        // create and configure the graphics object
        graphics = new SVGGraphics2D(ctx, textAsShapes);
        graphics.setColor(Color.WHITE);
        graphics.setBackground(Color.WHITE);
        graphics.setPaint(Color.white);
        graphics.setFont(new Font(KlighdConstants.DEFAULT_FONT_NAME,
                KlighdConstants.DEFAULT_FONT_STYLE, KlighdConstants.DEFAULT_FONT_SIZE));

        // + RENDERING -> sets all other hints to initial value.
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        // + FRACTIONAL_METRICS -> sets initial values for text-rendering and shape-rendering.
        graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        // + ANTIALIASING -> shape-rendering and text-rendering
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        // + COLOR_RENDERING -> color-rendering
        graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_SPEED);
        // + INTERPOLATION -> image-rendering
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        // + TEXT_ANTIALIASING -> text-rendering
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
    }

    /**
     * @return the currently rendered SVG.
     */
    public String getSVG() {
        StringWriter sw = new StringWriter();
        try {
            graphics.stream(sw, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * {@inheritDoc}
     */
    public Device getDevice() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setDevice(final Device theDevice) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    public GC getGC() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setGC(final GC theGc) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    public LineAttributes getLineAttributes() {
        return lineAttributes;
    }

    /**
     * {@inheritDoc}
     */
    public void setLineAttributes(final LineAttributes attributes) {
        lineAttributes = attributes;
        Stroke s = new BasicStroke(lineAttributes.width, lineAttributes.cap - 1, lineAttributes.join - 1,
                        lineAttributes.miterLimit, lineAttributes.dash, lineAttributes.dashOffset);
        graphics.setStroke(s);
    }

    /**
     * {@inheritDoc}
     */
    public int getAlpha() {
        return alpha;
    }

    /**
     * {@inheritDoc}
     */
    public void setAlpha(final int alpha) {
        this.alpha = alpha;

        // in awt the alpha is encoded in the color
        Color c = graphics.getColor();
        Color c2 = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
        graphics.setColor(c2);
    }
    
    private RGB strokeColor = null;
    private Pair<RGBGradient, Rectangle2D> strokePattern = null;

    /**
     * {@inheritDoc}
     */
    public RGB getStrokeColor() {
        return color2rgb(graphics.getColor());
    }
    
    /**
     * {@inheritDoc}
     */
    public void setStrokeColor(final RGB color) {
        this.strokeColor = color;
        this.strokePattern = null;
    }

    /**
     * {@inheritDoc}
     */
    public void setStrokePattern(final RGBGradient gradient, final Rectangle2D bounds) {
        this.strokePattern = Pair.of(gradient, bounds);
        this.strokeColor = null;
    }

    /**
     * {@inheritDoc}
     */
    public RGB getFillColor() {
        return color2rgb(graphics.getColor());
    }
    
    /**
     * {@inheritDoc}
     */
    public void setFillColor(final RGB backgroundColor) {
        this.fillColor = backgroundColor;
        this.fillPattern = null;
    }

    /**
     * {@inheritDoc}
     */
    public void setFillPattern(final RGBGradient backgroundGradient, final Rectangle2D bounds) {
        this.fillPattern = Pair.of(backgroundGradient, bounds);
        this.fillColor = null;
    }

    /**
     * {@inheritDoc}
     */
    public FontData getFontData() {
        return fontData;
    }

    /**
     * {@inheritDoc}
     */
    public void setFont(final FontData theFontData) {
        this.fontData = theFontData;
        if (theFontData == null) {
            return;
        }
        graphics.setFont(new Font(theFontData.getName(), KTextUtil.swtFontStyle2Awt(theFontData
                .getStyle()), theFontData.getHeight()));
    }

    /**
     * {@inheritDoc}
     */
    public void setUnderline(final int theUnderlining, final RGB color) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    public void setStrikeout(final boolean theStrikeout, final RGB color) {
        // TODO Auto-generated method stub
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
    public void setTransform(final AffineTransform transform) {
        graphics.setTransform(transform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final AffineTransform transform) {
        graphics.transform(transform);
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
    public void setClip(final Shape clip) {
        graphics.setClip(clip);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clip(final Shape clip) {
        graphics.clip(clip);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Shape s) {
        final Paint p =
                this.strokeColor != null ? rgb2Color(this.strokeColor, this.alpha)
                        : this.strokePattern != null ? rgb2Pattern(this.strokePattern)
                                : Color.black;
        graphics.setPaint(p);
        graphics.draw(s);
    }

    /**
     * {@inheritDoc}
     */
    public void draw(final Path p) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    public void fill(final Shape s) {
        final Paint p =
                this.fillColor != null ? rgb2Color(this.fillColor, this.alpha)
                        : this.fillPattern != null ? rgb2Pattern(this.fillPattern)
                                : Color.black;
        graphics.setPaint(p);
        graphics.fill(s);
    }

    /**
     * {@inheritDoc}
     */
    public void fill(final Path p) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    public void drawImage(final Image image, final double width, final double height) {
        java.awt.Image img = convertToAWT(image.getImageData());
        graphics.drawImage(img, 0, 0, null);
    }

    /**
     * {@inheritDoc}
     */
    public void drawImage(final ImageData imageData, final double width, final double height) {
        java.awt.Image img = convertToAWT(imageData);
        graphics.drawImage(img, 0, 0, null);
    }

    /**
     * {@inheritDoc}
     */
    public void drawText(final String string) {
        // SVG 1.1 does not support automatic line wrapping, thus each line has to be drawn
        // individually.
        // SVG 1.2 supports a textArea with automatic wrapping, however this is not supported by all
        // browsers.
        int y = 0;
        int fontHeight =
                graphics.getFontMetrics().getHeight() - graphics.getFontMetrics().getDescent();

        // translate by the font height as the reference point for drawing text in svg seems to be
        // at the bottom left corner, SWT has it as the top left corner.
        translate(0, fontHeight);

        for (String line : string.split("\\r?\\n|\\r")) {
            graphics.drawString(line, 0, y);
            y += fontHeight;
        }
        translate(0, -fontHeight);
    }

    /*------------------------------------------------ 
     * Internal conversion methods.
     * ------------------------------------------------ */
    private static RGB color2rgb(final Color color) {
        return new RGB(color.getRed(), color.getGreen(), color.getBlue());
    }

    @SuppressWarnings("unused")
    private static Color rgb2Color(final RGB color) {
        return new Color(color.red, color.green, color.blue);
    }

    private static Color rgb2Color(final RGB color, final int alpha) {
        return new Color(color.red, color.green, color.blue, alpha);
    }

    private static GradientPaint rgb2Pattern(final Pair<RGBGradient, Rectangle2D> gradient) {
        return rgb2Pattern(gradient.getFirst(), gradient.getSecond());
    }

    private static GradientPaint rgb2Pattern(final RGBGradient gradient, final Rectangle2D bounds) {
        GradientPaint gp =
                new GradientPaint((float) bounds.getMinX(), (float) bounds.getMinY(),
                        rgb2Color(gradient.getColor1(), gradient.getAlpha1()), (float) bounds.getMaxX(),
                        (float) bounds.getMaxY(), rgb2Color(gradient.getColor2(), gradient.getAlpha2()));

        return gp;
    }

    private static BufferedImage convertToAWT(final ImageData data) {
        ColorModel colorModel = null;
        PaletteData palette = data.palette;
        if (palette.isDirect) {
            colorModel =
                    new DirectColorModel(data.depth, palette.redMask, palette.greenMask,
                            palette.blueMask);
            BufferedImage bufferedImage =
                    new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(
                            data.width, data.height), false, null);
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    int pixel = data.getPixel(x, y);
                    RGB rgb = palette.getRGB(pixel);
                    // CHECKSTYLEOFF Magic Numbers
                    bufferedImage.setRGB(x, y, rgb.red << 16 | rgb.green << 8 | rgb.blue);
                    // CHECKSTYLEON Magic Numbers
                }
            }
            return bufferedImage;
        } else {
            RGB[] rgbs = palette.getRGBs();
            byte[] red = new byte[rgbs.length];
            byte[] green = new byte[rgbs.length];
            byte[] blue = new byte[rgbs.length];
            for (int i = 0; i < rgbs.length; i++) {
                RGB rgb = rgbs[i];
                red[i] = (byte) rgb.red;
                green[i] = (byte) rgb.green;
                blue[i] = (byte) rgb.blue;
            }
            if (data.transparentPixel != -1) {
                colorModel =
                        new IndexColorModel(data.depth, rgbs.length, red, green, blue,
                                data.transparentPixel);
            } else {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue);
            }
            BufferedImage bufferedImage =
                    new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(
                            data.width, data.height), false, null);
            WritableRaster raster = bufferedImage.getRaster();
            int[] pixelArray = new int[1];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    int pixel = data.getPixel(x, y);
                    pixelArray[0] = pixel;
                    raster.setPixel(x, y, pixelArray);
                }
            }
            return bufferedImage;
        }
    }

    /* ------------------------------------------------ */
    /* legacy methods due to inheritance of Graphics2D */
    /* that is required by KlighdCanvas -> PSWTCanvas */
    /* and PPaintContext */
    /* ------------------------------------------------ */

    @Override
    public Composite getComposite() {
        return graphics.getComposite();
    }

    @Override
    public void setComposite(final Composite comp) {
        graphics.setComposite(comp);
    }

    @Override
    public void setColor(final java.awt.Color c) {
        setStrokeColor(color2rgb(c));
    }

    @Override
    public void setBackground(final java.awt.Color c) {
        setFillColor(color2rgb(c));
    }

    @Override
    public void fillRect(final int x, final int y, final int width, final int height) {
        fill(new Rectangle2D.Double(x, y, width, height));
    }

    @Override
    public void setRenderingHint(final Key hintKey, final Object hintValue) {
        graphics.setRenderingHint(hintKey, hintValue);
    }

    /* ------------------------------------------------ */
    /* legacy methods due to inheritance of Graphics2D */
    /* that are not supported by this implementation */
    /* ------------------------------------------------ */

    // CHECKSTYLEOFF Parameter

    @Override
    public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawString(String str, int x, int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawString(String str, float x, float y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, float x, float y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawGlyphVector(GlyphVector g, float x, float y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hit(java.awt.Rectangle rect, Shape s, boolean onStroke) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GraphicsConfiguration getDeviceConfiguration() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPaint(Paint paint) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setStroke(Stroke s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getRenderingHint(Key hintKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRenderingHints(Map<?, ?> hints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addRenderingHints(Map<?, ?> hints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RenderingHints getRenderingHints() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void translate(int x, int y) {
        graphics.translate(x, y);
    }

    @Override
    public void translate(double tx, double ty) {
        graphics.translate(tx, ty);
    }

    @Override
    public void rotate(double theta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rotate(double theta, double x, double y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void scale(double sx, double sy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shear(double shx, double shy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Paint getPaint() {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.awt.Color getBackground() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stroke getStroke() {
        throw new UnsupportedOperationException();
    }

    @Override
    public FontRenderContext getFontRenderContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Graphics create() {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.awt.Color getColor() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPaintMode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setXORMode(java.awt.Color c1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.awt.Font getFont() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFont(java.awt.Font font) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FontMetrics getFontMetrics(java.awt.Font f) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.awt.Rectangle getClipBounds() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clipRect(int x, int y, int width, int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setClip(int x, int y, int width, int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearRect(int x, int y, int width, int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(java.awt.Image img, AffineTransform xform, ImageObserver obs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(java.awt.Image img, int x, int y, ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(java.awt.Image img, int x, int y, int width, int height,
            ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(java.awt.Image img, int x, int y, Color bgcolor, ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(java.awt.Image img, int x, int y, int width, int height,
            Color bgcolor, ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(java.awt.Image img, int dx1, int dy1, int dx2, int dy2, int sx1,
            int sy1, int sx2, int sy2, ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(java.awt.Image img, int dx1, int dy1, int dx2, int dy2, int sx1,
            int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException();
    }
}
