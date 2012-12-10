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
            createControl(optionData, null, null);
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
            createControl(optionData, minValue, maxValue);
        }
    }
    
    private static final int SLIDER_MIN_WIDTH = 100;
    
    /**
     * Create a control for the given layout option data instance with given bounds.
     * 
     * @param optionData a layout option data instance
     * @param minValue the minimal value for the option
     * @param maxValue the maximal value for the option
     */
    public void createControl(final LayoutOptionData<?> optionData, final Float minValue,
            final Float maxValue) {
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
                Slider slider = new Slider(parent, 0);
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
        public void widgetDefaultSelected(final SelectionEvent event) {
        }
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
        public void widgetDefaultSelected(final SelectionEvent event) {
        }
    }

}
