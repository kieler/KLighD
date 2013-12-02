/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.syntheses;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.SynthesisOption;
import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData;
// SUPPRESS CHECKSTYLE PREVIOUS LineLength

/**
 * The abstract base class for KLighD diagram synthesis implementations.<br>
 * <br>
 * Implementations of this class have to implement {@link #transform(S)} that performs the mapping
 * of the semantic/business objects into instances of the KGraph/KRendering view model format.<br>
 * <br>
 * Some hints for the use with Xtend (www.xtend-lang.org):<br>
 * If your custom view synthesis transformation is written in Xtend and leverages <b>create
 * extensions</b> or <b>dependency injection</b> with Google Guice, please note the hint in
 * {@link ReinitializingDiagramSynthesisProxy} on registering such transformations.<br>
 * <br>
 * If a transformation implementation incorporates helper transformations containing shared or
 * outsourced parts and the outsourced ones need to have access to the main transformation instance,
 * e.g. for accessing the transformation context, this can be realized by means of Guice, too. The
 * helper transformation implementation must declare an injected field of type
 * AbstractDiagramSynthesis&lt;?&gt;, the actual {@link AbstractDiagramSynthesis} implementation
 * must be annotated with {@link javax.inject.Singleton &#64;Singleton} on class level. This way the
 * helper classes are provided with the current instance of the main transformation. <br>
 * <br>
 * Furthermore, diagram syntheses may leverage other ones, e.g. for realizing composed views. This
 * can be achieved by simply declaring an injected field or extension and calling the related
 * {@link #transform(Object, TransformationContext)} method. If multiple instance of such a delegate
 * transformation are needed (e.g. due to the use of create extensions) a field of type
 * {@link com.google.inject.Provider Provider&lt;yourTransformationClass&gt;} can be declared. Each
 * time calling {@link com.google.inject.Provider#get() get()} on this provider a new instance will
 * be obtained as long as the provided class is <b>not</b> declared as singleton (via
 * {@link javax.inject.Singleton &#64;Singleton}). The
 * {@link de.cau.cs.kieler.core.krendering.extensions.ViewSynthesisShared ViewSynthesisShared}
 * annotation helps if the described feature of employing helper classes is required.
 * 
 * @param <S>
 *            Type of the model to be visualized
 * 
 * @author chsch
 * @author uru
 */
public abstract class AbstractDiagramSynthesis<S> implements ITransformation<S, KNode> {
    
    /** the current context of this synthesis. */
    private TransformationContext<S, KNode> currentContext = null;
    
    /** whether it has been tried to infer the classes. */
    private boolean triedToInferClasses = false;
    /** the inferred source model class. */
    private Class<?> sourceModelClass = null;
    /** the inferred target model class. */
    private Class<?> targetModelClass = null;
    
    /** the name of the {@code transform} method. */
    protected static final String TRANSFORM_METHOD_NAME = "transform";
    
    /**
     * This constant expression is a convenience handle to easy the access to the
     * {@link KRenderingFactory} in derivatives of this class, i.e. concrete diagram syntheses.
     */
    protected static final KRenderingFactory RENDERING_FACTORY = KRenderingFactory.eINSTANCE; 

    /**
     * Method hook to be called by KLighD's runtime.<br>
     * Concrete implementations shall not call or override this method.
     * 
     * @param model
     *            the semantic model to be depicted
     * @param transformationContext
     *            the transformationContext serving as provider and acceptor for various data
     *            (option values, source view tracing, ...)
     * @return the related KGraph/KRendering diagram description
     */
    public KNode transform(final S model, final TransformationContext<S, KNode> transformationContext) {
        use(transformationContext);
        return transform(model);
    }
    
