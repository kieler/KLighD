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

import org.eclipse.elk.core.math.ElkPadding;

import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.krendering.KContainerRendering;
import de.cau.cs.kieler.klighd.labels.inline.InlineLabelConfigurator.LayoutMode;

/**
 * Implementors of this interface create decorations for a label's rendering. Since decorations
 * may take up space around the edges of the label's container rendering, they indicate the amount
 * of space that needs to be left between the borders and the actual text rendering by returning
 * an appropriate {@link ElkPadding} instance.
 * 
 * @author cds
 */
@FunctionalInterface
public interface IDecoratorRenderingProvider {
    
    /**
     * Creates decorator renderings and adds them to the container. Returns the padding between the
     * label's text and the label's border that must be left for the text to be legible.
     * 
     * @param container
     *            the container rendering to add decorators to.
     * @param label
     *            the label for which to create decorators.
     * @param layoutMode
     *            the layout mode to assume. Paddings and the exact decorators created may change
     *            depending on the layout mode.
     * @return the necessary padding.
     */
    ElkPadding createDecoratorRendering(KContainerRendering container, KLabel label,
            LayoutMode layoutMode);
    
}
