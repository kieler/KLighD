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
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

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

        final int format;
        if (subFormatId.equals(SUB_FORMAT_JPEG)) {
            format = SWT.IMAGE_JPEG;
        } else if (subFormatId.equals(SUB_FORMAT_PNG)) {
            format = SWT.IMAGE_PNG;
        } else {
            // default format is bmp
            format = SWT.IMAGE_BMP;
        }

        final PCamera camera = canvas.getCamera();

        // create the target image and a linked graphics context
        final PBounds bounds;
        if (cameraViewport) {
            bounds = camera.getFullBounds();
        } else {
            bounds = camera.getUnionOfLayerFullBounds();
        }

        // construct an affine transform for applying the scale factor
        // and apply it to the camera's bounds
        final PAffineTransform transform = new PAffineTransform();
        transform.scale(scale, scale);
        transform.transform(bounds, bounds);

        // reveal the size and respect the indentation imposed by x/y on both sides
        // in order to avoid clippings of root figure drawings
        final int width = (int) (bounds.width + 2 * bounds.x);
        final int height = (int) (bounds.height + 2 * bounds.y);

        // let Piccolo render onto a image GC
        final Image image = new Image(canvas.getDisplay(), width, height);
        final GC gc = new GC(image);

        final PPaintContext paintContext =
                new PPaintContext(new KlighdSWTGraphicsImpl(gc, canvas.getDisplay()));

        // apply scaling translation to the paint context, too, for actually scaling the diagram
        paintContext.pushTransform(transform);

        if (cameraViewport) {
            camera.fullPaint(paintContext);
        } else {
            fullPaintLayers(paintContext, camera);
        }
        paintContext.popTransform(transform);

        // create an image loader to save the image
        final ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { image.getImageData() };
        loader.save(stream, format);
        
        // release all native resources
        paintContext.getGraphics().dispose();
        gc.dispose();
        image.dispose();
    }

    @SuppressWarnings("unchecked")
    private static void fullPaintLayers(final PPaintContext paintContext, final PCamera camera) {
        paintContext.pushCamera(camera);
        for (PLayer layer : (List<PLayer>) camera.getLayersReference()) {
            layer.fullPaint(paintContext);
        }
        paintContext.popCamera();
    }

}
