/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo;

import java.awt.geom.Point2D;

import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

/**
 * This handler provides basic zooming functionality for the canvas using the mouse wheel.
 * 
 * @author mri
 */
public class PMouseWheelZoomEventHandler extends PBasicInputEventHandler {

    /** the zoom sensitivity. */
    private static final double ZOOM_SENSITIVITY = 0.05;
    /** the minimum scale. */
    private double minScale = 0;
    /** the maximum scale. */
    private double maxScale = Double.MAX_VALUE;

    /**
     * Constructs a mouse wheel zoom event handler.
     */
    public PMouseWheelZoomEventHandler() {
        super();
    }

    /**
     * Returns the minimum scale.
     * 
     * @return the minimum scale
     */
    public double getMinScale() {
        return minScale;
    }

    /**
     * Sets the minimum scale.
     * 
     * @param minScale
     *            the minimum scale
     */
    public void setMinScale(final double minScale) {
        this.minScale = minScale;
    }

    /**
     * Returns the maximum scale.
     * 
     * @return the maximum scale
     */
    public double getMaxScale() {
        return maxScale;
    }

    /**
     * Sets the maximum scale.
     * 
     * @param maxScale
     *            the maximum scale
     */
    public void setMaxScale(final double maxScale) {
        this.maxScale = maxScale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseWheelRotated(final PInputEvent event) {
        final PCamera camera = event.getCamera();
        double scaleDelta = 1.0 + ZOOM_SENSITIVITY * event.getWheelRotation();

        final double currentScale = camera.getViewScale();
        final double newScale = currentScale * scaleDelta;

        if (newScale < minScale) {
            scaleDelta = minScale / currentScale;
        }
        if (maxScale > 0 && newScale > maxScale) {
            scaleDelta = maxScale / currentScale;
        }
        Point2D viewZoomPoint = event.getPosition();
        camera.scaleViewAboutPoint(scaleDelta, viewZoomPoint.getX(), viewZoomPoint.getY());
    }

}
