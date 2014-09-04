/******************************************************************************
 * Copyright (c) 2002, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package de.cau.cs.kieler.klighd.ui.printing.preview;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.ui.printing.actions.PrintActionHelper;
import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingPluginImages;
import de.cau.cs.kieler.klighd.ui.printing.internal.PageData.PageMargins;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * Print Preview Action to display the Print Preview dialog. There are no static methods, so you
 * must create an instance of this class.
 * 
 * Call doPrintPreview() after you've made an instance.
 * 
 * This class should be combined with the DiagramPrinter to reuse functionality.
 * 
 * @author Wayne Diu, wdiu
 */
public class PrintPreviewHelper {

    /**
     * Action helper for print. This must be passed in to have something happen when print is
     * pressed
     */
    protected PrintActionHelper printActionHelper;

    /* SWT interface variables */

    /** Body of the shell. */
    protected Composite body;

    /** Composite for the pages */
    protected Composite composite;

    /** Height of the button bar, initialized right before the button bar is created. */
    protected int buttonBarHeight;

    /** Shell for the new pop up */
    protected Shell shell;

    /** Temporary shell to be used when creating the diagram editpart. */
    private Shell tempShell;

    /* Toolbar items */

    /** Enable or disable the print option */
    protected boolean enablePrinting = true;

    /* Images */

    /** List of images to dispose */
    private List<Image> imageList = new ArrayList<Image>();

    /** Enabled print image */
    protected Image printImage;

    /** Disabled Print image */
    protected Image disabledPrintImage;

    /** Page image, unlikely to ever be disabled */
    protected Image pageImage;

    /** Enabled left image */
    protected Image leftImage;

    /** Disabled left image */
    protected Image disabledLeftImage;

    /** Enabled right image */
    protected Image rightImage;

    /** Disabled right image */
    protected Image disabledRightImage;

    /** Enabled up image */
    protected Image upImage;

    /** Diabled up image */
    protected Image disabledUpImage;

    /** Enabled down image */
    protected Image downImage;

    /** Disabled down image */
    protected Image disabledDownImage;

    /** Close image, unlikely to ever be disabled */
    protected Image closeImage;

    /** Border size */
    protected static final int BORDER_SIZE = 20;

    /** the background color */
    private static final Color BACKGROUND_COLOR = new Color(Display.getDefault(), 124, 124, 124);

    private PrintOptions options;

    /** Initialize all toolbar images */
    protected void initializeToolbarImages() {
        printImage = DiagramUIPrintingPluginImages.DESC_PRINT.createImage();
        disabledPrintImage = DiagramUIPrintingPluginImages.DESC_PRINT_DISABLED.createImage();
        pageImage = DiagramUIPrintingPluginImages.DESC_PAGE.createImage();
        leftImage = DiagramUIPrintingPluginImages.DESC_LEFT.createImage();
        disabledLeftImage = DiagramUIPrintingPluginImages.DESC_LEFT_DISABLED.createImage();
        rightImage = DiagramUIPrintingPluginImages.DESC_RIGHT.createImage();
        disabledRightImage = DiagramUIPrintingPluginImages.DESC_RIGHT_DISABLED.createImage();
        upImage = DiagramUIPrintingPluginImages.DESC_UP.createImage();
        disabledUpImage = DiagramUIPrintingPluginImages.DESC_UP_DISABLED.createImage();
        downImage = DiagramUIPrintingPluginImages.DESC_DOWN.createImage();
        disabledDownImage = DiagramUIPrintingPluginImages.DESC_DOWN_DISABLED.createImage();
        closeImage = DiagramUIPrintingPluginImages.DESC_CLOSE.createImage();
    }

    /**
     * Enable or disable printing depending on where the print preview is invoked from.
     * 
     * @param enablePrinting
     */
    public void enablePrinting(boolean enablePrinting) {
        this.enablePrinting = enablePrinting;
    }

