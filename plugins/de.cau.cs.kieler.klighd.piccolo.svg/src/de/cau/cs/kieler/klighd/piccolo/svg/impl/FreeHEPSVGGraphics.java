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
import java.util.Properties;

import org.freehep.graphicsio.svg.SVGGraphics2D;
import org.freehep.util.UserProperties;

import de.cau.cs.kieler.klighd.piccolo.svg.KlighdAbstractSVGGraphics;

/**
 * @author uru
 * 
 */
public class FreeHEPSVGGraphics extends KlighdAbstractSVGGraphics {

    ByteArrayOutputStream baos;
    SVGGraphics2D graphicsDelegate;

    Rectangle2D bounds;

    /**
     * 
     */
    public FreeHEPSVGGraphics(Rectangle2D bounds) {
        super(null);
        this.bounds = bounds;
        System.out.println("Free!!");
        init();

    }

    private void init() {
        
        System.out.println("Init with bounds " + bounds);
        
        baos = new ByteArrayOutputStream();
        // Dimension d = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
        int w = (int) (bounds.getWidth() - bounds.getX());
        int h = (int) (bounds.getHeight() - bounds.getY());
        Dimension d = new Dimension(w, h);
        graphicsDelegate = new SVGGraphics2D(baos, d);
        UserProperties props = new UserProperties();
        props.setProperty(SVGGraphics2D.TEXT_AS_SHAPES, false);
        graphicsDelegate.setProperties(props);
        graphicsDelegate.startExport();
        graphicsDelegate.translate(bounds.getX(), bounds.getY());

        setGraphicsDelegate(graphicsDelegate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSVG() {

//        System.out.println("Getting Stream!");

        try {
            graphicsDelegate.endExport();
            graphicsDelegate.closeStream();

            String s = new String(baos.toByteArray());
//            System.out.println(s);
            return s;
        } catch (IOException e) {
            // TODO Auto-generated catch block
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
