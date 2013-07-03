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
package de.cau.cs.kieler.core.kgraph.text.ui.random;

import java.lang.reflect.Field;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kgraph.text.ui.internal.KGraphActivator;
import de.cau.cs.kieler.core.kgraph.text.ui.random.wizard.Messages;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.options.PortConstraints;

/**
 * Property holder for random graph generator options.
 *
 * @author msp
 */
public class GeneratorOptions extends MapPropertyHolder {

    /** the serial version UID. */
    private static final long serialVersionUID = -917483559995737504L;
    
    /** the possible graph types. */
    public static enum GraphType {
        /** any graph. */
        ANY,
        /** a tree. */
        TREE,
        /** a biconnected graph. */
        BICONNECTED,
        /** a triconnected graph. */
        TRICONNECTED,
        /** an acyclic graph without transitive edges. */
        ACYCLIC_NO_TRANSITIVE_EDGES
    }

    /** the possible ways to determine edges. */
    public enum EdgeDetermination {
        /** number of edges in the graph. */
        GRAPH_EDGES,
        /** number of outgoing edges per node. */
        OUTGOING_EDGES,
        /** relative number of edges (relative to n). */
        RELATIVE,
        /** density (relative to n^2). */
        DENSITY
    }
    
    //~~~~~~~~~~~~~~~~ Options for file creation
    
    /** the preference key for the number of graphs. */
    public static final IProperty<Integer> NUMBER_OF_GRAPHS = new Property<Integer>(
            "randomWizard.numberOfGraphs", 1);
    /** the preference key for the filename. */
    public static final IProperty<String> FILE_NAME = new Property<String>(
            "randomWizard.filename", "random.kgt");

    //~~~~~~~~~~~~~~~~ All other options depend on which GRAPH_TYPE has been selected

    /** option for the graph type. */
    public static final IProperty<GraphType> GRAPH_TYPE = new Property<GraphType>("basic.graphType",
            GraphType.ANY);

    //~~~~~~~~~~~~~~~~ Options for all graph types

    /** option for the number of nodes. */
    public static final IProperty<Integer> NUMBER_OF_NODES = new Property<Integer>(
            "basic.numberOfNodes", 10, 1);
    /** option for the minimum number of nodes. */
    public static final IProperty<Integer> NUMBER_OF_NODES_MIN = new Property<Integer>(
            "basic.numberOfNodesMin", 10, 1);
    /** option for the maximum number of nodes. */
    public static final IProperty<Integer> NUMBER_OF_NODES_MAX = new Property<Integer>(
            "basic.numberOfNodesMax", 10, 1);
    /** option that enables hierarchical graphs. */
    public static final IProperty<Boolean> ENABLE_HIERARCHY = new Property<Boolean>(
            "basic.enableHierarchy", false);
    /** option for the chance of creating a compound node. */
    public static final IProperty<Float> HIERARCHY_CHANCE = new Property<Float>(
            "basic.hierarchyChance", 0.05f, 0.0f, 1.0f);
    /** option for the maximum hierarchy level. */
    public static final IProperty<Integer> MAX_HIERARCHY_LEVEL = new Property<Integer>(
            "basic.maxHierarchyLevel", 3);
    /** option for the factor to calculate the number of nodes in a compound node. */
    public static final IProperty<Float> HIERARCHY_NODES_FACTOR = new Property<Float>(
            "basic.hierarchyNodesFactor", 0.5f, 0.0f);
    /** option for the chance of creating a hypernode. */
    public static final IProperty<Float> HYPERNODE_CHANCE = new Property<Float>(
            "basic.hypernodeChance", 0.0f, 0.0f, 1.0f);
    /** option for using ports to connect nodes. */
    public static final IProperty<Boolean> ENABLE_PORTS = new Property<Boolean>(
            "basic.ports", false);
    /** option for the chance of edges to use already existing ports. */
    public static final IProperty<Float> USE_EXISTING_PORTS_CHANCE = new Property<Float>(
            "basic.useExistingPortsChance", 0.3f);
    /** option for allowing cross-hierarchy edges. */
    public static final IProperty<Boolean> CROSS_HIERARCHY_EDGES = new Property<Boolean>(
            "basic.crossHierarchyEdges", false);
    /** option for using a time-based randomization seed. */
    public static final IProperty<Boolean> TIME_BASED_RANDOMIZATION = new Property<Boolean>(
            "basic.timeBasedRandomization", true);
    /** option for the fixed randomization seed value. */
    public static final IProperty<Integer> RANDOMIZATION_SEED = new Property<Integer>(
            "basic.randomizationSeed", 0);
    /** option for setting the size of nodes. */
    public static final IProperty<Boolean> SET_NODE_SIZE = new Property<Boolean>(
            "basic.setNodeSize", true);
    /** option for the minimal width of nodes. */
    public static final IProperty<Integer> MIN_NODE_WIDTH = new Property<Integer>(
            "basic.minNodeWidth", 30, 0);
    /** option for the maximal width of nodes. */
    public static final IProperty<Integer> MAX_NODE_WIDTH = new Property<Integer>(
            "basic.maxNodeWidth", 30, 0);
    /** option for the minimal height of nodes. */
    public static final IProperty<Integer> MIN_NODE_HEIGHT = new Property<Integer>(
            "basic.minNodeHeight", 30, 0);
    /** option for the maximal height of nodes. */
    public static final IProperty<Integer> MAX_NODE_HEIGHT = new Property<Integer>(
            "basic.maxNodeHeight", 30, 0);
    /** option for creating node labels. */
    public static final IProperty<Boolean> CREATE_NODE_LABELS = new Property<Boolean>(
            "basic.createNodeLabels", true);
    /** option for setting the size of ports. */
    public static final IProperty<Boolean> SET_PORT_SIZE = new Property<Boolean>(
            "basic.setPortSize", true);
    /** option for creating port labels. */
    public static final IProperty<Boolean> CREATE_PORT_LABELS = new Property<Boolean>(
            "basic.createPortLabels", false);

