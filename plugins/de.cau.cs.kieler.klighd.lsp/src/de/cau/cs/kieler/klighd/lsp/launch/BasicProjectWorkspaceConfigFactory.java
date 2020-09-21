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
 * This code is provided under the terms of the Eclipse Public License (EPL).
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
public class BasicProjectWorkspaceConfigFactory extends MultiProjectWorkspaceConfigFactory {

    @Override
    public void findProjects(WorkspaceConfig workspaceConfig, URI uri) {
        if (uri == null) {
            return;
        }
        File baseFile = new File(uri.toFileString());
        if (!baseFile.isDirectory()) {
            return;
        }
        for (File dir : baseFile.listFiles(File::isDirectory)) {
            FileProjectConfig project = new FileProjectConfig(dir, workspaceConfig);
            // Disable source folder to not create an xtext folder
            // project.addSourceFolder(".");
            workspaceConfig.addProject(project);
        }
    }
}
