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

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;

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
 * Transformations that shall be wrapped with an instance of this class must be registered by
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
    private Module transformationClassBinding = null;
    

    /**
     * Package protected constructor.
     * @param clazz the transformation class
     */
    ReinitializingTransformationProxy(final Class<AbstractTransformation<S, T>> clazz) {
        this.transformationClass = clazz;
        
        // The following module definition allows to create cyclic references between the
        //  instances of the major transformation ('clazz') and helper classes with outsourced
        //  parts, e.g. the visualization of domain-specific expressions, that will be injected
        //  into instances of 'clazz'.
        // If the outsourced transformation has an injected field of type AbstractTransformation<?, ?>
        //  and 'clazz' is annotated with @Singleton the helper classes can access the current
        //  instance of 'clazz', the transformation context and thus, e.g., the source image lookup
        //  library.
        // In addition, a standard binding of ResourceSet is provided for special use requiring one.
        this.transformationClassBinding = new Module() {
            public void configure(final Binder binder) {
                binder.bind(ResourceSet.class).to(ResourceSetImpl.class);
                binder.bind(new TypeLiteral<AbstractTransformation<?, ?>>() { }).to(clazz);
            }
        };
    }
    
    
    private AbstractTransformation<S, T> getNewDelegateInstance() {
        return Guice.createInjector(this.transformationClassBinding).getInstance(
                this.transformationClass);
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
