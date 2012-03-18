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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

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

    /** the transformations.. */
    private Set<ITransformation<?, ?>> transformations = Sets.newLinkedHashSet();
    /** the mapping of transformation id's on transformation edges. */
    private Map<ITransformation<?, ?>, List<TransformationEdge>> transformationEdgeMapping = Maps
            .newHashMap();
    /** the mapping of transformations on class nodes representing source classes. */
    private Map<ITransformation<?, ?>, List<ClassNode>> transformationSourceNodeMapping = Maps
            .newHashMap();
    /** the mapping of transformations on class nodes representing target classes. */
    private Map<ITransformation<?, ?>, List<ClassNode>> transformationTargetNodeMapping = Maps
            .newHashMap();

    /** the viewer provider data. */
    private Set<IViewerProvider<?>> viewerProviders = Sets.newLinkedHashSet();
    /** the mapping of viewer providers on viewer provider data. */
    private Map<IViewerProvider<?>, List<ViewerProviderData>> viewerProviderDataMapping = Maps
            .newHashMap();
    
    /** the update strategies. */
    private Set<IUpdateStrategy<?>> updateStrategies = Sets.newLinkedHashSet();
    
    /**
     * Adds a model transformation to the graph.
     * 
     * @param transformation
     *            the model transformation
     */
    public void addTransformation(final ITransformation<?, ?> transformation) {
        if (!transformations.contains(transformation)) {
            Class<?> sourceClass = transformation.getSourceClass();
            Class<?> targetClass = transformation.getTargetClass();
            // only add the transformation if both source and target class can be determined
            if (sourceClass != null && targetClass != null) {
                // insert the classes into the graph if they are not present already
                createClassNode(sourceClass);
                createClassNode(targetClass);
                // insert the transformation
                createTransformationEdges(transformation, sourceClass, targetClass);
                transformations.add(transformation);
            }
        }
    }

    /**
     * Adds a viewer provider to the graph.

     * @param viewerProvider
     *            the viewer provider
     */
    public void addViewerProvider(final IViewerProvider<?> viewerProvider) {
        if (!viewerProviders.contains(viewerProvider)) {
            // only add the viewer provider if the model class can be determined
            Class<?> modelClass = viewerProvider.getModelClass();
            if (modelClass != null) {
                // insert the model class into the graph if it is not present already
                createClassNode(modelClass);
                // insert the viewer provider
                createViewerProviderDatas(viewerProvider, modelClass);
                viewerProviders.add(viewerProvider);
            }
        }
    }
    
    /**
     * Adds an update strategy to the graph.
     * 
     * @param updateStrategy
     *            the update strategy
     */
    public void addUpdateStrategy(final IUpdateStrategy<?> updateStrategy) {
        if (updateStrategies.add(updateStrategy)) {
            // only add the update strategy if the model class can be determined
            Class<?> modelClass = updateStrategy.getModelClass();
            if (modelClass != null) {
                // insert the update strategy
                attachUpdateStrategy(updateStrategy, modelClass);
            } else {
                updateStrategies.remove(updateStrategy);
            }
        }
    }

    /**
     * Configures the given view context for the specified model. Optionally an update strategy can
     * be specified.
     * 
     * @param viewContext
     *            the view context
     * @param model
     *            the model
     * @param updateStrategy
     *            the update strategy or null
     *            
     * @return true if the context has been configured; false else
     */
    public boolean configureViewContext(final ViewContext viewContext, final Object model,
            final IUpdateStrategy<?> updateStrategy) {
        if (model == null) {
            return false;
        }

        // find all suitable source class nodes
        List<ClassNode> sourceNodes = findClassNodes(model.getClass());

        // find all possible paths suitable for the model
        List<Path> paths = findAllPathsToViewers(sourceNodes);

        // configure the view context using the collected paths
        return configureViewContext(viewContext, paths, model, updateStrategy);
    }

    /**
     * Configures the given view context for the specified model and model transformations.<br>
     * The model transformations have to be in the order in which they should be invoked in the
     * configured view context. Optionally an update strategy can be specified.
     * 
     * @param viewContext
     *            the view context
     * @param model
     *            the model
     * @param updateStrategy
     *            the update strategy or null
     * @param modelTransformations
     *            the model transformations
     * @return true if the context has been configured; false else
     */
    public boolean configureViewContext(final ViewContext viewContext, final Object model,
            final IUpdateStrategy<?> updateStrategy,
            final ITransformation<?, ?>... modelTransformations) {
        if (model == null) {
            return false;
        }

        // make sure at least one transformation has been given
        if (modelTransformations.length == 0) {
            return configureViewContext(viewContext, model, updateStrategy);
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
        return configureViewContext(viewContext, newPaths, model, updateStrategy);
    }

    /**
     * Configures the given view context for the specified model and viewer provider. Optionally an
     * update strategy can be specified.
     * 
     * @param viewContext
     *            the view context
     * @param model
     *            the model
     * @param viewerProvider
     *            the viewer provider
     * @param updateStrategy
     *            the update strategy or null
     * @return true if the context has been configured; false else
     */
    public boolean configureViewContext(final ViewContext viewContext, final Object model,
            final IViewerProvider<?> viewerProvider, final IUpdateStrategy<?> updateStrategy) {
        if (model == null) {
            return false;
        }

        // find all suitable source class nodes
        List<ClassNode> sourceNodes = findClassNodes(model.getClass());

        // get all suitable viewer provider data
        List<ViewerProviderData> viewerProviderDatas = viewerProviderDataMapping
                .get(viewerProvider);

        // find all possible paths
        List<Path> paths = Lists.newLinkedList();
        for (ViewerProviderData viewerProviderData : viewerProviderDatas) {
            paths.addAll(findAllPaths(sourceNodes, viewerProviderData.classNode));
        }

        // configure the view context using the collected paths
        if (configureViewContext(viewContext, paths, model, updateStrategy)) {
            // make sure the correct viewer provider is set
            viewContext.setViewerProvider(viewerProvider);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Configures the given view context for the specified model, model transformations and viewer
     * provider. Optionally an update strategy can be specified. <br>
     * The model transformations have to be in the order in which they should be invoked in the
     * configured view context.
     * 
     * @param viewContext
     *            the view context
     * @param model
     *            the model
     * @param viewerProvider
     *            the viewer provider
     * @param updateStrategy
     *            the update strategy or null
     * @param modelTransformations
     *            the model transformation
     * @return true if the context has been configured; false else
     */
    public boolean configureViewContext(final ViewContext viewContext, final Object model,
            final IViewerProvider<?> viewerProvider, final IUpdateStrategy<?> updateStrategy,
            final ITransformation<?, ?>... modelTransformations) {
        if (model == null) {
            return false;
        }

        // make sure at least one transformation has been given
        if (modelTransformations.length == 0) {
            return configureViewContext(viewContext, model, viewerProvider, updateStrategy);
        }

        // find all suitable source class nodes
        List<ClassNode> sourceNodes = findClassNodes(model.getClass());

        // find all paths which use the edges of all the given transformations
        List<Path> paths = findAllPathsWithTransformations(sourceNodes, modelTransformations);

        // get all suitable viewer provider datas
        List<ViewerProviderData> viewerProviderDatas = viewerProviderDataMapping
                .get(viewerProvider);

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
        if (configureViewContext(viewContext, newPaths, model, updateStrategy)) {
            // make sure the correct viewer provider is set
            viewContext.setViewerProvider(viewerProvider);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Configures the given view context with the shortest path in the list of paths. Assumes path
     * integrity and available viewer provider data on the target class node.
     * 
     * @param viewContext
     *            the view context
     * @param paths
     *            the list of paths
     * @param model
     *            the model
     * @param updateStrategy
     *            the update strategy or null
     * @return true if the view context has been configured with one of the paths; false else
     */
    private boolean configureViewContext(final ViewContext viewContext, final List<Path> paths,
            final Object model, final IUpdateStrategy<?> updateStrategy) {
        viewContext.reset();
        // get the shortest path
        Path path = getShortestPath(paths);
        if (path != null) {
            // initialize the view context with transformation contexts
            ClassNode targetNode;
            if (path.edges.size() > 0) {
                for (TransformationEdge edge : path.edges) {
                    TransformationContext<?, ?> transformationContext = TransformationContext
                            .create(edge.transformation);
                    viewContext.addTransformationContext(transformationContext);
                }
                // set the target node
                targetNode = path.edges.get(path.edges.size() - 1).target;
            } else {
                TransformationContext<?, ?> transformationContext = TransformationContext
                        .create(new IdentityTransformation());
                viewContext.addTransformationContext(transformationContext);
                // set the target node
                targetNode = path.sourceNode;
            }
            
            // set the viewer provider
            ViewerProviderData viewerProviderData = targetNode.viewerProviders.get(0);
            viewContext.setViewerProvider(viewerProviderData.viewerProvider);
            
            // set the update strategy
            if (updateStrategy != null) {
                if (viewerProviderData.updateStrategies.contains(updateStrategy)) {
                    viewContext.setUpdateStrategy(updateStrategy);
                } else if (viewerProviderData.updateStrategies.size() > 0) {
                    viewContext.setUpdateStrategy(viewerProviderData.updateStrategies.get(0));

                }
            } else {
                if (viewerProviderData.updateStrategies.size() > 0) {
                    viewContext.setUpdateStrategy(viewerProviderData.updateStrategies.get(0));
                }
            }
            
            return true;            
        } else {
            return false;
        }
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
        List<TransformationEdge> transformationEdges = transformationEdgeMapping.get(iterator
                .next());
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
                    Pair<ClassNode, ClassNode> pair = new Pair<ClassNode, ClassNode>(sourceNode,
                            targetNode);
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
     * Returns the shortest path from a list of paths, that is the path with the fewest edges.
     * 
     * @param listOfPaths
     *            the list of paths
     */
    private static Path getShortestPath(final List<Path> listOfPaths) {
        if (listOfPaths.size() == 0) {
            return null;
        }
        Path shortestPath = listOfPaths.get(0);
        for (Path path : listOfPaths) {
            if (path.edges.size() < shortestPath.edges.size()) {
                shortestPath = path;
            }
        }
        return shortestPath;
    }

    /**
     * Creates a new class node for the given class if it does not exist already.
     * 
     * @param clazz
     *            the class
     * @return the class node
     */
    private ClassNode createClassNode(final Class<?> clazz) {
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
        return node;
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
        for (ITransformation<?, ?> transformation : transformations) {
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
    private void createViewerProviderDatas(final IViewerProvider<?> viewerProvider,
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
    private void createViewerProviderData(final IViewerProvider<?> viewerProvider,
            final ClassNode classNode) {
        ViewerProviderData viewerProviderData = new ViewerProviderData();
        viewerProviderDataMapping.get(viewerProvider).add(viewerProviderData);
        viewerProviderData.viewerProvider = viewerProvider;
        viewerProviderData.classNode = classNode;
        updateUpdateStrategies(viewerProviderData);
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
        for (IViewerProvider<?> viewerProvider : viewerProviders) {
            if (viewerProvider.getModelClass().isAssignableFrom(clazz)) {
                createViewerProviderData(viewerProvider, classNode);
            }
        }
    }

    /**
     * Attaches an update strategy to all viewer provider datas which support the update strategy.
     * 
     * @param updateStrategy
     *            the update strategy
     * @param modelClass
     *            the model class
     */
    private void attachUpdateStrategy(final IUpdateStrategy<?> updateStrategy,
            final Class<?> modelClass) {
        for (List<ViewerProviderData> viewerProviderDatas : viewerProviderDataMapping.values()) {
            for (ViewerProviderData viewerProviderData : viewerProviderDatas) {
                Class<?> viewerProviderDataClass = viewerProviderData.classNode.clazz;
                if (modelClass.isAssignableFrom(viewerProviderDataClass)) {
                    Class<?> viewerProviderClass = viewerProviderData.viewerProvider.getModelClass();
                    if (viewerProviderClass.isAssignableFrom(modelClass)) {
                        viewerProviderData.updateStrategies.add(updateStrategy);
                    }
                }
            }
        }
    }
    
    /**
     * Attaches update strategies to the viewer provider data if supported.
     * 
     * @param viewerProviderData
     *            the viewer provider data
     */
    private void updateUpdateStrategies(final ViewerProviderData viewerProviderData) {
        for (IUpdateStrategy<?> updateStrategy : updateStrategies) {
            Class<?> modelClass = updateStrategy.getModelClass();
            Class<?> viewerProviderDataClass = viewerProviderData.classNode.clazz;
            if (modelClass.isAssignableFrom(viewerProviderDataClass)) {
                Class<?> viewerProviderClass = viewerProviderData.viewerProvider.getModelClass();
                if (viewerProviderClass.isAssignableFrom(modelClass)) {
                    viewerProviderData.updateStrategies.add(updateStrategy);
                }
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
        private IViewerProvider<?> viewerProvider;
        /** the update strategies which support model instances of the model class. */
        private LinkedList<IUpdateStrategy<?>> updateStrategies = Lists.newLinkedList();
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
