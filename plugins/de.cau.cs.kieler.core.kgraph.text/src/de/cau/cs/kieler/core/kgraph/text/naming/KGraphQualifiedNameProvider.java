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
package de.cau.cs.kieler.core.kgraph.text.naming;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.naming.SimpleNameProvider;

import com.google.inject.Inject;

/**
 * This custom {@link IQualifiedNameProvider} realizes the computation of the {@link QualifiedName}
 * of {@link EObject EObjects} involved in KGraph/KRendering-based view models. Due to the absence
 * of a name attribute in the involved classes it uses the {@link EObject EObject's} fragment URI.
 * It is used e.g. in the linking phase (connecting cross references in the AST) or the serialization
 * in order to determine the correct identifier to dump out. 
 * 
 * The actual translation of the fragment URI into a {@link QualifiedName} is delegated to an
 * implementation of {@link IQualifiedNameConverter} as this translation must also be performed for
 * content assist proposals (whose computation involves the {@link IQualifiedNameConverter} too).
 * 
 * TODO: In an advanced version of the textual KGraph language the fragment URI used to identify the
 * edges' target and ports or the reference of KRenderingRefs shall be parsed and decomposed by related
 * parser rules.
 * 
 * @author chsch
 * @kieler.design proposed 2012-11-01 chsch
 * @kieler.rating proposed yellow 2012-11-01 chsch
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