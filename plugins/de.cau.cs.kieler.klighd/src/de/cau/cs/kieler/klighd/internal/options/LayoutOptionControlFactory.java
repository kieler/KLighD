/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.internal.options;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.ILayoutData;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.AlgorithmSelectionDialog;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.ui.service.LayoutOptionManager;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.macrolayout.KGraphPropertyLayoutConfig;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;
import de.cau.cs.kieler.klighd.views.IDiagramWorkbenchPart;

/**
 * A factory for controls for layout options.
 *
 * @author msp
 */
public class LayoutOptionControlFactory {
    
    /** The parent composite into which controls are created. */
    private Composite parent;
    /** The workbench part containing the diagram viewer. */
    private IDiagramWorkbenchPart workbenchPart;
    /** the form toolkit used to create controls. */
    private FormToolkit formToolkit;
    /** The layout configurator for retrieving initial values of controls. */
    private ILayoutConfig defaultLayoutConfig;
    /** The layout context for retrieving initial values of controls. */
    private LayoutContext defaultLayoutContext;
    /** The layout configurator that stores the values set by option controls. */
    private LightLayoutConfig lightLayoutConfig;
    /** The set of controls to be disposed when {@link #clear()} is called. */
    private final Collection<Control> controls = new LinkedList<Control>();
    /** Whether the layout shall be refreshed automatically. */
    private boolean autoRefreshLayout = true;
    
    /** number of columns in the grid for enumeration value selection. */
    private static final int ENUM_GRID_COLS = 2;
    /** widget data identifier for the attached selection listener. */
    private static final String DATA_SELECTION_LISTENER = "klighd.selectionListener";
    
    /**
     * Create an option control factory.
     * 
     * @param parent
     *            the parent container
     * @param workbenchPart
     *            the workbench part containing the diagram viewer
     * @param formToolkit
     *            the form toolkit used to create controls
     * @param theLightLayoutConfig
     *            the layout configuration to the user-chosen values in
     */
    public LayoutOptionControlFactory(final Composite parent,
            final IDiagramWorkbenchPart workbenchPart, final FormToolkit formToolkit,
            final LightLayoutConfig theLightLayoutConfig) {
        this.parent = parent;
        this.workbenchPart = workbenchPart;
        this.formToolkit = formToolkit;

        this.lightLayoutConfig = theLightLayoutConfig;
        
        // configure the parent's layout
        this.parent.setLayout(new GridLayout(1, false)); // chsch: changed this from 2 to 1

        // set a dummy configurator with default values
        defaultLayoutConfig = new DefaultLayoutConfig();
        defaultLayoutContext = new LayoutContext();
    }
    
