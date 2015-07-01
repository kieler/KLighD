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
package de.cau.cs.kieler.core.kgraph.text.ui.random.wizard;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.core.kgraph.text.ui.random.GeneratorOptions;
import de.cau.cs.kieler.core.kgraph.text.ui.random.GeneratorOptions.EdgeDetermination;

/**
 * Abstract superclass for basic properties of random graphs.
 * 
 * @author msp
 */
public abstract class AbstractRandomGraphPage extends WizardPage {
    
    /** the generator options. */
    private GeneratorOptions options;

    /**
     * Constructs a RandomGraphAnyPage.
     * 
     * @param pageName the name of the page
     * @param options the generator options
     */
    public AbstractRandomGraphPage(final String pageName, final GeneratorOptions options) {
        super(pageName);
        this.options = options;
    }
    
    /**
     * Returns the generator options.
     * 
     * @return the generator options
     */
    protected GeneratorOptions getOptions() {
        return options;
    }

    // CHECKSTYLEOFF MagicNumber
    
    /**
     * Creates the group holding node options and adds it to the given composite.
     * 
     * @param composite the composite to add the group to.
     * @return the created group container
     */
    protected final Composite createNodesGroup(final Composite composite) {
        Group nodesGroup = new Group(composite, SWT.NONE);
        nodesGroup.setText(Messages.RandomGraphPage_nodes_group_caption);
        nodesGroup.setLayout(new GridLayout(2, false));
        nodesGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        
        // Minimum number of Nodes
        Label label = new Label(nodesGroup, SWT.NONE);
        label.setText(Messages.RandomGraphPage_number_of_nodes_min);
        final Spinner nodesMinSpinner = new Spinner(nodesGroup, SWT.BORDER | SWT.SINGLE);
        nodesMinSpinner.setToolTipText(Messages.RandomGraphPage_number_of_nodes_min_help);
        nodesMinSpinner.setValues(options.getProperty(GeneratorOptions.NUMBER_OF_NODES_MIN),
                1, Integer.MAX_VALUE, 0, 1, 10);
        GridData gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        nodesMinSpinner.setLayoutData(gridData);

        // Maximum number of Nodes
        label = new Label(nodesGroup, SWT.NONE);
        label.setText(Messages.RandomGraphPage_number_of_nodes_max);
        final Spinner nodesMaxSpinner = new Spinner(nodesGroup, SWT.BORDER | SWT.SINGLE);
        nodesMaxSpinner.setToolTipText(Messages.RandomGraphPage_number_of_nodes_max_help);
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
        
        return nodesGroup;
    }

