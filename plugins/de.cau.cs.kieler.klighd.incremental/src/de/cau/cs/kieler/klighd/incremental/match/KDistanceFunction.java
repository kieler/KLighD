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

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.match.eobject.EditionDistance;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;

import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.impl.IPropertyToObjectMapImpl;
import de.cau.cs.kieler.klighd.krendering.KChildArea;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.util.KlighdPredicates;

/**
 * Compares two given EObjects and computes their distance.
 * 
 * @author csp
 */
public class KDistanceFunction extends EditionDistance {

    private static final Predicate<Object> CANDIDATES = KlighdPredicates.instanceOf(
            KGraphElement.class, KRendering.class);
    
    private static final IProperty<Object> MODEL_ELEMENT = KlighdInternalProperties.MODEL_ELEMEMT;

    /**
     * {@inheritDoc}
     */
    @Override
    public double getThresholdAmount(EObject eObj) {
//        if (eObj instanceof KLayoutData) {
//            // LayoutData has no identifying value, therefore matches always
//            // and gets finally matched by nearest in Graph hierarchy
//            // (with restriction on matching conatainer, see distance- & areIdentic-function)
//            return Double.MAX_VALUE;
//        } else 
    	if (eObj instanceof KNode) {
            // FIXME evaluate better value
            // KNodes have too many optional (unset) features, so threshold-calculation fails
            return 90;
        }
        return super.getThresholdAmount(eObj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double distance(Comparison inProgress, EObject a, EObject b) {
        if (a instanceof IPropertyToObjectMapImpl && b instanceof IPropertyToObjectMapImpl) {
            // Match IPropertyToObjectMaps only for identical keys.
            IPropertyToObjectMapImpl aMap = (IPropertyToObjectMapImpl) a;
            IPropertyToObjectMapImpl bMap = (IPropertyToObjectMapImpl) b;
            if (!aMap.getKey().equals(bMap.getKey()))
                return Double.MAX_VALUE;
//        } else if (a instanceof KShapeLayout && b instanceof KShapeLayout) {
//            // Match KShapeLayouts only for matching containers.
//            if (!haveMatchingContainers(inProgress, a, b))
			// return Double.MAX_VALUE;
		} else if (a.eClass().getClassifierID() == b.eClass().getClassifierID() && CANDIDATES.apply(a)
				&& CANDIDATES.apply(a)) {
			Object modelA = ((EMapPropertyHolder) a).getProperty(MODEL_ELEMENT);
			Object modelB = ((EMapPropertyHolder) b).getProperty(MODEL_ELEMENT);
			if (modelA == modelB) {
				return 0;
			}
        } else if (a instanceof KChildArea && b instanceof KChildArea) {
            // Match KChildAreas only for matching containers.
            if (!haveMatchingContainers(inProgress, a, b))
                return Double.MAX_VALUE;
        }
        return super.distance(inProgress, a, b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areIdentic(Comparison inProgress, EObject a, EObject b) {
        if (a instanceof IPropertyToObjectMapImpl && b instanceof IPropertyToObjectMapImpl) {
            // Match IPropertyToObjectMaps only for identical keys.
            IPropertyToObjectMapImpl aMap = (IPropertyToObjectMapImpl) a;
            IPropertyToObjectMapImpl bMap = (IPropertyToObjectMapImpl) b;
            if (!aMap.getKey().equals(bMap.getKey()))
                return false;
//        } else if (a instanceof KShapeLayout && b instanceof KShapeLayout) {
//            // Match KShapeLayouts only for matching containers.
//            return haveMatchingContainers(inProgress, a, b);
		} else if (CANDIDATES.apply(a) && CANDIDATES.apply(a)) {
			Object modelA = ((EMapPropertyHolder) a).getProperty(MODEL_ELEMENT);
			Object modelB = ((EMapPropertyHolder) b).getProperty(MODEL_ELEMENT);
			if (modelA == modelB) {
				return true;
			}
        } else if (a instanceof KChildArea && b instanceof KChildArea) {
            // Match KChildAreas only for matching containers.
            return haveMatchingContainers(inProgress, a, b);
        }
        return super.areIdentic(inProgress, a, b);
    }

    /**
     * Check, wether two eObjects have matching containers.
     * 
     * @param inProgress
     *            the comparison being processed right now.
     * @param a first object.
     * @param b second object.
     * @return true, if a and b have matching containers.
     */
    protected boolean haveMatchingContainers(Comparison inProgress, EObject a, EObject b) {
        return (inProgress.getMatch(a.eContainer()) == inProgress.getMatch(b.eContainer()));
    }
}
