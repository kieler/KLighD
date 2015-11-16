/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels;

import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;

/**
 * A condition on {@code EObject}s that checks if an object's container is an instance of a given class.
 * 
 * @author cds
 */
public class EmfContainerCondition implements Predicate<EObject> {
    
    /** The class the container must be an instance of for the condition to apply. */
    private Class<?> containerClass;
    
    
    /**
     * Creates a new instance with the given container class requirement.
     * 
     * @param containerClass
     *            the class an EObject's container must be an instance of if the condition is to
     *            apply.
     */
    public EmfContainerCondition(final Class<?> containerClass) {
        this.containerClass = containerClass;
    }
    
    
    /**
     * {@inheritDoc}
     */
    public boolean apply(final EObject object) {
        return containerClass.isInstance(object.eContainer());
    }

}
