/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.incremental.merge;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPort;

/**
 * An {@link EcoreUtil.Copier ecore copier} that maps {@link KPort KPorts} to their copy and tracks
 * nodes whose ports have been updaten.
 * 
 * @author csp
 */
public class KNodeCopier extends Copier {

    private static final long serialVersionUID = 965672143875620708L;

    private Map<KPort, KPort> newToBasePortMap;
    private Set<KNode> portupdatedNodes;

    /**
     * Create a copier.
     * 
     * @param newToBasePortMap
     *            a map to save new ports in.
     * @param portupdatedNodes
     *            a set of nodes whose ports have been updated.
     */
    public KNodeCopier(final Map<KPort, KPort> newToBasePortMap,
            final Set<KNode> portupdatedNodes) {
        
        this.newToBasePortMap = newToBasePortMap;
        this.portupdatedNodes = portupdatedNodes;
    }

    @Override
    public EObject copy(final EObject eObject) {
        EObject copy = super.copy(eObject);
        if (copy instanceof KPort) {
            newToBasePortMap.put((KPort) eObject, (KPort) copy);
        } else if (copy instanceof KNode) {
            portupdatedNodes.add((KNode) copy);
        }
        return copy;
    }

}
