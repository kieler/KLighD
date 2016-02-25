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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.kgraph.text.ui.random.GeneratorOptions;
import de.cau.cs.kieler.kgraph.text.ui.random.GeneratorOptions.EdgeDetermination;

/**
 * The options page for the 'Acyclic, No Transitive Edges' graph type.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphANTEPage extends AbstractRandomGraphPage {

    /**
     * Constructs a RandomGraphANTEPage.
     * 
     * @param options the generator options
     */
    public RandomGraphANTEPage(final GeneratorOptions options) {
        super("randomGraphANTEPage", options); //$NON-NLS-1$
        setTitle(Messages.RandomGraphANTEPage_title);
        setDescription(Messages.RandomGraphANTEPage_description);
    }
    
    // CHECKSTYLEOFF MagicNumber

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        setControl(composite);
        
        GridLayout layout = new GridLayout(1, false);
        layout.verticalSpacing = 10;
        composite.setLayout(layout);
        
        // add NUMBER_OF_NODES option
        createNodesGroup(composite);
        
        // add NUMBER_OF_EDGES option
        createEdgeGroup(composite, EnumSet.of(EdgeDetermination.ABSOLUTE, EdgeDetermination.RELATIVE,
                EdgeDetermination.DENSITY));
        
        // add PLANAR option
        final Button planarButton = new Button(composite, SWT.CHECK);
        planarButton.setToolTipText(Messages.RandomGraphANTEPage_planarity_help);
        planarButton.setText(Messages.RandomGraphANTEPage_planarity_caption);
        planarButton.setSelection(getOptions().getProperty(GeneratorOptions.PLANAR));
        planarButton.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false, 1, 1));
        
        planarButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                getOptions().setProperty(GeneratorOptions.PLANAR, planarButton.getSelection());
            }
        });
    }

}
