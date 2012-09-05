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
package de.cau.cs.kieler.klighd.piccolo.krendering;

import org.eclipse.emf.ecore.EObject;

/**
 * The interface for Piccolo nodes whose KGraph/KRendering source element is traced.
 *
 * @author chsch
 * 
 * @param <T> the type of the traced element.
 */
public interface ITracingElement<T extends EObject> {
    
    /**
     * Returns the graph element traced by this node.
     * 
     * @return the traced view graph element.
     */
    T getGraphElement();

}