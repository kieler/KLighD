/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
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
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.utils.KGraphElementIDGenerator
import java.util.List
import javax.inject.Singleton
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension

/**
 * @author jet, cos
 * 
 */
@Singleton
class ConstraintsLanguageServerExtension implements ILanguageServerExtension, ConstraintsCommandExtension {

    @Accessors ConstraintsLanguageClient client;

    @Inject
    KGraphDiagramState diagramState

    @Inject
    Injector injector

    override initialize(ILanguageServerAccess access) {
    }

    override setLayerConstraint(LayerConstraint lc) {
        setConstraint(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, lc.uri, lc.ID, lc.layer, lc.layerCons)
    }

    override setPositionConstraint(PositionConstraint pc) {
        setConstraint(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, pc.uri, pc.ID,
            pc.position, pc.posCons)
    }

    override deleteStaticConstraint(DeleteConstraint dc) {
        val uri = dc.uri
        val kNode = getKNode(uri, dc.ID)
        if (kNode !== null) {
            ConstraintsUtils.nullifyPosConstraint(kNode)
            ConstraintsUtils.nullifyLayerConstraint(kNode)
            updateSourceCode(kNode, uri)

        }
    }

    override deletePositionConstraint(DeleteConstraint dc) {
        val uri = dc.uri
        val kNode = getKNode(uri, dc.ID)
        if (kNode !== null) {
            ConstraintsUtils.nullifyPosConstraint(kNode)
            updateSourceCode(
                kNode,
                LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT,
                null,
                uri
            )

        }
    }

    override deleteLayerConstraint(DeleteConstraint dc) {
        val uri = dc.uri
        val kNode = getKNode(uri, dc.ID)
        if (kNode !== null) {
            ConstraintsUtils.nullifyLayerConstraint(kNode)
            updateSourceCode(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, null, uri)
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
    private def setConstraint(IProperty<Integer> PropID, String uri, String targetID, int valueId, int valueCons) {
        val root = getRoot(uri)
        val kNode = getKNode(uri, targetID, root)
        val parentOfNode = kNode.parent

        if (kNode !== null && parentOfNode !== null) {
            var layerID = kNode.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
            var List<KNode> residingLayer
            residingLayer = ConstraintsUtils.getNodesOfLayer(layerID, parentOfNode.children)

            var reval = new Reevaluation(kNode)
            switch (PropID) {
                case LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT:
                    reval.reevaluatePosConstraintsAfterPosChangeInLayer(residingLayer, kNode, valueId)
            }
            kNode.setProperty(PropID, valueCons)

            var changes = reval.changedNodes
            changes.add(kNode)
            updateSourceCode(changes.toList, uri)
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
            val kGEle = KGraphElementIDGenerator.findElementById(mapKToS, ID)

            if (kGEle instanceof KNode) {
                return kGEle as KNode
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
     * Updates the source code of the elk model that is in the resource of {@code uri}.
     */
    private def updateSourceCode(KNode kNode, IProperty<Integer> PropID, Integer value, String uri) {
        // set Property of corresponding elkNode 
        val elkNode = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)

        if (elkNode instanceof ElkNode) {
            // elkNode.setProperty(PropID, value)
            ConstraintsUtils.copyConstraintProp(elkNode, kNode, PropID)
            refreshModelInEditor(ConstraintsUtils.getRootNodeOf(elkNode), uri)

        }
    }

    /**
     * Updates the source code of the elk model that is in the resource of {@code uri}.
     */
    private def updateSourceCode(KNode kNode, String uri) {
        // set Property of corresponding elkNode 
        val elkNode = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)

        if (elkNode instanceof ElkNode) {
            ConstraintsUtils.copyAllConstraints(elkNode, kNode)

            refreshModelInEditor(ConstraintsUtils.getRootNodeOf(elkNode), uri)

        }
    }

    /**
     * Updates the source code of the elk model that is in the resource of {@code uri}.
     */
    private def updateSourceCode(List<KNode> kNodes, String uri) {

        for (kNode : kNodes) {
            // set Property of corresponding elkNode 
            val elkNode = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)

            if (elkNode instanceof ElkNode) {
                ConstraintsUtils.copyAllConstraints(elkNode, kNode)
            }
        }

        val n = kNodes.head.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        if (n instanceof ElkNode) {
            refreshModelInEditor(ConstraintsUtils.getRootNodeOf(n), uri)
        }

    }

    /** 
     * Takes an updated Elk model, clears the resource and saves the updated model in the resource.
     * This also refreshes the model that is shown in KEITH/KIELER.
     * 
     * @param newModel the (updated) Elk model that should be shown in the editor of KEITH/KIELER
     * @param resourceUri the uri of the model's resource
     */
    private def refreshModelInEditor(ElkNode newModel, String resourceUri) {
        val resource = ConstraintsUtils.getResourceFromUri(resourceUri, injector)
        // Delete the old model
        resource.contents.clear
        // Store the new model
        resource.contents += newModel
        // Serialize it into the file without any additional options
        resource.save(emptyMap())
    }

    /**
     * Sets a layer constraint and a positional constraint that 
     * are encapsulated in an instance of StaticConstraint.
     * 
     */
    override setStaticConstraint(StaticConstraint sc) {
        val uri = sc.uri
        val root = getRoot(uri)
        val kNode = getKNode(uri, sc.ID, root)
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
            val newLayerCons = sc.layerCons

            val layerId = kNode.getProperty(LayeredOptions.LAYERING_LAYER_I_D)

            var targetLayerNodes = ConstraintsUtils.getNodesOfLayer(newLayerId, allNodes)
            var oldLayerNodes = ConstraintsUtils.getNodesOfLayer(layerId, allNodes)

            // Reevaluate insertion of node to target layer
            var reval = new Reevaluation(kNode)

//TODO: Should this be active now or not? Probably not. It should be an option.
//            if (reval.reevaluateAfterEmptyingALayer(kNode, layerCons, allNodes)) {
//                layerCons--
//            }
            //TODO: Shift reevaluation is not ready yet. 
            //reval.shiftIfNec(kNode, newLayerId, newLayerCons, newPosId, newPosCons, oldLayerNodes, targetLayerNodes,allNodes)
            reval.reevaluatePosConstraintsAfterLayerSwap(targetLayerNodes, oldLayerNodes, kNode, newPosId)

            ConstraintsUtils.setLayerConstraint(kNode, newLayerCons)
            ConstraintsUtils.setPosConstraint(kNode, newPosCons)

            var ns = reval.changedNodes
            ns.add(kNode)
            // Update source code of the model
            updateSourceCode(ns.toList, uri)

        }
    }

    @Inject KGraphLanguageServerExtension kGraphLanguageServerExt

    override refreshLayout(String uri) {
        kGraphLanguageServerExt.updateLayout(uri)
    }

}
