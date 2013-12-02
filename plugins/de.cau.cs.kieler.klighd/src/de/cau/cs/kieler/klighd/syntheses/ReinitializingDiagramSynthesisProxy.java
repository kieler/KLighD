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
package de.cau.cs.kieler.klighd.syntheses;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.TypeLiteral;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.extensions.ViewSynthesisShared;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.SynthesisOption;


/**
 * This diagam synthesis proxy realizes the re-initialization of stateful diagram synthesis
 * implementations. It is needed for Xtend-based diagram syntheses leveraging "create extensions",
 * for example. This implementation cares about creating new synthesis instances, as well as their
 * proper initialization by means of Guice.<br>
 * <br>
 * Diagram syntheses that shall be wrapped with an instance of this class must be registered by
 * prefixing their class name with the string <code>
 * de.cau.cs.kieler.klighd.syntheses.GuiceBasedSynthesisFactory:</code> . See also the description
 * of the extension point <code>de.cau.cs.kieler.klighd.diagramSyntheses</code>.<br>
 * <br>
 * This class shall not be instantiated by any user program but only by the runtime.
 * 
 * @param <S>
 *            type of the input models
 * 
 * @author chsch
 */
public class ReinitializingDiagramSynthesisProxy<S> extends AbstractDiagramSynthesis<S> {

    private Class<AbstractDiagramSynthesis<S>> transformationClass = null;
    private AbstractDiagramSynthesis<S> transformationDelegate = null;
    private Module transformationClassBinding = null;
    

    /**
     * Package protected constructor.
     * @param clazz the transformation class
     */
    ReinitializingDiagramSynthesisProxy(final Class<AbstractDiagramSynthesis<S>> clazz) {
        this.transformationClass = clazz;
        
        // The following module definition provides the various features:
        //  * A standard binding of ResourceSet is provided for special uses requiring one.
        //  * Helper transformations injected into the main one may declare an injected field
        //    or extension of type AbstractDiagramSynthesis<?> in order to generically access
        //    the main transformation (current instance of 'clazz'), e.g. for adding stuff to
        //    the current transformation context. Guice is taking care of not deadlocking in
        //    such a cyclic reference; this is resolved by providing the already created instance
        //    of 'clazz' which is exactly what I want :-).
        //  * An instance of ViewSynthesisScope is registered and bound to the annotation type
        //    'ViewSynthesisShared' causing the integration of that scope instance into the
        //    field injection logic for all classes annotated with this annotation
        this.transformationClassBinding = new Module() {
            public void configure(final Binder binder) {
                binder.bind(ResourceSet.class).to(ResourceSetImpl.class);
                binder.bind(new TypeLiteral<AbstractDiagramSynthesis<?>>() { }).to(clazz);
                binder.bindScope(ViewSynthesisShared.class, new ViewSynthesisScope(clazz));
            }
        };
    }
    
    /**
     * This {@link Scope} realizes the requirement of injecting the same instances of helper classes
     * like extension libraries into implementations of {@link AbstractDiagramSynthesis} and further
     * helper classes implementations helper classes.<br>
     * <br>
     * Example: DataDependencyVisualisation --requires--> KNodeExtensions,<br>
     * DataDependencyVisualisation --requires--> ExpressionVisuHelper --requires--> KNodeExtensions,<br>
     * <br>
     * while the instances of KNodeExtensions should be the same.<br>
     * 
     * In addition, the DataDependencyVisualisation may re-use the StateMachineVisualisation with<br>
     * <br>
     * StateMachineVisualisation --requires--> KNodeExtensions,<br>
     * <br>
     * both sub types of {@link AbstractDiagramSynthesis}. The helper instance(s) of the
     * StateMachineVisualisation instance however shall be disjoint from the ones of
     * DataDependencyVisualisation's instance(s).<br>
     * This requirement is realized by this {@link Scope} by maintaining a Set of instances that
     * have already been created. If, however, an instance of {@link AbstractDiagramSynthesis} or a
     * subclass is requested, those instances will be forgotten - the set is cleared. Thus, new ones
     * are requested from the upstream {@link Provider}.<br>
     * <br>
     * <b>Attention</b>: Classes whose instantiation shall be controlled by this {@link Scope} must
     * by annotated with the {@link ViewSynthesisShared} annotation.
     * 
     * 
     * @author chsch
     */
    public class ViewSynthesisScope implements Scope {
        
        /**
         * Constructor.
         * 
         * @param themainTransformationClazz
         *              the main transformation class
         */
        public ViewSynthesisScope(
                final Class<AbstractDiagramSynthesis<S>> themainTransformationClazz) {
            this.mainTransformationClazz = themainTransformationClazz;
        }
        
        private Class<AbstractDiagramSynthesis<S>> mainTransformationClazz = null;
        private Set<Object> instances = Sets.newHashSet();

