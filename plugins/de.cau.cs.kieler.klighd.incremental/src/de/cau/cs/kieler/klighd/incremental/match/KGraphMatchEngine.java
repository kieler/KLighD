/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.incremental.match;

import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.match.engine.AbstractSimilarityChecker;
import org.eclipse.emf.compare.match.engine.GenericMatchEngine;
import org.eclipse.emf.compare.match.statistic.MetamodelFilter;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.impl.IPropertyToObjectMapImpl;

/**
 * I customized {@link org.eclipse.emf.compare.match.engine.IMatchEngine IMatchEngine} that realizes
 * customizations of the EMF Compare match process. It is registered in the <code>plugin.xml</code>.<br>
 * <br>
 * Currently, it solely contributes a custom similarity checker by overriding
 * {@link #prepareChecker()}. That checker refines the checking of
 * {@link de.cau.cs.kieler.core.properties.IProperty IProperty} definitions on
 * {@link de.cau.cs.kieler.core.kgraph.KGraphData KGraphData} and delegates to the generic one for
 * other data.
 * 
 * @author chsch
 */
public class KGraphMatchEngine extends GenericMatchEngine {

    /**
     * {@inheritDoc}
     */
    protected AbstractSimilarityChecker prepareChecker() {
        return new KGraphSimilarityChecker(filter, super.prepareChecker());
    }


    /**
     * Specialized similarity checker that is in charge of determining the similarity of
     * {@link de.cau.cs.kieler.core.properties.IProperty IProperty} definitions on
     * {@link de.cau.cs.kieler.core.kgraph.KGraphData KGraphData}.
     * 
     * @author chsch
     */
    private static class KGraphSimilarityChecker extends AbstractSimilarityChecker {

        /**
         * @param mmFilter
         * @param bridge
         */
        public KGraphSimilarityChecker(final MetamodelFilter mmFilter,
                final AbstractSimilarityChecker theDelegate) {
            super(mmFilter);
            this.delegate = theDelegate;
        }
        
        private AbstractSimilarityChecker delegate = null;

        private static final EClass IPROPERTY_TO_OBJECT_MAP_CLASS
                = KGraphPackage.eINSTANCE.getIPropertyToObjectMap();
        
        private static final double VALUES_EQUAL = 1d;
        private static final double VALUE_TYPES_EQUAL = 0.66d;
        private static final double IDS_EQUAL = 0.33d;
        private static final double FULLY_UNEQUAL = 0d;


        /**
         * {@inheritDoc}
         */
        public double absoluteMetric(final EObject obj1, final EObject obj2)
                throws FactoryException {
            
            if (IPROPERTY_TO_OBJECT_MAP_CLASS.isInstance(obj1)
                    && IPROPERTY_TO_OBJECT_MAP_CLASS.isInstance(obj2)) {
                final IPropertyToObjectMapImpl p1 = (IPropertyToObjectMapImpl) obj1;
                final IPropertyToObjectMapImpl p2 = (IPropertyToObjectMapImpl) obj2;
                
                if (p1.getKey().equals(p2.getKey())) {
                    if (p1.getValue().equals(p2.getValue())) {
                        return VALUES_EQUAL;
                        
                    } else if (p1.getValue().getClass().equals(p2.getValue().getClass())) {
                        return VALUE_TYPES_EQUAL;

                    } else {
                        return IDS_EQUAL;
                    }
                } else {
                    return FULLY_UNEQUAL;
                }
            } else {
                return delegate.absoluteMetric(obj1, obj2);
            }
        }

        /**
         * I didn't get the actual meaning of this method, I hope I did it the right way.<br>
         * <br>
         * {@inheritDoc}
         */
        public boolean isSimilar(final EObject obj1, final EObject obj2) throws FactoryException {
            if (IPROPERTY_TO_OBJECT_MAP_CLASS.isInstance(obj1)
                    && IPROPERTY_TO_OBJECT_MAP_CLASS.isInstance(obj2)) {
                final IPropertyToObjectMapImpl p1 = (IPropertyToObjectMapImpl) obj1;
                final IPropertyToObjectMapImpl p2 = (IPropertyToObjectMapImpl) obj2;
                
                boolean result = p1.getKey().equals(p2.getKey())
                        && p1.getValue().equals(p2.getValue());
                return result;
            }
            return delegate.isSimilar(obj1, obj2);
        }

        @Override
        public void init(final EObject leftObject, final EObject rightObject) throws FactoryException {
            this.delegate.init(leftObject, rightObject);
        }

        @Override
        public void init(final Resource leftResource, final Resource rightResource)
                throws FactoryException {
            this.delegate.init(leftResource, rightResource);
        }
    }
}
