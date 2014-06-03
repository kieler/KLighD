/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.options;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
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

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.KlighdPreferences;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.SynthesisOption;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.ui.parts.DiagramViewPart;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;

/**
 * Builds up the diagram side bar containing the controls for configuring diagram synthesis and
 * layout options.
 * 
 * @author chsch
 */
public final class DiagramSideBar {
    
    /** The priority of the {@link ILayoutConfig} employed in side bars. */
    public static final int LAYOUT_CONFIG_PRIORITY = KlighdConstants.SIDE_BAR_LAYOUT_CONFIG_PRIORITY;
    
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

    /** Right and top offset for the toolbar buttons shown in the canvas. */
    private static final int CANVAS_TOOLBAR_OFFSET = 3;

    /**
     * Disables the toolbar-buttons completely if set to false.
     * Preparation for later use with preferences page.
     */
    private static final boolean TOOLBAR_ACTIVE = true;
    
    private boolean initiallyExpanded = KlighdPreferences.isExpandSideBar();
    
    private boolean expanded = initiallyExpanded;
    
    /** The layout configurator that stores the values set by the layout option controls. */
    private final VolatileLayoutConfig layoutConfig = new VolatileLayoutConfig(LAYOUT_CONFIG_PRIORITY);
    
    private final Composite sideBarParent;

    /** the factory for diagram synthesis option controls. */
    private SynthesisOptionControlFactory synthesisOptionControlFactory;

    /** the factory for layout option controls. */
    private LayoutOptionControlFactory layoutOptionControlFactory;

    /** the form toolkit, is only kept to properly dispose it finally. */
    private FormToolkit optionsformToolkit;

    /** The form that holds the zoom buttons in the sidebar. */
    private Form sideToolbarForm;
    
    /** The composite that holds the zoom buttons in the canvas. */
    private Composite canvasToolbarContainer;

    /** The form that holds synthesis options. */
    private Form synthesisOptionsForm;

    /** The form that holds layout options. */
    private Form layoutOptionsForm;

    /** the set of resources to be disposed when the view is closed. */
    private final List<Resource> resources = new LinkedList<Resource>();

    private final List<Control> sideBarControls = Lists.newArrayListWithCapacity(5);
    
    private FormData sashLayoutData = null;

    /**
     * The horizontal position of the side bar's sash in terms of the (negative) offset wrt. the
     * workbenchPart's right side. Its kept as an attribute in order to avoid the re-initialization
     * of the side bar's size in case a different model has been assigned to the current view.
     */
    private int horizontalPos = initiallyExpanded ? -INITIAL_OPTIONS_FORM_WIDTH : -SASH_WIDTH;
    
    /**
     * Hidden constructor.
     * 
     * @param parent
     *            the parent {@link Composite} to attach the side bar to
     */
    private DiagramSideBar(final Composite parent) {
        this.sideBarParent = parent;
    }
    
    /**
     * Provides the {@link ILayoutConfig} with layout settings from <code>this</code> side bar.
     * 
     * @return the employed {@link ILayoutConfig}
     */
    public ILayoutConfig getLayoutConfig() {
        return this.layoutConfig;
    }

    /**
     * Creates and attaches a side bar to the given <code>parent</code> {@link Composite}.<br>
     * The given <code>diagramContainer</code> is thereby integrated in the tree of widgets.
     * 
     * @param parent
     *            the parent {@link Composite} to attach the side bar to
     * @param diagramContainer
     *            the {@link Composite} accommodating the diagram (later on)
     * @param viewContext
     *            the {@link ViewContext} to get the configured option entries from
     * @return an instance of {@link DiagramSideBar} allowing to access the employed
     *         {@link ILayoutConfig}
     */
    public static DiagramSideBar createSideBar(final Composite parent,
            final Composite diagramContainer, final ViewContext viewContext) {
        return new DiagramSideBar(parent).initialize(diagramContainer, viewContext);
    }
    
