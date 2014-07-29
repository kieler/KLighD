/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.klighd.piccolo.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Timer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.ViewChangeType;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.IDiagramOutlinePage;
import de.cau.cs.kieler.klighd.internal.ILayoutRecorder;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdActionEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdBasicInputEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseWheelZoomEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdPanEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdSelectionEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdSelectiveZoomEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdShowLensEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import de.cau.cs.kieler.klighd.viewers.AbstractViewer;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.POffscreenCanvas;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A viewer for Piccolo2D diagram contexts.
 * 
 * @author mri
 * @author chsch
 */
public class PiccoloViewer extends AbstractViewer<KNode> implements ILayoutRecorder,
        IDiagramOutlinePage.Provider {

    private static final int VIEW_PORT_CHANGE_NOTIFY_DELAY = 250; // ms

    /** the canvas used for drawing. */
    private KlighdCanvas canvas;
    /** the content outline page. */
    private PiccoloOutlinePage outlinePage;

    /** the parent viewer. */
    private ContextViewer parentViewer;
    /** the graph controller. */
    private DiagramController controller;

    /**
     * Creates a Piccolo2D viewer with default style.
     * 
     * @param parentViewer
     *            the parent {@link ContextViewer}
     * @param parent
     *            the parent composite
     */
    public PiccoloViewer(final ContextViewer parentViewer, final Composite parent) {
        this(parentViewer, parent, SWT.NONE);
    }

    /**
     * Creates a Piccolo2D viewer with given style.
     * 
     * @param theParentViewer
     *            the parent {@link ContextViewer}
     * @param parent
     *            the parent composite
     * @param style
     *            the style attributes
     */
    public PiccoloViewer(final ContextViewer theParentViewer, final Composite parent,
            final int style) {
        if (parent.isDisposed()) {
            final String msg =
                    "KLighD (piccolo): A 'PiccoloViewer' has been tried to attach to a"
                            + "disposed 'Composite' widget.";
            throw new IllegalArgumentException(msg);
        }
        this.parentViewer = theParentViewer;
        this.canvas = new KlighdCanvas(parent, style);

        final KlighdMainCamera camera = canvas.getCamera();

        // install the required event handlers, they rely on SWT event type codes
        // the order of registering them DOES MATTER,
        //  the first has lowest priority, the last highest
        // make sure those handlers properly execute 'event.setHandled(true);'
        //  in order to skip invoking the less priority handlers
        camera.addInputEventListener(new KlighdActionEventHandler(this));
        camera.addInputEventListener(new KlighdShowLensEventHandler(camera));
        camera.addInputEventListener(new KlighdMouseWheelZoomEventHandler());
        camera.addInputEventListener(new KlighdBasicInputEventHandler(
                new KlighdPanEventHandler(canvas)));
        camera.addInputEventListener(new KlighdBasicInputEventHandler(
                new KlighdSelectiveZoomEventHandler()));
        camera.addInputEventListener(
                new KlighdSelectionEventHandler(theParentViewer));

        // add a tooltip element
        new PiccoloTooltip(parent.getDisplay(), canvas.getCamera());

        // A timer being in charge of buffering and thus aggregating a bunch of single
        // view transform changes occurring closely after each other to a single view
        // change notification. Once the time elapsed without restarting it in the
        // meantime the provided {@link ActionListener#actionPerformed(ActionEvent)}
        // method is called.
        final Timer timer =
                camera.getRoot().createTimer(VIEW_PORT_CHANGE_NOTIFY_DELAY, new ActionListener() {

                    public void actionPerformed(final ActionEvent e) {
                        PiccoloViewer.this.notifyViewChangeListeners(ViewChangeType.VIEW_PORT,
                                null, camera.getViewBounds(), camera.getViewScale());
                    }
                });
        timer.setRepeats(false);

        // Install a listener sensitive to canvas size changes, i.e. view port changes,
        // being in charge of notifying the registered view change listeners of new view port
        // bounds.
        this.canvas.addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(final ControlEvent e) {
                timer.restart();
            }
        });

        // Install a listener sensitive to view transform changes, i.e. view port changes,
        // being in charge of notifying the registered view change listeners of new view port
        // bounds.
        camera.addPropertyChangeListener(PCamera.PROPERTY_VIEW_TRANSFORM,
                new PropertyChangeListener() {

                    /**
                     * Called after each particular change of the camera's view transform.
                     * (Re-)Starts the timer in order to aggregate subsequent notifications, which
                     * occur during animations or manual zooming and panning.
                     */
                    public void propertyChange(final PropertyChangeEvent evt) {
                        timer.restart();
                    }
                });
    }

    /**
     * {@inheritDoc}
     */
    public IDiagramOutlinePage getDiagramOutlinePage() {
        if (outlinePage == null || outlinePage.isDisposed()) {
            outlinePage = createDiagramOutlinePage();
            outlinePage.setContent(this.controller.getNode());
        }
        return outlinePage;
    }

    /**
     * Factory method for creation of a corresponding outline page.<br>
     * To be overridden by subclasses in order to inject specialized outline pages, see e.g.
     * PiccoloViewerProvider in <code>de.cau.cs.kieler.klighd.ui.internal.PiccoloViewer</code>.
     * 
     * @return a {@link PiccoloOutlinePage}
     */
    protected PiccoloOutlinePage createDiagramOutlinePage() {
        return new PiccoloOutlinePage();
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
    public ContextViewer getContextViewer() {
        return this.parentViewer;
    }

    /**
     * {@inheritDoc}
     */
    public ViewContext getViewContext() {
        return this.parentViewer.getViewContext();
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(final KNode model, final boolean sync) {

        final boolean edgesFirst =
                getViewContext().getProperty(KlighdProperties.EDGES_FIRST).booleanValue();

        // create a controller for the graph
        controller = new DiagramController(model, canvas.getCamera(), sync, edgesFirst);

        // update the outline page
        if (outlinePage != null && !outlinePage.isDisposed()) {
            outlinePage.setContent(controller.getNode());
        } else {
            this.outlinePage = null;
        }

    }

    /**
     * {@inheritDoc}
     */
    public void startRecording() {
        controller.startRecording();
    }

    /**
     * {@inheritDoc}
     */
    public void stopRecording(final int animationTime) {
        final ViewContext viewContext = this.getViewContext();
        final ZoomStyle zoomStyle;
        final KNode focusNode;

        // get the zoomStyle
        if (viewContext != null) {
            final ZoomStyle nzs = viewContext.getProperty(KlighdInternalProperties.NEXT_ZOOM_STYLE);
            if (nzs != null) {
                zoomStyle = nzs;
                
                // in case 'nzs' is unequal to ZOOM_TO_FOCUS, the NEXT_FOCUS_NODE is likely to be null,
                //  otherwise it may be null, or may denote to a KNode
                //  - both cases have to be handled properly!
                focusNode = viewContext.getProperty(KlighdInternalProperties.NEXT_FOCUS_NODE);

                viewContext.setProperty(KlighdInternalProperties.NEXT_ZOOM_STYLE, null);
                viewContext.setProperty(KlighdInternalProperties.NEXT_FOCUS_NODE, null);
            } else {
                zoomStyle = this.getViewContext().getZoomStyle();
                focusNode = null;
            }
        } else {
            zoomStyle = ZoomStyle.NONE;
            focusNode = null;
        }

        stopRecording(zoomStyle, focusNode, animationTime);
    }

    /**
     * {@inheritDoc}
     */
    public void stopRecording(final ZoomStyle zoomStyle, final KNode focusNode,
            final int animationTime) {
        controller.stopRecording(zoomStyle, focusNode, animationTime);
    }

    /**
     * Convenience method simplifying the notification call.
     * 
     * @see #notifyViewChangeListeners(ViewChangeType, KGraphElement, java.awt.geom.Rectangle2D,
     *      float)
     * 
     * @param type
     *            the corresponding {@link ViewChangeType}
     * @param affectedElement
     *            a potentially affect few element, e.g. a collapsed or expanded
     *            {@link de.cau.cs.kieler.core.kgraph.KNode KNode}, may be <code>null</code>
     */
    protected void notifyViewChangeListeners(final ViewChangeType type,
            final KGraphElement affectedElement) {
        final PCamera camera = canvas.getCamera();
        super.notifyViewChangeListeners(type, affectedElement, camera.getViewBounds(),
                camera.getViewScale());
                
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDisplayed(final KGraphElement diagramElement, final boolean checkParents) {
        final RenderingContextData contextData = RenderingContextData.basicGet(diagramElement);
        if (contextData == null || !contextData.isActive(diagramElement)) {
            // in this case either node rendering figure exists as of now or it has been
            // removed from the diagram due to node collapse or a hide execution
            return false;
        }

        if (checkParents) {
            // TODO implementation to be fine-tuned wrt. performance etc.
            final PNode node = (PNode) controller.getRepresentation(diagramElement);
            return node != null && NodeUtil.isDisplayed(node, this.canvas.getCamera());

        } else {
            // beyond testing for the 'active' flag I want to at least test the display of the
            // corresponding parent node in case of labels and ports (and their labels)

            if (diagramElement instanceof KNode) {
                // nothing to do
                return true;
            }

            if (diagramElement instanceof KEdge) {
                // edges are handled explicitly at removal of nodes (see DiagramController) so we
                // don't
                // test their source and target node explicitly here.
                return true;
            }

            if (diagramElement instanceof KPort) {
                return isDisplayed(((KPort) diagramElement).getNode(), false);
            }

            if (diagramElement instanceof KLabel) {
                return isDisplayed(((KLabel) diagramElement).getParent(), false);
            }

            // the required default case, should never be executed!
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isVisible(final KGraphElement diagramElement, final boolean checkContainment) {
        return controller.isVisible(diagramElement, checkContainment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void zoomToLevel(final float zoomLevel, final int duration) {
        controller.getZoomController().zoomToLevel(zoomLevel, duration);
    }

    /**
     * {@inheritDoc}
     */
    public void zoomToFocus(final KNode diagramElement, final int duration) {
        controller.getZoomController().zoomToFocus(diagramElement, duration);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void zoom(final ZoomStyle style, final int duration) {
        controller.getZoomController().zoom(style, null, duration);
    }

    /**
     * {@inheritDoc}
     */
    public float getZoomLevel() {
        return (float) canvas.getCamera().getViewScale();
    }


    private static final String NO_DIAGRAM_ELEMENT_REPRESENTATION_ERROR_MSG =
            "KLighD: There is no figure represtation (PNode) of diagramElement XX!";

    private static final String NOT_IN_CURRENT_CLIP_ERROR_MSG =
            "KLighD: The figure representation of diagram element XX is not"
                    + " displayed in the current diagram clipping!";

    private static final String NOT_IN_CURRENT_CLIP_REVEAL_ERROR_MSG =
            NOT_IN_CURRENT_CLIP_ERROR_MSG + " It thus cannot be revealed.";

    /**
     * {@inheritDoc}
     */
    public void reveal(final KGraphElement diagramElement, final int duration) {
        final PNode node = getRepresentation(diagramElement);
        if (node == null) {
            StatusManager.getManager().handle(
                    new Status(IStatus.WARNING, KlighdPiccoloPlugin.PLUGIN_ID, new String(
                            NO_DIAGRAM_ELEMENT_REPRESENTATION_ERROR_MSG).replace("XX",
                            diagramElement.toString())));
        } else {
            final PCamera camera = canvas.getCamera();
            final PBounds destBounds =
                    NodeUtil.clipRelativeGlobalBoundsOf(node, this.canvas.getCamera()
                            .getDisplayedINode());

            if (destBounds != null) {
                // move the camera so it includes the bounds of the node
                camera.animateViewToPanToBounds(destBounds, duration);

            } else {
                StatusManager.getManager().handle(
                        new Status(IStatus.WARNING, KlighdPiccoloPlugin.PLUGIN_ID, new String(
                                NOT_IN_CURRENT_CLIP_REVEAL_ERROR_MSG).replace("XX",
                                diagramElement.toString())));
            }
        }
    }

    private static final String NOT_IN_CURRENT_CLIP_CENTER_ON_ERROR_MSG =
            NOT_IN_CURRENT_CLIP_ERROR_MSG + " Thus the diagram cannot be centered on that element.";

    /**
     * {@inheritDoc}
     */
    public void centerOn(final KGraphElement diagramElement, final int duration) {
        final PNode node = getRepresentation(diagramElement);
        if (node == null) {
            StatusManager.getManager().handle(
                    new Status(IStatus.WARNING, KlighdPiccoloPlugin.PLUGIN_ID, new String(
                            NO_DIAGRAM_ELEMENT_REPRESENTATION_ERROR_MSG).replace("XX",
                            diagramElement.toString())));
        } else {
            final PCamera camera = canvas.getCamera();
            final PBounds destBounds =
                    NodeUtil.clipRelativeGlobalBoundsOf(node, this.canvas.getCamera()
                            .getDisplayedINode());

            if (destBounds != null) {
                // center the camera on the node
                camera.animateViewToCenterBounds(destBounds, false, duration);

            } else {
                StatusManager.getManager().handle(
                        new Status(IStatus.WARNING, KlighdPiccoloPlugin.PLUGIN_ID, new String(
                                NOT_IN_CURRENT_CLIP_CENTER_ON_ERROR_MSG).replace("XX",
                                diagramElement.toString())));
            }
        }
    }

    private static final String NOT_IN_CURRENT_CLIP_TO_TOP_LEFT_ERROR_MSG =
            NOT_IN_CURRENT_CLIP_ERROR_MSG + " Thus the diagram cannot be panned to align"
                    + " that node with the visible area's top left corner.";

    /**
     * {@inheritDoc}
     */
    public void panToTopLeftCorner(final KNode diagramElement, final int duration) {
        final PNode node = getRepresentation(diagramElement);
        if (node == null) {
            StatusManager.getManager().handle(
                    new Status(IStatus.WARNING, KlighdPiccoloPlugin.PLUGIN_ID, new String(
                            NO_DIAGRAM_ELEMENT_REPRESENTATION_ERROR_MSG).replace("XX",
                            diagramElement.toString())));
        } else {
            final KlighdMainCamera camera = canvas.getCamera();

            final PBounds destBounds =
                    NodeUtil.clipRelativeGlobalBoundsOf(node, this.canvas.getCamera()
                            .getDisplayedINode());

            if (destBounds != null) {
                final double scale = camera.getViewTransformReference().getScale();

                // compose a new affine transform describing the desired camera view ... 
                final AffineTransform t = AffineTransform.getScaleInstance(scale, scale);
                t.translate(-destBounds.x, -destBounds.y);
                
                // ... and trigger the change 
                camera.animateViewToTransform(t, duration);
            } else {
                StatusManager.getManager().handle(
                        new Status(IStatus.WARNING, KlighdPiccoloPlugin.PLUGIN_ID, new String(
                                NOT_IN_CURRENT_CLIP_TO_TOP_LEFT_ERROR_MSG).replace("XX",
                                diagramElement.toString())));
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void panDiagramToTopLeftCorner(final int duration) {
        final KlighdMainCamera camera = canvas.getCamera();

        final double scale = camera.getViewTransformReference().getScale();
        final AffineTransform t = AffineTransform.getScaleInstance(scale, scale);

        final PBounds destBounds =
                NodeUtil.clipRelativeGlobalBoundsOf(camera.getDisplayedLayer(),
                        camera.getDisplayedINode());
        t.translate(-destBounds.x, -destBounds.y);
        camera.animateViewToTransform(t, duration);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExpanded(final KNode diagramElement) {
        return controller.isExpanded(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void collapse(final KNode diagramElement) {
        controller.collapse(diagramElement);
        this.notifyViewChangeListeners(ViewChangeType.COLLAPSE, diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void expand(final KNode diagramElement) {
        controller.expand(diagramElement);
        this.notifyViewChangeListeners(ViewChangeType.EXPAND, diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void toggleExpansion(final KNode diagramElement) {
        controller.toggleExpansion(diagramElement);

        if (isExpanded(diagramElement)) {
            this.notifyViewChangeListeners(ViewChangeType.EXPAND, diagramElement);
        } else {
            this.notifyViewChangeListeners(ViewChangeType.COLLAPSE, diagramElement);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void hide(final KGraphElement diagramElement) {
        controller.hide(diagramElement);
        this.notifyViewChangeListeners(ViewChangeType.HIDE, diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void show(final KGraphElement diagramElement) {
        controller.show(diagramElement);
        this.notifyViewChangeListeners(ViewChangeType.SHOW, diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void clip(final KNode diagramElement) {
        controller.clip(diagramElement);
        this.notifyViewChangeListeners(ViewChangeType.CLIP, diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public KNode getClip() {
        return controller.getClip();
    }

    /**
     * {@inheritDoc}
     */
    public void scale(final KNode diagramElement, final float factor) {
        final KShapeLayout layoutData = diagramElement.getData(KShapeLayout.class);
        if (layoutData != null) {
            layoutData.setProperty(LayoutOptions.SCALE_FACTOR, factor);
        }

        if (isExpanded(diagramElement)) {
            controller.getZoomController().setFocusNode(diagramElement);
        }

        this.notifyViewChangeListeners(ViewChangeType.SCALE, diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public float getScale(final KNode diagramElement) {
        final KShapeLayout layoutData = diagramElement.getData(KShapeLayout.class);
        if (layoutData != null) {
            return layoutData.getProperty(LayoutOptions.SCALE_FACTOR);
        } else {
            return 1f;
        }
    }

    /**
     * Returns the Piccolo2D representation for the given diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the Piccolo2D representation
     */
    protected PNode getRepresentation(final KGraphElement diagramElement) {
        final PNode node = (PNode) controller.getRepresentation(diagramElement);
        if (node != null && node.getRoot() == canvas.getRoot()) {
            return node;
        }
        return null;
    }

    /**
     * Returns the canvas used to render the scene graph.
     * 
     * @return the canvas
     */
    public KlighdCanvas getCanvas() {
        return canvas;
    }

    /**
     * Renders this viewer's contents to the passed gc with the targeted bounds.
     * 
     * @param gc
     *            where to draw to.
     * @param bounds
     *            the bounds of the target we are printing to.
     */
    public void renderOffscreen(final GC gc, final Rectangle bounds) {

        // create a wrapping graphics object
        final KlighdSWTGraphicsImpl g2 = new KlighdSWTGraphicsImpl(gc, gc.getDevice());

        final AffineTransform t = g2.getTransform();
        t.translate(bounds.x, bounds.y);
        g2.setTransform(t);

        // create an offscreen canvas and fetch its camera
        final POffscreenCanvas offCanvas = new POffscreenCanvas(bounds.width, bounds.height);
        final PCamera camera = offCanvas.getCamera();
        camera.getLayersReference().clear();

        // let the camera view the original canvas's first layer
        camera.addLayer(canvas.getLayer());

        // fit the currently displayed diagram into the passed bounds
        final KNode displayedNode =
                controller.getMainCamera().getDisplayedINode().getGraphElement();

        final PBounds newBounds =
                controller.getZoomController().toPBoundsIncludingPortsAndLabels(displayedNode);
        camera.animateViewToCenterBounds(newBounds, true, 0);

        // set up a new paint context and paint the camera
        final KlighdPaintContext paintContext = new KlighdPaintContext(g2, true);
        paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);
        camera.fullPaint(paintContext);
        g2.dispose();
    }
}
