/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
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
package de.cau.cs.kieler.klighd.lsp.launch;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.ide.server.MultiProjectWorkspaceConfigFactory;
import org.eclipse.xtext.workspace.FileProjectConfig;
import org.eclipse.xtext.workspace.WorkspaceConfig;

/** 
 * Overrides default workspace configuration to not create an xtext folder workspace.
 * 
 * @author sdo
 */
class BasicProjectWorkspaceConfigFactory extends MultiProjectWorkspaceConfigFactory {

    override void findProjects(WorkspaceConfig workspaceConfig, URI uri) {
        if (uri === null) {
            return;
        }
        val File baseFile = new File(uri.toFileString());
        if (!baseFile.isDirectory()) {
            return;
        }
        for (File dir : baseFile.listFiles([isDirectory])) {
            val FileProjectConfig project = new FileProjectConfig(dir, workspaceConfig);
            // Disable source folder to not create an xtext folder
            // project.addSourceFolder(".");
            workspaceConfig.addProject(project);
        }
    }
}
