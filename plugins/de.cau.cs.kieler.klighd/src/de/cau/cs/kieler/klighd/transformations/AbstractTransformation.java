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
//import java.util.Map;
//
//import com.google.common.collect.HashMultimap;
//import com.google.common.collect.Maps;
//import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.TransformationContext;

/**
 * An abstract base class for KLighD model transformations.<br>
 * Provides a {@code transform} method with a simpler signature.
 * 
 * chsch: Currently it is not clear for, whether we need the
 * getSourceElement/getTargetElement methods, since my current
 * transformation save the mapping immediately in the related context
 * so the context does not need to ask the transformation for that
 * (and actually does not do that).
 * However I might imaging that this feature is desirable in some context
 * so I leave the methods still in here.
 * 
 * @author mri
 * 
 * @param <S>
 *            the type of the source model
 * @param <T>
 *            the type of the target model
 */
public abstract class AbstractTransformation<S, T> implements ITransformation<S, T> {
    
    private TransformationContext<S, T> currentContext = null;
    
    /**
     * Initializes the transformation run.
     * Currently, just keeps the context to be used
     * (allowing to neglect it in the concrete transformation methods).
     * 
     * @param transformationContext the context to be used during the current run
     */
    protected void use(final TransformationContext<S, T> transformationContext) {        
        this.currentContext = transformationContext;
        this.currentContext.clear();
    }

    /**
     * {@inheritDoc}
     */
    public Object getSourceElement(final Object element,
            final TransformationContext<S, T> transformationContext) {
        return this.currentContext.getSourceElement(element);
    }
    
    /**
     * {@inheritDoc}
     */
    public Object getTargetElement(final Object element,
            final TransformationContext<S, T> transformationContext) {
        return this.currentContext.getTargetElement(element);
    }

    /**
     * Method to put a pair of source target into the lookup table.<br>
     * Name, Parameter ordering, and return value (the target) are optimized for
     * calling in Xtend2 based transformations in a fluent interface fashion, like
     * "model.createShape().putToSourceTargetLookUpWith(model);"<br><br>
     * 
     * Usage requires to perform 'use(TransformationContext)' at the beginning of
     * {@link ITransformation#transform(Object, TransformationContext)}.
     * 
     * @param <D> the type of the target element which is implicitly determined 
     * @param derived the derived element
     * @param source the model element
     * @return the image element
     */
    protected <D> D putToLookUpWith(final D derived, final Object source) {
        if (this.currentContext == null) {
            throw new WrappedException(new KielerModelException("KLighD transformation "
                    + this.getClass().getCanonicalName()
                    + " uses 'putToLookUp(...) and probably does not invoke"
                    + "'use(TransformationContxt)' at the beginning of its 'transform()' method",
                    this));
        }
        this.currentContext.addSourceTargetPair(source, derived);
        return derived;
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
                    && method.getParameterTypes().length == 2
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
