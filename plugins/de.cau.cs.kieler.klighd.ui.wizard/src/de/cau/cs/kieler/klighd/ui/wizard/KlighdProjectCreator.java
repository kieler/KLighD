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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.util.PluginProjectFactory;
import org.eclipse.xtext.ui.util.ProjectFactory;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.ui.wizard.IProjectInfo;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
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

    /**
     * Specialization in order to be able to configure the JRE container.
     * Implementation is quite ugly and shall be replaced once
     * <a href=https://bugs.eclipse.org/bugs/show_bug.cgi?id=467391>
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=467391</a> is resolved.
     *  
     * @author chsch
     */
    public static class KlighdProjectFactory extends PluginProjectFactory {
        
        private static final String J2SE_15 = "J2SE-1.5";
        private static final String JAVA_SE_1 = "JavaSE-1.";

        private String jreContainer = J2SE_15;
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void setBreeToUse(final String breeToUse) {
            super.setBreeToUse(breeToUse);
            this.jreContainer = breeToUse;
        }

        private static final Predicate<IClasspathEntry> CLASSPATH_FILTER = 
                new Predicate<IClasspathEntry>() {

            @SuppressWarnings("restriction")
            public boolean apply(final IClasspathEntry input) {
                
                // I explicitly need to check for 'ClasspathEntry' as the path can't
                //  be modified via just 'IClasspathEntry', see below
                if (input instanceof org.eclipse.jdt.internal.core.ClasspathEntry) {
                    final IPath path = input.getPath();
                    return path != null && JavaRuntime.JRE_CONTAINER.equals(path.segment(0));
                    
                } else {
                    return false;
                }
            }
        };

        /**
         * {@inheritDoc}
         */
        @Override
        @SuppressWarnings("restriction")
        protected void addMoreClasspathEntriesTo(final List<IClasspathEntry> classpathEntries) {

            final org.eclipse.jdt.internal.core.ClasspathEntry jre =
                    (org.eclipse.jdt.internal.core.ClasspathEntry) 
                            Iterables.find(classpathEntries, CLASSPATH_FILTER, null);

            if (jre != null) {
                final String lastSegment = jre.path.lastSegment();
    
                if (lastSegment.equals(J2SE_15) || lastSegment.startsWith(JAVA_SE_1)) {
                    jre.path = jre.path.uptoSegment(2).append(jreContainer);
                }
            }

            super.addMoreClasspathEntriesTo(classpathEntries);
        }
    }
    
    @Inject
    private Provider<KlighdProjectFactory> projectFactoryProvider;

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
    protected KlighdProjectFactory createProjectFactory() {
        // need to instantiate via injection since
        //  the base class 'ProjectFactory' has fields requiring injection
        return projectFactoryProvider.get();
    }

    /**
     * @param monitor
     *            progress monitor
     * @return a created project.
     */
    protected IProject createProject(final IProgressMonitor monitor) {
        return createAndConfigureProjectFactory().createProject(monitor, null);
    }

    /**
     * Composes the project factory, e.g., by adding all required bundles and exported packages.
     * 
     * @return the configured project factory
     */
    protected ProjectFactory createAndConfigureProjectFactory() {
        final KlighdProjectInfo info = (KlighdProjectInfo) getProjectInfo();

        final PluginProjectFactory ppf = createProjectFactory();

        ppf.setProjectName(info.getProjectName());
        ppf.setLocation(info.getProjectLocation());
        ppf.addFolders(getAllFolders());
        ppf.addReferencedProjects(getReferencedProjects());
        ppf.addProjectNatures(getProjectNatures());
        ppf.addBuilderIds(getBuilders());
        if (info.isCreateXtendFile()) {            
            ppf.addFolders(Lists.newArrayList("xtend-gen"));
        }

        ppf.setBreeToUse(info.getExecutionEnvironment());
        ppf.addRequiredBundles(getRequiredBundles());
        ppf.addExportedPackages(getExportedPackages());
        ppf.addImportedPackages(getImportedPackages());
        ppf.setActivatorClassName(getActivatorClassName());

        ppf.addContributor(new KlighdProjectContributor((KlighdProjectInfo) getProjectInfo()));

        return ppf;
    }

    /**
     * @return the projects to be referenced
     */
    protected List<IProject> getReferencedProjects() {
        return Collections.emptyList();
    }

    /**
     * @return the required project natures
     */
    @SuppressWarnings("restriction")
    protected String[] getProjectNatures() {
        return new String[] {
                JavaCore.NATURE_ID,
                org.eclipse.pde.internal.core.natures.PDE.PLUGIN_NATURE,
                XtextProjectHelper.NATURE_ID
            };
    }

    /**
     * @return the required builders
     */
    @SuppressWarnings("restriction")
    protected String[] getBuilders() {
        return new String[] {
                JavaCore.BUILDER_ID,
                org.eclipse.pde.internal.core.natures.PDE.MANIFEST_BUILDER_ID,
                org.eclipse.pde.internal.core.natures.PDE.SCHEMA_BUILDER_ID,
                XtextProjectHelper.BUILDER_ID
            };
    }

    /**
     * @return the exported packages
     */
    protected List<String> getExportedPackages() {
        return Collections.emptyList();
    }

    /**
     * @return the imported packages
     */
    protected List<String> getImportedPackages() {
        KlighdProjectInfo info = (KlighdProjectInfo) getProjectInfo();
        String packageString =
                info.getSourceModelClassFullyQualified().substring(0,
                        info.getSourceModelClassFullyQualified().lastIndexOf('.'));
        if (packageString.startsWith("java.")) {
            return Lists.newArrayList();
        } else {
            return Lists.newArrayList(packageString);
        }
    }
    
    /**
     * @return the name of the activator class
     */
    protected String getActivatorClassName() {
        return null;
    }

    /**
     * @param project
     *            the project meta data
     * 
     * @return the file to be opened after project creation
     */
    protected IFile getFileToOpen(final IProject project) {
        KlighdProjectInfo info = (KlighdProjectInfo) getProjectInfo();
        IFolder folder =
                project.getFolder(KlighdWizardSetup.SRC_FOLDER
                        + info.getTransformationPackage().replace(".", "/"));
        IFile file =
                folder.getFile(info.getTransformationName()
                        + (info.isCreateXtendFile() ? ".xtend" : ".java"));
        return file;
    }

    /**
     * @return the required bundles
     */
    protected List<String> getRequiredBundles() {
        return KlighdWizardSetup.REQUIRED_BUNDLES;
    }

    /**
     * @return the folders being created within the project
     */
    protected List<String> getAllFolders() {
        return Lists.newArrayList(KlighdWizardSetup.SRC_FOLDER);
    }

    /**
     * {@inheritDoc}
     */
    public IFile getResult() {
        return result;
    }

}
