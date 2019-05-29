/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.export;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.cau.cs.kieler.klighd.DiagramExportConfig;
import de.cau.cs.kieler.klighd.IExportBranding;
import de.cau.cs.kieler.klighd.IExportBranding.Trim;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * Generic SVG {@link KlighdCanvasExporter}. It is able to export SVGs by means of all SVG
 * generators registered via the {@link de.cau.cs.kieler.klighd.piccolo.svg.SVGGeneratorManager
 * SVGGeneratorManager#EXTP_ID_SVGGENERATORS} extension point.
 *
 * In order to work, the 'subFormat' ID of a registered exporter has to match the specified ID in
 * the 'svgGenerators' extension point.
 *
 * @author uru
 * @author chsch
 */
public class SVGExporter extends KlighdCanvasExporter {

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus export(final KlighdCanvas canvas, final ExportData data) {
        return export(canvas.getCamera(), data);
    }

    /**
     * Exports the diagram depicted by the given <code>camera</code>.
     *
     * @param camera
     *            the camera representing the diagram
     * @param data
     *            the specified export info
     *
     * @return {@link org.eclipse.core.runtime.Status#OK_STATUS Status#OK_STATUS} if the diagram
     *         export went successfully, an {@link IStatus} providing information on the failure
     *         otherwise.
     * @see KlighdCanvasExporter#export(KlighdCanvas, ExportData)
     */
    public IStatus export(final KlighdMainCamera camera, final ExportData data) {

        final Iterable<IExportBranding> brandings =
                KlighdDataManager.getExportBrandingByFormat(data.format(), data.viewContext());

        // ... an determine the bounds of the diagram to be exported
        final PBounds bounds = this.getExportedBounds(camera, data.cameraViewport());

        final Trim diagramTrim = getMaximumDiagramTrim(brandings, bounds);
        final Trim diagramTileTrim = getMaximumDiagramTileTrim(brandings);

        final PBounds extendedBounds =
                new PBounds(0, 0, bounds.width + diagramTrim.getWidth() + diagramTileTrim.getWidth(),
                        bounds.height  + diagramTrim.getHeight() + diagramTileTrim.getHeight());

        final Dimension tileBounds = extendedBounds.getBounds().getSize();

        final DiagramExportConfig exportConfig =
                new DiagramExportConfig(data.viewContext(), bounds, tileBounds)
                        .setBrandingsAndTrim(brandings, diagramTrim, diagramTileTrim)
                        .setApplyCameraZoomLevel(data.applyCameraZoomLevel())
                        .setExportSemanticData(true);

        // initialize a graphics object that 'collects' all the drawing instructions
        final KlighdAbstractSVGGraphics graphics;
        try {
            graphics = SVGGeneratorManager.createGraphics(data.format(), extendedBounds,
                    data.textAsShapes(), data.embedFonts(), data.description(), data.css(),
                    data.additionalRootData());

        } catch (final IllegalArgumentException e) {
            final String msg = "KLighD SVG export: Failed to load SVG exporter backend.";
            return new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg, e);
        }

        // The global clip setting is required as (in PPaintContext) a default one will be set!
        // This however will let various browsers go crazy and don't show anything!
        //  (in case of an SVG output)
        graphics.setClip(extendedBounds);

        // Check whether the background should be drawn
        if (!data.transparentBackground()) {
            // explicitly initialize the background with the color requested
            graphics.setFillColor(data.backgroundColor());
            graphics.fill(extendedBounds);
        }

        // do the actual diagram drawing work
        drawDiagram(exportConfig, graphics, camera,
                AffineTransform.getTranslateInstance(diagramTileTrim.left, diagramTileTrim.top),
                IDENTITY, getBasicTileClip(tileBounds, diagramTileTrim));

        OutputStream stream = null;
        try {
            // dump out the resulting SVG description via the provided output stream
            stream = data.createOutputStream();
            graphics.stream(stream);
            stream.close();
            return Status.OK_STATUS;

        } catch (final IOException e) {
            String msg = "KLighD SVG export: Failed to write SVG data";
            if (stream != null) {
                msg += " into the provided OutputStream of type "
                        + stream.getClass().getCanonicalName() + KlighdPlugin.LINE_SEPARATOR
                        + " the stream instance is " + stream.toString();
            }
            return new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg, e);
        }
    }
}
