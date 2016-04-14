/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kgraph.text.ui.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Random;

import org.eclipse.elk.core.klayoutdata.KIdentifier;
import org.eclipse.elk.core.klayoutdata.KLayoutDataFactory;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KLabel;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.KPort;

import com.google.common.collect.Lists;

/**
 * The random graph generator for KGraphs.
 * 
 * @author mri
 * @author msp
 */
public class RandomGraphGenerator {
    
    /** the fixed width of ports. */
    public static final float PORT_WIDTH = 4.0f;
    /** the fixed height of ports. */
    public static final float PORT_HEIGHT = 4.0f;
    /** minimal separation of ports. */
    public static final float PORT_SEPARATION = 7.0f;
    
    /** the maximal number of iterations for distributing edges. */
    private static final int MAX_ITER = 12;

    /** the generator options holder. */
    private GeneratorOptions options;
    /** the random number generator used to generate the graph. */
    private Random random;
    /** the counter used to generate node labels. */
    private int nodeLabelCounter;
    /** the counter used to generate port labels. */
    private int portLabelCounter;
    
    /**
     * Create a random graph generator with given random number generator.
     * 
     * @param random a random number generator
     */
    public RandomGraphGenerator(final Random random) {
        this.random = random;
    }

    /**
     * {@inheritDoc}
     */
    public KNode generate(final GeneratorOptions genOptions) {
        // reset the generator
        nodeLabelCounter = 0;
        portLabelCounter = 0;
        this.options = genOptions;
        if (!genOptions.getProperty(GeneratorOptions.ENABLE_HIERARCHY)) {
            genOptions.setProperty(GeneratorOptions.HIERARCHY_CHANCE, 0.0f);
            genOptions.setProperty(GeneratorOptions.CROSS_HIERARCHY_EDGES, false);
        }
        
        // initialize basic properties
        int minNodes = genOptions.getProperty(GeneratorOptions.NUMBER_OF_NODES_MIN);
        int maxNodes = genOptions.getProperty(GeneratorOptions.NUMBER_OF_NODES_MAX);
        int n = randomInt(minNodes, maxNodes);
        
        int minOut = genOptions.getProperty(GeneratorOptions.MIN_OUTGOING_EDGES);
        int maxOut = genOptions.getProperty(GeneratorOptions.MAX_OUTGOING_EDGES);
        int m;
        switch (genOptions.getProperty(GeneratorOptions.EDGE_DETERMINATION)) {
        case ABSOLUTE: {
            m = genOptions.getProperty(GeneratorOptions.EDGES_ABSOLUTE);
            int var = genOptions.getProperty(GeneratorOptions.EDGES_ABS_STDDEV);
            if (var > 0) {
                m += Math.round(random.nextGaussian() * var);
            }
            if (m < 0) {
                m = 0;
            }
            break;
        }
        
        case RELATIVE: {
            double rel = genOptions.getProperty(GeneratorOptions.EDGES_RELATIVE);
            double var = genOptions.getProperty(GeneratorOptions.EDGES_REL_STDDEV);
            if (var > 0) {
                rel += random.nextGaussian() * var;
            }
            m = (int) Math.round(rel * n);
            if (m < 0) {
                m = 0;
            }
            break;
        }
        
        case DENSITY: {
            double d = genOptions.getProperty(GeneratorOptions.DENSITY);
            double var = genOptions.getProperty(GeneratorOptions.DENSITY_STDDEV);
            if (var > 0) {
                d += random.nextGaussian() * var;
            }
            m = (int) (Math.round(d * n * (n - 1) / 2));
            if (m < 0) {
                m = 0;
            }
            break;
        }
        
        case OUTGOING: {
            double edgesPerNode = minOut + random.nextDouble() * (maxOut - minOut);
            m = (int) (Math.round(n * edgesPerNode));
            break;
        }
        
        default:
            throw new IllegalArgumentException("Selected edge determination is not supported.");
        }
        
        // generate the graph
        KNode graph = ElkUtil.createInitializedNode();
        
        switch (genOptions.getProperty(GeneratorOptions.GRAPH_TYPE)) {
        case CUSTOM: {
            List<KNode> generatedNodes;
            
            switch (genOptions.getProperty(GeneratorOptions.EDGE_DETERMINATION)) {
            case OUTGOING:
                generatedNodes = generateAnyGraph(graph, n, minOut, maxOut, 0);
                break;
            default:
                generatedNodes = generateAnyGraph(graph, n, m, 0);
            }
            
            if (genOptions.getProperty(GeneratorOptions.CROSS_HIERARCHY_EDGES)) {
                // create edges randomly across the whole compound graph, crossing hierarchy borders
                switch (genOptions.getProperty(GeneratorOptions.EDGE_DETERMINATION)) {
                case OUTGOING: {
                    int[] outgoingEdges = determineOutgoingEdges(generatedNodes, minOut, maxOut);
                    connectRandomlyAndConditional(generatedNodes, outgoingEdges, basicCondition);
                    break;
                }
                default: {
                    int createdEdges = 0;
                    int iterations = 0;
                    do {
                        int[] outgoingEdges = determineOutgoingEdges(generatedNodes, m - createdEdges);
                        createdEdges += connectRandomlyAndConditional(generatedNodes, outgoingEdges,
                                basicCondition);
                        iterations++;
                    } while (createdEdges < m && iterations < MAX_ITER);
                }
                }
            }
            break;
        }
        
        case BIPARTITE: {
            float minPartition = genOptions.getProperty(GeneratorOptions.MIN_PARTITION_FRAC);
            float maxPartition = genOptions.getProperty(GeneratorOptions.MAX_PARTITION_FRAC);
            generateBipartite(graph, n, m, minPartition, maxPartition, 0);
            break;
        }
        
        case TREE: {
            int maxDegree = genOptions.getProperty(GeneratorOptions.MAX_DEGREE);
            int maxWidth = genOptions.getProperty(GeneratorOptions.MAX_WIDTH);
            generateTree(graph, n, maxDegree, maxWidth, 0);
            break;
        }
        
        case BICONNECTED: {
            generateBiconnectedGraph(graph, n, m, 0);
            break;
        }
        
        case TRICONNECTED: {
            float p1 = random.nextFloat();
            float p2 = 1.0f - p1;
            generateTriconnectedGraph(graph, n, p1, p2, 0);
            break;
        }
        
        case ACYCLIC_NO_TRANSITIVE_EDGES: {
            boolean planar = genOptions.getProperty(GeneratorOptions.PLANAR);
            generateANTEGraph(graph, n, m, planar, false, 0);
            break;
        }
        
        default:
            throw new IllegalArgumentException("Selected graph generator is not supported.");
        }
        
        // remove isolated nodes if requested
        if (!genOptions.getProperty(GeneratorOptions.ISOLATED_NODES)) {
            removeIsolatedNodes(graph);
        }
        
        // if ports require a predefined position, assign it to all ports
        if (genOptions.getProperty(GeneratorOptions.ENABLE_PORTS)
                && genOptions.getProperty(GeneratorOptions.PORT_CONSTRAINTS)
                == PortConstraints.FIXED_POS) {
            LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
            nodeQueue.add(graph);
            do {
                KNode node = nodeQueue.removeFirst();
                distributePorts(node);
                nodeQueue.addAll(node.getChildren());
            } while (!nodeQueue.isEmpty());
        }
        
        return graph;
    }

