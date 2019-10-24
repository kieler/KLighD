/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.LightDiagramLayoutConfig
import de.cau.cs.kieler.klighd.lsp.utils.KGraphMappingUtil
import de.cau.cs.kieler.klighd.lsp.utils.RenderingPreparer
import java.io.ByteArrayOutputStream
import java.util.ArrayList
import org.apache.log4j.Logger
import org.eclipse.elk.graph.ElkNode
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.sprotty.Action
import org.eclipse.sprotty.SGraph
import org.eclipse.sprotty.SModelRoot
import org.eclipse.sprotty.layout.ElkLayoutEngine
import de.cau.cs.kieler.klighd.ViewContext

/**
 * Handles the server side layout of KGraphs.
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nir
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangLayoutEngine.xtend">
 *      YangLayoutEngine</a>
 */
public class KGraphLayoutEngine extends ElkLayoutEngine {
    /**
     * Stores data for the generation of diagrams.
     */
    @Inject KGraphDiagramState diagramState
	
	public static val LOG = Logger.getLogger(KGraphLayoutEngine)
	
	override layout(SModelRoot root, Action cause) {
	    synchronized (diagramState) {
    	    if (root instanceof SGraph) {
    	        // The layout is executed on the KGraph, not the SGraph. So get the KGraph belonging to this SGraph from
    	        // the KGraphContext.
                val kGraphContext = diagramState.getKGraphContext(root.id)

                // layout of KGraph
                val lightDiagramLayoutConfig = new LightDiagramLayoutConfig(kGraphContext)

                // Get the layout configurator.
                val configurator = diagramState.getLayoutConfig(root.id)

                var configurators = new ArrayList
                configurators.add(configurator)
                lightDiagramLayoutConfig.options(configurators)

                synchronized (kGraphContext.viewModel) {
                    lightDiagramLayoutConfig.performLayout
                    RenderingPreparer.prepareRendering(kGraphContext.viewModel)
                }

                // map layouted KGraph to SGraph
                KGraphMappingUtil.mapLayout(diagramState.getKGraphToSModelElementMap(root.id))
            }
        }
    }

    /**
     * Performs the layout only on the KGraph without mapping it to a SGraph
     */
    def onlyLayoutOnKGraph(String rootID) {
        val kGraphContext = diagramState.getKGraphContext(rootID)

        // layout of KGraph
        val lightDiagramLayoutConfig = new LightDiagramLayoutConfig(kGraphContext)

        // Get the layout configurator.
        val configurator = diagramState.getLayoutConfig(rootID)

        var configurators = new ArrayList
        configurators.add(configurator)
        lightDiagramLayoutConfig.options(configurators)

        synchronized (kGraphContext.viewModel) {
            lightDiagramLayoutConfig.performLayout
            RenderingPreparer.prepareRendering(kGraphContext.viewModel)
        }
    }

    override protected applyEngine(ElkNode elkGraph) {
        if (LOG.isTraceEnabled)
            LOG.info(elkGraph.toXMI)
        super.applyEngine(elkGraph)
    }

    private def toXMI(ElkNode elkGraph) {
        val resourceSet = new ResourceSetImpl
        val resource = resourceSet.createResource(URI.createFileURI('output.elkg'))
        resource.contents += elkGraph
        val outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        return outputStream.toString
    }
}
