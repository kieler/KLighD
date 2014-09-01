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

import de.cau.cs.kieler.klighd.IDiagramExporter.ExportData;
import de.cau.cs.kieler.klighd.IDiagramExporter.TilingData;
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
 * Provides the gathered information as an {@link ExportData} object.
 * 
 * @author mri
 * @author uru
 * @author csp
 */
public class SaveAsImageDialog extends Dialog {

    /** the default dialog width. */
    private static final int DEFAULT_WIDTH = 500;
    /** the default dialog height. */
    private static final int DEFAULT_HEIGHT = 330;

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
    /** the preference keys for the tiling information. */
    private static final String PREFERENCE_TILING_IS_MAXSIZE = 
            "saveAsImageDialog.tilingIsMaxsize"; //$NON-NLS-1$
    private static final String PREFERENCE_TILING_X = "saveAsImageDialog.tilingX"; //$NON-NLS-1$
    private static final String PREFERENCE_TILING_Y = "saveAsImageDialog.tilingY"; //$NON-NLS-1$

    /** the preference store. */
    private IPreferenceStore preferenceStore = null;

    /** the file text. */
    private Text fileText;
    /** the workspace path checkbox. */
    private Button workspacePathCheckbox;
    /** the file format combo. */
    private Combo imageFormatCombo;
    /** the tiling options button. **/
    private Button tilingOptionsButton;
    /** the tiling state label. **/
    private Label tilingStateLabel;
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

    /** the export data recorded created based on the form data. **/
    private ExportData exportData;

