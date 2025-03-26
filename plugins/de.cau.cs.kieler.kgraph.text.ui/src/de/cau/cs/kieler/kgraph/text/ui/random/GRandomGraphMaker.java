/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016-2025 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.kgraph.text.ui.random;

import de.cau.cs.kieler.kgraph.text.grandom.Configuration;
import de.cau.cs.kieler.kgraph.text.grandom.ConstraintType;
import de.cau.cs.kieler.kgraph.text.grandom.DoubleQuantity;
import de.cau.cs.kieler.kgraph.text.grandom.Edges;
import de.cau.cs.kieler.kgraph.text.grandom.Flow;
import de.cau.cs.kieler.kgraph.text.grandom.FlowType;
import de.cau.cs.kieler.kgraph.text.grandom.Formats;
import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions;
import de.cau.cs.kieler.kgraph.text.grandom.Hierarchy;
import de.cau.cs.kieler.kgraph.text.grandom.Nodes;
import de.cau.cs.kieler.kgraph.text.grandom.Ports;
import de.cau.cs.kieler.kgraph.text.grandom.RandGraph;
import de.cau.cs.kieler.kgraph.text.grandom.RandomGraphGenerator;
import de.cau.cs.kieler.kgraph.text.grandom.Side;
import de.cau.cs.kieler.kgraph.text.grandom.Size;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;

public class GRandomGraphMaker {

    private final Iterable<Configuration> configs;

    private final int DEFAULT_NUM_GRAPHS = 1;

    private final String DEFAULT_NAME = "random";

    private final String DEFAULT_FORMAT = Formats.KGT.toString();

    public GRandomGraphMaker(final RandGraph rdg) {
        this.configs = rdg.getConfigs();
    }

