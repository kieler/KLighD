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
package de.cau.cs.kieler.klighd.piccolo.export;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.OutputStream;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.IDiagramExporter;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * Abstract implementation of {@link IDiagramExporter} supporting the export of diagrams depicted by
 * a {@link KlighdCanvas}.<br>
 * <br>
 * Thus, it treats the {@link Control} in
 * {@link #export(OutputStream, Control, boolean, int, boolean, boolean, String)} as a
 * {@link KlighdCanvas} and redirects to
 * {@link #export(OutputStream, KlighdCanvas, boolean, int, boolean, boolean, String)}, which has to
 * be implemented by concrete subclasses.<br>
 * <br>
 * Provides the common methods {@link #getExportedBounds(KlighdMainCamera, boolean)} and
 * {@link #drawDiagram(KlighdMainCamera, boolean, KlighdSWTGraphics, PBounds)} that are to be
 * incorporated by concrete implementation in order to achieve consistent behavior amongst all
 * registered {@link IDiagramExporter IDiagramExporters}.
 * 
 * @author chsch
 */
public abstract class KlighdCanvasExporter implements IDiagramExporter {
    
    /**
     * {@inheritDoc}
     */
    public void export(final OutputStream stream, final Control control,
            final boolean cameraViewport, final int scale, final boolean textAsShapes,
            final boolean embedFonts, final String subFormatId) {
        
        final KlighdCanvas canvas;
        if (control instanceof KlighdCanvas) {
            canvas = (KlighdCanvas) control;
            export(stream, canvas, cameraViewport, scale, textAsShapes, embedFonts, subFormatId);
        } else {
            final String msg = "";
            StatusManager.getManager().handle(
                    new Status(IStatus.WARNING, KlighdPiccoloPlugin.PLUGIN_ID, msg));
            return;
        }
    }

    /**
     * Exports the diagram currently visible on the given {@code canvas} to the passed output
     * stream. If the {@code cameraViewport} flag is set, only the visible area is exported. The
     * {@code scale} value can be used for instance during the export of bitmap graphics to increase
     * the rendering quality by up-scaling the visible area before exporting. Some implementations
     * of the {@link IDiagramExporter} interface might support multiple sub formats of the same parent
     * format, e.g., bmp and png are both bitmap formats.
     * 
     * @param stream
     *            the output stream
     * @param canvas
     *            the visible canvas
     * @param cameraViewport
     *            should only the visible area be exported?
     * @param scale
     *            should the canvas be scaled before exporting
     * @param textAsShapes
     *            whether text in vector graphics should be rendered as shapes
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     * @param subFormatId
     *            an id for a certain subformat
     */
    public abstract void export(OutputStream stream, KlighdCanvas canvas, boolean cameraViewport,
            int scale, boolean textAsShapes, boolean embedFonts, String subFormatId);

    
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
     * Does the actual diagram rendering work by means of the employed {@link KlighdSWTGraphics}.<br>
     * This method is supposed to be used by all registered {@link IDiagramExporter
     * IDiagramExporters} in order to achieve consistent exporting behavior.
     * 
     * @param camera
     *            the {@link KlighdMainCamera} showing the diagram to be exported
     * @param exportViewport
     *            if <code>true</code> the camera's view port is exported, otherwise the camera's
     *            displayed layer is exported
     * @param graphics
     *            the graphics object to 'draw' the diagram on
     * @param bounds
     *            the of the diagram to be exported, required for determining the main clip and the
     *            background coloring; may be <code>null</code>,
     *            {@link #getExportedBounds(KlighdMainCamera, boolean)} will be called in that case
     */
    protected void drawDiagram(final KlighdMainCamera camera, final boolean exportViewport,
            final KlighdSWTGraphics graphics, final PBounds bounds) {

        final PBounds theBounds;
        if (bounds != null) {
            theBounds = bounds; 
        } else {
            theBounds = getExportedBounds(camera, exportViewport);
        }

        // adjust the zero reference point
        graphics.transform(AffineTransform.getTranslateInstance(-theBounds.getX(), -theBounds.getY()));

        // The global clip setting is required as (in PPaintContext) a default one will be set!
        // This however will let various  browsers go crazy and don't show anything!
        graphics.setClip(theBounds);

        // explicitly initialize the white background (required especially for SVG exports)  
        graphics.setFillColor(KlighdConstants.WHITE);
        graphics.fill(theBounds);

        final PPaintContext paintContext = new PPaintContext((Graphics2D) graphics);

        // the following setting contradict the defaults in BatikSVGGraphics
        //  which leads to a blown-up svg file with a huge amount of repeated local style settings
        // therefore, here and in KlighdAbstractSVGGraphics#setRenderingHint(Key, Object)
        //  the propagation of such RenderingHints has been suppressed

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
    }
}