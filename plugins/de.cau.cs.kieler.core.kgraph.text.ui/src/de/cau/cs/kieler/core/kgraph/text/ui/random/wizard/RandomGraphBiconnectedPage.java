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

import java.util.EnumSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.core.kgraph.text.ui.random.GeneratorOptions;
import de.cau.cs.kieler.core.kgraph.text.ui.random.GeneratorOptions.EdgeDetermination;

/**
 * The options page for the BICONNECTED graph type.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphBiconnectedPage extends AbstractRandomGraphPage {

    /**
     * Constructs a RandomGraphBiconnectedPage.
     * 
     * @param options the generator options
     */
    public RandomGraphBiconnectedPage(final GeneratorOptions options) {
        super("randomGraphBiconnectedPage", options); //$NON-NLS-1$
        setTitle(Messages.RandomGraphBiconnectedPage_title);
        setDescription(Messages.RandomGraphBiconnectedPage_description);
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
    }

}
