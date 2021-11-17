/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2016 by
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
package de.cau.cs.kieler.klighd.kgraph.util;

import java.util.Set;

import org.eclipse.emf.common.util.AbstractTreeIterator;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KPort;

/**
 * This class can be used to implement Iterators for selections. The {@link SelectionIterator} is
 * used in {@link KGraphUtil} to get the {@link KGraphElement graph elements} connected to a
 * selected edge.
 * 
 * @see DefaultSelectionIterator
 */
public abstract class SelectionIterator extends AbstractTreeIterator<KGraphElement> {

    private static final long serialVersionUID = 478793714459586388L;

    /**
     * The list of already visited ports. Used to break infinite loops.
     */
    protected Set<KPort> visited; // SUPPRESS CHECKSTYLE VisibilityModifier

    /**
     * Creates a {@link SelectionIterator} which needs to be configured afterwards by adding the set
     * of visited nodes. The iterator won't include the starting object.
     * 
     * @param edge
     *            The object to iterate from
     */
    public SelectionIterator(final KEdge edge) {
        super(edge, false);
    }

    /**
     * Attach a set of {@link ElkPortt ports} to the iterator to be used as a set of visited ports.
     * Can be used to share a set of nodes across multiple iterators (target and source iterator).
     * 
     * @param visitedSet
     *            The set of nodes to be used as visited set.
     */
    public void attachVisitedSet(final Set<KPort> visitedSet) {
        this.visited = visitedSet;
    }
}
