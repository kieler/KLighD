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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.core.kgraph.text.ui.internal.KGraphActivator;
import de.cau.cs.kieler.core.kgraph.text.ui.random.RandomGraphGenerator;

/**
 * The options page for the ACYCLIC_NO_TRANSITIV_EDGES graph type.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphANTEPage extends WizardPage {

    /** the selected number of nodes. */
    private int numberOfNodes;
    /** the selected number of edges. */
    private int numberOfEdges;
    /** the selected planar restriction. */
    private boolean planar;

    /**
     * Constructs a RandomGraphANTEPage.
     */
    public RandomGraphANTEPage() {
        super("randomGraphANTEPage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphANTEPage_title);
        setDescription(Messages.RandomGraphANTEPage_description);
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
        composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        composite.setLayout(new GridLayout(2, false));
        
        GridData gridData;
        
        // add NUMBER_OF_NODES option
        Label label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphANTEPage_number_of_nodes_caption);
        
        final Spinner nodesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        nodesSpinner.setToolTipText(Messages.RandomGraphANTEPage_number_of_nodes_help);
        nodesSpinner.setValues(numberOfNodes, 1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        nodesSpinner.setLayoutData(gridData);
        
        nodesSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfNodes = nodesSpinner.getSelection();
            }
        });
        
        // add NUMBER_OF_EDGES option
        label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphANTEPage_number_of_edges_caption);
        
        final Spinner edgesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        edgesSpinner.setToolTipText(Messages.RandomGraphANTEPage_number_of_edges_help);
        edgesSpinner.setValues(numberOfEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        edgesSpinner.setLayoutData(gridData);
        
        edgesSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfEdges = edgesSpinner.getSelection();
            }
        });
        
        // add PLANAR option
        final Button planarButton = new Button(composite, SWT.CHECK);
        planarButton.setToolTipText(Messages.RandomGraphANTEPage_planarity_help);
        planarButton.setText(Messages.RandomGraphANTEPage_planarity_caption);
        planarButton.setSelection(planar);
        planarButton.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false, 2, 1));
        
        planarButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(final SelectionEvent e) {
                planar = planarButton.getSelection();
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
                // do nothing
            }
        });
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Saves the selected options to the preference store.
     */
    public void savePreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        preferenceStore.setValue(RandomGraphGenerator.NUMBER_OF_NODES.getId(), numberOfNodes);
        preferenceStore.setValue(RandomGraphGenerator.NUMBER_OF_EDGES.getId(), numberOfEdges);
        preferenceStore.setValue(RandomGraphGenerator.PLANAR.getId(), planar);
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        numberOfNodes = preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_NODES.getId());
        numberOfEdges = preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_EDGES.getId());
        planar = preferenceStore.getBoolean(RandomGraphGenerator.PLANAR.getId());
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_NODES.getId(),
                RandomGraphGenerator.NUMBER_OF_NODES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_EDGES.getId(),
                RandomGraphGenerator.NUMBER_OF_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.PLANAR.getId(),
                RandomGraphGenerator.PLANAR.getDefault());
    }

    /**
     * Returns the selected number of nodes.
     * 
     * @return the number of nodes
     */
    public int getNumberOfNodes() {
        return numberOfNodes;
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
     * Returns whether the generated graph should be planar.
     * 
     * @return true if the generated graph should be planar; false else
     */
    public boolean getPlanar() {
        return planar;
    }
}
