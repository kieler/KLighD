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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.core.kgraph.text.ui.random.GeneratorOptions;

/**
 * The options page for utility settings.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphOptionsPage extends WizardPage {
    
    /** the generator options. */
    private GeneratorOptions options;

    /**
     * Constructs a RandomGraphOptionsPage.
     * 
     * @param options the generator options
     */
    public RandomGraphOptionsPage(final GeneratorOptions options) {
        super("randomGraphUtilityPage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphUtilityPage_title);
        setDescription(Messages.RandomGraphUtilityPage_description);
        this.options = options;
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        createOptions(composite);
        setControl(composite);
    }

    // CHECKSTYLEOFF MagicNumber
    private void createOptions(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        composite.setLayoutData(gridData);
        
        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 10;
        composite.setLayout(layout);
        
        // Create and add the diffent option groups
        createHierarchyGroup(composite);
        createPortsGroup(composite);
        
        // Hypernode Chance option
        Label label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphUtilityPage_hypernode_caption);
        
        final Spinner hypernodeSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        hypernodeSpinner.setToolTipText(Messages.RandomGraphUtilityPage_hypernode_help);
        hypernodeSpinner.setValues((int) (options.getProperty(GeneratorOptions.HYPERNODE_CHANCE) * 100),
                0, 100, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        hypernodeSpinner.setLayoutData(gridData);
        
        hypernodeSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.HYPERNODE_CHANCE,
                        hypernodeSpinner.getSelection() / 100.0f);
            }
        });
    }

    /**
     * @param composite
     */
    private void createHierarchyGroup(final Composite composite) {
        GridData gridData;
        // Hierarchy Group
        Group hierarchyGroup = new Group(composite, SWT.NULL);
        hierarchyGroup.setText(Messages.RandomGraphUtilityPage_hierarchy_group_caption);
        hierarchyGroup.setLayout(new GridLayout(2, false));
        hierarchyGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
        
        // Allow Hierarchy button
        final Button hierarchyButton = new Button(hierarchyGroup, SWT.CHECK);
        hierarchyButton.setText(Messages.RandomGraphUtilityPage_hierarchy_enable_caption);
        hierarchyButton.setToolTipText(Messages.RandomGraphUtilityPage_hierarchy_enable_help);
        hierarchyButton.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false, 2, 1));
        hierarchyButton.setSelection(options.getProperty(GeneratorOptions.ENABLE_HIERARCHY));
        
        // Hierarchy Percentage option
        Label label = new Label(hierarchyGroup, SWT.NULL);
        label.setText(Messages.RandomGraphUtilityPage_hierarchy_caption);
        
        gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);
        
        final Spinner hierarchySpinner = new Spinner(hierarchyGroup, SWT.BORDER | SWT.SINGLE);
        hierarchySpinner.setToolTipText(Messages.RandomGraphUtilityPage_hierarchy_help);
        hierarchySpinner.setValues((int) (options.getProperty(GeneratorOptions.HIERARCHY_CHANCE) * 100),
                1, 100, 0, 1, 10);
        hierarchySpinner.setEnabled(options.getProperty(GeneratorOptions.ENABLE_HIERARCHY));
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        hierarchySpinner.setLayoutData(gridData);
        
        // Maximum Hierarchy Level option
        label = new Label(hierarchyGroup, SWT.NULL);
        label.setText(Messages.RandomGraphUtilityPage_max_hierarchy_caption);
        
        gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);
        
        final Spinner hierarchyLevelSpinner = new Spinner(hierarchyGroup, SWT.BORDER | SWT.SINGLE);
        hierarchyLevelSpinner.setToolTipText(Messages.RandomGraphUtilityPage_max_hierarchy_help);
        hierarchyLevelSpinner.setValues(options.getProperty(GeneratorOptions.MAX_HIERARCHY_LEVEL),
                1, Integer.MAX_VALUE, 0, 1, 10);
        hierarchyLevelSpinner.setEnabled(options.getProperty(GeneratorOptions.ENABLE_HIERARCHY));
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        hierarchyLevelSpinner.setLayoutData(gridData);
        
        // Hierarchy Nodes Factor option
        label = new Label(hierarchyGroup, SWT.NULL);
        label.setText(Messages.RandomGraphUtilityPage_hierarchy_factor_caption);
        
        gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);
        
        final Spinner hierarchyFactorSpinner = new Spinner(hierarchyGroup, SWT.BORDER | SWT.SINGLE);
        hierarchyFactorSpinner.setToolTipText(Messages.RandomGraphUtilityPage_hierarchy_factor_help);
        hierarchyFactorSpinner.setValues(
                (int) (options.getProperty(GeneratorOptions.HIERARCHY_NODES_FACTOR) * 100),
                0, Integer.MAX_VALUE, 2, 1, 10);
        hierarchyFactorSpinner.setEnabled(options.getProperty(GeneratorOptions.ENABLE_HIERARCHY));
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        hierarchyFactorSpinner.setLayoutData(gridData);
        
        // Cross-Hierarchy Edges option
        final Button hierarchyEdgesButton = new Button(hierarchyGroup, SWT.CHECK);
        hierarchyEdgesButton.setText(Messages.RandomGraphUtilityPage_hierarchy_edges_caption);
        hierarchyEdgesButton.setToolTipText(Messages.RandomGraphUtilityPage_hierarchy_edges_help);
        hierarchyEdgesButton.setSelection(options.getProperty(GeneratorOptions.CROSS_HIERARCHY_EDGES));
        hierarchyEdgesButton.setEnabled(options.getProperty(GeneratorOptions.ENABLE_HIERARCHY));
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false, 2, 1);
        gridData.horizontalIndent = 30;
        hierarchyEdgesButton.setLayoutData(gridData);
        
        // Event Listeners
        hierarchyButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                boolean hierarchyEnabled = hierarchyButton.getSelection();
                options.setProperty(GeneratorOptions.ENABLE_HIERARCHY, hierarchyEnabled);
                if (hierarchyEnabled) {
                    options.setProperty(GeneratorOptions.HIERARCHY_CHANCE,
                            hierarchySpinner.getSelection() / 100.0f);
                } else {
                    options.setProperty(GeneratorOptions.HIERARCHY_CHANCE, 0.0f);
                }
                hierarchySpinner.setEnabled(hierarchyEnabled);
                hierarchyLevelSpinner.setEnabled(hierarchyEnabled);
                hierarchyFactorSpinner.setEnabled(hierarchyEnabled);
                hierarchyEdgesButton.setEnabled(hierarchyEnabled);
            }
        });
        
        hierarchySpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.HIERARCHY_CHANCE,
                        hierarchySpinner.getSelection() / 100.0f);
            }
        });
        
        hierarchyLevelSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.MAX_HIERARCHY_LEVEL,
                        hierarchyLevelSpinner.getSelection());
            }
        });
        
        hierarchyFactorSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.HIERARCHY_NODES_FACTOR,
                        hierarchyFactorSpinner.getSelection() / 100f);
            }
        });
        
        hierarchyEdgesButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.CROSS_HIERARCHY_EDGES,
                        hierarchyEdgesButton.getSelection());
            }
        });
    }

    /**
     * @param composite
     */
    private void createPortsGroup(final Composite composite) {
        // Ports Group
        Group portsGroup = new Group(composite, SWT.NULL);
        portsGroup.setText(Messages.RandomGraphUtilityPage_ports_group_caption);
        portsGroup.setLayout(new GridLayout(2, false));
        portsGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
        
        // Enable Ports option
        final Button portsButton = new Button(portsGroup, SWT.CHECK);
        portsButton.setText(Messages.RandomGraphUtilityPage_ports_caption);
        portsButton.setToolTipText(Messages.RandomGraphUtilityPage_ports_help);
        portsButton.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false, 2, 1));
        portsButton.setSelection(options.getProperty(GeneratorOptions.ENABLE_PORTS));
        
        // Use existing ports chance
        Label label = new Label(portsGroup, SWT.NULL);
        label.setText(Messages.RandomGraphUtilityPage_ports_reuse_caption);
        
        GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);
        
        final Spinner useExistingPortsChanceSpinner = new Spinner(portsGroup, SWT.BORDER | SWT.SINGLE);
        useExistingPortsChanceSpinner.setToolTipText(Messages.RandomGraphUtilityPage_port_reuse_help);
        useExistingPortsChanceSpinner.setValues(
                (int) (options.getProperty(GeneratorOptions.USE_EXISTING_PORTS_CHANCE) * 100),
                0, 100, 0, 1, 10);
        useExistingPortsChanceSpinner.setEnabled(options.getProperty(GeneratorOptions.ENABLE_PORTS));
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        useExistingPortsChanceSpinner.setLayoutData(gridData);

        // Event listeners
        portsButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                boolean enablePorts = portsButton.getSelection();
                options.setProperty(GeneratorOptions.ENABLE_PORTS, enablePorts);
                useExistingPortsChanceSpinner.setEnabled(enablePorts);
            }
        });
        
        useExistingPortsChanceSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.USE_EXISTING_PORTS_CHANCE,
                        useExistingPortsChanceSpinner.getSelection() / 100.0);
            }
        });
    }
    
}
