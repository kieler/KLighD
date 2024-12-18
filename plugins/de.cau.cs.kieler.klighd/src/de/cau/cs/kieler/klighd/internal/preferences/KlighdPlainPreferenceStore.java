/*
 * KLighD - KIELER Lightweight Diagrams
 *
 * https://github.com/Kieler/KLighD
 *
 * Copyright 2020 by TypeFox GmbH (typefox.io) and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.internal.preferences;

import java.util.HashMap;

import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;

import de.cau.cs.kieler.klighd.IKlighdPreferenceStore;

public class KlighdPlainPreferenceStore implements IKlighdPreferenceStore {

    private final HashMap<Object, Object> values = new HashMap<>();

    private final HashMap<Object, Object> defaults = new HashMap<>();

    public KlighdPlainPreferenceStore() {
        new PreferenceInitializer().initializeDefaultPreferences(this);
    }

    @Override
    public boolean getBoolean(final String preferenceId) {
        final Object value = this.values.get(preferenceId);
        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue();
        } else {
            final Object defaultValue = this.defaults.get(preferenceId);
            return defaultValue instanceof Boolean ? ((Boolean) defaultValue).booleanValue()
                    : BOOLEAN_DEFAULT_DEFAULT;
        }
    }

    @Override
    public double getDouble(final String preferenceId) {
        final Object value = this.values.get(preferenceId);
        if (value instanceof Double) {
            return ((Double) value).doubleValue();
        } else {
            final Object defaultValue = this.defaults.get(preferenceId);
            return defaultValue instanceof Double ? ((Double) defaultValue).doubleValue()
                    : DOUBLE_DEFAULT_DEFAULT;
        }
    }

    @Override
    public float getFloat(final String preferenceId) {
        final Object value = this.values.get(preferenceId);
        if (value instanceof Float) {
            return ((Float) value).floatValue();
        } else {
            final Object defaultValue = this.defaults.get(preferenceId);
            return defaultValue instanceof Float ? ((Float) defaultValue).floatValue()
                    : FLOAT_DEFAULT_DEFAULT;
        }
    }

    @Override
    public int getInt(final String preferenceId) {
        final Object value = this.values.get(preferenceId);
        if (value instanceof Integer) {
            return ((Integer) value).intValue();
        } else {
            final Object defaultValue = this.defaults.get(preferenceId);
            return defaultValue instanceof Integer ? ((Integer) defaultValue).intValue()
                    : INT_DEFAULT_DEFAULT;
        }
    }

    @Override
    public long getLong(final String preferenceId) {
        final Object value = this.values.get(preferenceId);
        if (value instanceof Long) {
            return ((Long) value).longValue();
        } else {
            final Object defaultValue = this.defaults.get(preferenceId);
            return defaultValue instanceof Long ? ((Long) defaultValue).longValue()
                    : LONG_DEFAULT_DEFAULT;
        }
    }

    @Override
    public String getString(final String preferenceId) {
        final Object value = this.values.get(preferenceId);
        if (value instanceof Integer) {
            return (String) value;
        } else {
            final Object defaultValue = this.defaults.get(preferenceId);
            return defaultValue instanceof String ? (String) defaultValue : STRING_DEFAULT_DEFAULT;
        }
    }

    @Override
    public void setValue(final String preferenceId, final boolean value) {
        this.values.put(preferenceId, Boolean.valueOf(value));
    }

    @Override
    public void setValue(final String preferenceId, final double value) {
        this.values.put(preferenceId, Double.valueOf(value));
    }

    @Override
    public void setValue(final String preferenceId, final float value) {
        this.values.put(preferenceId, Float.valueOf(value));
    }

    @Override
    public void setValue(final String preferenceId, final int value) {
        this.values.put(preferenceId, Integer.valueOf(value));
    }

    @Override
    public void setValue(final String preferenceId, final long value) {
        this.values.put(preferenceId, Long.valueOf(value));
    }

    @Override
    public void setValue(final String preferenceId, final String value) {
        this.values.put(preferenceId, value);
    }

    @Override
    public boolean isDefault(final String preferenceId) {
        return (!values.containsKey(preferenceId) && defaults.containsKey(preferenceId)
                || defaults.containsKey(preferenceId)
                        && defaults.get(preferenceId) == values.get(preferenceId));
    }

    @Override
    public void setToDefault(final String preferenceId) {
        this.values.remove(preferenceId);
    }

    @Override
    public boolean getDefaultBoolean(final String preferenceId) {
        final Object defaultValue = this.defaults.get(preferenceId);
        return defaultValue instanceof Boolean ? ((Boolean) defaultValue).booleanValue()
                : BOOLEAN_DEFAULT_DEFAULT;
    }

    @Override
    public double getDefaultDouble(final String preferenceId) {
        final Object defaultValue = this.defaults.get(preferenceId);
        return defaultValue instanceof Double ? ((Double) defaultValue).doubleValue()
                : DOUBLE_DEFAULT_DEFAULT;
    }

    @Override
    public float getDefaultFloat(final String preferenceId) {
        final Object defaultValue = this.defaults.get(preferenceId);
        return defaultValue instanceof Float ? ((Float) defaultValue).floatValue()
                : FLOAT_DEFAULT_DEFAULT;
    }

    @Override
    public int getDefaultInt(final String preferenceId) {
        final Object defaultValue = this.defaults.get(preferenceId);
        return defaultValue instanceof Integer ? ((Integer) defaultValue).intValue()
                : INT_DEFAULT_DEFAULT;
    }

    @Override
    public long getDefaultLong(final String preferenceId) {
        final Object defaultValue = this.defaults.get(preferenceId);
        return defaultValue instanceof Long ? ((Long) defaultValue).longValue()
                : LONG_DEFAULT_DEFAULT;
    }

    @Override
    public String getDefaultString(final String preferenceId) {
        final Object defaultValue = this.defaults.get(preferenceId);
        return defaultValue instanceof String ? (String) defaultValue : STRING_DEFAULT_DEFAULT;
    }

    @Override
    public void setDefault(final String preferenceId, final boolean value) {
        this.defaults.put(preferenceId, Boolean.valueOf(value));
    }

    @Override
    public void setDefault(final String preferenceId, final double value) {
        this.defaults.put(preferenceId, Double.valueOf(value));
    }

    @Override
    public void setDefault(final String preferenceId, final float value) {
        this.defaults.put(preferenceId, Float.valueOf(value));
    }

    @Override
    public void setDefault(final String preferenceId, final int value) {
        this.defaults.put(preferenceId, Integer.valueOf(value));
    }

    @Override
    public void setDefault(final String preferenceId, final long value) {
        this.defaults.put(preferenceId, Long.valueOf(value));
    }

    @Override
    public void setDefault(final String preferenceId, final String value) {
        this.defaults.put(preferenceId, value);
    }

    @Override
    public void addPreferenceChangeListener(IPreferenceChangeListener listener) {
    }

    @Override
    public void removePreferenceChangeListener(IPreferenceChangeListener listener) {
    }
}
