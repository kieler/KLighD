/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
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

import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.KLabelRenderingController;

/**
 * The Piccolo2D node for representing a {@code KLabel}.
 * 
 * @author mri
 * @author chsch
 */
public class KLabelNode extends KGraphElementNode<KLabel> {

    private static final long serialVersionUID = -3999806360081871118L;

    /** the property name for changes of the label's text. */
    public static final String PROPERTY_TEXT = "labelText";

    /** the label rendering controller deployed to manage the rendering of the label. */
    private KLabelRenderingController renderingController;

    /** the text. */
    private String text = null;

    /**
     * Constructs a Piccolo2D node for representing a {@code KLabel}.
     * 
     * @param label
     *            the label
     */
    public KLabelNode(final KLabel label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    public void setRenderingController(final AbstractKGERenderingController<KLabel,
            ? extends IInternalKGraphElementNode<KLabel>> controller) {

        if (controller == null || controller instanceof KLabelRenderingController) {
            this.renderingController = (KLabelRenderingController) controller;
        } else {
            final String s = "KLighD: Fault occured while building up a concrete KLabel rendering: "
                    + "KLabelNodes are supposed to be controlled by KLabelRenderingControllers rather "
                    + "than " + controller.getClass().getCanonicalName();
            throw new IllegalArgumentException(s);
        }
    }

    /**
     * {@inheritDoc}
     */
    public KLabelRenderingController getRenderingController() {
        return this.renderingController;
    }
    
    /**
     * Sets the text for the label.
     * 
     * @param text
     *            the text
     */
    public void setText(final String text) {
        this.text = text;
        firePropertyChange(-1, PROPERTY_TEXT, null, text);
    }

    /**
     * Returns the text for the label.
     * 
     * @return the text
     */
    public String getText() {
        return text;
    }
}