    /**
     * Do the print preview.
     * 
     * @param printActionHelper
     *            , an IPrintActionHelper with a doPrint method that will be called when the print
     *            button is pressed. I use this parameter so that this class can be extensible, e.g.
     *            ModelerPrintActionHelper will implement it for Modeler and something else will
     *            implement it for a different editor.
     */
    public void doPrintPreview(PrintActionHelper prActionHelper) {
        this.printActionHelper = prActionHelper;

//        if (!fitToPage) {
//        setUserScale(PrintHelperUtil.getScale());
//        }

//        if (getDiagramPart() == null) {
//            MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//                    .getShell(), DiagramUIPrintingMessages.PrintPreview_Title,
//                    DiagramUIPrintingMessages.PrintPreview_NotEnabled);
//            return;
//        }

        initializeToolbarImages();

        Display display = PlatformUI.getWorkbench().getDisplay();

        // check for rtl Torientation...
        int style = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getStyle();
        if ((style & SWT.MIRRORED) != 0) {
            shell =
                    new Shell(display, SWT.APPLICATION_MODAL | SWT.TITLE | SWT.CLOSE | SWT.BORDER
                            | SWT.RIGHT_TO_LEFT);
        } else
            shell = new Shell(display, SWT.APPLICATION_MODAL | SWT.TITLE | SWT.CLOSE | SWT.BORDER);

        shell.setSize(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getSize());
        shell.setText(DiagramUIPrintingMessages.PrintPreview_Title);
        shell.setLocation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
                .getLocation());
        shell.setLayout(new GridLayout(1, true));

        Composite toolBar = new Composite(shell, SWT.NONE);
        RowLayout layout = new RowLayout();
        layout.center = true;
//        layout.fill = true;
        layout.justify = true;
        layout.type = SWT.HORIZONTAL;
        toolBar.setLayout(layout);

        RowData separatorData = new RowData(SWT.DEFAULT, 1);

        Button printButton = new Button(toolBar, SWT.PUSH);

        printButton.setToolTipText(DiagramUIPrintingMessages.PrintPreview_PrintToolItem);
        printButton.setImage(printImage);
        printButton.addSelectionListener(new SelectionListener() {

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                // should not be enabled
                Assert.isNotNull(printActionHelper);

                printActionHelper.doPrint(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                        .getActivePage().getActivePart());
                shell.setActive();

            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
                widgetSelected(e);
            }
        });

        if (printActionHelper == null || !enablePrinting) {
            printButton.setEnabled(false);
            printButton.setImage(disabledPrintImage);
        }

        Label sep1 = new Label(toolBar, SWT.SEPARATOR);
        sep1.setLayoutData(separatorData);

        new Label(toolBar, SWT.NONE).setText("Pages:");

        final Spinner pageWideSpinner = new Spinner(toolBar, SWT.NONE);
        pageWideSpinner.setMinimum(1);
        pageWideSpinner.setMaximum(100);
        pageWideSpinner.setSelection(options.getFitToPagesWidth());

        new Label(toolBar, SWT.NONE).setText("x");

        final Spinner pageTallSpinner = new Spinner(toolBar, SWT.NONE);
        pageTallSpinner.setMinimum(1);
        pageTallSpinner.setMaximum(100);
        pageTallSpinner.setSelection(options.getFitToPagesHeight());

        pageWideSpinner.addSelectionListener(new SelectionAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                options.setFitToPagesWidth(pageWideSpinner.getSelection());
                updateComposite();
            }

        });

        pageTallSpinner.addSelectionListener(new SelectionAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                options.setFitToPagesHeight(pageTallSpinner.getSelection());
                updateComposite();
            }

        });

        RowData spinnerData = new RowData(20, SWT.DEFAULT);
        pageWideSpinner.setLayoutData(spinnerData);
        pageTallSpinner.setLayoutData(spinnerData);

