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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
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
        checkButton.addSelectionListener(new SelectionListener() {
            
            public void widgetSelected(final SelectionEvent event) {
                // set the new option value and trigger the diagram update
                context.configureOption(option, ((Button) event.widget).getSelection());
                DiagramViewManager.getInstance().updateView(viewId);
            }
            
            public void widgetDefaultSelected(final SelectionEvent e) {
                // nothing
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
            button.addSelectionListener(new SelectionListener() {

                public void widgetSelected(final SelectionEvent event) {
                    if (((Button) event.widget).getSelection()) {
                        // set the new option value and trigger the diagram update
                        context.configureOption(option, value);
                        DiagramViewManager.getInstance().updateView(viewId);
                    }
                }

                public void widgetDefaultSelected(final SelectionEvent event) {
                    // nothing
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
        container.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        container.setLayout(gl);
        controls.add(container);
        
        // add the label ...
        final Label label = formToolkit.createLabel(container, "");
        
        // ... and the scaler for choosing the value
        final Scale scaler = new Scale(container, SWT.NONE);
        // the following setting is needed on windows
        scaler.setBackground(container.getBackground());
        scaler.setToolTipText(option.getName());

        // configure its layout, esp. the minimal width and the 'grab additional space' 
        final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.minimumWidth = SLIDER_MIN_WIDTH;
        scaler.setLayoutData(gridData);

        // ... and its provided range
        final int min = (int) Math.floor(option.getRange().getFirst().floatValue());
        final int max = (int) Math.ceil(option.getRange().getSecond().floatValue());
        scaler.setMinimum(min);
        scaler.setMaximum(max);
        scaler.setSelection(((Number) context.getOptionValue(option)).intValue());
        
        // configure the label's text in terms of a fixed string and the initial value
        final String labelString = option.getName() + ": ";        
        label.setText(labelString + scaler.getSelection());

        // and finally add a selection listener for instant diagram updates
        scaler.addSelectionListener(new SelectionListener() {
            
            // a little buffer used for dropping unnecessary events
            private int currentValue = scaler.getSelection();
            
            public void widgetSelected(final SelectionEvent event) {
                final int value = ((Scale) event.widget).getSelection();
                label.setText(labelString + value);
                container.layout(true);
                
                if (value == currentValue) {
                    return;
                } else {
                    currentValue = value;
                }
                
                // set the new option value and trigger the diagram update
                context.configureOption(option, value);
                DiagramViewManager.getInstance().updateView(viewId);
            }
            
            public void widgetDefaultSelected(final SelectionEvent e) {
                // nothing
            }
        });
    }
}
