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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;

/**
 * A factory for controls for layout options.
 *
 * @author msp
 */
public class OptionControlFactory {
    
    private Composite parent;
    private IWorkbenchPart workbenchPart;
    
    public OptionControlFactory(final Composite parent, final IWorkbenchPart workbenchPart) {
        this.parent = parent;
        this.workbenchPart = workbenchPart;
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
     * Create a control for the given layout option identifier.
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
    
    private static final int DEFAULT_MIN = 0;
    private static final int DEFAULT_MAX = 100;
    private static final int SLIDER_MIN_WIDTH = 100;
    
    /**
     * Create a control for the given layout option data instance.
     * 
     * @param optionData a layout option data instance
     * @param minValue the minimal value for the option
     * @param maxValue the maximal value for the option
     */
    public void createControl(final LayoutOptionData<?> optionData, final Float minValue,
            final Float maxValue) {
        Label label = new Label(parent, SWT.NONE);
        label.setText(optionData.getName());
        label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        
        switch (optionData.getType()) {
        case FLOAT: {
            // determine the minimal value
            float minFloat = DEFAULT_MIN;
            if (minValue != null) {
                minFloat = minValue;
            } else if (optionData.getLowerBound() instanceof Float) {
                minFloat = (Float) optionData.getLowerBound();
            }
            // determine the maximal value
            float maxFloat = DEFAULT_MAX;
            if (maxValue != null) {
                maxFloat = maxValue;
            } else if (optionData.getUpperBound() instanceof Float) {
                maxFloat = (Float) optionData.getUpperBound();
            }
            // create the slider
            Slider slider = new Slider(parent, 0);
            slider.addSelectionListener(new SliderListener());
            GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
            gridData.minimumWidth = SLIDER_MIN_WIDTH;
            slider.setLayoutData(gridData);
            break;
        }
        default:
            label = new Label(parent, SWT.NONE);
            label.setText("This option type is not supported");
            label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        }
    }
    
    private class SliderListener implements SelectionListener {

        /**
         * {@inheritDoc}
         */
        public void widgetSelected(SelectionEvent e) {
            // TODO Auto-generated method stub
            
        }

        /**
         * {@inheritDoc}
         */
        public void widgetDefaultSelected(SelectionEvent e) {
        }
        
    }

}
