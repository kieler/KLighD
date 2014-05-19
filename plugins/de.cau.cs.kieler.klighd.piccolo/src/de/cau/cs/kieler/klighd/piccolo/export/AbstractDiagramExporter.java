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

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * Abstract diagram exporter providing the common methods
 * {@link #getExportedBounds(KlighdMainCamera, boolean)} and
 * {@link #drawDiagram(KlighdMainCamera, boolean, KlighdSWTGraphics, PBounds)} to be re-used in
 * concrete implementation of {@link de.cau.cs.kieler.klighd.IDiagramExporter IDiagramExporter} and
 * {@link de.cau.cs.kieler.klighd.IOffscreenRenderer IOffscreenRenderer}, in order to achieve
 * consistent behavior amongst all those implementations.
 * 
 * @author chsch
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
     * Does the actual diagram rendering work by means of the employed {@link KlighdSWTGraphics}.<br>
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
