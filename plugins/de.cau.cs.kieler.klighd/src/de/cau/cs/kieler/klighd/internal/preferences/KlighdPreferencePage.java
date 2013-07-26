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
package de.cau.cs.kieler.klighd.internal.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.internal.Messages;

/**
 * Preference page for KLighD.
 * 
 * @author cds
 */
public final class KlighdPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /** checkbox for animation. */
    private Button animationCheckBox;
    /** checkbox for zoom-to-fit. */
    private Button zoomCheckBox;
    
    
    /**
     * Creates a new instance.
     */
    public KlighdPreferencePage() {
        super();
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////
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

        preferenceStore.setValue(KlighdPreferences.ANIMATE_LAYOUT, animationCheckBox.getSelection());
        preferenceStore.setValue(KlighdPreferences.ZOOM_TO_FIT, zoomCheckBox.getSelection());
        
        return true;
    }

    @Override
    protected void performDefaults() {
        super.performDefaults();
        
        IPreferenceStore preferenceStore = getPreferenceStore();
        
        // Set default values
        animationCheckBox.setSelection(
                preferenceStore.getDefaultBoolean(KlighdPreferences.ANIMATE_LAYOUT));
        zoomCheckBox.setSelection(
                preferenceStore.getDefaultBoolean(KlighdPreferences.ZOOM_TO_FIT));
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////
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
        
        return composite;
    }

    /**
     * Creates the group control that houses general layout options.
     * 
     * @param parent the parent control.
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
        animationCheckBox.setSelection(
                getPreferenceStore().getBoolean(KlighdPreferences.ANIMATE_LAYOUT));
        
        // Zoom-to-Fit
        zoomCheckBox = new Button(generalGroup, SWT.CHECK | SWT.LEFT);
        zoomCheckBox.setText(Messages.KlighdPreferencePage_zoomToFit_text);
        zoomCheckBox.setToolTipText(Messages.KlighdPreferencePage_zoomToFit_tooltip);
        zoomCheckBox.setSelection(
                getPreferenceStore().getBoolean(KlighdPreferences.ZOOM_TO_FIT));
        
        return generalGroup;
    }
    
    // End of UI code -- no magic numbers allowed anymore.
    // CHECKSTYLEON MagicNumber

}