    /**
     * @see #createSideBar(Composite, Composite, ViewContext)
     */
    private DiagramSideBar initialize(final Composite diagramContainer,
            final ViewContext viewContext) {
        // SUPPRESS CHECKSTYLE PREVIOUS 2 Length -- UI stuff is lengthy :-(
        
        // in addition to the side bar initialization preference setting
        //  a diagram specific configuration shall be possible; thus...
        switch (viewContext.getProperty(KlighdSynthesisProperties.REQUESTED_SIDE_BAR_HANDLING)) {
        case UNDEFINED:
            break;
        case EXPAND: 
            initiallyExpanded = true;
            horizontalPos = -INITIAL_OPTIONS_FORM_WIDTH;
            break;
        case COLLAPSE:
            initiallyExpanded = false;
            horizontalPos = -SASH_WIDTH;
        }
        expanded = initiallyExpanded;
        
        sideBarParent.setLayout(new FormLayout());
        
        // for easier managing the visibility of the side bar's collapse/expand arrows
        //  let's put them in a dedicated container, this is advantageous below
        final Composite arrowsContainer = new Composite(sideBarParent, SWT.NONE);
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

        // create the left arrow for expanding the options pane
        final Label leftArrowLabel = new Label(arrowsContainer, SWT.NONE);
        final Image leftArrow = KlighdPlugin.getImageDescriptor("icons/arrow-left.gif").createImage();
        resources.add(leftArrow);
        leftArrowLabel.setImage(leftArrow);
        leftArrowLabel.setVisible(true);

        // depending on the preference of initially expanding or collapsing the side bar
        //  let the corresponding symbol be visible
        arrowLabelContainerLayout.topControl = initiallyExpanded ? rightArrowLabel : leftArrowLabel;

        // create the sash for resizing the options pane
        final Sash sash = new Sash(sideBarParent, SWT.VERTICAL);
        sideBarControls.add(sash);
        sash.addPaintListener(new LinePainter());
        sash.setVisible(false);

        optionsformToolkit = new FormToolkit(sideBarParent.getDisplay());

        if (TOOLBAR_ACTIVE) {
            // create toolbar buttons in the sidebar
            sideToolbarForm = optionsformToolkit.createForm(sideBarParent);
            sideToolbarForm.setText(null);
            sideBarControls.add(sideToolbarForm);
            final Composite toolbarContainer = sideToolbarForm.getBody();

            initializeToolbar(toolbarContainer, viewContext);
        }

        final ScrolledForm formRootScroller = optionsformToolkit.createScrolledForm(sideBarParent);
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
        layoutOptionControlFactory = new LayoutOptionControlFactory(
                layoutOptionsContainer, optionsformToolkit, layoutConfig);
        
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

        // initially put the sash outside its parent composite's visible area
        //  right next to that widget's right border (i.e. at 100% of its width)
        // if the side bar is to be shown later on, a negative absolute offset
        //  will be configured on the 'left' form attachment 
        sashLayoutData = new FormData();
        sashLayoutData.top = new FormAttachment(arrowsContainer);
        sashLayoutData.bottom = new FormAttachment(FULL);
        sashLayoutData.left = new FormAttachment(FULL);
        sashLayoutData.width = SASH_WIDTH;
        sash.setLayoutData(sashLayoutData);
        
        if (TOOLBAR_ACTIVE){
            final FormData toolbarFormLayoutData = new FormData();
            toolbarFormLayoutData.top = new FormAttachment(0);
            toolbarFormLayoutData.left = new FormAttachment(sash);
            toolbarFormLayoutData.right = new FormAttachment(FULL);
            sideToolbarForm.setLayoutData(toolbarFormLayoutData);
        }

        final FormData formRootLayoutData = new FormData();        
        formRootLayoutData.top = TOOLBAR_ACTIVE ? new FormAttachment(sideToolbarForm) : new FormAttachment(0);
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
        sash.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                expanded = true;
                updateToolbar(viewContext);
                final int maxDiagSize = sideBarParent.getClientArea().width - MINIMAL_OPTIONS_FORM_WIDTH;
                if (maxDiagSize > event.x) {
                    sashLayoutData.left.numerator = FULL;
                    sashLayoutData.left.offset = -(sideBarParent.getClientArea().width - event.x);
                } else {
                    sashLayoutData.left.numerator = FULL;
                    sashLayoutData.left.offset = -MINIMAL_OPTIONS_FORM_WIDTH;
                    // The following line appears to be evil, but this is required
                    //  to let the sash respect the limit correctly.
                    event.x = maxDiagSize;
                }
                horizontalPos = sashLayoutData.left.offset;
                arrowLabelContainerLayout.topControl = rightArrowLabel;
                sideBarParent.layout(true, true);
            }
        });
        
        // a "pseudo" field ;-)
        // the initialization with #INITIAL_OPTIONS_FORM_WIDTH is required for proper expansion
        //  in case the side bar is initially to be collapsed
        final int[] lastXpos = new int[] { INITIAL_OPTIONS_FORM_WIDTH };
        
        // register actions for the collapse / expand labels
        rightArrowLabel.addMouseListener(new MouseAdapter() {
            public void mouseUp(final MouseEvent event) {
                expanded = false;
                updateToolbar(viewContext);
                sashLayoutData.left.numerator = FULL;
                sashLayoutData.left.offset = -sashLayoutData.width;
                lastXpos[0] = sideBarParent.getClientArea().width - sash.getBounds().x;
                horizontalPos = sashLayoutData.left.offset;
                arrowLabelContainerLayout.topControl = leftArrowLabel;
                sideBarParent.layout(true, true);
            }
        });
        leftArrowLabel.addMouseListener(new MouseAdapter() {
            public void mouseUp(final MouseEvent event) {
                expanded = true;
                updateToolbar(viewContext);
                sashLayoutData.left.numerator = FULL;
                sashLayoutData.left.offset = -lastXpos[0];
                horizontalPos = sashLayoutData.left.offset;
                arrowLabelContainerLayout.topControl = rightArrowLabel;
                sideBarParent.layout(true, true);
            }
        });        
        return this;
    }
    
    
    /**
     * Initialize the toolbar, that is create the three buttons and register selectionlisteners.
     * 
     * @param parent
     *            the {@link Composite} to which the buttons are being added
     * @param viewContext
     *            the current {@link ViewContext}
     */
    private void initializeToolbar(final Composite parent, final ViewContext viewContext) {
        if (!TOOLBAR_ACTIVE) {
            return;
        }
        parent.setLayout(new RowLayout());
        final Button zoomToFitBtn = new Button(parent, SWT.TOGGLE);
        final Button zoomToFocusBtn = new Button(parent, SWT.TOGGLE);
        final Button zoomToOneBtn = new Button(parent, SWT.PUSH);

        // Zoom to Fit
        Image zoomToFitImage =
                KlighdPlugin.getImageDescriptor("icons/kieler-zoomtofit.gif").createImage();
        resources.add(zoomToFitImage);
        zoomToFitBtn.setImage(zoomToFitImage);
        zoomToFitBtn.setToolTipText("Toggle Zoom to Fit");
        // set extra data to identify the buttons while updating later
        zoomToFitBtn.setData("type", "zoomToFit");
        zoomToFitBtn.setSelection(viewContext.isZoomToFit());
        zoomToFitBtn.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                viewContext.setZoomStyle(ZoomStyle.create(false, zoomToFitBtn.getSelection(), false));
                // perform zoom to fit upon activation of the toggle button
                if (zoomToFitBtn.getSelection()) {
                    LightDiagramServices.layoutAndZoomDiagram(viewContext.getDiagramWorkbenchPart());
                    // uncheck the zoom to focus button
                    zoomToFocusBtn.setSelection(false);
                }
            }
        });

        // Zoom to focus
        Image zoomToFocusImage =
                KlighdPlugin.getImageDescriptor("icons/kieler-zoomtofocus.gif").createImage();
        resources.add(zoomToFocusImage);
        zoomToFocusBtn.setImage(zoomToFocusImage);
        zoomToFocusBtn.setToolTipText("Toggle Zoom to Focus");
        // set extra data to identify the buttons while updating later
        zoomToFocusBtn.setData("type", "zoomToFocus");
        zoomToFocusBtn.setSelection(viewContext.isZoomToFocus());
        zoomToFocusBtn.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                viewContext.setZoomStyle(ZoomStyle.create(false, false,
                        zoomToFocusBtn.getSelection()));
                // perform zoom to focus upon activation of the toggle button
                if (zoomToFocusBtn.getSelection()) {
                    LightDiagramServices.layoutAndZoomDiagram(viewContext.getDiagramWorkbenchPart());
                    // uncheck the zoom to fit button
                    zoomToFitBtn.setSelection(false);
                }
            }
        });

        // Zoom to one
        Image zoomToOneImage =
                KlighdPlugin.getImageDescriptor("icons/kieler-zoomtoone.gif").createImage();
        resources.add(zoomToOneImage);
        zoomToOneBtn.setImage(zoomToOneImage);
        zoomToOneBtn.setToolTipText("Zoom to Original Size");
        zoomToOneBtn.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                viewContext.getViewer().zoomToLevel(1, KlighdConstants.DEFAULT_ANIMATION_TIME);
            }
        });

        parent.pack();
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
     * Update the options to be displayed in the options pane.
     * 
     * @param diagramComposite
     *            the {@link Composite} the diagram canvas is contained in
     * @param viewContext
     *            the current {@link ViewContext}
     * @param fitSpace
     *            true if the diagram shall fit the available space
     */
    public void updateOptions(final Composite diagramComposite, final ViewContext viewContext,
            final boolean fitSpace) {

        if (diagramComposite.isDisposed()) {
            return;
        }
        
        updateToolbar(viewContext);
        
        // remove any option controls that have been created before
        synthesisOptionControlFactory.clear();
        
        // remove any option controls that have been created before
        layoutOptionControlFactory.clear();
        // initialize a layout configuration for retrieving default values
        layoutOptionControlFactory.initialize(viewContext);

        List<Pair<IProperty<?>, List<?>>> recommendedOptions =
                viewContext.getDisplayedLayoutOptions();
        
        boolean layoutOptionsAvailable = false;
        for (Pair<IProperty<?>, List<?>> pair : recommendedOptions) {
            final Object first;
            final Object second;
            if (pair.getSecond() instanceof Collection) {
                final Iterator<?> it = ((Collection<?>) pair.getSecond()).iterator();
                first = it.hasNext() ? it.next() : null;
                second = it.hasNext() ? it.next() : null;
            } else {
                first = null;
                second = null;
            }
            
            if (first instanceof Number && second instanceof Number) {
                layoutOptionControlFactory.createControl(pair.getFirst().getId(),
                        ((Number) first).floatValue(), ((Number) second).floatValue());
                layoutOptionsAvailable = true;
            } else if (pair.getSecond() == null) {
                layoutOptionControlFactory.createControl(pair.getFirst().getId());
                layoutOptionsAvailable = true;
            } else {
                layoutOptionControlFactory.createControl(pair.getFirst().getId(),
                        pair.getSecond());
                layoutOptionsAvailable = true;
            }
        }
        
        boolean synthesisOptionsAvailable = false;

        for (final SynthesisOption option : viewContext.getDisplayedSynthesisOptions()) {
            if (option.isCheckOption()) {
                synthesisOptionControlFactory.createCheckOptionControl(option, viewContext);
                synthesisOptionsAvailable = true;
            } else if (option.isChoiceOption()) {
                synthesisOptionControlFactory.createChoiceOptionControl(option, viewContext);
                synthesisOptionsAvailable = true;
            } else if (option.isRangeOption()) {
                synthesisOptionControlFactory.createRangeOptionControl(option, viewContext);
                synthesisOptionsAvailable = true;
            } else if (option.isSeparator()) {
                synthesisOptionControlFactory.createSeparator(option.getName());
            }
        }
        
        this.enableOptionsSideBar(viewContext, fitSpace, synthesisOptionsAvailable,
                layoutOptionsAvailable);
        final IDiagramWorkbenchPart part = viewContext.getDiagramWorkbenchPart();
        if (part instanceof DiagramViewPart) {
            ((DiagramViewPart) part).getAction(DiagramViewPart.ACTION_ID_RESET_LAYOUT_OPTIONS)
                    .setEnabled(layoutOptionsAvailable);
        }
    }

    /**
     * Update the toolbar buttons. In case the canvas-toolbar is not yet initialized, initialze it
     * (see {@link DiagramSideBar#initializeToolbar(Composite, ViewContext)}).
     * 
     * @param viewContext
     *            the current {@link ViewContext}
     */
    private void updateToolbar(final ViewContext viewContext) {
        if (!TOOLBAR_ACTIVE) {
            return;
        }
        // Set the visibility only if the canvasToolbar is already initialized.
        if (canvasToolbarContainer != null) {
            canvasToolbarContainer.setVisible(!expanded);
        }

        if (expanded) {
            // Sidebar is expanded: simply update the selections status of the buttons.
            updateToolbar(sideToolbarForm.getBody(), viewContext);
        } else {
            // Sidebar is hidden: if required, initialize the canvas toolbar.
            // Finally update the selection status.
            if (viewContext.getViewer().getControl() instanceof KlighdCanvas
                    && canvasToolbarContainer == null) {
                final KlighdCanvas canvas = (KlighdCanvas) viewContext.getViewer().getControl();

                canvasToolbarContainer = new Composite(canvas, SWT.NONE);

                initializeToolbar(canvasToolbarContainer, viewContext);
                canvasToolbarContainer.pack();

                new Listener() {
                    // Constructor
                    {
                        canvas.addListener(SWT.Resize, this);
                    }

                    public void handleEvent(final Event event) {
                        final Point canvasSize = canvas.getSize();
                        final Point buttonBarSize = canvasToolbarContainer.getSize();

                        // Take care of the positioning of the canvasToolbar because the KlightCanvas
                        // has no Layout set.
                        canvasToolbarContainer.setLocation(
                                canvasSize.x - buttonBarSize.x - CANVAS_TOOLBAR_OFFSET,
                                CANVAS_TOOLBAR_OFFSET);
                    }
                };
            }

            updateToolbar(canvasToolbarContainer, viewContext);
        }
    }
    
    /**
     * Update the selection-status of the toolbar buttons.
     * 
     * @param container
     *            the {@link Composite} the buttons are contained in
     * @param viewContext
     *            the current {@link ViewContext}
     */
    private void updateToolbar(final Composite container, final ViewContext viewContext) {
        for (Control button : container.getChildren()) {
            Object type = button.getData("type");
            if ("zoomToFit".equals(type)) {
                ((Button) button).setSelection(viewContext.isZoomToFit());
            } else if ("zoomToFocus".equals(type)) {
                ((Button) button).setSelection(viewContext.isZoomToFocus());
            }
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
    private void enableOptionsSideBar(final ViewContext viewContext,
            final boolean zoomToFit, final boolean showSynthesisOptions,
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
                // put the layout options form at the top
                ((FormData) layoutOptionsForm.getLayoutData()).top = new FormAttachment(0);
            } else {
                // restore the initial configuration in case such options are available again
                ((FormData) layoutOptionsForm.getLayoutData()).top =
                        new FormAttachment(synthesisOptionsForm, SYNTHESIS_LAYOUT_OPTIONS_SPACE);
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
        if (!this.sideBarParent.isDisposed()) {
            this.sideBarParent.layout(true, true);
        }

        // let the diagram fit the available space,
        if (zoomToFit) {
            viewContext.getViewer().zoom(ZoomStyle.ZOOM_TO_FIT, 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void resetLayoutOptionsToDefaults() {
        this.layoutOptionControlFactory.resetToDefaults();
    }
    
    /**
     * Disposes the {@link Resource Resources}.
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
    

}
