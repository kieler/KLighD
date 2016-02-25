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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kgraph.text.serializer;

import org.eclipse.elk.graph.KGraphPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.parsetree.reconstr.impl.DefaultTransientValueService;

import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;

/**
 * A KGraph specific {@link org.eclipse.xtext.parsetree.reconstr.ITransientValueService
 * ITransientValueService}. Prevents serializer from trying to dump out eOpposite relations and
 * {@link de.cau.cs.kieler.core.properties.IProperty} data.
 * 
 * It is required while serializing portions of the model after applying semantic modifications via
 * quick fixes.
 * 
 * @author chsch
 * @kieler.design proposed 2012-11-01 chsch
 * @kieler.rating proposed yellow 2012-11-01 chsch
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
