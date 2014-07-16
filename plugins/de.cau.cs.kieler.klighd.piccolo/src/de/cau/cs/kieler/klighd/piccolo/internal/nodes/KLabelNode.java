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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.KLabelRenderingController;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.KlighdSemanticDiagramData;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * The Piccolo node for representing a {@code KLabel}.
 * 
 * @author mri
 */
public class KLabelNode extends PEmptyNode implements IGraphElement<KLabel> {

    private static final long serialVersionUID = -3999806360081871118L;

    /** the property name for changes of the label's text. */
    public static final String PROPERTY_TEXT = "labelText";

    /** the represented {@link KLabel}. */
    private transient KLabel label;
    /** the label rendering controller deployed to manage the rendering of {@link #label}. */
    private KLabelRenderingController renderingController;

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
    }

    /**
     * {@inheritDoc}
     */
    public KLabel getGraphElement() {
        return label;
    }

    /**
     * {@inheritDoc}
     */
    public void setRenderingController(
            final AbstractKGERenderingController<KLabel, ? extends IGraphElement<KLabel>> controller) {
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        KlighdSWTGraphics g2 = (KlighdSWTGraphics) paintContext.getGraphics();
        KlighdSemanticDiagramData sd =
                getGraphElement().getData(KLayoutData.class).getProperty(
                        KlighdProperties.SEMANTIC_DATA);
        g2.startGroup(sd);
        super.paint(paintContext);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintAfterChildren(final PPaintContext paintContext) {
        super.paintAfterChildren(paintContext);
        KlighdSWTGraphics g2 = (KlighdSWTGraphics) paintContext.getGraphics();
        g2.endGroup();
    }
}
