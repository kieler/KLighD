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

import java.io.OutputStream;

import org.eclipse.swt.widgets.Control;

/**
 * Interface of diagram exporters like <code>.png</code> or </code>.svg</code> exporters, for instance.
 * 
 * @author uru
 * @author chsch
 * 
 * @see IDiagramExporter#export(OutputStream, Control, boolean, int, boolean, String)
 */
public interface IDiagramExporter {

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
     * @param control
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
    void export(final OutputStream stream, final Control control, final boolean cameraViewport,
            final int scale, boolean textAsShapes, final String subFormatId);


}
