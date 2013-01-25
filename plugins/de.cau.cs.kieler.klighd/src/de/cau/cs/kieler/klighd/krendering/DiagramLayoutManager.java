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
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.BiMap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.util.KGraphSwitch;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.krendering.PlacementUtil.Bounds;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.viewers.KlighdViewer;
import de.cau.cs.kieler.klighd.views.DiagramEditorPart;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;

/**
 * A diagram layout manager for KLighD viewers which support instances of {@code KNode}.<br>
 * <br>
 * If the {@code KNode} instances have attached {@code KRendering} data the manager uses it to
 * compute the node insets.
 * 
 * @author mri
 */
public class DiagramLayoutManager implements IDiagramLayoutManager<KGraphElement> {

    /** the duration for applying the layout. */
    public static final IProperty<Integer> APPLY_LAYOUT_DURATION = new Property<Integer>(
            "krendering.layout.applyLayoutDuration", 0);
    /** the viewer visualizing the graph. */
    public static final IProperty<IViewer<?>> VIEWER = new Property<IViewer<?>>(
            "krendering.layout.viewer");
    /** the list of edges found in the graph. */
    private static final IProperty<List<KEdge>> EDGES = new Property<List<KEdge>>(
            "krendering.layout.edges");

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
        } else if (object instanceof DiagramEditorPart) {
            DiagramEditorPart editor = (DiagramEditorPart) object;
            viewContext = editor.getContextViewer().getCurrentViewContext();
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getAdapter(final Object object, final Class adapterType) {
        if (adapterType.isAssignableFrom(KGraphPropertyLayoutConfig.class)) {
            return propertyLayoutConfig;
        } else if (adapterType.isAssignableFrom(KGraphElement.class)) {
            if (object instanceof KGraphElement) {
                return object;
            }
        }
        if (object instanceof IAdaptable) {
            return ((IAdaptable) object).getAdapter(adapterType);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Class<?>[] getAdapterList() {
        return new Class<?>[] { KGraphElement.class };
    }

    /**
     * {@inheritDoc}
     */
    public LayoutMapping<KGraphElement> buildLayoutGraph(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        KNode graph = null;
        IViewer<?> viewer = null;

        // search for the root node
        if (diagramPart instanceof KNode) {
            graph = (KNode) diagramPart;
        } else {
            if (workbenchPart instanceof DiagramViewPart) {
                DiagramViewPart view = (DiagramViewPart) workbenchPart;
                viewer = view.getContextViewer().getActiveViewer();
            } else if (workbenchPart instanceof DiagramEditorPart) {
                DiagramEditorPart editor = (DiagramEditorPart) workbenchPart;
                viewer = editor.getContextViewer().getActiveViewer();
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

        // remember the viewer if any
        if (viewer != null) {
            mapping.setProperty(VIEWER, viewer);
        }

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
    public LayoutMapping<KGraphElement> buildLayoutGraph(final KNode graph) {
        LayoutMapping<KGraphElement> mapping = new LayoutMapping<KGraphElement>(this);
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

        if (nodeLayout != null) {

            transferShapeLayout(nodeLayout, layoutLayout);

            // integrate the minimal estimated node size based on the updated layoutLayout
            //  - manipulating the nodeLayout may cause immediate glitches in the diagram
            //   (through the listeners)
            KRendering rootRendering = node.getData(KRendering.class);
            if (rootRendering != null) {
                // calculate the minimal size need for the first rendering ... 
                Bounds minSize = PlacementUtil.estimateSize(rootRendering,
                        new Bounds(layoutLayout.getWidth(), layoutLayout.getHeight()));
                
                // ... and update the node size if it exceeds its size
                if (minSize.width > layoutLayout.getWidth()) {
                    layoutLayout.setWidth(minSize.width);
                    // In order to instruct KIML to not shrink the node beyond the minimal size,
                    //  e.g. due to less space required by child nodes,
                    //  configure a related layout option!
                    // This has to be done on the original node instance, as layout options are
                    //  transfered by the {@link KGraphPropertyLayoutConfig}.
                    nodeLayout.setProperty(LayoutOptions.MIN_WIDTH, minSize.width);
                }
                if (minSize.height > layoutLayout.getHeight()) {
                    layoutLayout.setHeight(minSize.height);
                    // see comment above
                    nodeLayout.setProperty(LayoutOptions.MIN_HEIGHT, minSize.height);
                }
            }
        }

        // set insets if available
        KInsets layoutInsets = layoutLayout.getInsets();
        PlacementUtil.calculateInsets(node, layoutInsets);

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
        if (portLayout != null) {
            transferShapeLayout(portLayout, layoutLayout);
        }

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

            KPort layoutSourcePort = null;
            if (edge.getSourcePort() != null) {
                layoutSourcePort = (KPort) graphMap.get(edge.getSourcePort());
            }
            KPort layoutTargetPort = null;
            if (edge.getTargetPort() != null) {
                layoutTargetPort = (KPort) graphMap.get(edge.getTargetPort());
            }
            if (layoutSource != null && layoutTarget != null) {
                createEdge(mapping, edge, layoutSource, layoutTarget, layoutSourcePort,
                        layoutTargetPort);
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
            final KNode layoutSource, final KNode layoutTarget, final KPort layoutSourcePort,
            final KPort layoutTargetPort) {
        KEdge layoutEdge = KimlUtil.createInitializedEdge();

        // set the edge layout
        KEdgeLayout layoutLayout = layoutEdge.getData(KEdgeLayout.class);
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        if (edgeLayout != null) {
            transferEdgeLayout(edgeLayout, layoutLayout, null);
        }

        layoutEdge.setSource(layoutSource);
        layoutEdge.setTarget(layoutTarget);
        if (layoutSourcePort != null) {
            layoutEdge.setSourcePort(layoutSourcePort);
        }
        if (layoutTargetPort != null) {
            layoutEdge.setTargetPort(layoutTargetPort);
        }

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
        if (labelLayout != null) {
            transferShapeLayout(labelLayout, layoutLayout);
        }

        layoutLabel.setText(label.getText());

        mapping.getGraphMap().put(layoutLabel, label);
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final LayoutMapping<KGraphElement> mapping, final boolean zoomToFit,
            final int animationTime) {
        // set the animation time as property on the root element
        KShapeLayout parentLayout = mapping.getParentElement().getData(KShapeLayout.class);
        if (parentLayout != null) {
            parentLayout.setProperty(APPLY_LAYOUT_DURATION, animationTime);
        }

        // get the visualizing viewer if any
        IViewer<?> viewer = mapping.getProperty(VIEWER);

        // apply the layout
        if (viewer != null) {
            viewer.setRecording(true);
            applyLayout(mapping);
            viewer.setZoomToFit(zoomToFit);
            viewer.setRecording(false);
        } else {
            applyLayout(mapping);
        }
    }

    /**
     * Applies the computed layout back to the graph.
     * 
     * @param mapping
     *            the layout mapping that was created by this manager
     */
    private static void applyLayout(final LayoutMapping<KGraphElement> mapping) {
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
                        transferEdgeLayout(layoutLayout, edgeLayout, layoutEdge);
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
        // Attention: Layout options are transfered by the {@link KGraphPropertyLayoutConfig}
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
     * @param edge
     *            the edge, or {@code null} if no point checking shall be performed
     */
    private static void transferEdgeLayout(final KEdgeLayout sourceEdgeLayout,
            final KEdgeLayout targetEdgeLayout, final KEdge edge) {

        // do not notify listeners about any change on the displayed KGraph
        final boolean deliver = targetEdgeLayout.eDeliver();
        targetEdgeLayout.eSetDeliver(false);

        targetEdgeLayout.copyProperties(sourceEdgeLayout);

        if (targetEdgeLayout.getSourcePoint() == null) {
            targetEdgeLayout.setSourcePoint(KLayoutDataFactory.eINSTANCE.createKPoint());
        }
        if (edge == null) {
            // transfer the source point without checking
            KPoint sourcePoint = sourceEdgeLayout.getSourcePoint();
            targetEdgeLayout.getSourcePoint().setPos(sourcePoint.getX(), sourcePoint.getY());
        } else {
            KNode sourceNode = edge.getSource();
            KVector offset = new KVector();
            // If the target is a descendant of the source, the edge's source point is already
            // relative to the source node's position.
            if (!KimlUtil.isDescendant(edge.getTarget(), sourceNode)) {
                KShapeLayout sourceLayout = sourceNode.getData(KShapeLayout.class);
                offset.x = -sourceLayout.getXpos();
                offset.y = -sourceLayout.getYpos();
            }
            checkAndCopyPoint(sourceEdgeLayout.getSourcePoint(), targetEdgeLayout.getSourcePoint(),
                    sourceNode, edge.getSourcePort(), offset);
        }

        // transfer the bend points, reusing any existing KPoint instances
        ListIterator<KPoint> sourceBendIter = sourceEdgeLayout.getBendPoints().listIterator();
        ListIterator<KPoint> targetBendIter = targetEdgeLayout.getBendPoints().listIterator();
        while (sourceBendIter.hasNext()) {
            KPoint sourcePoint = sourceBendIter.next();
            KPoint targetPoint;
            if (targetBendIter.hasNext()) {
                targetPoint = targetBendIter.next();
            } else {
                targetPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                targetBendIter.add(targetPoint);
            }
            targetPoint.setPos(sourcePoint.getX(), sourcePoint.getY());
        }
        // remove any superfluous points
        while (targetBendIter.hasNext()) {
            targetBendIter.next();
            targetBendIter.remove();
        }

        // reactivate notifications for the final modification
        targetEdgeLayout.eSetDeliver(deliver);
        
        if (targetEdgeLayout.getTargetPoint() == null) {
            targetEdgeLayout.setTargetPoint(KLayoutDataFactory.eINSTANCE.createKPoint());
        }
        if (edge == null) {
            // transfer the target point without checking
            KPoint targetPoint = sourceEdgeLayout.getTargetPoint();
            targetEdgeLayout.getTargetPoint().setPos(targetPoint.getX(), targetPoint.getY());
        } else {
            KNode sourceNode = edge.getSource();
            KNode targetNode = edge.getTarget();
            KVector offset = new KVector();
            if (sourceNode.getParent() == targetNode.getParent()) {
                // The source and target are on the same level, so just subtract the target position.
                KShapeLayout targetLayout = targetNode.getData(KShapeLayout.class);
                offset.x = -targetLayout.getXpos();
                offset.y = -targetLayout.getYpos();
            } else {
                // The source and target are on different levels, so transform coordinate system.
                KNode referenceNode = sourceNode;
                if (!KimlUtil.isDescendant(targetNode, sourceNode)) {
                    referenceNode = referenceNode.getParent();
                }
                KimlUtil.toAbsolute(offset, referenceNode);
                KimlUtil.toRelative(offset, targetNode);
            }
            checkAndCopyPoint(sourceEdgeLayout.getTargetPoint(), targetEdgeLayout.getTargetPoint(),
                    targetNode, edge.getTargetPort(), offset);
        }
    }
    
    /**
     * Check whether the given source point lies on the boundary of the corresponding node or
     * port and transfer the corrected position to the target point.
     * 
     * @param sourcePoint the point from which to take the position
     * @param targetPoint the point to which to copy the anchored position
     * @param node the corresponding node
     * @param port the corresponding port, or {@code null}
     * @param offset the offset that must be added to the source point in order to make it
     *          relative to the given node
     */
    private static void checkAndCopyPoint(final KPoint sourcePoint, final KPoint targetPoint,
            final KNode node, final KPort port, final KVector offset) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        KVector p = sourcePoint.createVector();
        if (port == null) {
            p.add(offset);
            AnchorUtil.anchorPoint(p, nodeLayout.getWidth(), nodeLayout.getHeight(),
                    node.getData(KRendering.class));
        } else {
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            offset.translate(-portLayout.getXpos(), -portLayout.getYpos());
            p.add(offset);
            AnchorUtil.anchorPoint(p, portLayout.getWidth(), portLayout.getHeight(),
                    port.getData(KRendering.class));
        }
        targetPoint.applyVector(p.sub(offset));
    }


    /**
     * {@inheritDoc}
     */
    public void undoLayout(final LayoutMapping<KGraphElement> mapping) {
        throw new UnsupportedOperationException(
                "Undo is not supported by the KLighD KRendering layout manager.");
    }

}
