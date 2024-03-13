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
package de.cau.cs.kieler.klighd.lsp.interactive

import java.util.List
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.lsp4j.TextEdit

/**
 * Service interface for implementations that serialize a set constraint in the model.
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
     * @param changedNodes The added constraints.
     * @param graph The model, e.g. SCChart or ElkGraph.
     * @param resource The resource to change
     * @return The TextEdit to send to the client consisting of the new text and a range.
     */
    def TextEdit serializeConstraints(List<ConstraintProperty<Object>> changedNodes, Object graph, Resource resource);
}