    /**
     * Translates the an model of type S into a KGraph/KRendering diagram description.
     * 
     * @param model
     *            the semantic model to be depicted
     * @return the related KGraph/KRendering diagram description
     */
    public abstract KNode transform(S model);

    
    /**
     * Method to put a pair of source target into the lookup table.<br>
     * Name, parameter ordering, and return value (the target) are optimized for
     * calling in Xtend-based transformations in a fluent interface fashion, like
     * "model.createShape().putToLookUpWith(model);"<br>
     * <br>
     * Usage requires to perform 'use(TransformationContext)' at the beginning of
     * {@link ITransformation#transform(Object, TransformationContext)}.
     * 
     * @param <D> the type of the target element which is implicitly determined 
     * @param derived the derived element
     * @param source the model element
     * @return the image element
     */
    @SuppressWarnings("unchecked")
    public <D> D putToLookUpWith(final D derived, final Object source) {
        if (KGraphPackage.eINSTANCE.getKGraphData().isInstance(derived)) {
            ((KGraphData) derived).setProperty(KlighdInternalProperties.MODEL_ELEMEMT, source);
        } else if (KGraphPackage.eINSTANCE.getKGraphElement().isInstance(derived)) {
            Iterables.getFirst(
                    (Iterable<KGraphData>) (Iterable<?>) Iterables.filter(
                            ((KGraphElement) derived).getData(), KLayoutData.class), null)
                    .setProperty(KlighdInternalProperties.MODEL_ELEMEMT, source);
        }
        
        if (this.currentContext == null) {
            throw new IllegalStateException("KLighD transformation "
                    + this.getClass().getCanonicalName()
                    + " uses 'putToLookUp(...) and probably does not invoke"
                    + "'use(TransformationContxt)' at the beginning of its 'transform()' method");
        }
        this.currentContext.addSourceTargetPair(source, derived);
        return derived;
    }
    
