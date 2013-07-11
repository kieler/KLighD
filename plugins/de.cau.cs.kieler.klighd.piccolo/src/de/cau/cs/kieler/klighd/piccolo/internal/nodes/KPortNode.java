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
package de.cau.cs.kieler.klighd.piccolo.krendering;

import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.KPortRenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PZIndexNode;

/**
 * The Piccolo node for representing a {@code KPort}.
 * 
 * @author mri
 */
public class KPortNode extends PZIndexNode implements ILabeledGraphElement<KPort> {

    private static final long serialVersionUID = 6016725932024647084L;

    /** the property for the Piccolo representation of a port. */
    public static final IProperty<KPortNode> PORT_REP = new Property<KPortNode>(
            "klighd.piccolo.prepresentation");
    
    /** the number of z-layers (rendering and ports). */
    private static final int Z_LAYERS = 2;
    /** the z-index for the label layer. */
    private static final int LABEL_LAYER = 1;
    
    /** the represented {@link KPort}. */
    private transient KPort port;
    /** the port rendering controller deployed to manage the rendering of {@link #port}. */
    private KPortRenderingController renderingController;

    /**
     * Constructs a Piccolo node for representing a {@code KPort}.
     * 
     * @param port
     *            the port
     */
    public KPortNode(final KPort port) {
        super(Z_LAYERS);
        this.port = port;
        RenderingContextData.get(port).setProperty(PORT_REP, this);
        Boolean b = port.getData(KShapeLayout.class).getProperty(
                KlighdConstants.KLIGHD_SELECTION_UNPICKABLE);
        setPickable(b != null && b.equals(Boolean.TRUE) ? false : true);
    }
    
    /**
     * {@inheritDoc}
     */
    public KPort getGraphElement() {
        return port;
    }

    /**
     * {@inheritDoc}
     */
    public void setRenderingController(
            final AbstractKGERenderingController<KPort, ? extends IGraphElement<KPort>> controller) {
        if (controller == null || controller instanceof KPortRenderingController) {
            this.renderingController = (KPortRenderingController) controller;
        } else {
            String s = "KLighD: Fault occured while building up a concrete KPort rendering: KPortNodes"
                    + " are supposed to be controlled by KPortRenderingController rather than "
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
        addChild(label, LABEL_LAYER);
    }
}
