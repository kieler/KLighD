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
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Function;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.klighd.DisplayedActionData;
import de.cau.cs.kieler.klighd.SynthesisOption;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.ISynthesis;

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
 * {@link #transform(Object, ViewContext)} method. If multiple instance of such a delegate
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
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch 
 */
public abstract class AbstractDiagramSynthesis<S> implements ISynthesis {

    /** the current context of this synthesis. */
    private ViewContext currentContext = null;

    /** whether it has been tried to infer the classes. */
    private boolean triedToInferClasses = false;

    /** the inferred source model class. */
    private Class<?> sourceModelClass = null;

    /** the name of the {@code transform} method. */
    private static final String TRANSFORM_METHOD_NAME = "transform";

    /**
     * This constant expression is a convenience handle to easy the access to the
     * {@link KRenderingFactory} in derivatives of this class, i.e. concrete diagram syntheses.
     */
    protected static final KRenderingFactory RENDERING_FACTORY = KRenderingFactory.eINSTANCE; 


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
     * {@inheritDoc}
     */
    protected void inferSourceAndTargetModelClass() {
        this.triedToInferClasses = true;
        // try to find a method with one parameter which returns KNode
        // takes the first matching method with parameter 0 != Object
        Method transformMethod = null;
        for (final Method method : getClass().getDeclaredMethods()) {
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
            this.sourceModelClass = transformMethod.getParameterTypes()[0];
        }
    }


    /**
     * {@inheritDoc}<br>
     * May be overridden by concrete implementations in order to incorporate property settings of
     * the <code>viewContext</code> into the decision.
     */
    public boolean supports(final Object model, final ViewContext viewContext) {

        if (this.getSourceClass().isInstance(model)) {
            @SuppressWarnings("unchecked")
            final S input = (S) model;
            return supports(input);

        } else {
            return false;
        }
    }

    /**
     * Method for checking whether <code>this</code> diagram synthesis implementation supports the
     * given <code>model</code>.<br>
     * May be overridden by concrete implementations.
     * 
     * @param model
     *            the model to test
     * @return <code>true</code> if <code>this</code> transformation supports <code>model</code>,
     *         and <code>false</code> otherwise
     */
    public boolean supports(final S model) {
        return true;
    }


    /**
     * Method hook to be called by KLighD's runtime.<br>
     * Concrete implementations must not override this method but {@link #transform(Object)}.
     * The {@link ViewContext} can be accessed via {@link #getUsedContext()}.
     * 
     * @param model
     *            the semantic model to be depicted
     * @param viewContext
     *            the {@link ViewContext} serving as provider and acceptor for various data
     *            (option values, source view tracing, ...)
     * @return the related KGraph/KRendering diagram description
     */
    public final KNode transform(final Object model, final ViewContext viewContext) {
        use(viewContext);

        @SuppressWarnings("unchecked")
        final S input = (S) model;
        final KNode result = transform(input);

        // clear the reference to ViewContext in order to allow the garbage collector to dispose
        //  view model, the source model and other memory consuming components once the
        //  corresponding viewer or diagram workbench part is closed (instances of this class are
        //  kept for the life-time of the tool in case they are employed without Google Guice).
        use(null);
        return result;
    }

    /**
     * Translates the an model of type S into a KGraph/KRendering diagram description.<br>
     * The {@link ViewContext} can be accessed via {@link #getUsedContext()}.
     * 
     * @param model
     *            the semantic model to be depicted
     * @return the related KGraph/KRendering diagram description
     */
    public abstract KNode transform(S model);


    /**
     * Associates a view model element with a input model element.<br>
     * Name, parameter ordering, and return value (the target) are optimized for calling in
     * Xtend-based transformations in a fluent interface fashion, like
     * "model.createShape().associateWith(model);"<br>
     * <br>
     * <b>Note:</b> A view model element can be associated with at most 1 input model element.
     * Hence, this method shall be called at most once for a given view model element.<br>
     * In contrast, multiple view model elements can be associated with an input model element.
     * 
     * @param <T>
     *            the type of the view model element
     * @param derived
     *            the view model element
     * @param source
     *            the input model element
     * @return the view model element for convenience
     */
    public <T extends EObject> T associateWith(final T derived, final Object source) {
        currentContext.associateSourceTargetPair(source, derived);
        return derived;
    }

    /**
     * Associates a view model element with a input model element.<br>
     * Name, parameter ordering, and return value (the target) are optimized for calling in
     * Xtend-based transformations in a fluent interface fashion, like
     * "model.createShape().putToLookUpWith(model);"<br>
     * 
     * @deprecated use {@link #associateWith(EObject, Object)}
     * 
     * @param <D>
     *            the type of the target element which is implicitly determined
     * @param derived
     *            the derived element
     * @param source
     *            the model element
     * @return the image element
     */
    public <D extends EObject> D putToLookUpWith(final D derived, final Object source) {
        return this.associateWith(derived, source);
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
     * {@inheritDoc}<br>
     * <br> 
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
     * </pre>
     * 
     * The layout options will be displayed in the side bar of the corresponding view or editor
     * part according to the order within the returned list.<br>
     * <br>
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
    //  Offered diagram actions handling    

    /**
     * {@inheritDoc}
     */
    public List<DisplayedActionData> getDisplayedActions() {
        return Collections.emptyList();
    }


    // ---------------------------------------------------------------------------------- //
    //  Hook allowing to register additional ILayoutConfigs for
    //   those in a row with the default one    

    /**
     * {@inheritDoc}
     */
    public List<? extends ILayoutConfig> getAdditionalLayoutConfigs() {
        return Collections.emptyList();
    }


    // ---------------------------------------------------------------------------------- //
    //  Hook for registering model update functions being used for transferring
    //   name & label updates back to the application/business/semantic model

    /**
     * {@inheritDoc}
     */
    public Function<String, Void> getTextUpdateFunction(final KText kText, final KGraphElement element) {
        return null;
    }


    // ---------------------------------------------------------------------------------- //
    //  Convenience methods to be used in concrete implementations

    /**
     * Convenience method for setting the initially applied diagram clip node.<br>
     * Refers to {@link #getUsedContext()} for determining the {@link ViewContext} to perform this
     * definition and delegates to {@link #initiallyClipTo(ViewContext, KNode)}.
     * 
     * @param node
     *            the initial diagram clip node
     * @return <code>node</code> for convenience
     */
    protected final KNode initiallyClipTo(final KNode node) {
        return setInitialClipTo(getUsedContext(), node);
    }


    private static final String NO_VIEWCONTEXT_ERROR_MSG =
            "KLighD: Failed to set the initial diagram clip in XX: No ViewContext is available.";

    /**
     * Convenience method for setting the initially applied diagram clip node.
     * 
     * @param viewContext
     *            the {@link ViewContext} to perform this definition in
     * @param node
     *            the initial diagram clip node
     * @return <code>node</code> for convenience
     */
    protected final KNode setInitialClipTo(final ViewContext viewContext, final KNode node) {
        if (viewContext != null) {
            return DiagramSyntheses.initiallyClipTo(viewContext, node);
        } else {
            throw new RuntimeException(NO_VIEWCONTEXT_ERROR_MSG.replace("XX", getClass().getName()));
        }
    }


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
    protected final <R extends KGraphElement, T> R setLayoutOption(final R element,
            final IProperty<T> option, final T value) {

        return DiagramSyntheses.setLayoutOption(element, option, value);
    }

    /**
     * Convenience method for defining multiple layout options for {@link KGraphElement
     * KGraphElements}.<br>
     * The required <code>optionValueMap</code> can be easily created via
     * {@link com.google.common.collect.ImmutableMap#of(Object, Object, Object, Object)
     * ImmutableMap#of(Object, Object, Object, Object)}, for example.
     *
     * @param <R>
     *            the concrete type of <code>element</code>
     * @param element
     *            the element to set the layout option on
     * @param optionValueMap
     *            a {@link Map} containing valid pairs of layout options, e.g. some of
     *            {@link de.cau.cs.kieler.kiml.options.LayoutOptions LayoutOptions}, and
     *            corresponding option values.
     * @return <code>element</code> allowing to perform multiple operations on it in one statement
     */
    public static <R extends KGraphElement> R setLayoutOptions(final R element,
            final Map<IProperty<?>, ?> optionValueMap) {
        
        return DiagramSyntheses.setLayoutOptions(element, optionValueMap);
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
    protected final <T> KNode setExpansionAwareLayoutOption(final KNode node, final IProperty<T> option,
            final T collapsedValue, final T expandedValue) {

        return DiagramSyntheses.setExpansionAwareLayoutOption(
                node, option, collapsedValue, expandedValue);
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
    protected final <T> KPort setExpansionAwareLayoutOption(final KPort port,
            final IProperty<T> option, final T collapsedValue, final T expandedValue) {

        return DiagramSyntheses.setExpansionAwareLayoutOption(
                port, option, collapsedValue, expandedValue);
    }

    /**
     * Initializes the {@link ViewContext} being used within this diagram synthesis allowing to
     * neglect it in the concrete diagram synthesis methods' interfaces (parameters).<br>
     * <br>
     * In common case this method need not to be called by application code but only if delegate
     * diagram syntheses are employed by means of a {@link com.google.inject.Provider Provider}, see
     * {@link ReinitializingDiagramSynthesisProxy#ViewSynthesisScope ViewSynthesisScope} for
     * details.<br>
     * <br>
     * Make sure to reset the reference by calling <code>use(null)</code> after calling
     * {@link #transform(Object)} in order safely let the garbage collect dispose the given view
     * context and its referenced data if possible. Otherwise this can result in a memory leak!
     * 
     * @param viewContext
     *            the context to be used during the current run
     */
    public void use(final ViewContext viewContext) {
        this.currentContext = viewContext;
    }
    
    /**
     * Getter.
     * 
     * @return the currently used transformation context or <code>null</code> if no one is set.
     */
    protected ViewContext getUsedContext() {
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
        final Object result = this.getUsedContext().getOptionValue(option);
        if (result == null) {
            return false;

        } else if (result instanceof Boolean) {
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
        final Object result = this.getUsedContext().getOptionValue(option);
        if (result == null) {
            return 0;

        } else if (result instanceof Float) {
            return ((Float) result).intValue();

        } else  if (result instanceof Integer) {
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
        final Object result = this.getUsedContext().getOptionValue(option);
        if (result == null) {
            return 0.0f;

        } else if (result instanceof Float) {
            return (Float) result;

        } else {
            throw new IllegalArgumentException("KLighD transformation option handling: "
                    + "The transformation " + this
                    + " attempted to evaluate the non-Float valued transformation option "
                    + option.getName() + " expecting a float value.");
        }
    }
}
