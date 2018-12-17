/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import io.typefox.sprotty.api.Action
import io.typefox.sprotty.api.IDiagramExpansionListener
import io.typefox.sprotty.api.IDiagramServer
import io.typefox.sprotty.server.xtext.LanguageAwareDiagramServer

class KGraphDiagramExpansionListener implements IDiagramExpansionListener {
	
	override expansionChanged(Action action, IDiagramServer server) {
	    // TODO: implement changing of the rendering / calling the ExpandAndCollapseAction on server and client side
		if (server instanceof LanguageAwareDiagramServer) {
			val languageServerExtension = server.languageServerExtension
			languageServerExtension.updateDiagram(server)
		}
	}
}