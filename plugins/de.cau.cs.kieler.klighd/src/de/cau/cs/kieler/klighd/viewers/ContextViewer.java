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

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

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
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.IViewerEventListener;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.TransformationOption;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.options.LayoutOptionControlFactory;
import de.cau.cs.kieler.klighd.internal.options.LightLayoutConfig;
import de.cau.cs.kieler.klighd.internal.options.SynthesisOptionControlFactory;
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger;
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger.KlighdSelectionState;
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger.KlighdSelectionState.SelectionElement;
import de.cau.cs.kieler.klighd.util.ModelingUtil;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;
import de.cau.cs.kieler.klighd.views.IDiagramWorkbenchPart;

/**
 * A viewer for instances of type {@code ViewContext}. It is instantiated by
 * {@link de.cau.cs.kieler.klighd.views.DiagramViewPart DiagramViewPart} and
 * {@link de.cau.cs.kieler.klighd.views.DiagramEditorPart DiagramEditorPart}.<br>
 * <br>
 * This class acts as a wrapper for the viewer supplied by the current view context. The method
 * {@code getControl} returns the control of that viewer, all other methods are delegated to the
 * wrapped viewer.<br>
 * <br>
 * The motivation of this class is the intended multiformity of model viewers (although only the
 * Piccolo2D-based one has been realized). In addition, multiple diagram viewers, i.e. diagram
 * exhibiting controls, might be hosted and unified within a {@link ContextViewer}.<br>
 * <br>
 * During the initialization it is possible to set a message to be shown instead of a view context,
 * the wrapped viewer is then of type {@code StringViewer}.<br>
 * <br>
 * This viewer also implements the {@code ISelectionProvider} interface and acts as KLighD's
 * provider of selection events.
 * 
 * @author mri
 * @author chsch
 * @author msp
 */
