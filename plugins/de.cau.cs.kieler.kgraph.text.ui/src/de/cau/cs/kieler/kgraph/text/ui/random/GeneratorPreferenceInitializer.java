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
package de.cau.cs.kieler.kgraph.text.ui.random;

import java.lang.reflect.Field;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions;
import de.cau.cs.kieler.kgraph.text.ui.KGraphUiModule;
import de.cau.cs.kieler.kgraph.text.ui.internal.TextActivator;
import de.cau.cs.kieler.kgraph.text.ui.random.wizard.Messages;

/**
 * Preference initializer for graph generation options.
 *
 * @author msp
 */
public class GeneratorPreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeDefaultPreferences() {
        try {
            IPreferenceStore preferenceStore = TextActivator.getInstance().getPreferenceStore();
            for (Field field : GeneratorOptions.class.getFields()) {
                if (IProperty.class.isAssignableFrom(field.getType())) {
                    IProperty<?> option = (IProperty<?>) field.get(null);
                    Object value = option.getDefault();
                    if (value instanceof Boolean) {
                        preferenceStore.setDefault(option.getId(), (Boolean) value);
                    } else if (value instanceof Integer) {
                        preferenceStore.setDefault(option.getId(), (Integer) value);
                    } else if (value instanceof Float) {
                        preferenceStore.setDefault(option.getId(), (Float) value);
                    } else if (value instanceof Double) {
                        preferenceStore.setDefault(option.getId(), (Double) value);
                    } else if (value != null) {
                        preferenceStore.setDefault(option.getId(), value.toString());
                    }
                }
            }
        } catch (IllegalAccessException exception) {
            IStatus status = new Status(IStatus.ERROR, KGraphUiModule.PLUGIN_ID,
                    Messages.RandomGraphWizard_default_preferences_error, exception);
            StatusManager.getManager().handle(status);
        }
    }

}
