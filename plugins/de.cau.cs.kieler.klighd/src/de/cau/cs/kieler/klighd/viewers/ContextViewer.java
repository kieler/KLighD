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

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static de.cau.cs.kieler.klighd.util.KlighdPredicates.isSelectable;
import static de.cau.cs.kieler.klighd.util.KlighdPredicates.notIn;
import static java.util.Collections.singleton;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.SynthesisOption;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.IDiagramOutlinePage;
import de.cau.cs.kieler.klighd.internal.ILayoutRecorder;
import de.cau.cs.kieler.klighd.internal.options.LayoutOptionControlFactory;
import de.cau.cs.kieler.klighd.internal.options.SynthesisOptionControlFactory;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
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
public class ContextViewer implements IViewer<Object>, ILayoutRecorder, ISelectionProvider,
        IDiagramOutlinePage.Provider {

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
    /** the {@link #currentViewer} if it is a {@link ILayoutRecorder}, <code>null</code> otherwise. */
    private ILayoutRecorder layoutRecorder;
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
    private VolatileLayoutConfig lightLayoutConfig = new VolatileLayoutConfig();

    
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

        List<Pair<IProperty<?>, List<?>>> recommendedOptions =
                currentViewContext.getDisplayedLayoutOptions();
        
        boolean layoutOptionsAvailable = false;
        for (Pair<IProperty<?>, List<?>> pair : recommendedOptions) {
            IProperty<?> first = pair.getFirst();
            Collection<?> second = pair.getSecond();
            
            if (first instanceof Number && second instanceof Number) {
                layoutOptionControlFactory.createControl(pair.getFirst().getId(),
                        ((Number) first).floatValue(), ((Number) second).floatValue());
                layoutOptionsAvailable = true;
            } else if (first == null && second == null) {
                layoutOptionControlFactory.createControl(pair.getFirst().getId());
                layoutOptionsAvailable = true;
            } else {
                layoutOptionControlFactory.createControl(pair.getFirst().getId(), second);
                layoutOptionsAvailable = true;
            }
        }
        
        boolean synthesisOptionsAvailable = false;
        final ViewContext vc = this.getViewContext();

        for (final SynthesisOption option : vc.getDisplayedSynthesisOptions()) {
            if (option.isCheckOption()) {
                synthesisOptionControlFactory.createCheckOptionControl(option, vc, viewId);
                synthesisOptionsAvailable = true;
            } else if (option.isChoiceOption()) {
                synthesisOptionControlFactory.createChoiceOptionControl(option, vc, viewId);
                synthesisOptionsAvailable = true;
            } else if (option.isRangeOption()) {
                synthesisOptionControlFactory.createRangeOptionControl(option, vc, viewId);
                synthesisOptionsAvailable = true;
            } else if (option.isSeparator()) {
                synthesisOptionControlFactory.createSeparator(option.getName());
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
    
    private final List<Control> sideBarControls = newArrayListWithCapacity(5);
    
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
    public void setModel(final Object model) {
        setModel(model, false);
    }
    
    /**
     * {@inheritDoc}
     */
    public synchronized void setModel(final Object model, final boolean sync) {
        // if the model is a view context adapt the viewer to the given context if possible
        if (model instanceof ViewContext) {
            final ViewContext viewContext = (ViewContext) model;
            // remove the old viewer
            removeViewer();

            // create the new viewer
            IViewer<?> viewer = viewContext.createViewer(this, diagramComposite);

            // add the new viewer
            addViewer(viewer);

            // set the new view context
            currentViewContext = viewContext;
            
            // initialize the current selection
            notifySelectionListeners(new KlighdTreeSelection(currentViewContext));

        } else if (model instanceof String) {
            // if the model is a string show it
            showMessage((String) model);
            
            // provide no selection in this case!
        }
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
     * Returns the {@link IDiagramWorkbenchPart} this viewer is attached to.
     * 
     * @return the workbench part
     */
    public IDiagramWorkbenchPart getWorkbenchPart() {
        return workbenchPart;
    }


    /**
     * {@inheritDoc}
     */
    public IDiagramOutlinePage getDiagramOutlinePage() {
        if (currentViewer instanceof IDiagramOutlinePage.Provider) {
            return ((IDiagramOutlinePage.Provider) currentViewer).getDiagramOutlinePage();
        } else {
            return null;
        }
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
     * {@inheritDoc}
     */
    public ViewContext getViewContext() {
        return currentViewContext;
    }

    /**
     * Returns the currently active view context.
     * 
     * @deprecated use {@link IViewer#getViewContext()}, which is implemented by this class
     * 
     * @return the view context
     */
    public ViewContext getCurrentViewContext() {
        return currentViewContext;
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
     * Returns the {@link VolatileLayoutConfig} that contains the configuration values set via the
     * layout options controls in the side bar.
     * 
     * @return the lightLayoutConfig
     */
    public VolatileLayoutConfig getLightLayoutConfig() {
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
        
        if (currentViewer instanceof ILayoutRecorder) {
            layoutRecorder = (ILayoutRecorder) currentViewer;
        }
    }

    private synchronized void removeViewer() {
        if (currentViewer != null) {
            currentViewer.getControl().dispose();
            currentViewer = null;
            currentViewContext = null;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void startRecording() {
        if (layoutRecorder != null) {
            layoutRecorder.startRecording();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void stopRecording(final int animationTime) {
        if (layoutRecorder != null) {
            layoutRecorder.stopRecording(animationTime);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void stopRecording(final ZoomStyle zoomStyle,
            final int animationTime) {
        if (layoutRecorder != null) {
            layoutRecorder.stopRecording(zoomStyle, animationTime);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void zoomToLevel(final float zoomLevel, final int duration) {
        if (currentViewer != null) {
            currentViewer.zoomToLevel(zoomLevel, duration);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void zoom(final ZoomStyle style, final int duration) {
        if (currentViewer != null) {
            currentViewer.zoom(style, duration);
        }
    }


    /* ----------------------------- */
    /*   the view manipulation API   */
    /* ----------------------------- */

    /**
     * {@inheritDoc}
     */
    public boolean isExpanded(final Object semanticElement) {
        final EObject diagramNode =
                getViewContext().getTargetElement(semanticElement, KNode.class);
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
    public void collapse(final Object semanticElement) {
        final EObject diagramNode =
                getViewContext().getTargetElement(semanticElement, KNode.class);
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
        final EObject diagramNode =
                getViewContext().getTargetElement(semanticElement, KNode.class);
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
        final EObject diagramNode =
                getViewContext().getTargetElement(semanticElement, KNode.class);
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
    public void hide(final Object semanticElement) {
        final EObject diagramElement =
                getViewContext().getTargetElement(semanticElement, KGraphElement.class);
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
        final EObject diagramElement =
                getViewContext().getTargetElement(semanticElement, KGraphElement.class);
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
    public void clip(final Object semanticElement) {
        if (semanticElement == null) {
            this.currentViewer.clip(this.getViewContext().getViewModel());
            return;
        }
        
        final EObject diagramElement =
                getViewContext().getTargetElement(semanticElement, KGraphElement.class);
        if (diagramElement instanceof KNode) {
            this.currentViewer.clip(diagramElement);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void clip(final KNode diagramElement) {
        if (diagramElement == null) {
            this.currentViewer.clip(this.getViewContext().getViewModel());
        } else {
            this.currentViewer.clip(diagramElement);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public KNode getClip() {
        return this.currentViewer.getClip();
    }


    /**
     * {@inheritDoc}
     */
    public void reveal(final Object semanticElement, final int duration) {
        final EObject diagramElement =
                getViewContext().getTargetElement(semanticElement, KGraphElement.class);
        if (diagramElement instanceof KGraphElement) {
            currentViewer.centerOn(diagramElement, duration);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void reveal(final KGraphElement diagramElement, final int duration) {
        if (currentViewer != null) {
            currentViewer.reveal(diagramElement, duration);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void centerOn(final Object semanticElement, final int duration) {
        final EObject diagramElement =
                getViewContext().getTargetElement(semanticElement, KGraphElement.class);
        if (diagramElement instanceof KGraphElement) {
            currentViewer.centerOn(diagramElement, duration);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void centerOn(final KGraphElement diagramElement, final int duration) {
        if (currentViewer != null) {
            currentViewer.centerOn(diagramElement, duration);
        }
    }


    /* ----------------------------- */
    /*   the selection setting API   */
    /* ----------------------------- */

    private final Function<Object, EObject> getViews = new Function<Object, EObject>() {
        public EObject apply(final Object semanticElement) {
            final EObject diagramElement = getViewContext().getTargetElement(semanticElement, null);
            return isSelectable().apply(diagramElement) ? diagramElement : null;
        }
    };
    

    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final Object semanticElement) {
        final EObject diagramElement = getViews.apply(semanticElement);
        if (diagramElement != null) {
            toggleSelectionOfDiagramElements(singleton(diagramElement));
        }
    };
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final KGraphElement diagramElement) {
        toggleSelectionOfDiagramElements(singleton(diagramElement));
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final KText diagramElement) {
        toggleSelectionOfDiagramElements(singleton(diagramElement));
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOfSemanticElements(final Set<Object> semanticElements) {
        toggleSelectionOfDiagramElements(
                Sets.newHashSet(transform(semanticElements, getViews)));
   
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOfDiagramElements(final Set<? extends EObject> toBeToggled) {
        final List<EObject> theSelection = newArrayList(this.selection.eIterator());
        for (EObject diagramElement : Sets.filter(toBeToggled, isSelectable())) {
            boolean removed = theSelection.remove(diagramElement);
            if (!removed) {
                theSelection.add(diagramElement);
            }
        }
        updateSelection(theSelection);
        
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final Object semanticElement) {
        final EObject diagramElement = getViews.apply(semanticElement);
        // Collections.singleton accepts 'null' values and 'updateSelection' does so, too!
        updateSelection(singleton(diagramElement));
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final KGraphElement diagramElement) {
        updateSelection(singleton(diagramElement));
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final KText diagramElement) {
        updateSelection(singleton(diagramElement));
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionToSemanticElements(final Iterable<? extends Object> semanticElements) {
        updateSelection(transform(semanticElements, getViews));
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionToDiagramElements(final Iterable<? extends EObject> diagramElements) {
        updateSelection(filter(diagramElements, isSelectable()));
    }
    

    /**
     * Function for determining the (root) {@link KRendering KRenderings} corresponding to a
     * selected diagram element. Returns the input value wrapped in a {@link Collection} if that is
     * a {@link KRendering}, and all contained {@link KRendering KRenderings} if the input is a
     * {@link KGraphElement}. The function is used for determining the elements whose value of
     * {@link KlighdInternalProperties#SELECTED} is to be updated.
     */
    private static final Function<EObject, Iterable<KRendering>> AS_RENDERING
        = new Function<EObject, Iterable<KRendering>>() {
        
        public Iterable<KRendering> apply(final EObject eo) {
            if (KRenderingPackage.eINSTANCE.getKRendering().isInstance(eo)) {
                return singleton((KRendering) eo);
            } else {
                return newArrayList(filter(eo.eContents(), KRendering.class));
            }
        }
    };
    
    private void updateSelection(final Iterable<? extends EObject> diagramElements) {
        // here the selected elements are assumed to be diagram elements, i.e. KGraph elements or KTexts
        
        final List<EObject> currentlySelected = newArrayList(this.selection.eIterator());
        final List<EObject> toBeSelected = newArrayList(filter(diagramElements, Predicates.notNull())); 
        
        for (KRendering r : concat(transform(filter(currentlySelected, notIn(toBeSelected)),
                AS_RENDERING))) {
            r.setProperty(KlighdInternalProperties.SELECTED, false);
        }
        
        for (KRendering r : concat(transform(toBeSelected, AS_RENDERING))) {
            r.setProperty(KlighdInternalProperties.SELECTED, true);
        }
        
        // update the selection status for the ISelectionProvider interface
        notifySelectionListeners(new KlighdTreeSelection(currentViewContext, toBeSelected));
    }


    /* ------------------------------- */
    /*   The ISelectionProvider part   */
    /* ------------------------------- */

    /** the current selection. */
    private KlighdTreeSelection selection = null;

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
    private void notifySelectionListeners(final KlighdTreeSelection theSelection) {
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
    public KlighdTreeSelection getSelection() {
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
}