    /**
     * {@inheritDoc}
     */
    protected void inferSourceAndTargetModelClass() {
        this.setTriedToInferClass();
        // try to find a method with one parameter which returns KNode
        // takes the first matching method with parameter 0 != Object
        Method transformMethod = null;
        for (Method method : getClass().getDeclaredMethods()) {
            if (method.getParameterTypes().length == 1
                    && method.getReturnType().equals(KNode.class)
                    && method.getName().equals(TRANSFORM_METHOD_NAME)) {
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
            this.setSourceClass(transformMethod.getParameterTypes()[0]);
            this.setTargetClass(KNode.class);
        }
    }


    // ---------------------------------------------------------------------------------- //
    //  Synthesis option handling    

    /**
     * A diagram synthesis can use this method to specify {@link SynthesisOption SynthesisOptions}
     * it makes use of. The option settings can evaluated within the synthesis' transformed method,
     * they don't have any influence in the behavior of KLighD's runtime.
     * 
     * A {@link SynthesisOption} option might be used to either display or hide comments in the
     * resulting diagram. The {@link SynthesisOption} class provides several convenience methods to
     * create an option, e.g. {@link SynthesisOption#createRangeOption(...)} to create a 'slider'.
     * 
     * The synthesis options will be displayed in the side bar of the corresponding view or editor
     * part according to the order within the returned list.
     * 
     * @return a list with the desired synthesis options.
     */
    public List<SynthesisOption> getDisplayedSynthesisOptions() {
        return Collections.emptyList();
    }

    // ---------------------------------------------------------------------------------- //
    //  Recommended layout option handling    

    /**
     * Returns a list of layout options that will be displayed in the diagram side bar and that are
     * directly manipulatable by the user. For each layout option a, possibly restricted, set of
     * allowed input values can be specified.
     * 
     * Use the {@link #specifyLayoutOption(IProperty, List)} method to conveniently specify
     * the options. An example usage might look like the following (Xtend code). The shown example
     * will create a choice widget allowing all possible values of KlayLayered'
     * NodePlacementStrategy enumeration. Furthermore, a slider is created to set the layout's
     * spacing that allows values in the interval [0,255].
     * 
     * <pre>
     *  override getDisplayedLayoutOptions() {
     *      return ImmutableList::of(
     *          specifyLayoutOption(Properties::NODE_PLACER, 
     *              ImmutableList::copyOf(NodePlacementStrategy::values)),
     *          specifyLayoutOption(LayoutOptions::SPACING, ImmutableList::of(0, 255))
     *      )
     * }
     * 
     * <pre>
     * 
     * The layout options will be displayed in the side bar of the corresponding view or editor
     * part according to the order within the returned list.
     * 
     * @return a {@link List} of {@link Pair Pairs} where each pair specifies a recommended layout
     * option.
     */
    public List<Pair<IProperty<?>, List<?>>> getDisplayedLayoutOptions() {
        return Collections.emptyList();
    }

    /**
     * Convenient function to assemble a pair of layout option and allowed values. The type of the
     * elements within the 'values' parameter depends on the tyoe if the specified property. For
     * instance, for an IProperty<Integer> one might pass a List<Integer> of size 2 to specify the
     * lower and upper bound for the values.
     * 
     * @param prop
     *            the desired property.
     * @param values
     *            the allowed values.
     * @return a pair with the property and the possible values.
     */
    protected Pair<IProperty<?>, List<?>> specifyLayoutOption(final IProperty<?> prop,
            final List<?> values) {
        return Pair.<IProperty<? extends Object>, List<? extends Object>>of(prop, values);
    }

    // ---------------------------------------------------------------------------------- //
    //  Convenience methods to be used in concrete implementations   

    /**
     * Convenience method for defining layout options for {@link KGraphElement KGraphElements}.
     * 
     * @param <R>
     *            the concrete type of <code>element</code>
     * @param <T>
     *            the property value type
     * @param element
     *            the element to set the layout option on
     * @param option
     *            the particular layout option, e.g. one of
     *            {@link de.cau.cs.kieler.kiml.options.LayoutOptions LayoutOptions}
     * @param value
     *            the option value
     * @return <code>node</code> allowing to perform multiple operations on it in one statement
     */
    protected <R extends KGraphElement, T> R setLayoutOption(final R element,
            final IProperty<T> option, final T value) {
        element.getData(KLayoutData.class).setProperty(option, value);
        return element;
    }
    
    /**
     * Convenience method for defining collapse/expand state dependent layout options for
     * {@link KNode KNodes}.
     * 
     * @param <T>
     *            the property value type
     * @param node
     *            the node to set the layout option on
     * @param option
     *            the particular layout option, e.g. one of
     *            {@link de.cau.cs.kieler.kiml.options.LayoutOptions LayoutOptions}
     * @param collapsedValue
     *            the value in case <code>node</code> is collapsed
     * @param expandedValue
     *            the value in case <code>node</code> is expanded
     * @return <code>node</code> allowing to perform multiple operations on it in one statement
     */
    protected <T> KNode setExpansionAwareLayoutOption(final KNode node, final IProperty<T> option,
            final T collapsedValue, final T expandedValue) {
        KLayoutData sl = node.getData(KLayoutData.class);
        if (sl != null) {
            ExpansionAwareLayoutOption.setProperty(sl, option, collapsedValue, expandedValue);
        }
        return node;
    }

    /**
     * Convenience method for defining collapse/expand state dependent layout options for
     * {@link KPort KPorts}. The collapse/expand state refers to that of the {@link KNode}
     * containing the {@link KPort}.
     * 
     * @param <T>
     *            the property value type
     * @param port
     *            the port to set the layout option on
     * @param option
     *            the particular layout option, e.g. one of
     *            {@link de.cau.cs.kieler.kiml.options.LayoutOptions LayoutOptions}
     * @param collapsedValue
     *            the value in case <code>port</code>'s container node is collapsed
     * @param expandedValue
     *            the value in case <code>port</code>'s container node is expanded
     * @return <code>node</code> allowing to perform multiple operations on it in one statement
     */
    protected <T> KPort setExpansionAwareLayoutOption(final KPort port, final IProperty<T> option,
            final T collapsedValue, final T expandedValue) {
        KLayoutData sl = port.getData(KLayoutData.class); 
        ExpansionAwareLayoutOptionData data = sl.getProperty(ExpansionAwareLayoutOption.OPTION);
        
        if (data == null) {
            data = new ExpansionAwareLayoutOptionData();
            sl.setProperty(ExpansionAwareLayoutOption.OPTION, data);
        }
        
        data.setProperty(option, collapsedValue, expandedValue);
                
        return port;
    }
    
    /**
     * 
     * @param kText
     * @param element
     * @return
     */
    public Function<String, Void> getTextUpdateFunction(final KText kText, final KGraphElement element) {
        return new Function<String, Void>() {
            public Void apply(final String arg0) {
                // TODO Auto-generated method stub
                //System.out.println(arg0);
                System.out.println("hello world");
                return null;
            }
        };
    }
    
    /**
     * Initializes the transformation run.
     * Currently, just keeps the context to be used
     * (allowing to neglect it in the concrete transformation methods).
     * 
     * @param transformationContext the context to be used during the current run
     */
    protected void use(final TransformationContext<S, KNode> transformationContext) {        
        this.currentContext = transformationContext;
        this.currentContext.clear();
    }
    
    /**
     * Getter.
     * 
     * @return the currently used transformation context or <code>null</code> if no one is set.
     */
    protected TransformationContext<S, KNode> getUsedContext() {
        return this.currentContext;
    }

    // ---------------------------------------------------------------------------------- //
    //  Convenience methods to retrieve the value of a SynthesisOption

    /**
     * Convenience getter.
     * 
     * @param option the option to evaluate the configuration state / the configured value.
     * @return the configured value of {@link SynthesisOption} option.
     */
    public Object getObjectValue(final SynthesisOption option) {
        return this.getUsedContext().getOptionValue(option);
    }
    
    /**
     * Convenience getter.
     * 
     * @param option the option to evaluate the configuration state / the configured value.
     * @return the configured value of {@link SynthesisOption} option.
     */
    public boolean getBooleanValue(final SynthesisOption option) {
        Object result = this.getUsedContext().getOptionValue(option);
        if (result instanceof Boolean) {
            return (Boolean) result; 
        } else {
            throw new IllegalArgumentException("KLighD transformation option handling: "
                    + "The transformation " + this
                    + " attempted to evaluate the non-Boolean valued transformation option "
                    + option.getName() + " expecting a Boolean value.");
        }
    }
    
    /**
     * Convenience getter.
     * 
     * @param option the option to evaluate the configuration state / the configured value.
     * @return the configured value of {@link SynthesisOption} option.
     */
    public int getIntValue(final SynthesisOption option) {
        Object result = this.getUsedContext().getOptionValue(option);
        if (result instanceof Float) {
            return ((Float) result).intValue();
        }
        if (result instanceof Integer) {
            return (Integer) result; 
        } else {
            throw new IllegalArgumentException("KLighD transformation option handling: "
                    + "The transformation " + this
                    + " attempted to evaluate the non-Integer valued transformation option "
                    + option.getName() + " expecting a int value.");
        }
    }
    
    /**
     * Convenience getter.
     * 
     * @param option the option to evaluate the configuration state / the configured value.
     * @return the configured value of {@link SynthesisOption} option.
     */
    public float getFloatValue(final SynthesisOption option) {
        Object result = this.getUsedContext().getOptionValue(option);
        if (result instanceof Float) {
            return (Float) result; 
        } else {
            throw new IllegalArgumentException("KLighD transformation option handling: "
                    + "The transformation " + this
                    + " attempted to evaluate the non-Float valued transformation option "
                    + option.getName() + " expecting a float value.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getSourceClass() {
        if (!triedToInferClass()) {
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
        if (!triedToInferClass()) {
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
     * Getter for the triedToInferClasses flag.
     * 
     * @return true if source and target classes have been tried to infer
     */
    protected boolean triedToInferClass() {
        return this.triedToInferClasses;
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
    public boolean supports(final S model) {
        return true;
    }

}
