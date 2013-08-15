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

import java.awt.Graphics2D;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.Constants;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdKeyEventListener;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolox.swt.PSWTCanvas;

/**
 * A specialized version of {@link PSWTCanvas} with KLighD-specific customizations.
 *
 * @author chsch
 */
public class KlighdCanvas extends PSWTCanvas {

    /**
     * The {@link KlighdSWTGraphics} abstractor to be incorporated while drawing the diagram.
     */
    private KlighdSWTGraphics graphics;

    /**
     * Construct a canvas with the basic scene graph consisting of a root,
     * camera, and layer. Event handlers for zooming and panning are
     * automatically installed.
     * 
     * @param parent component onto which the canvas is installed
     * @param style component style for the PSWTCanvas
     */
    public KlighdCanvas(final Composite parent, final int style) {
        super(parent, style);

        this.graphics = new KlighdSWTGraphicsImpl(null, parent.getDisplay());
        this.getRoot().addAttribute(Constants.DEVICE, parent.getDisplay());
        this.getRoot().addAttribute(Constants.MAIN_CAMERA, this.getCamera());

        // this reduces flickering drastically
        this.setDoubleBuffered(true);

    }    

    @Override
    protected Graphics2D getGraphics2D(final GC gc, final Device device) {
        graphics.setDevice(device);
        graphics.setGC(gc);
        return (Graphics2D) graphics;
    }

    /**
     * With this specialized implementation I register customized event listeners that do not
     * translate SWT events into AWT ones.
     */
    @Override
    protected void installInputSources() {
        // TODO for the moment we need the original ones, too, as long as the the 
        //  PSWTSimpleSelectionEventHandler is not migrated to the custom listeners
        super.installInputSources();
        
        this.addKeyListener(new KlighdKeyEventListener(this));
        KlighdMouseEventListener mouseListener = new KlighdMouseEventListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMoveListener(mouseListener);
        this.addMouseTrackListener(mouseListener);
        this.addMouseWheelListener(mouseListener);
    }

    /**
     * {@inheritDoc}.<br>
     * <br>
     * This specialized method checks the validity of the canvas
     * before something is painted in order to avoid the 'Widget is disposed' errors.
     */
    public void repaint(final PBounds bounds) {
        if (!this.isDisposed()) {
            super.repaint(bounds);
        }
    }
}