    /** the basic condition which cares for self-loops, multi-edges and cycles. */
    private final EdgeCondition basicCondition = new EdgeCondition() {
        public boolean evaluate(final KNode node1, final KNode node2) {
            if (!options.getProperty(GeneratorOptions.SELF_LOOPS) && node1 == node2) {
                return false;
            }
            if (!options.getProperty(GeneratorOptions.MULTI_EDGES) && connected(node1, node2)) {
                return false;
            }
            if (!options.getProperty(GeneratorOptions.CYCLES) && findNodeWithDFS(node2, node1)) {
                return false;
            }
            return true;
        }
    };

    /**
     * Generates a random graph.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param m
     *            the number of edges
     * @param hierarchyLevel
     *            the current hierarchy level
     * @return the list of created nodes
     */
    private List<KNode> generateAnyGraph(final KNode parent, final int n, final int m,
            final int hierarchyLevel) {
        // create the nodes
        List<KNode> nodes = createIndependentSet(parent, n);
        // connect the nodes
        if (!options.getProperty(GeneratorOptions.CROSS_HIERARCHY_EDGES)) {
            // determine the number of outgoing edges for every node
            int createdEdges = 0;
            int iterations = 0;
            do {
                int[] outgoingEdges = determineOutgoingEdges(nodes, m - createdEdges);
                createdEdges += connectRandomlyAndConditional(nodes, outgoingEdges, basicCondition);
                iterations++;
            } while (createdEdges < m && iterations < MAX_ITER);
        }
        // recursively create hierarchy if applicable
        float hierarchyChance = options.getProperty(GeneratorOptions.HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f
                && hierarchyLevel < options.getProperty(GeneratorOptions.MAX_HIERARCHY_LEVEL)) {
            for (KNode node : nodes.toArray(new KNode[nodes.size()])) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes and edges in the compound node
                    float sizeFactor = random.nextFloat() * options.getProperty(
                            GeneratorOptions.HIERARCHY_NODES_FACTOR);
                    int cn = Math.round(sizeFactor * n);
                    if (cn == 0) {
                        cn = 1;
                    }
                    int cm = Math.round(sizeFactor * m);
                    List<KNode> childNodes = generateAnyGraph(node, cn, cm, hierarchyLevel + 1);
                    nodes.addAll(childNodes);
                }
            }
        }
        return nodes;
    }

    /**
     * Generates a random graph.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param minOut
     *            the minimum number of outgoing edges per node
     * @param maxOut
     *            the maximum number of outgoing edges per node
     * @param hierarchyLevel
     *            the current hierarchy level
     * @return the list of created nodes
     */
    private List<KNode> generateAnyGraph(final KNode parent, final int n, final int minOut,
            final int maxOut, final int hierarchyLevel) {
        // create the nodes
        List<KNode> nodes = createIndependentSet(parent, n);
        // connect the nodes
        if (!options.getProperty(GeneratorOptions.CROSS_HIERARCHY_EDGES)) {
            // determine the number of outgoing edges for every node
            int[] outgoingEdges = determineOutgoingEdges(nodes, minOut, maxOut);
            connectRandomlyAndConditional(nodes, outgoingEdges, basicCondition);
        }
        // recursively create hierarchy if applicable
        float hierarchyChance = options.getProperty(GeneratorOptions.HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f
                && hierarchyLevel < options.getProperty(GeneratorOptions.MAX_HIERARCHY_LEVEL)) {
            for (KNode node : nodes.toArray(new KNode[nodes.size()])) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float sizeFactor = random.nextFloat() * options.getProperty(
                            GeneratorOptions.HIERARCHY_NODES_FACTOR);
                    int cn = Math.round(sizeFactor * n);
                    if (cn == 0) {
                        cn = 1;
                    }
                    List<KNode> childNodes = generateAnyGraph(node, cn, minOut, maxOut,
                            hierarchyLevel + 1);
                    nodes.addAll(childNodes);
                }
            }
        }
        return nodes;
    }
    
    /**
     * Generates a random bipartite graph.
     * 
     * @param parent the parent node
     * @param n the number of nodes
     * @param m the number of edges
     * @param minPartition the minimal fraction of nodes in the second set
     * @param maxPartition the maximal fraction of nodes in the second set
     * @param hierarchyLevel the current hierarchy level
     */
    private void generateBipartite(final KNode parent, final int n, final int m,
            final float minPartition, final float maxPartition, final int hierarchyLevel) {
        int n2 = ElkMath.boundi(Math.round(n * (minPartition + random.nextFloat()
                * (maxPartition - minPartition))), 1, n - 1);
        int n1 = n - n2;
        KNode[] nodes1 = new KNode[n1];
        for (int i = 0; i < n1; i++) {
            nodes1[i] = createNode(parent);
        }
        KNode[] nodes2 = new KNode[n2];
        for (int i = 0; i < n2; i++) {
            nodes2[i] = createNode(parent);
        }
        boolean allowCycles = options.getProperty(GeneratorOptions.CYCLES);
        for (int j = 0; j < m; j++) {
            int source;
            if (allowCycles) {
                source = random.nextInt(n);
            } else {
                source = random.nextInt(n1);
            }
            if (source < n1) {
                int target = random.nextInt(n2);
                connectConditional(nodes1[source], nodes2[target], basicCondition);
            } else {
                int target = random.nextInt(n1);
                connectConditional(nodes2[source - n1], nodes1[target], basicCondition);
            }
        }
        
        // recursively create hierarchy if applicable
        float hierarchyChance = options.getProperty(GeneratorOptions.HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f
                && hierarchyLevel < options.getProperty(GeneratorOptions.MAX_HIERARCHY_LEVEL)) {
            for (KNode node : parent.getChildren()) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float hierarchyNodesFactor = options.getProperty(
                            GeneratorOptions.HIERARCHY_NODES_FACTOR);
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    int cm = Math.round((float) cn / n * m);
                    generateBipartite(node, cn, cm, minPartition, maxPartition, hierarchyLevel + 1);
                }
            }
        }
    }

    /**
     * Generates a random tree. The implementation is based upon the one used in the OGDF library.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param maxDeg
     *            the maximum degree
     * @param maxWidth
     *            the maximum width
     * @param hierarchyLevel
     *            the current hierarchy level
     */
    private void generateTree(final KNode parent, final int n, final int maxDeg, final int maxWidth,
            final int hierarchyLevel) {
        int max = 0;
        int nodeIdCounter = 0;
        @SuppressWarnings("unchecked")
        Pair<KNode, Integer>[] possible = (Pair<KNode, Integer>[]) new Pair[n];
        int[] width = new int[n + 1];
        int[] level = new int[n];
        // create the root node
        KNode rootNode = createNode(parent);
        int rootId = nodeIdCounter++;
        possible[0] = new Pair<KNode, Integer>(rootNode, rootId);
        level[rootId] = 0;
        // create the tree
        for (int i = 1; i < n;) {
            // get the node to append to
            int x = randomInt(0, max);
            Pair<KNode, Integer> nodeInfo = possible[x];
            KNode node = nodeInfo.getFirst();
            int nodeId = nodeInfo.getSecond();
            // check for the width constraint
            if (maxWidth != 0 && width[level[nodeId] + 1] == maxWidth) {
                possible[x] = possible[max--];
                continue;
            }
            // check for the out-degree constraint
            if (maxDeg != 0 && node.getOutgoingEdges().size() + 1 == maxDeg) {
                possible[x] = possible[max--];
            }
            // append a new node
            KNode newNode = createNode(parent);
            int newNodeId = nodeIdCounter++;
            possible[++max] = new Pair<KNode, Integer>(newNode, newNodeId);
            connect(node, newNode);
            level[newNodeId] = level[nodeId] + 1;
            ++width[level[newNodeId]];
            ++i;
        }
        
        // recursively create hierarchy if applicable
        float hierarchyChance = options.getProperty(GeneratorOptions.HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f
                && hierarchyLevel < options.getProperty(GeneratorOptions.MAX_HIERARCHY_LEVEL)) {
            for (KNode node : parent.getChildren()) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float hierarchyNodesFactor = options.getProperty(
                            GeneratorOptions.HIERARCHY_NODES_FACTOR);
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    generateTree(node, cn, maxDeg, maxWidth, hierarchyLevel + 1);
                }
            }
        }
    }

    /**
     * Generates a biconnected graph. The implementation is based upon the one used in the OGDF
     * library.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param m
     *            the number of edges
     * @param hierarchyLevel
     *            the current hierarchy level
     */
    // CHECKSTYLEOFF MagicNumber
    private void generateBiconnectedGraph(final KNode parent, final int n, final int m,
            final int hierarchyLevel) {
        int realN = Math.max(3, n);
        int realM = Math.max(m, realN);
        // the number of split-edge operations
        int kse = realN - 3;
        // the number of add-edge operations
        int kae = realM - realN;
        KNode[] nodes = new KNode[realN];
        KEdge[] edges = new KEdge[realM];
        // start with a triangle
        nodes[0] = createNode(parent);
        nodes[1] = createNode(parent);
        nodes[2] = createNode(parent);
        edges[0] = connect(nodes[0], nodes[1]);
        edges[1] = connect(nodes[1], nodes[2]);
        edges[2] = connect(nodes[2], nodes[0]);
        int nNodes = 3;
        int nEdges = 3;
        // generate the graph
        while (kse + kae > 0) {
            int p = randomInt(1, kse + kae);
            if (p <= kse) {
                // split edge
                KEdge edge = edges[randomInt(0, nEdges - 1)];
                Pair<KNode, KEdge> splitInfo = split(edge);
                nodes[nNodes++] = splitInfo.getFirst();
                edges[nEdges++] = splitInfo.getSecond();
                --kse;
            } else {
                // add edge
                int i = randomInt(0, nNodes - 1);
                int j = (i + randomInt(1, nNodes - 1)) % nNodes;
                edges[nEdges++] = connect(nodes[i], nodes[j]);
                --kae;
            }
        }
        // recursively create hierarchy if applicable
        float hierarchyChance = options.getProperty(GeneratorOptions.HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f
                && hierarchyLevel < options.getProperty(GeneratorOptions.MAX_HIERARCHY_LEVEL)) {
            for (KNode node : nodes) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float hierarchyNodesFactor = options.getProperty(
                            GeneratorOptions.HIERARCHY_NODES_FACTOR);
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    // preserve density for number of edges
                    float density = (float) m / (n * n);
                    int cm = (int) density * cn * cn;
                    generateBiconnectedGraph(node, cn, cm, hierarchyLevel + 1);
                }
            }
        }
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Generates a triconnected graph. The implementation is based upon the one used in the OGDF
     * library.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param p1
     *            the probability for the first additional edge to be added
     * @param p2
     *            the probability for the second additional edge to be added
     * @param hierarchyLevel
     *            the current hierarchy level
     */
    // CHECKSTYLEOFF MagicNumber
    private void generateTriconnectedGraph(final KNode parent, final int n, final float p1,
            final float p2, final int hierarchyLevel) {
        int realN = Math.max(n, 4);
        // start with a clique of size 4
        List<KNode> cliqueNodes = createClique(parent, 4);
        // array of all nodes
        KNode[] nodes = new KNode[realN];
        int i = 0;
        for (KNode node : cliqueNodes) {
            nodes[i++] = node;
        }
        // array of neighbors
        KEdge[] neighbors = new KEdge[realN];
        // neighbor markings
        // 0 = not marked
        // 1 = marked left
        // 2 = marked right
        // 3 = marked both
        int[] marks = new int[n];
        // generate the graph
        for (; i < n; ++i) {
            KNode node = nodes[randomInt(0, i - 1)];
            // create a new node to split 'node' in two
            KNode newNode = createNode(parent);
            nodes[i] = newNode;
            // build array of all neighbors
            int d = node.getOutgoingEdges().size() + node.getIncomingEdges().size();
            int j = 0;
            for (KEdge edge : node.getOutgoingEdges()) {
                neighbors[j++] = edge;
            }
            for (KEdge edge : node.getIncomingEdges()) {
                neighbors[j++] = edge;
            }
            // mark two distinct neighbors for left
            for (j = 2; j > 0;) {
                int r = randomInt(0, d - 1);
                if ((marks[r] & 1) == 0) {
                    marks[r] |= 1;
                    --j;
                }
            }
            // mark two distinct neighbors for right
            for (j = 2; j > 0;) {
                int r = randomInt(0, d - 1);
                if ((marks[r] & 2) == 0) {
                    marks[r] |= 2;
                    --j;
                }
            }
            // perform the node-split
            for (j = 0; j < d; ++j) {
                int mark = marks[j];
                marks[j] = 0;
                // decide to with which node each neighbor is connected
                double x = random.nextDouble();
                switch (mark) {
                case 0:
                    if (x < p1) {
                        mark = 1;
                    } else if (x < p1 + p2) {
                        mark = 2;
                    } else {
                        mark = 3;
                    }
                    break;
                case 1:
                case 2:
                    if (x >= p1 + p2) {
                        mark = 3;
                    }
                    break;
                }
                // move edge or create new one if necessary
                KEdge edge = neighbors[j];
                switch (mark) {
                case 2:
                    if (node == edge.getSource()) {
                        moveSource(edge, newNode);
                    } else {
                        moveTarget(edge, newNode);
                    }
                    break;
                case 3:
                    if (node == edge.getSource()) {
                        connect(newNode, edge.getTarget());
                    } else {
                        connect(newNode, edge.getSource());
                    }
                    break;
                }
            }
            connect(node, newNode);
        }
        // recursively create hierarchy if applicable
        float hierarchyChance = options.getProperty(GeneratorOptions.HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f
                && hierarchyLevel < options.getProperty(GeneratorOptions.MAX_HIERARCHY_LEVEL)) {
            for (KNode node : parent.getChildren()) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float hierarchyNodesFactor = options.getProperty(
                            GeneratorOptions.HIERARCHY_NODES_FACTOR);
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    generateTriconnectedGraph(node, cn, p1, p2, hierarchyLevel + 1);
                }
            }
        }
    }

    /**
     * Generates an acyclic graph without transitive edges. The implementation is based upon the one
     * used in the OGDF library.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param m
     *            the number of edges
     * @param planar
     *            whether the generated graph should be planar
     * @param singleSource
     *            whether the graph is a single source graph
     * @param hierarchyLevel
     *            the current hierarchy level
     */
    private void generateANTEGraph(final KNode parent, final int n, final int m,
            final boolean planar, final boolean singleSource, final int hierarchyLevel) {
        KNode[] nnr = new KNode[3 * n];
        int[] vrt = new int[3 * n];
        int[] fst = new int[n + 1];
        List<HierarchyEdge> startEdges = new LinkedList<HierarchyEdge>();
        HierarchyEdge actEdge, nextEdge;
        int act, next, n1, n2, idc = 0;
        boolean connected;
        // create the nodes
        for (int i = 0; i < n; ++i) {
            createNode(parent);
        }
        int numberOfLayers = 0, totNumber = 0, realCount = 0;
        fst[0] = 0;
        for (KNode node : parent.getChildren()) {
            nnr[totNumber] = node;
            vrt[totNumber++] = 0;
            realCount++;
            float r = random.nextFloat();
            if (totNumber == 1 && singleSource || realCount == n || r * r * n < 1) {
                fst[++numberOfLayers] = totNumber;
            }
        }
        // determine allowed neighbors
        int[] leftN = new int[totNumber];
        int[] rightN = new int[totNumber];
        for (int l = 1; l < numberOfLayers; l++) {
            if (planar) {
                n1 = fst[l - 1];
                n2 = fst[l];
                leftN[n2] = n1;
                while (n1 < fst[l] && n2 < fst[l + 1]) {
                    float r = random.nextFloat();
                    if (n1 != fst[l] - 1
                            && (n2 == fst[l + 1] - 1 || r < (float) (fst[l] - fst[l - 1])
                                    / (float) (fst[l + 1] - fst[l - 1]))) {
                        n1++;
                    } else {
                        rightN[n2] = n1;
                        if (++n2 < fst[l + 1]) {
                            leftN[n2] = n1;
                        }
                    }
                }
            } else {
                for (n2 = fst[l]; n2 < fst[l + 1]; n2++) {
                    leftN[n2] = fst[l - 1];
                    rightN[n2] = fst[l] - 1;
                }
            }
        }
        // insert edges
        @SuppressWarnings("unchecked")
        List<HierarchyEdge>[] edgeIn = new LinkedList[totNumber];
        @SuppressWarnings("unchecked")
        List<HierarchyEdge>[] edgeOut = new LinkedList[totNumber];
        for (int i = 0; i < totNumber; ++i) {
            edgeIn[i] = new LinkedList<HierarchyEdge>();
            edgeOut[i] = new LinkedList<HierarchyEdge>();
        }
        if (numberOfLayers != 0) {
            float x1 = m;
            float x2 = 0;
            for (n2 = fst[1]; n2 < totNumber; n2++) {
                if (vrt[n2] == 0) {
                    x2 += rightN[n2] - leftN[n2] + 1;
                }
            }
            for (n2 = fst[1]; n2 < totNumber; n2++) {
                if (vrt[n2] == 0) {
                    connected = !singleSource;
                    for (n1 = leftN[n2]; n1 <= rightN[n2] || !connected; n1++) {
                        float r = random.nextFloat();
                        if (r < x1 / x2 || n1 > rightN[n2]) {
                            next = (n1 <= rightN[n2] ? n1 : randomInt(leftN[n2], rightN[n2]));
                            act = n2;
                            nextEdge = new HierarchyEdge(next, act, idc++);
                            while (vrt[next] != 0) {
                                act = next;
                                next = randomInt(leftN[act], rightN[act]);
                                edgeOut[act].add(nextEdge);
                                nextEdge = new HierarchyEdge(next, act, idc++);
                                edgeIn[act].add(nextEdge);
                            }
                            startEdges.add(nextEdge);
                            connected = true;
                            x1 -= 1;
                        }
                        if (n1 <= rightN[n2]) {
                            x2 -= 1;
                        }
                    }
                }
            }
        }
        if (planar) {
            for (act = 0; act < totNumber; act++) {
                Collections.sort(edgeIn[act], new TailComparator());
                Collections.sort(edgeOut[act], new HeadComparator());
            }
        }
        for (act = 0; act < totNumber; act++) {
            List<HierarchyEdge> hedges = edgeIn[act];
            for (HierarchyEdge hedge : hedges) {
                nextEdge = hedge;
                nextEdge.setNext(edgeOut[act].remove(0));
            }
        }
        for (HierarchyEdge hedge : startEdges) {
            actEdge = hedge;
            nextEdge = actEdge;
            while (vrt[nextEdge.getHead()] != 0) {
                nextEdge = nextEdge.getNext();
            }
            connect(nnr[actEdge.getTail()], nnr[nextEdge.getHead()]);
        }
        // recursively create hierarchy if applicable
        float hierarchyChance = options.getProperty(GeneratorOptions.HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f
                && hierarchyLevel < options.getProperty(GeneratorOptions.MAX_HIERARCHY_LEVEL)) {
            for (KNode node : parent.getChildren()) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float hierarchyNodesFactor = options.getProperty(
                            GeneratorOptions.HIERARCHY_NODES_FACTOR);
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    // preserve density for number of edges
                    float density = (float) m / (n * n);
                    int cm = (int) density * cn * cn;
                    generateANTEGraph(node, cn, cm, planar, singleSource, hierarchyLevel + 1);
                }
            }
        }
    }

    // CHECKSTYLEON MagicNumber

    /**
     * A helper class for creating hierarchical graphs.
     */
    private static class HierarchyEdge {

        /** the head, tail and id. */
        private int head, tail, id;

        /** the next edge. */
        private HierarchyEdge next;

        /**
         * Constructs a HierarchyEdge.
         * 
         * @param head
         *            the head
         * @param tail
         *            the tail
         * @param id
         *            the id
         */
        public HierarchyEdge(final int head, final int tail, final int id) {
            this.head = head;
            this.tail = tail;
            this.id = id;
        }

        /**
         * Returns the head.
         * 
         * @return the head
         */
        public int getHead() {
            return head;
        }

        /**
         * Returns the tail.
         * 
         * @return the tail
         */
        public int getTail() {
            return tail;
        }

        /**
         * Returns the id.
         * 
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * Returns the next edge.
         * 
         * @return the next edge
         */
        public HierarchyEdge getNext() {
            return next;
        }

        /**
         * Sets the next edge.
         * 
         * @param next
         *            the next edge
         */
        public void setNext(final HierarchyEdge next) {
            this.next = next;
        }
    }

    /**
     * Compares hierarchy edges by the id attribute.
     * 
     * @param edge1
     *            the first edge
     * @param edge2
     *            the second edge
     * @return the result of the comparison
     */
    private static int compareId(final HierarchyEdge edge1, final HierarchyEdge edge2) {
        return edge1.getId() < edge2.getId() ? -1 : (edge1.getId() > edge2.getId() ? 1 : 0);
    }

    /**
     * A helper class for comparing hierarchy edges by the head attribute.
     */
    private static class HeadComparator implements Comparator<HierarchyEdge> {

        /**
         * {@inheritDoc}
         */
        public int compare(final HierarchyEdge edge1, final HierarchyEdge edge2) {
            return edge1.getHead() < edge2.getHead() ? -1 : (edge1.getHead() > edge2.getHead() ? 1
                    : compareId(edge1, edge2));
        }
    }

    /**
     * A helper class for comparing hierarchy edges by the tail attribute.
     */
    private static class TailComparator implements Comparator<HierarchyEdge> {

        /**
         * {@inheritDoc}
         */
        public int compare(final HierarchyEdge edge1, final HierarchyEdge edge2) {
            return edge1.getTail() < edge2.getTail() ? -1 : (edge1.getTail() > edge2.getTail() ? 1
                    : compareId(edge1, edge2));
        }
    }

    /**
     * Creates an independent set of nodes.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @return the list of created nodes
     */
    private List<KNode> createIndependentSet(final KNode parent, final int n) {
        List<KNode> nodes = new ArrayList<KNode>(n);
        for (int i = 0; i < n; ++i) {
            KNode node = createNode(parent);
            nodes.add(node);
        }
        return nodes;
    }

    /**
     * Creates a clique.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @return the list of created nodes
     */
    private List<KNode> createClique(final KNode parent, final int n) {
        List<KNode> nodes = createIndependentSet(parent, n);
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                connect(nodes.get(i), nodes.get(j));
            }
        }
        return nodes;
    }

    /**
     * Creates a node.
     * 
     * @param parent the parent node
     * @return the node
     */
    private KNode createNode(final KNode parent) {
        KNode node = ElkUtil.createInitializedNode();
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        float hypernodeChance = options.getProperty(GeneratorOptions.HYPERNODE_CHANCE);
        if (hypernodeChance > 0.0f && random.nextFloat() < hypernodeChance) {
            nodeLayout.setProperty(CoreOptions.HYPERNODE, true);
        }
        
        // create label and identifier
        String nodeid = String.valueOf(nodeLabelCounter++);
        if (options.getProperty(GeneratorOptions.CREATE_NODE_LABELS)) {
            KLabel label = ElkUtil.createInitializedLabel(node);
            label.setText("N" + nodeid);
        }
        KIdentifier identifier = KLayoutDataFactory.eINSTANCE.createKIdentifier();
        identifier.setId("n" + nodeid);
        node.getData().add(identifier);
        
        // set size of the node
        if (options.getProperty(GeneratorOptions.SET_NODE_SIZE)) {
            nodeLayout.setWidth(randomInt(options.getProperty(GeneratorOptions.MIN_NODE_WIDTH),
                    options.getProperty(GeneratorOptions.MAX_NODE_WIDTH)));
            nodeLayout.setHeight(randomInt(options.getProperty(GeneratorOptions.MIN_NODE_HEIGHT),
                    options.getProperty(GeneratorOptions.MAX_NODE_HEIGHT)));
        }
        
        // set port constraints
        PortConstraints portConstraints = options.getProperty(GeneratorOptions.PORT_CONSTRAINTS);
        if (portConstraints != PortConstraints.UNDEFINED) {
            nodeLayout.setProperty(CoreOptions.PORT_CONSTRAINTS, portConstraints);
        }
        
        parent.getChildren().add(node);
        return node;
    }
    
    /**
     * Creates a port.
     * 
     * @param node the containing node
     * @param source {@code true} if the port will be used as a source port, {@code false} if it will
     *               be used as a target port.
     * @return the port
     */
    private KPort createPort(final KNode node, final boolean source) {
        KPort port = ElkUtil.createInitializedPort();
        node.getPorts().add(port);
        KShapeLayout portLayout = port.getData(KShapeLayout.class);
        
        // create label and identifier
        String portId = String.valueOf(portLabelCounter++);
        if (options.getProperty(GeneratorOptions.CREATE_PORT_LABELS)) {
            KLabel label = ElkUtil.createInitializedLabel(port);
            label.setText("P" + portId);
        }
        KIdentifier identifier = KLayoutDataFactory.eINSTANCE.createKIdentifier();
        identifier.setId("p" + portId);
        port.getData().add(identifier);
        
        // set size of the port
        if (options.getProperty(GeneratorOptions.SET_PORT_SIZE)) {
            portLayout.setWidth(PORT_WIDTH);
            portLayout.setHeight(PORT_HEIGHT);
        }
        
        if (options.getProperty(GeneratorOptions.PORT_CONSTRAINTS).isSideFixed()) {
            // determine a random node side
            int northProb, eastProb, southProb, westProb;
            if (source) {
                northProb = options.getProperty(GeneratorOptions.OUTGOING_NORTH_SIDE);
                eastProb = options.getProperty(GeneratorOptions.OUTGOING_EAST_SIDE);
                southProb = options.getProperty(GeneratorOptions.OUTGOING_SOUTH_SIDE);
                westProb = options.getProperty(GeneratorOptions.OUTGOING_WEST_SIDE);
            } else {
                northProb = options.getProperty(GeneratorOptions.INCOMING_NORTH_SIDE);
                eastProb = options.getProperty(GeneratorOptions.INCOMING_EAST_SIDE);
                southProb = options.getProperty(GeneratorOptions.INCOMING_SOUTH_SIDE);
                westProb = options.getProperty(GeneratorOptions.INCOMING_WEST_SIDE);
            }
            int p = random.nextInt(northProb + eastProb + southProb + westProb);
            PortSide portSide;
            if (p < northProb) {
                portSide = PortSide.NORTH;
            } else if (p < northProb + eastProb) {
                portSide = PortSide.EAST;
            } else if (p < northProb + eastProb + southProb) {
                portSide = PortSide.SOUTH;
            } else {
                portSide = PortSide.WEST;
            }
            portLayout.setProperty(CoreOptions.PORT_SIDE, portSide);
        }
        
        return port;
    }

    /**
     * Connects two nodes with an edge.
     * 
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @param directed
     *            whether the edge should be directed or undirected
     * @return the edge
     */
    private KEdge connect(final KNode source, final KNode target) {
        KEdge edge = ElkUtil.createInitializedEdge();
        
        edge.setSource(source);
        edge.setTarget(target);
        
        if (options.getProperty(GeneratorOptions.ENABLE_PORTS)) {
            if (!isHypernode(source)) {
                KPort sourcePort = retrievePort(source, true);
                edge.setSourcePort(sourcePort);
            }
            
            if (!isHypernode(target)) {
                KPort targetPort = retrievePort(target, false);
                edge.setTargetPort(targetPort);
            }
        }
        
        if (options.getProperty(GeneratorOptions.EDGE_LABELS)) {
            addEdgeLabel(edge);
        }
        
        return edge;
    }
    
    /**
     * Creates a {@link KLabel} with random text and adds it to the edge.
     * 
     * @param edge the edge to add the label to.
     */
    private void addEdgeLabel(final KEdge edge) {
        KLabel label = ElkUtil.createInitializedLabel(edge);
        label.setText(Blindtext.getRandomText());
        edge.getLabels().add(label);
    }

    /**
     * Retrieves a port for a new edge to connect to the given node through. This can either be a newly
     * created port, or an existing one. Which one it is depends on the chance of ports to be reused.
     * 
     * <p>An outgoing edge will only ever try to reuse ports that only have outgoing edges connected
     * to them. The same is true for incoming edges and ports with only incoming edges.</p>
     * 
     * @param node the node to add the port to.
     * @param source {@code true} if the port will be used as a source port, {@code false} if it will
     *               be used as a target port.
     * @return the new or existing port.
     */
    private KPort retrievePort(final KNode node, final boolean source) {
        // We might want to reuse an existing port
        float reusePortsChance = options.getProperty(GeneratorOptions.USE_EXISTING_PORTS_CHANCE);
        if (reusePortsChance > 0.0f && random.nextFloat() < reusePortsChance) {
            // Collect candidate ports for reuse
            List<KPort> reuseCandidates = Lists.newLinkedList();
            
            for (KPort port : node.getPorts()) {
                // Two flags indicating whether the port already has edges pointing in the right
                // or wrong direction connected to it
                boolean connectedToDesiredEdges = false;
                boolean connectedToBadEdges = false;
                
                for (KEdge edge : port.getEdges()) {
                    connectedToDesiredEdges = (source && edge.getSourcePort() == port)
                            || (!source && edge.getTargetPort() == port);
                    connectedToBadEdges = (source && edge.getTargetPort() == port)
                            || (!source && edge.getSourcePort() == port);
                }
                
                // If there are only edges pointing in the same direction as the new edge connected to
                // the port, it qualifies as a candidate for reuse
                if (connectedToDesiredEdges && !connectedToBadEdges) {
                    reuseCandidates.add(port);
                }
            }
            
            // If there are candidates for reuse, choose one at random
            if (!reuseCandidates.isEmpty()) {
                return reuseCandidates.get(randomInt(0, reuseCandidates.size() - 1));
            }
        }
        
        // We were unable to reuse an existing port, so create a new one and return that
        return createPort(node, source);
    }

    /**
     * Connects two nodes with an edge if the given condition is evaluated to true.
     * 
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @param condition
     *            the condition
     * @return whether the nodes have been connected
     */
    private boolean connectConditional(final KNode source, final KNode target,
            final EdgeCondition condition) {
        if (condition.evaluate(source, target)) {
            connect(source, target);
            return true;
        }
        return false;
    }

    /**
     * Changes the source of a given edge to a given node.
     * 
     * @param edge
     *            the edge
     * @param node
     *            the new source node
     */
    private void moveSource(final KEdge edge, final KNode node) {
        if (options.getProperty(GeneratorOptions.ENABLE_PORTS)) {
            // Check if we need to remove the old source port
            if (edge.getSourcePort() != null && edge.getSourcePort().getEdges().size() == 1) {
                edge.getSource().getPorts().remove(edge.getSourcePort());
            }
            
            if (!isHypernode(node)) {
                KPort newPort = retrievePort(node, true);
                edge.setSourcePort(newPort);
            }
        }
        
        edge.setSource(node);
    }

    /**
     * Changes the target of a given edge to a given node.
     * 
     * @param edge
     *            the edge
     * @param node
     *            the new target node
     */
    private void moveTarget(final KEdge edge, final KNode node) {
        if (options.getProperty(GeneratorOptions.ENABLE_PORTS)) {
            // Check if we need to remove the old target port
            if (edge.getTargetPort() != null && edge.getTargetPort().getEdges().size() == 1) {
                edge.getTarget().getPorts().remove(edge.getTargetPort());
            }
            
            if (!isHypernode(node)) {
                KPort newPort = retrievePort(node, false);
                edge.setTargetPort(newPort);
            }
        }
        
        edge.setTarget(node);
    }

    /**
     * Splits an edge by inserting a new node and a new edge.
     * 
     * @param edge
     *            the edge
     * @return a pair containing the new node and the new edge
     */
    private Pair<KNode, KEdge> split(final KEdge edge) {
        KNode newNode = createNode(edge.getSource().getParent());
        KEdge newEdge = connect(newNode, edge.getTarget());
        moveTarget(edge, newNode);
        return new Pair<KNode, KEdge>(newNode, newEdge);
    }

    /**
     * Connects a source node a number of times to randomly selected nodes of a given list if the
     * condition evaluates to true for the selected node.
     * 
     * @param source
     *            the source node
     * @param targets
     *            the target nodes
     * @param number
     *            the number of times the node has to be connected to random nodes
     * @param condition
     *            the condition
     * @return the number of edges which could be inserted
     */
    private int connectRandomlyAndConditional(final KNode source, final List<KNode> targets,
            final int number, final EdgeCondition condition) {
        KNode[] targetBuffer = targets.toArray(new KNode[0]);
        int edges = 0;
        int bufferEnd = targetBuffer.length - 1;
        // try connecting the source to up to 'number' nodes randomly
        while (edges < number && bufferEnd >= 0) {
            int i = randomInt(0, bufferEnd);
            KNode target = targetBuffer[i];
            if (connectConditional(source, target, condition)) {
                edges++;
            } else {
                // the current node does not fulfill the condition so replace it with an element
                // from the end of the buffer
                targetBuffer[i] = targetBuffer[bufferEnd];
                bufferEnd--;
            }

        }
        return edges;
    }

    /**
     * Connects every node in a list of nodes with random nodes of the same list for the specified
     * number of times.
     * 
     * @param nodes
     *            the list of nodes
     * @param outgoingEdges
     *            the number of outgoing edges for every node
     * @param condition
     *            the condition
     * @return the number of edges which could be inserted
     */
    private int connectRandomlyAndConditional(final List<KNode> nodes, final int[] outgoingEdges,
            final EdgeCondition condition) {
        // connect every node to the specified number of other nodes
        int edges = 0;
        for (int i = 0; i < nodes.size(); i++) {
            KNode source = nodes.get(i);
            edges += connectRandomlyAndConditional(source, nodes, outgoingEdges[i], condition);
        }
        return edges;
    }

    /**
     * Randomly calculates a number of outgoing edges for every node in a list.
     * 
     * @param nodes
     *            the list of nodes
     * @param minOut
     *            the minimum number of outgoing edges per node
     * @param maxOut
     *            the maximum number of outgoing edges per node
     * @return the number of outgoing edges for every node
     */
    private int[] determineOutgoingEdges(final List<KNode> nodes, final int minOut, final int maxOut) {
        // determine the number of outgoing edges for every node
        int n = nodes.size();
        int[] numberOfEdges = new int[n];
        for (int i = 0; i < n; ++i) {
            int c = randomInt(minOut, maxOut);
            numberOfEdges[i] = c;
        }
        return numberOfEdges;
    }

    /**
     * Randomly calculates a number of outgoing edges for every node in a list until a given number
     * of edges have been inserted.
     * 
     * @param nodes
     *            the list of nodes
     * @param m
     *            the number of edges
     * @return the number of outgoing edges for every node
     */
    private int[] determineOutgoingEdges(final List<KNode> nodes, final int m) {
        // determine the number of outgoing edges for every node
        int[] outgoingEdges = new int[nodes.size()];
        for (int c = 0; c < m; ++c) {
            int i = randomInt(0, nodes.size() - 1);
            ++outgoingEdges[i];
        }
        return outgoingEdges;
    }
    
    /**
     * Remove all nodes from the graph that have no incoming or outgoing connections and have
     * no child nodes.
     * 
     * @param parent the parent node of the graph
     */
    private static void removeIsolatedNodes(final KNode parent) {
        ListIterator<KNode> nodeIter = parent.getChildren().listIterator();
        while (nodeIter.hasNext()) {
            KNode node = nodeIter.next();
            removeIsolatedNodes(node);
            if (node.getIncomingEdges().isEmpty() && node.getOutgoingEdges().isEmpty()
                    && node.getChildren().isEmpty()) {
                nodeIter.remove();
            }
        }
    }

    /**
     * Returns whether a node can be reached from another node.
     * PRECONDITION: the graph containing the nodes has to be acyclic! If that condition is not met
     * the method is likely to loop infinitely!
     * 
     * @param start
     *            the start node
     * @param end
     *            the end node
     * @return whether the end node can be reached from the start node
     */
    private static boolean findNodeWithDFS(final KNode start, final KNode end) {
        Queue<KNode> nodes = new LinkedList<KNode>();
        nodes.add(start);
        do {
            KNode node = nodes.poll();
            if (node == end) {
                return true;
            }
            for (KEdge edge : node.getOutgoingEdges()) {
                nodes.add(edge.getTarget());
            }
        } while (!nodes.isEmpty());
        return false;
    }

    /**
     * Returns whether two nodes are connected.
     * 
     * @param node1
     *            the first node
     * @param node2
     *            the second node
     * @return whether the two nodes are connected
     */
    private static boolean connected(final KNode node1, final KNode node2) {
        for (KEdge edge : node1.getOutgoingEdges()) {
            if (edge.getTarget() == node2) {
                return true;
            }
        }
        for (KEdge edge : node2.getOutgoingEdges()) {
            if (edge.getTarget() == node1) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Distribute all ports on the border of the given node.
     * 
     * @param node a node
     */
    private static void distributePorts(final KNode node) {
        // count the ports on each side of the node
        int northCount = 0, eastCount = 0, southCount = 0, westCount = 0;
        for (KPort port : node.getPorts()) {
            switch (port.getData(KShapeLayout.class).getProperty(CoreOptions.PORT_SIDE)) {
            case NORTH:
                northCount++;
                break;
            case EAST:
                eastCount++;
                break;
            case SOUTH:
                southCount++;
                break;
            case WEST:
                westCount++;
                break;
            }
        }
        
        // make sure the node is big enough to contain all ports
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        if (nodeLayout.getWidth() < (northCount + 1) * (PORT_WIDTH + PORT_SEPARATION)) {
            nodeLayout.setWidth((northCount + 1) * (PORT_WIDTH + PORT_SEPARATION));
        }
        if (nodeLayout.getWidth() < (southCount + 1) * (PORT_WIDTH + PORT_SEPARATION)) {
            nodeLayout.setWidth((southCount + 1) * (PORT_WIDTH + PORT_SEPARATION));
        }
        if (nodeLayout.getHeight() < (eastCount + 1) * (PORT_HEIGHT + PORT_SEPARATION)) {
            nodeLayout.setHeight((eastCount + 1) * (PORT_HEIGHT + PORT_SEPARATION));
        }
        if (nodeLayout.getHeight() < (westCount + 1) * (PORT_HEIGHT + PORT_SEPARATION)) {
            nodeLayout.setHeight((westCount + 1) * (PORT_HEIGHT + PORT_SEPARATION));
        }
        
        // distribute the ports on each node side
        float northDelta = nodeLayout.getWidth() / (northCount + 1);
        float eastDelta = nodeLayout.getHeight() / (eastCount + 1);
        float southDelta = nodeLayout.getWidth() / (southCount + 1);
        float westDelta = nodeLayout.getHeight() / (westCount + 1);
        float northPos = 0, eastPos = 0, southPos = 0, westPos = 0;
        for (KPort port : node.getPorts()) {
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            switch (portLayout.getProperty(CoreOptions.PORT_SIDE)) {
            case NORTH:
                northPos += northDelta;
                portLayout.setXpos(northPos - portLayout.getWidth() / 2);
                portLayout.setYpos(-portLayout.getHeight());
                break;
            case EAST:
                eastPos += eastDelta;
                portLayout.setXpos(nodeLayout.getWidth());
                portLayout.setYpos(eastPos - portLayout.getHeight() / 2);
                break;
            case SOUTH:
                southPos += southDelta;
                portLayout.setXpos(southPos - portLayout.getWidth() / 2);
                portLayout.setYpos(nodeLayout.getHeight());
                break;
            case WEST:
                westPos += westDelta;
                portLayout.setXpos(-portLayout.getWidth());
                portLayout.setYpos(westPos - portLayout.getHeight() / 2);
                break;
            }
        }
    }

    /**
     * Returns a random integer number in the given range (including the boundaries).
     * 
     * @param from
     *            the minimal number
     * @param to
     *            the maximal number
     * @return a random integer number
     */
    private int randomInt(final int from, final int to) {
        assert from <= to;
        return from + random.nextInt(to - from + 1);
    }
    
    /**
     * Determine whether the given node is a hypernode.
     * 
     * @param node a node
     * @return true if the node is a hypernode
     */
    private static boolean isHypernode(final KNode node) {
        return node.getData(KShapeLayout.class).getProperty(CoreOptions.HYPERNODE);
    }

    /**
     * An interface for expressing conditions for creating an edge between two nodes.
     */
    private interface EdgeCondition {
        /**
         * Returns whether the condition is met for an edge from the first to the second node.
         * 
         * @param node1
         *            the first node
         * @param node2
         *            the second node
         * @return true if the condition for the edge is met; false else
         */
        boolean evaluate(final KNode node1, final KNode node2);
    }
    
}
