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
package de.cau.cs.kieler.klighd.piccolo.internal.util;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KColor;
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
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRotation;
import de.cau.cs.kieler.core.krendering.KShadow;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KStyleHolder;
import de.cau.cs.kieler.core.krendering.KStyleRef;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTextStrikeout;
import de.cau.cs.kieler.core.krendering.KTextUnderline;
import de.cau.cs.kieler.core.krendering.KVerticalAlignment;
import de.cau.cs.kieler.core.krendering.LineStyle;
import de.cau.cs.kieler.core.krendering.util.KRenderingSwitch;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.util.KlighdPredicates;

/**
 * Dedicated utility class providing structures and methods to handle the {@link KStyle} applications.
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
     * Indicates the 'selected' state of the represented KGraphElement,
     *  all selection styles must be applied!
     */
    private boolean applySelectionStyles = false;
    /**
     * Indicates the demand for applying the default selection highlighting styles,
     *  as the current (compound) rendering doesn't provide custom selection styles.
     * Is not incorporated for KTexts, as currently only the selection styles
     *  contained by themselves are considered for text selection highlighting.
     * The decision on using the default selection highlighting styles is made based
     *  on the presence of any selection style in their 'styles' list. 
     */
    private boolean applyDefaultSelectionStyles = false;
    /**
     * The root rendering is tracked for enabling the inheritance of default selection
     * highlighting styles by those renderings referencing the root by means of a {@link KStyleRef}. 
     */
    private KRendering rootRendering;


    /**
     * Enhances <code>this</code> {@link Styles} record with the currently effective {@link KStyle
     * KStyles}.
     * 
     * @param styleHolder
     *            the {@link KStyleHolder}, usually a {@link KRendering}, whose effective styles are
     *            to be determined
     * @param propagatedStyles
     *            the list of styles propagated from container {@link KRendering KRenderings}
     * @param isSelected
     *            a flag indicating whether 'selection' styles shall be taken into account
     * @param useDefaultRenderingStyles
     *            a flag indicating whether default selection highlighting styles shall be taken
     *            into account (usually, if no 'selection' styles are present)
     * @param theRootRendering
     *            the root rendering of the current
     *            {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement} to be highlighted
     *            (used for properly propagating default highlighting styles to those rendering
     *            referencing the root one via a {@link KStyleRef}).
     * @return the prepared {@link Styles} record
     */
    public Styles deriveStyles(final KStyleHolder styleHolder, final List<KStyle> propagatedStyles,
            final boolean isSelected, final boolean useDefaultRenderingStyles,
            final KRendering theRootRendering) {
        this.applySelectionStyles = isSelected;
        this.applyDefaultSelectionStyles = useDefaultRenderingStyles;
        this.rootRendering = theRootRendering;
        
        deriveStyles(styleHolder, propagatedStyles, null);

        return this;
    }
    
    /**
     * Enhances a styles container with a list of styles.
     * 
     * @param styleList
     *            the list of styles
     * @param styleTypes
     *            the list of allowed style types
     */
    private void deriveStyles(final KStyleHolder styleHolder, final List<KStyle> propagatedStyles,
            final List<Class<KStyle>> styleTypes) {

        final Iterable<KStyle> filteredStyles;

        // first of all filter the styles of 'styleHolder' if any filter is given,
        //  i.e. 'styleTypes' contains elements
        if (styleTypes == null || styleTypes.isEmpty()) {
            filteredStyles = styleHolder.getStyles();

        } else {
            // create a filter Predicate doing the instanceof testing of the referenced styles
            final Predicate<KStyle> styleFilter = new Predicate<KStyle>() {

                public boolean apply(final KStyle input) {
                    for (final Class<KStyle> styleType : styleTypes) {
                        if (styleType.isInstance(input)) {
                            return true;
                        }
                    }
                    return false;
                }
            };
            
            filteredStyles = Iterables.filter(styleHolder.getStyles(), styleFilter);
        }

        // afterwards apply propagated styles
        final Iterable<KStyle> withPropagatedStyles =
                Iterables.concat(filteredStyles, propagatedStyles);

        // now attach default  
        final Iterable<KStyle> withDefaultHighlightingStyles;

        // if selection styles are required ... 
        if (this.applySelectionStyles) {
            // check whether any default selection highlighting is required;
            // distinct the cases KText and non-KText since, in addition to KGraphElements,
            //  KTexts may be independently selectable.

            if (styleHolder instanceof KText) {
                withDefaultHighlightingStyles =
                        !Iterables.any(withPropagatedStyles, KlighdPredicates.isSelection())
                        ? Iterables.concat(withPropagatedStyles, getDefaultTextSelectionStyles())
                                : withPropagatedStyles;
            } else {
                // An case of non-KText renderings default selection styles must be applied
                //  to the root rendering only!
                // Note: According to this approach no highlighting will be visible
                //  if the root rendering is invisible!

                withDefaultHighlightingStyles =
                        this.applyDefaultSelectionStyles && styleHolder == this.rootRendering
                        ? Iterables.concat(withPropagatedStyles, getDefaultNonTextSelectionStyles())
                                : withPropagatedStyles;
            }
        } else {
            withDefaultHighlightingStyles = withPropagatedStyles;
        }

        // new apply the style - eventually ;-)
        for (final KStyle style : withDefaultHighlightingStyles) {
            if (!style.isSelection() || this.applySelectionStyles) {
                kSwitch.doSwitch(style);
            }
        }
    }

    private static List<KStyle> defaultNonTextSelectionStyles = null;

    private static List<KStyle> getDefaultNonTextSelectionStyles() {
        if (defaultNonTextSelectionStyles != null) {
            return defaultNonTextSelectionStyles;
        }

        defaultNonTextSelectionStyles = Lists.newArrayList();
        
        final KColor c = KlighdConstants.DEFAULT_SELECTION_HIGHLIGHTING_BACKGROUND_COLOR;
        final KBackground bg = KRenderingFactory.eINSTANCE.createKBackground().setColor(
                c.getRed(), c.getGreen(), c.getGreen());
        bg.setSelection(true);
        defaultNonTextSelectionStyles.add(bg);

        final KLineStyle lineStyle = KRenderingFactory.eINSTANCE.createKLineStyle();
        lineStyle.setLineStyle(LineStyle.DASH);
        lineStyle.setSelection(true);
        defaultNonTextSelectionStyles.add(lineStyle);
        
        return defaultNonTextSelectionStyles;
    }

    private static List<KStyle> defaultTextSelectionStyles = null;

    private static List<KStyle> getDefaultTextSelectionStyles() {
        if (defaultTextSelectionStyles != null) {
            return defaultTextSelectionStyles;
        }

        defaultTextSelectionStyles = Lists.newArrayList();
        
        final KColor c = KlighdConstants.DEFAULT_SELECTION_HIGHLIGHTING_BACKGROUND_COLOR;
        final KBackground bg = KRenderingFactory.eINSTANCE.createKBackground().setColor(
                c.getRed(), c.getGreen(), c.getGreen());
        bg.setSelection(true);
        defaultTextSelectionStyles.add(bg);

        final KFontBold fontBold = KRenderingFactory.eINSTANCE.createKFontBold();
        fontBold.setBold(true);
        fontBold.setSelection(true);
        defaultTextSelectionStyles.add(fontBold);
        
        return defaultTextSelectionStyles;
    }

    private KRenderingSwitch<Void> kSwitch = new KRenderingSwitch<Void>() {

        // styleRef
        @Override
        public Void caseKStyleRef(final KStyleRef style) {
            Styles.this.deriveStyles(style.getStyleHolder(), Collections.<KStyle>emptyList(),
                    style.getReferencedTypes());
            return null;
        }

        // foreground
        @Override
        public Void caseKForeground(final KForeground f) {
            Styles.this.foreground = f;
            return null;
        }

        // background
        @Override
        public Void caseKBackground(final KBackground b) {
            Styles.this.background = b;
            return null;
        }

        // whether the foreground is invisible or not
        @Override
        public Void caseKInvisibility(final KInvisibility i) {
            Styles.this.invisibility = i;
            return null;
        }

        // line width
        @Override
        public Void caseKLineWidth(final KLineWidth lw) {
            Styles.this.lineWidth = lw;
            return null;
        }

        // line style
        @Override
        public Void caseKLineStyle(final KLineStyle ls) {
            Styles.this.lineStyle = ls;
            return null;
        }

        // line cap style
        @Override
        public Void caseKLineCap(final KLineCap lcs) {
            Styles.this.lineCap = lcs;
            return null;
        }
        
        // line join style
        @Override
        public Void caseKLineJoin(final KLineJoin ljs) {
            Styles.this.lineJoin = ljs;
            return null;
        }
        
        // rotation
        @Override
        public Void caseKRotation(final KRotation r) {
            Styles.this.rotation = r;
            return null;
        }

        // shadow
        @Override
        public Void caseKShadow(final KShadow s) {
            Styles.this.shadow = s;
            return null;
        }

        // horizontal alignment
        @Override
        public Void caseKHorizontalAlignment(final KHorizontalAlignment ha) {
            Styles.this.horizontalAlignment = ha;
            return null;
        }

        // vertical alignment
        @Override
        public Void caseKVerticalAlignment(final KVerticalAlignment va) {
            Styles.this.verticalAlignment = va;
            return null;
        }

        // font name
        @Override
        public Void caseKFontName(final KFontName fn) {
            Styles.this.fontName = fn;
            return null;
        }

        // font size
        @Override
        public Void caseKFontSize(final KFontSize fs) {
            Styles.this.fontSize = fs;
            return null;
        }

        // italic
        @Override
        public Void caseKFontItalic(final KFontItalic fi) {
            Styles.this.italic = fi;
            return null;
        }

        // bold
        @Override
        public Void caseKFontBold(final KFontBold fb) {
            Styles.this.bold = fb;
            return null;
        }

        // underline
        @Override
        public Void caseKTextUnderline(final KTextUnderline tu) {
            Styles.this.underline = tu;
            return null;
        }

        // strike-out
        @Override
        public Void caseKTextStrikeout(final KTextStrikeout ts) {
            Styles.this.strikeout = ts;
            return null;
        }
    };
}
