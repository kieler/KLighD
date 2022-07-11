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

import java.util.List;

/**
 * @author mka
 *
 */
public class ReadabilityThresholdCountAggregator implements IZLevelAggregator<Readability, Integer, Double> {

    
    private double threshold;
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer aggregate(List<Readability> sampleables, double z) {
        int count = 0;
        for (Readability sampleable : sampleables) {
            if (sampleable.getZSample(z) >= this.threshold) {
                count++;
            }
        }
        return count;
    }
    
    public ReadabilityThresholdCountAggregator(double threshold) {
        this.threshold = threshold;
    }

}
