/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.labels.inline;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeLabelPlacement;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

import com.google.common.math.DoubleMath;

import de.cau.cs.kieler.klighd.IStyleModifier;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPoint;
import de.cau.cs.kieler.klighd.krendering.KInvisibility;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory;

/**
 * Switches label decorations on and off depending on the direction of the edge segment their
 * inline edge label is placed on.
 * 
 * TODO Document usage.
 * 
 * @author cds
 */
public class InlineEdgeLabelStyleModifier implements IStyleModifier {
    
    /** Modifier ID. */
    public static final String ID =
            "de.cau.cs.kieler.klighd.labels.inline.inlineEdgeLabelStyleModifier";
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    // Installation
    
    /**
     * Installs a {@link KInvisibility} style on the given rendering that is set up for the given
     * visibility mode and that will be evaluated after layout runs by this style modifier.
     * 
     * @param rendering
     *            the rendering to install the invisibility style on.
     * @param visibility
     *            the visibility mode.
     */
    public static void install(final KRendering rendering, final Visibility visibility) {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        
        KInvisibility invisibility = factory.createKInvisibility();
        invisibility.setInvisible(false);
        invisibility.setProperty(STYLE_VISIBILITY, visibility);
        invisibility.setModifierId(ID);
        
        rendering.getStyles().add(invisibility);
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    // IStyleModifier

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean modify(final StyleModificationContext context) {
        if (!shouldProcess(context)) {
            return false;
        }
        
        // The graph element is an edge label
        KInvisibility style = (KInvisibility) context.getStyle();
        KLabel label = (KLabel) context.getGraphElement();
        KEdge edge = (KEdge) label.eContainer();
        
        // We use the layout direction to make decisions later
        Direction layoutDirection = retrieveLayoutDirection(edge);
        if (layoutDirection == null) {
            // The edge is too hierarchical for us...
            return false;
        }
        
        // Here's the simple choices
        Visibility visibility = context.getStyle().getProperty(STYLE_VISIBILITY);
        
        if (visibility == Visibility.SEGMENT_HORIZONTAL) {
            // Center edge labels are always placed on horizontal segments in horizontal layouts
            style.setInvisible(layoutDirection.isVertical());
            
        } else if (visibility == Visibility.SEGMENT_VERTICAL) {
            // Center edge labels are always placed on vertical segments in vertical layouts
            style.setInvisible(layoutDirection.isHorizontal());
            
        } else {
            // Check whether the desired visibility direction matches the actual direction of the
            // edge segment the label is placed on
            style.setInvisible(computeDirectionalVisibility(edge, label) != visibility);
        }
        
        return true;
    }

    /**
     * Checks whether we even need to bother calculating things.
     */
    private boolean shouldProcess(final StyleModificationContext context) {
        // We only make sense on invisibility styles
        if (!(context.getStyle() instanceof KInvisibility)) {
            return false;
        }
        
        // If the style doesn't have a visibility option set, we don't care about it. At all.
        if (!context.getStyle().hasProperty(STYLE_VISIBILITY)) {
            return false;
        }
        
        // We only care about labels
        if (!(context.getGraphElement() instanceof KLabel)) {
            return false;
        }
        
        // Edge labels, in fact
        if (!(context.getGraphElement().eContainer() instanceof KEdge)) {
            return false;
        }
        
        // Center edge labels, in particular
        EdgeLabelPlacement placement = context.getGraphElement().getProperty(
                CoreOptions.EDGE_LABELS_PLACEMENT);
        if (placement != EdgeLabelPlacement.CENTER && placement != EdgeLabelPlacement.UNDEFINED) {
            return false;
        }

        // Center edge labels that are placed inline, to be perfectly precise
        if (!context.getGraphElement().getProperty(CoreOptions.EDGE_LABELS_INLINE)) {
            return false;
        }
        
        // All clear!
        return true;
    }

    /**
     * Retrieves the layout direction for the edge.
     */
    private Direction retrieveLayoutDirection(final KEdge edge) {
        // Works because shouldProcess(...) checks whether the graph element is an edge label
        KNode source = edge.getSource();
        KNode target = edge.getTarget();
        
        KNode graph = null;
        
        if (source.getParent() == target.getParent()) {
            graph = source.getParent();
        } else if (source.getParent() == target) {
            graph = target;
        } else if (source == target.getParent()) {
            graph = source;
        }
        
        return graph == null
                ? null
                : graph.getProperty(CoreOptions.DIRECTION);
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    // Directional Visibility
    
    /** Tolerance for coordinate comparisons. */
    private static final double TOLERANCE = 0.5;
    
    /**
     * Computes the visibility directional styles must desire to be visible.
     */
    private Visibility computeDirectionalVisibility(final KEdge edge, final KLabel label) {
        Pair<KPoint, KPoint> labelSegment = computeLabelSegmentEndPoints(edge, label);
        if (labelSegment == null) {
            // We couldn't find the edge segment the label was placed on
            return null;
        }
        
        KPoint tailPoint = labelSegment.getFirst();
        KPoint headPoint = labelSegment.getSecond();
        
        if (DoubleMath.fuzzyEquals(tailPoint.getX(), headPoint.getX(), TOLERANCE)) {
            // Vertical segment
            if (tailPoint.getY() < headPoint.getY()) {
                return Visibility.DIRECTION_DOWN;
            } else if (tailPoint.getY() > headPoint.getY()) {
                return Visibility.DIRECTION_UP;
            }
            
        } else if (DoubleMath.fuzzyEquals(tailPoint.getY(), headPoint.getY(), TOLERANCE)) {
            // Horizontal segment
            if (tailPoint.getX() < headPoint.getX()) {
                return Visibility.DIRECTION_RIGHT;
            } else if (tailPoint.getX() > headPoint.getX()) {
                return Visibility.DIRECTION_LEFT;
            }
        }
        
        return null;
    }
    
    /**
     * Return the end points of the edge segment the label is placed on, or {@code null} if such
     * a segment could not be found. The first point points the way to the edge's tail, the second
     * point points to the edge's head.
     */
    private Pair<KPoint, KPoint> computeLabelSegmentEndPoints(final KEdge edge,
            final KLabel label) {
        
        Rectangle2D.Float labelRect = new Rectangle2D.Float(
                label.getXpos(), label.getYpos(), label.getWidth(), label.getHeight());
        
        // Retrieve all edge coordinates (except for the source point, we'll retrieve that soon)
        List<KPoint> points = new ArrayList<>(edge.getBendPoints().size() + 2);
        points.addAll(edge.getBendPoints());
        points.add(edge.getTargetPoint());
        
        // Iterate over all pairs of consecutive points
        KPoint prevPoint = edge.getSourcePoint();
        for (KPoint currPoint : points) {
            boolean segmentFound = labelRect.intersectsLine(
                    prevPoint.getX(), prevPoint.getY(), currPoint.getX(), currPoint.getY());
            
            if (segmentFound) {
                return Pair.of(prevPoint, currPoint);
            }
            
            // DON'T STOP... BELIEVIN'...!
            prevPoint = currPoint;
        }
        
        // We haven't found and edge segment that intersects the label
        return null;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    // Things
    
    /** Property set on styles that don't always want to be visible. */
    public static final IProperty<Visibility> STYLE_VISIBILITY = new Property<>(
            "inlineEdgeLabelStyleVisibility");
    
    /**
     * Styles on things that don't always want to be visible can set to be visible in different
     * situations, all of which have to do with the orientation of the edge segment they are on.
     */
    public static enum Visibility {
        /** Visible if the label lies on a horizontal edge segment. */
        SEGMENT_HORIZONTAL,
        /** Visible if the label lies on a vertical edge segment. */
        SEGMENT_VERTICAL,
        /**
         * Visible if the label lies on a horizontal edge segment that has to be traversed
         * leftwards to get to the edge's head.
         */
        DIRECTION_LEFT,
        /**
         * Visible if the label lies on a horizontal edge segment that has to be traversed
         * rightwards to get to the edge's head.
         */
        DIRECTION_RIGHT,
        /**
         * Visible if the label lies on a vertical edge segment that has to be traversed
         * upwards to get to the edge's head.
         */
        DIRECTION_UP,
        /**
         * Visible if the label lies on a vertical edge segment that has to be traversed
         * downwards to get to the edge's head.
         */
        DIRECTION_DOWN;
    }
}
