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
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Resource;
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

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.SynthesisOption;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.ui.parts.DiagramViewPart;

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
    
    /** The layout configurator that stores the values set by the layout option controls. */
    private final VolatileLayoutConfig layoutConfig = new VolatileLayoutConfig(LAYOUT_CONFIG_PRIORITY);
    
    private final Composite sideBarParent;

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

    private final List<Control> sideBarControls = Lists.newArrayListWithCapacity(5);
    
    private FormData sashLayoutData = null;

    /**
     * The horizontal position of the side bar's sash in terms of the (negative) offset wrt. the
     * workbenchPart's right side. Its kept as an attribute in order to avoid the re-initialization
     * of the side bar's size in case a different model has been assigned to the current view.
     */
    private int horizontalPos = -INITIAL_OPTIONS_FORM_WIDTH;
    
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
        arrowLabelContainerLayout.topControl = rightArrowLabel;

        // create the left arrow for expanding the options pane
        final Label leftArrowLabel = new Label(arrowsContainer, SWT.NONE);
        final Image leftArrow = KlighdPlugin.getImageDescriptor("icons/arrow-left.gif").createImage();
        resources.add(leftArrow);
        leftArrowLabel.setImage(leftArrow);
        leftArrowLabel.setVisible(true);
        
        // create the sash for resizing the options pane
        final Sash sash = new Sash(sideBarParent, SWT.VERTICAL);
        sideBarControls.add(sash);
        sash.addPaintListener(new LinePainter());
        sash.setVisible(false);
        
        optionsformToolkit = new FormToolkit(sideBarParent.getDisplay());

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
                sideBarParent.layout(true);
            }
        });
        
        // a "pseudo" field ;-)
        final int[] lastXpos = new int[] { 0 };
        
        // register actions for the collapse / expand labels
        rightArrowLabel.addMouseListener(new MouseAdapter() {
            public void mouseUp(final MouseEvent event) {
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
