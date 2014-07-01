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
package de.cau.cs.kieler.klighd.ui.internal.handlers;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SaveAsDialog;

import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.KlighdDataManager.ExporterDescriptor;
import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.ui.internal.Messages;

/**
 * The 'save-as-image' dialog for Piccolo2D.
 * 
 * The available image formats are retrieved from the {@link ExporterManager#EXTP_ID_EXPORTERS}
 * extension point. An additional description of each format is added in parentheses (...), the
 * parentheses are stripped when the file extension is added.
 * 
 * @author mri
 * @author uru
 */
public class SaveAsImageDialog extends Dialog {

    /** the default dialog width. */
    private static final int DEFAULT_WIDTH = 500;
    /** the default dialog height. */
    private static final int DEFAULT_HEIGHT = 300;

    /** the preference key for the file path. */
    private static final String PREFERENCE_FILE_PATH = "saveAsImageDialog.filePath"; //$NON-NLS-1$
    /** the preference key for the workspace path. */
    private static final String PREFERENCE_WORKSPACE_PATH 
            = "saveAsImageDialog.workspacePath"; //$NON-NLS-1$
    /** the preference key for the image format. */
    private static final String PREFERENCE_IMAGE_FORMAT = "saveAsImageDialog.imageFormat"; //$NON-NLS-1$
    /** the preference key for the camera viewport. */
    private static final String PREFERENCE_CAMERA_VIEWPORT = "saveAsImageDialog."
            + "cameraViewport"; //$NON-NLS-1$
    /** the preference key for the scale factor. */
    private static final String PREFERENCE_SCALE_FACTOR = "saveAsImageDialog.scaleFactor"; //$NON-NLS-1$
    /** the preference key for the text as shapes property. */
    private static final String PREFERENCE_TEXT_AS_SHAPES 
        = "saveAsImageDialog.textAsShapes"; //$NON-NLS-1$
    /** the preference key for the embed fonts property. */
    private static final String PREFERENCE_EMBED_FONTS 
        = "saveAsImageDialog.embedFonts"; //$NON-NLS-1$

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
    /** the camera text as shapes checkbox. */
    private Button textAsShapesCheckbox;
    /** the camera embed fonts checkbox. */
    private Button embedFontsCheckbox;
    /** the message image. */
    private Label messageImageLabel;
    /** the message label. */
    private Label messageLabel;

    private Scale scaleSlider;

    /** the selected path. */
    private IPath path;
    /** whether the selected path is workspace relative. */
    private boolean workspacePath;
    /** whether to render through the camera view port. */
    private boolean cameraViewport;
    /** whether to transform text to shapes in vector graphics. */
    private boolean textAsShapes;
    /** whether the texts' fonts shall be embedded in the output. */
    private boolean embedFonts;
    /** the selected scaleFactor. */
    private int scaleFactor;

    /** the list of available export descriptors. */
    private List<ExporterDescriptor> descriptors;
    /** currently selected exporter. */
    private ExporterDescriptor currentExporter;

