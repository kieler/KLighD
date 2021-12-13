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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.piccolo.internal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdFocusEventListener;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdInputManager;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdKeyEventListener;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.NodeDisposeListener;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.PCanvas;
import edu.umd.cs.piccolo.PComponent;
import edu.umd.cs.piccolo.PInputManager;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.event.PInputEventListener;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PDebug;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;
import edu.umd.cs.piccolo.util.PStack;
import edu.umd.cs.piccolox.swt.SWTTimer;

/**
 * A specialized version of {@link PCanvas} based on {@link edu.umd.cs.piccolox.swt.PSWTCanvas} with
 * lots of KLighD-specific customizations.
 *
 * @author chsch
 */
public class KlighdCanvas extends Composite implements PComponent {

    /**
     * The {@link de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics KlighdSWTGraphics} abstractor to
     * be incorporated while drawing the diagram.
     */
    private KlighdSWTGraphicsEx graphics;

    /**
     * The {@link PInputManager} employed in this diagram canvas, is cached here for the sake of
     * squeezing out some more performance, which is most effective while dispatching mouse move
     * events.
     */
    private PInputManager inputManager;

    /** The diagram's main camera providing zooming and panning. */
    private KlighdMainCamera camera;

    /**
     * Construct a canvas with the basic scene graph consisting of a root node, a camera, and a
     * layer (via <code>super(...)</code> and {@link #createBasicSceneGraph()}). The original event
     * handlers for zooming and panning coming via <code>super(...)</code> are removed, appropriate
     * ones are installed later on by users of this class.
     *
     * @param parent
     *            component onto which the canvas is installed
     * @param style
     *            {@link Composite} style of <code>this</code> {@link KlighdCanvas}
     */
    public KlighdCanvas(final Composite parent, final int style) {
        this(parent, style, KlighdProperties.CANVAS_COLOR.getDefault());
    }
    
    /**
     * Construct a canvas with the basic scene graph consisting of a root node, a camera, and a
     * layer (via <code>super(...)</code> and {@link #createBasicSceneGraph()}). The original event
     * handlers for zooming and panning coming via <code>super(...)</code> are removed, appropriate
     * ones are installed later on by users of this class.
     *
     * @param parent
     *            component onto which the canvas is installed
     * @param style
     *            {@link Composite} style of <code>this</code> {@link KlighdCanvas}
     * @param backgroundColor
     *            The {@link Color} of the canvas background
     */
    public KlighdCanvas(final Composite parent, final int style, final Color backgroundColor) {
        super(parent, style | SWT.NO_BACKGROUND | SWT.NO_REDRAW_RESIZE);

        cursorStack = new PStack();
        setCamera(createBasicSceneGraph());
        installInputSources();

        installPaintListener();
        installDisposeListener();
        
        // Set the background color of the canvas
        this.backgroundColor = backgroundColor;
        
        this.graphics = new KlighdSWTGraphicsImpl(parent.getDisplay());

        // this reduces flickering drastically
        this.setDoubleBuffered(true);

        this.addDisposeListener(new DisposeListener() {

            public void widgetDisposed(final DisposeEvent e) {
                final KlighdCanvas thisCanvas = KlighdCanvas.this;

                NodeDisposeListener.disposePNode(thisCanvas.getCamera().getRoot());

                // this horrible static reference may preserve a pick path containing
                //  a reference to the camera containing a reference to ...,
                //  thus. preventing the gc from disposing almost every diagram data
                PPickPath.CURRENT_PICK_PATH = null;

                // this way the back buffer image will be disposed!
                thisCanvas.setDoubleBuffered(false);

                if (thisCanvas.graphics != null) {
                    thisCanvas.graphics.dispose();
                    thisCanvas.graphics = null;
                }
                if (thisCanvas.inputManager != null) {
                    thisCanvas.inputManager = null;
                }
            }
        });
    }

    /**
     * Specialized {@link PRoot} inspired by {@link edu.umd.cs.piccolox.swt.PSWTRoot}
     *  and contributing the customized {@link KlighdInputManager}.
     *
     * @author chsch
     */
    protected class KlighdRoot extends PRoot {

        private static final long serialVersionUID = 5244208939770217529L;

        private final Composite composite = KlighdCanvas.this;
        private PInputManager inputManager = null;

        /**
         * Standard constructor.
         */
        public KlighdRoot() {
        }

        @Override
        public PInputManager getDefaultInputManager() {
            if (inputManager == null) {
                inputManager = new KlighdInputManager();
                addInputSource(inputManager);

                if (!KlighdCanvas.this.isDisposed()) {
                    // TODO: Make sure this check doesn't lead to a memory leak 
                    KlighdCanvas.this.addListener(SWT.Dispose, new Listener() {
                        public void handleEvent(final Event event) {
                            removeInputSource(inputManager);
                            inputManager = null;
                        }
                    });
                }
            }
            return inputManager;
        }

