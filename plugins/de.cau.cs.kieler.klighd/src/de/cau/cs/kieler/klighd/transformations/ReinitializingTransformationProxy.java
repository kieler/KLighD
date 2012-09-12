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

import java.util.Set;

import com.google.inject.Guice;

import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.TransformationOption;


/**
 * This transformation proxy realizes the re-initialization of stateful model transformations. It is
 * needed for Xtend2 based transformation leveraging "create extensions", for example. The
 * implementation cares about creating new transformation instances, as well as their proper
 * initialization by means of Guice.<br>
 * <br>
 * 
 * Transformation that shall be preceded with an instance of this class must be registered by
 * prefixing their class name with the string <code>
 * "de.cau.cs.kieler.klighd/de.cau.cs.kieler.klighd.transformations.GuiceBasedTransformationFactory:"
 * </code>.
 * 
 * This class shall not be instantiated by any user program but only by the runtime.
 * 
 * @param <S>
 *            type of the input models
 * @param <T>
 *            type of the created models
 * 
 * @author chsch
 */
public class ReinitializingTransformationProxy<S, T> extends AbstractTransformation<S, T> {

    private Class<AbstractTransformation<S, T>> transformationClass = null;
    private ITransformation<S, T> transformationDelegate = null;
    

    /**
     * Package protected constructor.
     * @param clazz the transformation class
     */
    ReinitializingTransformationProxy(final Class<AbstractTransformation<S, T>> clazz) {
        this.transformationClass = clazz;
    }
    
    
    private AbstractTransformation<S, T> getNewDelegateInstance() {
        return Guice.createInjector().getInstance(this.transformationClass);
    }
    
    
    /**
     * {@inheritDoc}<br>
     * Delegates to the 'delegate' object.
     */
    public T transform(final S model, final TransformationContext<S, T> transformationContext) {
        this.transformationDelegate = getNewDelegateInstance(); 
        return this.transformationDelegate.transform(model, transformationContext);
    }
    
    
    /**
     * Stub enforced by {@link AbstractTransformation}.
     * @param model the model
     * @return null
     */
    public T transform(final S model) {
        return null;
    }


    /**
     * {@inheritDoc}
     */
    public Set<TransformationOption> getTransformationOptions() {
        if (this.transformationDelegate == null) {
            return getNewDelegateInstance().getTransformationOptions();
        }
        return this.transformationDelegate.getTransformationOptions();
    }
    
    
    /**
     * {@inheritDoc}
     */
    public String toString() {
        return this.getClass().getSimpleName() + "(" + getNewDelegateInstance() + ")";
    }
    

    /**
     * Getter for the delegate attribute.
     * @return the delegate
     */
    public ITransformation<S, T> getDelegate() {
        return this.transformationDelegate;
    }
    
    
    /**
     * {@inheritDoc}
     */
    protected void inferSourceAndTargetModelClass() {
        this.setTriedToInferClass();
        AbstractTransformation<S, T> delegate = getNewDelegateInstance();        
        if (delegate != null) {
            delegate.inferSourceAndTargetModelClass();
            this.setSourceClass(delegate.getSourceClass());
            this.setTargetClass(delegate.getTargetClass());
        }
    }
}
