/*
 * KLighD - KIELER Lightweight Diagrams
 *
 * https://github.com/Kieler/KLighD
 *
 * Copyright 2020 by TypeFox GmbH (typefox.io) and others.
 *
 * This code is provided under the terms of the Eclipse Public License 1.0 (EPL-1.0).
 */
package de.cau.cs.kieler.klighd;

import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;

public interface IKlighdPreferenceStore {

    /**
     * The default-default value for boolean preferences (<code>false</code>).
     */
    public static final boolean BOOLEAN_DEFAULT_DEFAULT = false;

    /**
     * The default-default value for double preferences (<code>0.0</code>).
     */
    public static final double DOUBLE_DEFAULT_DEFAULT = 0.0;

    /**
     * The default-default value for float preferences (<code>0.0f</code>).
     */
    public static final float FLOAT_DEFAULT_DEFAULT = 0.0f;

    /**
     * The default-default value for int preferences (<code>0</code>).
     */
    public static final int INT_DEFAULT_DEFAULT = 0;

    /**
     * The default-default value for long preferences (<code>0L</code>).
     */
    public static final long LONG_DEFAULT_DEFAULT = 0L;

    /**
     * The default-default value for String preferences (<code>""</code>).
     */
    public static final String STRING_DEFAULT_DEFAULT = "";

    /**
     * Returns the current value of the boolean-valued preference with the
     * given name.
     * Returns the default-default value (<code>false</code>) if there
     * is no preference with the given name, or if the current value
     * cannot be treated as a boolean.
     *
     * @param name the name of the preference
     * @return the boolean-valued preference
     */
    public boolean getBoolean(String name);

    /**
     * Returns the current value of the double-valued preference with the
     * given name.
     * Returns the default-default value (<code>0.0</code>) if there
     * is no preference with the given name, or if the current value
     * cannot be treated as a double.
     *
     * @param name the name of the preference
     * @return the double-valued preference
     */
    public double getDouble(String name);

    /**
     * Returns the current value of the float-valued preference with the
     * given name.
     * Returns the default-default value (<code>0.0f</code>) if there
     * is no preference with the given name, or if the current value
     * cannot be treated as a float.
     *
     * @param name the name of the preference
     * @return the float-valued preference
     */
    public float getFloat(String name);

    /**
     * Returns the current value of the integer-valued preference with the given
     * name. Returns the default-default value (<code>0</code>) if there is no
     * preference with the given name, or if the current value cannot be treated
     * as an integer.
     *
     * @param name
     *            the name of the preference
     * @return the int-valued preference
     */
    public int getInt(String name);

    /**
     * Returns the current value of the long-valued preference with the
     * given name.
     * Returns the default-default value (<code>0L</code>) if there
     * is no preference with the given name, or if the current value
     * cannot be treated as a long.
     *
     * @param name the name of the preference
     * @return the long-valued preference
     */
    public long getLong(String name);

    /**
     * Returns the current value of the string-valued preference with the
     * given name.
     * Returns the default-default value (the empty string <code>""</code>)
     * if there is no preference with the given name, or if the current value
     * cannot be treated as a string.
     *
     * @param name the name of the preference
     * @return the string-valued preference
     */
    public String getString(String name);

    /**
     * Sets the current value of the boolean-valued preference with the
     * given name.
     * <p>
     * A preference change event is reported if the current value of the
     * preference actually changes from its previous value. In the event
     * object, the preference name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, boolean value);

    /**
     * Sets the current value of the double-valued preference with the
     * given name.
     * <p>
     * A preference change event is reported if the current value of the
     * preference actually changes from its previous value. In the event
     * object, the preference name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, double value);

    /**
     * Sets the current value of the float-valued preference with the
     * given name.
     * <p>
     * A preference change event is reported if the current value of the
     * preference actually changes from its previous value. In the event
     * object, the preference name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, float value);

    /**
     * Sets the current value of the integer-valued preference with the
     * given name.
     * <p>
     * A preference change event is reported if the current value of the
     * preference actually changes from its previous value. In the event
     * object, the preference name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, int value);

    /**
     * Sets the current value of the long-valued preference with the
     * given name.
     * <p>
     * A preference change event is reported if the current value of the
     * preference actually changes from its previous value. In the event
     * object, the preference name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, long value);

    /**
     * Sets the current value of the string-valued preference with the
     * given name.
     * <p>
     * A preference change event is reported if the current value of the
     * preference actually changes from its previous value. In the event
     * object, the preference name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, String value);

    /**
     * Returns whether the current value of the preference with the given name
     * has the default value.
     *
     * @param name the name of the preference
     * @return <code>true</code> if the preference has a known default value
     * and its current value is the same, and <code>false</code> otherwise
     * (including the case where the preference is unknown to this store)
     */
    public boolean isDefault(String name);

