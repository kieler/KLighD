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

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.freehep.graphicsio.svg.SVGGraphics2D;
import org.freehep.util.UserProperties;

import de.cau.cs.kieler.klighd.piccolo.svg.KlighdAbstractSVGGraphics;

/**
 * 
 * 
 * @author uru
 * 
 * @see <a href="http://java.freehep.org/vectorgraphics/">
 *      http://java.freehep.org/vectorgraphics/</a>
 */
public class FreeHEPSVGGraphics extends KlighdAbstractSVGGraphics {

    private ByteArrayOutputStream baos;
    private SVGGraphics2D graphicsDelegate;

    private Rectangle2D bounds;
    private boolean textAsShapes;

    /**
     * @param bounds
     *            the bounds will be set as viewport values for the resulting root <svg ..> tag.
     * @param textAsShapes
     *            whether text should be rendered as shapes
     */
    public FreeHEPSVGGraphics(final Rectangle2D bounds, final boolean textAsShapes) {
        super(null);
        this.bounds = bounds;
        this.textAsShapes = textAsShapes;

        init();
    }

    private void init() {
        baos = new ByteArrayOutputStream();

        // calc viewport
        int w = (int) (bounds.getWidth() - bounds.getX());
        int h = (int) (bounds.getHeight() - bounds.getY());
        Dimension d = new Dimension(w, h);
        // create graphics object
        graphicsDelegate = new SVGGraphics2D(baos, d);

        // some settings
        UserProperties props = new UserProperties();
        props.setProperty(SVGGraphics2D.TEXT_AS_SHAPES, textAsShapes);
        graphicsDelegate.setProperties(props);

        // start
        graphicsDelegate.startExport();
        graphicsDelegate.translate(bounds.getX(), bounds.getY());

        setGraphicsDelegate(graphicsDelegate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSVG() {
        try {
            // end stream and retrieve svg from stream
            graphicsDelegate.endExport();
            graphicsDelegate.closeStream();

            String s = new String(baos.toByteArray());
            return s;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        init();
    }

}
