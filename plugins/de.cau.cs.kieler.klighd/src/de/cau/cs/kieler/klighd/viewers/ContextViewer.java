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
package de.cau.cs.kieler.klighd.viewers;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KLineStyle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.LineStyle;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.IViewerEventListener;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.TransformationOption;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.options.OptionControlFactory;
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger;
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger.KlighdSelectionState;
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger.KlighdSelectionState.SelectionElement;
import de.cau.cs.kieler.klighd.views.DiagramViewManager;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;

/**
 * A viewer for instances of type {@code ViewContext}. This viewer acts as a wrapper for the viewer
 * supplied by the current view context. The method {@code getControl} returns the control for that
 * viewer and all other methods are delegated to the wrapped viewer.<br>
 * <br>
 * In addition it is possible to set a message to be shown instead of a view context, the wrapped
 * viewer is then of type {@code StringViewer}.<br>
 * <br>
 * This viewer also implements the {@code ISelectionProvider} interface and acts as the KLighD view
 * provider for selection events.
 * 
 * @author mri
 * @author chsch
 * @author msp
 */
public class ContextViewer extends AbstractViewer<Object> implements IViewerEventListener,
        ISelectionProvider {

    /** the workbench part for which the viewer is created. */
    private IWorkbenchPart workbenchPart;
    /** the parent composite for diagram viewers. */
    private Composite parent;
    /** the id of the view this viewer belongs to. */
    private String viewId;
    /** the current viewer. */
    private IViewer<Object> currentViewer;
    /** the current view context. */
    private ViewContext currentViewContext = null;
    /** the selection listeners registered on this view. */
    private Set<ISelectionChangedListener> selectionListeners
        = new LinkedHashSet<ISelectionChangedListener>();
    /** the current selection. */
    private Selection selection = new Selection();
    
    /** the factory for option controls. */
    private OptionControlFactory optionControlFactory;
    /** controller for the expanded options pane. */
    private final PaneController expandedController = new PaneController();
    /** controller for the collapsed options pane. */
    private final PaneController collapsedController = new PaneController();
    /** the form toolkit. */
    private FormToolkit formToolkit;
    /** the set of resources to be disposed when the view is closed. */
    private final List<Resource> resources = new LinkedList<Resource>();

    /**
     * Constructs a view context viewer.
     * 
     * @param parent
     *            the parent composite
     * @param viewId
     *            the id of the view this viewer belongs to
     * @param workbenchPart
     *            the workbench part this view is attached to
     */
    public ContextViewer(final Composite parent, final String viewId,
            final IWorkbenchPart workbenchPart) {
        Composite diagramContainer = new Composite(parent, SWT.NONE);
        this.parent = diagramContainer;
        this.viewId = viewId;
        this.workbenchPart = workbenchPart;
        showMessage("");
        
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.verticalSpan = 2;
        diagramContainer.setLayoutData(gridData);
        diagramContainer.setLayout(new FillLayout());
        
        // create the options pane
        createOptionsContainer(parent);
    }
    
    /**
     * Release all resources created for this viewer.
     */
    public void dispose() {
        if (formToolkit != null) {
            formToolkit.dispose();
            formToolkit = null;
        }
        for (Resource res : resources) {
            res.dispose();
        }
        resources.clear();
    }

    /**
     * {@inheritDoc}
     */
    public Control getControl() {
        return currentViewer.getControl();
    }
    
    /**
     * {@inheritDoc}
     */
    public ContextViewer getContextViewer() {
        return this;
    }


    /**
     * Update the options to be displayed in the options pane.
     * TODO make the selection of options configurable through method arguments
     */
    public void updateOptions() {
        // remove any option controls that have been created before
        optionControlFactory.clear();
        // initialize a layout configuration for retrieving default values
        optionControlFactory.initialize();
        
        // TODO implement a generic interface for selecting layout options
        // SUPPRESS CHECKSTYLE NEXT 5 MagicNumber 
        optionControlFactory.createControl(LayoutOptions.ALGORITHM.getId());
        optionControlFactory.createControl(LayoutOptions.SPACING.getId(), 3f, 200f);
        optionControlFactory.createControl(LayoutOptions.RANDOM_SEED.getId(), 1f, 100f);
        optionControlFactory.createControl(LayoutOptions.DIRECTION.getId(),
                EnumSet.of(Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN));
        
        // TODO make this configurable, too
        collapsedController.setVisible(false);
        expandedController.setVisible(true);
    }
    
    
    /**
     * Fills the synthesis option menu after the model (viewContext) has been set.
     */
    private void updateOptionsMenu() {
        if (workbenchPart instanceof DiagramViewPart) {
            DiagramViewPart viewPart = (DiagramViewPart) workbenchPart;
    
            final ViewContext context = viewPart.getContextViewer().getCurrentViewContext();
            if (context == null) {
                return;
            }
    
            IMenuManager mm = viewPart.getViewSite().getActionBars().getMenuManager();
            for (IContributionItem item : mm.getItems()) {
                // remove all contribution items that do not start with the prefix for permanent actions
                if (item.getId() == null
                        || !item.getId().startsWith(DiagramViewPart.PERMANENT_ACTION_PREFIX)) {
                    mm.remove(item);
                }
            }
    
            for (final Map.Entry<TransformationContext<?, ?>, Set<TransformationOption>> entry : context
                    .getTransformationOptions().entrySet()) {
                mm.add(new Separator());
                
                for (final TransformationOption option : entry.getValue()) {
                    
                    if (option.isCheckOption()) {
                        mm.add(new OptionEntryAction(option.getName(), IAction.AS_CHECK_BOX,
                                (Boolean) entry.getKey().getOptionValue(option)) {
                            public void runWithEvent(final Event event) {
                                if ((event.type & SWT.MouseUp) != 0) {
                                    entry.getKey().configureOption(option, isChecked());
                                    DiagramViewManager.getInstance().updateView(viewId);
                                }
                            }
                        });
                        
                    } else if (option.isChoiceOption()) {
                        mm.add(new Separator(entry.getKey().getTransformation().getClass()
                                .getSimpleName() + "_" + option.getName()));
                        
                        for (final Object value : option.getValues()) {
                            
                            mm.add(new OptionEntryAction(value.toString(), IAction.AS_RADIO_BUTTON,
                                    option.getInitialValue().equals(value)) {
                                public void runWithEvent(final Event event) {
                                    if ((event.type & SWT.MouseUp) != 0 && isChecked()) {
                                        entry.getKey().configureOption(option, value);
                                        DiagramViewManager.getInstance().updateView(viewId);
                                    }
                                }                        
                            });
                        }
                    }
                }
            }
            viewPart.getViewSite().getActionBars().updateActionBars();
        }
    }
    
    /**
     * A simple paint listener that draws a vertical line.
     */
    private final class LinePainter implements PaintListener {
        public void paintControl(final PaintEvent event) {
            Point size = ((Control) event.widget).getSize();
            event.gc.setForeground(Display.getCurrent().getSystemColor(
                    SWT.COLOR_WIDGET_NORMAL_SHADOW));
            event.gc.drawLine(1, 0, 1, size.y);
        }
    }
    
    /**
     * The initial width of the option pane.
     */
    private static final int DEFAULT_PALETTE_WIDTH = 150;
    /**
     * The minimal width of the option pane and the diagram viewer.
     */
    private static final int MIN_WIDTH = 60;
    
    /**
     * Create the container for layout options, including controls for collapsing and expanding.
     * 
     * @param optParent the parent composite into which controls are created
     */
    private void createOptionsContainer(final Composite optParent) {
        // create the right arrow for collapsing the options pane
        Label rightArrowLabel = new Label(optParent, SWT.NONE);
        GridData rightArrowLayoutData = new GridData(SWT.CENTER, SWT.TOP, false, false);
        rightArrowLabel.setLayoutData(rightArrowLayoutData);
        Image rightArrow = KlighdPlugin.getImageDescriptor("icons/arrow-right.gif").createImage();
        resources.add(rightArrow);
        rightArrowLabel.setImage(rightArrow);
        
        // create container for options
        formToolkit = new FormToolkit(optParent.getDisplay());
        ScrolledForm form = formToolkit.createScrolledForm(optParent);
        form.setText("Options");
        final GridData formLayoutData = new GridData(SWT.FILL, SWT.FILL, false, true);
        formLayoutData.widthHint = DEFAULT_PALETTE_WIDTH;
        formLayoutData.verticalSpan = 2;
        form.setLayoutData(formLayoutData);
        Composite optionsContainer = form.getBody();
        optionsContainer.setLayout(new GridLayout(2, false));
        
        // create the factory for option controls to fill the options container
        optionControlFactory = new OptionControlFactory(optionsContainer, workbenchPart, formToolkit);
        
        // create the left arrow for expanding the options pane
        Label leftArrowLabel = new Label(optParent, SWT.NONE);
        GridData leftArrowLayoutData = new GridData(SWT.CENTER, SWT.TOP, false, false);
        leftArrowLayoutData.horizontalSpan = 2;
        leftArrowLabel.setLayoutData(leftArrowLayoutData);
        Image leftArrow = KlighdPlugin.getImageDescriptor("icons/arrow-left.gif").createImage();
        resources.add(leftArrow);
        leftArrowLabel.setImage(leftArrow);
        
        // create the sash for resizing the options pane
        Sash sash = new Sash(optParent, SWT.VERTICAL);
        GridData sashLayoutData = new GridData(SWT.CENTER, SWT.FILL, false, true);
        sash.setLayoutData(sashLayoutData);
        sash.addPaintListener(new LinePainter());
        sash.addListener(SWT.Selection, new Listener() {
            public void handleEvent(final Event event) {
                // the following filter doesn't work on OSX, event.detail is always zero
                // if (event.detail == SWT.DRAG) {
                    // FIXME the "30" in the next line was determined experimentally
                    // SUPPRESS CHECKSTYLE NEXT MagicNumber
                    int newWidth = optParent.getClientArea().width - (event.x + 30);
                    if (event.x > MIN_WIDTH && newWidth > MIN_WIDTH) {
                        formLayoutData.widthHint = newWidth;
                        optParent.layout();
                    }
                // }
            }
        });
        
        // create a line below the expansion arrow
        Composite dummyLine = new Composite(optParent, SWT.NONE);
        GridData dummyLineLayoutData = new GridData(SWT.CENTER, SWT.FILL, false, true);
        dummyLineLayoutData.widthHint = 3;  // SUPPRESS CHECKSTYLE MagicNumber
        dummyLineLayoutData.horizontalSpan = 2;
        dummyLine.setLayoutData(dummyLineLayoutData);
        dummyLine.addPaintListener(new LinePainter());
        
        // create controllers for collapsing and expanding
        expandedController.controls = new Control[] {
                rightArrowLabel, sash, form
        };
        expandedController.layoutData = new GridData[] {
                rightArrowLayoutData, sashLayoutData, formLayoutData
        };
        expandedController.setVisible(false);
        collapsedController.controls = new Control[] {
                leftArrowLabel, dummyLine
        };
        collapsedController.layoutData = new GridData[] {
                leftArrowLayoutData, dummyLineLayoutData
        };
        collapsedController.setVisible(false);
        
        // register actions for the collapse / expand labels
        rightArrowLabel.addMouseListener(new MouseAdapter() {
            public void mouseUp(final MouseEvent event) {
                expandedController.setVisible(false);
                collapsedController.setVisible(true);
                optParent.layout();
            }
        });
        leftArrowLabel.addMouseListener(new MouseAdapter() {
            public void mouseUp(final MouseEvent event) {
                collapsedController.setVisible(false);
                expandedController.setVisible(true);
                optParent.layout();
            }
        });
        
        // set the grid layout of the parent container
        GridLayout gridLayout = new GridLayout(3, false); // SUPPRESS CHECKSTYLE MagicNumber
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        optParent.setLayout(gridLayout);
    }
    
    /**
     * A controller class for easy collapsing and expanding.
     */
    private class PaneController {
        
        /** the controls that are made visible or invisible. */
        private Control[] controls;
        /** the layout data of the controls, used to exclude the controls from the grid. */
        private GridData[] layoutData;
        
        /**
         * Make the contained controls visible or invisible.
         * 
         * @param visible {@code true} to make all controls visible, or {@code false} to make
         *              them all invisible
         */
        void setVisible(final boolean visible) {
            for (Control c : controls) {
                c.setVisible(visible);
            }
            for (GridData ld : layoutData) {
                ld.exclude = !visible;
            }
        }
    }
    

    /**
     * {@inheritDoc}
     */
    public synchronized void setModel(final Object model, final boolean sync) {
        // if the model is a view context adapt the viewer to the given context if possible
        if (model instanceof ViewContext) {
            ViewContext viewContext = (ViewContext) model;
            // remove the old viewer
            removeViewer();

            // create the new viewer
            IViewer<?> viewer =
                    LightDiagramServices.getInstance().createViewer(this, viewContext, parent);

            // add the new viewer
            addViewer(viewer);
            // set the new view context
            currentViewContext = viewContext;
            // reset the current selection
            resetSelection();
            
        } else if (model instanceof String) {
            // if the model is a string show it
            showMessage((String) model);

            // reset the current selection
            resetSelection();
        }
        
        // fill menu with the option entries provided by the incorporated transformations
        updateOptionsMenu();
    }
    
    
    /**
     * A {@link Action Actions} representing view synthesis options in
     * {@link org.eclipse.ui.IViewPart IViewParts}' menus.
     * 
     * @author chsch
     */
    private static class OptionEntryAction extends Action {
     
        public OptionEntryAction(final String text, final int style, final Boolean initiallyChecked) {
            super(text, style);
            this.setChecked(initiallyChecked);
        }
    }
    

    /**
     * {@inheritDoc}
     */
    public synchronized Object getModel() {
        if (currentViewer != null) {
            return currentViewer.getModel();
        }
        return null;
    }

    /**
     * Shows the given message.
     * 
     * @param message
     *            the message
     */
    public synchronized void showMessage(final String message) {
        if (!((IViewer<?>) currentViewer instanceof StringViewer)) {
            removeViewer();
            addViewer(new StringViewer(parent));
        }
        currentViewer.setModel(message, false);
    }

    @SuppressWarnings("unchecked")
    private synchronized void addViewer(final IViewer<?> viewer) {
        currentViewer = (IViewer<Object>) viewer;
        parent.layout();
        currentViewer.addEventListener(this);
    }

    private synchronized void removeViewer() {
        if (currentViewer != null) {
            currentViewer.removeEventListener(this);
            currentViewer.getControl().dispose();
            currentViewer = null;
            currentViewContext = null;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public IContentOutlinePage getOutlinePage() {
        if (currentViewer != null) {
            return currentViewer.getOutlinePage();
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public void selected(final IViewer<?> viewer, final EObject selectedElement) {
        notifyListenersSelected(selectedElement);
    }

    /**
     * {@inheritDoc}
     */
    public void unselected(final IViewer<?> viewer, final EObject unselectedElement) {
        notifyListenersUnselected(unselectedElement);
    }

    /**
     * {@inheritDoc}
     */
    public void selection(final IViewer<?> viewer, final Iterable<? extends Object> selectedElements) {
        // here the selected elements are assumed to be diagram elements, i.e. KGraph elements or KTexts
        List<? extends EObject> diagramElements = Lists.newLinkedList(Iterables.filter(
                selectedElements, EObject.class));
        
        updateSelectionHighlighting(diagramElements);
        
        KlighdSelectionTrigger trigger = KlighdSelectionTrigger.getInstance();
        if (trigger != null) {
            // create the selection objects
            List<SelectionElement> selections = new LinkedList<SelectionElement>();
            // create the selection state
            KlighdSelectionState state = new KlighdSelectionState(viewId, getCurrentViewContext(),
                    currentViewer, selections);
            // fill the selection
            for (EObject diagramObject : diagramElements) {
                selections.add(state.new SelectionElement(diagramObject));
            }
            trigger.trigger(state);
        }
        
        // update the selection status for the ISelectionProvider interface
        List<Object> selectedModelElements = Lists.newArrayList();
        Object modelElement;
        for (Object element : selectedElements) {            
            modelElement = getCurrentViewContext().getSourceElement(element);
            if (modelElement != null) {
                selectedModelElements.add(modelElement);
            }
        }
        updateSelection(selectedModelElements);
        
        // propagate event to listeners on this viewer
        notifyListenersSelection(selectedModelElements);  
    }

    /** a map used to track the highlighting styles, which have been attached to selected elements. */
    private Map<EObject, Iterable<? extends KStyle>> selectionHighlighting = Maps.newHashMap();
    
    private void updateSelectionHighlighting(final List<? extends EObject> diagramElements) {
        // chsch: the following lines realizes the highlighting of selected diagram elements
        //  (if the diagram is given as a KGraph/KRendering model)
        List<?> noLongerSelected = Lists.newLinkedList(selectionHighlighting.keySet());
        noLongerSelected.removeAll(diagramElements);
        for (Object element : noLongerSelected) {
            // the related value should never be null!!
            for (KStyle style : selectionHighlighting.remove(element)) {
                EcoreUtil.remove(style);
            }
        }
        
        List<EObject> newlySelected = Lists.newLinkedList(Iterables.filter(diagramElements,
                EObject.class));
        newlySelected.removeAll(selectionHighlighting.keySet());

        for (EObject element : newlySelected) {

            final KBackground bg = KRenderingFactory.eINSTANCE.createKBackground();
            final KColor bgColor = KRenderingFactory.eINSTANCE.createKColor();
            bg.setColor(bgColor);
            // the color values of 'DimGray'   // SUPPRESS CHECKSTYLE NEXT 3 MagicNumber
            bgColor.setRed(190);
            bgColor.setGreen(190);
            bgColor.setBlue(190);

            if (KGraphPackage.eINSTANCE.getKGraphElement().isInstance(element)) {
                KRendering rendering = ((KGraphElement) element).getData(KRendering.class);
                KLineStyle style = KRenderingFactory.eINSTANCE.createKLineStyle();
                if (rendering != null) {
                    style.setLineStyle(LineStyle.DASH);
                    List<KStyle> styles = Lists.newArrayList(style, bg);
                    rendering.getStyles().addAll(styles);
                    selectionHighlighting.put(element, styles);
                    
                    if (KGraphPackage.eINSTANCE.getKEdge().isInstance(element)) {
                        for (KStyle s: styles) {
                            s.setPropagateToChildren(true);
                        }
                    }
                }
            } else if (KRenderingPackage.eINSTANCE.getKText().isInstance(element)) {
                ((KText) element).getStyles().add(bg);                
                selectionHighlighting.put(element, Lists.newArrayList(bg));
            }
        }
        // end of selection highlighting stuff
    }
        
    private void updateSelection(final List<?> selectedElements) {
        synchronized (selection) {
            selection.selectedElements.clear();
            selection.selectedElements.addAll(selectedElements);
        }
        notifySelectionListeners();
    }

    private void resetSelection() {
        synchronized (selection) {
            selection.selectedElements.clear();
        }
        notifySelectionListeners();
    }

    private void notifySelectionListeners() {
        synchronized (selectionListeners) {
            if (selectionListeners.size() > 0) {
                // create a clone of the selection
                Selection clone;
                synchronized (selection) {
                    clone = selection.clone();
                }
                // notify all selection listeners
                for (ISelectionChangedListener listener : selectionListeners) {
                    listener.selectionChanged(new SelectionChangedEvent(this, clone));
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setRecording(final boolean recording) {
        currentViewer.setRecording(recording);
    }

    /**
     * {@inheritDoc} 
     */
    public void setZoomToFit(final boolean zoomToFit) {
        currentViewer.setZoomToFit(zoomToFit);
    }
    
    /**
     * {@inheritDoc}
     */
    public void collapse(final Object semanticElement) {
        Object diagramNode = getCurrentViewContext().getTargetElement(semanticElement);
        if (diagramNode instanceof KNode) {
            currentViewer.collapse((KNode) diagramNode);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void collapse(final KNode diagramElement) {
        currentViewer.collapse(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void expand(final Object semanticElement) {
        Object diagramNode = getCurrentViewContext().getTargetElement(semanticElement);
        if (diagramNode instanceof KNode) {
            currentViewer.expand((KNode) diagramNode);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void expand(final KNode diagramElement) {
        currentViewer.expand(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleExpansion(final Object semanticElement) {
        Object diagramNode = getCurrentViewContext().getTargetElement(semanticElement);
        if (diagramNode instanceof KNode) {
            currentViewer.toggleExpansion((KNode) diagramNode);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleExpansion(final KNode diagramElement) {
        currentViewer.toggleExpansion(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public ISelection getSelection() {
        synchronized (selection) {
            return selection.clone();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setSelection(final ISelection selection) {
        // not supported yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelection(final Iterable<EObject> diagramElements) {
        if (currentViewer != null) {
            currentViewer.setSelection(diagramElements);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearSelection() {
        if (currentViewer != null) {
            currentViewer.clearSelection();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void select(final Iterable<EObject> diagramElements) {
        if (currentViewer != null) {
            currentViewer.select(diagramElements);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unselect(final Iterable<EObject> diagramElements) {
        if (currentViewer != null) {
            currentViewer.unselect(diagramElements);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reveal(final EObject diagramElement, final int duration) {
        if (currentViewer != null) {
            currentViewer.reveal(diagramElement, duration);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void centerOn(final EObject diagramElement, final int duration) {
        if (currentViewer != null) {
            currentViewer.centerOn(diagramElement, duration);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void zoom(final float zoomLevel, final int duration) {
        if (currentViewer != null) {
            currentViewer.zoom(zoomLevel, duration);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void zoomToFit(final int duration) {
        if (currentViewer != null) {
            currentViewer.zoomToFit(duration);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
        selectionListeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
        selectionListeners.remove(listener);
    }

    /**
     * Returns the {@link IWorkbenchPart} this viewer is attached to.
     * 
     * @return the workbench part
     */
    public IWorkbenchPart getWorkbenchPart() {
        return workbenchPart;
    }

    /**
     * Returns the currently active viewer.
     * 
     * @return the viewer
     */
    public IViewer<?> getActiveViewer() {
        return currentViewer;
    }

    /**
     * Returns the id of the related view part.
     * 
     * @return the view part id.
     */
    public String getViewPartId() {
        return viewId;
    }

    /**
     * Returns the currently active view context.
     * 
     * @return the view context
     */
    public synchronized ViewContext getCurrentViewContext() {
        return currentViewContext;
    }

    /**
     * An implementation of {@code IStructuredSelection} for the {@code ISelectionProvider}.
     */
    private class Selection implements IStructuredSelection, Iterable<Object>, Cloneable {
        // TODO chsch: IMO implementing ITreeSelection is reasonable and helpful 

        /** the objects which make up the selection. */
        private List<Object> selectedElements = new LinkedList<Object>();

        /**
         * {@inheritDoc}
         */
        public boolean isEmpty() {
            return selectedElements.isEmpty();
        }

        /**
         * {@inheritDoc}
         */
        public Object getFirstElement() {
            if (selectedElements.isEmpty()) {
                return null;
            } else {
                return selectedElements.get(0);
            }
        }

        /**
         * {@inheritDoc}
         */
        public Iterator<Object> iterator() {
            return selectedElements.iterator();
        }

        /**
         * {@inheritDoc}
         */
        public int size() {
            return selectedElements.size();
        }

        /**
         * {@inheritDoc}
         */
        public Object[] toArray() {
            return selectedElements.toArray();
        }

        /**
         * {@inheritDoc}
         */
        public List<Object> toList() {
            return selectedElements;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Selection clone() {
            Selection clone = new Selection();
            clone.selectedElements.addAll(selectedElements);
            return clone;
        }

    }

}
