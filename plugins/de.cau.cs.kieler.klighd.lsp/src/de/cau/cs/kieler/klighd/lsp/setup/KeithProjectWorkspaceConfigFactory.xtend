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
package de.cau.cs.kieler.klighd.lsp.setup

import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.ide.server.IWorkspaceConfigFactory
import org.eclipse.xtext.workspace.FileProjectConfig
import org.eclipse.xtext.workspace.WorkspaceConfig

/**
 * @author sdo
 *
 */
class KeithProjectWorkspaceConfigFactory implements IWorkspaceConfigFactory {
    
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
    
}