    /** the tilinginfo. **/
    private TilingData tilingInfo;

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
                return e1.fileExtension.compareTo(e2.fileExtension);
            }
        });
        
        // get the saved tiling info
        if (preferenceStore.getBoolean(PREFERENCE_TILING_IS_MAXSIZE)) {
            tilingInfo =
                    TilingData.createMaxSizeTiledData(preferenceStore.getInt(PREFERENCE_TILING_X),
                            preferenceStore.getInt(PREFERENCE_TILING_Y));
        } else {
            tilingInfo =
                    TilingData.createTiledData(preferenceStore.getInt(PREFERENCE_TILING_X),
                            preferenceStore.getInt(PREFERENCE_TILING_Y));
        }
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
        createFileGroup(composite);
        createImageFormatGroup(composite);
        createOptionsGroup(composite);
        createMessageGroup(composite);

        updateTilingOptions();
        return composite;
    }

    private static final int FILE_GROUP_COLUMNS = 3;
    private static final int FILE_TEXT_WIDTH_HINT = 300;
    private static final int BROWSE_WIDTH_HINT = 150;

    private void createFileGroup(final Composite parent) {
        final Composite composite = createComposite(parent, FILE_GROUP_COLUMNS);

        // label
        final Label label = new Label(composite, SWT.NONE);
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
            @Override
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
            @Override
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
            @Override
            public void widgetSelected(final SelectionEvent event) {
                handleFileSystemBrowse();
            }
        });
    }

    private static final int IMAGE_FORMAT_GROUP_COLUMNS = 4;
    private static final int IMAGE_FORMAT_COMBO_WIDTH_HINT = 210;
    private static final int IMAGE_FORMAT_SLIDER_MAX = 16;

    private void createImageFormatGroup(final Composite parent) {
        final Composite composite = createComposite(parent, IMAGE_FORMAT_GROUP_COLUMNS);

        // label
        Label label = new Label(composite, SWT.NONE);
        label.setText(Messages.SaveAsImageDialog_image_format_caption);

        // assemble the file extension descriptions
        final String[] imageFormats = new String[descriptors.size()];
        int i = 0;
        for (final ExporterDescriptor descr : descriptors) {
            final String descrText =
                    descr.description != null ? " (" + descr.description + ")" : "";
            imageFormats[i++] = descr.fileExtension + descrText;
        }
        
        // image formats
        imageFormatCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        imageFormatCombo.setItems(imageFormats);

        // load image format index from preference store
        int index = preferenceStore.getInt(PREFERENCE_IMAGE_FORMAT);
        index = index < 0 || index >= imageFormats.length ? 0 : index;
        imageFormatCombo.setText(imageFormats[index]);
        imageFormatCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                updateFileText();
                validateFileText();
                updateTilingOptions();
            }
        });
        GridData gridData;
        gridData = new GridData(SWT.NONE);
        gridData.widthHint = IMAGE_FORMAT_COMBO_WIDTH_HINT;
        gridData.horizontalSpan = IMAGE_FORMAT_GROUP_COLUMNS - 1;
        imageFormatCombo.setLayoutData(gridData);
        
        
        // scaling options
        label = new Label(composite, SWT.NONE);
        label.setText(Messages.SaveAsImageDialog_scale_factor);

        scaleSlider = new Scale(composite, SWT.HORIZONTAL);
        scaleSlider.setToolTipText("Scale factor"); //$NON-NLS-1$
        scaleSlider.setMinimum(1);
        scaleSlider.setMaximum(IMAGE_FORMAT_SLIDER_MAX);
        scaleSlider.setSelection(preferenceStore.getInt(PREFERENCE_SCALE_FACTOR));

        gridData = new GridData(SWT.LEFT, SWT.CENTER, true, false);
        gridData.minimumWidth = FILE_TEXT_WIDTH_HINT;
        gridData.horizontalSpan = IMAGE_FORMAT_GROUP_COLUMNS - 2;
        scaleSlider.setLayoutData(gridData);

        final Label scaleVal = new Label(composite, SWT.BORDER);
        scaleVal.setText(String.valueOf(scaleSlider.getSelection()));
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        scaleVal.setLayoutData(gridData);

        scaleSlider.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final Scale s = ((Scale) e.widget);
                final int n = s.getSelection();
                scaleVal.setText(String.valueOf(n));
                composite.layout();
            }
        });

        
        // tiling options
        label = new Label(composite, SWT.NONE);
        label.setText(Messages.SaveAsImageDialog_tiling);
        
        tilingStateLabel = new Label(composite, SWT.NONE);
        
        tilingOptionsButton = new Button(composite, SWT.PUSH);
        tilingOptionsButton.setText(Messages.SaveAsImageDialog_tiling_options_caption);
        
        gridData = new GridData(SWT.RIGHT, SWT.NONE, false, false);
        gridData.widthHint = BROWSE_WIDTH_HINT;
        gridData.horizontalSpan = IMAGE_FORMAT_GROUP_COLUMNS - 2;
        tilingOptionsButton.setLayoutData(gridData);
        
        tilingOptionsButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                final TilingDialog tilingDialog = new TilingDialog(getParentShell(), tilingInfo);
                tilingDialog.open();
                tilingInfo = tilingDialog.getTilingInfo();
                updateTilingOptions();
            }
        });
    }

    private void updateTilingOptions() {
        if (descriptors.get(imageFormatCombo.getSelectionIndex()).supportsTiling) {
            tilingOptionsButton.setEnabled(true);
            if (tilingInfo.isMaxsize) {
                tilingStateLabel.setText(tilingInfo.maxWidth + " px x "
                        + tilingInfo.maxHeight + " px");
            } else {
                tilingStateLabel.setText(tilingInfo.rows + " row(s) x " + tilingInfo.cols
                        + " column(s)");
            }
        } else {
            tilingOptionsButton.setEnabled(false);
            tilingStateLabel.setText("No tiling supported");
        }
        tilingStateLabel.getParent().layout();
    }

    private void createOptionsGroup(final Composite parent) {
        final Composite composite = createComposite(parent, 1);

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
            
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final boolean selected = ((Button) e.widget).getSelection();
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
        final Composite composite = createComposite(parent, 2);
        messageImageLabel = new Label(composite, SWT.NONE);
        messageImageLabel.setImage(JFaceResources.getImage(DLG_IMG_MESSAGE_ERROR));
        messageImageLabel.setVisible(false);
        messageLabel = new Label(composite, SWT.NONE);
        final GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        gridData.widthHint = MESSAGE_LABEL_WIDTH_HINT;
        messageLabel.setLayoutData(gridData);
        messageLabel.setVisible(false);
    }

    private void validateFileText() {
        if (fileText.getText().length() > 0 && Path.ROOT.isValidPath(fileText.getText())) {
            final IPath filePath = new Path(fileText.getText());
            final IPath containerPath = filePath.removeLastSegments(1);
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
                final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
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
                final File file = new File(filePath.toString());
                if (file.isDirectory()) {
                    // file path exists but describes a folder
                    setErrorStatus(Messages.SaveAsImageDialog_path_is_not_valid_error);
                    return;
                }
                final File container = new File(containerPath.toString());
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
        final FileDialog fileDialog = new FileDialog(getShell(), SWT.SAVE);
        // FIXME this does not always work ... if the dialog concats the
        // extension it does not check if that file exists
        fileDialog.setOverwrite(true);
        // extensions passed to the dialog have to include the '.'
        String ext = imageFormatCombo.getText().toLowerCase();
        // remove any details contained in parentheses
        if (ext.contains("(")) {
            ext = ext.substring(0, ext.indexOf("(")).trim();
        }
        final String[] extensions = { "*." + ext }; //$NON-NLS-1$
        fileDialog.setFilterExtensions(extensions);
        fileDialog.setText(Messages.SaveAsImageDialog_save_as_caption);
        // open the dialog
        final String selectedFile = fileDialog.open();
        // dialog has not been canceled
        if (selectedFile != null) {
            workspacePathCheckbox.setSelection(false);
            fileText.setText(selectedFile);
        }
    }

    private void handleWorkspaceBrowse() {
        // TODO a better workspace selection dialog would be good, but it seems
        // such a thing does not exist in Eclipse for some reason
        final SaveAsDialog fileDialog = new SaveAsDialog(getShell());
        final int status = fileDialog.open();
        if (status == SaveAsDialog.OK) {
            final IPath filePath = fileDialog.getResult();
            final String ext = filePath.getFileExtension();
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
            final IPath filePath = new Path(fileText.getText());
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
     * @return the currentExporter
     */
    public ExporterDescriptor getCurrentExporter() {
        return currentExporter;
    }

    /**
     * @return the export information.
     */
    public ExportData getExportData() {
        return exportData;
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
        preferenceStore.setValue(PREFERENCE_TILING_IS_MAXSIZE, tilingInfo.isMaxsize);
        if (tilingInfo.isMaxsize) {
            preferenceStore.setValue(PREFERENCE_TILING_X, tilingInfo.maxWidth);
            preferenceStore.setValue(PREFERENCE_TILING_Y, tilingInfo.maxHeight);
        } else {
            preferenceStore.setValue(PREFERENCE_TILING_X, tilingInfo.rows);
            preferenceStore.setValue(PREFERENCE_TILING_Y, tilingInfo.cols);
        }
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
        
        currentExporter = descriptors.get(imageFormatCombo.getSelectionIndex()); 
        exportData = new ExportData(currentExporter.subFormatId, new Path(fileText.getText()),
                workspacePathCheckbox.getSelection(), cameraViewportCheckbox.getSelection(),
                scaleSlider.getSelection(), textAsShapesCheckbox.getSelection(),
                embedFontsCheckbox.getSelection());

        if (currentExporter.supportsTiling && tilingInfo.isTiled) {
            exportData.setTilingInfo(tilingInfo);
        }

        // has to be last because it disposes the dialog
        super.okPressed();
    }

}
