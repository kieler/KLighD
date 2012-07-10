/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Preference store implementation that uses maps to hold preference values
 * in memory. Subclasses may access {@link #currentMap} and {@link #defaultMap}
 * and use their {@code keySet} methods to obtain the sets of all stored current
 * and default values. This can be used to write these keys together with
 * their values to a file for permanent storage.
 * 
 * @author msp
 * @deprecated this isn't used anywhere
 */
public class MapPreferenceStore implements IKielerPreferenceStore {

    /** hash map used to store current preference values in memory. */
    private Map<String, Object> currentMap = new LinkedHashMap<String, Object>();
    /** hash map used to store default preference values in memory. */
    private Map<String, Object> defaultMap = new LinkedHashMap<String, Object>();
    
    /**
     * {@inheritDoc}
     */
    public boolean contains(final String name) {
        return currentMap.get(name) != null || defaultMap.get(name) != null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean getBoolean(final String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof Boolean) {
            return ((Boolean) currentValue).booleanValue();
        }
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Boolean) {
            return ((Boolean) defaultValue).booleanValue();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean getDefaultBoolean(final String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Boolean) {
            return ((Boolean) defaultValue).booleanValue();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public double getDefaultDouble(final String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Double) {
            return ((Double) defaultValue).doubleValue();
        }
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public float getDefaultFloat(final String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Float) {
            return ((Float) defaultValue).floatValue();
        }
        return 0.0f;
    }

    /**
     * {@inheritDoc}
     */
    public int getDefaultInt(final String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Integer) {
            return ((Integer) defaultValue).intValue();
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public long getDefaultLong(final String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Long) {
            return ((Long) defaultValue).longValue();
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public String getDefaultString(final String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof String) {
            return (String) defaultValue;
        }
        return "";
    }

    /**
     * {@inheritDoc}
     */
    public double getDouble(final String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof Double) {
            return ((Double) currentValue).doubleValue();
        }
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Double) {
            return ((Double) defaultValue).doubleValue();
        }
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public float getFloat(final String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof Float) {
            return ((Float) currentValue).floatValue();
        }
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Float) {
            return ((Float) defaultValue).floatValue();
        }
        return 0.0f;
    }

    /**
     * {@inheritDoc}
     */
    public int getInt(final String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof Integer) {
            return ((Integer) currentValue).intValue();
        }
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Integer) {
            return ((Integer) defaultValue).intValue();
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public long getLong(final String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof Long) {
            return ((Long) currentValue).longValue();
        }
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Long) {
            return ((Long) defaultValue).longValue();
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public String getString(final String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof String) {
            return (String) currentValue;
        }
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof String) {
            return (String) defaultValue;
        }
        return "";
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDefault(final String name) {
        Object currentValue = currentMap.get(name);
        Object defaultValue = defaultMap.get(name);
        return defaultValue != null && (currentValue == null
                || currentValue.equals(defaultValue));
    }

    /**
     * {@inheritDoc}
     */
    public void setDefault(final String name, final double value) {
        defaultMap.put(name, Double.valueOf(value));
    }

    /**
     * {@inheritDoc}
     */
    public void setDefault(final String name, final float value) {
        defaultMap.put(name, Float.valueOf(value));
    }

    /**
     * {@inheritDoc}
     */
    public void setDefault(final String name, final int value) {
        defaultMap.put(name, Integer.valueOf(value));
    }

    /**
     * {@inheritDoc}
     */
    public void setDefault(final String name, final long value) {
        defaultMap.put(name, Long.valueOf(value));
    }

    /**
     * {@inheritDoc}
     */
    public void setDefault(final String name, final String defaultObject) {
        defaultMap.put(name, defaultObject);
    }

    /**
     * {@inheritDoc}
     */
    public void setDefault(final String name, final boolean value) {
        defaultMap.put(name, Boolean.valueOf(value));
    }

    /**
     * {@inheritDoc}
     */
    public void setToDefault(final String name) {
        currentMap.remove(name);
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(final String name, final double value) {
        currentMap.put(name, Double.valueOf(value));
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(final String name, final float value) {
        currentMap.put(name, Float.valueOf(value));
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(final String name, final int value) {
        currentMap.put(name, Integer.valueOf(value));
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(final String name, final long value) {
        currentMap.put(name, Long.valueOf(value));
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(final String name, final String value) {
        currentMap.put(name, value);
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(final String name, final boolean value) {
        currentMap.put(name, Boolean.valueOf(value));
    }

}
