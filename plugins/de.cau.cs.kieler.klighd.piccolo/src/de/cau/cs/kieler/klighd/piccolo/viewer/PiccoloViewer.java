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
import java.awt.geom.Rectangle2D;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.piccolo.Messages;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.ZoomActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdActionEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdSimpleSelectionEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.PMouseWheelZoomEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.ITracingElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PEmptyNode;
import de.cau.cs.kieler.klighd.piccolo.ui.SaveAsImageAction;
import de.cau.cs.kieler.klighd.util.ModelingUtil;
import de.cau.cs.kieler.klighd.viewers.AbstractViewer;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.POffscreenCanvas;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventFilter;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

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

    private Text textinput;
    private KlighdTextInputVerifyListener textinputlistener = new KlighdTextInputVerifyListener();
    
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
        //canvas.getCamera().getViewScale()
        textinput = new Text(canvas, SWT.NONE);
        textinput.addVerifyListener(textinputlistener);
        //textinput.setBackground(new Color(null, 96, 255, 96));
        //textinput.setVisible(false);
        
        // canvas.setDefaultRenderQuality(PPaintContext.LOW_QUALITY_RENDERING);
        // canvas.removeInputEventListener(canvas.getPanEventHandler());
        // prevent conflicts with selection handler
        canvas.getPanEventHandler().setEventFilter(
                new PInputEventFilter(InputEvent.BUTTON1_MASK, InputEvent.CTRL_MASK));
        
        // exchange the zoom event handler
        canvas.removeInputEventListener(canvas.getZoomEventHandler());
        canvas.addInputEventListener(new PMouseWheelZoomEventHandler());
        
        canvas.addInputEventListener(new KlighdTextInputHandler());
        // add a context menu
        addContextMenu(canvas);

        // add a tooltip element
        new PiccoloTooltip(parent.getDisplay(), canvas.getCamera());

        // register a print action with the global action bars
        if (getContextViewer().getWorkbenchPart() instanceof DiagramViewPart) {
            DiagramViewPart viewPart = (DiagramViewPart) getContextViewer().getWorkbenchPart();

            // register print action
            viewPart.getViewSite()
                    .getActionBars()
                    .setGlobalActionHandler(ActionFactory.PRINT.getId(),
                            new PrintAction(this, viewPart));
        }

    }

    
    /**
     * 
     * Listens to committed text inputs.
     * 
     * @author ckru
     *
     */
    private class KlighdTextInputVerifyListener implements VerifyListener {

        /**
         * node the committed text is linked to.
         */
        @SuppressWarnings("unused")
        private KText node;
        
        /**
         * {@inheritDoc}
         */
        public void verifyText(final VerifyEvent e) {
            // TODO Auto-generated method stub
            
        }
        
        /**
         * Set node currently linked to the textinput.
         * @param n node currently linked to the textinput.
         */
        public void setNode(final KText n) {
            node = n;
        }
        
    }
    
    /**
     * 
     * Handles mouseover on a text element and displays a text input dialog in that case.
     * 
     * @author ckru
     *
     */
    private class KlighdTextInputHandler extends PBasicInputEventHandler {
        
        public KlighdTextInputHandler() {
            super();
        }
        
        /**
         * {@inheritDoc}
         */
        public void mouseMoved(final PInputEvent event) {
            updateTextInput(event);
        }

        /**
         * {@inheritDoc}
         */
        public void mouseDragged(final PInputEvent event) {
            updateTextInput(event);
        }
        
        /**
         * Sets position, style and text of the textinput widget to the text element the mouse
         * currently hovers over.
         * 
         * @param event
         *            the event that triggered this update.
         */
        private void updateTextInput(final PInputEvent event) {
            PNode n = event.getPickedNode();
            
            KText kText = null;
            KlighdStyledText styledText = null;
            
            if (n instanceof KLabelNode) {
                final KLabelNode labelNode = (KLabelNode) n;
                KLabel kLabel = labelNode.getGraphElement();
                kText = Iterables.getFirst(ModelingUtil.eAllContentsOfType(kLabel, KText.class), null);
                
                if (kText != null) {
                    styledText = (KlighdStyledText) labelNode.getRenderingController()
                            .getPNodeController(kText).getNode();
                }
                
            } else if (n instanceof KlighdStyledText) {
                styledText = (KlighdStyledText) n;
                kText = styledText.getGraphElement();
            }
            
            if (kText == null || !kText.isCursorSelectable()) {
                    // set input widget invisible if mouse is not over a text element
                    textinput.setVisible(false);
                    return;
            }
                
            String text = styledText.getText();
            
            //determine text value
            textinput.setText(text);
            
            //determine global position of the text element
            Rectangle2D bounds = n.getGlobalBounds();
            canvas.getCamera().getViewTransformReference().transform(bounds, bounds);
            textinput.setLocation((int) bounds.getX(), (int) bounds.getY());
            
            //determine font data (i.e. font size)
            FontData fd = new FontData(styledText.getFontData().toString());
            fd.setHeight((int) Math.round((styledText.getFontData().getHeight()
                    * canvas.getCamera().getViewScale())));
            textinput.setSize(textinput.computeSize(SWT.DEFAULT, SWT.DEFAULT));
            textinput.setFont(new Font(textinput.getDisplay(), fd));
            
            //determine text color
            Color textColor = new Color(textinput.getDisplay(), styledText.getPenColor());
            textinput.setForeground(textColor);
            
            // link this currently selected node to verify listener
            textinputlistener.setNode(kText);
            
            textinput.setVisible(true);
        }
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
    public void setSelection(final Iterable<KGraphElement> diagramElements) {
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
    public void select(final Iterable<KGraphElement> diagramElements) {
        if (selectionHandler != null) {
            for (KGraphElement diagramElement : diagramElements) {
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
    public void unselect(final Iterable<KGraphElement> diagramElements) {
        if (selectionHandler != null) {
            for (KGraphElement diagramElement : diagramElements) {
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
    public void reveal(final KGraphElement diagramElement, final int duration) {
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
    public void centerOn(final KGraphElement diagramElement, final int duration) {
        PNode node = getRepresentation(diagramElement);
        if (node != null) {
            // center the camera on the node
            PCamera camera = canvas.getCamera();
            camera.animateViewToCenterBounds(node.getGlobalFullBounds(), false, duration);
        }
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
    @Override
    public void collapse(final KNode diagramElement) {
        controller.collapse(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void expand(final KNode diagramElement) {
        controller.expand(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleExpansion(final KNode diagramElement) {
        controller.toggleExpansion(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void hide(final KGraphElement diagramElement) {
        controller.hide(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void show(final KGraphElement diagramElement) {
        controller.show(diagramElement);
    }
    
    /**
     * Returns the Piccolo representation for the given diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the Piccolo representation
     */
    private PNode getRepresentation(final KGraphElement diagramElement) {
        PNode node = (PNode) controller.getRepresentation(diagramElement);
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
        
        Iterable<EObject> elements = Iterables.transform(nodes, new Function<PNode, EObject>() {

            public EObject apply(final PNode node) {

                PNode element = node;
                while (element != null
                        && !ITracingElement.class.isAssignableFrom(element.getClass())) {
                    element = element.getParent();
                }

                if (element == null) {
                    return null;
                } else {
                    return ITracingElement.class.cast(element).getGraphElement();
                }
            }
        });
        notifyListenersSelection(Iterables.filter(elements, Predicates.notNull()));
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
        KlighdSWTGraphicsImpl g2 = new KlighdSWTGraphicsImpl(gc, gc.getDevice());

        // create an offscreen canvas and fetch its camera
        POffscreenCanvas offCanvas = new POffscreenCanvas(bounds.width, bounds.height);
        PCamera camera = offCanvas.getCamera();

        // let the camera view the original canvas's first layer
        camera.addLayer(canvas.getLayer());

        // fit the overall diagram into the passed bounds
        // (copied from #zoomToFit(0))
        if (controller.getNode().getParent() instanceof PLayer) {
            KShapeLayout topNodeLayout =
                    controller.getNode().getGraphElement().getData(KShapeLayout.class);
            PBounds newBounds =
                    new PBounds(topNodeLayout.getXpos(), topNodeLayout.getYpos(),
                            topNodeLayout.getWidth(), topNodeLayout.getHeight());
            camera.animateViewToCenterBounds(newBounds, true, 0);
        }

        // set up a new paint context and paint the camera
        final PPaintContext paintContext = new PPaintContext(g2);
        paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);
        camera.fullPaint(paintContext);
    }
}
