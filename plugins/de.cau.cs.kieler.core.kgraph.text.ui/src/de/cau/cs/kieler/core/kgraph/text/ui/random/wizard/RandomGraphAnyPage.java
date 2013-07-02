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
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.core.kgraph.text.ui.random.GeneratorOptions;

/**
 * The options page for the custom graph type.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphAnyPage extends WizardPage {
    
    /** the generator options. */
    private GeneratorOptions options;

    /**
     * Constructs a RandomGraphAnyPage.
     * 
     * @param options the generator options
     */
    public RandomGraphAnyPage(final GeneratorOptions options) {
        super("randomGraphAnyPage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphAnyPage_title);
        setDescription(Messages.RandomGraphAnyPage_description);
        this.options = options;
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        createOptions(composite);
        setControl(composite);
    }

    // CHECKSTYLEOFF MagicNumber
    private void createOptions(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        
        GridLayout layout = new GridLayout(1, false);
        layout.verticalSpacing = 10;
        composite.setLayout(layout);
        
        // Create the different groups
        createNodesGroup(composite);
        createEdgeGroup(composite);
        createGraphPropertiesGroup(composite);
    }

    /**
     * Creates the group holding node options and adds it to the given composite.
     * 
     * @param composite the composite to add the group to.
     */
    private void createNodesGroup(final Composite composite) {
        GridData gridData;
        
        // Nodes group
        Group nodesGroup = new Group(composite, SWT.NONE);
        nodesGroup.setText(Messages.RandomGraphAnyPage_nodes_group_caption);
        nodesGroup.setLayout(new GridLayout(2, false));
        nodesGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        
        // Number of Nodes
        Label label = new Label(nodesGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_number_of_nodes_caption);
        label.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false, 2, 1));
        
        label = new Label(nodesGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_number_of_nodes_min);
        
        final Spinner nodesMinSpinner = new Spinner(nodesGroup, SWT.BORDER | SWT.SINGLE);
        nodesMinSpinner.setToolTipText(Messages.RandomGraphAnyPage_number_of_nodes_min_help);
        nodesMinSpinner.setValues(options.getProperty(GeneratorOptions.NUMBER_OF_NODES_MIN),
                1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        nodesMinSpinner.setLayoutData(gridData);
        
        label = new Label(nodesGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_number_of_nodes_max);
        
        final Spinner nodesMaxSpinner = new Spinner(nodesGroup, SWT.BORDER | SWT.SINGLE);
        nodesMaxSpinner.setToolTipText(Messages.RandomGraphAnyPage_number_of_nodes_max_help);
        nodesMaxSpinner.setValues(options.getProperty(GeneratorOptions.NUMBER_OF_NODES_MAX),
                1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        nodesMaxSpinner.setLayoutData(gridData);
        
        // Event Listeners
        nodesMinSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.NUMBER_OF_NODES_MIN,
                        nodesMinSpinner.getSelection());
                validate();
            }
        });

        nodesMaxSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.NUMBER_OF_NODES_MAX,
                        nodesMaxSpinner.getSelection());
                validate();
            }
        });
    }

    /**
     * Creates the group holding edge options and adds it to the given composite.
     * 
     * @param composite the composite to add the group to.
     */
    private void createEdgeGroup(final Composite composite) {
        GridData gridData;
        
        // Edges Group
        Group edgeGroup = new Group(composite, SWT.NONE);
        edgeGroup.setText(Messages.RandomGraphAnyPage_edges_group_caption);
        edgeGroup.setLayout(new GridLayout(4, false));
        edgeGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        
        // Edge Group Description
        Label edgeDescriptionLabel = new Label(edgeGroup, SWT.WRAP);
        edgeDescriptionLabel.setText(Messages.RandomGraphAnyPage_edges_description_caption);
        edgeDescriptionLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
        
        // Number of Edges
        Button edgesSwitch = new Button(edgeGroup, SWT.RADIO | SWT.LEFT);
        edgesSwitch.setText(Messages.RandomGraphAnyPage_number_of_edges_caption);
        edgesSwitch.setToolTipText(Messages.RandomGraphAnyPage_number_of_edges_help);
        
        final Spinner edgesSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
        edgesSpinner.setToolTipText(Messages.RandomGraphAnyPage_number_of_edges_help);
        edgesSpinner.setValues(options.getProperty(GeneratorOptions.NUMBER_OF_EDGES),
                0, Integer.MAX_VALUE, 0, 1, 10);
        edgesSpinner.setEnabled(false);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        edgesSpinner.setLayoutData(gridData);
        
        Label label = new Label(edgeGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_number_of_edges_variance_caption);
        
        final Spinner edgesVarianceSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
        edgesVarianceSpinner.setToolTipText(Messages.RandomGraphAnyPage_number_of_edges_variance_help);
        edgesVarianceSpinner.setValues(options.getProperty(GeneratorOptions.EDGES_VARIANCE),
                0, Integer.MAX_VALUE, 0, 1, 10);
        edgesVarianceSpinner.setEnabled(false);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        edgesVarianceSpinner.setLayoutData(gridData);
        
        // Relative Number of Edges
        Button edgesRelSwitch = new Button(edgeGroup, SWT.RADIO | SWT.LEFT);
        edgesRelSwitch.setText(Messages.RandomGraphAnyPage_rel_number_of_edges_caption);
        edgesRelSwitch.setToolTipText(Messages.RandomGraphAnyPage_rel_number_of_edges_help);
        
        final Spinner edgesRelSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
        edgesRelSpinner.setToolTipText(Messages.RandomGraphAnyPage_rel_number_of_edges_help);
        edgesRelSpinner.setValues((int) (options.getProperty(GeneratorOptions.EDGES_RELATIVE) * 100),
                0, Integer.MAX_VALUE, 2, 1, 10);
        edgesRelSpinner.setEnabled(false);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        edgesRelSpinner.setLayoutData(gridData);
        
        label = new Label(edgeGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_rel_number_of_edges_variance_caption);
        
        final Spinner edgesRelVarianceSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
        edgesRelVarianceSpinner.setToolTipText(
                Messages.RandomGraphAnyPage_rel_number_of_edges_variance_help);
        edgesRelVarianceSpinner.setValues(
                (int) (options.getProperty(GeneratorOptions.EDGES_REL_VARIANCE) * 100),
                0, Integer.MAX_VALUE, 2, 1, 10);
        edgesRelVarianceSpinner.setEnabled(false);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        edgesRelVarianceSpinner.setLayoutData(gridData);
        
        // Density
        Button densitySwitch = new Button(edgeGroup, SWT.RADIO | SWT.LEFT);
        densitySwitch.setText(Messages.RandomGraphAnyPage_density_caption);
        densitySwitch.setToolTipText(Messages.RandomGraphAnyPage_density_help);
        
        final Spinner densitySpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
        densitySpinner.setToolTipText(Messages.RandomGraphAnyPage_density_help);
        densitySpinner.setValues((int) (options.getProperty(GeneratorOptions.DENSITY) * 100),
                0, 100, 0, 1, 10);
        densitySpinner.setEnabled(false);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        densitySpinner.setLayoutData(gridData);
        
        label = new Label(edgeGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_density_variance_caption);
        
        final Spinner densityVarianceSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
        densityVarianceSpinner.setToolTipText(Messages.RandomGraphAnyPage_density_variance_help);
        densityVarianceSpinner.setValues(
                (int) (options.getProperty(GeneratorOptions.DENSITY_VARIANCE) * 100),
                0, 100, 0, 1, 10);
        densityVarianceSpinner.setEnabled(false);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        densityVarianceSpinner.setLayoutData(gridData);
        
        // Outgoing Edges
        Button outgoingSwitch = new Button(edgeGroup, SWT.RADIO | SWT.LEFT);
        outgoingSwitch.setText(Messages.RandomGraphAnyPage_outgoing_caption);
        outgoingSwitch.setToolTipText(Messages.RandomGraphAnyPage_outgoing_help);
        outgoingSwitch.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false, 4, 1));
        
        // Minimum
        label = new Label(edgeGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_min_outgoing_caption);
        
        gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);
        
        final Spinner minOutSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
        minOutSpinner.setToolTipText(Messages.RandomGraphAnyPage_min_outgoing_help);
        minOutSpinner.setValues(options.getProperty(GeneratorOptions.MIN_OUTGOING_EDGES),
                0, Integer.MAX_VALUE, 0, 1, 10);
        minOutSpinner.setEnabled(false);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        minOutSpinner.setLayoutData(gridData);
        // fill the remaining space
        new Label(edgeGroup, SWT.NONE).setLayoutData(
                new GridData(SWT.NONE, SWT.NONE, false, false, 2, 1));
        
        // Maximum
        label = new Label(edgeGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_max_outgoing_caption);
        
        gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);
        
        final Spinner maxOutSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
        maxOutSpinner.setToolTipText(Messages.RandomGraphAnyPage_max_outgoing_help);
        maxOutSpinner.setValues(options.getProperty(GeneratorOptions.MAX_OUTGOING_EDGES),
                0, Integer.MAX_VALUE, 0, 1, 10);
        maxOutSpinner.setEnabled(false);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        maxOutSpinner.setLayoutData(gridData);
        // fill the remaining space
        new Label(edgeGroup, SWT.NONE).setLayoutData(
                new GridData(SWT.NONE, SWT.NONE, false, false, 2, 1));

        // Event listeners
        edgesSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.NUMBER_OF_EDGES, edgesSpinner.getSelection());
            }
        });
        
        edgesVarianceSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.EDGES_VARIANCE,
                        edgesVarianceSpinner.getSelection());
            }
        });
        
        edgesRelSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.EDGES_RELATIVE,
                        edgesRelSpinner.getSelection() / 100.0);
            }
        });
        
        edgesRelVarianceSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.EDGES_REL_VARIANCE,
                        edgesRelVarianceSpinner.getSelection() / 100.0);
            }
        });

        densitySpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.DENSITY,
                        densitySpinner.getSelection() / 100.0);
            }
        });

        densityVarianceSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.DENSITY_VARIANCE,
                        densityVarianceSpinner.getSelection() / 100.0);
            }
        });

        minOutSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.MIN_OUTGOING_EDGES, minOutSpinner.getSelection());
                validate();
            }
        });

        maxOutSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.MAX_OUTGOING_EDGES, maxOutSpinner.getSelection());
                validate();
            }
        });
        
        // Radio Button Controlling
        edgesSwitch.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.EDGE_DETERMINATION,
                        GeneratorOptions.EdgeDetermination.GRAPH_EDGES);
                edgesSpinner.setEnabled(true);
                edgesVarianceSpinner.setEnabled(true);
                edgesRelSpinner.setEnabled(false);
                edgesRelVarianceSpinner.setEnabled(false);
                densitySpinner.setEnabled(false);
                densityVarianceSpinner.setEnabled(false);
                minOutSpinner.setEnabled(false);
                maxOutSpinner.setEnabled(false);
            }
        });
        edgesRelSwitch.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.EDGE_DETERMINATION,
                        GeneratorOptions.EdgeDetermination.RELATIVE);
                edgesSpinner.setEnabled(false);
                edgesVarianceSpinner.setEnabled(false);
                edgesRelSpinner.setEnabled(true);
                edgesRelVarianceSpinner.setEnabled(true);
                densitySpinner.setEnabled(false);
                densityVarianceSpinner.setEnabled(false);
                minOutSpinner.setEnabled(false);
                maxOutSpinner.setEnabled(false);
            }
        });
        densitySwitch.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.EDGE_DETERMINATION,
                        GeneratorOptions.EdgeDetermination.DENSITY);
                edgesSpinner.setEnabled(false);
                edgesVarianceSpinner.setEnabled(false);
                edgesRelSpinner.setEnabled(false);
                edgesRelVarianceSpinner.setEnabled(false);
                densitySpinner.setEnabled(true);
                densityVarianceSpinner.setEnabled(true);
                minOutSpinner.setEnabled(false);
                maxOutSpinner.setEnabled(false);
            }
        });
        outgoingSwitch.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.EDGE_DETERMINATION,
                        GeneratorOptions.EdgeDetermination.OUTGOING_EDGES);
                edgesSpinner.setEnabled(false);
                edgesVarianceSpinner.setEnabled(false);
                edgesRelSpinner.setEnabled(false);
                edgesRelVarianceSpinner.setEnabled(false);
                densitySpinner.setEnabled(false);
                densityVarianceSpinner.setEnabled(false);
                minOutSpinner.setEnabled(true);
                maxOutSpinner.setEnabled(true);
            }
        });
        switch (options.getProperty(GeneratorOptions.EDGE_DETERMINATION)) {
        case DENSITY:
            densitySwitch.setSelection(true);
            densitySpinner.setEnabled(true);
            densityVarianceSpinner.setEnabled(true);
            break;
        case OUTGOING_EDGES:
            outgoingSwitch.setSelection(true);
            minOutSpinner.setEnabled(true);
            maxOutSpinner.setEnabled(true);
            break;
        case RELATIVE:
            edgesRelSwitch.setSelection(true);
            edgesRelSpinner.setEnabled(true);
            edgesRelVarianceSpinner.setEnabled(true);
            break;
        case GRAPH_EDGES:
        default:
            edgesSwitch.setSelection(true);
            edgesSpinner.setEnabled(true);
            edgesVarianceSpinner.setEnabled(true);
            break;
        }
    }

    /**
     * Creates the group holding graph property options and adds it to the given composite.
     * 
     * @param composite the composite to add the group to.
     */
    private void createGraphPropertiesGroup(final Composite composite) {
        // Graph Properties Group
        Group optionsGroup = new Group(composite, SWT.NONE);
        optionsGroup.setText(Messages.RandomGraphAnyPage_options_group_caption);
        optionsGroup.setLayout(new RowLayout(SWT.VERTICAL));
        optionsGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        
        // Self Loops
        final Button selfLoopsButton = new Button(optionsGroup, SWT.CHECK);
        selfLoopsButton.setText(Messages.RandomGraphAnyPage_self_loops_caption);
        selfLoopsButton.setToolTipText(Messages.RandomGraphAnyPage_self_loops_help);
        selfLoopsButton.setSelection(options.getProperty(GeneratorOptions.SELF_LOOPS));
        
        // Multi Edges
        final Button multiEdgesButton = new Button(optionsGroup, SWT.CHECK);
        multiEdgesButton.setText(Messages.RandomGraphAnyPage_multi_edges_caption);
        multiEdgesButton.setToolTipText(Messages.RandomGraphAnyPage_multi_edges_help);
        multiEdgesButton.setSelection(options.getProperty(GeneratorOptions.MULTI_EDGES));
        
        // Cycles
        final Button cyclesButton = new Button(optionsGroup, SWT.CHECK);
        cyclesButton.setText(Messages.RandomGraphAnyPage_cycles_caption);
        cyclesButton.setToolTipText(Messages.RandomGraphAnyPage_cycles_help);
        cyclesButton.setSelection(options.getProperty(GeneratorOptions.CYCLES));
        
        // Isolated Nodes
        final Button isolatedButton = new Button(optionsGroup, SWT.CHECK);
        isolatedButton.setText(Messages.RandomGraphAnyPage_isolated_nodes_caption);
        isolatedButton.setToolTipText(Messages.RandomGraphAnyPage_isolated_nodes_help);
        isolatedButton.setSelection(options.getProperty(GeneratorOptions.ISOLATED_NODES));
        
        // Event listeners
        selfLoopsButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.SELF_LOOPS, selfLoopsButton.getSelection());
            }
        });

        multiEdgesButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.MULTI_EDGES, multiEdgesButton.getSelection());
            }
        });

        cyclesButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.CYCLES, cyclesButton.getSelection());
            }
        });

        isolatedButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.ISOLATED_NODES, isolatedButton.getSelection());
            }
        });
    }
    
    /**
     * Check all values that can be configured through this wizard page.
     */
    private void validate() {
        if (options.getProperty(GeneratorOptions.NUMBER_OF_NODES_MIN)
                > options.getProperty(GeneratorOptions.NUMBER_OF_NODES_MAX)) {
            setErrorMessage(Messages.RandomGraphAnyPage_number_of_nodes_error);
            return;
        }
        if (options.getProperty(GeneratorOptions.MIN_OUTGOING_EDGES)
                > options.getProperty(GeneratorOptions.MAX_OUTGOING_EDGES)) {
            setErrorMessage(Messages.RandomGraphAnyPage_outgoing_edges_error);
            return;
        }
        setErrorMessage(null);
    }
    
}
