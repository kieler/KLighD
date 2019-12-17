/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.constraints

import com.google.inject.Inject
import com.google.inject.Injector
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.utils.KGraphElementIDGenerator
import java.util.List
import javax.inject.Singleton
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension

/**
 * @author jet, cos, sdo
 * 
 */
@Singleton
class ConstraintsLanguageServerExtension implements ILanguageServerExtension {

    @Accessors ConstraintsLanguageClient client;

    @Inject
    KGraphDiagramState diagramState

    @Inject
    Injector injector

    override initialize(ILanguageServerAccess access) {
    }

    def setLayerConstraint(LayerConstraint lc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        setConstraint(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, uri, lc.id, lc.layer, lc.layerCons)
    }

    def setPositionConstraint(PositionConstraint pc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        setConstraint(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, uri, pc.id,
            pc.position, pc.posCons)
    }

    /**
     * Sets a layer constraint and a positional constraint that 
     * are encapsulated in an instance of StaticConstraint.
     * 
     */
    def setStaticConstraint(StaticConstraint sc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val root = getRoot(uri)
        val kNode = getKNode(uri, sc.id, root)
        val parentOfNode = kNode.parent

        // In case that the interactive mode is active, the viewContext is not null 
        // and the element is actually a KNode. Carry on.
        if (kNode !== null && parentOfNode !== null) {
            /*
             * As long as no increased pos constraint is present in the target layer
             * and no increased layer constraint is present left to the target layer
             * newLayerId === newLayer Cons && newPosCons = newPosId
             * In the other cases both values can differ.
             */
            var allNodes = parentOfNode.children
            var newLayerId = sc.layer
            val newPosId = sc.position
            val newPosCons = sc.posCons
            var newLayerCons = sc.layerCons

            val layerId = kNode.getProperty(LayeredOptions.LAYERING_LAYER_I_D)

            var targetLayerNodes = ConstraintsUtils.getNodesOfLayer(newLayerId, allNodes)
            var oldLayerNodes = ConstraintsUtils.getNodesOfLayer(layerId, allNodes)

            // Reevaluate insertion of node to target layer
            var reval = new Reevaluation(kNode)

            //TODO: As an option, look what it does
            if (reval.reevaluateAfterEmptyingALayer(kNode, newLayerCons, allNodes)) {
                newLayerCons--
            }
            // TODO: Shift reevaluation is not ready yet. 
            // reval.shiftIfNec(kNode, newLayerId, newLayerCons, newPosId, newPosCons, oldLayerNodes, targetLayerNodes,allNodes)
            reval.reevaluatePositionConstraintsAfterLayerSwap(targetLayerNodes, oldLayerNodes, kNode, newPosId)

            var changedNodes = reval.changedNodes
            changedNodes.add(new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, newPosCons))
            changedNodes.add(new ConstraintProperty(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, newLayerCons))
            // Update source code of the model
            val resource = ConstraintsUtils.getResourceFromUri(uri, injector)
            for (entry : changedNodes) {
                updateSourceCode(resource, entry)
            }
            resource.save(emptyMap)

        }
    }

    def deleteStaticConstraint(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val resource = ConstraintsUtils.getResourceFromUri(uri, injector)
            updateSourceCode(resource, new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, null))
            updateSourceCode(resource, new ConstraintProperty(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, null))
            resource.save(emptyMap)

        }
    }

    def deletePositionConstraint(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val resource = ConstraintsUtils.getResourceFromUri(uri, injector)
            updateSourceCode(resource, new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, null))
            resource.save(emptyMap)
        }
    }

    def deleteLayerConstraint(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val resource = ConstraintsUtils.getResourceFromUri(uri, injector)
            updateSourceCode(resource, new ConstraintProperty(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, null))
            resource.save(emptyMap)
        }
    }

    /**
     * Sets a layer or position constraint with a chosen {@code value} on the node 
     * that is specified by the {@code targetID}.
     * 
     * @param PropID the type of constraint that should be set (LayerConstraint or PositionConstraint) 
     * The IProperty class is expected.
     * @param uri The uri of the diagram/file.
     * @param targetID The id of the node on which the constraint should be set.
     * @param value Either the id of the position or the id of the layer.
     */
    private def setConstraint(IProperty<Integer> property, String uri, String targetID, int valueId, int valueCons) {
        val root = getRoot(uri)
        val kNode = getKNode(uri, targetID, root)
        val parentOfNode = kNode.parent

        if (kNode !== null && parentOfNode !== null) {
            var layerID = kNode.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
            var List<KNode> residingLayer
            residingLayer = ConstraintsUtils.getNodesOfLayer(layerID, parentOfNode.children)

            var reval = new Reevaluation(kNode)
            switch (property) {
                case LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT:
                    reval.reevaluatePositionConstraintsAfterPosChangeInLayer(residingLayer, kNode, valueId)
            }
            
            var changedNodes = reval.changedNodes
            changedNodes.add(new ConstraintProperty(kNode, property, valueCons))
            val resource = ConstraintsUtils.getResourceFromUri(uri, injector)
            for (entry : changedNodes) {
                updateSourceCode(resource, entry)
            }
        }
    }

    /**
     * Returns the {@code KNode of the node described by {@code ID}.
     * Returns null if the {@code ViewContext} of the resource described by {@code uri} is null.
     * Returns null if the element behind the ID is no kNode.
     * Returns null if the {@code INTERACTIVE_LAYOUT} IProperty is not set on the root of the resource.
     */
    private def getKNode(String uri, String ID, KNode root) {

        if (root?.getProperty(LayeredOptions.INTERACTIVE_LAYOUT)) {
            val mapKToS = diagramState.getKGraphToSModelElementMap(uri)

            // KGraphElement which corresponding SNode has the correct ID
            val kGraphElement = KGraphElementIDGenerator.findElementById(mapKToS, ID)

            if (kGraphElement instanceof KNode) {
                return kGraphElement as KNode
            } else {
                return null
            }
        }

    }

    /**
     * Returns the {@code KNode} of the node described by {@code ID}.
     * Returns null if the {@code ViewContext} of the resource described by {@code uri} is null.
     * Returns null if the element behind the ID is no kNode.
     * Returns null if the {@code INTERACTIVE_LAYOUT} IProperty is not set on the root of the resource.
     * This version of getKNode retrieves the root itself. If you already have retrieved the root, 
     * then you should use the other variant.
     * @param uri The resource's uri
     * @param ID The Id of the requested KNode
     */
    private def getKNode(String uri, String ID) {
        val root = getRoot(uri)
        return getKNode(uri, ID, root)
    }

    /**
     * Returns the root node of the resource's model behind a given {@cods uri}.
     * 
     * @param uri The uri that points at the desired resource.
     * @return The root node of the resource's model
     */
    private def getRoot(String uri) {

        var ViewContext viewContext = null
        synchronized (diagramState) {
            viewContext = diagramState.getKGraphContext(uri)
        }

        return viewContext?.viewModel

    }
    
    /**
     * Searches for node specified by ConstraintProperty and sets the desired property
     */
    private def updateSourceCode(Resource resource, ConstraintProperty entry) {
        val elkNode = entry.KNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as ElkNode
        
        val resourceElement = (resource.contents.head as ElkNode).children.filter[child |
            return child.identifier == elkNode.identifier
        ].head
        resourceElement.setProperty(entry.property, entry.value)
        resource.save(emptyMap())
    }
}
