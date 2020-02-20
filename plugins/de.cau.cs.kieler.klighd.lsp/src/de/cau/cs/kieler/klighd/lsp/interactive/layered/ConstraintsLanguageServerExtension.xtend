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
package de.cau.cs.kieler.klighd.lsp.interactive.layered

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
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveUtil
import de.cau.cs.kieler.klighd.lsp.interactive.ConstraintProperty

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
        val kNode = LSPUtil.getKNode(diagramState, uri, sc.id)
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

            var targetLayerNodes = InteractiveUtil.getNodesOfLayer(newLayerId, allNodes)
            var oldLayerNodes = InteractiveUtil.getNodesOfLayer(layerId, allNodes)

            // Reevaluate insertion of node to target layer
            var reval = new LayeredConstraintReevaluation(kNode)

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
            refreshModelInEditor(changedNodes, uri)

        }
    }

    def deleteStaticConstraint(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val changedNodes = #[new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, null),
                new ConstraintProperty(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, null)]
            refreshModelInEditor(changedNodes, uri)

        }
    }

    def deletePositionConstraint(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val changedNodes = #[new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, null)]
            refreshModelInEditor(changedNodes, uri)
        }
    }

    def deleteLayerConstraint(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val changedNodes = #[new ConstraintProperty(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, null)]
            refreshModelInEditor(changedNodes, uri)
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
        val kNode = LSPUtil.getKNode(diagramState, uri, targetID)
        val parentOfNode = kNode.parent

        if (kNode !== null && parentOfNode !== null) {
            var layerID = kNode.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
            var List<KNode> residingLayer
            residingLayer = InteractiveUtil.getNodesOfLayer(layerID, parentOfNode.children)

            var reval = new LayeredConstraintReevaluation(kNode)
            switch (property) {
                case LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT:
                    reval.reevaluatePositionConstraintsAfterPosChangeInLayer(residingLayer, kNode, valueId)
            }
            
            var changedNodes = reval.changedNodes
            changedNodes.add(new ConstraintProperty(kNode, property, valueCons))
            refreshModelInEditor(changedNodes, uri)
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
        return LSPUtil.getKNode(diagramState, uri, ID)
    }
    
    /**
     * Changes property changes defined by changedNodes to the resource
     * @param changedNodes list of all changes to nodes
     * @param uri uri of resource
     * TODO use some kind of updateDocument mechanism and do not just modify the contents of the resource directly
     */
    def refreshModelInEditor(List<ConstraintProperty> changedNodes, String uri) {
        val resource = InteractiveUtil.getResourceFromUri(uri, injector)
            
        for (entry : changedNodes) {
            // set Property of corresponding elkNode 
            val kNode = entry.KNode
            val elkNode = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
            
            if (elkNode instanceof ElkNode) {
                kNode.setProperty(entry.property, entry.value)
                InteractiveUtil.copyAllConstraints(elkNode, kNode)
            }
        }

        val elkNode = changedNodes.head.KNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        if (elkNode instanceof ElkNode) {
            val elkGraph = InteractiveUtil.getRootNodeOf(elkNode)
            resource.contents.clear
            resource.contents += elkGraph
            resource.save(emptyMap)
        }

    }
}
