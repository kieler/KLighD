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
package de.cau.cs.kieler.klighd.incremental.diff;

import java.util.List;

import org.eclipse.emf.compare.diff.engine.GenericDiffEngine;
import org.eclipse.emf.compare.diff.engine.check.AttributesCheck;
import org.eclipse.emf.compare.diff.engine.check.ReferencesCheck;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.match.metamodel.Side;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.impl.IPropertyToObjectMapImpl;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.microlayout.GridPlacementUtil;

/**
 * A customized {@link org.eclipse.emf.compare.diff.engine.IDiffEngine2 IDiffEngine2} that realizes
 * customizations of the EMF Compare diff process. It is registered in the <code>plugin.xml</code>.<br>
 * <br>
 * Besides the customizations in {@link KGraphAttributesCheck} and {@link KGraphReferencesCheck}
 * this class modifies the treatment of {@link UnmatchElement UnmatchElements} by overriding
 * {@link #processUnmatchedElements(DiffGroup, List)} and delegating to the super implementation
 * with filtered parameters.<br>
 * <br>
 * Currently is suppresses the transfer of single attribute values of
 * {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout KShapeLayouts},
 * {@link de.cau.cs.kieler.kiml.klayoutdata.KInsets KInsets}, as well as
 * {@link de.cau.cs.kieler.kiml.klayoutdata.KPoint KPoint} instances.<br>
 * <br>
 * In addition the treatment of {@link IProperty} definitions on
 * {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} data is tailored.
 * 
 * @author chsch
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class KGraphDiffEngine extends GenericDiffEngine {

    /**
     * {@inheritDoc}
     */
    protected AttributesCheck getAttributesChecker() {
        return new KGraphAttributesCheck(this.getMatchManager());
    }

    /**
     * {@inheritDoc}
     */
    protected ReferencesCheck getReferencesChecker() {
        return new KGraphReferencesCheck(getMatchManager());
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This customized implementation applies {@link #FILTER} to the provided <code>unmatched</code>
     * elements and passes that modified list to the <code>super</code> implementation.
     */
    protected void processUnmatchedElements(final DiffGroup diffRoot,
            final List<UnmatchElement> unmatched) {
        List<UnmatchElement> filteredList = Lists.newLinkedList(Iterables.filter(unmatched, FILTER));
        super.processUnmatchedElements(diffRoot, filteredList);
    }

    /**
     * A list of {@link IProperty IProperties} whose occurrences on KRendering data are to be merged
     * during the merge step. This list acts as a "white list", it is incorporated during the
     * pre-processing of unmatched elements being identified during the match step. That
     * pre-processing occurs during the diff step, see
     * {@link #processUnmatchedElements(DiffGroup, List)} below.<br>
     * <br>
     * Property definitions on KRendering data beyond these ones are ignored while processing
     * unmatched elements and, thus, won't be deleted.<br>
     * <br>
     * Note that property definitions on {@link de.cau.cs.kieler.kiml.klayoutdata.KLayoutData
     * KLayoutData} are not considered here! See {@link #FILTER} for that restriction.
     */
    private static final List<IProperty<?>> MERGE_PROPERTIES = ImmutableList.<IProperty<?>>of(
            GridPlacementUtil.CHILD_AREA_POSITION,
            GridPlacementUtil.ESTIMATED_GRID_DATA,
            KlighdInternalProperties.MODEL_ELEMEMT);
    
    /**
     * This predicate defines the conditions for dropping elements of the list of unmatched ones. An
     * element is kept in the list, and thus treated as an unmatched one in the upcoming merge, if
     * the related value of <code>res</code> is <code>true</code>, and dropped (and thus later on
     * ignored) if <code>res</code> is false.
     */
    private static final Predicate<UnmatchElement> FILTER = new Predicate<UnmatchElement>() {
        public boolean apply(final UnmatchElement element) {
            boolean res = true;
            // omit all KPoints, they are managed by KIML
            res &= !(KLayoutDataPackage.eINSTANCE.getKPoint().isInstance(element.getElement()));
            
            // Omit all IPropertyToObjectMapImpls that are attached to KRendering-related
            //  data structures except for those mentioned in MERGE_PROPERTIES.
            // Those are set by the KLighD translation infrastructure rather than the
            //  Piccolo-based rendering binding; they must be updated/replaced during the merge!
            // The remaining ones have been attached to the displayed view model to cache
            //  information and shall be preserved!
            res &= !(element.getSide().equals(Side.LEFT)
                    && element.getElement() instanceof IPropertyToObjectMapImpl
                    && element.getElement().eContainer().eClass().getEPackage()
                            .equals(KRenderingPackage.eINSTANCE)
                    && !MERGE_PROPERTIES.contains(
                            ((IPropertyToObjectMapImpl) element.getElement()).getKey()));
            return res; 
        }
    };
}
