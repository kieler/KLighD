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

import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import java.io.ByteArrayOutputStream
import java.util.ArrayList
import java.util.List
import java.util.ServiceLoader
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.lsp4j.Position
import org.eclipse.lsp4j.Range
import org.eclipse.lsp4j.TextEdit

/**
 * Provides utility methods for interactive layout.
 *
 * @author sdo
 */
class InteractiveUtil {

    /**
     * Calculates the nodes that are in the layer based on the layer ID. The nodes are sorted by their pos id. 
     * @param layer the layer which containing nodes should be calculated
     * @param nodes all nodes the graph contains
     * @return sorted list of nodes in the specified layer
     */
    def static List<KNode> getNodesOfLayer(int layer, List<KNode> nodes) {

        var ArrayList<KNode> sortedNodes = newArrayList()

        // layer <= maxLayerId: Collect all nodes with the fitting layer id in a list
        var nodeCount = 0

        for (n : nodes) {
            val layerID = n.getProperty(LayeredOptions.LAYERING_LAYER_ID)
            if (layerID === layer) {
                sortedNodes.add(n)
                nodeCount++
            }
        }

        // sort them based on their position id - this is used for speeding up future reevaluation
        sortedNodes.sort([ a, b |
            a.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID) -
                b.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
        ])

        return sortedNodes
    }

    /**
     * Copies an arbitrary IProperty of a KNode to an ElkNode if the value on the KNode 
     * is different to the value on the ElkNode.
     * If the new value on the KNode was the default value of the property 
     * then the property is set to null on the ElkNode.
     * @param elkNode The target ElkNode
     * @param kNode The source KNode of the property
     * @param prop Determines which IProperty should be copied
     */
    static def <T> copyConstraintProp(ElkNode elkNode, KNode kNode, IProperty<T> prop) {
        val kNodeValue = kNode.getProperty(prop)

        if (elkNode.getProperty(prop) !== kNodeValue) {
            if (kNodeValue === prop.^default) {
                elkNode.setProperty(prop, null)
            } else {
                elkNode.setProperty(prop, kNodeValue)
            }
        }
    }

    /**
     * Determines the root of the given node.
     * 
     * @param node ElkNode, which root should be returned.
     * @return The root node of a node. If the node itself has no parent the original node is returned.
     */
    static def getRootNodeOf(ElkNode node) {
        var ElkNode parent = node
        while (parent.parent !== null) {
            parent = parent.parent
        }
        return parent
    }
    
    /**
     * Determines the nodes that are connected to {@code node} by relative constraints.
     * The returned list of nodes is sorted based on the position of the nodes.
     * 
     * @param node One node of the chain
     * @param layerNodes Nodes that are in the same layer as {@code node}
     * @return All nodes of the relative constraint chain of the given node present in the given layer nodes.
     */
    static def getChain(KNode node, List<KNode> layerNodes) {
        var pos = layerNodes.indexOf(node)
        var chainNodes = new ArrayList<KNode>();
        chainNodes.add(node)
        
        // from node to the start
        for (var i = pos - 1; i >= 0; i--) {
            if (layerNodes.get(i).getProperty(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF) == getIdOfNode(layerNodes.get(i + 1))
                || layerNodes.get(i + 1).getProperty(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF) == getIdOfNode(layerNodes.get(i))) {
                    chainNodes.add(0, layerNodes.get(i))
            } else {
                i = -1
            }
        }
        
        // count from node to the end
        for (var i = pos + 1; i < layerNodes.size; i++) {
            if (layerNodes.get(i).getProperty(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF) == getIdOfNode(layerNodes.get(i - 1))
                || layerNodes.get(i - 1).getProperty(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF) == getIdOfNode(layerNodes.get(i))) {
                    chainNodes.add(layerNodes.get(i))
            } else {
                i = layerNodes.size
            }
        }
        
        return chainNodes
    }
    
        
    /**
     * Checks whether the nodes of {@code chain1} and the nodes {@code chain2} can be merged to one chain
     * 
     * @param chain1 One of the two chains.
     * @param chain2 Other one of the two chains.
     * @return Returns true if the chains can be merged.
     */
    static def isMergeImpossible(List<KNode> chain1, List<KNode> chain2) {
        var connectedNodes = new ArrayList<KNode>()
        for (n : chain1) {
            var edges = n.outgoingEdges
            for (e : edges) {
                connectedNodes.add(e.target)
            }
            edges = n.incomingEdges
            for (e : edges) {
                connectedNodes.add(e.source)
            }
        }
        
        for (n : connectedNodes) {
            if (chain2.contains(n)) {
                return true
            }
        }
        
        return false
    }
    
    /**
     * Calls serializer for corresponding model type if one was bound.
     * 
     * @param changedNodes List of {@code ConstraintProperty} that represent the changed nodes.
     * @param model The KNode that has to be changed.
     * @param uri The uri of the model file that will be rewritten if a serializer exists.
     */
    static def serializeConstraints(List<ConstraintProperty<Object>> changedNodes, KNode model, String uri,
        KGraphLanguageServerExtension languageServer, KGraphLanguageClient client
    ) {
        var serializer = false
        
        var sourceModel = model.getProperty(KlighdInternalProperties.MODEL_ELEMENT)
        if (!model.hasProperty(KlighdInternalProperties.MODEL_ELEMENT)) {
            sourceModel = model.children.get(0).getProperty(KlighdInternalProperties.MODEL_ELEMENT)
        }
        for (IConstraintSerializer cs : ServiceLoader.load(IConstraintSerializer,
                KlighdDataManager.getClassLoader())) {
            if (cs.canHandle(sourceModel)) {
                val resource = languageServer.getResource(uri)
                val textEdit = cs.serializeConstraints(changedNodes, model, resource)
                // Send changes to the client.
                client.replaceContentInFile(uri, textEdit.newText, textEdit.range)
                // If a serializer is registered, we do not need to update the layout since the diagram will update 
                // since the model changes.
                serializer = true
            }
        }
        // If there is no serializer that changes the textual model (which will cause a refresh model action),
        // we have to update the layout (not refresh the layout) to update based on the changed via model.
        if (!serializer) {
            languageServer.updateLayout(uri)
        }
        return
    }
    
    /**
     * Creates a string of a model resource by saving the resource and returning the output string.
     * 
     * @param resource Resource to get the string for.
     */
    static def String serializeResource(Resource resource) {
        var outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        return outputStream.toString
    }
    
    /**
     * Creates a TextEdit based on the before and after text.
     * 
     * @param codeBefore The whole text before
     * @param codeAfter The new text
     * @return A TextEdit that assumes that the whole code before will ne replaced.
     */
    static def TextEdit calculateTextEdit(String codeBefore, String codeAfter) {
        val lines = codeBefore.split("\r\n|\r|\n")
        val lastLineLength = lines.get(lines.size - 1).length
        val Range range = new Range(new Position(0, 0), new Position(lines.length, lastLineLength))
        return new TextEdit(range, codeAfter)
    }
    
    /**
     * Returns id of a node.
     * 
     * @param node The node.
     * @return The id string of the node.
     */
    static def String getIdOfNode(KNode node) {
        var nameStringOfReferenceNode = node.toString
        val id = node.getData(KIdentifier)
        if (id !== null) {
            nameStringOfReferenceNode = id.id
        }
        return nameStringOfReferenceNode        
    }
    
}