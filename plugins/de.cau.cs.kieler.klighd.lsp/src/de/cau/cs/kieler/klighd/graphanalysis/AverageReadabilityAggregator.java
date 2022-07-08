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
public class AverageReadabilityAggregator implements IZLevelAggregator<Readability, Double, Double> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Double aggregate(List<Readability> sampleables, double z) {
        double result = 0;
        for (Readability readability : sampleables) {
            result += readability.getZSample(z);
        }
        return result / sampleables.size();
    }

}
