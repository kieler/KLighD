/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.util;

import java.awt.Graphics2D;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * 
 * @author chsch
 */
public class KlighdPaintContext extends PPaintContext {

    /**
     * Constructor.<br>
     * Addition of semantic data to the created diagram is switch off.
     * 
     * @param graphics
     *            the {@link KlighdSWTGraphics} graphics system abstraction object to be used.
     */
    public KlighdPaintContext(final KlighdSWTGraphics graphics) {
        this(graphics, false);
    }

    /**
     * Constructor.
     * 
     * @param graphics
     *            the {@link KlighdSWTGraphics} graphics system abstraction object to be used.
     * @param addSemanticData
     *            flag determining whether semantic data shall be added to the diagram, e.g. while
     *            exporting an SVG based image
     */
    public KlighdPaintContext(final KlighdSWTGraphics graphics, final boolean addSemanticData) {
        super((Graphics2D) graphics);
        this.addSemanticData = addSemanticData;
    }

    private double cameraZoomScale;
    private boolean addSemanticData;

    @Override
    public void pushCamera(final PCamera aCamera) {
        super.pushCamera(aCamera);

        if (aCamera instanceof KlighdMainCamera) {
            aCamera.getViewTransformReference().getScaleX();
        }
    }

    /**
     * Provides the current diagram zoom factor as determined by the active {@link KlighdMainCamera}'s
     * view {@link java.awt.geom.AffineTransform transform}.
     * 
     * @return the current diagram zoom factor 
     */
    public double getCameraZoomScale() {
        return this.cameraZoomScale;
    }

    /**
     * Returns <code>true</code> if semantic data shall be added to the diagram while drawing, e.g.
     * while creating an SVG export.
     * 
     * @return <code>true</code> if semantic data shall be added to the diagram while drawing,
     *         <code>false</code> otherwise.
     */
    public boolean isAddSemanticData() {
        return this.addSemanticData;
    }

    /**
     * {@inheritDoc}
     */
    public KlighdSWTGraphics getKlighdGraphics() {
        return (KlighdSWTGraphics) super.getGraphics();
    }
}
