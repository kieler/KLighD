/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2021 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
import de.cau.cs.kieler.klighd.krendering.KRenderingUtil
import de.cau.cs.kieler.klighd.lsp.model.SKEdge
import de.cau.cs.kieler.klighd.lsp.model.SKGraph
import de.cau.cs.kieler.klighd.lsp.model.SKLabel
import de.cau.cs.kieler.klighd.lsp.model.SKNode
import de.cau.cs.kieler.klighd.lsp.model.SKPort
import de.cau.cs.kieler.klighd.lsp.utils.KGraphElementIdGenerator
import de.cau.cs.kieler.klighd.lsp.utils.SprottyProperties
import de.cau.cs.kieler.klighd.util.KlighdPredicates
import de.cau.cs.kieler.klighd.util.KlighdProperties
import de.cau.cs.kieler.klighd.util.RenderingContextData
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import java.util.LinkedList
import java.util.List
import java.util.Queue
import org.apache.log4j.Logger
import org.eclipse.sprotty.Dimension
import org.eclipse.sprotty.SEdge
import org.eclipse.sprotty.SGraph
import org.eclipse.sprotty.SLabel
import org.eclipse.sprotty.SModelElement
import org.eclipse.sprotty.SNode
import org.eclipse.sprotty.SPort
import org.eclipse.xtext.util.CancelIndicator

/**
 * A breadth-first implementation of {@link KGraphDiagramGenerator}. Breadth-first traversal allows
 * limiting the amount of depth of a diagram that is rendered.
 * @author mka
 */
class KGraphIncrementalDiagramGenerator extends KGraphDiagramGenerator {
    static val LOG = Logger.getLogger(KGraphIncrementalDiagramGenerator)
    
    /**
     * Queue of remaining child {@link Knode}s to process in breadth-first traversal to construct diagram.
     */
    Queue<KNode> childrenToProcess
    
    /**
     * Queue of graph elements that still need to be post processed.
     */
    Queue<Pair<KGraphElement, SModelElement>> elementsToPostProcess
    

    /**
     * Creates a {@link ViewContext} containing the KGraph model for any {@link Object} model with a registered 
     * transformation in KLighD. 
     */
    static def ViewContext translateModel(Object model, ViewContext oldVC) {
        return LightDiagramServices.translateModel2(model, oldVC)
    }

    /**
     * Generates an {@link SGraph} from the resource if its content is a {@link KNode}.
     */
    override generate(Context context) {
        // TODO: The context now contains more data (especially, also some IDiagramState). Check if this has advantages
        // over our solution
        val content = context.resource.contents.head
        var SGraph ret = null
        if (content instanceof KNode) {
            ret = toSGraph(content as KNode, context.resource.URI.toString, context.cancelIndicator)
        }
        return ret
    }
    
    /**
     * Translates a plain {@link KNode} or a KNode translated by {@link #translateModel} to an {@link SGraph}. 
     * @param parentNode      the KNode that should be translated. This is the parent node containing all elements of
     *                        the graph, that is translated
     * @param uri             The uri of the source model the parent node was synthesized from. Used as the ID of the
     *                        generated graph.
     * @param cancelIndicator Indicates, if the action requesting this translation has already been canceled.
     */
    
    // NOTE: This is a temporary adapter function to serve externally like the existing toSGraph function
    // but internally enable incremental building of the SGraph
    // this will simulate a client requesting pieces, the next steps will be incremental support in both
    // directions (client requests and further processing such as layout not done with this sgraph though)
    // most importantly: communication between server and client should start using incremental data
    override SGraph toSGraph(KNode parentNode, String uri, CancelIndicator cancelIndicator) {
        
        kGraphToSModelElementMap = new HashMap
        textMapping = new HashMap
        modelLabels = new ArrayList
        images = new HashSet
        idGen = new KGraphElementIdGenerator
        edgesToGenerate = new LinkedList
        elementsToPostProcess = new LinkedList
        childrenToProcess = new LinkedList
        // generate an SGraph root element around the translation of the parent KNode.
        diagramRoot = new SKGraph => [
            type = 'graph'
            id = uri
            children = new ArrayList
        ]

        diagramRoot.children.addAll(incrementalCreateNodesAndPrepareEdges(parentNode, diagramRoot))
        incrementalPostProcess()

        /* ----------- Extracted to individual calls -----------------
        // priority style queue queuing elements in viewing area first might be interesting
        // wagon.sctx has 9 levels and 310 (node) elements
        val maxLevel = hierarchyDepth // controls how many hierarchy levels should be generated
        var currentLevel = 0
        while (childrenToProcess.peek() !== null && currentLevel < maxLevel) {
            
            var elementsOnLevel = childrenToProcess.size()
            while (elementsOnLevel > 0) {
                processNextElement()
            
                elementsOnLevel--
            }
            currentLevel++
            
        }
        * 
        */

        return if (cancelIndicator.canceled) 
               null
           else 
               diagramRoot
    }
    
