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

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Condition for testing if a given label is a center edge label.
 * 
 * @author ybl
 */
public class CenterEdgeLabelCondition implements Predicate<KLabel> {

    /**
     * {@inheritDoc}
     */
    public boolean apply(final KLabel klabel) {
        if (klabel.eContainer() instanceof KEdge) {
            KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
            return labelLayout.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT)
                    == EdgeLabelPlacement.CENTER;
        }
        
        return false;
    }

}
