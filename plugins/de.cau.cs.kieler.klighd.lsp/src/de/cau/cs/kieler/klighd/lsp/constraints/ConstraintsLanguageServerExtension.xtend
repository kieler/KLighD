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
import org.eclipse.sprotty.SNode
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import org.eclipse.elk.graph.ElkNode

/**
 * @author jet, cos
 * 
 */
@Singleton
class ConstraintsLanguageServerExtension implements ILanguageServerExtension, ConstraintsCommandExtension {

    @Accessors ConstraintsLanguageClient client;

    @Inject
    KGraphDiagramState diagramState

    override initialize(ILanguageServerAccess access) {
    }

    override sayHello(String msg) {
        println("Hallo " + msg)
        client.sayGoodbye("Good bye my old friend")
    }

    override setLayerConstraint(LayerConstraint lc) {
        val mapKToS = diagramState.getKGraphToSModelElementMap(lc.getUri)

        // KGraphElement which corresponding SNode has the correct ID
        val kGEle = KGraphElementIDGenerator.findElementById(mapKToS, lc.getID)
        // set property of KNode
        if (kGEle instanceof KNode) {
            val kNode = kGEle as KNode
            // TODO: check whether value for the property is valid
            kNode.setProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, lc.getLayer)
            // set Property of corresponding elkNode
            val elkNode = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
            if (elkNode instanceof ElkNode) {
                elkNode.setProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, lc.getLayer)
            }
        }
    }
    
    // TODO: define & implement setPositionConstraint method 

}
