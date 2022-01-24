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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KCustomFigureNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.Styles;

/**
 * A specialized {@link PNodeController} dedicated to instances of
 * {@link de.cau.cs.kieler.klighd.krendering.KCustomRendering KCustomRendering}.
 * 
 * @author chsch
 */
public abstract class KCustomFigureController extends PNodeController<KCustomFigureNode> {

    /**
     * Constructs a node controller for a {@code PSWTAdvancedPath}.
     * 
     * @param node
     *            the path
     */
    public KCustomFigureController(final KCustomFigureNode node) {
        super(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyChanges(final Styles styles) {
        // since rotation is applicable to KCustomFigureNodes
        //  apply it the potentially available rotation style 
        
        if (styles.rotation != null) {
            this.setRotation(styles.rotation.getRotation(), styles.rotation.getRotationAnchor());
            styles.rotation = null;
        } else {
            this.setRotation(0, null);
        }

        // now let the concrete KCustomFigureNode apply the remaining styles
        getNode().applyStyles(styles);
    }
}
