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
        setConstraint(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, pc.getUri, pc.getID, -1)
    }

    
    override deleteLayerConstraint(LayerConstraint lc) {
        setConstraint(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, lc.getUri, lc.getID, -1)
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
        val mapKToS = diagramState.getKGraphToSModelElementMap(uri)

        // KGraphElement which corresponding SNode has the correct ID
        val kGEle = KGraphElementIDGenerator.findElementById(mapKToS, targetID)

        var ViewContext viewContext = null
        synchronized (diagramState) {
            viewContext = diagramState.getKGraphContext(uri)
        }

        if (viewContext !== null) {
            val root = viewContext.viewModel

            // set property of KNode
            if (root.getProperty(LayeredOptions.INTERACTIVE_LAYOUT) && kGEle instanceof KNode) {
                val kNode = kGEle as KNode
                // TODO: check whether value for the property is valid
                
                kNode.setProperty(PropID, value)

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
        }
    }
    
    override setStaticConstraint(StaticConstraint sc) {
        throw new UnsupportedOperationException("TODO: auto-generated method stub")
    }

}
