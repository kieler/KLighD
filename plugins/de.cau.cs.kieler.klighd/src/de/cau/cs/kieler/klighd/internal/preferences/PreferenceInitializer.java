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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import de.cau.cs.kieler.klighd.IKlighdPreferenceStore;
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
        initializeDefaultPreferences(KlighdPreferences.getPreferenceStore());
    }

    public void initializeDefaultPreferences(final IKlighdPreferenceStore preferenceStore) {

        // Set default option values
        preferenceStore.setDefault(KlighdPreferences.ANIMATE_LAYOUT,
                KlighdPreferences.ANIMATE_LAYOUT_DEFAULT);

        preferenceStore.setDefault(KlighdPreferences.ADVANCED_PANNING_MODE,
                KlighdPreferences.ADVANCED_PANNING_MODE_DEFAULT);

        preferenceStore.setDefault(KlighdPreferences.ZOOM_STYLE,
                KlighdPreferences.ZOOM_STYLE_DEFAULT);

        preferenceStore.setDefault(KlighdPreferences.ZOOM_TO_FOCUS_STYLE,
                KlighdPreferences.ZOOM_TO_FOCUS_STYLE_DEFAULT);

        preferenceStore.setDefault(KlighdPreferences.ZOOM_ON_WORKBENCHPART_CHANGE,
                KlighdPreferences.ZOOM_ON_WORKBENCHPART_CHANGE_DEFAULT);

        preferenceStore.setDefault(KlighdPreferences.EXPAND_SIDE_BAR,
                KlighdPreferences.EXPAND_SIDE_BAR_DEFAULT);

        preferenceStore.setDefault(KlighdPreferences.SHOW_ZOOM_CONFIG_BUTTONS,
                KlighdPreferences.SHOW_ZOOM_CONFIG_BUTTONS_DEFAULT);

        if (KlighdPreferences.USER_ZOOMING_MINIMAL_LEVEL_DEFAULT != null) {
            preferenceStore.setDefault(KlighdPreferences.USER_ZOOMING_MINIMAL_LEVEL,
                    KlighdPreferences.USER_ZOOMING_MINIMAL_LEVEL_DEFAULT.floatValue());
        }

        if (KlighdPreferences.USER_ZOOMING_MAXIMAL_LEVEL_DEFAULT != null) {
            preferenceStore.setDefault(KlighdPreferences.USER_ZOOMING_MAXIMAL_LEVEL,
                    KlighdPreferences.USER_ZOOMING_MAXIMAL_LEVEL_DEFAULT.floatValue());
        }


        // Magnification lens settings
        preferenceStore.setDefault(KlighdPreferences.MAGNIFICATION_LENS_ENABLED,
                KlighdPreferences.MAGNIFICATION_LENS_ENABLED_DEFAULT);

        preferenceStore.setDefault(KlighdPreferences.MAGNIFICATION_LENS_WIDTH,
                KlighdPreferences.MAGNIFICATION_LENS_WIDTH_DEFAULT);

        preferenceStore.setDefault(KlighdPreferences.MAGNIFICATION_LENS_HEIGHT,
                KlighdPreferences.MAGNIFICATION_LENS_HEIGHT_DEFAULT);

        preferenceStore.setDefault(KlighdPreferences.MAGNIFICATION_LENS_SCALE,
                KlighdPreferences.MAGNIFICATION_LENS_SCALE_DEFAULT);
    }
}
