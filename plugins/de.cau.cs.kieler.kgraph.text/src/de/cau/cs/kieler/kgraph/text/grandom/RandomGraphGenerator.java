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
package de.cau.cs.kieler.kgraph.text.grandom;

import static de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.properties.IProperty;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions.RandVal;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory;
import de.cau.cs.kieler.klighd.kgraph.KIdentifier;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;

/**
 * The random graph generator for KGraphs.
 *
 * @author mri
 * @author msp
 */
public class RandomGraphGenerator {

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

    private int maxHierarchyLevel;

    /**
     * Create a random graph generator with given random number generator.
     *
     * @param random
     *            a random number generator
     */
    public RandomGraphGenerator(final Random random) {
        this.random = random;
    }

    /**
     * {@inheritDoc}
     */
    public KNode generate(final GeneratorOptions opts) {
        // reset the generator
        nodeLabelCounter = 0;
        portLabelCounter = 0;
        this.options = opts;
        // generate the graph
        KNode parent = KGraphUtil.createInitializedNode();
        maxHierarchyLevel = get(MAX_HIERARCHY_LEVEL).intVal(random);
        if (get(SMALL_HIERARCHY)) {
            List<List<KNode>> atomicNodesOnLevels = addHierarchicalNodes(parent);
            connectAtomicNodesOnDifferentLevels(atomicNodesOnLevels);
            return parent;
        } else {
            return makeGraph(parent);
        }
    }

    private List<List<KNode>> addHierarchicalNodes(final KNode parent) {
        List<List<KNode>> atomicNodes = new ArrayList<>();
        Deque<KNode> allGraphs = new LinkedList<KNode>();
        allGraphs.push(parent);
        for (int i = 0; i < maxHierarchyLevel; i++) {
            List<KNode> hierarchicalNodes = new ArrayList<>();
            while (!allGraphs.isEmpty()) {
                KNode p = allGraphs.pop();
                makeGraph(p);
                // Must copy list, because eclipse EList cannot be shuffled.
                if (!p.getChildren().isEmpty()) {
                    atomicNodes.add(new ArrayList<>(p.getChildren()));
                }
                // Do not add more hierarchical nodes on lowest level.
                if (i < maxHierarchyLevel - 1) {
                    int numHierarch = get(NUMBER_HIERARCHICAL_NODES).intVal(random);
                    hierarchicalNodes.addAll(createIndependentSet(p, numHierarch));
                }
            }
            for (KNode node : hierarchicalNodes) {
                allGraphs.push(node);
            }
        }
        return atomicNodes;
    }

    private void connectAtomicNodesOnDifferentLevels(final List<List<KNode>> atomicNodes) {
        int numCrossHier;
        if (get(EXACT_RELATIVE_HIER) != null) {
            Set<KEdge> newHashSet = Sets.newHashSet();
            for (List<KNode> level : atomicNodes) {
                for (KNode node : level) {
                    newHashSet.addAll(node.getIncomingEdges());
                    newHashSet.addAll(node.getOutgoingEdges());
                }
            }
            numCrossHier = (int) (newHashSet.size() * get(EXACT_RELATIVE_HIER).val(random));
        } else {
            numCrossHier = get(CROSS_HIER).intVal(random);
        }
        if (atomicNodes.size() < 2) {
            return;
        }
        for (int i = 0; i < numCrossHier; i++) {
            if (atomicNodes.size() > 1) {
                List<List<KNode>> levels = sample(atomicNodes, 2);
                connect(sample(levels.get(0), 1).get(0), sample(levels.get(1), 1).get(0));
            }
        }
    }

    private <T> List<T> sample(final List<T> list, final int number) {
        List<T> shuffleThis = new ArrayList<>(list);
        Collections.shuffle(shuffleThis);
        if (number > list.size()) {
            return shuffleThis;
        }
        return shuffleThis.subList(0, number);
    }

