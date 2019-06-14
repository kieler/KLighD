/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.syntheses;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.google.inject.Inject;

import de.cau.cs.kieler.klighd.SynthesisOption;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared;

/** 
 * Abstract class for partial syntheses, delegating helper methods to be usable like a AbstractDiagramSynthesis.
 * A non-abstract subclass of this can be used within any other diagram synthesis or sub-synthesis via injection.
 * An example parent synthesis should use subclass of this abstract sub synthesis as follows (Xtend code):
 * <pre>
 *&#64;ViewSynthesisShared
 *class ExampleSynthesis extends AbstractDiagramSynthesis&#60;ModelToVisualize&#62; {
 *    // Alternatively: extends AbstractSubSynthesis&#60;SubModel, KNode&#62;
 *    &#64;Inject OtherSubSynthesis subSynthesis
 *    
 *    override transform(ModelToVisualize model) {
 *        [...]
 *        val subModel = subSynthesis.transform(model.submodel)
 *        [...]
 *    }
 *
 *}
 * </pre>
 * for some model class ModelToVisualize with a submodel and some sub synthesis OtherSubSynthesis extending this class
 * for that submodel.
 * 
 * @param <I> Input type of the sub-synthesis.
 * @param <O> Output type of the sub-synthesis.
 * @author nre
 * @see AbstractDiagramSynthesis
 */
@ViewSynthesisShared
public abstract class AbstractSubSynthesis<I, O extends KGraphElement> {
    
    /**
     * The parent synthesis that helper methods are delegated to.
     * This will always be the instance of the parent synthesis as it is the only synthesis known to the injector here.
     * The &#64;{@link ViewSynthesisShared} annotation makes sure it is used in singleton scope and this injected
     * synthesis really is the parent synthesis that injected this sub synthesis.
     */
    @Inject private AbstractDiagramSynthesis<?> parent;

    /** 
     * Transforms the given element into a list of diagram elements.
     * @param element The model element to transform.
     * @return The transformed diagram elements.
     */
    public abstract List<O> transform(I element);

    /** 
     * The {@link SynthesisOption}s this sub-synthesis contributes to the synthesis.
     * @see de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis#getDisplayedSynthesisOptions()
     */
    public List<SynthesisOption> getDisplayedSynthesisOptions() {
        return Collections.emptyList();
    }

    // -------------------------------------------------------------------------
    // Delegate from AbstractDiagramSynthesis
    
    /**
     * @see de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis#associateWith(org.eclipse.emf.ecore.EObject, java.lang.Object)
     */
    public <T extends EObject> T associateWith(final T derived, final Object source) {
        return parent.associateWith(derived, source);
    }

    /** 
     * @see de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis#getObjectValue(de.cau.cs.kieler.klighd.SynthesisOption)
     */
    public Object getObjectValue(final SynthesisOption option) {
        return parent.getObjectValue(option);
    }

    /** 
     * @see de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis#getBooleanValue(de.cau.cs.kieler.klighd.SynthesisOption)
     */
    public boolean getBooleanValue(final SynthesisOption option) {
        return parent.getBooleanValue(option);
    }

    /** 
     * @see de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis#getIntValue(de.cau.cs.kieler.klighd.SynthesisOption)
     */
    public int getIntValue(final SynthesisOption option) {
        return parent.getIntValue(option);
    }

    /** 
     * @see de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis#getFloatValue(de.cau.cs.kieler.klighd.SynthesisOption)
     */
    public float getFloatValue(final SynthesisOption option) {
        return parent.getFloatValue(option);
    }

    /**
     * @see de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis#getUsedContext()
     */
    public ViewContext getUsedContext() {
        return parent.getUsedContext();
    }
}

