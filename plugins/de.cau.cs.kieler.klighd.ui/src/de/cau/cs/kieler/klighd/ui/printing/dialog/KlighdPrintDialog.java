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
package de.cau.cs.kieler.klighd.ui.printing.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.google.common.base.Strings;

import de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.PrintOptions;

/**
 * A dialog that offers platform independent printing options and print preview in a separate tray.<br>
 * <br>
 * To add own GUI elements, you may override any of the {@code create*BlockArea(Composite)} methods
 * to change or omit the corresponding controls. Or you may override the
 * {@link #createDialogArea(Composite)} method and call the needed
 * {@code create*BlockArea(Composite)} methods.<br>
 * <br>
 * The implementation is inspired by
 * {@link org.eclipse.gmf.runtime.diagram.ui.printing.render.dialogs.JPSPrintDialog JPSPrintDialog}
 * of the GMF project.
 *
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 */
public class KlighdPrintDialog extends TrayDialog {

    private DataBindingContext bindings;
    private final PrintOptions options;

    private PrinterBlock printerBlock;
    private ScalingBlock scalingBlock;
    private RangeBlock rangeBlock;
    private CopiesBlock copiesBlock;
    private AlignmentBlock alignmentBlock;
    private ActionsBlock actionsBlock;

    /**
     * Creates a new Klighd print dialog based on the given options.
     *
     * @param parentShell
     *            the object that returns the current parent shell
     * @param options
     *            the options to configure the print job
     * @see PrintOptions
     */
    public KlighdPrintDialog(final IShellProvider parentShell, final PrintOptions options) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.options = options;
    }

    /**
     * Creates a new KLighD print dialog based on the given options.
     *
     * @param shell
     *            the parent shell, or <code>null</code> to create a top-level shell
     * @param options
     *            the options to configure the print job
     * @see PrintOptions
     */
    public KlighdPrintDialog(final Shell shell, final PrintOptions options) {
        super(shell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.options = options;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int open() {
        if (!checkPrinterData()) {
            return IDialogConstants.CANCEL_ID;
        }

        return super.open();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(KlighdUIPrintingMessages.PrintDialog_Title);
        setHelpAvailable(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        bindings = new DataBindingContext(SWTObservables.getRealm(parent.getDisplay()));

        final Composite result = new Composite(parent, SWT.NONE);
        DialogUtil.layout(result, 2);

        createPrinterBlockArea(result);
        createScalingBlockArea(result);
        createRangeBlockArea(result);

        // I deactivated 'copies' block as this information can be changed in the native dialog
        //  but it is not delivered back (at least by OSX) within the returned PrinterData.
        // Thus the 'copies' cannot be updated properly.
        // Furthermore, I think printing a bunch of copies is not that important.

        // createCopiesBlockArea(result);
        createAlignmentBlockArea(result);
        createExtensibleBlockArea(result);
        createActionsBlockArea(result);

        if (PrintOptions.getInitiallyShowPreview()) {

            // this asyncExec is employed in order to let the main dialog get build up properly;
            // for some reason its layout gets heavily corrupted if the preview is opened directly
            //  some good soul might investigate this some day...
            parent.getDisplay().asyncExec(new Runnable() {
                public void run() {
                    openPreview();
                }
            });
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PrintPreviewTray getTray() {
        return (PrintPreviewTray) super.getTray();
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
     * Creates the copies block area.
     *
     * @param parent the parent composite
     */
    protected void createAlignmentBlockArea(final Composite parent) {
         alignmentBlock = new AlignmentBlock(bindings, options);
         alignmentBlock.createContents(parent);
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
        actionsBlock = new ActionsBlock(this);
        DialogUtil.layoutSpanHorizontal(actionsBlock.createContents(parent), 2);
    }

    /**
     * Opens the print preview by injecting a corresponding {@link PrintPreviewTray}.
     */
    public void openPreview() {
        this.openTray(new PrintPreviewTray(bindings, options));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonPressed(final int buttonId) {
        switch (buttonId) {
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
        // copiesBlock.dispose();
        printerBlock.dispose();
        scalingBlock.dispose();
        rangeBlock.dispose();
        actionsBlock.dispose();

        final PrintPreviewTray printPreviewTray = this.getTray();
        if (printPreviewTray != null) {
            printPreviewTray.dispose();
        }

        return super.close();
    }

    /**
     * Checks whether the printer data in the current print options are valid (i.e. a printer can be
     * created based on them). In addition, the pre-loaded printer name is checked to be non-empty
     * (as, at least on OSX, a printer can be properly instantiated based on the default printer data).
     *
     * If those checks are not passed properly
     *  the system's native print dialog is opened to let the user choose a valid printer.
     */
    private boolean checkPrinterData() {
        try {
            options.getPrinter();
            if (!Strings.isNullOrEmpty(options.getPrinterName())) {
                return true;
            }
        } catch (final Throwable e) {
            // nothing
        }

        final PrintDialog printDialog = new PrintDialog(getParentShell());
        printDialog.setText("Select a printer");

        final PrinterData data = printDialog.open();
        if (data != null) {
            options.setPrinterData(data);
            return true;
        } else {
            return false;
        }
    }
}
