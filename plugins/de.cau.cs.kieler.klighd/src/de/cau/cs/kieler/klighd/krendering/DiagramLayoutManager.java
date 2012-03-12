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
package de.cau.cs.kieler.klighd.krendering;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.BiMap;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.util.KGraphSwitch;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KDirectPlacementData;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KPlacement;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPolylinePlacementData;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KStackPlacement;
import de.cau.cs.kieler.core.krendering.KStackPlacementData;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.krendering.util.KRenderingSwitch;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.viewers.KlighdViewer;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;

/**
 * A diagram layout manager for KLighD viewers which support instances of {@code KNode}.<br>
 * <br>
 * If the {@code KNode} instances have attached {@code KRendering} data the manager uses it to
 * compute the node insets if not set otherwise.
 * 
 * @author mri
 */
public class DiagramLayoutManager implements IDiagramLayoutManager<KGraphElement> {

    /** the list of edges found in the graph. */
    public static final IProperty<List<KEdge>> EDGES =
            new Property<List<KEdge>>("krendering.edges");

    /** the property layout config. */
    private ILayoutConfig propertyLayoutConfig = new KGraphPropertyLayoutConfig();
    
    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object object) {
        // KGraph instances are supported
        if (object instanceof KNode) {
            return true;
        }
        // KGraph viewer are supported
        ViewContext viewContext = null;
        if (object instanceof DiagramViewPart) {
            DiagramViewPart view = (DiagramViewPart) object;
            viewContext = view.getContextViewer().getCurrentViewContext();
        } else if (object instanceof ContextViewer) {
            ContextViewer contextViewer = (ContextViewer) object;
            viewContext = contextViewer.getCurrentViewContext();
        } else if (object instanceof KlighdViewer) {
            KlighdViewer klighdViewer = (KlighdViewer) object;
            viewContext = klighdViewer.getContextViewer().getCurrentViewContext();
        }
        if (viewContext != null) {
            return viewContext.getViewerProvider().getModelClass().equals(KNode.class);
        } else if (object instanceof IViewer<?>) {
            IViewer<?> viewer = (IViewer<?>) object;
            Object model = viewer.getModel();
            return model instanceof KNode;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutMapping<KGraphElement> buildLayoutGraph(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        KNode graph = null;

        // search for the root node
        if (diagramPart instanceof KNode) {
            graph = (KNode) diagramPart;
        } else {
            IViewer<?> viewer = null;
            if (workbenchPart instanceof DiagramViewPart) {
                DiagramViewPart view = (DiagramViewPart) workbenchPart;
                viewer = view.getContextViewer().getActiveViewer();
            } else if (diagramPart instanceof ContextViewer) {
                ContextViewer contextViewer = (ContextViewer) diagramPart;
                viewer = contextViewer.getActiveViewer();
            } else if (diagramPart instanceof KlighdViewer) {
                KlighdViewer klighdViewer = (KlighdViewer) diagramPart;
                viewer = klighdViewer.getContextViewer().getActiveViewer();
            } else if (diagramPart instanceof IViewer<?>) {
                viewer = (IViewer<?>) diagramPart;
            }
            if (viewer != null) {
                Object model = viewer.getModel();
                if (model instanceof KNode) {
                    graph = (KNode) model;
                }
            }
        }

        // if no root node could be found
        if (graph == null) {
            throw new UnsupportedOperationException(
                    "Not supported by the KLighD KRendering layout manager: Workbench part "
                            + workbenchPart + ", diagram part " + diagramPart);
        }

        // create the mapping
        LayoutMapping<KGraphElement> mapping = buildLayoutGraph(graph);
        mapping.setProperty(EclipseLayoutConfig.ACTIVATION, false);
        
        // add the property layout config
        mapping.getLayoutConfigs().add(propertyLayoutConfig);

        return mapping;
    }

    /**
     * Builds a layout graph from the given graph.
     * 
     * @param graph
     *            the graph to build the layout graph from
     * @return the layout graph mapping
     */
    public static LayoutMapping<KGraphElement> buildLayoutGraph(final KNode graph) {
        LayoutMapping<KGraphElement> mapping = new LayoutMapping<KGraphElement>();
        mapping.setProperty(EDGES, new LinkedList<KEdge>());

        // set the parent element
        mapping.setParentElement(graph);

        KNode layoutGraph = KimlUtil.createInitializedNode();
        KShapeLayout layoutGraphShapeLayout = layoutGraph.getData(KShapeLayout.class);
        KShapeLayout graphShapeLayout = graph.getData(KShapeLayout.class);
        if (graphShapeLayout != null) {
            transferShapeLayout(graphShapeLayout, layoutGraphShapeLayout);
        }

        mapping.getGraphMap().put(layoutGraph, graph);
        mapping.setLayoutGraph(layoutGraph);

        // traverse the children of the layout root
        processNodes(mapping, graph, layoutGraph);
        // transform all connections in the selected area
        processConnections(mapping);

        return mapping;
    }

    /**
     * Processes all child nodes of the given parent node.
     * 
     * @param mapping
     *            the layout mapping
     * @param parent
     *            the parent node
     * @param layoutParent
     *            the layout parent node
     */
    private static void processNodes(final LayoutMapping<KGraphElement> mapping,
            final KNode parent, final KNode layoutParent) {
        // iterate through the children of the node
        for (KNode node : parent.getChildren()) {
            createNode(mapping, node, layoutParent);
        }
    }

    /**
     * Creates a layout node for the node inside the given layout parent node.
     * 
     * @param mapping
     *            the layout mapping
     * @param node
     *            the node
     * @param layoutParent
     *            the layout parent node
     */
    private static void createNode(final LayoutMapping<KGraphElement> mapping, final KNode node,
            final KNode layoutParent) {
        KNode layoutNode = KimlUtil.createInitializedNode();

        // set the node layout
        KShapeLayout layoutLayout = layoutNode.getData(KShapeLayout.class);
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        transferShapeLayout(nodeLayout, layoutLayout);

        // set insets if available
        KInsets layoutInsets = layoutLayout.getInsets();
        calculateInsets(node, layoutInsets);

        layoutParent.getChildren().add(layoutNode);
        mapping.getGraphMap().put(layoutNode, node);

        // process ports
        for (KPort port : node.getPorts()) {
            createPort(mapping, port, layoutNode);
        }

        // process labels
        for (KLabel label : node.getLabels()) {
            createLabel(mapping, label, layoutNode);
        }

        // process the child as new parent
        processNodes(mapping, node, layoutNode);

        // store all the edges to process them later
        List<KEdge> edges = mapping.getProperty(EDGES);
        for (KEdge edge : node.getOutgoingEdges()) {
            edges.add(edge);
        }
    }

    /**
     * Creates a layout port for the port attached to the given layout node.
     * 
     * @param mapping
     *            the layout mapping
     * @param port
     *            the port
     * @param layoutNode
     *            the layout node
     */
    private static void createPort(final LayoutMapping<KGraphElement> mapping, final KPort port,
            final KNode layoutNode) {
        KPort layoutPort = KimlUtil.createInitializedPort();

        // set the port layout
        KShapeLayout layoutLayout = layoutPort.getData(KShapeLayout.class);
        KShapeLayout portLayout = port.getData(KShapeLayout.class);
        transferShapeLayout(portLayout, layoutLayout);

        layoutPort.setNode(layoutNode);
        mapping.getGraphMap().put(layoutPort, port);

        // process labels
        for (KLabel label : port.getLabels()) {
            createLabel(mapping, label, layoutPort);
        }
    }

    /**
     * Processes all edges collected while processing nodes.
     * 
     * @param mapping
     *            the layout mapping
     */
    private static void processConnections(final LayoutMapping<KGraphElement> mapping) {
        BiMap<KGraphElement, KGraphElement> graphMap = mapping.getGraphMap().inverse();

        // iterate through the list of collected edges
        List<KEdge> edges = mapping.getProperty(EDGES);
        for (KEdge edge : edges) {
            KNode layoutSource = (KNode) graphMap.get(edge.getSource());
            KNode layoutTarget = (KNode) graphMap.get(edge.getTarget());
            if (layoutSource != null && layoutTarget != null) {
                createEdge(mapping, edge, layoutSource, layoutTarget);
            }
        }
    }

    /**
     * Creates a layout edge for the edge between the given layout source node and layout target
     * node.
     * 
     * @param mapping
     *            the layout mapping
     * @param edge
     *            the edge
     * @param layoutSource
     *            the layout source node
     * @param layoutTarget
     *            the layout target node
     */
    private static void createEdge(final LayoutMapping<KGraphElement> mapping, final KEdge edge,
            final KNode layoutSource, final KNode layoutTarget) {
        KEdge layoutEdge = KimlUtil.createInitializedEdge();

        // set the edge layout
        KEdgeLayout layoutLayout = layoutEdge.getData(KEdgeLayout.class);
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        transferEdgeLayout(edgeLayout, layoutLayout);

        layoutEdge.setSource(layoutSource);
        layoutEdge.setTarget(layoutTarget);
        mapping.getGraphMap().put(layoutEdge, edge);

        // process labels
        for (KLabel label : edge.getLabels()) {
            createLabel(mapping, label, layoutEdge);
        }
    }

    /**
     * Creates a layout label for the label attached to the given labeled layout element.
     * 
     * @param mapping
     *            the layout mapping
     * @param label
     *            the label
     * @param layoutLabeledElement
     *            the labeled layout element
     */
    private static void createLabel(final LayoutMapping<KGraphElement> mapping, final KLabel label,
            final KLabeledGraphElement layoutLabeledElement) {
        KLabel layoutLabel = KimlUtil.createInitializedLabel(layoutLabeledElement);

        // set the label layout
        KShapeLayout layoutLayout = layoutLabel.getData(KShapeLayout.class);
        KShapeLayout labelLayout = label.getData(KShapeLayout.class);
        transferShapeLayout(labelLayout, layoutLayout);

        mapping.getGraphMap().put(layoutLabel, label);
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final LayoutMapping<KGraphElement> mapping, final boolean zoomToFit,
            final int animationTime) {
        applyLayout(mapping);
    }

    /**
     * Applies the computed layout back to the graph.
     * 
     * @param mapping
     *            the layout mapping that was created by this manager
     */
    public static void applyLayout(final LayoutMapping<KGraphElement> mapping) {
        Set<Entry<KGraphElement, KGraphElement>> elementMappings = mapping.getGraphMap().entrySet();

        // apply the layout of all mapped layout elements back to the associated element
        for (Entry<KGraphElement, KGraphElement> elementMapping : elementMappings) {
            KGraphElement layoutElement = elementMapping.getKey();
            final KGraphElement element = elementMapping.getValue();

            new KGraphSwitch<Boolean>() {
                public Boolean caseKNode(final KNode layoutNode) {
                    KShapeLayout layoutLayout = layoutNode.getData(KShapeLayout.class);
                    KShapeLayout nodeLayout = element.getData(KShapeLayout.class);
                    if (nodeLayout != null) {
                        transferShapeLayout(layoutLayout, nodeLayout);
                    }
                    return true;
                }

                public Boolean caseKEdge(final KEdge layoutEdge) {
                    KEdgeLayout layoutLayout = layoutEdge.getData(KEdgeLayout.class);
                    KEdgeLayout edgeLayout = element.getData(KEdgeLayout.class);
                    if (edgeLayout != null) {
                        transferEdgeLayout(layoutLayout, edgeLayout);
                    }
                    return true;
                }

                public Boolean caseKPort(final KPort layoutPort) {
                    KShapeLayout layoutLayout = layoutPort.getData(KShapeLayout.class);
                    KShapeLayout portLayout = element.getData(KShapeLayout.class);
                    if (portLayout != null) {
                        transferShapeLayout(layoutLayout, portLayout);
                    }
                    return true;
                }

                public Boolean caseKLabel(final KLabel layoutLabel) {
                    KShapeLayout layoutLayout = layoutLabel.getData(KShapeLayout.class);
                    KShapeLayout labelLayout = element.getData(KShapeLayout.class);
                    if (labelLayout != null) {
                        transferShapeLayout(layoutLayout, labelLayout);
                    }
                    return true;
                }
            } /**/.doSwitch(layoutElement);
        }
    }

    /**
     * Transfers the source shape layout to the target shape layout.
     * 
     * @param sourceShapeLayout
     *            the source shape layout
     * @param targetShapeLayout
     *            the target shape layout
     */
    private static void transferShapeLayout(final KShapeLayout sourceShapeLayout,
            final KShapeLayout targetShapeLayout) {
        targetShapeLayout.setPos(sourceShapeLayout.getXpos(), sourceShapeLayout.getYpos());
        targetShapeLayout.setSize(sourceShapeLayout.getWidth(), sourceShapeLayout.getHeight());
    }

    /**
     * Transfers the source edge layout to the target edge layout.
     * 
     * @param sourceEdgeLayout
     *            the source edge layout
     * @param targetEdgeLayout
     *            the target edge layout
     */
    private static void transferEdgeLayout(final KEdgeLayout sourceEdgeLayout,
            final KEdgeLayout targetEdgeLayout) {
        targetEdgeLayout.setSourcePoint(copyPoint(sourceEdgeLayout.getSourcePoint()));
        targetEdgeLayout.setTargetPoint(copyPoint(sourceEdgeLayout.getTargetPoint()));
        targetEdgeLayout.getBendPoints().clear();
        for (KPoint bendPoint : sourceEdgeLayout.getBendPoints()) {
            targetEdgeLayout.getBendPoints().add(copyPoint(bendPoint));
        }
    }

    /**
     * Returns a copy of the given point.
     * 
     * @param point
     *            the point
     * @return the copy
     */
    private static KPoint copyPoint(final KPoint point) {
        KPoint copy = KLayoutDataFactory.eINSTANCE.createKPoint();
        copy.setPos(point.getX(), point.getY());
        return copy;
    }

    /**
     * Calculates the offset of the child area in the given node, which equals the insets of the
     * node.<br>
     * <br>
     * To ensure correct layout, changes in the bounds of the node are not allowed to influence the
     * insets. If this is not given the insets can be incorrect.
     * 
     * @param node
     *            the node
     * @param insets
     *            the insets
     */
    public static void calculateInsets(final KNode node, final KInsets insets) {
        KRendering rendering = node.getData(KRendering.class);

        // no rendering so the whole node is the child area
        if (rendering == null) {
            return;
        }

        // find the path to the child area
        LinkedList<KRendering> path = Lists.newLinkedList();
        if (!findChildArea(rendering, path)) {
            // no child area so the whole node is the child area
            return;
        }

        // get the shape layout for the initial bounds of the reference parent
        KShapeLayout shapeLayout = node.getData(KShapeLayout.class);
        if (shapeLayout == null) {
            return;
        }

        // the data of the reference parent
        KContainerRendering currentParent = null;
        Bounds currentBounds =
                new Bounds(0.0f, 0.0f, shapeLayout.getWidth(), shapeLayout.getHeight());

        // the calculated insets
        float leftInset = 0.0f;
        float rightInset = 0.0f;
        float topInset = 0.0f;
        float bottomInset = 0.0f;

        while (!path.isEmpty()) {
            KRendering currentRendering = path.pollFirst();

            // calculate the bounds of the current rendering
            Bounds bounds;
            if (currentParent == null) {
                bounds = calculateBounds(null, currentBounds, null, currentRendering);
            } else {
                bounds =
                        calculateBounds(currentParent.getChildPlacement(), currentBounds,
                                currentParent.getChildren(), currentRendering);
            }

            // update the insets using the new bounds
            leftInset += bounds.x;
            rightInset += currentBounds.width - bounds.x - bounds.width;
            topInset += bounds.y;
            bottomInset += currentBounds.height - bounds.y - bounds.height;

            // dereference all rendering references
            while (currentRendering instanceof KRenderingRef) {
                KRenderingRef renderingRef = (KRenderingRef) currentRendering;
                currentRendering = renderingRef.getRendering();
                path.removeFirst();
            }

            // if the rendering is a container rendering set the new current parent
            if (currentRendering instanceof KContainerRendering) {
                currentParent = (KContainerRendering) currentRendering;
                currentBounds = bounds;
            }
        }

        // set the insets
        insets.setLeft(leftInset);
        insets.setRight(rightInset);
        insets.setTop(topInset);
        insets.setBottom(bottomInset);
    }

    /**
     * Calculates the bounds of a child rendering inside a parent with given placement and bounds.
     * 
     * @param placement
     *            the placement
     * @param parentBounds
     *            the parent bounds
     * @param children
     *            the other children of the parent containing this rendering
     * @param child
     *            the child rendering
     * @return the bounds of the child rendering inside the parent rendering
     */
    private static Bounds calculateBounds(final KPlacement placement, final Bounds parentBounds,
            final List<KRendering> children, final KRendering child) {
        Bounds bounds = null;
        if (placement == null) {
            bounds =
                    evaluateDirectPlacement(asDirectPlacementData(child.getPlacementData()),
                            parentBounds);
        } else {
            bounds = new KRenderingSwitch<Bounds>() {
                public Bounds caseKGridPlacement(final KGridPlacement gridPlacement) {
                    // TODO implement this
                    return new Bounds(0, 0, parentBounds.width, parentBounds.height);
                }

                public Bounds caseKStackPlacement(final KStackPlacement stackPlacement) {
                    return evaluateStackPlacement(asStackPlacementData(child.getPlacementData()),
                            parentBounds);
                }
            } /**/.doSwitch(placement);
        }

        if (child instanceof KPolyline) {
            return evaluatePolylinePlacement(asPolylinePlacementData(child.getPlacementData()),
                    bounds);
        } else {
            return bounds;
        }
    }

    /**
     * Returns the bounds for a direct placement data in given parent bounds.
     * 
     * @param dpd
     *            the direct placement data
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    private static Bounds evaluateDirectPlacement(final KDirectPlacementData dpd,
            final Bounds parentBounds) {
        if (dpd == null) {
            return new Bounds(0, 0, parentBounds.width, parentBounds.height);
        }

        // determine the top-left
        KPosition topLeft = dpd.getTopLeft();
        Point topLeftPoint = evaluateDirectPosition(topLeft, parentBounds);

        // determine the bottom-right
        KPosition bottomRight = dpd.getBottomRight();
        Point bottomRightPoint = evaluateDirectPosition(bottomRight, parentBounds);

        return new Bounds(topLeftPoint.x, topLeftPoint.y, bottomRightPoint.x - topLeftPoint.x,
                bottomRightPoint.y - topLeftPoint.y);
    }

    /**
     * Returns the bounds for a stack placement data in given parent bounds.
     * 
     * @param spd
     *            the stack placement data
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    private static Bounds evaluateStackPlacement(final KStackPlacementData spd,
            final Bounds parentBounds) {
        float width = (float) parentBounds.width;
        float height = (float) parentBounds.height;

        if (spd == null) {
            return new Bounds(0, 0, width, height);
        }

        return new Bounds(spd.getInsetLeft(), spd.getInsetTop(), width - spd.getInsetRight(),
                height - spd.getInsetBottom());
    }

    /**
     * Returns the bounds for a polyline placement data in given parent bounds.
     * 
     * @param ppd
     *            the polyline placement data
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    private static Bounds evaluatePolylinePlacement(final KPolylinePlacementData ppd,
            final Bounds parentBounds) {
        if (ppd == null || ppd.getPoints().size() == 0) {
            return new Bounds(0, 0, parentBounds.width, parentBounds.height);
        }

        // evaluate the points of the polyline inside the parent bounds to compute the bounding box
        float minX = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;
        float minY = Float.MAX_VALUE;
        float maxY = Float.MIN_VALUE;
        for (KPosition polyPoint : ppd.getPoints()) {
            Point point = evaluateDirectPosition(polyPoint, parentBounds);
            if (point.x < minX) {
                minX = point.x;
            }
            if (point.x > maxX) {
                maxX = point.x;
            }
            if (point.y < minY) {
                minY = point.y;
            }
            if (point.y > maxY) {
                maxY = point.y;
            }
        }

        return new Bounds(minX, minY, parentBounds.width - maxX, parentBounds.height - maxY);
    }

    /**
     * Evaluates a position inside given parent bounds.
     * 
     * @param position
     *            the position
     * @param parentBounds
     *            the parent bounds
     * @return the evaluated position
     */
    private static Point evaluateDirectPosition(final KPosition position, final Bounds parentBounds) {
        float width = (float) parentBounds.width;
        float height = (float) parentBounds.height;
        Point point = new Point(0.0f, 0.0f);
        KXPosition xPos = position.getX();
        KYPosition yPos = position.getY();
        if (xPos instanceof KLeftPosition) {
            point.x = xPos.getAbsolute() + xPos.getRelative() * width;
        } else {
            point.x = width - xPos.getAbsolute() - xPos.getRelative() * width;
        }
        if (yPos instanceof KTopPosition) {
            point.y = yPos.getAbsolute() + yPos.getRelative() * height;
        } else {
            point.y = height - yPos.getAbsolute() - yPos.getRelative() * height;
        }
        return point;
    }

    /**
     * Returns the given placement data as direct placement data.
     * 
     * @param data
     *            the placement data
     * @return the direct placement data or null if the placement data is no direct placement data
     */
    private static KDirectPlacementData asDirectPlacementData(final KPlacementData data) {
        if (data instanceof KDirectPlacementData) {
            return (KDirectPlacementData) data;
        }
        return null;
    }

    /**
     * Returns the given placement data as stack placement data.
     * 
     * @param data
     *            the placement data
     * @return the stack placement data or null if the placement data is no stack placement data
     */
    private static KStackPlacementData asStackPlacementData(final KPlacementData data) {
        if (data instanceof KStackPlacementData) {
            return (KStackPlacementData) data;
        } else if (data instanceof KPolylinePlacementData) {
            KPolylinePlacementData polylinePlacementData = (KPolylinePlacementData) data;
            return asStackPlacementData(polylinePlacementData.getDetailPlacementData());
        }
        return null;
    }

    /**
     * Returns the given placement data as polyline placement data.
     * 
     * @param data
     *            the placement data
     * @return the polyline placement data or null if the placement data is no polyline placement
     *         data
     */
    private static KPolylinePlacementData asPolylinePlacementData(final KPlacementData data) {
        if (data instanceof KPolylinePlacementData) {
            return (KPolylinePlacementData) data;
        }
        return null;
    }

    /**
     * Searches the rendering for a child area and records the path in the given list of renderings.
     * 
     * @param rendering
     *            the rendering
     * @param path
     *            the list of renderings which will contain the path of renderings to the child area
     * @return true if a child area has been found; false else
     */
    private static boolean findChildArea(final KRendering rendering,
            final LinkedList<KRendering> path) {
        path.addLast(rendering);
        if (rendering instanceof KChildArea) {
            return true;
        }
        if (rendering instanceof KContainerRendering) {
            KContainerRendering containerRendering = (KContainerRendering) rendering;
            for (KRendering childRendering : containerRendering.getChildren()) {
                if (findChildArea(childRendering, path)) {
                    return true;
                }
            }
        } else if (rendering instanceof KRenderingRef) {
            KRenderingRef renderingReference = (KRenderingRef) rendering;
            KRendering referencedRendering = renderingReference.getRendering();
            if (referencedRendering != null) {
                if (findChildArea(referencedRendering, path)) {
                    return true;
                }
            }
        }
        path.removeLast();
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void undoLayout(final LayoutMapping<KGraphElement> mapping) {
        throw new UnsupportedOperationException(
                "Undo is not supported by the KLighD KRendering layout manager.");
    }

    /**
     * {@inheritDoc}
     */
    public IMutableLayoutConfig getLayoutConfig() {
        return null;
    }

    /**
     * A data holder class for bounds.
     */
    private static class Bounds {

        /** the x-coordinate. */
        private float x;
        /** the y-coordinate. */
        private float y;
        /** the width. */
        private float width;
        /** the height. */
        private float height;

        /**
         * Constructs bounds from the given coordinates and dimensions.
         * 
         * @param x
         *            the x-coordinate
         * @param y
         *            the y-coordinate
         * @param width
         *            the width
         * @param height
         *            the height
         */
        public Bounds(final float x, final float y, final float width, final float height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

    }

    /**
     * A data holder class for points.
     */
    private static class Point {

        /** the x-coordinate. */
        private float x;
        /** the y-coordinate. */
        private float y;

        /**
         * Constructs a point from the given coordinates.
         * 
         * @param x
         *            the x-coordinate
         * @param y
         *            the y-coordinate
         */
        public Point(final float x, final float y) {
            this.x = x;
            this.y = y;
        }

    }

}
