/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.views;

import java.io.File;
import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SaveAsDialog;

import de.cau.cs.kieler.kiml.formats.GraphFormatData;
import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.klighd.KlighdPlugin;

/**
 * The dialog that lets the user select a graph file format and a file to export a graph into.
 * 
 * @author mri
 * @author msp
 */
public class ExportDialog extends Dialog {

    /** the preference key for the file path. */
    private static final String PREFERENCE_FILE_PATH = "exportDialog.filePath"; //$NON-NLS-1$
    /** the preference key for the workspace path. */
    private static final String PREFERENCE_WORKSPACE_PATH = "exportDialog.workspacePath"; //$NON-NLS-1$
    /** the preference key for the selected exporter. */
    private static final String PREFERENCE_EXPORTER = "exportDialog.exporter"; //$NON-NLS-1$

    /** the preference store. */
    private IPreferenceStore preferenceStore;
    /** the file text. */
    private Text fileText;
    /** the workspace path checkbox. */
    private Button workspacePathCheckbox;
    /** the file format combo. */
    private Combo fileFormatCombo;
    /** the message image. */
    private Label messageImageLabel;
    /** the message label. */
    private Label messageLabel;
    /** the last selected graph format data. */
    private GraphFormatData lastFormat;
    /** the export file path. */
    private String exportFile;
    /** is the selected path relative to the workspace? */
    private boolean exportWorkspacePath;

