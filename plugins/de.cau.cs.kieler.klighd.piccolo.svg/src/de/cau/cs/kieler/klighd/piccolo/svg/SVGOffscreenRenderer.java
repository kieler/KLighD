/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.svg;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.klighd.IOffscreenRenderer;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;

/**
 * An {@link IOffscreenRenderer} producing SVG diagrams.
 * 
 * @author chsch
 */
public class SVGOffscreenRenderer implements IOffscreenRenderer {

    /**
     * {@inheritDoc}
     */
    public String render(final ViewContext viewContext, final IPropertyHolder properties) {
        final KlighdSVGCanvas canvas = new KlighdSVGCanvas(true);
        
        // create a controller for the graph
        // since the controller attaches the 'ACTIVE' and 'POPULATED' flags that are examined
        //  by the KlighdLayoutManager we need to do this before arranging the diagram
        final DiagramController c =
                new DiagramController(viewContext.getViewModel(), canvas.getCamera(), true);

        if (properties == null) {
            // layout the diagram
            DiagramLayoutEngine.INSTANCE.layout(null, viewContext);
            
        } else {
            // expand the desired elements... 
            for (Object o : properties.getProperty(IOffscreenRenderer.EXPANDED_ELEMENTS)) {
                final KNode node = viewContext.getTargetElement(o, KNode.class);
                if (node != null) {
                    c.expand(node);
                }
            }
            
            // and collapse the desired elements, respectively
            for (Object o : properties.getProperty(IOffscreenRenderer.COLLAPSED_ELEMENTS)) {
                final KNode node = viewContext.getTargetElement(o, KNode.class);
                if (node != null) {
                    c.collapse(node);
                }
            }
            
            if (!properties.getProperty(IOffscreenRenderer.NO_LAYOUT)) {
                // layout the diagram
                DiagramLayoutEngine.INSTANCE.layout(null, viewContext);
            }
        }
        return canvas.setDiagramBounds(viewContext.getViewModel()).render();
    }
}