//        leftTool = new ToolItem(toolBar, SWT.NULL);
//        if ((style & SWT.MIRRORED) != 0) {
//            // switch left and right for RTL...
//            leftTool.setToolTipText(DiagramUIPrintingMessages.PrintPreview_RightToolItem);
//            leftTool.setImage(rightImage);
//            leftTool.setDisabledImage(disabledRightImage);
//        } else {
//            leftTool.setToolTipText(DiagramUIPrintingMessages.PrintPreview_LeftToolItem);
//            leftTool.setImage(leftImage);
//            leftTool.setDisabledImage(disabledLeftImage);
//        }
//
//        leftTool.addSelectionListener(new SelectionListener() {
//
//            /**
//             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
//             */
//            public void widgetSelected(SelectionEvent e) {
//                if (userX > 0) {
//                    userX--;
//                    refreshComposite();
//                }
//            }
//
//            /**
//             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
//             */
//            public void widgetDefaultSelected(SelectionEvent e) {
//                widgetSelected(e);
//            }
//        });
//
//        rightTool = new ToolItem(bar, SWT.NULL);
//        if ((style & SWT.MIRRORED) != 0) {
//            // switch left and right for RTL
//            rightTool.setToolTipText(DiagramUIPrintingMessages.PrintPreview_LeftToolItem);
//            rightTool.setImage(leftImage);
//            rightTool.setDisabledImage(disabledLeftImage);
//        } else {
//            rightTool.setToolTipText(DiagramUIPrintingMessages.PrintPreview_RightToolItem);
//            rightTool.setImage(rightImage);
//            rightTool.setDisabledImage(disabledRightImage);
//        }
//
//        rightTool.addSelectionListener(new SelectionListener() {
//
//            /**
//             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
//             */
//            public void widgetSelected(SelectionEvent e) {
//                // check for max pages to be safe
//                if (!(userX + numberOfColumnsToDisplay + 1 > getTotalNumberOfColumns())) {
//                    userX++;
//                    refreshComposite();
//                }
//            }
//
//            /**
//             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
//             */
//            public void widgetDefaultSelected(SelectionEvent e) {
//                widgetSelected(e);
//            }
//        });
//
//        upTool = new ToolItem(bar, SWT.NULL);
//        upTool.setToolTipText(DiagramUIPrintingMessages.PrintPreview_UpToolItem);
//        upTool.setImage(upImage);
//        upTool.setDisabledImage(disabledUpImage);
//        upTool.addSelectionListener(new SelectionListener() {
//
//            /**
//             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
//             */
//            public void widgetSelected(SelectionEvent e) {
//                if (userY > 0) {
//                    userY--;
//                    refreshComposite();
//                }
//            }
//
//            /**
//             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
//             */
//            public void widgetDefaultSelected(SelectionEvent e) {
//                widgetSelected(e);
//            }
//        });
//
//        downTool = new ToolItem(bar, SWT.NULL);
//        downTool.setToolTipText(DiagramUIPrintingMessages.PrintPreview_DownToolItem);
//        downTool.setImage(downImage);
//        downTool.setDisabledImage(disabledDownImage);
//        downTool.addSelectionListener(new SelectionListener() {
//
//            /**
//             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
//             */
//            public void widgetSelected(SelectionEvent e) {
//                if (!(userY + numberOfRowsToDisplay + 1 > getTotalNumberOfRows())) {
//                    userY++;
//                    refreshComposite();
//                }
//            }
//
//            /**
//             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
//             */
//            public void widgetDefaultSelected(SelectionEvent e) {
//                widgetSelected(e);
//            }
//        });

        Label sep2 = new Label(toolBar, SWT.SEPARATOR);
        sep2.setLayoutData(separatorData);

        new Label(toolBar, SWT.NONE).setText("Scale:");

        final Scale factorScale = new Scale(toolBar, SWT.HORIZONTAL);
        factorScale.setMinimum(1);
        factorScale.setMaximum(100);
        factorScale.setLayoutData(new RowData(150, SWT.DEFAULT));
        factorScale.setSelection(options.getScaleFactor());

        final Label factorLabel = new Label(toolBar, SWT.NONE);
        GC gc = new GC(factorLabel);
        factorLabel.setLayoutData(new RowData(gc.textExtent("100%").x, SWT.DEFAULT));
        gc.dispose();
        factorLabel.setText(getDisplayScale(options.getScaleFactor()));

        factorScale.addSelectionListener(new SelectionAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                options.setScaleFactor(factorScale.getSelection());
                factorLabel.setText(getDisplayScale(options.getScaleFactor()));
                updateComposite();
            }

        });

