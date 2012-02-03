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
package de.cau.cs.kieler.klighd.piccolo.krendering;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.impl.KGraphDataImpl;

/**
 * A graph data implementation for storing context information about elements in a Piccolo
 * visualization of a KGraph with attached KRendering data.
 * 
 * @author mri
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

}
