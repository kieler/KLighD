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
public class MaxAggregator<T extends Comparable> implements IZLevelAggregator<IZSampleable<T>, T, T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public T aggregate(List<IZSampleable<T>> sampleables, double z) {
        T max = null;
        for (IZSampleable<T> sampleable : sampleables) {
            if (max == null) {
                max = sampleable.getZSample(z);
            } else {
                T next = sampleable.getZSample(z);
                if (max.compareTo(next) < 0) {
                    max = next;
                }
            }
        }
        return max;
    }

}
