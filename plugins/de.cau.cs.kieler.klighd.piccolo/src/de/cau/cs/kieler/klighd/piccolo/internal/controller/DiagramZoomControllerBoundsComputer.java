/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.util.Set;

import org.eclipse.elk.core.math.Spacing;
import org.eclipse.elk.core.options.CoreOptions;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KInsets;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPoint;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * Logic of the bounding box computation of displayed diagrams (excerpts) required for zooming.
 * Is outsourced from {@link DiagramZoomController} for better testability.
 * 
 * @author chsch
 */
public class DiagramZoomControllerBoundsComputer {

    private final Predicate<KGraphElement> isDisplayedFilter;

    /**
     * Constructor.
     * 
     * @param isDisplayedFilter
     *            the filter determining whether to include a particular port, label, or
     *            subdiagram's edge in the bounding box computation
     */
    public DiagramZoomControllerBoundsComputer(final Predicate<KGraphElement> isDisplayedFilter) {
        this.isDisplayedFilter = isDisplayedFilter;
    }

    /**
     * Converts the <code>element</code>'s layout data into {@link PBounds}, respects an attached
     * {@link LayoutOptions#SCALE_FACTOR}.
     *
     * @param element
     *            the element
     * @return the requested bounding box in form of a {@link PBounds}
     */
    public PBounds toPBounds(final KShapeLayout element) {
        final double scale = element.getProperty(CoreOptions.SCALE_FACTOR);
        return new PBounds(element.getXpos(), element.getYpos(),
                element.getWidth() * scale, element.getHeight() * scale);
    }

    /**
     * Converts <code>node</code>'s layout data into {@link PBounds} s.t. <code>node</code>'s ports
     * and labels are included, respects an attached {@link LayoutOptions#SCALE_FACTOR}.
     *
     * @param node
     *            the node
     * @param doComputeSubDiagramSize
     *            set to <code>true</code> yields the bounding box of the nested diagram's content
     *            including <code>node</code>'s ports and labels if visible, with <code>false</code>
     *            the bounds of the given <code>node</code> including its port and labels if visible
     *            are returned
     * @param defaultZoomToFitContentSpacing
     *            default spacing to be applied if <code>doComputeSubDiagramSize</code> is
     *            <code>true</code>, see also
     *            {@link de.cau.cs.kieler.klighd.util.KlighdProperties#ZOOM_TO_FIT_CONTENT_SPACING},
     *            may be <code>null</code>.
     * @return the requested bounding box in form of a {@link PBounds}
     */
    public PBounds toPBoundsIncludingPortsAndLabels(final KNode node,
            final boolean doComputeSubDiagramSize, final Spacing defaultZoomToFitContentSpacing) {

        final Iterable<KGraphElement> kges = getIncludedKGEs(node, isDisplayedFilter);
        return toPBoundsIncludingPortsAndLabels(
                node, kges, doComputeSubDiagramSize, defaultZoomToFitContentSpacing);
    }


    /**
     * Provides an {@link Iterable} of <code>node</code>'s ports and labels to be included into its
     * bounding box.
     * 
     * @param node
     *            the {@link KNode} whose bounding box is requested
     * @param isDisplayedFilter
     *            the filter determining whether to include a particular port or label in the
     *            bounding box computation
     * @return an {@link Iterable} of <code>node</code>'s ports and labels to be included into its
     *         bounding box
     */
    public Iterable<KGraphElement> getIncludedKGEs(final KNode node,
            final Predicate<KGraphElement> isDisplayedFilter) {

        final Iterable<KLabel> portLabels = Iterables.concat(
                Iterables.<KPort, Iterable<KLabel>> transform(node.getPorts(), p -> p.getLabels()));

        final Iterable<KGraphElement> kges =
                Iterables.concat(node.getPorts(), node.getLabels(), portLabels);

        return Iterables.filter(kges, isDisplayedFilter);
    }

