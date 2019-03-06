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

import de.cau.cs.kieler.klighd.lsp.utils.SimpleTraceRegionProvider
import io.typefox.sprotty.server.xtext.IDiagramGenerator
import io.typefox.sprotty.server.xtext.ide.IdeDiagramModule
import io.typefox.sprotty.server.xtext.ide.IdeLanguageServerExtension
import io.typefox.sprotty.server.xtext.tracing.TraceRegionProvider

/**
 * Allows to bind all needed modules for KGraph diagram generation via Guice.
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nir
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangDiagramModule.xtend">
 *      YangDiagramModule</a>
 */
public class KGraphDiagramModule extends IdeDiagramModule {
	
	public def Class<? extends IdeLanguageServerExtension> bindIdeLanguageServerExtension() {
		KGraphLanguageServerExtension
	}
	
	override bindILayoutEngine() {
		KGraphLayoutEngine
	}
	
	public def Class<? extends IDiagramGenerator> bindIDiagramGenerator() {
		KGraphDiagramGenerator
	}
	
	override bindIDiagramServer() {
        KGraphDiagramServer
    }
    
	public def Class<? extends TraceRegionProvider> bindTraceRegionProvider() {
        SimpleTraceRegionProvider
    }
}
