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
package de.cau.cs.kieler.klighd.diff;

import java.util.List;

import org.eclipse.emf.compare.diff.engine.GenericDiffEngine;
import org.eclipse.emf.compare.diff.engine.check.AttributesCheck;
import org.eclipse.emf.compare.diff.engine.check.ReferencesCheck;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;

/**
 * A customized {@link org.eclipse.emf.compare.diff.engine.IDiffEngine2 IDiffEngine2} that realizes
 * customizations of the EMF Compare diff process.<br>
 * <br>
 * Besides the customizations in {@link KGraphAttributesCheck} and {@link KGraphReferencesCheck}
 * this class modifies the treatment of {@link UnmatchElement UnmatchElements} by overriding
 * {@link #processUnmatchedElements(DiffGroup, List)} and delegating to the super implementation
 * with filtered parameters. Currently is suppresses the transfer of KPoints values.
 * 
 * TODO: This implementation is assumed to not work properly with pre-defined layout data.
 * 
 * @author chsch, sgu
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
     * {@inheritDoc}
     */
    protected void processUnmatchedElements(final DiffGroup diffRoot,
            final List<UnmatchElement> unmatched) {
        List<UnmatchElement> filteredList = Lists.newLinkedList(Iterables.filter(unmatched,
                new Predicate<UnmatchElement>() {
                    public boolean apply(final UnmatchElement element) {
                        return !(KLayoutDataPackage.eINSTANCE.getKPoint().isInstance(
                                        element.getElement()));
                    }
                }));
        super.processUnmatchedElements(diffRoot, filteredList);
    }
}
