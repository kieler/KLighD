/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
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
package de.cau.cs.kieler.klighd;

/**
 * The interface for classes which implement the execution of a model transformation in a given view
 * context.
 * 
 * @author mri
 */
public interface ITransformationEngine {

    /**
     * Tries to transform the model in the given view context.
     * 
     * @param viewContext
     *            the view context
     * @param model
     *            the model
     * @return true if the transformation has been performed successfully; false else
     */
    boolean tryTransform(final ViewContext viewContext, final Object model);

    /**
     * Transform the model in the given view context under the assumption that it is supported.
     * 
     * @param viewContext
     *            the view context
     * @param model
     *            the model
     * @return the transformed model
     */
    Object transform(final ViewContext viewContext, final Object model);

}