    /**
     * Converts <code>node</code>'s layout data into {@link PBounds} s.t. <code>node</code>'s ports
     * and labels are included, respects an attached {@link LayoutOptions#SCALE_FACTOR}.
     *
     * @param node
     *            the node
     * @param kges
     *            the ports and labels to be included into the computed bounding box
     * @param doComputeSubDiagramSize
     *            set to <code>true</code> yields the bounding box of the nested diagram's content
     *            including <code>node</code>'s ports and labels if visible, with <code>false</code>
     *            the bounds of the given <code>node</code> including its port and labels if visible
     *            are returned
     * @param defaultZoomToFitContentSpacing
     *            default spacing to be applied if <code>doComputeSubDiagramSize</code> is
     *            <code>true</code>, see also
     *            {@link de.cau.cs.kieler.klighd.util.KlighdProperties#ZOOM_TO_FIT_CONTENT_SPACING},
     *            may be <code>null</code>.
     * @return the requested bounding box in form of a {@link PBounds}
     */
    public PBounds toPBoundsIncludingPortsAndLabels(final KNode node,
            final Iterable<KGraphElement> kges, final boolean doComputeSubDiagramSize,
            final Spacing defaultZoomToFitContentSpaceing) {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        // note: for all the computations below the reference coordinate system is
        //  that of node's parent. Consequently not only node's width and height
        //  are evaluated but also its x and y position is included. This is necessary
        //  to properly make allowance of port and label positions being negative.

        final PBounds nodeBounds;
        if (doComputeSubDiagramSize) {
            nodeBounds = getContainedSubDiagramsBoundingBox(node, this.isDisplayedFilter);
        } else {
            nodeBounds = toPBounds(node);
        }

        if (!nodeBounds.isEmpty()) {
            minX = nodeBounds.getMinX();
            minY = nodeBounds.getMinY();
            maxX = nodeBounds.getMaxX();
            maxY = nodeBounds.getMaxY();
        }

        final float scale = node.getProperty(CoreOptions.SCALE_FACTOR).floatValue();

        boolean includedElement = false;
        boolean minXchanged = false;
        boolean minYchanged = false;
        boolean maxXchanged = false;
        boolean maxYchanged = false;

        for (final KShapeLayout element : Iterables.filter(kges, KShapeLayout.class)) {
            double val;
            final float xPos;
            final float yPos;
            if (element.eContainer() instanceof KPort) {
                // in case of a port label:
                xPos = node.getXpos() + element.getXpos() * scale + ((KShapeLayout) element.eContainer()).getXpos() * scale;
                yPos = node.getYpos() + element.getYpos() * scale + ((KShapeLayout) element.eContainer()).getYpos() * scale;
            } else {
                xPos = node.getXpos() + element.getXpos() * scale;
                yPos = node.getYpos() + element.getYpos() * scale;
            }

            if (xPos < minX) {
                minX = xPos;
                minXchanged = true;
            }

            if (yPos < minY) {
                minY = yPos;
                minYchanged = true;
            }

            val = xPos + element.getWidth() * scale;
            if (val > maxX) {
                maxX = val;
                maxXchanged = true;
            }

            val = yPos + element.getHeight() * scale;
            if (val > maxY) {
                maxY = val;
                maxYchanged = true;
            }

            includedElement = true;
        }

        if (includedElement) {
            final Spacing specificFitContentSpacing =
                    node.getProperty(KlighdProperties.ZOOM_TO_FIT_CONTENT_SPACING);
            final Spacing viewPortSpacing = specificFitContentSpacing != null
                    ? specificFitContentSpacing : defaultZoomToFitContentSpaceing;
            if (doComputeSubDiagramSize && viewPortSpacing != null) {
                // increase the nested diagram content's bounding box by the 'viewPortSpacing'
                //  in order to avoid surrounding edges to be hidden in half of their line width
                minX = minXchanged ? minX : nodeBounds.getX() - viewPortSpacing.getLeft();
                minY = minYchanged ? minY : nodeBounds.getY() - viewPortSpacing.getTop();
                maxX = maxXchanged ? maxX : nodeBounds.getMaxX() + viewPortSpacing.getRight();
                maxY = maxYchanged ? maxY : nodeBounds.getMaxY() + viewPortSpacing.getBottom();
            }
            nodeBounds.setRect(
                    minX, minY, maxX - minX, maxY - minY);

        } else {
            if (doComputeSubDiagramSize && nodeBounds.isEmpty()) {
                nodeBounds.setRect(toPBounds(node));
            }
            final KInsets insets = node.getInsets();
            nodeBounds.setRect(
                    nodeBounds.getX() + insets.getLeft() * scale,
                    nodeBounds.getY() + insets.getTop() * scale,
                    nodeBounds.getWidth() - insets.getLeft() * scale - insets.getRight() * scale,
                    nodeBounds.getHeight() - insets.getTop() * scale - insets.getBottom() * scale
            );
        }

        return nodeBounds;
    }

