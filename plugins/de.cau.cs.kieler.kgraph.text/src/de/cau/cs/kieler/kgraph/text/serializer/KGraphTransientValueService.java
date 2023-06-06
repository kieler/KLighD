/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.kgraph.text.serializer;

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.parsetree.reconstr.impl.DefaultTransientValueService;

import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;

/**
 * A KGraph specific {@link org.eclipse.xtext.parsetree.reconstr.ITransientValueService
 * ITransientValueService}. Prevents serializer from trying to dump out eOpposite relations and
 * {@link IProperty} data.
 * 
 * It is required while serializing portions of the model after applying semantic modifications via
 * quick fixes.
 * 
 * @author chsch
 */
public class KGraphTransientValueService extends DefaultTransientValueService {

    private static final KGraphPackage PACKAGE = KGraphPackage.eINSTANCE; 
    private static final KRenderingPackage R_PACKAGE = KRenderingPackage.eINSTANCE; 
    
    /**
     * {@inheritDoc}
     */
    public boolean isTransient(final EObject owner, final EStructuralFeature feature,
            final int index) {
        if (feature == PACKAGE.getKNode_IncomingEdges()
                || feature == PACKAGE.getKNode_Parent()
                || feature == PACKAGE.getKEdge_Source()
                || feature == R_PACKAGE.getKRendering_Parent()
                || feature == PACKAGE.getKPort_Edges()
                || feature == PACKAGE.getEMapPropertyHolder_Properties()) {
            return true;
        }
        return false;
    }

}
