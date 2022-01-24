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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.kgraph.text.ui.random.wizard;

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

import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions;
import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions.RandVal;

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
        createNodesGroup(composite);
        createHierarchyGroup(composite);
        createPortsGroup(composite);
    }

    /**
     * Create group for nodes.
     *
     * @param composite the parent composite
     */
    private void createNodesGroup(final Composite composite) {
        Group nodesGroup = new Group(composite, SWT.NONE);
        nodesGroup.setText(Messages.RandomGraphUtilityPage_nodes_group_caption);
        nodesGroup.setLayout(new GridLayout(2, false));
        nodesGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));

        // Set node size button
        final Button setSizeButton = new Button(nodesGroup, SWT.CHECK);
        setSizeButton.setText(Messages.RandomGraphUtilityPage_node_size_caption);
        setSizeButton.setToolTipText(Messages.RandomGraphUtilityPage_node_size_help);
        setSizeButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
        setSizeButton.setSelection(options.getProperty(GeneratorOptions.SET_NODE_SIZE));

        // Node size: min. / max. width and height
        Label label = new Label(nodesGroup, SWT.NONE);
        label.setText(Messages.RandomGraphUtilityPage_node_width_caption);
        GridData gridData = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);

        Composite widthComposite = new Composite(nodesGroup, SWT.NONE);
        gridData = new GridData(SWT.LEFT, SWT.TOP, true, false, 2, 1);
        gridData.horizontalIndent = 50;
        widthComposite.setLayoutData(gridData);

        GridLayout gridLayout = new GridLayout(4, false);
        gridLayout.marginHeight = 0;
        widthComposite.setLayout(gridLayout);

        label = new Label(widthComposite, SWT.NONE);
        label.setText(Messages.RandomGraphUtilityPage_node_size_min);
        final Spinner widthMinSpinner = new Spinner(widthComposite, SWT.BORDER | SWT.SINGLE);
        widthMinSpinner.setToolTipText(Messages.RandomGraphUtilityPage_node_width_min_help);
        widthMinSpinner.setValues((int) Math.round(options.getProperty(GeneratorOptions.NODE_WIDTH).min()),
                0, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 80;
        widthMinSpinner.setLayoutData(gridData);
        widthMinSpinner.setEnabled(options.getProperty(GeneratorOptions.SET_NODE_SIZE));

        label = new Label(widthComposite, SWT.NONE);
        label.setText(Messages.RandomGraphUtilityPage_node_size_max);
        final Spinner widthMaxSpinner = new Spinner(widthComposite, SWT.BORDER | SWT.SINGLE);
        widthMaxSpinner.setToolTipText(Messages.RandomGraphUtilityPage_node_width_max_help);
        widthMaxSpinner.setValues((int) Math.round(options.getProperty(GeneratorOptions.NODE_WIDTH).max()),
                0, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 80;
        widthMaxSpinner.setLayoutData(gridData);
        widthMaxSpinner.setEnabled(options.getProperty(GeneratorOptions.SET_NODE_SIZE));

        label = new Label(nodesGroup, SWT.NONE);
        label.setText(Messages.RandomGraphUtilityPage_node_height_caption);
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
        gridData.horizontalIndent = 30;
        gridData.verticalIndent = 10;
        label.setLayoutData(gridData);

        Composite heightComposite = new Composite(nodesGroup, SWT.NONE);
        gridData = new GridData(SWT.LEFT, SWT.TOP, true, false, 2, 1);
        gridData.horizontalIndent = 50;
        heightComposite.setLayoutData(gridData);

        gridLayout = new GridLayout(4, false);
        gridLayout.marginHeight = 0;
        heightComposite.setLayout(gridLayout);

        label = new Label(heightComposite, SWT.NONE);
        label.setText(Messages.RandomGraphUtilityPage_node_size_min);
        final Spinner heightMinSpinner = new Spinner(heightComposite, SWT.BORDER | SWT.SINGLE);
        heightMinSpinner.setToolTipText(Messages.RandomGraphUtilityPage_node_height_min_help);
        heightMinSpinner.setValues((int) Math.round(options.getProperty(GeneratorOptions.NODE_HEIGHT).min()),
                0, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 80;
        heightMinSpinner.setLayoutData(gridData);
        heightMinSpinner.setEnabled(options.getProperty(GeneratorOptions.SET_NODE_SIZE));

        label = new Label(heightComposite, SWT.NONE);
        label.setText(Messages.RandomGraphUtilityPage_node_size_max);
        final Spinner heightMaxSpinner = new Spinner(heightComposite, SWT.BORDER | SWT.SINGLE);
        heightMaxSpinner.setToolTipText(Messages.RandomGraphUtilityPage_node_height_max_help);
        heightMaxSpinner.setValues((int) Math.round(options.getProperty(GeneratorOptions.NODE_HEIGHT).max()),
                0, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 80;
        heightMaxSpinner.setLayoutData(gridData);
        heightMaxSpinner.setEnabled(options.getProperty(GeneratorOptions.SET_NODE_SIZE));

        // Enable node labels option
        final Button nodeLabelsButton = new Button(nodesGroup, SWT.CHECK);
        nodeLabelsButton.setText(Messages.RandomGraphUtilityPage_node_labels_caption);
        nodeLabelsButton.setToolTipText(Messages.RandomGraphUtilityPage_node_labels_help);
        nodeLabelsButton.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false, 2, 1));
        nodeLabelsButton.setSelection(options.getProperty(GeneratorOptions.CREATE_NODE_LABELS));

        // Hypernode Chance option
        label = new Label(nodesGroup, SWT.NONE);
        label.setText(Messages.RandomGraphUtilityPage_hypernode_caption);

        final Spinner hypernodeSpinner = new Spinner(nodesGroup, SWT.BORDER | SWT.SINGLE);
        hypernodeSpinner.setToolTipText(Messages.RandomGraphUtilityPage_hypernode_help);
        hypernodeSpinner.setValues((int) (options.getProperty(GeneratorOptions.HYPERNODE_CHANCE) * 100),
                0, 100, 0, 1, 10);

        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 80;
        hypernodeSpinner.setLayoutData(gridData);

        // Event Listeners
        setSizeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                boolean setSizeEnabled = setSizeButton.getSelection();
                options.setProperty(GeneratorOptions.SET_NODE_SIZE, setSizeEnabled);
                widthMinSpinner.setEnabled(setSizeEnabled);
                widthMaxSpinner.setEnabled(setSizeEnabled);
                heightMinSpinner.setEnabled(setSizeEnabled);
                heightMaxSpinner.setEnabled(setSizeEnabled);
            }
        });

        RandVal nodeWidth = RandVal.minMax(0, 0);
        options.setProperty(GeneratorOptions.NODE_WIDTH, nodeWidth);
        widthMinSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                nodeWidth.setMin(widthMinSpinner.getSelection());
            }
        });

        widthMaxSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                nodeWidth.setMax(widthMaxSpinner.getSelection());
            }
        });

        RandVal nodeHeight = RandVal.minMax(0, 0);
        options.setProperty(GeneratorOptions.NODE_HEIGHT, nodeHeight);
        heightMinSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                nodeHeight.setMin(heightMinSpinner.getSelection());
            }
        });

        heightMaxSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                nodeHeight.setMax(heightMaxSpinner.getSelection());
            }
        });

        nodeLabelsButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.CREATE_NODE_LABELS,
                        nodeLabelsButton.getSelection());
            }
        });

        hypernodeSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.HYPERNODE_CHANCE,
                        hypernodeSpinner.getSelection() / 100.0f);
            }
        });
    }

    /**
     * Create group for hierarchy.
     *
     * @param composite the parent composite
     */
    private void createHierarchyGroup(final Composite composite) {
        Group hierarchyGroup = new Group(composite, SWT.NONE);
        hierarchyGroup.setText(Messages.RandomGraphUtilityPage_hierarchy_group_caption);
        hierarchyGroup.setLayout(new GridLayout(2, false));
        hierarchyGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));

        // Allow Hierarchy button
        final Button hierarchyButton = new Button(hierarchyGroup, SWT.CHECK);
        hierarchyButton.setText(Messages.RandomGraphUtilityPage_hierarchy_enable_caption);
        hierarchyButton.setToolTipText(Messages.RandomGraphUtilityPage_hierarchy_enable_help);
        hierarchyButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
        hierarchyButton.setSelection(options.getProperty(GeneratorOptions.ENABLE_HIERARCHY));

        // Hierarchy Percentage option
        Label label = new Label(hierarchyGroup, SWT.NONE);
        label.setText(Messages.RandomGraphUtilityPage_hierarchy_caption);

        GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);

        final Spinner hierarchySpinner = new Spinner(hierarchyGroup, SWT.BORDER | SWT.SINGLE);
        hierarchySpinner.setToolTipText(Messages.RandomGraphUtilityPage_hierarchy_help);
        hierarchySpinner.setValues((int) (options.getProperty(GeneratorOptions.HIERARCHY_CHANCE) * 100),
                1, 100, 0, 1, 10);
        hierarchySpinner.setEnabled(options.getProperty(GeneratorOptions.ENABLE_HIERARCHY));

        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 80;
        hierarchySpinner.setLayoutData(gridData);

        // Maximum Hierarchy Level option
        label = new Label(hierarchyGroup, SWT.NONE);
        label.setText(Messages.RandomGraphUtilityPage_max_hierarchy_caption);

        gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);

        final Spinner hierarchyLevelSpinner = new Spinner(hierarchyGroup, SWT.BORDER | SWT.SINGLE);
        hierarchyLevelSpinner.setToolTipText(Messages.RandomGraphUtilityPage_max_hierarchy_help);
        hierarchyLevelSpinner.setValues(options.getProperty(GeneratorOptions.MAX_HIERARCHY_LEVEL).defaultInt(),
                1, Integer.MAX_VALUE, 0, 1, 10);
        hierarchyLevelSpinner.setEnabled(options.getProperty(GeneratorOptions.ENABLE_HIERARCHY));

        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 80;
        hierarchyLevelSpinner.setLayoutData(gridData);

        // Hierarchy Nodes Factor option
        label = new Label(hierarchyGroup, SWT.NONE);
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

        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 80;
        hierarchyFactorSpinner.setLayoutData(gridData);

        // Cross-Hierarchy Edges option
        final Button hierarchyEdgesButton = new Button(hierarchyGroup, SWT.CHECK);
        hierarchyEdgesButton.setText(Messages.RandomGraphUtilityPage_hierarchy_edges_caption);
        hierarchyEdgesButton.setToolTipText(Messages.RandomGraphUtilityPage_hierarchy_edges_help);
        hierarchyEdgesButton.setSelection(options.getProperty(GeneratorOptions.CROSS_HIERARCHY_EDGES));
        hierarchyEdgesButton.setEnabled(options.getProperty(GeneratorOptions.ENABLE_HIERARCHY));

        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
        gridData.horizontalIndent = 30;
        hierarchyEdgesButton.setLayoutData(gridData);

        // Event Listeners
        hierarchyButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                boolean hierarchyEnabled = hierarchyButton.getSelection();
                options.setProperty(GeneratorOptions.ENABLE_HIERARCHY, hierarchyEnabled);
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
                        RandVal.exact(hierarchyLevelSpinner.getSelection()));
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
     * Create group for ports.
     *
     * @param composite the parent composite
     */
    private void createPortsGroup(final Composite composite) {
        Group portsGroup = new Group(composite, SWT.NULL);
        portsGroup.setText(Messages.RandomGraphUtilityPage_ports_group_caption);
        portsGroup.setLayout(new GridLayout(2, false));
        portsGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));

        // Enable Ports option
        final Button portsButton = new Button(portsGroup, SWT.CHECK);
        portsButton.setText(Messages.RandomGraphUtilityPage_ports_caption);
        portsButton.setToolTipText(Messages.RandomGraphUtilityPage_ports_help);
        portsButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
        portsButton.setSelection(options.getProperty(GeneratorOptions.ENABLE_PORTS));

        // Use existing ports chance
        Label label = new Label(portsGroup, SWT.NONE);
        label.setText(Messages.RandomGraphUtilityPage_ports_reuse_caption);

        GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);

        final Spinner useExistingPortsChanceSpinner = new Spinner(portsGroup, SWT.BORDER | SWT.SINGLE);
        useExistingPortsChanceSpinner.setToolTipText(Messages.RandomGraphUtilityPage_port_reuse_help);
        useExistingPortsChanceSpinner.setValues(
                (int) (options.getProperty(GeneratorOptions.USE_EXISTING_PORTS_CHANCE).defaultVal() * 100),
                0, 100, 0, 1, 10);
        useExistingPortsChanceSpinner.setEnabled(options.getProperty(GeneratorOptions.ENABLE_PORTS));

        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 80;
        useExistingPortsChanceSpinner.setLayoutData(gridData);

        // Set port size button
        final Button setSizeButton = new Button(portsGroup, SWT.CHECK);
        setSizeButton.setText(Messages.RandomGraphUtilityPage_port_size_caption);
        setSizeButton.setToolTipText(Messages.RandomGraphUtilityPage_port_size_help);
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
        gridData.horizontalIndent = 30;
        setSizeButton.setLayoutData(gridData);
        setSizeButton.setSelection(options.getProperty(GeneratorOptions.SET_PORT_SIZE));
        setSizeButton.setEnabled(options.getProperty(GeneratorOptions.ENABLE_PORTS));

        // Enable port labels option
        final Button portLabelsButton = new Button(portsGroup, SWT.CHECK);
        portLabelsButton.setText(Messages.RandomGraphUtilityPage_port_labels_caption);
        portLabelsButton.setToolTipText(Messages.RandomGraphUtilityPage_port_labels_help);
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
        gridData.horizontalIndent = 30;
        portLabelsButton.setLayoutData(gridData);
        portLabelsButton.setSelection(options.getProperty(GeneratorOptions.CREATE_PORT_LABELS));
        portLabelsButton.setEnabled(options.getProperty(GeneratorOptions.ENABLE_PORTS));

        // Event listeners
        portsButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                boolean enablePorts = portsButton.getSelection();
                options.setProperty(GeneratorOptions.ENABLE_PORTS, enablePorts);
                useExistingPortsChanceSpinner.setEnabled(enablePorts);
                portLabelsButton.setEnabled(enablePorts);
                setSizeButton.setEnabled(enablePorts);
            }
        });

        useExistingPortsChanceSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                options.setProperty(GeneratorOptions.USE_EXISTING_PORTS_CHANCE,
                        RandVal.exact(useExistingPortsChanceSpinner.getSelection() / 100.0f));
            }
        });

        setSizeButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.SET_PORT_SIZE,
                        setSizeButton.getSelection());
            }
        });

        portLabelsButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                options.setProperty(GeneratorOptions.CREATE_PORT_LABELS,
                        portLabelsButton.getSelection());
            }
        });
    }

}
