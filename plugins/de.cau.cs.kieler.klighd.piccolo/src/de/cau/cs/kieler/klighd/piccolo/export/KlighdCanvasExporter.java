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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.IDiagramExporter;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;

/**
 * Abstract implementation of {@link IDiagramExporter} supporting the export of diagrams depicted by
 * a {@link KlighdCanvas}.<br>
 * <br>
 * Thus, it treats the {@link Control} in
 * {@link #export(de.cau.cs.kieler.klighd.IDiagramExporter.ExportInfo))} as a {@link KlighdCanvas}
 * and redirects to {@link #export(KlighdExportInfo))}, which has to be implemented by concrete
 * subclasses.
 * 
 * @author chsch
 */
public abstract class KlighdCanvasExporter extends AbstractDiagramExporter implements
        IDiagramExporter {

    private static final String IVALID_CONTROL_FAILURE =
            "KLighD diagram export: "
                    + "The SWT Control of type ## is not supported by this &&!";

    /**
     * {@inheritDoc}
     */
    public void export(final ExportData data, final Control control) {

        if (control instanceof KlighdCanvas) {
            final KlighdCanvas canvas = (KlighdCanvas) control;
            export(data, canvas);
        } else {
            final String msg = IVALID_CONTROL_FAILURE
                    .replace("##", control.getClass().getCanonicalName())
                    .replace("&&", this.getClass().getCanonicalName());
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
     * of the {@link IDiagramExporter} interface might support multiple sub formats of the same
     * parent format, e.g., bmp and png are both bitmap formats.
     * 
     * @param data
     *            the specified export data
     * @param canvas
     *            the canvas to export
     */
    public abstract void export(ExportData data, KlighdCanvas canvas);
}
