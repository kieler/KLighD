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

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;
import de.cau.cs.kieler.klighd.ui.printing.util.PrintExporter;

/**
 * A dialog that supports platform independent printing based on the Java Printing Service API.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
public class JPSPrintDialog extends TrayDialog {

    private DataBindingContext bindings;
    private final PrintOptions options;

    protected PrinterBlock printerBlock;
    protected ScalingBlock scalingBlock;
    private RangeBlock rangeBlock;
    private CopiesBlock copiesBlock;
    private ActionsBlock actionsBlock;

    private final DialogBlock.IDialogUnitConverter dluConverter =
            new DialogBlock.IDialogUnitConverter() {

                public int convertHorizontalDLUsToPixels(int dlus) {
                    return JPSPrintDialog.this.convertHorizontalDLUsToPixels(dlus);
                }

                public Shell getShell() {
                    return JPSPrintDialog.this.getShell();
                }
            };

    public JPSPrintDialog(IShellProvider parentShell, PrintOptions options, List<String> allDiagrams) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.options = options;
    }

    public JPSPrintDialog(Shell shell, PrintOptions options, List<String> allDiagrams) {
        super(shell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.options = options;
    }

    /**
     * @param shell
     * 
     */
    private boolean checkPrinterData(Shell shell) {
        try {
            new Printer(options.getPrinterData());
        } catch (Throwable e) {
            PrintDialog printDialog = new PrintDialog(shell);
            printDialog.setText("Select a printer");
            PrinterData data = printDialog.open();
            if (data != null) {
                options.setPrinterData(data);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);

        newShell.setText(DiagramUIPrintingMessages.JPSPrintDialog_Title);
        setHelpAvailable(false);
        setDialogHelpAvailable(false);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        bindings = new DataBindingContext(SWTObservables.getRealm(parent.getDisplay()));

        Composite result = new Composite(parent, SWT.NONE);
        DialogBlock.layout(result, 2);

        createPrinterBlockArea(result);
        createScalingBlockArea(result);
        createRangeBlockArea(result);
        createCopiesBlockArea(result);
        createActionsBlockArea(result);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int open() {
        if (!checkPrinterData(getParentShell())) {
            return IDialogConstants.CANCEL_ID;
        }
        return super.open();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeBounds() {
        super.initializeBounds();
        final Shell shell = getShell();
        shell.setMinimumSize(shell.getSize());
    }

    protected void createPrinterBlockArea(Composite result) {
        printerBlock = new PrinterBlock(dluConverter, bindings, options);
        printerBlock.layoutSpanHorizontal(printerBlock.createContents(result), 2);
    }

    protected void createScalingBlockArea(Composite result) {
        scalingBlock = new ScalingBlock(dluConverter, bindings, options);
        scalingBlock.layoutSpanHorizontal(scalingBlock.createContents(result), 2);
    }

    protected void createRangeBlockArea(Composite result) {
        rangeBlock = new RangeBlock(dluConverter, bindings, options);
        rangeBlock.createContents(result);
    }

    protected void createCopiesBlockArea(Composite result) {
        copiesBlock = new CopiesBlock(dluConverter, bindings, options);
        copiesBlock.createContents(result);
    }

    protected void createExtensibleBlockArea(Composite result) {
        // meant to be overridden by subclasses to add additional blocks.
    }

    protected void createActionsBlockArea(Composite result) {
        actionsBlock = new ActionsBlock(dluConverter, bindings, options, this);
        actionsBlock.layoutSpanHorizontal(actionsBlock.createContents(result), 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonPressed(int buttonId) {
        switch (buttonId) {
        case -1:
            break;
        case IDialogConstants.OK_ID:
            options.storeToPreferences();
            // fall through!
        default:
            super.buttonPressed(buttonId);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean close() {
        bindings.dispose();
        copiesBlock.dispose();
        printerBlock.dispose();
        scalingBlock.dispose();
        rangeBlock.dispose();
        actionsBlock.dispose();
        return super.close();
    }
}
