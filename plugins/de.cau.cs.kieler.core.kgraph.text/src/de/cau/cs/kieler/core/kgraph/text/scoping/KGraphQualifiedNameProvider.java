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
package de.cau.cs.kieler.core.kgraph.text.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.naming.SimpleNameProvider;

import com.google.inject.Inject;

/**
 * 
 * @author chsch
 */
public class KGraphQualifiedNameProvider extends SimpleNameProvider implements
        IQualifiedNameProvider {
    
    @Inject
    private IQualifiedNameConverter qualifiedNameConverter;
    
    /**
     * Simply delegate to the {@link IQualifiedNameConverter} to obtain the QN.
     * 
     * @param obj
     *            the {@link EObject} to compute the QN for
     * @return the {@link QualifiedName}
     */
    public QualifiedName getFullyQualifiedName(final EObject obj) {
        return qualifiedNameConverter.toQualifiedName(obj.eResource().getURIFragment(obj));
    }
}