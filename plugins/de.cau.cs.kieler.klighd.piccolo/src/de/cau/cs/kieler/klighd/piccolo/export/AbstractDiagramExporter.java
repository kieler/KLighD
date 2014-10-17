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

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.google.common.base.Function;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klighd.DiagramExportConfig;
import de.cau.cs.kieler.klighd.IExportBranding;
import de.cau.cs.kieler.klighd.IExportBranding.Trim;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.util.Iterables2;
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
     * Singleton {@link AffineTransform} instance denoting the identical transform. Note:
     * Application code must not manipulate it, overriding all the setters and throwing exceptions
     * is just to lengthy for all those operations...
     */
    protected static final AffineTransform IDENTITY = new AffineTransform();


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
    protected final PBounds getExportedBounds(final KlighdMainCamera camera,
            final boolean exportViewport) {
        final PBounds bounds;

        if (exportViewport) {
            bounds = camera.getBounds();
        } else {
            bounds = new PBounds(camera.getDisplayedINode().getExportedBounds());
        }

        return bounds;
    }


    /**
     * Calculates the cumulated (maximal) diagram {@link Trim} that is defined to require for each
     * side the maximum of those trims required by the given {@code exportBrandings} on that
     * particular side.
     *
     * @param exportBrandings
     *            the {@link IExportBranding IExportBrandings} to be applied
     * @param bounds
     *            A
     * @return the desired cumulated {@link Trim}
     */
    protected final Trim getMaximumDiagramTrim(final Iterable<IExportBranding> exportBrandings,
            final Rectangle2D bounds) {
        return getCumulatedTrim(false, false, exportBrandings, bounds);
    }

    /**
     * Calculates the cumulated (maximal) diagram tile {@link Trim} that is defined to require for
     * each side the maximum of those trims required by the given {@code exportBrandings} on that
     * particular side.
     *
     * @param exportBrandings
     *            the {@link IExportBranding IExportBrandings} to be applied
     * @param bounds
     *            A
     * @param fixSizedTiles
     *            if {@code true} the returned {@link Trim} will reduce the area being available
     *            for drawing, otherwise the tile is increased by the provided {@link Trim}
     * @return the desired cumulated {@link Trim}
     */
    protected final Trim getMaximumDiagramTileTrim(final Iterable<IExportBranding> exportBrandings,
            final Rectangle2D bounds, final boolean fixSizedTiles) {
        return getCumulatedTrim(true, fixSizedTiles, exportBrandings, bounds);
    }

    private Trim getCumulatedTrim(final boolean tileTrim, final boolean fixSizedTiles,
            final Iterable<IExportBranding> exportBrandings, final Rectangle2D bounds) {

        final Trim res =
                Iterables2.fold(exportBrandings, new Function<Pair<Trim, IExportBranding>, Trim>() {

            public Trim apply(final Pair<Trim, IExportBranding> input) {
                final Trim exportersTrim = tileTrim
                        ? input.getSecond().getDiagramTileTrimm(bounds, fixSizedTiles)
                                : input.getSecond().getDiagramTrim(bounds);

                final Trim result = input.getFirst();

                if (result == null) {
                    return exportersTrim;

                } else if (exportersTrim == null) {
                    return result;

                } else {

                    final float maxLeft = Math.max(result.left, exportersTrim.left);
                    final float maxRight = Math.max(result.right, exportersTrim.right);
                    final float maxTop = Math.max(result.top, exportersTrim.top);
                    final float maxBottom = Math.max(result.bottom, exportersTrim.bottom);

                    if (maxLeft != result.left || maxRight != result.right || maxTop != result.top
                            || maxBottom != result.bottom) {
                        return new Trim(maxLeft, maxRight, maxTop, maxBottom);

                    } else {
                        return result;
                    }
                }
            }
        });

        return res != null ? res : new Trim(0, 0, 0, 0);
    }


    /**
     * Provides a {@link Rectangle} describing the basic clip of diagram tiles based on the actual
     * bounds of the employed {@link org.eclipse.swt.graphics.Drawable Drawable} (
     * {@link org.eclipse.swt.graphics.Image Image} or {@link org.eclipse.swt.graphics.Printer
     * Printer} and the cumulated required tile {@link Trim} being scaled to the size of the
     * employed drawable.
     *
     * @param drawablesBounds
     *            the actual bounds of the employed {@link org.eclipse.swt.graphics.Drawable
     *            Drawable}
     * @param tileTrimScaled
     *            the cumulated required {@link Trim} scaled to {@code drawablesBounds} if necessary
     * @return a {@link Rectangle} describing the unadjusted clip rectangle that is to be applied
     *         without having configured any {@link AffineTransform transform} on the corresponding
     *         graphics
     */
    protected Rectangle getBasicTileClip(final Rectangle drawablesBounds, final Trim tileTrimScaled) {
        final Rectangle2D clip = new Rectangle2D.Double(
                Math.ceil(tileTrimScaled.left),
                Math.ceil(tileTrimScaled.top),
                Math.floor(drawablesBounds.width - tileTrimScaled.getWidth()),
                Math.floor(drawablesBounds.height - tileTrimScaled.getHeight()));
        return clip.getBounds();
    }

    /**
     *
     * @param exportConfig
     *            TODO
     * @param graphics
     *            TODO
     * @param camera
     *            TODO
     * @param drawablesBounds
     *            TODO
     * @param baseTileClip
     *            maybe {@code null}
     */
    protected void drawDiagramTile(final DiagramExportConfig exportConfig,
            final KlighdSWTGraphics graphics, final KlighdMainCamera camera,
            final Rectangle drawablesBounds, final Rectangle baseTileClip) {
        drawDiagramTile(exportConfig, graphics, camera, drawablesBounds, baseTileClip, 1d, null);
    }

    /**
     *
     * @param exportConfig TODO
     * @param graphics TODO
     * @param camera TODO
     * @param drawablesBounds TODO
     * @param baseTileClip maybe {@code null}
     * @param tileScale TODO
     * @param centeringOffset TODO
     */
    protected void drawDiagramTile(final DiagramExportConfig exportConfig,
            final KlighdSWTGraphics graphics, final KlighdMainCamera camera,
            final Rectangle drawablesBounds, final Rectangle baseTileClip,
            final double tileScale, final Point2D centeringOffset) {

        final Trim tileTrimScaled = exportConfig.tileTrim.getScaled((float) tileScale);

        // initial clip definition
        final Rectangle tileClip = baseTileClip != null
                ? baseTileClip : getBasicTileClip(drawablesBounds, tileTrimScaled);
        graphics.setClip(tileClip);

        // apply translation for tiled export if necessary
        final AffineTransform tileTransform = AffineTransform.getTranslateInstance(
                -exportConfig.column * drawablesBounds.getWidth(),
                -exportConfig.row * drawablesBounds.getHeight());

        if (centeringOffset != null) {
            tileTransform.translate(centeringOffset.getX(), centeringOffset.getY());
        }

        tileTransform.translate(
                (exportConfig.column + 1) * tileTrimScaled.left
                        + exportConfig.column * tileTrimScaled.right,
                (exportConfig.row + 1) * tileTrimScaled.top
                        + exportConfig.row * tileTrimScaled.bottom);

        final AffineTransform tileScaling = AffineTransform.getScaleInstance(tileScale, tileScale);
        tileTransform.concatenate(tileScaling);

        // apply the scale factor to the employed graphics object
        //  by means of a corresponding affine transform
        tileTransform.scale(exportConfig.diagramScale, exportConfig.diagramScale);

        drawDiagram(exportConfig, graphics, camera, tileTransform, tileScaling, tileClip);
    }


    /**
     * Performs the actual diagram rendering work by means of the employed {@link KlighdSWTGraphics}
     * .<br>
     * <br>
     * This method is supposed to be used by all registered
     * {@link de.cau.cs.kieler.klighd.IDiagramExporter IDiagramExporters} in order to achieve
     * consistent exporting behavior.
     *
     * @param config
     *            an {@link DiagramExportConfig} record comprising all information required for
     *            proper export, must not be {@code null}
     * @param graphics
     *            the {@link KlighdSWTGraphics} to 'draw' the diagram on
     * @param camera
     *            the {@link KlighdMainCamera} showing the diagram to be exported
     * @param diagramTransform
     *            TODO
     * @param tileScaling
     *            the scaling of the tile, e.g. for implementing the shrunk print page previews
     * @param tileClip
     *            a {@link Rectangle} describing the basic unadjusted (except for tile scaling) tile
     *            clip to prevent the diagram being drawn into the tile trim
     */
    protected final void drawDiagram(final DiagramExportConfig config,
            final KlighdSWTGraphics graphics, final KlighdMainCamera camera,
            final AffineTransform diagramTransform, final AffineTransform tileScaling,
            final Rectangle tileClip) {

        final Trim diagramTrim = config.diagramTrim;

        final PBounds theBounds;
        if (config.diagramBounds instanceof PBounds) {
            theBounds = (PBounds) config.diagramBounds;

        } else if (config.diagramBounds != null) {
            theBounds = new PBounds(config.diagramBounds);

        } else {
            theBounds = getExportedBounds(camera, config.exportViewport);
        }

        if (config.tileBounds != null) {
            graphics.setTransform(tileScaling);
            graphics.setClip(config.tileBounds);

            for (final IExportBranding branding : config.exportBrandings) {
                branding.drawDiagramTileBackground(graphics, config);
                graphics.setTransform(tileScaling);
            }

            graphics.setTransform(IDENTITY);
            graphics.setClip(tileClip);
            graphics.setTransform(diagramTransform);
        }

        if (diagramTrim != null) {
            graphics.transform(
                    AffineTransform.getTranslateInstance(diagramTrim.left, diagramTrim.top));
        }

        for (final IExportBranding branding : config.exportBrandings) {
            branding.drawDiagramBackground(graphics, config);

            // Restore the transform in case the hooks changed something.
            graphics.setTransform(diagramTransform);
        }

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
        if (config.exportViewport) {
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

        for (final IExportBranding branding : config.exportBrandings) {
            // reset the zero point,
            //  or restore the transform in case 'hook' changed something, respectively
            graphics.setTransform(diagramTransform);
            branding.drawDiagramOverlay(graphics, config);
        }

        if (config.tileBounds != null) {
            graphics.setTransform(tileScaling);
            graphics.setClip(config.tileBounds);

            for (final IExportBranding branding : config.exportBrandings) {
                // Reset the transform in case 'hook' changed something.
                graphics.setTransform(tileScaling);
                branding.drawDiagramTileOverlay(graphics, config);
            }
        }
    }
}
