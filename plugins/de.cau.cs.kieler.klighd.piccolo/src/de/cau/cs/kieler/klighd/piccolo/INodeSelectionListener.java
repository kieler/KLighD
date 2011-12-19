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
package de.cau.cs.kieler.klighd.piccolo;

import java.util.Collection;

import edu.umd.cs.piccolo.PNode;

/**
 * An interface for listeners on the selection of a {@code PSWTSimpleSelectionHandler}.
 * 
 * @author mri
 */
public interface INodeSelectionListener {

    /**
     * Handles the selection of a number of nodes.
     * 
     * @param handler
     *            the handler notifying the listener
     * @param nodes
     *            the selected nodes
     */
    void selected(final PSWTSimpleSelectionEventHandler handler, final Collection<PNode> nodes);

}
