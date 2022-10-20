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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.interactive

import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import java.util.HashMap
import java.util.List

/**
 * Service interface for implementions that serialize a set constraint in the model.
 * E.g. for ElkGraphs a property is added and the graph serialized,
 * for SCCharts an Annotation with a layout constraint is added and the graph serialized.
 * 
 * @author sdo
 *
 */
interface IConstraintSerializer {
    /**
     * Checks whether this serializer can handle a graph type
     * 
     * @param graph The graph to serialize
     * @return true if the graph can be serialized
     */
    def boolean canHandle(Object graph);
    
    /**
     * @param 
     */
    def void serializeConstraints(List<ConstraintProperty<Object>> changedNodes, Object graph, String uri,
        KGraphLanguageServerExtension ls, KGraphLanguageClient client);
}