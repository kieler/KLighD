/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2025 by
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
package de.cau.cs.kieler.klighd.lsp

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphData
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.krendering.KColor
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KImage
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
import de.cau.cs.kieler.klighd.krendering.KRenderingUtil
import de.cau.cs.kieler.klighd.lsp.model.ImageData
import de.cau.cs.kieler.klighd.lsp.model.SKEdge
import de.cau.cs.kieler.klighd.lsp.model.SKGraph
import de.cau.cs.kieler.klighd.lsp.model.SKLabel
import de.cau.cs.kieler.klighd.lsp.model.SKNode
import de.cau.cs.kieler.klighd.lsp.model.SKPort
import de.cau.cs.kieler.klighd.lsp.utils.KGraphElementIdGenerator
import de.cau.cs.kieler.klighd.lsp.utils.KGraphMappingUtil
import de.cau.cs.kieler.klighd.lsp.utils.SprottyProperties
import de.cau.cs.kieler.klighd.util.KlighdPredicates
import de.cau.cs.kieler.klighd.util.KlighdProperties
import de.cau.cs.kieler.klighd.util.RenderingContextData
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import org.apache.log4j.Logger
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.sprotty.Dimension
import org.eclipse.sprotty.SEdge
import org.eclipse.sprotty.SGraph
import org.eclipse.sprotty.SLabel
import org.eclipse.sprotty.SModelElement
import org.eclipse.sprotty.SNode
import org.eclipse.sprotty.SPort
import org.eclipse.sprotty.xtext.IDiagramGenerator
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.util.CancelIndicator

/**
 * A diagram generator that can create Sprotty {@link SGraph} from any {@link Object} that has a registered view
 * synthesis to {@link KNode} for KLighD. 
 * For translation first call the {@link #translateModel(Object, ViewContext)} function to translate the Object to a KNode and then call
 * {@link #toSGraph(KNode, String, CancelIndicator)} with that KNode to translate it to an SGraph.
 * During translation a map for mapping between the source and target element types is generated.
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nre
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangDiagramGenerator.xtend">
 *      YangDiagramGenerator</a>
 */
class KGraphDiagramGenerator implements IDiagramGenerator {
	static val LOG = Logger.getLogger(KGraphDiagramGenerator)
    
    /**
     * A map that maps each {@link KGraphElement} to its {@link SModelElement}.
     * Convenient for finding a specific key KGraphElement faster.
     * @see KGraphDiagramState
     * @see KGraphLayoutEngine
     */
    @Accessors(PUBLIC_GETTER)
    protected var BiMap<KGraphElement, SModelElement> kGraphToSModelElementMap
    
    /**
     * The data of all {@link KImage}s contained in the view model.
     */
    @Accessors(PUBLIC_GETTER)
    protected var HashSet<ImageData> images

    /**
     * The root node of the translated {@link SGraph}.
     */
	protected var SGraph diagramRoot
    
    /**
     * Generates unique IDs for any KGraphElement.
     */
    protected KGraphElementIdGenerator idGen
    
    /**
     * List of all {@link KEdge}s that need to be generated in the end and added into the list that is the second
     * element of each pair.
     */
    protected List<Pair<KEdge, List<SModelElement>>> edgesToGenerate
    
    /**
     * The color that the background canvas of the viewer should be assigned.
     */
    protected KColor backgroundColor = null

    /**
     * Creates a {@link ViewContext} containing the KGraph model for the {@link ViewContext} of any {@link Object} model
     * with a registered transformation in KLighD. 
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
     * @param viewContext     the viewContext containing the KNode that should be translated and further information
     *                        such as the background color for the canvas. This is the parent node containing all
     *                        elements of the graph, that is translated
     * @param uri             The uri of the source model the parent node was synthesized from. Used as the ID of the
     *                        generated graph.
     * @param cancelIndicator Indicates, if the action requesting this translation has already been canceled.
     */
	def SGraph toSGraph(ViewContext viewContext, String uri, CancelIndicator cancelIndicator) {
	    if (viewContext.hasProperty(KlighdProperties.CANVAS_COLOR)) {
	        val color = viewContext.getProperty(KlighdProperties.CANVAS_COLOR)
	        backgroundColor = KRenderingFactory.eINSTANCE.createKColor() => [
                red = color.red
                green = color.green
                blue = color.blue
            ]
	    }
	    return toSGraph(viewContext.viewModel, uri, cancelIndicator)
	}
	
