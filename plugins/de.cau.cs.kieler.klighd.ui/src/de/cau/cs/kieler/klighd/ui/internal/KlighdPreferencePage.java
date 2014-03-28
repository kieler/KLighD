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
package de.cau.cs.kieler.klighd.ui.internal;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.KlighdPreferences;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.Messages;

/**
 * Preference page for KLighD.
 * 
 * @author cds
 * @author chsch
 */
public final class KlighdPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /** checkbox for animation. */
    private Button animationCheckBox;

    /** group for zoom styles. */
    private Group zoomStyleGroup;
    /** radio button for zoom-to-fit. */
    private Button zoomToFit;
    /** radio button for zoomToFocus. */
    private Button zoomToFocus;
    /** radio button for no zoom. */
    private Button zoomNone;
    
    private Spinner magLensWidth;
    private Spinner magLensHeight;
    private Spinner magLensScale;
    
    /**
     * Creates a new instance.
     */
    public KlighdPreferencePage() {
        super();
    }

    // /////////////////////////////////////////////////////////////////////////////////////////
    // Handling of Preferences

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(KlighdPlugin.getDefault().getPreferenceStore());
    }

    @Override
    public boolean performOk() {
        IPreferenceStore preferenceStore = getPreferenceStore();

        preferenceStore
                .setValue(KlighdPreferences.ANIMATE_LAYOUT, animationCheckBox.getSelection());
        ZoomStyle zoomStyle = getZoomStyleFromSelection();
        preferenceStore.setValue(KlighdPreferences.ZOOM_STYLE, zoomStyle.name());
        
        preferenceStore.setValue(KlighdPreferences.MAGNIFICATION_LENS_WIDTH,
                magLensWidth.getSelection());
        preferenceStore.setValue(KlighdPreferences.MAGNIFICATION_LENS_HEIGHT,
                magLensHeight.getSelection());
        preferenceStore.setValue(KlighdPreferences.MAGNIFICATION_LENS_SCALE,
                magLensScale.getSelection());

        return true;
    }

    @Override
    protected void performDefaults() {
        super.performDefaults();

        IPreferenceStore preferenceStore = getPreferenceStore();

        // Set default values
        animationCheckBox.setSelection(preferenceStore
                .getDefaultBoolean(KlighdPreferences.ANIMATE_LAYOUT));
        setZoomStyleSelection(ZoomStyle.valueOf(preferenceStore
                .getDefaultString(KlighdPreferences.ZOOM_STYLE)));
        
        magLensWidth.setSelection(preferenceStore
                .getDefaultInt(KlighdPreferences.MAGNIFICATION_LENS_WIDTH));
        magLensHeight.setSelection(preferenceStore
                .getDefaultInt(KlighdPreferences.MAGNIFICATION_LENS_HEIGHT));
        magLensScale.setSelection(preferenceStore
                .getDefaultInt(KlighdPreferences.MAGNIFICATION_LENS_SCALE));

    }

    // /////////////////////////////////////////////////////////////////////////////////////////
    // UI Creation

    // UI code is allowed to use magic numbers
    // CHECKSTYLEOFF MagicNumber

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(final Composite parent) {

        Composite composite = new Composite(parent, SWT.NONE);

        GridLayout compositeLayout = new GridLayout(1, false);
        compositeLayout.verticalSpacing = 10;
        composite.setLayout(compositeLayout);

        Group generalGroup = createGeneralGroup(composite);
        generalGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        Group zoomGroup = createZoomToFitGroup(composite);
        zoomGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        
        Group magnificationLensGroup = createMagnificationLensConfig(composite);
        magnificationLensGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        return composite;
    }

    /**
     * Creates the group control that houses general layout options.
     * 
     * @param parent
     *            the parent control.
     * @return the group control.
     */
    private Group createGeneralGroup(final Composite parent) {
        Group generalGroup = new Group(parent, SWT.NONE);
        generalGroup.setText(Messages.KlighdPreferencePage_generalOptions);

        FillLayout groupLayout = new FillLayout(SWT.VERTICAL);
        groupLayout.marginWidth = 10;
        groupLayout.marginHeight = 5;
        generalGroup.setLayout(groupLayout);

        // Layout Animation
        animationCheckBox = new Button(generalGroup, SWT.CHECK | SWT.LEFT);
        animationCheckBox.setText(Messages.KlighdPreferencePage_animateLayout_text);
        animationCheckBox.setToolTipText(Messages.KlighdPreferencePage_animateLayout_tooltip);
        animationCheckBox.setSelection(getPreferenceStore().getBoolean(
                KlighdPreferences.ANIMATE_LAYOUT));

        return generalGroup;
    }
    
    private Group createZoomToFitGroup(final Composite parent) {
        // zoom styles
        zoomStyleGroup = new Group(parent, SWT.NONE);
        zoomStyleGroup.setText("Zoom Style");
        zoomStyleGroup.setLayout(new RowLayout(SWT.HORIZONTAL));

        // Zoom-to-Fit
        zoomToFit = new Button(zoomStyleGroup, SWT.RADIO | SWT.LEFT);
        zoomToFit.setText(Messages.KlighdPreferencePage_zoomToFit_text);
        zoomToFit.setToolTipText(Messages.KlighdPreferencePage_zoomToFit_tooltip);

        // zoom to focus
        zoomToFocus = new Button(zoomStyleGroup, SWT.RADIO | SWT.LEFT);
        zoomToFocus.setText(Messages.KlighdPreferencePage_zoomToFocus_text);
        zoomToFocus.setToolTipText(Messages.KlighdPreferencePage_zoomToFocus_tooltip);

        // no zoom
        zoomNone = new Button(zoomStyleGroup, SWT.RADIO | SWT.LEFT);
        zoomNone.setText(Messages.KlighdPreferencePage_zoomNone_text);
        zoomNone.setToolTipText(Messages.KlighdPreferencePage_zoomNone_tooltip);

        // Selection
        ZoomStyle initialStyle = ZoomStyle.valueOf(
                getPreferenceStore().getString(KlighdPreferences.ZOOM_STYLE));
        setZoomStyleSelection(initialStyle);
        
        return zoomStyleGroup;
    }
    
    // End of UI code -- no magic numbers allowed anymore.
    // CHECKSTYLEON MagicNumber

    private void setZoomStyleSelection(final ZoomStyle style) {
        switch (style) {
        case ZOOM_TO_FIT:
            zoomToFit.setSelection(true);
            break;
        case ZOOM_TO_FOCUS:
            zoomToFocus.setSelection(true);
            break;
        default: // NONE
            zoomNone.setSelection(true);
        }
    }

    private ZoomStyle getZoomStyleFromSelection() {
        if (zoomToFit.getSelection()) {
            return ZoomStyle.ZOOM_TO_FIT;
        } else if (zoomToFocus.getSelection()) {
            return ZoomStyle.ZOOM_TO_FOCUS;
        } else {
            return ZoomStyle.NONE;
        }
    }
    
    private Group createMagnificationLensConfig(final Composite parent) {
        // SUPPRESS CHECKSTYLE NEXT 35 MagicNumber
        
        final Group magnificationLensGroup = new Group(parent, SWT.NONE);
        magnificationLensGroup.setText("Magification lens");
        magnificationLensGroup.setLayout(new GridLayout(6, false));
        
        final Label width = new Label(magnificationLensGroup, SWT.NONE);
        width.setText("width:");
        magLensWidth = new Spinner(magnificationLensGroup, SWT.NONE);
        magLensWidth.setMinimum(100);
        magLensWidth.setMaximum(1000);
        magLensWidth.setIncrement(10);
        magLensWidth.setPageIncrement(50);
        magLensWidth.setSelection(getPreferenceStore().getInt(
                KlighdPreferences.MAGNIFICATION_LENS_WIDTH));
        
        final Label height = new Label(magnificationLensGroup, SWT.NONE);
        height.setText("height:");
        magLensHeight = new Spinner(magnificationLensGroup, SWT.NONE);
        magLensHeight.setMinimum(100);
        magLensHeight.setMaximum(1000);
        magLensHeight.setIncrement(10);
        magLensHeight.setPageIncrement(50);
        magLensHeight.setSelection(getPreferenceStore().getInt(
                KlighdPreferences.MAGNIFICATION_LENS_HEIGHT));
        
        final Label scale = new Label(magnificationLensGroup, SWT.NONE);
        scale.setText("scale:");
        magLensScale = new Spinner(magnificationLensGroup, SWT.NONE);
        magLensScale.setMinimum(50);
        magLensScale.setDigits(2);
        magLensScale.setMaximum(500);
        magLensScale.setIncrement(25);
        magLensScale.setPageIncrement(100);
        magLensScale.setSelection(getPreferenceStore().getInt(
                KlighdPreferences.MAGNIFICATION_LENS_SCALE));
        
        return magnificationLensGroup;
    }
}
