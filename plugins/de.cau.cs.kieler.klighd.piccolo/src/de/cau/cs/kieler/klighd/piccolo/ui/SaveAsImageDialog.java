/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.ui;

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
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
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

import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;

/**
 * The 'save-as-image' dialog for Piccolo.
 * 
 * @author mri
 */
public class SaveAsImageDialog extends Dialog {

    /** the default dialog width. */
    private static final int DEFAULT_WIDTH = 500;
    /** the default dialog height. */
    private static final int DEFAULT_HEIGHT = 270;

    /** the preference key for the file path. */
    private static final String PREFERENCE_FILE_PATH
        = "saveAsImageDialog.filePath"; //$NON-NLS-1$
    /** the preference key for the workspace path. */
    private static final String PREFERENCE_WORKSPACE_PATH
        = "saveAsImageDialog.workspacePath"; //$NON-NLS-1$
    /** the preference key for the image format. */
    private static final String PREFERENCE_IMAGE_FORMAT
        = "saveAsImageDialog.imageFormat"; //$NON-NLS-1$
    /** the preference key for the camera viewport. */
    private static final String PREFERENCE_CAMERA_VIEWPORT
        = "saveAsImageDialog.cameraViewport"; //$NON-NLS-1$

    /** the available image formats. */
    private static final String[] IMAGE_FORMATS
        = { "BMP", "JPG", "PNG" }; //$NON-NLS-1$

    /** the preference store. */
    private IPreferenceStore preferenceStore = null;

    /** the file text. */
    private Text fileText;
    /** the workspace path checkbox. */
    private Button workspacePathCheckbox;
    /** the file format combo. */
    private Combo imageFormatCombo;
    /** the camera viewport checkbox. */
    private Button cameraViewportCheckbox;
    /** the message image. */
    private Label messageImageLabel;
    /** the message label. */
    private Label messageLabel;

    /** the selected path. */
    private IPath path;
    /** whether the selected path is workspace relative. */
    private boolean workspacePath;
    /** the selected SWT image format. */
    private int swtImageFormat;
    /** whether to render through the camera viewport. */
    private boolean cameraViewport;

    /**
     * Constructs the dialog for saving a Piccolo scene graph as an image.
     * 
     * @param parentShell
     *            the parent shell
     */
    public SaveAsImageDialog(final Shell parentShell) {
        super(parentShell);
        // receive the preference store
        preferenceStore = KlighdPiccoloPlugin.getDefault().getPreferenceStore();
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
        createImageFormatGroup(composite);
        createOptionsGroup(composite);
        createMessageGroup(composite);

        return composite;
    }

    private static final int FILE_GROUP_COLUMNS = 3;
    private static final int FILE_TEXT_WIDTH_HINT = 300;
    private static final int BROWSE_WIDTH_HINT = 150;

