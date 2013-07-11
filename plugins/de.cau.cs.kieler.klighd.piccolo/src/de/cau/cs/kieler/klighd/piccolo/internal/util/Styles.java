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
public final class Styles {
            
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

    /**
     * Enhances a styles container with a list of styles.
     * 
     * @param styleList
     *            the list of styles
     * @return the styles container
     */
    public Styles deriveStyles(final List<KStyle> styleList) {
        for (KStyle style : styleList) {
            kSwitch.doSwitch(style);
        }
        return this;
    }
    
    private KRenderingSwitch<Void> kSwitch = new KRenderingSwitch<Void>() {

        // styleRef
        public Void caseKStyleRef(final KStyleRef style) {
            Styles.this.deriveStyles(style.getStyleHolder().getStyles());
            return null;
        }

        // foreground
        public Void caseKForeground(final KForeground f) {
            Styles.this.foreground = f;
            return null;
        }

        // background
        public Void caseKBackground(final KBackground b) {
            Styles.this.background = b;
            return null;
        }

        // whether the foreground is invisible or not
        public Void caseKInvisibility(final KInvisibility i) {
            Styles.this.invisibility = i;
            return null;
        }

        // line width
        public Void caseKLineWidth(final KLineWidth lw) {
            Styles.this.lineWidth = lw;
            return null;
        }

        // line style
        public Void caseKLineStyle(final KLineStyle ls) {
            Styles.this.lineStyle = ls;
            return null;
        }

        // line cap style
        public Void caseKLineCap(final KLineCap lcs) {
            Styles.this.lineCap = lcs;
            return null;
        }
        
        // line join style
        public Void caseKLineJoin(final KLineJoin ljs) {
            Styles.this.lineJoin = ljs;
            return null;
        }
        
        // rotation
        public Void caseKRotation(final KRotation r) {
            Styles.this.rotation = r;
            return null;
        }

        // shadow
        public Void caseKShadow(final KShadow s) {
            Styles.this.shadow = s;
            return null;
        }

        // horizontal alignment
        public Void caseKHorizontalAlignment(final KHorizontalAlignment ha) {
            Styles.this.horizontalAlignment = ha;
            return null;
        }

        // vertical alignment
        public Void caseKVerticalAlignment(final KVerticalAlignment va) {
            Styles.this.verticalAlignment = va;
            return null;
        }

        // font name
        public Void caseKFontName(final KFontName fn) {
            Styles.this.fontName = fn;
            return null;
        }

        // font size
        public Void caseKFontSize(final KFontSize fs) {
            Styles.this.fontSize = fs;
            return null;
        }

        // italic
        public Void caseKFontItalic(final KFontItalic fi) {
            Styles.this.italic = fi;
            return null;
        }

        // bold
        public Void caseKFontBold(final KFontBold fb) {
            Styles.this.bold = fb;
            return null;
        }

        // underline
        public Void caseKTextUnderline(final KTextUnderline tu) {
            Styles.this.underline = tu;
            return null;
        }

        // strike-out
        public Void caseKTextStrikeout(final KTextStrikeout ts) {
            Styles.this.strikeout = ts;
            return null;
        }
    };
}
