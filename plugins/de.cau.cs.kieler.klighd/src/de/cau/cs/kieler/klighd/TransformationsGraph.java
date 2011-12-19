/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
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
package de.cau.cs.kieler.klighd;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klighd.transformations.IdentityTransformation;

/**
 * A graph structure to express the relations between instances of {@code ITransformation} and
 * {@code IViewerProvider} and infered source, target and model classes.<br>
 * <br>
 * Provides the functionality required to configure a view context for a given model, with or
 * without a number of hints for transformations and viewers.
 * 
 * @author mri
 */
public class TransformationsGraph {

    /** the attribute code for a visited node. */
    private static final int VISITED = 0x01;

    /** the mapping of model classes on model class nodes. */
    private Map<Class<?>, ClassNode> classNodeMapping = Maps.newLinkedHashMap();

    /** the mapping of transformation id's on transformation edges. */
    private Map<String, ITransformation<?, ?>> idTransformationMapping = Maps.newLinkedHashMap();
    /** the mapping of transformations on class nodes representing source classes. */
    private Map<ITransformation<?, ?>, List<TransformationEdge>> transformationEdgeMapping = Maps
            .newHashMap();
    /** the mapping of transformations on class nodes representing source classes. */
    private Map<ITransformation<?, ?>, List<ClassNode>> transformationSourceNodeMapping = Maps
            .newHashMap();
    /** the mapping of transformations on class nodes representing target classes. */
    private Map<ITransformation<?, ?>, List<ClassNode>> transformationTargetNodeMapping = Maps
            .newHashMap();

    /** the mapping of viewer provider id's on viewer provider data. */
    private Map<String, IViewerProvider> idViewerProviderMapping = Maps.newLinkedHashMap();
    /** the mapping of viewer providers on class nodes representing supported classes. */
    private Map<IViewerProvider, List<ViewerProviderData>> viewerProviderDataMapping = Maps
            .newHashMap();

    /**
     * Adds a model transformation under a given identifier to the graph.
     * 
     * @param id
     *            the identifier for the model transformation
     * @param transformation
     *            the model transformation
     */
    public void addModelTransformation(final String id, final ITransformation<?, ?> transformation) {
        // only add the transformation if it is not already present
        if (id.length() > 0 && !idTransformationMapping.containsValue(transformation)
                && !idTransformationMapping.containsKey(id)) {
            Class<?> sourceClass = transformation.getSourceClass();
            Class<?> targetClass = transformation.getTargetClass();
            // only add the transformation if both source and target class can be determined
            if (sourceClass != null && targetClass != null) {
                // insert the classes into the graph if they are not present already
                createClassNode(sourceClass);
                createClassNode(targetClass);
                // map the identifier on the transformation
                idTransformationMapping.put(id, transformation);
                // insert the transformation
                createTransformationEdges(transformation, sourceClass, targetClass);
            }
        }
    }

    /**
     * Adds a viewer provider under the given identifier to the graph.
     * 
     * @param id
     *            the identifier for the viewer provider
     * @param viewerProvider
     *            the viewer provider
     */
    public void addViewerProvider(final String id, final IViewerProvider viewerProvider) {
        // only add the viewer provider if it is not already present
        if (id.length() > 0 && !idViewerProviderMapping.containsValue(viewerProvider)
                && !idViewerProviderMapping.containsKey(id)) {
            // only add the viewer provider if the model class can be determined
            Class<?> modelClass = viewerProvider.getModelClass();
            if (modelClass != null) {
                // insert the model class into the graph if it is not present already
                createClassNode(modelClass);
                // map the identifier on the viewer provider
                idViewerProviderMapping.put(id, viewerProvider);
                // insert the viewer provider
                createViewerProviderDatas(viewerProvider, modelClass);
            }
        }
    }

    /**
     * Returns the transformation with the given identifier.
     * 
     * @param id
     *            the identifier
     * @return the transformation or null if there is no transformation with the given id
     */
    public ITransformation<?, ?> getTransformationById(final String id) {
        if (id == null) {
            return null;
        }
        return idTransformationMapping.get(id);
    }

    /**
     * Returns the viewer provider with the given identifier.
     * 
     * @param id
     *            the identifier
     * @return the viewer provider or null if there is no viewer provider with the given id
     */
    public IViewerProvider getViewerProviderById(final String id) {
        if (id == null) {
            return null;
        }
        return idViewerProviderMapping.get(id);
    }

