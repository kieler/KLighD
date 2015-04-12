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
package de.cau.cs.kieler.klighd.piccolo.internal.activities;

import java.awt.geom.Point2D;

import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.MathUtil;
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;

/**
 * The Piccolo activity for applying bend points to a {@KEdgeNode}.
 *
 * @author mri, chsch
 */
public class ApplyBendPointsActivity extends PInterpolatingActivity implements
        IStartingAndFinishingActivity {

    /** the edge node for this activity. */
    private final KEdgeNode edgeNode;

    /** the source bends. */
    private Point2D[] sourceBends;
    /** the target bends. */
    private Point2D[] targetBends;
    /** the delta bends. */
    private Point2D[] deltaBends;
    /** the temporary bends. */
    private Point2D[] tempBends;

    /** the source junctions. */
    private Point2D[] sourceJunctions;
    /** the target junctions. */
    private Point2D[] targetJunctions;
    /** the delta bends. */
    private Point2D[] deltaJunctions;
    /** the temporary bends. */
    private Point2D[] tempJunctions;

    /** a local memory indicating whether a style update took place already. */
    private boolean stylesModified = false;

    /**
     * Constructs an activity to apply new bend points to an edge node over a specified duration.
     *
     * @param edgeNode
     *            the edge node
     * @param newBends
     *            the new bend points
     * @param newJunctions
     *            the new junction points
     * @param duration
     *            the duration
     */
    public ApplyBendPointsActivity(final KEdgeNode edgeNode, final Point2D[] newBends,
            final Point2D[] newJunctions, final long duration) {
        super(duration);
        this.edgeNode = edgeNode;
        this.targetBends = newBends;
        this.targetJunctions = newJunctions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activityStarted() {
        prepareBendTransition();
        prepareJunctionTransition();

        edgeNode.setVisible(true);
        super.activityStarted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelativeTargetValue(final float zeroToOne) {
        if (zeroToOne == 1.0f) {
            // when the activity completes set the target bend points
            edgeNode.setBendPoints(targetBends);
            edgeNode.setJunctionPoints(targetJunctions);
        } else {
            // as long as the activity is not completed use proxy bend points
            for (int i = 0; i < sourceBends.length; ++i) {
                final Point2D sourceBend = sourceBends[i];
                final Point2D deltaBend = deltaBends[i];
                tempBends[i].setLocation(sourceBend.getX() + zeroToOne * deltaBend.getX(),
                        sourceBend.getY() + zeroToOne * deltaBend.getY());
            }
            edgeNode.setBendPoints(tempBends);


            for (int i = 0; i < targetJunctions.length; ++i) {
                final Point2D sourceJunction = sourceJunctions[i];
                final Point2D deltaJunction = deltaJunctions[i];
                tempJunctions[i].setLocation(sourceJunction.getX() + zeroToOne * deltaJunction.getX(),
                        sourceJunction.getY() + zeroToOne * deltaJunction.getY());
            }
            edgeNode.setJunctionPoints(tempJunctions);
        }
        if (!stylesModified && zeroToOne > 1f / 2f) {
            stylesModified = true;
            if (edgeNode.getRenderingController() != null) {
                edgeNode.getRenderingController().modifyStyles();
            }
        }
        super.setRelativeTargetValue(zeroToOne);
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization puts the desired bend points to the edge.
     */
    @Override
    public void activityFinished() {
        edgeNode.setBendPoints(targetBends);
        edgeNode.setJunctionPoints(targetJunctions);

        if (!stylesModified) {
            stylesModified = true;
            if (edgeNode.getRenderingController() != null) {
                edgeNode.getRenderingController().modifyStyles();
            }
        }
        super.activityFinished();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isAnimation() {
        return true;
    }

    /**
     * Creates the required proxy bend points and buffers for performing the bend point transition.
     */
    protected void prepareBendTransition() {
        // for a smooth transition of bends the maximum number of bends in the source and target
        // layout are required
        final Point2D[] originSourceBends = edgeNode.getBendPoints();
        final int sourceNumber = originSourceBends.length;
        final int targetNumber = targetBends.length;
        final int maxNumber = Math.max(sourceNumber, targetNumber);
        sourceBends = new Point2D[maxNumber];
        deltaBends = new Point2D[maxNumber];

        // create proxy bend points if required
        if (sourceNumber == targetNumber) {
            // no proxies required
            for (int i = 0; i < maxNumber; ++i) {
                final Point2D sourceBend = (Point2D) originSourceBends[i].clone();
                final Point2D targetBend = targetBends[i];
                sourceBends[i] = sourceBend;
                deltaBends[i] = new Point2D.Double(targetBend.getX() - sourceBend.getX(),
                        targetBend.getY() - sourceBend.getY());
            }
        } else if (sourceNumber < targetNumber) {
            // less source bend points
            createBendsSourceProxy(originSourceBends);
        } else {
            createBendsTargetProxy(originSourceBends);
        }

        // prepare the bend point buffer
        tempBends = new Point2D[maxNumber];
        for (int i = 0; i < maxNumber; ++i) {
            tempBends[i] = new Point2D.Double();
        }
    }

    /**
     * a.
     */
    protected void prepareJunctionTransition() {
        final Point2D[] originSourceJunctions = edgeNode.getJunctionPoints();
        final int sourceNumber = originSourceJunctions.length;
        final int targetNumber = targetJunctions.length;

        if (sourceNumber == 0 && targetNumber == 0) {
            sourceJunctions = new Point2D[0];
            deltaJunctions = new Point2D[0];
            tempJunctions = new Point2D[0];

        } else {
            sourceJunctions = new Point2D[targetNumber];
            deltaJunctions = new Point2D[targetNumber];
            tempJunctions = new Point2D[targetNumber];

            for (int i = 0; i < targetNumber; ++i) {
                final Point2D sourceJunction;
                final Point2D targetJunction = targetJunctions[i];
                if (i < sourceNumber) {
                    sourceJunction = (Point2D) originSourceJunctions[i].clone();
                } else if (i == 0) {
                    sourceJunction = (Point2D) sourceBends[0].clone();
                } else {
                    sourceJunction = (Point2D) sourceJunctions[i - 1].clone();
                }
                sourceJunctions[i] = sourceJunction;
                deltaJunctions[i] = new Point2D.Double(targetJunction.getX() - sourceJunction.getX(),
                        targetJunction.getY() - sourceJunction.getY());
            }

            // prepare the bend point buffer
            for (int i = 0; i < targetNumber; ++i) {
                tempJunctions[i] = new Point2D.Double();
            }
        }
    }

    /**
     * Creates the bend points for the transition and adds a required amount of source proxy bend
     * points.
     *
     * @param sourceBendsTemp
     *            the source bend points
     */
    private void createBendsSourceProxy(final Point2D[] sourceBendsTemp) {
        // create proxies for the source bend points
        final double[] sourceRels = createIndexRelativePositionMapping(sourceBendsTemp);
        final double[] targetRels = createIndexRelativePositionMapping(targetBends);
        int k = 0;
        for (int i = 0; i < targetRels.length; ++i) {
            final double targetRel = targetRels[i];
            // only inserts proxy bend points as long as it is still possible to use all
            // existing ones (i.e. don't insert proxy bends instead of real ones)

            // chsch: original code, produced an ArrayIndexOutOfBound exception...
            // if (sourceRels[k] > targetRel && targetRels.length - i > sourceRels.length - k) {
            // sourceBends[i] = MathUtil.getPoint(sourceBendsTemp, targetRel);
            // } else {
            // sourceBends[i] = (Point2D) sourceBendsTemp[k].clone();
            // ++k;
            // }

            // chsch: my replacement,
            if (k < sourceRels.length && sourceRels[k] <= targetRel) {
                sourceBends[i] = (Point2D) sourceBendsTemp[k++].clone();
            } else {
                sourceBends[i] = MathUtil.getPoint(sourceBendsTemp, targetRel);
            }
        }

        // calculate the bend point delta
        for (int i = 0; i < targetRels.length; ++i) {
            final Point2D sourceBend = sourceBends[i];
            final Point2D targetBend = targetBends[i];
            deltaBends[i] = new Point2D.Double(targetBend.getX() - sourceBend.getX(),
                    targetBend.getY() - sourceBend.getY());
        }
    }

    /**
     * Creates the bend points for the transition and adds a required amount of target proxy bend
     * points.
     *
     * @param sourceBendsTemp
     *            the source bend points
     */
    private void createBendsTargetProxy(final Point2D[] sourceBendsTemp) {
        // copy the source bend points into the array
        for (int i = 0; i < sourceBendsTemp.length; ++i) {
            sourceBends[i] = sourceBendsTemp[i];
        }

        // create proxies for the target bend points
        final double[] sourceRels = createIndexRelativePositionMapping(sourceBendsTemp);
        final double[] targetRels = createIndexRelativePositionMapping(targetBends);
        final Point2D[] targetBendsTemp = new Point2D[sourceRels.length];
        int k = 0;
        for (int i = 0; i < sourceRels.length; ++i) {
            final double sourceRel = sourceRels[i];

            // chsch: original code, produced an ArrayIndexOutOfBound exception...
            // if (targetRels[k] > sourceRel && sourceRels.length - i > targetRels.length - k) {
            // targetBendsTemp[i] = MathUtil.getPoint(targetBends, sourceRel);
            // } else {
            // targetBendsTemp[i] = (Point2D) targetBends[k].clone();
            // ++k;
            // }

            // chsch: my replacement,
            if (k < targetRels.length && targetRels[k] <= sourceRel) {
                targetBendsTemp[i] = (Point2D) targetBends[k++].clone();
            } else {
                targetBendsTemp[i] = MathUtil.getPoint(targetBends, sourceRel);
            }
        }

        // calculate the bend point delta
        for (int i = 0; i < sourceRels.length; ++i) {
            final Point2D sourceBend = sourceBends[i];
            final Point2D targetBend = targetBendsTemp[i];
            deltaBends[i] = new Point2D.Double(targetBend.getX() - sourceBend.getX(),
                    targetBend.getY() - sourceBend.getY());
        }
    }

    /**
     * Interprets the list of bend points as a polyline and returns a mapping of bend point indices
     * to the relative position of the bend point on the line.
     *
     * @param bends
     *            the bend points
     * @return the mapping
     */
    private double[] createIndexRelativePositionMapping(final Point2D[] bends) {
        final double[] mapping = new double[bends.length];
        final double length = MathUtil.getLength(bends);
        if (length > 0) {
            Point2D lastBend = bends[0];
            mapping[0] = 0.0f;
            double currentLength = 0.0f;
            for (int i = 1; i < bends.length; ++i) {
                final Point2D currentBend = bends[i];
                currentLength += lastBend.distance(currentBend);
                mapping[i] = currentLength / length;
                lastBend = currentBend;
            }
        } else {
            for (int i = 0; i < bends.length - 1; ++i) {
                mapping[i] = 0.0f;
                mapping[bends.length - 1] = 1.0f;
            }
        }
        return mapping;
    }

}
