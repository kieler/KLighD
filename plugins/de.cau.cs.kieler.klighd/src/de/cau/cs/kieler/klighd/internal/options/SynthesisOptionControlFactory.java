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
package de.cau.cs.kieler.klighd.internal.options;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.TransformationOption;
import de.cau.cs.kieler.klighd.views.DiagramViewManager;

/**
 * A factory providing methods for creating diagram synthesis option controls in the diagram side bar.
 * 
 * @author chsch
 */
public class SynthesisOptionControlFactory {

    /** The parent composite into which controls are created. */
    private Composite parent;
    /** the form toolkit used to create controls. */
    private FormToolkit formToolkit;
    /** The set of controls to be disposed when {@link #clear()} is called. */
    private final Collection<Control> controls = new LinkedList<Control>();
    
    /** minimal width of sliders. */
    private static final int SLIDER_MIN_WIDTH = 70;
    /** the vertical space between different option controls, e.g. 2 check buttons. */
    private static final int MAJOR_VERTICAL_SPACING = 6;
    /** the vertical space between different option value controls, e.g. 2 radio buttons. */
    private static final int MINOR_VERTICAL_SPACING = 3;
    /** the vertical space above a group separator (separator with a label). */
    private static final int GROUP_SEPARATOR_SPACING = 10;
    /** the horizontal indentation option value controls, e.g. radio buttons. */
    private static final int MINOR_HORIZONTAL_MARGIN = 10;
    
    
    /**
     * Constructor.
     * 
     * @param parent the widget container
     * @param formToolkit the form toolkit to be used to create controls
     */
    public SynthesisOptionControlFactory(final Composite parent, final FormToolkit formToolkit) {
        this.parent = parent;
        this.formToolkit = formToolkit;
        
        // configure the parent's layout
        GridLayout gl = new GridLayout(1, false);
        gl.verticalSpacing = MAJOR_VERTICAL_SPACING;
        this.parent.setLayout(gl);
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
     * Factory method for creating a horizontal spacer with an optional label.
     * 
     * @param labelText the label text of the separator. If {@code null} or empty, the separator won't
     *                  have a label.
     */
    public void createSeparator(final String labelText) {
        // Check if the separator is supposed to have a label
        if (labelText == null || labelText.isEmpty()) {
            Label separator = formToolkit.createSeparator(parent, SWT.HORIZONTAL);
            controls.add(separator);
            GridDataFactory.fillDefaults().grab(true, false).applyTo(separator);
        } else {
            Label label = formToolkit.createLabel(parent, labelText);
            controls.add(label);
            label.setForeground(formToolkit.getColors().getColor(IFormColors.TITLE));
            GridDataFactory.fillDefaults()
                .grab(true, false)
                .indent(0, GROUP_SEPARATOR_SPACING)
                .applyTo(label);
        }

    }
    
    /**
     * Factory method for creating a check button related to a 'check' option.  
     * 
     * @param option the 'check' option
     * @param context the related {@link TransformationContext} the option is declared in
     * @param viewId the id of the current view, is required used for invoking the diagram update. 
     */
    public void createCheckOptionControl(final TransformationOption option,
            final TransformationContext<?, ?> context, final String viewId) {

        final Button checkButton = formToolkit.createButton(parent, option.getName(), SWT.CHECK);
        checkButton.setToolTipText(option.getName());
        controls.add(checkButton);

        // set initial value
        checkButton.setSelection((Boolean) context.getOptionValue(option));

        // add a selection listener for instant diagram updates
        checkButton.addSelectionListener(new SelectionAdapter() {
            
            @Override
            public void widgetSelected(final SelectionEvent event) {
                // set the new option value and trigger the diagram update
                context.configureOption(option, ((Button) event.widget).getSelection());
                
                // trigger the diagram update
                Display.getCurrent().asyncExec(new Runnable() {
                    public void run() {
                        DiagramViewManager.getInstance().updateView(viewId);
                    }
                });
            }
        });
    }
    
    /**
     * Factory method for creating a check button related to a 'choice' option.  
     * 
     * @param option the 'choice' option
     * @param context the related {@link TransformationContext} the option is declared in
     * @param viewId the id of the current view, is required used for invoking the diagram update. 
     */
    public void createChoiceOptionControl(final TransformationOption option,
            final TransformationContext<?, ?> context, final String viewId) {
        
        final GridLayout gl = new GridLayout();
        gl.verticalSpacing = MINOR_VERTICAL_SPACING;
        gl.marginHeight = 0;
        gl.marginWidth = 0;

        // create a container composite in order to realize correct radio button grouping
        final Composite valuesContainer = formToolkit.createComposite(parent);
        //  ... and determine its layout
        valuesContainer.setLayout(gl);
        controls.add(valuesContainer);

        // add the radio group label ...
        formToolkit.createLabel(valuesContainer, option.getName() + ":");
        
        // ... and a radio button for each possible value
        GridData vGd;
        for (final Object value : option.getValues()) {
            vGd = new GridData();
            vGd.horizontalIndent = MINOR_HORIZONTAL_MARGIN;
            
            // create the button ...
            Button button = formToolkit.createButton(valuesContainer, value.toString(), SWT.RADIO);
            button.setToolTipText(value.toString());
            button.setLayoutData(vGd);

            // ... add a selection listener for instant diagram updates ...
            button.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(final SelectionEvent event) {
                    if (((Button) event.widget).getSelection()) {
                        // set the new option value and trigger the diagram update
                        context.configureOption(option, value);
                        
                        // trigger the diagram update
                        Display.getCurrent().asyncExec(new Runnable() {
                            public void run() {
                                DiagramViewManager.getInstance().updateView(viewId);
                            }
                        });
                    }
                }
            });

            // ... and set its selection depending on the currently configured option value
            button.setSelection(value.equals(context.getOptionValue(option)));
        }
    }
    
    /**
     * Factory method for creating a check button related to a 'range' option.  
     * 
     * @param option the 'range' option
     * @param context the related {@link TransformationContext} the option is declared in
     * @param viewId the id of the current view, is required used for invoking the diagram update. 
     */
    public void createRangeOptionControl(final TransformationOption option,
            final TransformationContext<?, ?> context, final String viewId) {
        
        final GridLayout gl = new GridLayout();
        gl.verticalSpacing = MINOR_VERTICAL_SPACING;
        gl.marginTop = 0;
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        
        // create a container composite in order to group the label and the scaler
        final Composite container = formToolkit.createComposite(parent);
        //  ... and determine its layout
        container.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        container.setLayout(gl);
        controls.add(container);
        
        // add the label ...
        final Label label = formToolkit.createLabel(container, "");
        
        // ... and the scaler for choosing the value
        final Scale scale = new Scale(container, SWT.NONE);
        // the following setting is needed on windows
        scale.setBackground(container.getBackground());
        scale.setToolTipText(option.getName());

        // configure its layout, esp. the minimal width and the 'grab additional space' 
        final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.minimumWidth = SLIDER_MIN_WIDTH;
        scale.setLayoutData(gridData);

        // ... and its provided range
        final double min = option.getRange().getFirst().doubleValue();
        final double max = option.getRange().getSecond().doubleValue();
        final double stepSize = option.getStepSize().doubleValue();
        final double init = ((Number) context.getOptionValue(option)).doubleValue();
        
        final double minShifted = min + stepSize / 2;
        
        final boolean floatSteps = (option.getStepSize() instanceof Float
                || option.getStepSize() instanceof Double);
        
        final int scalerMax = 255;
        final double scalerStepSize = (max - min) / scalerMax;
        
        scale.setMinimum(0);
        scale.setMaximum(scalerMax);
        
        final int scaleInit = (int) Math.ceil((init - min) / scalerStepSize);
        scale.setSelection(scaleInit);
        
        // configure the label's text in terms of a fixed string and the initial value
        final String labelString = option.getName() + ": ";
        label.setText(labelString + context.getOptionValue(option));

        // and finally add a selection listener for instant diagram updates
        scale.addSelectionListener(new SelectionAdapter() {
            
            // a little buffer used for dropping unnecessary events
            private double currentValue = scale.getSelection();
            
            @Override
            public void widgetSelected(final SelectionEvent event) {
                final Scale scale = (Scale) event.widget;
                
                // determine the actually selected value
                Double value = minShifted + scalerStepSize * ((double) scale.getSelection());
                
                // round it wrt the required step size
                value = min + Math.floor((value - min) / stepSize) * stepSize;
                
                // configure the adjusted selection
                //  lets the the scaler snap to the closest possible value
                scale.setSelection((int) Math.floor((value - min) / scalerStepSize));

                // check whether the value actually changed
                if (value == currentValue) {
                    return;
                } else {
                    currentValue = value;
                }
                
                // update the value in the label
                //  and configure the new option value in the view context
                if (floatSteps) {
                    label.setText(labelString + value);
                    context.configureOption(option, value.floatValue());
                } else {
                    label.setText(labelString + value.intValue());
                    context.configureOption(option, value.intValue());
                }
                container.layout(true);
                
                // trigger the diagram update
                Display.getCurrent().asyncExec(new Runnable() {
                    public void run() {
                        DiagramViewManager.getInstance().updateView(viewId);
                    }
                });
            }
        });
    }
}
