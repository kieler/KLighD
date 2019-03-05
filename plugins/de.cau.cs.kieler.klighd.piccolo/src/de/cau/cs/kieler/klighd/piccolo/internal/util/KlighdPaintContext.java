/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.util;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Stack;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMagnificationLensCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * This is a specialization of {@link PPaintContext} suppressing the super class' clipping
 * functionality and contributing behavior for adding semantic data into drawn (vector graphics)
 * images as well as implementing the diagram scale dependent visibility of diagram figures (or
 * figure parts).
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
        return new KlighdPaintContext(graphics, false, false, false, true, false);
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
        return new KlighdPaintContext(graphics, true, false, false, true, false);
    }

    /**
     * Factory method creating a {@link KlighdPaintContext} configured for exporting the diagram
     * into an image.
     *
     * @param graphics
     *            the {@link KlighdSWTGraphics} to draw on
     * @param applyCameraZoomLevel
     *            if <code>true</code> the diagram zoom level determined by the
     *            {@link KlighdMainCamera}'s view transform will be used while evaluating the
     *            visibility of the particular diagram elements and diagram element figure parts, if
     *            <code>false</code> a diagram zoom level of <code>1.0<code> is assumed.
     * @return the desired {@link KlighdPaintContext}
     */
    public static KlighdPaintContext createExportDiagramPaintContext(
            final KlighdSWTGraphics graphics, boolean applyCameraZoomLevel, boolean addSemanticData) {
        return new KlighdPaintContext(graphics, false, true, false, applyCameraZoomLevel, addSemanticData);
    }

    /**
     * Factory method creating a {@link KlighdPaintContext} configured for printing the diagram.
     *
     * @param graphics
     *            the {@link KlighdSWTGraphics} to draw on
     * @param applyCameraZoomLevel
     *            if <code>true</code> the diagram zoom level determined by the
     *            {@link KlighdMainCamera}'s view transform will be used while evaluating the
     *            visibility of the particular diagram elements and diagram element figure parts, if
     *            <code>false</code> a diagram zoom level of <code>1.0<code> is assumed.
     * @return the desired {@link KlighdPaintContext}
     */
    public static KlighdPaintContext createPrintoutPaintContext(final KlighdSWTGraphics graphics,
            boolean applyCameraZoomLevel) {
        return new KlighdPaintContext(graphics, false, false, true, applyCameraZoomLevel, false);
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
     * @param applyCameraZoomLevel
     *            if <code>true</code> the diagram zoom level determined by the
     *            {@link KlighdMainCamera}'s view transform will be used while evaluating the
     *            visibility of the particular diagram elements and diagram element figure parts, if
     *            <code>false</code> a diagram zoom level of <code>1.0<code> is assumed.
     * @param addSemanticData
     *            flag determining whether semantic data shall be added to the diagram, e.g. while
     *            exporting an SVG based image
     */
    protected KlighdPaintContext(final KlighdSWTGraphics graphics, final boolean outline,
            final boolean export, final boolean printout, boolean applyCameraZoomLevel,
            final boolean addSemanticData) {
        super((Graphics2D) graphics);
        this.mainDiagram = !(outline || export || printout);
        this.outline = outline;
        this.export = export;
        this.printout = printout;
        this.applyCameraZoomLevel = applyCameraZoomLevel;
        this.addSemanticData = addSemanticData;
    }

    private double cameraZoomScale = 1d;
    private final boolean mainDiagram;
    private final boolean outline;
    private final boolean export;
    private final boolean printout;
    private final boolean applyCameraZoomLevel;
    private final boolean addSemanticData;

    private final Stack<Double> cameraScales = new Stack<Double>();

    /**
     * Provides the current diagram zoom factor as determined by the active {@link KlighdMainCamera}'s
     * view {@link java.awt.geom.AffineTransform transform}, adjusted by the drawn parent
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode KNodeNode}'s scale settings.
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
     * @return <code>true</code> if the diagram zoom level determined by the
     *         {@link KlighdMainCamera}'s view transform shall be used while evaluating the
     *         visibility of the particular diagram elements and diagram element figure parts,
     *         <code>false</code> if a diagram zoom level of <code>1.0<code> shall be assumed
     */
    public boolean isApplyCameraZoomLevel() {
        return applyCameraZoomLevel;
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
     * @return the employed {@link KlighdSWTGraphics} (delegates to {@link #getGraphics()} and casts
     *         accordingly)
     */
    public KlighdSWTGraphics getKlighdGraphics() {
        return (KlighdSWTGraphics) super.getGraphics();
    }


    @Override
    public void pushCamera(final PCamera aCamera) {
        super.pushCamera(aCamera);

        if (isApplyCameraZoomLevel()) {
            if (aCamera instanceof KlighdMainCamera) {
                cameraZoomScale = aCamera.getViewTransformReference().getScaleX();
            } else if (aCamera instanceof KlighdMagnificationLensCamera) {
                cameraZoomScale = aCamera.getViewTransformReference().getScaleX();
            }
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
     * @return the employed top camera
     */
    public PCamera getTopCamera() {
        return (PCamera) cameraStack.get(0);
    }

    /**
     * {@inheritDoc}<br>
     * This specialization suppresses the original clipping behavior as we don't need it or event
     * don't want to have it. Thus, this method <b>does nothing</b>.
     */
    @Override
    public void pushClip(final Shape clip) {
        // don't change the clip
    }

    /**
     * {@inheritDoc}<br>
     * This specialization suppresses the original clipping behavior as we don't need it or event
     * don't want to have it. Thus, this method <b>does nothing</b>.
     */
    @Override
    public void popClip(final Shape clip) {
        // don't change the clip
    }

    /**
     * Provides clipping if actually required, e.g. while drawing the
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMagnificationLensCamera
     * KlighdMagnificationLensCamera}, delegates to {@link PPaintContext#pushClip(Shape)}.
     *
     * @param clip determines the shape to set the clip to
     */
    public void forcedPushClip(final Shape clip) {
        super.pushClip(clip);
    }

    /**
     * Resets clipping to former state if a clip was defined by means of
     * {@link #forcedPushClip(Shape)} earlier, delegates to {@link PPaintContext#popClip(Shape)}.
     */
    public void forcedPopClip() {
        super.popClip(null);
    }

    /**
     * Saves the (adjusted) {@link #cameraZoomScale} and applies <code>scale</code> to the current
     * value. Is intended to be called from
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KChildAreaNode KChildAreaNode} only!
     *
     * @param scale
     *            the scale factor to be applied to the current {@link #cameraZoomScale}
     */
    public void pushNodeScale(final double scale) {
        cameraScales.push(cameraZoomScale);
        cameraZoomScale *= scale;
    }

    /**
     * Restores the previous logged (adjusted) camera zoom scale.<br>
     * Is intended to be called from
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KChildAreaNode KChildAreaNode} only!
     */
    public void popNodeScale() {
        cameraZoomScale = cameraScales.pop();
    }
}