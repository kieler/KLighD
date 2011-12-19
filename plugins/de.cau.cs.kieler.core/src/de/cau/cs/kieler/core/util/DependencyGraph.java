/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * A graph structure for expressing and resolving object dependencies.
 * 
 * @author mri
 * @param <S>
 *            the identifier type
 * @param <T>
 *            the object type
 */
public class DependencyGraph<S extends Comparable<S>, T extends IDepending<S>>
        implements IDependencyGraph<S, T> {

    /** the removed marker. */
    private static final int MARKER_REMOVED = 0;
    /** the not-visited-and-known marker. */
    private static final int MARKER_NOT_VISITED_AND_KNOWN = 1;
    /** the not-visited marker. */
    private static final int MARKER_NOT_VISITED = 2;
    /** the visited marker. */
    private static final int MARKER_VISITED = 3;

    /** a mapping of the objects identifiers on the nodes holding them. */
    private Map<S, Node> nodes = new HashMap<S, Node>();

    /**
     * {@inheritDoc}
     */
    public boolean add(final T object) {
        Node node = new Node(object);
        nodes.put(object.getId(), node);
        if (!node.initDependencies()) {
            nodes.remove(object.getId());
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> remove(final T object) {
        if (nodes.containsKey(object.getId())) {
            List<Node> removedNodes =
                    removeNodeAndDependencies(nodes.get(object.getId()));
            List<T> removedObjects = new LinkedList<T>();
            for (Node node : removedNodes) {
                removedObjects.add(node.getObject());
            }
            return removedObjects;
        } else {
            return new LinkedList<T>();
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<T> addAll(final Collection<T> objects) {
        Queue<Node> nodeQueue = new LinkedList<Node>();
        List<T> invalidObjects = new LinkedList<T>();
        // insert all nodes
        for (T object : objects) {
            Node node = new Node(object);
            nodes.put(object.getId(), node);
            nodeQueue.add(node);
        }
        // resolve dependencies
        List<Node> invalidNodes = new LinkedList<Node>();
        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.remove();
            // invalid object
            if (!node.initDependencies()) {
                invalidNodes.add(node);
            }
        }
        // remove invalid nodes
        for (Node invalidNode : invalidNodes) {
            List<Node> removedNodes = removeNodeAndDependencies(invalidNode);
            for (Node node : removedNodes) {
                invalidObjects.add(node.getObject());
            }
        }
        // removes all cycles from the graph
        invalidObjects.addAll(removeCycles());

        return invalidObjects;
    }

    /**
     * {@inheritDoc}
     */
    public T get(final S id) {
        if (nodes.containsKey(id)) {
            return nodes.get(id).getObject();
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<T> dependencySort(final List<T> objects) {
        LinkedList<T> sorted = new LinkedList<T>();
        // reset marker
        for (Node node : nodes.values()) {
            node.setMarker(MARKER_NOT_VISITED);
        }
        Stack<Node> stack = new Stack<Node>();
        for (T object : objects) {
            Node node = nodes.get(object.getId());
            if (node != null) {
                node.setMarker(MARKER_NOT_VISITED_AND_KNOWN);
                stack.add(node);
            }
        }
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.getMarker() < MARKER_VISITED) {
                node.setMarker(MARKER_VISITED);
                stack.add(node);
                for (Node dependency : node.getStrongDependencies()) {
                    stack.add(dependency);
                }
                for (Node dependency : node.getWeakDependencies()) {
                    if (dependency.getMarker() == MARKER_NOT_VISITED_AND_KNOWN) {
                        stack.add(dependency);
                    }
                }
            } else if (node.getMarker() != MARKER_VISITED + 1) {
                node.setMarker(MARKER_VISITED + 1);
                sorted.addLast(node.getObject());
            }
        }
        return sorted;
    }

    /**
     * {@inheritDoc}
     */
    public <R> R deriveObject(final T object,
            final DerivationDetail<T, R> derivationDetail) {
        Node node = nodes.get(object.getId());
        if (node == null) {
            return null;
        }
        R derivative = derivationDetail.derive(node.getObject());
        if (derivative == null) {
            return null;
        }
        Queue<Pair<Node, R>> nodeQueue = new LinkedList<Pair<Node, R>>();
        nodeQueue.add(new Pair<Node, R>(node, derivative));
        while (!nodeQueue.isEmpty()) {
            Pair<Node, R> currentPair = nodeQueue.poll();
            R currentDerivative = currentPair.getSecond();
            for (Node dependency : currentPair.getFirst()
                    .getStrongDependencies()) {
                R dependencyDerivative =
                        derivationDetail.derive(dependency.getObject());
                if (dependencyDerivative == null) {
                    return null;
                }
                derivationDetail.makeDependent(currentDerivative,
                        dependencyDerivative, dependency.getObject());
                nodeQueue.add(new Pair<Node, R>(dependency,
                        dependencyDerivative));
            }
        }
        return derivative;
    }

    /**
     * Removes all cycles from the graph.
     * 
     * @return the list of objects which node representations had to be removed
     */
    private List<T> removeCycles() {
        List<T> removedObjects = new LinkedList<T>();
        // reset marker
        for (Node node : nodes.values()) {
            node.setMarker(MARKER_NOT_VISITED);
        }
        // check for every node if it lies on a cycle
        int i = 0;
        Queue<Node> allNodes = new LinkedList<Node>(nodes.values());
        while (!allNodes.isEmpty()) {
            Node node = allNodes.remove();
            // DFS
            Stack<Node> stack = new Stack<Node>();
            node.setMarker(MARKER_VISITED + i);
            stack.addAll(node.getStrongDependants());
            while (!stack.isEmpty()) {
                Node currentNode = stack.pop();
                if (currentNode.getMarker() != MARKER_VISITED + i) {
                    currentNode.setMarker(MARKER_VISITED + i);
                    stack.addAll(currentNode.getStrongDependants());
                } else if (node == currentNode) {
                    // cycle detected
                    List<Node> removedNodes =
                            removeNodeAndDependencies(currentNode);
                    stack.removeAll(removedNodes);
                    allNodes.removeAll(removedNodes);
                    for (Node removedNode : removedNodes) {
                        removedObjects.add(removedNode.getObject());
                    }
                }
            }
            ++i;
        }
        return removedObjects;
    }

    /**
     * Removes the node from the graph and all nodes that depend on it or
     * another removed node.
     * 
     * @param node
     *            the node
     * @return the removed nodes
     */
    private List<Node> removeNodeAndDependencies(final Node node) {
        List<Node> removedNodes = new LinkedList<Node>();
        // BFS
        Queue<Node> nodesToRemove = new LinkedList<Node>();
        node.setMarker(MARKER_REMOVED);
        nodesToRemove.add(node);
        while (!nodesToRemove.isEmpty()) {
            Node removeNode = nodesToRemove.remove();
            removedNodes.add(removeNode);
            // strongly dependant nodes have to be removed as well
            for (Node dependantNode : removeNode.getStrongDependants()) {
                if (dependantNode.getMarker() != MARKER_REMOVED) {
                    dependantNode.getStrongDependencies().remove(removeNode);
                    dependantNode.setMarker(MARKER_REMOVED);
                    nodesToRemove.add(dependantNode);
                }
            }
            // remove node from dependencies
            for (Node dependencyNode : removeNode.getWeakDependencies()) {
                dependencyNode.getWeakDependants().remove(removeNode);
            }
            for (Node dependencyNode : removeNode.getStrongDependencies()) {
                dependencyNode.getStrongDependants().remove(removeNode);
            }
            // remove the node from the mapping
            nodes.remove(removeNode.getObject().getId());
        }
        return removedNodes;
    }

    /**
     * A class representing a node in the graph.
     */
    private class Node {

        /** a list of nodes that depend weakly on this node. */
        private List<Node> weakDependants = new LinkedList<Node>();
        /** a list of nodes that depend strongly on this node. */
        private List<Node> strongDependants = new LinkedList<Node>();
        /** a list of nodes that this node is depending weakly on. */
        private List<Node> weakDependencies = new LinkedList<Node>();
        /** a list of nodes that this node is depending strongly on. */
        private List<Node> strongDependencies = new LinkedList<Node>();
        /** the object this node is representing. */
        private T object;
        /** an utility marker attribute. */
        private int marker = 0;

        /**
         * Constructs a new node representing an object.
         * 
         * @param obj
         *            the object
         */
        public Node(final T obj) {
            object = obj;
        }

        /**
         * Initializes this nodes dependencies.
         * 
         * @return true if the dependencies could be resolved
         */
        public boolean initDependencies() {
            // find dependency nodes
            if (object.getDependencies() != null) {
                for (Dependency<S> dependency : object.getDependencies()) {
                    Node depNode = nodes.get(dependency.getID());
                    if (depNode != null) {
                        if (dependency.isWeak()) {
                            weakDependencies.add(depNode);
                        } else {
                            strongDependencies.add(depNode);
                        }
                    } else {
                        return false;
                    }
                }
                // add this to weak dependency nodes dependants
                for (Node node : weakDependencies) {
                    node.getWeakDependants().add(this);
                }
                // add this to strong dependency nodes dependants
                for (Node node : strongDependencies) {
                    node.getStrongDependants().add(this);
                }
            }
            return true;
        }

        /**
         * Returns this nodes weak dependants.
         * 
         * @return the dependants
         */
        public List<Node> getWeakDependants() {
            return weakDependants;
        }

        /**
         * Returns this nodes strong dependants.
         * 
         * @return the dependants
         */
        public List<Node> getStrongDependants() {
            return strongDependants;
        }

        /**
         * Returns this nodes weak dependencies.
         * 
         * @return the dependencies
         */
        public List<Node> getWeakDependencies() {
            return weakDependencies;
        }

        /**
         * Returns this nodes strong dependencies.
         * 
         * @return the dependencies
         */
        public List<Node> getStrongDependencies() {
            return strongDependencies;
        }

        /**
         * Returns this nodes object.
         * 
         * @return the object
         */
        public T getObject() {
            return object;
        }

        /**
         * Returns the marker attribute.
         * 
         * @return the marker
         */
        public int getMarker() {
            return marker;
        }

        /**
         * Sets the marker attribute.
         * 
         * @param newMarker
         *            the new marker attribute
         */
        public void setMarker(final int newMarker) {
            marker = newMarker;
        }
    }
}
