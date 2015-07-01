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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.syntheses;

import java.util.EnumSet;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortAlignment;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortLabelPlacement;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.kiml.options.SizeOptions;
import de.cau.cs.kieler.kiml.util.BoxLayoutProvider;
import de.cau.cs.kieler.kiml.util.FixedLayoutProvider;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;

/**
 * A library class listing the typical/relevant layout options to set in diagram synthesis
 * implementations.<br>
 * The offered set may be incomplete, refer to {@link LayoutOptions} for further option definitions.
 * However, keep in mind that some of the options defined there are set/overwritten by KLighD
 * internally, and some, e.g. the label concerning ones, are set in the helper functions, e.g. in
 * <code>de.cau.cs.kieler.core.krendering.extensions.KLabelExtensions</code>, already.
 *
 * @author chsch
 */
public final class DiagramLayoutOptions {

    private DiagramLayoutOptions() {
    }

    /**
     * Configures the layout algorithm which is used to arrange the children (nodes and their edges)
     * of the {@link de.cau.cs.kieler.core.kgraph.KNode KNode} the option is set on. Setting this
     * option on leaf {@link de.cau.cs.kieler.core.kgraph.KNode KNode} (without any children) has no
     * effect.<br>
     * Examples of valid algorithm ids are {@link #BOX}, {@link #FIXED}, {@link #KLAY_LAYERED},
     * {@link #GRAPHVIZ_DOT}, {@link #GRAPHVIZ_CIRCO}, {@link #OGDF_PLANARIZATION},
     * {@link #OGDF_CIRCULAR}.<br>
     * <br>
     * Find more layout algorithms and information on their options on our
     * <a href="http://layout.rtsys.informatik.uni-kiel.de:9444/Providedlayout.html">web service</a>.
     *
     * @see LayoutOptions#ALGORITHM
     */
    public static final IProperty<String> ALGORITHM = LayoutOptions.ALGORITHM;

    /**
     * @see BoxLayoutProvider
     */
    public static final String BOX = BoxLayoutProvider.ID;

    /**
     * @see FixedLayoutProvider
     */
    public static final String FIXED = FixedLayoutProvider.ID;

    /**
     * Our default layouter for layer-based diagrams with and without ports, implemented in Java.<br>
     * Supports {@link EdgeRouting#POLYLINE oblique polyline}, {@link EdgeRouting#ORTHOGONAL
     * orthogonal polyline}, and experimental {@link EdgeRouting#SPLINES spline} edge routing.<br>
     * <br>
     * If you choose this layouter, you might want to try the different node placement strategies we
     * currently offer. Have a look at the layout options called <code>NODE_PLACER</code> being
     * defined in <code>de.cau.cs.kieler.klay.layered.Properties</code>, try the different values
     * offered by <code>de.cau.cs.kieler.klay.layered.p4nodes.NodePlacementStrategy</code>. You need
     * to add a dependency to <code>de.cau.cs.kieler.klay.layered</code> to your plug-in to access
     * the aforementioned classes.
     */
    public static final String KLAY_LAYERED = "de.cau.cs.kieler.klay.layered";

    /**
     * Creates layer-based layouts.<br>
     * Requires the bundle <code>de.cau.cs.kieler.kiml.graphviz.layouter</code> and <a
     * href="http://www.graphviz.org/">Graphviz</a> to be installed.<br>
     * Supports {@link EdgeRouting#POLYLINE oblique polyline}, {@link EdgeRouting#ORTHOGONAL
     * orthogonal polyline}, and {@link EdgeRouting#SPLINES spline} edge routing.
     */
    public static final String GRAPHVIZ_DOT = "de.cau.cs.kieler.graphviz.dot";

    /**
     * Creates circular layouts.<br>
     * Requires the bundle <code>de.cau.cs.kieler.graphviz.layouter</code> and <a
     * href="http://www.graphviz.org/">Graphviz</a> to be installed.
     */
    public static final String GRAPHVIZ_CIRCO = "de.cau.cs.kieler.graphviz.circo";

    /**
     * Dedicated to Class-Diagram-like diagrams.<br>
     * Requires the bundle <code>de.cau.cs.kieler.kiml.ogdf</code>.
     */
    public static final String OGDF_PLANARIZATION = "de.cau.cs.kieler.ogdf.planarization";
    /**
     * Creates circular layouts.<br>
     * Requires the bundle <code>de.cau.cs.kieler.kiml.ogdf</code>.
     */
    public static final String OGDF_CIRCULAR = "de.cau.cs.kieler.kiml.ogdf.circular";


