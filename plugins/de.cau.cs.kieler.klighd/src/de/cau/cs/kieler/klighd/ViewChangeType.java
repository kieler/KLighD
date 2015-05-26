/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import java.util.EnumSet;

/**
 * Defines the possible view change types being performed by KLighD {@link IViewer IViewers}.
 *
 * @author chsch
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public enum ViewChangeType {

    /**
     * Indicates a change of the {@link de.cau.cs.kieler.core.kgraph.KNode KNode} the diagram view
     * is clipped to.
     */
    CLIP,

    /**
     * Indicates a collapse of a {@link de.cau.cs.kieler.core.kgraph.KNode KNode} contained in the
     * view model.
     */
    COLLAPSE,

    /**
     * Indicates an expansion of a {@link de.cau.cs.kieler.core.kgraph.KNode KNode} contained in the
     * view model.
     */
    EXPAND,

    /**
     * Indicates the hiding of a {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement}
     * contained in the view model.
     */
    HIDE,

    /**
     * Indicates a change of the scale factor of a {@link de.cau.cs.kieler.core.kgraph.KNode KNode}
     * contained view model.
     */
    SCALE,

    /**
     * Indicates the show-up of a {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement}
     * contained in the view model.
     */
    SHOW,

    /**
     * Indicates a change of the diagram view's visible area due to zooming, panning, or the further
     * view change type encountered in this enumeration.
     */
    VIEW_PORT;

    /**
     * Convenience factory method.
     *
     * @return an {@link EnumSet} containing {@link #COLLAPSE} and {@link #EXPAND}
     */
    public static EnumSet<ViewChangeType> collapseExpand() {
        return EnumSet.of(COLLAPSE, EXPAND);
    }

    /**
     * Convenience factory method.
     *
     * @return an {@link EnumSet} containing {@link #HIDE} and {@link #SHOW}
     */
    public static EnumSet<ViewChangeType> hideShow() {
        return EnumSet.of(HIDE, SHOW);
    }

    /**
     * Convenience factory method.
     *
     * @return an {@link EnumSet} containing {@link #COLLAPSE}, {@link #EXPAND}, {@link #HIDE}, and
     *         {@link #SHOW}
     */
    public static EnumSet<ViewChangeType> collapseExpandHideShow() {
        return EnumSet.of(COLLAPSE, EXPAND);
    }

    /**
     * Convenience factory method.
     *
     * @return an {@link EnumSet} containing {@link #CLIP}, {@link #COLLAPSE}, {@link #EXPAND},
     *         {@link #HIDE}, and {@link #SHOW}
     */
    public static EnumSet<ViewChangeType> clipCollapseExpandHideShow() {
        return EnumSet.of(HIDE, SHOW);
    }

    /**
     * Convenience factory method.
     *
     * @return an {@link EnumSet} containing all entries of {@link ViewChangeType}.
     */
    public static EnumSet<ViewChangeType> all() {
        return EnumSet.allOf(ViewChangeType.class);
    }
}
