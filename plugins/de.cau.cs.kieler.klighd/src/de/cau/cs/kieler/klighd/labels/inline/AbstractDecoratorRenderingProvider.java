/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.labels.inline;

import de.cau.cs.kieler.klighd.krendering.KAreaPlacementData;
import de.cau.cs.kieler.klighd.krendering.KBottomPosition;
import de.cau.cs.kieler.klighd.krendering.KLeftPosition;
import de.cau.cs.kieler.klighd.krendering.KPosition;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory;
import de.cau.cs.kieler.klighd.krendering.KRightPosition;
import de.cau.cs.kieler.klighd.krendering.KTopPosition;
import de.cau.cs.kieler.klighd.krendering.KXPosition;
import de.cau.cs.kieler.klighd.krendering.KYPosition;

/**
 * Base class that can be used by decorator rendering providers. Since we cannot use KRendering
 * extensions in the base KLighD plug-in, this base class provides a few convenience classes to
 * setup positions and things.
 * 
 * @author cds
 */
public abstract class AbstractDecoratorRenderingProvider implements IDecoratorRenderingProvider {
    
    /** Rendering factory. */
    protected static final KRenderingFactory R_FACTORY = KRenderingFactory.eINSTANCE;

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Rendering Utility Methods
    
    /**
     * Creates a {@link KLeftPosition} with the given data.
     * 
     * @param abs absolute position.
     * @param rel relative position.
     * @return generated position.
     */
    protected KLeftPosition left(final float abs, final float rel) {
        KLeftPosition pos = R_FACTORY.createKLeftPosition();
        pos.setAbsolute(abs);
        pos.setRelative(rel);
        return pos;
    }

    /**
     * Creates a {@link KRightPosition} with the given data.
     * 
     * @param abs absolute position.
     * @param rel relative position.
     * @return generated position.
     */
    protected KRightPosition right(final float abs, final float rel) {
        KRightPosition pos = R_FACTORY.createKRightPosition();
        pos.setAbsolute(abs);
        pos.setRelative(rel);
        return pos;
    }

    /**
     * Creates a {@link KTopPosition} with the given data.
     * 
     * @param abs absolute position.
     * @param rel relative position.
     * @return generated position.
     */
    protected KTopPosition top(final float abs, final float rel) {
        KTopPosition pos = R_FACTORY.createKTopPosition();
        pos.setAbsolute(abs);
        pos.setRelative(rel);
        return pos;
    }

    /**
     * Creates a {@link KBottomPosition} with the given data.
     * 
     * @param abs absolute position.
     * @param rel relative position.
     * @return generated position.
     */
    protected KBottomPosition bottom(final float abs, final float rel) {
        KBottomPosition pos = R_FACTORY.createKBottomPosition();
        pos.setAbsolute(abs);
        pos.setRelative(rel);
        return pos;
    }
    
    /**
     * Sets an area placement on the given rendering using the given positions.
     * 
     * @param rendering the rendering to set the placement data on.
     * @param left left position.
     * @param top top position.
     * @param right right position.
     * @param bottom bottom position.
     */
    protected void setPlacement(final KRendering rendering, final KXPosition<?> left,
            final KYPosition<?> top, final KXPosition<?> right, final KYPosition<?> bottom) {
        
        KPosition topLeft = R_FACTORY.createKPosition();
        topLeft.setY(top);
        topLeft.setX(left);
        
        KPosition bottomRight = R_FACTORY.createKPosition();
        bottomRight.setY(bottom);
        bottomRight.setX(right);
        
        KAreaPlacementData data = R_FACTORY.createKAreaPlacementData();
        data.setTopLeft(topLeft);
        data.setBottomRight(bottomRight);
        
        rendering.setPlacementData(data);
    }

}
