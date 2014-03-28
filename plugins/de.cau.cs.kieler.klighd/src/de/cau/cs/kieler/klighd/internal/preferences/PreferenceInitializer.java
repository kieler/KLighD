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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.KlighdPreferences;

/**
 * Initializes the KLighD preferences to their default values.
 * 
 * @author cds
 * @author chsch
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        
        // Set default option values
        preferenceStore.setDefault(
                KlighdPreferences.ANIMATE_LAYOUT, KlighdPreferences.ANIMATE_LAYOUT_DEFAULT);

        preferenceStore.setDefault(
                KlighdPreferences.ZOOM_STYLE, KlighdPreferences.ZOOM_STYLE_DEFAULT);

        // Magnification lens settings
        preferenceStore.setDefault(KlighdPreferences.MAGNIFICATION_LENS_WIDTH,
                KlighdPreferences.MAGNIFICATION_LENS_WIDTH_DEFAULT);
        preferenceStore.setDefault(KlighdPreferences.MAGNIFICATION_LENS_HEIGHT,
                KlighdPreferences.MAGNIFICATION_LENS_HEIGHT_DEFAULT);
        preferenceStore.setDefault(KlighdPreferences.MAGNIFICATION_LENS_SCALE,
                KlighdPreferences.MAGNIFICATION_LENS_SCALE_DEFAULT);
    }
}
