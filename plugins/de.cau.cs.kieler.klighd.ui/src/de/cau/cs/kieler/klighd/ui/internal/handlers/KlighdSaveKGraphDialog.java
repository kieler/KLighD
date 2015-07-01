/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.handlers;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SaveAsDialog;

import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.ui.internal.Messages;

/**
 * The 'KLighd-export-underlying-KGraph'-dialog.
 * 
 * This dialog provides the options to save the exported KGraph
 * to the workspace or the file system. The resulting file
 * has the '.kgx' extension.
 * 
 * @author mkr
 *
 */
public class KlighdSaveKGraphDialog extends Dialog {
    
    /** the preference key for the file path. */
    private static final String PREFERENCE_FILE_PATH 
                = "exportUnderlyingKGraphDialog.filePath"; //$NON-NLS-1$
    /** the preference key for the workspace path. */
    private static final String PREFERENCE_WORKSPACE_PATH
                = "exportUnderlyingKGraphDialog.workspacePath"; //$NON-NLS-1$
    /** default file extension. */
    private static final String EXTENSION = "kgx";
    
    /** the preference store. */
    private final IPreferenceStore preferenceStore = KlighdUIPlugin.getDefault().getPreferenceStore();
 
    /** the file text. */
    private Text fileText;
    /** the workspace path checkbox. */
    private Button workspacePathCheckbox;
    /** the resulting save path. */
    private IPath resultPath;
    /** flag indicates whether its a workspace path or not. */
    private Boolean isWorkspacePath;
       
    /**
     * Constructs the dialog for exporting a underlying KGraph. 
     * 
     * @param parentShell
     *            the parent shell
     */
    public KlighdSaveKGraphDialog(final Shell parentShell) {
       super(parentShell);
       this.isWorkspacePath = false;
       this.resultPath = null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(final Composite parent) {
        final Control control = super.createContents(parent);
        updateFileText();
        validateFileText();
        return control;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);
        createExportGroup(composite);
        
        return composite;
    }
    
    private static final int FILE_GROUP_COLUMNS = 3;
    private static final int FILE_TEXT_WIDTH_HINT = 300;
    private static final int BROWSE_WIDTH_HINT = 150;
    
