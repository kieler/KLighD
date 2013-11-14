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

import de.cau.cs.kieler.klighd.piccolo.internal.Constants;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdFocusEventListener;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdInputManager;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdKeyEventListener;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PInputManager;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.event.PPanEventHandler;
import edu.umd.cs.piccolo.event.PZoomEventHandler;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolox.swt.PSWTCanvas;
import edu.umd.cs.piccolox.swt.PSWTRoot;

/**
 * A specialized version of {@link PSWTCanvas} with lots of KLighD-specific customizations.
 *
 * @author chsch
 */
public class KlighdCanvas extends PSWTCanvas {

    /**
     * The {@link de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics KlighdSWTGraphics} abstractor to
     * be incorporated while drawing the diagram.
     */
    private KlighdSWTGraphicsEx graphics;

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

        // remove the original event handlers as they require AWT event type codes
        //  instances of this class are augment with SWT-based event handlers
        //  e.g. in PiccoloViewer or PiccoloOutlinePage
        this.removeInputEventListener(this.getZoomEventHandler());
        this.removeInputEventListener(this.getPanEventHandler());
        
        this.graphics = new KlighdSWTGraphicsImpl(null, parent.getDisplay());
        this.getRoot().addAttribute(Constants.DEVICE, parent.getDisplay());
        this.getRoot().addAttribute(Constants.MAIN_CAMERA, this.getCamera());

        // this reduces flickering drastically
        this.setDoubleBuffered(true);

    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization is required for injecting the specialized root node providing the
     * KLighD-specific {@link KlighdInputManager}. This input manager replaces the event evaluation
     * based on AWT's event type codes by one based on SWT's event type codes.<br>
     * <br>
     * Besides, no initial instance of {@link edu.umd.cs.piccolo.PLayer PLayer} is added - our
     * {@link KNodeTopNode} takes that part; see
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController#DiagramController(
     * de.cau.cs.kieler.core.kgraph.KNode, PCamera, boolean)
     * DiagramController.DiagramController(KNode, PCamera, boolean)}.
     */
    @Override // SUPPRESS CHECKSTYLE Javadoc, see http://sourceforge.net/p/checkstyle/bugs/592/
    public PCamera createBasicSceneGraph() {
        final PRoot r = new PSWTRoot(this) {
            private static final long serialVersionUID = -4737922663028304522L;

            private PInputManager inputManager = null;
            
            @Override
            public PInputManager getDefaultInputManager() {
                if (inputManager == null) {
                    inputManager = new KlighdInputManager();
                    addInputSource(inputManager);
                }
                return inputManager;
            }
        };
        
        final PCamera c = new PCamera();
        r.addChild(c);
        return c;
    }

    @Override
    protected Graphics2D getGraphics2D(final GC gc, final Device device) {
        graphics.setDevice(device);
        graphics.setGC(gc);
        return (Graphics2D) graphics;
    }

    /**
     * With this specialized implementation I register customized event listeners that do not
     * translate SWT events into AWT ones. The original event listeners are omitted.
     */
    @Override
    protected void installInputSources() {

        this.addFocusListener(new KlighdFocusEventListener(this));
        this.addKeyListener(new KlighdKeyEventListener(this));
        
        final KlighdMouseEventListener mouseListener = new KlighdMouseEventListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMoveListener(mouseListener);
        this.addMouseTrackListener(mouseListener);
        this.addMouseWheelListener(mouseListener);
        this.addGestureListener(mouseListener);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PPanEventHandler getPanEventHandler() {
        final String msg = "KLighD Piccolo viewer: "
                + "Method is not supported as a different pan event handler is deployed.";
        throw new UnsupportedOperationException(msg);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PZoomEventHandler getZoomEventHandler() {
        final String msg = "KLighD Piccolo viewer: "
                + "Method is not supported as a different zoom event handler is deployed.";
        throw new UnsupportedOperationException(msg);
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialized method checks the validity of the canvas
     * before something is painted in order to avoid the 'Widget is disposed' errors.
     */
    public void repaint(final PBounds bounds) {
        if (!this.isDisposed()) {
            super.repaint(bounds);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        
        if (this.graphics != null) {
            this.graphics.dispose();
        }
    }
}
