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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis;
import de.cau.cs.kieler.klighd.syntheses.ReinitializingDiagramSynthesisProxy;

/**
 * A data record that is used to keep information related to the execution of
 * {@link ITransformation ITransformations}. Since such transformations are supposed to do not
 * maintain memories beyond the end of their executions each information that is required later on,
 * e.g. source target data mappings, shall be stored in transformation contexts.
 * 
 * @author mri
 * @author chsch
 * 
 * @param <S>
 *            the type of the source model
 * @param <T>
 *            the type of the target model
 */
public class TransformationContext<S, T> {
    // SUPPRESS CHECKSTYLE PREVIOUS FinalClass
    //  chsch: Since I need a subclass of this one this class cannot be final (see #createAlias).

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
        for (SynthesisOption option: transformationContext.getDisplayedSynthesisOptions()) {
            transformationContext.configureOption(option, option.getInitialValue());
        }
        return transformationContext;
    }
    
    /**
     * Creates an aliasing {@link TransformationContext} allowing to reuse parts of
     * "third party" {@link ITransformation ITransformations}.
     * 
     * @param <S>
     *            the type of the source model
     * @param <T>
     *            the type of the target model
     * @param theOriginal
     *            the original context to be aliased
     * @return the intended transformation context
     */
    public static <S, T> TransformationContext<S, T> createAlias(
            final TransformationContext<?, T> theOriginal) {
        return new TransformationContext<S, T>() {
            
            private TransformationContext<?, T> original = theOriginal;
            
            /**
             * Getter.
             * 
             * @param option the option to evaluate the configuration state / the configured value.
             * @return the configured value of {@link SynthesisOption} option.
             */
            public Object getOptionValue(final SynthesisOption option) {
                return this.original.getOptionValue(option);
            }
            
            /**
             * Put a pair of a model and a derived element into the lookup table.
             * 
             * @param derived the derived element
             * @param model the model element
             */
            public void addSourceTargetPair(final Object model, final Object derived) {
                this.original.addSourceTargetPair(model, derived);
            }
        };
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


    // ---------------------------------------------------------------------------------- //
    //  Transformation option handling    

    private List<SynthesisOption> transformationOptions = null;
    
    /**
     * Returns the set of {@link SynthesisOption TransformationOptions} declared by the
     * transformation and forward to the users in the UI in order to allow them to influence the
     * transformation result. The provider method of the transformation is ask only once in order
     * to prevent any manipulation on the configured option at runtime.
     * 
     * @return the set of {@link SynthesisOption TransformationOptions}
     */
    public List<SynthesisOption> getDisplayedSynthesisOptions() {
        if (this.transformationOptions == null) {
            this.transformationOptions = transformation.getDisplayedSynthesisOptions();
        }
        return this.transformationOptions;
    }
    
    /** Memory of the configured transformation options to be evaluated by the transformation. */
    private Map<SynthesisOption, Object> configuredOptions = Maps.newHashMap();
        
    /**
     *
     * @param option the {@link SynthesisOption} to set
     * @param value the value of the {@link SynthesisOption}
     */
    public void configureOption(final SynthesisOption option, final Object value) {
        
        if (option == null || !this.transformationOptions.contains(option)) {
            throw new IllegalArgumentException("KLighD transformation option handling: "
                    + "Attempted to configure illegal option ("
                    + (option == null ? null : option.getName())
                    + "), which is not introduced by the transformation "
                    + this.transformation + ".");
        }
        
        if (value == null) {
            configuredOptions.remove(option);
        } else {
            configuredOptions.put(option, value);
        }
    }
    
    /**
     * Getter.
     * 
     * @param option the option to evaluate the configuration state / the configured value.
     * @return the configured value of {@link SynthesisOption} option.
     */
    public Object getOptionValue(final SynthesisOption option) {
        
        if (option == null) {
            throw new IllegalArgumentException("KLighD transformation option handling: "
                    + "The transformation " + this.transformation
                    + " attempted to evaluate the transformation option \"null\".");
        }        
        return this.configuredOptions.get(option);
    }


    // ---------------------------------------------------------------------------------- //
    //  Recommended layout option handling    

    /**
     * Passes the recommended layout options and related values provided by the transformation.  
     * 
     * @return a map of options (map keys) and related values (map values)
     */
    public List<Pair<IProperty<?>, List<?>>> getDisplayedLayoutOptions() {
        ITransformation<?, ?> theTransformation = this.transformation;
        if (this.transformation instanceof ReinitializingDiagramSynthesisProxy<?>) {
            theTransformation =
                    ((ReinitializingDiagramSynthesisProxy<?>) this.transformation).getDelegate();
        }
        if (theTransformation instanceof AbstractDiagramSynthesis<?>) {
            return ((AbstractDiagramSynthesis<?>) theTransformation).getDisplayedLayoutOptions();
        } else {
            return Collections.emptyList();
        }
    }


    // ---------------------------------------------------------------------------------- //
    //  Source target element handling    

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
     * Returns all target elements that are tracked in the source target mappings.<br>
     * Must be used in {@link ViewContext} only, therefore it is "package protected".
     * 
     * @return an {@link Iterable} containing all tracked target elements
     */
    Iterable<?> getTargetElements() {
        return this.targetSourceElementMap.keySet();
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
    public Collection<?> getTargetElement(final Object element) {
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
