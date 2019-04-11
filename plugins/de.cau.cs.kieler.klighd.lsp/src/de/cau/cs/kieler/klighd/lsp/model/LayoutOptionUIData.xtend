/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.model

import java.util.ArrayList
import java.util.Collection
import java.util.List
import org.eclipse.elk.core.data.LayoutOptionData
import org.eclipse.elk.core.data.LayoutOptionData.Type
import org.eclipse.xtend.lib.annotations.Data

/**
 * Data class holding a representation of all necessary data for some arbitrary UI displaying layout options.
 * 
 * @author nre
 */
 @Data
class LayoutOptionUIData {
    /** identifier of the layout option. */
    String optionId
    /** the default value of this option and its human-readable form. */
    Pair<Object, String> defaultValue
    /** type of the layout option. */
    Type type
    /** user friendly name of the layout option. */
    String name
    /** a description to be displayed in the UI. */
    String description
    /** cached value of the available choices. */
    String[] choices
    /** the minimal value for the option, or {@code null} */
    Float minValue
    /** the maximal value for the option, or {@code null} */
    Float maxValue
    /** the set of values and their human-readable form to offer */
    Pair<List<?>, List<String>> availableValues
    
    new(LayoutOptionData layoutOptionData, Float minValue, Float maxValue, Collection<?> availableValues) {
        this.optionId = layoutOptionData.id
        val defaultValue = layoutOptionData.^default
        this.defaultValue = defaultValue -> defaultValue.userValue
        this.type = layoutOptionData.type
        this.name = layoutOptionData.name
        this.description = layoutOptionData.description
        this.choices = layoutOptionData.choices
        this.minValue = getMinValue(layoutOptionData, minValue)
        this.maxValue = getMaxValue(layoutOptionData, maxValue)
        val available = availableValues?.toList
        if (available !== null) {
            this.availableValues = available -> available.map[ userValue ]
        } else {
            this.availableValues = new ArrayList -> new ArrayList
        }
    }
    
    /**
     * Get a user-friendly value for the given object.
     *
     * @param object an object, e.g. an enumeration value
     * @return a user-friendly string to display in the UI
     */
    private static def String userValue(Object object) {
        val string = object.toString();
        val builder = new StringBuilder(string.length)
        var capital = true;
        for (var i = 0; i < string.length; i++) {
            if (string.charAt(i) == '_') {
                builder.append(' ')
                capital = true;
            } else if (capital) {
                builder.append(Character.toUpperCase(string.charAt(i)))
                capital = false;
            } else {
                builder.append(Character.toLowerCase(string.charAt(i)))
            }
        }
        return builder.toString()
    }
    
    /** The default value for the lower bound. */
    private static final int DEFAULT_MIN = 0

    /**
     * Get a lower bound for values of the given option.
     *
     * @param optionData a layout option data instance
     * @param requested the requested bound, or {@code null}
     * @return a minimal value
     */
    def private static float getMinValue(LayoutOptionData optionData, Float requested) {
        if (requested !== null) {
            return requested
        }
        val lowerBound = optionData.lowerBound
        if (lowerBound instanceof Number) {
            return (lowerBound as Number).floatValue
        }
        return DEFAULT_MIN;
    }

    /** The default value for the upper bound. */
    private static final int DEFAULT_MAX = 100

    /**
     * Get an upper bound for values of the given option.
     *
     * @param optionData a layout option data instance
     * @param requested the requested bound, or {@code null}
     * @return a maximal value
     */
    def private static float getMaxValue(LayoutOptionData optionData, Float requested) {
        if (requested !== null) {
            return requested
        }
        val upperBound = optionData.upperBound
        if (upperBound instanceof Number) {
            return (upperBound as Number).floatValue
        }
        return DEFAULT_MAX;
    }
}