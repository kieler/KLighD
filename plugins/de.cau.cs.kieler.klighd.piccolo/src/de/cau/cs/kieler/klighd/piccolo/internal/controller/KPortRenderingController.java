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
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.util.Collections;

import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KPortNode;
import edu.umd.cs.piccolo.PNode;

/**
 * @author mri
 */
public class KPortRenderingController extends AbstractKGERenderingController<KPort, KPortNode> {

    /**
     * Constructs a rendering controller for a port.
     * 
     * @param port
     *            the Piccolo node representing a port
     */
    public KPortRenderingController(final KPortNode port) {
        super(port.getGraphElement(), port);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PNode internalUpdateRendering() {
        PNode repNode = getRepresentation();

        // evaluate the rendering data
        KRendering currentRendering = getCurrentRendering();
        PNode renderingNode;
        if (currentRendering != null) {
            renderingNode = handleAreaPlacementRendering(currentRendering,
                    Collections.<KStyle>emptyList(), repNode);
        } else {
            renderingNode = handleAreaPlacementRendering(createDefaultPortRendering(),
                    Collections.<KStyle>emptyList(), repNode);
        }
        
        return renderingNode;
    }
    
    /**
     * Creates a default rendering for ports without attached rendering data.
     * 
     * @return the rendering
     */
    private static KRendering createDefaultPortRendering() {
        // create the default rendering model
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KRectangle rect = factory.createKRectangle();
        
        KForeground foreground = factory.createKForeground();
        foreground.setColor(factory.createKColor());
        KBackground background = factory.createKBackground();
        background.setColor(factory.createKColor());

        rect.getStyles().add(foreground);
        rect.getStyles().add(background);
        return rect;
    }
    
}
