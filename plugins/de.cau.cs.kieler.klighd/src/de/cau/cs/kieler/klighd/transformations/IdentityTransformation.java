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
package de.cau.cs.kieler.klighd.transformations;

import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.TransformationContext;

/**
 * An implementation of {@code ITransformation} which represents the identity.
 * 
 * @author mri
 */
public class IdentityTransformation implements ITransformation<Object, Object> {

    /**
     * {@inheritDoc}
     */
    public Object transform(final Object model,
            final TransformationContext<Object, Object> transformationContext) {
        return model;
    }

    /**
     * {@inheritDoc}
     */
    public Object getSourceElement(final Object element,
            final TransformationContext<Object, Object> transformationContext) {
        return element;
    }

    /**
     * {@inheritDoc}
     */
    public Object getTargetElement(final Object element,
            final TransformationContext<Object, Object> transformationContext) {
        return element;
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getSourceClass() {
        return Object.class;
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getTargetClass() {
        return Object.class;
    }

    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object model) {
        return true;
    }

}
