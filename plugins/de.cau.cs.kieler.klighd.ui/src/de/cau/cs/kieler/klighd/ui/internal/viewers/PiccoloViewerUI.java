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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.google.common.base.Function;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IModelModificationHandler;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.INode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloOutlinePage;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.piccolo.viewer.PrintAction;
import de.cau.cs.kieler.klighd.ui.KlighdTextSelection;
import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.ui.modifymodel.ModelModificationHandlerProvider;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import edu.umd.cs.piccolo.PNode;

/**
 * UI stuff such as the text input mechanism and registry of actions are extracted from the
 * PiccoloViewer to this class to optimize dependencies. PiccoloViewerUi instance will be generated
 * by the PiccoloViewerProvider.
 * 
 * @author chsch
 * @author ckru
 */
public class PiccoloViewerUI extends PiccoloViewer {

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

        // registers a print action by means of the global action bars
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

                @Override
                public void run() {
                    printer.run();
                }
            });
        }

        addLabelTextWidget(parentViewer);
    }

    /**
     * SWT text element that acts as an overlay for labels in some situations.
     */
    private StyledText labelTextWidget;


    /**
     * Adds a text widget to the viewer that can be used to select and edit texts.
     * 
     * @param parentViewer
     *            the viewer to which to add the text widget
     */
    private void addLabelTextWidget(final ContextViewer parentViewer) {
        labelTextWidget = new StyledText(this.getCanvas(), SWT.MULTI);
        labelTextWidget.setEditable(false);

        // create a additional (context) menu manager, ...
        final MenuManager menu = new MenuManager();

        // ... install it on the text input control, and ...
        labelTextWidget.setMenu(menu.createContextMenu(labelTextWidget));

        // ... and register it in the workbench part site, in order to let the work bench populate it!
        final IWorkbenchPart part = parentViewer.getViewContext().getDiagramWorkbenchPart();
        part.getSite().registerContextMenu(KlighdUIPlugin.FLOATING_TEXT_MENU_ID, menu, parentViewer);

        labelTextWidget.setDoubleClickEnabled(false);

        this.getCanvas().getCamera()
                .addInputEventListener(new KlighdLabelWidgetHandler(this, labelTextWidget));        

        final PiccoloViewerUI thisViewer = this;

        // add a selection changed listener to the diagram viewer in order to deactivate
        //  the cursor selection text widget on diagram selections
        final ISelectionChangedListener selectionListener = new ISelectionChangedListener() {
            public void selectionChanged(final SelectionChangedEvent event) {
                if (event.getSelection() instanceof KlighdTextSelection) {
                    return;
                }
                updateModelAfterTextChange(labelTextWidget, thisViewer.getViewContext());
                labelTextWidget.setEditable(false);
                labelTextWidget.setSelection(0, 0);
                labelTextWidget.setVisible(false);
            }
        };
        parentViewer.addSelectionChangedListener(selectionListener);

        labelTextWidget.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(final DisposeEvent e) {
                parentViewer.removeSelectionChangedListener(selectionListener);
            }
        });

        // create and register (in constructor) a dedicated SWT event listener on the labelTextWidget
        new LabelTextWidgetListener();

    }

    /**
     * 
     * @author chsch
     */
    private class LabelTextWidgetListener implements Listener {
        
        /**
         * Constructor.
         */
        public LabelTextWidgetListener() {
            final StyledText text = labelTextWidget;
            text.addListener(SWT.MouseDoubleClick, this);
            text.addListener(SWT.KeyDown, this);
            text.addListener(SWT.MouseUp, this);
            text.addListener(SWT.KeyDown, this);
            text.addListener(SWT.KeyUp, this);
        }

        private String prevSelection = null;
        
        public void handleEvent(final Event event) {
            final PiccoloViewerUI thisViewer = PiccoloViewerUI.this;
            final StyledText text = labelTextWidget;

            switch (event.type) {
            case SWT.MouseDoubleClick:
                text.selectAll();
                
                // the following statement is more or less good will,
                //  it will not have direct functional effect
                text.getAccessible().textSelectionChanged();
                
                thisViewer.updateSelection(
                        new KlighdTextSelection(text.getText(), true, true));
                break;

            case SWT.KeyDown:
                if (((char) event.keyCode == SWT.CR) && ((event.stateMask & SWT.SHIFT) != 0)) {
                    updateModelAfterTextChange(text, thisViewer.getViewContext());
                    text.setEditable(false);
                    text.setVisible(false);

                } else if ((char) event.keyCode == SWT.SHIFT) {
                    prevSelection = labelTextWidget.getSelectionText();
                }
                break;

            case SWT.KeyUp:
                System.out.println(event.keyCode + " " + SWT.SHIFT);
                if (event.keyCode == SWT.SHIFT) {
                    final String selection = labelTextWidget.getSelectionText();
                    if (!selection.equals(prevSelection)) {
                        thisViewer.updateSelection(new KlighdTextSelection(selection, false, false));
                    }
                }
                break;

            case SWT.MouseUp:
                final String selection = text.getSelectionText();
                thisViewer.updateSelection(new KlighdTextSelection(selection, false, false));
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateSelection(final ISelection selection) {
        super.updateSelection(selection);
        System.out.println("Selection changed: "); // + selection);
    }

    /**
     * Is used to update the underlying business model according to the change of text (label)
     * represented by the {@link StyledText} widget <code>textWidget</code>. Identifies the
     * corresponding KText & {@link KGraphElement} data, asks for a matching model update function,
     * and, if available, executes that function (wraps it with transaction helpers if required).
     * 
     * @param textWidget
     *            the {@link StyledText} widget containing the updated text (label)
     * @param viewContext
     *            the employed {@link ViewContext}
     */
    private void updateModelAfterTextChange(final StyledText textWidget, final ViewContext viewContext) {
        final KlighdStyledText textNode = (KlighdStyledText) textWidget
                        .getData(KlighdLabelWidgetHandler.STYLED_TEXT_FIGURE_KEY);

        if (textNode == null) {
            return;
        }

        final KLabelNode relatedLabel;
        final KGraphElement relatedKGE;

        // determine whether 'textNode' is contained by a KLabelNode and if so reveal that label node
        //  this is done based on the PNodes since potential KRenderingRefs are resolved while
        //  building up the PNode network,
        // identifying the corresponding KLabel on the KGraph/KRendering will be much more difficult!
        PNode node = textNode;
        while (true) {
            node = node.getParent();

            if (node instanceof INode) {
                // the textNode appears not to be contained in a KLabelNode but
                //  (via path nodes and helper ones) directly in a KNodeNode or KNodeTopNode
                relatedLabel = null;
                relatedKGE = ((INode) node).getGraphElement();
                break;

            } else if (node instanceof KLabelNode) {
                // the text is contained in a KLabelNode ...
                relatedLabel = (KLabelNode) node;
                relatedKGE = relatedLabel.getGraphElement();
                break;
            }
        }

        if (relatedLabel != null && textWidget.getText().equals(relatedLabel.getText())
                || relatedLabel == null && textWidget.getText().equals(textNode.getText())) {
            // A performance short cut: if the widget's text doesn't differ from the related KText's
            //  text or, if available, the corresponding KLabel's text stop here!
            // Note that the '== null' check in 2nd condition alternative is important!!
            return;
        }

        // by means of the (accessible) KText and parent KGraphElement request a
        //  model update function from the employed diagram synthesis
        final Function<String, Void> f = viewContext.getDiagramSynthesis().getTextUpdateFunction(
                textNode.getGraphElement(), relatedKGE);

        if (f == null) {
            // in case no function for updating that particular text (label) is available,
            // hence this text cannot be updated, stop here!
            return;
        }

        final IWorkbenchPart wPart = viewContext.getSourceWorkbenchPart();
        final IModelModificationHandler handler =
                ModelModificationHandlerProvider.getInstance().getFittingHandler(wPart);
        try {
            if (handler != null) {
                handler.execute(wPart, f, textWidget.getText());
            } else {
                f.apply(textWidget.getText());
            }
        } catch (final Exception e) {
            final String msg =
                    "KLighD: An error occured while applying the updated string value in "
                            + viewContext.getDiagramWorkbenchPart().getPartId() + "!";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID, msg, e));
        }
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
}
