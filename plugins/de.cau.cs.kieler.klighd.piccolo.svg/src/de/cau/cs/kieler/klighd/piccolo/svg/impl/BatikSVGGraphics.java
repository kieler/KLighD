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
package de.cau.cs.kieler.klighd.piccolo.svg.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.io.StringWriter;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.svg.KlighdAbstractSVGGraphics;

/**
 * A wrapper for batik's {@link SVGGraphics2D} svg generator. Allows to render the contents of
 * KlighD views as SVGs.
 * 
 * After painting to the graphics object, the {@link #getSVG()} method can be used to retrieve the
 * SVG as String.
 * 
 * @author uru
 * 
 * @see <a href="http://xmlgraphics.apache.org/batik/"> http://xmlgraphics.apache.org/batik/</a>
 */
public class BatikSVGGraphics extends KlighdAbstractSVGGraphics {

    private SVGGraphics2D graphicsDelegate;
    private Document document;

    private static final String SVG_NS = "http://www.w3.org/2000/svg";

    private Rectangle2D bounds;

    /**
     * Constructor.<br>
     * Delegates to {@link #KlighdBatikSVGGraphics(boolean) #KlighdBatikSVGGraphics(false)}.
     * 
     * @param bounds
     *            the bounds will be set as viewport values for the resulting root <svg ..> tag.
     */
    public BatikSVGGraphics(final Rectangle2D bounds) {
        this(bounds, false);
    }

    /**
     * Constructor.
     * 
     * @param bounds
     *            the bounds will be set as viewport values for the resulting root <svg ..> tag.
     * @param textAsShapes
     *            whether text should be rendered as shapes
     */
    public BatikSVGGraphics(final Rectangle2D bounds, final Boolean textAsShapes) {
        super(null);
        this.bounds = bounds;

        // Get a DOMImplementation.
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        document = domImpl.createDocument(SVG_NS, "svg", null);

        // assemble context
        SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);
        ctx.setEmbeddedFontsOn(true);

        // create and configure the graphics object
        graphicsDelegate = new SVGGraphics2D(ctx, textAsShapes);
        // IMPORTANT
        super.setGraphicsDelegate(graphicsDelegate);
        graphicsDelegate.setColor(Color.WHITE);
        graphicsDelegate.setBackground(Color.WHITE);
        graphicsDelegate.setPaint(Color.white);
        graphicsDelegate.setFont(new Font(KlighdConstants.DEFAULT_FONT_NAME,
                KlighdConstants.DEFAULT_FONT_STYLE, KlighdConstants.DEFAULT_FONT_SIZE));

        // + RENDERING -> sets all other hints to initial value.
        graphicsDelegate.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_SPEED);
        // + FRACTIONAL_METRICS -> sets initial values for text-rendering and shape-rendering.
        graphicsDelegate.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        // + ANTIALIASING -> shape-rendering and text-rendering
        graphicsDelegate.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        // + COLOR_RENDERING -> color-rendering
        graphicsDelegate.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_SPEED);
        // + INTERPOLATION -> image-rendering
        graphicsDelegate.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        // + TEXT_ANTIALIASING -> text-rendering
        graphicsDelegate.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSVG() {
        StringWriter sw = new StringWriter();
        try {
            graphicsDelegate.stream(sw, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // batik does not create any viewport elements, so we add them here
        StringBuffer sb = new StringBuffer(sw.toString());
        sb.insert(
                sb.indexOf("<svg") + "<svg".length() + 1,
                " x=\"" + (int) bounds.getX() + "px\" y=\"" + (int) bounds.getY() + "px\" "
                        + "width=\"" + (int) bounds.getWidth() + "px\" height=\""
                        + (int) bounds.getHeight() + "px\" ");

        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        // done implicitly when retrieving the svg
        // call it just to be sure
        getSVG();
    }

}
