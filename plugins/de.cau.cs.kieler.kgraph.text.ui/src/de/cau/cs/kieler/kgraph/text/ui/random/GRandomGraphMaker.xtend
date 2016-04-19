/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.kgraph.text.ui.random

import de.cau.cs.kieler.kgraph.text.grandom.Configuration
import de.cau.cs.kieler.kgraph.text.grandom.ConstraintType
import de.cau.cs.kieler.kgraph.text.grandom.DoubleQuantity
import de.cau.cs.kieler.kgraph.text.grandom.FlowType
import de.cau.cs.kieler.kgraph.text.grandom.Formats
import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions
import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions.EdgeDetermination
import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions.GraphType
import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions.RandVal
import de.cau.cs.kieler.kgraph.text.grandom.Nodes
import de.cau.cs.kieler.kgraph.text.grandom.RandGraph
import de.cau.cs.kieler.kgraph.text.grandom.RandomGraphGenerator
import de.cau.cs.kieler.kgraph.text.grandom.Side
import java.io.IOException
import java.util.ArrayList
import java.util.Collections
import java.util.List
import java.util.Random
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.elk.core.options.PortConstraints
import org.eclipse.elk.graph.KNode
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl

import static de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions.*
import org.eclipse.core.runtime.CoreException
import de.cau.cs.kieler.kgraph.text.ui.random.GeneratorOptionsUtil

class GRandomGraphMaker {

    val Iterable<Configuration> configs
    val DEFAULT_NUM_GRAPHS = 1
    val DEFAULT_NAME = "random"
    val DEFAULT_FORMAT = Formats.KGT.toString

    new(RandGraph rdg) {
        configs = rdg.configs
    }

    def gen(IProject project) {
        for (config : configs) {
            val filename = if(config.filename.exists) config.filename else DEFAULT_NAME
            val format = if(config.format.exists) config.format else DEFAULT_FORMAT
            val graphs = genKNode(config)
            var fileNum = 0
            for (var i = 0; i < graphs.size; i++) {
                while (project.getFile(filename + fileNum + '.' + format).exists) {
                    fileNum++
                }
                val f = project.getFile(filename + fileNum + '.' + format)
                serialize(graphs.get(i), f)
            }
        }
    }

    def List<KNode> genKNode(Configuration config) {
        val generated = new ArrayList
        val samples = config.samples
        val nGraphs = if(samples.exists) samples else DEFAULT_NUM_GRAPHS
        val r = random(config)
        for (i : 0 ..< nGraphs) {
            val options = parseGenOptions(config, r)
            val n = new RandomGraphGenerator(r).generate(options)
            generated.add(n)
        }
        return generated
    }

    def parseGenOptions(Configuration config, Random r) {
        val GeneratorOptions genOpt = new GeneratorOptions()
        if (GeneratorOptionsUtil.getPreferenceStore().exists)
            GeneratorOptionsUtil.loadPreferences(genOpt)
        setGraphType(config, genOpt)
        nodes(config.nodes, r, genOpt)
        edges(config, genOpt, r)
        hierarchy(config, genOpt, r)
       
        setQuantities(genOpt, config.fraction, PARTITION_FRAC)
        genOpt.setIfExists(config.maxWidth, MAX_WIDTH)
        genOpt.setIfExists(config.maxDegree, MAX_DEGREE)

        genOpt
    }

    def hierarchy(Configuration configuration, GeneratorOptions options, Random random) {
        val hierarchy = configuration.hierarchy
        if (hierarchy.exists){
            val hierarch = hierarchy.numHierarchNodes
            setQuantities(options, hierarchy.crossHierarchRel, EXACT_RELATIVE_HIER)
            options.setProperty(SMALL_HIERARCHY, hierarch.exists)
            setQuantities(options, hierarchy.levels, MAX_HIERARCHY_LEVEL)
            setQuantities(options, hierarch, NUMBER_HIERARCHICAL_NODES)
            setQuantities(options, hierarchy.edges, CROSS_HIER)
        }
    }

    private def random(Configuration g) {
        val seed = g.seed
        if (seed == null)
            return new Random()
        else
            return new Random(seed)
    }

    private def edges(Configuration config, GeneratorOptions genOpt, Random r) {
        val edges = config.edges
        if (edges.exists) {
            if (edges.total){
                genOpt.setProperty(EDGE_DETERMINATION, EdgeDetermination.ABSOLUTE)
                setQuantities(genOpt, edges.NEdges, EDGES_ABSOLUTE)
            } else if (edges.density) {
                setQuantities(genOpt, edges.NEdges, DENSITY)
                genOpt.setProperty(EDGE_DETERMINATION, EdgeDetermination.DENSITY)
            } else if (edges.relative) {
                genOpt.setProperty(EDGE_DETERMINATION, EdgeDetermination.RELATIVE)
                setQuantities(genOpt, edges.NEdges, RELATIVE_EDGES)
            } else {
                genOpt.setProperty(EDGE_DETERMINATION, EdgeDetermination.OUTGOING)
                genOpt.setQuantities(edges.NEdges, OUTGOING_EDGES)
            }
            genOpt.setProperty(EDGE_LABELS, edges.labels)
            genOpt.setProperty(SELF_LOOPS, edges.selfLoops)
        }
    }

