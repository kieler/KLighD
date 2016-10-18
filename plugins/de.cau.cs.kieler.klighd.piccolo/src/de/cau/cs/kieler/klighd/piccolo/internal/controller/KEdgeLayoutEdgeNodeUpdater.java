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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.awt.geom.Point2D;

import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KEdgeLayout;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.KPoint;
import de.cau.cs.kieler.klighd.krendering.KPolyline;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingUtil;
import de.cau.cs.kieler.klighd.krendering.KSpline;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;

/**
 * A specialized EMF adapter which is in charge of synchronizing the points of the
 * {@link KEdgeNode} that represents of the {@link de.cau.cs.kieler.core.kgraph.KEdge KEdge}
 * this adapter is attached to.
 *
 * @author chsch
 */
class KEdgeLayoutEdgeNodeUpdater extends AdapterImpl {

    KEdgeLayoutEdgeNodeUpdater(final KEdgeNode theEdgeRep, final DiagramController theController) {
        this.controller = theController;
        this.edgeRep = theEdgeRep;
    }

    private DiagramController controller = null;
    private final KEdgeNode edgeRep;

    @Override
    public void notifyChanged(final Notification notification) {
        super.notifyChanged(notification);

        final KEdge edge = edgeRep.getViewModelElement();
        final KRendering rendering = KRenderingUtil.dereference(edge.getData(KRendering.class));
        final boolean renderedAsPolyline =
                rendering instanceof KPolyline && !(rendering instanceof KSpline);

        final KEdgeLayout edL;
        final Object notifier = notification.getNotifier();
        if (notifier instanceof KEdgeLayout) {
            final int featureId = notification.getFeatureID(KEdgeLayout.class);

            if (featureId == KGraphPackage.KEDGE_LAYOUT__BEND_POINTS
                    || featureId == KGraphPackage.KEDGE_LAYOUT__SOURCE_POINT
                    || featureId == KGraphPackage.KEDGE_LAYOUT__TARGET_POINT) {
                edL = (KEdgeLayout) notifier;
            } else {
                edL = null;
            }
        } else if (notifier instanceof KPoint) {
            final int featureId = notification.getFeatureID(KPoint.class);

            if (featureId == KGraphPackage.KPOINT__X
                    || featureId == KGraphPackage.KPOINT__Y) {
                edL = (KEdgeLayout) ((EObject) notifier).eContainer();

            } else {
                edL = null;
            }
        } else {
            edL = null;
        }

        if (edL != null) {
            if (controller.isRecording()) {
                controller.recordChange(edgeRep, Pair.of(
                        getBendPoints(edL, renderedAsPolyline), getJunctionPoints(edL)));
            } else {
                edgeRep.setBendPoints(getBendPoints(edL, renderedAsPolyline));
                edgeRep.setJunctionPoints(getJunctionPoints(edL));

                final KEdgeRenderingController nodeController = edgeRep.getRenderingController();
                if (nodeController != null) {
                    nodeController.modifyStyles();
                }

                edgeRep.firePropertyChange(0, IKlighdNode.PROPERTY_BOUNDS_FINISHED, null, Boolean.TRUE);
            }
        }
    }

    /**
     * Returns an array of bend points from the given {@code KEdgeLayout}.
     *
     * @param edgeLayout
     *            the edge layout
     * @param renderedAsPolyline
     *            true if the edge is rendered by a polyline, causes approximation of the bend
     *            points if the layouter returned spline-based ones
     * @return the bend points
     */
    // method is package protected as it is used in DiagramController, too
    static Point2D[] getBendPoints(final KEdgeLayout edgeLayout,
            final boolean renderedAsPolyline) {

        // chsch: the following 8 lines for approximating spline connections are mainly taken
        // from de.cau.cs.kieler.kiml.gmf.GmfLayoutEditPolicy#getBendPoints()
        KVectorChain bendPoints = edgeLayout.createVectorChain();

        // for connections that support splines the control points are passed without change
        final boolean layoutedAsSpline = edgeLayout.getProperty(CoreOptions.EDGE_ROUTING)
                == EdgeRouting.SPLINES;
        // in other cases an approximation is used // SUPPRESS CHECKSTYLE NEXT MagicNumber
        if (renderedAsPolyline && layoutedAsSpline && bendPoints.size() >= 4) {
            bendPoints = ElkMath.approximateBezierSpline(bendPoints);
        }

        // build the bend point array
        final Point2D[] points = new Point2D[bendPoints.size()];
        int i = 0;
        for (final KVector bend : bendPoints) {
            points[i++] = new Point2D.Double(bend.x, bend.y);
        }
        return points;
    }

    /**
     * Returns an array of junction points from the given {@code KEdgeLayout}.
     *
     * @param edgeLayout
     *            the edge layout
     * @return the junction points or an empty Point2D[] if none exist
     */
    // method is package protected as it is used in DiagramController, too
    static Point2D[] getJunctionPoints(final KEdgeLayout edgeLayout) {

        final KVectorChain junctionPoints = edgeLayout.getProperty(CoreOptions.JUNCTION_POINTS);

        if (junctionPoints == null || junctionPoints.isEmpty()) {
            return new Point2D[0];
        }

        // build the bend point array
        final Point2D[] points = new Point2D[junctionPoints.size()];
        int i = 0;
        for (final KVector bend : junctionPoints) {
            points[i++] = new Point2D.Double(bend.x, bend.y);
        }
        return points;
    }
}
