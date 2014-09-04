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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * A dialog that presents advanced printer options to the user.
 * 
 * @author James Bruck (jbruck)
 */
public class JPSOptionsDialog extends TrayDialog {

    private DataBindingContext bindings;
    public final PrintOptions options;

    private QualityBlock qualityBlock;
    private ColorBlock colorBlock;
    private SidesBlock sidesBlock;
    private JobAttributesBlock jobAttributesBlock;

    private final DialogBlock.IDialogUnitConverter dluConverter =
            new DialogBlock.IDialogUnitConverter() {

                public int convertHorizontalDLUsToPixels(int dlus) {
                    return JPSOptionsDialog.this.convertHorizontalDLUsToPixels(dlus);
                }

                public Shell getShell() {
                    return JPSOptionsDialog.this.getShell();
                }
            };

    protected JPSOptionsDialog(Shell shell, DataBindingContext bindings, PrintOptions options) {
        super(shell);
        this.options = options;
        this.bindings = bindings;
    }

    public JPSOptionsDialog(IShellProvider parentShell, DataBindingContext bindings,
            PrintOptions options) {
        super(parentShell);
        this.options = options;
        this.bindings = bindings;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell newShell) {

        super.configureShell(newShell);
        newShell.setText(DiagramUIPrintingMessages.JPSOptionsDialog_AdvancedOptions);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent) {

        Composite result = new Composite(parent, SWT.NONE);
        DialogBlock.layout(result, 2);

        createColorBlockArea(result);
        createQualityBlockArea(result);
        createSidesBlockArea(result);
        createJobAttributesBlockArea(result);

        return result;
    }

    protected void createQualityBlockArea(Composite result) {
        qualityBlock = new QualityBlock(dluConverter, bindings, options);
        qualityBlock.createContents(result);
    }

    protected void createColorBlockArea(Composite result) {
        colorBlock = new ColorBlock(dluConverter, bindings, options);
        colorBlock.createContents(result);
    }

    protected void createSidesBlockArea(Composite result) {
        sidesBlock = new SidesBlock(dluConverter, bindings, options);
        sidesBlock.createContents(result);
    }

    protected void createJobAttributesBlockArea(Composite result) {
        jobAttributesBlock = new JobAttributesBlock(dluConverter, bindings, options);
        jobAttributesBlock.createContents(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.TrayDialog#close()
     */
    public boolean close() {
        qualityBlock.dispose();
        colorBlock.dispose();
        sidesBlock.dispose();
        jobAttributesBlock.dispose();
        return super.close();
    }
}