    public void gen(final IProject project) {
        try {
            for (final Configuration config : this.configs) {
                final String filename =
                        this.exists(config.getFilename()) ? config.getFilename() : DEFAULT_NAME;
                final Object format =
                        this.exists(config.getFormat()) ? config.getFormat() : DEFAULT_FORMAT;
                final List<KNode> graphs = this.genKNode(config);
                int fileNum = 0;
                for (int i = 0; (i < graphs.size()); i++) {

                    while (project.getFile((((filename + Integer.valueOf(fileNum)) + ".") + format))
                            .exists()) {
                        fileNum++;
                    }
                    final IFile f = project
                            .getFile((((filename + Integer.valueOf(fileNum)) + ".") + format));
                    this.serialize(graphs.get(i), f);

                }
            }
        } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
        }
    }

    public List<KNode> genKNode(final Configuration config) {
        final ArrayList<KNode> generated = new ArrayList<KNode>();
        final int samples = config.getSamples();
        final int nGraphs = this.exists(Integer.valueOf(samples)) ? samples : DEFAULT_NUM_GRAPHS;
        final Random r = this.random(config);
        ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, nGraphs, true);
        for (final Integer i : _doubleDotLessThan) {

            final GeneratorOptions options = this.parseGenOptions(config, r);
            final KNode n = new RandomGraphGenerator(r).generate(options);
            generated.add(n);

        }
        return generated;
    }

    public GeneratorOptions parseGenOptions(final Configuration config, final Random r) {
        final GeneratorOptions genOpt = new GeneratorOptions();
        if (this.exists(GeneratorOptionsUtil.getPreferenceStore())) {
            GeneratorOptionsUtil.loadPreferences(genOpt);
        }
        this.setGraphType(config, genOpt);
        this.nodes(config.getNodes(), r, genOpt);
        this.edges(config, genOpt, r);
        this.hierarchy(config, genOpt, r);
        this.setQuantities(genOpt, config.getFraction(), GeneratorOptions.PARTITION_FRAC);
        this.<Integer> setIfExists(genOpt, config.getMaxWidth(), GeneratorOptions.MAX_WIDTH);
        this.<Integer> setIfExists(genOpt, config.getMaxDegree(), GeneratorOptions.MAX_DEGREE);
        return genOpt;
    }

    public void hierarchy(final Configuration configuration, final GeneratorOptions options,
            final Random random) {
        final Hierarchy hierarchy = configuration.getHierarchy();
        if (this.exists(hierarchy)) {
            final DoubleQuantity hierarch = hierarchy.getNumHierarchNodes();
            this.setQuantities(options, hierarchy.getCrossHierarchRel(),
                    GeneratorOptions.EXACT_RELATIVE_HIER);
            options.<Boolean> setProperty(GeneratorOptions.SMALL_HIERARCHY,
                    Boolean.valueOf(this.exists(hierarch)));
            this.setQuantities(options, hierarchy.getLevels(),
                    GeneratorOptions.MAX_HIERARCHY_LEVEL);
            this.setQuantities(options, hierarch, GeneratorOptions.NUMBER_HIERARCHICAL_NODES);
            this.setQuantities(options, hierarchy.getEdges(), GeneratorOptions.CROSS_HIER);
        }
    }

    private Random random(final Configuration g) {
        final Integer seed = g.getSeed();
        if ((seed == null)) {
            return new Random();
        } else {
            return new Random((seed).intValue());
        }
    }

    private void edges(final Configuration config, final GeneratorOptions genOpt, final Random r) {
        final Edges edges = config.getEdges();
        if (this.exists(edges)) {
            if (edges.isTotal()) {
                genOpt.<GeneratorOptions.EdgeDetermination> setProperty(
                        GeneratorOptions.EDGE_DETERMINATION,
                        GeneratorOptions.EdgeDetermination.ABSOLUTE);
                this.setQuantities(genOpt, edges.getNEdges(), GeneratorOptions.EDGES_ABSOLUTE);
            } else if (edges.isDensity()) {
                this.setQuantities(genOpt, edges.getNEdges(), GeneratorOptions.DENSITY);
                genOpt.<GeneratorOptions.EdgeDetermination> setProperty(
                        GeneratorOptions.EDGE_DETERMINATION,
                        GeneratorOptions.EdgeDetermination.DENSITY);
            } else if (edges.isRelative()) {
                genOpt.<GeneratorOptions.EdgeDetermination> setProperty(
                        GeneratorOptions.EDGE_DETERMINATION,
                        GeneratorOptions.EdgeDetermination.RELATIVE);
                this.setQuantities(genOpt, edges.getNEdges(), GeneratorOptions.RELATIVE_EDGES);
            } else {
                genOpt.<GeneratorOptions.EdgeDetermination> setProperty(
                        GeneratorOptions.EDGE_DETERMINATION,
                        GeneratorOptions.EdgeDetermination.OUTGOING);
                this.setQuantities(genOpt, edges.getNEdges(), GeneratorOptions.OUTGOING_EDGES);
            }

            genOpt.<Boolean> setProperty(GeneratorOptions.EDGE_LABELS,
                    Boolean.valueOf(edges.isLabels()));
            genOpt.<Boolean> setProperty(GeneratorOptions.SELF_LOOPS,
                    Boolean.valueOf(edges.isSelfLoops()));
        }
    }

    private void nodes(final Nodes nodes, final Random r, final GeneratorOptions genOpt) {
        if (this.exists(nodes)) {
            this.setQuantities(genOpt, nodes.getNNodes(), GeneratorOptions.NUMBER_OF_NODES);
            genOpt.<Boolean> setProperty(GeneratorOptions.CREATE_NODE_LABELS,
                    Boolean.valueOf(nodes.isLabels()));
            this.ports(nodes, genOpt, r);
            this.size(nodes, r, genOpt);
            boolean _isRemoveIsolated = nodes.isRemoveIsolated();
            boolean _not = (!_isRemoveIsolated);
            genOpt.<Boolean> setProperty(GeneratorOptions.ISOLATED_NODES, Boolean.valueOf(_not));
        }
    }

    private void size(final Nodes nodes, final Random r, final GeneratorOptions genOpt) {
        final Size size = nodes.getSize();
        if (this.exists(size)) {
            this.setQuantities(genOpt, size.getWidth(), GeneratorOptions.NODE_WIDTH);
            this.setQuantities(genOpt, size.getHeight(), GeneratorOptions.NODE_HEIGHT);
        }
    }

    private void ports(final Nodes nodes, final GeneratorOptions genOpt,
            final Random r) {
        final Ports ports = nodes.getPorts();
        if (this.exists(ports)) {
            genOpt.<Boolean> setProperty(GeneratorOptions.ENABLE_PORTS, Boolean.valueOf(true));
            genOpt.<Boolean> setProperty(GeneratorOptions.CREATE_PORT_LABELS,
                    Boolean.valueOf(ports.isLabels()));
            genOpt.<PortConstraints> setProperty(GeneratorOptions.PORT_CONSTRAINTS,
                    this.getConstraint(ports.getConstraint()));
            this.setQuantities(genOpt, ports.getReUse(),
                    GeneratorOptions.USE_EXISTING_PORTS_CHANCE);
            final EList<Flow> flows = ports.getFlow();
            if (this.exists(flows)) {
                for (final Flow f : flows) {
                    this.setFlowSide(genOpt, f.getFlowType(), f.getSide(), f.getAmount(), r);
                }
            }
            final Size size = ports.getSize();
            genOpt.<Boolean> setProperty(GeneratorOptions.SET_PORT_SIZE, Boolean.valueOf(true));
            if (this.exists(size)) {
                {
                    this.setQuantities(genOpt, size.getHeight(), GeneratorOptions.PORT_HEIGHT);
                    this.setQuantities(genOpt, size.getWidth(), GeneratorOptions.PORT_WIDTH);
                }
            }

        }

    }

    private void setQuantities(final GeneratorOptions genOpt, final DoubleQuantity quant,
            final IProperty<GeneratorOptions.RandVal> randomValue) {
        if (this.exists(quant)) {
            genOpt.<GeneratorOptions.RandVal> setProperty(randomValue, this.toRandVal(quant));
        }
    }

    public GeneratorOptions.RandVal toRandVal(final DoubleQuantity quant) {
        if (quant.isMinMax()) {
            return GeneratorOptions.RandVal.minMax((quant.getMin()).doubleValue(),
                    (quant.getMax()).doubleValue());
        } else if (quant.isGaussian()) {
            return GeneratorOptions.RandVal.gaussian((quant.getMean()).doubleValue(),
                    (quant.getStddv()).doubleValue());
        } else {
            return GeneratorOptions.RandVal.exact((quant.getQuant()).doubleValue());
        }

    }

    private void setFlowSide(final GeneratorOptions options, final FlowType type, final Side side,
            final DoubleQuantity quant, final Random r) {

        final int amount = this.toRandVal(quant).intVal(r);
        switch (type) {
        case INCOMING:
            switch (side) {
            case EAST:
                options.<Integer> setProperty(GeneratorOptions.INCOMING_EAST_SIDE,
                        Integer.valueOf(amount));
                break;
            case NORTH:
                options.<Integer> setProperty(GeneratorOptions.INCOMING_NORTH_SIDE,
                        Integer.valueOf(amount));
                break;
            case SOUTH:
                options.<Integer> setProperty(GeneratorOptions.INCOMING_SOUTH_SIDE,
                        Integer.valueOf(amount));
                break;
            case WEST:
                options.<Integer> setProperty(GeneratorOptions.INCOMING_WEST_SIDE,
                        Integer.valueOf(amount));
                break;
            default:
                break;
            }
            break;
        case OUTGOING:
            switch (side) {
            case EAST:
                options.<Integer> setProperty(GeneratorOptions.OUTGOING_EAST_SIDE,
                        Integer.valueOf(amount));
                break;
            case NORTH:
                options.<Integer> setProperty(GeneratorOptions.OUTGOING_NORTH_SIDE,
                        Integer.valueOf(amount));
                break;
            case SOUTH:
                options.<Integer> setProperty(GeneratorOptions.OUTGOING_SOUTH_SIDE,
                        Integer.valueOf(amount));
                break;
            case WEST:
                options.<Integer> setProperty(GeneratorOptions.OUTGOING_WEST_SIDE,
                        Integer.valueOf(amount));
                break;
            default:
                break;
            }
            break;
        default:
            break;
        }

    }

    private PortConstraints getConstraint(final ConstraintType constraint) {
        if (constraint != null) {
            switch (constraint) {
            case FREE:
                return PortConstraints.FREE;
            case ORDER:
                return PortConstraints.FIXED_ORDER;
            case POSITION:
                return PortConstraints.FIXED_POS;
            case SIDE:
                return PortConstraints.FIXED_SIDE;
            case RATIO:
                return PortConstraints.FIXED_RATIO;
            default:
                break;
            }
        }
        return PortConstraints.UNDEFINED;
    }

    private void setGraphType(final Configuration configuration, final GeneratorOptions options) {
        switch (configuration.getForm()) {
        case ACYCLIC:
            options.<GeneratorOptions.GraphType> setProperty(GeneratorOptions.GRAPH_TYPE,
                    GeneratorOptions.GraphType.ACYCLIC_NO_TRANSITIVE_EDGES);
            break;
        case BICONNECTED:
            options.<GeneratorOptions.GraphType> setProperty(GeneratorOptions.GRAPH_TYPE,
                    GeneratorOptions.GraphType.BICONNECTED);
            break;
        case BIPARTITE:
            options.<GeneratorOptions.GraphType> setProperty(GeneratorOptions.GRAPH_TYPE,
                    GeneratorOptions.GraphType.BIPARTITE);
            break;
        case CUSTOM:
            options.<GeneratorOptions.GraphType> setProperty(GeneratorOptions.GRAPH_TYPE,
                    GeneratorOptions.GraphType.CUSTOM);
            break;
        case TREES:
            options.<GeneratorOptions.GraphType> setProperty(GeneratorOptions.GRAPH_TYPE,
                    GeneratorOptions.GraphType.TREE);
            break;
        case TRICONNECTED:
            options.<GeneratorOptions.GraphType> setProperty(GeneratorOptions.GRAPH_TYPE,
                    GeneratorOptions.GraphType.TRICONNECTED);
            break;
        default:
            break;
        }

    }

    private void serialize(final KNode graph, final IFile file) throws IOException, CoreException {
        final ResourceSetImpl resourceSet = new ResourceSetImpl();
        final Resource resource =
                resourceSet.createResource(URI.createURI(file.getLocationURI().toString()));
        resource.getContents().add(graph);
        resource.save(Collections.EMPTY_MAP);
        file.refreshLocal(1, null);
    }

    private boolean exists(final Object o) {
        return (o != null);
    }

    private <T extends Object> boolean setIfExists(final GeneratorOptions genOpt, final T value,
            final IProperty<T> property) {
        if (this.exists(value)) {
            genOpt.<T> setProperty(property, value);
            return true;
        }
        return false;
    }
}
