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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.core.options.CoreOptions;

import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * @author mka
 *
 */
public class KGraphAnalysis {
    
    private KNode graph;
    @SuppressWarnings("rawtypes")
    private List<ZSampler> zSamplers;
    private List<List<?>> results;
    private double sampleStepSize;
    
    
    public KGraphAnalysis(KNode graph) {
        this.graph = graph;
        double scaleLimit = 1;
        
        if (graph.getProperty(CoreOptions.TOPDOWN_LAYOUT)) {
            // determine min_scale, inverse of minScale is scaleLimit for topdown graphs
            scaleLimit = 1.0 / getMinScale(graph, 1);
        } else {
            // use dimensions of graph to determine scaleLimit
            double assumedViewportWidth = 500;
            // for graphs smaller than the viewport use a scale nearly 1
            double zoomOutScale = Math.min(assumedViewportWidth / graph.getWidth(), 0.999999);
            scaleLimit = Math.exp(Math.abs(Math.log(zoomOutScale)));
        }
        System.out.println("scalelimit " + scaleLimit);
        this.sampleStepSize = Math.max((double) 1 / scaleLimit, 0.0001);
        zSamplers = new ArrayList<>();
        // Set up z samplers, need to know this order to know what results mean, an additional field for a string id 
        // somewhere could be useful
        AverageReadabilityAggregator avgReadAgg = new AverageReadabilityAggregator();
        ReadabilityEvaluator readEval = new ReadabilityEvaluator(scaleLimit);
        zSamplers.add(new ZSampler<Readability, Double, Double>(readEval, avgReadAgg, "Average Readability"));
        
        double threshold = 0.8;
        ReadabilityThresholdCountAggregator threshCountAgg = new ReadabilityThresholdCountAggregator(threshold);
        zSamplers.add(new ZSampler<>(readEval, threshCountAgg, "Readability Threshold " + threshold));
        
        // plot readabilities
        // for large graphs with many texts the resulting python script is too large
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/mka/projects/readability-measure/plot_readabilities.py"), "utf-8"))) {
            ReadabilityPyPlotter.plotAllReadabilities(readEval.evaluate(graph), sampleStepSize, writer);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }

    
    public void runAnalysis() {
        
        results = new ArrayList<>();
        for (ZSampler<?,?,?> sampler : zSamplers) {
            results.add(sampler.getSamples(graph, sampleStepSize));
        }
    }
    
    public String formattedOuput() {
        List<String> samplerNames = new ArrayList<>(this.zSamplers.size());
        for (ZSampler sampler : this.zSamplers) {
            samplerNames.add(sampler.getName());
        }
        return samplerNames.toString() + "\n" + results.toString();
    }
    
    private double getMinScale(KNode graph, double scale) {
        List<Double> scales = new ArrayList<>();
        for (KNode child : graph.getChildren()) {
            scales.add(getMinScale(child, scale * graph.getProperty(CoreOptions.TOPDOWN_SCALE_FACTOR)));
        }
        //System.out.println("Scales");
        //System.out.println(scales);
        double minScale = scale;
        for (Double scaleValue : scales) {
            if (scaleValue < minScale) {
                minScale = scaleValue;
            }
        }
        // System.out.println(minScale);
        return minScale;
    }

}
