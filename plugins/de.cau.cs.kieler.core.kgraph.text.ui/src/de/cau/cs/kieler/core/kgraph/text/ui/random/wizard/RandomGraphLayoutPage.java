/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kgraph.text.ui.random.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.core.kgraph.text.ui.random.GeneratorOptions;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.options.PortConstraints;

/**
 * The page for layout options.
 * 
 * @author msp
 */
public class RandomGraphLayoutPage extends WizardPage {
    
    /** the generator options. */
    private GeneratorOptions options;

    /**
     * Constructs a RandomGraphOptionsPage.
     * 
     * @param options the generator options
     */
    public RandomGraphLayoutPage(final GeneratorOptions options) {
        super("randomGraphLayoutPage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphLayoutPage_title);
        setDescription(Messages.RandomGraphLayoutPage_description);
        this.options = options;
    }

    // CHECKSTYLEOFF MagicNumber
    
    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        composite.setLayoutData(gridData);
        setControl(composite);
        
        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 10;
        composite.setLayout(layout);
        
        // Create and add the different option groups
        createPortsGroup(composite);
    }
    
    /**
     * Create group for ports.
     * 
     * @param composite the parent composite
     */
    private void createPortsGroup(final Composite composite) {
        Group portsGroup = new Group(composite, SWT.NULL);
        portsGroup.setText(Messages.RandomGraphLayoutPage_ports_group_caption);
        portsGroup.setLayout(new GridLayout(1, false));
        portsGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));
        
        // No port constraints
        final Button noConstraintsButton = new Button(portsGroup, SWT.RADIO);
        noConstraintsButton.setText(Messages.RandomGraphLayoutPage_ports_none_caption);
        noConstraintsButton.setToolTipText(Messages.RandomGraphLayoutPage_ports_none_help);
        noConstraintsButton.setSelection(options.getProperty(GeneratorOptions.PORT_CONSTRAINTS)
                == PortConstraints.UNDEFINED);
        
        // Free port constraints
        final Button freeConstraintsButton = new Button(portsGroup, SWT.RADIO);
        freeConstraintsButton.setText(Messages.RandomGraphLayoutPage_ports_free_caption);
        freeConstraintsButton.setToolTipText(Messages.RandomGraphLayoutPage_ports_free_help);
        freeConstraintsButton.setSelection(options.getProperty(GeneratorOptions.PORT_CONSTRAINTS)
                == PortConstraints.FREE);
        
        // Fixed side port constraints
        final Button fixedSideButton = new Button(portsGroup, SWT.RADIO);
        fixedSideButton.setText(Messages.RandomGraphLayoutPage_ports_fixed_side_caption);
        fixedSideButton.setToolTipText(Messages.RandomGraphLayoutPage_ports_fixed_side_help);
        fixedSideButton.setSelection(options.getProperty(GeneratorOptions.PORT_CONSTRAINTS)
                == PortConstraints.FIXED_SIDE);
        
        // Fixed position port constraints
        final Button fixedPosButton = new Button(portsGroup, SWT.RADIO);
        fixedPosButton.setText(Messages.RandomGraphLayoutPage_ports_fixed_pos_caption);
        fixedPosButton.setToolTipText(Messages.RandomGraphLayoutPage_ports_fixed_pos_help);
        fixedPosButton.setSelection(options.getProperty(GeneratorOptions.PORT_CONSTRAINTS)
                == PortConstraints.FIXED_POS);
        
        // Side distribution for incoming edges
        Composite incomingComposite = new Composite(portsGroup, SWT.NONE);
        GridData gridData = new GridData();
        gridData.horizontalIndent = 30;
        incomingComposite.setLayoutData(gridData);
        incomingComposite.setLayout(new GridLayout(4, false));
        
        new Label(incomingComposite, SWT.NONE);
        final Spinner incomingNorthSpinner = createPortSideSpinner(incomingComposite,
                Messages.RandomGraphLayoutPage_ports_north_caption,
                GeneratorOptions.INCOMING_NORTH_SIDE);
        incomingNorthSpinner.setToolTipText(Messages.RandomGraphLayoutPage_ports_incoming_north_help);
        new Label(incomingComposite, SWT.NONE);
        final Spinner incomingWestSpinner = createPortSideSpinner(incomingComposite,
                Messages.RandomGraphLayoutPage_ports_incoming_caption + "   "
                        + Messages.RandomGraphLayoutPage_ports_west_caption,
                GeneratorOptions.INCOMING_WEST_SIDE);
        incomingWestSpinner.setToolTipText(Messages.RandomGraphLayoutPage_ports_incoming_west_help);
        final Spinner incomingEastSpinner = createPortSideSpinner(incomingComposite,
                Messages.RandomGraphLayoutPage_ports_east_caption,
                GeneratorOptions.INCOMING_EAST_SIDE);
        incomingEastSpinner.setToolTipText(Messages.RandomGraphLayoutPage_ports_incoming_east_help);
        new Label(incomingComposite, SWT.NONE);
        final Spinner incomingSouthSpinner = createPortSideSpinner(incomingComposite,
                Messages.RandomGraphLayoutPage_ports_south_caption,
                GeneratorOptions.INCOMING_SOUTH_SIDE);
        incomingSouthSpinner.setToolTipText(Messages.RandomGraphLayoutPage_ports_incoming_south_help);
        new Label(incomingComposite, SWT.NONE);

        // Side distribution for outgoing edges
        Composite outgoingComposite = new Composite(portsGroup, SWT.NONE);
        gridData = new GridData();
        gridData.horizontalIndent = 30;
        outgoingComposite.setLayoutData(gridData);
        outgoingComposite.setLayout(new GridLayout(4, false));
        
        new Label(outgoingComposite, SWT.NONE);
        final Spinner outgoingNorthSpinner = createPortSideSpinner(outgoingComposite,
                Messages.RandomGraphLayoutPage_ports_north_caption,
                GeneratorOptions.OUTGOING_NORTH_SIDE);
        outgoingNorthSpinner.setToolTipText(Messages.RandomGraphLayoutPage_ports_outgoing_north_help);
        new Label(outgoingComposite, SWT.NONE);
        final Spinner outgoingWestSpinner = createPortSideSpinner(outgoingComposite,
                Messages.RandomGraphLayoutPage_ports_outgoing_caption + "   "
                        + Messages.RandomGraphLayoutPage_ports_west_caption,
                GeneratorOptions.OUTGOING_WEST_SIDE);
        outgoingWestSpinner.setToolTipText(Messages.RandomGraphLayoutPage_ports_outgoing_west_help);
        final Spinner outgoingEastSpinner = createPortSideSpinner(outgoingComposite,
                Messages.RandomGraphLayoutPage_ports_east_caption,
                GeneratorOptions.OUTGOING_EAST_SIDE);
        outgoingEastSpinner.setToolTipText(Messages.RandomGraphLayoutPage_ports_outgoing_east_help);
        new Label(outgoingComposite, SWT.NONE);
        final Spinner outgoingSouthSpinner = createPortSideSpinner(outgoingComposite,
                Messages.RandomGraphLayoutPage_ports_south_caption,
                GeneratorOptions.OUTGOING_SOUTH_SIDE);
        outgoingSouthSpinner.setToolTipText(Messages.RandomGraphLayoutPage_ports_outgoing_south_help);
        new Label(outgoingComposite, SWT.NONE);
        
        // Event listeners
        noConstraintsButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.PORT_CONSTRAINTS, PortConstraints.UNDEFINED);
                incomingNorthSpinner.setEnabled(false);
                incomingEastSpinner.setEnabled(false);
                incomingSouthSpinner.setEnabled(false);
                incomingWestSpinner.setEnabled(false);
                outgoingNorthSpinner.setEnabled(false);
                outgoingEastSpinner.setEnabled(false);
                outgoingSouthSpinner.setEnabled(false);
                outgoingWestSpinner.setEnabled(false);
            }
        });
        
        freeConstraintsButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.PORT_CONSTRAINTS, PortConstraints.FREE);
                incomingNorthSpinner.setEnabled(false);
                incomingEastSpinner.setEnabled(false);
                incomingSouthSpinner.setEnabled(false);
                incomingWestSpinner.setEnabled(false);
                outgoingNorthSpinner.setEnabled(false);
                outgoingEastSpinner.setEnabled(false);
                outgoingSouthSpinner.setEnabled(false);
                outgoingWestSpinner.setEnabled(false);
            }
        });
        
        fixedSideButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
                incomingNorthSpinner.setEnabled(true);
                incomingEastSpinner.setEnabled(true);
                incomingSouthSpinner.setEnabled(true);
                incomingWestSpinner.setEnabled(true);
                outgoingNorthSpinner.setEnabled(true);
                outgoingEastSpinner.setEnabled(true);
                outgoingSouthSpinner.setEnabled(true);
                outgoingWestSpinner.setEnabled(true);
            }
        });
        
        fixedPosButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
                incomingNorthSpinner.setEnabled(true);
                incomingEastSpinner.setEnabled(true);
                incomingSouthSpinner.setEnabled(true);
                incomingWestSpinner.setEnabled(true);
                outgoingNorthSpinner.setEnabled(true);
                outgoingEastSpinner.setEnabled(true);
                outgoingSouthSpinner.setEnabled(true);
                outgoingWestSpinner.setEnabled(true);
            }
        });
    }
    
    /**
     * Create a spinner for distribution of port sides.
     * 
     * @param composite the parent composite
     * @param labelText the label to display
     * @param generatorOption the generator option affected by the created spinner
     * @return a spinner for port sides
     */
    private Spinner createPortSideSpinner(final Composite composite, final String labelText,
            final IProperty<Integer> generatorOption) {
        Label label = new Label(composite, SWT.NONE);
        label.setText(labelText);
        GridData gridData = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
        label.setLayoutData(gridData);

        final Spinner spinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        spinner.setValues(options.getProperty(generatorOption), 0, Integer.MAX_VALUE, 0, 1, 10);
        spinner.setEnabled(options.getProperty(GeneratorOptions.PORT_CONSTRAINTS).isSideFixed());
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 60;
        spinner.setLayoutData(gridData);
        
        spinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(generatorOption, spinner.getSelection());
                validate();
            }
        });
        return spinner;
    }
    
    /**
     * Check all values that can be configured through this wizard page.
     */
    private void validate() {
        int incomingSum = options.getProperty(GeneratorOptions.INCOMING_NORTH_SIDE)
                + options.getProperty(GeneratorOptions.INCOMING_EAST_SIDE)
                + options.getProperty(GeneratorOptions.INCOMING_SOUTH_SIDE)
                + options.getProperty(GeneratorOptions.INCOMING_WEST_SIDE);
        int outgoingSum = options.getProperty(GeneratorOptions.OUTGOING_NORTH_SIDE)
                + options.getProperty(GeneratorOptions.OUTGOING_EAST_SIDE)
                + options.getProperty(GeneratorOptions.OUTGOING_SOUTH_SIDE)
                + options.getProperty(GeneratorOptions.OUTGOING_WEST_SIDE);
        if (incomingSum <= 0) {
            setErrorMessage(Messages.RandomGraphLayoutPage_ports_incoming_error);
            setPageComplete(false);
        } else if (outgoingSum <= 0) {
            setErrorMessage(Messages.RandomGraphLayoutPage_ports_outgoing_error);
            setPageComplete(false);
        } else {
            setErrorMessage(null);
            setPageComplete(true);
        }
    }
    
}
