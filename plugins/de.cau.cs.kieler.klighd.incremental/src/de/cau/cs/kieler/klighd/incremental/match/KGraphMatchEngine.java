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
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;

/**
 * I customized {@link org.eclipse.emf.compare.match.engine.IMatchEngine IMatchEngine} that realizes
 * customizations of the EMF Compare match process. It is registered in the <code>plugin.xml</code>.<br>
 * <br>
 * Currently, it solely contributes a custom similarity checker by overriding
 * {@link #prepareChecker()}. That checker refines the checking of
 * {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout KShapeLayouts} and
 * {@link de.cau.cs.kieler.core.properties.IProperty IProperty} definitions on
 * {@link de.cau.cs.kieler.core.kgraph.KGraphData KGraphData}, and delegates to the generic standard
 * checker for other data. See {@link KGraphSimilarityChecker} for details.
 * 
 * @author chsch
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class KGraphMatchEngine extends GenericMatchEngine {

    /**
     * {@inheritDoc}
     */
    protected AbstractSimilarityChecker prepareChecker() {
        return new KGraphSimilarityChecker(filter, super.prepareChecker());
    }


    /**
     * Specialized similarity checker that is in charge of determining the (un-)similarity of
     * {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout KShapeLayouts} and
     * {@link de.cau.cs.kieler.core.properties.IProperty IProperty} definitions on
     * {@link de.cau.cs.kieler.core.kgraph.KGraphData KGraphData}.<br>
     * <br>
     * While the similarity of IProperty definitions is determined according to some natural
     * function, {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout KShapeLayouts} are stated to
     * be dissimilar with respect to any other object.<br>
     * <br>
     * This implies that shape layouts are always treated as unmatched elements, which in turn leads
     * to the removal of the instance in the target model, in our case the view's base view model,
     * and the addition of a copy of the newly created view model's shape layout instance. This way
     * out-dated memories created by the diagram rendering infrastructure in form of IProperties,
     * mainly the <code>KlighdLayoutManager</code>, are disposed properly.
     * 
     * @author chsch
     */
    private static class KGraphSimilarityChecker extends AbstractSimilarityChecker {

        /**
         * Constructor.
         * 
         * @param mmFilter
         *            a meta model filter the checker can use to know whether a feature always has
         *            the same value or not in the models.
         * @param theDelegate
         *            the {@link AbstractSimilarityChecker} to delegate all requests to that are not
         *            "in scope" of this checker implementation
         */
        public KGraphSimilarityChecker(final MetamodelFilter mmFilter,
                final AbstractSimilarityChecker theDelegate) {
            super(mmFilter);
            this.delegate = theDelegate;
        }
        
        /** The generic standard checker that is referred to for most input data. */
        private AbstractSimilarityChecker delegate = null;

        // Some "equality" values that are returned for IProperty definitions,
        //  I just guessed those values - I'm not aware of any policy for such values
        private static final double VALUES_EQUAL = 1d;
        private static final double VALUE_TYPES_EQUAL = 0.66d;
        private static final double IDS_EQUAL = 0.33d;
        private static final double FULLY_UNEQUAL = 0d;

        private static final EClass KSHAPE_LAYOUT_CLASS
            = KLayoutDataPackage.eINSTANCE.getKShapeLayout();
        
        private static final EClass IPROPERTY_TO_OBJECT_MAP_CLASS
            = KGraphPackage.eINSTANCE.getIPropertyToObjectMap();
        

        /**
         * {@inheritDoc}<br>
         * <br>
         * This customization states that instances of
         * {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout KShapeLayout} are always dissimilar
         * to other objects, which results in a replacement (and re-initialization) of the instance
         * in the target model under all circumstances.<br>
         * <br>
         * The similarity of {@link IPropertyToObjectMapImpl IPropertyToObjectMapImpls}, i.e.
         * IProperty definitions, is determined based on the property's id, value type, and concrete
         * value.
         */
        public double absoluteMetric(final EObject obj1, final EObject obj2)
                throws FactoryException {
            
            if (KSHAPE_LAYOUT_CLASS.isInstance(obj1) || KSHAPE_LAYOUT_CLASS.isInstance(obj2)) {
                return FULLY_UNEQUAL;
            }
            
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
         * {@inheritDoc}<br>
         * <br>
         * I didn't get the actual meaning of this method, I hope I did it the right way.<br>
         * <br>
         * This customization states that instances of
         * {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout KShapeLayout} are always dissimilar
         * to other objects, which results in a replacement (and re-initialization) of the instance
         * in the target model under all circumstances.<br>
         * <br>
         * The similarity of {@link IPropertyToObjectMapImpl IPropertyToObjectMapImpls}, i.e.
         * IProperty definitions, is determined based on the property's id and concrete value.
         */
        public boolean isSimilar(final EObject obj1, final EObject obj2) throws FactoryException {
            
            if (KSHAPE_LAYOUT_CLASS.isInstance(obj1) || KSHAPE_LAYOUT_CLASS.isInstance(obj2)) {
                return false;
            }
            
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
