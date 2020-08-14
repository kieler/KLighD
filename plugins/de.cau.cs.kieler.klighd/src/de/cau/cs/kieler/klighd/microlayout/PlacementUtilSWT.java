/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.microlayout;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.Klighd;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.krendering.KFontBold;
import de.cau.cs.kieler.klighd.krendering.KFontItalic;
import de.cau.cs.kieler.klighd.krendering.KFontName;
import de.cau.cs.kieler.klighd.krendering.KFontSize;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingUtil;
import de.cau.cs.kieler.klighd.krendering.KStyle;
import de.cau.cs.kieler.klighd.krendering.KText;
import de.cau.cs.kieler.klighd.krendering.KTextUtil;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil.Point;
import de.cau.cs.kieler.klighd.util.Iterables2;
import de.cau.cs.kieler.klighd.util.ModelingUtil;

/**
 * A utility class for evaluating the micro layout of KRenderings.
 * 
 * @author chsch
 * @author sgu
 * @author akoc
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch 
 */
public final class PlacementUtilSWT {

    /**
     * Hidden default constructor.
     */
    private PlacementUtilSWT() {
    }

    // CHECKSTYLEOFF Visibility
    /**
     * Returns the font data used to render the given label. If the label has a {@link KText} rendering
     * attached, its font information are used. Otherwise, defaults defined in {@link KlighdConstants}
     * are used.
     * 
     * @param kLabel the label whose font information to retrieve.
     * @return font information for the given label.
     */
    public static FontData fontDataFor(final KLabel kLabel) {
        return fontDataFor(kLabel, false);
    }

    /**
     * Returns the font data used to render the given label. If the label has a {@link KText}
     * rendering attached, its font information are used. Otherwise, defaults defined in
     * {@link KlighdConstants} are used.
     * 
     * @param kLabel
     *            the label whose font information to retrieve.
     * @param setFontLayoutOptions
     *            if <code>true</code> the layout options {@link LayoutOptions#FONT_NAME} and
     *            {@link LayoutOptions#FONT_SIZE} are set/updated on <code>kLabel</code>'s layout
     *            data as expected by Graphviz (dot) for properly sizing <i>edge</i> labels
     * @return font information for the given label.
     */
    public static FontData fontDataFor(final KLabel kLabel, final boolean setFontLayoutOptions) {
        final KRendering rootRendering = Iterators.getNext(
                ModelingUtil.eAllContentsOfType2(kLabel, KRendering.class),
                null);
        
        final Iterator<KText> kTexts = Iterators.filter(
                KRenderingUtil.selfAndAllChildren(rootRendering), KText.class);
        final KText kText = Iterators.getNext(kTexts, null);
        
        // Check if we have found a KText thingy
        if (setFontLayoutOptions) {
            return fontDataFor(kText, kLabel);
        } else {
            return fontDataFor(kText, null);
        }
    }
    
    /**
     * Returns the font data defined by the given rendering. Missing font information are substituted by
     * defaults defined in {@link KlighdConstants}.
     * 
     * @param kText the rendering whose font information to retrieve.
     * @return font information for the given rendering.
     */
    public static FontData fontDataFor(final KText kText) {
        return fontDataFor(kText, null);
    }

    private static final Predicate<KStyle> FILTER = new Predicate<KStyle>() {
        public boolean apply(final KStyle style) {
            return style.isPropagateToChildren();
        }
    };
    
    /**
     * Returns the font data defined by the given rendering. Missing font information are
     * substituted by defaults defined in {@link KlighdConstants}.
     * 
     * @param kText
     *            the rendering whose font information to retrieve.
     * @param graphElement
     *            if unequal to <code>null</code> the layout options {@link LayoutOptions#FONT_NAME}
     *            and {@link LayoutOptions#FONT_SIZE} will be set/updated to the determined font
     *            name & size values
     * @return font information for the given rendering.
     */
    static FontData fontDataFor(final KText kText, final KGraphElement graphElement) {
        KFontName kFontName = null;
        KFontSize kFontSize = null;
        KFontBold kFontBold = null;
        KFontItalic kFontItalic = null;
        
        if (kText != null) {
            // the following lines look for font styles propagated from parents
            //  TODO also make allowance of styles propagated via KRenderingRefs
            final List<KStyle> styles = Lists.newLinkedList(kText.getStyles());            
            for (final KRendering k : Iterables2.toIterable(Iterators.filter(
                    ModelingUtil.eAllContainers(kText), KRendering.class))) {
                Iterables.addAll(styles, Iterables.filter(k.getStyles(), FILTER));
            }
            
            kFontName = Iterables.getLast(Iterables.filter(styles, KFontName.class), null);
            kFontSize = Iterables.getLast(Iterables.filter(styles, KFontSize.class), null);
            kFontBold = Iterables.getLast(Iterables.filter(styles, KFontBold.class), null);
            kFontItalic = Iterables.getLast(Iterables.filter(styles, KFontItalic.class), null);
        }

        final String fontName = kFontName != null
                ? kFontName.getName()
                : KlighdConstants.DEFAULT_FONT_NAME;

        final int fontSize = kFontSize != null
                ? kFontSize.getSize()
                : KlighdConstants.DEFAULT_FONT_SIZE;

        int fontStyle = kFontBold != null && kFontBold.isBold()
                ? KlighdConstants.DEFAULT_FONT_STYLE_SWT | SWT.BOLD
                : KlighdConstants.DEFAULT_FONT_STYLE_SWT;

        fontStyle = kFontItalic != null && kFontItalic.isItalic()
                ? fontStyle | SWT.ITALIC : fontStyle;

        if (graphElement != null) {
            // setting the font name and size layout options is expected by the Graphviz layouter
            //  as it does not rely on given sizes but computes it on its own based on the given
            //  label text and font configuration 
            graphElement.setProperty(CoreOptions.FONT_NAME, fontName);
            graphElement.setProperty(CoreOptions.FONT_SIZE, fontSize);
        }

        return new FontData(fontName, fontSize, fontStyle);
    }