        /**
         * Creates a timer that will fire the listener every delay milliseconds.
         * 
         * @param delay time in milliseconds between firings of listener
         * @param listener listener to be fired
         * 
         * @return the created timer
         */
        public Timer createTimer(final int delay, final ActionListener listener) {
            return new SWTTimer(composite.getDisplay(), delay, listener);
        }

        /**
         * Processes Inputs if any kind of IO needs to be done.
         */
        public void scheduleProcessInputsIfNeeded() {
            if (composite.isDisposed()
                    || !Thread.currentThread().equals(composite.getDisplay().getThread())) {
                return;
            }

            if (!processInputsScheduled && !processingInputs
                    && (getFullBoundsInvalid() || getChildBoundsInvalid() || getPaintInvalid() || getChildPaintInvalid())) {

                processInputsScheduled = true;
                composite.getDisplay().asyncExec(new Runnable() {
                    public void run() {
                        processInputs();
                        processInputsScheduled = false;
                    }
                });
            }
        }
    }

    /**
     * Builds the basic scene graph associated with this canvas. Developers may
     * override this method to install their own layers, and cameras.
     * <br>
     * This customization is required for injecting the specialized root node providing the
     * KLighD-specific {@link KlighdInputManager}. This input manager replaces the event evaluation
     * based on AWT's event type codes by one based on SWT's event type codes.<br>
     * <br>
     * Besides, no initial instance of {@link edu.umd.cs.piccolo.PLayer PLayer} is added - our
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode KNodeTopNode} takes that
     * part; see
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController#DiagramController(
     * de.cau.cs.kieler.klighd.kgraph.KNode, KlighdMainCamera, boolean, boolean)
     * DiagramController.DiagramController(KNode, KlighdMainCamera, boolean, boolean)}.
     * 
     * @return KlighdMainCamera viewing the freshly created scene
     */
    public KlighdMainCamera createBasicSceneGraph() {
        return new KlighdMainCamera(new KlighdRoot());
    }

    protected Graphics2D getGraphics2D(final GC gc, final Device device) {
        graphics.setDevice(device);
        graphics.setGC(gc);
        return (Graphics2D) graphics;
    }

    protected KlighdPaintContext getPaintContext(final Graphics2D g2) {
        return KlighdPaintContext.createDiagramPaintContext(graphics);
    }

    /**
     * Return the camera associated with this canvas. All input events from this
     * canvas go through this camera. And this is the camera that paints this
     * canvas.
     * 
     * @return the camera associated with this canvas
     */
    public KlighdMainCamera getCamera() {
        return this.camera;
    }

    /**
     * This method installs mouse and key listeners on the canvas that forward
     * those events to Piccolo2D.
     */
    protected void installInputSources() {

        this.addFocusListener(new KlighdFocusEventListener(this));
        this.addKeyListener(new KlighdKeyEventListener(this));

        final KlighdMouseEventListener mouseListener = new KlighdMouseEventListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMoveListener(mouseListener);
        this.addMouseTrackListener(mouseListener);
        this.addMouseWheelListener(mouseListener);
        this.addDragDetectListener(mouseListener);
        this.addGestureListener(mouseListener);
    }

