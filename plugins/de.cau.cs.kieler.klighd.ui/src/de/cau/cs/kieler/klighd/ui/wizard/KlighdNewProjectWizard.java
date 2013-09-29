package de.cau.cs.kieler.klighd.ui.wizard;

import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.ui.wizard.IProjectInfo;
import org.eclipse.xtext.ui.wizard.XtextNewProjectWizard;

import com.google.inject.Inject;

public class KlighdNewProjectWizard extends XtextNewProjectWizard {

	private KlighdNewProjectCreationPage mainPage;

	@Inject
	public KlighdNewProjectWizard(IProjectCreator creator) {
		super(creator);
		 setWindowTitle("fooo");
		//		setDefaultPageImageDescriptor(Activator.getImageDescriptor("icons/wizban/newxprj_wiz.gif")); //$NON-NLS-1$
	}

	@Override
	public void addPages() {
		super.addPages();
		mainPage = new KlighdNewProjectCreationPage("mainPage", this.selection); //$NON-NLS-1$
		addPage(mainPage);
	}

	@Override
	protected IProjectInfo getProjectInfo() {
		KlighdProjectInfo projectInfo = new KlighdProjectInfo();
		// projectInfo.setCreateTestProject(true);
		// projectInfo.setCreateFeatureProject(mainPage.isCreateFeatureProject());
		// projectInfo.setFileExtension(mainPage.getFileExtensions());
		// projectInfo.setLanguageName(mainPage.getLanguageName());
		// projectInfo.setProjectName(mainPage.getProjectName());
		// projectInfo.setWorkingSets(mainPage.getSelectedWorkingSets());
		// Map<String, WizardContribution> contributions =
		// WizardContribution.getFromRegistry();
		// projectInfo.setWizardContribution(contributions.get(mainPage.getGeneratorConfig()));
		// projectInfo.setProjectsRootLocation(mainPage.getLocationPath());
		// projectInfo.setWorkbench(getWorkbench());
		// projectInfo.setCreateEclipseRuntimeLaunchConfig(!existsEclipseRuntimeLaunchConfig());
		// String encoding = null;
		// try {
		// encoding =
		// ResourcesPlugin.getWorkspace().getRoot().getDefaultCharset();
		// }
		// catch (final CoreException e) {
		// encoding = System.getProperty("file.encoding");
		// }
		// projectInfo.setEncoding(encoding);
		return projectInfo;
	}
}
