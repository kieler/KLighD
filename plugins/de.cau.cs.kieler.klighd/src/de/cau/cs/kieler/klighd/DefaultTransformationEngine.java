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

/**
 * 
 * 
 * @author mri
 */
public class DefaultTransformationEngine implements ITransformationEngine {

    /**
     * {@inheritDoc}
     */
    public boolean tryTransform(final ViewContext viewContext, final Object model) {
        Object currentModel = model;
        for (TransformationContext<?, ?> transformationContext : viewContext
                .getTransformationContexts()) {
            @SuppressWarnings("unchecked")
            TransformationContext<Object, Object> objTransformationContext =
                    (TransformationContext<Object, Object>) transformationContext;
            ITransformation<Object, Object> transformation =
                    objTransformationContext.getTransformation();
            // perform the transformation if possible
            if (transformation.supports(currentModel)) {
                objTransformationContext.setSourceModel(currentModel);
                currentModel = transformation.transform(currentModel, objTransformationContext);
                objTransformationContext.setTargetModel(currentModel);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public Object transform(final ViewContext viewContext, final Object model) {
        Object currentModel = model;
        for (TransformationContext<?, ?> transformationContext : viewContext
                .getTransformationContexts()) {
            @SuppressWarnings("unchecked")
            TransformationContext<Object, Object> objTransformationContext =
                    (TransformationContext<Object, Object>) transformationContext;
            ITransformation<Object, Object> transformation =
                    objTransformationContext.getTransformation();
            objTransformationContext.setSourceModel(currentModel);
            currentModel = transformation.transform(currentModel, objTransformationContext);
            objTransformationContext.setTargetModel(currentModel);
        }
        return currentModel;
    }

}
