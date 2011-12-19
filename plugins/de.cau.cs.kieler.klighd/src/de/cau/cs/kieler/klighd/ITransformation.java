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
package de.cau.cs.kieler.klighd;

/**
 * An interface for a model-to-model transformation between two unrestricted models.
 * 
 * @author mri
 * 
 * @param <S>
 *            the type of the source model
 * @param <T>
 *            the type of the target model
 */
public interface ITransformation<S, T> {

    /**
     * Performs the actual transformation from an object of type {@code S} to a model of type
     * {@code T} in a given transformation context.
     * 
     * @param model
     *            the source model
     * @param transformationContext
     *            the transformation context
     * @return the target model
     */
    T transform(S model, TransformationContext<S, T> transformationContext);

    /**
     * Returns the element in the source model which is represented by the given element in the
     * target model.
     * 
     * @param element
     *            the element in the target model
     * @param transformationContext
     *            the transformation context
     * @return the element in the source model or null if the element could not be found
     */
    Object getSourceElement(Object element, TransformationContext<S, T> transformationContext);

    /**
     * Returns the element in the target model which represents the given element in the source
     * model.
     * 
     * @param element
     *            the element in the source model
     * @param transformationContext
     *            the transformation context
     * @return the element in the target model or null if the element could not be found
     */
    Object getTargetElement(Object element, TransformationContext<S, T> transformationContext);

    /**
     * Returns the class of the source model.
     * 
     * @return the class of the source model
     */
    Class<?> getSourceClass();

    /**
     * Returns the class of the target model.
     * 
     * @return the class of the target model
     */
    Class<?> getTargetClass();
    
    /**
     * Returns whether the given model is a valid source for the transformation. This can be used in
     * addition to the source/target model type inference in order to restrict the application of
     * the transformation further. Especially in case of transformations with the same source and
     * target model type (e.g. filters) this method will be used in order to determine the
     * application of the transformation.
     * 
     * @param model
     *            the model, an instance of the class returned by {@code getSourceClass}
     * @return true if this transformation supports the given model as a source; false else
     */
    boolean supports(final Object model);

}
