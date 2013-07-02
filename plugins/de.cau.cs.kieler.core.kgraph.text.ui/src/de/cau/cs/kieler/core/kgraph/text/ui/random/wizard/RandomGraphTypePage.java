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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.cau.cs.kieler.core.kgraph.text.ui.random.GeneratorOptions;

/**
 * The random graph wizard page which lets the user select a graph type. The graph type implies what
 * algorithm is used for the generation.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphTypePage extends WizardPage {

    /** the generator options. */
    private GeneratorOptions options;

    /**
     * Constructs a RandomGraphTypePage.
     * 
     * @param options the generator options
     */
    public RandomGraphTypePage(final GeneratorOptions options) {
        super("randomGraphTypePage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphTypePage_title);
        setDescription(Messages.RandomGraphTypePage_description);
        this.options = options;
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        createGraphTypeGroup(composite);
        setControl(composite);
    }

    // CHECKSTYLEOFF MagicNumber
    private void createGraphTypeGroup(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.spacing = 5;
        composite.setLayout(rowLayout);
        
        // label
        Label label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphTypePage_graph_type_caption);
        
        // create buttons
        addRadioButton(
                composite,
                Messages.RandomGraphTypePage_tree_graph_type_caption,
                Messages.RandomGraphTypePage_tree_graph_type_help,
                GeneratorOptions.GraphType.TREE);
        addRadioButton(
                composite,
                Messages.RandomGraphTypePage_biconnected_graph_type_caption,
                Messages.RandomGraphTypePage_biconnected_graph_type_help,
                GeneratorOptions.GraphType.BICONNECTED);
        addRadioButton(composite,
                Messages.RandomGraphTypePage_triconnected_graph_type_caption,
                Messages.RandomGraphTypePage_triconnected_graph_type_help,
                GeneratorOptions.GraphType.TRICONNECTED);
        addRadioButton(
                composite,
                Messages.RandomGraphTypePage_ante_graph_type_caption,
                Messages.RandomGraphTypePage_ante_graph_type_help,
                GeneratorOptions.GraphType.ACYCLIC_NO_TRANSITIVE_EDGES);
        addRadioButton(
                composite,
                Messages.RandomGraphTypePage_any_graph_type_caption,
                Messages.RandomGraphTypePage_any_graph_type_help,
                GeneratorOptions.GraphType.ANY);
    }
    
    /**
     * Creates a radio button with the specified properties as child of the given parent composite.
     * 
     * @param parent the new radio button's parent composite.
     * @param text the button's text.
     * @param toolTip the button's tool tip text.
     * @param type the graph type represented by the button.
     */
    private void addRadioButton(final Composite parent, final String text, final String toolTip,
            final GeneratorOptions.GraphType type) {
        
        final Button radio = new Button(parent, SWT.RADIO | SWT.LEFT);
        radio.setText(text);
        radio.setToolTipText(toolTip);
        
        if (type.equals(options.getProperty(GeneratorOptions.GRAPH_TYPE))) {
            radio.setSelection(true);
        }
        
        radio.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                if (radio.getSelection()) {
                    options.setProperty(GeneratorOptions.GRAPH_TYPE, type);
                }
            }
        });
    }
    
}
