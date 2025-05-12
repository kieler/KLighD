/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2024 by
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
package de.cau.cs.kieler.klighd.lsp

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.LightDiagramLayoutConfig
import de.cau.cs.kieler.klighd.lsp.launch.AbstractLanguageServer
import de.cau.cs.kieler.klighd.lsp.utils.KGraphMappingUtil
import de.cau.cs.kieler.klighd.lsp.utils.RenderingPreparer
import java.io.ByteArrayOutputStream
import java.util.ArrayList
import org.apache.log4j.Logger
import org.eclipse.elk.core.math.ElkPadding
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.graph.ElkNode
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.sprotty.Action
import org.eclipse.sprotty.SGraph
import org.eclipse.sprotty.SModelRoot
import org.eclipse.sprotty.layout.ElkLayoutEngine

/**
 * Handles the server side layout of KGraphs.
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nre
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangLayoutEngine.xtend">
 *      YangLayoutEngine</a>
 */
class KGraphLayoutEngine extends ElkLayoutEngine {
    /**
     * Stores data for the generation of diagrams.
     */
    @Inject KGraphDiagramState diagramState
	
	public static val LOG = Logger.getLogger(KGraphLayoutEngine)
	
	override layout(SModelRoot root, Action cause) {
	    AbstractLanguageServer.addToMainThreadQueue([
    	    synchronized (diagramState) {
        	    if (root instanceof SGraph) {
        	        // The layout is executed on the KGraph, not the SGraph. So get the KGraph belonging to this SGraph from
        	        // the KGraphContext.
                    onlyLayoutOnKGraph(root.id)
    
                    // map layouted KGraph to SGraph
                    KGraphMappingUtil.mapLayout(diagramState.getKGraphToSModelElementMap(root.id))
                }
            }
        ])
    }

    /**
     * Performs the layout only on the KGraph without mapping it to a SGraph
     * 
     * @param uri The identifying URI of the graph.
     */
    def onlyLayoutOnKGraph(String uri) {
        val kGraphContext = diagramState.getKGraphContext(uri)
        // Remove any padding from the root node to avoid blank padding around the edge of the entire graph.
        kGraphContext.viewModel.setProperty(CoreOptions.PADDING, new ElkPadding(0))

        // layout of KGraph
        val lightDiagramLayoutConfig = new LightDiagramLayoutConfig(kGraphContext)

        // Get the layout configurator.
        val configurator = diagramState.getLayoutConfig(uri)

        var configurators = new ArrayList
        configurators.add(configurator)
        lightDiagramLayoutConfig.options(configurators)

        synchronized (kGraphContext.viewModel) {
            lightDiagramLayoutConfig.performLayout
            RenderingPreparer.prepareRenderingLayout(kGraphContext.viewModel, diagramState.getKGraphToSModelElementMap(uri))
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
