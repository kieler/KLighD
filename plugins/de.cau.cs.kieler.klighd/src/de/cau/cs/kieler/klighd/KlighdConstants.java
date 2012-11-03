/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import java.awt.Font;

import org.eclipse.swt.SWT;

/**
 * This class contains definitions of initial & default values.
 * 
 * @author chsch
 */
public final class KlighdConstants {
    
    /**
     * This font name is used for {@link de.cau.cs.kieler.core.krendering.KText KTexts}, if no related
     * {@link de.cau.cs.kieler.core.krendering.KFontName KFontName} style is attached.
     */
    public static final String DEFAULT_FONT_NAME = "Arial";
    
    /**
     * This font size is used for {@link de.cau.cs.kieler.core.krendering.KText KTexts}, if no related
     * {@link de.cau.cs.kieler.core.krendering.KFontSize KFontSize} style is attached.
     */
    public static final int DEFAULT_FONT_SIZE = 10;
    
    /**
     * This font style used for {@link de.cau.cs.kieler.core.krendering.KText KTexts}, if no related
     * {@link de.cau.cs.kieler.core.krendering.KFontBold KFontBold} and/or
     * {@link de.cau.cs.kieler.core.krendering.KFontItalic KFontItalic} style is attached.
     * 
     * Note: This is a AWT constant! 
     */
    public static final int DEFAULT_FONT_STYLE = Font.PLAIN;

    /**
     * This font style used for {@link de.cau.cs.kieler.core.krendering.KText KTexts}, if no related
     * {@link de.cau.cs.kieler.core.krendering.KFontBold KFontBold} and/or
     * {@link de.cau.cs.kieler.core.krendering.KFontItalic KFontItalic} style is attached.
     * 
     * Note: SWT font style are composed by performing 'style |= SWT.BOLD' and/or 'style |= SWT.ITALIC'
     *  while 'style' is the style integer. 
     */
    public static final int DEFAULT_FONT_STYLE_SWT = SWT.NORMAL;
    
    
    /**
     * This constant defines the standard margin size around a KText rendering,
     * if it is defined by related placement data.
     */
    public static final int DEFAULT_TEXT_PADDING = 10;


    /**
     * Hidden default constructor.
     */
    private KlighdConstants() {
    }
}
