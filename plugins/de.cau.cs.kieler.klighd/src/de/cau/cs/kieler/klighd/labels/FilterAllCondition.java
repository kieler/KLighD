/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels;

import com.google.common.base.Predicate;

import de.cau.cs.kieler.core.kgraph.KLabel;

/**
 * A condition that never applies.
 * 
 * @author ybl
 */
public class FilterAllCondition implements Predicate<KLabel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean apply(final KLabel input) {
        return false;
    }

}