    private def nodes(Nodes nodes, Random r, GeneratorOptions genOpt) {
        if (nodes.exists) {
            setQuantities(genOpt, nodes.NNodes, NUMBER_OF_NODES)
            genOpt.setProperty(CREATE_NODE_LABELS, nodes.labels)
            ports(nodes, genOpt, r)
            size(nodes, r, genOpt)
        }
    }

    private def size(Nodes nodes, Random r, GeneratorOptions genOpt) {
        val size = nodes.size
        if (size.exists) {
            setQuantities(genOpt, size.width, NODE_WIDTH)
            setQuantities(genOpt, size.height, NODE_HEIGHT)
        }
    }

    private def ports(Nodes nodes, GeneratorOptions genOpt, Random r) {
        val ports = nodes.ports
        if (ports.exists) {
            genOpt.setProperty(ENABLE_PORTS, true)
            genOpt.setProperty(CREATE_PORT_LABELS, ports.labels)
            genOpt.setProperty(PORT_CONSTRAINTS, getConstraint(ports.constraint))
            setQuantities(genOpt, ports.reUse, USE_EXISTING_PORTS_CHANCE)
            val flows = ports.flow
            if (flows.exists) {
                for (f : flows)
                    genOpt.setFlowSide(f.flowType, f.side, f.amount, r)
            }
            val size = ports.size
            genOpt.setProperty(GeneratorOptions.SET_PORT_SIZE, true)
            if (size.exists){
                setQuantities(genOpt, size.height, PORT_HEIGHT)
                setQuantities(genOpt, size.width, PORT_WIDTH)
            }
        }
    }


    private def setQuantities(GeneratorOptions genOpt, DoubleQuantity quant, IProperty<RandVal> randomValue) {
        if (quant.exists)
            genOpt.setProperty(randomValue, quant.toRandVal)
    }

    def RandVal toRandVal(DoubleQuantity quant) {
        if (quant.minMax) {
            RandVal.minMax(quant.min, quant.max)
        } else if (quant.gaussian) {
            RandVal.gaussian(quant.mean, quant.stddv)
        } else {
            RandVal.exact(quant.quant)
        }
    }

    private def setFlowSide(GeneratorOptions options, FlowType type, Side side, DoubleQuantity quant, Random r) {
        val amount = quant.toRandVal.intVal(r)
        switch (type) {
            case INCOMING: {
                switch (side) {
                    case EAST: {
                        options.setProperty(INCOMING_EAST_SIDE, amount)
                    }
                    case NORTH: {
                        options.setProperty(INCOMING_NORTH_SIDE, amount)
                    }
                    case SOUTH: {
                        options.setProperty(INCOMING_SOUTH_SIDE, amount)
                    }
                    case WEST: {
                        options.setProperty(INCOMING_WEST_SIDE, amount)
                    }
                }
            }
            case OUTGOING: {
                switch (side) {
                    case EAST: {
                        options.setProperty(OUTGOING_EAST_SIDE, amount)
                    }
                    case NORTH: {
                        options.setProperty(OUTGOING_NORTH_SIDE, amount)
                    }
                    case SOUTH: {
                        options.setProperty(OUTGOING_SOUTH_SIDE, amount)
                    }
                    case WEST: {
                        options.setProperty(OUTGOING_WEST_SIDE, amount)
                    }
                }
            }
        }
    }

    private def getConstraint(ConstraintType constraint) {
        switch (constraint) {
            case FREE: {
                return PortConstraints.FREE
            }
            case ORDER: {
                return PortConstraints.FIXED_ORDER
            }
            case POSITION: {
                return PortConstraints.FIXED_POS
            }
            case SIDE: {
                return PortConstraints.FIXED_SIDE
            }
            case RATIO: {
                return PortConstraints.FIXED_RATIO
            }
        }
        return PortConstraints.UNDEFINED
    }


    private def void setGraphType(Configuration configuration, GeneratorOptions options) {
        switch (configuration.form) {
            case ACYCLIC: {
                options.setProperty(GRAPH_TYPE, GraphType.ACYCLIC_NO_TRANSITIVE_EDGES)
            }
            case BICONNECTED: {
                options.setProperty(GRAPH_TYPE, GraphType.BICONNECTED)
            }
            case BIPARTITE: {
                options.setProperty(GRAPH_TYPE, GraphType.BIPARTITE)
            }
            case CUSTOM: {
                options.setProperty(GRAPH_TYPE, GraphType.CUSTOM)
            }
            case TREES: {
                options.setProperty(GRAPH_TYPE, GraphType.TREE)
            }
            case TRICONNECTED: {
                options.setProperty(GRAPH_TYPE, GraphType.TRICONNECTED)
            }
        }
    }

    private def serialize(KNode graph, IFile file) throws IOException, CoreException {
        val resourceSet = new ResourceSetImpl();
        val resource = resourceSet.createResource(URI.createURI(file.getLocationURI().toString()));
        resource.getContents().add(graph);
        resource.save(Collections.EMPTY_MAP);
        file.refreshLocal(1, null);
    }

    private def exists(Object o) {
        return o != null
    }


    private def <T> setIfExists(GeneratorOptions genOpt, T value, IProperty<T> property) {
        if (value.exists){
            genOpt.setProperty(property, value)
            return true
        }
    }
}
