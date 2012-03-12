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

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * A layout configuration which derives layout options from properties attached to layout data of
 * graph elements.
 * 
 * @author mri
 */
public class KGraphPropertyLayoutConfig implements ILayoutConfig {

    /** the priority for the property layout layout configuration. */
    public static final int PRIORITY = 20;

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }

    /**
     * {@inheritDoc}
     */
    public void enrich(final LayoutContext context) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (diagramPart instanceof KGraphElement) {
            KGraphElement element = (KGraphElement) diagramPart;
            KShapeLayout shapeLayout = element.getData(KShapeLayout.class);
            if (shapeLayout != null) {
                return shapeLayout.getProperty(optionData);
            } else {
                KEdgeLayout edgeLayout = element.getData(KEdgeLayout.class);
                if (edgeLayout != null) {
                    return edgeLayout.getProperty(optionData);
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void transferValues(final KGraphData graphData, final LayoutContext context) {
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (diagramPart instanceof KGraphElement) {
            KGraphElement element = (KGraphElement) diagramPart;
            KShapeLayout shapeLayout = element.getData(KShapeLayout.class);
            if (shapeLayout != null) {
                graphData.copyProperties(shapeLayout);
            } else {
                KEdgeLayout edgeLayout = element.getData(KEdgeLayout.class);
                if (edgeLayout != null) {
                    graphData.copyProperties(edgeLayout);
                }
            }
        }
    }

}