    //~~~~~~~~~~~~~~~~ Layout options

    /** option for port constraints. */
    public static final IProperty<PortConstraints> PORT_CONSTRAINTS = new Property<PortConstraints>(
            "layout.portConstraints", PortConstraints.UNDEFINED);
    /** option for relative probability of incoming edges on the north side. */
    public static final IProperty<Integer> INCOMING_NORTH_SIDE = new Property<Integer>(
            "layout.incomingNorthSide", 10);
    /** option for relative probability of incoming edges on the east side. */
    public static final IProperty<Integer> INCOMING_EAST_SIDE = new Property<Integer>(
            "layout.incomingEastSide", 5);
    /** option for relative probability of incoming edges on the south side. */
    public static final IProperty<Integer> INCOMING_SOUTH_SIDE = new Property<Integer>(
            "layout.incomingSouthSide", 10);
    /** option for relative probability of incoming edges on the west side. */
    public static final IProperty<Integer> INCOMING_WEST_SIDE = new Property<Integer>(
            "layout.incomingWestSide", 75);
    /** option for relative probability of outgoing edges on the north side. */
    public static final IProperty<Integer> OUTGOING_NORTH_SIDE = new Property<Integer>(
            "layout.outgoingNorthSide", 10);
    /** option for relative probability of outgoing edges on the east side. */
    public static final IProperty<Integer> OUTGOING_EAST_SIDE = new Property<Integer>(
            "layout.outgoingEastSide", 75);
    /** option for relative probability of outgoing edges on the south side. */
    public static final IProperty<Integer> OUTGOING_SOUTH_SIDE = new Property<Integer>(
            "layout.outgoingSouthSide", 10);
    /** option for relative probability of outgoing edges on the west side. */
    public static final IProperty<Integer> OUTGOING_WEST_SIDE = new Property<Integer>(
            "layout.outgoingWestSide", 5);
    
    //~~~~~~~~~~~~~~~~ Options for GRAPH_TYPE ANY

    /** option for specifying how to determine edges. */
    public static final IProperty<EdgeDetermination> EDGE_DETERMINATION =
            new Property<EdgeDetermination>("basic.edgeDetermination",
                    EdgeDetermination.GRAPH_EDGES);
    /** option for the variance in the number of edges. */
    public static final IProperty<Integer> EDGES_VARIANCE = new Property<Integer>(
            "basic.edgesVariance", 0, 0);
    /** option for the relative number of edges. */
    public static final IProperty<Double> EDGES_RELATIVE = new Property<Double>(
            "basic.relEdges", 1.5, 0.0);
    /** option for variance in the relative number of edges. */
    public static final IProperty<Double> EDGES_REL_VARIANCE = new Property<Double>(
            "basic.relEdgesVariance", 0.0, 0.0);
    /** option for graph density. */
    public static final IProperty<Double> DENSITY = new Property<Double>(
            "basic.density", 0.1, 0.0);
    /** option for variance in graph density. */
    public static final IProperty<Double> DENSITY_VARIANCE = new Property<Double>(
            "basic.densityVariance", 0.0, 0.0);
    /** option for the minimum number of outgoing edges. */
    public static final IProperty<Integer> MIN_OUTGOING_EDGES = new Property<Integer>(
            "basic.minOutgoingEdges", 0, 0);
    /** option for the maximum number of outgoing edges. */
    public static final IProperty<Integer> MAX_OUTGOING_EDGES = new Property<Integer>(
            "basic.maxOutgoingEdges", 0, 0);
    /** option for allowing self-loops. */
    public static final IProperty<Boolean> SELF_LOOPS = new Property<Boolean>(
            "basic.selfLoops", true);
    /** option for allowing multi-edges. */
    public static final IProperty<Boolean> MULTI_EDGES = new Property<Boolean>(
            "basic.multiEdges", true);
    /** option for allowing cycles. */
    public static final IProperty<Boolean> CYCLES = new Property<Boolean>(
            "basic.cycles", true);
    /** option for allowing isolated nodes. */
    public static final IProperty<Boolean> ISOLATED_NODES = new Property<Boolean>(
            "basic.isolatedNodes", true);

