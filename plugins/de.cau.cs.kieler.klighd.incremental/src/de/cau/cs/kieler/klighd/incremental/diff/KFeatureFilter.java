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
package de.cau.cs.kieler.klighd.incremental.diff;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;

/**
 * Used by the {@link KDiffEngine}. Determines, which references and attributes to check for
 * differences.
 * 
 * @author csp
 */
public class KFeatureFilter extends FeatureFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isIgnoredReference(Match match, EReference reference) {
        // Consider all references for differences.
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<EAttribute> getAttributesToCheck(final Match match) {
        if (match.getLeft() == null) {
            return match.getRight().eClass().getEAllAttributes().iterator();
        }
        return Iterators.filter(match.getLeft().eClass().getEAllAttributes().iterator(),
                new Predicate<EAttribute>() {
                    public boolean apply(EAttribute attribute) {
                        // Don't detect differences in layout attributes with default value.
                        // These will get adjusted by following layout.
                        return !(IGNORED_ATTRIBUTES.contains(attribute)
                                && !match.getLeft().eIsSet(attribute));
                    }
                });
    }

    /**
     * List of attributes that will be ignored.
     */
    private static final List<EAttribute> IGNORED_ATTRIBUTES = ImmutableList.of(
            KGraphPackage.eINSTANCE.getKShapeLayout_Xpos(),
            KGraphPackage.eINSTANCE.getKShapeLayout_Ypos(),
            KGraphPackage.eINSTANCE.getKShapeLayout_Width(),
            KGraphPackage.eINSTANCE.getKShapeLayout_Height(),
            KGraphPackage.eINSTANCE.getKInsets_Left(),
            KGraphPackage.eINSTANCE.getKInsets_Right(),
            KGraphPackage.eINSTANCE.getKInsets_Top(),
            KGraphPackage.eINSTANCE.getKInsets_Bottom(),
            KGraphPackage.eINSTANCE.getKPoint_X(),
            KGraphPackage.eINSTANCE.getKPoint_Y());

}
