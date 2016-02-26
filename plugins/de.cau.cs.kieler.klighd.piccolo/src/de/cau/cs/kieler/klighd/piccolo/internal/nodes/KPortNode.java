/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import org.eclipse.elk.graph.KPort;

import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.KPortRenderingController;
import edu.umd.cs.piccolo.PNode;

/**
 * The Piccolo2D node for representing a {link KPort}.
 * 
 * @author mri
 * @author chsch
 */
public class KPortNode extends KGraphElementNode<KPort> implements
        IInternalKGraphElementNode.IKLabeledGraphElementNode<KPort> {

    private static final long serialVersionUID = 6016725932024647084L;

    /** the port rendering controller deployed to manage the rendering of {@link #port}. */
    private KPortRenderingController renderingController;

    /**
     * Constructs a Piccolo2D node for representing a {@code KPort}.
     * 
     * @param port
     *            the port
     */
    public KPortNode(final KPort port) {
        super(port);
    }

    /**
     * {@inheritDoc}
     */
    public void setRenderingController(final AbstractKGERenderingController<KPort,
            ? extends IInternalKGraphElementNode<KPort>> controller) {

        if (controller == null || controller instanceof KPortRenderingController) {
            this.renderingController = (KPortRenderingController) controller;

        } else {
            final String s = "KLighD: Fault occured while building up a concrete KPort rendering: "
                    + "KPortNodes are supposed to be controlled by KPortRenderingController rather than "
                    + controller.getClass().getCanonicalName();
            throw new IllegalArgumentException(s);
        }
    }

    /**
     * {@inheritDoc}
     */
    public KPortRenderingController getRenderingController() {
        return this.renderingController;
    }
    
    /**
     * Adds the representation of a label to this port.
     * 
     * @param label
     *            the label representation
     */
    public void addLabel(final KLabelNode label) {
        addChild(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChild(final PNode child) {
        if (child instanceof KLabelNode) {
            super.addChild(child);
        } else {
            // There is only one figure child supposed to be attached to KPortNodes
            //  so the following is justified
            super.addChild(0, child);
        }
    }
}
