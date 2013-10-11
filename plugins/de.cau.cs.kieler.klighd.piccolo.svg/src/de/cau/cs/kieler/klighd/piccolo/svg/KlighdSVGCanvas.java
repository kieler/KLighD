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

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.svg.impl.BatikSVGGraphics;
import de.cau.cs.kieler.klighd.piccolo.svg.impl.FreeHEPSVGGraphics;
import de.cau.cs.kieler.klighd.piccolo.svg.impl.VectorGraphicsSVGGraphics;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PComponent;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PUtil;

/**
 * This class can be used to render a {@link KNode} model to an SVG. The piccolo infrastructure is
 * used to create piccolo elements for the respective KGraph model. Using batik's
 * {@link org.apache.batik.svggen.SVGGraphics2D SVGGraphics2D} generator, an SVG is created.
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

    public enum SVGGenerator {
        Batik, VectorGraphics, FreeHEP
    }

    private KlighdAbstractSVGGraphics graphics;

    private PCamera camera;
    private PPaintContext paintContext;

    private SVGGenerator svgGenerator = SVGGenerator.FreeHEP;

    private static final PBounds INITIAL_BOUNDS = new PBounds(0, 0, 800, 600);

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
     *            the initial bounds of this canvas * @param textAsShapes whether text should be
     *            rendered as a shape.
     */
    public KlighdSVGCanvas(final Rectangle2D bounds, final boolean textAsShapes) {
        // create a graphics object
        graphics = createGraphics(textAsShapes, bounds);

        // create a new camera
        camera = PUtil.createBasicScenegraph();
        // camera.setBounds(bounds);
        camera.setComponent(this);
    }

    private static KlighdAbstractSVGGraphics createGraphics(final boolean textAsShapes,
            final Rectangle2D bounds) {

        SVGGenerator svgGen = SVGGenerator.FreeHEP;
       
        // TODO add textAsShapes and bounds to all graphics!
        switch (svgGen) {
        case VectorGraphics:
            return new VectorGraphicsSVGGraphics();
        case FreeHEP:
            return new FreeHEPSVGGraphics(bounds);
        default:
            // batik
            return new BatikSVGGraphics(textAsShapes);
        }
    }

    /**
     * @return the rendered SVG.
     */
    public String render() {

        // System.out.println(camera.getC);
        System.out.println(camera.getFullBounds());
        System.out.println(camera.getGlobalFullBounds());
        System.out.println(camera.getRoot().getFullBounds());
        camera.setBounds(camera.getRoot().getFullBounds());

        graphics = createGraphics(false, camera.getRoot().getFullBounds());
        // initially clear the graphics object
        graphics.clear();

        // set up the paint context
        paintContext = new PPaintContext(graphics);

        // the following clip sit is required in order to get rid of the one set in
        // the constructor call above, which lets Inkscape & browsers go crazy
        graphics.setClip(camera.getBounds());
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
    public PCamera getCamera() {
        return camera;
    }

    /**
     * @param bounds
     *            new bounds forwarded to the camera.
     */
    public void setBounds(final Rectangle2D bounds) {
        camera.setBounds(bounds);
    }

    /*---------------------------------------------------------------------
     * Static convenient methods for rendering existing models and cameras.
     */

    /**
     * Render this offscreen canvas to the specified graphics.
     * 
     * @param model
     *            the {@link KNode} model to be rendered.
     * @return the rendered svg.
     */
    public static String render(final KNode model) {

        if (model == null) {
            throw new IllegalArgumentException("model must not be null");
        }

        // infer the bounds from the size of the top most model element
        KShapeLayout shape = model.getData(KShapeLayout.class);
        PBounds bounds = new PBounds(0, 0, shape.getWidth(), shape.getHeight());

        // init a new camera
        PCamera camera = PUtil.createBasicScenegraph();
        camera.setBounds((PBounds) bounds.clone());

        // create the piccolo elements for the kgraph model
        DiagramController controller = new DiagramController(model, camera.getLayer(0), true);
        controller.initialize();

        // set up the paint context
        KlighdAbstractSVGGraphics graphics = createGraphics(false, bounds);
        final PPaintContext paintContext = new PPaintContext(graphics);

        // the following clip sit is required in order to get rid of the one set in
        // the constructor call above, which lets Inkscape & browsers go crazy
        graphics.setClip(camera.getBounds());
        paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);

        // perform the painting
        camera.fullPaint(paintContext);

        // return the created svg
        String svg = graphics.getSVG();
        return svg;
    }

    /*---------------------------------------------------------------------
     * Render from a PCamera.
     */

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

        Rectangle2D bounds = null;
        if (viewPort) {
            bounds = camera.getBounds();
        } else {
            bounds = camera.getRoot().getFullBounds();
        }

        // set up the paint context
        System.out.println(bounds);
        System.out.println(camera.getFullBounds());
        System.out.println(camera.getGlobalFullBounds());
        System.out.println(camera.getRoot().getFullBounds());
        KlighdAbstractSVGGraphics graphics = createGraphics(textAsShapes, bounds);

        // the following clip sit is required in order to get rid of the one set in
        // the constructor call above, which lets Inkscape & browsers go crazy
        if (viewPort) {
            graphics.setClip(camera.getBounds());
        }

        final PPaintContext paintContext = new PPaintContext(graphics);
        paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);

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
     */
    public static void render(final PCamera camera, final boolean viewport,
            final boolean textAsShapes, final OutputStream stream) {
        String svg = render(camera, viewport, textAsShapes);
        try {
            stream.write(svg.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // HACKING for testing
    // CHECKSTYLEOFF Javadoc
    public static void staticRenderStream(final PCamera camera, final Boolean viewport,
            final Boolean textAsShapes, final OutputStream stream) {
        KlighdSVGCanvas.render(camera, viewport, textAsShapes, stream);
    }

    public static String staticRender(final KNode aModel) {
        return KlighdSVGCanvas.render(aModel);
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
