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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;

import com.google.inject.Inject;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;

/**
 * This class contains custom scoping description.
 * 
 * @author msp
 * @author chsch
 * @author csp
 */
public class KGraphScopeProvider extends AbstractDeclarativeScopeProvider {

    @Inject
    KGraphQualifiedNameProvider nameProvider;

    /**
     * Provides a scope for ports in the given context.
     * 
     * @param context
     *            the context of the queried scope.
     * @param reference
     *            the reference to provide a scope for.
     * @return the scope of valid ports in the given context.
     */
    public IScope scope_KPort(EObject context, EReference reference) {
        if (context instanceof KEdge) {
            // We are looking for a port in the context of an edge
            if (reference == KGraphPackage.eINSTANCE.getKEdge_SourcePort()) {
                // The reference is source port, so we provide a scope for all ports in the source node.
                return Scopes.scopeFor(((KEdge) context).getSource().getPorts(),
                        nameProvider, IScope.NULLSCOPE);
            } else if (reference == KGraphPackage.eINSTANCE.getKEdge_TargetPort()
                    && ((KEdge) context).getTarget() != null) {
                // The reference is target port, so we provide a scope for all ports in the target node.
                return Scopes.scopeFor(((KEdge) context).getTarget().getPorts(),
                        nameProvider, IScope.NULLSCOPE);
            }
            // Should never happen, but if we end up here, there are no valid ports to provide.
            return IScope.NULLSCOPE;
        } else {
            // Return null to indicate that we can't determine a valid scope.
            return null;
        }
    }
}
