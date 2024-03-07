/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019-2022 by
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
package de.cau.cs.kieler.klighd.lsp.interactive.layered

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import com.google.inject.Singleton
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import de.cau.cs.kieler.klighd.lsp.interactive.ConstraintProperty
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveUtil
import java.util.LinkedList
import java.util.List
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension

/**
 * Language server extension to change the layered algorithm in the interactive mode.
 * 
 * @author jep, cos, sdo
 */
@Singleton
class LayeredInteractiveLanguageServerExtension implements ILanguageServerExtension {

    @Accessors KGraphLanguageClient client;

    @Inject
    KGraphDiagramState diagramState
    
    @Inject
    KGraphLanguageServerExtension languageServer

    override initialize(ILanguageServerAccess access) {
        // Not implemented, since it is not needed.
    }

    /**
     * Sets a 'in layer predecessor'-constraint.
     * 
     * @param constraint The constraint.
     * @param clientId The client id.
     */
    def setInLayerPredecessorOfConstraint(InLayerPredecessorOfConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        setRelativeConstraint(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF, uri, constraint.id,
            constraint.getReferencedNode, clientId)
    }
    
    /**
     * Sets a 'in layer successor'-constraint.
     * 
     * @param constraint The constraint.
     * @param clientId The client id.
     */
    def setInLayerSuccessorOfConstraint(InLayerSuccessorOfConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        setRelativeConstraint(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF, uri, constraint.id,
            constraint.getReferencedNode, clientId)
    }
    