    /**
     * Creates the group holding edge options and adds it to the given composite.
     * 
     * @param composite the composite to add the group to
     * @param edgeDeterminations the edge determination types that are shown
     * @return the created group container
     */
    protected final Composite createEdgeGroup(final Composite composite,
            final EnumSet<EdgeDetermination> edgeDeterminations) {
        Group edgeGroup = new Group(composite, SWT.NONE);
        edgeGroup.setText(Messages.RandomGraphPage_edges_group_caption);
        edgeGroup.setLayout(new GridLayout(4, false));
        edgeGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        final List<Control> controlsToDisable = new ArrayList<Control>();
        EdgeDetermination currentDetermination = options.getProperty(
                GeneratorOptions.EDGE_DETERMINATION);
        
        // Edge Group Description
        Label edgeDescriptionLabel = new Label(edgeGroup, SWT.WRAP);
        edgeDescriptionLabel.setText(Messages.RandomGraphPage_edges_description_caption);
        edgeDescriptionLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
        
        // Number of Edges
        if (edgeDeterminations.contains(EdgeDetermination.ABSOLUTE)) {
            Button edgesSwitch = new Button(edgeGroup, SWT.RADIO | SWT.LEFT);
            edgesSwitch.setText(Messages.RandomGraphPage_number_of_edges_caption);
            edgesSwitch.setToolTipText(Messages.RandomGraphPage_number_of_edges_help);
            edgesSwitch.setSelection(currentDetermination == EdgeDetermination.ABSOLUTE);
            
            final Spinner edgesSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
            edgesSpinner.setToolTipText(Messages.RandomGraphPage_number_of_edges_help);
            edgesSpinner.setValues(options.getProperty(GeneratorOptions.EDGES_ABSOLUTE),
                    0, Integer.MAX_VALUE, 0, 1, 10);
            edgesSpinner.setEnabled(currentDetermination == EdgeDetermination.ABSOLUTE);
            
            GridData gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
            gridData.widthHint = 80;
            edgesSpinner.setLayoutData(gridData);
            controlsToDisable.add(edgesSpinner);
            
            Label label = new Label(edgeGroup, SWT.NONE);
            label.setText(Messages.RandomGraphPage_number_of_edges_variance_caption);
            
            final Spinner edgesVarianceSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
            edgesVarianceSpinner.setToolTipText(Messages.RandomGraphPage_number_of_edges_variance_help);
            edgesVarianceSpinner.setValues(options.getProperty(GeneratorOptions.EDGES_ABS_STDDEV),
                    0, Integer.MAX_VALUE, 0, 1, 10);
            edgesVarianceSpinner.setEnabled(currentDetermination == EdgeDetermination.ABSOLUTE);
            
            gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
            gridData.widthHint = 80;
            edgesVarianceSpinner.setLayoutData(gridData);
            controlsToDisable.add(edgesVarianceSpinner);
            
            edgesSpinner.addModifyListener(new ModifyListener() {
                public void modifyText(final ModifyEvent e) {
                    options.setProperty(GeneratorOptions.EDGES_ABSOLUTE, edgesSpinner.getSelection());
                }
            });

            edgesVarianceSpinner.addModifyListener(new ModifyListener() {
                public void modifyText(final ModifyEvent e) {
                    options.setProperty(GeneratorOptions.EDGES_ABS_STDDEV,
                            edgesVarianceSpinner.getSelection());
                }
            });

            edgesSwitch.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    options.setProperty(GeneratorOptions.EDGE_DETERMINATION,
                            EdgeDetermination.ABSOLUTE);
                    for (Control c : controlsToDisable) {
                        c.setEnabled(c == edgesSpinner || c == edgesVarianceSpinner);
                    }
                }
            });
        }
        
        // Relative Number of Edges
        if (edgeDeterminations.contains(EdgeDetermination.RELATIVE)) {
            Button edgesRelSwitch = new Button(edgeGroup, SWT.RADIO | SWT.LEFT);
            edgesRelSwitch.setText(Messages.RandomGraphPage_rel_number_of_edges_caption);
            edgesRelSwitch.setToolTipText(Messages.RandomGraphPage_rel_number_of_edges_help);
            edgesRelSwitch.setSelection(currentDetermination == EdgeDetermination.RELATIVE);
            
            final Spinner edgesRelSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
            edgesRelSpinner.setToolTipText(Messages.RandomGraphPage_rel_number_of_edges_help);
            edgesRelSpinner.setValues((int) (options.getProperty(GeneratorOptions.EDGES_RELATIVE) * 100),
                    0, Integer.MAX_VALUE, 2, 1, 10);
            edgesRelSpinner.setEnabled(currentDetermination == EdgeDetermination.RELATIVE);
            
            GridData gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
            gridData.widthHint = 80;
            edgesRelSpinner.setLayoutData(gridData);
            controlsToDisable.add(edgesRelSpinner);
            
            Label label = new Label(edgeGroup, SWT.NONE);
            label.setText(Messages.RandomGraphPage_rel_number_of_edges_variance_caption);
            
            final Spinner edgesRelVarianceSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
            edgesRelVarianceSpinner.setToolTipText(
                    Messages.RandomGraphPage_rel_number_of_edges_variance_help);
            edgesRelVarianceSpinner.setValues(
                    (int) (options.getProperty(GeneratorOptions.EDGES_REL_STDDEV) * 100),
                    0, Integer.MAX_VALUE, 2, 1, 10);
            edgesRelVarianceSpinner.setEnabled(currentDetermination == EdgeDetermination.RELATIVE);
            
            gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
            gridData.widthHint = 80;
            edgesRelVarianceSpinner.setLayoutData(gridData);
            controlsToDisable.add(edgesRelVarianceSpinner);
            
            edgesRelSpinner.addModifyListener(new ModifyListener() {
                public void modifyText(final ModifyEvent e) {
                    options.setProperty(GeneratorOptions.EDGES_RELATIVE,
                            edgesRelSpinner.getSelection() / 100.0);
                }
            });
            
            edgesRelVarianceSpinner.addModifyListener(new ModifyListener() {
                public void modifyText(final ModifyEvent e) {
                    options.setProperty(GeneratorOptions.EDGES_REL_STDDEV,
                            edgesRelVarianceSpinner.getSelection() / 100.0);
                }
            });
            
            edgesRelSwitch.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    options.setProperty(GeneratorOptions.EDGE_DETERMINATION,
                            EdgeDetermination.RELATIVE);
                    for (Control c : controlsToDisable) {
                        c.setEnabled(c == edgesRelSpinner || c == edgesRelVarianceSpinner);
                    }
                }
            });
        }
        
        // Density
        if (edgeDeterminations.contains(EdgeDetermination.DENSITY)) {
            Button densitySwitch = new Button(edgeGroup, SWT.RADIO | SWT.LEFT);
            densitySwitch.setText(Messages.RandomGraphPage_density_caption);
            densitySwitch.setToolTipText(Messages.RandomGraphPage_density_help);
            densitySwitch.setSelection(currentDetermination == EdgeDetermination.DENSITY);
            
            final Spinner densitySpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
            densitySpinner.setToolTipText(Messages.RandomGraphPage_density_help);
            densitySpinner.setValues((int) (options.getProperty(GeneratorOptions.DENSITY) * 100),
                    0, 100, 0, 1, 10);
            densitySpinner.setEnabled(currentDetermination == EdgeDetermination.DENSITY);
            
            GridData gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
            gridData.widthHint = 80;
            densitySpinner.setLayoutData(gridData);
            controlsToDisable.add(densitySpinner);
            
            Label label = new Label(edgeGroup, SWT.NONE);
            label.setText(Messages.RandomGraphPage_density_variance_caption);
            
            final Spinner densityVarianceSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
            densityVarianceSpinner.setToolTipText(Messages.RandomGraphPage_density_variance_help);
            densityVarianceSpinner.setValues(
                    (int) (options.getProperty(GeneratorOptions.DENSITY_STDDEV) * 100),
                    0, 100, 0, 1, 10);
            densityVarianceSpinner.setEnabled(currentDetermination == EdgeDetermination.DENSITY);
            
            gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
            gridData.widthHint = 80;
            densityVarianceSpinner.setLayoutData(gridData);
            controlsToDisable.add(densityVarianceSpinner);

            densitySpinner.addModifyListener(new ModifyListener() {
                public void modifyText(final ModifyEvent e) {
                    options.setProperty(GeneratorOptions.DENSITY,
                            densitySpinner.getSelection() / 100.0);
                }
            });

            densityVarianceSpinner.addModifyListener(new ModifyListener() {
                public void modifyText(final ModifyEvent e) {
                    options.setProperty(GeneratorOptions.DENSITY_STDDEV,
                            densityVarianceSpinner.getSelection() / 100.0);
                }
            });
            
            densitySwitch.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    options.setProperty(GeneratorOptions.EDGE_DETERMINATION,
                            EdgeDetermination.DENSITY);
                    for (Control c : controlsToDisable) {
                        c.setEnabled(c == densitySpinner || c == densityVarianceSpinner);
                    }
                }
            });
        }
        
        // Outgoing Edges
        if (edgeDeterminations.contains(EdgeDetermination.OUTGOING)) {
            Button outgoingSwitch = new Button(edgeGroup, SWT.RADIO | SWT.LEFT);
            outgoingSwitch.setText(Messages.RandomGraphAnyPage_outgoing_caption);
            outgoingSwitch.setToolTipText(Messages.RandomGraphAnyPage_outgoing_help);
            outgoingSwitch.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false, 4, 1));
            outgoingSwitch.setSelection(currentDetermination == EdgeDetermination.OUTGOING);
            
            // Minimum
            Label label = new Label(edgeGroup, SWT.NONE);
            label.setText(Messages.RandomGraphPage_min_outgoing_caption);
            
            GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
            gridData.horizontalIndent = 30;
            label.setLayoutData(gridData);
            
            final Spinner minOutSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
            minOutSpinner.setToolTipText(Messages.RandomGraphPage_min_outgoing_help);
            minOutSpinner.setValues(options.getProperty(GeneratorOptions.MIN_OUTGOING_EDGES),
                    0, Integer.MAX_VALUE, 0, 1, 10);
            minOutSpinner.setEnabled(currentDetermination == EdgeDetermination.OUTGOING);
            
            gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
            gridData.widthHint = 80;
            minOutSpinner.setLayoutData(gridData);
            controlsToDisable.add(minOutSpinner);
            
            // fill the remaining space
            new Label(edgeGroup, SWT.NONE).setLayoutData(
                    new GridData(SWT.NONE, SWT.NONE, false, false, 2, 1));
            
            // Maximum
            label = new Label(edgeGroup, SWT.NONE);
            label.setText(Messages.RandomGraphPage_max_outgoing_caption);
            
            gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
            gridData.horizontalIndent = 30;
            label.setLayoutData(gridData);
            
            final Spinner maxOutSpinner = new Spinner(edgeGroup, SWT.BORDER | SWT.SINGLE);
            maxOutSpinner.setToolTipText(Messages.RandomGraphPage_max_outgoing_help);
            maxOutSpinner.setValues(options.getProperty(GeneratorOptions.MAX_OUTGOING_EDGES),
                    0, Integer.MAX_VALUE, 0, 1, 10);
            maxOutSpinner.setEnabled(currentDetermination == EdgeDetermination.OUTGOING);
            
            gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
            gridData.widthHint = 80;
            maxOutSpinner.setLayoutData(gridData);
            controlsToDisable.add(maxOutSpinner);
            
            // fill the remaining space
            new Label(edgeGroup, SWT.NONE).setLayoutData(
                    new GridData(SWT.NONE, SWT.NONE, false, false, 2, 1));

            minOutSpinner.addModifyListener(new ModifyListener() {
                public void modifyText(final ModifyEvent e) {
                    options.setProperty(GeneratorOptions.MIN_OUTGOING_EDGES,
                            minOutSpinner.getSelection());
                    validate();
                }
            });

            maxOutSpinner.addModifyListener(new ModifyListener() {
                public void modifyText(final ModifyEvent e) {
                    options.setProperty(GeneratorOptions.MAX_OUTGOING_EDGES,
                            maxOutSpinner.getSelection());
                    validate();
                }
            });

            outgoingSwitch.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    options.setProperty(GeneratorOptions.EDGE_DETERMINATION,
                            EdgeDetermination.OUTGOING);
                    for (Control c : controlsToDisable) {
                        c.setEnabled(c == minOutSpinner || c == maxOutSpinner);
                    }
                }
            });
        }
        
        return edgeGroup;
    }

    /**
     * Check all values that can be configured through this wizard page.
     */
    protected void validate() {
        if (options.getProperty(GeneratorOptions.NUMBER_OF_NODES_MIN)
                > options.getProperty(GeneratorOptions.NUMBER_OF_NODES_MAX)) {
            setErrorMessage(Messages.RandomGraphPage_number_of_nodes_error);
            setPageComplete(false);
        } else if (options.getProperty(GeneratorOptions.MIN_OUTGOING_EDGES)
                > options.getProperty(GeneratorOptions.MAX_OUTGOING_EDGES)) {
            setErrorMessage(Messages.RandomGraphAnyPage_outgoing_edges_error);
            setPageComplete(false);
            return;
        } else {
            setErrorMessage(null);
            setPageComplete(true);
        }
    }
    
}