public class ContextViewer extends AbstractViewer<Object> implements IViewerEventListener,
        ISelectionProvider {

    /** the workbench part for which the viewer is created. */
    private IDiagramWorkbenchPart workbenchPart;
    /** the parent composite for diagram viewers. */
    private Composite diagramComposite;
    /** the id of the view this viewer belongs to. */
    private String viewId;
    /** the current viewer. */
    private IViewer<Object> currentViewer;
    /** the current view context. */
    private ViewContext currentViewContext = null;
    /** the factory for diagram synthesis option controls. */
    private SynthesisOptionControlFactory synthesisOptionControlFactory;
    /** the factory for layout option controls. */
    private LayoutOptionControlFactory layoutOptionControlFactory;
    /** the form toolkit, is only kept to properly dispose it finally. */
    private FormToolkit optionsformToolkit;
    /** The form that holds synthesis options. */
    private Form synthesisOptionsForm;
    /** The form that holds layout options. */
    private Form layoutOptionsForm;
    /** the set of resources to be disposed when the view is closed. */
    private final List<Resource> resources = new LinkedList<Resource>();

    /** The layout configurator that stores the values set by the layout option controls. */
    private LightLayoutConfig lightLayoutConfig = new LightLayoutConfig();

    
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
            final IDiagramWorkbenchPart workbenchPart) {
        this.viewId = viewId;
        this.workbenchPart = workbenchPart;
        
        // introduce a new Composite that accommodates the visualized content
        this.diagramComposite = new Composite(parent, SWT.NONE);
        this.diagramComposite.setLayout(new FillLayout());
        
        // create the options pane
        createOptionsContainer(this.diagramComposite);

        // initialize with the display of an empty string
        showMessage("");
    }
    
    /**
     * Release all resources created for this viewer.
     */
    public void dispose() {
        if (optionsformToolkit != null) {
            optionsformToolkit.dispose();
            optionsformToolkit = null;
        }
        for (Resource res : resources) {
            res.dispose();
        }
        resources.clear();
    }

    /**
     * Update the options to be displayed in the options pane.
     * 
     * @param fitSpace true if the diagram shall fit the available space
     */
    public void updateOptions(final boolean fitSpace) {
        if (this.diagramComposite.isDisposed()) {
            return;
        }
        
        // remove any option controls that have been created before
        synthesisOptionControlFactory.clear();
        
        // remove any option controls that have been created before
        layoutOptionControlFactory.clear();
        // initialize a layout configuration for retrieving default values
        layoutOptionControlFactory.initialize();

        Map<IProperty<?>, Collection<?>> recommendedOptions =
                currentViewContext.getRecommendedLayoutOptions();
        
        boolean layoutOptionsAvailable = false;
        for (Entry<IProperty<?>, Collection<?>> entry : recommendedOptions.entrySet()) {
            Collection<?> values = entry.getValue();
            Object first = Iterables.get(values, 0, null);
            Object second = Iterables.get(values, 1, null);
            
            if (values.size() == 2 && first instanceof Number && second instanceof Number) {
                layoutOptionControlFactory.createControl(entry.getKey().getId(),
                        ((Number) first).floatValue(), ((Number) second).floatValue());
                layoutOptionsAvailable = true;
            } else if (values.size() == 0) {
                layoutOptionControlFactory.createControl(entry.getKey().getId());
                layoutOptionsAvailable = true;
            } else {
                layoutOptionControlFactory.createControl(entry.getKey().getId(), values);
                layoutOptionsAvailable = true;
            }
        }
        
        boolean synthesisOptionsAvailable = false;
        for (final Map.Entry<TransformationContext<?, ?>, Set<TransformationOption>> entry
                : this.getCurrentViewContext().getTransformationOptions().entrySet()) {
            for (final TransformationOption option : entry.getValue()) {
                if (option.isCheckOption()) {
                    synthesisOptionControlFactory.createCheckOptionControl(option, entry.getKey(),
                            viewId);
                    synthesisOptionsAvailable = true;
                } else if (option.isChoiceOption()) {
                    synthesisOptionControlFactory.createChoiceOptionControl(option, entry.getKey(),
                            viewId);
                    synthesisOptionsAvailable = true;
                } else if (option.isRangeOption()) {
                    synthesisOptionControlFactory.createRangeOptionControl(option, entry.getKey(),
                            viewId);
                    synthesisOptionsAvailable = true;
                } else if (option.isSeparator()) {
                    synthesisOptionControlFactory.createSeparator();
                }
            }
        }
        
        this.enableOptionsSideBar(fitSpace, synthesisOptionsAvailable, layoutOptionsAvailable);
        if (workbenchPart instanceof DiagramViewPart) {
            DiagramViewPart viewPart = (DiagramViewPart) workbenchPart;
            viewPart.getAction(DiagramViewPart.ACTION_ID_RESET_LAYOUT_OPTIONS)
                    .setEnabled(layoutOptionsAvailable);
        }
    }
    
    /**
     * Returns the layout option control factory.
     * 
     * @return the layout option control factory
     */
    public LayoutOptionControlFactory getLayoutOptionControlFactory() {
        return layoutOptionControlFactory;
    }
    
    /** The initial width of the option pane and the diagram viewer. */
    private static final int INITIAL_OPTIONS_FORM_WIDTH = 230;
    
    /** The minimal width of the option pane and the diagram viewer. */
    private static final int MINIMAL_OPTIONS_FORM_WIDTH = 100;
    
    /** The space left between the 'Diagram options' and 'Layout options' sub forms. */
    private static final int SYNTHESIS_LAYOUT_OPTIONS_SPACE = 20;
    
    /** The width of the separator. */
    private static final int SASH_WIDTH = 7;
    
    /** Constant value denoting 100%. */
    private static final int FULL = 100;
    
    private final List<Control> sideBarControls = Lists.newArrayListWithCapacity(5);
    
    private FormData sashLayoutData = null;

    /**
     * The horizontal position of the side bar's sash in terms of the (negative) offset wrt. the
     * workbenchPart's right side. Its kept as an attribute in order to avoid the re-initialization
     * of the side bar's size in case a different model has been assigned to the current view.
     */
    private int horizontalPos = -INITIAL_OPTIONS_FORM_WIDTH;
    
    /**
     * Create the container for layout options, including controls for collapsing and expanding.
     * 
     * @param diagramContainer
     *            the composite accommodating the diagram,<br>
     *            its parent is assumed to be able to host the option form
     */
    private void createOptionsContainer(final Composite diagramContainer) {
        final Composite partComposite = diagramContainer.getParent();
        partComposite.setLayout(new FormLayout());
        
        // for easier managing the visibility of the side bar's collapse/expand arrows
        //  let's put them in a dedicated container, this is advantageous below
        final Composite arrowsContainer = new Composite(partComposite, SWT.NONE);
        sideBarControls.add(arrowsContainer);
        arrowsContainer.setVisible(false);
        final StackLayout arrowLabelContainerLayout = new StackLayout(); 
        arrowsContainer.setLayout(arrowLabelContainerLayout);

        // create the right arrow for collapsing the options pane
        final Label rightArrowLabel = new Label(arrowsContainer, SWT.NONE);
        final Image rightArrow = KlighdPlugin.getImageDescriptor("icons/arrow-right.gif").createImage();
        resources.add(rightArrow);
        rightArrowLabel.setImage(rightArrow);
        rightArrowLabel.setVisible(true);
        arrowLabelContainerLayout.topControl = rightArrowLabel;

        // create the left arrow for expanding the options pane
        final Label leftArrowLabel = new Label(arrowsContainer, SWT.NONE);
        final Image leftArrow = KlighdPlugin.getImageDescriptor("icons/arrow-left.gif").createImage();
        resources.add(leftArrow);
        leftArrowLabel.setImage(leftArrow);
        leftArrowLabel.setVisible(true);
        
        // create the sash for resizing the options pane
        final Sash sash = new Sash(partComposite, SWT.VERTICAL);
        sideBarControls.add(sash);
        sash.addPaintListener(new LinePainter());
        sash.setVisible(false);
        
        optionsformToolkit = new FormToolkit(partComposite.getDisplay());

        final ScrolledForm formRootScroller = optionsformToolkit.createScrolledForm(partComposite);
        formRootScroller.setText(null);
        sideBarControls.add(formRootScroller);
                
        Composite formRoot = formRootScroller.getBody();
        formRoot.setLayout(new FormLayout());

        // create container for diagram synthesis options
        synthesisOptionsForm = optionsformToolkit.createForm(formRoot);
        sideBarControls.add(synthesisOptionsForm);
        synthesisOptionsForm.setText("Diagram Options");
        synthesisOptionsForm.setVisible(false);
        final Composite synthesisOptionsContainer = synthesisOptionsForm.getBody();
        
        // create the factory for diagram synthesis option controls to fill the options container
        synthesisOptionControlFactory = new SynthesisOptionControlFactory(
                synthesisOptionsContainer, optionsformToolkit);
        
        // create container for layout options
        layoutOptionsForm = optionsformToolkit.createForm(formRoot);
        sideBarControls.add(layoutOptionsForm);
        layoutOptionsForm.setText("Layout Options");
        layoutOptionsForm.setVisible(false);
        final Composite layoutOptionsContainer = layoutOptionsForm.getBody();
        
        // create the factory for layout option controls to fill the options container
        layoutOptionControlFactory = new LayoutOptionControlFactory(layoutOptionsContainer,
                workbenchPart, optionsformToolkit, lightLayoutConfig);
        
        // prepare the form layout data for each of the above created widgets
        final FormData diagramContainerLayoutData = new FormData();
        diagramContainerLayoutData.top = new FormAttachment(0);
        diagramContainerLayoutData.bottom = new FormAttachment(FULL);
        diagramContainerLayoutData.left = new FormAttachment(0); 
        diagramContainerLayoutData.right = new FormAttachment(sash); 
        diagramContainer.setLayoutData(diagramContainerLayoutData);
        
        final FormData arrowsContainerLayoutData = new FormData();
        arrowsContainerLayoutData.top = new FormAttachment(0);
        arrowsContainerLayoutData.bottom = new FormAttachment(sash);
        arrowsContainerLayoutData.left = new FormAttachment(diagramContainer); 
        arrowsContainerLayoutData.right = new FormAttachment(formRootScroller);
        arrowsContainer.setLayoutData(arrowsContainerLayoutData);
        
        sashLayoutData = new FormData();
        sashLayoutData.top = new FormAttachment(arrowsContainer);
        sashLayoutData.bottom = new FormAttachment(FULL);
        sashLayoutData.left = new FormAttachment(FULL);
        sashLayoutData.width = SASH_WIDTH;
        sash.setLayoutData(sashLayoutData);
        
        final FormData formRootLayoutData = new FormData();        
        formRootLayoutData.top = new FormAttachment(0);
        formRootLayoutData.bottom = new FormAttachment(FULL);
        formRootLayoutData.left = new FormAttachment(sash); 
        formRootLayoutData.right = new FormAttachment(FULL); 
        formRootScroller.setLayoutData(formRootLayoutData);
        
        final FormData synthesisOptionsFormLayoutData = new FormData();        
        synthesisOptionsFormLayoutData.top = new FormAttachment(0);
        synthesisOptionsFormLayoutData.left = new FormAttachment(0); 
        synthesisOptionsFormLayoutData.right = new FormAttachment(FULL); 
        synthesisOptionsForm.setLayoutData(synthesisOptionsFormLayoutData);
        
        final FormData layoutOptionsFormLayoutData = new FormData();        
        layoutOptionsFormLayoutData.top = new FormAttachment(
                synthesisOptionsForm, SYNTHESIS_LAYOUT_OPTIONS_SPACE);
        layoutOptionsFormLayoutData.bottom = new FormAttachment(FULL);
        layoutOptionsFormLayoutData.left = new FormAttachment(0); 
        layoutOptionsFormLayoutData.right = new FormAttachment(FULL); 
        layoutOptionsForm.setLayoutData(layoutOptionsFormLayoutData);

        // register the sash moving handler for resizing the options pane
        sash.addListener(SWT.Selection, new Listener() {
            public void handleEvent(final Event event) {
                final int maxDiagSize = partComposite.getClientArea().width - MINIMAL_OPTIONS_FORM_WIDTH;
                if (maxDiagSize > event.x) {
                    sashLayoutData.left.numerator = FULL;
                    sashLayoutData.left.offset = -(partComposite.getClientArea().width - event.x);
                } else {
                    sashLayoutData.left.numerator = FULL;
                    sashLayoutData.left.offset = -MINIMAL_OPTIONS_FORM_WIDTH;
                    // The following line appears to be evil, but this is required
                    //  to let the sash respect the limit correctly.
                    event.x = maxDiagSize;
                }
                horizontalPos = sashLayoutData.left.offset;
                partComposite.layout(true);
            }
        });
        
        // a "pseudo" field ;-)
        final int[] lastXpos = new int[] { 0 };
        
        // register actions for the collapse / expand labels
        rightArrowLabel.addMouseListener(new MouseAdapter() {
            public void mouseUp(final MouseEvent event) {
                sashLayoutData.left.numerator = FULL;
                sashLayoutData.left.offset = -sashLayoutData.width;
                lastXpos[0] = partComposite.getClientArea().width - sash.getBounds().x;                
                horizontalPos = sashLayoutData.left.offset;
                arrowLabelContainerLayout.topControl = leftArrowLabel;
                partComposite.layout(true, true);
            }
        });
        leftArrowLabel.addMouseListener(new MouseAdapter() {
            public void mouseUp(final MouseEvent event) {                
                sashLayoutData.left.numerator = FULL;
                sashLayoutData.left.offset = -lastXpos[0];
                horizontalPos = sashLayoutData.left.offset;
                arrowLabelContainerLayout.topControl = rightArrowLabel;
                partComposite.layout(true, true);
            }
        });
    }
    
    /**
     * A simple paint listener that draws a vertical line.
     */
    private final class LinePainter implements PaintListener {
        public void paintControl(final PaintEvent event) {
            Point size = ((Control) event.widget).getSize();
            event.gc.setForeground(Display.getCurrent().getSystemColor(
                    SWT.COLOR_WIDGET_NORMAL_SHADOW));
            event.gc.drawLine(event.width / 2, 0, event.width / 2, size.y);
        }
    }
    
    /**
     * A simple enabler of the side bar controls. It is to be executed in case there are diagram
     * options to provide in the side bar.
     * 
     * @param zoomToFit
     *            {@code true} if the diagram shall fit the available space
     * @param showSynthesisOptions
     *            {@code true} if the synthesis options group should be displayed.
     * @param showLayoutOptions
     *            {@code true} if the layout options group should be displayed.
     */
    private void enableOptionsSideBar(final boolean zoomToFit, final boolean showSynthesisOptions,
            final boolean showLayoutOptions) {       
        if (showSynthesisOptions || showLayoutOptions) {
            // define the controls (sash, right arrow, form) to be visible
            for (Control c : this.sideBarControls) {
                if (c == synthesisOptionsForm) {
                    c.setVisible(showSynthesisOptions);
                } else if (c == layoutOptionsForm) {
                    c.setVisible(showLayoutOptions);
                } else {
                    c.setVisible(true);
                }
            }
            
            if (!showSynthesisOptions) {
                // in case no diagram synthesis options are available
                //  put the layout options form at the top
                ((FormData) layoutOptionsForm.getLayoutData()).top = new FormAttachment(0);
            } else {
                // restore the initial configuration in case such options are available again
                ((FormData) layoutOptionsForm.getLayoutData()).top = new FormAttachment(
                        synthesisOptionsForm, SYNTHESIS_LAYOUT_OPTIONS_SPACE);
            }
            
            if (this.sashLayoutData != null) {
                this.sashLayoutData.left.numerator = FULL;
                this.sashLayoutData.left.offset = horizontalPos;
            }
            
        } else {
            for (Control c : this.sideBarControls) {
                c.setVisible(false);
            }
            if (this.sashLayoutData != null) {
                this.horizontalPos = this.sashLayoutData.left.offset;
                this.sashLayoutData.left.numerator = FULL;
                this.sashLayoutData.left.offset = 0;
            }
        }
        
        // re-layout the view part's composite
        this.diagramComposite.getParent().layout(true, true);
        
        // let the diagram fit the available space,
        //  should be dependent on a preference setting in future (TODO)
        if (zoomToFit) {
            this.currentViewer.zoom(ZoomStyle.ZOOM_TO_FIT, 0);
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
                    LightDiagramServices.getInstance().createViewer(this, viewContext, diagramComposite);

            // add the new viewer
            addViewer(viewer);
            // set the new view context
            currentViewContext = viewContext;

        } else if (model instanceof String) {
            // if the model is a string show it
            showMessage((String) model);

        }

        // initialize the current selection
        notifySelectionListeners(new KlighdTreeSelection(currentViewContext));
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
     * {@inheritDoc}
     */
    public synchronized Object getModel() {
        if (currentViewer != null) {
            return currentViewer.getModel();
        }
        return null;
    }
    
    
    /**
     * Returns the {@link LightLayoutConfig} that contains the configuration values set via the
     * layout options controls in the side bar.
     * 
     * @return the lightLayoutConfig
     */
    public LightLayoutConfig getLightLayoutConfig() {
        return lightLayoutConfig;
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
            addViewer(new StringViewer(diagramComposite));
        }
        currentViewer.setModel(message, false);
    }

    @SuppressWarnings("unchecked")
    private synchronized void addViewer(final IViewer<?> viewer) {
        currentViewer = (IViewer<Object>) viewer;
        diagramComposite.layout();
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
        List<EObject> diagramElements = Lists.newLinkedList(
                Iterables.filter(selectedElements, EObject.class));
        
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
        notifySelectionListeners(new KlighdTreeSelection(currentViewContext, diagramElements));

        // propagate event to listeners on this viewer
        notifyListenersSelection(selectedElements);  
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
                            s.setPropagateToChildren(false);
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

    /**
     * {@inheritDoc}
     */
    public void startRecording() {
        currentViewer.startRecording();
    }
    
    /**
     * {@inheritDoc}
     */
    public void stopRecording(final ZoomStyle zoomStyle,
            final int animationTime) {
        currentViewer.stopRecording(zoomStyle, animationTime);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExpanded(final Object semanticElement) {
        Object diagramNode = getCurrentViewContext().getTargetElement(semanticElement);
        if (diagramNode instanceof KNode) {
            return currentViewer.isExpanded((KNode) diagramNode);
        } else {
            return false;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isExpanded(final KNode diagramElement) {
        return currentViewer.isExpanded(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collapse(final Object semanticElement) {
        Object diagramNode = getCurrentViewContext().getTargetElement(semanticElement);
        if (diagramNode instanceof KNode) {
            currentViewer.collapse((KNode) diagramNode);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void collapse(final KNode diagramElement) {
        currentViewer.collapse(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void expand(final Object semanticElement) {
        Object diagramNode = getCurrentViewContext().getTargetElement(semanticElement);
        if (diagramNode instanceof KNode) {
            currentViewer.expand((KNode) diagramNode);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void expand(final KNode diagramElement) {
        currentViewer.expand(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleExpansion(final Object semanticElement) {
        final Object diagramNode = getCurrentViewContext().getTargetElement(semanticElement);
        if (diagramNode instanceof KNode) {
            currentViewer.toggleExpansion((KNode) diagramNode);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleExpansion(final KNode diagramElement) {
        currentViewer.toggleExpansion(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void hide(final Object semanticElement) {
        final Object diagramElement = getCurrentViewContext().getTargetElement(semanticElement);
        if (diagramElement instanceof KGraphElement) {
            currentViewer.hide(diagramElement);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void hide(final KGraphElement diagramElement) {
        currentViewer.hide(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void show(final Object semanticElement) {
        final Object diagramElement = getCurrentViewContext().getTargetElement(semanticElement);
        if (diagramElement instanceof KGraphElement) {
            currentViewer.show(diagramElement);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void show(final KGraphElement diagramElement) {
        currentViewer.show(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelection(final Iterable<KGraphElement> diagramElements) {
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
    public void select(final Iterable<KGraphElement> diagramElements) {
        if (currentViewer != null) {
            currentViewer.select(diagramElements);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unselect(final Iterable<KGraphElement> diagramElements) {
        if (currentViewer != null) {
            currentViewer.unselect(diagramElements);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override    
    public void reveal(final Object semanticElement, final int duration) {
        final Object diagramElement = getCurrentViewContext().getTargetElement(semanticElement);
        if (diagramElement instanceof KGraphElement) {
            currentViewer.centerOn(diagramElement, duration);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void reveal(final KGraphElement diagramElement, final int duration) {
        if (currentViewer != null) {
            currentViewer.reveal(diagramElement, duration);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override    
    public void centerOn(final Object semanticElement, final int duration) {
        final Object diagramElement = getCurrentViewContext().getTargetElement(semanticElement);
        if (diagramElement instanceof KGraphElement) {
            currentViewer.centerOn(diagramElement, duration);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void centerOn(final KGraphElement diagramElement, final int duration) {
        if (currentViewer != null) {
            currentViewer.centerOn(diagramElement, duration);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void zoomToLevel(final float zoomLevel, final int duration) {
        if (currentViewer != null) {
            currentViewer.zoomToLevel(zoomLevel, duration);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void zoom(final ZoomStyle style, final int duration) {
        if (currentViewer != null) {
            currentViewer.zoom(style, duration);
        }
    }

    /**
     * Returns the {@link IDiagramWorkbenchPart} this viewer is attached to.
     * 
     * @return the workbench part
     */
    public IDiagramWorkbenchPart getWorkbenchPart() {
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
    
    
    /* ------------------------------- */
    /*   The ISelectionProvider part   */

    /** the current selection. */
    private ISelection selection = null;

    /** the selection listeners registered on this view. */
    private Set<ISelectionChangedListener> selectionListeners = Sets.newLinkedHashSet();

    /**
     * Notifies the registered {@link ISelectionChangedListener ISelectionChangedListeners}. Such
     * listeners are registered e.g. by the platform in order to broadcast changes in the selection
     * across change listeners registered in the {@link org.eclipse.ui.ISelectionService
     * ISelectionService}.
     * 
     * @param theSelection
     */
    private void notifySelectionListeners(final ISelection theSelection) {
        this.selection = theSelection;
        synchronized (selectionListeners) {
            for (ISelectionChangedListener listener : selectionListeners) {
                listener.selectionChanged(new SelectionChangedEvent(this, theSelection));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public ISelection getSelection() {
        return this.selection;
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
     * A specialized {@link TreeSelection} providing the selected view elements as well as
     * {@link ContextViewer} that contributed <code>this</code> selection and an {@link Iterator}
     * providing {@link Pair Pairs} of the selected view elements and their corresponding source
     * model elements.
     * 
     * @author chsch
     */
    public class KlighdTreeSelection extends TreeSelection implements Iterable<Object> {
        
        private ViewContext viewContext;
        
        /**
         * Constructor.
         * 
         * @param theViewContext
         *            the current view's {@link ViewContext}
         * @param path
         *            a single {@link TreePath}.
         */
        public KlighdTreeSelection(final ViewContext theViewContext, final TreePath... path) {
            super(path);
            this.viewContext = theViewContext;
        }

        /**
         * Constructor.
         * 
         * @param viewContext
         *            the current view's {@link ViewContext}
         * @param selectedElements
         *            the view elements being currently selected
         */
        public KlighdTreeSelection(final ViewContext viewContext, final List<EObject> selectedElements) {
            this(viewContext, Iterables.toArray(
                    Lists.transform(selectedElements, new Function<EObject, TreePath>() {
                        public TreePath apply(final EObject eObject) {
                            return new TreePath(Iterables.toArray(Lists.reverse(
                                    Lists.newArrayList(ModelingUtil.selfAndEAllContainers(eObject))),
                                    Object.class));
                        }
                    }), TreePath.class));
        }

        /**
         * Getter.
         * 
         * @return the {@link ContextViewer} providing this selection.
         */
        public ContextViewer getContextViewer() {
            return ContextViewer.this;
        }

        /**
         * Getter.
         * 
         * @return the {@link ViewContext} of the diagram the selection has been performed in.
         */
        public ViewContext getViewContext() {
            return this.viewContext;
        }

        @Override
        public Iterator<Object> iterator() {
            // the aim of this method is only to apply the cast and avoid the warning on class level
            
            @SuppressWarnings("unchecked")
            final Iterator<Object> iterator = (Iterator<Object>) super.iterator();
            return iterator;
        }
        
        /**
         * Analogously to {@link #iterator()} this methods returns an {@link Iterator} providing the
         * source model elements associated with the selected view elements.
         * 
         * @return an {@link Iterator} providing the requested source model elements
         */
        public Iterator<Object> sourceElementIterator() {
            return Iterators.transform(KlighdTreeSelection.this.iterator(),
                    new Function<Object, Object>() {
                        public Object apply(final Object object) {
                            return KlighdTreeSelection.this.viewContext.getSourceElement(object);
                        }
                    });
        }

        /**
         * Analogously to {@link #iterator()} this methods returns an {@link Iterator} providing the
         * source model elements associated with the selected view elements.
         * 
         * @return an {@link Iterator} providing the requested source model elements
         */
        public Iterator<Pair<EObject, Object>> sourceViewPairIterator() {
            return Iterators.transform(KlighdTreeSelection.this.iterator(),
                    new Function<Object, Pair<EObject, Object>>() {
                        public Pair<EObject, Object> apply(final Object object) {
                            return Pair.of((EObject) object,
                                    KlighdTreeSelection.this.viewContext.getSourceElement(object));
                        }
                    });
        }
    }
}
