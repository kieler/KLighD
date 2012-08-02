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
package de.cau.cs.kieler.klighd.piccolo.krendering;

import java.awt.geom.Point2D;

import de.cau.cs.kieler.klighd.piccolo.util.MathUtil;
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;

/**
 * The Piccolo activity for applying bend points to a {@KEdgeNode}.
 * 
 * @author mri
 */
public class ApplyBendPointsActivity extends PInterpolatingActivity {
    
    /** the edge node for this activity.  */
    private KEdgeNode edgeNode;
    
    /** the soure bends. */
    private Point2D[] sourceBends;
    /** the target bends. */
    private Point2D[] targetBends;
    /** the delta bends. */
    private Point2D[] deltaBends;
    /** the temporary bends. */
    private Point2D[] tempBends;
    
    /**
     * Constructs an activity to apply new bend points to an edge node over a specified duration.
     * 
     * @param edgeNode
     *            the edge node
     * @param newBends
     *            the new bend points
     * @param duration
     *            the duration
     */
    public ApplyBendPointsActivity(final KEdgeNode edgeNode, final Point2D[] newBends,
            final long duration) {
        super(duration);
        this.edgeNode = edgeNode;
        this.targetBends = newBends;
    }
    
    /**
     * {@inheritDoc}
     */
    protected void activityStarted() {
        if (getFirstLoop()) {
            prepareBendTransition();
        }
        super.activityStarted();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelativeTargetValue(final float zeroToOne) {
        super.setRelativeTargetValue(zeroToOne);
        if (zeroToOne == 1.0f) {
            // when the activity completes set the target bend points
            edgeNode.setBendPoints(targetBends);
        } else {
            // as long as the activity is not completed use proxy bend points
            for (int i = 0; i < sourceBends.length; ++i) {
                Point2D sourceBend = sourceBends[i];
                Point2D deltaBend = deltaBends[i];
                tempBends[i].setLocation(sourceBend.getX() + zeroToOne * deltaBend.getX(),
                        sourceBend.getY() + zeroToOne * deltaBend.getY());
            }
            edgeNode.setBendPoints(tempBends);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isAnimation() {
        return true;
    }

    /**
     * Creates the required proxy bend points and buffers for performing the bend point
     * transition.
     * 
     * @param edgeLayout
     *            the edge layout to be applied
     */
    private void prepareBendTransition() {
        // for a smooth transition of bends the maximum number of bends in the source and target
        // layout are required
        Point2D[] originSourceBends = edgeNode.getBendPoints();
        int sourceNumber = originSourceBends.length;
        int targetNumber = targetBends.length;
        int maxNumber = Math.max(sourceNumber, targetNumber);
        sourceBends = new Point2D[maxNumber];
        deltaBends = new Point2D[maxNumber];
        
        // create proxy bend points if required
        if (sourceNumber == targetNumber) {
            // no proxies required
            for (int i = 0; i < maxNumber; ++i) {
                Point2D sourceBend = (Point2D) originSourceBends[i].clone();
                Point2D targetBend = targetBends[i];
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
     * Creates the bend points for the transition and adds a required amount of source proxy
     * bend points.
     * 
     * @param sourceBendsTemp
     *            the source bend points
     */
    private void createBendsSourceProxy(final Point2D[] sourceBendsTemp) {
        // create proxies for the source bend points
        double[] sourceRels = createIndexRelativePositionMapping(sourceBendsTemp);
        double[] targetRels = createIndexRelativePositionMapping(targetBends);
        int k = 0;
        for (int i = 0; i < targetRels.length; ++i) {
            double targetRel = targetRels[i];
            // only inserts proxy bend points as long as it is still possible to use all
            // existing ones (i.e. don't insert proxy bends instead of real ones)

            // chsch: original code, produced an ArrayIndexOutOfBound exception...
            // if (sourceRels[k] > targetRel && targetRels.length - i > sourceRels.length - k) {
            //     sourceBends[i] = MathUtil.getPoint(sourceBendsTemp, targetRel);
            // } else {
            //     sourceBends[i] = (Point2D) sourceBendsTemp[k].clone();
            //     ++k;
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
            Point2D sourceBend = sourceBends[i];
            Point2D targetBend = targetBends[i];
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
    private void createBendsTargetProxy(final Point2D[] sourceBendsTemp) {
        // copy the source bend points into the array
        for (int i = 0; i < sourceBendsTemp.length; ++i) {
            sourceBends[i] = (Point2D) sourceBendsTemp[i];
        }
        
        // create proxies for the target bend points
        double[] sourceRels = createIndexRelativePositionMapping(sourceBendsTemp);
        double[] targetRels = createIndexRelativePositionMapping(targetBends);
        Point2D[] targetBendsTemp = new Point2D[sourceRels.length];
        int k = 0;
        for (int i = 0; i < sourceRels.length; ++i) {
            double sourceRel = sourceRels[i];
            
            // chsch: the following code maybe erroneous, see foregoing method
            if (targetRels[k] > sourceRel && sourceRels.length - i > targetRels.length - k) {
                targetBendsTemp[i] = MathUtil.getPoint(targetBends, sourceRel);
            } else {
                targetBendsTemp[i] = (Point2D) targetBends[k].clone();
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
    private double[] createIndexRelativePositionMapping(final Point2D[] bends) {
        double[] mapping = new double[bends.length];
        double length = MathUtil.getLength(bends);
        if (length > 0) {
            Point2D lastBend = bends[0];
            mapping[0] = 0.0f;
            double currentLength = 0.0f;
            for (int i = 1; i < bends.length; ++i) {
                Point2D currentBend = bends[i];
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
