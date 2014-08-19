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
package de.cau.cs.kieler.klighd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.swt.widgets.Control;

/**
 * Interface of diagram exporters like <code>.png</code> or </code>.svg</code> exporters, for
 * instance.
 * 
 * @author uru
 * @author chsch
 * @author csp
 * 
 * @see IDiagramExporter#export(OutputStream, Control, boolean, int, boolean, boolean, String)
 */
public interface IDiagramExporter {

    /**
     * Exports the diagram currently visible on the given {@code control} to the passed output
     * stream. If the {@code cameraViewport} flag is set, only the visible area is exported. The
     * {@code scale} value can be used for instance during the export of bitmap graphics to increase
     * the rendering quality by up-scaling the visible area before exporting. Some implementations
     * of the {@link IDiagramExporter} interface might support multiple sub formats of the same
     * parent format, e.g., bmp and png are both bitmap formats.
     * 
     * @param data
     *            the specified export info
     * @param control
     *            the control to export
     */
    void export(ExportData data, Control control);

    /**
     * Capsules the information needed to export a diagram to the filesystem. Merely a record to
     * hold the information.
     * 
     * @author csp
     */
    public class ExportData {

        // CHECKSTYLEOFF javadoc
        public final OutputStream stream;
        public final IPath path;
        public final boolean isWorkspacePath;
        public final boolean isCameraViewport;
        public final int scale;
        public final boolean isTextAsShapes;
        public final boolean isEmbedFonts;
        public final String subFormatId;

        private TilingData tilingInfo;

        /**
         * @param path
         *            the path to write the image to (either filesystem or workspace)
         * @param isWorkspacePath
         *            wether the given path should be interpreted as filesystem or workspace
         *            relative
         * @param control
         *            the canvas
         * @param cameraViewport
         *            true if the scene graph should be rendered through the camera, i.e. only
         *            render what is visible on the canvas; false to render the whole scene graph
         * @param scale
         *            the scale factor to apply while constructing the image
         * @param textAsShapes
         *            whether text in vector graphics should be rendered as shapes
         * @param embedFonts
         *            whether the texts' fonts shall be embedded in the output
         * @param subFormatId
         *            an id for a certain subformat
         */
        public ExportData(final IPath path, final boolean isWorkspacePath,
                final boolean cameraViewport, final int scale, final boolean textAsShapes,
                final boolean embedFonts, final String subFormatId) {
            this.stream = null;
            this.path = path;
            this.isWorkspacePath = isWorkspacePath;
            this.isCameraViewport = cameraViewport;
            this.scale = scale;
            this.isTextAsShapes = textAsShapes;
            this.isEmbedFonts = embedFonts;
            this.subFormatId = subFormatId;
            this.tilingInfo = TilingData.createNonTiledData();
        }

        /**
         * @param stream
         *            the output stream
         * @param control
         *            the canvas
         * @param cameraViewport
         *            true if the scene graph should be rendered through the camera, i.e. only
         *            render what is visible on the canvas; false to render the whole scene graph
         * @param scale
         *            the scale factor to apply while constructing the image
         * @param textAsShapes
         *            whether text in vector graphics should be rendered as shapes
         * @param embedFonts
         *            whether the texts' fonts shall be embedded in the output
         * @param subFormatId
         *            an id for a certain subformat
         */
        public ExportData(final OutputStream stream, final boolean cameraViewport, final int scale,
                final boolean textAsShapes, final boolean embedFonts, final String subFormatId) {
            this.stream = stream;
            this.path = null;
            this.isWorkspacePath = false;
            this.isCameraViewport = cameraViewport;
            this.scale = scale;
            this.isTextAsShapes = textAsShapes;
            this.isEmbedFonts = embedFonts;
            this.subFormatId = subFormatId;
            this.tilingInfo = TilingData.createNonTiledData();
        }

        /**
         * If an outputstream has been set, it is simply returned. Otherwise, a new stream pointing
         * to the given path is created.
         * 
         * @return the outputstream to write the image to.
         * @throws IOException
         *             if there is a problem obtaining an open output stream.
         */
        public OutputStream createOutputStream() throws IOException {
            if (path == null) {
                return stream;
            } else {
                return createOutputStream(path);
            }
        }

        /**
         * If a path has been set, a new outputstream is created. The name of the file is appended
         * by the given row and column.
         * 
         * @param row
         *            number of current row
         * @param col
         *            number of current column
         * @return the outputstream to write the image to.
         * @throws IOException
         *             if there is a problem obtaining an open output stream.
         * @throws IllegalArgumentException
         *             if no path has been set.
         */
        public OutputStream createOutputStream(final int row, final int col) throws IOException {
            if (path == null) {
                throw new IllegalArgumentException("Not tileable.");
            }
            String ext = path.getFileExtension();
            String name = path.removeFileExtension().lastSegment() + "_" + row + "-" + col;
            IPath aPath = path.removeLastSegments(1).append(name).addFileExtension(ext);
            return createOutputStream(aPath);
        }

        private OutputStream createOutputStream(final IPath aPath) throws IOException {
            if (isWorkspacePath) {
                // workspace path
                final URI fileURI = URI.createPlatformResourceURI(aPath.toOSString(), true);
                final OutputStream outputStream = URIConverter.INSTANCE.createOutputStream(fileURI);
                return outputStream;
            } else {
                // file system path
                final File file = new File(aPath.toString());
                final FileOutputStream outputStream = new FileOutputStream(file);
                return outputStream;
            }
        }

        /**
         * @return the tilingInfo
         */
        public TilingData getTilingInfo() {
            return tilingInfo;
        }

        /**
         * @param tilingInfo
         *            the tilingInfo to set
         */
        public void setTilingInfo(final TilingData tilingInfo) {
            if (path == null) {
                throw new IllegalArgumentException("Not tileable.");
            }
            this.tilingInfo = tilingInfo;
        }
    }

    /**
     * Capsules the information needed to tile a diagram for export. Merely a record to hold the
     * information.
     * 
     * @author csp
     */
    public static final class TilingData {

        /** Non tiled tiling information. */
        public static final TilingData NON_TILED = new TilingData();

        // CHECKSTYLEOFF javadoc
        public final int maxWidth, maxHeight;
        public final int cols, rows;
        public final boolean isTiled;
        public final boolean isMaxsize;

        private TilingData(final int maxWidth, final int maxHeight, final int rows, final int cols,
                final boolean tiled, final boolean isMaxsize) {
            this.maxWidth = maxWidth;
            this.maxHeight = maxHeight;
            this.rows = rows;
            this.cols = cols;
            this.isTiled = tiled;
            this.isMaxsize = isMaxsize;
        }

        private TilingData() {
            this(-1, -1, 1, 1, false, false);
        }

        /**
         * @return non tiled information.
         */
        public static TilingData createNonTiledData() {
            return NON_TILED;
        }

        /**
         * Create tiled information with given numbers of rows and columns.
         * 
         * @param rows
         *            number of rows.
         * @param cols
         *            number of columns.
         * @return the tiled information.
         */
        public static TilingData createTiledData(final int rows, final int cols) {
            return new TilingData(-1, -1, Math.max(1, rows), Math.max(1, cols), rows > 1
                    || cols > 1, false);
        }

        /**
         * Create tiled information with given maximum size.
         * 
         * @param maxWidth
         *            maximal width.
         * @param maxHeight
         *            maximal height.
         * @return the tiled information.
         */
        public static TilingData createMaxSizeTiledData(final int maxWidth, final int maxHeight) {
            return new TilingData(Math.max(1, maxWidth), Math.max(1, maxHeight), 1, 1, true, true);
        }

    }
}