//        ToolItem separator = new ToolItem(bar, SWT.SEPARATOR);
//        final Combo zoomCombo = new Combo(bar, SWT.DROP_DOWN);
//
////        zoomCombo.add(FIT_TO_PAGES);
//        for (int i = 0; i < zoomLevels.length; i++) {
//            zoomCombo.add(getDisplayScale(zoomLevels[i]));
//        }
//
////        if (this.fitToPage) {
////            zoomCombo.setText(FIT_TO_PAGES);
////            zoomCombo.select(0);
////        } else {
//        zoomCombo.setText(getDisplayScale(options.getScaleFactor()));
////        }
//
//        zoomCombo.addSelectionListener(new SelectionAdapter() {
//
//            /**
//             * Apply the zoom scale as indicated by the text in the combo-box selection.
//             * 
//             * @param combo
//             *            Obtain zoom information from the combo-box.
//             */
//            private void doZoom(Combo combo) {
//                String scaleFactor = combo.getText();
//                int percentageIndex = scaleFactor.indexOf("%"); //$NON-NLS-1$
//                if (percentageIndex > 0) {
//                    scaleFactor = scaleFactor.substring(0, percentageIndex);
//                }
//
//                int scalePercentage = options.getScaleFactor();
//                try {
//                    scalePercentage = Integer.parseInt(scaleFactor);
//                } catch (NumberFormatException e) {
//                    // Ignore invalid entry; default is last known acceptable value
//                }
//
//                if (scalePercentage < 5) {
//                    scalePercentage = 5;
//                }
//                options.setScaleFactor(scalePercentage);
//                refreshComposite();
//                combo.setText(getDisplayScale(scalePercentage));
//            }
//
//            /*
//             * (non-Javadoc)
//             * 
//             * @see
//             * org.eclipse.swt.events.SelectionAdapter#widgetDefaultSelected(org.eclipse.swt.events
//             * .SelectionEvent)
//             */
//            public void widgetDefaultSelected(SelectionEvent e) {
//                doZoom((Combo) e.getSource());
//            }
//
//            /*
//             * (non-Javadoc)
//             * 
//             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.
//             * SelectionEvent)
//             */
//            public void widgetSelected(SelectionEvent e) {
////                String selectedString = ((Combo) e.getSource()).getText();
////                if (FIT_TO_PAGES.compareToIgnoreCase(selectedString) == 0) {
////                    FitToPagesDialog fitToPages = new FitToPagesDialog(shell);
////                    if (fitToPages.open() == Dialog.OK) {
////                        int pagesWide = fitToPages.getPagesWide();
////                        int pagesTall = fitToPages.getPagesTall();
////                        options.setFitToPagesWidth(pagesWide);
////                        options.setFitToPagesHeight(pagesTall);
////
////                        setPages(pagesWide, pagesTall);
////                        refreshComposite();
////                    }
////                } else {
//                    doZoom((Combo) e.getSource());
////                }
//
//            }
//        });
//
//        zoomCombo.pack();
//        separator.setWidth(zoomCombo.getSize().x);
//        separator.setControl(zoomCombo);

        Label sep3 = new Label(toolBar, SWT.SEPARATOR);
        sep3.setLayoutData(separatorData);

        Button closeButton = new Button(toolBar, SWT.PUSH);
        closeButton.setToolTipText(DiagramUIPrintingMessages.PrintPreview_CloseToolItem);
        closeButton.setImage(closeImage);
        closeButton.addSelectionListener(new SelectionListener() {

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                dispose();
                shell.close();
                shell.dispose();
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
                widgetSelected(e);
            }
        });

        buttonBarHeight = toolBar.getBounds().height - toolBar.getBounds().y;
        separatorData.height = buttonBarHeight - layout.marginTop - layout.marginBottom;
        sep1.setLayoutData(separatorData);
        sep2.setLayoutData(separatorData);
        sep3.setLayoutData(separatorData);

        toolBar.setBounds(0, 0, shell.getSize().x, buttonBarHeight);

        // do the body in the middle
        body = new Composite(shell, SWT.NULL);
        body.setLayout(new GridLayout(1, true));
        body.setLayoutData(new GridData(GridData.FILL_BOTH));
        body.setBackground(BACKGROUND_COLOR);

        composite = new Composite(body, SWT.NULL);
        composite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));

        refreshComposite();

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

        dispose();
        shell.dispose();

    }

//    /**
//     * Initialize the map mode variable
//     */
//    private void initializeMapMode() {
//        DiagramEditor diagramEditor = getDiagramPart();
//
//        assert diagramEditor != null;
//
//        IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
//
//        if (viewer != null) {
//            RootEditPart rootEP = viewer.getRootEditPart();
//
//            if (rootEP instanceof DiagramRootEditPart) {
//                this.mm = ((DiagramRootEditPart) rootEP).getMapMode();
//                ;
//                return;
//
//            }
//        }
//
//        this.mm = MapModeUtil.getMapMode();
//    }

