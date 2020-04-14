/*
 * KLighD - KIELER Lightweight Diagrams
 *
 * https://github.com/Kieler/KLighD
 *
 * Copyright 2020 by TypeFox GmbH (typefox.io) and others.
 *
 * This code is provided under the terms of the Eclipse Public License 1.0 (EPL-1.0).
*/
package de.cau.cs.kieler.klighd.ui.internal.preferences;

import java.util.Map;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.IKlighdPreferenceStore;
import de.cau.cs.kieler.klighd.Klighd;

public class KlighdPlatformPreferenceStore implements IKlighdPreferenceStore {
    
    public final static KlighdPlatformPreferenceStore INSTANCE = new KlighdPlatformPreferenceStore();
    
    // this field is package protected for use in the 'KlighdPreferencePage'
    final org.eclipse.jface.preference.IPreferenceStore delegate;
    
    // this field is actually not required except to satisfy some check in the constructor of
    //  org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent called below
    private final IEclipsePreferences prefNode = InstanceScope.INSTANCE.getNode(Klighd.PLUGIN_ID);
    
    private KlighdPlatformPreferenceStore() {
        delegate = new ScopedPreferenceStore(InstanceScope.INSTANCE, Klighd.PLUGIN_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getBoolean(String name) {
        return delegate.getBoolean(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDouble(String name) {
        return delegate.getDouble(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getFloat(String name) {
        return delegate.getFloat(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInt(String name) {
        return delegate.getInt(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLong(String name) {
        return delegate.getLong(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getString(String name) {
        return delegate.getString(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String name, boolean value) {
        delegate.setValue(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String name, double value) {
        delegate.setValue(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String name, float value) {
        delegate.setValue(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String name, int value) {
        delegate.setValue(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String name, long value) {
        delegate.setValue(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String name, String value) {
        delegate.setValue(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDefault(String name) {
        return delegate.isDefault(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setToDefault(String name) {
        delegate.setToDefault(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getDefaultBoolean(String name) {
        return delegate.getDefaultBoolean(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDefaultDouble(String name) {
        return delegate.getDefaultDouble(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getDefaultFloat(String name) {
        return delegate.getDefaultFloat(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDefaultInt(String name) {
        return delegate.getDefaultInt(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getDefaultLong(String name) {
        return delegate.getDefaultLong(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultString(String name) {
        return delegate.getDefaultString(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefault(String name, boolean value) {
        delegate.setDefault(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefault(String name, double value) {
        delegate.setDefault(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefault(String name, float value) {
        delegate.setDefault(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefault(String name, int value) {
        delegate.setDefault(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefault(String name, long value) {
        delegate.setDefault(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefault(String name, String defaultObject) {
        delegate.setDefault(name, defaultObject);
    }

    private final Map<IPreferenceChangeListener, IPropertyChangeListener> listeners = Maps.newHashMap();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPreferenceChangeListener(IPreferenceChangeListener listener) {
        synchronized (this) {
            if (!listeners.containsKey(listener)) {
                final IPropertyChangeListener alias = e -> {
                    listener.preferenceChange(new PreferenceChangeEvent(
                            prefNode, e.getProperty(), e.getOldValue(), e.getNewValue()));
                };
                listeners.put(listener, alias);
                delegate.addPropertyChangeListener(alias);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePreferenceChangeListener(IPreferenceChangeListener listener) {
        synchronized (this) {
            delegate.removePropertyChangeListener(listeners.get(listener));
            listeners.remove(listener);
        }
    }
}
