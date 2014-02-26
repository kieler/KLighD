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
package de.cau.cs.kieler.klighd.piccolo.svg.export;

import java.io.OutputStream;

import de.cau.cs.kieler.klighd.piccolo.export.KlighdCanvasExporter;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.svg.KlighdSVGCanvas;
import edu.umd.cs.piccolo.PCamera;

/**
 * Generic SVG {@link IViewExporter}. Is able to export all svg generators registered by the
 * {@link de.cau.cs.kieler.klighd.piccolo.svg.SVGGeneratorManager
 * SVGGeneratorManager#EXTP_ID_SVGGENERATORS} extension point.
 * 
 * In order to work, the 'subFormat' ID of a registered exporter has to match the specified ID in
 * the 'svgGenerators' extension point.
 * 
 * @author uru
 */
public class SVGExporter extends KlighdCanvasExporter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void export(final OutputStream stream, final KlighdCanvas canvas,
            final boolean cameraViewport, final int scale, final boolean textAsShapes,
            final boolean embedFonts, final String subFormatId) {

        // just call the svg canvas's static render method, pass the subFormat, the canvas knows how
        // to handle it
        PCamera camera = canvas.getCamera();
        KlighdSVGCanvas.render(camera, cameraViewport, textAsShapes, embedFonts, stream, subFormatId);
    }
}
