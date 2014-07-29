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
     * Exports the diagram currently visible on the given {@code canvas} to the passed output
     * stream. If the {@code cameraViewport} flag is set, only the visible area is exported. The
     * {@code scale} value can be used for instance during the export of bitmap graphics to increase
     * the rendering quality by up-scaling the visible area before exporting. Some implementations
     * of the {@link IDiagramExporter} interface might support multiple sub formats of the same
     * parent format, e.g., bmp and png are both bitmap formats.
     * 
     * @param info
     *          the specified export info
     */
    void export(ExportInfo info);

    /**
     * Capsules the information needed to export a diagram to the filesystem. Merely a record to
     * hold the information.
     * 
     * @author csp
     */
    public class ExportInfo {
        
        private OutputStream stream;
        private IPath path;
        private boolean isWorkspacePath;
        private Control control;
        private boolean cameraViewport;
        private int scale;
        private boolean textAsShapes;
        private boolean embedFonts;
        private String subFormatId;
        
        private TilingInfo tilingInfo;

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
        public ExportInfo(final IPath path, final boolean isWorkspacePath, final Control control,
                final boolean cameraViewport, final int scale, final boolean textAsShapes,
                final boolean embedFonts, final String subFormatId) {
            this.path = path;
            this.isWorkspacePath = isWorkspacePath;
            this.control = control;
            this.cameraViewport = cameraViewport;
            this.scale = scale;
            this.textAsShapes = textAsShapes;
            this.embedFonts = embedFonts;
            this.subFormatId = subFormatId;
            this.tilingInfo = TilingInfo.createNonTiledInfo();
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
        public ExportInfo(final OutputStream stream, final Control control,
                final boolean cameraViewport, final int scale, final boolean textAsShapes,
                final boolean embedFonts, final String subFormatId) {
            this.stream = stream;
            this.control = control;
            this.cameraViewport = cameraViewport;
            this.scale = scale;
            this.textAsShapes = textAsShapes;
            this.embedFonts = embedFonts;
            this.subFormatId = subFormatId;
            this.tilingInfo = TilingInfo.createNonTiledInfo();
        }

        /**
         * If an outputstream has been set, it is simply returned. Otherwise, a new stream pointing
         * to the given path is created.
         * 
         * @return the outputstream to write the image to.
         * @throws IOException if there is a problem obtaining an open output stream.
         */
        public OutputStream createOutputStream() throws IOException {
            if (path == null) {
                return stream;
            } else {
                return createOutputStream(path);
            }
        }
        
        /**
         * If a path has been set, a new outputstream is created.
         * The name of the file is appended by the given row and column.
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
         * @return the outputstream, if set, {@code null} otherwise
         */
        public OutputStream getStream() {
            return stream;
        }
        
        /**
         * @return {@code true} if a path has been set
         */
        public boolean hasPath() { //TODO call it "tileable" ?
            return path != null;
        }
        
        /**
         * @return the path if set, {@code null} otherwise
         */
        public IPath getPath() {
            return path;
        }
        
        /**
         * @return {@code true} ({@code false}) if the path has been set and is workspace
         *         (filesystem) relative, {@code null} otherwise
         */
        public boolean isWorkspacePath() {
            return isWorkspacePath;
        }

        /**
         * @return the control
         */
        public Control getControl() {
            return control;
        }

        /**
         * @return the cameraViewport
         */
        public boolean isCameraViewport() {
            return cameraViewport;
        }

        /**
         * @return the scale
         */
        public int getScale() {
            return scale;
        }

        /**
         * @return the textAsShapes
         */
        public boolean isTextAsShapes() {
            return textAsShapes;
        }

        /**
         * @return the embedFonts
         */
        public boolean isEmbedFonts() {
            return embedFonts;
        }

        /**
         * @return the subFormatId
         */
        public String getSubFormatId() {
            return subFormatId;
        }
        
        /**
         * @return the tilingInfo
         */
        public TilingInfo getTilingInfo() {
            return tilingInfo;
        }

        /**
         * @param tilingInfo the tilingInfo to set
         */
        public void setTilingInfo(final TilingInfo tilingInfo) {
            if (path == null) {
                throw new IllegalArgumentException("Not tileable.");
            }
            this.tilingInfo = tilingInfo;
        }

        /**
         * Capsules the information needed to tile a diagram for export. Merely a record to
         * hold the information.
         * 
         * @author csp
         */
        public static class TilingInfo {
            
            /** Non tiled tiling information. */
            public static final TilingInfo NON_TILED = new TilingInfo();

            private int maxWidth, maxHeight;
            private int cols, rows;
            private boolean tiled;
            private boolean isMaxsize;
            
            /**
             * @param maxWidth
             * @param maxHeight
             * @param cols
             * @param rows
             */
            protected TilingInfo() {
                this.tiled = false;
                this.isMaxsize = false;
                this.maxWidth = -1;
                this.maxHeight = -1;
                this.rows = 1;
                this.cols = 1;
            }

            /**
             * @return non tiled information.
             */
            public static TilingInfo createNonTiledInfo() {
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
            public static TilingInfo createTiledInfo(final int rows, final int cols) {
                TilingInfo info = new TilingInfo();
                info.rows = Math.max(1, rows);
                info.cols = Math.max(1, cols);
                info.tiled = info.rows > 1 || info.cols > 1;
                info.isMaxsize = false;
                return info;
            }

            /**
             * Create tiled information with gives maximum size.
             * 
             * @param maxWidth
             *            maximal width.
             * @param maxHeight
             *            maximal height.
             * @return the tiled information.
             */
            public static TilingInfo createMaxSizeTiledInfo(final int maxWidth, final int maxHeight) {
                TilingInfo info = new TilingInfo();
                info.maxWidth = Math.max(1, maxWidth);
                info.maxHeight = Math.max(1, maxHeight);
                info.tiled = true;
                info.isMaxsize = true;
                return info;
            }
            
            /**
             * @return the maxWidth
             */
            public int getMaxWidth() {
                return maxWidth;
            }

            /**
             * @return the maxHeight
             */
            public int getMaxHeight() {
                return maxHeight;
            }

            /**
             * @return the number of cols
             */
            public int getCols() {
                return cols;
            }

            /**
             * @return the number of rows
             */
            public int getRows() {
                return rows;
            }
            
            /**
             * @return {@code true} if it's tiled
             */
            public boolean isTiled() {
                return tiled;
            }

            /**
             * @return {@code true} if a maximum size has been set
             */
            public boolean isMaxsize() {
                return isMaxsize;
            }
        }
    }
}
