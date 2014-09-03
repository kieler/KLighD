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

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * Generic {@link IViewExporter} for bitmap formats, e.g., png and jpeg.
 * 
 * Currently the following formats are supported:
 * <ul>
 * <li>bmp</li>
 * <li>jpeg</li>
 * <li>png</li>
 * </ul>
 * 
 * @author chsch
 * @author uru
 */
public class BitmapExporter extends KlighdCanvasExporter {

    /** the bmp format. */
    public static final String SUB_FORMAT_BMP = "bmp";

    /** the jpeg format. */
    public static final String SUB_FORMAT_JPEG = "jpeg";

    /** the png format. */
    public static final String SUB_FORMAT_PNG = "png";

    private static final String ERROR_MSG_PREFIX = "KLighD bitmap export: ";


    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus export(final ExportData data, final KlighdCanvas canvas) {

        // reveal the canvas' camera ...
        final KlighdMainCamera camera = canvas.getCamera();

        // ... and determine the bounds of the diagram to be exported
        final PBounds bounds = getExportedBounds(camera, data.isCameraViewport);

        // determine the employed image's size
        int width = (int) Math.ceil(data.scale * bounds.width);
        int height = (int) Math.ceil(data.scale * bounds.height);

        // if export is tiled, compute resp. receive the needed number of rows and columns
        int rows = 1, cols = 1;
        if (data.getTilingInfo().isTiled) {
            final TilingData tilingInfo = data.getTilingInfo();
            if (tilingInfo.isMaxsize) {
                rows = (int) Math.ceil(((double) height) / tilingInfo.maxHeight);
                cols = (int) Math.ceil(((double) width) / tilingInfo.maxWidth);
            } else {
                rows = tilingInfo.rows;
                cols = tilingInfo.cols;
            }
            // adapt the tiling
            width = (int) Math.ceil(((double) width) / cols);
            height = (int) Math.ceil(((double) height) / rows);
        }

        // for each row and columns draw and export the image
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                final IStatus res = exportTile(data, canvas, bounds, row, col, width, height);
                if (res != Status.OK_STATUS) {
                    return res;
                }
            }
        }

        return Status.OK_STATUS;
    }


    private IStatus exportTile(final ExportData data, final KlighdCanvas canvas, final PBounds bounds,
            final int row, final int col, final int width, final int height) {

        // initialize an SWT Image that serves as the pixel 'canvas'
        final Image image;
        try {
            image = new Image(canvas.getDisplay(), width, height);

        } catch (final OutOfMemoryError e) {
            System.gc();

            final String msg = ERROR_MSG_PREFIX + "Out of heap space memory!";
            return new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg, e);
        }

        final GC gc = new GC(image);

        // initialize a graphics object that 'collects' all the drawing instructions
        final KlighdSWTGraphics graphics = new KlighdSWTGraphicsImpl(gc, canvas.getDisplay());

        // apply translation and clipping for tiled export if necessary
        if (data.getTilingInfo().isTiled) {
            graphics.transform(AffineTransform.getTranslateInstance(-col * width, -row * height));
            graphics.clip(new Rectangle(width, height));
        }

        // apply the scale factor to the employed graphics object
        //  by means of a corresponding affine transform
        graphics.transform(AffineTransform.getScaleInstance(data.scale, data.scale));

        // do the action diagram drawing work
        drawDiagram(canvas.getCamera(), data.isCameraViewport, graphics, bounds, false);

        // create an image loader to save the image
        // although the API differently suggests:
        //  the ImageData array below must contain exactly 1 element,
        //  see the implementations of FileFormat.unloadIntoByteStream(ImageLoader)
        final ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { image.getImageData() };

        // translate the requested format identifier
        final int format;
        if (data.format.equals(SUB_FORMAT_JPEG)) {
            format = SWT.IMAGE_JPEG;
        } else if (data.format.equals(SUB_FORMAT_PNG)) {
            format = SWT.IMAGE_PNG;
        } else {
            // default format is bmp
            format = SWT.IMAGE_BMP;
        }

        // dump out the binary image data via the provided output stream
        OutputStream stream = null;
        IStatus status;

        try {
            if (data.getTilingInfo().isTiled) {
                stream = data.createOutputStream(row, col);
            } else {
                stream = data.createOutputStream();
            }

            loader.save(stream, format);
            stream.close();
            status = Status.OK_STATUS;

        } catch (final SWTError e) {
            String msg = ERROR_MSG_PREFIX + "Failed to write bitmap data";
            if (stream != null) {
                msg += " into the provided OutputStream of type "
                    + stream.getClass().getCanonicalName()
                    + KlighdPlugin.LINE_SEPARATOR + " the stream instance is "
                    + stream.toString();
            }

            status = new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg, e);

        } catch (final IOException e) {
            final String msg;
            if (stream == null) {
                msg = ERROR_MSG_PREFIX + "Failed to create output stream to write the image to.";
            } else {
                msg = ERROR_MSG_PREFIX + "Closing the employed output stream failed.";
            }
            status = new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg, e);

        } catch (final Throwable t) {
            status = new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, t.getMessage(), t);
        }

        // release all native resources
        ((Graphics2D) graphics).dispose();
        gc.dispose();
        image.dispose();

        return status;
    }
}
