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
package de.cau.cs.kieler.klighd.lsp.interactive.rectpack

import com.google.inject.Inject
import com.google.inject.Injector
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveUtil
import java.util.Arrays
import java.util.List
import javax.inject.Singleton
import org.eclipse.elk.alg.packing.rectangles.options.RectPackingOptions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.graph.ElkNode
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension
import org.eclipse.xtext.resource.XtextResourceSet

/**
 * @author sdo
 * 
 */
@Singleton
class RectPackInterativeLanguageServerExtension implements ILanguageServerExtension {

    @Accessors LanguageClient client;

    @Inject
    KGraphDiagramState diagramState

    @Inject
    Injector injector

    override initialize(ILanguageServerAccess access) {
    }

    /**
     * Set order constraint of node specified by node id.
     * This changes all order values of all constraints of a previous layout run.
     * 
     * @param constraint constraint to be set
     * @param clientId identifier of diagram
     */
    def setPositionConstraint(RectPackSetPositionConstraint constraint, String clientId) {
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
            val modifier = previousPosition < desiredPosition ? 1 : -1
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

    def deletePositionConstraint(RectPackDeletePositionConstraint constraint, String clientId) {
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
            val modifier = previousPosition < desiredPosition ? 1 : -1
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
     */
    def setAspectRatio(SetAspectRatio constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, constraint.id)
        kNode.setProperty(RectPackingOptions.ASPECT_RATIO, constraint.aspectRatio)
        val resource = injector.getInstance(XtextResourceSet).getResource(URI.createURI(uri), true)
        val elkNode = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        if (elkNode instanceof ElkNode) {
            elkNode.setProperty(RectPackingOptions.ASPECT_RATIO, constraint.aspectRatio)
            val elkGraph = EcoreUtil.getRootContainer(elkNode)
            resource.contents.clear
            resource.contents += elkGraph
            resource.save(emptyMap)
        }
    }

    /**
     * Changes property changes defined by changedNodes to the resource
     * @param kNode just some kNode of the correct graph
     * @param uri uri of resource
     * TODO use some kind of updateDocument mechanism and do not just modify the contents of the resource directly
     */
    def refreshModelInEditor(List<KNode> changedNodes, String uri) {
        val resource = injector.getInstance(XtextResourceSet).getResource(URI.createURI(uri), true)
        for (node : changedNodes) {
            val elkNode = node.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
            if (elkNode instanceof ElkNode) {
                InteractiveUtil.copyAllConstraints(elkNode, node)
            }
        }
        val elkNode = changedNodes.get(0).getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        if (elkNode instanceof ElkNode) {
            val elkGraph = EcoreUtil.getRootContainer(elkNode)
            resource.contents.clear
            resource.contents += elkGraph
            resource.save(emptyMap)
        }

    }
}
