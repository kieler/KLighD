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

import java.io.OutputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.IDiagramExporter;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;

/**
 * @author chsch
 *
 */
public abstract class KlighdCanvasExporter implements IDiagramExporter {
    
    /**
     * {@inheritDoc}
     */
    public void export(final OutputStream stream, final Control control,
            final boolean cameraViewport, final int scale, final boolean textAsShapes,
            final String subFormatId) {
        
        final KlighdCanvas canvas;
        if (control instanceof KlighdCanvas) {
            canvas = (KlighdCanvas) control;
            export(stream, canvas, cameraViewport, scale, textAsShapes, subFormatId);
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
     * @param subFormatId
     *            an id for a certain subformat
     */
    public abstract void export(OutputStream stream, KlighdCanvas canvas, boolean cameraViewport,
            int scale, boolean textAsShapes, String subFormatId);
}
