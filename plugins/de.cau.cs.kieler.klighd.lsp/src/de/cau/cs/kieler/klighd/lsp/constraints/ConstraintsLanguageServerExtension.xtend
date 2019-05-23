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
        println("URI: " + lc.getUri)
        val mapKToS = diagramState.getKGraphToSModelElementMap(lc.getUri)
        println("Map: " + mapKToS)

    // TODO: implement the method. Therefore we need the correct diagramState and
    // hence the correct injector. => Niklas & Sören
    
//        val map = diagramState.kGraphToSModelElementMap
//        println(map.isEmpty)

//        val kGraphEle = KGraphElementIDGenerator.findElementById(mapKToS, lc.getID)
//        if (kGraphEle instanceof KNode) {
//            val kNode = kGraphEle as KNode
//            kNode.setProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, lc.getLayer)
//            println("Test ID: " + mapKToS.get(kGraphEle).id)
//        }
    }
    
    // TODO: define & implement setPositionConstraint method 

}