    // TODO: need mechanisms to manipulate the queue in such a way to prioritize generating requested pieces
    /**
     * Function to request next part of the diagram. Should eventually support getting specific pieces if they are ready.
     */
    def SModelElement getNextDiagramPiece() {
        if (childrenToProcess.peek() !== null) {
            return processNextElement()
        }
    }
    
    /**
     * Like getNextDiagramPiece, but doesn't return piece. Only triggers generation.
     */
    def generateNextDiagramPiece() {
        getNextDiagramPiece
    }
    
    def boolean nodeChildrenAllProcessed(KGraphElement node) {
        for (child: childrenToProcess) {
            if (child.parent.equals(node)) {
                return false
            }
        }
        return true
    }
    
    private def SModelElement processNextElement() {
        val node = childrenToProcess.remove()
        // get parent node to add children to
        val skNode = kGraphToSModelElementMap.get(node.parent)
        
        skNode.children.addAll(incrementalCreateNodesAndPrepareEdges(node, skNode))
        incrementalPostProcess()
        return skNode
    }
    
    /**
     * Translates one {@code node} and its outgoing edges to {@link SModelElement}s. Also handles tracing and
     * mapping between {@link KGraphElement}s and SModelElements.
     * The edges are translated together with the nodes, because {@link KNode}s contain {@link KEdge}s in the field 
     * {@link KNode#getOutgoingEdges} as children, whereas outgoing {@link SEdge}s are siblings of their originating 
     * {@link SNode}s.
     * The edges are stored to be generated later in the {@code edgesToGenerate} list and need to be generated later.
     * The children of the node are added to the {@code childrenToProcess} queue to be generated later.
     */
    private def List<SModelElement> incrementalCreateNodesAndPrepareEdges(KNode node, SModelElement parent) {
        val nodeAndEdgeElements = new ArrayList
        // add all node children
        val SNode nodeElement = incrementalGenerateNode(node)
        nodeAndEdgeElements.add(nodeElement)
        kGraphToSModelElementMap.put(node, nodeElement)
        elementsToPostProcess.add(new Pair(node, nodeElement))
        nodeElement.trace(node)

        // Add all edges in a list to be generated later, as they need their source and target nodes or ports
        // to be generated previously. Because hierarchical edges could connect to any arbitrary parent or child node,
        // they can only be generated safely in the end.\
        // NOTE: since I will now do post processing for every increment, hierarchical edges should be completely broken
        for (edge : node.outgoingEdges) {
            if (edge.target !== null) {
                // if target node is directly or indirectly contained by the source node
                if (KGraphUtil.isDescendant(edge.target, node)) {
                    // then generated element of node (add to its children)
                    edgesToGenerate.add(edge -> nodeElement.children)
                } else {
                    // otherwise the source node's parent generated element (add to its children)
                    edgesToGenerate.add(edge -> parent.children)
                }
            }
        }
        return nodeAndEdgeElements
    }
    
    /**
     * Creates a Sprotty node corresponding to the given {@link KNode}.
     */
    private def SKNode incrementalGenerateNode(KNode node) {
        // only generate self and leave children as stubs to be generated later
        val nodeElement = configSElement(SKNode, idGen.getId(node))
        
        nodeElement.size = new Dimension(node.width, node.height)
        nodeElement.tooltip = node.getProperty(KlighdProperties.TOOLTIP)
        val filteredData = node.data.filter [
            KRendering.isAssignableFrom(it.class) || KRenderingLibrary.isAssignableFrom(it.class)
        ].toList
        
        nodeElement.data = node.data.filter[KRenderingLibrary.isAssignableFrom(it.class)].toList
        
        setProperties(nodeElement, node)
        findSpecialRenderings(filteredData)
        
        val renderingContextData = RenderingContextData.get(node)
        // activate the element by default if it does not have an active/inactive status yet.
        if (!renderingContextData.containsPoperty(KlighdInternalProperties.ACTIVE)) {
            renderingContextData.setProperty(KlighdInternalProperties.ACTIVE, true)
        }
        
        var boolean isExpanded
        if (renderingContextData.hasProperty(SprottyProperties.EXPANDED)) {
            isExpanded = renderingContextData.getProperty(SprottyProperties.EXPANDED)
        } else {
            // If the expanded property does not exist yet, use the initial expansion.
            isExpanded = node.getProperty(KlighdProperties.EXPAND)
        }
        
        nodeElement.children.addAll(createPorts(node.ports))
        nodeElement.children.addAll(createLabels(node.labels))
        
        if ((!node.children.empty) && isExpanded) {
            renderingContextData.setProperty(KlighdInternalProperties.POPULATED, true)
            // nodeElement.children.addAll(createNodesAndPrepareEdges(node.children, nodeElement))
            childrenToProcess.addAll(node.children)
        } else {
            renderingContextData.setProperty(KlighdInternalProperties.POPULATED, false)
        }
       
        
        return nodeElement
    }

