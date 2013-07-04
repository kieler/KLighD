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

import org.eclipse.jface.viewers.IStructuredSelection;
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
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import de.cau.cs.kieler.core.kgraph.text.ui.random.GeneratorOptions;

/**
 * The wizard page from which to select the file where the graph is generated into.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphNewFilePage extends WizardNewFileCreationPage {

    /** the generator options. */
    private GeneratorOptions options;

    /**
     * Constructs the new file wizard page.
     * 
     * @param selection the selection the wizard is called on
     * @param options the generator options
     */
    public RandomGraphNewFilePage(final IStructuredSelection selection,
            final GeneratorOptions options) {
        super("randomGraphNewFilePage", selection); //$NON-NLS-1$
        setTitle(Messages.RandomGraphNewFilePage_title);
        setDescription(Messages.RandomGraphNewFilePage_description);
        setFileExtension("kgt"); //$NON-NLS-1$
        setAllowExistingResources(true);
        setFileName(options.getProperty(GeneratorOptions.FILE_NAME));
        this.options = options;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    // CHECKSTYLEOFF MagicNumber
    protected void createAdvancedControls(final Composite parent) {
        // group box
        Group group = new Group(parent, SWT.NONE);
        group.setText(Messages.RandomGraphNewFilePage_options_group_caption);
        
        GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        group.setLayoutData(gridData);
        
        GridLayout layout = new GridLayout(2, false);
        group.setLayout(layout);
        
        // add option for a number of graphs to be created
        Label label = new Label(group, SWT.NONE);
        label.setText(Messages.RandomGraphNewFilePage_number_of_graphs_caption);
        
        final Spinner graphsSpinner = new Spinner(group, SWT.BORDER | SWT.SINGLE);
        graphsSpinner.setToolTipText(Messages.RandomGraphNewFilePage_number_of_graphs_help);
        graphsSpinner.setValues(options.getProperty(GeneratorOptions.NUMBER_OF_GRAPHS),
                1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        graphsSpinner.setLayoutData(gridData);
        
        // label for randomization seed section
        label = new Label(group, SWT.NONE);
        label.setText("Randomization seed:");
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false, 2, 1);
        gridData.verticalIndent = 10;
        label.setLayoutData(gridData);
        
        // add option for time-based randomization seed
        final Button timeSeedButton = new Button(group, SWT.RADIO);
        timeSeedButton.setText(Messages.RandomGraphNewFilePage_time_seed_caption);
        timeSeedButton.setToolTipText(Messages.RandomGraphNewFilePage_time_seed_help);
        timeSeedButton.setSelection(options.getProperty(GeneratorOptions.TIME_BASED_RANDOMIZATION));
        
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
        gridData.horizontalIndent = 10;
        timeSeedButton.setLayoutData(gridData);
        
        // add option for constant randomization seed
        final Button constantSeedButton = new Button(group, SWT.RADIO);
        constantSeedButton.setText(Messages.RandomGraphNewFilePage_constant_seed_caption);
        constantSeedButton.setToolTipText(Messages.RandomGraphNewFilePage_constant_seed_help);
        constantSeedButton.setSelection(!options.getProperty(GeneratorOptions.TIME_BASED_RANDOMIZATION));
        
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.horizontalIndent = 10;
        constantSeedButton.setLayoutData(gridData);
        
        final Spinner seedSpinner = new Spinner(group, SWT.BORDER | SWT.SINGLE);
        seedSpinner.setToolTipText(Messages.RandomGraphNewFilePage_constant_seed_spinner_help);
        seedSpinner.setValues(options.getProperty(GeneratorOptions.RANDOMIZATION_SEED),
                0, Integer.MAX_VALUE, 0, 1, 10);
        seedSpinner.setEnabled(!options.getProperty(GeneratorOptions.TIME_BASED_RANDOMIZATION));
        
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 50;
        seedSpinner.setLayoutData(gridData);
        
        // create the advanced options and hide them
        Composite advanced = new Composite(group, SWT.NONE);
        advanced.setVisible(false);
        gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
        gridData.exclude = true;
        advanced.setLayoutData(gridData);
        layout = new GridLayout();
        advanced.setLayout(layout);
        super.createAdvancedControls(advanced);
        
        // event listeners
        graphsSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.NUMBER_OF_GRAPHS, graphsSpinner.getSelection());
            }
        });
        timeSeedButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                boolean timeBasedSeed = timeSeedButton.getSelection();
                options.setProperty(GeneratorOptions.TIME_BASED_RANDOMIZATION, timeBasedSeed);
                seedSpinner.setEnabled(!timeBasedSeed);
            }
        });
        seedSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.RANDOMIZATION_SEED, seedSpinner.getSelection());
            }
        });
    }
    
}
