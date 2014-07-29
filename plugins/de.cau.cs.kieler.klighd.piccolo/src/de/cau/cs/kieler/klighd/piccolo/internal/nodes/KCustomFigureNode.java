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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.core.krendering.KCustomRendering;
import de.cau.cs.kieler.klighd.piccolo.internal.util.Styles;

/**
 * Abstract class of all Piccolo2D nodes that are supposed to wrap figures of other 2D
 * drawing frameworks.
 * 
 * @author chsch
 */
public abstract class KCustomFigureNode extends KlighdNode.KlighdFigureNode<KCustomRendering> {

    private static final long serialVersionUID = -7601315964455163260L;

    /**
     * Applies the settings of {@link de.cau.cs.kieler.core.krendering.KStyle KStyle} attached to
     * respective {@link de.cau.cs.kieler.core.krendering.KCustomRendering KCustomRenderings} to the
     * actual custom figure wrapped by <code>this</code> node.<br>
     * <br>
     * Subclasses have to implement this method.
     * 
     * @param styles
     *            the record of {@link de.cau.cs.kieler.core.krendering.KStyle KStyles} to be
     *            applied to the actual custom figure
     */
    public abstract void applyStyles(final Styles styles);
}
