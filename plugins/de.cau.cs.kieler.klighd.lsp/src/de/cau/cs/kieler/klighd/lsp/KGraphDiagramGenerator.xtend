/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphData
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
import de.cau.cs.kieler.klighd.krendering.KText
import de.cau.cs.kieler.klighd.lsp.model.SKEdge
import de.cau.cs.kieler.klighd.lsp.model.SKGraph
import de.cau.cs.kieler.klighd.lsp.model.SKLabel
import de.cau.cs.kieler.klighd.lsp.model.SKNode
import de.cau.cs.kieler.klighd.lsp.model.SKPort
import de.cau.cs.kieler.klighd.lsp.utils.KGraphElementIDGenerator
import io.typefox.sprotty.api.Dimension
import io.typefox.sprotty.api.IDiagramState
import io.typefox.sprotty.api.SEdge
import io.typefox.sprotty.api.SGraph
import io.typefox.sprotty.api.SLabel
import io.typefox.sprotty.api.SModelElement
import io.typefox.sprotty.api.SNode
import io.typefox.sprotty.api.SPort
import io.typefox.sprotty.server.xtext.IDiagramGenerator
import io.typefox.sprotty.server.xtext.tracing.ITraceProvider
import io.typefox.sprotty.server.xtext.tracing.Traceable
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Random
import org.apache.log4j.Logger
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.util.CancelIndicator

/**
 * A diagram generator that can create Sprotty {@link SGraph} from any {@link EObject} that has a registered view
 * synthesis to {@link KNode} for KLighD. 
 * For translation first call the {@link translateModel} function to translate the EObject to a KNode and then call
 * {@link generate(KNode, String, CancelIndicator)} with that KNode to translate it to an SGraph.
 * During translation a map for mapping between the source and target element types is generated as well as
 * a list of all generated texts in Sprotty and a map to each text in the source model.
 * The function {@link #generateTextDiagram} can be called after the translation to get a simpler SGraph, that contains
 * only labels with all texts in the source KNode.
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nir
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangDiagramGenerator.xtend">
 *      YangDiagramGenerator</a>
 */
public class KGraphDiagramGenerator implements IDiagramGenerator {
	private static val LOG = Logger.getLogger(KGraphDiagramGenerator)
    
    /**
     * A map that maps each {@link KGraphElement} to its {@link SModelElement}.
     * Convenient for finding a specific key KGraphElement faster.
     * @see KGraphDiagramState
     * @see KGraphLayoutEngine
     */
    @Accessors(PUBLIC_GETTER)
    private var Map<KGraphElement, SModelElement> kGraphToSModelElementMap
    
    /**
     * A list containing all texts from the source KGraph inside Sprotty labels. Used for the simpler texts-only SGraph.
     * @see #generateTextDiagram
     * @see KGraphDiagramState
     */
    @Accessors(PUBLIC_GETTER)
    private var ArrayList<SKLabel> modelLabels
    
    /**
     * A map containing all {@link KText}s from the source KGraph under the key of their ID in the texts-only SGraph.
     * @see #generateTextDiagram
     * @see KGraphDiagramState
     */
    @Accessors(PUBLIC_GETTER)
    private var Map<String, KText> textMapping

    /**
     * The root node of the translated {@link SGraph}.
     */
	private var SGraph diagramRoot
	
	/**
	 * Provides functionality to tag SModelElements.
	 */
	@Inject
	private ITraceProvider traceProvider
    
    /**
     * A random value used to generate unique IDs for elements without a name.
     * This causes that the same object will get different IDs over multiple updates (prevents morphing of not matching
     * objects into each other). Unnamed elements therefore cannot morph on updated models. Name your elements!
     */
    private int randomOffset // TODO: maybe exchange the random value for the hash of the object
    
    /**
     * Counting how many labels and texts are in the graph.
     */
    private int numLabels
    
    /**
     * Generates unique IDs for any KGraphElement.
     */
    private KGraphElementIDGenerator idGen
    
    /**
     * List of all {@link KEdge}s that need to be generated in the end and added into the list that is the second
     * element of each pair.
     */
    private List<Pair<KEdge, List<SModelElement>>> edgesToGenerate

    /**
     * Creates a {@link ViewContext} containing the KGraph model for any {@link Object} model with a registered 
     * transformation in KLighD. 
     */
    public static def ViewContext translateModel(Object model, ViewContext oldVC) {
        return LightDiagramServices.translateModel2(model, oldVC)
    }
	
	/**
	 * Generates an {@link SGraph} from the resource if its content is a {@link KNode}.
	 */
	override generate(Resource resource, IDiagramState state, CancelIndicator cancelIndicator) {
		val content = resource.contents.head
		var SGraph ret = null
		if (content instanceof KNode) {
			ret = toSGraph(content as KNode, resource.URI.toString, cancelIndicator)
		}
		return ret
	}
	