//    /**
//     * Return the diagram editor part that we are doing the print preview for
//     * 
//     * @return DiagramEditor, diagram editor part that we are doing the print preview for. I do not
//     *         return IDiagramWorkbenchPart because DiagramEditorPart contains some extra methods
//     *         for the page break checks.
//     */
//    private IDiagramWorkbenchPart getDiagramPart() {
//        // more explicit than using window
//        IWorkbenchPart editorPart =
//                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
//                        .getActivePart();
//
//        if (!(editorPart instanceof IDiagramWorkbenchPart)) {
//            return null;
//        }
//
//        IDiagramWorkbenchPart diagramEditorPart = (IDiagramWorkbenchPart) editorPart;
//
//        return diagramEditorPart;
//    }

//    /**
//     * Return the diagram bounds of the diagram we are doing the print preview for.
//     * 
//     * @return Rectangle with diagram bounds of the diagram we are doing the print preview for.
//     */
//    protected Rectangle getDiagramBounds() {
////        return PageInfoHelper.getChildrenBounds(getDiagramEditPart(), null);
//        // null because we are not ignoring anything
//        return new Rectangle(0, 0, 500, 500);
//    }
//
//    /**
//     * Return and cache the page break bounds of the diagram we are doing the print preview for.
//     * 
//     * @return Rectangle with page break bounds of the diagram we are doing the print preview for.
//     */
//    protected Rectangle getPageBreakBounds() {
////        if (pageBreakBounds == null) {
////            pageBreakBounds =
////                    PrintHelperUtil.getPageBreakBounds(getDiagramEditPart(), true).getCopy();
////        }
////
////        return pageBreakBounds;
//        return new Rectangle(0, 0, 500, 500);
//    }

//    /**
//     * Return the pageBreakBounds if possible. If not, return the diagram bounds.
//     * 
//     * @return Rectangle with page break bounds if possible. If not possible, just return the
//     *         diagram bounds.
//     */
//    protected Rectangle getBounds() {
//        // don't worry about storing it, it's cached
//        return (getPageBreakBounds() == null) ? getDiagramBounds() : getPageBreakBounds();
//    }

//    /**
//     * Return the diagram edit part that we are doing the print preview for. Uses
//     * getDiagramEditorPart().
//     * 
//     * @return DiagramEditPart, diagram edit part that we are doing the print preview for
//     */
//    protected DiagramEditPart getDiagramEditPart() {
//        if (diagramEditPart == null) {
//            diagramEditPart = getDiagramPart().getDiagramEditPart();
//        }
//        if (diagramEditPart == null) {
//            Diagram diagram = getDiagramPart().getDiagram(); // do not getDiagramEditPart
//            PreferencesHint preferencesHint = getPreferencesHint(getDiagramPart());
//            diagramEditPart =
//                    PrintHelperUtil.createDiagramEditPart(diagram, preferencesHint, getTempShell());
//            PrintHelperUtil.initializePreferences(diagramEditPart, preferencesHint);
//        }
//        return diagramEditPart;
//    }

//    /**
//     * Lazily creates a new shell.
//     * 
//     * @return
//     */
//    private Shell getTempShell() {
//        if (tempShell == null) {
//            tempShell = new Shell();
//        }
//        return tempShell;
//    }

//    protected PreferencesHint getPreferencesHint(IEditorPart editorPart) {
//        if (editorPart instanceof IDiagramWorkbenchPart) {
//            RootEditPart rootEP =
//                    ((IDiagramWorkbenchPart) editorPart).getDiagramGraphicalViewer()
//                            .getRootEditPart();
//            if (rootEP instanceof IDiagramPreferenceSupport) {
//                return ((IDiagramPreferenceSupport) rootEP).getPreferencesHint();
//            }
//        }
//        return PreferencesHint.USE_DEFAULTS;
//    }

//    /**
//     * Return and cache the total number of columns used by the diagram
//     * 
//     * @return int total number of columns used by the diagram
//     */
//    private int getTotalNumberOfColumns() {
//        return options.getFitToPagesWidth();
//    }
//
//    /**
//     * Return and cache the total number of rows used by the diagram
//     * 
//     * @return int total number of rows used by the diagram
//     */
//    private int getTotalNumberOfRows() {
//        return options.getFitToPagesHeight();
//    }

