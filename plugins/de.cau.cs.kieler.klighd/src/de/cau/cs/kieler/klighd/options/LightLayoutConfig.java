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
package de.cau.cs.kieler.klighd.options;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;

/**
 * Layout configurator for Light Diagrams. The configuration is derived from widgets created
 * by the {@link OptionControlFactory}.
 *
 * @author msp
 */
public class LightLayoutConfig implements ILayoutConfig {

    /** the fixed priority of the light layout configurator. */
    public static final int PRIORITY = 50;
    
    /** the map of layout options set for this configurator. */
    private final Map<LayoutOptionData<?>, Object> optionMap = Maps.newHashMap();
    
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
        // nothing to enrich
    }
    
    /**
     * Set the given layout option value.
     * 
     * @param optionData a layout option data instance
     * @param value the new value for the option
     */
    public void setOption(final LayoutOptionData<?> optionData, final Object value) {
        optionMap.put(optionData, value);
    }
    
    /**
     * Returns the stored value for the given layout option, if any.
     * 
     * @param optionData a layout option data instance
     * @return the current value for the option
     */
    public Object getOption(final LayoutOptionData<?> optionData) {
        return optionMap.get(optionData);
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        Object value = optionMap.get(optionData);
        if (value != null) {
            Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
            if (matchesTargetType(optionData, diagramPart)) {
                return value;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void transferValues(final KGraphData graphData, final LayoutContext context) {
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        for (Map.Entry<LayoutOptionData<?>, Object> entry : optionMap.entrySet()) {
            if (matchesTargetType(entry.getKey(), diagramPart)) {
                graphData.setProperty(entry.getKey(), entry.getValue());
            }
        }
    }
    
    /**
     * Check whether the given diagram part matches the target type of the layout option.
     * 
     * @param optionData a layout option data instance
     * @param diagramPart a graph element
     * @return true if the layout option can be applied to the graph element
     */
    private boolean matchesTargetType(final LayoutOptionData<?> optionData,
            final Object diagramPart) {
        Set<LayoutOptionData.Target> optionTargets = optionData.getTargets();
        if (diagramPart instanceof KNode) {
            if (optionTargets.contains(LayoutOptionData.Target.NODES)
                    || !((KNode) diagramPart).getChildren().isEmpty()
                    && optionTargets.contains(LayoutOptionData.Target.PARENTS)) {
                return true;
            }
        } else if (diagramPart instanceof KEdge) {
            if (optionTargets.contains(LayoutOptionData.Target.EDGES)) {
                return true;
            }
        } else if (diagramPart instanceof KPort) {
            if (optionTargets.contains(LayoutOptionData.Target.PORTS)) {
                return true;
            }
        } else if (diagramPart instanceof KLabel) {
            if (optionTargets.contains(LayoutOptionData.Target.LABELS)) {
                return true;
            }
        }
        return false;
    }

}
