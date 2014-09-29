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
package de.cau.cs.kieler.klighd.piccolo.export;

import java.awt.geom.AffineTransform;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * Abstract diagram exporter providing the common methods
 * {@link #getExportedBounds(KlighdMainCamera, boolean)} and
 * {@link #drawDiagram(KlighdMainCamera, boolean, KlighdSWTGraphics, PBounds)} to be re-used in
 * concrete implementation of {@link de.cau.cs.kieler.klighd.IDiagramExporter IDiagramExporter} and
 * {@link de.cau.cs.kieler.klighd.IOffscreenRenderer IOffscreenRenderer}, in order to achieve
 * consistent behavior amongst all those implementations.
 * 
 * @author chsch
 * @author csp
 */
public abstract class AbstractDiagramExporter {

    /**
     * Helper method computing the actual unadjusted bounds of the content of the diagram to be
     * exported.
     * 
     * @param camera
     *            the {@link KlighdMainCamera} whose depicted content is to be exported
     * @param exportViewport
     *            if <code>true</code> the camera's view port is considered,
     *            otherwise the closure bounds of the camera's displayed layer is considered 
     * @return a new {@link PBounds} instance containing the requested bounds data
     */
    protected PBounds getExportedBounds(final KlighdMainCamera camera, final boolean exportViewport) {
        final PBounds bounds;

        if (exportViewport) {
            bounds = camera.getBounds();

        } else {
            final PLayer displayedLayer = camera.getDisplayedLayer();

            if (displayedLayer.getParent() == camera.getRoot()) {
                // in case the displayed layer is the topNode, invalidate its full bounds
                //  as they are not required to be up to date for the interactive on screen rendering
                displayedLayer.invalidateFullBounds();
                bounds = displayedLayer.getFullBounds();

            } else {
                bounds = displayedLayer.getUnionOfChildrenBounds(null);
            }
        }
        return bounds;
    }


    /**
     * Factory method hook for injecting a {@link KlighdPaintContext} tailored to the type of
     * diagram rendering/export.<br>
     * <br>
     * May be overriden by concrete implementations, base implementation returns a
     * {@link KlighdPaintContext} tailored to diagram image exports.
     * 
     * @param graphics
     *            the {@link KlighdSWTGraphics} to draw on
     * @return a {@link KlighdPaintContext} tailored to the type of diagram rendering/export.
     */
    protected KlighdPaintContext createPaintContext(final KlighdSWTGraphics graphics) {
        return KlighdPaintContext.createExportDiagramPaintContext(graphics);
    }


    /**
     * Performs the actual diagram rendering work by means of the employed {@link KlighdSWTGraphics}
     * by delegating to {@link #drawDiagram(KlighdMainCamera, boolean, KlighdSWTGraphics, PBounds,
     * double, Collection, boolean)}.
     * 
     * @param camera
     *            the {@link KlighdMainCamera} showing the diagram to be exported
     * @param exportViewport
     *            if <code>true</code> the camera's view port is exported, otherwise the camera's
     *            displayed layer is exported
     * @param graphics
     *            the graphics object to 'draw' the diagram on
     * @param bounds
     *            the bounds of the diagram part to be exported, required for determining the main
     *            clip and
     *            the background coloring; may be <code>null</code>,
     *            {@link #getExportedBounds(KlighdMainCamera, boolean)} will be called in that case
     * @param exportSemanticData
     *            if <code>true</code> semantic data that are attached to the diagram's view model
     *            are exported to the image (if implemented by the employed {@link KlighdSWTGraphics}) 
     */
    protected void drawDiagram(final KlighdMainCamera camera, final boolean exportViewport,
            final KlighdSWTGraphics graphics, final PBounds bounds, final boolean exportSemanticData) {
        drawDiagram(camera, exportViewport, graphics, bounds, 1,
                Collections.<IExportHook>emptyList(), exportSemanticData);
    }


    /**
     * Performs the actual diagram rendering work by means of the employed {@link KlighdSWTGraphics}
     * by delegating to {@link #drawDiagram(KlighdMainCamera, boolean, KlighdSWTGraphics, PBounds,
     * double, Collection, boolean)}.
     * 
     * @param camera
     *            the {@link KlighdMainCamera} showing the diagram to be exported
     * @param exportViewport
     *            if <code>true</code> the camera's view port is exported, otherwise the camera's
     *            displayed layer is exported
     * @param graphics
     *            the graphics object to 'draw' the diagram on
     * @param bounds
     *            the bounds of the diagram part to be exported, required for determining the main
     *            clip and the background coloring; may be <code>null</code>,
     *            {@link #getExportedBounds(KlighdMainCamera, boolean)} will be called in that case
     * @param scale
     *            the scaling factor
     * @param exportSemanticData
     *            if <code>true</code> semantic data that are attached to the diagram's view model are
     *            exported to the image (if implemented by the employed {@link KlighdSWTGraphics})
     */
    protected void drawDiagram(final KlighdMainCamera camera, final boolean exportViewport,
            final KlighdSWTGraphics graphics, final PBounds bounds, final double scale,
            final boolean exportSemanticData) {
        drawDiagram(camera, exportViewport, graphics, bounds, scale,
                Collections.<IExportHook>emptyList(), exportSemanticData);
    }


    /**
     * Performs the actual diagram rendering work by means of the employed {@link KlighdSWTGraphics}
     * by delegating to {@link #drawDiagram(KlighdMainCamera, boolean, KlighdSWTGraphics, PBounds,
     * double, Collection, boolean)}.
     * 
     * @param camera
     *            the {@link KlighdMainCamera} showing the diagram to be exported
     * @param exportViewport
     *            if <code>true</code> the camera's view port is exported, otherwise the camera's
     *            displayed layer is exported
     * @param graphics
     *            the graphics object to 'draw' the diagram on
     * @param bounds
     *            the bounds of the diagram part to be exported, required for determining the main
     *            clip and the background coloring; may be <code>null</code>,
     *            {@link #getExportedBounds(KlighdMainCamera, boolean)} will be called in that case
     * @param hooks
     *            a {@link Collection} of {@link IExportHook IExportHooks} to apply
     * @param exportSemanticData
     *            if <code>true</code> semantic data that are attached to the diagram's view model
     *            are exported to the image (if implemented by the employed
     *            {@link KlighdSWTGraphics})
     */
    protected void drawDiagram(final KlighdMainCamera camera, final boolean exportViewport,
            final KlighdSWTGraphics graphics, final PBounds bounds,
            final Collection<IExportHook> hooks, final boolean exportSemanticData) {
        drawDiagram(camera, exportViewport, graphics, bounds, 1, hooks, exportSemanticData);
    }


    /**
     * Performs the actual diagram rendering work by means of the employed {@link KlighdSWTGraphics}.<br>
     * <br>
     * This method is supposed to be used by all registered
     * {@link de.cau.cs.kieler.klighd.IDiagramExporter IDiagramExporters} in order to achieve
     * consistent exporting behavior.
     * 
     * @param camera
     *            the {@link KlighdMainCamera} showing the diagram to be exported
     * @param exportViewport
     *            if <code>true</code> the camera's view port is exported, otherwise the camera's
     *            displayed layer is exported
     * @param graphics
     *            the graphics object to 'draw' the diagram on
     * @param bounds
     *            the bounds of the diagram part to be exported, required for determining the main
     *            clip and
     *            the background coloring; may be <code>null</code>,
     *            {@link #getExportedBounds(KlighdMainCamera, boolean)} will be called in that case
     * @param scale
     *            the scaling factor
     * @param hooks
     *            a {@link Collection} of {@link IExportHook IExportHooks} to apply
     * @param exportSemanticData
     *            if <code>true</code> semantic data that are attached to the diagram's view model
     *            are exported to the image (if implemented by the employed {@link KlighdSWTGraphics}) 
     */
    protected void drawDiagram(final KlighdMainCamera camera, final boolean exportViewport,
            final KlighdSWTGraphics graphics, final PBounds bounds, final double scale,
            final Collection<IExportHook> hooks, final boolean exportSemanticData) {

        final PBounds theBounds;
        if (bounds != null) {
            theBounds = bounds;
        } else {
            theBounds = getExportedBounds(camera, exportViewport);
        }

        final PBounds preBounds = new PBounds(0, 0, theBounds.width, theBounds.height);

        // The global clip setting is required as (in PPaintContext) a default one will be set!
        // This however will let various browsers go crazy and don't show anything!
        graphics.setClip(preBounds);

        // explicitly initialize the white background (required especially for SVG exports)
        graphics.setFillColor(KlighdConstants.WHITE);
        graphics.fill(preBounds);
        
        
        // Save the transform to restore it after hook call.
        final AffineTransform transform = graphics.getTransform();

        // transformation applied by the pre draw hooks
        final AffineTransform preTransform = new AffineTransform();
        
        for (final IExportHook hook : hooks) {
            preTransform.concatenate(hook.drawPreDiagram((KlighdSWTGraphicsEx) graphics, preBounds));
            // Restore the transform in case the hooks changed something.
            graphics.setTransform(transform);
        }
        
        // apply the pre hook transformation
        graphics.transform(preTransform);

        // adjust the zero reference point
        graphics.transform(AffineTransform.getTranslateInstance(-theBounds.x,
                -theBounds.y));
        
        graphics.transform(AffineTransform.getScaleInstance(scale, scale));

        final KlighdPaintContext paintContext = createPaintContext(graphics);

        // the following setting contradict the defaults in BatikSVGGraphics
        // which leads to a blown-up svg file with a huge amount of repeated local style settings
        // therefore, here and in KlighdAbstractSVGGraphics#setRenderingHint(Key, Object)
        // the propagation of such RenderingHints has been suppressed

        // paintContext.setRenderQuality(PPaintContext.LOW_QUALITY_RENDERING);

        final PLayer exportedLayer = camera.getDisplayedLayer();

        // perform the painting
        if (exportViewport) {
            // only render the current viewport
            camera.fullPaint(paintContext);

        } else if (exportedLayer != null) {
            paintContext.pushCamera(camera);
            paintContext.pushTransform(exportedLayer.getInverseTransform());

            exportedLayer.fullPaint(paintContext);

        } else {
            // the fallback case, should not happen in context of KLighD
            @SuppressWarnings("unchecked")
            final List<PLayer> layersReference = camera.getLayersReference();
            for (final PLayer layer : layersReference) {
                layer.fullPaint(paintContext);
            }
        }

        // reset the zero point 
        graphics.setTransform(transform);
        
        for (final IExportHook hook : hooks) {
            hook.drawPostDiagram((KlighdSWTGraphicsEx) graphics, preBounds);
            // Restore the transform in case the hooks changed something.
            graphics.setTransform(transform);
        }
    }
}
