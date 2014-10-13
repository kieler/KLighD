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
import java.awt.geom.Rectangle2D;
import java.util.Collections;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PAffineTransform;
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
     *            otherwise the closure bounds of the camera's displayed layer are returned
     * @return a new {@link PBounds} instance containing the requested bounds data
     */
    protected PBounds getExportedBounds(final KlighdMainCamera camera, final boolean exportViewport) {
        final PBounds bounds;

        if (exportViewport) {
            bounds = camera.getBounds();
        } else {
            bounds = new PBounds(camera.getDisplayedINode().getExportedBounds());
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
     * double, boolean, Iterable)}.
     * The diagram scale is set to 100%, exporting of semantic data is deactivated.
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
     */
    protected void drawDiagram(final KlighdMainCamera camera, final boolean exportViewport,
            final KlighdSWTGraphics graphics, final PBounds bounds) {
        drawDiagram(camera, exportViewport, graphics, bounds, 1, false,
                Collections.<IExportHook>emptyList());
    }


    /**
     * Performs the actual diagram rendering work by means of the employed {@link KlighdSWTGraphics}
     * by delegating to {@link #drawDiagram(KlighdMainCamera, boolean, KlighdSWTGraphics, PBounds,
     * double, boolean, Iterable)}. Exporting of semantic data is deactivated.
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
     */
    protected void drawDiagram(final KlighdMainCamera camera, final boolean exportViewport,
            final KlighdSWTGraphics graphics, final PBounds bounds, final double scale) {
        drawDiagram(camera, exportViewport, graphics, bounds, scale, false,
                Collections.<IExportHook>emptyList());
    }


    /**
     * Performs the actual diagram rendering work by means of the employed {@link KlighdSWTGraphics}
     * by delegating to {@link #drawDiagram(KlighdMainCamera, boolean, KlighdSWTGraphics, PBounds,
     * double, boolean, Iterable)}. The diagram scale is set to 100%.
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
        drawDiagram(camera, exportViewport, graphics, bounds, 1, exportSemanticData,
                Collections.<IExportHook>emptyList());
    }


    /**
     * Performs the actual diagram rendering work by means of the employed {@link KlighdSWTGraphics}
     * by delegating to {@link #drawDiagram(KlighdMainCamera, boolean, KlighdSWTGraphics, PBounds,
     * double, boolean, Iterable)}. Exporting of semantic data is deactivated.
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
     *            an {@link Iterable} of {@link IExportHook IExportHooks} to apply
     */
    protected void drawDiagram(final KlighdMainCamera camera, final boolean exportViewport,
            final KlighdSWTGraphics graphics, final PBounds bounds, final double scale,
            final Iterable<IExportHook> hooks) {
        drawDiagram(camera, exportViewport, graphics, bounds, scale, false, hooks);
    }


    /**
     * Performs the actual diagram rendering work by means of the employed {@link KlighdSWTGraphics}
     * by delegating to {@link #drawDiagram(KlighdMainCamera, boolean, KlighdSWTGraphics, PBounds,
     * double, boolean, Iterable)}. The diagram scale is set to 100%.
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
     * @param exportSemanticData
     *            if <code>true</code> semantic data that are attached to the diagram's view model
     *            are exported to the image (if implemented by the employed
     *            {@link KlighdSWTGraphics})
     * @param hooks
     *            an {@link Iterable} of {@link IExportHook IExportHooks} to apply
     */
    protected void drawDiagram(final KlighdMainCamera camera, final boolean exportViewport,
            final KlighdSWTGraphics graphics, final PBounds bounds,
            final boolean exportSemanticData, final Iterable<IExportHook> hooks) {
        drawDiagram(camera, exportViewport, graphics, bounds, 1, exportSemanticData, hooks);
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
     * @param exportSemanticData
     *            if <code>true</code> semantic data that are attached to the diagram's view model
     *            are exported to the image (if implemented by the employed {@link KlighdSWTGraphics})
     * @param hooks
     *            an {@link Iterable} of {@link IExportHook IExportHooks} to apply
     */
    protected void drawDiagram(final KlighdMainCamera camera, final boolean exportViewport,
            final KlighdSWTGraphics graphics, final PBounds bounds, final double scale,
            final boolean exportSemanticData, final Iterable<IExportHook> hooks) {

        final PBounds theBounds;
        if (bounds != null) {
            theBounds = bounds;
        } else {
            theBounds = getExportedBounds(camera, exportViewport);
        }

        final PBounds preBounds = new PBounds(0, 0, theBounds.width, theBounds.height);

        // explicitly initialize the white background (required especially for SVG exports)
        graphics.setFillColor(KlighdConstants.WHITE);
        graphics.fill(preBounds);

        // Save the transform to restore it after hook call.
        final PAffineTransform transform = new PAffineTransform(graphics.getTransform());
        // VERIFYME transform.transform(preBounds, preBounds);

        // transformation applied by the pre draw hooks
        final AffineTransform preTransform = new AffineTransform();

        for (final IExportHook hook : hooks) {
            preTransform.concatenate(hook.drawPreDiagram(graphics, preBounds));
            // Restore the transform in case the hooks changed something.
            graphics.setTransform(transform);
        }

        // apply the pre hook transformation
        graphics.transform(preTransform);

        // adjust the zero reference point corresponding to the exported bounds' reference point
        graphics.transform(
                AffineTransform.getTranslateInstance(-theBounds.x, -theBounds.y));

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
            @SuppressWarnings("unchecked") // Piccolo2D classes do not use type parameters :-(
            final Iterable<PLayer> layersReference = camera.getLayersReference();
            for (final PLayer layer : layersReference) {
                layer.fullPaint(paintContext);
            }
        }

        for (final IExportHook hook : hooks) {
            // reset the zero point,
            //  or restore the transform in case 'hook' changed something, respectively
            graphics.setTransform(transform);
            hook.drawPostDiagram(graphics, preBounds);
        }

        final Rectangle2D imageSize = getBufferImageSize();
        if (imageSize != null) {

            final AffineTransform identity = new AffineTransform();
            graphics.setTransform(identity);
            graphics.setClip(imageSize);

            for (final IExportHook hook : hooks) {
                // Reset the transform in case 'hooks' changed something.
                graphics.setTransform(identity);
                hook.drawPostDiagramTile(graphics, imageSize);
            }
        }
    }

    /**
     * Method hook providing the size of the employed buffer image(s), which is required for
     * properly implementing {@link IExportHook#drawPostDiagramTile(KlighdSWTGraphics, Rectangle2D)}.
     *
     * @return the buffer image size of {@code null} if no buffer images are used
     */
    protected Rectangle2D getBufferImageSize() {
        return null;
    }
}
