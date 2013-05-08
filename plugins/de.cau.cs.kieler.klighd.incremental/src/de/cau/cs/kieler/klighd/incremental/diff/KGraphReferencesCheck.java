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
package de.cau.cs.kieler.klighd.incremental.diff;

import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.engine.check.ReferencesCheck;
import org.eclipse.emf.ecore.EReference;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;

/**
 * A customized {@link ReferencesCheck} that realizes KGraph-specific customizations of the EMF
 * Compare diff process.<br>
 * <br>
 * This concrete {@link ReferencesCheck} excludes the checking of the
 * {@link de.cau.cs.kieler.core.kgraph.KNode#getIncomingEdges() KNode#getIncomingEdges} association
 * while deriving a diff model based on a given match model.
 * 
 * @author chsch
 */
public class KGraphReferencesCheck extends ReferencesCheck {

    /**
     * Simply delegates to the super constructor.
     * 
     * @param manager
     *            the IMatchManager instance to determine matches for certain <code>EObject</code>
     */
    public KGraphReferencesCheck(final IMatchManager manager) {
        super(manager);
    }

    /**
     * {@inheritDoc}
     */
    protected boolean shouldBeIgnored(final EReference reference) {
        boolean res = reference.equals(KGraphPackage.eINSTANCE.getKNode_IncomingEdges());
        return res ? res : super.shouldBeIgnored(reference);
    }
}