    /**
     * Constructs an export dialog.
     * 
     * @param parent
     *            the parent shell
     */
    public ExportDialog(final Shell parent) {
        super(parent);
        // receive the preference store
        preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(final Composite parent) {
        Control control = super.createContents(parent);
        validateFileText();
        return control;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        createFileGroup(composite);
        createExportTypeGroup(composite);
        createMessageGroup(composite);
        return composite;
    }

    private static final int FILE_GROUP_COLUMNS = 3;
    private static final int FILE_TEXT_WIDTH_HINT = 300;
    private static final int BROWSE_WIDTH_HINT = 150;

    /**
     * Create group for file selection.
     * 
     * @param parent the parent composite
     */
    private void createFileGroup(final Composite parent) {
        Composite composite = createComposite(parent, FILE_GROUP_COLUMNS);
        // file label
        Label label = new Label(composite, SWT.NONE);
        label.setText("&File:");
        // file path text
        fileText = new Text(composite, SWT.BORDER);
        // load path from preference store
        String path = preferenceStore.getString(PREFERENCE_FILE_PATH);
        fileText.setText(path);
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
        button.setText("&Workspace...");
        gridData = new GridData(SWT.RIGHT, SWT.NONE, true, false);
        gridData.widthHint = BROWSE_WIDTH_HINT;
        button.setLayoutData(gridData);
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                handleWorkspaceBrowse();
            }
        });
        // is workspace path checkbox
        workspacePathCheckbox = new Button(composite, SWT.CHECK | SWT.LEFT);
        workspacePathCheckbox.setText("Is a workspace &path");
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        gridData.horizontalSpan = 2;
        workspacePathCheckbox.setLayoutData(gridData);
        // load option from preference store
        boolean workspacePath = preferenceStore.getBoolean(PREFERENCE_WORKSPACE_PATH);
        workspacePathCheckbox.setSelection(workspacePath);
        workspacePathCheckbox.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                validateFileText();
            }
        });
        // browse file system button
        button = new Button(composite, SWT.PUSH);
        button.setText("File &system...");
        gridData = new GridData(SWT.RIGHT, SWT.NONE, true, false);
        gridData.widthHint = BROWSE_WIDTH_HINT;
        button.setLayoutData(gridData);
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                handleFileSystemBrowse();
            }
        });
    }

    private static final int EXPORT_TYPE_COMBO_WIDTH_HINT = 210;

    /**
     * Create group for export file format selection.
     * 
     * @param parent the parent composite
     */
    private void createExportTypeGroup(final Composite parent) {
        Composite composite = createComposite(parent, 2);
        // label
        Label label = new Label(composite, SWT.NONE);
        label.setText("File f&ormat:");
        fileFormatCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        Collection<GraphFormatData> formatData = GraphFormatsService.getInstance().getFormatData();
        String[] formatNames = new String[formatData.size()];
        if (formatNames.length > 0) {
            int i = 0;
            for (GraphFormatData gfd : formatData) {
                formatNames[i++] = gfd.getName();
            }
            fileFormatCombo.setItems(formatNames);
            // get last exporter from preference store
            String lastFormatName = preferenceStore.getString(PREFERENCE_EXPORTER);
            if (lastFormatName.length() > 0) {
                fileFormatCombo.setText(lastFormatName);
            } else {
                fileFormatCombo.setText(formatNames[0]);
            }
            updateFormat();
        } else {
            fileFormatCombo.setEnabled(false);
        }
        fileFormatCombo.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                // this order is vital for the functionality of this dialog
                updateFormat();
                updateFileText();
                validateFileText();
            }
        });
        GridData gridData = new GridData(SWT.NONE);
        gridData.widthHint = EXPORT_TYPE_COMBO_WIDTH_HINT;
        fileFormatCombo.setLayoutData(gridData);
    }

    private static final int MESSAGE_LABEL_WIDTH_HINT = 300;

    /**
     * Create group for message display.
     * 
     * @param parent the parent composite
     */
    private void createMessageGroup(final Composite parent) {
        Composite composite = createComposite(parent, 2);
        messageImageLabel = new Label(composite, SWT.NONE);
        messageImageLabel.setImage(JFaceResources.getImage(DLG_IMG_MESSAGE_ERROR));
        messageImageLabel.setVisible(false);
        messageLabel = new Label(composite, SWT.NONE);
        GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        gridData.widthHint = MESSAGE_LABEL_WIDTH_HINT;
        messageLabel.setLayoutData(gridData);
        messageLabel.setVisible(false);
    }

    /**
     * Create and configure a composite with grid layout.
     * 
     * @param parent the parent composite
     * @param columns the number of columns
     * @return a new composite
     */
    private Composite createComposite(final Composite parent, final int columns) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = columns;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        gridLayout.makeColumnsEqualWidth = false;
        GridData data = new GridData(SWT.FILL, SWT.NONE, true, false);
        composite.setLayoutData(data);
        composite.setLayout(gridLayout);
        return composite;
    }

    /**
     * Validate the path given in the file selection text.
     */
    private void validateFileText() {
        if (fileText.getText().length() > 0 && Path.ROOT.isValidPath(fileText.getText())) {
            IPath filePath = new Path(fileText.getText());
            IPath containerPath = filePath.removeLastSegments(1);
            if (filePath.hasTrailingSeparator()) {
                // file describes a folder
                setErrorStatus("The specified file path is invalid.");
                return;
            }
            if (workspacePathCheckbox.getSelection()) {
                // workspace path
                if (containerPath.segmentCount() == 0) {
                    // file path describes file outside a project
                    setErrorStatus("The file has to be located inside a project.");
                    return;
                }
                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                IResource resource = root.findMember(filePath);
                if (resource != null && resource.exists() && resource instanceof IContainer) {
                    // file path exists but describes a folder
                    setErrorStatus("The specified file path is invalid.");
                    return;
                }
                resource = root.findMember(containerPath);
                if (resource == null || !resource.exists() || !(resource instanceof IContainer)) {
                    // container does not exist
                    setErrorStatus("The specified container does not exist.");
                    return;
                }
            } else {
                File file = new File(filePath.toString());
                if (file.isDirectory()) {
                    // file path exists but describes a folder
                    setErrorStatus("The specified file path is invalid.");
                    return;
                }
                File container = new File(containerPath.toString());
                if (!container.exists()) {
                    // container does not exist
                    setErrorStatus("The specified container does not exist.");
                    return;
                }
            }
        } else {
            setErrorStatus("The specified file path is invalid.");
            return;
        }
        setOKStatus();
    }

    /**
     * Open a file dialog for selection in the file system.
     */
    private void handleFileSystemBrowse() {
        FileDialog fileDialog = new FileDialog(getShell(), SWT.SAVE);
        // FIXME this does not always work ... if the dialog concatenates the extension
        // it does not check whether that file exists
        fileDialog.setOverwrite(true);
        // extensions passed to the dialog have to include the '.'
        String[] extensions = lastFormat.getExtensions().clone();
        for (int i = 0; i < extensions.length; ++i) {
            extensions[i] = "." + extensions[i]; //$NON-NLS-1$
        }
        fileDialog.setFilterExtensions(extensions);
        fileDialog.setText("Save As");
        // open the dialog
        String selectedFile = fileDialog.open();
        // dialog has not been canceled
        if (selectedFile != null) {
            workspacePathCheckbox.setSelection(false);
            fileText.setText(selectedFile);
        }
    }

    /**
     * Open a file dialog for selection in the workspace.
     */
    private void handleWorkspaceBrowse() {
        // TODO a better workspace selection dialog would be good, but it seems
        // such a thing does not exist in eclipse for some reason
        SaveAsDialog fileDialog = new SaveAsDialog(getShell());
        int status = fileDialog.open();
        if (status == SaveAsDialog.OK) {
            IPath filePath = fileDialog.getResult();
            String ext = filePath.getFileExtension();
            workspacePathCheckbox.setSelection(true);
            if (ext != null && ext.length() > 0) {
                fileText.setText(filePath.toString());
            } else {
                // if no file extension was specified take the default one
                fileText.setText(filePath.toString() + "." //$NON-NLS-1$
                        + lastFormat.getExtensions()[0]);
            }
        }
    }
    
    /**
     * Update the graph format from the combo selection.
     */
    private void updateFormat() {
        String formatName = fileFormatCombo.getItem(fileFormatCombo.getSelectionIndex());
        Collection<GraphFormatData> formatData = GraphFormatsService.getInstance().getFormatData();
        for (GraphFormatData gfd : formatData) {
            if (gfd.getName().equals(formatName)) {
                lastFormat = gfd;
                break;
            }
        }
    }

    /**
     * Update the file text from the selected file format (adapt extension).
     */
    private void updateFileText() {
        if (fileText.getText().length() > 0 && Path.ROOT.isValidPath(fileText.getText())) {
            IPath filePath = new Path(fileText.getText());
            if (filePath.getFileExtension() != null) {
                if (!filePath.getFileExtension().equals(lastFormat.getExtensions()[0])) {
                    fileText.setText(filePath.removeFileExtension()
                            .addFileExtension(lastFormat.getExtensions()[0]).toString());
                }
            } else {
                fileText.setText(filePath.addFileExtension(lastFormat.getExtensions()[0])
                        .toString());
            }
        }
    }

    /**
     * Set an error status in the message label.
     * 
     * @param message the new message
     */
    private void setErrorStatus(final String message) {
        messageLabel.setText(message);
        messageImageLabel.setVisible(true);
        messageLabel.setVisible(true);
        getButton(IDialogConstants.OK_ID).setEnabled(false);
        getButton(IDialogConstants.CANCEL_ID).getShell().setDefaultButton(
                getButton(IDialogConstants.CANCEL_ID));
    }

    /**
     * Set an ok status in the message label.
     */
    private void setOKStatus() {
        messageImageLabel.setVisible(false);
        messageLabel.setVisible(false);
        getButton(IDialogConstants.OK_ID).setEnabled(true);
        getButton(IDialogConstants.OK_ID).getShell().setDefaultButton(
                getButton(IDialogConstants.OK_ID));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText("Export Graph");
    }

    /**
     * Returns the selected exporter.
     * 
     * @return the selected exporter
     */
    public GraphFormatData getFormat() {
        return lastFormat;
    }

    /**
     * Returns the selected export file path.
     * 
     * @return the selected path or null if the dialog has not successfully finished
     */
    public String getExportFile() {
        return exportFile;
    }

    /**
     * Returns whether the selected export file path is relative to the workspace.
     * 
     * @return true if the selected export file path is relative to the workspace
     */
    public boolean isExportWorkspacePath() {
        return exportWorkspacePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean close() {
        // save settings to preference store
        preferenceStore.setValue(PREFERENCE_FILE_PATH, fileText.getText());
        preferenceStore.setValue(PREFERENCE_WORKSPACE_PATH, workspacePathCheckbox.getSelection());
        String formatName = fileFormatCombo.getItem(fileFormatCombo.getSelectionIndex());
        preferenceStore.setValue(PREFERENCE_EXPORTER, formatName);
        return super.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void okPressed() {
        // create export configuration
        exportFile = fileText.getText();
        exportWorkspacePath = workspacePathCheckbox.getSelection();
        // has to be last because it disposes the dialog
        super.okPressed();
    }

}
