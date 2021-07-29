/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2021 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import com.google.inject.Module
import de.cau.cs.kieler.klighd.lsp.utils.KeithDiagramSelectionListener
import de.cau.cs.kieler.klighd.lsp.utils.LazyTraceProvider
import de.cau.cs.kieler.klighd.lsp.utils.SimpleTraceRegionProvider
import org.eclipse.sprotty.IDiagramSelectionListener
import org.eclipse.sprotty.xtext.DefaultDiagramModule
import org.eclipse.sprotty.xtext.IDiagramGenerator
import org.eclipse.sprotty.xtext.ls.DiagramUpdater
import org.eclipse.sprotty.xtext.tracing.TextRegionProvider
import org.eclipse.xtext.ide.server.WorkspaceManager
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.naming.SimpleNameProvider
import org.eclipse.xtext.service.AbstractGenericModule

/**
 * Guice {@link Module} that binds all needed modules for KGraph diagram generation via Guice.
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nre
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangDiagramModule.xtend">
 *      YangDiagramModule</a>
 * @see AbstractGenericModule
 */
class KGraphDiagramModule extends DefaultDiagramModule {
	
	override bindILayoutEngine() {
		KGraphLayoutEngine
	}
	
	def Class<? extends IDiagramGenerator> bindIDiagramGenerator() {
		KGraphDiagramGenerator
	}
	
	override bindIDiagramServer() {
        KGraphDiagramServer
    }
    
	def Class<? extends TextRegionProvider> bindTraceRegionProvider() {
        SimpleTraceRegionProvider
    }
    
    override bindTraceProvider() {
        LazyTraceProvider
    }
    
    override bindIDiagramServerFactory() {
        KGraphDiagramServerFactory
    }
    
    def Class<? extends DiagramUpdater> bindDiagramUpdater() {
        KGraphDiagramUpdater
    }
    
    override Class<? extends IDiagramSelectionListener> bindIDiagramSelectionListener() {
        KeithDiagramSelectionListener
    }
    
    def Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
        SimpleNameProvider
    }
    
    def Class<? extends WorkspaceManager> bindWorkspaceManger() {
        KeithWorkspaceManager
    }
}
