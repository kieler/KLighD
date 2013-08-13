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
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PComponent;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PUtil;

/**
 * This class can be used to render a {@link KNode} model to an SVG. The piccolo infrastructure is
 * used to create piccolo elements for the respective KGraph model. Using batik's
 * {@link SVGGraphics2D} generator, an SVG is created.
 * 
 * Basic usage: <code>
 *      KNode model = getLayoutedModel();
 *      KlighdSVGCanvas canvas = new KlighdSVGCanvas(model);
 *      
 *      String svg = canvas.render();
 * </code>
 * 
 * Inspired by the {@link POffscreenCanvas} class.
 * 
 * @author uru
 * 
 */
public class KlighdSVGCanvas implements PComponent {

    private KlighdSimpleSVGGraphicsImpl graphics = new KlighdSimpleSVGGraphicsImpl();

    /**
     * 
     */
    public KlighdSVGCanvas() {
    }

    /**
     * Render this offscreen canvas to the specified graphics.
     * 
     * @param model
     *            the {@link KNode} model to be rendered.
     * @return the rendered svg.
     */
    public String render(final KNode model) {

        if (model == null) {
            throw new IllegalArgumentException("model must not be null");
        }

        // infer the bounds from the size of the top most model element
        KShapeLayout shape = model.getData(KShapeLayout.class);
        PBounds bounds = new PBounds(0, 0, shape.getWidth(), shape.getHeight());

        // init a new camera
        PCamera camera = PUtil.createBasicScenegraph();
        camera.setComponent(this);
        camera.setBounds((PBounds) bounds.clone());

        // TODO require sync?
        // create the piccolo elements for the kgraph model
        DiagramController controller = new DiagramController(model, camera.getLayer(0), true);
        controller.initialize();

        // set up the paint context
        final PPaintContext paintContext = new PPaintContext(graphics);
        paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);

        // perform the painting
        camera.fullPaint(paintContext);

        // return the created svg
        String svg = graphics.getSVG();
        return svg;
    }

    /*
     * ------------------------------------- Rendering from a PCamera
     * -------------------------------------
     */

    /**
     * @param camera
     *            the camera to render
     * @return the rendered svg of the camera's viewport, to render the whole svg use the
     *         {@link #render(PCamera, boolean)} method and pass true as second argument.
     */
    public String render(final PCamera camera) {
        return render(camera, true);
    }

    /**
     * @param camera
     *            the camera to render.
     * @param viewPort
     *            whether to render only the camera's viewport or the whole diagram.
     * @return the SVG string.
     */
    public String render(final PCamera camera, final boolean viewPort) {
        // set up the paint context
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
     * @param stream
     *            the stream to which the svg is written.
     */
    public void render(final PCamera camera, final boolean viewport, final OutputStream stream) {
        String svg = render(camera, viewport);
        try {
            stream.write(svg.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // HACKING for testing
    // CHECKSTYLEOFF Javadoc
    public static void staticRenderStream(final PCamera camera, final Boolean viewport,
            final OutputStream stream) {
        new KlighdSVGCanvas().render(camera, viewport, stream);
    }

    public static String staticRender(final KNode aModel) {
        return new KlighdSVGCanvas().render(aModel);
    }

    public static String staticRender(final PCamera camera) {
        return new KlighdSVGCanvas().render(camera);
    }

    // STOP hacking!

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    @Override
    public void repaint(final PBounds bounds) {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    @Override
    public void paintImmediately() {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    @Override
    public void pushCursor(final Cursor cursor) {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    @Override
    public void popCursor() {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    @Override
    public void setInteracting(final boolean interacting) {
    }

}
