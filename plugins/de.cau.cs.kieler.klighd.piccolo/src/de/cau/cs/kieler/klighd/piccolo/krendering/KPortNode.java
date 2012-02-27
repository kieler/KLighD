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
import de.cau.cs.kieler.core.util.IWrapper;
import de.cau.cs.kieler.klighd.piccolo.krendering.controller.RenderingContextData;
import de.cau.cs.kieler.klighd.piccolo.nodes.PEmptyNode;

/**
 * The Piccolo node for representing a {@code KPort}.
 * 
 * @author mri
 */
public class KPortNode extends PEmptyNode implements IWrapper<KPort> {

    private static final long serialVersionUID = 6016725932024647084L;

    /** the property for the Piccolo representation of a port. */
    public static final IProperty<KPortNode> PORT_REP = new Property<KPortNode>(
            "klighd.piccolo.prepresentation");
    
    /** the encapsulated {@code KPort}. */
    private KPort port;

    /**
     * Constructs a Piccolo node for representing a {@code KPort}.
     * 
     * @param port
     *            the port
     */
    public KPortNode(final KPort port) {
        this.port = port;
        setPickable(true);
        RenderingContextData.get(port).setProperty(PORT_REP, this);
    }

    /**
     * {@inheritDoc}
     */
    public KPort getWrapped() {
        return port;
    }

}