//    /**
//     * Make sure printer is installed. Should not be able to print preview if no printer is
//     * installed, even though technically it will work.
//     * 
//     * Call this immediately with the rest of the initialization.
//     */
//    protected boolean isPrinterInstalled() {
//        try {
//            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
//            return printServices.length > 0;
//        } catch (SWTError e) {
////            Trace.catching(DiagramPrintingPlugin.getInstance(),
////                    DiagramPrintingDebugOptions.EXCEPTIONS_CATCHING, PrintPreviewHelper.class,
////                    "isPrinterInstalled", //$NON-NLS-1$
////                    e);
//
//            if (e.code == SWT.ERROR_NO_HANDLES) {
//                // it might have really been ERROR_NO_HANDLES, but there's
//                // no way for me to really know
//                return false;
//            }
//
////            // if (e.code != SWT.ERROR_NO_HANDLES)
////            Log.error(DiagramPrintingPlugin.getInstance(),
////                    DiagramPrintingStatusCodes.GENERAL_UI_FAILURE,
////                    "Failed to make instance of Printer object", e); //$NON-NLS-1$
////
////            // else if another swt error
////            Trace.throwing(DiagramPrintingPlugin.getInstance(),
////                    DiagramPrintingDebugOptions.EXCEPTIONS_CATCHING, PrintPreviewHelper.class,
////                    "isPrinterInstalled", //$NON-NLS-1$
////                    e);
//            throw e;
//        }
//    }

    /**
     * Clean up by deleting the images in the image list
     */
    private void disposeImages() {
        for (Image image : imageList) {
            safeDisposeImage(image);
        }
        imageList.clear();
    }

    /**
     * A convenience method for refreshing the displayed image in the preview.
     */
    private void refreshComposite() {

        updateComposite();

        updateLeftRightUpDownButtonsForToolbar();
    }

    /**
     * Draw the composite centered on the body based on the number of columns. Also calls the method
     * to make the images and insert them into the composite.
     * 
     * @param numberOfRows
     *            the number of rows that the composite should contain. I need this to figure out
     *            the height of the image.
     * @param numberOfColumns
     *            the number of columns that the composite should contain. I need this to figure out
     *            the width of the image.
     */
    private void updateComposite() {
        Assert.isNotNull(shell);
        Assert.isNotNull(composite);

        Control[] children = composite.getChildren();
        for (int i = 0; i < children.length; i++) {
            children[i].dispose();
        }
        disposeImages();

        // the next two lines of code are intentional
        composite.setLayout(null);
        composite.pack();

        composite.setLayout(new GridLayout(options.getFitToPagesWidth(), true));

        // (shell height - toolbar height - top border - bottom border - title -
        // ((# of rows - 1) x vertical border between images)) / # of rows
        int imageHeight =
                (shell.getSize().y - buttonBarHeight - BORDER_SIZE - BORDER_SIZE - BORDER_SIZE - ((options
                        .getFitToPagesHeight() - 1) * BORDER_SIZE)) / options.getFitToPagesHeight();

        // (shell width - left border - right border - ((# of columns - 1) x
        // horizontal border between images)) / # of columns
        int imageWidth =
                (shell.getSize().x - BORDER_SIZE - BORDER_SIZE - ((options.getFitToPagesWidth() - 1) * BORDER_SIZE))
                        / options.getFitToPagesWidth();

        // now adjust to the limiting one based on aspect ratio

        // to make this conform to the page breaks, RATLC00247228
        // get printer ratio from the page, not the real printer

        MediaSize size = MediaSize.getMediaSizeForName(MediaSizeName.ISO_A4);

        // width / height
        float printerRatio = size.getX(MediaSize.MM) / size.getY(MediaSize.MM);

        if (imageHeight * printerRatio < imageWidth) {
            // round down
            imageWidth = (int) (imageHeight * printerRatio);
        } else if (imageWidth * (1 / printerRatio) < imageHeight) {
            // round down
            imageHeight = (int) (imageWidth * (1.0f / printerRatio));
        }

        // make sure height and width are not 0, if too small <4, don't bother
        if (!(imageHeight <= 4 || imageWidth <= 4)) {

//            // or imageWidth / pageSize.x
//            float scale =
//                    (imageHeight / (float) pageSize.y)
//                            / (float) DiagramMapModeUtil.getScale(getMapMode());
//
//            scale *= userScale;

            PageMargins margins = new PageMargins();

            final int DEFAULT_MARGIN = 25;

            float userScale = options.getScaleFactor() / 100f;

            margins.left = (int) (DEFAULT_MARGIN / userScale);
            margins.right = (int) (DEFAULT_MARGIN / userScale);
            margins.bottom = (int) (DEFAULT_MARGIN / userScale);
            margins.top = (int) (DEFAULT_MARGIN / userScale);

            int totalWidth = (int) (options.getFitToPagesWidth() * imageWidth * userScale);
            int totalHeight = (int) (options.getFitToPagesHeight() * imageHeight * userScale);
            for (int i = 0; i < options.getFitToPagesHeight(); i++) {
                for (int j = 0; j < options.getFitToPagesWidth(); j++) {
                    Label label = new Label(composite, SWT.NULL);
                    Image pageImg =
                            makeImage(totalWidth, totalHeight, new Rectangle(imageWidth * j,
                                    imageHeight * i, imageWidth, imageHeight));
                    label.setImage(pageImg);
                    imageList.add(pageImg);
                }
            }
        }

        composite.pack();

        // GridData.VERTICAL_ALIGN_CENTER | GridData.HORIZONTAL_ALIGN_CENTER
        // won't do it for you
        org.eclipse.swt.graphics.Rectangle compositeBounds = composite.getBounds();

        // this approximation is OK
        compositeBounds.x = (shell.getSize().x - BORDER_SIZE - compositeBounds.width) / 2;
        compositeBounds.y =
                (shell.getSize().y - buttonBarHeight - BORDER_SIZE - BORDER_SIZE - BORDER_SIZE - compositeBounds.height) / 2;
        composite.setBounds(compositeBounds);
    }

    /**
     * Update the enabled and disabled states for the toolbar
     */
    protected void updateLeftRightUpDownButtonsForToolbar() {
//        if (userX == 0) {
//            leftTool.setEnabled(false);
//        } else {
//            leftTool.setEnabled(true);
//        }
//
//        // should be (user + 1) + (display - 1), the +1 and -1 can be taken out
//        if (userX + numberOfColumnsToDisplay + 1 > getTotalNumberOfColumns()) {
//            rightTool.setEnabled(false);
//        } else {
//            rightTool.setEnabled(true);
//        }
//
//        if (userY == 0) {
//            upTool.setEnabled(false);
//        } else {
//            upTool.setEnabled(true);
//        }
//
//        if (userY + numberOfRowsToDisplay + 1 > getTotalNumberOfRows()) {
//            downTool.setEnabled(false);
//        } else {
//            downTool.setEnabled(true);
//        }
    }

    /**
     * Makes the image for the location at row, col. row and col start from 0. The image will have a
     * size of imageWidth x imageHeight.
     * 
     * @param imageWidth
     *            int of pixels of width of image
     * @param imageWidth
     *            int of pixels of height of image
     * @param row
     *            int of row to make image at, starting at 0
     * @param col
     *            int of column to make image at, starting at 0
     * @return Image of size imageWidth * imageHeight
     */
    protected Image makeImage(int totalWidth, int totalHeight, Rectangle clip) {

        Image image = new Image(shell.getDisplay(), clip.width, clip.height);

        GC gc = null;

        // check for rtl orientation...
        if ((shell.getStyle() & SWT.MIRRORED) != 0) {
            gc = new GC(image, SWT.RIGHT_TO_LEFT);
        } else
            gc = new GC(image);

        Transform transform = new Transform(gc.getDevice());
        gc.getTransform(transform);
        transform.translate(-clip.x, -clip.y);
        gc.setTransform(transform);

        gc.setClipping(clip);

        ((PiccoloViewer) ((IDiagramWorkbenchPart) PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage().getActivePart()).getViewer()
                .getContextViewer().getActiveViewer()).renderOffscreen(gc,
                new org.eclipse.swt.graphics.Rectangle(0, 0, totalWidth, totalHeight));

        gc.dispose();

        return image;
    }