    /**
     * @see LayoutOptions#ADDITIONAL_PORT_SPACE;
     */
    public static final IProperty<Margins> ADDITIONAL_PORT_SPACE =
            LayoutOptions.ADDITIONAL_PORT_SPACE;

    /**
     * @see LayoutOptions#BORDER_SPACING
     */
    public static final IProperty<Float> BORDER_SPACING = LayoutOptions.BORDER_SPACING;

    /**
     * @see LayoutOptions#DIRECTION
     */
    public static final IProperty<Direction> DIRECTION = LayoutOptions.DIRECTION;

    /**
     * @see LayoutOptions#EDGE_ROUTING
     */
    public static final IProperty<EdgeRouting> EDGE_ROUTING = LayoutOptions.EDGE_ROUTING;

    /**
     * @see LayoutOptions#LABEL_SPACING
     */
    public static final IProperty<Float> LABEL_SPACING = LayoutOptions.LABEL_SPACING;

    /**
     * @see LayoutOptions#LAYOUT_HIERARCHY
     */
    public static final IProperty<Boolean> LAYOUT_HIERARCHY = LayoutOptions.LAYOUT_HIERARCHY;

    /**
     * @see LayoutOptions#OFFSET
     */
    public static final IProperty<Float> OFFSET = LayoutOptions.OFFSET;

    /**
     * @see LayoutOptions#PORT_ALIGNMENT
     */
    public static final IProperty<PortAlignment> PORT_ALIGNMENT = LayoutOptions.PORT_ALIGNMENT;

    /**
     * @see LayoutOptions#PORT_ALIGNMENT_EAST
     */
    public static final IProperty<PortAlignment> PORT_ALIGNMENT_EAST = LayoutOptions.PORT_ALIGNMENT_EAST;

    /**
     * @see LayoutOptions#PORT_ALIGNMENT_NORTH
     */
    public static final IProperty<PortAlignment> PORT_ALIGNMENT_NORTH =
            LayoutOptions.PORT_ALIGNMENT_NORTH;

    /**
     * @see LayoutOptions#PORT_ALIGNMENT_SOUTH
     */
    public static final IProperty<PortAlignment> PORT_ALIGNMENT_SOUTH =
            LayoutOptions.PORT_ALIGNMENT_SOUTH;

    /**
     * @see LayoutOptions#PORT_ALIGNMENT_WEST
     */
    public static final IProperty<PortAlignment> PORT_ALIGNMENT_WEST = LayoutOptions.PORT_ALIGNMENT_WEST;

    /**
     * @see LayoutOptions#PORT_ANCHOR
     */
    public static final IProperty<KVector> PORT_ANCHOR = LayoutOptions.PORT_ANCHOR;

    /**
     * @see LayoutOptions#PORT_CONSTRAINTS
     */
    public static final IProperty<PortConstraints> PORT_CONSTRAINTS = LayoutOptions.PORT_CONSTRAINTS;

    /**
     * @see LayoutOptions#PORT_INDEX
     */
    public static final IProperty<Integer> PORT_INDEX = LayoutOptions.PORT_INDEX;

    /**
     * @see LayoutOptions#PORT_LABEL_PLACEMENT
     */
    public static final IProperty<PortLabelPlacement> PORT_LABEL_PLACEMENT =
            LayoutOptions.PORT_LABEL_PLACEMENT;

    /**
     * @see LayoutOptions#PORT_SPACING
     */
    public static final IProperty<Float> PORT_SPACING = LayoutOptions.PORT_SPACING;

    /**
     * @see LayoutOptions#PORT_SIDE
     */
    public static final IProperty<PortSide> PORT_SIDE = LayoutOptions.PORT_SIDE;

    /**
     * @see LayoutOptions#SIZE_CONSTRAINT
     */
    public static final IProperty<EnumSet<SizeConstraint>> SIZE_CONSTRAINT =
            LayoutOptions.SIZE_CONSTRAINT;

    /**
     * @see LayoutOptions#SIZE_OPTIONS
     */
    public static final IProperty<EnumSet<SizeOptions>> SIZE_OPTIONS = LayoutOptions.SIZE_OPTIONS;

    /**
     * @see LayoutOptions#SPACING
     */
    public static final IProperty<Float> SPACING = LayoutOptions.SPACING;

}
