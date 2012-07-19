/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.krendering;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klighd.piccolo.krendering.controller.RenderingContextData;
import de.cau.cs.kieler.klighd.piccolo.nodes.PEmptyNode;

/**
 * The Piccolo node for representing a {@code KLabel}.
 * 
 * @author mri
 */
public class KLabelNode extends PEmptyNode implements IGraphElement<KLabel> {

    private static final long serialVersionUID = -3999806360081871118L;

    /** the property for the Piccolo representation of a label. */
    public static final IProperty<KLabelNode> LABEL_REP = new Property<KLabelNode>(
            "klighd.piccolo.prepresentation");

    /** the property name for changes of the label's text. */
    public static final String PROPERTY_TEXT = "labelText";

    /** the encapsulated {@code KLabel}. */
    private transient KLabel label;

    /** the text. */
    private String text = "";

    /**
     * Constructs a Piccolo node for representing a {@code KLabel}.
     * 
     * @param label
     *            the label
     */
    public KLabelNode(final KLabel label) {
        this.label = label;
        setPickable(true);
        RenderingContextData.get(label).setProperty(LABEL_REP, this);
    }

    /**
     * {@inheritDoc}
     */
    public KLabel getGraphElement() {
        return label;
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
