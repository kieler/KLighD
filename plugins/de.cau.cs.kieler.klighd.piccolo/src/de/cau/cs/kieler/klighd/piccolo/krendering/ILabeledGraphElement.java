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

import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;

/**
 * The interface for Piccolo nodes representing a {@code KLabeledGraphElement}.
 * 
 * @author mri
 * 
 * @param <T> the type of the labeled graph element
 */
public interface ILabeledGraphElement<T extends KLabeledGraphElement> extends IGraphElement<T> {

    /**
     * Adds the representation of a label to this element.
     * 
     * @param label
     *            the label representation
     */
    void addLabel(KLabelNode label);

}
