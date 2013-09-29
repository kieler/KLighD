package de.cau.cs.kieler.klighd.ui.wizard;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.xpand2.XpandExecutionContextImpl;
import org.eclipse.xpand2.XpandFacade;
import org.eclipse.xpand2.output.Outlet;
import org.eclipse.xpand2.output.OutputImpl;
import org.eclipse.xtend.type.impl.java.JavaBeansMetaModel;
import org.eclipse.xtext.ui.util.IProjectFactoryContributor;
import org.eclipse.xtext.ui.util.PluginProjectFactory;
import org.eclipse.xtext.ui.util.ProjectFactory;
import org.eclipse.xtext.ui.wizard.AbstractProjectCreator;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class KlighdProjectCreator extends AbstractProjectCreator {

    @Inject
    private Provider<PluginProjectFactory> projectFactoryProvider;

    @Override
    protected ProjectFactory configureProjectFactory(final ProjectFactory factory) {
        PluginProjectFactory ppf = (PluginProjectFactory) factory;

        ppf.setProjectName(getProjectInfo().getProjectName());
        ppf.addFolders(getAllFolders());
        // ppf.addReferencedProjects(getReferencedProjects());
        ppf.addProjectNatures(getProjectNatures());
        ppf.addBuilderIds(getBuilders());

        ppf.addRequiredBundles(getRequiredBundles());
        ppf.addExportedPackages(getExportedPackages());
        ppf.addImportedPackages(getImportedPackages());
        ppf.setActivatorClassName(getActivatorClassName());

        factory.addContributor(new KlighdProjectContributor((KlighdProjectInfo) getProjectInfo()));

        return factory;
    }

    @Override
    protected PluginProjectFactory createProjectFactory() {
        return projectFactoryProvider.get();
        // return new PluginProjectFactory();
    }

    /**
     * @return the names of the exported packages. May not be <code>null</code>
     */
    protected List<String> getExportedPackages() {
        return Collections.emptyList();
    }

    /**
     * @return the names of the imported packages that a new project requires. May not be
     *         <code>null</code>
     */
    protected List<String> getImportedPackages() {
        return Lists.newArrayList("org.apache.log4j", "org.apache.commons.logging");
    }

    /**
     * @return the class-name of the bundle-activator or <code>null</code>
     */
    protected String getActivatorClassName() {
        return "TESTACTIVATOR";
    }

    /**
     * @return the names of the bundles that a new project requires. May not be <code>null</code>
     */
    protected List<String> getRequiredBundles() {
        return Lists.newArrayList("com.google.guava", "com.google.inject", "org.eclipse.xtend.lib",
                "org.eclipse.xtext.xbase.lib", "de.cau.cs.kieler.klighd",
                "de.cau.cs.kieler.core.krendering", "de.cau.cs.kieler.core.krendering.extensions",
                "de.cau.cs.kieler.kiml");
    }

    @Override
    protected String getModelFolderName() {
        return "MODELFOLDER";
    }

    @Override
    protected List<String> getAllFolders() {
        return Lists.newArrayList("src");
    }

}
