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

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.TransformationOption;

/**
 * A duplicating transformation à la {@link org.eclipse.emf.ecore.util.EcoreUtil#copy
 * EcoreUtil#copy} preserving the source-target-mapping.<br>
 * Is used in the {@link de.cau.cs.kieler.klighd.TransformationsGraph TransformationsGraph} while
 * configuring {@link de.cau.cs.kieler.klighd.ViewContext ViewContexts} in case no semantic
 * transformation is needed in order to visualize the given model. Its aim is to decouple the model
 * access performed e.g. by the model editor and those performed by
 * {@link de.cau.cs.kieler.klighd.IUpdateStrategy IUpdateStrategys} and
 * KLighD {@link de.cau.cs.kieler.klighd.IViewer IViewers}.
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
    public Class<?> getSourceClass() {
        return EObject.class;
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getTargetClass() {
        return EObject.class;
    }

    /**
     * {@inheritDoc}
     */
    public Set<TransformationOption> getTransformationOptions() {
        return Collections.emptySet();
    }

}
