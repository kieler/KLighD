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
import java.awt.geom.AffineTransform;
import java.io.OutputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.ui.statushandlers.StatusManager;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void export(final OutputStream stream, final KlighdCanvas canvas,
            final boolean cameraViewport, final int scale, final boolean textAsShapes,
            final boolean embedFonts, final String subFormatId) {

        // reveal the canvas' camera ...
        final KlighdMainCamera camera = canvas.getCamera();

        // ... an determine the bounds of the diagram to be exported
        final PBounds bounds = getExportedBounds(camera, cameraViewport);

        // determine the employed image's size
        final int width = (int) Math.ceil(scale * bounds.width);
        final int height = (int) Math.ceil(scale * bounds.height);

        // initialize an SWT Image that serves as the pixel 'canvas'
        final Image image = new Image(canvas.getDisplay(), width, height);
        final GC gc = new GC(image);

        // initialize a graphics object that 'collects' all the drawing instructions 
        final KlighdSWTGraphics graphics = new KlighdSWTGraphicsImpl(gc, canvas.getDisplay());

        // apply the scale factor to the employed graphics object
        //  by means of a corresponding affine transform
        graphics.transform(AffineTransform.getScaleInstance(scale, scale));

        // do the action diagram drawing work
        drawDiagram(camera, cameraViewport, graphics, bounds); 

        // create an image loader to save the image
        // although the API differently suggests:
        //  the ImageData array below must contain exactly 1 element,
        //   see the implementations of FileFormat.unloadIntoByteStream(ImageLoader) 
        final ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { image.getImageData() };

        // translate the requested format identifier
        final int format;
        if (subFormatId.equals(SUB_FORMAT_JPEG)) {
            format = SWT.IMAGE_JPEG;
        } else if (subFormatId.equals(SUB_FORMAT_PNG)) {
            format = SWT.IMAGE_PNG;
        } else {
            // default format is bmp
            format = SWT.IMAGE_BMP;
        }

        // dump out the binary image data via the provided output stream
        try {
            loader.save(stream, format);
        } catch (final SWTError e) {
            final String msg = "KLighD bitmap export: "
                    + "Failed to write bitmap data into the provided OutputStream of type "
                    + stream.getClass().getCanonicalName() + KlighdPlugin.LINE_SEPARATOR
                    + " the stream instance is " + stream.toString();
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg, e));
        }

        // release all native resources
        ((Graphics2D) graphics).dispose();
        gc.dispose();
        image.dispose();
    }
}
