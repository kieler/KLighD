/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.krendering;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.transformations.AbstractTransformation;
import edu.umd.cs.piccolo.PNode;

/**
 * The transformation from a KGraph with attached KRendering data to a Piccolo scene graph
 * structure.
 * 
 * @author mri
 */
public class KRendering2PNodeTransformation extends AbstractTransformation<KNode, PNode> {

    /**
     * {@inheritDoc}
     */
    public PNode transform(final KNode model,
            final TransformationContext<KNode, PNode> transformationContext) {
        KNodeTopNode topNode = new KNodeTopNode(model);
        topNode.expand();
        return topNode;
    }

}
