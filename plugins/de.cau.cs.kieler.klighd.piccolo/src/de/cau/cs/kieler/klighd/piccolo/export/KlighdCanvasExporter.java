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

import org.eclipse.core.runtime.IPath;
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
 * {@link #export(de.cau.cs.kieler.klighd.IDiagramExporter.ExportInfo))} as a
 * {@link KlighdCanvas} and redirects to
 * {@link #export(KlighdExportInfo))}, which has to
 * be implemented by concrete subclasses.
 * 
 * @author chsch
 */
public abstract class KlighdCanvasExporter extends AbstractDiagramExporter implements IDiagramExporter {
    
    /**
     * {@inheritDoc}
     */
    public void export(final ExportInfo info) {
        
        if (info.getControl() instanceof KlighdCanvas) {
            export(KlighdExportInfo.fromExportInfo(info));
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
     * @param info
     *          the specified export info
     */
    public abstract void export(KlighdExportInfo info);
    
    
    public static class KlighdExportInfo extends ExportInfo{

        public KlighdExportInfo(final IPath path, final boolean isWorkspacePath,
                final KlighdCanvas canvas, final boolean cameraViewport, final int scale,
                final boolean textAsShapes, final boolean embedFonts, final String subFormatId) {
            super(path, isWorkspacePath, canvas, cameraViewport, scale, textAsShapes, embedFonts,
                    subFormatId);
        }
        
        public KlighdExportInfo(final OutputStream stream, final KlighdCanvas canvas,
                final boolean cameraViewport, final int scale, final boolean textAsShapes,
                final boolean embedFonts, final String subFormatId) {
            super(stream, canvas, cameraViewport, scale, textAsShapes, embedFonts, subFormatId);
        }
        
        public static KlighdExportInfo fromExportInfo(ExportInfo info) {
            KlighdExportInfo newInfo;
            if (info.hasPath()) {
                newInfo =
                        new KlighdExportInfo(info.getPath(), info.isWorkspacePath(),
                                (KlighdCanvas) info.getControl(), info.isCameraViewport(),
                                info.getScale(), info.isTextAsShapes(), info.isEmbedFonts(),
                                info.getSubFormatId());
                newInfo.setTilingInfo(info.getTilingInfo());
            } else {
                newInfo =
                        new KlighdExportInfo(info.getStream(),
                                (KlighdCanvas) info.getControl(), info.isCameraViewport(),
                                info.getScale(), info.isTextAsShapes(), info.isEmbedFonts(),
                                info.getSubFormatId());
            }
            return newInfo;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public KlighdCanvas getControl() {
            return (KlighdCanvas) super.getControl();
        }
        
    }
}