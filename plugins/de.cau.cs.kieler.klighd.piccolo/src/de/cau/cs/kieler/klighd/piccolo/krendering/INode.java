/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
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

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * The interface for Piccolo nodes representing a {@code KNode}.
 * 
 * @author mri
 */
public interface INode extends IGraphElement<KNode> {

    /** the property for the Piccolo representation of a node. */
    IProperty<INode> NODE_REP = new Property<INode>("klighd.piccolo.prepresentation");

    /**
     * Returns the child area of this parent node.
     * 
     * @return the child area
     */
    KChildAreaNode getChildArea();

}
