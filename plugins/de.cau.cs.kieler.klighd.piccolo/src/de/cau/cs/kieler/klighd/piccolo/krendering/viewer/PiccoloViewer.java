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
package de.cau.cs.kieler.klighd.piccolo.krendering.viewer;

import java.awt.event.InputEvent;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KBackgroundColor;
import de.cau.cs.kieler.core.krendering.KBackgroundVisibility;
import de.cau.cs.kieler.core.krendering.KLineStyle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.LineStyle;
import de.cau.cs.kieler.klighd.piccolo.INodeSelectionListener;
import de.cau.cs.kieler.klighd.piccolo.Messages;
import de.cau.cs.kieler.klighd.piccolo.PMouseWheelZoomEventHandler;
import de.cau.cs.kieler.klighd.piccolo.PSWTSimpleSelectionEventHandler;
import de.cau.cs.kieler.klighd.piccolo.activities.ZoomActivity;
import de.cau.cs.kieler.klighd.piccolo.krendering.ITracingElement;
import de.cau.cs.kieler.klighd.piccolo.krendering.controller.GraphController;
import de.cau.cs.kieler.klighd.piccolo.krendering.controller.RenderingContextData;
import de.cau.cs.kieler.klighd.piccolo.nodes.PEmptyNode;
import de.cau.cs.kieler.klighd.piccolo.ui.SaveAsImageAction;
import de.cau.cs.kieler.klighd.viewers.AbstractViewer;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.event.PInputEventFilter;
import edu.umd.cs.piccolox.swt.PSWTCanvas;

/**
 * A viewer for Piccolo diagram contexts.
 * 
 * @author mri
 */
public class PiccoloViewer extends AbstractViewer<KNode> implements INodeSelectionListener {

    /** the canvas used for drawing. */
    private PSWTCanvas canvas;
    /** the current selection event handler. */
    private PSWTSimpleSelectionEventHandler selectionHandler = null;
    /** a map used track highlighting style attached to selected elements. */
    private Map<EObject, Iterable<? extends KStyle>> selectionHighlighting = Maps.newHashMap();
    /** the content outline page. */
    private PiccoloOutlinePage outlinePage;
    
    /** the graph controller. */
    private GraphController controller;

    /**
     * Creates a Piccolo viewer with default style.
     * 
     * @param parent
     *            the parent composite
     */
    public PiccoloViewer(final Composite parent) {
        this(parent, SWT.NONE);
    }

    /**
     * Creates a Piccolo viewer with given style.
     * 
     * @param parent
     *            the parent composite
     * @param style
     *            the style attributes
     */
    public PiccoloViewer(final Composite parent, final int style) {
        canvas = new PSWTCanvas(parent, style);
        // this reduces flickering drastically
        canvas.setDoubleBuffered(true);
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
        controller = new GraphController(model, camera.getLayer(0), sync);
        controller.initialize();
        
        // update the outline page
        if (outlinePage != null) {
            outlinePage.setContent(camera.getLayer(0));
        }

        // add a node for the marquee
        PEmptyNode marqueeParent = new PEmptyNode();
        camera.getLayer(1).addChild(marqueeParent);

        // add a selection handler
        selectionHandler = new PSWTSimpleSelectionEventHandler(camera, marqueeParent);
        canvas.addInputEventListener(selectionHandler);

        // forward the selection events
        selectionHandler.addSelectionListener(this);
        for (KStyle style: Iterables.concat(selectionHighlighting.values())) {
            EcoreUtil.remove(style);
        }
        selectionHighlighting.clear();
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
        List<PLayer> layers = camera.getLayersReference();
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
    public void setSelection(final Object[] diagramElements) {
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
    public void select(final Object[] diagramElements) {
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
    public void unselect(final Object[] diagramElements) {
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
    public void reveal(final Object diagramElement, final int duration) {
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
    public void centerOn(final Object diagramElement, final int duration) {
        PNode node = getRepresentation(diagramElement);
        if (node != null) {
            // center the camera on the node
            PCamera camera = canvas.getCamera();
            camera.animateViewToCenterBounds(node.getFullBounds(), false, duration);
        }
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
            PNode node = RenderingContextData.get(element).getProperty(GraphController.REP);
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
    public PSWTCanvas getCanvas() {
        return canvas;
    }

    /**
     * {@inheritDoc}
     */
    public void selected(final PSWTSimpleSelectionEventHandler handler, final PNode node) {
        if (node instanceof ITracingElement<?>) {
            ITracingElement<?> graphElement = (ITracingElement<?>) node;
            notifyListenersSelected(graphElement.getGraphElement());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void unselected(final PSWTSimpleSelectionEventHandler handler, final PNode node) {
        if (node instanceof ITracingElement<?>) {
            ITracingElement<?> graphElement = (ITracingElement<?>) node;
            notifyListenersUnselected(graphElement.getGraphElement());
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void selection(final PSWTSimpleSelectionEventHandler handler,
            final Collection<PNode> nodes) {
        List<Object> graphElements = Lists.newLinkedList();
        for (PNode node : nodes) {
            if (node instanceof ITracingElement<?>) {
                ITracingElement<?> graphElement = (ITracingElement<?>) node;
                graphElements.add(graphElement.getGraphElement());
            }
        }
        
        // chsch: the following lines realizes the highlighting of selected diagram elements
        //  (if the diagram is given as a KGraph/KRendering model)
        List<EObject> noLongerSelected = Lists.newLinkedList(selectionHighlighting.keySet());
        noLongerSelected.removeAll(graphElements);
        for (EObject element : noLongerSelected) {
            // the related value should never be null!!
            for (KStyle style : selectionHighlighting.remove(element)) {
                EcoreUtil.remove(style);
            }
        }
        
        List<EObject> newlySelected = Lists.newLinkedList(Iterables.filter(graphElements,
                EObject.class));
        newlySelected.removeAll(selectionHighlighting.keySet());
        for (EObject element : newlySelected) {
            KLineStyle style = KRenderingFactory.eINSTANCE.createKLineStyle();
            style.setLineStyle(LineStyle.DASH);
            if (KGraphPackage.eINSTANCE.getKGraphElement().isInstance(element)) {
                KRendering rendering = ((KGraphElement) element).getData(KRendering.class);
                if (rendering != null) {
                    rendering.getStyles().add(0, style);
                    selectionHighlighting.put(element, Lists.newArrayList(style));
                }
            } else if (KRenderingPackage.eINSTANCE.getKText().isInstance(element)) {
                final KBackgroundVisibility bgv = KRenderingFactory.eINSTANCE
                        .createKBackgroundVisibility();
                bgv.setVisible(true);
                final KBackgroundColor bgc = KRenderingFactory.eINSTANCE.createKBackgroundColor();
                // the color values of 'DimGray'   // SUPPRESS CHECKSTYLE NEXT 3 MagicNumber
                bgc.setRed(190);
                bgc.setGreen(190);
                bgc.setBlue(190);
                ((KText) element).getStyles().addAll(Lists.newArrayList(bgv, bgc));
                selectionHighlighting.put(element, Lists.newArrayList(bgv, bgc));
            }
        }
        // end of selection highlighting stuff
        
        if (graphElements.size() > 0) {
            // chsch question: Is this restriction reasonable?
            //  one may also be informed on the clearing of the selection...
            notifyListenersSelection(graphElements);
        }
    }

}