    /**
     * Function to be called after the the {@link SKGraph} has been generated and all edges are prepared to be added
     * in the {@link edgesToGenerate} field. This method translates all {@link KEdge}s in the {@link edgesToGenerate}
     * field to {@link SModelElement}s and adds them to their corresponding parent SModelElement.
     * Also handles tracing and mapping between {@link KGraphElement}s and SModelElements.
     * Only creates edges that can already be created i.e., where the corresponding nodes have already been
     * generated.
     */
    private def incrementalCreateEdges() {
        // need to remove processed edges from list and can only process edges where both nodes already exist
        // go through queue of edges and create those that can be generated
        var remainingEdges = new LinkedList<Pair<KEdge, List<SModelElement>>>
        for( Pair<KEdge, List<SModelElement>> edgeAndParent : edgesToGenerate ) {
            val edge = edgeAndParent.key
            val parent = edgeAndParent.value
            
            // check if source and target of edge have already been generated
            if (kGraphToSModelElementMap.containsKey(edge.target) && kGraphToSModelElementMap.containsKey(edge.source)){
                val SEdge edgeElement = generateEdge(edge)
                    if (edgeElement !== null) {
                    parent.add(edgeElement)
                    kGraphToSModelElementMap.put(edge, edgeElement)
                    elementsToPostProcess.add(new Pair(edge, edgeElement))
                    edgeElement.trace(edge)
                }
            } else {
                remainingEdges.add(edgeAndParent)
            }
        }
        // update edgesToGenerate
        edgesToGenerate = remainingEdges
    }

    /**
     * Translates all {@code ports} to SModelElements. Also handles tracing and mapping between
     * KGraphElements and SModelElements.
     */
    private def List<SPort> createPorts(List<KPort> ports) {
        val List<SPort> portElements = new ArrayList
        for (port : ports) {
            val SPort portElement = generatePort(port)
            portElements.add(portElement)
            kGraphToSModelElementMap.put(port, portElement)
            elementsToPostProcess.add(new Pair(port, portElement))
            portElement.trace(port)
        }
        return portElements
    }

    /**
     * Translates all {@code labels} to SModelElements. Also handles tracing and mapping between
     * KGraphElements and SModelElements.
     */
    private def List<SLabel> createLabels(List<KLabel> labels) {
        val List<SLabel> labelElements = new ArrayList
        for (label : labels) {
            val SLabel labelElement = generateLabel(label, true)
            labelElements.add(labelElement)
            kGraphToSModelElementMap.put(label, labelElement)
            elementsToPostProcess.add(new Pair(label, labelElement))
            labelElement.trace(label)
        }
        return labelElements
    }


    /**
     * Handles processing that has to happen after the generation of the SKGraph model depending on data that may only
     * be accessible once all elements are generated. In each call the elements that can be processed are processed
     * and subsequently removed from the queue.
     */
    def incrementalPostProcess() {
        // Create the edges all edges now that their source and target IDs are defined
        // do this incrementally after each step always only generating the edges that can at that point be generated
        incrementalCreateEdges()

        // Add all active renderings to the sModelElements.
        while (!elementsToPostProcess.isEmpty) {
            val elementPair = elementsToPostProcess.remove()
            val kGraphElement = elementPair.key
            val sModelElement = elementPair.value
            var KRendering currentRendering
            val renderings = kGraphElement.data.filter(KRendering)
            // Getting the current rendering similar to AbstractKGERenderingController#getCurrentRendering
            if (kGraphElement instanceof KNode) {
                // in case the node to be depicted is tagged as 'populated',
                // i.e. children are depicted in the diagram ...
                if (RenderingContextData.get(kGraphElement).getProperty(KlighdInternalProperties.POPULATED)) {
                    // ... look for a rendering tagged as 'expanded', ...
                    currentRendering = renderings.findFirst [
                        KlighdPredicates.isExpandedRendering().apply(it)
                    ]

                    // ... and if none exists ...
                    if (currentRendering === null) {
                        // ... take the first one that is not marked as 'collapsed' one
                        currentRendering = renderings.findFirst [
                            !KlighdPredicates.isCollapsedRendering().apply(it)
                        ]
                    }
                } else {
                    // in case the node to be depicted is tagged as 'not populated',
                    // i.e. no children are visible in the diagram
                    // look for a rendering marked as 'collapsed' one, ...
                    currentRendering = renderings.findFirst [
                        KlighdPredicates.isCollapsedRendering().apply(it)
                    ]

                    // ... and if none exists ...
                    if (currentRendering === null) {
                        // ... take the first one that is not marked as 'expanded' one
                        currentRendering = renderings.findFirst [
                            !KlighdPredicates.isExpandedRendering().apply(it)
                        ]
                    }
                }
                (sModelElement as SKNode).data.add(currentRendering)
            } else {
                // Child renderings of elements other than KNodes are always displayed.
                if (renderings.empty) {
                    // Create a default rendering for each type.
                    currentRendering = KRenderingUtil.createDefaultRendering(kGraphElement)
                    kGraphElement.data.add(currentRendering)
                } else {
                    currentRendering = renderings.head
                }
                switch (sModelElement.class) {
                    case SKEdge:
                        (sModelElement as SKEdge).data = #[currentRendering]
                    case SKPort:
                        (sModelElement as SKPort).data = #[currentRendering]
                    case SKLabel:
                        (sModelElement as SKLabel).data = #[currentRendering]
                }
            }
        }
    }
}