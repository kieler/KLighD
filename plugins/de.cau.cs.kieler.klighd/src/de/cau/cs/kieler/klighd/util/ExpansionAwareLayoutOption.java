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
package de.cau.cs.kieler.klighd.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.util.IDataObject;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.IPropertyHolder;
import org.eclipse.elk.graph.properties.MapPropertyHolder;
import org.eclipse.elk.graph.properties.Property;

/**
 * Contributes support for declarative layout option value definitions depending on the
 * collapsed/expanded state of the {@link de.cau.cs.kieler.klighd.kgraph.KNode KNode}.
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch 
 */
public final class ExpansionAwareLayoutOption {
    
    private ExpansionAwareLayoutOption() {
    }
    
    /**
     * The layout option singleton (similar to those in
     * {@link de.cau.cs.kieler.kiml.options.LayoutOptions LayoutOptions}.<br>
     * Its id is also registered in the plugin.xml. 
     */
    public static final IProperty<ExpansionAwareLayoutOptionData> OPTION =
            new Property<ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData>(
                    "de.cau.cs.kieler.klighd.expansionAwareLayoutOption");

    /**
     * Convenience method for defining {@link ExpansionAwareLayoutOption
     * ExpansionAwareLayoutOptions} on {@link IPropertyHolder IPropertyHolders}.<br>
     * Adds the property definition to existing {@link ExpansionAwareLayoutOptionData} if present,
     * and attaches a new instance otherwise.
     * 
     * @param <T>
     *            the property value type
     * @param holder
     *            an {@link IPropertyHolder} to define the property on
     * @param property
     *            the particular layout option, e.g. one of
     *            {@link de.cau.cs.kieler.kiml.options.LayoutOptions LayoutOptions}
     * @param collapsedValue
     *            the value in case <code>node</code> is collapsed
     * @param expandedValue
     *            the value in case <code>node</code> is expanded
     */
    public static <T> void setProperty(final IPropertyHolder holder, final IProperty<T> property,
            final T collapsedValue, final T expandedValue) {
        ExpansionAwareLayoutOptionData data = holder.getProperty(OPTION);
        if (data == null) {
            data = new ExpansionAwareLayoutOptionData();
            holder.setProperty(OPTION, data);
        }
        data.setProperty(property, collapsedValue, expandedValue);
    }

    /**
     * Dedicated implementation {@link IDataObject} of defining value type of
     * {@link ExpansionAwareLayoutOption#OPTION}.
     * 
     * @author chsch
     */
    public static class ExpansionAwareLayoutOptionData implements IDataObject {

        private static final long serialVersionUID = 8851060475752240313L;

        private MapPropertyHolder collapsedValues;
        private MapPropertyHolder expandedValues;

        /**
         * Standard constructor, is called by ELK.
         */
        public ExpansionAwareLayoutOptionData() {
            this.collapsedValues = new MapPropertyHolder();
            this.expandedValues = new MapPropertyHolder();
        }

        /**
         * Copy constructor, is called by ELK.
         */
        public ExpansionAwareLayoutOptionData(ExpansionAwareLayoutOptionData original) {
            this.collapsedValues = new MapPropertyHolder().copyProperties(original.collapsedValues);
            this.expandedValues = new MapPropertyHolder().copyProperties(original.expandedValues);
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
         * @return <code>this</code> ExpansionAwareLayoutOptionData object for convenience
         */
        public <T> ExpansionAwareLayoutOptionData setProperty(final IProperty<T> property,
                final T collapsedValue, final T expandedValue) {
            this.collapsedValues.setProperty(property, collapsedValue);
            this.expandedValues.setProperty(property, expandedValue);
            return this;
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
            // MapPropertyHolder.getProperty(IProperty) is not used by intention,
            //  in order to avoid the return of the property's default value!
            // That would prevent evaluation of further layout configs
            @SuppressWarnings("unchecked")
            final
            T res = expanded ? (T) expandedValues.getAllProperties().get(property)
                            : (T) collapsedValues.getAllProperties().get(property);
            return res;
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
         * 
         * This particular implementation parses the content of the {@link #collapsedValues} and
         * {@link #expandedValues} maps from the following form:s
         * <pre>
         * '((collapsed: id0 = value0,, id1 = value1 ...;; expanded: id0 = value0,, id1 = value1 ...))'
         * </pre>.
         */
        public void parse(final String string) {
            final Iterator<String> definitions =
                    Arrays.asList(string.trim().split("\\(\\(|;;|\\)\\)")).iterator();
            
            while (definitions.hasNext()) {
                final String next = definitions.next().trim();
                final IPropertyHolder holder;
                
                if (next.startsWith("collapsed:")) {
                    holder = collapsedValues;
                } else if (next.startsWith("expanded:")) {
                    holder = expandedValues;
                } else {
                    continue;
                }

                final Iterator<String> keyVals =
                        Arrays.asList(next.substring(next.indexOf(':') + 1).split(",,|=")).iterator();
                
                while (keyVals.hasNext()) {
                    final String key = keyVals.next().trim();
                    if (keyVals.hasNext()) {
                        final String value = keyVals.next().trim();
                        setOption(holder, key, value);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         * 
         * This particular implementation serializes the {@link #collapsedValues} and
         * {@link #expandedValues} maps in the following form:
         * <pre>
         * '((collapsed: id0 = value0,, id1 = value1 ...;; expanded: id0 = value0,, id1 = value1 ...))'
         * </pre>.
         */
        @Override
        public String toString() {
            final String collapsed = "collapsed: " + toString(collapsedValues); 
            final String expanded = "expanded: " + toString(expandedValues);

            return "((" + collapsed + ";; " + expanded + "))";
        }
        
        private String toString(final IPropertyHolder properties) {
            String result = new String();
            
            for (final Map.Entry<IProperty<?>, Object> p : properties.getAllProperties().entrySet()) {
                result += p.getKey().getId() + " = " + p.getValue().toString() + ",, ";
            }

            return result.replaceFirst(",, $", "");
        }
        
        /**
         * Set a layout option using a serialized key / value pair.
         * 
         * @param holder theproperty holder to modify
         * @param id the layout option identifier
         * @param value the value for the layout option
         */
        private static void setOption(final IPropertyHolder holder, final String id, final String value) {
            LayoutOptionData optionData = LayoutMetaDataService.getInstance().getOptionData(id);
            if (optionData != null) {
                Object obj = optionData.parseValue(value);
                if (obj != null) {
                    holder.setProperty(optionData, obj);
                }
            }
        }
    }
}
