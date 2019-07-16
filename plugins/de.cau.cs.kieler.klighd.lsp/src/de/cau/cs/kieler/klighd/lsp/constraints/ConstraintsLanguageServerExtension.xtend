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
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.utils.KGraphElementIDGenerator
import javax.inject.Singleton
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension
import org.eclipse.elk.graph.properties.IProperty
import java.util.HashMap
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import com.google.inject.Injector
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.lsp4j.jsonrpc.validation.NonNull
import java.net.URLDecoder
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import org.eclipse.elk.graph.ElkNode
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramUpdater
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServerManager
import org.eclipse.sprotty.xtext.ls.DiagramLanguageServer
import org.eclipse.sprotty.xtext.ls.IDiagramServerManager
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import java.util.List

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

    override sayHello(String msg) {
        println("Hallo " + msg)
        client.sayGoodbye("Good bye my old friend")
    }

    override setLayerConstraint(LayerConstraint lc) {
        setConstraint(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, lc.getUri, lc.getID, lc.getLayer)
    }

    override setPositionConstraint(PositionConstraint pc) {
        setConstraint(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, pc.getUri, pc.getID,
            pc.getPosition)
    }

    override deletePositionConstraint(PositionConstraint pc) {
        val uri = pc.uri
        val kNode = getKNode(uri, pc.ID)
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

    override deleteLayerConstraint(LayerConstraint lc) {
        val kNode = getKNode(lc.uri, lc.ID)
        if (kNode !== null) {
            ConstraintsUtils.nullifyLayerConstraint(kNode)
            updateSourceCode(kNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, null, lc.uri)
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
    private def setConstraint(IProperty<Integer> PropID, String uri, String targetID, int value) {
        val kNode = getKNode(uri, targetID)

        if (kNode !== null) {
            kNode.setProperty(PropID, value)
            updateSourceCode(kNode, PropID, value, uri)
        }
    }

    /**
     * Returns the {@code KNode} of the node described by {@code ID}.
     * Returns null if the {@code ViewContext} of the resource described by {@code uri} is null.
     * Returns null if the element behind the ID is no kNode.
     * Returns null if the {@code INTERACTIVE_LAYOUT} IProperty is not set on the root of the resource.
     */
    private def getKNode(String uri, String ID) {
        val mapKToS = diagramState.getKGraphToSModelElementMap(uri)

        // KGraphElement which corresponding SNode has the correct ID
        val kGEle = KGraphElementIDGenerator.findElementById(mapKToS, ID)

        var ViewContext viewContext = null
        synchronized (diagramState) {
            viewContext = diagramState.getKGraphContext(uri)
        }

        if (viewContext !== null) {
            val root = viewContext.viewModel

            // set property of KNode
            if (root.getProperty(LayeredOptions.INTERACTIVE_LAYOUT) && kGEle instanceof KNode) {
                return kGEle as KNode
            } else {
                return null
            }
        } else {
            return null
        }
    }

    /**
     * Updates the source code of the elk model that is in the resource of {@code uri}.
     */
    private def updateSourceCode(KNode kNode, IProperty<Integer> PropID, Integer value, String uri) {
        // set Property of corresponding elkNode 
        val elkNode = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)

        if (elkNode instanceof ElkNode) {
            elkNode.setProperty(PropID, value)
            val elkGraph = elkNode.parent
            val resource = ConstraintsUtils.getResourceFromUri(uri, injector)

            // Delete the old model
            resource.contents.clear
            // Store the new model
            resource.contents += elkGraph
            // Serialize it into the file
            resource.save(emptyMap())
        }
    }

    /**
     * Updates the source code of the elk model that is in the resource of {@code uri}.
     */
    private def updateSourceCode(KNode kNode, List<IProperty<Integer>> propIDs, List<Integer> vals, String uri) {
        // set Property of corresponding elkNode 
        val elkNode = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)

        if (elkNode instanceof ElkNode) {
            for (var i = 0; i < propIDs.length; i++) {
                elkNode.setProperty(propIDs.get(i), vals.get(i))
            }
            val elkGraph = elkNode.parent
            val resource = ConstraintsUtils.getResourceFromUri(uri, injector)

            // Delete the old model
            resource.contents.clear
            // Store the new model
            resource.contents += elkGraph
            // Serialize it into the file
            resource.save(emptyMap())
        }
    }

    /**
     * Sets a layer constraint and a positional constraint that 
     * are encapsulated in an instance of StaticConstraint.
     * 
     */
    override setStaticConstraint(StaticConstraint sc) {
        val uri = sc.uri
        val kNode = getKNode(uri, sc.ID)

        // In case that the interactive mode is active, the viewContext is not null 
        // and the element is actually a KNode. Carry on.
        if (kNode !== null) {
            val layer = sc.layer
            val pos = sc.position

            ConstraintsUtils.setLayerConstraint(kNode, layer)
            // Reevaluate possible shifting
            ConstraintsUtils.setPosConstraint(kNode, pos)
            // Reevaluate insertion of node to target layer
            // Update source code of the model
            val props = #[LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT,
                LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT]
            val vals = #[layer, pos]
            updateSourceCode(kNode, props, vals, uri)

        }
    }

    @Inject KGraphLanguageServerExtension kGraphLanguageServerExt

    override refreshLayout(String uri) {

        val fittingServers = kGraphLanguageServerExt.diagramServerManager.findDiagramServersByUri(uri)
        val diagramServer = fittingServers.head as KGraphDiagramServer
        val diagramUpdater = kGraphLanguageServerExt.diagramUpdater as KGraphDiagramUpdater

        // Triggers the new layout and sends it to the client
        diagramUpdater.updateLayout(diagramServer)
    }

}
