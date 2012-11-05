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

import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.naming.IQualifiedNameConverter.DefaultImpl;

/**
 * This custom {@link IQualifiedNameConverter} realizes translation of (here) fragment URIs into
 * {@link QualifiedName QualifiedNames} by simply creating a {@link QualifiedName} containing of
 * just one segment containing the given fragment URI. See also the
 * {@link KGraphQualifiedNameProvider}.
 * 
 * It is used during the computation of valid content assist proposals and in the
 * {@link KGraphScopeProvider}.
 * 
 * @author chsch
 * @kieler.design proposed 2012-11-01 chsch
 * @kieler.rating proposed yellow 2012-11-01 chsch
 */
public class KGraphQualifiedNameConverter extends DefaultImpl implements IQualifiedNameConverter {

    /**
     * {@inheritDoc}<br>
     * In this customized version, put the whole string into the first segment. 
     */
    public QualifiedName toQualifiedName(final String qualifiedNameAsString) {
        return QualifiedName.create(qualifiedNameAsString);
    }
    
}
