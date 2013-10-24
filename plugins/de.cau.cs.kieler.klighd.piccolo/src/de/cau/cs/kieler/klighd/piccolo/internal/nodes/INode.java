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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * The interface for Piccolo nodes representing a {@code KNode}.
 * 
 * Info (chsch): So far I figured out, only INodes are set pickable, concrete rendering nodes
 *  such as rectangles, paths, ... are not pickable. This influences e.g. the selection
 *  handling.
 * 
 * @author mri
 */
public interface INode extends IGraphElement<KNode> {

    /**
     * Returns the child area of this parent node.
     * 
     * @return the child area
     */
    KChildAreaNode getChildArea();

}
