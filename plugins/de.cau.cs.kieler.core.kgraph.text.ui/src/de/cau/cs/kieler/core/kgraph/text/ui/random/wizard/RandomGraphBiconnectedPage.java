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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.core.kgraph.text.ui.internal.KGraphActivator;
import de.cau.cs.kieler.core.kgraph.text.ui.random.RandomGraphGenerator;

/**
 * The options page for the BICONNECTED graph type.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphBiconnectedPage extends WizardPage {

    /** the selected number of nodes. */
    private int numberOfNodes;
    /** the selected number of edges. */
    private int numberOfEdges;

    /**
     * Constructs a RandomGraphBiconnectedPage.
     */
    public RandomGraphBiconnectedPage() {
        super("randomGraphBiconnectedPage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphBiconnectedPage_title);
        setDescription(Messages.RandomGraphBiconnectedPage_description);
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
        label.setText(Messages.RandomGraphBiconnectedPage_number_of_nodes_caption);
        
        final Spinner nodesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        nodesSpinner.setToolTipText(Messages.RandomGraphBiconnectedPage_number_of_nodes_help);
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
        label.setText(Messages.RandomGraphBiconnectedPage_number_of_edges_caption);
        
        final Spinner edgesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        edgesSpinner.setToolTipText(Messages.RandomGraphBiconnectedPage_number_of_edges_help);
        edgesSpinner.setValues(numberOfEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        edgesSpinner.setLayoutData(gridData);
        
        edgesSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfEdges = edgesSpinner.getSelection();
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
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        numberOfNodes = preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_NODES.getId());
        numberOfEdges = preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_EDGES.getId());
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_NODES.getId(),
                RandomGraphGenerator.NUMBER_OF_NODES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_EDGES.getId(),
                RandomGraphGenerator.NUMBER_OF_EDGES.getDefault());
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
}
