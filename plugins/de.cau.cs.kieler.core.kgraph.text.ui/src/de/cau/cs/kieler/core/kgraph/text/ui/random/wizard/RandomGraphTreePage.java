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
package de.cau.cs.kieler.core.kgraph.text.ui.random.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.core.kgraph.text.ui.random.GeneratorOptions;

/**
 * The options page for the TREE graph type.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphTreePage extends WizardPage {

    /** the generator options. */
    private GeneratorOptions options;

    /**
     * Constructs a RandomGraphTreePage.
     * 
     * @param options the generator options
     */
    public RandomGraphTreePage(final GeneratorOptions options) {
        super("randomGraphTreePage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphTreePage_title);
        setDescription(Messages.RandomGraphTreePage_description);
        this.options = options;
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        composite.setLayout(new GridLayout(2, false));
        setControl(composite);
        
        GridData gridData;
        
        // add NUMBER_OF_NODES option
        Label label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphTreePage_number_of_nodes_caption);
        
        final Spinner nodesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        nodesSpinner.setToolTipText(Messages.RandomGraphTreePage_number_of_nodes_help);
        nodesSpinner.setValues(options.getProperty(GeneratorOptions.NUMBER_OF_NODES),
                1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        nodesSpinner.setLayoutData(gridData);
        
        nodesSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.NUMBER_OF_NODES, nodesSpinner.getSelection());
            }
        });
        
        // add MAX_DEGREE option
        label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphTreePage_max_degree_caption);
        
        final Spinner degreeSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        degreeSpinner.setToolTipText(Messages.RandomGraphTreePage_max_degree_help);
        degreeSpinner.setValues(options.getProperty(GeneratorOptions.MAX_DEGREE),
                1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        degreeSpinner.setLayoutData(gridData);
        
        degreeSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.MAX_DEGREE, degreeSpinner.getSelection());
            }
        });
        
        // add MAX_WIDTH option
        label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphTreePage_max_width_caption);
        
        final Spinner widthSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        widthSpinner.setToolTipText(Messages.RandomGraphTreePage_max_width_help);
        widthSpinner.setValues(options.getProperty(GeneratorOptions.MAX_WIDTH),
                1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        widthSpinner.setLayoutData(gridData);
        
        widthSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.MAX_WIDTH, widthSpinner.getSelection());
            }
        });
    }
    
}
