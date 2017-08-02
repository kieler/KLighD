/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.labels.inline;

import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.krendering.KContainerRendering;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KText;

/**
 * Implementors of this interface know how to provide text renderings for labels. The produced
 * renderings are not limited to a simple {@link KText} instance, but can instead be an arbitrarily
 * complex rendering hierarchy. The only requirement is that the hierarchy includes exactly one
 * {@link KText} somewhere, and that this instance cannot have a text set on it.
 * 
 * @author cds
 */
@FunctionalInterface
public interface ITextRenderingProvider {
    
    /**
     * Creates a rendering for the given label and adds it to the given container.The rendering is
     * returned to have its placement set up according to the decorators added to the container
     * previously.
     * 
     * @param container
     *            the container to add the rendering to.
     * @param label
     *            the label to create the rendering for.
     * @return rendering for the given label.
     */
    KRendering createTextRendering(KContainerRendering container, KLabel label);
    
}
