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

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
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
    public IStatus export(final ExportData data, final KlighdCanvas canvas) {

        // reveal the canvas' camera ...
        final KlighdMainCamera camera = canvas.getCamera();

        // ... an determine the bounds of the diagram to be exported
        final PBounds bounds = this.getExportedBounds(camera, data.isCameraViewport);
        
        // initialize a graphics object that 'collects' all the drawing instructions 
        final KlighdAbstractSVGGraphics graphics =
                SVGGeneratorManager.createGraphics(data.format, bounds,
                        data.isTextAsShapes, data.isEmbedFonts);

        // do the actual diagram drawing work
        this.drawDiagram(camera, data.isCameraViewport, graphics, bounds); 

        OutputStream stream = null;
        try {
            // dump out the resulting SVG description via the provided output stream
            stream = data.createOutputStream();
            graphics.stream(stream);
            stream.close();
            return Status.OK_STATUS;

        } catch (final IOException e) {
            String msg = "KLighD SVG export: "
                    + "Failed to write SVG data";
            if (stream != null) {
                msg += " into the provided OutputStream of type "
                        + stream.getClass().getCanonicalName() + KlighdPlugin.LINE_SEPARATOR
                        + " the stream instance is " + stream.toString();
            }
            return new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg, e);
        }        
    }
}
