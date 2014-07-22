/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * Common base class of KLighD-specific {@link PNode PNodes}.
 * 
 * @author chsch
 */
public class KlighdNode extends PNode {

    private static final long serialVersionUID = 6876586117083105843L;

    /**
     * Constructor.
     */
    public KlighdNode() {
        this.addPropertyChangeListener(NodeDisposeListener.DISPOSE, new NodeDisposeListener(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getPickable() {
        return super.getPickable() && !getOccluded();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        // do nothing
    }
}
