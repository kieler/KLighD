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
import de.cau.cs.kieler.klighd.kgraph.KGraphData
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KImage
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
import de.cau.cs.kieler.klighd.krendering.KRenderingUtil
import de.cau.cs.kieler.klighd.krendering.KText
import de.cau.cs.kieler.klighd.lsp.model.ImageData
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
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.sprotty.Dimension
import org.eclipse.sprotty.SEdge
import org.eclipse.sprotty.SGraph
import org.eclipse.sprotty.SLabel
import org.eclipse.sprotty.SModelElement
import org.eclipse.sprotty.SNode
import org.eclipse.sprotty.SPort
import org.eclipse.xtext.resource.XtextResource
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
     * Generates a trace for the {@code kElement}'s source EObject on the {@code sElement}. 
     * The kElement must be synthesized by a KLighD synthesis before and must have its source EObject stored in the 
     * {@link KlighdInternalProperties#MODEL_ELEMEMT} property.
     */
    private def void trace(SModelElement sElement, KGraphElement kElement) {
        // The real model element that can be traced is the EObject that got synthesized in the
        // {@link translateModel} function. That model element has to be stored in the properties during the 
        // synthesis. Otherwise the tracing will not work
        // FIXME: For large diagrams (expanded railway environment) the tracing alone
        // requires an additional 40s (almost 50% of the generation time), which is not acceptable.
        // This should be done just in time when tracing is requested instead of statically for every element.
        if (activeTracing) {
            val modelKElement = kElement.properties.get(KlighdInternalProperties.MODEL_ELEMEMT)
            if (modelKElement instanceof EObject) {
                if (modelKElement.eResource instanceof XtextResource) {
                    traceProvider.trace(sElement, modelKElement)
                }
            }
        }
    }
    
    /**
     * Creates a Sprotty edge corresponding to the given {@link KEdge}.
     * Assumes, that the source and target nodes or ports of this {@code edge} have already been generated.
     */
    private def SKEdge generateEdge(KEdge edge) {
        val SKEdge edgeElement = configSElement(SKEdge, idGen.getId(edge))
        edgeElement.sourceId = idGen.getId(edge.source)
        edgeElement.targetId = idGen.getId(edge.target)
        edgeElement.tooltip = edge.getProperty(KlighdProperties.TOOLTIP)

        val renderings = edge.data.filter[KRendering.isAssignableFrom(it.class)].toList
        
        findSpecialRenderings(renderings)
        edgeElement.children.addAll(createLabels(edge.labels))
        edgeElement.junctionPoints = edge.getProperty(CoreOptions.JUNCTION_POINTS)

        // activate the element by default if it does not have an active/inactive status yet.
        val renderingContextData = RenderingContextData.get(edge)
        if (!renderingContextData.containsPoperty(KlighdInternalProperties.ACTIVE)) {
            renderingContextData.setProperty(KlighdInternalProperties.ACTIVE, true)
        }

        return edgeElement
    }

    /**
     * Creates a Sprotty port corresponding to the given {@link KPort}.
     */
    private def SKPort generatePort(KPort port) {
        val SKPort portElement = configSElement(SKPort, idGen.getId(port))
        portElement.tooltip = port.getProperty(KlighdProperties.TOOLTIP)
        
        val renderings = port.data.filter [ KRendering.isAssignableFrom(it.class)].toList
        
        findSpecialRenderings(renderings)
        portElement.children.addAll(createLabels(port.labels))

        // activate the element by default if it does not have an active/inactive status yet.
        val renderingContextData = RenderingContextData.get(port)
        if (!renderingContextData.containsPoperty(KlighdInternalProperties.ACTIVE)) {
            renderingContextData.setProperty(KlighdInternalProperties.ACTIVE, true)
        }

        return portElement
    }

    /**
     * Creates a Sprotty label corresponding to the given {@link KLabel}.
     * 
     * @param isMainGraphElement Describes, if the generated label will be part of the main generated {@link SGraph}.
     */
    private def SKLabel generateLabel(KLabel label, boolean isMainGraphElement) {
        val id = isMainGraphElement ? idGen.getId(label) : label.data.filter(KIdentifier).head.id
        val SKLabel labelElement = configSElement(SKLabel, id)
        labelElement.tooltip = label.getProperty(KlighdProperties.TOOLTIP)
        labelElement.text = label.text

        val renderings = label.data.filter[KRendering.isAssignableFrom(it.class)].toList

        if (isMainGraphElement) {
            // remember KLabel element for later size estimation
            findSpecialRenderings(renderings)
            // activate the element by default if it does not have an active/inactive status yet.
            val renderingContextData = RenderingContextData.get(label)
            if (!renderingContextData.containsPoperty(KlighdInternalProperties.ACTIVE)) {
                renderingContextData.setProperty(KlighdInternalProperties.ACTIVE, true)
            }
        } else {
            // Add the renderings here already to the element.
            labelElement.data = renderings
        }
        return labelElement
    }

    /**
     * Generates a generic {@link SModelElement} with the defaults {@code id}, {@code type} already set and the 
     * {@code children} list already initialized.
     */
    private static def <E extends SModelElement> E configSElement(Class<E> elementClass, String idStr) {
        elementClass.constructor.newInstance => [
            id = idStr
            type = getTypeString(it)
            children = new ArrayList
        ]
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

    /**
     * Looks through the data of elements and searches for special renderings that are needed to be pre-processed before
     * rendering:
     * Finds all KText and KLabel elements within the renderings in dataList and puts them as new labels in the
     * {@code modelLabels} field.
     * Remembers the mapping to the KText elements from the source model in the textMapping field.
     * Stores all {@link KImage}s in the {@code images} field.
     */
    private def void findSpecialRenderings(List<KGraphData> datas) {
        for (data : datas) {
            findSpecialRenderings(data)
        }
    }

    /**
     * Finds all {@link KText}, {@link KLabel} and {@link KImage} elements within the renderings in {@code dataList} and 
     * stores them. Also remembers the mapping to the KText elements from the source model in the {@code textMapping} 
     * field.
     */
    private def void findSpecialRenderings(KGraphData data) {
        val List<SKLabel> dataLabels = newArrayList
        var ImageData imageData = null
        if (data instanceof KText) {
            // create a new Label with data as its text for each line in the original text.
            // KTexts in Labels have their texts stored inside their ancestor KLabel, not in the KText itself
            var container = data.eContainer
            while (container instanceof KRendering) {
                container = container.eContainer
            }
            var String text
            if (container instanceof KLabel) {
                text = container.text
            } else {
                text = data.text
            }
            // Found the original text, split it up into individual labels for each line.
            // If there is no text, ignore this KText.
            val lines = text?.split("\\r?\\n", -1)
            lines?.forEach [ line, index |
                val newLabel = KGraphFactory.eINSTANCE.createKLabel
                newLabel.text = line
                // need to put a copy of the text inside the new label because otherwise inserting it into the label will
                // modify the eContainer feature of the Text, which should not be changed
                val newData = EcoreUtil.copy(data)
                newData.text = line
                newLabel.data += newData
                val identifier = KGraphFactory.eINSTANCE.createKIdentifier
                var uniqueId = ""
                do {
                    uniqueId = diagramRoot.id + KGraphElementIdGenerator.ID_SEPARATOR + "texts-only" + 
                    KGraphElementIdGenerator.ID_SEPARATOR + KGraphElementIdGenerator.LABEL_SEPARATOR 
                    + Math.random + KGraphElementIdGenerator.ID_SEPARATOR + index
                } while (textMapping.get(uniqueId) !== null)
                identifier.id = uniqueId
                newLabel.data += identifier
                
                // generate a new Label as if it would belong to the main model
                val sKLabel = generateLabel(newLabel, false)
                // All lines point towards the same original data. For matching, the index in the ID has to be taken
                // into account as well to match it to its line.
                textMapping.put(sKLabel.id, data)
                
                dataLabels += sKLabel
            ]
        } else if (data instanceof KContainerRendering) {
            // KImages are container renderings themselves, so also look for their child renderings.
            if (data instanceof KImage) {
                imageData = ImageData.of(data)
            }
            
            for (childData: data.children) {
                findSpecialRenderings(childData)
            }
        } else if (data instanceof KRenderingLibrary) {
            for (libraryEntry : data.renderings) {
                if (libraryEntry instanceof KRendering) {
                    findSpecialRenderings(libraryEntry)
                }
            }
        }
        if (!dataLabels.empty) {
            modelLabels.addAll(dataLabels)
        }
        if (imageData !== null) {
            images.add(imageData)
        }
    }

    /**
     * Returns a String describing the type of the {@link SModelElement}.
     */
    private static def String getTypeString(SModelElement element) {
        switch element {
            SNode: 'node'
            SLabel: 'label'
            SEdge: 'edge'
            SPort: 'port'
            default: throw new IllegalArgumentException("Unknown SModelElement type: "+ element?.class)
        }
    }

    /**
     * Generates a simple text-only {@link SGraph} for a Graph with only the given labels.
     * The {@code label}s are expected to come from the {@code modelLabels} field after the {@link #toSGraph}
     * translation.
     * 
     * @param labels A list of all labels, this SGraph should contain.
     * @param parentId The ID of the graph containing all these labels.
     */
    static def SGraph generateTextDiagram(List<SKLabel> labels, String parentId) {
        // equivalent for the SRootElement
        val root = new SKGraph => [
            type = 'graph'
            id = parentId + KGraphElementIdGenerator.ID_SEPARATOR + "texts-only"
            children = new ArrayList
        ]

        root.children = new ArrayList
        root.children += labels
        return root
    }
    
    def restoreState(KGraphDiagramState state) {
        throw new UnsupportedOperationException("TODO: auto-generated method stub")
    }
    
}