    /**
     * Clear the current layout configuration and reinitialize option values. These values
     * are used when new controls are created. If any controls have been created before,
     * they should be removed first using {@link #clear()}.
     */
    public void initialize() {
        lightLayoutConfig.clear();
        EObject inputModel = null;
        Object viewModel = null;
        if (workbenchPart instanceof DiagramViewPart) {
            ViewContext viewContext = ((DiagramViewPart) workbenchPart).getContextViewer()
                    .getCurrentViewContext();
            if (viewContext != null) {
                if (viewContext.getInputModel() instanceof EObject) {
                    inputModel = (EObject) viewContext.getInputModel();
                } else if (viewContext.getInputModel() instanceof Collection<?>) {
                    Collection<?> collection = (Collection<?>) viewContext.getInputModel();
                    Iterator<?> iterator = collection.iterator();
                    if (iterator.hasNext()) {
                        Object next = iterator.next();
                        if (next instanceof EObject) {
                            inputModel = (EObject) next;
                        }
                    }
                }
                viewModel = viewContext.getViewModel();
            }
            IViewer<?> viewer = ((DiagramViewPart) workbenchPart).getContextViewer().getActiveViewer();
            if (viewer != null) {
                viewModel = viewer.getModel();
            }
        }
        // create the layout configurator
        LayoutOptionManager optionManager = DiagramLayoutEngine.INSTANCE.getOptionManager();
        defaultLayoutConfig = optionManager.createConfig(inputModel, new KGraphPropertyLayoutConfig());
        // create and enrich the layout context
        defaultLayoutContext = new LayoutContext();
        defaultLayoutContext.setProperty(EclipseLayoutConfig.WORKBENCH_PART, workbenchPart);
        defaultLayoutContext.setProperty(LayoutContext.DOMAIN_MODEL, inputModel);
        defaultLayoutContext.setProperty(LayoutContext.DIAGRAM_PART, viewModel);
        defaultLayoutContext.setProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS, true);
        defaultLayoutContext.setProperty(LayoutContext.OPT_TARGETS,
                EnumSet.of(LayoutOptionData.Target.PARENTS));
        defaultLayoutConfig.enrich(defaultLayoutContext);
    }
    
    /**
     * Clear the previously created option controls.
     */
    public void clear() {
        for (Control c : controls) {
            c.dispose();
        }
        controls.clear();
    }
    
    /**
     * Refresh the layout of the displayed diagram.
     * 
     * @param animate whether the new layout shall be animated
     */
    private void refreshLayout(final boolean animate) {
        if (autoRefreshLayout) {
            LightDiagramServices.layoutDiagram(workbenchPart, animate);
        }
    }
    
    /**
     * Create a control for the given layout option identifier.
     * 
     * @param optionId a layout option identifier
     */
    public void createControl(final String optionId) {
        LayoutOptionData<?> optionData = LayoutDataService.getInstance().getOptionData(optionId);
        if (optionData != null) {
            createControl(optionData, null, null, null);
        }
    }
    
    /**
     * Create a control for the given layout option identifier with given bounds.
     * 
     * @param optionId a layout option identifier
     * @param minValue the minimal value for the option
     * @param maxValue the maximal value for the option
     */
    public void createControl(final String optionId, final Float minValue, final Float maxValue) {
        LayoutOptionData<?> optionData = LayoutDataService.getInstance().getOptionData(optionId);
        if (optionData != null) {
            createControl(optionData, minValue, maxValue, null);
        }
    }
    
    /**
     * Create a control for the given layout option identifier with given available option values.
     * 
     * @param optionId a layout option identifier
     * @param availableValues the set of values to offer
     */
    public void createControl(final String optionId, final Collection<?> availableValues) {
        LayoutOptionData<?> optionData = LayoutDataService.getInstance().getOptionData(optionId);
        if (optionData != null) {
            createControl(optionData, null, null, availableValues);
        }
    }
    
    /**
     * Reset all displayed layout options to their default values.
     */
    public void resetToDefaults() {
        // temporarily disable auto-refresh to avoid multiple layout runs triggered by listeners
        autoRefreshLayout = false;
        lightLayoutConfig.clear();
        for (Control control : controls) {
            if (control.getData() instanceof LayoutOptionData<?>) {
                LayoutOptionData<?> optionData = (LayoutOptionData<?>) control.getData();
                final Object defaultValue = defaultLayoutConfig.getValue(optionData,
                        defaultLayoutContext);
                switch (optionData.getType()) {
                
                case INT:
                case FLOAT: {
                    Scale slider = (Scale) control;
                    SliderListener sliderListener = (SliderListener) control.getData(
                            DATA_SELECTION_LISTENER);
                    if (sliderListener != null) {
                        float initialValue = KielerMath.limit(((Number) defaultValue).floatValue(),
                                sliderListener.minFloat, sliderListener.maxFloat);
                        int selection = Math.round((initialValue - sliderListener.minFloat)
                                / (sliderListener.maxFloat - sliderListener.minFloat)
                                * (slider.getMaximum() - slider.getMinimum())) + slider.getMinimum();
                        slider.setSelection(selection);
                    }
                    break;
                }
                
                case BOOLEAN:
                case ENUM: {
                    // the composite's children store the available values in their 'Data' fields
                    Composite composite = (Composite) control;
                    Control selection = Iterators.find(Iterators.forArray(composite.getChildren()),
                            new Predicate<Control>() {
                        public boolean apply(final Control input) {
                            if (input.getData() != null) {
                                return input.getData().equals(defaultValue);
                            }
                            return false;
                        }
                    });
                    for (Control c : composite.getChildren()) {
                        ((Button) c).setSelection(c == selection);
                    }
                    break;
                }
                
                }
            }
        }
        autoRefreshLayout = true;
        refreshLayout(true);
    }
    
    /**
     * Create a control for the given layout option data instance with given bounds.
     * 
     * @param optionData a layout option data instance
     * @param minValue the minimal value for the option, or {@code null}
     * @param maxValue the maximal value for the option, or {@code null}
     * @param availableValues the set of values to offer, or {@code null}
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void createControl(final LayoutOptionData<?> optionData, final Float minValue,
            final Float maxValue, final Collection<?> availableValues) {
        
        if (optionData.equals(LayoutOptions.ALGORITHM)) {
            Button button = formToolkit.createButton(parent, "Select Layout Algorithm...", SWT.PUSH);
            button.setToolTipText(optionData.getDescription());
            button.addSelectionListener(new AlgorithmListener());
            GridData gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
            // gridData.horizontalSpan = 2;
            button.setLayoutData(gridData);
            button.setData(optionData);
            controls.add(button);
            // set initial value for the algorithm selection dialog
            String algorithmHint = defaultLayoutContext.getProperty(DefaultLayoutConfig.CONTENT_HINT);
            if (algorithmHint != null && algorithmHint.length() > 0) {
                lightLayoutConfig.setOption(optionData, algorithmHint);
            }
            
            // chsch: via this tweak we get more space below the 'Select ...' button as in GridData
            //  one can only specify vertical indentation on the top side of the widget
            Composite dummy = new Composite(parent, SWT.NONE | SWT.NO_BACKGROUND);
            dummy.setLayoutData(new GridData(1, 1));
            controls.add(dummy);
            
        } else {
            Label label = formToolkit.createLabel(parent, optionData.getName() + ":");
            label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
            controls.add(label);
            
            switch (optionData.getType()) {
            case INT:
            case FLOAT: {
                Scale slider = new Scale(parent, SWT.NONE);
                // the following setting is needed on windows
                slider.setBackground(parent.getBackground());
                slider.setToolTipText(optionData.getDescription());
                SliderListener sliderListener = new SliderListener(optionData,
                        getMinValue(optionData, minValue), getMaxValue(optionData, maxValue));
                GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
                slider.setLayoutData(gridData);
                slider.setData(optionData);
                controls.add(slider);
                // set initial value for the slider
                float initialValue = ((Number) defaultLayoutConfig.getValue(optionData,
                        defaultLayoutContext)).floatValue();
                initialValue = KielerMath.limit(initialValue, sliderListener.minFloat,
                        sliderListener.maxFloat);
                sliderListener.setOptionValue(initialValue);
                int selection = Math.round((initialValue - sliderListener.minFloat)
                        / (sliderListener.maxFloat - sliderListener.minFloat)
                        * (slider.getMaximum() - slider.getMinimum())) + slider.getMinimum();
                slider.setSelection(selection);
                // add selection listener for instant layout updates
                slider.addSelectionListener(sliderListener);
                slider.setData(DATA_SELECTION_LISTENER, sliderListener);
                break;
            }
            
            case BOOLEAN: {
                Composite valuesContainer = formToolkit.createComposite(parent);
                valuesContainer.setLayout(new GridLayout(2, false));
                Button trueButton = formToolkit.createButton(valuesContainer, "True", SWT.RADIO);
                trueButton.setToolTipText(optionData.getDescription());
                trueButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
                trueButton.setData(true);
                Button falseButton = formToolkit.createButton(valuesContainer, "False", SWT.RADIO);
                falseButton.setToolTipText(optionData.getDescription());
                falseButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
                falseButton.setData(false);
                valuesContainer.setData(optionData);
                controls.add(valuesContainer);
                // set initial value for the radio buttons
                if ((Boolean) defaultLayoutConfig.getValue(optionData, defaultLayoutContext)) {
                    trueButton.setSelection(true);
                } else {
                    falseButton.setSelection(true);
                }
                // add selection listeners for instant layout updates
                trueButton.addSelectionListener(new EnumerationListener(optionData, Boolean.TRUE));
                falseButton.addSelectionListener(new EnumerationListener(optionData, Boolean.FALSE));
                break;
            }
            
            case ENUM: {
                Composite valuesContainer = formToolkit.createComposite(parent);
                valuesContainer.setLayout(new GridLayout(ENUM_GRID_COLS, false));
                Object[] values;
                if (availableValues != null) {
                    values = availableValues.toArray();
                } else {
                    values = ((Class<Enum>) optionData.getOptionClass()).getEnumConstants();
                }
                Object initialValue = defaultLayoutConfig.getValue(optionData, defaultLayoutContext);
                for (Object value : values) {
                    Button button = formToolkit.createButton(valuesContainer, getUserValue(value),
                            SWT.RADIO);
                    button.setToolTipText(optionData.getDescription());
                    button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
                    button.setData(value);
                    if (value.equals(initialValue)) {
                        button.setSelection(true);
                    }
                    button.addSelectionListener(new EnumerationListener(optionData, value));
                }
                valuesContainer.setData(optionData);
                controls.add(valuesContainer);
                break;
            }
            
            default:
                label = new Label(parent, SWT.NONE);
                label.setText("This option type is not supported");
                label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
                controls.add(label);
            }
        }
    }
    
    /** The default value for the lower bound. */
    private static final int DEFAULT_MIN = 0;
    
    /**
     * Get a lower bound for values of the given option.
     * 
     * @param optionData a layout option data instance
     * @param requested the requested bound, or {@code null}
     * @return a minimal value
     */
    private static float getMinValue(final LayoutOptionData<?> optionData, final Float requested) {
        if (requested != null) {
            return requested;
        }
        if (optionData.getLowerBound() instanceof Float) {
            return (Float) optionData.getLowerBound();
        }
        if (optionData.getLowerBound() instanceof Integer) {
            return (Integer) optionData.getLowerBound();
        }
        return DEFAULT_MIN;
    }
    
    /** The default value for the upper bound. */
    private static final int DEFAULT_MAX = 100;

    /**
     * Get an upper bound for values of the given option.
     * 
     * @param optionData a layout option data instance
     * @param requested the requested bound, or {@code null}
     * @return a maximal value
     */
    private static float getMaxValue(final LayoutOptionData<?> optionData, final Float requested) {
        if (requested != null) {
            return requested;
        }
        if (optionData.getUpperBound() instanceof Float) {
            return (Float) optionData.getUpperBound();
        }
        if (optionData.getUpperBound() instanceof Integer) {
            return (Integer) optionData.getUpperBound();
        }
        return DEFAULT_MAX;
    }
    
    /**
     * Get a user-friendly value for the given object.
     * 
     * @param object an object, e.g. an enumeration value
     * @return a user-friendly string to display in the UI
     */
    private static String getUserValue(final Object object) {
        String string = object.toString();
        StringBuilder builder = new StringBuilder(string.length());
        boolean capital = true;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '_') {
                builder.append(' ');
                capital = true;
            } else if (capital) {
                builder.append(Character.toUpperCase(string.charAt(i)));
                capital = false;
            } else {
                builder.append(Character.toLowerCase(string.charAt(i)));
            }
        }
        return builder.toString();
    }
    
    /**
     * A listener for sliders.
     */
    private class SliderListener extends SelectionAdapter {
        
        /** the layout option that is affected by the slider. */
        private LayoutOptionData<?> optionData;
        /** the maximal value. */
        private float minFloat;
        /** the minimal value. */
        private float maxFloat;
        
        /**
         * Create a slider listener.
         * 
         * @param optionData the layout option that is affected by the slider
         * @param minFloat the maximal value
         * @param maxFloat the minimal value
         */
        SliderListener(final LayoutOptionData<?> optionData, final float minFloat,
                final Float maxFloat) {
            this.optionData = optionData;
            this.minFloat = minFloat;
            if (maxFloat <= minFloat) {
                this.maxFloat = minFloat + 1;
            } else {
                this.maxFloat = maxFloat;
            }
        }

        @Override
        public void widgetSelected(final SelectionEvent event) {
            Scale slider = (Scale) event.widget;
            float sliderValue = (float) (slider.getSelection() - slider.getMinimum())
                    / (slider.getMaximum() - slider.getMinimum());
            float optionValue = minFloat + sliderValue * (maxFloat - minFloat);
            setOptionValue(optionValue);
            
            // trigger a new layout on the displayed diagram
            refreshLayout(false);
        }
        
        /**
         * Set the new value of the layout option.
         * 
         * @param optionValue the layout option value
         */
        @SuppressWarnings("incomplete-switch")
        public void setOptionValue(final float optionValue) {
            switch (optionData.getType()) {
            case INT:
                lightLayoutConfig.setOption(optionData, (int) optionValue);
                break;
            case FLOAT:
                lightLayoutConfig.setOption(optionData, optionValue);
                break;
            }
        }
    }
    
    /**
     * A listener for the layout algorithm selection button.
     */
    private class AlgorithmListener extends SelectionAdapter {

        @Override
        public void widgetSelected(final SelectionEvent event) {
            final LayoutOptionData<?> optionData = LayoutDataService.getInstance().getOptionData(
                    LayoutOptions.ALGORITHM.getId());
            String initialValue = (String) lightLayoutConfig.getOption(optionData);
            AlgorithmSelectionDialog dialog = new AlgorithmSelectionDialog(parent.getShell(),
                    initialValue);
            dialog.addAlgorithmSelectionListener(new ISelectionChangedListener() {
                public void selectionChanged(final SelectionChangedEvent event) {
                    // instantly update the layout when an algorithm is selected
                    IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                    if (!selection.isEmpty() && selection.getFirstElement() instanceof ILayoutData) {
                        ILayoutData layoutData = (ILayoutData) selection.getFirstElement();
                        lightLayoutConfig.setOption(optionData, layoutData.getId());
                        refreshLayout(true);
                    }
                }
            });
            if (dialog.open() == AlgorithmSelectionDialog.OK) {
                lightLayoutConfig.setOption(optionData, dialog.getSelectedHint());
            } else {
                lightLayoutConfig.setOption(optionData, initialValue);
            }
            
            // trigger a new layout on the displayed diagram
            refreshLayout(true);
        }
    }
    
    /**
     * A listener for enumeration values.
     */
    private class EnumerationListener extends SelectionAdapter {
        
        /** the layout option that is affected by the button. */
        private LayoutOptionData<?> optionData;
        /** the enumeration value to set when selection is triggered. */
        private Object value;
        
        /**
         * Creates an enumeration listener.
         * 
         * @param optionData the layout option that is affected by the button
         * @param value the enumeration value to set when selection is triggered
         */
        public EnumerationListener(final LayoutOptionData<?> optionData, final Object value) {
            this.optionData = optionData;
            this.value = value;
        }

        @Override
        public void widgetSelected(final SelectionEvent event) {
            if (((Button) event.widget).getSelection()) {
                lightLayoutConfig.setOption(optionData, value);
                refreshLayout(true);
            }
        }
    }
}
