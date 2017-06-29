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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.kgraph.util;

import java.util.Collections;
import java.util.Iterator;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KPort;

/**
 * The default {@link SelectionIterator} for usage in
 * {@link KGraphUtilUtil#getConnectedElements(KEdge, boolean) KGraphUtil.getConnectedElements(...)}.
 * The iterator follows all edges transitively connected to the initial edge. Optionally the
 * iterator can also include the corresponding ports in the selection.
 */
public class DefaultSelectionIterator extends SelectionIterator {

    private static final long serialVersionUID = 2945508835051993888L;

    private boolean addPorts;

    private boolean followEdgeDirection;

    /**
     * Creates a new iterator which can optionally include ports in its selection and can be
     * configured to either iterate towards the tail or the head of the selected {@link KEdge}.
     * 
     * @param edge
     *            the edge to start with
     * @param addPorts
     *            flag to determine whether ports should be included in the selection
     * @param followEdgeDirection
     *            flag whether the iterator should iterate towards the head or the tail of the edge
     */
    public DefaultSelectionIterator(final KEdge edge, final boolean addPorts,
            final boolean followEdgeDirection) {
        
        super(edge);
        this.addPorts = addPorts;
        this.followEdgeDirection = followEdgeDirection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Iterator<? extends KGraphElement> getChildren(final Object object) {
        // Ensure that the visited set is properly initialized
        if (visited == null) {
            visited = Sets.newHashSet();
        }
        
        if (object instanceof KEdge) {
            KEdge edge = (KEdge) object;
            final KPort port = followEdgeDirection ? edge.getTargetPort() : edge.getSourcePort();

            if (port == null || visited.contains(port)) {
                // return an empty iterator if no target/source port is configured
                // or if the target/source port has been visited already, in order
                // to break infinite loops
                return Collections.<KGraphElement>emptyIterator();
            }

            visited.add(port);

            // for each object (kedge) visited by this iterator check all the edges connected to
            // 'port' and visit those edges satisfying the criterion stated above
            Iterator<KEdge> resultEdges =
                    Iterators.filter(port.getEdges().iterator(), new Predicate<KEdge>() {

                        public boolean apply(final KEdge input) {
                            return followEdgeDirection ? port == input.getSourcePort()
                                    : port == input.getTargetPort();
                        }
                    });

            // If the port should be added to the selection, add it to the result set
            if (addPorts) {
                Iterator<KGraphElement> portIterator =
                        Iterators.singletonIterator((KGraphElement) port);
                return Iterators.concat(portIterator, resultEdges);
            } else {
                return resultEdges;
            }
        } else {
            return Collections.<KGraphElement>emptyIterator();
        }
    }
}