        /**
         * {@inheritDoc}<br>
         * <br>
         * This method is called once for each class ('key') to be injected. Hence (potentially)
         * multiple {@link Provider Providers} accessing the {@link #instances} set exist.
         * The returned provider is called each time an instance of 'key' is required.
         * 
         * @return a {@link Provider} dedicated to the class denoted by 'key' 
         */
        public <U> Provider<U> scope(final Key<U> key, final Provider<U> unscoped) {
            
            return new Provider<U>() {
                
                /**
                 * {@inheritDoc}<br>
                 * <br>
                 * This method contains the realization logic of the requirements described in
                 * {@link ViewSynthesisScope}. Realize that this method will be called recursively
                 * as the call of 'unscoped.get()' will result in a call of another {@link Provider}
                 * created by {@link ViewSynthesisScope#scope(Key, Provider)}. Thus the call of
                 * 'get()' for the required instance of 'mainTransformationClazz' invoked by the
                 * 'getInstance()' call in
                 * {@link ReinitializingDiagramSynthesisProxy#getNewDelegateInstance} will be the
                 * first one entering this method, and the last one leaving it.
                 */
                public U get() {
                    
                    // determine the class to provide an instance for
                    @SuppressWarnings("unchecked")
                    final Class<U> theClazzToBeInjected = (Class<U>) key.getTypeLiteral()
                            .getRawType();

                    U instance = null;
                    if (theClazzToBeInjected != mainTransformationClazz
                            && AbstractDiagramSynthesis.class.isAssignableFrom(theClazzToBeInjected)) {
                        // in case an instance of another fully-fledged transformation class requested
                        //  the current provider makes the scope (!) to forget all its known class
                        //  instances as stated in the requirements description above.
                        instances.clear();
                    } else {
                        // other try to reveal the first instance of 'theClazzToBeInjected' from the
                        //  instances memory (their must exist at most one; Guice resolves the mapping
                        //  of interfaces or abstract classes to concrete once in advance)
                        instance = Iterables.getFirst(
                                Iterables.filter(instances, theClazzToBeInjected), null);
                        if (instance != null) {
                            // if such an instance exists we're done :-) ...
                            return instance;
                        }
                    }
                    
                    // ... otherwise request the upstream Provider, keep the instance in mind, and return
                    instance = unscoped.get();
                    instances.add(instance);
                    return instance;
                }

                /**
                 * {@inheritDoc}
                 */
                public String toString() {
                    // implementation derived from com.google.Scopes.SINGLETON
                    return String.format("%s[%s]", unscoped, ViewSynthesisScope.this);
                }
            };
        }
        
        /**
         * {@inheritDoc}
         */
        public String toString() {
            return "KLighD.ViewSynthesisShared";
        }
    }
    
    
    private AbstractDiagramSynthesis<S> getNewDelegateInstance() {
        final AbstractDiagramSynthesis<S> res;
        try {
            res = Guice.createInjector(this.transformationClassBinding).getInstance(
                            this.transformationClass);
        } catch (Exception e) {
            final String nl = KlighdDataManager.NEW_LINE;
            final String msg =
                    "KLighD: Cannot instantiate " + this.transformationClass.getCanonicalName()
                            + "." + nl + "Is that class free of compiler errors?" + nl
                            + "Does it extend " + AbstractDiagramSynthesis.class.getCanonicalName()
                            + "?" + nl + "See exception trace below.";
            throw new WrappedException(e, msg);
        }
        return res; 
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean supports(final S model) {
        if (this.transformationDelegate == null) {
            this.transformationDelegate = getNewDelegateInstance();
        }
        return this.transformationDelegate.supports(model);
    }
    
    /**
     * {@inheritDoc}<br>
     * Delegates to the 'delegate' object.
     */
    public KNode transform(final S model, final TransformationContext<S, KNode> transformationContext) {
        this.transformationDelegate = getNewDelegateInstance(); 
        return this.transformationDelegate.transform(model, transformationContext);
    }
    
    
    /**
     * Stub enforced by {@link AbstractTransformation}.
     * @param model the model
     * @return null
     */
    public KNode transform(final S model) {
        return null;
    }


    /**
     * {@inheritDoc}
     */
    public List<SynthesisOption> getDisplayedSynthesisOptions() {
        if (this.transformationDelegate == null) {
            return getNewDelegateInstance().getDisplayedSynthesisOptions();
        }
        return this.transformationDelegate.getDisplayedSynthesisOptions();
    }
    
    
    @Override
    public Function<String, Void> getTextUpdateFunction(final KText kText, 
            final  KGraphElement element) {
        if (this.transformationDelegate == null) {
            return getNewDelegateInstance().getTextUpdateFunction(kText, element);
        }
        return this.transformationDelegate.getTextUpdateFunction(kText, element);
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
    public AbstractDiagramSynthesis<S> getDelegate() {
        return this.transformationDelegate;
    }
    
    
    /**
     * {@inheritDoc}
     */
    protected void inferSourceAndTargetModelClass() {
        this.setTriedToInferClass();
        AbstractDiagramSynthesis<S> delegate = getNewDelegateInstance();        
        if (delegate != null) {
            delegate.inferSourceAndTargetModelClass();
            this.setSourceClass(delegate.getSourceClass());
            this.setTargetClass(delegate.getTargetClass());
        }
    }
}
