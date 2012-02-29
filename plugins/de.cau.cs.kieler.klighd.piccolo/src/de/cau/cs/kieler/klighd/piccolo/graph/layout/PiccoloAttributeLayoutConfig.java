/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.graph.layout;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.ILayoutData;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import edu.umd.cs.piccolo.PNode;

/**
 * A layout configuration which derives layout options from attributes attached to Piccolo nodes.
 * 
 * @author mri
 */
public class PiccoloAttributeLayoutConfig implements ILayoutConfig {

    /** the priority for the Piccolo attribute layout configuration. */
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
        if (diagramPart instanceof PNode) {
            return getValue(optionData, (PNode) diagramPart);
        }
        return null;
    }
    
    /**
     * Parses the option value for the option specified by the given layout option data from the
     * attributes of the Piccolo node.
     * 
     * @param optionData
     *            the layout option data
     * @param node
     *            the Piccolo node
     * @return the value or null if no value for the option is stored in the attributes
     */
    private Object getValue(final LayoutOptionData<?> optionData, final PNode node) {
        String optionId = optionData.getId();
        // check if there is an attribute with a key equaling the full id
        Object value = node.getAttribute(optionId);
        if (value == null) {
            // check if there is an attribute with a key equaling the last segment of the id
            int dotIndex = optionData.getId().lastIndexOf('.');
            if (dotIndex >= 0) {
                value = node.getAttribute(optionId.substring(dotIndex + 1));
            }
        }
        if (value != null) {
            if (value instanceof String) {
                String s = (String) value;
                if (optionData.equals(LayoutOptions.ALGORITHM)) {
                    ILayoutData layoutData = getAlgorithmData(s);
                    if (layoutData != null) {
                        return layoutData.getId();
                    }
                } else {
                    return optionData.parseValue(s);
                }
            } else if (value instanceof Boolean && optionData.getType() == Type.BOOLEAN) {
                return (Boolean) value;
            } else if (value instanceof Integer && optionData.getType() == Type.INT) {
                return (Integer) value;
            } else if (value instanceof Float && optionData.getType() == Type.FLOAT) {
                return (Float) value;
            } else if (value instanceof Double && optionData.getType() == Type.FLOAT) {
                return ((Double) value).floatValue();
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void transferValues(final KGraphData graphData, final LayoutContext context) {
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (diagramPart instanceof PNode) {
            PNode node = (PNode) diagramPart;
            LayoutDataService layoutDataService = LayoutDataService.getInstance();
            IProperty<?>[] affectedOptions = getAffectedOptions(node);
            if (affectedOptions != null) {
                for (IProperty<?> property : affectedOptions) {
                    LayoutOptionData<?> optionData;
                    if (property instanceof LayoutOptionData<?>) {
                        optionData = (LayoutOptionData<?>) property;
                    } else {
                        optionData = layoutDataService.getOptionData(property.getId());
                    }
                    Object value = getValue(optionData, node);
                    graphData.setProperty(property, value);
                }
            }
        }
    }
    
    /**
     * Returns the options which are affected by this layout configuration derived from the given
     * Piccolo node.<br><br>
     * 
     * chsch: set this and dependent method/field 'public static' for use in
     * {@link PiccoloDiagramLayoutManager}. Why is the list transformed into an array?
     * 
     * @param node
     *            the node
     * @return the options
     */
    @SuppressWarnings("unchecked")
    public static IProperty<?>[] getAffectedOptions(final PNode node) {
        List<LayoutOptionData<?>> data = new LinkedList<LayoutOptionData<?>>();
        Enumeration<Object> keyEnumeration = node.getClientPropertyKeysEnumeration();
        while (keyEnumeration.hasMoreElements()) {
            Object key = keyEnumeration.nextElement();
            if (key instanceof String) {
                LayoutOptionData<?> option = getOptionData((String) key);
                if (option != null) {
                    data.add(option);
                }
            }
        }
        return data.toArray(new IProperty<?>[data.size()]);
    }

    /** a caching map for layout option data. */
    private static Map<String, LayoutOptionData<?>> optionDataMap;

    /**
     * Return a layout option data for the given identifier.<br>
     * This method has been copied from {@code AnnotationsLayoutConfig}.
     * TODO maybe place this in a utility class
     * 
     * @param id
     *            an identifier
     * @return the corresponding option data
     */
    private static LayoutOptionData<?> getOptionData(final String id) {
        if (optionDataMap == null) {
            optionDataMap = new HashMap<String, LayoutOptionData<?>>();
            LayoutDataService layoutServices = LayoutDataService.getInstance();
            for (LayoutOptionData<?> optionData : layoutServices.getOptionData()) {
                String optionId = optionData.getId();
                int dotIndex = optionId.lastIndexOf('.');
                if (dotIndex >= 0) {
                    optionDataMap.put(optionId.substring(dotIndex + 1), optionData);
                }
                optionDataMap.put(optionId, optionData);
            }
        }
        return optionDataMap.get(id);
    }

    /** a caching map for layout algorithm data. */
    private Map<String, ILayoutData> algorithmDataMap;

    /**
     * Return a layout algorithm data for the given identifier.<br>
     * This method has been copied from {@code AnnotationsLayoutConfig}.
     * TODO maybe place this in a utility class
     * 
     * @param id
     *            an identifier
     * @return the corresponding algorithm data
     */
    private ILayoutData getAlgorithmData(final String id) {
        if (algorithmDataMap == null) {
            algorithmDataMap = new HashMap<String, ILayoutData>();
            LayoutDataService layoutServices = LayoutDataService.getInstance();
            // add layout type data to the cache
            for (LayoutTypeData typeData : layoutServices.getTypeData()) {
                String typeId = typeData.getId();
                int dotIndex = typeId.lastIndexOf('.');
                if (dotIndex >= 0) {
                    algorithmDataMap.put(typeId.substring(dotIndex + 1), typeData);
                }
                algorithmDataMap.put(typeId, typeData);
            }
            // add layout algorithm data to the cache
            for (LayoutAlgorithmData algorithmData : layoutServices.getAlgorithmData()) {
                String algoId = algorithmData.getId();
                int dotIndex = algoId.lastIndexOf('.');
                if (dotIndex >= 0) {
                    algorithmDataMap.put(algoId.substring(dotIndex + 1), algorithmData);
                }
                algorithmDataMap.put(algoId, algorithmData);
            }
        }
        return algorithmDataMap.get(id);
    }

}
