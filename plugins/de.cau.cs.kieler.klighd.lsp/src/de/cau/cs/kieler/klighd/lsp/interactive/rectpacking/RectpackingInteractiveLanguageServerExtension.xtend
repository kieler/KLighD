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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.interactive.rectpacking

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveUtil
import java.io.ByteArrayOutputStream
import java.util.Arrays
import java.util.List
import java.util.Map
import javax.inject.Singleton
import org.eclipse.elk.alg.rectpacking.options.RectPackingOptions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.graph.ElkNode
import org.eclipse.lsp4j.Position
import org.eclipse.lsp4j.Range
import org.eclipse.lsp4j.TextEdit
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
            kNode.setProperty(RectPackingOptions.DESIRED_POSITION, desiredPosition)
            refreshModelInEditor(changedNodes, uri)

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
            refreshModelInEditor(changedNodes, uri)

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
        kNode.setProperty(RectPackingOptions.ASPECT_RATIO, Double.valueOf(constraint.aspectRatio))
        val resource = languageServer.getResource(uri);  
                  
        // Get previous file content as String
        var outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeBefore = outputStream.toString
        
        val elkNode = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        if (elkNode instanceof ElkNode) {
            val Map<String, List<TextEdit>> changes = newHashMap
            elkNode.setProperty(RectPackingOptions.ASPECT_RATIO, constraint.aspectRatio)
            
            // Get changed file as String
            outputStream = new ByteArrayOutputStream
            resource.save(outputStream, emptyMap)
            val codeAfter = outputStream.toString
            
            // The range is the length of the previous file.
            val Range range = new Range(new Position(0, 0), new Position(codeBefore.split("\r\n|\r|\n").length, 0))
            val TextEdit textEdit = new TextEdit(range, codeAfter)
            changes.put(uri, #[textEdit]);
            this.client.replaceContentInFile(uri, codeAfter, range)
            return
        }
    }

    /**
     * Applies property changes to the file given by the uri by sending by notifying the client to execute the changes.
     * 
     * @param changedNodes The KNodes that changed.
     * @param uri uri of resource
     */
    def refreshModelInEditor(List<KNode> changedNodes, String uri) {
        val resource = languageServer.getResource(uri);
            
        // Get previous file content as String
        var outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeBefore = outputStream.toString
        
        for (node : changedNodes) {
            val elkNode = node.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
            if (elkNode instanceof ElkNode) {
                InteractiveUtil.copyAllConstraints(elkNode, node)
            }
        }
        val elkNode = changedNodes.get(0).getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        if (elkNode instanceof ElkNode) {
            val Map<String, List<TextEdit>> changes = newHashMap
            
            // Get changed file as String
            outputStream = new ByteArrayOutputStream
            resource.save(outputStream, emptyMap)
            val codeAfter = outputStream.toString().trim()
            
            // The range is the length of the previous file.
            val Range range = new Range(new Position(0, 0), new Position(codeBefore.split("\r\n|\r|\n").length, 0))
            val TextEdit textEdit = new TextEdit(range, codeAfter)
            changes.put(uri, #[textEdit]);
            this.client.replaceContentInFile(uri, codeAfter, range)
            return
        }

    }
}
