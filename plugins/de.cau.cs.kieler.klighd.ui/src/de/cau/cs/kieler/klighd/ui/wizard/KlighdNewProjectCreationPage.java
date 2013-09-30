package de.cau.cs.kieler.klighd.ui.wizard;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.core.BinaryType;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * 
 * 
 * @author uru
 */
public class KlighdNewProjectCreationPage extends WizardNewProjectCreationPage {

	private Text transformationPackage;
	private Text transformationName;
	private Text sourceModel;
	private Button useJavaLang;
	private Button useXtendLang;

	private SelectionDialog typeSelectionDialog;

	public KlighdNewProjectCreationPage(String pageName) {
		super(pageName);
		setTitle("KlighD Project Wizard");
		setDescription("Creates a new KlighD Project");
	}

	@Override
	public void createControl(Composite parent) {
		setInitialProjectName(KlighdWizardSetup.DEFAULT_PROJECT); //$NON-NLS-1$
		super.createControl(parent);

		// query transformation details
		createTransformationSelectionGroup((Composite) getControl());

		// set default values
		setDefaults();
		Dialog.applyDialogFont(getControl());
	}

	protected void setDefaults() {
		transformationName
				.setText(KlighdWizardSetup.DEFAULT_TRANSFORMATION_NAME);
		transformationPackage.setText(KlighdWizardSetup.DEFAULT_PROJECT);
		useXtendLang.setSelection(true);

		validatePage();
	}

	protected void createTransformationSelectionGroup(Composite parent) {
		Group transformationGroup = new Group(parent, SWT.NONE);
		transformationGroup.setFont(parent.getFont());
		transformationGroup.setText("Transformation");
		transformationGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true,
				false));
		transformationGroup.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(transformationGroup, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		composite.setLayout(new GridLayout(2, false));

		// Transformation Name
		Label transformationLabel = new Label(composite, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		transformationLabel.setText("Name:");

		transformationName = new Text(composite, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		transformationName.setLayoutData(data);
		transformationName.setFont(parent.getFont());

		// Transformation Package
		Label transformationPackageLabel = new Label(composite, SWT.NONE);
		GridData data2 = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		transformationPackageLabel.setText("Package:");

		transformationPackage = new Text(composite, SWT.BORDER);
		data2 = new GridData(GridData.FILL_HORIZONTAL);
		data2.horizontalSpan = 1;
		transformationPackage.setLayoutData(data);
		transformationPackage.setFont(parent.getFont());

		// Source Model
		final Group typeGroup = new Group(parent, SWT.NONE);
		typeGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		typeGroup.setLayout(new GridLayout(3, false));
		typeGroup.setFont(parent.getFont());
		// create the dialog
		createTypeSelectionDialog(typeGroup);
		// label
		Label sourceModelLabel = new Label(typeGroup, SWT.NONE);
		sourceModelLabel.setText("Source Model:");
		// text field
		sourceModel = new Text(typeGroup, SWT.BORDER);
		GridData textData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		textData.horizontalSpan = 1;
		textData.horizontalIndent = 0;
		sourceModel.setLayoutData(textData);
		// button
		Button browseButton = new Button(typeGroup, SWT.PUSH);
		browseButton.setText("Browse");
		browseButton.setFont(parent.getFont());
		Listener browseListener = new Listener() {

			public void handleEvent(Event event) {
				typeSelectionDialog.open();
				Object[] res = typeSelectionDialog.getResult();
				if (res != null && res.length > 0) {
					IJavaElement element = (IJavaElement) res[0];
					if (element.getElementType() == IJavaElement.TYPE) {
						IType type = (IType) element;
						sourceModel.setText(type.getFullyQualifiedName());
					}
				}
			}
		};
		browseButton.addListener(SWT.MouseUp, browseListener);

		// Which language to use, Java or Xtend?
		final Group languageGroup = new Group(parent, SWT.NONE);
		languageGroup
				.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		languageGroup.setLayout(new GridLayout(2, false));
		languageGroup.setText("Language");
		useJavaLang = new Button(languageGroup, SWT.RADIO);
		useJavaLang.setText("Java");
		useJavaLang.setLayoutData(new GridData());
		useXtendLang = new Button(languageGroup, SWT.RADIO);
		useXtendLang.setText("Xtend");
		useXtendLang.setLayoutData(new GridData());

		// Listen to changes
		Listener modifyListener = new Listener() {
			public void handleEvent(Event event) {
				setPageComplete(validatePage());
			}
		};
		transformationName.addListener(SWT.Modify, modifyListener);
		transformationPackage.addListener(SWT.Modify, modifyListener);
		useJavaLang.addListener(SWT.Modify, modifyListener);
		useXtendLang.addListener(SWT.Modify, modifyListener);
		sourceModel.addListener(SWT.Modify, modifyListener);
	}

	private void createTypeSelectionDialog(final Composite parent) {
		// search the whole workspace
		try {
			IJavaSearchScope scope = SearchEngine.createWorkspaceScope();

			typeSelectionDialog = JavaUI.createTypeDialog(parent.getShell(),
					new ProgressMonitorDialog(parent.getShell()), scope,
					IJavaElementSearchConstants.CONSIDER_ALL_TYPES, false);
		} catch (JavaModelException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected boolean validatePage() {
		if (!super.validatePage())
			return false;
		IStatus status = JavaConventions.validatePackageName(getProjectName(),
				JavaCore.VERSION_1_5, JavaCore.VERSION_1_5);
		if (!status.isOK()) {
			// setErrorMessage(Messages.WizardNewXtextProjectCreationPage_ErrorMessageProjectName
			// + status.getMessage());
			return false;
		}
		if (transformationName == null)
			return true;
		if (transformationName.getText().length() == 0)
			return false;

		status = JavaConventions.validateJavaTypeName(
				transformationName.getText(), JavaCore.VERSION_1_5,
				JavaCore.VERSION_1_5);
		if (!status.isOK()) {
			// setErrorMessage(Messages.WizardNewXtextProjectCreationPage_ErrorMessageLanguageName
			// + status.getMessage());
			return false;
		}
		// if (extensionsField.getText().length() == 0)
		// return false;
		setErrorMessage(null);
		setMessage(null);
		return true;
	}

	public String getTransformationName() {
		return transformationName.getText();
	}

	public String getTransformationPackage() {
		return transformationPackage.getText();
	}

	public String getSourceModel() {
		return sourceModel.getText();
	}

	public boolean isCreateXtendFile() {
		return useXtendLang.getSelection();
	}

}
