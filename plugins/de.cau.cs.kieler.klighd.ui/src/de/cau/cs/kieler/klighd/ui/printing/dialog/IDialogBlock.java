/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.printing.dialog;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Interface for dialog blocks used in the {@link KlighdPrintDialog}.
 * 
 * @author csp
 */
interface IDialogBlock {

    /**
     * Create the contents of this block.
     * 
     * @param parent
     *            the parent {@link Composite} to use
     * @return the created {@link Control}
     */
    Control createContents(Composite parent);

    /**
     * Dispose all necessary objects.
     */
    void dispose();
}
