/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.transformations;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.TransformationContext;

/**
 * A duplicating transformation á la {@link org.eclipse.emf.ecore.util.EcoreUtil#copy
 * EcoreUtil#copy} preserving the source-target-mapping.<br>
 * Is currently used in the {@link de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy
 * SimpleUpdateStrategy}.
 * 
 * @author chsch
 * 
 * @param <S>
 *            Type of the model to be duplicated.
 */
public class DuplicatingTransformation<S extends EObject> implements ITransformation<S, S> {

    private Copier currentCopier = null;
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public S transform(final S model, final TransformationContext<S, S> transformationContext) {
        // 3 lines are copied from EcoreUtil.copy()
        this.currentCopier = new Copier();
        final EObject result = this.currentCopier.copy(model);
        this.currentCopier.copyReferences();
        transformationContext.clear();
        for (Map.Entry<EObject, EObject> entry : currentCopier.entrySet()) {
            transformationContext.addSourceTargetPair(entry.getKey(), entry.getValue());
        }
        return (S) result;
    }

    /**
     * {@inheritDoc}
     */
    public Object getSourceElement(final Object element,
            final TransformationContext<S, S> transformationContext) {
        return transformationContext.getSourceElement(element);
    }

    /**
     * {@inheritDoc}
     */
    public Object getTargetElement(final Object element,
            final TransformationContext<S, S> transformationContext) {
        return transformationContext.getTargetElement(element);
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getSourceClass() {
        return EObject.class;
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getTargetClass() {
        return EObject.class;
    }

}
