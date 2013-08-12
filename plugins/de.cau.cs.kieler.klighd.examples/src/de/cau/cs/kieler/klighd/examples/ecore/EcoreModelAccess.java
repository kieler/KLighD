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
package de.cau.cs.kieler.klighd.examples.ecore;

import java.util.List;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.util.RunnableWithResult;

/**
 * @author chsch
 *
 */
public class EcoreModelAccess implements RunnableWithResult<List<? extends EObject>> {

    /**
     * Constructor.
     * 
     * @param theElements
     */
    public EcoreModelAccess(List<? extends EObject> theElements) {
        this.elements = theElements;
    }
    
    private List<? extends EObject> elements = null;
    
    /**
     * {@inheritDoc}
     */
    public void run() {        
    }

    /**
     * {@inheritDoc}
     */
    public List<? extends EObject> getResult() {
        return EModelElementCollection.of(Lists.newArrayList(Iterables.filter(elements, EModelElement.class)));
    }

}