    /**
     * Dispatches the given event to the default input manager for the root of
     * this canvas.
     * 
     * @param awtEvent awt event needing dispatching
     * @param type type of the event
     */
    public void sendInputEventToInputManager(final InputEvent awtEvent, final int type) {
        // instead of calling the super implementation
        //  save some performance and keep and call the input manager directly

        if (this.inputManager == null) {
            this.inputManager = getRoot().getDefaultInputManager();
        }
        this.inputManager.processEventFromCamera(awtEvent, type, getCamera());
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialization simply deactivates the inherited behavior. Since we do not (have
     * opportunities to?) change the rendering quality, there is no need repaint the whole visible
     * diagram area after user interaction.
     */
    @Override
    public void setInteracting(final boolean isInteracting) {
    }

    /**
     * Exists to dispatch from the Swing's repaint method to SWT's redraw method.<br>
     * Checks the validity of the canvas before something is painted in order to avoid the 'Widget
     * is disposed' errors.
     */
    public void repaint() {
        if (!this.isDisposed()) {
            super.redraw();
        }
    }

    /**
     * Flags the bounds provided as needing to be redrawn.<br>
     * Checks the validity of the canvas before something is painted in order to avoid the 'Widget
     * is disposed' errors.
     * 
     * @param bounds
     *            the bounds that should be repainted
     */
    @Override
    public void repaint(final PBounds bounds) {
        if (!this.isDisposed()) {
            bounds.expandNearestIntegerDimensions();
            bounds.inset(-1, -1);

            redraw((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height, true);
        }
    }

    /**
     * Changes the bounds of this PSWTCanvas. Updating the camera and the double
     * buffered image appropriately.
     * 
     * @param rectangle the new bounds
     */
    public void setBounds(final Rectangle rectangle) {
        this.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public void setBounds(final int x, final int y, final int newWidth, final int newHeight) {
        // extracted the following check from the super implementation
        //  in order to allow to roll back most of the customizations in the Piccolo2D code some day
        if (newWidth == 0 || newHeight == 0) {
            // chsch: introduced this check as the workbench sometimes determines width
            //  and/or height of zero that results in an exception later on.
            return;
        } else {
            final PBounds bounds = getCamera().getBoundsReference();
            final boolean resize = bounds.width != newWidth || bounds.height != newHeight;

            camera.setBounds(camera.getX(), camera.getY(), newWidth, newHeight);

            if (resize && getDoubleBuffered()) {
                resizeBackBuffer(newWidth, newHeight);
            }

            super.setBounds(x, y, newWidth, newHeight);
        }
    }

    private void resizeBackBuffer(final int newWidth, final int newHeight) {
        if (backBuffer != null) {
            backBuffer.dispose();
        }
        backBuffer = new Image(getDisplay(), newWidth, newHeight);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private Image backBuffer;
    private boolean doubleBuffered = true;
    private final PStack cursorStack;
    private Cursor curCursor;
    private boolean paintingImmediately;
    private boolean animatingOnLastPaint;

    protected Color backgroundColor;
    
    private void installPaintListener() {
        addPaintListener(new PaintListener() {
            public void paintControl(final PaintEvent pe) {
                paintComponent(pe.gc, pe.x, pe.y, pe.width, pe.height);
            }
        });
    }

    private void installDisposeListener() {
        addDisposeListener(new DisposeListener() {
            public void widgetDisposed(final DisposeEvent de) {
                getRoot().getActivityScheduler().removeAllActivities();
            }
        });
    }

    // ****************************************************************
    // Basic - Methods for accessing common Piccolo2D nodes.
    // ****************************************************************

    /**
     * Set the camera associated with this canvas. All input events from this
     * canvas go through this camera. And this is the camera that paints this
     * canvas.
     * 
     * @param newCamera camera to attach to this canvas
     */
    public void setCamera(final KlighdMainCamera newCamera) {
        if (camera != null) {
            camera.setComponent(null);
        }

        camera = newCamera;

        if (camera != null) {
            camera.setComponent(this);

            final Rectangle swtRect = getBounds();

            camera.setBounds(new Rectangle2D.Double(swtRect.x, swtRect.y, swtRect.width, swtRect.height));
        }
    }

    /**
     * Return root for this canvas.
     * 
     * @return root of the scene this canvas is viewing through its camera
     */
    public PRoot getRoot() {
        return camera.getRoot();
    }

    /**
     * Helper method to return the first layer attached to the camera of this
     * canvas.
     * 
     * Short form of <code>canvas.getCamera.getLayer(0)</code>
     * 
     * @return the first layer attached to the camera of this canvas
     */
    public PLayer getLayer() {
        return camera.getLayer(0);
    }

    /**
     * Add an input listener to the camera associated with this canvas.
     * 
     * @param listener listener to add to to the camera
     */
    public void addInputEventListener(final PInputEventListener listener) {
        getCamera().addInputEventListener(listener);
    }

    /**
     * Remove an input listener to the camera associated with this canvas. Does
     * nothign is the listener is not found.
     * 
     * @param listener listener to remove from the set of event listeners
     *            attached to this canvas.
     */
    public void removeInputEventListener(final PInputEventListener listener) {
        getCamera().removeInputEventListener(listener);
    }

    // ****************************************************************
    // Painting
    // ****************************************************************

    /**
     * Return true if any activities that respond with true to the method
     * isAnimating were run in the last PRoot.processInputs() loop. This values
     * is used by this canvas to determine the render quality to use for the
     * next paint.
     * 
     * @return true if there is an animating activity that is currently active
     */
    public boolean getAnimating() {
        return getRoot().getActivityScheduler().getAnimating();
    }

    /**
     * Get whether this canvas should use double buffering - the default is to
     * double buffer.
     * 
     * @return true if double buffering is enabled
     */
    public boolean getDoubleBuffered() {
        return doubleBuffered;
    }

    /**
     * Set whether this canvas should use double buffering - the default is yes.
     * 
     * @param doubleBuffered value of double buffering flas
     */
    public void setDoubleBuffered(final boolean doubleBuffered) {
        this.doubleBuffered = doubleBuffered;
        if (!doubleBuffered && backBuffer != null) {
            backBuffer.dispose();
            backBuffer = null;
        }
    }

    /**
     * Set the canvas cursor, and remember the previous cursor on the cursor
     * stack. Under the hood it is mapping the java.awt.Cursor to
     * org.eclipse.swt.graphics.Cursor objects.
     * 
     * @param newCursor new cursor to push onto the cursor stack
     */
    public void pushCursor(final java.awt.Cursor newCursor) {
        Cursor swtCursor = null;
        if (newCursor.getType() == java.awt.Cursor.N_RESIZE_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_SIZEN);
        }
        else if (newCursor.getType() == java.awt.Cursor.NE_RESIZE_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_SIZENE);
        }
        else if (newCursor.getType() == java.awt.Cursor.NW_RESIZE_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_SIZENW);
        }
        else if (newCursor.getType() == java.awt.Cursor.S_RESIZE_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_SIZES);
        }
        else if (newCursor.getType() == java.awt.Cursor.SE_RESIZE_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_SIZESE);
        }
        else if (newCursor.getType() == java.awt.Cursor.SW_RESIZE_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_SIZESW);
        }
        else if (newCursor.getType() == java.awt.Cursor.E_RESIZE_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_SIZEE);
        }
        else if (newCursor.getType() == java.awt.Cursor.W_RESIZE_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_SIZEW);
        }
        else if (newCursor.getType() == java.awt.Cursor.TEXT_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_IBEAM);
        }
        else if (newCursor.getType() == java.awt.Cursor.HAND_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_HAND);
        }
        else if (newCursor.getType() == java.awt.Cursor.MOVE_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_SIZEALL);
        }
        else if (newCursor.getType() == java.awt.Cursor.CROSSHAIR_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_CROSS);
        }
        else if (newCursor.getType() == java.awt.Cursor.WAIT_CURSOR) {
            swtCursor = new Cursor(getDisplay(), SWT.CURSOR_WAIT);
        }

        if (swtCursor != null) {
            if (curCursor != null) {
                cursorStack.push(curCursor);
            }
            curCursor = swtCursor;
            setCursor(swtCursor);
        }
    }

    /**
     * Pop the cursor on top of the cursorStack and set it as the canvas cursor.
     */
    public void popCursor() {
        if (curCursor != null) {
            // We must manually dispose of cursors under SWT
            curCursor.dispose();
        }

        if (cursorStack.isEmpty()) {
            curCursor = null;
        }
        else {
            curCursor = (Cursor) cursorStack.pop();
        }

        // This sets the cursor back to default
        setCursor(curCursor);
    }

    /**
     * Paints the region specified of the canvas onto the given Graphics
     * Context.
     * 
     * @param gc graphics onto within painting should occur
     * @param x left of the dirty region
     * @param y top of the dirty region
     * @param w width of the dirty region
     * @param h height of the dirty region
     */
    public void paintComponent(final GC gc, final int x, final int y, final int w, final int h) {
        PDebug.startProcessingOutput();

        GC imageGC = null;
        Graphics2D g2 = null;
        if (doubleBuffered) {
            imageGC = new GC(backBuffer);
            g2 = getGraphics2D(imageGC, getDisplay());
        }
        else {
            g2 = getGraphics2D(gc, getDisplay());
        }

        g2.setColor(backgroundColor);
        g2.setBackground(backgroundColor);

        final Rectangle rect = getBounds();
        g2.fillRect(0, 0, rect.width, rect.height);

        // This fixes a problem with standard debugging of region management in
        // SWT
        if (PDebug.debugRegionManagement) {
            final Rectangle r = gc.getClipping();
            final Rectangle2D r2 = new Rectangle2D.Double(r.x, r.y, r.width, r.height);
            g2.setBackground(PDebug.getDebugPaintColor());
            g2.fill(r2);
        }

        // create new paint context
        final PPaintContext paintContext = getPaintContext(g2);

        // paint Piccolo2D
        camera.fullPaint(paintContext);

        // if switched state from animating to not animating invalidate
        // the entire screen so that it will be drawn with the default instead
        // of animating render quality.
        if (animatingOnLastPaint && !getAnimating()) {
            repaint();
        }
        animatingOnLastPaint = getAnimating();

        final boolean region = PDebug.debugRegionManagement;
        PDebug.debugRegionManagement = false;
        PDebug.endProcessingOutput(g2);
        PDebug.debugRegionManagement = region;

        if (doubleBuffered) {
            gc.drawImage(backBuffer, 0, 0);

            // Dispose of the allocated image gc
            imageGC.dispose();
        }
    }
    
    /**
     * Performs an immediate repaint if no other client is currently performing
     * one.
     */
    public void paintImmediately() {
        if (paintingImmediately) {
            return;
        }

        paintingImmediately = true;
        redraw();
        update();
        paintingImmediately = false;
    }

}
