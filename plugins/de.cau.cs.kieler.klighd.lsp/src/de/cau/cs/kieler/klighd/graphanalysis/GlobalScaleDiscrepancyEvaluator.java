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
public class GlobalScaleDiscrepancyEvaluator implements IKGraphLayoutEvaluator<ScaleDiscrepancy, Double> {

    private List<List<NodeScalePair>> topologyMap;
    private List<ScaleDiscrepancy> results;
    
    public GlobalScaleDiscrepancyEvaluator(KNode graph, double scaleLimit) {
        this.topologyMap = new ArrayList<>();
        constructTopologyMap(graph, 1.0, 0);
        
        results = new ArrayList<>();
        
        // for each topology level, construct pairs of scale discrepancies
        // TODO: this pairs all nodes on one level, it might also make sense to only do this for siblings as another measure
        for (List<NodeScalePair> nodes : topologyMap) {
            for (NodeScalePair nodeA : nodes) {
                for (NodeScalePair nodeB : nodes) {
                    if (nodeA != nodeB) {
                        results.add(new ScaleDiscrepancy(nodeA.getNode(), nodeA.getScale(), 
                                nodeB.getNode(), nodeB.getScale(), scaleLimit));
                    }
                }
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ScaleDiscrepancy> getResults() {
        
        return results;
    }
    
    private void constructTopologyMap(KNode node, double scale, int hierarchyLevel) {
        double topdownScaleFactor = node.getProperty(CoreOptions.TOPDOWN_SCALE_FACTOR);
        if (this.topologyMap.size() <= hierarchyLevel) {
            // create new entry for this hierarchy level
            this.topologyMap.add(new ArrayList<>());
        }
        this.topologyMap.get(hierarchyLevel).add(new NodeScalePair(node, scale * topdownScaleFactor));
        
        
        for (KNode child : node.getChildren()) {
            constructTopologyMap(child, scale * topdownScaleFactor, hierarchyLevel+1);
        }
    }
    
    private class NodeScalePair {
        
        private KNode node;
        private double scale;
        
        public NodeScalePair(KNode node, double scale) {
            this.node = node;
            this.scale = scale;
        }
        
        public KNode getNode() {
            return this.node;
        }
        
        public double getScale() {
            return this.scale;
        }
        
    }

}
