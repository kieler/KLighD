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
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.emf.common.util.URI

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
        setConstraint(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, lc.getUri, lc.getID, lc.getLayer)
    }

    override setPositionConstraint(PositionConstraint pc) {
        setConstraint(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, pc.getUri, pc.getID,
            pc.getPosition)
    }

    private def setConstraint(IProperty<Integer> PropID, String uri, String targetID, int value) {
        val mapKToS = diagramState.getKGraphToSModelElementMap(uri.toString)
        // KGraphElement which corresponding SNode has the correct ID
        val kGEle = KGraphElementIDGenerator.findElementById(mapKToS, targetID)
        // set property of KNode
        if (kGEle instanceof KNode) {
            val kNode = kGEle as KNode
            // TODO: check whether value for the property is valid
            kNode.setProperty(PropID, value)
            // set Property of corresponding elkNode
//            val elkNode = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
//            if (elkNode instanceof ElkNode) {
//                elkNode.setProperty(PropID, value)
//            }
            
           
        }
    }
    
    override deletePositionConstraint(PositionConstraint pc) {
        throw new UnsupportedOperationException("TODO: auto-generated method stub")
    }
    
    override deleteLayerConstraint(LayerConstraint lc) {
        throw new UnsupportedOperationException("TODO: auto-generated method stub")
    }

}
