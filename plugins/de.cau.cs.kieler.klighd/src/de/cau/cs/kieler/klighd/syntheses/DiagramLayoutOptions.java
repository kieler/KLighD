/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2015 by
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
package de.cau.cs.kieler.klighd.syntheses;

import java.util.EnumSet;

import org.eclipse.elk.core.math.ElkMargin;
import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.BoxLayouterOptions;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.options.FixedLayouterOptions;
import org.eclipse.elk.core.options.HierarchyHandling;
import org.eclipse.elk.core.options.PortAlignment;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.options.PortLabelPlacement;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.elk.core.options.SizeOptions;
import org.eclipse.elk.core.util.BoxLayoutProvider;
import org.eclipse.elk.core.util.FixedLayoutProvider;
import org.eclipse.elk.graph.properties.IProperty;

/**
 * A library class listing the typical/relevant layout options to set in diagram synthesis
 * implementations.<br>
 * The offered set may be incomplete, refer to {@link CoreOptions} for further option definitions.
 * However, keep in mind that some of the options defined there are set/overwritten by KLighD
 * internally, and some, e.g. the label concerning ones, are set in the helper functions, e.g. in
 * <code>de.cau.cs.kieler.klighd.krendering.extensions.KLabelExtensions</code>, already.
 *
 * @author chsch
 */
public final class DiagramLayoutOptions {

    // MIGRATE The names of the underlying options have changed. Should these names change as well?
    
    private DiagramLayoutOptions() {
    }

    /**
     * Configures the layout algorithm which is used to arrange the children (nodes and their edges)
     * of the {@link de.cau.cs.kieler.klighd.kgraph.KNode KNode} the option is set on. Setting this
     * option on leaf {@link de.cau.cs.kieler.klighd.kgraph.KNode KNode} (without any children) has no
     * effect.<br>
     * Examples of valid algorithm ids are {@link #BOX}, {@link #FIXED}, {@link #KLAY_LAYERED},
     * {@link #GRAPHVIZ_DOT}, {@link #GRAPHVIZ_CIRCO}, {@link #OGDF_PLANARIZATION},
     * {@link #OGDF_CIRCULAR}.<br>
     * <br>
     * Find more layout algorithms and information on their options on our
     * <a href="http://layout.rtsys.informatik.uni-kiel.de:9444/Providedlayout.html">web service</a>.
     *
     * @see CoreOptions#ALGORITHM
     */
    public static final IProperty<String> ALGORITHM = CoreOptions.ALGORITHM;

    /**
     * @see BoxLayoutProvider
     */
    public static final String BOX = BoxLayouterOptions.ALGORITHM_ID;

    /**
     * @see FixedLayoutProvider
     */
    public static final String FIXED = FixedLayouterOptions.ALGORITHM_ID;

    /**
     * The default layouter for layer-based diagrams with and without ports, implemented in Java.<br>
     * Supports {@link EdgeRouting#POLYLINE oblique polyline}, {@link EdgeRouting#ORTHOGONAL
     * orthogonal polyline}, and experimental {@link EdgeRouting#SPLINES spline} edge routing.<br>
     * <br>
     * If you choose this layouter, you might want to try the different node placement strategies we
     * currently offer. Have a look at the layout options called <code>NODE_PLACEMENT_STRATEGY</code> being
     * defined in <code>org.eclipse.elk.alg.layered.options.LayeredOptions</code>, try the different values
     * offered by <code>org.eclipse.elk.alg.layered.options.NodePlacementStrategy</code>. You need
     * to add a dependency to the <code>org.eclipse.elk.alg.layered</code> eclipse plug-in or maven artifact
     * to access the aforementioned classes.
     */
    public static final String ELK_LAYERED = "org.eclipse.elk.layered";
    
    /**
     * @deprecated use {@link #ELK_LAYERED}
     */
    public static final String KLAY_LAYERED = ELK_LAYERED;
    
    /**
     * Creates layer-based layouts.<br>
     * Requires the bundle <code>org.eclipse.elk.alg.graphviz.layouter</code> and <a
     * href="http://www.graphviz.org/">Graphviz</a> to be installed.<br>
     * Supports {@link EdgeRouting#POLYLINE oblique polyline}, {@link EdgeRouting#ORTHOGONAL
     * orthogonal polyline}, and {@link EdgeRouting#SPLINES spline} edge routing.
     */
    public static final String GRAPHVIZ_DOT = "org.eclipse.elk.graphviz.dot";

    /**
     * Creates circular layouts.<br>
     * Requires the bundle <code>org.eclipse.elk.alg.graphviz.layouter</code> and <a
     * href="http://www.graphviz.org/">Graphviz</a> to be installed.
     */
    public static final String GRAPHVIZ_CIRCO = "org.eclipse.elk.graphviz.circo";

