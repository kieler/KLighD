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

import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * @author mka
 *
 */
public class ZSampler<T extends IZSampleable<U>, S, U> {

    private IKGraphLayoutEvaluator<T, U> evaluator;
    private IZLevelAggregator<T, S, U> aggregator;
    private String name;
    
    private final double Z_MIN = 0;
    private final double Z_MAX = 1;
    
    public ZSampler(IKGraphLayoutEvaluator<T, U> evaluator, IZLevelAggregator<T, S, U> aggregator, String name) {
        this.evaluator = evaluator;
        this.aggregator = aggregator;
        this.name = name;
    }
    
    public List<S> getSamples(KNode graph, double sampleStepSize) {
        if (sampleStepSize >= Z_MAX) {
            throw new IllegalArgumentException("Step size must be less than " + Z_MAX);
        }
        List<T> sampleables = evaluator.getResults();
        List<S> result = new ArrayList<>();
        int sampleCount = (int) (Z_MAX / sampleStepSize);
        for (int i = (int) Z_MIN; i <= sampleCount; i++) {
            double z = (float) i / sampleCount;
            result.add(aggregator.aggregate(sampleables, z));
        }
        return result;
    }
    
    public String getName() {
        return this.name;
    }

}
