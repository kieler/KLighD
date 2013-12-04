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
package de.cau.cs.kieler.klighd.piccolo.viewer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdBasicInputEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.util.LimitedKGraphContentAdapter;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolox.swt.SWTTimer;

/**
 * A content outline page for the Piccolo viewer.
 * 
 * @author msp
 * @author uru
 */
public class PiccoloOutlinePage implements IContentOutlinePage {

    /** the canvas used for drawing. */
    private KlighdCanvas outlineCanvas;
    /** the graph layer to display. */
    private KNodeTopNode topNode;
    /** the observed knode. */
    private KNode rootNode;
    /** the adapter listening to layout changes. */
    private Adapter nodeLayoutAdapter;

    /** the element that holds the outline rectangle. */
    private KlighdPath outlineRect;

    // Properties for the appearance of the outline rectangle.
    private static final int OUTLINE_EDGE_ROUNDNESS = 5;
    private static final int OUTLINE_EDGE_OPACITY = 25;
    private static final Color OUTLINE_EDGE_COLOR = new Color(0, 0, 200);

    // Delay of the timed repaint.
    private static final int REPAINT_DELAY = 200;

    /** Indicates whether a drag action on the outline canvas is in progress. */
    private boolean isDragging = false;
    /** A timer to reduce the load during panning of the original canvas. */
    private SWTTimer outlineRectTimer;