//    /**
//     * Convenience method to determine if a page at row y and column x exists. Pages start at 1.
//     * 
//     * For example, the first page is 1-1.
//     * 
//     * @param x
//     *            , column number of the page to check
//     * @param y
//     *            , row number of the page to check
//     * 
//     * @return boolean true if the page exists, false if it doesn't
//     */
//    private boolean doesPageExist(int x, int y) {
//        return x > 0 && y > 0 && x <= getTotalNumberOfColumns() && y <= getTotalNumberOfRows();
//    }

    /**
     * Safely dispose an image
     * 
     * @param image
     *            , the Image to dispose.
     */
    private void safeDisposeImage(Image image) {
        if (image != null && !image.isDisposed())
            image.dispose();
    }

    /**
     * Dispose resources.
     */
    protected void dispose() {
        disposeImages();
        safeDisposeImage(printImage);
        safeDisposeImage(disabledPrintImage);
        safeDisposeImage(pageImage);
        safeDisposeImage(leftImage);
        safeDisposeImage(disabledLeftImage);
        safeDisposeImage(rightImage);
        safeDisposeImage(disabledRightImage);
        safeDisposeImage(upImage);
        safeDisposeImage(disabledUpImage);
        safeDisposeImage(downImage);
        safeDisposeImage(disabledDownImage);
        safeDisposeImage(closeImage);
        if (tempShell != null) {
            tempShell.dispose();
            tempShell = null;
        }
    }

    /**
     * Prepare a string appropriate to show the scale factor to the user.
     * 
     * @param scale
     *            the scale factor, an integer greater than 0.
     * @return A string of the scale factor to be displayed to the user.
     */
    private String getDisplayScale(int scale) {
        return String.valueOf(scale) + "%"; //$NON-NLS-1$
    }

