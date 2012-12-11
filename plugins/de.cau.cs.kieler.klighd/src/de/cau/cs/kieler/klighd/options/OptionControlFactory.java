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
package de.cau.cs.kieler.klighd.options;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.ImmutableList;

import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.AlgorithmSelectionDialog;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;

/**
 * A factory for controls for layout options.
 *
 * @author msp
 */
public class OptionControlFactory {
    
    /** The parent composite into which controls are created. */
    private Composite parent;
    /** The workbench part containing the diagram viewer. */
    private IWorkbenchPart workbenchPart;
    /** The layout configurator that stores the values set by option controls. */
    private final LightLayoutConfig layoutConfig = new LightLayoutConfig();
    /** The set of controls to be disposed when {@link #clear()} is called. */
    private final Collection<Control> controls = new LinkedList<Control>();
    
    /**
     * Create an option control factory.
     * 
     * @param parent the parent container
     * @param workbenchPart the workbench part containing the diagram viewer
     */
    public OptionControlFactory(final Composite parent, final IWorkbenchPart workbenchPart) {
        this.parent = parent;
        this.workbenchPart = workbenchPart;
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
        DiagramLayoutEngine.INSTANCE.layout(workbenchPart, null, animate, false, false, false,
                ImmutableList.<ILayoutConfig>of(layoutConfig));
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
    
    private static final int SLIDER_MIN_WIDTH = 80;
    
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
            Button button = new Button(parent, SWT.PUSH);
            button.setText("Select Layout Algorithm...");
            button.setToolTipText(optionData.getDescription());
            button.addSelectionListener(new AlgorithmListener());
            GridData gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
            gridData.horizontalSpan = 2;
            button.setLayoutData(gridData);
            controls.add(button);
            // TODO retrieve initial values for layout options
        } else {
            Label label = new Label(parent, SWT.NONE);
            label.setText(optionData.getName());
            label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
            controls.add(label);
            
            switch (optionData.getType()) {
            case INT:
            case FLOAT: {
                Slider slider = new Slider(parent, SWT.NONE);
                slider.setToolTipText(optionData.getDescription());
                slider.addSelectionListener(new SliderListener(optionData,
                        getMinValue(optionData, minValue), getMaxValue(optionData, maxValue)));
                GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
                gridData.minimumWidth = SLIDER_MIN_WIDTH;
                slider.setLayoutData(gridData);
                controls.add(slider);
                // TODO retrieve initial values for layout options
                break;
            }
            case BOOLEAN: {
                Composite valuesContainer = new Composite(parent, SWT.NONE);
                Button trueButton = new Button(valuesContainer, SWT.RADIO);
                trueButton.setText("True");
                trueButton.setToolTipText(optionData.getDescription());
                trueButton.addSelectionListener(new EnumerationListener(optionData, Boolean.TRUE));
                trueButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
                Button falseButton = new Button(valuesContainer, SWT.RADIO);
                falseButton.setText("False");
                falseButton.setToolTipText(optionData.getDescription());
                falseButton.addSelectionListener(new EnumerationListener(optionData, Boolean.FALSE));
                falseButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
                controls.add(valuesContainer);
                // TODO retrieve initial values for layout options
                break;
            }
            case ENUM: {
                Composite valuesContainer = new Composite(parent, SWT.NONE);
                Object[] values;
                if (availableValues != null) {
                    values = availableValues.toArray();
                } else {
                    values = ((Class<Enum>) optionData.getOptionClass()).getEnumConstants();
                }
                valuesContainer.setLayout(new GridLayout(values.length, false));
                for (Object value : values) {
                    Button button = new Button(valuesContainer, SWT.RADIO);
                    button.setText(getUserValue(value));
                    button.setToolTipText(optionData.getDescription());
                    button.addSelectionListener(new EnumerationListener(optionData, value));
                    button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
                }
                controls.add(valuesContainer);
                // TODO retrieve initial values for layout options
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
    private class SliderListener implements SelectionListener {
        
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
            if (maxFloat < minFloat) {
                this.maxFloat = minFloat;
            } else {
                this.maxFloat = maxFloat;
            }
        }

        /**
         * {@inheritDoc}
         */
        public void widgetSelected(final SelectionEvent event) {
            Slider slider = (Slider) event.widget;
            float sliderValue = (float) (slider.getSelection() - slider.getMinimum())
                    / (slider.getMaximum() - slider.getMinimum());
            float optionValue = minFloat + sliderValue * (maxFloat - minFloat);
            switch (optionData.getType()) {
            case INT:
                layoutConfig.setOption(optionData, (int) optionValue);
                break;
            case FLOAT:
                layoutConfig.setOption(optionData, optionValue);
                break;
            }
            
            // trigger a new layout on the displayed diagram
            refreshLayout(false);
        }

        /**
         * {@inheritDoc}
         */
        public void widgetDefaultSelected(final SelectionEvent event) { }
    }
    
    /**
     * A listener for the layout algorithm selection button.
     */
    private class AlgorithmListener implements SelectionListener {

        /**
         * {@inheritDoc}
         */
        public void widgetSelected(final SelectionEvent event) {
            LayoutOptionData<?> optionData = LayoutDataService.getInstance().getOptionData(
                    LayoutOptions.ALGORITHM.getId());
            AlgorithmSelectionDialog dialog = new AlgorithmSelectionDialog(parent.getShell(),
                    (String) layoutConfig.getOption(optionData));
            if (dialog.open() == AlgorithmSelectionDialog.OK) {
                layoutConfig.setOption(optionData, dialog.getSelectedHint());
            }
            
            // trigger a new layout on the displayed diagram
            refreshLayout(true);
        }

        /**
         * {@inheritDoc}
         */
        public void widgetDefaultSelected(final SelectionEvent event) { }
    }
    
    /**
     * A listener for enumeration values.
     */
    private class EnumerationListener implements SelectionListener {
        
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

        /**
         * {@inheritDoc}
         */
        public void widgetSelected(final SelectionEvent event) {
            if (((Button) event.widget).getSelection()) {
                layoutConfig.setOption(optionData, value);
                refreshLayout(true);
            }
        }

        /**
         * {@inheritDoc}
         */
        public void widgetDefaultSelected(final SelectionEvent event) { }
    }

}
