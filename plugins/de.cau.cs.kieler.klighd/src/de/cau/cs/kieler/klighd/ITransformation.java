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

import java.util.List;

/**
 * An interface for a model-to-model transformation between two unrestricted models. ITransformation
 * are intended to be stateless (wrt. data kept in mind for the next run). Hence, all semantic
 * knowledge must be deposited in the provided {@link TransformationContext}.
 * 
 * For implementing custom view synthesis transformation we suggest to take
 * {@link de.cau.cs.kieler.klighd.transformations.AbstractTransformation AbstractTransformation} as
 * super class.
 * 
 * @author mri, chsch
 * 
 * @param <S>
 *            the type of the source model
 * @param <T>
 *            the type of the target model
 */
public interface ITransformation<S, T> {

    /**
     * Callback to the transformation for checking whether it supports the given <code>model</code>.
     * 
     * @param model
     *            the model to test
     * @return true if <code>this</code> transformation supports <code>model</code>, false otherwise
     */
    boolean supports(S model);
    
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
     * Contributes the set of {@link SynthesisOption SynthesisOptions} provided
     * in the UI in order to allow users to influence steer the diagram synthesis.
     * 
     * @return the set of {@link SynthesisOption SynthesisOptions}
     */
    List<SynthesisOption> getDisplayedSynthesisOptions();

}
