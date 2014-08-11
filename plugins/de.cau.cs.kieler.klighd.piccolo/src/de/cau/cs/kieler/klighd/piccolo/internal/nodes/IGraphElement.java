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

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;

/**
 * The interface for custom Piccolo2D nodes representing a {@link KGraphElement}.
 * 
 * @author mri, chsch
 * 
 * @param <T>
 *            the type of the graph element
 */
public interface IGraphElement<T extends KGraphElement> extends ITracingElement<T> {

    /**
     * Returns the graph element represented by this node.<br>
     * With the introduction of {@link ITracingElement} this method definition is obsolete,
     * it is merely kept for the sake of having the whole interface definition at a single place.
     * 
     * @return the graph element
     */
    T getGraphElement();
    
    /**
     * Setter for memorizing the rendering controller that is in charge of managing the correct
     * display and update of the diagram figures based on
     * {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} definitions attached to the
     * {@link KGraphElement KGraphElements}.
     * 
     * @param controller
     *            the deployed rendering controller
     */
    void setRenderingController(
            AbstractKGERenderingController<T, ? extends IGraphElement<T>> controller);

    /**
     * Getter for accessing the rendering controller that is in charge of managing the correct
     * display and update of the diagram figures based on
     * {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} definitions attached to the
     * {@link KGraphElement KGraphElements}.
     * 
     * @return the related rendering controller
     */
    AbstractKGERenderingController<T, ? extends IGraphElement<T>> getRenderingController();

    /**
     * The interface for Piccolo2D nodes representing a {@link KLabeledGraphElement}.
     * 
     * @author mri
     * @author chsch
     * 
     * @param <T> the type of the labeled graph element
     */
    public interface ILabeledGraphElement<T extends KLabeledGraphElement> extends IGraphElement<T> {

        /**
         * Adds the representation of a label to this element.
         * 
         * @param label
         *            the label representation
         */
        void addLabel(KLabelNode label);
    }
}