    /**
     * Dedicated to Class-Diagram-like diagrams.<br>
     * Requires the bundle <code>de.cau.cs.kieler.kiml.ogdf</code>.
     */
    public static final String OGDF_PLANARIZATION = "de.cau.cs.kieler.kiml.ogdf.planarization";
    /**
     * Creates circular layouts.<br>
     * Requires the bundle <code>de.cau.cs.kieler.kiml.ogdf</code>.
     */
    public static final String OGDF_CIRCULAR = "de.cau.cs.kieler.kiml.ogdf.circular";


    /**
     * @see CoreOptions#ADDITIONAL_PORT_SPACE;
     */
    public static final IProperty<ElkMargin> ADDITIONAL_PORT_SPACE =
            CoreOptions.SPACING_PORTS_SURROUNDING;

    /**
     * @see CoreOptions#DIRECTION
     */
    public static final IProperty<Direction> DIRECTION = CoreOptions.DIRECTION;

    /**
     * @see CoreOptions#EDGE_ROUTING
     */
    public static final IProperty<EdgeRouting> EDGE_ROUTING = CoreOptions.EDGE_ROUTING;

    /**
     * @see CoreOptions#LABEL_SPACING
     */
    public static final IProperty<Double> LABEL_SPACING = CoreOptions.SPACING_LABEL_LABEL;

    /**
     * @see CoreOptions#HIERARCHY_HANDLING
     */
    public static final IProperty<HierarchyHandling> HIERARCHY_HANDLING =
            CoreOptions.HIERARCHY_HANDLING;

    /**
     * @see CoreOptions#OFFSET
     */
    public static final IProperty<Double> OFFSET = CoreOptions.PORT_BORDER_OFFSET;

    /**
     * @see CoreOptions#PORT_ALIGNMENT
     */
    public static final IProperty<PortAlignment> PORT_ALIGNMENT = CoreOptions.PORT_ALIGNMENT_DEFAULT;

    /**
     * @see CoreOptions#PORT_ALIGNMENT_EAST
     */
    public static final IProperty<PortAlignment> PORT_ALIGNMENT_EAST = CoreOptions.PORT_ALIGNMENT_EAST;

    /**
     * @see CoreOptions#PORT_ALIGNMENT_NORTH
     */
    public static final IProperty<PortAlignment> PORT_ALIGNMENT_NORTH =
            CoreOptions.PORT_ALIGNMENT_NORTH;

    /**
     * @see CoreOptions#PORT_ALIGNMENT_SOUTH
     */
    public static final IProperty<PortAlignment> PORT_ALIGNMENT_SOUTH =
            CoreOptions.PORT_ALIGNMENT_SOUTH;

    /**
     * @see CoreOptions#PORT_ALIGNMENT_WEST
     */
    public static final IProperty<PortAlignment> PORT_ALIGNMENT_WEST = CoreOptions.PORT_ALIGNMENT_WEST;

    /**
     * @see CoreOptions#PORT_ANCHOR
     */
    public static final IProperty<KVector> PORT_ANCHOR = CoreOptions.PORT_ANCHOR;

    /**
     * @see CoreOptions#PORT_CONSTRAINTS
     */
    public static final IProperty<PortConstraints> PORT_CONSTRAINTS = CoreOptions.PORT_CONSTRAINTS;

    /**
     * @see CoreOptions#PORT_INDEX
     */
    public static final IProperty<Integer> PORT_INDEX = CoreOptions.PORT_INDEX;

    /**
     * @see CoreOptions#PORT_LABEL_PLACEMENT
     */
    public static final IProperty<EnumSet<PortLabelPlacement>> PORT_LABEL_PLACEMENT =
            CoreOptions.PORT_LABELS_PLACEMENT;

    /**
     * @see CoreOptions#PORT_SPACING
     */
    public static final IProperty<Double> PORT_SPACING = CoreOptions.SPACING_PORT_PORT;

    /**
     * @see CoreOptions#PORT_SIDE
     */
    public static final IProperty<PortSide> PORT_SIDE = CoreOptions.PORT_SIDE;

    /**
     * @see CoreOptions#SIZE_CONSTRAINT
     */
    public static final IProperty<EnumSet<SizeConstraint>> SIZE_CONSTRAINT =
            CoreOptions.NODE_SIZE_CONSTRAINTS;

    /**
     * @see CoreOptions#SIZE_OPTIONS
     */
    public static final IProperty<EnumSet<SizeOptions>> SIZE_OPTIONS = CoreOptions.NODE_SIZE_OPTIONS;

    /**
     * @see CoreOptions#PADDING
     */
    public static final IProperty<ElkPadding> PADDING = CoreOptions.PADDING;

    /**
     * @see CoreOptions#SPACING_NODE_NODE
     */
    public static final IProperty<Double> SPACING = CoreOptions.SPACING_NODE_NODE;

}
