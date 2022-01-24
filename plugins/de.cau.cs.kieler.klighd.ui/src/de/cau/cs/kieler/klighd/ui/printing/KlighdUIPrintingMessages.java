// SUPPRESS CHECKSTYLE NEXT Header
/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.ui.printing;

import org.eclipse.osgi.util.NLS;

/**
 * An accessor class for externalized strings.
 *
 * @author cmahoney
 * @author csp
 * @author chsch
 */
public final class KlighdUIPrintingMessages extends NLS {

    private static final String BUNDLE_NAME =
            "de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages"; //$NON-NLS-1$

    // CHECKSTYLEOFF Name|Visibility|Javadoc -- this is a generated file

    public static String PrintAction_JobName;

    public static String KlighdPrintDialog_InitialDialog_title;
    public static String KlighdPrintDialog_Title;
    public static String KlighdPrintDialog_OK_label;

    public static String PrintDialog_Button_PrintPreview;
    public static String PrintDialog_Alignment;
    public static String PrintDialog_Alignment_centerHorizontally;
    public static String PrintDialog_Alignment_centerVertically;
    public static String PrintDialog_Copies;
    public static String PrintDialog_NumberOfCopies;
    public static String PrintDialog_Collate;
    public static String KlighdPrintDialog_Copies_OSXToolTip;
    public static String PrintDialog_Printer;
    public static String PrintDialog_Name;
    public static String PrintDialog_PrinterSettings;
    public static String PrintDialog_PrintRange;
    public static String PrintDialog_All;
    public static String PrintDialog_Pages;
    public static String PrintDialog_From;
    public static String PrintDialog_To;

    public static String PrintDialog_Orientation_orientation;
    public static String PrintDialog_Orientation_portrait;
    public static String PrintDialog_Orientation_landscape;

    public static String PrintDialog_Scaling;
    public static String PrintDialog_Scaling_to100;
    public static String PrintDialog_Scaling_fitPages;
    public static String PrintDialog_Scaling_adjustPages;
    public static String PrintDialog_Scaling_lbl_scaleTo;
    public static String PrintDialog_Scaling_lbl_percent;
    public static String PrintDialog_Scaling_lbl_printTo;
    public static String PrintDialog_Scaling_lbl_pagesWide;
    public static String PrintDialog_Scaling_lbl_pagesTall;

    private KlighdUIPrintingMessages() {
        // Do not instantiate
    }

    static {
        NLS.initializeMessages(BUNDLE_NAME, KlighdUIPrintingMessages.class);
    }
}
