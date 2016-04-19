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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions;

/**
 * The options page for the tree graph type.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphTreePage extends AbstractRandomGraphPage {

    /**
     * Constructs a RandomGraphTreePage.
     * 
     * @param options the generator options
     */
    public RandomGraphTreePage(final GeneratorOptions options) {
        super("randomGraphTreePage", options); //$NON-NLS-1$
        setTitle(Messages.RandomGraphTreePage_title);
        setDescription(Messages.RandomGraphTreePage_description);
    }

    // CHECKSTYLEOFF MagicNumber
    
    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        composite.setLayout(new GridLayout(2, false));
        setControl(composite);
        
        GridData gridData;
        
        // add NUMBER_OF_NODES option
        Composite nodeGroup = createNodesGroup(composite);
        ((GridData) nodeGroup.getLayoutData()).horizontalSpan = 2;
        
        // add MAX_DEGREE option
        Label label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphTreePage_max_degree_caption);
        
        final Spinner degreeSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        degreeSpinner.setToolTipText(Messages.RandomGraphTreePage_max_degree_help);
        degreeSpinner.setValues(getOptions().getProperty(GeneratorOptions.MAX_DEGREE),
                1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        degreeSpinner.setLayoutData(gridData);
        
        degreeSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                getOptions().setProperty(GeneratorOptions.MAX_DEGREE, degreeSpinner.getSelection());
            }
        });
        
        // add MAX_WIDTH option
        label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphTreePage_max_width_caption);
        
        final Spinner widthSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        widthSpinner.setToolTipText(Messages.RandomGraphTreePage_max_width_help);
        widthSpinner.setValues(getOptions().getProperty(GeneratorOptions.MAX_WIDTH),
                1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        widthSpinner.setLayoutData(gridData);
        
        widthSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                getOptions().setProperty(GeneratorOptions.MAX_WIDTH, widthSpinner.getSelection());
            }
        });
    }
    
}
