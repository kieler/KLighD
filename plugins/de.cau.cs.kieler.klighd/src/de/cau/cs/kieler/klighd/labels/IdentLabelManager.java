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

import de.cau.cs.kieler.core.kgraph.KLabel;

/**
 * Label manager that leaves the label text untouched.
 * 
 * @author ybl
 */
public class IdentLabelManager extends AbstractKlighdLabelManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public String resizeLabel(final KLabel label, final double targetWidth) {
        return null;
    }

}
