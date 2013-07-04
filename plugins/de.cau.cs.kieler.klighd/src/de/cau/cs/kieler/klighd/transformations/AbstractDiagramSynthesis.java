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
package de.cau.cs.kieler.klighd.transformations;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.macrolayout.ExpansionAwareLayoutOption;
import de.cau.cs.kieler.klighd.macrolayout.ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData;

/**
 * This is a specialized {@link AbstractTransformation} with target model type {@link KNode}.<br>
 * <br>
 * Its aim is to simplify and shrink the class declaration parts of diagram synthesis
 * transformations creating KGraph/KRendering specifications. Please see also the documentation of
 * {@link AbstractTransformation}.
 * 
 * @param <S>
 *            Type of the model to be visualized
 * 
 * @author chsch
 */
public abstract class AbstractDiagramSynthesis<S> extends AbstractTransformation<S, KNode> {
    
    /**
     * This constant expression is a convenience handle to easy the access to the
     * {@link KRenderingFactory} in derivatives of this class, i.e. concrete diagram syntheses.
     */
    protected static final KRenderingFactory RENDERING_FACTORY = KRenderingFactory.eINSTANCE; 

    /**
     * {@inheritDoc}
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
    //  Recommended layout option handling    

    /**
     * Concrete implementations may provide a set of recommended layout options and optionally a
     * restricted set of values to be provided in the user interface for configuring the layout of
     * the displayed diagram.
     * 
     * @return a map of options (map keys) and related values (map values)
     */
    public Map<IProperty<?>, Collection<?>> getRecommendedLayoutOptions() {
        return Collections.emptyMap();
    }


    // ---------------------------------------------------------------------------------- //
    //  Convenience methods to be used in concrete implementations   

    /**
     * Convenience method for defining layout options for {@link KGraphElement KGraphElements}.
     * 
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
    protected <T> KGraphElement setLayoutOption(final KGraphElement element,
            final IProperty<T> option, final T value) {
        element.getData(KShapeLayout.class).setProperty(option, value);
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
        KShapeLayout sl = node.getData(KShapeLayout.class); 
        ExpansionAwareLayoutOptionData data = sl.getProperty(ExpansionAwareLayoutOption.OPTION);
        
        if (data == null) {
            data = new ExpansionAwareLayoutOptionData();
            sl.setProperty(ExpansionAwareLayoutOption.OPTION, data);
        }
        
        data.setProperty(option, collapsedValue, expandedValue);
        
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
        KShapeLayout sl = port.getData(KShapeLayout.class); 
        ExpansionAwareLayoutOptionData data = sl.getProperty(ExpansionAwareLayoutOption.OPTION);
        
        if (data == null) {
            data = new ExpansionAwareLayoutOptionData();
            sl.setProperty(ExpansionAwareLayoutOption.OPTION, data);
        }
        
        data.setProperty(option, collapsedValue, expandedValue);
                
        return port;
    }
}
