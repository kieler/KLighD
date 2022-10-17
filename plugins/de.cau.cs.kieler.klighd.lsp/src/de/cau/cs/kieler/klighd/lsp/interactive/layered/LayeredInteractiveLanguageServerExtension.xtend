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
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import de.cau.cs.kieler.klighd.lsp.interactive.ConstraintProperty
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveUtil
import java.io.ByteArrayOutputStream
import java.util.HashMap
import java.util.List
import java.util.Map
import javax.inject.Singleton
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.lsp4j.Position
import org.eclipse.lsp4j.Range
import org.eclipse.lsp4j.TextEdit
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.interactive.RelativeConstraintProperty
//import de.cau.cs.kieler.sccharts.impl.StateImpl

/**
 * Language server extension to change the layered algorithm in the interactive mode.
 * 
 * @author jet, cos, sdo
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
     * @param cons the constraint
     * @param clientId the client id
     */
    def setILPredOfConstraint(ILPredOfConstraint cons, String clientId) {
        val uri = diagramState.getURIString(clientId)
        setRelativeConstraint(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF, uri, cons.id,
            cons.otherNode)
    }
    
    /**
     * Sets a 'in layer successor'-constraint.
     * @param cons the constraint
     * @param clientId the client id
     */
    def setILSuccOfConstraint(ILSuccOfConstraint cons, String clientId) {
        val uri = diagramState.getURIString(clientId)
        setRelativeConstraint(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF, uri, cons.id,
            cons.otherNode)
    }
    
    /**
     * Delete relative constraints.
     * @param dc the constraint to delete
     * @param clientId the client id
     */
    def deleteRelativeConstraints(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val changedNodes = newHashMap()
            changedNodes.put(new RelativeConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF), null)
            changedNodes.put(new RelativeConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF), null)
            refreshModelInEditorForRC(changedNodes, uri)
        }
    }
    
    /**
     * Delete iLPredOf constraints.
     * @param dc the constraint to delete
     * @param clientId the client id
     */
    def deleteILPredOfConstraint(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val changedNodes = newHashMap()
            changedNodes.put(new RelativeConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF), null)
            refreshModelInEditorForRC(changedNodes, uri)
        }
    }
    
    /**
     * Delete iLSuccOf constraints.
     * @param dc the constraint to delete
     * @param clientId the client id
     */
    def deleteILSuccOfConstraint(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val changedNodes = newHashMap()
            changedNodes.put(new RelativeConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF), null)
            refreshModelInEditorForRC(changedNodes, uri)
        }
    }
    
    /**
     * sets a relative constraint with a chosen {@code value} on the node that is specified by the {@code targetID}.
     * 
     * @param PropID the type of constraint that should be set (ILPredOfConstraint or ILSuccOfConstraint) 
     * The IProperty class is expected.
     * @param uri The uri of the diagram/file.
     * @param targetId The id of the node on which the constraint should be set.
     * @param node the id of the node to which the relation should be set.
     */
    private def setRelativeConstraint(IProperty<String> property, String uri, String targetId, String node) {
        val kNode = LSPUtil.getKNode(diagramState, uri, targetId)
        val parentOfNode = kNode.parent
        
        // get the actual label of the node
        val otherNode = LSPUtil.getKNode(diagramState, uri, node)
        
        val test = otherNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        var value = ""
        // FIXME
//        if (test instanceof StateImpl) {
//            value = test.name
//        } else
        if (test instanceof ElkNode) {
            value = otherNode.labels.get(0).text
        }
        
        if (kNode !== null && parentOfNode !== null) {
            var layerID = otherNode.getProperty(LayeredOptions.LAYERING_LAYER_ID);
            var posID = otherNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID);
            if (layerID === kNode.getProperty(LayeredOptions.LAYERING_LAYER_ID)
                && posID > kNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)) {
                posID--;
            }
            if (property === LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF) {
                posID++;
            }
            var layerNodes = InteractiveUtil.getNodesOfLayer(layerID, parentOfNode.children)
            var oldLayerNodes = InteractiveUtil.getNodesOfLayer(kNode.getProperty(LayeredOptions.LAYERING_LAYER_ID), parentOfNode.children)
            
            // update position constraints
            val layerSwap = layerID !== kNode.getProperty(LayeredOptions.LAYERING_LAYER_ID)
            var relReval = new RelativeConstraintReevaluation(kNode)
            var reval = new LayeredConstraintReevaluation(kNode)
                        
            kNode.setProperty(property, null);
            var List<KNode> chain = InteractiveUtil.getChain(kNode, oldLayerNodes)
            
            if (layerSwap) {
                reval.reevaluatePositionConstraintsAfterLayerSwap(layerNodes, oldLayerNodes, kNode, posID)
                reval.reevaluateLayerConstraintsInChain(layerID, chain)
            } else {
                if (posID !== kNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID) 
                    && chain.contains(otherNode)
                    && posID >= chain.get(0).getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
                    && posID <= chain.get(chain.size - 1).getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)) {
                    // node is moved within its chain
                    relReval.reevaluateRCAfterSwapInChain(kNode, oldLayerNodes)   
                }
                reval.reevaluatePositionConstraintsAfterPosChangeInLayer(layerNodes, kNode, posID)
            }
            
            var posCons = posID
            if (layerSwap || posID < kNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)) {
                // posID must be increased by the number of predecessors
                posCons = posID + chain.indexOf(kNode)
            }

            reval.reevaluatePosConsInChain(kNode, posCons, chain)
            if (!reval.changedNodes.isEmpty) {
                refreshModelInEditor(reval.changedNodes, uri)
            }
            
            // update relative constraints
            if (layerSwap || posID !== kNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)) {
                relReval.checkRelCons(kNode, posID, layerNodes, oldLayerNodes, property)
                relReval.reevaluateRelCons(kNode, posID, layerNodes, oldLayerNodes)
            }
            val relChangedNodes = relReval.changedNodes
            relChangedNodes.put(new RelativeConstraintProperty(kNode, property), value)
            refreshModelInEditorForRC(relChangedNodes, uri)
        }
    }

    /**
     * Sets a layer constraint.
     * @param lc the layer constraint
     * @param clientId the client id
     */
    def setLayerConstraint(LayerConstraint lc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        setConstraint(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, uri, lc.id, lc.layer, lc.layerCons)
    }

    /**
     * Sets a position constraint.
     * @param pc the position constraint
     * @param clientId the client id
     */
    def setPositionConstraint(PositionConstraint pc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        setConstraint(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, uri, pc.id,
            pc.position, pc.posCons)
    }

    /**
     * Sets a layer constraint and a positional constraint that 
     * are encapsulated in an instance of StaticConstraint.
     * @param sc the constraint
     * @param clientId the client id
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
            var newLayerCons = sc.layerCons

            val layerId = kNode.getProperty(LayeredOptions.LAYERING_LAYER_ID)

            var targetLayerNodes = InteractiveUtil.getNodesOfLayer(newLayerId, allNodes)
            var oldLayerNodes = InteractiveUtil.getNodesOfLayer(layerId, allNodes)
            
            val chain = InteractiveUtil.getChain(kNode, oldLayerNodes)
            // posID must be increased by the number of predecessors
            val newPosId = sc.position + chain.indexOf(kNode)
            val newPosCons = sc.posCons + chain.indexOf(kNode)
            
            // Reevaluate insertion of node to target layer
            var reval = new LayeredConstraintReevaluation(kNode)

            if (reval.reevaluateAfterEmptyingALayer(kNode, newLayerCons, allNodes)) {
                newLayerCons--
            }
            reval.reevaluatePositionConstraintsAfterLayerSwap(targetLayerNodes, oldLayerNodes, kNode, newPosId)
            reval.reevaluateLayerConstraintsInChain(newLayerCons, chain)
            reval.reevaluatePosConsInChain(kNode, newPosCons, chain)

            var changedNodes = reval.changedNodes
            changedNodes.put(new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT), newPosCons)
            changedNodes.put(new ConstraintProperty(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT), newLayerCons)
            // Update source code of the model
            refreshModelInEditor(changedNodes, uri)
            
            // update relative constraints
            var relReval = new RelativeConstraintReevaluation(kNode)
            relReval.reevaluateRelCons(kNode, newPosId, targetLayerNodes, oldLayerNodes)
            val relChangedNodes = relReval.changedNodes
            if (!relChangedNodes.isEmpty) {
                refreshModelInEditorForRC(relChangedNodes, uri)
            }
        }
    }

    /**
     * Delete a constraint.
     * @param dc the constraint o delete
     * @param clientId the client id
     */
    def deleteStaticConstraint(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val changedNodes = newHashMap()
            changedNodes.put(new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT), null)
            changedNodes.put(new ConstraintProperty(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT), null)
            refreshModelInEditor(changedNodes, uri)
        }
    }

    /**
     * Delete a position constraint.
     * @param dc the position constraint o delete
     * @param clientId the client id
     */
    def deletePositionConstraint(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val changedNodes = newHashMap()
            changedNodes.put(new ConstraintProperty(kNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT), null)
            refreshModelInEditor(changedNodes, uri)
        }
    }

    /**
     * Delete a layer constraint.
     * @param dc the layer constraint o delete
     * @param clientId the client id
     */
    def deleteLayerConstraint(DeleteConstraint dc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = getKNode(uri, dc.id)
        if (kNode !== null) {
            val changedNodes = newHashMap()
            changedNodes.put(new ConstraintProperty(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT), null)
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
     * @param targetId The id of the node on which the constraint should be set.
     * @param value Either the id of the position or the id of the layer.
     */
    private def setConstraint(IProperty<Integer> property, String uri, String targetId, int valueId, int valueCons) {
        val kNode = LSPUtil.getKNode(diagramState, uri, targetId)
        val parentOfNode = kNode.parent

        if (kNode !== null && parentOfNode !== null) {
            var layerID = kNode.getProperty(LayeredOptions.LAYERING_LAYER_ID)
            var posID = kNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT);
            if (property === LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) {
                layerID = valueId
            }
            
            var List<KNode> layerNodes = InteractiveUtil.getNodesOfLayer(layerID, parentOfNode.children)
            val oldLayerNodes = InteractiveUtil.getNodesOfLayer(kNode.getProperty(LayeredOptions.LAYERING_LAYER_ID), parentOfNode.children)
            var newValueCons = valueCons
            var newValueId = valueId
            val oldPos = kNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
            
            var relReval = new RelativeConstraintReevaluation(kNode)
            val chain = InteractiveUtil.getChain(kNode, oldLayerNodes)
            if (property === LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT) {
                posID = valueId
                if (posID != -1 && posID >= chain.get(0).getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
                    && posID <= chain.get(chain.size - 1).getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)) {
                    // node is moved within its chain
                    relReval.reevaluateRCAfterSwapInChain(kNode, oldLayerNodes)   
                } else if (posID < oldPos) {
                    // posID must be increased by the number of predecessors
                    newValueCons += chain.indexOf(kNode)
                    newValueId += chain.indexOf(kNode)
                }
            }
        
            var reval = new LayeredConstraintReevaluation(kNode)
            switch (property) {
                case LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT: {
                    reval.reevaluatePositionConstraintsAfterPosChangeInLayer(layerNodes, kNode, newValueId)
                    reval.reevaluatePosConsInChain(kNode, newValueCons, chain)
                }
                case LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT:
                    reval.reevaluateLayerConstraintsInChain(layerID, chain)
            }
            
            var changedNodes = reval.changedNodes
            changedNodes.put(new ConstraintProperty(kNode, property), newValueCons)
            refreshModelInEditor(changedNodes, uri)
            
            // update relative constraints
            if (posID != -1) {
                relReval.reevaluateRelCons(kNode, posID, layerNodes, oldLayerNodes)
                val relChangedNodes = relReval.changedNodes
                if (!relChangedNodes.isEmpty) {
                    refreshModelInEditorForRC(relChangedNodes, uri)
                }
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
     * 
     * @param uri The resource's uri
     * @param nodeId The Id of the requested KNode
     * @return The requested node
     */
    private def getKNode(String uri, String nodeId) {
        return LSPUtil.getKNode(diagramState, uri, nodeId)
    }
    
    /**
     * Sends request to the client to update the file according to the property changes.
     * 
     * @param changedNodes list of all changes to nodes
     * @param uri uri of resource
     */
    def refreshModelInEditor(HashMap<ConstraintProperty, Integer> changedNodes, String uri) {
        val resource = languageServer.getResource(uri)
            
        // Get previous file content as String
        var outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeBefore = outputStream.toString
        
        var changed = false
        for (entry : changedNodes.keySet) {
            // set Property/annotation of corresponding model element 
            val kNode = entry.KNode
            val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)

            if (node instanceof ElkNode /*FIXME || node instanceof StateImpl*/) {
                val value = changedNodes.get(entry)
                if (kNode.getProperty(entry.property) !== value) {
                    kNode.setProperty(entry.property, value)
                    InteractiveUtil.copyAllConstraints(node, kNode)
                    changed = true;
                }
            }
        }

        if (changed) {
            val Map<String, List<TextEdit>> changes = newHashMap
            
            // Get changed file as String
            outputStream = new ByteArrayOutputStream
            resource.save(outputStream, emptyMap)
            val String codeAfter = outputStream.toString().trim
            // The range is the length of the previous file.
            val Range range = new Range(new Position(0, 0), new Position(codeBefore.split("\r\n|\r|\n").length, 0))
            val TextEdit textEdit = new TextEdit(range, codeAfter)
            changes.put(uri, #[textEdit]);
            this.client.replaceContentInFile(uri, codeAfter, range)
            return
        } else {
            languageServer.updateDiagram(uri)
        }
    }
    
    
    /**
     * Sends request to the client to update the file according to the property changes.
     * 
     * @param changedNodes list of all changes to nodes
     * @param uri uri of resource
     */
    def refreshModelInEditorForRC(HashMap<RelativeConstraintProperty, String> changedNodes, String uri) {
        val resource = languageServer.getResource(uri)
            
        // Get previous file content as String
        var outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeBefore = outputStream.toString
        
        var changed = false
        for (entry : changedNodes.keySet) {
            // set Property/annotation of corresponding model element  
            val kNode = entry.KNode
            val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
            // FIXME
            if (node instanceof ElkNode /*|| node instanceof StateImpl*/) {
                val value = changedNodes.get(entry)
                kNode.setProperty(entry.property, value)
                InteractiveUtil.copyAllConstraints(node, kNode)
                changed = true;
            }
        }

        if (changed) {
            val Map<String, List<TextEdit>> changes = newHashMap
            
            // Get changed file as String
            outputStream = new ByteArrayOutputStream
            resource.save(outputStream, emptyMap)
            val String codeAfter = outputStream.toString().trim
            // The range is the length of the previous file.
            val Range range = new Range(new Position(0, 0), new Position(codeBefore.split("\r\n|\r|\n").length, 0))
            val TextEdit textEdit = new TextEdit(range, codeAfter)
            changes.put(uri, #[textEdit]);
            this.client.replaceContentInFile(uri, codeAfter, range)
            return
        } else {
            languageServer.updateDiagram(uri)
        }
    }
}
