/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import edu.umd.cs.piccolo.PNode;

/**
 * A specialized {@link PropertyChangeListener} employed for cleaning up employed SWT
 * {@link org.eclipse.swt.graphics.Resource Resources} by disposing them.
 * 
 * @author chsch
 */
public class NodeDisposeListener implements PropertyChangeListener {

    /**
     * A {@link PNode} property change event key indicating dispose
     * {@link org.eclipse.swt.graphics.Device}-dependent SWT objects.
     */
    public static final String DISPOSE = "dispose";

    /**
     * The property change event code related to {@link #DISPOSE} events.<br>
     * It is set to zero since this code is only used for deciding whether to propagate such events
     * to the parent nodes, too. This, however, is not necessary and even not valid in context of
     * {@link #DISPOSE} events. (see {@link PNode#firePropertyChange(int, String, Object, Object)}
     * for details).
     */
    public static final int DISPOSE_CODE = 1 << 31; // SUPPRESS CHECKSTYLE MagicNumber
    // this number seems not to be used in other PNode implementations' property codes

    private final PNode pnode;

    /**
     * Constructor.
     * 
     * @param node
     *            the {@link PNode} this listener will be attached to in order to access its
     *            children.
     */
    public NodeDisposeListener(final PNode node) {
        this.pnode = node;
    }

    /**
     * {@inheritDoc}
     */
    public void propertyChange(final PropertyChangeEvent evt) {
        disposePNode(pnode);
    }

    /**
     * Helper method for disposing KLighD-specific {@link PNode PNodes}.
     * 
     * @param pnode
     *            the {@link PNode} that has to be disposed and to whose children the dispose
     *            request is to be propagated
     */
    public static void disposePNode(final PNode pnode) {
        if (pnode instanceof IResourceEmployer) {
            ((IResourceEmployer) pnode).disposeSWTResource();
        }

        for (Object p : pnode.getChildrenReference()) {
            ((PNode) p).firePropertyChange(DISPOSE_CODE, DISPOSE, pnode, null);
        }
    }

    /**
     * A common interface of all KLighD-specific PNodes that employ SWT
     * {@link org.eclipse.swt.graphics.Resource Resources}, which have to be disposed, e.g. while
     * closing the corresponding diagram.
     * 
     * @author chsch
     */
    public interface IResourceEmployer {

        /**
         * Releases the SWT {@link org.eclipse.swt.graphics.Resource Resource(s)} from its employer
         * by disposing it(them) and resetting the particular object attribute(s) to
         * <code>null</code>.
         */
        void disposeSWTResource();

    }
}
