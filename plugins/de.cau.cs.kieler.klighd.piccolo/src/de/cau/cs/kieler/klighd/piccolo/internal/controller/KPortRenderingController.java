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

import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
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
        super(port.getViewModelElement(), port);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PNode internalUpdateRendering() {
        final KPortNode repNode = getRepresentation();

        // evaluate the rendering data
        final KRendering currentRendering = getCurrentRendering();

        final PNode renderingNode;
        if (currentRendering != null) {
            renderingNode = handleAreaAndPointPlacementRendering(currentRendering, repNode);
        } else {
            renderingNode = handleAreaAndPointPlacementRendering(createDefaultRendering(), repNode);
        }
        
        return renderingNode;
    }
    
    /**
     * Creates a default rendering for ports without attached rendering data.
     * 
     * @return the rendering
     */
    protected KRendering createDefaultRendering() {
        // create the default rendering model
        final KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        final KRectangle rect = factory.createKRectangle();
        
        final KForeground foreground = factory.createKForeground().setColor(0, 0, 0);
        final KBackground background = factory.createKBackground().setColor(0, 0, 0);

        rect.getStyles().add(foreground);
        rect.getStyles().add(background);
        return rect;
    }
    
}