    /**
     * Property Listener that listens to changes on the original canvas and triggers a redraw of the
     * outline rectangle.
     */
    private PropertyChangeListener propertyListener = new PropertyChangeListener() {

        public void propertyChange(final PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals(PCamera.PROPERTY_VIEW_TRANSFORM)
                    || evt.getPropertyName().equals(PNode.PROPERTY_FULL_BOUNDS)) {

                // only use the timer if we are not dragging
                if (isDragging) {
                    adjustOutlineRect();
                } else {
                    if (outlineRectTimer != null) {
                        outlineRectTimer.restart();
                    }
                }
            }
        }
    };

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        outlineCanvas = new KlighdCanvas(parent, SWT.NONE);

        // add a handler to the outline canvas to allow dragging
        outlineCanvas.addInputEventListener(new KlighdBasicInputEventHandler(new OutlineDragHandler()));


        // initialize the timers to redraw the outline rect
        outlineRectTimer = new SWTTimer(parent.getDisplay(), REPAINT_DELAY, new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
                adjustOutlineRect();
            }
        });

        outlineRectTimer.setRepeats(false);
        outlineRectTimer.start();

        // create the actual outline view elements
        setContent(topNode);
    }

    /**
     * {@inheritDoc}
     */
    public Control getControl() {
        return outlineCanvas;
    }

    /**
     * {@inheritDoc}
     */
    public void setFocus() {
        outlineCanvas.setFocus();
    }

    /**
     * Update the content of the outline page.
     * 
     * @param newTopNode
     *            the {@link KNodeTopNode} to be displayed
     */
    public void setContent(final KNodeTopNode newTopNode) {
        if (outlineCanvas == null) {
            this.topNode = newTopNode;
            return;
        } 

        if (topNode != null && topNode != newTopNode) {
            // detach the propertyListener from the previously observed top node
            //  this' outlineCanvas' camera from the former topNode
            if (topNode.getCameraCount() == 0) {
                throw new IllegalStateException(
                        "The PLayer passed to the PiccoloOutlineView has "
                                + "to contain at least one camera.");
            }

            this.topNode.getDiagramMainCamera().removePropertyChangeListener(propertyListener);
            this.outlineCanvas.getCamera().removeChild(this.topNode);
        }
        
        this.topNode = newTopNode;
        
        final PCamera diagramMainCamera = topNode.getDiagramMainCamera();
        
        // listen to view transformations
        diagramMainCamera.addPropertyChangeListener(propertyListener);

        final KlighdMainCamera camera = outlineCanvas.getCamera();
        
        // install the outlineCanvas' camera into the given (new) topNode 
        camera.addLayer(topNode);

        // add a further layer to the camera
        //  that contains a rectangle indicating the visible part of the model
        final PLayer overlayLayer = new PLayer();
        camera.addLayer(overlayLayer);
        
        // initialize & configure the outline rectangle
        //  the concrete path data are set later on in #adjustCamera();
        outlineRect = new KlighdPath();
        outlineRect.setPaint(OUTLINE_EDGE_COLOR);
        outlineRect.setPaintAlpha(OUTLINE_EDGE_OPACITY);
        overlayLayer.addChild(outlineRect);

        // add listeners to layout changes and canvas resizing
        rootNode = topNode.getGraphElement();
        nodeLayoutAdapter = new LimitedKGraphContentAdapter(KShapeLayout.class) {
           
            @Override
            public void notifyChanged(final Notification notification) {
                super.notifyChanged(notification);
                
                if (notification.getNotifier() == rootNode) {
                    // in case anything is changed on the node, e.g. the node's shape layout
                    //  is removed or a new one is added by the simple update strategy
                    //  don't do anything!! 
                    return;
                }
                
                int featureId = notification.getFeatureID(KShapeLayout.class);
                if (featureId == KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH
                        || featureId == KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT
                        || featureId == KLayoutDataPackage.KSHAPE_LAYOUT__XPOS
                        || featureId == KLayoutDataPackage.KSHAPE_LAYOUT__YPOS) {
                    adjustCamera();
                }
            }
        };
        
        rootNode.eAdapters().add(nodeLayoutAdapter);
        outlineCanvas.addControlListener(canvasResizeListener);
        
        adjustCamera();
    }

    /** the control listener reacting to canvas resizing. */
    private ControlListener canvasResizeListener = new ControlListener() {
        public void controlMoved(final ControlEvent e) {
            adjustCamera();
        }

        public void controlResized(final ControlEvent e) {
            adjustCamera();
        }
    };


    /** the minimal size of the view. */
    private static final float MIN_SIZE = 10.0f;

    /**
     * Adjust the view bounds of this' outlineCanvas' camera to the bounds of the currently tracked
     * graph layout. Note, that the bounds of the canvas are automatically updated of the size of
     * the outline page change, since the outlineCanvas is provided by this class via
     * {@link #getControl()}. The canvas in turn immediately updates the bounds of its camera, see
     * {@link edu.umd.cs.piccolox.swt.PSWTCanvas#setBounds(int, int, int, int)
     * PSWTCanvas#.setBounds(int, int, int, int)}. Thus we don't need to care about the canvas' and
     * camera's (full) bounds and can limit the modifications to the camera's view bounds (/view
     * transform).
     */
    private void adjustCamera() {
        // always reveal the current shape layout - it may be exchanged over the diagram's life time
        final KShapeLayout layoutData = rootNode.getData(KShapeLayout.class);
        
        float width = Math.max(layoutData.getWidth(), MIN_SIZE);
        float height = Math.max(layoutData.getHeight(), MIN_SIZE);
        outlineCanvas.getCamera().setViewBounds(
                new Rectangle2D.Double(layoutData.getXpos(), layoutData.getYpos(), width, height));
    }


    /**
     * Adjusts the displayed outline rectangle to the current view snippet.
     */
    private void adjustOutlineRect() {
        final PCamera originalCamera = topNode.getDiagramMainCamera();
        if (originalCamera == null) {
            return;
        }
        
        // get the new bounds
        final PBounds bounds = originalCamera.getViewBounds();
        outlineRect.setPathToRoundRectangle((float) bounds.x, (float) bounds.y,
                (float) bounds.width, (float) bounds.height, OUTLINE_EDGE_ROUNDNESS,
                OUTLINE_EDGE_ROUNDNESS);

        // schedule a repaint
        outlineCanvas.getCamera().invalidatePaint();
    }


    private boolean disposed;

    /**
     * Getter.
     * 
     * @return <code>true</code> if {@link #dispose()} has been called on this outline page
     */
    public boolean isDisposed() {
        return disposed;
    }
    
    /**
     * {@inheritDoc}
     */
    public void dispose() {
        final PCamera originalCamera = topNode.getDiagramMainCamera();

        outlineRectTimer = null;

        if (originalCamera != null) {
            // remove all the listeners!
            originalCamera.removePropertyChangeListener(propertyListener);
        }

        if (rootNode != null) {
            rootNode.eAdapters().remove(nodeLayoutAdapter);
            rootNode = null;
            nodeLayoutAdapter = null;
        }
        if (canvasResizeListener != null) {
            if (!outlineCanvas.isDisposed()) {
                outlineCanvas.removeControlListener(canvasResizeListener);
            }
            canvasResizeListener = null;
        }
        
        // the canvas, which is accessible by the platform via #getControl()
        //  is disposed separately by the platform

        this.disposed = true;
    }


    /**
     * {@inheritDoc}
     */
    public void setActionBars(final IActionBars actionBars) {
        // no action to register
    }

    /**
     * {@inheritDoc}
     */
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
        // selection is not supported by this outline page
    }

    /**
     * {@inheritDoc}
     */
    public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
        // selection is not supported by this outline page
    }

    /**
     * {@inheritDoc}
     */
    public ISelection getSelection() {
        // selection is not supported by this outline page
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setSelection(final ISelection selection) {
        // selection is not supported by this outline page
    }


    /**
     * A drag handler that allows the user to drag the outline rectangle within the outline view and
     * propagates the movement to the actual editor part.
     * 
     * @author uru
     */
    private class OutlineDragHandler extends PDragSequenceEventHandler {

        /** reference point for the drag motion. */
        private Point2D last = null;

        /**
         * {@inheritDoc}
         */
        @Override
        protected void startDrag(final PInputEvent event) {
            isDragging = true;
            super.startDrag(event);
            last = event.getPosition();

            final PCamera originalCamera = topNode.getDiagramMainCamera();
            final PBounds outlineRectBounds = originalCamera.getViewBounds();

            // if the user clicks outside the outline rect,
            // center it on this point before dragging starts
            boolean withinRect = outlineRectBounds.contains(event.getPosition());
            if (!withinRect) {
                // translate the camera by the delta between click
                // and current center point of the bounds
                Point2D center = outlineRectBounds.getCenter2D();
                Point2D delta =
                        new Point2D.Double(center.getX() - event.getPosition().getX(),
                                center.getY() - event.getPosition().getY());
                originalCamera.translateView(delta.getX(), delta.getY());
            }

        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void drag(final PInputEvent event) {
            super.drag(event);
            Point2D pos = event.getPosition();
            Point2D delta = new Point2D.Double(pos.getX() - last.getX(), pos.getY() - last.getY());
            topNode.getDiagramMainCamera().translateView(-delta.getX(), -delta.getY());
            last = pos;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void endDrag(final PInputEvent event) {
            super.endDrag(event);
            last = null;
            isDragging = false;
        }
    }
}
