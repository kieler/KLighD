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

package de.cau.cs.kieler.klighd.ui.printing.util;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.klighd.ui.printing.dialogs.JPSPrintDialog;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * Default implementation of a print-helper.
 * 
 * @author Christian W. Damus (cdamus)
 * @author James Bruck (jbruck)
 */
public class PrintHelper {
	private final PrintOptions options = new PrintOptions();

	List<String> diagramList;

	public PrintHelper() {
		initPrintOptions();
	}

	/**
	 * Initialize the default options.
	 */
	private void initPrintOptions() {
//		options.setScaleFactor(true);
				
//		options.setScaleFactor(PrintHelperUtil.getScale());
//		options.setFitToPagesWidth(PrintHelperUtil.getScaleToWidth());
//		options.setFitToPagesHeight(PrintHelperUtil.getScaleToHeight());

		options.setAllPages(true);
		options.setRangeFrom(1);
		options.setRangeTo(1);

		options.setCopies(1);
		options.setCollate(false);

		options.setQualityHigh(true);
		options.setSideOneSided(true);
		options.setChromaticityColor(true);

		options.setDiagramCurrent(true);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.printing.IPrintHelper#openPrintDlg(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public PrinterData openPrintDlg(List availableDiagramList) {
		PrinterData result = null;
		this.diagramList = availableDiagramList;

		JPSPrintDialog dlg = new JPSPrintDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow(), options, this.diagramList);

		if (dlg.open() == IDialogConstants.OK_ID) {
			if (options.getDestination() != null) {
				result = options.getDestination().getPrinterData();
			}
		} else {
			// revert
			initPrintOptions();
		}
		return result;
	}

	public boolean getDlgCollate() {
		return options.isCollate();
	}

	public int getDlgNumberOfCopies() {
		return options.getCopies();
	}

	public int getDlgPagesFrom() {
		return options.getRangeFrom();
	}

	public int getDlgPagesTo() {
		return options.getRangeTo();
	}

	public boolean getDlgPrintRangeAll() {
		return options.isAllPages();
	}

	public boolean getDlgPrintRangePages() {
		return !getDlgPrintRangeAll();
	}

	public int getDlgScaleFitToM() {
		return options.getFitToPagesWidth();
	}

	public int getDlgScaleFitToN() {
		return options.getFitToPagesHeight();
	}

	public int getDlgScalePercent() {
		return options.getScaleFactor();
	}

	public void setDlgOrientation(boolean landscape) {
		// TODO Auto-generated method stub
	}

	public void setDlgPaperSize(int index, double width, double length) {
		// TODO Auto-generated method stub
	}

	public boolean getDlgDiagramPrintRangeCurrent() {
		return options.isDiagramCurrent();
	}

	public boolean getDlgDiagramPrintRangeSelection() {
		return options.isDiagramSelection();
	}

	public boolean isDlgDiagramSelected(int index) {
		String diagramToPrint = diagramList.get(index);
		if (options.getDiagramsToPrint() != null) {
			return options.getDiagramsToPrint().contains(diagramToPrint);
		}
		return false;
	}

	public boolean getDlgDiagramPrintRangeAll() {
		// TODO Not supported by the JPS dialog
		return false;
	}

	public PrintOptions getPrintOptions() {
		return options;
	}
	
	public void setScaleFactor(int scaleFactor) {
	 	options.setScaleFactor(scaleFactor)	;
	}

	public void setScaleToWidthHeight(int width, int height) {
		options.setFitToPagesWidth(width);
		options.setFitToPagesHeight(height);
	}

}
