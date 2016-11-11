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

import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.compare.diff.IDiffProcessor;

/**
 * Equals the {@link DefaultDiffEngine} except it uses the {@link KFeatureFilter}.
 * 
 * @author csp
 * 
 */
public class KDiffEngine extends DefaultDiffEngine {

    /**
     * Create the diff engine.
     * 
     * @param processor
     *            this instance will be called for each detected difference.
     */
    public KDiffEngine(IDiffProcessor processor) {
        super(processor);
    }

    @Override
    protected FeatureFilter createFeatureFilter() {
        return new KFeatureFilter();
    }

}
