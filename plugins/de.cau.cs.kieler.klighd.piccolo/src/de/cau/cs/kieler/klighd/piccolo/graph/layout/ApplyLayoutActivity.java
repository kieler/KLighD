/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.graph.layout;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphEdge;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphObject;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphPositional;
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * The Piccolo activity for applying a layout back to a diagram utilizing the interfaces from the
 * {@code de.cau.cs.kieler.klighd.piccolo.graph} package.
 * 
 * @author mri
 */
public class ApplyLayoutActivity extends PInterpolatingActivity {

    /** the default duration for the activity. */
    private static final long DEFAULT_DURATION = 1000;

    /** the list of layout transitions required to apply the layout. */
    private List<ILayoutTransition> layoutTransitions = new LinkedList<ILayoutTransition>();

    /**
     * Constructs an apply layout activity.
     * 
     * @param mapping
     *            the layout mapping which contains the layout information
     */
    public ApplyLayoutActivity(final LayoutMapping<IGraphObject> mapping) {
        this(mapping, DEFAULT_DURATION);
    }

    /**
     * Contructs an apply layout activity with a given duration.
     * 
     * @param mapping
     *            the layout mapping which contains the layout information
     * @param duration
     *            the duration over which the activity applies the layout
     */
    public ApplyLayoutActivity(final LayoutMapping<IGraphObject> mapping, final long duration) {
        super(duration > 0 ? duration : DEFAULT_DURATION);
        // create the layout transitions required to apply the layout
        createLayoutTransitions(mapping);
    }

