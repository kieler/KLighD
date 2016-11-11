/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.incremental.match;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import com.google.common.base.Predicate;

import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.util.RenderingContextData;

/**
 * Filters, which features should be in scope for matching.
 * @author csp
 *
 */
public class KScopeFilter implements Predicate<EObject> {

    public boolean apply(EObject eObject) {
        final EPackage ePackage = eObject.eClass().getEPackage();
        final int id = eObject.eClass().getClassifierID();

        if (ePackage == KGraphPackage.eINSTANCE) {

            switch (id) {
            case KGraphPackage.KLABEL:
            case KGraphPackage.KNODE:
            case KGraphPackage.KEDGE:
            case KGraphPackage.KPORT:
            case KGraphPackage.KSHAPE_LAYOUT:
            case KGraphPackage.KEDGE_LAYOUT:
            case KGraphPackage.KINSETS:
                return true;
            case KGraphPackage.IPROPERTY_TO_OBJECT_MAP:
                if (eObject.eContainer() instanceof RenderingContextData) {
                    return false;
                }
                return true;
            case KGraphPackage.KPOINT:
            default:
                // e.g. instances of RenderingContextData,
                // which is a pure Java subclass of KGraphDataImpl
                return false;
            }

        } else if (ePackage == KRenderingPackage.eINSTANCE) {
            return true;
        } else {
            return false;
        }
    }
}
