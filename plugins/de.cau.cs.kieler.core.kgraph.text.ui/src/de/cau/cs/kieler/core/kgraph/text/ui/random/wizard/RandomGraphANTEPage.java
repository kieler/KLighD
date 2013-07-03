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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.core.kgraph.text.ui.random.GeneratorOptions;

/**
 * The options page for the ACYCLIC_NO_TRANSITIVE_EDGES graph type.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphANTEPage extends WizardPage {

    /** the generator options. */
    private GeneratorOptions options;

    /**
     * Constructs a RandomGraphANTEPage.
     * 
     * @param options the generator options
     */
    public RandomGraphANTEPage(final GeneratorOptions options) {
        super("randomGraphANTEPage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphANTEPage_title);
        setDescription(Messages.RandomGraphANTEPage_description);
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
        label.setText(Messages.RandomGraphANTEPage_number_of_nodes_caption);
        
        final Spinner nodesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        nodesSpinner.setToolTipText(Messages.RandomGraphANTEPage_number_of_nodes_help);
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
        
        // add NUMBER_OF_EDGES option
        label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphANTEPage_number_of_edges_caption);
        
        final Spinner edgesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        edgesSpinner.setToolTipText(Messages.RandomGraphANTEPage_number_of_edges_help);
        edgesSpinner.setValues(options.getProperty(GeneratorOptions.NUMBER_OF_EDGES),
                0, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        edgesSpinner.setLayoutData(gridData);
        
        edgesSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.NUMBER_OF_EDGES, edgesSpinner.getSelection());
            }
        });
        
        // add PLANAR option
        final Button planarButton = new Button(composite, SWT.CHECK);
        planarButton.setToolTipText(Messages.RandomGraphANTEPage_planarity_help);
        planarButton.setText(Messages.RandomGraphANTEPage_planarity_caption);
        planarButton.setSelection(options.getProperty(GeneratorOptions.PLANAR));
        planarButton.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false, 2, 1));
        
        planarButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.PLANAR, planarButton.getSelection());
            }
        });
    }
    
}
