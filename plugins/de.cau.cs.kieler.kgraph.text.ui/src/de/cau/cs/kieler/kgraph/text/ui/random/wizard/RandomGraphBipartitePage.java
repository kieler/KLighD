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
package de.cau.cs.kieler.kgraph.text.ui.random.wizard;

import java.util.EnumSet;

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

import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions;
import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions.EdgeDetermination;
import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions.RandVal;

/**
 * The options page for the bipartite graph type.
 * 
 * @author msp
 */
public class RandomGraphBipartitePage extends AbstractRandomGraphPage {
    
    /**
     * Constructs a RandomGraphBipartitePage.
     * 
     * @param options the generator options
     */
    public RandomGraphBipartitePage(final GeneratorOptions options) {
        super("randomGraphBipartitePage", options); //$NON-NLS-1$
        setTitle(Messages.RandomGraphBipartitePage_title);
        setDescription(Messages.RandomGraphBipartitePage_description);
    }

    // CHECKSTYLEOFF MagicNumber
    
    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        setControl(composite);
        
        GridLayout layout = new GridLayout(1, false);
        layout.verticalSpacing = 10;
        composite.setLayout(layout);
        
        // Create the different groups
        createNodesGroup(composite);
        createEdgeGroup(composite, EnumSet.of(EdgeDetermination.ABSOLUTE, EdgeDetermination.RELATIVE,
                EdgeDetermination.DENSITY));
        createGraphPropertiesGroup(composite);
        createPartitionGroup(composite);
    }

    /**
     * Creates the group holding graph property options and adds it to the given composite.
     * 
     * @param composite the composite to add the group to.
     */
    private void createGraphPropertiesGroup(final Composite composite) {
        Group optionsGroup = new Group(composite, SWT.NONE);
        optionsGroup.setText(Messages.RandomGraphAnyPage_options_group_caption);
        optionsGroup.setLayout(new RowLayout(SWT.VERTICAL));
        optionsGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        
        // Multi Edges
        final Button multiEdgesButton = new Button(optionsGroup, SWT.CHECK);
        multiEdgesButton.setText(Messages.RandomGraphAnyPage_multi_edges_caption);
        multiEdgesButton.setToolTipText(Messages.RandomGraphAnyPage_multi_edges_help);
        multiEdgesButton.setSelection(getOptions().getProperty(GeneratorOptions.MULTI_EDGES));
        
        // Cycles
        final Button cyclesButton = new Button(optionsGroup, SWT.CHECK);
        cyclesButton.setText(Messages.RandomGraphAnyPage_cycles_caption);
        cyclesButton.setToolTipText(Messages.RandomGraphAnyPage_cycles_help);
        cyclesButton.setSelection(getOptions().getProperty(GeneratorOptions.CYCLES));
        
        // Isolated Nodes
        final Button isolatedButton = new Button(optionsGroup, SWT.CHECK);
        isolatedButton.setText(Messages.RandomGraphAnyPage_isolated_nodes_caption);
        isolatedButton.setToolTipText(Messages.RandomGraphAnyPage_isolated_nodes_help);
        isolatedButton.setSelection(getOptions().getProperty(GeneratorOptions.ISOLATED_NODES));

        multiEdgesButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                getOptions().setProperty(GeneratorOptions.MULTI_EDGES, multiEdgesButton.getSelection());
            }
        });

        cyclesButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                getOptions().setProperty(GeneratorOptions.CYCLES, cyclesButton.getSelection());
            }
        });

        isolatedButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                getOptions().setProperty(GeneratorOptions.ISOLATED_NODES, isolatedButton.getSelection());
            }
        });
    }
    
    /**
     * Creates the group holding partition options and adds it to the given composite.
     * 
     * @param composite the composite to add the group to
     */
    private void createPartitionGroup(final Composite composite) {
        Group partitionGroup = new Group(composite, SWT.NONE);
        partitionGroup.setText(Messages.RandomGraphBipartitePage_partition_group_caption);
        partitionGroup.setLayout(new GridLayout(2, false));
        partitionGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        
        // Minimum partition fraction
        Label label = new Label(partitionGroup, SWT.NONE);
        label.setText(Messages.RandomGraphBipartitePage_partition_min);
        final Spinner partitionMinSpinner = new Spinner(partitionGroup, SWT.BORDER | SWT.SINGLE);
        partitionMinSpinner.setToolTipText(Messages.RandomGraphBipartitePage_partition_min_help);

        partitionMinSpinner.setValues(
                (int) (getOptions().getProperty(GeneratorOptions.PARTITION_FRAC).min() * 100),
                1, 99, 2, 1, 10);
        GridData gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        partitionMinSpinner.setLayoutData(gridData);

        // Maximum partition fraction
        label = new Label(partitionGroup, SWT.NONE);
        label.setText(Messages.RandomGraphBipartitePage_partition_max);
        final Spinner partitionMaxSpinner = new Spinner(partitionGroup, SWT.BORDER | SWT.SINGLE);
        partitionMaxSpinner.setToolTipText(Messages.RandomGraphBipartitePage_partition_max_help);
        partitionMaxSpinner.setValues(
                (int) (getOptions().getProperty(GeneratorOptions.PARTITION_FRAC).max() * 100),
                1, 99, 2, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        partitionMaxSpinner.setLayoutData(gridData);
        
        // Event Listeners
        RandVal partitionFrac = RandVal.minMax(0, 0);
        partitionMinSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                partitionFrac.setMin(partitionMinSpinner.getSelection() / 100);
            }
        });

        partitionMaxSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                partitionFrac.setMax(partitionMaxSpinner.getSelection() / 100);
            }
        });
    }
}