    private void createFileGroup(final Composite parent) {
        Composite composite = createComposite(parent, FILE_GROUP_COLUMNS);

        // label
        Label label = new Label(composite, SWT.NONE);
        label.setText(Messages.SaveAsImageDialog_file_caption);

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
        button.setText(Messages.SaveAsImageDialog_browse_workspace_caption);
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
        workspacePathCheckbox.setText(Messages.SaveAsImageDialog_is_workspace_path_caption);
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        gridData.horizontalSpan = 2;
        workspacePathCheckbox.setLayoutData(gridData);
        // load option from preference store
        workspacePathCheckbox.setSelection(preferenceStore.getBoolean(PREFERENCE_WORKSPACE_PATH));
        workspacePathCheckbox.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                validateFileText();
            }
        });

        // browse file system button
        button = new Button(composite, SWT.PUSH);
        button.setText(Messages.SaveAsImageDialog_browse_file_system_caption);
        gridData = new GridData(SWT.RIGHT, SWT.NONE, true, false);
        gridData.widthHint = BROWSE_WIDTH_HINT;
        button.setLayoutData(gridData);
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                handleFileSystemBrowse();
            }
        });
    }

    private static final int IMAGE_FORMAT_COMBO_WIDTH_HINT = 210;

    private void createImageFormatGroup(final Composite parent) {
        Composite composite = createComposite(parent, 2);

        // label
        Label label = new Label(composite, SWT.NONE);
        label.setText(Messages.SaveAsImageDialog_image_format_caption);

        // image formats
        imageFormatCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        imageFormatCombo.setItems(IMAGE_FORMATS);
        // load image format index from preference store
        int index = preferenceStore.getInt(PREFERENCE_IMAGE_FORMAT);
        index = index < 0 || index >= IMAGE_FORMATS.length ? 0 : index;
        imageFormatCombo.setText(IMAGE_FORMATS[index]);
        imageFormatCombo.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                updateFileText();
                validateFileText();
            }
        });
        GridData gridData = new GridData(SWT.NONE);
        gridData.widthHint = IMAGE_FORMAT_COMBO_WIDTH_HINT;
        imageFormatCombo.setLayoutData(gridData);
    }

    private void createOptionsGroup(final Composite parent) {
        Composite composite = createComposite(parent, 1);

        // viewport
        cameraViewportCheckbox = new Button(composite, SWT.CHECK | SWT.LEFT);
        cameraViewportCheckbox.setText(Messages.SaveAsImageDialog_use_camera_viewport_caption);
        cameraViewportCheckbox.setSelection(preferenceStore.getBoolean(PREFERENCE_CAMERA_VIEWPORT));
    }

    private static final int MESSAGE_LABEL_WIDTH_HINT = 300;

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

    private void validateFileText() {
        if (fileText.getText().length() > 0 && Path.ROOT.isValidPath(fileText.getText())) {
            IPath filePath = new Path(fileText.getText());
            IPath containerPath = filePath.removeLastSegments(1);
            if (filePath.hasTrailingSeparator()) {
                // file describes a folder
                setErrorStatus(Messages.SaveAsImageDialog_path_is_not_valid_error);
                return;
            }
            if (workspacePathCheckbox.getSelection()) {
                // workspace path
                if (containerPath.segmentCount() == 0) {
                    // file path describes file outside a project
                    setErrorStatus(Messages.SaveAsImageDialog_file_outside_project_error);
                    return;
                }
                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                IResource resource = root.findMember(filePath);
                if (resource != null && resource.exists() && resource instanceof IContainer) {
                    // file path exists but describes a folder
                    setErrorStatus(Messages.SaveAsImageDialog_path_is_not_valid_error);
                    return;
                }
                resource = root.findMember(containerPath);
                if (resource == null || !resource.exists() || !(resource instanceof IContainer)) {
                    // container does not exist
                    setErrorStatus(Messages.SaveAsImageDialog_container_not_exist_error);
                    return;
                }
            } else {
                File file = new File(filePath.toString());
                if (file.isDirectory()) {
                    // file path exists but describes a folder
                    setErrorStatus(Messages.SaveAsImageDialog_path_is_not_valid_error);
                    return;
                }
                File container = new File(containerPath.toString());
                if (!container.exists()) {
                    // container does not exist
                    setErrorStatus(Messages.SaveAsImageDialog_container_not_exist_error);
                    return;
                }
            }
        } else {
            setErrorStatus(Messages.SaveAsImageDialog_path_is_not_valid_error);
            return;
        }
        setOKStatus();
    }

    private void handleFileSystemBrowse() {
        FileDialog fileDialog = new FileDialog(getShell(), SWT.SAVE);
        // FIXME this does not always work ... if the dialog concats the
        // extension it does not check if that file exists
        fileDialog.setOverwrite(true);
        // extensions passed to the dialog have to include the '.'
        String[] extensions = { "." + imageFormatCombo.getText().toLowerCase() }; //$NON-NLS-1$
        fileDialog.setFilterExtensions(extensions);
        fileDialog.setText(Messages.SaveAsImageDialog_save_as_caption);
        // open the dialog
        String selectedFile = fileDialog.open();
        // dialog has not been canceled
        if (selectedFile != null) {
            workspacePathCheckbox.setSelection(false);
            fileText.setText(selectedFile);
        }
    }

    private void handleWorkspaceBrowse() {
        // TODO a better workspace selection dialog would be good, but it seems
        // such a thing does not exist in Eclipse for some reason
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
                        + imageFormatCombo.getText().toLowerCase());
            }
        }
    }

    private void updateFileText() {
        if (fileText.getText().length() > 0 && Path.ROOT.isValidPath(fileText.getText())) {
            IPath filePath = new Path(fileText.getText());
            String ext = imageFormatCombo.getText().toLowerCase();
            if (filePath.getFileExtension() != null) {
                if (!filePath.getFileExtension().equals(ext)) {
                    fileText.setText(filePath.removeFileExtension().addFileExtension(ext)
                            .toString());
                }
            } else {
                fileText.setText(filePath.addFileExtension(ext).toString());
            }
        }
    }

    private void setErrorStatus(final String message) {
        messageLabel.setText(message);
        messageImageLabel.setVisible(true);
        messageLabel.setVisible(true);
        getButton(IDialogConstants.OK_ID).setEnabled(false);
        getButton(IDialogConstants.CANCEL_ID).getShell().setDefaultButton(
                getButton(IDialogConstants.CANCEL_ID));
    }

    private void setOKStatus() {
        messageImageLabel.setVisible(false);
        messageLabel.setVisible(false);
        getButton(IDialogConstants.OK_ID).setEnabled(true);
        getButton(IDialogConstants.OK_ID).getShell().setDefaultButton(
                getButton(IDialogConstants.OK_ID));
    }

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
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages.SaveAsImageDialog_title);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Point getInitialSize() {
        return new Point(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Returns the selected path.
     * 
     * @return the path
     */
    public IPath getFilePath() {
        return path;
    }

    /**
     * Returns whether the selected path is workspace relative.
     * 
     * @return true if the selected path is workspace relative; false else
     */
    public boolean isWorkspacePath() {
        return workspacePath;
    }

    /**
     * Returns the SWT code for the selected image format.
     * 
     * @return the SWT image format code
     */
    public int getSWTImageFormat() {
        return swtImageFormat;
    }

    /**
     * Returns whether to render the image through the camera viewport.
     * 
     * @return true to render the image through the camera viewport; false to render the whole scene
     *         graph without any view transformation
     */
    public boolean isCameraViewport() {
        return cameraViewport;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean close() {
        // save settings to preference store
        preferenceStore.setValue(PREFERENCE_FILE_PATH, fileText.getText());
        preferenceStore.setValue(PREFERENCE_WORKSPACE_PATH, workspacePathCheckbox.getSelection());
        preferenceStore.setValue(PREFERENCE_IMAGE_FORMAT, imageFormatCombo.getSelectionIndex());
        preferenceStore.setValue(PREFERENCE_CAMERA_VIEWPORT, cameraViewportCheckbox.getSelection());
        return super.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void okPressed() {
        // remember the dialog results
        // chsch: to make sure a valid extension is attached to the file name
        //  in case the combo is untouched 
        updateFileText();
        path = new Path(fileText.getText());
        workspacePath = workspacePathCheckbox.getSelection();
        swtImageFormat = swtImageFormatByIndex(imageFormatCombo.getSelectionIndex());
        cameraViewport = cameraViewportCheckbox.getSelection();
        // has to be last because it disposes the dialog
        super.okPressed();
    }

    private int swtImageFormatByIndex(final int index) {
        // CHECKSTYLEOFF MagicNumber
        switch (index) {
        case 1:
            return SWT.IMAGE_JPEG;
        case 2:
            return SWT.IMAGE_PNG;
        case 0:
        default:
            return SWT.IMAGE_BMP;
        }
        // CHECKSTYLEON MagicNumber
    }

}
