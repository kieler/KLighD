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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import de.cau.cs.kieler.kgraph.text.ui.random.GeneratorOptions;
import de.cau.cs.kieler.kgraph.text.ui.random.GeneratorOptions.EdgeDetermination;

/**
 * The options page for the custom graph type.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphCustomPage extends AbstractRandomGraphPage {
    
    /**
     * Constructs a RandomGraphCustomPage.
     * 
     * @param options the generator options
     */
    public RandomGraphCustomPage(final GeneratorOptions options) {
        super("randomGraphAnyPage", options); //$NON-NLS-1$
        setTitle(Messages.RandomGraphAnyPage_title);
        setDescription(Messages.RandomGraphAnyPage_description);
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
        createEdgeGroup(composite, EnumSet.allOf(EdgeDetermination.class));
        createGraphPropertiesGroup(composite);
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
        
        // Self Loops
        final Button selfLoopsButton = new Button(optionsGroup, SWT.CHECK);
        selfLoopsButton.setText(Messages.RandomGraphAnyPage_self_loops_caption);
        selfLoopsButton.setToolTipText(Messages.RandomGraphAnyPage_self_loops_help);
        selfLoopsButton.setSelection(getOptions().getProperty(GeneratorOptions.SELF_LOOPS));
        
        // Multi Edges
        final Button multiEdgesButton = new Button(optionsGroup, SWT.CHECK);
        multiEdgesButton.setText(Messages.RandomGraphAnyPage_multi_edges_caption);
        multiEdgesButton.setToolTipText(Messages.RandomGraphAnyPage_multi_edges_help);
        multiEdgesButton.setSelection(getOptions().getProperty(GeneratorOptions.MULTI_EDGES));
        
        // Edge labels
        final Button edgeLabelButton = new Button(optionsGroup, SWT.CHECK);
        edgeLabelButton.setText(Messages.RandomGraphCustomPage_edge_labels_caption);
        edgeLabelButton.setToolTipText(Messages.RandomGraphCustomPage_edge_labels_help);
        edgeLabelButton.setSelection(getOptions().getProperty(GeneratorOptions.EDGE_LABELS));
        
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
        
        // Event listeners
        selfLoopsButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                getOptions().setProperty(GeneratorOptions.SELF_LOOPS, selfLoopsButton.getSelection());
            }
        });

        multiEdgesButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                getOptions().setProperty(GeneratorOptions.MULTI_EDGES, multiEdgesButton.getSelection());
            }
        });
        
        edgeLabelButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                getOptions().setProperty(GeneratorOptions.EDGE_LABELS, edgeLabelButton.getSelection());
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
    
}
