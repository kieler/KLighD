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

import java.util.Collections;
import java.util.Set;

import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.TransformationOption;

/**
 * An implementation of {@code ITransformation} which represents the identity. <br>
 * <br>
 * The class was used in the {@link de.cau.cs.kieler.klighd.TransformationsGraph
 * TransformationsGraph} while configuring {@link de.cau.cs.kieler.klighd.ViewContext ViewContexts}
 * in case no semantic transformation is needed but is currently replaced by the
 * {@link DuplicatingTransformation}
 * 
 * @author mri, chsch
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

    /**
     * {@inheritDoc}
     */
    public Set<TransformationOption> getTransformationOptions() {
        return Collections.emptySet();
    }
}
