/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.graphanalysis;

import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * @author mka
 *
 */
public class ScaleDiscrepancy implements IZSampleable<Double> {

    private KNode nodeA;
    private KNode nodeB;
    private double nodeScaleA;
    private double nodeScaleB;
    private double scaleLimit;
    
    public ScaleDiscrepancy(KNode nodeA, double nodeScaleA, KNode nodeB, double nodeScaleB, double scaleLimit) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.nodeScaleA = nodeScaleA;
        this.nodeScaleB = nodeScaleB;
        this.scaleLimit = scaleLimit;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Double getZSample(double z) {
        // check wether z is between both scales
        double zA = scaleToZlevel(nodeScaleA);
        double zB = scaleToZlevel(nodeScaleB);
        if (zA >= z && zB <= z || zB >= z && zA <= z) {
            return getScaleDiscrepancy();
        }
        // if z is not between both nodes, disregard the discrepancy for this sample
        return 0.0;
    }
    
    public double getScaleDiscrepancy() {
        // might want to change this to a non-linear function to give higher discrepancies a larger weight
        // this calculation relies on both scales being in the range [0,1] so that the resulting discrepancy
        // is also in the range [0,1], this assumption is ok for what I'm examining, but doesn't necessarily 
        // have to hold in the general case
        
        // adjust this to modify how quickly the discrepancy rises
        // 1 > w > 0: discrepancy rises very quickly then slows down, we don't want that
        // w > 1: discrepancy rises slowly and then fast i.e. small discrepancies are not so bad, and large discrepancies are very bad
        // w = 1: linear
        double weightingFactor = 3;
        
        // use z level of the nodes to get comparable discrepancies that are bounded to [0,1]
        double zA = scaleToZlevel(nodeScaleA);
        double zB = scaleToZlevel(nodeScaleB);
        
        double D = Math.abs(zA - zB);
        return Math.pow(D, weightingFactor);
    }
    
    private double scaleToZlevel(double scale) {
        // for a > 1, s = z - az + a
        // for a <= 1, s = -z + az + 1
        if (scaleLimit > 1) {
            return (1/scale - scaleLimit) / (1 - scaleLimit); // invert scale of text for this equation to hold
        } else {
            return (scale - 1) / (scaleLimit - 1); // this case should only happen for topdown layout, where scale should always be 1 and therefore z should always be 0
        }
    }
    
    public String toString() {
        return "(" + nodeScaleA + " (" + scaleToZlevel(nodeScaleA) + "), " + nodeScaleB + "(" +  scaleToZlevel(nodeScaleB) + "), " + getScaleDiscrepancy() +", " + scaleLimit + ")";
    }

}
