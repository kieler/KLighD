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

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.swt.graphics.FontData;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.KlighdOptions;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.krendering.KFontBold;
import de.cau.cs.kieler.klighd.krendering.KFontItalic;
import de.cau.cs.kieler.klighd.krendering.KFontName;
import de.cau.cs.kieler.klighd.krendering.KFontSize;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingOptions;
import de.cau.cs.kieler.klighd.krendering.KRenderingUtil;
import de.cau.cs.kieler.klighd.krendering.KStyle;
import de.cau.cs.kieler.klighd.krendering.KText;
import de.cau.cs.kieler.klighd.krendering.KTextUtil;
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
public final class PlacementUtilAWT {

    /**
     * Hidden default constructor.
     */
    private PlacementUtilAWT() {
    }
    public static Font fontFor(final ElkLabel label) {
        return PlacementUtilAWT.fontFor(ktextFor(label), null);
    }

    
    /**
     * Returns the KText that controls the rendering for the given label. The rendering is found by
     * inspecting the {@link KlighdOptions#K_RENDERING} property.
     * 
     * @param label
     *            the label whose KText to retrieve.
     * @return the KText or {@code null} if none could be found.
     */
    public static KText ktextFor(final ElkLabel label) {
        // Try finding the KText we will inspect for the font
        KRendering rootRendering = label.getProperty(KRenderingOptions.K_RENDERING);
        
        final Iterator<KText> kTexts = Iterators.filter(
                KRenderingUtil.selfAndAllChildren(rootRendering), KText.class);
        return Iterators.getNext(kTexts, null);
    }

    // CHECKSTYLEOFF Visibility

    private static final Predicate<KStyle> FILTER = new Predicate<KStyle>() {
        public boolean apply(final KStyle style) {
            return style.isPropagateToChildren();
        }
    };
    
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
    public static Font fontFor(final KLabel kLabel, final boolean setFontLayoutOptions) {
        final KRendering rootRendering = Iterators.getNext(
                ModelingUtil.eAllContentsOfType2(kLabel, KRendering.class),
                null);
        
        final Iterator<KText> kTexts = Iterators.filter(
                KRenderingUtil.selfAndAllChildren(rootRendering), KText.class);
        final KText kText = Iterators.getNext(kTexts, null);
        
        // Check if we have found a KText thingy
        if (setFontLayoutOptions) {
            return fontFor(kText, kLabel);
        } else {
            return fontFor(kText, null);
        }
    }
    
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
    public static Font fontFor(final KText kText, final KGraphElement graphElement) {
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
                ? KlighdConstants.DEFAULT_FONT_STYLE_SWT | Font.BOLD
                : KlighdConstants.DEFAULT_FONT_STYLE_SWT;

        fontStyle = kFontItalic != null && kFontItalic.isItalic()
                ? fontStyle | Font.ITALIC : fontStyle;

        if (graphElement != null) {
            // setting the font name and size layout options is expected by the Graphviz layouter
            //  as it does not rely on given sizes but computes it on its own based on the given
            //  label text and font configuration 
            graphElement.setProperty(CoreOptions.FONT_NAME, fontName);
            graphElement.setProperty(CoreOptions.FONT_SIZE, fontSize);
        }

        return new Font(fontName, KTextUtil.swtFontStyle2Awt(fontStyle), fontSize);
    }
    // private static BufferedImage bi = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    // private static Graphics2D fmg = bi.createGraphics();
    
    public static Bounds estimateTextSizeAWT(final Font font, final String text) {
        
        /*BufferUtil buffer = BufferUtil.getBufferedImage(1,  1);
        Graphics2D fmg = buffer.getGraphics();
        fmg.setFont(font);
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
        
        return textBounds;*/
        return new Bounds(Strings.isNullOrEmpty(text) ? 0 : 10, 10);
    }

    /**
     * Find the String with the biggest width according to a font in string array.
     * 
     * @param font
     *            font of the text used to estimate the text size.
     * @param words
     *            words from which the biggest one is searched.
     * @return biggest word.
     */
    public static float getWidthOfBiggestWord(final Font font, final String[] words) {
        Bounds bWSize = PlacementUtilAWT.estimateTextSizeAWT(font, "");
        Bounds textPartSize;

        for (String textpart : words) {
            textPartSize = PlacementUtilAWT.estimateTextSizeAWT(font, textpart);
            if (bWSize.getWidth() < textPartSize.getWidth()) {
                bWSize = textPartSize;
            }
        }
        
        return bWSize.getWidth();
    }

    /**
     * Determines the first part of the String which fits in a certain width depending on the font.
     * 
     * @param text
     *            the text which is supposed to be shortened.
     * @param fontData
     *            the font the text will be rendered with.
     * @param targetWidth
     *            the width the text is supposed to fit in.
     * @return the part of the text which fits in the target width.
     */
    public static String findFittingString(final String text, final Font font,
            final double targetWidth) {
        
        String textWithoutWraps = text.replace("\n", " ");
        Bounds newSize = PlacementUtilAWT.estimateTextSizeAWT(font, textWithoutWraps);
        String newText = "";

        // Guess how many characters will fit into the target width (and make sure it's not more
        // than the number of characters we actually have)
        int newTextLength = (int) (targetWidth / (newSize.getWidth() / textWithoutWraps.length()));
        newTextLength = Math.min(newTextLength, textWithoutWraps.length());

        // Shorten the text accordingly and calculate its bounds
        newText = textWithoutWraps.substring(0, newTextLength);
        newSize = PlacementUtilAWT.estimateTextSizeAWT(font, newText);

        if (newSize.getWidth() > targetWidth) {
            // Text is still to long and some more characters have to go
            while (newSize.getWidth() > targetWidth && newTextLength > 1) {
                newTextLength--;
                newText = textWithoutWraps.substring(0, newTextLength);
                newSize = PlacementUtilAWT.estimateTextSizeAWT(font, newText);
            }
        } else {
            // There is some space for more characters
            while (newSize.getWidth() < targetWidth
                    && newTextLength < textWithoutWraps.length() - 1) {
                
                newTextLength++;
                String newTextCandidate = textWithoutWraps.substring(0, newTextLength);
                newSize = PlacementUtilAWT.estimateTextSizeAWT(font, newTextCandidate);
                
                if (newSize.getWidth() <= targetWidth) {
                    newText = newTextCandidate;
                }
            }
        }
        
        return newText;
    }
}
