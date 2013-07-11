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
package de.cau.cs.kieler.klighd.piccolo.krendering.viewer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.krendering.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolox.swt.PSWTCanvas;

/**
 * A content outline page for the Piccolo viewer.
 * 
 * @author msp
 * @author uru
 */
public class PiccoloOutlinePage implements IContentOutlinePage {

    /** the canvas used for drawing. */
    private PSWTCanvas canvas;
    /** the graph layer to display. */
    private PLayer graphLayer;
    /** the layout data of the observed parent node. */
    private KShapeLayout graphLayout;
    /** the adapter listening to layout changes. */
    private Adapter graphLayoutAdapter;
    /** the control listener reacting to canvas resizing. */
    private ControlListener canvasResizeListener;
    /** the original camera of the editor part. */
    private PCamera originalCamera;
    /** the element that holds the outline rectangle. */
    private PSWTAdvancedPath outlineRect;

    // Properties for the appearance of the outline rectangle
    private static final int OUTLINE_EDGE_ROUNDNESS = 5;
    private static final int OUTLINE_EDGE_OPACITY = 25;
    private static final Color OUTLINE_EDGE_COLOR = new Color(0, 0, 200);

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        canvas = new PSWTCanvas(parent, SWT.NONE) {

            private KlighdSWTGraphics graphics = new KlighdSWTGraphicsImpl(null,
                    parent.getDisplay());

            @Override
            protected Graphics2D getGraphics2D(final GC gc, final Device device) {
                graphics.setDevice(device);
                graphics.setGC(gc);
                return (Graphics2D) graphics;
            }
        };
        // reduce flickering
        canvas.setDoubleBuffered(true);
        setContent(graphLayer);
    }

    /**
     * {@inheritDoc}
     */
    public Control getControl() {
        return canvas;
    }

    /**
     * {@inheritDoc}
     */
    public void setFocus() {
        canvas.setFocus();
    }

    /**
     * Update the content of the outline page.
     * 
     * @param newLayer
     *            the graph layer to display
     */
    public void setContent(final PLayer newLayer) {

        if (canvas != null) {
            if (this.graphLayer != null) {

                if (graphLayer.getCameraCount() == 0) {
                    throw new IllegalStateException(
                            "The PLayer passed to the PiccoloOutlineView has "
                                    + "to contain at least one camera.");
                }
                originalCamera = graphLayer.getCamera(0);

                this.graphLayer.getRoot().removeChild(canvas.getCamera());
                this.graphLayer.removeCamera(canvas.getCamera());

                // listen to property changes of the root element
                if (graphLayer.getChildrenCount() > 0) {
                    graphLayer.getChild(0).addPropertyChangeListener(new PropertyChangeListener() {

                        public void propertyChange(final PropertyChangeEvent evt) {
                            adjustOutlineRect();
                        }
                    });
                }

                // listen to view transformations
                originalCamera.addPropertyChangeListener(new PropertyChangeListener() {

                    public void propertyChange(final PropertyChangeEvent evt) {
                        if (evt.getPropertyName().equals("viewTransform")) {
                            adjustOutlineRect();
                        }

                    }
                });
            }
            if (graphLayout != null) {
                graphLayout.eAdapters().remove(graphLayoutAdapter);
                graphLayout = null;
            }
            if (canvasResizeListener != null) {
                canvas.removeControlListener(canvasResizeListener);
                canvasResizeListener = null;
            }

            // install a new camera into the given layer
            final PCamera camera = new PCamera();
            newLayer.getRoot().addChild(camera);
            camera.addLayer(newLayer);

            // add a new layer to the new camera that contains a rectangle indicating the visible
            // part of the model
            PLayer outlineLayer = new PLayer();
            PBounds bounds = originalCamera.getBounds();
            // configure the outline rectangle
            outlineRect =
                    PSWTAdvancedPath.createRoundRectangle((float) bounds.x, (float) bounds.y,
                            (float) bounds.width, (float) bounds.height, OUTLINE_EDGE_ROUNDNESS,
                            OUTLINE_EDGE_ROUNDNESS);
            outlineRect.setPaint(OUTLINE_EDGE_COLOR);
            outlineRect.setPaintAlpha(OUTLINE_EDGE_OPACITY);
            camera.addLayer(outlineLayer);
            outlineLayer.addChild(outlineRect);

            canvas.setCamera(camera);

            // add a handler to the outline canvas to allow dragging
            canvas.addInputEventListener(new OutlineDragHandler());

            // add listeners to layout changes and canvas resizing
            PNode childNode = newLayer.getChild(0);
            if (childNode instanceof KNodeTopNode) {
                graphLayout =
                        ((KNodeTopNode) childNode).getGraphElement().getData(KShapeLayout.class);
                adjustCamera(camera);
                graphLayoutAdapter = new AdapterImpl() {
                    public void notifyChanged(final Notification notification) {
                        int featureId = notification.getFeatureID(KShapeLayout.class);
                        if (featureId == KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH
                                || featureId == KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT
                                || featureId == KLayoutDataPackage.KSHAPE_LAYOUT__XPOS
                                || featureId == KLayoutDataPackage.KSHAPE_LAYOUT__YPOS) {
                            adjustCamera(camera);
                        }
                    }
                };
                graphLayout.eAdapters().add(graphLayoutAdapter);
                canvasResizeListener = new ControlListener() {
                    public void controlMoved(final ControlEvent e) {
                        adjustCamera(camera);
                    }

                    public void controlResized(final ControlEvent e) {
                        adjustCamera(camera);
                    }
                };
                canvas.addControlListener(canvasResizeListener);
            }
        }
        this.graphLayer = newLayer;
    }

    /** the minimal size of the view. */
    private static final float MIN_SIZE = 10.0f;

    /**
     * Adjust the given camera to the bounds of the currently tracked graph layout.
     * 
     * @param camera
     *            a camera
     */
    private void adjustCamera(final PCamera camera) {
        float width = Math.max(graphLayout.getWidth(), MIN_SIZE);
        float height = Math.max(graphLayout.getHeight(), MIN_SIZE);
        camera.setViewBounds(new Rectangle2D.Double(graphLayout.getXpos(), graphLayout.getYpos(),
                width, height));
    }

    /**
     * Adjusts the displayed outline rectangle to the current view snippet.
     */
    private void adjustOutlineRect() {
        // get the new bounds
        PBounds bounds = originalCamera.getViewBounds();
        outlineRect.setPathToRoundRectangle((float) bounds.x, (float) bounds.y,
                (float) bounds.width, (float) bounds.height, OUTLINE_EDGE_ROUNDNESS,
                OUTLINE_EDGE_ROUNDNESS);

        // schedule a repaint
        canvas.getCamera().invalidatePaint();
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        // nothing to do here
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
            super.startDrag(event);
            last = event.getPosition();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void drag(final PInputEvent event) {
            super.drag(event);
            Point2D pos = event.getPosition();
            Point2D delta = new Point2D.Double(pos.getX() - last.getX(), pos.getY() - last.getY());
            originalCamera.translateView(-delta.getX(), -delta.getY());
            last = pos;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void endDrag(final PInputEvent event) {
            super.endDrag(event);
            last = null;
        }
    }
}
