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
package de.cau.cs.kieler.klighd.transformations;

import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.TransformationContext;

/**
 * An abstract base class for KLighD model transformations.<br>
 * Provides a {@code transform} method with a simpler signature.
 * 
 * @author mri
 * 
 * @param <S>
 *            the type of the source model
 * @param <T>
 *            the type of the target model
 */
public abstract class AbstractTransformation<S, T> implements ITransformation<S, T> {

    /** The current transformation context. */
    private TransformationContext<S, T> context = null;
    /** The lookup tables maintaining the model-image-relation of the transformation. */
    private Multimap<Object, Object> sourceTargetElementMap = null;
    private Map<Object, Object> targetSourceElementMap = null;

    /**
     * {@inheritDoc}
     */
    public T transform(final S model, final TransformationContext<S, T> transformationContext) {
        context = transformationContext;
        T targetModel = transform(transformationContext.getSourceModel());
        context = null;
        return targetModel;
    }

    /**
     * Performs the actual transformation from an object of type {@code S} to a model of type
     * {@code T}.
     * 
     * @param model
     *            the source model
     * @return the target model
     */
    public abstract T transform(final S model);

    /**
     * Returns the current transformation context this transformation is executing in.
     * 
     * @return the transformation context while executing the transform method; null else
     */
    public TransformationContext<S, T> getTransformationContext() {
        return context;
    }

    /**
     * {@inheritDoc}
     */
    public Object getSourceElement(final Object element,
            final TransformationContext<S, T> transformationContext) {
        context = transformationContext;
        Object sourceElement = getSourceElement(element);
        context = null;
        return sourceElement;
    }
    
    /**
     * Returns the element in the source model which is represented by the given element in the
     * target model.
     * 
     * @param target
     *            the element in the target model
     * @return the element in the source model or null if the element could not be found
     */
    public Object getSourceElement(final Object target) {
        if (this.targetSourceElementMap != null) {
            return this.targetSourceElementMap.get(target);
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public Object getTargetElement(final Object element,
            final TransformationContext<S, T> transformationContext) {
        context = transformationContext;
        Object targetElement = getTargetElement(element);
        context = null;
        return targetElement;
    }
    
    /**
     * Returns the element in the target model which represents the given element in the source
     * model.
     * 
     * @param source
     *            the element in the source model
     * @return the element in the target model or null if the element could not be found
     */
    public Object getTargetElement(final Object source) {
        if (this.sourceTargetElementMap != null) {
            return this.sourceTargetElementMap.get(source);
        }
        return null;
    }

    /**
     * Method to put a pair of source target into the lookup table.<br>
     * Name, Parameter ordering, and return value (the target) are optimized for
     * calling in Xtend2 based transformations in a fluent interface fashion, like
     * "model.createShape().putToSourceTargetLookUpWith(model);"
     * 
     * @param <C> the type of the target element which is implicitly determined 
     * @param target the image element
     * @param source the model element
     * @return the image element
     */
    protected <C> C putToLookUpWith(final C target, final Object source) {
        if (this.targetSourceElementMap == null
                || this.sourceTargetElementMap == null) {
            this.targetSourceElementMap = Maps.newHashMap();
            this.sourceTargetElementMap = HashMultimap.create();
        }
        this.sourceTargetElementMap.put(source, target);
        this.targetSourceElementMap.put(target, source);
        return target;
    }
     
    
    
    /** whether it has been tried to infer the classes. */
    private boolean triedToInferClasses = false;
    /** the inferred source model class. */
    private Class<?> sourceModelClass = null;
    /** the inferred target model class. */
    private Class<?> targetModelClass = null;

    /**
     * {@inheritDoc}
     */
    public Class<?> getSourceClass() {
        if (!triedToInferClasses) {
            inferSourceAndTargetModelClass();
        }
        return sourceModelClass;
    }
    
    /**
     * Setter for the sourceModelClass property.
     * @param theSourceClass the class of the source models.
     */
    protected void setSourceClass(final Class<?> theSourceClass) {
        this.sourceModelClass = theSourceClass;
    }
    
    /**
     * {@inheritDoc}
     */
    public Class<?> getTargetClass() {
        if (!triedToInferClasses) {
            inferSourceAndTargetModelClass();
        }
        return targetModelClass;
    }  
    
    /**
     * Setter for the sourceModelClass property.
     * @param theTargetClass the class of the source models.
     */
    protected void setTargetClass(final Class<?> theTargetClass) {
        this.targetModelClass = theTargetClass;
    }   
    
    /**
     * Setter for the triedToInferClasses flag.
     */
    protected void setTriedToInferClass() {
        this.triedToInferClasses = true;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object model) {
        return true;
    }


    /** the name of the {@code transform} method. */
    private static final String TRANSFORM_METHOD_NAME = "transform";

    /**
     * Tries to infer the class of the source and target model by analyzing the transform method.
     */
    protected void inferSourceAndTargetModelClass() {
        triedToInferClasses = true;
        // try to find a method with one parameter which returns non-void
        // takes the first matching method if the parameter is not Object
        Method transformMethod = null;
        for (Method method : getClass().getDeclaredMethods()) {
            if (method.getName().equals(TRANSFORM_METHOD_NAME)
                    && method.getParameterTypes().length == 1
                    && !method.getReturnType().equals(Void.TYPE)) {
                transformMethod = method;
                // keep searching if the parameter is of type Object
                // this is necessary to skip the method with type Object that is always present when
                // dealing with generic typed methods
                if (!method.getParameterTypes()[0].equals(new Object().getClass())) {
                    break;
                }
            }
        }
        // infer the types if a matching method has been found
        if (transformMethod != null) {
            sourceModelClass = transformMethod.getParameterTypes()[0];
            targetModelClass = transformMethod.getReturnType();
        }
    }

}
