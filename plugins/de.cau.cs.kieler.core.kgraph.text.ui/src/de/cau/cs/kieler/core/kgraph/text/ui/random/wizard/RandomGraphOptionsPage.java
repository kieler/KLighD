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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.core.kgraph.text.ui.internal.KGraphActivator;
import de.cau.cs.kieler.core.kgraph.text.ui.random.RandomGraphGenerator;

/**
 * The options page for utility settings.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphOptionsPage extends WizardPage {
    
    /** ID of the Enable Hierarchy option. */
    private static final String ENABLE_HIERARCHY = "basic.enableHierarchy"; //$NON-NLS-1$
    /**
     * Default percentage of hierarchichal nodes. We cannot take the default value defined in
     * {@link RandomGraphGenerator#HIERARCHY_CHANCE} since that can be 0, which we don't allow
     * here.
     */
    private static final int DEFAULT_HIERARCHY_CHANCE = 5;
    /** A hundred percent as a float value. */
    private static final float HUNDRED_PERCENT = 100.0f;
    
    /** if hierarchy is enabled. */
    private boolean hierarchyEnabled;
    /** the selected hierarchy chance. */
    private int hierarchyChance;
    /** the selected maximum hierarchy level. */
    private int maxHierarchyLevel;
    /** the selected hierarchy nodes factor. */
    private float hierarchyNodesFactor;
    /** the selected hypernode chance. */
    private int hypernodeChance;
    /** the selected port usage. */
    private boolean ports;
    /** chance for edges to use existing ports. */
    private int useExistingPortsChance;
    /** whether cross-hierarchy edges are allowed. */
    private boolean crossHierarchyEdges;

    /**
     * Constructs a RandomGraphOptionsPage.
     */
    public RandomGraphOptionsPage() {
        super("randomGraphUtilityPage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphUtilityPage_title);
        setDescription(Messages.RandomGraphUtilityPage_description);
        setDefaultPreferences();
        loadPreferences();
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
        hypernodeSpinner.setValues(hypernodeChance, 0, 100, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        hypernodeSpinner.setLayoutData(gridData);
        
        hypernodeSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                hypernodeChance = hypernodeSpinner.getSelection();
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
        
        // Hierarchy Percentage option
        Label label = new Label(hierarchyGroup, SWT.NULL);
        label.setText(Messages.RandomGraphUtilityPage_hierarchy_caption);
        
        gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);
        
        final Spinner hierarchySpinner = new Spinner(hierarchyGroup, SWT.BORDER | SWT.SINGLE);
        hierarchySpinner.setToolTipText(Messages.RandomGraphUtilityPage_hierarchy_help);
        hierarchySpinner.setValues(hierarchyChance, 1, 100, 0, 1, 10);
        hierarchySpinner.setEnabled(hierarchyEnabled);
        
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
        hierarchyLevelSpinner.setValues(maxHierarchyLevel, 1, Integer.MAX_VALUE, 0, 1, 10);
        hierarchyLevelSpinner.setEnabled(hierarchyEnabled);
        
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
        hierarchyFactorSpinner.setValues((int) (hierarchyNodesFactor * 100), 0, Integer.MAX_VALUE,
                2, 1, 10);
        hierarchyFactorSpinner.setEnabled(hierarchyEnabled);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        hierarchyFactorSpinner.setLayoutData(gridData);
        
        // Cross-Hierarchy Edges option
        final Button hierarchyEdgesButton = new Button(hierarchyGroup, SWT.CHECK);
        hierarchyEdgesButton.setText(Messages.RandomGraphUtilityPage_hierarchy_edges_caption);
        hierarchyEdgesButton.setToolTipText(Messages.RandomGraphUtilityPage_hierarchy_edges_help);
        hierarchyEdgesButton.setSelection(crossHierarchyEdges);
        hierarchyEdgesButton.setEnabled(hierarchyEnabled);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false, 2, 1);
        gridData.horizontalIndent = 30;
        hierarchyEdgesButton.setLayoutData(gridData);
        
        // Event Listeners
        hierarchyButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                hierarchyEnabled = hierarchyButton.getSelection();
                
                hierarchySpinner.setEnabled(hierarchyEnabled);
                hierarchyLevelSpinner.setEnabled(hierarchyEnabled);
                hierarchyFactorSpinner.setEnabled(hierarchyEnabled);
                hierarchyEdgesButton.setEnabled(hierarchyEnabled);
            }
        });
        
        hierarchySpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                hierarchyChance = hierarchySpinner.getSelection();
            }
        });
        
        hierarchyLevelSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                maxHierarchyLevel = hierarchyLevelSpinner.getSelection();
            }
        });
        
        hierarchyFactorSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                hierarchyNodesFactor = ((float) hierarchyFactorSpinner.getSelection()) / 100f;
            }
        });
        
        hierarchyEdgesButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(final SelectionEvent e) {
                crossHierarchyEdges = hierarchyEdgesButton.getSelection();
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
                // do nothing
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
        portsButton.setSelection(ports);
        
        // Use existing ports chance
        Label label = new Label(portsGroup, SWT.NULL);
        label.setText(Messages.RandomGraphUtilityPage_ports_reuse_caption);
        
        GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gridData.horizontalIndent = 30;
        label.setLayoutData(gridData);
        
        final Spinner useExistingPortsChanceSpinner = new Spinner(portsGroup, SWT.BORDER | SWT.SINGLE);
        useExistingPortsChanceSpinner.setToolTipText(Messages.RandomGraphUtilityPage_port_reuse_help);
        useExistingPortsChanceSpinner.setValues(useExistingPortsChance, 0, 100, 0, 1, 10);
        useExistingPortsChanceSpinner.setEnabled(ports);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        useExistingPortsChanceSpinner.setLayoutData(gridData);

        // Event listeners
        portsButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                ports = portsButton.getSelection();
                useExistingPortsChanceSpinner.setEnabled(ports);
            }
        });
        
        useExistingPortsChanceSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                useExistingPortsChance = useExistingPortsChanceSpinner.getSelection();
            }
        });
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Saves the selected options to the preference store.
     */
    public void savePreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        preferenceStore.setValue(ENABLE_HIERARCHY,
                hierarchyEnabled);
        preferenceStore.setValue(RandomGraphGenerator.HIERARCHY_CHANCE.getId(),
                hierarchyChance);
        preferenceStore.setValue(RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getId(),
                maxHierarchyLevel);
        preferenceStore.setValue(RandomGraphGenerator.HIERARCHY_NODES_FACTOR.getId(),
                hierarchyNodesFactor);
        preferenceStore.setValue(RandomGraphGenerator.HYPERNODE_CHANCE.getId(),
                hypernodeChance);
        preferenceStore.setValue(RandomGraphGenerator.PORTS.getId(),
                ports);
        preferenceStore.setValue(RandomGraphGenerator.USE_EXISTING_PORTS_CHANCE.getId(),
                useExistingPortsChance);
        preferenceStore.setValue(RandomGraphGenerator.CROSS_HIERARCHY_EDGES.getId(),
                crossHierarchyEdges);
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        hierarchyEnabled = preferenceStore.getBoolean(
                ENABLE_HIERARCHY);
        hierarchyChance = preferenceStore.getInt(
                RandomGraphGenerator.HIERARCHY_CHANCE.getId());
        maxHierarchyLevel = preferenceStore.getInt(
                RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getId());
        hierarchyNodesFactor = preferenceStore.getFloat(
                RandomGraphGenerator.HIERARCHY_NODES_FACTOR.getId());
        hypernodeChance = preferenceStore.getInt(
                RandomGraphGenerator.HYPERNODE_CHANCE.getId());
        ports = preferenceStore.getBoolean(
                RandomGraphGenerator.PORTS.getId());
        useExistingPortsChance = preferenceStore.getInt(
                RandomGraphGenerator.USE_EXISTING_PORTS_CHANCE.getId());
        crossHierarchyEdges = preferenceStore.getBoolean(
                RandomGraphGenerator.CROSS_HIERARCHY_EDGES.getId());
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        preferenceStore.setDefault(ENABLE_HIERARCHY,
                false);
        preferenceStore.setDefault(RandomGraphGenerator.HIERARCHY_CHANCE.getId(),
                DEFAULT_HIERARCHY_CHANCE);
        preferenceStore.setDefault(RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getId(),
                RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.HIERARCHY_NODES_FACTOR.getId(),
                RandomGraphGenerator.HIERARCHY_NODES_FACTOR.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.HYPERNODE_CHANCE.getId(),
                (int) (RandomGraphGenerator.HYPERNODE_CHANCE.getDefault() * HUNDRED_PERCENT));
        preferenceStore.setDefault(RandomGraphGenerator.PORTS.getId(),
                RandomGraphGenerator.PORTS.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.USE_EXISTING_PORTS_CHANCE.getId(),
                (int) (RandomGraphGenerator.USE_EXISTING_PORTS_CHANCE.getDefault() * HUNDRED_PERCENT));
        preferenceStore.setDefault(RandomGraphGenerator.CROSS_HIERARCHY_EDGES.getId(),
                RandomGraphGenerator.CROSS_HIERARCHY_EDGES.getDefault());
    }
    
    /**
     * Checks if hierarchy is enabled.
     * 
     * @return {@code true} if hierarchy is enabled.
     */
    public boolean isHierarchyEnabled() {
        return hierarchyEnabled;
    }

    /**
     * Returns the hierarchy chance.
     * 
     * @return the hierarchy chance
     */
    public float getHierarchyChance() {
        return hierarchyChance / HUNDRED_PERCENT;
    }

    /**
     * Returns the maximum hierarchy level.
     * 
     * @return the maximum hierarchy level
     */
    public int getMaximumHierarchyLevel() {
        return maxHierarchyLevel;
    }

    /**
     * Returns the factor for computing the number of nodes in a compound node.
     * 
     * @return the factor
     */
    public float getHierarchyNodesFactor() {
        return hierarchyNodesFactor;
    }

    /**
     * Returns the hypernode chance.
     * 
     * @return the hypernode chance
     */
    public float getHypernodeChance() {
        return hypernodeChance / HUNDRED_PERCENT;
    }

    /**
     * Returns whether ports have to be used to connect nodes.
     * 
     * @return true if ports have to be used; false else
     */
    public boolean getPorts() {
        return ports;
    }
    
    /**
     * Returns the chance for edges to use existing ports rather than newly created ones.
     * 
     * @return the chance to use existing ports.
     */
    public float getUseExistingPortsChance() {
        return useExistingPortsChance / HUNDRED_PERCENT;
    }
    
    /**
     * Returns whether cross-hierarchy edges are allowed.
     * 
     * @return true if cross-hierarchy edges are allowed
     */
    public boolean getCrossHierarchyEdges() {
        return crossHierarchyEdges;
    }
    
}
