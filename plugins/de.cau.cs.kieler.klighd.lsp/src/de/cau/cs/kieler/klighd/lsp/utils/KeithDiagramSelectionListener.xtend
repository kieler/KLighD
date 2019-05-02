/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.utils

import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import org.eclipse.sprotty.Action
import org.eclipse.sprotty.IDiagramServer
import org.eclipse.sprotty.xtext.DiagramSelectionListener
import org.eclipse.sprotty.xtext.LanguageAwareDiagramServer

/**
 * Extends Sprotty's {@link DiagramSelectionListener} by first checking the configurable option in Keith that indicates
 * if the selection of a diagram element should even be displayed in the text editor.
 * 
 * @author nre
 */
class KeithDiagramSelectionListener extends DiagramSelectionListener {
    override selectionChanged(Action action, IDiagramServer server) {
        // Find out if the selection should be displayed in the text editor.
        if (server instanceof LanguageAwareDiagramServer) {
            val languageServer = server.diagramLanguageServer
            if (languageServer instanceof KGraphLanguageServerExtension) {
                if (!languageServer.shouldSelectText) {
                    // If not, return and do nothing.
                    return
                }
            }
        }
        // Otherwise, display the selection in the text editor.
        super.selectionChanged(action, server)
    }
}