    private KNode makeGraph(final KNode graph) {

        if (!get(ENABLE_HIERARCHY)) {
            set(HIERARCHY_CHANCE, 0.0f);
            set(CROSS_HIERARCHY_EDGES, false);
        }

        // initialize basic properties
        int n = get(NUMBER_OF_NODES).intVal(random);

        int m;
        switch (get(EDGE_DETERMINATION)) {
        case ABSOLUTE: {
            m = get(EDGES_ABSOLUTE).intVal(random);
            break;
        }

        case RELATIVE: {
            m = (int) (n * get(RELATIVE_EDGES).val(random));
            break;
        }

        case DENSITY: {
            double d = get(DENSITY).val(random);
            m = (int) (Math.round(d * n * (n - 1) / 2));
            break;
        }

        case OUTGOING: {
            double edgesPerNode = get(OUTGOING_EDGES).val(random);
            m = (int) (Math.round(n * edgesPerNode));
            break;
        }

        default:
            throw new IllegalArgumentException("Selected edge determination is not supported.");
        }

        switch (get(GRAPH_TYPE)) {
        case CUSTOM: {
            generateCustomGraph(n, m, graph);
            break;
        }

        case BIPARTITE: {
            generateBipartite(graph, n, m, 0);
            break;
        }

        case TREE: {
            int maxDegree = get(MAX_DEGREE);
            int maxWidth = get(MAX_WIDTH);
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
            boolean planar = get(PLANAR);
            generateANTEGraph(graph, n, m, planar, false, 0);
            break;
        }

        default:
            throw new IllegalArgumentException("Selected graph generator is not supported.");
        }

        // remove isolated nodes if requested
        if (!get(ISOLATED_NODES)) {
            removeIsolatedNodes(graph);
        }

        // if ports require a predefined position, assign it to all ports
        if (get(ENABLE_PORTS)
                && get(PORT_CONSTRAINTS) == PortConstraints.FIXED_POS) {
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

    private List<KNode> generateCustomGraph(final int n, final int m,
            final KNode graph) {
        List<KNode> generatedNodes;
        switch (get(EDGE_DETERMINATION)) {
        case OUTGOING:
            generatedNodes = generateAnyGraph(graph, n, 0);
            break;
        default:
            generatedNodes = generateAnyGraph(graph, n, m, 0);
        }

        // TODO-alan consider removal
        if (get(CROSS_HIERARCHY_EDGES)) {
            // create edges randomly across the whole compound graph, crossing hierarchy borders
            switch (get(EDGE_DETERMINATION)) {
            case OUTGOING: {
                int[] outgoingEdges = determineOutgoingEdges(generatedNodes, get(OUTGOING_EDGES));
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

        return generatedNodes;

    }

    /** the basic condition which cares for self-loops, multi-edges and cycles. */
    private final EdgeCondition basicCondition = new EdgeCondition() {
        public boolean evaluate(final KNode node1, final KNode node2) {
            if (!get(SELF_LOOPS) && node1 == node2) {
                return false;
            }
            if (!get(MULTI_EDGES) && connected(node1, node2)) {
                return false;
            }
            if (!get(CYCLES) && findNodeWithDFS(node2, node1)) {
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
        if (!get(CROSS_HIERARCHY_EDGES)) {
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
        float hierarchyChance = get(HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f && hierarchyLevel < maxHierarchyLevel) {
            for (KNode node : nodes.toArray(new KNode[nodes.size()])) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes and edges in the compound node
                    float sizeFactor = random.nextFloat() * get(
                            HIERARCHY_NODES_FACTOR);
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
    private List<KNode> generateAnyGraph(final KNode parent, final int n, final int hierarchyLevel) {
        // create the nodes
        List<KNode> nodes = createIndependentSet(parent, n);
        // connect the nodes
        if (!get(CROSS_HIERARCHY_EDGES)) {
            // determine the number of outgoing edges for every node
            int[] outgoingEdges = determineOutgoingEdges(nodes, get(OUTGOING_EDGES));
            connectRandomlyAndConditional(nodes, outgoingEdges, basicCondition);
        }
        // recursively create hierarchy if applicable
        float hierarchyChance = get(HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f && hierarchyLevel < maxHierarchyLevel) {
            for (KNode node : nodes.toArray(new KNode[nodes.size()])) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float sizeFactor = random.nextFloat() * get(
                            HIERARCHY_NODES_FACTOR);
                    int cn = Math.round(sizeFactor * n);
                    if (cn == 0) {
                        cn = 1;
                    }
                    List<KNode> childNodes = generateAnyGraph(node, cn,
                            hierarchyLevel + 1, maxHierarchyLevel);
                    nodes.addAll(childNodes);
                }
            }
        }
        return nodes;
    }

    /**
     * Generates a random bipartite graph.
     *
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param m
     *            the number of edges
     * @param minPartition
     *            the minimal fraction of nodes in the second set
     * @param maxPartition
     *            the maximal fraction of nodes in the second set
     * @param hierarchyLevel
     *            the current hierarchy level
     */
    private void generateBipartite(final KNode parent, final int n, final int m, final int hierarchyLevel) {
        int n2 = ElkMath.boundi((int) Math.round(n * get(PARTITION_FRAC).val(random)), 1, n - 1);
        int n1 = n - n2;
        KNode[] nodes1 = new KNode[n1];
        for (int i = 0; i < n1; i++) {
            nodes1[i] = createNode(parent);
        }
        KNode[] nodes2 = new KNode[n2];
        for (int i = 0; i < n2; i++) {
            nodes2[i] = createNode(parent);
        }
        boolean allowCycles = get(CYCLES);
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
        float hierarchyChance = get(HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f && hierarchyLevel < maxHierarchyLevel) {
            for (KNode node : parent.getChildren()) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float hierarchyNodesFactor = get(
                            HIERARCHY_NODES_FACTOR);
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    int cm = Math.round((float) cn / n * m);
                    generateBipartite(node, cn, cm, hierarchyLevel + 1);
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
        float hierarchyChance = get(HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f && hierarchyLevel < maxHierarchyLevel) {
            for (KNode node : parent.getChildren()) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float hierarchyNodesFactor = get(
                            HIERARCHY_NODES_FACTOR);
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    generateTree(node, cn, maxDeg, maxWidth, hierarchyLevel + 1);
                }
            }
        }
    }

    /**
     * Generates a biconnected graph. The implementation is based upon the one used in the OGDF library.
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
        float hierarchyChance = get(HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f && hierarchyLevel < maxHierarchyLevel) {
            for (KNode node : nodes) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float hierarchyNodesFactor = get(
                            HIERARCHY_NODES_FACTOR);
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
     * Generates a triconnected graph. The implementation is based upon the one used in the OGDF library.
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
        float hierarchyChance = get(HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f
                && hierarchyLevel < maxHierarchyLevel) {
            for (KNode node : parent.getChildren()) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float hierarchyNodesFactor = get(
                            HIERARCHY_NODES_FACTOR);
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    generateTriconnectedGraph(node, cn, p1, p2, hierarchyLevel + 1);
                }
            }
        }
    }

    /**
     * Generates an acyclic graph without transitive edges. The implementation is based upon the one used in the OGDF
     * library.
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
        float hierarchyChance = get(HIERARCHY_CHANCE);
        if (hierarchyChance > 0.0f
                && hierarchyLevel < maxHierarchyLevel) {
            for (KNode node : parent.getChildren()) {
                if (!isHypernode(node) && random.nextFloat() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    float hierarchyNodesFactor = get(
                            HIERARCHY_NODES_FACTOR);
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
        HierarchyEdge(final int head, final int tail, final int id) {
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
     * @param parent
     *            the parent node
     * @return the node
     */
    private KNode createNode(final KNode parent) {
        KNode node = KGraphUtil.createInitializedNode();
        float hypernodeChance = get(HYPERNODE_CHANCE);
        if (hypernodeChance > 0.0f && random.nextFloat() < hypernodeChance) {
            node.setProperty(CoreOptions.HYPERNODE, true);
        }

        // create label and identifier
        String nodeid = String.valueOf(nodeLabelCounter++);
        if (get(CREATE_NODE_LABELS)) {
            KLabel label = KGraphUtil.createInitializedLabel(node);
            label.setText("N" + nodeid);
        }
        KIdentifier identifier = KGraphFactory.eINSTANCE.createKIdentifier();
        identifier.setId("n" + nodeid);
        node.getData().add(identifier);

        // set size of the node
        if (get(SET_NODE_SIZE)) {
            node.setWidth(get(NODE_WIDTH).floatVal(random));
            node.setHeight(get(NODE_HEIGHT).floatVal(random));
        }

        // set port constraints
        PortConstraints portConstraints = get(PORT_CONSTRAINTS);
        if (portConstraints != PortConstraints.UNDEFINED) {
            node.setProperty(CoreOptions.PORT_CONSTRAINTS, portConstraints);
        }

        parent.getChildren().add(node);
        return node;
    }

    /**
     * Creates a port.
     *
     * @param node
     *            the containing node
     * @param source
     *            {@code true} if the port will be used as a source port, {@code false} if it will be used as a target
     *            port.
     * @return the port
     */
    private KPort createPort(final KNode node, final boolean source) {
        KPort port = KGraphUtil.createInitializedPort();
        node.getPorts().add(port);

        // create label and identifier
        String portId = String.valueOf(portLabelCounter++);
        if (get(CREATE_PORT_LABELS)) {
            KLabel label = KGraphUtil.createInitializedLabel(port);
            label.setText("P" + portId);
        }
        KIdentifier identifier = KGraphFactory.eINSTANCE.createKIdentifier();
        identifier.setId("p" + portId);
        port.getData().add(identifier);

        // set size of the port
        if (get(SET_PORT_SIZE)) {
            port.setWidth(get(PORT_WIDTH).floatVal(random));
            port.setHeight(get(PORT_HEIGHT).floatVal(random));
        }

        if (get(PORT_CONSTRAINTS).isSideFixed()) {
            // determine a random node side
            int northProb, eastProb, southProb, westProb;
            if (source) {
                northProb = get(OUTGOING_NORTH_SIDE);
                eastProb = get(OUTGOING_EAST_SIDE);
                southProb = get(OUTGOING_SOUTH_SIDE);
                westProb = get(OUTGOING_WEST_SIDE);
            } else {
                northProb = get(INCOMING_NORTH_SIDE);
                eastProb = get(INCOMING_EAST_SIDE);
                southProb = get(INCOMING_SOUTH_SIDE);
                westProb = get(INCOMING_WEST_SIDE);
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
            port.setProperty(CoreOptions.PORT_SIDE, portSide);
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
        KEdge edge = KGraphUtil.createInitializedEdge();

        edge.setSource(source);
        edge.setTarget(target);

        if (get(ENABLE_PORTS)) {
            if (!isHypernode(source)) {
                KPort sourcePort = retrievePort(source, true);
                edge.setSourcePort(sourcePort);
            }

            if (!isHypernode(target)) {
                KPort targetPort = retrievePort(target, false);
                edge.setTargetPort(targetPort);
            }
        }

        if (get(EDGE_LABELS)) {
            addEdgeLabel(edge);
        }

        return edge;
    }

    /**
     * Creates a {@link KLabel} with random text and adds it to the edge.
     *
     * @param edge
     *            the edge to add the label to.
     */
    private void addEdgeLabel(final KEdge edge) {
        KLabel label = KGraphUtil.createInitializedLabel(edge);
        label.setText(Blindtext.getRandomText());
        edge.getLabels().add(label);
    }

    /**
     * Retrieves a port for a new edge to connect to the given node through. This can either be a newly created port, or
     * an existing one. Which one it is depends on the chance of ports to be reused.
     *
     * <p>
     * An outgoing edge will only ever try to reuse ports that only have outgoing edges connected to them. The same is
     * true for incoming edges and ports with only incoming edges.
     * </p>
     *
     * @param node
     *            the node to add the port to.
     * @param source
     *            {@code true} if the port will be used as a source port, {@code false} if it will be used as a target
     *            port.
     * @return the new or existing port.
     */
    private KPort retrievePort(final KNode node, final boolean source) {
        // We might want to reuse an existing port
        float reusePortsChance = get(USE_EXISTING_PORTS_CHANCE).floatVal(random);
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
        if (get(ENABLE_PORTS)) {
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
        if (get(ENABLE_PORTS)) {
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
     * Connects a source node a number of times to randomly selected nodes of a given list if the condition evaluates to
     * true for the selected node.
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
     * Connects every node in a list of nodes with random nodes of the same list for the specified number of times.
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

    private int[] determineOutgoingEdges(final List<KNode> nodes, final RandVal val) {
        // determine the number of outgoing edges for every node
        int n = nodes.size();
        int[] numberOfEdges = new int[n];
        for (int i = 0; i < n; ++i) {
            int c = val.intVal(random);
            numberOfEdges[i] = c;
        }
        return numberOfEdges;
    }

    /**
     * Randomly calculates a number of outgoing edges for every node in a list until a given number of edges have been
     * inserted.
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
     * Remove all nodes from the graph that have no incoming or outgoing connections and have no child nodes.
     *
     * @param parent
     *            the parent node of the graph
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
     * Returns whether a node can be reached from another node. PRECONDITION: the graph containing the nodes has to be
     * acyclic! If that condition is not met the method is likely to loop infinitely!
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
     * @param node
     *            a node
     */
    private void distributePorts(final KNode node) {
        // count the ports on each side of the node
        int northCount = 0, eastCount = 0, southCount = 0, westCount = 0;
        for (KPort port : node.getPorts()) {
            switch (port.getProperty(CoreOptions.PORT_SIDE)) {
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
        float portWidth = get(PORT_WIDTH).floatVal(random);
        float portHeight = get(PORT_HEIGHT).floatVal(random);
        if (node.getWidth() < (northCount + 1) * (portWidth + PORT_SEPARATION)) {
            node.setWidth((northCount + 1) * (portWidth + PORT_SEPARATION));
        }
        if (node.getWidth() < (southCount + 1) * (portWidth + PORT_SEPARATION)) {
            node.setWidth((southCount + 1) * (portWidth + PORT_SEPARATION));
        }
        if (node.getHeight() < (eastCount + 1) * (portHeight + PORT_SEPARATION)) {
            node.setHeight((eastCount + 1) * (portHeight + PORT_SEPARATION));
        }
        if (node.getHeight() < (westCount + 1) * (portHeight + PORT_SEPARATION)) {
            node.setHeight((westCount + 1) * (portHeight + PORT_SEPARATION));
        }

        // distribute the ports on each node side
        float northDelta = node.getWidth() / (northCount + 1);
        float eastDelta = node.getHeight() / (eastCount + 1);
        float southDelta = node.getWidth() / (southCount + 1);
        float westDelta = node.getHeight() / (westCount + 1);
        float northPos = 0, eastPos = 0, southPos = 0, westPos = 0;
        for (KPort port : node.getPorts()) {
            switch (port.getProperty(CoreOptions.PORT_SIDE)) {
            case NORTH:
                northPos += northDelta;
                port.setXpos(northPos - port.getWidth() / 2);
                port.setYpos(-port.getHeight());
                break;
            case EAST:
                eastPos += eastDelta;
                port.setXpos(node.getWidth());
                port.setYpos(eastPos - port.getHeight() / 2);
                break;
            case SOUTH:
                southPos += southDelta;
                port.setXpos(southPos - port.getWidth() / 2);
                port.setYpos(node.getHeight());
                break;
            case WEST:
                westPos += westDelta;
                port.setXpos(-port.getWidth());
                port.setYpos(westPos - port.getHeight() / 2);
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
     * @param node
     *            a node
     * @return true if the node is a hypernode
     */
    private static boolean isHypernode(final KNode node) {
        return node.getProperty(CoreOptions.HYPERNODE);
    }

    private <T> T get(final IProperty<T> property) {
        return options.getProperty(property);
    }

    private <T> void set(final IProperty<T> property, final T val) {
        options.setProperty(property, val);
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
