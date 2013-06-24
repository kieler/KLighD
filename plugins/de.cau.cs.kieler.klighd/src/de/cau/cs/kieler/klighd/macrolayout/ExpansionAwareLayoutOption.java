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
package de.cau.cs.kieler.klighd.macrolayout;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.IDataObject;

/**
 * @author chsch
 */
public final class ExpansionAwareLayoutOption {
    
    private ExpansionAwareLayoutOption() {
    }
    
    /**
     * 
     */
    public static final IProperty<ExpansionAwareLayoutOptionData> OPTION =
            new Property<ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData>(
                    "de.cau.cs.kieler.klighd.expansionAwareLayoutOption");

    /**
     * @author chsch
     */
    public static class ExpansionAwareLayoutOptionData implements IDataObject {

        private static final long serialVersionUID = 8851060475752240313L;

        private MapPropertyHolder collapsedValues;
        private MapPropertyHolder expandedValues;

        /**
         * Standard constructor, is called by KIML.
         */
        public ExpansionAwareLayoutOptionData() {
            this.collapsedValues = new MapPropertyHolder();
            this.expandedValues = new MapPropertyHolder();
        }
        
        /**
         * Adds a configuration of collapsed/expanded-dependent layout option values.
         * 
         * @param <T>
         *            the property value type
         * @param property
         *            the particular layout option, e.g. one of
         *            {@link de.cau.cs.kieler.kiml.options.LayoutOptions LayoutOptions}
         * @param collapsedValue
         *            the value in case <code>node</code> is collapsed
         * @param expandedValue
         *            the value in case <code>node</code> is expanded
         */
        public <T> void setProperty(final IProperty<T> property, final T collapsedValue,
                final T expandedValue) {
            this.collapsedValues.setProperty(property, collapsedValue);
            this.expandedValues.setProperty(property, expandedValue);
        }

        /**
         * Retrieves a property value depending on the value of <code>expanded</code>.
         * 
         * @param <T>
         *            the type of the property value to retrieve.
         * @param property
         *            the layout option to get the value for
         * @param expanded
         *            <code>true</code>, if the 'expanded' state value is required;
         *            <code>false</code> otherwise
         * @return the related property value
         */
        public <T> T getValue(final IProperty<T> property, final boolean expanded) {
            return expanded ? expandedValues.getProperty(property) : collapsedValues
                    .getProperty(property);
        }
        
        /**
         * Returns all collapsed- or expanded-dependent layout option values depending on the value
         * of <code>expanded</code>.
         * 
         * @param expanded
         *            <code>true</code>, if the 'expanded' state value is required;
         *            <code>false</code> otherwise
         * @return the {@link MapPropertyHolder} containing the related layout option settings
         */
        public IPropertyHolder getValues(final boolean expanded) {
            return expanded ? expandedValues : collapsedValues;
        }
        

        /**
         * {@inheritDoc}
         */
        public void parse(final String string) {
            // TODO
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return super.toString(); //"(" + collapsedValues + ", " + expandedValues + ")";
        }
    }
}