    /**
     * Constructs the dialog for saving the diagram as an image.
     * 
     * @param parentShell
     *            the parent shell
     */
    public SaveAsImageDialog(final Shell parentShell) {
        super(parentShell);
        // receive the preference store
        preferenceStore = KlighdUIPlugin.getDefault().getPreferenceStore();

        // get the available descriptors
        descriptors = KlighdDataManager.getInstance().getAvailableExporters();
        // sort by name
        Collections.sort(descriptors, new Comparator<ExporterDescriptor>() {
            /**
             * {@inheritDoc}
             */
            public int compare(final ExporterDescriptor e1, final ExporterDescriptor e2) {
                return e1.getFileExtension().compareTo(e2.getFileExtension());
            }
        });
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

    private static final int IMAGE_FORMAT_GROUP_COLUMNS = 3;
    private static final int IMAGE_FORMAT_COMBO_WIDTH_HINT = 210;
    private static final int IMAGE_FORMAT_SLIDER_MAX = 16;

    private void createImageFormatGroup(final Composite parent) {
        final Composite composite = createComposite(parent, IMAGE_FORMAT_GROUP_COLUMNS);

        // label
        Label label = new Label(composite, SWT.NONE);
        label.setText(Messages.SaveAsImageDialog_image_format_caption);

        // assemble the file extension descriptions
        String[] imageFormats = new String[descriptors.size()];
        int i = 0;
        for (ExporterDescriptor descr : descriptors) {
            String descrText =
                    descr.getDescription() != null ? " (" + descr.getDescription() + ")" : "";
            imageFormats[i++] = descr.getFileExtension() + descrText;
        }
        
        // image formats
        imageFormatCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        imageFormatCombo.setItems(imageFormats);

        // load image format index from preference store
        int index = preferenceStore.getInt(PREFERENCE_IMAGE_FORMAT);
        index = index < 0 || index >= imageFormats.length ? 0 : index;
        imageFormatCombo.setText(imageFormats[index]);
        imageFormatCombo.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                updateFileText();
                validateFileText();
            }
        });
        GridData gridData = new GridData(SWT.NONE);
        gridData.widthHint = IMAGE_FORMAT_COMBO_WIDTH_HINT;
        gridData.horizontalSpan = 2;
        imageFormatCombo.setLayoutData(gridData);

        label = new Label(composite, SWT.NONE);
        label.setText(Messages.SaveAsImageDialog_scale_factor);

        scaleSlider = new Scale(composite, SWT.HORIZONTAL);
        scaleSlider.setToolTipText("Scale factor"); //$NON-NLS-1$
        scaleSlider.setMinimum(1);
        scaleSlider.setMaximum(IMAGE_FORMAT_SLIDER_MAX);
        scaleSlider.setSelection(preferenceStore.getInt(PREFERENCE_SCALE_FACTOR));

        gridData = new GridData(SWT.LEFT, SWT.CENTER, true, false);
        gridData.minimumWidth = FILE_TEXT_WIDTH_HINT;
        scaleSlider.setLayoutData(gridData);

        final Label scaleVal = new Label(composite, SWT.BORDER);
        scaleVal.setText(String.valueOf(scaleSlider.getSelection()));
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        scaleVal.setLayoutData(gridData);

        scaleSlider.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                Scale s = ((Scale) e.widget);
                int n = s.getSelection();
                scaleVal.setText(String.valueOf(n));
                composite.layout();
            }
        });

    }

    private void createOptionsGroup(final Composite parent) {
        Composite composite = createComposite(parent, 1);

        // viewport
        cameraViewportCheckbox = new Button(composite, SWT.CHECK | SWT.LEFT);
        cameraViewportCheckbox.setText(Messages.SaveAsImageDialog_use_camera_viewport_caption);
        cameraViewportCheckbox.setSelection(preferenceStore.getBoolean(PREFERENCE_CAMERA_VIEWPORT));
        
        // text as shapes
        textAsShapesCheckbox = new Button(composite, SWT.CHECK | SWT.LEFT);
        textAsShapesCheckbox.setText(Messages.SaveAsImageDialog_text_as_shapes);
        textAsShapesCheckbox.setSelection(preferenceStore.getBoolean(PREFERENCE_TEXT_AS_SHAPES));
        
        // embed fonts
        embedFontsCheckbox = new Button(composite, SWT.CHECK | SWT.LEFT);
        embedFontsCheckbox.setText(Messages.SaveAsImageDialog_embed_fonts);
        embedFontsCheckbox.setSelection(preferenceStore.getBoolean(PREFERENCE_EMBED_FONTS));
        
        updateEmbedFontsCheckbox(textAsShapesCheckbox.getSelection(), embedFontsCheckbox.getSelection());
        textAsShapesCheckbox.addSelectionListener(new SelectionAdapter() {
            private boolean prevEmbedFonts = embedFontsCheckbox.getSelection();
            
            public void widgetSelected(final SelectionEvent e) {
                boolean selected = ((Button) e.widget).getSelection();
                if (selected) {
                    prevEmbedFonts = embedFontsCheckbox.getSelection();
                }
                updateEmbedFontsCheckbox(selected, prevEmbedFonts);
            }
        });
    }
    
    private void updateEmbedFontsCheckbox(final boolean disabled, final boolean prevSelection) {
        if (disabled) {
            embedFontsCheckbox.setEnabled(false);
            embedFontsCheckbox.setSelection(false);
        } else {
            embedFontsCheckbox.setSelection(prevSelection);
            embedFontsCheckbox.setEnabled(true);
        }
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
        String ext = imageFormatCombo.getText().toLowerCase();
        // remove any details contained in parentheses
        if (ext.contains("(")) {
            ext = ext.substring(0, ext.indexOf("(")).trim();
        }
        String[] extensions = { "*." + ext }; //$NON-NLS-1$
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
                String extDefault = imageFormatCombo.getText().toLowerCase();
                if (extDefault.contains("(")) {
                    extDefault = extDefault.substring(0, extDefault.indexOf("(")).trim();
                }
                fileText.setText(filePath.toString() + "." //$NON-NLS-1$
                        + extDefault);
            }
        }
    }

    private void updateFileText() {
        if (fileText.getText().length() > 0 && Path.ROOT.isValidPath(fileText.getText())) {
            IPath filePath = new Path(fileText.getText());
            String ext = imageFormatCombo.getText().toLowerCase();
            // remove any details contained in parentheses
            if (ext.contains("(")) {
                ext = ext.substring(0, ext.indexOf("(")).trim();
            }
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
        GridLayout gridLayout = new GridLayout(columns, false);
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;

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
     * @return the currentExporter
     */
    public ExporterDescriptor getCurrentExporter() {
        return currentExporter;
    }

    /**
     * Returns the scale factor to apply to the image while saving.
     * 
     * @return the the scale factor in range of 1 to {@link #IMAGE_FORMAT_SLIDER_MAX}-1.
     */
    public int getScaleFactor() {
        return scaleFactor;
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
     * Returns whether text in vector graphics should be rendered as shapes.
     * 
     * @return true if text should be rendered as shapes in vector graphics.
     */
    public boolean isTextAsShapes() {
        return textAsShapes;
    }

    /**
     * Returns whether the texts' fonts shall be embedded in the output.
     * 
     * @return true if the texts' fonts shall be embedded in the output.
     */
    public boolean isEmbedFonts() {
        return embedFonts;
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
        preferenceStore.setValue(PREFERENCE_SCALE_FACTOR, scaleSlider.getSelection());
        preferenceStore.setValue(PREFERENCE_TEXT_AS_SHAPES, textAsShapesCheckbox.getSelection());
        preferenceStore.setValue(PREFERENCE_EMBED_FONTS, embedFontsCheckbox.getSelection());
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
        path = new Path(fileText.getText());
        workspacePath = workspacePathCheckbox.getSelection();
        currentExporter = descriptors.get(imageFormatCombo.getSelectionIndex()); 
        cameraViewport = cameraViewportCheckbox.getSelection();
        textAsShapes = textAsShapesCheckbox.getSelection();
        embedFonts = embedFontsCheckbox.getSelection();
        scaleFactor = scaleSlider.getSelection();
        // has to be last because it disposes the dialog
        super.okPressed();
    }

}
