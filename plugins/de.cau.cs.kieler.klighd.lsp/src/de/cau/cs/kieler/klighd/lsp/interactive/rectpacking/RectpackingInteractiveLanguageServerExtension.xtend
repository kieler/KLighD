/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
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
package de.cau.cs.kieler.klighd.lsp.interactive.rectpacking

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import de.cau.cs.kieler.klighd.lsp.interactive.ConstraintProperty
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveUtil
import java.util.Arrays
import java.util.List
import javax.inject.Singleton
import org.eclipse.elk.alg.rectpacking.options.RectPackingOptions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension

/**
 * Language server extension to change the rectpacking algorithm in the interactive mode.
 * 
 * @author sdo
 */
@Singleton
class RectpackingInteractiveLanguageServerExtension implements ILanguageServerExtension {

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
     * @param constraint constraint to be set
     * @param clientId identifier of diagram
     */
    def setPositionConstraint(RectpackingSetPositionConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, constraint.id)
        val parent = kNode.parent;
        val List<KNode> changedNodes = newArrayList(kNode)
        if (parent.getProperty(CoreOptions.INTERACTIVE_LAYOUT)) {
            // Sort all nodes regarding their actual position
            val KNode[] clonedChildren = parent.children.clone()
            Arrays.sort(clonedChildren, [a,b |
                return Integer.compare(a.getProperty(RectPackingOptions.CURRENT_POSITION), b.getProperty(RectPackingOptions.CURRENT_POSITION))
            ])

            // Get previous and new position of node
            val desiredPosition = constraint.order
            var previousPosition = kNode.getProperty(RectPackingOptions.DESIRED_POSITION)
            if (previousPosition === -1) {
                previousPosition = kNode.getProperty(RectPackingOptions.CURRENT_POSITION)
            }

            // Change desired position of nodes that are influenced by the movement
            val modifier = if (previousPosition < desiredPosition) 1 else -1
            for (var int position = previousPosition; position != desiredPosition + modifier; position += modifier) {
                val node = clonedChildren.get(position)
                if (node.hasProperty(RectPackingOptions.DESIRED_POSITION)) {
                    val nodePosition = node.getProperty(RectPackingOptions.DESIRED_POSITION)
                    if ((previousPosition < nodePosition && nodePosition <= desiredPosition) || 
                        (previousPosition > nodePosition && nodePosition >= desiredPosition)) {
                        node.setProperty(
                            RectPackingOptions.DESIRED_POSITION,
                            position - modifier)
                        changedNodes.add(node)
                    }
                }
            }
            refreshModelInEditor(new ConstraintProperty(kNode, RectPackingOptions.DESIRED_POSITION, desiredPosition), KGraphUtil.getRootNodeOf(kNode), uri)

        }
    }

    /**
     * Delete a position constraint.
     * 
     * @param constraint The deletion constraint
     * @param clientId The client id of the corresponding  diagram view.
     */
    def deletePositionConstraint(RectpackingDeletePositionConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, constraint.id)
        val parent = kNode.parent
        val List<KNode> changedNodes = newArrayList(kNode)
        if (parent.getProperty(CoreOptions.INTERACTIVE_LAYOUT)) {
            // Sort all nodes regarding their actual position
            val KNode[] clonedChildren = parent.children.clone()
            Arrays.sort(clonedChildren, [a,b |
                return Integer.compare(a.getProperty(RectPackingOptions.CURRENT_POSITION), b.getProperty(RectPackingOptions.CURRENT_POSITION))
            ])

            // Get previous and new position of node
            val desiredPosition = parent.children.indexOf(kNode)
            val int previousPosition = kNode.getProperty(RectPackingOptions.CURRENT_POSITION)

            // Change desired position of nodes that are influenced by the movement
            val modifier = if (previousPosition < desiredPosition) 1 else -1
            for (var int position = previousPosition; position != desiredPosition; position += modifier) {
                val node = clonedChildren.get(position)
                if (node.hasProperty(RectPackingOptions.DESIRED_POSITION)) {
                    val nodePosition = node.getProperty(RectPackingOptions.DESIRED_POSITION)
                    if ((previousPosition < nodePosition && nodePosition <= desiredPosition) || 
                        (previousPosition > nodePosition && nodePosition >= desiredPosition)) {
                        node.setProperty(
                            RectPackingOptions.DESIRED_POSITION,
                            position - modifier)
                        changedNodes.add(node)
                    }
                }
            }
            kNode.setProperty(RectPackingOptions.DESIRED_POSITION, null)
            refreshModelInEditor(new ConstraintProperty(kNode, RectPackingOptions.DESIRED_POSITION, null), KGraphUtil.getRootNodeOf(kNode), uri)

        }
    }
    
    /**
     * Sets the aspect ratio.
     * 
     * @param constraint The aspect ratio constraint.
     * @param The client id of the corresponding diagram view.
     */
    def setAspectRatio(SetAspectRatio constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, constraint.id)
        refreshModelInEditor(new ConstraintProperty(kNode, RectPackingOptions.ASPECT_RATIO, Double.valueOf(constraint.aspectRatio)),
            KGraphUtil.getRootNodeOf(kNode), uri
        )
    }

    /**
     * Applies property changes to the file given by the uri by sending by notifying the client to execute the changes.
     * 
     * @param changedNodes The KNodes that changed.
     * @param model The main KNode
     * @param uri uri of resource
     */
    def refreshModelInEditor(ConstraintProperty<Object> constraint, KNode model, String uri) {
        val KNode kNode = constraint.KNode
        kNode.setProperty(constraint.property, constraint.value)
        InteractiveUtil.serializeConstraints(#[constraint], model, uri, this.languageServer, this.client)
        return

    }
}