    /**
     * A font cache preserving requested font configurations in order to avoid re-instantiation of
     * {@link Font}, which is assumed to be much more expensive than {@link FontData}.
     */
    private static final Map<FontData, Font> FONT_CACHE = Maps.newHashMap();

    /**
     * Two instances of {@link GC} that the text size estimation is delegated to.
     * We use two instances here because label management uses size estimation in another thread
     * and SWT is not exactly thread-safe.
     * It is unclear if this solves the issue completely, but it should at least circumvent
     * the most common case.
     */
    // private static GC gc = null;
    // private static GC asyncGC = null;
    // private static Point displayScale = null;
    private static BufferedImage bi = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    private static Graphics2D fmg = bi.createGraphics();
    
    /**
     * Returns the minimal bounds required by a drawing of the string <code>text</code> while
     * respecting the given <code>fontData</code>. While being in an Eclipse context and having a
     * {@link Display}, the method uses SWT's {@link GC} to perform estimations. Otherwise it falls
     * back to AWT's {@link FontMetrics}.
     * 
     * @param fontData
     *            an SWT {@link FontData} record describing font name, size, and style
     * @param text
     *            the text string whose size is to be estimated; maybe <code>null</code>
     * @return the minimal bounds for the string
     */
    /*
    public static Bounds estimateTextSize(final FontData fontData, final String text) {
        final Display display = Display.getCurrent();
        // if a GC has been instantiated before or a display is available.
        if (gc != null || display != null) {
            return estimateTextSizeSWT(fontData, text, display);
        } else {
            // if no display is available fallback to awt metrics
            return estimateTextSizeAWT(fontData, text);
        }
    }

    private static Bounds estimateTextSizeSWT(final FontData fontData,
            final String text, final Display display) {

        // In order to estimate the required size of a given string according to the determined
        // font, style, and size GCs are instantiated, configured, and queried.
        if (gc == null) {
            // Create GC for the main thread
            gc = new GC(display);
            gc.setAntialias(SWT.OFF);
            // Create (identical) GC for asynchronous threads
            asyncGC = new GC(display);
            asyncGC.setAntialias(SWT.OFF);

            // determine the current display scale, used below for compensating the text bounds
            //  see 
            org.eclipse.swt.graphics.Point dpi = display.getDPI();
            displayScale = new Point(
                KlighdConstants.DEFAULT_DISPLAY_DPI / dpi.x,
                KlighdConstants.DEFAULT_DISPLAY_DPI / dpi.y);
        }

        // Find the GC suitable for this thread.
        // The main/UI thread has direct access to the Display, 
        // so we use that check as the distinguishing feature
        // between the main thread and the other stuff
        final GC myGC = Display.getCurrent() != null ? gc : asyncGC;        

        Font font = FONT_CACHE.get(fontData);
        if (font == null) {
            font = new Font(display, fontData);
            FONT_CACHE.put(fontData, font);
        }
        myGC.setFont(font);

        final Bounds textBounds;
        if (Strings.isNullOrEmpty(text)) {
            // if no text string is given, take the bounds of a space character to get a proper
            // value for the height
            textBounds = new Bounds(myGC.textExtent(" "));
            textBounds.width = 0f; // omit the width in this case
        } else {
            textBounds = new Bounds(myGC.textExtent(text));
        }

        if (!Klighd.isSuppressDisplayScaleCompensationWhileHandlingText()) {
            textBounds.width  *= displayScale.x;
            textBounds.height *= displayScale.y;
        }

        return textBounds;
    }*/

    public static Bounds estimateTextSizeAWT(final FontData fontData, final String text) {
        fmg.setFont(new java.awt.Font(fontData.getName(), 
                KTextUtil.swtFontStyle2Awt(fontData.getStyle()), 
                fontData.getHeight()));
        final FontMetrics fm = fmg.getFontMetrics();

        final Bounds textBounds;
        if (Strings.isNullOrEmpty(text)) {
            // if no text string is given, take the bounds of a space character to get a proper
            // value for the height
            textBounds = new Bounds(fm.getStringBounds(" ", fmg));
            textBounds.width = 0f; // omit the width in this case
        } else {
            textBounds = new Bounds(fm.getStringBounds(text, fmg));
        }
        
        return textBounds;
    }
    
}
