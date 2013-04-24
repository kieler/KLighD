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

import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;

import com.google.inject.Inject;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.krendering.KStyleHolder;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;

/**
 *
 * @author msp
 */
public class KGraphQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {
    
    @Inject
    private IQualifiedNameConverter qualifiedNameConverter;
    
    public QualifiedName qualifiedName(final KGraphElement element) {
        KIdentifier identifier = element.getData(KIdentifier.class);
        if (identifier != null) {
            return qualifiedNameConverter.toQualifiedName(identifier.getId());
        }
        return null;
    }
    
    public QualifiedName qualifiedName(final KStyleHolder styleHolder) {
    	return qualifiedNameConverter.toQualifiedName(styleHolder.getId());
    }

}
