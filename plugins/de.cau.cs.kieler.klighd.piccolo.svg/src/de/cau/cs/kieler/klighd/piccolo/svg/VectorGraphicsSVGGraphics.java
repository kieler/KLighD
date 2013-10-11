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

import java.awt.geom.Rectangle2D;

import de.erichseifert.vectorgraphics2d.SVGGraphics2D;

/**
 * @author uru
 */
public class VectorGraphicsSVGGraphics extends KlighdAbstractSVGGraphics {

    /**
     * 
     */
    public VectorGraphicsSVGGraphics() {
        super(new CustomizedSVGGraphics2D());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSVG() {
        SVGGraphics2D g = (SVGGraphics2D) getGraphicsDelegate();

        StringBuffer sb = new StringBuffer(new String(g.getBytes()));
        // add a surrounding group
        sb.insert(sb.indexOf("<style") - 1, "<g>");
        sb.insert(sb.lastIndexOf("<") - 1, "</g>");

        return sb.toString();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        setGraphicsDelegate(new CustomizedSVGGraphics2D());
    }

    /**
     * Slightly custamize the original svg generator.
     * 
     * @author uru
     */
    private static class CustomizedSVGGraphics2D extends SVGGraphics2D {

        /**
         * 
         */
        public CustomizedSVGGraphics2D() {
            super(0, 0, Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);
            // super(0, 0, 400, 400);
        }

        @Override
        protected void writeHeader() {
            Rectangle2D bounds = getBounds();
            double x = bounds.getX();
            double y = bounds.getY();
            double w = bounds.getWidth();
            double h = bounds.getHeight();
            writeln("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writeln("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" ",
                    "\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">");
            writeln("<svg version=\"1.2\" xmlns=\"http://www.w3.org/2000/svg\" ",
                    "xmlns:xlink=\"http://www.w3.org/1999/xlink\" ", "x=\"", x, "mm\" y=\"", y,
                    "mm\" ", "width=\"", w, "mm\" height=\"", h, "mm\" "
                    // we dont want the viewBox
                    // + "viewBox=\"", x, " ", y, " ", w, " ", h, "\""
                    , ">");
            writeln("<style type=\"text/css\"><![CDATA[");
            writeln("text { font:", getFont().getSize2D(), "px ", getFont().getFamily(), "; }");
            writeln("]]></style>");
        }
    }
}
