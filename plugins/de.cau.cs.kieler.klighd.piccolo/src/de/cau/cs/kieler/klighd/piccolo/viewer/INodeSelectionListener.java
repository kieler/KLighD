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
package de.cau.cs.kieler.klighd.piccolo.viewer;

import de.cau.cs.kieler.klighd.piccolo.internal.events.PSWTSimpleSelectionEventHandler;
import edu.umd.cs.piccolo.PNode;

/**
 * An interface for listeners on the selection of a {@code PSWTSimpleSelectionHandler}.
 * 
 * @author mri, chsch
 */
public interface INodeSelectionListener {

    /**
     * Informs the implementing listener about the selection of <code>node</code>. Strictly
     * after firing such notifications for all pending selection events
     * {@link #selection(PSWTSimpleSelectionEventHandler, Iterable)} is called.
     * 
     * @param handler
     *            the handler notifying the listener
     * @param node
     *            the newly selected node
     */
    void selected(final PSWTSimpleSelectionEventHandler handler, final PNode node);

    /**
     * Informs the implementing listener about the de-selection of <code>node</code>. Strictly
     * after firing such notifications for all pending selection events
     * {@link #selection(PSWTSimpleSelectionEventHandler, Iterable)} is called.
     * 
     * @param handler
     *            the handler notifying the listener
     * @param node
     *            the newly unselected node
     */
    void unselected(final PSWTSimpleSelectionEventHandler handler, final PNode node);

    /**
     * Informs the implementing listener about a change in the current selection and provides the
     * updated set of selected elements. This method is supposed to be called after all pending
     * {@link #selected(PSWTSimpleSelectionEventHandler, PNode)} and
     * {@link #unselected(PSWTSimpleSelectionEventHandler, PNode)} notifications on the particular
     * nodes have been fired.
     * 
     * @param handler
     *            the handler notifying the listener
     * @param nodes
     *            the selected nodes
     */
    void selection(final PSWTSimpleSelectionEventHandler handler, final Iterable<PNode> nodes);
    
}
