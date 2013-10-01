/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.util.PluginProjectFactory;
import org.eclipse.xtext.ui.util.ProjectFactory;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.ui.wizard.IProjectInfo;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Inspired by the {@link org.eclipse.xtext.ui.wizard.AbstractProjectCreator AbstractProjectCreator}
 * class. Responsible to create and configure the actual project, e.g., determines the required
 * bundles, what code to generate, and which extension points to create.
 * 
 * @author uru
 */
public class KlighdProjectCreator extends WorkspaceModifyOperation implements IProjectCreator {

    @Inject
    private Provider<PluginProjectFactory> projectFactoryProvider;

    private IFile result;
    private IProjectInfo projectInfo;

    /**
     * {@inheritDoc}
     */
    public void setProjectInfo(final IProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    /**
     * {@inheritDoc}
     */
    protected IProjectInfo getProjectInfo() {
        return projectInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final IProgressMonitor monitor) throws CoreException,
            InvocationTargetException, InterruptedException {
        SubMonitor subMonitor = SubMonitor.convert(monitor, getProjectInfo().getProjectName(), 2);
        try {
            final IProject project = createProject(subMonitor.newChild(1));
            if (project == null) {
                return;
            }
            IFile modelFile = getFileToOpen(project);
            this.result = modelFile;
        } finally {
            subMonitor.done();
        }
    }

    /**
     * @return plugin project factory
     */
    protected PluginProjectFactory createProjectFactory() {
        return projectFactoryProvider.get();
    }

    /**
     * @param monitor
     *            progress monitor
     * @return a created project.
     */
    protected IProject createProject(final IProgressMonitor monitor) {
        ProjectFactory factory = createProjectFactory();
        configureProjectFactory(factory);
        return factory.createProject(monitor, null);
    }

    /**
     * Configures the project factory, e.g., by adding all required bundles and exported packages.
     * 
     * @param factory
     *            project factory
     * @return the configured project factory
     */
    protected ProjectFactory configureProjectFactory(final ProjectFactory factory) {
        PluginProjectFactory ppf = (PluginProjectFactory) factory;

        ppf.setProjectName(getProjectInfo().getProjectName());
        ppf.addFolders(getAllFolders());
        ppf.addReferencedProjects(getReferencedProjects());
        ppf.addProjectNatures(getProjectNatures());
        ppf.addBuilderIds(getBuilders());

        ppf.addRequiredBundles(getRequiredBundles());
        ppf.addExportedPackages(getExportedPackages());
        ppf.addImportedPackages(getImportedPackages());
        ppf.setActivatorClassName(getActivatorClassName());

        factory.addContributor(new KlighdProjectContributor((KlighdProjectInfo) getProjectInfo()));

        return factory;
    }

    protected List<IProject> getReferencedProjects() {
        return Collections.emptyList();
    }

    protected String[] getProjectNatures() {
        return new String[] { JavaCore.NATURE_ID, "org.eclipse.pde.PluginNature", //$NON-NLS-1$
                XtextProjectHelper.NATURE_ID };
    }

    protected String[] getBuilders() {
        return new String[] { JavaCore.BUILDER_ID, "org.eclipse.pde.ManifestBuilder", //$NON-NLS-1$
                "org.eclipse.pde.SchemaBuilder", //$NON-NLS-1$
                XtextProjectHelper.BUILDER_ID };
    }

    protected List<String> getExportedPackages() {
        return Collections.emptyList();
    }

    protected List<String> getImportedPackages() {
        return Lists.newArrayList();
    }

    protected String getActivatorClassName() {
        return null;
    }

    protected IFile getFileToOpen(IProject project) {
        KlighdProjectInfo info = (KlighdProjectInfo) getProjectInfo();
        IFolder folder =
                project.getFolder(KlighdWizardSetup.SRC_FOLDER
                        + info.getTransformationPackage().replace(".", "/"));
        IFile file =
                folder.getFile(info.getTransformationName()
                        + (info.isCreateXtendFile() ? ".xtend" : ".java"));
        return file;
    }

    protected List<String> getRequiredBundles() {
        return KlighdWizardSetup.REQUIRED_BULDES;
    }

    protected List<String> getAllFolders() {
        return Lists.newArrayList(KlighdWizardSetup.SRC_FOLDER);
    }

    public IFile getResult() {
        return result;
    }

}
