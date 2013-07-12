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
package de.cau.cs.kieler.klighd.internal.util;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * A collection of internal KLighD-specific {@link de.cau.cs.kieler.core.properties.IProperty
 * IProperties}.
 * 
 * @author chsch
 */
public final class KlighdInternalProperties {

    /**
     * Standard hidden constructor.
     */
    private KlighdInternalProperties() {
    }

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
    
    
}
