/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.util;

import java.util.List;

import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KFontBold;
import de.cau.cs.kieler.core.krendering.KFontItalic;
import de.cau.cs.kieler.core.krendering.KFontName;
import de.cau.cs.kieler.core.krendering.KFontSize;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KHorizontalAlignment;
import de.cau.cs.kieler.core.krendering.KInvisibility;
import de.cau.cs.kieler.core.krendering.KLineCap;
import de.cau.cs.kieler.core.krendering.KLineJoin;
import de.cau.cs.kieler.core.krendering.KLineStyle;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KRotation;
import de.cau.cs.kieler.core.krendering.KShadow;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KStyleRef;
import de.cau.cs.kieler.core.krendering.KTextStrikeout;
import de.cau.cs.kieler.core.krendering.KTextUnderline;
import de.cau.cs.kieler.core.krendering.KVerticalAlignment;
import de.cau.cs.kieler.core.krendering.util.KRenderingSwitch;

/**
 * Dedicated utility class providing structures and methods to handle the {@link KStyle} definitions.
 * 
 * @author chsch
 */
public final class StyleUtil {
    
    private StyleUtil() {
    }
    
    /**
     * A container class for the possible styles.
     */
    public static class Styles {
        
        // CHECKSTYLEOFF Visibility

        /** the foreground. */
        public KForeground foreground =  null;
        /** the background. */
        public KBackground background = null;
        /** the invisibility. */
        public KInvisibility invisibility = null;
        /** the line width. */
        public KLineWidth lineWidth = null;
        /** the line style. */
        public KLineStyle lineStyle = null;
        /** the line cap style. */
        public KLineCap lineCap = null;
        /** the line join style. */
        public KLineJoin lineJoin = null;
        /** the shadow. */
        public KShadow shadow = null;
        /** the rotation. */
        public KRotation rotation = null;
        /** the font name. */
        public KFontName fontName = null;
        /** the font size. */
        public KFontSize fontSize = null;
        /** the font italic property. */
        public KFontItalic italic = null;
        /** the font bold property. */
        public KFontBold bold = null;
        /** the horizontal alignment. */
        public KHorizontalAlignment horizontalAlignment = null;
        /** the vertical alignment. */
        public KVerticalAlignment verticalAlignment = null;
        /** the font underline property. */
        public KTextUnderline underline = null;
        /** the font underline property. */
        public KTextStrikeout strikeout = null;
        
        // CHECKSTYLEON Visibility
    }

    /**
     * Enhances a styles container with a list of styles.
     * 
     * @param styles
     *            the styles container or null to create an empty one
     * @param styleList
     *            the list of styles
     * @return the styles container
     */
    public static Styles deriveStyles(final Styles styles, final List<KStyle> styleList) {

        final Styles theStyles = (styles != null) ? styles : new Styles();

        KRenderingSwitch<Boolean> kSwitch = new KRenderingSwitch<Boolean>() {

            // styleRef
            public Boolean caseKStyleRef(final KStyleRef style) {
                deriveStyles(theStyles, style.getStyleHolder().getStyles());
                return true;
            }

            // foreground
            public Boolean caseKForeground(final KForeground f) {
                theStyles.foreground = f;
                return true;
            }

            // background
            public Boolean caseKBackground(final KBackground b) {
                theStyles.background = b;
                return true;
            }

            // whether the foreground is invisible or not
            public Boolean caseKInvisibility(final KInvisibility i) {
                theStyles.invisibility = i;
                return true;
            }

            // line width
            public Boolean caseKLineWidth(final KLineWidth lw) {
                theStyles.lineWidth = lw;
                return true;
            }

            // line style
            public Boolean caseKLineStyle(final KLineStyle ls) {
                theStyles.lineStyle = ls;
                return true;
            }

            // line cap style
            public Boolean caseKLineCap(final KLineCap lcs) {
                theStyles.lineCap = lcs;
                return true;
            }
            
            // line join style
            public Boolean caseKLineJoin(final KLineJoin ljs) {
                theStyles.lineJoin = ljs;
                return true;
            }
            
            // rotation
            public Boolean caseKRotation(final KRotation r) {
                theStyles.rotation = r;
                return true;
            }

            // shadow
            public Boolean caseKShadow(final KShadow s) {
                theStyles.shadow = s;
                return true;
            }

            // horizontal alignment
            public Boolean caseKHorizontalAlignment(final KHorizontalAlignment ha) {
                theStyles.horizontalAlignment = ha;
                return true;
            }

            // vertical alignment
            public Boolean caseKVerticalAlignment(final KVerticalAlignment va) {
                theStyles.verticalAlignment = va;
                return true;
            }

            // font name
            public Boolean caseKFontName(final KFontName fontName) {
                theStyles.fontName = fontName;
                return true;
            }

            // font size
            public Boolean caseKFontSize(final KFontSize fontSize) {
                theStyles.fontSize = fontSize;
                return true;
            }

            // italic
            public Boolean caseKFontItalic(final KFontItalic italic) {
                theStyles.italic = italic;
                return true;
            }

            // bold
            public Boolean caseKFontBold(final KFontBold bold) {
                theStyles.bold = bold;
                return true;
            }

            // underline
            public Boolean caseKTextUnderline(final KTextUnderline underline) {
                theStyles.underline = underline;
                return true;
            }

            // strike-out
            public Boolean caseKTextStrikeout(final KTextStrikeout strikeout) {
                theStyles.strikeout = strikeout;
                return true;
            }
        };
        
        for (KStyle style : styleList) {
            kSwitch.doSwitch(style);
        }
        return theStyles;
    }
}
