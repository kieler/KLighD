/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * An action which invokes the 'save-as-image' dialog and performs the save for Piccolo.
 * 
 * @author mri
 */
public class SaveAsImageAction extends Action {

    /** the associated Piccolo viewer. */
    private PiccoloViewer viewer;

    /**
     * Constructs the 'save-as-image' action.
     * 
     * @param viewer
     *            the associated Piccolo viewer
     * @param text
     *            the user-friendly text for the action
     */
    public SaveAsImageAction(final PiccoloViewer viewer, final String text) {
        super(text);
        this.viewer = viewer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        // open the dialog to receive the required user input
        SaveAsImageDialog dialog = new SaveAsImageDialog(viewer.getCanvas().getShell());
        int code = dialog.open();
        if (code == Dialog.OK) {
            try {
                // open the stream
                OutputStream stream =
                        createOutputStream(dialog.getFilePath(), dialog.isWorkspacePath());
                // render the canvas to an image and write it to the stream
                toImage(stream, viewer.getCanvas(), dialog.isCameraViewport(),
                        dialog.getSWTImageFormat(), dialog.getScaleFactor());
                stream.close();
            } catch (IOException exception) {
                Status myStatus =
                        new Status(IStatus.WARNING, KlighdPiccoloPlugin.PLUGIN_ID,
                                Messages.SaveAsImageAction_save_as_image_error, exception);
                StatusManager.getManager().handle(myStatus,
                        StatusManager.BLOCK | StatusManager.SHOW);
            }
        }
    }

    private OutputStream createOutputStream(final IPath path, final boolean isWorkspacePath)
            throws IOException {
        if (isWorkspacePath) {
            // workspace path
            URI fileURI = URI.createPlatformResourceURI(path.toOSString(), true);
            URIConverter uriConverter = new ExtensibleURIConverterImpl();
            OutputStream outputStream = uriConverter.createOutputStream(fileURI);
            return outputStream;
        } else {
            // file system path
            File file = new File(path.toString());
            FileOutputStream outputStream = new FileOutputStream(file);
            return outputStream;
        }
    }

    /**
     * Renders a Piccolo canvas to an image and writes it in the specified file format to the given
     * stream.
     * 
     * @param stream
     *            the output stream to write the image data to
     * @param canvas
     *            the canvas
     * @param cameraViewport
     *            true if the scene graph should be rendered through the camera, i.e. only render
     *            what is visible on the canvas; false to render the whole scene graph
     * @param format
     *            the file format, see {@code FileLoader}
     * @param scale
     *            the scale factor to apply while constructing the image
     */
    public static void toImage(final OutputStream stream, final KlighdCanvas canvas,
            final boolean cameraViewport, final int format, final int scale) {
        PCamera camera = canvas.getCamera();
        
        // create the target image and a linked graphics context
        PBounds bounds;
        if (cameraViewport) {
            bounds = camera.getFullBounds();
        } else {
            bounds = camera.getUnionOfLayerFullBounds();
        }
        
        // construct an affine transform for applying the scale factor
        //  and apply it to the camera's bounds
        PAffineTransform transform = new PAffineTransform();
        transform.scale(scale, scale);
        transform.transform(bounds, bounds);
        
        // reveal the size and respect the indentation imposed by x/y on both sides 
        //  in order to avoid clippings of root figure drawings
        int width = (int) (bounds.width + 2 * bounds.x);
        int height = (int) (bounds.height + 2 * bounds.y);

        // let Piccolo render onto a image GC
        Image image = new Image(canvas.getDisplay(), width, height);
        GC gc = new GC(image);
        
        PPaintContext paintContext = new PPaintContext(new KlighdSWTGraphicsImpl(gc,
                canvas.getDisplay()));
        
        // apply scaling translation to the paint context, too, for actually scaling the diagram
        paintContext.pushTransform(transform);
        
        if (cameraViewport) {
            camera.fullPaint(paintContext);
        } else {
            fullPaintLayers(paintContext, camera);
        }
        paintContext.popTransform(transform);
        
        // create an image loader to save the image
        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { image.getImageData() };
        loader.save(stream, format);
        // release all native resources
        gc.dispose();
        image.dispose();
    }

    @SuppressWarnings("unchecked")
    private static void fullPaintLayers(final PPaintContext paintContext, final PCamera camera) {
        for (PLayer layer : (List<PLayer>) camera.getLayersReference()) {
            layer.fullPaint(paintContext);
        }
    }

}
