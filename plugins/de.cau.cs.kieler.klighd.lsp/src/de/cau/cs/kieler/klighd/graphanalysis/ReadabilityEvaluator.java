/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.graphanalysis;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.core.options.CoreOptions;

import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.krendering.KContainerRendering;
import de.cau.cs.kieler.klighd.krendering.KText;

/**
 * @author mka
 *
 */
public class ReadabilityEvaluator implements IKGraphLayoutEvaluator<Readability, Double> {

    private double scaleLimit;
    
    public ReadabilityEvaluator(double scaleLimit) {
        this.scaleLimit = scaleLimit;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Readability> evaluate(KNode graph) {
        ArrayList<Readability> result= new ArrayList<>();
        
        // Traverse graph computing scales along the way and creating Readability objects for all KTexts
        traverseGraph(graph, 1, result);
        
        return result;
    }
    
    private void traverseGraph(KNode node, double scale, List<Readability> resultList) {
        double topdownScaleFactor = node.getProperty(CoreOptions.TOPDOWN_SCALE_FACTOR);
        for (KGraphData datum : node.getData()) {
            if (datum instanceof KText) {
                Readability readability = new Readability((KText) datum, scale, scaleLimit);
                // System.out.println(readability.plot(0.01));
                resultList.add(readability);
            }
            if (datum instanceof KContainerRendering) {
                // this should not be scaled I think use scale instead
                traverseKGraphData((KContainerRendering) datum, scale, resultList);
            }
        }
        
        for (KNode child : node.getChildren()) {
            traverseGraph(child, scale * topdownScaleFactor, resultList);
        }
    }
    
    private void traverseKGraphData(KContainerRendering container, double scale, List<Readability> resultList) {
        for (KGraphData datum : container.getChildren()) {
            if (datum instanceof KText) {
                Readability readability = new Readability((KText) datum, scale, scaleLimit);
                // System.out.println(readability.plot(0.01));
                resultList.add(readability);
            }
            if (datum instanceof KContainerRendering) {
                traverseKGraphData((KContainerRendering) datum, scale, resultList);
            }
        }
    }

}
