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

import java.awt.Cursor;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PComponent;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * This class can be used to render a {@link de.cau.cs.kieler.core.kgraph.KNode KNode} model to an
 * SVG. The piccolo infrastructure is used to create piccolo elements for the respective KGraph
 * model.
 * 
 * Using an svg generator implementation (e.g., batik's
 * {@link org.apache.batik.svggen.SVGGraphics2D SVGGraphics2D} generator), an SVG is created.
 * 
 * The canvas can either be instantiated and the {@link #render()} method used to retrieve the svg,
 * or one of the static render methods can be used.
 * 
 * Basic usage: <code>
 *  TODO
 * </code>
 * 
 * Inspired by the {@link edu.umd.cs.piccolo.POffscreenCanvas POffscreenCanvas} class.
 * 
 * @author uru
 * 
 */
public class KlighdSVGCanvas implements PComponent {

    private KlighdMainCamera camera;

    private static final PBounds INITIAL_BOUNDS = new PBounds(0, 0, 800, 600);

    private boolean textAsShapes = false;

    /**
     * 
     */
    public KlighdSVGCanvas() {
        this(INITIAL_BOUNDS, false);
    }

    /**
     * @param textAsShapes
     *            whether text should be rendered as a shape.
     */
    public KlighdSVGCanvas(final boolean textAsShapes) {
        this(INITIAL_BOUNDS, textAsShapes);
    }

    /**
     * @param bounds
     *            the initial bounds of this canvas
     * @param textAsShapes
     *            whether text should be rendered as a shape.
     */
    public KlighdSVGCanvas(final Rectangle2D bounds, final boolean textAsShapes) {
        this.textAsShapes = textAsShapes;

        // create a new main camera
        camera = new KlighdMainCamera();
        
        // the basic PRoot is sufficient as this canvas doesn't rely on any SWT stuff
        final PRoot root = new PRoot();
        root.addChild(camera);

        // set the bounds of the camera,
        //  this is the actual size of the camera, not of what it is viewing
        camera.setBounds(bounds);
        camera.setComponent(this);
    }

    private static KlighdAbstractSVGGraphics createGraphics(final boolean textAsShapes,
            final Rectangle2D bounds) {
        return createGraphics(textAsShapes, bounds, DEFAULT_GENERATOR);
    }

    private static KlighdAbstractSVGGraphics createGraphics(final boolean textAsShapes,
            final Rectangle2D bounds, final String svgGen) {

        return SVGGeneratorManager.getInstance().createGraphics(svgGen, bounds, textAsShapes);
    }

    /**
     * @return the rendered SVG.
     */
    public String render() {

        // TODO
        // client side should send its available viewarea!
        // use this to set the camera's bounds.
        // don't touch the cameras bound here (in the canvas context)

        // TODO
        // is this ok in case we do not zoomToFit every time?
        // basically the svg's viewport might have to be bigger
        // but that should be fine? make it the size of the top level node
        // and translate it by the camera bounds?

        // remeber old bounds
        Rectangle2D bounds = camera.getBounds();
        // camera.setBounds(bounds);

        // create a new graphics object
        KlighdAbstractSVGGraphics graphics = createGraphics(textAsShapes, bounds);
        // initially clear the graphics object
        graphics.clear();

        // set up the paint context
        PPaintContext paintContext = new PPaintContext(graphics);

        // the following clip set is required in order to get rid of the one set in
        // the constructor call above, which lets Inkscape & browsers go crazy
        graphics.setClip(bounds);
        paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);

        camera.validateFullPaint();

        // perform the painting
        camera.fullPaint(paintContext);

        // return the created svg
        String svg = graphics.getSVG();
        return svg;
    }

    /**
     * @return the camera
     */
    public KlighdMainCamera getCamera() {
        return camera;
    }

    /*---------------------------------------------------------------------
     * Render from a PCamera.
     */

    /** The default svg generator to use. */
    public static final String DEFAULT_GENERATOR = "de.cau.cs.kieler.klighd.piccolo.svg.batik";

    /**
     * @param camera
     *            the camera to render
     * @return the rendered svg of the camera's viewport, to render the whole svg use the
     *         {@link #render(PCamera, boolean)} method and pass true as second argument.
     */
    public static String render(final PCamera camera) {
        return render(camera, true, false);
    }

    /**
     * @param camera
     *            the camera to render.
     * @param viewPort
     *            whether to render only the camera's viewport or the whole diagram.
     * @param textAsShapes
     *            whether text should be rendered as shapes
     * @return the SVG string.
     */
    public static String render(final PCamera camera, final boolean viewPort,
            final boolean textAsShapes) {
        return render(camera, viewPort, textAsShapes, DEFAULT_GENERATOR);
    }

    /**
     * @param camera
     *            the camera to render.
     * @param viewPort
     *            whether to render only the camera's viewport or the whole diagram.
     * @param textAsShapes
     *            whether text should be rendered as shapes
     * @param generatorId
     *            which svg generator to use
     * @return the SVG string.
     */
    public static String render(final PCamera camera, final boolean viewPort,
            final boolean textAsShapes, final String generatorId) {

        Rectangle2D bounds = null;
        if (viewPort) {
            bounds = camera.getBounds();
        } else {
            // we want the svg to contain all elements, not just the visible area
            bounds = camera.getRoot().getFullBounds();
        }

        // set up the paint context
        KlighdAbstractSVGGraphics graphics = createGraphics(textAsShapes, bounds, generatorId);

        final PPaintContext paintContext = new PPaintContext(graphics);
        paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);

        // remove the global clip!
        // the following clip set is required in order to get rid of the one set in
        // the constructor call above, which lets Inkscape & browsers go crazy (slower)
        graphics.setClip(null);

        // perform the painting
        if (viewPort) {
            // only render the current viewport
            camera.fullPaint(paintContext);
        } else {
            // render the whole diagram
            @SuppressWarnings("unchecked")
            List<PLayer> layersReference = (List<PLayer>) camera.getLayersReference();
            for (PLayer layer : layersReference) {
                layer.fullPaint(paintContext);
            }
        }

        // return the created svg
        String svg = graphics.getSVG();
        return svg;
    }

    /**
     * @param camera
     *            the camera to render.
     * @param viewport
     *            whether to render only the camera's viewport or the whole diagram.
     * @param textAsShapes
     *            whether text should be rendered as shapes
     * @param stream
     *            the stream to which the svg is written.
     * @param generatorId
     *            which svg generator to use
     */
    public static void render(final PCamera camera, final boolean viewport,
            final boolean textAsShapes, final OutputStream stream, final String generatorId) {
        String svg = render(camera, viewport, textAsShapes, generatorId);
        try {
            stream.write(svg.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // HACKING for testing
    // CHECKSTYLEOFF Javadoc

    public static void staticRenderStream(final PCamera camera, final Boolean viewport,
            final Boolean textAsShapes, final OutputStream stream, final String generator) {
        KlighdSVGCanvas.render(camera, viewport, textAsShapes, stream, generator);
    }

    public static String staticRender(final PCamera camera) {
        return KlighdSVGCanvas.render(camera);
    }

    // STOP hacking!

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    public void repaint(final PBounds bounds) {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    public void paintImmediately() {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    public void pushCursor(final Cursor cursor) {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    public void popCursor() {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    public void setInteracting(final boolean interacting) {
    }

}
