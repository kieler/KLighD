/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.test;

import java.awt.geom.Point2D;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.ApplyBendPointsActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;

/**
 * Tests the {@link ApplyBendPointsActivity} wrt. change, addition, and removal of edge bend points.
 * 
 * @author chsch
 */
public class ApplyBendPointsActivityTest {
    
    /**
     * Preparation of a realistic {@link KEdgeNode} related to a {@link KEdge} with proper source
     * and target {@link KNode KNodes} being contained in a root {@link KNode}.
     * 
     * @param currentBends the assumed given bend points of the edge
     * 
     * @return a {@link KEdgeNode} being required by the {@link ApplyBendPointsActivity}
     */
    private KEdgeNode prepareEdge(final Point2D[] currentBends) {
        KNode node0 = KGraphUtil.createInitializedNode(); 
        KNode node1 = KGraphUtil.createInitializedNode(); 
        KNode node2 = KGraphUtil.createInitializedNode();
        node0.getChildren().addAll(Lists.newArrayList(node1, node2));
        
        KEdge edge = KGraphUtil.createInitializedEdge();
        edge.setSource(node1);
        edge.setTarget(node2);
        
        final KEdgeNode edgeNode = new KEdgeNode(edge);
        edgeNode.setBendPoints(currentBends);
        return edgeNode;
    }
    
    
    private static final String newline = System.getProperty("line.separator");
    
    private String prepareMessage(final Point2D[] expectedBends, final Point2D[] currentBends) {
        return "Resulting bend points differ from required 'newBends'!" + newline
                + "Expected bend points: " + Arrays.toString(expectedBends) + newline
                + "Computed bend points: " + Arrays.toString(currentBends);
    }
    
    
    private void applyActivitiy(final Point2D[] currentBends, final Point2D[] newBends, final boolean animated) {

        final KEdgeNode edgeNode = prepareEdge(currentBends);
        final ApplyBendPointsActivity activity = new ApplyBendPointsActivity(edgeNode, newBends, new Point2D[0], 2);
        
        activity.activityStarted();
        
        if (animated) {
            activity.setRelativeTargetValue(0f);
            activity.setRelativeTargetValue(1f/10f);
            activity.setRelativeTargetValue(1f/3f);
            activity.setRelativeTargetValue(1f/2f);
            activity.setRelativeTargetValue(2f/3f);
            activity.setRelativeTargetValue(3f/4f);
            activity.setRelativeTargetValue(1f);
        }
        
        activity.activityFinished();
        
        Assert.assertTrue(
                prepareMessage(newBends, edgeNode.getBendPoints()),
                Arrays.equals(newBends, edgeNode.getBendPoints()));
    }
    
    @Test
    public void moveBendsTest01() {
        
        final Point2D[] currentBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0) };
        final Point2D[] newBends = new Point2D[] { new Point2D.Float(20, -20), new Point2D.Float(-3, 10) };

        applyActivitiy(currentBends, newBends, false);
    }
    
    @Test
    public void moveBendsTest01animated() {
        
        final Point2D[] currentBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0) };
        final Point2D[] newBends = new Point2D[] { new Point2D.Float(20, -20), new Point2D.Float(-3, 10) };

        applyActivitiy(currentBends, newBends, true);
    }
    
    @Test
    public void addBendsTest01() {
        
        final Point2D[] currentBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0) };
        final Point2D[] newBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0), new Point2D.Float(100, 100) };

        applyActivitiy(currentBends, newBends, false);
    }
    
    @Test
    public void addBendsTest01animated() {
        
        final Point2D[] currentBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0) };
        final Point2D[] newBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0), new Point2D.Float(100, 100) };

        applyActivitiy(currentBends, newBends, true);
    }
    
    @Test
    public void addBendsTest02() {
        
        final Point2D[] currentBends = new Point2D[] { };
        final Point2D[] newBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0), new Point2D.Float(100, 100) };

        applyActivitiy(currentBends, newBends, false);
    }
    
    @Test
    public void addBendsTest02animated() {
        
        final Point2D[] currentBends = new Point2D[] { };
        final Point2D[] newBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0), new Point2D.Float(100, 100) };

        applyActivitiy(currentBends, newBends, true);
    }
    
    @Test
    public void removeBendsTest01() {
        
        final Point2D[] currentBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0), new Point2D.Float(100, 100) };
        final Point2D[] newBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0) };

        applyActivitiy(currentBends, newBends, false);

    }
    
    @Test
    public void removeBendsTest01animated() {
        
        final Point2D[] currentBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0), new Point2D.Float(100, 100) };
        final Point2D[] newBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0) };

        applyActivitiy(currentBends, newBends, true);

    }
    @Test
    public void removeBendsTest02() {
        
        final Point2D[] currentBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0), new Point2D.Float(100, 100) };
        final Point2D[] newBends = new Point2D[] { };

        applyActivitiy(currentBends, newBends, false);

    }
    
    @Test
    public void removeBendsTest02animated() {
        
        final Point2D[] currentBends = new Point2D[] { new Point2D.Float(0, 0), new Point2D.Float(100, 0), new Point2D.Float(100, 100) };
        final Point2D[] newBends = new Point2D[] { };

        applyActivitiy(currentBends, newBends, true);

    }
}
