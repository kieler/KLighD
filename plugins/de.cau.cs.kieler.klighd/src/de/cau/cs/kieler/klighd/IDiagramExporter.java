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
package de.cau.cs.kieler.klighd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.swt.widgets.Control;

/**
 * Basic interface of diagram exporters creating images in <code>png</code> or </code>svg</code>
 * format, for instance.
 *
 * @author uru
 * @author chsch
 * @author csp
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 *
 * @see IDiagramExporter#export(Control, ExportData)
 */
public interface IDiagramExporter {

    /**
     * Exports the diagram depicted by the given <code>control</code>.
     * @param control
     *            the control to export
     * @param data
     *            the specified export info
     *
     * @return {@link org.eclipse.core.runtime.Status#OK_STATUS Status#OK_STATUS} if the diagram
     *         export went successfully, an {@link IStatus} providing information on the failure
     *         otherwise.
     */
    IStatus export(Control control, ExportData data);

    /**
     * A data record encapsulating the information needed to export a diagram to the file system.<br>
     *
     * @author csp
     */
    public class ExportData {

        // SUPPRESS CHECKSTYLE NEXT 9 Visibility|Javadoc
        public final ViewContext viewContext;
        public final String format;
        public final OutputStream stream;
        public final IPath path;
        public final boolean isWorkspacePath;
        public final boolean isCameraViewport;
        public final int scale;
        public final boolean isTextAsShapes;
        public final boolean isEmbedFonts;
        public final String description;

        private TilingData tilingInfo;

        /**
         * Constructor.
         *
         * @param viewContext
         *            the {@link ViewContext} providing access to the diagram' view & source model
         * @param format
         *            id of the format to transform the diagram into
         * @param path
         *            the path to write the image to (either file system or workspace)
         * @param isWorkspacePath
         *            whether the given path should be interpreted as file system or workspace
         *            relative
         * @param cameraViewport
         *            if <code>true</code> only the actually visible area is to be exported,
         *            otherwise export the whole diagram
         * @param scale
         *            the scale factor to apply while constructing the image, is usually only valid
         *            in case of raster (bitmap) images
         * @param textAsShapes
         *            whether text should be rendered as shapes (only vector images)
         * @param embedFonts
         *            whether the texts' fonts shall be embedded in the output (only vector images)
         */ // SUPPRESS CHECKSTYLE NEXT Number -- we need all these data
        public ExportData(final ViewContext viewContext, final String format, final IPath path,
                final boolean isWorkspacePath, final boolean cameraViewport, final int scale,
                final boolean textAsShapes, final boolean embedFonts) {
            this.viewContext = viewContext;
            this.format = format;
            this.stream = null;
            this.path = path;
            this.isWorkspacePath = isWorkspacePath;
            this.isCameraViewport = cameraViewport;
            this.scale = scale;
            this.isTextAsShapes = textAsShapes;
            this.isEmbedFonts = embedFonts;
            this.tilingInfo = TilingData.createNonTiledData();
            this.description = null;
        }

        /**
         * Constructor.
         *
         * @param viewContext
         *            the {@link ViewContext} providing access to the diagram' view & source model
         * @param format
         *            id of the format to transform the diagram into
         * @param stream
         *            the output stream
         * @param cameraViewport
         *            if <code>true</code> only the actually visible area is to be exported,
         *            otherwise export the whole diagram
         * @param scale
         *            the scale factor to apply while constructing the image, is usually only valid
         *            in case of raster (bitmap) images
         * @param textAsShapes
         *            whether text should be rendered as shapes (only vector images)
         * @param embedFonts
         *            whether the texts' fonts shall be embedded in the output (only vector images)
         * @param description
         *            optional description to be inserted into the {@code desc} property of the
         *            generated SVG. Can be null.
         */
        public ExportData(final ViewContext viewContext, final String format,
                final OutputStream stream, final boolean cameraViewport, final int scale,
                final boolean textAsShapes, final boolean embedFonts, final String description) {
            this.viewContext = viewContext;
            this.format = format;
            this.stream = stream;
            this.path = null;
            this.isWorkspacePath = false;
            this.isCameraViewport = cameraViewport;
            this.scale = scale;
            this.isTextAsShapes = textAsShapes;
            this.isEmbedFonts = embedFonts;
            this.tilingInfo = TilingData.createNonTiledData();
            this.description = description;
        }

        /**
         * Constructor.
         *
         * @param viewContext
         *            the {@link ViewContext} providing access to the diagram' view & source model
         * @param format
         *            id of the format to transform the diagram into
         * @param stream
         *            the output stream
         * @param cameraViewport
         *            if <code>true</code> only the actually visible area is to be exported,
         *            otherwise export the whole diagram
         * @param scale
         *            the scale factor to apply while constructing the image, is usually only valid
         *            in case of raster (bitmap) images
         * @param textAsShapes
         *            whether text should be rendered as shapes (only vector images)
         * @param embedFonts
         *            whether the texts' fonts shall be embedded in the output (only vector images)
         */
        public ExportData(final ViewContext viewContext, final String format,
                final OutputStream stream, final boolean cameraViewport, final int scale,
                final boolean textAsShapes, final boolean embedFonts) {
            this.viewContext = viewContext;
            this.format = format;
            this.stream = stream;
            this.path = null;
            this.isWorkspacePath = false;
            this.isCameraViewport = cameraViewport;
            this.scale = scale;
            this.isTextAsShapes = textAsShapes;
            this.isEmbedFonts = embedFonts;
            this.tilingInfo = TilingData.createNonTiledData();
            this.description = null;
        }

        /**
         * If an {@link OutputStream} has been configured, it is simply returned. Otherwise, a new
         * stream pointing to the given path is created.
         *
         * @return the {@link OutputStream} to write the image to.
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
         * If a path has been set, a new {@link OutputStream} is created. The name of the file is
         * appended by the given row and column.
         *
         * @param row
         *            number of current row
         * @param col
         *            number of current column
         * @return the {@link OutputStream} to write the image to.
         * @throws IOException
         *             if there is a problem obtaining an open output stream.
         * @throws IllegalArgumentException
         *             if no path has been set.
         */
        public OutputStream createOutputStream(final int row, final int col) throws IOException {
            if (path == null) {
                throw new IllegalArgumentException("Not tileable.");
            }
            final String ext = path.getFileExtension();
            final String name = path.removeFileExtension().lastSegment() + "_" + row + "-" + col;
            final IPath aPath = path.removeLastSegments(1).append(name).addFileExtension(ext);
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
     * Encapsulates the information needed to tile a diagram for export. Merely a record to hold the
     * information.
     *
     * @author csp
     */
    public static final class TilingData {

        /** Non tiled tiling information. */
        public static final TilingData NON_TILED = new TilingData();

        // SUPPRESS CHECKSTYLE NEXT 4 Visibility|Javadoc
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