	/**
	 * Translates a plain {@link KNode} or a KNode translated by {@link #translateModel} to an {@link SGraph}. 
	 * @param parentNode      the KNode that should be translated. This is the parent node containing all elements of
	 *                        the graph, that is translated
	 * @param uri             The uri of the source model the parent node was synthesized from. Used as the ID of the
	 *                        generated graph.
	 * @param cancelIndicator Indicates, if the action requesting this translation has already been canceled.
	 */
	def SGraph toSGraph(KNode parentNode, String uri, CancelIndicator cancelIndicator) {
        LOG.info("Generating diagram for input: '" + uri + "'")

        kGraphToSModelElementMap = HashBiMap.create
        images = new HashSet
        idGen = new KGraphElementIdGenerator
        edgesToGenerate = new ArrayList

        // generate an SGraph root element around the translation of the parent KNode.
        diagramRoot = new SKGraph => [
            type = 'graph'
            id = uri
            children = new ArrayList
        ]
        
        // Special property for the diagram background.
        if (backgroundColor !== null) {
            (diagramRoot as SKGraph).properties.put(KlighdInternalProperties.DIAGRAM_BACKGROUND.id, backgroundColor)
        }

        diagramRoot.children.addAll(createNodesAndPrepareEdges(#[parentNode], diagramRoot))
        // Do post processing.
        postProcess()

        LOG.info("Completed generating diagram for input: '" + uri + "'")

        return if (cancelIndicator.canceled) 
               null
           else 
               diagramRoot
	}

    /**
     * Translates all {@code nodes} and their outgoing edges to {@link SModelElement}s. Also handles
     * mapping between {@link KGraphElement}s and SModelElements.
     * The edges are translated together with the nodes, because {@link KNode}s contain {@link KEdge}s in the field 
     * {@link KNode#getOutgoingEdges} as children, whereas outgoing {@link SEdge}s are siblings of their originating 
     * {@link SNode}s.
     * The edges are stored to be generated later in the {@code edgesToGenerate} list and need to be generated later.
     */
    private def List<SModelElement> createNodesAndPrepareEdges(List<KNode> nodes, SModelElement parent) {
        val nodeAndEdgeElements = new ArrayList
        // add all node children
        for (node : nodes) {
            val SNode nodeElement = generateNode(node)
            if (nodeElement !== null) {
                nodeAndEdgeElements.add(nodeElement)
                kGraphToSModelElementMap.put(node, nodeElement)
    
                // Add all edges in a list to be generated later, as they need their source and target nodes or ports
                // to be generated previously. Because hierarchical edges could connect to any arbitrary parent or child node,
                // they can only be generated safely in the end.
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
            }
        }
        return nodeAndEdgeElements
    }

    /**
     * Function to be called after the the {@link SKGraph} has been generated and all edges are prepared to be added
     * in the {@link edgesToGenerate} field. This method translates all {@link KEdge}s in the {@link edgesToGenerate}
     * field to {@link SModelElement}s and adds them to their corresponding parent SModelElement.
     * Also handles mapping between {@link KGraphElement}s and SModelElements.
     */
    private def createEdges() {
        edgesToGenerate.forEach [ edgeAndParent |
            val edge = edgeAndParent.key
            val parent = edgeAndParent.value
            val SEdge edgeElement = generateEdge(edge)
            if (edgeElement !== null) {
                parent.add(edgeElement)
                kGraphToSModelElementMap.put(edge, edgeElement)
            }
        ]
    }

    /**
     * Translates all {@code ports} to SModelElements. Also handles mapping between
     * KGraphElements and SModelElements.
     */
    protected def List<SPort> createPorts(List<KPort> ports) {
        val List<SPort> portElements = new ArrayList
        for (port : ports) {
            val SPort portElement = generatePort(port)
            if (portElement !== null) {
                portElements.add(portElement)
                kGraphToSModelElementMap.put(port, portElement)
            }
        }
        return portElements
    }

    /**
     * Translates all {@code labels} to SModelElements. Also handles mapping between
     * KGraphElements and SModelElements.
     */
    protected def List<SLabel> createLabels(List<KLabel> labels) {
        val List<SLabel> labelElements = new ArrayList
        for (label : labels) {
            val SLabel labelElement = generateLabel(label)
            if (labelElement !== null) {
                labelElements.add(labelElement)
                kGraphToSModelElementMap.put(label, labelElement)
            }
        }
        return labelElements
    }

    /**
     * Creates a Sprotty node corresponding to the given {@link KNode}.
     */
    private def SKNode generateNode(KNode node) {
        val renderingContextData = RenderingContextData.get(node)
        // activate the element if it should be shown.
        renderingContextData.setProperty(KlighdInternalProperties.ACTIVE, node.getProperty(KlighdProperties.SHOW))
        if (!node.getProperty(KlighdProperties.SHOW)) {
            return null
        }
        
        val nodeElement = configSElement(SKNode, idGen.getId(node))

        nodeElement.size = new Dimension(node.width, node.height)
        val filteredData = node.data.filter [
            KRendering.isAssignableFrom(it.class) || KRenderingLibrary.isAssignableFrom(it.class)
        ].toList

        nodeElement.data = node.data.filter[KRenderingLibrary.isAssignableFrom(it.class)].toList
        
        setProperties(nodeElement, node)
        KGraphMappingUtil.mapProperties(node, nodeElement)
        findSpecialRenderings(filteredData)
        
        // Populate the children of this node only if child nodes exist and the node should be drawn expanded.
        var boolean isExpanded
        if (renderingContextData.hasProperty(SprottyProperties.EXPANDED)) {
            isExpanded = renderingContextData.getProperty(SprottyProperties.EXPANDED)
        } else {
            // If the expanded property does not exist yet, use the initial expansion.
            isExpanded = node.getProperty(KlighdProperties.EXPAND)
        }
        
        nodeElement.children.addAll(createPorts(node.ports))
        nodeElement.children.addAll(createLabels(node.labels))
        if (isExpanded) {
            renderingContextData.setProperty(KlighdInternalProperties.POPULATED, true)
            nodeElement.children.addAll(createNodesAndPrepareEdges(node.children, nodeElement))
        } else {
            renderingContextData.setProperty(KlighdInternalProperties.POPULATED, false)
        }
        return nodeElement
    }
    
    /**
     * Set all properties supported by the client.
     */
    def setProperties(SKNode nodeElement, KNode node) {
        
        var parent = node
        if (node.parent !== null) {
            parent = node.parent
        }
        
        // The client expects every node to know what its direction is
        nodeElement.direction = parent.getProperty(LayeredOptions.DIRECTION)
    }

    /**
     * Creates a Sprotty edge corresponding to the given {@link KEdge}.
     * Assumes, that the source and target nodes or ports of this {@code edge} have already been generated.
     */
    protected def SKEdge generateEdge(KEdge edge) {
        val renderingContextData = RenderingContextData.get(edge)
        // activate the element if it should be shown.
        renderingContextData.setProperty(KlighdInternalProperties.ACTIVE, edge.getProperty(KlighdProperties.SHOW))
        if (!edge.getProperty(KlighdProperties.SHOW)) {
            return null
        }
        
        val SKEdge edgeElement = configSElement(SKEdge, idGen.getId(edge))
        edgeElement.sourceId = idGen.getId(edge.source)
        edgeElement.targetId = idGen.getId(edge.target)

        val renderings = edge.data.filter[KRendering.isAssignableFrom(it.class)].toList
        
        KGraphMappingUtil.mapProperties(edge, edgeElement)
        findSpecialRenderings(renderings)
        edgeElement.children.addAll(createLabels(edge.labels))
        edgeElement.junctionPoints = edge.getProperty(CoreOptions.JUNCTION_POINTS)

        return edgeElement
    }

    /**
     * Creates a Sprotty port corresponding to the given {@link KPort}.
     */
    protected def SKPort generatePort(KPort port) {
        val renderingContextData = RenderingContextData.get(port)
        // activate the element if it should be shown.
        renderingContextData.setProperty(KlighdInternalProperties.ACTIVE, port.getProperty(KlighdProperties.SHOW))
        if (!port.getProperty(KlighdProperties.SHOW)) {
            return null
        }
        val SKPort portElement = configSElement(SKPort, idGen.getId(port))
        
        val renderings = port.data.filter [ KRendering.isAssignableFrom(it.class)].toList
        
        KGraphMappingUtil.mapProperties(port, portElement)
        findSpecialRenderings(renderings)
        portElement.children.addAll(createLabels(port.labels))

        return portElement
    }

    /**
     * Creates a Sprotty label corresponding to the given {@link KLabel}.
     */
    protected def SKLabel generateLabel(KLabel label) {
        val renderingContextData = RenderingContextData.get(label)
        // activate the element if it should be shown.
        renderingContextData.setProperty(KlighdInternalProperties.ACTIVE, label.getProperty(KlighdProperties.SHOW))
        if (!label.getProperty(KlighdProperties.SHOW)) {
            return null
        }
        val SKLabel labelElement = configSElement(SKLabel, idGen.getId(label))
        labelElement.text = label.text

        val renderings = label.data.filter[KRendering.isAssignableFrom(it.class)].toList

        KGraphMappingUtil.mapProperties(label, labelElement)
        findSpecialRenderings(renderings)
        return labelElement
    }

    /**
     * Generates a generic {@link SModelElement} with the defaults {@code id}, {@code type} already set and the 
     * {@code children} list already initialized.
     */
    protected static def <E extends SModelElement> E configSElement(Class<E> elementClass, String idStr) {
        elementClass.constructor.newInstance => [
            id = idStr
            type = getTypeString(it)
            children = new ArrayList
        ]
    }

    /**
     * Handles processing that has to happen after the generation of the SKGraph model depending on data that may only
     * be accessible once all elements are generated.
     */
    def postProcess() {
        // Create the edges all edges now that their source and target IDs are defined
        createEdges()

        // Add all active renderings to the sModelElements.
        this.kGraphToSModelElementMap.forEach [ kGraphElement, sModelElement |
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
        ]
    }

    /**
     * Looks through the data of elements and searches for special renderings that are needed to be pre-processed before
     * rendering:
     * Stores all {@link KImage}s in the {@code images} field.
     */
    protected def void findSpecialRenderings(List<KGraphData> datas) {
        for (data : datas) {
            findSpecialRenderings(data)
        }
    }

    /**
     * Finds all {@link KImage} elements within the renderings in {@code dataList} and stores them.
     */
    protected def void findSpecialRenderings(KGraphData data) {
        var ImageData imageData = null
        if (data instanceof KContainerRendering) {
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
        if (imageData !== null) {
            images.add(imageData)
        }
    }
    
    /**
     * Get method for the mapping from generated IDs to their corresponding {@link KGraphElement}s.
     * 
     * @return The mapping.
     */
    def getIdToKGraphElementMap() {
        return idGen.idToElementMap
    }

    /**
     * Returns a String describing the type of the {@link SModelElement}.
     */
    protected static def String getTypeString(SModelElement element) {
        switch element {
            SNode: 'node'
            SLabel: 'label'
            SEdge: 'edge'
            SPort: 'port'
            default: throw new IllegalArgumentException("Unknown SModelElement type: "+ element?.class)
        }
    }
}
