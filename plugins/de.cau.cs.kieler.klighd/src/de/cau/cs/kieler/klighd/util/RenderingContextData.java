/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
/**
 * 
 */
package de.cau.cs.kieler.klighd.util;

import com.google.common.base.Predicate;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.impl.KGraphDataImpl;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;

/**
 * A graph data implementation for storing context information about elements in a
 * visualization of a KGraph with attached KRendering data.
 * 
 * @author mri, chsch (moved it here from klighd.piccolo to be referenced in layout and compare stuff)
 */
public class RenderingContextData extends KGraphDataImpl {

    /**
     * Adds an instance of {@code RenderingContextData} to the given graph element if it has no such
     * data added. In any case returns the data instance.
     * 
     * @param element
     *            the graph element
     * @return the added data
     */
    public static RenderingContextData get(final KGraphElement element) {
        RenderingContextData data = element.getData(RenderingContextData.class);
        if (data == null) {
            data = new RenderingContextData();
            element.getData().add(data);
        }
        return data;
    }
    
    /**
     * Returns true if <code>property</code> has been defined for <code>this</code> instance.
     * 
     * @param property
     *            the <code>property</code> to check for definition
     * @return true if <code>property</code> is set, false otherwise.
     */
    public boolean containsPoperty(final IProperty<?> property) {
        return this.getProperties().keySet().contains(property);
    }

    /**
     * A predicate definition used to drop inactive nodes while processing the layout input graph.<br>
     * Currently all children of a node are active or non-active at a time, a selective filtering is
     * not done so far (see e.g. DiagramController#addExpansionListener). This might change in future.
     */
    public static final Predicate<KGraphElement> CHILD_ACTIVE = new Predicate<KGraphElement>() {
        public boolean apply(final KGraphElement node) {
            return !RenderingContextData.get(node).containsPoperty(KlighdInternalProperties.ACTIVE)
                    || RenderingContextData.get(node).getProperty(KlighdInternalProperties.ACTIVE);
        }
    };
}