    //~~~~~~~~~~~~~~~~ Options for GRAPH_TYPE TREE

    /** option for the maximum tree width ('0' meaning unlimited). */
    public static final IProperty<Integer> MAX_WIDTH = new Property<Integer>("basic.maxWidth", 0);
    /** option for the maximum degree ('0' meaning unlimited). */
    public static final IProperty<Integer> MAX_DEGREE = new Property<Integer>("basic.maxDegree", 0);

    //~~~~~~~~~~~~~~~~ Options for GRAPH_TYPE ACYCLIC_NO_TRANSITIVE_EDGES

    /** option for planarity. */
    public static final IProperty<Boolean> PLANAR = new Property<Boolean>("basic.planar", false);

    //~~~~~~~~~~~~~~~~ Options for GRAPH_TYPE ANY, BICONNECTED and ACYCLIC_NO_TRANSITIVE_EDGES

    /** option for the number of edges. */
    public static final IProperty<Integer> NUMBER_OF_EDGES = new Property<Integer>(
            "basic.numberOfEdges", 20, 0);
    
    //~~~~~~~~~~~~~~~~  Utility methods for preference handling
    
    /**
     * Save all options that are stored in this property holder in the plugin preferences.
     */
    public void savePreferences() {
        IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
        for (Map.Entry<IProperty<?>, Object> entry : getAllProperties().entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Boolean) {
                preferenceStore.setValue(entry.getKey().getId(), (Boolean) value);
            } else if (value instanceof Integer) {
                preferenceStore.setValue(entry.getKey().getId(), (Integer) value);
            } else if (value instanceof Float) {
                preferenceStore.setValue(entry.getKey().getId(), (Float) value);
            } else if (value instanceof Double) {
                preferenceStore.setValue(entry.getKey().getId(), (Double) value);
            } else {
                preferenceStore.setValue(entry.getKey().getId(), value.toString());
            }
        }
    }
    
    /**
     * Load preferences for all options that are defined as fields of type {@link IProperty} in
     * this class. The property types are derived from their default values.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void loadPreferences() {
        try {
            IPreferenceStore preferenceStore = KGraphActivator.getInstance().getPreferenceStore();
            for (Field field : getClass().getFields()) {
                if (IProperty.class.isAssignableFrom(field.getType())) {
                    IProperty<?> option = (IProperty<?>) field.get(this);
                    Object value;
                    if (option.getDefault() instanceof Boolean) {
                        value = preferenceStore.getBoolean(option.getId());
                    } else if (option.getDefault() instanceof Integer) {
                        value = preferenceStore.getInt(option.getId());
                    } else if (option.getDefault() instanceof Float) {
                        value = preferenceStore.getFloat(option.getId());
                    } else if (option.getDefault() instanceof Double) {
                        value = preferenceStore.getDouble(option.getId());
                    } else if (option.getDefault() instanceof Enum) {
                        String serializedValue = preferenceStore.getString(option.getId());
                        try {
                            value = Enum.valueOf((Class<? extends Enum>) option.getDefault().getClass(),
                                    serializedValue);
                        } catch (IllegalArgumentException exception) {
                            value = null;
                        }
                    } else {
                        value = preferenceStore.getString(option.getId());
                    }
                    setProperty(option, value);
                    // check lower and upper bounds of the generator option
                    checkProperties(option);
                }
            }
        } catch (IllegalAccessException exception) {
            IStatus status = new Status(IStatus.ERROR,
                    KGraphActivator.DE_CAU_CS_KIELER_CORE_KGRAPH_TEXT_KGRAPH,
                    Messages.RandomGraphWizard_load_preferences_error, exception);
            StatusManager.getManager().handle(status);
        }
    }

}
