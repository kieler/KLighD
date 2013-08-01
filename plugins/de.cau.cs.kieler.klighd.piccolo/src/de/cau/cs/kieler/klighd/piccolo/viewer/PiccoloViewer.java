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

import java.awt.event.InputEvent;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.Messages;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.ZoomActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdActionEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.PMouseWheelZoomEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdSimpleSelectionEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.ITracingElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PEmptyNode;
import de.cau.cs.kieler.klighd.piccolo.ui.SaveAsImageAction;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import de.cau.cs.kieler.klighd.viewers.AbstractViewer;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.event.PInputEventFilter;

/**
 * A viewer for Piccolo diagram contexts.
 * 
 * @author mri
 * @author chsch
 */
public class PiccoloViewer extends AbstractViewer<KNode> implements INodeSelectionListener {

    /** the canvas used for drawing. */
    private KlighdCanvas canvas;
    /** the current selection event handler. */
    private KlighdSimpleSelectionEventHandler selectionHandler = null;
    /** the content outline page. */
    private PiccoloOutlinePage outlinePage;
    
    /** the parent viewer. */
    private ContextViewer parentViewer;
    /** the graph controller. */
    private DiagramController controller;

    /**
     * Creates a Piccolo viewer with default style.
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
     * Creates a Piccolo viewer with given style.
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
            final String msg = "KLighD (piccolo): A 'PiccoloViewer' has been tried to attach to a"
                    + "disposed 'Composite' widget.";
            throw new IllegalArgumentException(msg);
        }
        this.parentViewer = theParentViewer;
        this.canvas = new KlighdCanvas(parent, style);
        
        // canvas.setDefaultRenderQuality(PPaintContext.LOW_QUALITY_RENDERING);
        // canvas.removeInputEventListener(canvas.getPanEventHandler());
        // prevent conflicts with selection handler
        canvas.getPanEventHandler().setEventFilter(
                new PInputEventFilter(InputEvent.BUTTON1_MASK, InputEvent.CTRL_MASK));
        // exchange the zoom event handler
        canvas.removeInputEventListener(canvas.getZoomEventHandler());
        canvas.addInputEventListener(new PMouseWheelZoomEventHandler());
        // add a context menu
        addContextMenu(canvas);
        
        // add a tooltip element
        new PiccoloTooltip(parent.getDisplay(), canvas.getCamera());
    }

    /**
     * Creates the context menu and adds the actions.
     * 
     * @param composite
     *            the composite to add the context menu to
     */
    private void addContextMenu(final Composite composite) {
        MenuManager menuManager = new MenuManager();
        // add the 'save-as-image' action
        Action saveAsImageAction =
                new SaveAsImageAction(this, Messages.PiccoloViewer_save_as_image_text);
        menuManager.add(saveAsImageAction);

        // create the context menu
        Menu menu = menuManager.createContextMenu(composite);
        composite.setMenu(menu);
        
        // register the context menu in the current work bench part site
        //  this enables the population with entries contributed via extension points
        this.getContextViewer().getWorkbenchPart().getSite()
                .registerContextMenu(menuManager, this.getContextViewer());
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
    public void setModel(final KNode model, final boolean sync) {
        // remove the old selection handler
        if (selectionHandler != null) {
            canvas.removeInputEventListener(selectionHandler);
            selectionHandler = null;
        }
        
        // prepare the camera
        PCamera camera = canvas.getCamera();
        // resetCamera(camera);
        resizeAndResetLayers(2);

        // create a controller for the graph
        controller = new DiagramController(model, camera.getLayer(0), sync);
        controller.initialize();
        
        // update the outline page
        if (outlinePage != null) {
            outlinePage.setContent(camera.getLayer(0));
        }

        // add a node for the marquee
        PEmptyNode marqueeParent = new PEmptyNode();
        camera.getLayer(1).addChild(marqueeParent);

        // add a selection handler
        selectionHandler = new KlighdSimpleSelectionEventHandler(camera, marqueeParent);
        canvas.addInputEventListener(selectionHandler);
        canvas.addInputEventListener(new KlighdActionEventHandler(this));

        // forward the selection events
        selectionHandler.addSelectionListener(this);
    }

    /**
     * {@inheritDoc}
     */
    public KNode getModel() {
        if (controller != null) {
            return controller.getNode().getGraphElement();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IContentOutlinePage getOutlinePage() {
        if (outlinePage == null) {
            outlinePage = new PiccoloOutlinePage();
            outlinePage.setContent(canvas.getCamera().getLayer(0));
        }
        return outlinePage;
    }

    /**
     * Resizes the number of layers in the camera to the given number and resets them.
     * 
     * @param number
     *            the number of layers
     */
    private void resizeAndResetLayers(final int number) {
        PRoot root = canvas.getRoot();
        PCamera camera = canvas.getCamera();
        // resize down
        while (camera.getLayerCount() > number) {
            PLayer layer = camera.getLayer(camera.getLayerCount() - 1);
            camera.removeLayer(layer);
            root.removeChild(layer);
        }
        // resize up
        while (camera.getLayerCount() < number) {
            PLayer layer = new PLayer();
            root.addChild(layer);
            camera.addLayer(layer);
        }
        // reset
        @SuppressWarnings("unchecked")
        Iterable<PLayer> layers = camera.getLayersReference();
        for (PLayer layer : layers) {
            layer.removeAllChildren();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setRecording(final boolean recording) {
        controller.setRecording(recording);
    }

    /**
     * {@inheritDoc}
     */
    public void setZoomToFit(final boolean zoomToFit) {
        controller.setZoomToFit(zoomToFit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelection(final Iterable<EObject> diagramElements) {
        if (selectionHandler != null) {
            selectionHandler.unselectAll();
            select(diagramElements);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearSelection() {
        if (selectionHandler != null) {
            selectionHandler.unselectAll();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void select(final Iterable<EObject> diagramElements) {
        if (selectionHandler != null) {
            for (Object diagramElement : diagramElements) {
                PNode node = getRepresentation(diagramElement);
                if (node != null) {
                    selectionHandler.select(node);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unselect(final Iterable<EObject> diagramElements) {
        if (selectionHandler != null) {
            for (Object diagramElement : diagramElements) {
                PNode node = getRepresentation(diagramElement);
                if (node != null) {
                    selectionHandler.unselect(node);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void zoom(final float zoomLevel, final int duration) {
        ZoomActivity zoomActivity = new ZoomActivity(canvas.getCamera(), zoomLevel, duration);
        if (duration > 0) {
            canvas.getRoot().addActivity(zoomActivity);
        } else {
            zoomActivity.apply();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void zoomToFit(final int duration) {
        controller.zoomToFit(duration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reveal(final EObject diagramElement, final int duration) {
        PNode node = getRepresentation(diagramElement);
        if (node != null) {
            // move the camera so it includes the bounds of the node
            PCamera camera = canvas.getCamera();
            camera.animateViewToPanToBounds(node.getFullBounds(), duration);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void centerOn(final EObject diagramElement, final int duration) {
        PNode node = getRepresentation(diagramElement);
        if (node != null) {
            // center the camera on the node
            PCamera camera = canvas.getCamera();
            camera.animateViewToCenterBounds(node.getFullBounds(), false, duration);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void expand(final KNode diagramElement) {
        controller.expand(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleExpansion(final KNode diagramElement) {
        controller.toggleExpansion(diagramElement);
    }
    
    /**
     * Returns the Piccolo representation for the given diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the Piccolo representation
     */
    private PNode getRepresentation(final Object diagramElement) {
        if (diagramElement instanceof KGraphElement) {
            KGraphElement element = (KGraphElement) diagramElement;
            PNode node = RenderingContextData.get(element).getProperty(DiagramController.REP);
            if (node != null && node.getRoot() == canvas.getRoot()) {
                return node;
            }
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
     * {@inheritDoc}
     */
    public void selected(final KlighdSimpleSelectionEventHandler handler, final PNode node) {
        if (node instanceof ITracingElement<?>) {
            ITracingElement<?> graphElement = (ITracingElement<?>) node;
            notifyListenersSelected(graphElement.getGraphElement());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void unselected(final KlighdSimpleSelectionEventHandler handler, final PNode node) {
        if (node instanceof ITracingElement<?>) {
            ITracingElement<?> graphElement = (ITracingElement<?>) node;
            notifyListenersUnselected(graphElement.getGraphElement());
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void selection(final KlighdSimpleSelectionEventHandler handler,
            final Iterable<PNode> nodes) {
        
        @SuppressWarnings("unchecked")
        Iterable<ITracingElement<EObject>> elements = (Iterable<ITracingElement<EObject>>)
                (Iterable<?>) Iterables.filter(nodes, ITracingElement.class);
        
//        List<EObject> graphElements = Lists.newLinkedList();
//        for (ITracingElement<EObject> element : elements) {
//            graphElements.add(element.getGraphElement());
//        }
        
        notifyListenersSelection(Iterables.transform(elements,
                new Function<ITracingElement<EObject>, EObject>() {
                    public EObject apply(final ITracingElement<EObject> element) {
                        return element.getGraphElement();
                    }
                }));
    }

}
