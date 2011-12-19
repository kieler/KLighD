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

import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * The context in which a model transformation is executed.
 * 
 * @author mri
 * 
 * @param <S>
 *            the type of the source model
 * @param <T>
 *            the type of the target model
 */
public final class TransformationContext<S, T> extends MapPropertyHolder {

    /**
     * Creates a transformation context for a given transformation.
     * 
     * @param <S>
     *            the type of the source model
     * @param <T>
     *            the type of the target model
     * @param transformation
     *            the transformation
     * @return the transformation context
     */
    protected static <S, T> TransformationContext<S, T> create(
            final ITransformation<S, T> transformation) {
        TransformationContext<S, T> transformationContext = new TransformationContext<S, T>();
        transformationContext.transformation = transformation;
        return transformationContext;
    }

    /** the transformation in this context. */
    private ITransformation<S, T> transformation;

    /** the source model used in this context. */
    private S sourceModel = null;
    /** the target model created in this context. */
    private T targetModel = null;

    /** the view context in which the transformation context is contained. */
    private ViewContext viewContext = null;

    /**
     * Use {@code create} to instantiate this class.
     */
    private TransformationContext() {
        // do nothing
    }

    /**
     * Returns the transformation for this transformation context.
     * 
     * @return the transformation
     */
    public ITransformation<S, T> getTransformation() {
        return transformation;
    }

    /**
     * Returns the source model of this transformation context.
     * 
     * @return the source model
     */
    public S getSourceModel() {
        return sourceModel;
    }

    /**
     * Sets the source model of this transformation context.
     * 
     * @param sourceModel
     *            the source model
     */
    protected void setSourceModel(final S sourceModel) {
        this.sourceModel = sourceModel;
    }

    /**
     * Returns the target model of this transformation context.
     * 
     * @return the target model or null if the transformation has not executed yet
     */
    public T getTargetModel() {
        return targetModel;
    }

    /**
     * Sets the target model of this transformation context.
     * 
     * @param targetModel
     *            the target model
     */
    protected void setTargetModel(final T targetModel) {
        this.targetModel = targetModel;
    }

    /**
     * Returns the view context this transformation context is part of.
     * 
     * @return the view context
     */
    public ViewContext getViewContext() {
        return viewContext;
    }

    /**
     * Returns the element in the source model which is represented by the given element in the
     * target model.
     * 
     * @param element
     *            the element in the target model
     * @return the element in the source model or null if the element could not be found
     */
    public Object getSourceElement(final Object element) {
        return transformation.getSourceElement(element, this);
    }

    /**
     * Returns the element in the target model which represents the given element in the source
     * model.
     * 
     * @param element
     *            the element in the source model
     * @return the element in the target model or null if the element could not be found
     */
    public Object getTargetElement(final Object element) {
        return transformation.getTargetElement(element, this);
    }

    /**
     * Sets the view context containing this transformation context.<br>
     * <br>
     * Invoked only by {@code ViewContext}.
     * 
     * @param viewContext
     *            the view context
     */
    protected void setViewContext(final ViewContext viewContext) {
        this.viewContext = viewContext;
    }

}
