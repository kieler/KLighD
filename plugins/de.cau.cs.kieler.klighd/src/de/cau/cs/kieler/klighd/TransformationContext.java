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

import java.util.Map;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

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
public final class TransformationContext<S, T> {

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
    public static <S, T> TransformationContext<S, T> create(
            final ITransformation<S, T> transformation) {
        TransformationContext<S, T> transformationContext = new TransformationContext<S, T>();
        transformationContext.transformation = transformation;
        return transformationContext;
    }

    /** the transformation in this context. */
    private ITransformation<S, T> transformation;

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
     * Returns the view context this transformation context is part of.
     * 
     * @return the view context
     */
    public ViewContext getViewContext() {
        return viewContext;
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


    /** The lookup tables maintaining the model-image-relation of the transformation. */
    private Multimap<Object, Object> sourceTargetElementMap = null;
    private Map<Object, Object> targetSourceElementMap = null;
   
    /**
     * Put a pair of a model and a derived element into the lookup table.
     * 
     * @param derived the derived element
     * @param model the model element
     */
    public void addSourceTargetPair(final Object model, final Object derived) {
        if (this.targetSourceElementMap == null
                || this.sourceTargetElementMap == null) {
            this.targetSourceElementMap = Maps.newHashMap();
            this.sourceTargetElementMap = HashMultimap.create();
        }
        this.sourceTargetElementMap.put(model, derived);        
        this.targetSourceElementMap.put(derived, model);        
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
        if (this.targetSourceElementMap != null) {
            return this.targetSourceElementMap.get(element);
        }
        return null;
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
        if (this.sourceTargetElementMap != null) {
            return this.sourceTargetElementMap.get(element);
        }
        return null;
    }
    
    /**
     * Cleans up the source target mappings.
     */
    public void clear() {
        if (this.sourceTargetElementMap != null) {
            this.sourceTargetElementMap.clear();
        }
        if (this.targetSourceElementMap != null) {
            this.targetSourceElementMap.clear();
        }
    }
    
}
