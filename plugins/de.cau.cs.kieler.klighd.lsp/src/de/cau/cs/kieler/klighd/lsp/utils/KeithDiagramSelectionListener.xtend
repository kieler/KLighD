/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019, 2021 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.utils

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import org.eclipse.sprotty.Action
import org.eclipse.sprotty.IDiagramServer
import org.eclipse.sprotty.SModelIndex
import org.eclipse.sprotty.SelectAction
import org.eclipse.sprotty.xtext.DiagramSelectionListener
import org.eclipse.sprotty.xtext.LanguageAwareDiagramServer
import org.eclipse.sprotty.xtext.tracing.ITraceProvider

/**
 * Extends Sprotty's {@link DiagramSelectionListener} by first checking the configurable option in Keith that indicates
 * if the selection of a diagram element should even be displayed in the text editor.
 * 
 * @author nre
 */
class KeithDiagramSelectionListener extends DiagramSelectionListener {
    
    /**
     * Stores data for the generation of diagrams.
     */
    @Inject
    KGraphDiagramState diagramState
    
    /**
     * Provider to lazily trace the requested elements.
     */
    @Inject
    ITraceProvider traceProvider
    
    override selectionChanged(Action action, IDiagramServer server) {
        if (server instanceof LanguageAwareDiagramServer) {
            val languageServer = server.diagramLanguageServer
            if (languageServer instanceof KGraphLanguageServerExtension) {
                // Find out if the selection should be displayed in the text editor.
                if (!languageServer.shouldSelectText) {
                    // If not, return and do nothing.
                    return
                }
                // Make sure the lazy tracing has been executed for the selected elements first.
                if (action instanceof SelectAction && traceProvider instanceof LazyTraceProvider) {
                    val index = new SModelIndex(server.model)
                    synchronized (diagramState) {
                        val s2k = diagramState.getKGraphToSModelElementMap(server.model.id)?.inverse
                        for (id : (action as SelectAction).selectedElementsIDs ?: #[]) {
                            val selectedElement = index.get(id)
                            if (selectedElement.trace === null) {
                                val selectedKElement = s2k.get(selectedElement)
                                (traceProvider as LazyTraceProvider).traceFromKGraph(selectedElement, selectedKElement)
                            }
                        }
                    }
                }
            }
        }
        
        super.selectionChanged(action, server)
    }
    
}