    /**
     * Determines the bounding box of the given {@link KNode}'s nested diagram, i.e. its child nodes
     * *and* their connecting edges.
     * 
     * @param node
     *            whose nested diagram's size is to be computed
     * @param isDisplayedFilter
     *            the filter checking the visibility of the candidate nodes and edges
     * @return the bounding box of the given {@link KNode}'s nested diagram in form of a
     *         {@link PBounds}
     */
    public PBounds getContainedSubDiagramsBoundingBox(final KNode node,
            final Predicate<KGraphElement> isDisplayedFilter) {
        final PBounds nodeBounds = new PBounds();
        final Set<KEdge> visitedEdges = Sets.newHashSet();

        for (final KNode childNode : Iterables.filter(node.getChildren(), isDisplayedFilter)) {
            nodeBounds.add(toPBoundsIncludingPortsAndLabels(childNode, false, null));
            
            final Iterable<KEdge> allEdges = Iterables.concat(childNode.getOutgoingEdges(), childNode.getIncomingEdges());
            for (final KEdge edge : Iterables.filter(allEdges,
                    // check only those edge not already checked, whose target is a child of 'node'
                    //  (i.e. those "staying in the nested diagram"), and which are actually visible
                    // NOTE: the "staying in the nested diagram" might not be precise enough in the general case
                    e -> visitedEdges.add(e) && e.getTarget().getParent() == node && isDisplayedFilter.apply(e))) {
                final PBounds edgeBounds = toPBounds(edge);
                nodeBounds.add(edgeBounds);
            }
        }

        if (!nodeBounds.isEmpty()) {
            final double scale = node.getProperty(CoreOptions.SCALE_FACTOR);
            nodeBounds.setRect(
                    nodeBounds.getX() * scale, nodeBounds.getY() * scale,
                    nodeBounds.getWidth() * scale, nodeBounds.getHeight() * scale);

            // adjust the bounding box by the position of 'node'
            nodeBounds.moveBy(node.getXpos(), node.getYpos());
        }

        return nodeBounds;
    }

    /**
     * Converts <code>edge</code>'s layout data into {@link PBounds} denoting <code>edge</code>'s bounding box.
     *
     * @param edge
     *            the edge
     * @return <code>edge</code>'s bounding box in form of a {@link PBounds}
     */
    public PBounds toPBounds(final KEdge edge) {
        final PBounds bounds = new PBounds();
        final KPoint source = edge.getSourcePoint();
        if (source != null) {
            bounds.add(source.getX(), source.getY());
        }
        for (KPoint bend : edge.getBendPoints()) {
            bounds.add(bend.getX(), bend.getY());
        }
        final KPoint target = edge.getTargetPoint();
        if (target != null) {
            bounds.add(target.getX(), target.getY());
        }
        return bounds;
    }
}
