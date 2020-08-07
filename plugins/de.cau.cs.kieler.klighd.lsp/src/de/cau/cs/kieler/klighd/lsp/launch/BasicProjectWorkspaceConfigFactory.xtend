/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019,2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.launch

import com.google.inject.Inject
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.lsp4j.WorkspaceFolder
import org.eclipse.xtext.ide.server.IMultiRootWorkspaceConfigFactory
import org.eclipse.xtext.ide.server.UriExtensions
import org.eclipse.xtext.workspace.FileProjectConfig
import org.eclipse.xtext.workspace.WorkspaceConfig

/**
 * Overrides default workspace configuration to not create an xtext folder workspace.
 * 
 * @author sdo
 */
class BasicProjectWorkspaceConfigFactory implements IMultiRootWorkspaceConfigFactory {
    
    @Inject extension UriExtensions
    
    override getWorkspaceConfig(URI workspaceBaseURI) {
        val workspaceConfig = new WorkspaceConfig
        workspaceConfig.findProjects(workspaceBaseURI)
        return workspaceConfig
    }

    def void findProjects(WorkspaceConfig workspaceConfig, URI uri) {
        if (uri !== null) {
            val project = new FileProjectConfig(uri, workspaceConfig)
            // disable source folder
//            project.addSourceFolder('.') 
            workspaceConfig.addProject(project)
        }
    }
    
    override getWorkspaceConfig(List<WorkspaceFolder> workspaceFolders) {
        val workspaceConfig = new WorkspaceConfig
        for (workspaceFolder : workspaceFolders) {
            workspaceConfig.findProjects(toUri(workspaceFolder.uri))
        }
        return workspaceConfig
    }
    
}