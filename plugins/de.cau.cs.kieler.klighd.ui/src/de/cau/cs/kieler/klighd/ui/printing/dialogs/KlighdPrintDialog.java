// SUPPRESS CHECKSTYLE NEXT Header
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
package de.cau.cs.kieler.klighd.ui.printing.dialogs;

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

/**
 * A dialog that supports platform independent printing options.
 * A printpreview can be shown in the tray.
 * 
 * @author csp
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
public class KlighdPrintDialog extends TrayDialog {

    private DataBindingContext bindings;
    private final PrintOptions options;

    private PrinterBlock printerBlock;
    private ScalingBlock scalingBlock;
    private RangeBlock rangeBlock;
    private CopiesBlock copiesBlock;
    private ActionsBlock actionsBlock;

    /**
     * Creates a new Klighd print dialog based on the given options.
     * 
     * @param parentShell
     *            the object that returns the current parent shell
     * @param options
     *            the options to configure the print job
     */
    public KlighdPrintDialog(final IShellProvider parentShell, final PrintOptions options) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.options = options;
    }

    /**
     * Creates a new Klighd print dialog based on the given options.
     * 
     * @param shell
     *            the parent shell, or <code>null</code> to create a top-level shell
     * @param options
     *            the options to configure the print job
     */
    public KlighdPrintDialog(final Shell shell, final PrintOptions options) {
        super(shell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.options = options;
    }

    /**
     * @param shell
     * 
     */
    private boolean checkPrinterData(final Shell shell) {
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

        newShell.setText(DiagramUIPrintingMessages.PrintDialog_Title);
        setHelpAvailable(false);
        setDialogHelpAvailable(false);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        bindings = new DataBindingContext(SWTObservables.getRealm(parent.getDisplay()));

        Composite result = new Composite(parent, SWT.NONE);
        DialogUtil.layout(result, 2);

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

    /**
     * Creates the printer block area.
     *
     * @param parent the parent composite
     */
    protected void createPrinterBlockArea(final Composite parent) {
        printerBlock = new PrinterBlock(bindings, options, this);
        DialogUtil.layoutSpanHorizontal(printerBlock.createContents(parent), 2);
    }

    /**
     * Creates the scaling block area.
     *
     * @param parent the parent composite
     */
    protected void createScalingBlockArea(final Composite parent) {
        scalingBlock = new ScalingBlock(bindings, options);
        DialogUtil.layoutSpanHorizontal(scalingBlock.createContents(parent), 2);
    }

    /**
     * Creates the range block area.
     *
     * @param parent the parent composite
     */
    protected void createRangeBlockArea(final Composite parent) {
        rangeBlock = new RangeBlock(bindings, options);
        rangeBlock.createContents(parent);
    }

    /**
     * Creates the copies block area.
     *
     * @param parent the parent composite
     */
    protected void createCopiesBlockArea(final Composite parent) {
        copiesBlock = new CopiesBlock(bindings, options);
        copiesBlock.createContents(parent);
    }

    /**
     * Creates the extensible block area.
     *
     * @param parent the parent composite
     */
    protected void createExtensibleBlockArea(final Composite parent) {
        // meant to be overridden by subclasses to add additional blocks.
    }

    /**
     * Creates the actions block area.
     *
     * @param parent the parent composite
     */
    protected void createActionsBlockArea(final Composite parent) {
        actionsBlock = new ActionsBlock(bindings, options, this);
        DialogUtil.layoutSpanHorizontal(actionsBlock.createContents(parent), 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonPressed(final int buttonId) {
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
