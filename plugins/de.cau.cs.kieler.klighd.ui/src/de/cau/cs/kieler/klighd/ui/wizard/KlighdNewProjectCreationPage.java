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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.ui.JavaUIMessages;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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

import com.google.common.base.Strings;

/**
 * A wizard page that lets the user specify the project, package, name, and language for a new
 * klighd diagram synthesis project.
 * 
 * @author uru
 */
@SuppressWarnings("restriction")
public class KlighdNewProjectCreationPage extends WizardNewProjectCreationPage {

    private Text transformationPackage;
    private Text transformationName;
    private Text sourceModel;
    private Button useJavaLang;
    private Button useXtendLang;

    /**
     * Create a new wizard configuration page.
     * 
     * @param pageName
     *            name of the page
     */
    public KlighdNewProjectCreationPage(final String pageName) {
        super(pageName);
        setTitle("KlighD Project Wizard");
        setDescription("Creates a new KlighD Project");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControl(final Composite parent) {
        setInitialProjectName(KlighdWizardSetup.DEFAULT_PROJECT); //$NON-NLS-1$
        super.createControl(parent);

        // query transformation details
        createTransformationSelectionGroup((Composite) getControl());

        // set default values
        setDefaults();
        Dialog.applyDialogFont(getControl());
    }

    /**
     * Sets default values for the text boxes and checkboxes.
     */
    protected void setDefaults() {
        transformationName.setText(KlighdWizardSetup.DEFAULT_TRANSFORMATION_NAME);
        transformationPackage.setText(KlighdWizardSetup.DEFAULT_PROJECT);
        useXtendLang.setSelection(true);

        validatePage();
    }

    /**
     * Creates all ui elements.
     * 
     * @param parent
     *            parent composite
     */
    protected void createTransformationSelectionGroup(final Composite parent) {
        Group transformationGroup = new Group(parent, SWT.NONE);
        transformationGroup.setFont(parent.getFont());
        transformationGroup.setText("Transformation");
        transformationGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
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
        typeGroup.setText("Source Model");
        typeGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
        // CHECKSTYLEOFF MagicNumber
        typeGroup.setLayout(new GridLayout(2, false));
        // CHECKSTYLEON MagicNumber
        typeGroup.setFont(parent.getFont());
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

            public void handleEvent(final Event event) {
                try {
                    // open a dialog querying for the source model type
                    IJavaSearchScope scope = SearchEngine.createWorkspaceScope();
                    SelectionDialog dialog =
                            JavaUI.createTypeDialog(parent.getShell(), new ProgressMonitorDialog(
                                    parent.getShell()), scope,
                                    IJavaElementSearchConstants.CONSIDER_ALL_TYPES, false);
                    dialog.setTitle("Select Source Model Type");
                    dialog.setMessage(JavaUIMessages.OpenTypeAction_dialogMessage);

                    int result = dialog.open();
                    if (result != IDialogConstants.OK_ID) {
                        return;
                    }

                    // retrieve the qulaified name
                    Object[] res = dialog.getResult();
                    if (res != null && res.length > 0) {
                        IJavaElement element = (IJavaElement) res[0];
                        if (element.getElementType() == IJavaElement.TYPE) {
                            IType type = (IType) element;
                            sourceModel.setText(type.getFullyQualifiedName());
                        }
                    }
                } catch (JavaModelException e) {
                    // fallback to object
                    sourceModel.setText(Object.class.getCanonicalName());
                }
            }
        };
        browseButton.addListener(SWT.MouseUp, browseListener);

        // Which language to use, Java or Xtend?
        final Group languageGroup = new Group(parent, SWT.NONE);
        languageGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
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
            public void handleEvent(final Event event) {
                setPageComplete(validatePage());
            }
        };
        transformationName.addListener(SWT.Modify, modifyListener);
        transformationPackage.addListener(SWT.Modify, modifyListener);
        useJavaLang.addListener(SWT.Modify, modifyListener);
        useXtendLang.addListener(SWT.Modify, modifyListener);
        sourceModel.addListener(SWT.Modify, modifyListener);
    }

    @Override
    protected boolean validatePage() {
        if (!super.validatePage()) {
            return false;
        }

        // check project name
        IStatus status =
                JavaConventions.validatePackageName(getProjectName(), JavaCore.VERSION_1_5,
                        JavaCore.VERSION_1_5);
        if (!status.isOK()) {
            setErrorMessage("Invalid Project Name" + status.getMessage());
            return false;
        }

        // check transformation name and package
        if (transformationName == null || transformationPackage == null) {
            return true;
        }
        if (Strings.isNullOrEmpty(transformationName.getText())
                || Strings.isNullOrEmpty(transformationPackage.getText())) {
            return false;
        }
        status =
                JavaConventions.validateJavaTypeName(transformationName.getText(),
                        JavaCore.VERSION_1_5, JavaCore.VERSION_1_5);
        if (!status.isOK()) {
            setErrorMessage("Invalid Transformation Name" + status.getMessage());
            return false;
        }

        // check source model
        if (sourceModel == null) {
            return true;
        }
        if (Strings.isNullOrEmpty(sourceModel.getText())) {
            return false;
        }
        status =
                JavaConventions.validateJavaTypeName(sourceModel.getText(), JavaCore.VERSION_1_5,
                        JavaCore.VERSION_1_5);
        if (!status.isOK()) {
            setErrorMessage("Invalid Source Model Type" + status.getMessage());
            return false;
        }

        setErrorMessage(null);
        setMessage(null);
        return true;
    }

    /**
     * @return the transformation name
     */
    public String getTransformationName() {
        return transformationName.getText();
    }

    /**
     * @return the transformation package
     */
    public String getTransformationPackage() {
        return transformationPackage.getText();
    }

    /**
     * @return the fully qualified source model name
     */
    public String getSourceModel() {
        return sourceModel.getText();
    }

    /**
     * @return whether to create an xtend file or a java file
     */
    public boolean isCreateXtendFile() {
        return useXtendLang.getSelection();
    }

}
