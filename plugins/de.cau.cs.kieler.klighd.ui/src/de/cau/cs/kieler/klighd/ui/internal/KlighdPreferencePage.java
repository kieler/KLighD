/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

/**
 * Preference page for KLighD.
 *
 * @author cds
 * @author chsch
 */
public final class KlighdPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /** checkbox for animation. */
    private Button animationCheckBox;

    /** checkbox for advanced panning. */
    private Button advancedPanning;

    /** checkbox for expand side bar on initializing diagrams. */
    private Button expandSideBar;

    /** checkbox for show zoom buttons on initializing diagrams. */
    private Button showZoomConfigButtons;

    private static final String ADVANCED_PANNING_TOOLTIP =
            "If enabled diagram panning continues when mouse pointer leaves the diagram area and stops,"
            + " until it returns to diagram area or the mouse button is released.";

    private static final String EXPAND_SIDE_BAR_TOOLTIP =
            "Diagram side bars accommodate the controls for diagram options and layout options."
            + " If deactivated the side bars must be expanded manually.";

    private static final String SHOW_ZOOM_CONFIG_BUTTONS_TOOLTIP =
            "Zoom buttons are used to change the zooming behavior."
            + " If deactivated the zoom buttons are not visible.";

    /** checkbox for 'zoom on workbench part change'. */
    private Button zoomOnWorkbenchpartChange;

    /** radio button for zoom-to-fit. */
    private Button zoomToFit;
    /** radio button for zoomToFocus. */
    private Button zoomToFocus;
    /** radio button for no zoom. */
    private Button zoomNone;


    /** checkbox for setting the magnification lens enabled/disabled. */
    private Button magLensEnabled;

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
        final IPreferenceStore preferenceStore = getPreferenceStore();

        preferenceStore.setValue(KlighdPreferences.ANIMATE_LAYOUT,
                animationCheckBox.getSelection());

        preferenceStore.setValue(KlighdPreferences.ADVANCED_PANNING_MODE,
                advancedPanning.getSelection());

        preferenceStore.setValue(KlighdPreferences.EXPAND_SIDE_BAR,
                expandSideBar.getSelection());

        preferenceStore.setValue(KlighdPreferences.SHOW_ZOOM_CONFIG_BUTTONS,
                showZoomConfigButtons.getSelection());

        preferenceStore.setValue(KlighdPreferences.ZOOM_ON_WORKBENCHPART_CHANGE,
                zoomOnWorkbenchpartChange.getSelection());

        final ZoomStyle zoomStyle = getZoomStyleFromSelection();
        preferenceStore.setValue(KlighdPreferences.ZOOM_STYLE, zoomStyle.name());

        preferenceStore.setValue(KlighdPreferences.MAGNIFICATION_LENS_ENABLED,
                magLensEnabled.getSelection());
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

        final IPreferenceStore preferenceStore = getPreferenceStore();

        // Set default values
        animationCheckBox.setSelection(preferenceStore
                .getDefaultBoolean(KlighdPreferences.ANIMATE_LAYOUT));

        advancedPanning.setSelection(preferenceStore
                .getDefaultBoolean(KlighdPreferences.ADVANCED_PANNING_MODE));

        expandSideBar.setSelection(preferenceStore
                .getDefaultBoolean(KlighdPreferences.EXPAND_SIDE_BAR));

        showZoomConfigButtons.setSelection(preferenceStore
                .getDefaultBoolean(KlighdPreferences.SHOW_ZOOM_CONFIG_BUTTONS));

        zoomOnWorkbenchpartChange.setSelection(preferenceStore
                .getDefaultBoolean(KlighdPreferences.ZOOM_ON_WORKBENCHPART_CHANGE));

        setZoomStyleSelection(ZoomStyle.valueOf(preferenceStore
                .getDefaultString(KlighdPreferences.ZOOM_STYLE)));

        magLensEnabled.setSelection(preferenceStore
                .getDefaultBoolean(KlighdPreferences.MAGNIFICATION_LENS_ENABLED));
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

        final Composite composite = new Composite(parent, SWT.NONE);

        final GridLayout compositeLayout = new GridLayout(1, false);
        compositeLayout.verticalSpacing = 10;
        composite.setLayout(compositeLayout);

        final Group generalGroup = createGeneralGroup(composite);
        generalGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        final Group zoomGroup = createZoomToFitGroup(composite);
        zoomGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        final Group magnificationLensGroup = createMagnificationLensGroup(composite);
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
        final Group generalGroup = new Group(parent, SWT.NONE);
        generalGroup.setText(Messages.KlighdPreferencePage_generalOptions);
        generalGroup.setLayout(new RowLayout(SWT.VERTICAL));
        ((RowLayout) generalGroup.getLayout()).spacing = 5;

        // Layout Animation
        animationCheckBox = new Button(generalGroup, SWT.CHECK | SWT.LEFT);
        animationCheckBox.setText(Messages.KlighdPreferencePage_animateLayout_text);
        animationCheckBox.setToolTipText(Messages.KlighdPreferencePage_animateLayout_tooltip);
        animationCheckBox.setSelection(getPreferenceStore().getBoolean(
                KlighdPreferences.ANIMATE_LAYOUT));

        advancedPanning = new Button(generalGroup, SWT.CHECK | SWT.LEFT);
        advancedPanning.setText("Advanced panning");
        advancedPanning.setToolTipText(ADVANCED_PANNING_TOOLTIP);
        advancedPanning.setSelection(getPreferenceStore().getBoolean(
                KlighdPreferences.ADVANCED_PANNING_MODE));

        expandSideBar = new Button(generalGroup, SWT.CHECK | SWT.LEFT);
        expandSideBar.setText("Expand diagram side bar");
        expandSideBar.setToolTipText(EXPAND_SIDE_BAR_TOOLTIP);
        expandSideBar.setSelection(getPreferenceStore().getBoolean(
                KlighdPreferences.EXPAND_SIDE_BAR));

        return generalGroup;
    }

    private Group createZoomToFitGroup(final Composite parent) {
        // zoom styles

        final Group zoomGroup = new Group(parent, SWT.NONE);
        zoomGroup.setText("Diagram Zoom Options");
        zoomGroup.setLayout(new RowLayout(SWT.VERTICAL));
        ((RowLayout) zoomGroup.getLayout()).spacing = 5;

        final Label zoomSettingsLabel = new Label(zoomGroup, SWT.NONE);
        zoomSettingsLabel.setText("When opening a diagram:");

        final RowLayout zoomStyleGroupLayout = new RowLayout(SWT.HORIZONTAL);
        zoomStyleGroupLayout.marginLeft = 0;
        zoomStyleGroupLayout.marginTop = 0;
        zoomStyleGroupLayout.marginBottom = 5;
        final Composite zoomStyleGroup = new Composite(zoomGroup, SWT.NONE);
        zoomStyleGroup.setLayout(zoomStyleGroupLayout);

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
        setZoomStyleSelection(ZoomStyle.valueOf(getPreferenceStore().getString(
                KlighdPreferences.ZOOM_STYLE)));

        showZoomConfigButtons = new Button(zoomGroup, SWT.CHECK | SWT.LEFT);
        showZoomConfigButtons.setText("Show zoom configuration buttons in diagram");
        showZoomConfigButtons.setToolTipText(SHOW_ZOOM_CONFIG_BUTTONS_TOOLTIP);
        showZoomConfigButtons.setSelection(getPreferenceStore().getBoolean(
                KlighdPreferences.SHOW_ZOOM_CONFIG_BUTTONS));

        zoomOnWorkbenchpartChange = new Button(zoomGroup, SWT.CHECK | SWT.LEFT);
        zoomOnWorkbenchpartChange.setText(
                "Update layout and zoom after diagram window size changes");
        zoomOnWorkbenchpartChange.setSelection(getPreferenceStore().getBoolean(
                KlighdPreferences.ZOOM_ON_WORKBENCHPART_CHANGE));

        return zoomGroup;
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
        case ZOOM_TO_FOCUS_OR_INCREASE_TO_FIT:
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
            return ZoomStyle.getZoomToFocusStyle();
        } else {
            return ZoomStyle.NONE;
        }
    }


    private Group createMagnificationLensGroup(final Composite parent) {

        final Group magnificationLensGroup = new Group(parent, SWT.NONE);
        magnificationLensGroup.setText("Magification lens");
        magnificationLensGroup.setLayout(new RowLayout(SWT.VERTICAL));

        magLensEnabled = new Button(magnificationLensGroup, SWT.CHECK | SWT.LEFT);
        magLensEnabled.setText("Enable magnification lens, hit ALT + CTRL/CMD to use it");
        magLensEnabled.setSelection(getPreferenceStore().getBoolean(
                KlighdPreferences.MAGNIFICATION_LENS_ENABLED));

        final Composite magnificationLensSizeGroup =
                new Composite(magnificationLensGroup, SWT.NONE);

        // SUPPRESS CHECKSTYLE NEXT MagicNumber
        magnificationLensSizeGroup.setLayout(new GridLayout(6, false));
        magnificationLensSizeGroup.setEnabled(magLensEnabled.getSelection());

        magLensEnabled.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                magnificationLensSizeGroup.setEnabled(magLensEnabled.getSelection());
            }
        });

        // SUPPRESS CHECKSTYLE NEXT 30 MagicNumber

        final Label width = new Label(magnificationLensSizeGroup, SWT.NONE);
        width.setText("Width:");
        magLensWidth = new Spinner(magnificationLensSizeGroup, SWT.NONE);
        magLensWidth.setMinimum(100);
        magLensWidth.setMaximum(1000);
        magLensWidth.setIncrement(10);
        magLensWidth.setPageIncrement(50);
        magLensWidth.setSelection(getPreferenceStore().getInt(
                KlighdPreferences.MAGNIFICATION_LENS_WIDTH));

        final Label height = new Label(magnificationLensSizeGroup, SWT.NONE);
        height.setText("Height:");
        magLensHeight = new Spinner(magnificationLensSizeGroup, SWT.NONE);
        magLensHeight.setMinimum(100);
        magLensHeight.setMaximum(1000);
        magLensHeight.setIncrement(10);
        magLensHeight.setPageIncrement(50);
        magLensHeight.setSelection(getPreferenceStore().getInt(
                KlighdPreferences.MAGNIFICATION_LENS_HEIGHT));

        final Label scale = new Label(magnificationLensSizeGroup, SWT.NONE);
        scale.setText("Scale:");
        magLensScale = new Spinner(magnificationLensSizeGroup, SWT.NONE);
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
