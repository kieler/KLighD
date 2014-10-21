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
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMagnificationLensCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * This is a specialization of {@link PPaintContext} contributing behavior for adding semantic data
 * into drawn (vector graphics) images or implementing the diagram scale dependent visibility of
 * diagram figures (or figure parts).
 *
 * @author chsch
 */
public class KlighdPaintContext extends PPaintContext {

    /**
     * Factory method creating a {@link KlighdPaintContext} configured for on screen (main) diagram
     * drawing.
     *
     * @param graphics
     *            the {@link KlighdSWTGraphics} to draw on
     * @return the desired {@link KlighdPaintContext}
     */
    public static KlighdPaintContext createDiagramPaintContext(final KlighdSWTGraphics graphics) {
        return new KlighdPaintContext(graphics, false, false, false, false);
    }

    /**
     * Factory method creating a {@link KlighdPaintContext} configured for on screen outline diagram
     * drawing.
     *
     * @param graphics
     *            the {@link KlighdSWTGraphics} to draw on
     * @return the desired {@link KlighdPaintContext}
     */
    public static KlighdPaintContext createOutlinePaintContext(final KlighdSWTGraphics graphics) {
        return new KlighdPaintContext(graphics, true, false, false, false);
    }

    /**
     * Factory method creating a {@link KlighdPaintContext} configured for exporting the diagram
     * into an image.
     *
     * @param graphics
     *            the {@link KlighdSWTGraphics} to draw on
     * @return the desired {@link KlighdPaintContext}
     */
    public static KlighdPaintContext createExportDiagramPaintContext(final KlighdSWTGraphics graphics) {
        return new KlighdPaintContext(graphics, false, true, false, true);
    }

    /**
     * Factory method creating a {@link KlighdPaintContext} configured for printing the diagram.
     *
     * @param graphics
     *            the {@link KlighdSWTGraphics} to draw on
     * @return the desired {@link KlighdPaintContext}
     */
    public static KlighdPaintContext createPrintoutPaintContext(final KlighdSWTGraphics graphics) {
        return new KlighdPaintContext(graphics, false, false, true, false);
    }


    /**
     * Constructor.
     *
     * @param graphics
     *            the {@link KlighdSWTGraphics} graphics system abstraction object to be used.
     * @param outline
     *            indicates that the outline diagram image is about to be drawn
     * @param export
     *            indicates that the diagram is about to be exported into an image
     * @param printout
     *            indicates that the diagram is about to be printed
     * @param addSemanticData
     *            flag determining whether semantic data shall be added to the diagram, e.g. while
     *            exporting an SVG based image
     */
    protected KlighdPaintContext(final KlighdSWTGraphics graphics, final boolean outline,
            final boolean export, final boolean printout, final boolean addSemanticData) {
        super((Graphics2D) graphics);
        this.mainDiagram = !(outline || export || printout);
        this.outline = outline;
        this.export = export;
        this.printout = printout;
        this.addSemanticData = addSemanticData;
    }

    private double cameraZoomScale = 1d;
    private final boolean mainDiagram;
    private final boolean outline;
    private final boolean export;
    private final boolean printout;
    private final boolean addSemanticData;


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
     * @return <code>true</code> if this {@link KlighdPaintContext} is configured for drawing the
     *         outline, <code>false</code> otherwise
     */
    public boolean isMainDiagram() {
        return mainDiagram;
    }

    /**
     * @return <code>true</code> if this {@link KlighdPaintContext} is configured for drawing the
     *         outline, <code>false</code> otherwise
     */
    public boolean isOutline() {
        return outline;
    }

    /**
     * @return <code>true</code> if this {@link KlighdPaintContext} is configured for creating an
     *         exported diagram image, <code>false</code> otherwise
     */
    public boolean isImageExport() {
        return export;
    }

    /**
     * @return <code>true</code> if this {@link KlighdPaintContext} is configured for creating a
     *         diagram printout, <code>false</code> otherwise
     */
    public boolean isPrintout() {
        return printout;
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

    @Override
    public void pushCamera(final PCamera aCamera) {
        super.pushCamera(aCamera);

        if (isPrintout()) {
            // in case a printout is to be created leave the cameraZoomScale as it is,
            //  should be equal to 1d!
            return;

        } else if (aCamera instanceof KlighdMainCamera) {
            cameraZoomScale = aCamera.getViewTransformReference().getScaleX();

        } else if (aCamera instanceof KlighdMagnificationLensCamera) {
            cameraZoomScale = aCamera.getViewTransformReference().getScaleX();
        }
    }

    @Override
    public void popCamera() {
        final PCamera aCamera = getCamera();

        if (aCamera instanceof KlighdMagnificationLensCamera) {
            super.popCamera();
            cameraZoomScale = aCamera.getViewTransformReference().getScaleX();
        } else {
            super.popCamera();
        }
    }

    /**
     * {@inheritDoc}
     */
    public KlighdSWTGraphics getKlighdGraphics() {
        return (KlighdSWTGraphics) super.getGraphics();
    }
}
