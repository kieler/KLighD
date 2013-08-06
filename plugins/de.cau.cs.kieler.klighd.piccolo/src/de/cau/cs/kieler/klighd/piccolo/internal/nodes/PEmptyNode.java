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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * The base class for nodes with no visual representation besides child nodes.
 * 
 * @author mri, chsch
 */
public class PEmptyNode extends PNode {

    private static final long serialVersionUID = 6335184700871752958L;

    /**
     * Constructor.
     */
    public PEmptyNode() {
        final PropertyChangeListener disposeListener = new PropertyChangeListener() {

            public void propertyChange(final PropertyChangeEvent event) {
                if (event.getNewValue() == null) {
                    @SuppressWarnings("unchecked")
                    final List<PNode> children = PEmptyNode.this.getChildrenReference();
                    for (PNode p : children) {
                        p.firePropertyChange(NodeUtil.DISPOSE_CODE, NodeUtil.DISPOSE, this, null);
                    }
                }
            }
        };
        this.addPropertyChangeListener(PROPERTY_PARENT, disposeListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        // do nothing
    }
    
}
