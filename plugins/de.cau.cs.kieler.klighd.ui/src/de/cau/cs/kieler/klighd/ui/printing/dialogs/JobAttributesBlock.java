/******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package de.cau.cs.kieler.klighd.ui.printing.dialogs;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * A section of the options print dialog that handles the job attributes.
 * 
 * @author James Bruck (jbruck)
 */
public class JobAttributesBlock extends DialogBlock {
    private final DataBindingContext bindings;
    private final PrintOptions options;
    private Binding jobNameBinding;

    JobAttributesBlock(IDialogUnitConverter dluConverter, DataBindingContext bindings,
            PrintOptions options) {
        super(dluConverter);

        this.bindings = bindings;
        this.options = options;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.gmf.runtime.common.ui.printing.internal.dialogs.DialogBlock#createContents(org
     * .eclipse.swt.widgets.Composite)
     */
    public Control createContents(Composite parent) {
        final Realm realm = bindings.getValidationRealm();

        Composite result = group(parent, DiagramUIPrintingMessages.JPSOptionsDialog_JobAttributes);
        layout(result, 2);

        layoutHorizontalIndent(layoutAlignRight(label(result,
                DiagramUIPrintingMessages.JPSOptionsDialog_JobName)));
        Text jobName = text(result, 80);

        jobNameBinding =
                bindings.bindValue(SWTObservables.observeText(jobName, SWT.Modify),
                        BeansObservables.observeValue(realm, options,
                                PrintOptions.PROPERTY_JOB_NAME), null, null);

        return result;
    }

    @Override
    public void dispose() {
        bindings.removeBinding(jobNameBinding);
        jobNameBinding.dispose();
    }
}
