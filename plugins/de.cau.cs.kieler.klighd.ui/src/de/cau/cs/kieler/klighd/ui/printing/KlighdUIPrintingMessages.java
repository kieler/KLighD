// SUPPRESS CHECKSTYLE NEXT Header
/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.printing;

import org.eclipse.osgi.util.NLS;

/**
 * An accessor class for externalized strings.
 * 
 * @author cmahoney
 * @author csp
 */
public final class KlighdUIPrintingMessages extends NLS {

    private static final String BUNDLE_NAME =
            "de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages"; //$NON-NLS-1$

    // SUPPRESS CHECKSTYLE NEXT 22 Name|Visibility|Javadoc
    public static String PrintDialog_Button_PrintPreview;
    public static String PrintDialog_Copies;
    public static String PrintDialog_NumberOfCopies;
    public static String PrintDialog_Collate;
    public static String PrintDialog_Title;
    public static String PrintDialog_Printer;
    public static String PrintDialog_Name;
    public static String PrintDialog_Properties;
    public static String PrintDialog_PrintRange;
    public static String PrintDialog_All;
    public static String PrintDialog_Pages;
    public static String PrintDialog_From;
    public static String PrintDialog_To;
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
