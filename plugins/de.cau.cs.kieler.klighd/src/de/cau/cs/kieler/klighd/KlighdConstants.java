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

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

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
     * This constant defines the standard margin size around a KText rendering, if it is not defined
     * by related placement data.
     * 
     * @Attention It is primitively incorporated in
     * {@link de.cau.cs.kieler.klighd.krendering.PlacementUtil#estimateTextSize
     * (de.cau.cs.kieler.core.krendering.KText) PlacementUtil#estimateTextSize(KText)}.
     * I'm, however, not convinced that this is reasonable approach so I set it to zero for the
     * moment.
     */
    public static final int DEFAULT_TEXT_PADDING = 0;

    /**
     * Property to be attached to the {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
     * KShapeLayout} of a view model's nodes in some regression tests.
     */
    public static final IProperty<Float> KLIGHD_TESTING_EXPECTED_HEIGHT = new Property<Float>(
            "klighd.testing.expected.height");

    /**
     * Property to be attached to the {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
     * KShapeLayout} of a view model's nodes for properly performing regression tests.
     */
    public static final IProperty<Float> KLIGHD_TESTING_EXPECTED_WIDTH = new Property<Float>(
            "klighd.testing.expected.width");

    /**
     * Property to be attached to the {@link de.cau.cs.kieler.core.krendering.KText KTexts} of a
     * view model for properly performing various regression tests.
     */
    public static final IProperty<Float> KLIGHD_TESTING_HEIGHT = new Property<Float>(
            "klighd.testing.height");

    /**
     * Property to be attached to the {@link de.cau.cs.kieler.core.krendering.KText KTexts} of a
     * view model for properly performing various regression tests.
     */
    public static final IProperty<Float> KLIGHD_TESTING_WIDTH = new Property<Float>(
            "klighd.testing.width");
    
    /**
     * Property of {@link de.cau.cs.kieler.core.kgraph.KNode KNodes} indicating that the containing
     * node is not pickable in a KLighD diagram. Can be used to mask auxiliary encapsulating nodes.
     */
    public static final IProperty<Boolean> KLIGHD_SELECTION_UNPICKABLE = new Property<Boolean>(
            "klighd.selection.unpickable");

    /**
     * Hidden default constructor.
     */
    private KlighdConstants() {
    }
}
