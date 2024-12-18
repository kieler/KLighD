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
package de.cau.cs.kieler.klighd.lsp.interactive.mrtree

import com.google.inject.Inject
import com.google.inject.Singleton
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import de.cau.cs.kieler.klighd.lsp.interactive.ConstraintProperty
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveUtil
import java.util.List
import org.eclipse.elk.alg.mrtree.options.MrTreeOptions
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension

/**
 * Language server extension to change the mrtree algorithm in the interactive mode.
 * @author sdo
 * 
 */
@Singleton
class MrTreeInteractiveLanguageServerExtension implements ILanguageServerExtension {

    @Accessors KGraphLanguageClient client;

    @Inject
    KGraphDiagramState diagramState
    
    @Inject
    KGraphLanguageServerExtension languageServer

    override initialize(ILanguageServerAccess access) {
    }

    /**
     * Set order constraint of node specified by node id.
     * This changes all order values of all constraints of a previous layout run.
     * 
     * @param pc constraint to be set
     * @param clientId identifier of diagram
     */
    def setPositionConstraint(MrTreeSetPositionConstraint pc, String clientId) {
        val uri = diagramState.getURIString(clientId)
        setConstraint(MrTreeOptions.POSITION_CONSTRAINT, uri, pc.id,
            pc.position, pc.positionConstraint)
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
            val reeval = new PositionConstraintReevaluation(kNode)
            reeval.reevaluatePositionConstraintsAfterPosChangeInLayer(parentOfNode, kNode, valueId)
            
            var changedNodes = reeval.changedNodes
            refreshModelInEditor(changedNodes, KGraphUtil.getRootNodeOf(kNode), uri)
        }
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
