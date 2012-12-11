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

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

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
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (diagramPart instanceof KGraphElement) {
            KGraphElement element = (KGraphElement) diagramPart;
            KGraphData elementLayout;
            if (element instanceof KEdge) {
                elementLayout = element.getData(KEdgeLayout.class);
            } else {
                elementLayout = element.getData(KShapeLayout.class);
            }
            
            if (context.getProperty(LayoutContext.GRAPH_ELEM) == null) {
                context.setProperty(LayoutContext.GRAPH_ELEM, element);
            }
            
            if (context.getProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS)) {
                // if not defined yet, check whether a hint for the layout algorithm has been set
                if (context.getProperty(DefaultLayoutConfig.CONTENT_HINT) == null
                        && elementLayout != null) {
                    String contentHint = elementLayout.getProperty(LayoutOptions.ALGORITHM);
                    if (contentHint != null) {
                        context.setProperty(DefaultLayoutConfig.CONTENT_HINT, contentHint);
                    }
                }
                
                // find the parent node for the selected graph element
                KNode parentNode = null;
                if (element instanceof KLabel) {
                    element = ((KLabel) element).getParent();
                }
                if (element instanceof KNode) {
                    parentNode = ((KNode) element).getParent();
                } else if (element instanceof KEdge) {
                    parentNode = ((KEdge) element).getSource().getParent();
                } else if (element instanceof KPort) {
                    parentNode = ((KPort) element).getNode().getParent();
                }
                if (parentNode != null) {
                    if (context.getProperty(DefaultLayoutConfig.CONTAINER_HINT) == null) {
                        String containerHint = parentNode.getData(KShapeLayout.class)
                                .getProperty(LayoutOptions.ALGORITHM);
                        if (containerHint != null) {
                            context.setProperty(DefaultLayoutConfig.CONTAINER_HINT, containerHint);
                        }
                    }
                    context.setProperty(LayoutContext.CONTAINER_DIAGRAM_PART, parentNode);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (diagramPart instanceof KGraphElement) {
            KGraphElement element = (KGraphElement) diagramPart;
            KGraphData elementLayout;
            if (element instanceof KEdge) {
                elementLayout = element.getData(KEdgeLayout.class);
            } else {
                elementLayout = element.getData(KShapeLayout.class);
            }
            if (elementLayout != null) {
                return elementLayout.getProperty(optionData);
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
            KGraphData elementLayout;
            if (element instanceof KEdge) {
                elementLayout = element.getData(KEdgeLayout.class);
            } else {
                elementLayout = element.getData(KShapeLayout.class);
            }
            if (elementLayout != null) {
                graphData.copyProperties(elementLayout);
            }
        }
    }

}
