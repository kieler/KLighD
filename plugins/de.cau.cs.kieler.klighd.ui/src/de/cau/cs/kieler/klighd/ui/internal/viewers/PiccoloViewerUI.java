/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.viewers;

import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IModelModificationHandler;
import de.cau.cs.kieler.klighd.internal.ISynthesis;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.PNodeController;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdBasicInputEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.ITracingElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloOutlinePage;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.piccolo.viewer.PrintAction;
import de.cau.cs.kieler.klighd.syntheses.ReinitializingDiagramSynthesisProxy;
import de.cau.cs.kieler.klighd.ui.modifymodel.ModelModificationHandlerProvider;
import de.cau.cs.kieler.klighd.util.ModelingUtil;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PInputEvent;

/**
 * UI stuff such as the text input mechanism and registry of actions are extracted from the
 * PiccoloViewer to this class to optimize dependencies. PiccoloViewerUi instance will be generated
 * by the PiccoloViewerProvider.
 * 
 * @author ckru
 */
@SuppressWarnings("restriction")
public class PiccoloViewerUI extends PiccoloViewer {

    /**
     * SWT text element that acts as an overlay for labels in some situations.
     */
    private Text textinput;

    /**
     * Listens to text inputs and applies changes accordingly.
     */
    private KlighdTextInputVerifyListener textinputlistener = new KlighdTextInputVerifyListener();

    /**
     * Creates a Piccolo2D viewer with default style.
     * 
     * @param parentViewer
     *            the parent {@link ContextViewer}
     * @param parent
     *            the parent composite
     */
    public PiccoloViewerUI(final ContextViewer parentViewer, final Composite parent) {
        this(parentViewer, parent, SWT.None);
    }

    /**
     * Creates a Piccolo2D viewer with given style.
     * 
     * @param parentViewer
     *            the parent {@link ContextViewer}
     * @param parent
     *            the parent composite
     * @param style
     *            the style attributes
     */
    public PiccoloViewerUI(final ContextViewer parentViewer, final Composite parent, final int style) {
        super(parentViewer, parent);
        
        
        final IActionBars actions;
        final IDiagramWorkbenchPart part = getViewContext().getDiagramWorkbenchPart();

        if (part instanceof IEditorPart) {
            actions = ((IEditorPart) part).getEditorSite().getActionBars();

        } else if (getViewContext().getDiagramWorkbenchPart() instanceof IViewPart) {
            actions = ((IViewPart) part).getViewSite().getActionBars();

        } else {
            actions = null;
        }

        // register print action
        if (actions != null) {
            final PiccoloViewer thisViewer = this;

            actions.setGlobalActionHandler(ActionFactory.PRINT.getId(), new Action() {
                private final PrintAction printer = new PrintAction(thisViewer);

                public void run() {
                    printer.run();
                }
            });
        }
        
        
        this.getCanvas().getCamera().addInputEventListener(new KlighdTextInputHandler());
        addTextInput(parentViewer);
    }

    /**
     * Adds a text widget to the viewer that can be used to select and edit texts.
     * 
     * @param parentViewer
     *            the viewer to which to add the text widget
     */
    private void addTextInput(final ContextViewer parentViewer) {
        textinput = new Text(this.getCanvas(), SWT.MULTI);
        textinput.addListener(SWT.MouseUp, new Listener() {
            public void handleEvent(final Event event) {
                // textinput.setSize(textinput.getSize().x + 50, textinput.getSize().y);
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
                if (((e.stateMask & SWT.SHIFT) != 0) && ((char) e.keyCode == SWT.CR)) {
                    textinputlistener.handleEvent(null);
                    textinput.setEditable(false);
                    textinput.setVisible(false);
                }

            }
        });
        textinput.setEditable(false);
    }

    @Override
    protected PiccoloOutlinePage createDiagramOutlinePage() {
        return new PiccoloContentOutlinePage();
    }

    /**
     * A subclass of the {@link PiccoloOutlinePage} that implements the required
     * {@link IContentOutlinePage} interface.<br>
     * <br>
     * Since that interface requires an additional UI dependency but luckily is simply a composition
     * of {@link org.eclipse.ui.part.IPage IPage} and
     * {@link org.eclipse.jface.viewers.ISelectionProvider ISelectionProvider}, and we do not
     * support any selection providing functionality, {@link PiccoloOutlinePage} only implements
     * {@link org.eclipse.ui.part.IPage IPage} for the sake of reducing dependencies. The required
     * (empty) {@link org.eclipse.jface.viewers.ISelectionProvider ISelectionProvider} methods are
     * than contributed by this sub class.
     * 
     * @author chsch
     */
    private static class PiccoloContentOutlinePage extends PiccoloOutlinePage implements
            IContentOutlinePage {

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
    }

    /**
     * Listens to committed text inputs.
     * 
     * @author ckru
     */
    private class KlighdTextInputVerifyListener implements Listener {

        /**
         * Node the committed text is linked to.
         */
        private KText node;

        /**
         * The element whose text is supposed to change.
         */
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
                    ISynthesis synth = PiccoloViewerUI.this.getViewContext().getDiagramSynthesis();
                    if (synth instanceof ReinitializingDiagramSynthesisProxy) {
                        synth = ((ReinitializingDiagramSynthesisProxy<?>) synth).getDelegate();
                    }
                    Function<String, Void> f = synth.getTextUpdateFunction(node, element);
                    if (f == null) {
                        return;
                    }
                    PiccoloViewerUI.this.getContextViewer().getViewContext()
                            .getDiagramWorkbenchPart();
                    IWorkbenchPart wPart =
                            PiccoloViewerUI.this.getContextViewer().getViewContext()
                                    .getSourceWorkbenchPart();
                    IModelModificationHandler handler =
                            ModelModificationHandlerProvider.getInstance().getFittingHandler(wPart);
                    try {
                        if (handler != null) {
                            handler.execute(wPart, f, textinput.getText());
                        } else {
                            f.apply(textinput.getText());
                        }
                    } catch (Exception e) {
                        StatusManager.getManager().handle(
                                new Status(IStatus.ERROR, "de.cau.cs.kieler.klighd.ui",
                                        "An error has occured while applying the text", e));
                    }
                    textinput.setEditable(false);
                }
            }
        }

    }

    /**
     * Handles mouseover on a text element and displays a text input dialog in that case.
     * 
     * @author ckru
     */
    private class KlighdTextInputHandler extends KlighdBasicInputEventHandler {

        /**
         * Constructor that just calls super.
         */
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
         * Gets the element in the graph hierarchy thats linking the given PNode to the KGraph.
         * 
         * @param n
         *            the PNode whose text is about to be changed
         * @return the element thats linked to the KGraph.
         */
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
         * Sets position, style and text of the text input widget to the text element the mouse
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
            PiccoloViewerUI.this.getCanvas().getCamera().getViewTransformReference()
                    .transform(bounds, bounds);
            textinput.setLocation((int) bounds.getX(), (int) bounds.getY());

            // determine font data (i.e. font size)
            FontData fd = new FontData(styledText.getFontData().toString());
            fd.setHeight((int) Math
                    .round((styledText.getFontData().getHeight() * PiccoloViewerUI.this.getCanvas()
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

}
