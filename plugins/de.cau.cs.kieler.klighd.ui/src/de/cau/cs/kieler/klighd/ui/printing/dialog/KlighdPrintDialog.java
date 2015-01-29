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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.ui.printing.DiagramPrintOptions;
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
    private final boolean previewEnabled;

    private PrinterBlock printerBlock;
    private ScalingBlock scalingBlock;
    private RangeBlock rangeBlock;
    private CopiesBlock copiesBlock;
    private AlignmentBlock alignmentBlock;
    private ActionsBlock actionsBlock;

    /**
     * Creates a new KLighD print dialog based on the given options.
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
        this.previewEnabled = options instanceof DiagramPrintOptions;
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
        setShellStyle(getShellStyle() | SWT.MAX | SWT.RESIZE);
        this.options = options;
        this.previewEnabled = options instanceof DiagramPrintOptions;
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * Specialization first checks whether a valid printer configuration can be loaded from the
     * saved configuration (preferences). If so this dialog is opened in blocking mode. Otherwise
     * this dialog is opened (in background) without blocking and the native printer selection
     * dialog is opened on top (in blocking mode).<br>
     * If the user cancels the printer selections (<code>null</code> is returned by
     * {@link PrintDialog#open()}) the background instance of this dialog is closed and the
     * {@link IDialogConstants#CANCEL_ID} is returned.<br>
     * If the user properly selects a printer the returned printer data are applied to
     * {@link #options} {@code super.open()} is (again) called in blocking mode. The implementation
     * of {@code super.open()} checks whether the dialog is already visible on the screen and,
     * hence, skips the creation in this case but starts rather immediately the blocking event
     * listening loop.
     */
    @Override
    public final int open() {

        if (!checkPrinterData()) {
            setBlockOnOpen(false);
            super.open();

            final PrinterData data = getNativePrintDialog().open();
            if (data == null) {
                this.close();
                return IDialogConstants.CANCEL_ID;

            } else {
                options.setPrinterData(data);
            }
        }

        setBlockOnOpen(true);
        return super.open();
    }

    /**
     * Checks whether the printer data in the current print options are valid (i.e. an SWT Printer
     * can be created based on them).<br>
     * In addition, the configured printer name is checked to be non-empty (since, at least on OSX,
     * a printer can be properly instantiated based on the default printer data).
     *
     * @return <code>true</code> if a valid printer configuration is available, <code>false</code>
     *         otherwise.
     */
    private boolean checkPrinterData() {
        try {
            options.getPrinter();
            return true;

        } catch (final Throwable e) {
            return false;
        }
    }

    /**
     * Convenience factory method providing the native printer selection dialog.
     *
     * @return the native {@link PrintDialog}
     */
    protected PrintDialog getNativePrintDialog() {
        final PrintDialog printDialog = new PrintDialog(getParentShell());
        printDialog.setText(KlighdUIPrintingMessages.KlighdPrintDialog_InitialDialog_title);

        return printDialog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(KlighdUIPrintingMessages.KlighdPrintDialog_Title);
        setHelpAvailable(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        bindings = new DataBindingContext(SWTObservables.getRealm(parent.getDisplay()));

        final boolean previewInitiallyOpen =
                previewEnabled && DiagramPrintOptions.getInitiallyShowPreview() && checkPrinterData();

        final Composite result = new Composite(parent, SWT.NONE);
        DialogUtil.layout(result, 2);

        createPrinterBlockArea(result);
        createScalingBlockArea(result);
        createAlignmentBlockArea(result);
        createRangeBlockArea(result);
        createCopiesBlockArea(result);
        createExtensibleBlockArea(result);
        createActionsBlockArea(result, false);

        if (previewInitiallyOpen) {

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
    protected Button createButton(final Composite parent, final int id, final String label,
            final boolean defaultButton) {
        // method is overridden in order to change the OK button's label
        if (id == IDialogConstants.OK_ID) {
            return super.createButton(parent, id,
                    KlighdUIPrintingMessages.KlighdPrintDialog_OK_label, defaultButton);
        } else {
            return super.createButton(parent, id, label, defaultButton);
        }
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
         final Composite group = (Composite) copiesBlock.createContents(parent);

        if (KlighdPlugin.IS_MACOSX) {
            // I deactivated 'copies' block as this information can be changed in the native dialog
            //  but (on OSX) it is not delivered back within the returned PrinterData.
            // Thus, 'copies' cannot be updated properly.
            // This, however, seems not to hold for windows and linux.
            group.setToolTipText(KlighdUIPrintingMessages.KlighdPrintDialog_Copies_OSXToolTip);

            for (final Control con : group.getChildren()) {
                con.setEnabled(false);
            }
        }
    }

    /**
     * Creates the copies block area.
     *
     * @param parent the parent composite
     */
    protected void createAlignmentBlockArea(final Composite parent) {
         alignmentBlock = new AlignmentBlock(bindings, options);
         DialogUtil.layoutSpanHorizontal(alignmentBlock.createContents(parent), 2);
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
     * @param parent
     *            the parent composite
     * @param previewInitiallyOpen determines the text of the preview button; must be
     *            <code>true</code> if preview is initially visible, <code>false</code> otherwise
     */
    protected void createActionsBlockArea(final Composite parent, final boolean previewInitiallyOpen) {
        actionsBlock = new ActionsBlock(this, previewEnabled, previewInitiallyOpen);
        DialogUtil.layoutSpanHorizontal(actionsBlock.createContents(parent), 2);
    }

    /**
     * Opens the print preview by injecting a corresponding {@link PrintPreviewTray}.
     */
    public void openPreview() {
        if (previewEnabled) {
            this.openTray(new PrintPreviewTray(bindings, (DiagramPrintOptions) options));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonPressed(final int buttonId) {
        options.storeToPreferences();
//        switch (buttonId) {
//        case IDialogConstants.OK_ID:
//            // fall through!
//        default:
        super.buttonPressed(buttonId);
//        }
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

        final PrintPreviewTray printPreviewTray = this.getTray();
        if (printPreviewTray != null) {
            printPreviewTray.dispose();
        }

        return super.close();
    }
}
