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

import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.actions.ActionFactory;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.IDiagramOutlinePage;
import de.cau.cs.kieler.klighd.internal.ILayoutRecorder;
import de.cau.cs.kieler.klighd.piccolo.Messages;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.PNodeController;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdActionEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdBasicInputEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdSelectionEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.ITracingElement;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseWheelZoomEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.ui.SaveAsImageAction;
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis;
import de.cau.cs.kieler.klighd.util.ModelingUtil;
import de.cau.cs.kieler.klighd.viewers.AbstractViewer;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.POffscreenCanvas;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PPanEventHandler;
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

    /** the canvas used for drawing. */
    private KlighdCanvas canvas;
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
            final String msg =
                    "KLighD (piccolo): A 'PiccoloViewer' has been tried to attach to a"
                            + "disposed 'Composite' widget.";
            throw new IllegalArgumentException(msg);
        }
        this.parentViewer = theParentViewer;
        this.canvas = new KlighdCanvas(parent, style);

        textinput = new Text(canvas, SWT.MULTI);
        textinput.addListener(SWT.MouseDoubleClick, new Listener() {
            public void handleEvent(final Event event) {
                textinput.setEditable(true);
            }
        });

        parentViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(final SelectionChangedEvent event) {
                textinputlistener.handleEvent(null);
                textinput.setEditable(false);
                textinput.setVisible(false);
            }
        });

        textinput.addKeyListener(new KeyListener() {

            public void keyReleased(final KeyEvent e) {

            }

            public void keyPressed(final KeyEvent e) {
                if (((e.stateMask & SWT.SHIFT) != 0) && ((char) e.keyCode == 'f')) {
                    textinputlistener.handleEvent(null);
                    textinput.setEditable(false);
                    textinput.setVisible(false);
                }

            }
        });
        textinput.setEditable(false);
        // canvas.setDefaultRenderQuality(PPaintContext.LOW_QUALITY_RENDERING);

        final PCamera camera = canvas.getCamera();

        // install the required event handlers, they rely on SWT event type codes
        camera.addInputEventListener(new KlighdActionEventHandler(this));
        camera.addInputEventListener(new KlighdTextInputHandler());
        camera.addInputEventListener(new KlighdMouseWheelZoomEventHandler());
        camera.addInputEventListener(new KlighdBasicInputEventHandler(new PPanEventHandler()));
        // camera.addInputEventListener(new KlighdSwitchFocusEventHandler(this));
        camera.addInputEventListener(new KlighdSelectionEventHandler(theParentViewer));

        // add a node for the rubber band selection marquee
        // final PEmptyNode marqueeParent = new PEmptyNode();
        // camera.getLayer(1).addChild(marqueeParent);

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
    private class KlighdTextInputVerifyListener implements Listener {

        /**
         * node the committed text is linked to.
         */
        private KText node;

        private KGraphElement element;

        /**
         * Set node currently linked to the textinput.
         * 
         * @param n
         *            node currently linked to the textinput.
         */
        public void setNode(final KText n, final KGraphElement e) {
            node = n;
            element = e;
        }

        /**
         * {@inheritDoc}
         */
        public void handleEvent(final Event event) {
            if (node != null) {
                if (!textinput.getText().equals(node.getText())) {
                    ITransformation<?, ?> trans =
                            PiccoloViewer.this.parentViewer.getCurrentViewContext()
                                    .getTransformationContexts().get(0).getTransformation();
                    if (trans instanceof AbstractDiagramSynthesis) {
                        AbstractDiagramSynthesis<?> synth = (AbstractDiagramSynthesis<?>) trans;
                        Function<String, Void> f = synth.getTextUpdateFunction(node, element);
                        f.apply(textinput.getText());
                    }
                    textinput.setEditable(false);
                }
            }
        }

    }

    /**
     * 
     * Handles mouseover on a text element and displays a text input dialog in that case.
     * 
     * @author ckru
     * 
     */
    private class KlighdTextInputHandler extends KlighdBasicInputEventHandler {

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

        private ITracingElement<?> getParentTracingElement(final PNode n) {
            PNode parent = n.getParent();
            if (parent != null) {
                if (parent instanceof ITracingElement<?>) {
                    return (ITracingElement<?>) parent;
                } else {
                    return getParentTracingElement(parent);
                }
            } else {
                return null;
            }
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
            
            KGraphElement element = null;
            if (n instanceof KLabelNode) {
                final KLabelNode labelNode = (KLabelNode) n;
                element = labelNode.getGraphElement().getParent();
                KLabel kLabel = labelNode.getGraphElement();
                kText =
                        Iterables.getFirst(ModelingUtil.eAllContentsOfType(kLabel, KText.class),
                                null);

                if (kText != null) {
                    Iterator<PNodeController<?>> controllers =
                            labelNode.getRenderingController().getPNodeController(kText).iterator();
                    if (controllers.hasNext()) {
                        styledText = (KlighdStyledText) controllers.next().getNode();
                    } else {
                        kText = null;
                    }
                }

            } else if (n instanceof KlighdStyledText) {
                styledText = (KlighdStyledText) n;
                Object o = this.getParentTracingElement(n).getGraphElement();
                if (o instanceof KGraphElement) {
                    element = (KGraphElement) o;
                }
                kText = styledText.getGraphElement();
                
            }

            if ((kText == null || !kText.isCursorSelectable())) {
                // set input widget invisible if mouse is not over a text element
                if (!textinput.getEditable()) {
                    textinput.setVisible(false);
                }
                return;
            }
            String text = styledText.getText();

            // determine text value
            textinput.setText(text);

            // determine global position of the text element
            Rectangle2D bounds = n.getGlobalBounds();
            canvas.getCamera().getViewTransformReference().transform(bounds, bounds);
            textinput.setLocation((int) bounds.getX(), (int) bounds.getY());

            // determine font data (i.e. font size)
            FontData fd = new FontData(styledText.getFontData().toString());
            fd.setHeight((int) Math.round((styledText.getFontData().getHeight() * canvas
                    .getCamera().getViewScale())));
            textinput.setSize(textinput.computeSize(SWT.DEFAULT, SWT.DEFAULT));
            textinput.setFont(new Font(textinput.getDisplay(), fd));

            // determine text color
            Color textColor = new Color(textinput.getDisplay(), styledText.getPenColor());
            textinput.setForeground(textColor);

            // link this currently selected node to verify listener
            textinputlistener.setNode(kText, element);

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
        // this enables the population with entries contributed via extension points
        this.getContextViewer().getWorkbenchPart().getSite()
                .registerContextMenu(menuManager, this.getContextViewer());
    }

    /**
     * {@inheritDoc}
     */
    public IDiagramOutlinePage getDiagramOutlinePage() {
        if (outlinePage == null || outlinePage.isDisposed()) {
            outlinePage = new PiccoloOutlinePage();
            outlinePage.setContent(this.controller.getNode());
        }
        return outlinePage;
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

        // create a controller for the graph
        controller = new DiagramController(model, canvas.getCamera(), sync);

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
    public KNode getModel() {
        if (controller != null) {
            return controller.getNode().getGraphElement();
        }
        return null;
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
        final ZoomStyle zoomStyle;
        
        if (this.getViewContext() != null) {
            // get the zoomStyle
            zoomStyle = this.getViewContext().getZoomStyle();
        } else {
            zoomStyle = ZoomStyle.NONE;
        }
        
        stopRecording(zoomStyle, animationTime);
    }

    /**
     * {@inheritDoc}
     */
    public void stopRecording(final ZoomStyle zoomStyle, final int animationTime) {
        controller.stopRecording(zoomStyle, animationTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void zoomToLevel(final float zoomLevel, final int duration) {
        controller.zoomToLevel(zoomLevel, duration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void zoom(final ZoomStyle style, final int duration) {
        controller.zoom(style, duration);
    }

    /**
     * {@inheritDoc}
     */
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
    public void collapse(final KNode diagramElement) {
        controller.collapse(diagramElement);
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
     * {@inheritDoc}
     */
    public void hide(final KGraphElement diagramElement) {
        controller.hide(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void show(final KGraphElement diagramElement) {
        controller.show(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void clip(final KNode diagramElement) {
        controller.clip(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public KNode getClip() {
        return controller.getClip();
    }


    /**
     * Returns the Piccolo2D representation for the given diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the Piccolo2D representation
     */
    public PNode getRepresentation(final KGraphElement diagramElement) {
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
        g2.dispose();
    }
}