    private void createExportGroup(final Composite parent) {
        final Composite composite = createComposite(parent, FILE_GROUP_COLUMNS);
        
        // label
        final Label label = new Label(composite, SWT.NONE);
        label.setText(Messages.ExportUnderlyingKGraphDialog_file_caption);
        
        // file path text
        fileText = new Text(composite, SWT.BORDER);
        // load path from preference store
        fileText.setText(preferenceStore.getString(PREFERENCE_FILE_PATH));
        fileText.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                validateFileText();
            }
        });
        GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        gridData.widthHint = FILE_TEXT_WIDTH_HINT;
        fileText.setLayoutData(gridData);
        
        // browse workspace button
        Button button = new Button(composite, SWT.PUSH);
        button.setText(Messages.ExportUnderlyingKGraphDialog_browse_workspace_caption);
        gridData = new GridData(SWT.RIGHT, SWT.NONE, true, false);
        gridData.widthHint = BROWSE_WIDTH_HINT;
        button.setLayoutData(gridData);
        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent event) {
                handleWorkspaceBrowse();
            }
        });
        
        // is workspace path checkbox
        workspacePathCheckbox = new Button(composite, SWT.CHECK | SWT.LEFT);
        workspacePathCheckbox.setText(Messages.ExportUnderlyingKGraphDialog_is_workspace_path_caption);
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        gridData.horizontalSpan = 2;
        workspacePathCheckbox.setLayoutData(gridData);
        // load option from preference store
        workspacePathCheckbox.setSelection(preferenceStore.getBoolean(PREFERENCE_WORKSPACE_PATH));
        workspacePathCheckbox.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent event) {
                validateFileText();
            }
        });

        // browse file system button
        button = new Button(composite, SWT.PUSH);
        button.setText(Messages.ExportUnderlyingKGraphDialog_browse_file_system_caption);
        gridData = new GridData(SWT.RIGHT, SWT.NONE, true, false);
        gridData.widthHint = BROWSE_WIDTH_HINT;
        button.setLayoutData(gridData);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                handleFileSystemBrowse();
            }
        });
    }
        
    private void validateFileText() {
        if (fileText.getText().length() > 0 && Path.ROOT.isValidPath(fileText.getText())) {
            final IPath filePath = new Path(fileText.getText());
            final IPath containerPath = filePath.removeLastSegments(1);
            if (filePath.hasTrailingSeparator()) {
                // file describes a folder
                setErrorStatus(Messages.ExportUnderlyingKGraphDialog_path_is_not_valid_error);
                return;
            }
            if (workspacePathCheckbox.getSelection()) {
                // workspace path
                if (containerPath.segmentCount() == 0) {
                    // file path describes file outside a project
                    setErrorStatus(Messages.ExportUnderlyingKGraphDialog_file_outside_project_error);
                    return;
                }
                final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                IResource resource = root.findMember(filePath);
                if (resource != null && resource.exists() && resource instanceof IContainer) {
                    // file path exists but describes a folder
                    setErrorStatus(Messages.ExportUnderlyingKGraphDialog_path_is_not_valid_error);
                    return;
                }
                resource = root.findMember(containerPath);
                if (resource == null || !resource.exists() || !(resource instanceof IContainer)) {
                    // container does not exist
                    setErrorStatus(Messages.ExportUnderlyingKGraphDialog_container_not_exist_error);
                    return;
                }
            } else {
                final File file = new File(filePath.toString());
                if (file.isDirectory()) {
                    // file path exists but describes a folder
                    setErrorStatus(Messages.ExportUnderlyingKGraphDialog_path_is_not_valid_error);
                    return;
                }
                final File container = new File(containerPath.toString());
                if (!container.exists()) {
                    // container does not exist
                    setErrorStatus(Messages.ExportUnderlyingKGraphDialog_container_not_exist_error);
                    return;
                }
            }
        } else {
            setErrorStatus(Messages.ExportUnderlyingKGraphDialog_path_is_not_valid_error);
            return;
        }
        setOKStatus();
    }
    
    private void handleWorkspaceBrowse() {
        // TODO a better workspace selection dialog would be good, but it seems
        // such a thing does not exist in Eclipse for some reason
        final SaveAsDialog fileDialog = new SaveAsDialog(getShell());
        final int status = fileDialog.open();
        if (status == Dialog.OK) {
            final IPath filePath = fileDialog.getResult();
            final String ext = filePath.getFileExtension();
            workspacePathCheckbox.setSelection(true);
            if (ext == null) {
                fileText.setText(filePath.toOSString() + "." + EXTENSION);           
            } else {
                fileText.setText(filePath.toOSString());
            }
        }
    }
    
    private void handleFileSystemBrowse() {
        final FileDialog fileDialog = new FileDialog(getShell(), SWT.SAVE);
        // FIXME this does not always work ... if the dialog concats the
        // extension it does not check if that file exists
        fileDialog.setOverwrite(true);
        // extensions passed to the dialog have to include the '.'
        String newExt = EXTENSION;
        if (newExt.charAt(0) != '.') {
            newExt = '.' + EXTENSION;
        }
        final String[] extensions = { newExt }; //$NON-NLS-1$
        final String[] descriptions = {"kgx"}; //$NON-NLS-1$
        fileDialog.setFilterExtensions(extensions);
        fileDialog.setFilterNames(descriptions);
        fileDialog.setText(Messages.ExportUnderlyingKGraphDialog_save_as_caption);
        // open the dialog
        final String selectedFile = fileDialog.open();
        // dialog has not been canceled
        if (selectedFile != null) {
            workspacePathCheckbox.setSelection(false);
            fileText.setText(selectedFile);
        }
    }
    
    private void updateFileText() {
        if (fileText.getText().length() > 0 && Path.ROOT.isValidPath(fileText.getText())) {
            final IPath filePath = new Path(fileText.getText());
            if (filePath.getFileExtension() != null) {
                if (!filePath.getFileExtension().equals(EXTENSION)) {
                    fileText.setText(filePath.removeFileExtension().addFileExtension(EXTENSION)
                            .toString());
                }
            } else {
                fileText.setText(filePath.addFileExtension(EXTENSION).toString());
            }
        }
    }
    
    private void setErrorStatus(final String message) {
        getButton(IDialogConstants.OK_ID).setEnabled(false);
        getButton(IDialogConstants.CANCEL_ID).getShell().setDefaultButton(
                getButton(IDialogConstants.CANCEL_ID));
    }

    private void setOKStatus() {
        getButton(IDialogConstants.OK_ID).setEnabled(true);
        getButton(IDialogConstants.OK_ID).getShell().setDefaultButton(
                getButton(IDialogConstants.OK_ID));
    }
    
    private Composite createComposite(final Composite parent, final int columns) {
        final Composite composite = new Composite(parent, SWT.NONE);
        final GridLayout gridLayout = new GridLayout(columns, false);
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;

        final GridData data = new GridData(SWT.FILL, SWT.NONE, true, false);
        composite.setLayoutData(data);
        composite.setLayout(gridLayout);
        return composite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages.ExportUnderlyingKGraphDialog_title);
    }
    
    /**
     * 
     * @return
     *          the path to save the exported KGraph
     */
    protected IPath getFilePath() {
        return resultPath;
    }
    
    /**
     *
     * @return
     *          true, if save to workspace, false otherwise
     */
    protected boolean isWorkspacePath() {
        return isWorkspacePath;
    }
     
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean close() {
        // save settings to preference store
        preferenceStore.setValue(PREFERENCE_FILE_PATH, fileText.getText());
        preferenceStore.setValue(PREFERENCE_WORKSPACE_PATH, workspacePathCheckbox.getSelection());
        return super.close();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void okPressed() {
        // remember the dialog results
        // chsch: to make sure a valid extension is attached to the file name
        // in case the combo is untouched
        updateFileText();

        resultPath = new Path(fileText.getText());
        isWorkspacePath = workspacePathCheckbox.getSelection();

        // has to be last because it disposes the dialog
        super.okPressed();
    }
}
