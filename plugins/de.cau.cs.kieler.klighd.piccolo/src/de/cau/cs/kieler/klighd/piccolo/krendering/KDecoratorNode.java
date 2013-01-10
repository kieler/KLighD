/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.krendering;

import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.klighd.piccolo.nodes.PChildRepresentedNode;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * The Piccolo node for representing a {@code KRendering} being treated as a edge decorator.
 * 
 * @author chsch
 */
public class KDecoratorNode extends PChildRepresentedNode implements ITracingElement<KRendering> {

    private static final long serialVersionUID = -2824069198134013044L;

    private KRendering rendering;

    /**
     * Standard constructor.
     * 
     * @param theRendering
     *            the rendering being represented by this node.
     */
    public KDecoratorNode(final KRendering theRendering) {
        this.rendering = theRendering;
        this.setPickable(true);
    }
    
    /**
     * {@inheritDoc}
     */
    public KRendering getGraphElement() {
        return this.rendering;
    }

    /**
     * {@inheritDoc}.<br>
     * <br>
     * KDecoratorNodes state greedy picking as it is unlikely that they contain nested pickable
     * elements, e.g. text fields.
     */
    protected boolean pick(final PPickPath pickPath) {
        return true;
    }
    
}