//    /**
//     * Determine the page count when fit to page is used.
//     * 
//     * @param dgrmEP
//     *            - The diagram edit part
//     * @param figureBounds
//     *            - The bounds of the figure
//     * @param pageSize
//     *            - Page size
//     * @param applyUserScale
//     *            - The user scale
//     * @return Point.x contains the total number of pages that span in a column Point.y contains the
//     *         total number of pages that span in a row
//     */
//    protected org.eclipse.draw2d.geometry.Point getPageCount(DiagramEditPart dgrmEP,
//            Rectangle figureBounds, org.eclipse.draw2d.geometry.Point pageSize,
//            boolean applyUserScale) {
//        RootEditPart rootEditPart = dgrmEP.getRoot();
//
//        if (rootEditPart instanceof DiagramRootEditPart) {
//
//            DiagramRootEditPart diagramRootEditPart = (DiagramRootEditPart) rootEditPart;
//            PageBreakEditPart pageBreakEditPart = diagramRootEditPart.getPageBreakEditPart();
//
//            double fNumCols =
//                    ((PageBreaksFigure) pageBreakEditPart.getFigure()).getPageCount().y
//                            * (applyUserScale ? userScale : 1);
//
//            double fNumRows =
//                    ((PageBreaksFigure) pageBreakEditPart.getFigure()).getPageCount().x
//                            * (applyUserScale ? userScale : 1);
//
//            int numCols = (int) Math.ceil(fNumCols);
//            int numRows = (int) Math.ceil(fNumRows);
//
//            return new org.eclipse.draw2d.geometry.Point(numCols, numRows);
//
//        } else {
//            double fNumRows = (figureBounds.height * (applyUserScale ? userScale : 1)) / pageSize.y;
//            int numRows = (int) Math.ceil(fNumRows);
//
//            double fNumCols = (figureBounds.width * (applyUserScale ? userScale : 1)) / pageSize.x;
//            int numCols = (int) Math.ceil(fNumCols);
//
//            return new org.eclipse.draw2d.geometry.Point(numCols, numRows);
//        }
//    }

//    /**
//     * @param scaleFactor
//     */
//    public void setScaleFactor(int scaleFactor) {
//        setUserScale(scaleFactor);
//    }
//
//    /**
//     * Recalculates a zoom ratio that can be used when displaying fit to page.
//     * 
//     * @param rows
//     *            The number of rows to fit the display to.
//     * @param columns
//     *            The number of columns to fit the display to.
//     */
//    public void setPages(int width, int height) {
//
//        pageCols = width;
//        pageRows = height;
//    }

    /**
     * @param options
     */
    public void setOptions(PrintOptions options) {
        this.options = options;
    }

}