    /**
     * Delete relative constraints.
     * 
     * @param constraint The constraint to delete.
     * @param clientId The client id.
     */
    def deleteRelativeConstraints(DeleteConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, constraint.id)
        if (kNode !== null) {
            val changedNodes = new LinkedList<ConstraintProperty<Object>>
            changedNodes.add(new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF, null))
            changedNodes.add(new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF, null))
            refreshModelInEditor(changedNodes, KGraphUtil.getRootNodeOf(kNode), uri)
        }
    }
    
    /**
     * Delete in-layer-predecessor-of constraints.
     * 
     * @param constraint The constraint to delete.
     * @param clientId The client id.
     */
    def deleteInLayerPredecessorOfConstraint(DeleteConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, constraint.id)
        if (kNode !== null) {
            val changedNodes = new LinkedList<ConstraintProperty<Object>>
            changedNodes.add(new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF, null))
            refreshModelInEditor(changedNodes, KGraphUtil.getRootNodeOf(kNode), uri)
        }
    }
    
    /**
     * Delete in-layer-successor-of constraints.
     * 
     * @param constraint The constraint to delete.
     * @param clientId The client id.
     */
    def deleteILSuccOfConstraint(DeleteConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, constraint.id)
        if (kNode !== null) {
            val changedNodes = new LinkedList<ConstraintProperty<Object>>
            changedNodes.add(new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF, null))
            refreshModelInEditor(changedNodes, KGraphUtil.getRootNodeOf(kNode), uri)
        }
    }
    
    /**
     * Sets a relative constraint with a chosen {@code value} on the node that is specified by the {@code targetId}.
     * 
     * @param property The IProperty class of constraint that should be set.
     * @param uri The uri of the diagram/file.
     * @param targetId The id of the node on which the constraint should be set.
     * @param referencedId The id of the node to which the relation should be set.
     * @param clientId The client id.
     */
    private def setRelativeConstraint(
        IProperty<String> property,
        String uri,
        String targetId,
        String referencedId,
        String clientId
    ) {
        val targetNode = LSPUtil.getKNode(diagramState, uri, targetId)
        val parentOfNode = targetNode.parent

        // Get the actual label of the node
        val referencedNode = LSPUtil.getKNode(diagramState, uri, referencedId)

        var nameStringOfReferenceNode = targetNode.toString
        val id = referencedNode.getData(KIdentifier)
        if (id !== null) {
            nameStringOfReferenceNode = id.id
        }

        if (targetNode !== null && parentOfNode !== null) {
            var referenceLayer = referencedNode.getProperty(LayeredOptions.LAYERING_LAYER_ID);
            var targetPosition = referencedNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID);

            // Update target position depending on the constraint that should be set.
            if (referenceLayer === targetNode.getProperty(LayeredOptions.LAYERING_LAYER_ID) &&
                targetPosition > targetNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)) {
                targetPosition--;
            }
            if (property === LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF) {
                targetPosition++;
            }

            var layerNodes = InteractiveUtil.getNodesOfLayer(referenceLayer, parentOfNode.children)
            var oldLayerNodes = InteractiveUtil.getNodesOfLayer(
                targetNode.getProperty(LayeredOptions.LAYERING_LAYER_ID), parentOfNode.children)

            // Update position constraints.
            val layerSwap = referenceLayer !== targetNode.getProperty(LayeredOptions.LAYERING_LAYER_ID)
            var relativeConstraintReevaluation = new RelativeConstraintReevaluation(targetNode)
            var absoluteConstraintReevaluation = new LayeredConstraintReevaluation(targetNode)

            targetNode.setProperty(property, null);
            var List<KNode> chain = InteractiveUtil.getChain(targetNode, oldLayerNodes)

            if (layerSwap) {
                absoluteConstraintReevaluation.
                    reevaluatePositionConstraintsAfterLayerSwap(layerNodes, oldLayerNodes, targetNode, targetPosition)
                absoluteConstraintReevaluation.reevaluateLayerConstraintsInChain(referenceLayer, chain)
            } else {
                if (targetPosition !== targetNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID) &&
                    chain.contains(referencedNode) &&
                    targetPosition >= chain.get(0).getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID) &&
                    targetPosition <=
                        chain.get(chain.size - 1).getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)) {
                    // Node is moved within its chain.
                    relativeConstraintReevaluation.reevaluateRelativeConstraintAfterSwapInChain(targetNode, oldLayerNodes)
                }
                absoluteConstraintReevaluation.
                    reevaluatePositionConstraintsAfterPositionChangeInLayer(layerNodes, targetNode, targetPosition)
            }

            var posCons = targetPosition
            if (layerSwap ||
                targetPosition < targetNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)) {
                // Position ids must be increased by the number of predecessors.
                posCons = targetPosition + chain.indexOf(targetNode)
            }

            absoluteConstraintReevaluation.reevaluatePositionConstraintInChain(targetNode, posCons, chain)

            // Update relative constraints.
            if (layerSwap ||
                targetPosition !== targetNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)) {
                relativeConstraintReevaluation.checkRelativeConstraints(targetNode, targetPosition, layerNodes, oldLayerNodes,
                    property)
                relativeConstraintReevaluation.reevaluateRelativeConstraints(targetNode, targetPosition, layerNodes, oldLayerNodes)
            }
            val changedNodes = absoluteConstraintReevaluation.changedNodes
            changedNodes.addAll(relativeConstraintReevaluation.changedNodes)
            changedNodes.add(new ConstraintProperty(targetNode, property, nameStringOfReferenceNode))
            if (layerSwap) {
                // Already apply changes to be able to correctly identify if a chain is split because of the new
                // relative constraint.
                changedNodes.forEach[constraint|
                    val KNode kNode = constraint.KNode
                    kNode.setProperty(constraint.property, constraint.value)
                ]
                absoluteConstraintReevaluation.reevaluateAfterEmptyingALayer(targetNode, referenceLayer, parentOfNode.children)
            }
            refreshModelInEditor(changedNodes, KGraphUtil.getRootNodeOf(targetNode), uri)
        }
    }

    /**
     * Sets a layer constraint.
     * 
     * @param constraint The layer constraint.
     * @param clientId The client id.
     */
    def setLayerConstraint(LayerConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        setConstraint(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, uri, constraint.id, constraint.layer,
            constraint.getLayerConstraint, clientId)
    }

    /**
     * Sets a position constraint.
     * 
     * @param constraint The position constraint.
     * @param clientId The client id.
     */
    def setPositionConstraint(PositionConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        setConstraint(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, uri, constraint.id,
            constraint.position, constraint.getPositionConstraint, clientId)
    }

    /**
     * Sets a layer constraint and a positional constraint that 
     * are encapsulated in an instance of StaticConstraint.
     * 
     * @param constraint The constraint.
     * @param clientId The client id.
     */
    def setStaticConstraint(StaticConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, constraint.id)
        val parentOfNode = kNode.parent

        // In case that the interactive mode is active, the viewContext is not null 
        // and the element is actually a KNode. Carry on.
        if (kNode !== null && parentOfNode !== null) {
            // As long as no increased position constraint is present in the target layer
            // and no increased layer constraint is present left to the target layer
            // newLayerId === newLayerConstraint && newPosCons = newPosId
            // In the other cases both values can differ.
            var allNodes = parentOfNode.children
            var newLayerId = constraint.layer
            var newLayerConstraint = constraint.getLayerConstraint
            val List<ConstraintProperty<Object>> changedNodes = newLinkedList;
            // If layerId is -1 all other nodes need to have their layerId and layerChoiceId increased.
            if (newLayerConstraint == -1) {
                newLayerId++
                newLayerConstraint++
                allNodes.forEach[node |
                    if (node.hasProperty(LayeredOptions.LAYERING_LAYER_ID)) {
                        node.setProperty(LayeredOptions.LAYERING_LAYER_ID,
                            node.getProperty(LayeredOptions.LAYERING_LAYER_ID) + 1
                        )
                    }
                    if (node != kNode && node.hasProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)) { 
                        node.setProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT,
                            node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) + 1
                        )
                        changedNodes.add(new ConstraintProperty(node, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT,
                            node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)
                        ))
                    }
                ]
            }
            
            val layerId = kNode.getProperty(LayeredOptions.LAYERING_LAYER_ID)

            var targetLayerNodes = InteractiveUtil.getNodesOfLayer(newLayerId, allNodes)
            var oldLayerNodes = InteractiveUtil.getNodesOfLayer(layerId, allNodes)
            
            val chain = InteractiveUtil.getChain(kNode, oldLayerNodes)
            // posID must be increased by the number of predecessors
            val newPositionId = constraint.position + chain.indexOf(kNode)
            val newPositionConstraint = constraint.getPositionConstraint + chain.indexOf(kNode)
            
            // Reevaluate insertion of node to target layer
            var absoluteConstraintReevaluation = new LayeredConstraintReevaluation(kNode)

            if (absoluteConstraintReevaluation.reevaluateAfterEmptyingALayer(kNode, newLayerConstraint, allNodes)) {
                newLayerConstraint--
            }
            absoluteConstraintReevaluation.
                reevaluatePositionConstraintsAfterLayerSwap(targetLayerNodes, oldLayerNodes, kNode, newPositionId)
            absoluteConstraintReevaluation.reevaluateLayerConstraintsInChain(newLayerConstraint, chain)
            absoluteConstraintReevaluation.reevaluatePositionConstraintInChain(kNode, newPositionConstraint, chain)

            changedNodes.addAll(absoluteConstraintReevaluation.changedNodes)
            changedNodes.add(
                new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT,
                    newPositionConstraint))
            changedNodes.add(
                new ConstraintProperty(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, newLayerConstraint))
            
            // Update relative constraints.
            var relativeCOnstraintReevaluation = new RelativeConstraintReevaluation(kNode)
            relativeCOnstraintReevaluation.reevaluateRelativeConstraints(kNode, newPositionId, targetLayerNodes, oldLayerNodes)
            changedNodes.addAll(relativeCOnstraintReevaluation.changedNodes)
            // Update source code of the model.
            refreshModelInEditor(changedNodes, KGraphUtil.getRootNodeOf(kNode), uri)
        }
    }

    /**
     * Delete a constraint.
     * 
     * @param constraint The constraint to delete.
     * @param clientId The client id.
     */
    def deleteStaticConstraint(DeleteConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, constraint.id)
        if (kNode !== null) {
            val changedNodes = new LinkedList<ConstraintProperty<Object>>
            changedNodes.add(
                new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, null))
            changedNodes.add(new ConstraintProperty(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, null))
            refreshModelInEditor(changedNodes, KGraphUtil.getRootNodeOf(kNode), uri)
        }
    }

    /**
     * Delete a position constraint.
     * 
     * @param constraint The position constraint to delete.
     * @param clientId The client id.
     */
    def deletePositionConstraint(DeleteConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, constraint.id)
        if (kNode !== null) {
            val changedNodes = new LinkedList<ConstraintProperty<Object>>
            changedNodes.add(new ConstraintProperty(
                kNode,
                LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT,
                null
            ))
            refreshModelInEditor(changedNodes, KGraphUtil.getRootNodeOf(kNode), uri)
        }
    }

    /**
     * Delete a layer constraint.
     * 
     * @param constraint The layer constraint to delete.
     * @param clientId The client id.
     */
    def deleteLayerConstraint(DeleteConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, constraint.id)
        if (kNode !== null) {
            val changedNodes = new LinkedList<ConstraintProperty<Object>>
            changedNodes.add(new ConstraintProperty(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, null))
            refreshModelInEditor(changedNodes, KGraphUtil.getRootNodeOf(kNode), uri)
        }
    }

    /**
     * Sets a layer or position constraint with a chosen {@code value} on the node 
     * that is specified by the {@code targetID}.
     * 
     * @param property The IProperty of constraint that should be set (LayerConstraint or PositionConstraint).
     * @param uri The uri of the diagram/file.
     * @param targetId The id of the node on which the constraint should be set.
     * @param oldValue Either the id of the position or the id of the layer.
     * @param newValue Either the value if the position constraint or the layer constraint.
     * @param cliendId The client id.
     */
    private def setConstraint(IProperty<Integer> property, String uri, String targetId, int oldValue, int newValue,
        String clientId
    ) {
        val kNode = LSPUtil.getKNode(diagramState, uri, targetId)
        val parentOfNode = kNode.parent

        if (kNode !== null && parentOfNode !== null) {
            var layerId = kNode.getProperty(LayeredOptions.LAYERING_LAYER_ID)
            var positionId = kNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT);
            if (property === LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) {
                layerId = oldValue
            }
            var newValueConstraint = newValue
            var newValueId = oldValue
            
            val List<ConstraintProperty<Object>> changedNodes = newLinkedList;
            // If layerId is -1 all other nodes need to have their layerId and layerChoiceId increased.
            if (oldValue == -1) {
                layerId++
                newValueId++
                newValueConstraint++
                parentOfNode.children.forEach[node |
                    if (node.hasProperty(LayeredOptions.LAYERING_LAYER_ID)) {
                        node.setProperty(LayeredOptions.LAYERING_LAYER_ID,
                            node.getProperty(LayeredOptions.LAYERING_LAYER_ID) + 1
                        )
                    }
                    if (node != kNode && node.hasProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)) { 
                        node.setProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT,
                            node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) + 1
                        )
                        changedNodes.add(new ConstraintProperty(node, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT,
                            node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)
                        ))
                    }
                ]
            }
            
            var List<KNode> layerNodes = InteractiveUtil.getNodesOfLayer(layerId, parentOfNode.children)
            val oldLayerNodes = InteractiveUtil.getNodesOfLayer(kNode.getProperty(LayeredOptions.LAYERING_LAYER_ID),
                parentOfNode.children
            )

            val oldPosition = kNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
            
            var relativeConstraintReevaluation = new RelativeConstraintReevaluation(kNode)
            val chain = InteractiveUtil.getChain(kNode, oldLayerNodes)
            if (property === LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT) {
                positionId = oldValue
                if (positionId != -1 &&
                    positionId >= chain.get(0).getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID) &&
                    positionId <= chain.get(chain.size - 1).getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)) {
                    // node is moved within its chain
                    relativeConstraintReevaluation.reevaluateRelativeConstraintAfterSwapInChain(kNode, oldLayerNodes)
                } else if (positionId < oldPosition) {
                    // posID must be increased by the number of predecessors
                    newValueConstraint += chain.indexOf(kNode)
                    newValueId += chain.indexOf(kNode)
                }
            }
        
            var absoluteConstraintReevalution = new LayeredConstraintReevaluation(kNode)
            switch (property) {
                case LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT: {
                    absoluteConstraintReevalution.reevaluatePositionConstraintsAfterPositionChangeInLayer(layerNodes, kNode, newValueId)
                    absoluteConstraintReevalution.reevaluatePositionConstraintInChain(kNode, newValueConstraint, chain)
                }
                case LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT: {
                    absoluteConstraintReevalution.reevaluateLayerConstraintsInChain(layerId, chain)
                    if (absoluteConstraintReevalution.reevaluateAfterEmptyingALayer(kNode, newValueConstraint, parentOfNode.children)) {
                        newValueConstraint--
                    }
                }
            }
            
            changedNodes.addAll(absoluteConstraintReevalution.changedNodes)
            changedNodes.add(new ConstraintProperty(kNode, property, newValueConstraint))
            
            // Update relative constraints.
            if (positionId !== null) {
                relativeConstraintReevaluation.reevaluateRelativeConstraints(kNode, positionId, layerNodes, oldLayerNodes)
                changedNodes.addAll(relativeConstraintReevaluation.changedNodes)
            };
            refreshModelInEditor(changedNodes, KGraphUtil.getRootNodeOf(kNode), uri)
        }
    }

    /**
     * Returns the {@code KNode} of the node described by {@code ID}.
     * Returns null if the {@code ViewContext} of the resource described by {@code uri} is null.
     * Returns null if the element behind the ID is no kNode.
     * Returns null if the {@code INTERACTIVE_LAYOUT} IProperty is not set on the root of the resource.
     * This version of getKNode retrieves the root itself. If you already have retrieved the root, 
     * then you should use the other variant.
     * 
     * @param uri The resource's uri.
     * @param nodeId The Id of the requested KNode.
     * @return The requested node.
     */
    private def getKNode(String uri, String nodeId) {
        return LSPUtil.getKNode(diagramState, uri, nodeId)
    }
    
    /**
     * Sends request to the client to update the file according to the property changes.
     * 
     * @param changedNodes The list of all changes to nodes.
     * @param model The main kNode.
     * @param uri The uri of resource.
     */
    def refreshModelInEditor(List<ConstraintProperty<Object>> changedNodes, KNode model, String uri) {
        changedNodes.forEach[constraint|
            val KNode kNode = constraint.KNode
            kNode.setProperty(constraint.property, constraint.value)
        ]
        InteractiveUtil.serializeConstraints(changedNodes, model, uri, this.languageServer, this.client)
        return
    }
}