	/**
	 * Translates a plain {@link KNode} or a KNode translated by {@link #translateModel} to an {@link SGraph}. 
	 * @param parentNode      the KNode that should be translated. This is the parent node containing all elements of the 
	 *                        graph, that is translated
	 * @param identifier      The URI of the resource containing this KNode before the translation.
	 * @param cancelIndicator Indicates, if the action requesting this translation has already been canceled.
	 */
	public def SGraph toSGraph(KNode parentNode, String identifier, CancelIndicator cancelIndicator) {
//        println("Starting SGraph generation!")
//        val startTime = System.currentTimeMillis
        LOG.info("Generating diagram for input: '" + identifier + "'")
	    
        val r = new Random
        randomOffset = r.nextInt
        numLabels = 0
        kGraphToSModelElementMap = new HashMap
        textMapping = new HashMap
        modelLabels = new ArrayList
        idGen = new KGraphElementIDGenerator
        edgesToGenerate = new ArrayList
        
        // generate an SGraph root element around the translation of the parent KNode.
        diagramRoot = new SKGraph => [
            type = 'graph'
            id = identifier
            children = new ArrayList
        ]
        
        diagramRoot.children.addAll(createNodesAndPrepareEdges(#[parentNode], diagramRoot))
        createEdges()
        
//        val endTime = System.currentTimeMillis
//        println("SGraph generation finished after " + (endTime - startTime) + "ms.")
        
        return if (cancelIndicator.canceled) 
                   null 
               else 
                   diagramRoot
        // TODO: incorporate the cancelIndicator on more places?
	}

    /**
     * Translates all {@code nodes} and their outgoing edges to {@link SModelElement}s. Also handles tracing and
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
            nodeAndEdgeElements.add(nodeElement)
            kGraphToSModelElementMap.put(node, nodeElement)
            nodeElement.trace(node)
            
            // Add all edges in a list to be generated later, as they need their source and target nodes or ports
            // to be generated previously. Because hierarchical edges could connect to any arbitrary parent or child node,
            // they can only be generated safely in the end.
            for (edge : node.outgoingEdges) {
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
     * Function to be called after the the {@link SKGraph} has been generated and all edges are prepared to be added
     * in the {@link edgesToGenerate} field. This method translates all {@link KEdge}s in the {@link edgesToGenerate}
     * field to {@link SModelElement}s and adds them to their corresponding parent SModelElement.
     * Also handles tracing and mapping between {@link KGraphElement}s and SModelElements.
     */
    private def createEdges() {
        edgesToGenerate.forEach[ edgeAndParent |
            val edge = edgeAndParent.key
            val parent = edgeAndParent.value
            val SEdge edgeElement = generateEdge(edge)
            if (edgeElement !== null) {
                parent.add(edgeElement)
                kGraphToSModelElementMap.put(edge, edgeElement)
                edgeElement.trace(edge)
            }
        ]
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
        if (sElement instanceof Traceable) {
            // The real model element that can be traced is the EObject that got synthesized in the
            // {@link translateModel} function. That model element has to be stored in the properties during the 
            // synthesis. Otherwise the tracing will not work
            val modelKElement = kElement.properties.get(KlighdInternalProperties.MODEL_ELEMEMT)
            if (modelKElement instanceof EObject) {
                traceProvider.trace(sElement, modelKElement)    
            }
        }
    }
    
    /**
     * Creates a Sprotty node corresponding to the given {@link KNode}.
     */
    private def SKNode generateNode(KNode node) {
        val nodeElement = configSElement(SKNode, idGen.getId(node))
        
        nodeElement.size = new Dimension(node.width, node.height)
        val filteredData = node.data.filter [
            KRendering.isAssignableFrom(it.class)
            || KRenderingLibrary.isAssignableFrom(it.class)
        ].toList
        
        nodeElement.data = filteredData
        
        modelLabels.addAll(findTextsAndLabels(filteredData))
        
        nodeElement.children.addAll(createPorts(node.ports))
        nodeElement.children.addAll(createNodesAndPrepareEdges(node.children, nodeElement))
        nodeElement.children.addAll(createLabels(node.labels))
        return nodeElement 
    }
    
    /**
     * Creates a Sprotty edge corresponding to the given {@link KEdge}.
     * Assumes, that the source and target nodes or ports of this {@code edge} have already been generated.
     */
    private def SKEdge generateEdge(KEdge edge) {
        // SEdges do not contain a direct reference to their source and target elements, but they contain references to
        // the unique IDs of their source and target elements.
        val fromElementId = 
            /* The source (target) of an edge can be a port or a node. Prefer the port, if available. */
            if (edge.sourcePort !== null) {
                val element = kGraphToSModelElementMap.get(edge.sourcePort)
                if (element === null) {
                    // If the element is not a part of the view model, just ignore it.
                    return null
                }
                element.id
            } else {
                val element = kGraphToSModelElementMap.get(edge.source)
                if (element === null) {
                    return null
                }
                element.id
            }
        val toElementId = 
            if (edge.targetPort !== null) {
                val element = kGraphToSModelElementMap.get(edge.targetPort)
                if (element === null) {
                    return null
                } else {
                    element.id
                }
            } else {
                val element = kGraphToSModelElementMap.get(edge.target)
                if (element === null) {
                    return null
                } else {
                    element.id
                }
            }
        val SKEdge edgeElement = configSElement(SKEdge, idGen.getId(edge))
        
        edgeElement.sourceId = fromElementId
        edgeElement.targetId = toElementId
        
        val renderings = edge.data.filter [ KRendering.isAssignableFrom(it.class)].toList
        edgeElement.data = renderings
        
        modelLabels.addAll(findTextsAndLabels(renderings))
        
        edgeElement.children.addAll(createLabels(edge.labels))

        return edgeElement
    }
    
    /**
     * Creates a Sprotty port corresponding to the given {@link KPort}.
     */
    private def SKPort generatePort(KPort port) {
        val SKPort portElement = configSElement(SKPort, idGen.getId(port))
        
        val renderings = port.data.filter [ KRendering.isAssignableFrom(it.class)].toList
        portElement.data = renderings
        
        modelLabels.addAll(findTextsAndLabels(renderings))
        
        portElement.children.addAll(createLabels(port.labels))

        return portElement
    }
    
    /**
     * Creates a Sprotty label corresponding to the given {@link KLabel}.
     * 
     * @param main Describes, if the {@code label} is an element within the model KGraph. This controls, if this label
     * should be used for the text-only version the KGraph generated by {@link #generateTextDiagram}.
     */
    private def SKLabel generateLabel(KLabel label, boolean main) {
        val SKLabel labelElement = configSElement(SKLabel, idGen.getId(label))
        labelElement.text = label.text
        
        val renderings = label.data.filter [ KRendering.isAssignableFrom(it.class)].toList
        labelElement.data = renderings
        
        if (main) {
            // remember KLabel element for later size estimation
            modelLabels.addAll(findTextsAndLabels(renderings))
        }
        return labelElement
    }

    /**
     * generates a generic {@link SModelElement} with the defaults {@code id}, {@code type} already set and the 
     * {@code children} list already initialized.
     */
    private static def <E extends SModelElement> E configSElement(Class<E> elementClass, String idStr) {
        elementClass.constructor.newInstance => [
            id = idStr
            type = findType(it)
            children = new ArrayList
        ]
    }
    
    /**
     * Finds all KText and KLabel elements within the renderings in dataList and returns them as new labels.
     * Also remembers the mapping to the KText elements from the source model in the textMapping field.
     */
    private def List<SKLabel> findTextsAndLabels(List<KGraphData> dataList) {
        val dataTexts = new ArrayList
        for (data : dataList) {
            dataTexts.addAll(findTextsAndLabels(data))
        }
        return dataTexts
    }
    
    /**
     * Finds all {@link KText} and {@link KLabel} elements within the renderings in {@code dataList} and returns them as
     * new labels. Also remembers the mapping to the KText elements from the source model in the {@code textMapping} 
     * field.
     */
    private def List<SKLabel> findTextsAndLabels(KGraphData data) {
        val dataLabels = new ArrayList
        if (data instanceof KText) {
            // create a new Label with data as its text
            val label = KGraphFactory.eINSTANCE.createKLabel()
            // KTexts in Labels have their texts stored inside their ancestor KLabel, not in the KText itself
            var container = data.eContainer
            while (container instanceof KRendering) {
                container = container.eContainer
            }
            if (container instanceof KLabel) {
                label.text = container.text
            } else {
                label.text = data.text
            }
            if (label.text === null) {
                throw new IllegalStateException("Neither the KText nor its containing KLabel have a text!")
            }
            // need to put a copy of the text inside the new label because otherwise inserting it into the label will
            // modify the eContainer feature of the Text, which should not be changed
            label.data += EcoreUtil.copy(data)
            
            // generate a new Label as if it would belong to the main model
            val sKLabel = generateLabel(label, false)
            sKLabel.id = diagramRoot.id + KGraphElementIDGenerator.ID_SEPARATOR + "texts-only" + 
                KGraphElementIDGenerator.ID_SEPARATOR + KGraphElementIDGenerator.LABEL_SEPARATOR 
                + (numLabels + randomOffset)
            textMapping.put(sKLabel.id, data)
            numLabels++
            
            dataLabels += sKLabel
        } else if (data instanceof KContainerRendering) {
            for (childData: data.children) {
                dataLabels.addAll(findTextsAndLabels(childData))
            }
        } else if (data instanceof KRenderingLibrary) {
            // TODO: add all texts in the KRenderingLibrary and don't add any from KRenderingRefs
            // I don't have an example of used texts in KRenderingLibraries yet (construct one)
        }
        return dataLabels
    }
    
    /**
     * Returns a String describing the type of the {@link SModelElement}.
     */
    private static def String findType(SModelElement element) {
        switch element {
            SNode: 'node'
            SLabel: 'label'
            SEdge: 'edge'
            SPort: 'port'
            default: 'dontknow'
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
    public static def SGraph generateTextDiagram(List<SKLabel> labels, String parentId) {
        // equivalent for the SRootElement
        val root = new SKGraph => [
            type = 'graph'
            id = parentId + KGraphElementIDGenerator.ID_SEPARATOR + "texts-only"
            children = new ArrayList
        ]
        
        root.children = new ArrayList
        root.children += labels
        return root
    }
}