    /**
     * Fills the list of layout transitions.
     * 
     * @param mapping
     *            the layout mapping
     */
    private void createLayoutTransitions(final LayoutMapping<IGraphObject> mapping) {
        for (Entry<KGraphElement, IGraphObject> entry : mapping.getGraphMap().entrySet()) {
            IGraphObject graphObject = entry.getValue();
            if (graphObject instanceof IGraphPositional) {
                // add a transition for a positional
                KGraphElement layoutElement = entry.getKey();
                if (layoutElement instanceof KNode || layoutElement instanceof KPort) {
                    KShapeLayout shapeLayout = layoutElement.getData(KShapeLayout.class);
                    layoutTransitions.add(new PositionalLayoutTransition(
                            (IGraphPositional) graphObject, shapeLayout));
                }
            } else if (graphObject instanceof IGraphEdge) {
                // add a transition for an edge
                KGraphElement layoutElement = entry.getKey();
                if (layoutElement instanceof KEdge) {
                    KEdgeLayout edgeLayout = layoutElement.getData(KEdgeLayout.class);
                    layoutTransitions.add(new EdgeLayoutTransition((IGraphEdge) graphObject,
                            edgeLayout));
                }
            }
        }
        
        // chsch:
        //  This extension relocates edges that are excluded from automatic layout.
        //  It allows to visualize "symbolic internal connections" between flexible ports.
        //  For this purpose additional layout transitions are added consisting of the
        //  particular edges and "fake" edge layout data. 
        for (IGraphEdge edge : mapping.getProperty(PiccoloDiagramLayoutManager.NO_LAYOUT_EDGES)) {
            KGraphElement srcKPort = mapping.getGraphMap().inverse().get(edge.getSourcePort());
            KGraphElement destKPort = mapping.getGraphMap().inverse().get(edge.getTargetPort());
            KShapeLayout srcPortL = srcKPort.getData(KShapeLayout.class);
            KShapeLayout destPortL = destKPort.getData(KShapeLayout.class);
            KGraphElement srcNode = (KGraphElement) srcKPort.eContainer();
            KGraphElement destNode = (KGraphElement) destKPort.eContainer();
            KShapeLayout srcNodeL = srcNode.getData(KShapeLayout.class);
            KShapeLayout destNodeL = destNode.getData(KShapeLayout.class);
            
            KEdgeLayout edgeLayout = KimlUtil.createInitializedEdge().getData(KEdgeLayout.class);
            edgeLayout.getSourcePoint().setPos(
                    srcNodeL.getXpos() + srcPortL.getXpos() + srcPortL.getWidth() / 2,
                    srcNodeL.getYpos() + srcPortL.getYpos() + srcPortL.getHeight() / 2);
            edgeLayout.getTargetPoint().setPos(
                    destNodeL.getXpos() + destPortL.getXpos() + destPortL.getWidth() / 2,
                    destNodeL.getYpos() + destPortL.getYpos() + destPortL.getHeight() / 2);
            edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
            
            layoutTransitions.add(new EdgeLayoutTransition(edge, edgeLayout));
        }
        
        // edges can depend on the position of nodes and ports so handle positionals first
        Collections.sort(layoutTransitions, new Comparator<ILayoutTransition>() {
            public int compare(final ILayoutTransition lt1, final ILayoutTransition lt2) {
                if (lt1 instanceof PositionalLayoutTransition) {
                    if (lt2 instanceof EdgeLayoutTransition) {
                        return -1;
                    } else {
                        return 0;
                    }                    
                } else {
                    if (lt2 instanceof EdgeLayoutTransition) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        });
        // TODO add transitions for labels (or is the transitional code sufficient)?
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelativeTargetValue(final float zeroToOne) {
        super.setRelativeTargetValue(zeroToOne);
        // set relative target value for all layout transitions
        for (ILayoutTransition layoutTransition : layoutTransitions) {
            layoutTransition.setRelativeTargetValue(zeroToOne);
        }
    }

    /**
     * Instantly applies the effect of this activity that is applying the layout.
     */
    public void apply() {
        setRelativeTargetValue(1.0f);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isAnimation() {
        return true;
    }

    /**
     * The interface for layout transitions.
     */
    private interface ILayoutTransition {

        /**
         * Sets the relative target value.
         * 
         * @param zeroToOne
         *            the relative completion of the transition
         */
        void setRelativeTargetValue(final float zeroToOne);

    }

    /**
     * The layout transition for positionals.
     */
    private class PositionalLayoutTransition implements ILayoutTransition {

        /** the positional object. */
        private IGraphPositional positional;
        /** the source bounds. */
        private PBounds source;
        /** the delta bounds. */
        private PBounds delta;
        /** temporary bounds. */
        private PBounds tempBounds = new PBounds();

        /**
         * Constructs a positional layout transition.
         * 
         * @param positional
         *            the positional
         * @param shapeLayout
         *            the shape layout to be applied
         */
        public PositionalLayoutTransition(final IGraphPositional positional,
                final KShapeLayout shapeLayout) {
            this.positional = positional;
            this.source = (PBounds) positional.getRelativeBounds().clone();
            this.delta = new PBounds();
            this.delta.setRect(shapeLayout.getXpos() - this.source.x, shapeLayout.getYpos()
                    - this.source.y, shapeLayout.getWidth() - this.source.width,
                    shapeLayout.getHeight() - this.source.height);
        }

        /**
         * {@inheritDoc}
         */
        public void setRelativeTargetValue(final float zeroToOne) {
            tempBounds.setRect(source.x + zeroToOne * delta.x, source.y + zeroToOne * delta.y,
                    source.width + zeroToOne * delta.width, source.height + zeroToOne
                            * delta.height);
            positional.setRelativeBounds(tempBounds);
        }

    }

    /**
     * The layout transition for edges.
     */
    private class EdgeLayoutTransition implements ILayoutTransition {

        /** the edge. */
        private IGraphEdge edge;
        /** the soure bends. */
        private Point2D[] sourceBends;
        /** the delta bends. */
        private Point2D[] deltaBends;
        /** the target bends. */
        private List<Point2D> targetBends;
        /** the list of temporary bends. */
        private List<Point2D> tempBends;

        /**
         * Constructs an edge layout transition.
         * 
         * @param edge
         *            the edge
         * @param edgeLayout
         *            the edge layout to be applied
         */
        public EdgeLayoutTransition(final IGraphEdge edge, final KEdgeLayout edgeLayout) {
            this.edge = edge;
            // prepare bend point transition
            prepareBendTransition(edgeLayout);
        }

        /**
         * Creates the required proxy bend points and buffers for performing the bend point
         * transition.
         * 
         * @param edgeLayout
         *            the edge layout to be applied
         */
        private void prepareBendTransition(final KEdgeLayout edgeLayout) {
            List<Point2D> sourceBendsTemp = edge.getBends();
            // determine the target bends
            KPoint layoutSourceBend = edgeLayout.getSourcePoint();
            KPoint layoutTargetBend = edgeLayout.getTargetPoint();
            List<KPoint> layoutBends = edgeLayout.getBendPoints();
            targetBends = new ArrayList<Point2D>(layoutBends.size() + 2);
            targetBends.add(new Point2D.Double(layoutSourceBend.getX(), layoutSourceBend.getY()));
            for (KPoint point : layoutBends) {
                targetBends.add(new Point2D.Double(point.getX(), point.getY()));
            }
            targetBends.add(new Point2D.Double(layoutTargetBend.getX(), layoutTargetBend.getY()));
            // for a smooth transition of bends the maximum number of bends in the source and target
            // layout are required
            int sourceNumber = sourceBendsTemp.size();
            int targetNumber = targetBends.size();
            int maxNumber = Math.max(sourceNumber, targetNumber);
            sourceBends = new Point2D[maxNumber];
            deltaBends = new Point2D[maxNumber];
            // create proxy bend points if required
            if (sourceNumber == targetNumber) {
                // no proxies required
                for (int i = 0; i < maxNumber; ++i) {
                    Point2D sourceBend = (Point2D) sourceBendsTemp.get(i).clone();
                    Point2D targetBend = targetBends.get(i);
                    sourceBends[i] = sourceBend;
                    deltaBends[i] = new Point2D.Double(targetBend.getX() - sourceBend.getX(),
                            targetBend.getY() - sourceBend.getY());
                }
            } else if (sourceNumber < targetNumber) {
                // less source bend points
                createBendsSourceProxy(sourceBendsTemp);
            } else {
                createBendsTargetProxy(sourceBendsTemp);
            }
            // prepare the bend point buffer
            tempBends = new ArrayList<Point2D>(maxNumber);
            for (int i = 0; i < maxNumber; ++i) {
                tempBends.add(new Point2D.Double());
            }
        }

        /**
         * Creates the bend points for the transition and adds a required amount of source proxy
         * bend points.
         * 
         * @param sourceBendsTemp
         *            the source bend points
         */
        private void createBendsSourceProxy(final List<Point2D> sourceBendsTemp) {
            // create proxies for the source bend points
            float[] sourceRels = createIndexRelativePositionMapping(sourceBendsTemp);
            float[] targetRels = createIndexRelativePositionMapping(targetBends);
            int k = 0;
            for (int i = 0; i < targetRels.length; ++i) {
                float targetRel = targetRels[i];
                // only inserts proxy bend points as long as it is still possible to use all
                // existing ones (i.e. don't insert proxy bends instead of real ones)
                if (sourceRels[k] > targetRel && targetRels.length - i > sourceRels.length - k) {
                    sourceBends[i] = getPointOnPolyline(sourceBendsTemp, targetRel);
                } else {
                    sourceBends[i] = (Point2D) sourceBendsTemp.get(k).clone();
                    ++k;
                }
            }
            // calculate the bend point delta
            for (int i = 0; i < targetRels.length; ++i) {
                Point2D sourceBend = sourceBends[i];
                Point2D targetBend = targetBends.get(i);
                deltaBends[i] = new Point2D.Double(targetBend.getX() - sourceBend.getX(),
                        targetBend.getY() - sourceBend.getY());
            }
        }

        /**
         * Creates the bend points for the transition and adds a required amount of target proxy
         * bend points.
         * 
         * @param sourceBendsTemp
         *            the source bend points
         */
        private void createBendsTargetProxy(final List<Point2D> sourceBendsTemp) {
            // copy the source bend points into the array
            for (int i = 0; i < sourceBendsTemp.size(); ++i) {
                sourceBends[i] = (Point2D) sourceBendsTemp.get(i);
            }
            // create proxies for the target bend points
            float[] sourceRels = createIndexRelativePositionMapping(sourceBendsTemp);
            float[] targetRels = createIndexRelativePositionMapping(targetBends);
            Point2D[] targetBendsTemp = new Point2D[sourceRels.length];
            int k = 0;
            for (int i = 0; i < sourceRels.length; ++i) {
                float sourceRel = sourceRels[i];
                if (targetRels[k] > sourceRel && sourceRels.length - i > targetRels.length - k) {
                    targetBendsTemp[i] = getPointOnPolyline(targetBends, sourceRel);
                } else {
                    targetBendsTemp[i] = (Point2D) targetBends.get(k).clone();
                    ++k;
                }
            }
            // calculate the bend point delta
            for (int i = 0; i < sourceRels.length; ++i) {
                Point2D sourceBend = sourceBends[i];
                Point2D targetBend = targetBendsTemp[i];
                deltaBends[i] = new Point2D.Double(targetBend.getX() - sourceBend.getX(),
                        targetBend.getY() - sourceBend.getY());
            }
        }

        /**
         * Interprets the list of bend points as a polyline and returns a mapping of bend point
         * indices to the relative position of the bend point on the line.
         * 
         * @param bends
         *            the bend points
         * @return the mapping
         */
        private float[] createIndexRelativePositionMapping(final List<Point2D> bends) {
            float[] mapping = new float[bends.size()];
            float length = getPolylineLength(bends);
            if (length > 0) {
                Point2D lastBend = bends.get(0);
                mapping[0] = 0.0f;
                float currentLength = 0.0f;
                for (int i = 1; i < bends.size(); ++i) {
                    Point2D currentBend = bends.get(i);
                    currentLength += lastBend.distance(currentBend);
                    mapping[i] = currentLength / length;
                    lastBend = currentBend;
                }
            } else {
                for (int i = 0; i < bends.size() - 1; ++i) {
                    mapping[i] = 0.0f;
                    mapping[bends.size() - 1] = 1.0f;
                }
            }
            return mapping;
        }

        /**
         * Returns a point on the polyline specified by the given bend points by a relative
         * position.
         * 
         * @param bends
         *            the bend points
         * @param position
         *            the relative position
         * @return the point
         */
        private Point2D getPointOnPolyline(final List<Point2D> bends, final float position) {
            // find the segment and point for the location
            float searchDistance = getPolylineLength(bends) * position;
            Point2D lastBend = bends.get(0);
            float currentDistance = 0;
            for (int i = 1; i < bends.size(); ++i) {
                Point2D currentBend = bends.get(i);
                float d = (float) lastBend.distance(currentBend);
                if (d <= 0) {
                    continue;
                }
                if (currentDistance + d >= searchDistance) {
                    // compute the actual point on the polyline
                    float rD = searchDistance - currentDistance;
                    Point2D point = new Point2D.Double();
                    point.setLocation(lastBend.getX() + rD * (currentBend.getX() - lastBend.getX())
                            / d, lastBend.getY() + rD * (currentBend.getY() - lastBend.getY()) / d);
                    return point;
                } else {
                    currentDistance += d;
                }
                lastBend = currentBend;
            }
            return (Point2D) bends.get(0).clone();
        }

        /**
         * Returns the length of the polyline specified by the given bend points.
         * 
         * @param bends
         *            the bend points
         * @return the length of the polyline
         */
        private float getPolylineLength(final List<Point2D> bends) {
            Point2D lastBend = bends.get(0);
            float currentLength = 0.0f;
            for (int i = 1; i < bends.size(); ++i) {
                Point2D currentBend = bends.get(i);
                currentLength += lastBend.distance(currentBend);
                lastBend = currentBend;
            }
            return currentLength;
        }

        /**
         * {@inheritDoc}
         */
        public void setRelativeTargetValue(final float zeroToOne) {
            if (zeroToOne == 1.0f) {
                // when the activity completes set the target bend points
                edge.setBends(targetBends);
            } else {
                // as long as the activity is not completed use proxy bend points
                for (int i = 0; i < sourceBends.length; ++i) {
                    Point2D sourceBend = sourceBends[i];
                    Point2D deltaBend = deltaBends[i];
                    tempBends.get(i).setLocation(sourceBend.getX() + zeroToOne * deltaBend.getX(),
                            sourceBend.getY() + zeroToOne * deltaBend.getY());
                }
                edge.setBends(tempBends);
            }
        }

    }

}
