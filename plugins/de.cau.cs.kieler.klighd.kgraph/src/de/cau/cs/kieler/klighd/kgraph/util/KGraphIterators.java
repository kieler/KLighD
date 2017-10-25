/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.util;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Iterators;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPort;

/**
 * Collection of Iterators for KGraphs.
 * 
 * @author nbw
 */
public final class KGraphIterators {

    /** Private constructor to prevent instantiation. */
    private KGraphIterators() {
    }

    /**
     * Iterator that returns all {@link KGraphElement}s contained below the given
     * {@link KGraphElement}.
     * 
     * @param root
     *            The starting {@link KGraphElement} of the iteration.
     * @param includeRoot
     *            Flag to indicate whether the root {@link KGraphElement} should be included in the
     *            iteration.
     * @return The iterator for all {@link KGraphElement}s
     */
    public static Iterator<KGraphElement> getKGraphElementIterator(final KGraphElement root,
            final boolean includeRoot) {
        return new AbstractTreeIterator<KGraphElement>(root, includeRoot) {
            /** Generated serialization ID */
            private static final long serialVersionUID = 513548519163661754L;

            @Override
            protected Iterator<? extends KGraphElement> getChildren(final Object object) {
                if (object instanceof KLabeledGraphElement) {
                    return new KGraphSwitch<Iterator<? extends KGraphElement>>() {
                        public Iterator<? extends KGraphElement> caseKNode(final KNode node) {
                            return Iterators.concat(node.getLabels().iterator(),
                                    node.getPorts().iterator(), node.getOutgoingEdges().iterator(),
                                    node.getChildren().iterator());
                        }

                        public Iterator<? extends KGraphElement> caseKEdge(final KEdge edge) {
                            return edge.getLabels().iterator();
                        }

                        public Iterator<? extends KGraphElement> caseKPort(final KPort port) {
                            return port.getLabels().iterator();
                        }

                        public Iterator<? extends KGraphElement> defaultCase(final EObject object) {
                            return Collections.emptyIterator();
                        }
                    }.doSwitch((KGraphElement) object);
                }

                return Collections.emptyIterator();
            }
        };
    }

    /**
     * Iterator for all contained {@link KNode}s below the passed root {@link KNode}.
     * 
     * @param root
     *            The starting {@link KNode} for the iterator.
     * @param includeRoot
     *            Flag to indicate whether the root {@link KNode} should be included in the
     *            iteration.
     * @return The iterator for all {@link KNode}s.
     */
    public static Iterator<KNode> getKNodeIterator(final KNode root, final boolean includeRoot) {
        return new AbstractTreeIterator<KNode>(root, includeRoot) {
            /** Generated serialization ID */
            private static final long serialVersionUID = -8246381957328281345L;

            @Override
            protected Iterator<? extends KNode> getChildren(final Object object) {
                if (object instanceof KNode) {
                    return ((KNode) object).getChildren().iterator();
                } else {
                    return Collections.emptyIterator();
                }
            }
        };
    }
}