    /**
     * Sets the current value of the preference with the given name back
     * to its default value.
     * <p>
     * Note that the preferred way of re-initializing a preference to the
     * appropriate default value is to call <code>setToDefault</code>.
     * This is implemented by removing the named value from the store,
     * thereby exposing the default value.
     * </p>
     *
     * @param name the name of the preference
     */
    public void setToDefault(String name);

    /**
     * Returns the default value for the boolean-valued preference
     * with the given name.
     * Returns the default-default value (<code>false</code>) if there
     * is no default preference with the given name, or if the default
     * value cannot be treated as a boolean.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public boolean getDefaultBoolean(String name);

    /**
     * Returns the default value for the double-valued preference
     * with the given name.
     * Returns the default-default value (<code>0.0</code>) if there
     * is no default preference with the given name, or if the default
     * value cannot be treated as a double.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public double getDefaultDouble(String name);

    /**
     * Returns the default value for the float-valued preference
     * with the given name.
     * Returns the default-default value (<code>0.0f</code>) if there
     * is no default preference with the given name, or if the default
     * value cannot be treated as a float.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public float getDefaultFloat(String name);

    /**
     * Returns the default value for the integer-valued preference
     * with the given name.
     * Returns the default-default value (<code>0</code>) if there
     * is no default preference with the given name, or if the default
     * value cannot be treated as an integer.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public int getDefaultInt(String name);

    /**
     * Returns the default value for the long-valued preference
     * with the given name.
     * Returns the default-default value (<code>0L</code>) if there
     * is no default preference with the given name, or if the default
     * value cannot be treated as a long.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public long getDefaultLong(String name);

    /**
     * Returns the default value for the string-valued preference
     * with the given name.
     * Returns the default-default value (the empty string <code>""</code>)
     * is no default preference with the given name, or if the default
     * value cannot be treated as a string.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public String getDefaultString(String name);

    /**
     * Sets the default value for the boolean-valued preference with the
     * given name.
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No preference change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new default value for the preference
     */
    public void setDefault(String name, boolean value);

    /**
     * Sets the default value for the double-valued preference with the
     * given name.
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No preference change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new default value for the preference
     */
    public void setDefault(String name, double value);

    /**
     * Sets the default value for the float-valued preference with the
     * given name.
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No preference change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new default value for the preference
     */
    public void setDefault(String name, float value);

    /**
     * Sets the default value for the integer-valued preference with the
     * given name.
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No preference change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new default value for the preference
     */
    public void setDefault(String name, int value);

    /**
     * Sets the default value for the long-valued preference with the
     * given name.
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No preference change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new default value for the preference
     */
    public void setDefault(String name, long value);

    /**
     * Sets the default value for the string-valued preference with the
     * given name.
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No preference change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param defaultObject the new default value for the preference
     */
    public void setDefault(String name, String defaultObject);

    /**
     * <p>
     * Adds a preference change listener to this preference store.
     * </p>
     * <p>
     * <b>Note</b> The types of the oldValue and newValue of the
     * generated PreferenceChangeEvent are determined by whether
     * or not the typed API in IPreferenceStore was called.
     * If values are changed via setValue(name,type) the
     * values in the PreferenceChangedEvent will be of that type.
     * If they are set using a non typed API (i.e. #setToDefault
     * or using the OSGI Preferences) the values will be unconverted
     * Strings.
     * </p>
     * <p>
     * A listener will be called in the same Thread
     * that it is invoked in. Any Thread dependant listeners (such as
     * those who update an SWT widget) will need to update in the
     * correct Thread. In the case of an SWT update you can update
     * using Display#syncExec(Runnable) or Display#asyncExec(Runnable).
     * </p>
     * <p>
     * Likewise any application that updates an IPreferenceStore
     * from a Thread other than the UI Thread should be aware of
     * any listeners that require an update in the UI Thread.
     * </p>
     *
     * @param listener a preference change listener
     */
    public abstract void addPreferenceChangeListener(final IPreferenceChangeListener listener);

    /**
     * Removes the given listener from this preference store. Has no effect if the listener is not
     * registered.
     *
     * @param listener a preference change listener, must not be <code>null</code>
     */
    public abstract void removePreferenceChangeListener(final IPreferenceChangeListener listener);
}
