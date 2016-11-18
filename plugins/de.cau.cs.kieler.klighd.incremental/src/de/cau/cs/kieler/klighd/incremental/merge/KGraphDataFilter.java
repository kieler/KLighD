/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.incremental.merge;

import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.util.RenderingContextData;

/**
 * Filters, which features should be in scope for matching.
 * @author csp
 *
 */
public class KGraphDataFilter implements Predicate<KGraphData> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(final KGraphData data) {
        if (data instanceof RenderingContextData) {
            return false;
        }
        return true;
    }
}
