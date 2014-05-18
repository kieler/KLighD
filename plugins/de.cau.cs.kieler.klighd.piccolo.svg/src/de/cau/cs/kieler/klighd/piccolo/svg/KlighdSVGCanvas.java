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
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.OutputStream;

import com.google.common.base.Strings;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
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

    /** The default svg generator to use. */
    public static final String DEFAULT_GENERATOR = "de.cau.cs.kieler.klighd.piccolo.svg.batik";

    private static final PBounds INITIAL_BOUNDS = new PBounds(0, 0, 800, 600);

    private final String generatorId;
    private final boolean textAsShapes;
    private final boolean embedFonts;

    /**
     * Constructor.
     */
    public KlighdSVGCanvas() {
        this(INITIAL_BOUNDS, null, false, false);
    }

    /**
     * Constructor.
     * 
     * @param generator
     *            the SVG to be employed, maybe <code>null</code>, should be configuration if
     *            multiple generators are available
     */
    public KlighdSVGCanvas(final String generator) {
        this(INITIAL_BOUNDS, generator, false, false);
    }

    /**
     * Constructor.
     * 
     * @param generator
     *            the SVG to be employed, maybe <code>null</code>, should be configuration if
     *            multiple generators are available
     * @param textAsShapes
     *            whether text should be rendered as a shape.
     */
    public KlighdSVGCanvas(final String generator, final boolean textAsShapes) {
        this(INITIAL_BOUNDS, generator, textAsShapes, false);
    }

    /**
     * Constructor.
     * 
     * @param generator
     *            the SVG to be employed, maybe <code>null</code>, should be configuration if
     *            multiple generators are available
     * @param textAsShapes
     *            whether text should be rendered as a shape.
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     */
    public KlighdSVGCanvas(final String generator, final boolean textAsShapes,
            final boolean embedFonts) {
        this(INITIAL_BOUNDS, generator, textAsShapes, embedFonts);
    }

    /**
     * Constructor.
     * 
     * @param bounds
     *            the initial bounds of this canvas
     * @param generator
     *            the SVG to be employed, maybe <code>null</code>, should be configuration if
     *            multiple generators are available
     * @param textAsShapes
     *            whether text should be rendered as a shape.
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     */
    public KlighdSVGCanvas(final Rectangle2D bounds, final String generator,
            final boolean textAsShapes, final boolean embedFonts) {
        this(new KlighdMainCamera(), bounds, generator, textAsShapes, embedFonts);
        
        // the basic PRoot is sufficient as this canvas doesn't rely on any SWT stuff
        final PRoot root = new PRoot();
        root.addChild(camera);
    }
    
    /**
     * Constructor.
     * 
     * @param camera
     *            the {@link KlighdMainCamera} to be employed, may not be <code>null</code>
     * @param bounds
     *            the initial bounds of this canvas
     * @param generator
     *            the SVG to be employed, maybe <code>null</code>, should be configuration if
     *            multiple generators are available
     * @param textAsShapes
     *            whether text should be rendered as a shape.
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     */
    public KlighdSVGCanvas(final KlighdMainCamera camera, final Rectangle2D bounds,
            final String generator, final boolean textAsShapes, final boolean embedFonts) {
        if (Strings.isNullOrEmpty(generator)) {
            throw new IllegalArgumentException(
                    "KLighDSVGCanvas constuctor: provided generator id value of '" + generator
                            + "' is illegal!");
        }

        this.generatorId = generator;
        this.textAsShapes = textAsShapes;
        this.embedFonts = embedFonts;
        this.camera = camera;

        // camera.setComponent(this);
    }


    /**
     * @return the camera
     */
    public KlighdMainCamera getCamera() {
        return camera;
    }

//    /**
//     * Adjusts the diagram bounds based on with and height of the provided {@link KNode}.<br>
//     * Must be called before {@link #render()}.
//     * 
//     * @param node
//     *            the node to take width and height from
//     * @return <code>this</code> {@link KlighdSVGCanvas} for convenience.
//     */
//    public KlighdSVGCanvas setDiagramBounds(final KNode node) {
//        final KShapeLayout bounds = node.getData(KShapeLayout.class);
//        
//        this.camera.setBounds(0, 0, bounds.getWidth(), bounds.getHeight());
//        
//        return this;
//    }
//
    /**
     * @param output
     *            output
     * @throws IOException
     *             a
     */
    public void render(final OutputStream output) throws IOException {

        if (camera == null) {
            return;
        }
        
        final PLayer renderedLayer = camera.getDisplayedLayer();
        renderedLayer.invalidateFullBounds();
        
        final PBounds theBounds = renderedLayer.getUnionOfChildrenBounds(null);

        // create a new graphics object
        final KlighdAbstractSVGGraphics graphics =
                SVGGeneratorManager.createGraphics(generatorId, theBounds, textAsShapes, embedFonts);

        // adjust the zero reference point
        graphics.transform(AffineTransform.getTranslateInstance(-theBounds.getX(), -theBounds.getY()));

        // the following clip set is required in order to get rid of the one set in
        // the constructor call above, which lets Inkscape & browsers go crazy
        graphics.setClip(theBounds);

        // explicitly initialize the white background (required especially for SVG exports)  
        graphics.setFillColor(KlighdConstants.WHITE);
        graphics.fill(theBounds);

        // set up the paint context
        final PPaintContext paintContext = new PPaintContext(graphics);

        // paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);

        paintContext.pushCamera(camera);
        paintContext.pushTransform(renderedLayer.getInverseTransform());

        renderedLayer.fullPaint(paintContext);

        // return the created svg
        graphics.stream(output);
    }

    /*---------------------------------------------------------------------
     * Render from a PCamera.
     */

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
