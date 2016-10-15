/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kgraph.text.scoping;

import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;

import com.google.inject.Inject;

import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KIdentifier;
import de.cau.cs.kieler.klighd.krendering.KStyleHolder;

/**
 * Qualified name provider for the KGraph language.
 *
 * @author msp
 */
public class KGraphQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {
    
    @Inject
    private IQualifiedNameConverter qualifiedNameConverter;
    
    /**
     * Generate a qualified name for graph elements from their identifier.
     * 
     * @param element a graph element
     * @return a qualified name derived from the element's identifier
     */
    public QualifiedName qualifiedName(final KGraphElement element) {
        KIdentifier identifier = element.getData(KIdentifier.class);
        if (identifier != null) {
            return qualifiedNameConverter.toQualifiedName(identifier.getId());
        }
        return null;
    }
    
    /**
     * Generate a qualified name for style holders from their identifier.
     * 
     * @param styleHolder a style holder
     * @return a qualified name derived from the style holder's identifier
     */
    public QualifiedName qualifiedName(final KStyleHolder styleHolder) {
        return qualifiedNameConverter.toQualifiedName(styleHolder.getId());
    }

}
