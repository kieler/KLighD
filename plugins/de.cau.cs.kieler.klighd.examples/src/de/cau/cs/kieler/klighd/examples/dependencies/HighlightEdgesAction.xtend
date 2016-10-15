package de.cau.cs.kieler.klighd.examples.dependencies

import com.google.common.collect.Lists
import com.google.inject.Guice
import com.google.inject.Inject
import de.cau.cs.kieler.klighd.IAction
import de.cau.cs.kieler.klighd.krendering.KPolyline
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import java.util.List
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KEdge

class HighlightEdgesAction implements IAction {

    @Inject extension KRenderingExtensions //= new KRenderingExtensions
    @Inject extension KColorExtensions //= new KColorExtensions

    val List<KPolyline> lastRunEdges = Lists.newLinkedList

    var KNode lastNode = null 
    
    new() {
        // Actually inject the members
        Guice.createInjector().injectMembers(this);
    }

    override execute(ActionContext context) {

        // revert last highlight
        lastRunEdges.forEach [ e |
            e.foreground = "black".color
            e.lineWidth = 1f
        ]

        lastRunEdges.clear

        if (lastNode == context.getKNode) {
            lastNode = null
            return ActionResult.createResult(true) // we undid something
        }
        lastNode = context.getKNode

//        val String mode = context.viewContext.getOptionValue(
//            GeneralUserWorkloadDiagramSynthesis.HIGHLIGHT_EDGES) as String
//
//        if (mode == "Outgoing" || mode == "Both") {
            context.getKNode.outgoingEdges.forEach [ e |
                val r = e.rendering
                if (r instanceof KPolyline) {
                    r.highlight
                    lastRunEdges += r as KPolyline
                }
            ]
//        }

//        if (mode == "Incoming" || mode == "Both") {
            context.getKNode.incomingEdges.forEach [ e |
                val r = e.rendering
                if (r instanceof KPolyline) {
                    r.highlight
                    lastRunEdges += r as KPolyline
                }
            ]
//        }

//        return ActionResult.createResult(mode != "None")
          return ActionResult.createResult(true)
    }

    def highlight(KRendering pl) {
        pl.foreground = "red".color
        pl.lineWidth = 3f
    }

    def getRendering(KEdge node) {
        return node.getData(typeof(KRendering))
    }

}
