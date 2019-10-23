/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.ui.view;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.SynthesisOption;

/**
 * Utility class for type-safe parsing and serializing of {@link SynthesisOption}s.
 * 
 * @author als
 * @kieler.design 2015-11-18 proposed
 * @kieler.rating 2015-11-18 proposed yellow
 */
final class SynthesisOptionsPersistence {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private SynthesisOptionsPersistence() {
    }

    /**
     * Serializes the given value of the given option to a parsable string.
     * 
     * @param option
     *            the {@link SynthesisOption} of the value
     * @param value
     *            the value
     * @return the string representation of the value. Never null.
     */
    public static String serialize(final SynthesisOption option, final Object value) {
        try {
            if (option != null && value != null) {
                if (option.isCheckOption() && value instanceof Boolean) {
                    return value.toString();
                } else if (option.isRangeOption() && value instanceof Number) {
                    return value.toString();
                } else if (option.isChoiceOption()) {
                    return Integer.toString(value.toString().hashCode());
                } else if (option.isTextOption() && value instanceof String) {
                    return (String) value;
                }
            }
        } catch (Exception e) {
            StatusManager.getManager().handle(new Status(IStatus.WARNING,
                    KlighdViewPlugin.PLUGIN_ID,
                    SynthesisOptionsPersistence.class.getName() + ": Can not serialze value '"
                            + value.toString() + "' for synthesis option with name '"
                            + option.getName() + "'.",
                    e));
        }
        return "";
    }

    /**
     * Parses the given value into an Object compatible to the given option.
     * 
     * @param option
     *            the {@link SynthesisOption} of the value
     * @param value
     *            the string representation of the the value
     * @return the value as parsed instance or null if value cannot be parsed.
     */
    public static Object parse(final SynthesisOption option, final String value) {
        try {
            if (option != null && value != null && !value.isEmpty()) {
                if (option.isCheckOption()) {
                    return Boolean.parseBoolean(value);
                } else if (option.isRangeOption()) {
                    return Float.parseFloat(value);
                } else if (option.isChoiceOption()) {
                    int hash = Integer.parseInt(value);
                    for (Object match : option.getValues()) {
                        if (match != null && match.toString().hashCode() == hash) {
                            return match;
                        }
                    }
                } else if (option.isTextOption()) {
                    return value;
                }
            }
        } catch (Exception e) {
            StatusManager.getManager()
                    .handle(new Status(IStatus.WARNING, KlighdViewPlugin.PLUGIN_ID,
                            SynthesisOptionsPersistence.class.getName() + ": Can not parse value '"
                                    + value.toString() + "' for synthesis option with name '"
                                    + option.getName() + "'.",
                            e));
        }
        return null;
    }

}
