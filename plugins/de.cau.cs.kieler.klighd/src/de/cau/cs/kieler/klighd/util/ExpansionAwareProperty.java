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
package de.cau.cs.kieler.klighd.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;

/**
 * Properties of this class are used to define pairs of property values.<br>
 * The first entry denotes the value in case the related element is expanded, the second entry is
 * incorporated in case the element is collapsed.
 * 
 * Instances of this class are resolved to original property definitions in
 * {@link de.cau.cs.kieler.klighd.krendering.KGraphPropertyLayoutConfig KGraphPropertyLayoutConfig}.
 * 
 * @author chsch
 * 
 * @param <T>
 */
public class ExpansionAwareProperty<T> extends Property<Pair<T, T>> {

    /**
     * 
     */
    private static final BiMap<IProperty<?>, ExpansionAwareProperty<?>> MAP = HashBiMap.create();
    
    /**
     * Creates a property with given identifier and {@code null} as default value.
     * 
     * @param theid the identifier
     */
    public ExpansionAwareProperty(final String theid) {
        super(theid);
    }
    
    /**
     * A.
     * @return b
     */
    @SuppressWarnings("unchecked")
    public IProperty<T> getProperty() {
        return (IProperty<T>) MAP.inverse().get(this);
    }
    
    /**
     * A.
     * 
     * @param <T> B
     * @param property C
     * @return D
     */
    public static <T> ExpansionAwareProperty<T> of(final IProperty<T> property) {
        @SuppressWarnings({ "unchecked" })
        ExpansionAwareProperty<T> result = (ExpansionAwareProperty<T>) MAP.get(property);
        if (result == null) {
            result = new ExpansionAwareProperty<T>(property.getId());
            MAP.put((IProperty<?>) property, (ExpansionAwareProperty<?>) result);
        }
        return result;
    }
}
