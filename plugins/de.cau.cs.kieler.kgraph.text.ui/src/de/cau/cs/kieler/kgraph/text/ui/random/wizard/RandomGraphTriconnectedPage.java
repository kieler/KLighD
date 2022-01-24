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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions;

/**
 * The options page for the TRICONNECTED graph type.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphTriconnectedPage extends AbstractRandomGraphPage {

    /**
     * Constructs a RandomGraphTriconnectedPage.
     * 
     * @param options the generator options
     */
    public RandomGraphTriconnectedPage(final GeneratorOptions options) {
        super("randomGraphTriconnectedPage", options); //$NON-NLS-1$
        setTitle(Messages.RandomGraphTriconnectedPage_title);
        setDescription(Messages.RandomGraphTriconnectedPage_description);
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
    }
    
}
