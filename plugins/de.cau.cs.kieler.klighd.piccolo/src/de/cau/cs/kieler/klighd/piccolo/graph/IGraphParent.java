/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.graph;

import java.util.List;

/**
 * The interface for all graph objects containing nodes.
 * 
 * @author mri
 */
public interface IGraphParent extends IGraphPositional, IGraphObject {

    /**
     * Returns the child nodes of this parent.
     * 
     * @return the child nodes
     */
    List<IGraphNode> getChildren();

    /**
     * Returns the insets of the parent object for the children.
     * 
     * @return the insets or null for no insets
     */
    Insets getInsets();

}
