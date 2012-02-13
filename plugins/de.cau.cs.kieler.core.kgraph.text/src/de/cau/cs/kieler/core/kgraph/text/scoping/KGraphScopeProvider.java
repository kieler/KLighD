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

import javax.inject.Inject;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * This class contains custom scoping description enabling the proper handling of KGraphs.
 * 
 * @author chsch
 */
public class KGraphScopeProvider extends AbstractDeclarativeScopeProvider {
    
    @Inject
    private IQualifiedNameProvider nameProvider;

    /**
     * Method for computing the scope of {@link KEdge}s w.r.t. to their targets.
     * 
     * @param edge
     *            the edge to compute the valid scope of reachable {@link KNode}s.
     * @param reference
     *            the egde's reference to compute the scope for. (only 'target' is valid)
     * @return the {@link IScope} containing the valid {@link KNode}s.
     */
    // SUPPRESS CHECKSTYLE NEXT MethodNameCheck
    public IScope scope_KEdge_target(final KEdge edge, final EReference reference) {
        return Scopes.scopeFor(
                IterableExtensions.filter(
                        EcoreUtil2.eAllContents(EcoreUtil2.getRootContainer(edge)), KNode.class),
                nameProvider, IScope.NULLSCOPE);
    }
}
