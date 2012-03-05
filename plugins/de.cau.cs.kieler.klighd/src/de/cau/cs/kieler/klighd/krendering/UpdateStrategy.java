/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.krendering;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.IUpdateStrategy;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * The update strategy for KGraph models with attached KRendering data.
 * 
 * @author mri
 */
public class UpdateStrategy implements IUpdateStrategy<KNode> {

    /**
     * {@inheritDoc}
     */
    public KNode getInitialBaseModel(final ViewContext viewContext) {
        return KimlUtil.createInitializedNode();
    }

    /**
     * {@inheritDoc}
     */
    public KNode update(final KNode baseModel, final KNode newModel, final ViewContext viewContext) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getModelClass() {
        return KNode.class;
    }

}
