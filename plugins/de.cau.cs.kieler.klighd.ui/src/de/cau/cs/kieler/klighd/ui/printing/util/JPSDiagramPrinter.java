/******************************************************************************
 * Copyright (c) 2008, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package de.cau.cs.kieler.klighd.ui.printing.util;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.SheetCollate;
import javax.print.attribute.standard.Sides;

import org.eclipse.core.internal.runtime.Log;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.ScaledGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.internal.PageData;
import de.cau.cs.kieler.klighd.ui.printing.internal.PageData.PageMargins;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * This class supports printing using the Java Print Service API. The logic of
 * calculating page break etc. follows that of SWT printing but the actual
 * printing is done asynchronously in a platform independent way.
 * 
 * Much of the paging code was derived from the previous DiagramPrinter.
 * 
 * @author James Bruck (jbruck)
 */
public class JPSDiagramPrinter extends DiagramPrinter implements java.awt.print.Printable {

    // A constant that takes into account screen display DPI and the graphic DPI
    // 72.0 DPI is an AWT constant @see java.awt.Graphics2D
    private static double AWT_DPI_CONST = 72.0;

    // The print service used during printing.
    private PrintService printService;

    // Page information that is collected up front and used during the async
    // printing calls.
    private PageData[] pages;

    /**
     * Create a new print service given a printer name.
     * 
     * @param printerName
     */
    public void setPrinter(String printerName) {
        AttributeSet attributes =
                new HashPrintServiceAttributeSet(new PrinterName(printerName, Locale.getDefault()));
        PrintService[] services =
                PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PRINTABLE,
                        attributes);
        printService = services[0];
    }

    /**
     * Prints the contents of the diagram editor part.
     */
    public void run() {

        Iterator<PiccoloViewer> it = diagrams.iterator();
        Shell shell = new Shell();
        try {
            while (it.hasNext()) {
                PiccoloViewer diagram = it.next();
                DiagramEditor openedDiagramEditor =
                        DiagramEditorUtil.findOpenedDiagramEditorForID(ViewUtil.getIdStr(diagram));
                DiagramEditPart dgrmEP =
                        openedDiagramEditor == null ? PrintHelperUtil.createDiagramEditPart(
                                diagram, preferencesHint, shell) : openedDiagramEditor
                                .getDiagramEditPart();

                boolean loadedPreferences =
                        openedDiagramEditor != null
                                || PrintHelperUtil.initializePreferences(dgrmEP, preferencesHint);

                RootEditPart rep = dgrmEP.getRoot();
                if (rep instanceof DiagramRootEditPart) {
                    this.mapMode = ((DiagramRootEditPart) rep).getMapMode();
                }

                IPreferenceStore preferenceStore =
                        ((DiagramGraphicalViewer) dgrmEP.getViewer())
                                .getWorkspaceViewerPreferenceStore();
                if (preferenceStore
                        .getBoolean(WorkspaceViewerProperties.PREF_USE_WORKSPACE_SETTINGS)) {
                    if (dgrmEP.getDiagramPreferencesHint().getPreferenceStore() != null) {
                        preferenceStore =
                                (IPreferenceStore) dgrmEP.getDiagramPreferencesHint()
                                        .getPreferenceStore();
                    }
                }
                doPrintDiagram(printService.createPrintJob(), dgrmEP, loadedPreferences,
                        preferenceStore);
            }
        } finally {
            dispose();
            shell.dispose();
        }
    }

    /**
     * Print the diagram figure using specified scale factor.
     * 
     * @param dgrmEP
     *            The diagram edit part to print
     * @param loadedPreferences
     *            true if existing prefs could be loaded successfully, false if
     *            not and defaults are being used. This parameter is important
     *            to obtain the correct page break bounds.
     * @param fPreferences
     *            the preferenceStore that could either contain existing
     *            preferences or defaults
     */
    protected void printToScale(DiagramEditPart dgrmEP, boolean loadedPreferences,
            IPreferenceStore fPreferences) {

        Rectangle figureBounds = PrintHelperUtil.getPageBreakBounds(dgrmEP, loadedPreferences);

        org.eclipse.draw2d.geometry.Point pageBounds =
                PageInfoHelper.getPageSize(fPreferences, getMapMode());
        //
        // Translate to offset initial figure position
        //
        translated =
                new Point((int) (-figureBounds.x * userScale), (int) (-figureBounds.y * userScale));
        //
        // Calculate the number of page rows and columns
        //
        int numRows = 0, numCols = 0;

        PageMargins margins =
                adjustMarginsToScale(PageInfoHelper.getPageMargins(fPreferences, getMapMode()));

        FontData fontData = JFaceResources.getDefaultFont().getFontData()[0];

        org.eclipse.draw2d.geometry.Point pageCount =
                getPageCount(dgrmEP, figureBounds, pageBounds, true);

        numCols = pageCount.x;
        numRows = pageCount.y;

        int row = 1, col = 1, finalRow = 0, finalColumn = 0;

        List<PageData> pageList = new java.util.ArrayList<PageData>();

        if (this.printRangePageSelection) {
            //
            // Print only the pages specified in the page range...
            //
            row = calculateRowFromPage(this.pageFrom, numCols);
            col = calculateColumnFromPage(this.pageFrom, numCols, row);

            finalRow = calculateRowFromPage(this.pageTo, numCols);
            finalColumn = calculateColumnFromPage(this.pageTo, numCols, finalRow);
        }
        //
        // Print the pages in row, column order
        //
        for (; row <= numRows; row++) {
            for (; col <= numCols; col++) {

                pageList.add(new PageData(pageList.size(), row, col, dgrmEP, figureBounds, margins,
                        fontData, fPreferences));

                if (row == finalRow && col == finalColumn && this.printRangePageSelection == true) {
                    break;
                }
            }
            if (row == finalRow && col == finalColumn && this.printRangePageSelection == true) {
                break;
            }
            col = 1;
        }
        pages = pageList.toArray(new PageData[pageList.size()]);
    }

    /**
     * Print the diagram figure to fit the number and rows and columns specified
     * by the user.
     * 
     * @param dgrmEP
     *            The diagram edit part to print
     * @param loadedPreferences
     *            true if existing prefs could be loaded successfully, false if
     *            not and defaults are being used. This parameter is important
     *            to obtain the correct page break bounds.
     * @param fPreferences
     *            the preferenceStore that could either contain existing
     *            preferences or defaults
     */
    protected void printToPages(DiagramEditPart dgrmEP, boolean loadedPreferences,
            IPreferenceStore fPreferences) {

        Rectangle figureBounds = PrintHelperUtil.getPageBreakBounds(dgrmEP, loadedPreferences);

        org.eclipse.draw2d.geometry.Point pageBounds =
                PageInfoHelper.getPageSize(fPreferences, getMapMode());
        org.eclipse.draw2d.geometry.Point pageCount =
                getPageCount(dgrmEP, figureBounds, pageBounds, false);
        int numCols = pageCount.x;
        int numRows = pageCount.y;

        float actualWidth = 0;
        float actualHeight = 0;
        if (this.rows == 1 && this.columns == 1 && fitToPage) {
            figureBounds = dgrmEP.getChildrenBounds();
            actualWidth = figureBounds.width;
            actualHeight = figureBounds.height;
        } else {
            actualWidth = numCols * pageBounds.x;
            actualHeight = numRows * pageBounds.y;
        }

        int totalHeight = (this.rows * pageBounds.y);
        int totalWidth = (this.columns * pageBounds.x);

        float vScale = totalHeight / actualHeight;
        float hScale = totalWidth / actualWidth;

        this.userScale = Math.min(hScale, vScale);

        PageMargins margins =
                adjustMarginsToScale(PageInfoHelper.getPageMargins(fPreferences, getMapMode()));

        translated =
                new Point((int) (-figureBounds.x * userScale), (int) (-figureBounds.y * userScale));

        FontData fontData = JFaceResources.getDefaultFont().getFontData()[0];

        int row = 1, col = 1, finalRow = 0, finalColumn = 0;
        List<PageData> pageList = new java.util.ArrayList<PageData>();

        if (this.printRangePageSelection) {
            //
            // Print only the pages specified in the page range
            // this corresponds to the physical pages, not the print range of
            // pages on one physical page.
            //
            row = calculateRowFromPage(this.pageFrom, this.columns);
            col = calculateColumnFromPage(this.pageFrom, this.columns, row);

            finalRow = calculateRowFromPage(this.pageTo, this.columns);
            finalColumn = calculateColumnFromPage(this.pageTo, this.columns, finalRow);
        }

        for (; row <= rows; row++) {
            for (; col <= columns; col++) {

                pageList.add(new PageData(pageList.size(), row, col, dgrmEP, figureBounds, margins,
                        fontData, fPreferences));

                if (row == finalRow && col == finalColumn && this.printRangePageSelection == true) {
                    break;
                }
            }
            if (row == finalRow && col == finalColumn && this.printRangePageSelection == true) {
                break;
            }
            col = 1;
        }
        pages = pageList.toArray(new PageData[pageList.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.print.Printable#print(java.awt.Graphics,
     * java.awt.print.PageFormat, int)
     */
    public int print(java.awt.Graphics printGraphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {

        if (pageIndex >= pages.length) {
            return java.awt.print.Printable.NO_SUCH_PAGE;
        }

        try {
            printGraphics.setClip(0, 0, (int) pageFormat.getWidth(), (int) pageFormat.getHeight());

            swtGraphics =
                    new PrinterGraphicsToGraphics2DAdapter((java.awt.Graphics2D) printGraphics,
                            new Rectangle(0, 0, (int) pageFormat.getWidth(),
                                    (int) pageFormat.getHeight()));

            graphics = createMapModeGraphics(createPrinterGraphics(swtGraphics));
            graphics.scale(AWT_DPI_CONST / display_dpi.x);
            drawPage(pages[pageIndex]);
        } finally {
            dispose();

        }

        return java.awt.print.Printable.PAGE_EXISTS;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.gmf.runtime.diagram.ui.printing.internal.util.DiagramPrinter
     * #createMapModeGraphics(org.eclipse.draw2d.Graphics)
     */
    protected MapModeGraphics createMapModeGraphics(Graphics theGraphics) {
        return new RenderedMapModeGraphics(theGraphics, getMapMode());
    }

    protected ScaledGraphics createPrinterGraphics(Graphics theGraphics) {
        return new RenderedScaledGraphics(theGraphics);
    }

    /**
     * Set printing options in a format that is suitable for the Java print
     * service
     * 
     * @param jobName
     *            The printer job name to use
     * @param fPreferences
     *            obtain page information from preferences
     * @return PrintRequestAttribute set suitable for Java print service
     */
    protected PrintRequestAttributeSet initializePrintOptions(DocPrintJob printJob, String jobName,
            IPreferenceStore fPreferences) {

        PrintOptions advancedOptions = ((PrintHelper) (printHelper)).getPrintOptions();

        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();

        if (fPreferences.getBoolean(WorkspaceViewerProperties.PREF_USE_PORTRAIT)) {
            printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
        } else {
            printRequestAttributeSet.add(OrientationRequested.LANDSCAPE);
        }

        String pageSize = fPreferences.getString(WorkspaceViewerProperties.PREF_PAGE_SIZE);

        if (pageSize.compareToIgnoreCase(PageSetupPageType.LETTER.getName()) == 0) {
            printRequestAttributeSet.add(MediaSizeName.NA_LETTER);
        } else if (pageSize.compareToIgnoreCase(PageSetupPageType.LEGAL.getName()) == 0) {
            printRequestAttributeSet.add(MediaSizeName.NA_LEGAL);
        } else if (pageSize.compareToIgnoreCase(PageSetupPageType.EXECUTIVE.getName()) == 0) {
            printRequestAttributeSet.add(MediaSizeName.EXECUTIVE);
        } else if (pageSize.compareToIgnoreCase(PageSetupPageType.A3.getName()) == 0) {
            printRequestAttributeSet.add(MediaSizeName.ISO_A3);
        } else if (pageSize.compareToIgnoreCase(PageSetupPageType.A4.getName()) == 0) {
            printRequestAttributeSet.add(MediaSizeName.ISO_A4);
        } else if (pageSize.compareToIgnoreCase(PageSetupPageType.B4.getName()) == 0) {
            printRequestAttributeSet.add(MediaSizeName.ISO_B4);
        } else if (pageSize.compareToIgnoreCase(PageSetupPageType.B5.getName()) == 0) {
            printRequestAttributeSet.add(MediaSizeName.ISO_B5);
        }
        if (advancedOptions.isQualityLow()) {
            printRequestAttributeSet.add(PrintQuality.DRAFT);
        } else if (advancedOptions.isQualityMed()) {
            printRequestAttributeSet.add(PrintQuality.NORMAL);
        } else if (advancedOptions.isQualityHigh()) {
            printRequestAttributeSet.add(PrintQuality.HIGH);
        }
        if (advancedOptions.isSideDuplex()) {
            printRequestAttributeSet.add(Sides.DUPLEX);
        } else if (advancedOptions.isSideOneSided()) {
            printRequestAttributeSet.add(Sides.ONE_SIDED);
        } else if (advancedOptions.isSideTumble()) {
            printRequestAttributeSet.add(Sides.TUMBLE);
        }
        if (advancedOptions.isChromaticityColor()) {
            printRequestAttributeSet.add(Chromaticity.COLOR);
        } else {
            printRequestAttributeSet.add(Chromaticity.MONOCHROME);
        }

        final MediaSize mediaSize = getMediaSize(pageSize, printRequestAttributeSet, fPreferences);

        printRequestAttributeSet.add(new MediaPrintableArea(0f, 0f,
                (mediaSize.getX(MediaSize.INCH)), (mediaSize.getY(MediaSize.INCH)),
                MediaPrintableArea.INCH));

        printRequestAttributeSet.add(new Copies(printHelper.getDlgNumberOfCopies()));

        if (printHelper.getDlgCollate()) {
            printRequestAttributeSet.add(SheetCollate.COLLATED);
        } else {
            printRequestAttributeSet.add(SheetCollate.UNCOLLATED);
        }

        String userJobName = advancedOptions.getJobName();
        if (userJobName != null && userJobName.length() > 0) {
            jobName = userJobName;
        }
        printRequestAttributeSet.add(new JobName(jobName, Locale.getDefault()));

        return printRequestAttributeSet;
    }

    /**
     * Get the media size to use for printing.
     * 
     * @return the media size. <code>null</code> is never returned.
     */
    private MediaSize getMediaSize(String pageSize,
            PrintRequestAttributeSet printRequestAttributeSet, IPreferenceStore fPreferences) {

        MediaSize mediaSize;

        if (pageSize.compareToIgnoreCase(PageSetupPageType.USER_DEFINED.getName()) == 0) {
            float width = (float) fPreferences.getDouble(WorkspaceViewerProperties.PREF_PAGE_WIDTH);
            float height =
                    (float) fPreferences.getDouble(WorkspaceViewerProperties.PREF_PAGE_HEIGHT);
            int units =
                    fPreferences.getBoolean(WorkspaceViewerProperties.PREF_USE_INCHES) ? MediaSize.INCH
                            : MediaSize.MM;
            mediaSize = new MediaSize(width, height, units);
        } else {
            MediaSizeName media = (MediaSizeName) printRequestAttributeSet.get(Media.class);
            mediaSize = MediaSize.getMediaSizeForName(media);
        }
        /* we were not able to find the media size, use the default one */
        if (mediaSize == null) {
            mediaSize = MediaSize.ISO.A4;
        }
        return mediaSize;
    }

    /**
     * Prints to scale or prints to rows x columns pages
     * 
     * @param printJob
     * @param diagramEditPart
     * @param loadedPreferences
     * @param fPreferences
     */
    protected void doPrintDiagram(DocPrintJob printJob, DiagramEditPart diagramEditPart,
            boolean loadedPreferences, IPreferenceStore fPreferences) {

        PrintRequestAttributeSet printRequestAttributeSet =
                initializePrintOptions(printJob, diagramEditPart.getDiagramView().getName(),
                        fPreferences);

        if (isScaledPercent) {
            printToScale(diagramEditPart, loadedPreferences, fPreferences);
        } else {
            printToPages(diagramEditPart, loadedPreferences, fPreferences);
        }

        Doc doc =
                new SimpleDoc(this, DocFlavor.SERVICE_FORMATTED.PRINTABLE,
                        new HashDocAttributeSet());

        try {
            printJob.print(doc, printRequestAttributeSet);
        } catch (PrintException e) {

            Trace.catching(DiagramPrintingPlugin.getInstance(),
                    DiagramPrintingDebugOptions.EXCEPTIONS_CATCHING, DiagramPrinterUtil.class,
                    e.getMessage(), e);
            Log.warning(DiagramPrintingPlugin.getInstance(),
                    DiagramPrintingStatusCodes.RESOURCE_FAILURE, e.getMessage(), e);

            Display display = diagramEditPart.getViewer().getControl().getDisplay();
            if (display == null) {
                display = DisplayUtils.getDisplay();
            }

            MessageDialog.openError(display.getActiveShell(),
                    DiagramUIPrintingMessages.JPSDiagramPrinterUtil_ErrorTitle,
                    DiagramUIPrintingMessages.JPSDiagramPrinterUtil_ErrorMessage);
        }
    }

    /**
     * 
     * This method paints a portion of the diagram. (The area painted
     * representing one page.)
     * 
     * @param page
     *            indicates which page to print.
     */
    protected void drawPage(PageData page) {

        this.graphics.pushState();

        Display display =
                (Display.getCurrent() != null) ? Display.getCurrent() : Display.getDefault();

        int shellStyle = display.getActiveShell().getStyle();
        boolean rtlEnabled = (shellStyle & SWT.MIRRORED) != 0;

        if (rtlEnabled) {

            org.eclipse.draw2d.geometry.Point pageSize =
                    PageInfoHelper.getPageSize(page.preferences, false, getMapMode());

            Image image =
                    new Image(display, getMapMode().LPtoDP(pageSize.x), getMapMode().LPtoDP(
                            pageSize.y));

            GC imgGC = new GC(image, SWT.RIGHT_TO_LEFT);
            SWTGraphics tempSWTGraphic = new SWTGraphics(imgGC);
            ScaledGraphics tempScaledGraphic = new RenderedScaledGraphics(tempSWTGraphic);
            MapModeGraphics tempMapModeGraphic = createMapModeGraphics(tempScaledGraphic);

            imgGC.setFont(tempMapModeGraphic.getFont());

            internalDrawPage(page.diagram, page.bounds, page.preferences, page.margins,
                    tempMapModeGraphic, page.row, page.column, true);

            this.graphics.drawImage(image, 0, 0);

            tempMapModeGraphic.disposeImages();
            tempScaledGraphic.dispose();
            tempSWTGraphic.dispose();
            imgGC.dispose();
            image.dispose();

        } else {

            internalDrawPage(page.diagram, page.bounds, page.preferences, page.margins, graphics,
                    page.row, page.column, false);
        }

        this.graphics.popState();
    }

    /**
     * The real rendering of the page to the given graphical object occurs here.
     */
    protected void internalDrawPage(DiagramEditPart dgrmEP, Rectangle figureBounds,
            IPreferenceStore fPreferences, PageMargins margins, Graphics g, int rowIndex,
            int colIndex, boolean RTL_ENABLED) {

        org.eclipse.draw2d.geometry.Point pageSize =
                PageInfoHelper.getPageSize(fPreferences, false, getMapMode());

        int width = pageSize.x, height = pageSize.y;

        g.pushState();

        g.translate(translated.x, translated.y);
        g.scale(userScale);

        int translateX = -(width * (colIndex - 1));
        int translateY = -(height * (rowIndex - 1));

        int scaledTranslateX = (int) (translateX / userScale);
        int scaledTranslateY = (int) (translateY / userScale);

        int scaledWidth = (int) (width / userScale);
        int scaledHeight = (int) (height / userScale);

        if (RTL_ENABLED) {
            scaledTranslateX += (margins.left * (colIndex - 1)) + (margins.right * (colIndex));
            scaledTranslateY += ((margins.top * rowIndex) + (margins.bottom * (rowIndex - 1)));
        } else {
            scaledTranslateX += ((margins.left * colIndex) + (margins.right * (colIndex - 1)));
            scaledTranslateY += ((margins.top * rowIndex) + (margins.bottom * (rowIndex - 1)));
        }

        g.translate(scaledTranslateX, scaledTranslateY);

        Rectangle clip =
                new Rectangle((scaledWidth - margins.left - margins.right) * (colIndex - 1)
                        + figureBounds.x, (scaledHeight - margins.bottom - margins.top)
                        * (rowIndex - 1) + figureBounds.y, scaledWidth - margins.right
                        - margins.left, scaledHeight - margins.top - margins.bottom);
        g.clipRect(clip);

        dgrmEP.getLayer(LayerConstants.PRINTABLE_LAYERS).paint(g);

        g.popState();
    }

    /**
     * Adjust the page margins to be compatible with the user scale.
     * 
     * @param margins
     *            the page margins to adjust
     * @return adjusted page margins
     */
    private PageMargins adjustMarginsToScale(PageMargins margins) {

        margins.left /= userScale;
        margins.top /= userScale;
        margins.bottom /= userScale;
        margins.right /= userScale;

        return margins;
    }

    /**
     * A specialized graphics adapter used in printing.
     * 
     * There are several issues with the base adapter such as incorrect line
     * width settings and issues with gradient fill causing printing to be
     * offset wich are concerns specific to printing.
     * 
     * @author James Bruck (jbruck)
     * 
     */
    private class PrinterGraphicsToGraphics2DAdapter extends GraphicsToGraphics2DAdaptor {

        public PrinterGraphicsToGraphics2DAdapter(Graphics2D graphics, Rectangle viewPort) {
            super(graphics, viewPort);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.graphics.
         * GraphicsToGraphics2DAdaptor#setLineWidth(int)
         */
        public void setLineWidth(int width) {
            super.setLineWidth(width);

            BasicStroke scaledStroke = getStroke();
            //
            // Make a special case for line thickness to take the
            // printer resolution into account.
            //
            scaledStroke =
                    new BasicStroke((float) (width * AWT_DPI_CONST / 100),
                            scaledStroke.getEndCap(), scaledStroke.getLineJoin(),
                            scaledStroke.getMiterLimit(), scaledStroke.getDashArray(), 0);

            getGraphics2D().setStroke(scaledStroke);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.graphics.
         * GraphicsToGraphics2DAdaptor#fillGradient(int, int, int, int, boolean)
         */
        public void fillGradient(int x, int y, int w, int h, boolean vertical) {
            //
            // A bug in the draw2d layer causes printed output to be
            // offset if we use gradient fill. We will use an image
            // instead.
            //
            Image tempImage =
                    new Image(DisplayUtils.getDisplay(), new org.eclipse.swt.graphics.Rectangle(x,
                            y, w, h));
            GC gc = new GC(tempImage);
            SWTGraphics tempGraphics = new SWTGraphics(gc);

            tempGraphics.setForegroundColor(swtGraphics.getForegroundColor());
            tempGraphics.setBackgroundColor(swtGraphics.getBackgroundColor());
            tempGraphics.fillGradient(new org.eclipse.draw2d.geometry.Rectangle(0, 0, w, h),
                    vertical);
            drawImage(tempImage, 0, 0, w, h, x, y, w, h);

            tempGraphics.dispose();
            gc.dispose();
            tempImage.dispose();
        }
    }

}
