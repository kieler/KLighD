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
import org.eclipse.emf.common.util.URI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klighd.actions.CollapseExpandAction;
import de.cau.cs.kieler.klighd.microlayout.Bounds;

/**
 * This class contains definitions of initial & default values.
 * 
 * @author chsch
 */
public final class KlighdConstants {
    
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
     * Constant definition denoting the standard line drawing attributes.
     */
    public static final LineAttributes DEFAULT_LINE_ATTRIBUTES = new LineAttributes(1f);
    
    /**
     * This font name is used for {@link de.cau.cs.kieler.core.krendering.KText KTexts}, if no related
     * {@link de.cau.cs.kieler.core.krendering.KFontName KFontName} style is attached.
     * 
     * Note: This is a AWT constant! 
     */
    public static final String DEFAULT_FONT_NAME = Platform.getOS().equals(Platform.OS_WIN32) ? "Arial"
            : Font.SANS_SERIF;
    
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
     * Note: SWT font styles are composed by performing 'style |= SWT.BOLD' and/or 'style |= SWT.ITALIC'
     *  while 'style' is the style integer. 
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
     * Property to determine the minimal size of a node that has to hold for the node's whole
     * "life time".<br>
     * The {@link de.cau.cs.kieler.kiml.options.LayoutOptions#MIN_WIDTH LayoutOptions#MIN_WIDTH}/
     * {@link de.cau.cs.kieler.kiml.options.LayoutOptions#MIN_HEIGHT LayoutOptions#MIN_HEIGHT}
     * properties are not sufficient as they have to be modified for hierarchical diagrams before
     * each automatic layout run.<br>
     * <br>
     * <b>Caution</b>: This property has been defined in
     * {@link de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions KNodeExtensions}, too, in
     * order to enable the independence of both bundles. This is possible as {@link IProperty
     * IProperties} are determined to be equal or unequal based on their id's.
     */
    public static final IProperty<KVector> MINIMAL_NODE_SIZE = new Property<KVector>(
            "klighd.minimalNodeSize", new KVector(KlighdConstants.MINIMAL_NODE_BOUNDS.getWidth(),
                    KlighdConstants.MINIMAL_NODE_BOUNDS.getHeight()));

    /**
     * Property to be attached to root {@link de.cau.cs.kieler.core.krendering.KRendering
     * KRendering} objects of {@link de.cau.cs.kieler.core.kgraph.KNode KNodes} during the view
     * synthesis process indicating that the {@link de.cau.cs.kieler.core.krendering.KRendering
     * KRendering} is to be shown in the collapsed state of the node.
     */
    public static final IProperty<Boolean> COLLAPSED_RENDERING = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.collapsedRendering", false);

    /**
     * Property to be attached to root {@link de.cau.cs.kieler.core.krendering.KRendering
     * KRendering} objects of {@link de.cau.cs.kieler.core.kgraph.KNode KNodes} during the view
     * synthesis process indicating that the {@link de.cau.cs.kieler.core.krendering.KRendering
     * KRendering} is to be shown in the expanded state of the node.
     */
    public static final IProperty<Boolean> EXPANDED_RENDERING = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.expandedRendering", false);
    
    /**
     * Property indicating the auto expansion of a node if the value is true.<br>
     * This is property is currently to be attached to the nodes shape layout data during the view
     * synthesis process. If it is absent the node gets expanded, anyway.
     */
    public static final IProperty<Boolean> EXPAND = new Property<Boolean>("klighd.expand", true);

    /**
     * Property providing a URI to semantic elements to be depicted but that are to be loaded lazily.
     * This is property is currently to be attached to the nodes shape layout data during the view
     * synthesis process. 
     */
    public static final IProperty<URI> CHILD_URI = new Property<URI>("klighd.childURI");

    /**
     * Property indicating that the node has been populated. A node is populated, if and only if the
     * node's child nodes are visible in the diagram.<br>
     * <br>
     * <b>It is intended for KLighD internal use only!</b> The property declaration has been moved
     * here from klighd.piccolo's AbstractRenderingController.
     */
    public static final IProperty<Boolean> POPULATED = new Property<Boolean>("klighd.populated",
            false);
    
    /**
     * A property for identifying whether a node is currently active. A node is active if and only
     * if it is visible.<br>
     * <br>
     * <b>It is intended for KLighD internal use only!</b> The property declaration has been moved
     * here from klighd.piccolo's AbstractRenderingController.
     */
    public static final IProperty<Boolean> ACTIVE = new Property<Boolean>("klighd.active", false);

    /**
     * Property to be attached to the {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
     * KShapeLayout} of a view model's nodes for properly performing regression tests.
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
     * Property to be attached to the {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
     * KShapeLayout} of a view model's nodes in order to ignore those nodes.<br>
     * <br>
     * The id is also hard-coded in KGraphJavaValidator!
     */
    public static final IProperty<Boolean> KLIGHD_TESTING_IGNORE = new Property<Boolean>(
            "klighd.testing.ignore", false);

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