    /**
     * Configures the given view context for the specified model.
     * 
     * @param engine
     *            the transformation engine for performing transformations
     * @param viewContext
     *            the view context
     * @param model
     *            the model
     * @return true if the context has been configured; false else
     */
    public boolean configureViewContext(final ITransformationEngine engine,
            final ViewContext viewContext, final Object model) {
        if (model == null) {
            return false;
        }

        // find all suitable source class nodes
        List<ClassNode> sourceNodes = findClassNodes(model.getClass());

        // find all possible paths suitable for the model
        List<Path> paths = findAllPathsToViewers(sourceNodes);

        // configure the view context using the collected paths
        return configureViewContext(engine, viewContext, paths, model);
    }

    /**
     * Configures the given view context for the specified model and model transformations.<br>
     * The model transformations have to be in the order in which they should be invoked in the
     * configured view context.
     * 
     * @param engine
     *            the transformation engine for performing transformations
     * @param viewContext
     *            the view context
     * @param model
     *            the model
     * @param modelTransformations
     *            the model transformations
     * @return true if the context has been configured; false else
     */
    public boolean configureViewContext(final ITransformationEngine engine,
            final ViewContext viewContext, final Object model,
            final ITransformation<?, ?>... modelTransformations) {
        if (model == null) {
            return false;
        }

        // make sure at least one transformation has been given
        if (modelTransformations.length == 0) {
            return configureViewContext(engine, viewContext, model);
        }

        // find all suitable source class nodes
        List<ClassNode> sourceNodes = findClassNodes(model.getClass());

        // find all paths which use the edges of all the given transformations
        List<Path> paths = findAllPathsWithTransformations(sourceNodes, modelTransformations);

        // find all paths which lead to a viewer
        List<Path> newPaths = Lists.newLinkedList();
        for (Path path : paths) {
            ClassNode sourceNode = path.edges.getLast().target;
            newPaths.addAll(combinePaths(path, findAllPathsToViewers(sourceNode)));
        }

        // configure the view context using the collected paths
        return configureViewContext(engine, viewContext, newPaths, model);
    }

