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

import org.eclipse.elk.core.options.CoreOptions;

import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * @author mka
 *
 */
public class LocalScaleDiscrepancyEvaluator implements IKGraphLayoutEvaluator<ScaleDiscrepancy, Double> {

    private List<ScaleDiscrepancy> results;
    private double scaleLimit;
    
    public LocalScaleDiscrepancyEvaluator(KNode graph, double scaleLimit) {
        this.scaleLimit = scaleLimit;
        this.results = new ArrayList<>();
        traverseGraph(graph, 1.0);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ScaleDiscrepancy> getResults() {
        return this.results;
    }
    
    private void traverseGraph(KNode node, double scale) {
        double localScale = scale * node.getProperty(CoreOptions.TOPDOWN_SCALE_FACTOR);

        // pair up all children
        for (KNode childA : node.getChildren()) {
            for (KNode childB : node.getChildren()) {
                if (childA != childB) {
                    double scaleA = childA.getProperty(CoreOptions.TOPDOWN_SCALE_FACTOR);
                    double scaleB = childB.getProperty(CoreOptions.TOPDOWN_SCALE_FACTOR);
                    results.add(new ScaleDiscrepancy(childA, localScale * scaleA, childB, localScale * scaleB, scaleLimit));
                }
            }
        }
        
        for (KNode child : node.getChildren()) {
            traverseGraph(child, localScale);
        }
    }

}
