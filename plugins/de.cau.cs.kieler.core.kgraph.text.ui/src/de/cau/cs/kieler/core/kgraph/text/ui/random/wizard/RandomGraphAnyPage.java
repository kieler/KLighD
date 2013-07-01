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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.core.kgraph.text.ui.internal.KGraphActivator;
import de.cau.cs.kieler.core.kgraph.text.ui.random.RandomGraphGenerator;

/**
 * The options page for the custom graph type.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphAnyPage extends WizardPage {
    
    /** the minimum number of nodes. */
    private int numberOfNodesMin;
    /** the maximum number of nodes. */
    private int numberOfNodesMax;
    /** the selected edge determination. */
    private RandomGraphGenerator.EdgeDetermination edgeDetermination;
    /** the selected number of edges. */
    private int numberOfEdges;
    /** variance for the number of edges. */
    private int edgesVariance;
    /** the selected density. */
    private int density;
    /** variance for the density. */
    private int densityVariance;
    /** the selected relative edge number. */
    private int relativeEdgeNumber;
    /** variance for the relative edge number. */
    private int relativeEdgeNumberVariance;
    /** the selected minimum number of outgoing edges. */
    private int minOutgoingEdges;
    /** the selected maximum number of outgoing edges. */
    private int maxOutgoingEdges;
    /** the selected self loop allowance. */
    private boolean selfLoops;
    /** the selected multi edges allowance. */
    private boolean multiEdges;
    /** the selected cycle allowance. */
    private boolean cycles;
    /** the selected isolated nodes allowance. */
    private boolean isolatedNodes;

    /**
     * Constructs a RandomGraphAnyPage.
     */
    public RandomGraphAnyPage() {
        super("randomGraphAnyPage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphAnyPage_title);
        setDescription(Messages.RandomGraphAnyPage_description);
        setDefaultPreferences();
        loadPreferences();
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
        nodesMinSpinner.setValues(numberOfNodesMin, 1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        nodesMinSpinner.setLayoutData(gridData);
        
        label = new Label(nodesGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_number_of_nodes_max);
        
        final Spinner nodesMaxSpinner = new Spinner(nodesGroup, SWT.BORDER | SWT.SINGLE);
        nodesMaxSpinner.setToolTipText(Messages.RandomGraphAnyPage_number_of_nodes_max_help);
        nodesMaxSpinner.setValues(numberOfNodesMax, 1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        nodesMaxSpinner.setLayoutData(gridData);
        
        // Event Listeners
        nodesMinSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfNodesMin = nodesMinSpinner.getSelection();
                if (numberOfNodesMax < numberOfNodesMin) {
                    nodesMaxSpinner.setSelection(numberOfNodesMin);
                }
            }
        });

        nodesMaxSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfNodesMax = nodesMaxSpinner.getSelection();
                if (numberOfNodesMin > numberOfNodesMax) {
                    nodesMinSpinner.setSelection(numberOfNodesMax);
                }
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
        edgesSpinner.setValues(numberOfEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
        edgesSpinner.setEnabled(false);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        edgesSpinner.setLayoutData(gridData);
        
        Label label = new Label(edgeGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_number_of_edges_variance_caption);
        
        final Spinner edgesVarianceSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
        edgesVarianceSpinner.setToolTipText(Messages.RandomGraphAnyPage_number_of_edges_variance_help);
        edgesVarianceSpinner.setValues(edgesVariance, 0, Integer.MAX_VALUE, 0, 1, 10);
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
        edgesRelSpinner.setValues(relativeEdgeNumber, 0, Integer.MAX_VALUE, 2, 1, 10);
        edgesRelSpinner.setEnabled(false);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        edgesRelSpinner.setLayoutData(gridData);
        
        label = new Label(edgeGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_rel_number_of_edges_variance_caption);
        
        final Spinner edgesRelVarianceSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
        edgesRelVarianceSpinner.setToolTipText(
                Messages.RandomGraphAnyPage_rel_number_of_edges_variance_help);
        edgesRelVarianceSpinner.setValues(relativeEdgeNumberVariance, 0, Integer.MAX_VALUE, 2, 1, 10);
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
        densitySpinner.setValues(density, 0, 100, 0, 1, 10);
        densitySpinner.setEnabled(false);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        densitySpinner.setLayoutData(gridData);
        
        label = new Label(edgeGroup, SWT.NONE);
        label.setText(Messages.RandomGraphAnyPage_density_variance_caption);
        
        final Spinner densityVarianceSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
        densityVarianceSpinner.setToolTipText(Messages.RandomGraphAnyPage_density_variance_help);
        densityVarianceSpinner.setValues(densityVariance, 0, 100, 0, 1, 10);
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
        minOutSpinner.setValues(minOutgoingEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
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
        maxOutSpinner.setValues(maxOutgoingEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
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
                numberOfEdges = edgesSpinner.getSelection();
            }
        });
        
        edgesVarianceSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                edgesVariance = edgesVarianceSpinner.getSelection();
            }
        });
        
        edgesRelSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                relativeEdgeNumber = edgesRelSpinner.getSelection();
            }
        });
        
        edgesRelVarianceSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                relativeEdgeNumberVariance = edgesRelVarianceSpinner.getSelection();
            }
        });

        densitySpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                density = densitySpinner.getSelection();
            }
        });

        densityVarianceSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                densityVariance = densityVarianceSpinner.getSelection();
            }
        });

        minOutSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                minOutgoingEdges = minOutSpinner.getSelection();
                if (maxOutgoingEdges < minOutgoingEdges) {
                    maxOutSpinner.setSelection(minOutgoingEdges);
                }
            }
        });

        maxOutSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                maxOutgoingEdges = maxOutSpinner.getSelection();
                if (minOutgoingEdges > maxOutgoingEdges) {
                    minOutSpinner.setSelection(maxOutgoingEdges);
                }
            }
        });
        
        // Radio Button Controlling
        edgesSwitch.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                edgeDetermination = RandomGraphGenerator.EdgeDetermination.GRAPH_EDGES;
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
        edgesRelSwitch.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                edgeDetermination = RandomGraphGenerator.EdgeDetermination.RELATIVE;
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
        densitySwitch.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                edgeDetermination = RandomGraphGenerator.EdgeDetermination.DENSITY;
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
        outgoingSwitch.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                edgeDetermination = RandomGraphGenerator.EdgeDetermination.OUTGOING_EDGES;
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
        switch (edgeDetermination) {
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
        selfLoopsButton.setSelection(selfLoops);
        
        // Multi Edges
        final Button multiEdgesButton = new Button(optionsGroup, SWT.CHECK);
        multiEdgesButton.setText(Messages.RandomGraphAnyPage_multi_edges_caption);
        multiEdgesButton.setToolTipText(Messages.RandomGraphAnyPage_multi_edges_help);
        multiEdgesButton.setSelection(multiEdges);
        
        // Cycles
        final Button cyclesButton = new Button(optionsGroup, SWT.CHECK);
        cyclesButton.setText(Messages.RandomGraphAnyPage_cycles_caption);
        cyclesButton.setToolTipText(Messages.RandomGraphAnyPage_cycles_help);
        cyclesButton.setSelection(cycles);
        
        // Isolated Nodes
        final Button isolatedButton = new Button(optionsGroup, SWT.CHECK);
        isolatedButton.setText(Messages.RandomGraphAnyPage_isolated_nodes_caption);
        isolatedButton.setToolTipText(Messages.RandomGraphAnyPage_isolated_nodes_help);
        isolatedButton.setSelection(isolatedNodes);
        
        // Event listeners
        selfLoopsButton.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                selfLoops = selfLoopsButton.getSelection();
            }
        });

        multiEdgesButton.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                multiEdges = multiEdgesButton.getSelection();
            }
        });

        cyclesButton.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                cycles = cyclesButton.getSelection();
            }
        });

        isolatedButton.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                isolatedNodes = isolatedButton.getSelection();
            }
        });
    }

    /**
     * Saves the selected options to the preference store.
     */
    public void savePreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        preferenceStore.setValue(RandomGraphGenerator.NUMBER_OF_NODES_MIN.getId(), numberOfNodesMin);
        preferenceStore.setValue(RandomGraphGenerator.NUMBER_OF_NODES_MAX.getId(), numberOfNodesMax);
        preferenceStore.setValue(RandomGraphGenerator.EDGE_DETERMINATION.getId(),
                edgeDetermination.ordinal());
        preferenceStore.setValue(RandomGraphGenerator.NUMBER_OF_EDGES.getId(), numberOfEdges);
        preferenceStore.setValue(RandomGraphGenerator.EDGES_VARIANCE.getId(), edgesVariance);
        preferenceStore.setValue(RandomGraphGenerator.DENSITY.getId(), density);
        preferenceStore.setValue(RandomGraphGenerator.DENSITY_VARIANCE.getId(), densityVariance);
        preferenceStore.setValue(RandomGraphGenerator.EDGES_RELATIVE.getId(), relativeEdgeNumber);
        preferenceStore.setValue(RandomGraphGenerator.EDGES_REL_VARIANCE.getId(),
                relativeEdgeNumberVariance);
        preferenceStore.setValue(RandomGraphGenerator.MIN_OUTGOING_EDGES.getId(), minOutgoingEdges);
        preferenceStore.setValue(RandomGraphGenerator.MAX_OUTGOING_EDGES.getId(), maxOutgoingEdges);
        preferenceStore.setValue(RandomGraphGenerator.SELF_LOOPS.getId(), selfLoops);
        preferenceStore.setValue(RandomGraphGenerator.MULTI_EDGES.getId(), multiEdges);
        preferenceStore.setValue(RandomGraphGenerator.CYCLES.getId(), cycles);
        preferenceStore.setValue(RandomGraphGenerator.ISOLATED_NODES.getId(), isolatedNodes);
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        numberOfNodesMin = preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_NODES_MIN.getId());
        numberOfNodesMax = preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_NODES_MAX.getId());
        edgeDetermination = RandomGraphGenerator.EdgeDetermination.values()
                [preferenceStore.getInt(RandomGraphGenerator.EDGE_DETERMINATION.getId())];
        numberOfEdges = preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_EDGES.getId());
        edgesVariance = preferenceStore.getInt(RandomGraphGenerator.EDGES_VARIANCE.getId());
        relativeEdgeNumber = preferenceStore.getInt(RandomGraphGenerator.EDGES_RELATIVE.getId());
        relativeEdgeNumberVariance = preferenceStore.getInt(
                RandomGraphGenerator.EDGES_REL_VARIANCE.getId());
        density = preferenceStore.getInt(RandomGraphGenerator.DENSITY.getId());
        densityVariance = preferenceStore.getInt(RandomGraphGenerator.DENSITY_VARIANCE.getId());
        minOutgoingEdges = preferenceStore.getInt(RandomGraphGenerator.MIN_OUTGOING_EDGES.getId());
        maxOutgoingEdges = preferenceStore.getInt(RandomGraphGenerator.MAX_OUTGOING_EDGES.getId());
        selfLoops = preferenceStore.getBoolean(RandomGraphGenerator.SELF_LOOPS.getId());
        multiEdges = preferenceStore.getBoolean(RandomGraphGenerator.MULTI_EDGES.getId());
        cycles = preferenceStore.getBoolean(RandomGraphGenerator.CYCLES.getId());
        isolatedNodes = preferenceStore.getBoolean(RandomGraphGenerator.ISOLATED_NODES.getId());
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_NODES_MIN.getId(),
                RandomGraphGenerator.NUMBER_OF_NODES_MIN.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_NODES_MAX.getId(),
                RandomGraphGenerator.NUMBER_OF_NODES_MAX.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.EDGE_DETERMINATION.getId(),
                RandomGraphGenerator.EdgeDetermination.GRAPH_EDGES.ordinal());
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_EDGES.getId(),
                RandomGraphGenerator.NUMBER_OF_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.EDGES_VARIANCE.getId(),
                RandomGraphGenerator.EDGES_VARIANCE.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.EDGES_RELATIVE.getId(),
                (int) (RandomGraphGenerator.EDGES_RELATIVE.getDefault() * 100));
        preferenceStore.setDefault(RandomGraphGenerator.EDGES_REL_VARIANCE.getId(),
                (int) (RandomGraphGenerator.EDGES_REL_VARIANCE.getDefault() * 100));
        preferenceStore.setDefault(RandomGraphGenerator.DENSITY.getId(),
                (int) (RandomGraphGenerator.DENSITY.getDefault() * 100));
        preferenceStore.setDefault(RandomGraphGenerator.DENSITY_VARIANCE.getId(),
                (int) (RandomGraphGenerator.DENSITY_VARIANCE.getDefault() * 100));
        preferenceStore.setDefault(RandomGraphGenerator.MIN_OUTGOING_EDGES.getId(),
                RandomGraphGenerator.MIN_OUTGOING_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.MAX_OUTGOING_EDGES.getId(),
                RandomGraphGenerator.MAX_OUTGOING_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.SELF_LOOPS.getId(),
                RandomGraphGenerator.SELF_LOOPS.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.MULTI_EDGES.getId(),
                RandomGraphGenerator.MULTI_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.CYCLES.getId(),
                RandomGraphGenerator.CYCLES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.ISOLATED_NODES.getId(),
                RandomGraphGenerator.ISOLATED_NODES.getDefault());
    }

    /**
     * Returns the minimum number of nodes.
     * 
     * @return the minimum number of nodes
     */
    public int getMinNumberOfNodes() {
        return numberOfNodesMin;
    }

    /**
     * Returns the maximum number of nodes.
     * 
     * @return the maximum number of nodes
     */
    public int getMaxNumberOfNodes() {
        return numberOfNodesMax;
    }

    /**
     * Returns the selected edge determination.
     * 
     * @return the edge determination
     */
    public RandomGraphGenerator.EdgeDetermination getEdgeDetermination() {
        return edgeDetermination;
    }

    /**
     * Returns the selected number of edges.
     * 
     * @return the number of edges
     */
    public int getNumberOfEdges() {
        return numberOfEdges;
    }
    
    /**
     * Returns the variance of the number of edges.
     * 
     * @return the edge number variance
     */
    public int getEdgesVariance() {
        return edgesVariance;
    }
    
    /**
     * Returns the relative number of edges.
     * 
     * @return the relative number of edges
     */
    public double getRelativeNumberOfEdges() {
        return relativeEdgeNumber / 100.0;
    }
    
    
    /**
     * Returns the variance of relative edge number.
     * 
     * @return the relative edge number variance
     */
    public double getRelativeEdgesVariance() {
        return relativeEdgeNumberVariance / 100.0;
    }
    
    /**
     * Returns the selected density.
     * 
     * @return the density
     */
    public double getDensity() {
        return density / 100.0;
    }
    
    /**
     * Returns the density variance.
     * 
     * @return the density variance
     */
    public double getDensityVariance() {
        return densityVariance / 100.0;
    }

    /**
     * Returns the selected minimum number of outgoing edges.
     * 
     * @return the minimum number of outgoing edges
     */
    public int getMinOutgoingEdges() {
        return minOutgoingEdges;
    }

    /**
     * Returns the selected maximum number of outgoing edges.
     * 
     * @return the maximum number of outgoing edges
     */
    public int getMaxOutgoingEdges() {
        return maxOutgoingEdges;
    }

    /**
     * Returns whether self-loops are allowed.
     * 
     * @return true if self-loops are allowed; false otherwise
     */
    public boolean getSelfLoops() {
        return selfLoops;
    }

    /**
     * Returns whether multi-edges are allowed.
     * 
     * @return true if multi-edges are allowed; false otherwise
     */
    public boolean getMultiEdges() {
        return multiEdges;
    }

    /**
     * Returns whether cycles are allowed.
     * 
     * @return true if cycles are allowed; false otherwise
     */
    public boolean getCycles() {
        return cycles;
    }
    
    /**
     * Returns whether isolated nodes are allowed.
     * 
     * @return true if isolated nodes are allowed; false otherwise
     */
    public boolean getIsolatedNodes() {
        return isolatedNodes;
    }

    /**
     * An adapter class for the SelectionListener.
     */
    private abstract static class SelectionListenerAdapter implements SelectionListener {
        public void widgetDefaultSelected(final SelectionEvent e) {
            // do nothing
        }
    }
}