    /**
     * Configures the given view context for the specified model and viewer provider.
     * 
     * @param engine
     *            the transformation engine for performing transformations
     * @param viewContext
     *            the view context
     * @param model
     *            the model
     * @param viewerProvider
     *            the viewer provider
     * @return true if the context has been configured; false else
     */
    public boolean configureViewContext(final ITransformationEngine engine,
            final ViewContext viewContext, final Object model, final IViewerProvider viewerProvider) {
        if (model == null) {
            return false;
        }

        // find all suitable source class nodes
        List<ClassNode> sourceNodes = findClassNodes(model.getClass());

        // get all suitable viewer provider data
        List<ViewerProviderData> viewerProviderDatas =
                viewerProviderDataMapping.get(viewerProvider);

        // find all possible paths
        List<Path> paths = Lists.newLinkedList();
        for (ViewerProviderData viewerProviderData : viewerProviderDatas) {
            paths.addAll(findAllPaths(sourceNodes, viewerProviderData.classNode));
        }

        // configure the view context using the collected paths
        if (configureViewContext(engine, viewContext, paths, model)) {
            // make sure the correct viewer provider is set
            viewContext.setViewerProvider(viewerProvider);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Configures the given view context for the specified model, model transformations and viewer
     * provider.<br>
     * The model transformations have to be in the order in which they should be invoked in the
     * configured view context.
     * 
     * @param engine
     *            the transformation engine for performing transformations
     * @param viewContext
     *            the view context
     * @param model
     *            the model
     * @param viewerProvider
     *            the viewer provider
     * @param modelTransformations
     *            the model transformation
     * @return true if the context has been configured; false else
     */
    public boolean configureViewContext(final ITransformationEngine engine,
            final ViewContext viewContext, final Object model,
            final IViewerProvider viewerProvider,
            final ITransformation<?, ?>... modelTransformations) {
        if (model == null) {
            return false;
        }

        // make sure at least one transformation has been given
        if (modelTransformations.length == 0) {
            return configureViewContext(engine, viewContext, model, viewerProvider);
        }

        // find all suitable source class nodes
        List<ClassNode> sourceNodes = findClassNodes(model.getClass());

        // find all paths which use the edges of all the given transformations
        List<Path> paths = findAllPathsWithTransformations(sourceNodes, modelTransformations);

        // get all suitable viewer provider datas
        List<ViewerProviderData> viewerProviderDatas =
                viewerProviderDataMapping.get(viewerProvider);

        // find all paths which lead to the viewer
        List<Path> newPaths = Lists.newLinkedList();
        for (Path path : paths) {
            for (ViewerProviderData viewerProviderData : viewerProviderDatas) {
                ClassNode sourceNode = path.edges.getLast().target;
                ClassNode targetNode = viewerProviderData.classNode;
                newPaths.addAll(combinePaths(path, findAllPaths(sourceNode, targetNode)));
            }
        }

        // configure the view context using the collected paths
        if (configureViewContext(engine, viewContext, newPaths, model)) {
            // make sure the correct viewer provider is set
            viewContext.setViewerProvider(viewerProvider);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Configures the given view context with the shortest path in the list of paths which supports
     * the model. Assumes path integrity and available viewer provider data on the target class
     * node.
     * 
     * @param engine
     *            the transformation engine
     * @param viewContext
     *            the view context
     * @param paths
     *            the list of paths
     * @param model
     *            the model
     * @return true if the view context has been configured with one of the paths; false else
     */
    private boolean configureViewContext(final ITransformationEngine engine,
            final ViewContext viewContext, final List<Path> paths, final Object model) {
        viewContext.reset();
        // sort the list - shortest path first
        sortListOfPaths(paths);
        // perform the transformations until a complete path is supported
        for (Path path : paths) {
            // initialize the view context with transformation contexts
            if (path.edges.size() > 0) {
                for (TransformationEdge edge : path.edges) {
                    TransformationContext<?, ?> transformationContext =
                            TransformationContext.create(edge.transformation);
                    viewContext.addTransformationContext(transformationContext);
                }
                // set the viewer provider
                ClassNode targetNode = path.edges.get(path.edges.size() - 1).target;
                // the list of viewer providers is never empty for paths passed to this method
                viewContext.setViewerProvider(targetNode.viewerProviders.get(0).viewerProvider);
            } else {
                TransformationContext<?, ?> transformationContext =
                        TransformationContext.create(new IdentityTransformation());
                viewContext.addTransformationContext(transformationContext);
                // set the viewer provider
                viewContext
                        .setViewerProvider(path.sourceNode.viewerProviders.get(0).viewerProvider);
            }
            // perform the transformation if possible
            if (engine.tryTransform(viewContext, model)) {
                return true;
            }
            viewContext.reset();
        }
        return false;
    }

    /**
     * Returns a list of all class nodes which represent classes that can be assigned instances of
     * the given class.
     * 
     * @param clazz
     *            the class
     * @return the list of class nodes
     */
    private List<ClassNode> findClassNodes(final Class<?> clazz) {
        List<ClassNode> classNodes = Lists.newLinkedList();
        Collection<ClassNode> allClassNodes = classNodeMapping.values();
        // find class nodes representing classes which can be assigned instances of the parameter
        for (ClassNode classNode : allClassNodes) {
            if (classNode.clazz.isAssignableFrom(clazz)) {
                classNodes.add(classNode);
            }
        }
        return classNodes;
    }

    /**
     * Returns a list of paths which start in the source nodes and use all edges for the given
     * transformations in the given order.
     * 
     * @param sourceNodes
     *            the source nodes
     * @param modelTransformations
     *            the transformations
     * @return the list of paths
     */
    private List<Path> findAllPathsWithTransformations(final List<ClassNode> sourceNodes,
            final ITransformation<?, ?>... modelTransformations) {
        // find all possible paths suitable for the model and the transformations
        List<Path> paths = Lists.newLinkedList();

        // add the first segment of the paths (from source nodes to edge sources)
        Iterator<ITransformation<?, ?>> iterator = Arrays.asList(modelTransformations).iterator();
        List<TransformationEdge> transformationEdges =
                transformationEdgeMapping.get(iterator.next());
        for (TransformationEdge transformationEdge : transformationEdges) {
            paths.addAll(combinePaths(findAllPaths(sourceNodes, transformationEdge.source),
                    transformationEdge));
        }

        // add segments for the transformations
        while (iterator.hasNext()) {
            transformationEdges = transformationEdgeMapping.get(iterator.next());
            List<Path> newPaths = Lists.newLinkedList();
            // a mapping from the pair of source and target node on the connecting paths
            Map<Pair<ClassNode, ClassNode>, List<Path>> nodePathMap = Maps.newHashMap();
            for (TransformationEdge transformationEdge : transformationEdges) {
                for (Path path : paths) {
                    ClassNode sourceNode = path.edges.getLast().target;
                    ClassNode targetNode = transformationEdge.source;
                    // get the paths from the map or find the paths and map them
                    Pair<ClassNode, ClassNode> pair =
                            new Pair<ClassNode, ClassNode>(sourceNode, targetNode);
                    List<Path> sufPaths = nodePathMap.get(pair);
                    if (sufPaths == null) {
                        sufPaths = findAllPaths(sourceNode, targetNode);
                        nodePathMap.put(pair, sufPaths);
                    }
                    newPaths.addAll(combinePaths(combinePaths(path, sufPaths), transformationEdge));
                }
            }
            paths = newPaths;
        }
        return paths;
    }

    /**
     * Computes a list of paths from the start nodes to the target node.
     * 
     * @param startNodes
     *            the start nodes
     * @param targetNode
     *            the target node
     * @return the list of paths
     */
    private List<Path> findAllPaths(final List<ClassNode> startNodes, final ClassNode targetNode) {
        List<Path> sufPaths = Lists.newLinkedList();
        for (ClassNode sourceNode : startNodes) {
            sufPaths.addAll(findAllPaths(sourceNode, targetNode));
        }
        return sufPaths;
    }

    /**
     * Computes a list of all paths from the start to the target class node.
     * 
     * @param start
     *            the start node
     * @param target
     *            the target node
     * @return the list of paths
     */
    private List<Path> findAllPaths(final ClassNode start, final ClassNode target) {
        List<Path> paths = Lists.newLinkedList();
        Path path = new Path();
        path.sourceNode = start;
        findAllPathsRec(paths, path, start, target);
        return paths;
    }

    private void findAllPathsRec(final List<Path> paths, final Path path, final ClassNode node,
            final ClassNode target) {
        // prevent running into cycles
        if ((node.attributes & VISITED) > 0) {
            return;
        }
        // if the current node is the target node stop searching this branch and remember the path
        if (node == target) {
            paths.add((Path) path.clone());
            return;
        }
        // mark the node as visited
        node.attributes |= VISITED;
        // proceed with the recursive search
        for (TransformationEdge edge : node.outgoingTransformations) {
            path.edges.add(edge);
            findAllPathsRec(paths, path, edge.target, target);
            path.edges.removeLast();
        }
        // mark the node as not visited
        node.attributes &= ~VISITED;
    }

    /**
     * Computes a list of all paths from start nodes to nodes which have attached viewer provider
     * data. Does not compute paths which contain nodes that are not the target node with attached
     * viewer provider data.
     * 
     * @param start
     *            the start node
     * @return the list of paths
     */
    private List<Path> findAllPathsToViewers(final List<ClassNode> startNodes) {
        List<Path> paths = Lists.newLinkedList();
        for (ClassNode node : startNodes) {
            paths.addAll(findAllPathsToViewers(node));
        }
        return paths;
    }

    /**
     * Computes a list of all paths from the start node to nodes which have attached viewer provider
     * data. Does not compute paths which contain nodes that are not the target node with attached
     * viewer provider data.
     * 
     * @param start
     *            the start node
     * @return the list of paths
     */
    private List<Path> findAllPathsToViewers(final ClassNode start) {
        List<Path> paths = Lists.newLinkedList();
        Path path = new Path();
        path.sourceNode = start;
        findAllPathsToViewersRec(paths, path, start);
        return paths;
    }

    private void findAllPathsToViewersRec(final List<Path> paths, final Path path,
            final ClassNode node) {
        // prevent running into cycles
        if ((node.attributes & VISITED) > 0) {
            return;
        }
        // if the current node has viewer provider data attached stop searching this branch and
        // remember the path
        if (node.viewerProviders.size() > 0) {
            paths.add((Path) path.clone());
            return;
        }
        // mark the node as visited
        node.attributes |= VISITED;
        // proceed with the recursive search
        for (TransformationEdge edge : node.outgoingTransformations) {
            path.edges.add(edge);
            findAllPathsToViewersRec(paths, path, edge.target);
            path.edges.removeLast();
        }
        // mark the node as not visited
        node.attributes &= ~VISITED;
    }

    /**
     * Combines a list of given lists with a single edge by appending the edge.
     * 
     * @param prePaths
     *            the prefix paths
     * @param edge
     *            the transformation edge
     * @return the combined list of paths
     */
    private static List<Path> combinePaths(final List<Path> prePaths, final TransformationEdge edge) {
        if (prePaths.size() == 0) {
            Path path = new Path();
            path.sourceNode = edge.source;
            path.edges.add(edge);
            List<Path> paths = Lists.newLinkedList();
            paths.add(path);
            return paths;
        }
        List<Path> paths = Lists.newLinkedList();
        for (Path prePath : prePaths) {
            Path path = (Path) prePath.clone();
            path.edges.add(edge);
            paths.add(path);
        }
        return paths;
    }

    /**
     * Combines a path with a given listsof paths by concatenating the paths.
     * 
     * @param prePath
     *            the prefix path
     * @param sufPaths
     *            the suffix paths
     * @return the combined list of paths
     */
    private static List<Path> combinePaths(final Path prePath, final List<Path> sufPaths) {
        List<Path> paths = Lists.newLinkedList();
        for (Path sufPath : sufPaths) {
            Path path = (Path) prePath.clone();
            path.edges.addAll(sufPath.edges);
            paths.add(path);
        }
        return paths;
    }

    /**
     * Sorts a list of paths by the number of edges in the paths.
     * 
     * @param listOfPaths
     *            the list of paths
     */
    private static void sortListOfPaths(final List<Path> listOfPaths) {
        Collections.sort(listOfPaths, new Comparator<Path>() {
            public int compare(final Path o1, final Path o2) {
                return o1.edges.size() - o2.edges.size();
            }
        });
    }

    /**
     * Creates a new class node for the given class if it does not exist already.
     * 
     * @param clazz
     *            the class
     */
    private void createClassNode(final Class<?> clazz) {
        ClassNode node = classNodeMapping.get(clazz);
        if (node == null) {
            node = new ClassNode();
            node.clazz = clazz;
            classNodeMapping.put(clazz, node);
            // attach all required viewer provider data
            updateViewerProviderDatas(node);
            // connect the node using all possible transformation edges
            updateTransformationEdges(node);
        }
    }

    /**
     * Creates transformation edges between all class nodes which are supported by the given
     * transformation.
     * 
     * @param transformation
     *            the transformation
     * @param source
     *            the source class
     * @param targetClass
     *            the target class
     */
    private void createTransformationEdges(final ITransformation<?, ?> transformation,
            final Class<?> sourceClass, final Class<?> targetClass) {
        transformationEdgeMapping.put(transformation, new LinkedList<TransformationEdge>());
        Collection<ClassNode> classNodes = classNodeMapping.values();
        LinkedList<ClassNode> targetClassNodes = Lists.newLinkedList();
        transformationTargetNodeMapping.put(transformation, targetClassNodes);
        // collect all suitable target class nodes
        for (ClassNode classNode : classNodes) {
            if (classNode.clazz.isAssignableFrom(targetClass)) {
                targetClassNodes.add(classNode);
            }
        }
        // connect all suitable source class nodes
        List<ClassNode> sourceClassNodes = Lists.newLinkedList();
        transformationSourceNodeMapping.put(transformation, sourceClassNodes);
        for (ClassNode source : classNodes) {
            if (sourceClass.isAssignableFrom(source.clazz)) {
                sourceClassNodes.add(source);
                // create the edges
                for (ClassNode target : targetClassNodes) {
                    createTransformationEdge(transformation, source, target);
                }
            }
        }
    }

    /**
     * Creates a transformation edge between the given source and target node.
     * 
     * @param transformation
     *            the transformation
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @return the transformation edge
     */
    private void createTransformationEdge(final ITransformation<?, ?> transformation,
            final ClassNode source, final ClassNode target) {
        TransformationEdge transformationEdge = new TransformationEdge();
        transformationEdgeMapping.get(transformation).add(transformationEdge);
        transformationEdge.transformation = transformation;
        transformationEdge.source = source;
        transformationEdge.target = target;
        source.outgoingTransformations.add(transformationEdge);
    }

    /**
     * Connects the class node to other class nodes if a transformation for an edge between the
     * nodes exists.
     * 
     * @param classNode
     *            the class node
     */
    private void updateTransformationEdges(final ClassNode classNode) {
        Class<?> clazz = classNode.clazz;
        for (ITransformation<?, ?> transformation : idTransformationMapping.values()) {
            // insert edges with the class node as source if required
            if (transformation.getSourceClass().isAssignableFrom(clazz)) {
                // create the edges
                List<ClassNode> targetNodes = transformationTargetNodeMapping.get(transformation);
                for (ClassNode targetNode : targetNodes) {
                    createTransformationEdge(transformation, classNode, targetNode);
                }
                // add to the possible source nodes of the transformation
                transformationSourceNodeMapping.get(transformation).add(classNode);
            }

            // insert edges with the class node as target if required
            if (clazz.isAssignableFrom(transformation.getTargetClass())) {
                // create the edges
                List<ClassNode> sourceNodes = transformationSourceNodeMapping.get(transformation);
                for (ClassNode sourceNode : sourceNodes) {
                    createTransformationEdge(transformation, sourceNode, classNode);
                }
                // add to the possible source nodes of the transformation
                transformationTargetNodeMapping.get(transformation).add(classNode);
            }
        }
    }

    /**
     * Attaches viewer provider data to all class nodes which are supported by the given viewer
     * provider.
     * 
     * @param viewerProvider
     *            the viewer provider
     * @param modelClass
     *            the model class
     */
    private void createViewerProviderDatas(final IViewerProvider viewerProvider,
            final Class<?> modelClass) {
        viewerProviderDataMapping.put(viewerProvider, new LinkedList<ViewerProviderData>());
        Collection<ClassNode> classNodes = classNodeMapping.values();
        // attach to all suitable class nodes
        for (ClassNode classNode : classNodes) {
            if (modelClass.isAssignableFrom(classNode.clazz)) {
                createViewerProviderData(viewerProvider, classNode);
            }
        }
    }

    /**
     * Creates viewer provider data on the given class node.
     * 
     * @param viewerProvider
     *            the viewer provider
     * @param classNode
     *            the class node
     * @return the viewer provider data
     */
    private void createViewerProviderData(final IViewerProvider viewerProvider,
            final ClassNode classNode) {
        ViewerProviderData viewerProviderData = new ViewerProviderData();
        viewerProviderDataMapping.get(viewerProvider).add(viewerProviderData);
        viewerProviderData.viewerProvider = viewerProvider;
        viewerProviderData.classNode = classNode;
        classNode.viewerProviders.add(viewerProviderData);
    }

    /**
     * Attaches viewer provider data to the class node for registered viewer providers which support
     * the class represented by the class node.
     * 
     * @param classNode
     *            the class node
     */
    private void updateViewerProviderDatas(final ClassNode classNode) {
        Class<?> clazz = classNode.clazz;
        for (IViewerProvider viewerProvider : idViewerProviderMapping.values()) {
            if (viewerProvider.getModelClass().isAssignableFrom(clazz)) {
                createViewerProviderData(viewerProvider, classNode);
            }
        }
    }

    /**
     * The class for nodes in this graph representing model classes.
     */
    private static class ClassNode {

        /** the model class. */
        private Class<?> clazz;
        /** the transformations which take instances of the model class as source. */
        private LinkedList<TransformationEdge> outgoingTransformations = Lists.newLinkedList();
        /** the viewer providers which support model instances of the model class. */
        private LinkedList<ViewerProviderData> viewerProviders = Lists.newLinkedList();

        private int attributes = 0;

    }

    /**
     * The class representing viewer providers in this graph.
     */
    private static class ViewerProviderData {

        /** the viewer provider. */
        private IViewerProvider viewerProvider;
        /** the class node this viewer provider data attaches to. */
        private ClassNode classNode;

    }

    /**
     * The class for edges in this graph representing model transformations.
     */
    private static class TransformationEdge {

        /** the model transformation. */
        private ITransformation<?, ?> transformation;
        /** the class node representing the source model class for the transformation. */
        private ClassNode source;
        /** the class node representing the target model class for the transformation. */
        private ClassNode target;

    }

    /**
     * The class for paths in this graph.
     */
    private static class Path {

        /** the source class node of the path. */
        private ClassNode sourceNode;
        /** the transformation edges of the path. */
        private LinkedList<TransformationEdge> edges = Lists.newLinkedList();

        /**
         * {@inheritDoc}
         */
        @Override
        public Object clone() {
            Path clone = new Path();
            clone.sourceNode = sourceNode;
            clone.edges.addAll(edges);
            return clone;
        }

    }

}
