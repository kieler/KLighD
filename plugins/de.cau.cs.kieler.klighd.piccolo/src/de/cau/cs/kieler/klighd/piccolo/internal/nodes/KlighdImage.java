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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.InputStream;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A special {@link PNode} for integrating images in KLighD diagrams. The implementation is inspired
 * by that of {@link edu.umd.cs.piccolox.swt.PSWTImage}, some major differences wrt to design
 * requirements led to this new implementation. These differences involve the non-dependency to any
 * specific canvas implementation as well as to {@link org.eclipse.swt.widgets.Display Display}
 * being a specific {@link Device}.<br>
 * <br>
 * In contrast to {@link edu.umd.cs.piccolox.swt.PSWTImage} the bounds of nodes of this type are not
 * set while setting the image object to be displayed. Doing so results in flickering, at least
 * while drawing diagrams without animation. Instead the bounds are set top down by KlighD, see
 * {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.KGERenderingControllerHelper#createImage(
 * de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController,
 * de.cau.cs.kieler.core.krendering.KImage, java.util.List, PNode,
 * de.cau.cs.kieler.klighd.microlayout.Bounds) KGERenderingControllerHelper#createImage(...)}.<br>
 * <br>
 * If the amount of instances of {@link Image} created while drawing diagrams, e.g. such with lots
 * of copies of the same icon will lead to performance/memory issues, one might introduce a further
 * caching mechanism beyond that in
 * {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.KGERenderingControllerHelper#IMAGE_BUFFER
 * KGERenderingControllerHelper#IMAGE_BUFFER}. This could be done like for colors and fonts.
 * However, keeping every image in a lookup for the whole JVM life cycle sounds problematic...
 * 
 * @author chsch
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class KlighdImage extends PNode {

    private static final long serialVersionUID = 7201328608113593385L;
    private transient Image image;
    private transient ImageData imageData;

    /**
     * Constructor.
     * @param image
     *            image to be displayed by this {@link KlighdImage}
     */
    public KlighdImage(final Image image) {
        setImage(image);
        addDisposeListener();
    }

    /**
     * Constructor.
     * @param image
     *            image to be displayed by this {@link KlighdImage}
     */
    public KlighdImage(final ImageData image) {
        setImage(image);
        addDisposeListener();
    }

    /**
     * Constructor.
     * @param input
     *            stream providing the image, will be read and converted to an Image internally
     */
    public KlighdImage(final InputStream input) {
        setImage(input);
        addDisposeListener();
    }
    
    /**
     * Adds a lister to this node being in charge of disposing {@link #image} in case this node
     * (or one of its parents) is removed from its parent, which basically means that this node
     * has been removed.
     */
    private void addDisposeListener() {
        final PropertyChangeListener disposeListener = new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent event) {
                if (event.getNewValue() == null) {
                    if (event.getNewValue() == null && image != null) {
                        image.dispose();
                        image = null;
                    }
                    
                    @SuppressWarnings("unchecked")
                    final List<PNode> children = KlighdImage.this.getChildrenReference();
                    for (PNode p : children) {
                        p.firePropertyChange(NodeUtil.DISPOSE_CODE, NodeUtil.DISPOSE, this, null);
                    }
                }
            }
        };
        this.addPropertyChangeListener(PNode.PROPERTY_PARENT, disposeListener);
        this.addPropertyChangeListener(NodeUtil.DISPOSE, disposeListener);
    }

    /**
     * Getter.
     * 
     * @return the image that is displayed by this node, may be null
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image to be displayed by this node by delegating to
     * {@link ImageData#ImageData(InputStream)} and {@link #setImage(ImageData)}.
     * 
     * @param input
     *            stream providing the image data
     */
    public void setImage(final InputStream input) {
        setImage(new ImageData(input));
    }
    
    /**
     * Sets the image to be displayed by this node.
     *  
     * @param newImageData the image to be displayed, may be null
     */
    public void setImage(final ImageData newImageData) {
        final Image old = this.image;
        this.image = null;
        this.imageData = newImageData;

        if (old != null) {
            old.dispose();
        }

        firePropertyChange(PImage.PROPERTY_CODE_IMAGE, PImage.PROPERTY_IMAGE, old, imageData);
    }

    /**
     * Sets the image to be displayed by this node.
     *  
     * @param newImage the image to be displayed, may be null
     */
    public void setImage(final Image newImage) {
        final Image old = this.image;
        this.image = newImage;
        this.imageData = null;

        if (old != null) {
            old.dispose();
        }

        firePropertyChange(PImage.PROPERTY_CODE_IMAGE, PImage.PROPERTY_IMAGE, old, image);
    }

    /**
     * {@inheritDoc}
     */
    protected void paint(final PPaintContext paintContext) {
        final KlighdSWTGraphics graphics = (KlighdSWTGraphics) paintContext.getGraphics();
        final PBounds b = getBoundsReference();

        if (graphics.getDevice() != null) {
            // within an SWT environment
            if (this.image == null && this.imageData != null) {
                this.image = new Image(graphics.getDevice(), this.imageData);
                this.imageData = null;
            }
            graphics.drawImage(image, b.width, b.height);
        } else {
            // without any device we have to draw the raw image data
            if (image != null) {
                graphics.drawImage(image.getImageData(), b.width, b.height);
            } else if (imageData != null) {
                graphics.drawImage(imageData, b.width, b.height);
            }
        }
    }
}
