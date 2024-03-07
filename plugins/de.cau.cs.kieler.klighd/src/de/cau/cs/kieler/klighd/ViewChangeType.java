/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd;

import java.util.EnumSet;

/**
 * Defines the possible view change types being performed by KLighD {@link IViewer IViewers}.
 *
 * @author chsch
 */
public enum ViewChangeType {

    /**
     * Indicates a change of the {@link de.cau.cs.kieler.klighd.kgraph.KNode KNode} the diagram view
     * is clipped to.
     */
    CLIP,

    /**
     * Indicates a collapse of a {@link de.cau.cs.kieler.klighd.kgraph.KNode KNode} contained in the
     * view model.
     */
    COLLAPSE,

    /**
     * Indicates an expansion of a {@link de.cau.cs.kieler.klighd.kgraph.KNode KNode} contained in the
     * view model.
     */
    EXPAND,

    /**
     * Indicates the hiding of a {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement}
     * contained in the view model.
     */
    HIDE,

    /**
     * Indicates a change of the scale factor of a {@link de.cau.cs.kieler.klighd.kgraph.KNode KNode}
     * contained view model.
     */
    SCALE,

    /**
     * Indicates the show-up of a {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement}
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
        return EnumSet.of(COLLAPSE, EXPAND, HIDE, SHOW);
    }

    /**
     * Convenience factory method.
     *
     * @return an {@link EnumSet} containing {@link #CLIP}, {@link #COLLAPSE}, {@link #EXPAND},
     *         {@link #HIDE}, and {@link #SHOW}
     */
    public static EnumSet<ViewChangeType> clipCollapseExpandHideShow() {
        return EnumSet.of(CLIP, COLLAPSE, EXPAND, HIDE, SHOW);
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
