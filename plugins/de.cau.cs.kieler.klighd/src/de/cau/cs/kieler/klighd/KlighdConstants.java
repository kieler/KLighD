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

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.klighd.actions.CollapseExpandAction;
import de.cau.cs.kieler.klighd.microlayout.Bounds;

/**
 * This class contains definitions of initial & default values.
 * 
 * @author chsch
 */
public final class KlighdConstants {

    /**
     * The background color used in default selection highlighting
     * {@link de.cau.cs.kieler.core.krendering.KBackground KBackground} style.
     */
    // the color values of 'DimGray'
    public static final KColor DEFAULT_SELECTION_HIGHLIGHTING_BACKGROUND_COLOR =
            KRenderingFactory.eINSTANCE.createKColor().setColor(190, 190, 190);
    
    /**
     * Constant definition of the black {@link RGB} color constant.
     */
    public static final RGB BLACK = new RGB(0, 0, 0);

    /**
     * Constant definition of the red {@link RGB} color constant.
     */
    public static final RGB BLUE = new RGB(0, 0, 255);

    /**
     * Constant definition of the red {@link RGB} color constant.
     */
    public static final RGB GREEN = new RGB(0, 255, 0);

    /**
     * Constant definition of the red {@link RGB} color constant.
     */
    public static final RGB RED = new RGB(255, 0, 0);

    /**
     * Constant definition of the white {@link RGB} color constant.
     */
    public static final RGB WHITE = new RGB(255, 255, 255);

    /**
     * Constant definition of the yellow {@link RGB} color constant.
     */
    public static final RGB YELLOW = new RGB(255, 255, 0);

    /**
     * Constant definition of the <i>full transparent</i> SWT alpha value.
     * 
     * @see #ALPHA_FULL_OPAQUE
     */
    public static final int ALPHA_FULL_TRANSPARENT = 0;

    /**
     * Constant definition of the <i>full opaque</i> SWT alpha value.
     * 
     * @see #ALPHA_FULL_TRANSPARENT
     */
    public static final int ALPHA_FULL_OPAQUE = 255;

    /**
     * Default animation time in ms.
     */
    public static final int DEFAULT_ANIMATION_TIME = 500;
    
    
    /**
     * Constant definition denoting the standard line drawing attributes.
     */
    public static final LineAttributes DEFAULT_LINE_ATTRIBUTES = new LineAttributes(1f);

    /**
     * This font name is used for {@link de.cau.cs.kieler.core.krendering.KText KTexts}, if no
     * related {@link de.cau.cs.kieler.core.krendering.KFontName KFontName} style is attached.
     * 
     * Note: This is a AWT constant!
     */
    public static final String DEFAULT_FONT_NAME =
            Platform.getOS().equals(Platform.OS_WIN32) ? "Arial" : Font.SANS_SERIF;

    /**
     * A platform independent font name identifier for a default monospaced font.
     * 
     * Note: This is a AWT constant!
     */
    public static final String DEFAULT_MONOSPACE_FONT_NAME =
            Platform.getOS().equals(Platform.OS_WIN32) ? "Consolas"
                    : Platform.getOS().equals(Platform.OS_MACOSX) ? "Monaco" : "Monospace";

    /**
     * This font size is used for {@link de.cau.cs.kieler.core.krendering.KText KTexts}, if no
     * related {@link de.cau.cs.kieler.core.krendering.KFontSize KFontSize} style is attached.
     */
    public static final int DEFAULT_FONT_SIZE = 10;

    /**
     * This font size is used for Tooltips.
     */
    public static final int DEFAULT_TOOL_TIP_FONT_SIZE = 9;

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
     * Note: SWT font styles are composed by performing 'style |= SWT.BOLD' and/or 'style |=
     * SWT.ITALIC' while 'style' is the style integer.
     */
    public static final int DEFAULT_FONT_STYLE_SWT = SWT.NORMAL;

    /**
     * This font configuration used for {@link de.cau.cs.kieler.core.krendering.KText KTexts}.
     */
    public static final FontData DEFAULT_FONT = new FontData(DEFAULT_FONT_NAME, DEFAULT_FONT_SIZE,
            DEFAULT_FONT_STYLE_SWT);

    /**
     * This constant is used to express the non-underlining configuration as {@link SWT} doesn't
     * provide a constant for this case as {@link org.eclipse.swt.graphics.TextStyle TextStyle} uses
     * an extra boolean flag for that.
     */
    public static final int NO_FONT_UNDERLINING = -1;

    /**
     * Identifier of the built-in collapse expand action to be mentioned in instances of
     * {@link de.cau.cs.kieler.core.krendering.KAction KAction}.
     */
    public static final String ACTION_COLLAPSE_EXPAND = CollapseExpandAction.ID;

    /**
     * The minimal size of {@link de.cau.cs.kieler.core.kgraph.KNode KNodes} that is applied in case
     * no definition is given for a particular node.
     */
    public static final Bounds MINIMAL_NODE_BOUNDS = Bounds.immutableCopy(new Bounds(10, 10));
    
    /**
     * The priority of the {@link de.cau.cs.kieler.kiml.config.ILayoutConfig ILayoutConfig} employed
     * in the side bar layout configuration tools of KLighD diagrams.
     */
    // SUPPRESS CHECKSTYLE NEXT MagicNumber
    public static final int SIDE_BAR_LAYOUT_CONFIG_PRIORITY = VolatileLayoutConfig.DEFAULT_PRIORITY - 25;

    /**
     * Special type of semantic data that represents an 'id'. A specified value should be mapped to
     * rendering specific id elements, i.e. for SVGs a {@code <tag id="xyz" />} attribute should be
     * generated.
     * 
     * @see de.cau.cs.kieler.klighd.util.KlighdProperties#SEMANTIC_DATA KlighdProperties#SEMANTIC_DATA
     */
    public static final String SEMANTIC_DATA_ID = "__id";
    
    /**
     * Special type of semantic data that represents a 'class'. A specified value should be mapped
     * to rendering specific class elements, i.e. for SVGs a {@code <tag class="xyz" />} attribute
     * should be generated.
     * 
     * @see de.cau.cs.kieler.klighd.util.KlighdProperties#SEMANTIC_DATA KlighdProperties#SEMANTIC_DATA
     */
    public static final String SEMANTIC_DATA_CLASS = "__class";

    /**
     * Hidden default constructor.
     */
    private KlighdConstants() {
    }
}
