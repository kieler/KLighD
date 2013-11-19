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
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KCustomFigureNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.Styles;

/**
 * A specialized {@link PNodeController} dedicated to instances of
 * {@link de.cau.cs.kieler.core.krendering.KCustomRendering KCustomRendering}.
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
        getNode().applyStyles(styles);
    }
}
