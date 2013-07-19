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
package de.cau.cs.kieler.klighd.internal.macrolayout;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.BiMap;
import com.google.common.collect.Iterables;

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
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.viewers.KlighdViewer;
import de.cau.cs.kieler.klighd.views.IDiagramWorkbenchPart;

/**
 * A diagram layout manager for KLighD viewers that supports instances of {@code KNode}, as well as
 * the parts and viewers provided by KLighD.<br>
 * <br>
 * If the {@code KNode} instances have attached {@code KRendering} data the manager uses them to
 * compute the node insets as well as the minimal node size.
 * 
 * @author mri
 * @author chsch
 * @author msp
 */
public class KlighdLayoutManager implements IDiagramLayoutManager<KGraphElement> {

    /** the list of edges found in the graph. */
    private static final IProperty<List<KEdge>> EDGES = new Property<List<KEdge>>(
            "krendering.layout.edges");

    /**
     * A property that is used to tell KIML about the workbench part this layout manager is
     * responsible for. Note that this property is not referred to by KIML immediately, it rather
     * filters given property definitions by their value types and looks for one of
     * {@link IWorkbenchPart}.
     */
    private static final IProperty<IWorkbenchPart> WORKBENCH_PART = new Property<IWorkbenchPart>(
            "klighd.layout.workbenchPart");
    
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
        if (object instanceof IDiagramWorkbenchPart) {
            IDiagramWorkbenchPart view = (IDiagramWorkbenchPart) object;
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getAdapter(final Object object, final Class adapterType) {
        if (adapterType.isAssignableFrom(KGraphPropertyLayoutConfig.class)) {
            return propertyLayoutConfig;
        } else if (adapterType.isAssignableFrom(EObject.class)) {
            
            if (object instanceof KGraphElement) {
                return ((KGraphElement) object).getData(KLayoutData.class).getProperty(
                        KlighdInternalProperties.MODEL_ELEMEMT);
            }
            
            ContextViewer contextViewer = null;
            if (object instanceof ContextViewer) {
                contextViewer = (ContextViewer) object;
            } else if (object instanceof IDiagramWorkbenchPart) {
                contextViewer = ((IDiagramWorkbenchPart) object).getContextViewer();
            }
            
            if (contextViewer != null) {
                ViewContext viewContext = contextViewer.getCurrentViewContext();
                if (viewContext != null) {
                    Object model = viewContext.getInputModel();
                    if (model instanceof EObject) {
                        return model;
                    }
                }
            }
        } else if (adapterType.isAssignableFrom(KGraphElement.class)) {
            if (object instanceof KGraphElement) {
                return object;
            }
            ContextViewer contextViewer = null;
            if (object instanceof ContextViewer) {
                contextViewer = (ContextViewer) object;
            } else if (object instanceof IDiagramWorkbenchPart) {
                contextViewer = ((IDiagramWorkbenchPart) object).getContextViewer();
            }
            if (contextViewer != null) {
                Object model = contextViewer.getModel();
                if (model instanceof KGraphElement) {
                    return model;
                }
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
        IDiagramWorkbenchPart diagramWorkbenchPart = null;

        // search for the root node
        if (diagramPart instanceof KNode) {
            graph = (KNode) diagramPart;
        } else {
            if (workbenchPart instanceof IDiagramWorkbenchPart) {
                diagramWorkbenchPart = (IDiagramWorkbenchPart) workbenchPart;
                viewer = diagramWorkbenchPart.getContextViewer().getActiveViewer();
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
        mapping.setProperty(WORKBENCH_PART, diagramWorkbenchPart);

        // remember the viewer if any
        if (viewer != null) {
            mapping.setProperty(KlighdInternalProperties.VIEWER, viewer);
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
        // iterate through the parent's active children and put copies in the layout graph;
        //  a child is active if it contains RenderingContextData and the 'true' value wrt.
        //  the property KlighdConstants.ACTIVE, see the predicate definition below
        for (KNode node : Iterables.filter(parent.getChildren(), RenderingContextData.CHILD_ACTIVE)) {
            createNode(mapping, node, layoutParent);
        }
    }
    
    /**
     * An internal property attached to a node's shape layout when the size of the node is affected
     * by KIML. It is used to decide which value to rely on for the minimal node size.
     */
    private static final IProperty<Boolean> INITIAL_NODE_SIZE = new Property<Boolean>(
            "klighd.initialNodeSize", true);
    
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
        // initialize with defaultLayout and try to get specific layout attached to the node
        KShapeLayout layoutLayout = layoutNode.getData(KShapeLayout.class);
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        
        boolean isCompoundNode = RenderingContextData.get(node).getProperty(
                KlighdInternalProperties.POPULATED)
                && Iterables.any(node.getChildren(), RenderingContextData.CHILD_ACTIVE);

        Bounds size = null;
        if (nodeLayout != null) {
            // there is layoutData attached to the node,
            // so take that as node layout instead of the default-layout
            transferShapeLayout(nodeLayout, layoutLayout);
            
            // In the following the minimal width and height of the node is determined, which
            //  is used as a basis for the size estimation (necessary for grid-based micro layouts).

            // We start with standard minimal bounds given in the related constant. 
            Bounds minSize = Bounds.of(KlighdConstants.MINIMAL_NODE_BOUNDS);
            // check the definition of the minimal size property
            boolean minNodeSizeIsSet = nodeLayout.getProperties().containsKey(
                    KlighdProperties.MINIMAL_NODE_SIZE);
            
            if (minNodeSizeIsSet) {
                // if the minimal node size is given in terms of the dedicated property, use its values
                minSize = Bounds.of(nodeLayout.getProperty(KlighdProperties.MINIMAL_NODE_SIZE));
            } else if (!isCompoundNode || nodeLayout.getProperty(INITIAL_NODE_SIZE)) {
                // otherwise, if the node is a non-compound one or the size is not yet modified by KIML
                //  take the component-wise maximum of the standard bounds and 'nodelayout's values 
                minSize = Bounds.max(minSize, Bounds.of(nodeLayout.getWidth(), nodeLayout.getHeight()));
            }
                
            // explicitly store the determined minimal node size in the layout data of the node
            //  note that this information will be removed or overwritten by the update strategies
            boolean deliver = nodeLayout.eDeliver();
            nodeLayout.eSetDeliver(false);
            nodeLayout.setProperty(KlighdProperties.MINIMAL_NODE_SIZE,
                    new KVector(minSize.getWidth(), minSize.getHeight()));
            nodeLayout.eSetDeliver(deliver);

            KRendering rootRendering = node.getData(KRendering.class);
            // if a rendering definition is given ...
            if (rootRendering != null) {
                
                // ... calculate the minimal required size based on the determined 'minSize' bounds
                size = Bounds.max(minSize, PlacementUtil.estimateSize(rootRendering, minSize));

                // integrate the minimal estimated node size
                //  in case of a compound node, the minimal node size to be preserved by KIML must be
                //   handed over by means of the MIN_WIDTH/MIN_HEIGHT properties
                //  in case of non-compound nodes with SizeConstraint::MINIMUM_SIZE set, the property
                //   definitions are also relevant 
                nodeLayout.setProperty(LayoutOptions.MIN_WIDTH, size.getWidth());
                nodeLayout.setProperty(LayoutOptions.MIN_HEIGHT, size.getHeight());
                if (!isCompoundNode) {
                    // in case of non-compound nodes the node size is usually taken from the layoutLayout
                    layoutLayout.setSize(size.getWidth(), size.getHeight());
                }
            }
        }
        
        // set insets if available
        KInsets layoutInsets = layoutLayout.getInsets();
        PlacementUtil.calculateInsets(node.getData(KRendering.class), layoutInsets, size);

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
        if (isCompoundNode) {
            processNodes(mapping, node, layoutNode);
        }

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
            
            KEdgeLayout layout = edge.getData(KEdgeLayout.class);
            if (layout == null || layout.getProperty(LayoutOptions.NO_LAYOUT)) {
                continue;
            }
                
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
            transferEdgeLayout(edgeLayout, layoutLayout, true);
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
            
            // integrate the minimal estimated label size based on the updated layoutLayout
            // - manipulating the labelLayout may cause immediate glitches in the diagram
            // (through the listeners)
            KRendering rootRendering = label.getData(KRendering.class);
            if (rootRendering != null) {
                // calculate the minimal size need for the rendering ...
                Bounds minSize = PlacementUtil.estimateTextSize(label);
                // ... and update the node size if it exceeds its size
                if (minSize.getWidth() > layoutLayout.getWidth()) {
                    labelLayout.setWidth(minSize.getWidth());
                    layoutLayout.setWidth(minSize.getWidth());

                    // In order to instruct KIML to not shrink the node beyond the minimal size,
                    //  e.g. due to less space required by child nodes,
                    //  configure a related layout option!
                    // This has to be done on the original node instance, as layout options are
                    //  transfered by the {@link KGraphPropertyLayoutConfig}.
                    labelLayout.setProperty(LayoutOptions.MIN_WIDTH, minSize.getWidth());
                }
                if (minSize.getHeight() > layoutLayout.getHeight()) {
                    labelLayout.setHeight(minSize.getHeight());
                    layoutLayout.setHeight(minSize.getHeight());
                    // see comment above
                    labelLayout.setProperty(LayoutOptions.MIN_HEIGHT, minSize.getHeight());
                }
                layoutLayout.setInsets(minSize.getInsets());
            }
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
            parentLayout.setProperty(KlighdInternalProperties.APPLY_LAYOUT_DURATION, animationTime);
        }

        // get the visualizing viewer if any
        IViewer<?> viewer = mapping.getProperty(KlighdInternalProperties.VIEWER);

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
                        nodeLayout.setProperty(INITIAL_NODE_SIZE, false);
                    }
                    return true;
                }

                public Boolean caseKEdge(final KEdge layoutEdge) {
                    KEdgeLayout layoutLayout = layoutEdge.getData(KEdgeLayout.class);
                    KEdgeLayout edgeLayout = element.getData(KEdgeLayout.class);
                    if (edgeLayout != null) {
                        transferEdgeLayout(edgeLayout, layoutLayout, false);
                        edgeLayout.setProperty(LayoutOptions.JUNCTION_POINTS,
                                layoutLayout.getProperty(LayoutOptions.JUNCTION_POINTS));
                    }
                    return true;
                }

                public Boolean caseKPort(final KPort layoutPort) {
                    KShapeLayout layoutLayout = layoutPort.getData(KShapeLayout.class);
                    KShapeLayout portLayout = element.getData(KShapeLayout.class);
                    if (portLayout != null) {
                        transferShapeLayout(layoutLayout, portLayout);
                        portLayout.setProperty(KlighdProperties.LAYOUT_PORT_SIDE,
                                layoutLayout.getProperty(LayoutOptions.PORT_SIDE));
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
     * @param viewModelEdgeLayout
     *            the destination edge layout
     * @param layoutEdgeLayout
     *            the origin edge layout
     * @param viewModel2LayoutGraph
     *            if true the transfer is to be done from viewModel to layout graph, if false the
     *            other round
     */
    private static void transferEdgeLayout(final KEdgeLayout viewModelEdgeLayout,
            final KEdgeLayout layoutEdgeLayout, final boolean viewModel2LayoutGraph) {
        
        KEdge layoutEdge = (KEdge) layoutEdgeLayout.eContainer();

        // do not notify listeners about any change on the displayed KGraph in order
        //  to avoid unnecessary diagram refresh cycles
        final boolean deliver = viewModelEdgeLayout.eDeliver();
        viewModelEdgeLayout.eSetDeliver(false);

        viewModelEdgeLayout.copyProperties(layoutEdgeLayout);

        if (viewModelEdgeLayout.getSourcePoint() == null) {
            viewModelEdgeLayout.setSourcePoint(KLayoutDataFactory.eINSTANCE.createKPoint());
        }
        if (viewModel2LayoutGraph) {
            // transfer the source point without checking
            KPoint sourcePoint = viewModelEdgeLayout.getSourcePoint();
            layoutEdgeLayout.getSourcePoint().setPos(sourcePoint.getX(), sourcePoint.getY());
        } else {
            KNode layoutSourceNode = layoutEdge.getSource();
            KVector offset = new KVector();
            
            // If the target is a descendant of the source, the edge's source point is already
            // relative to the source node's position.
            if (!KimlUtil.isDescendant(layoutEdge.getTarget(), layoutSourceNode)) {
                KShapeLayout sourceLayout = layoutSourceNode.getData(KShapeLayout.class);
                offset.x = -sourceLayout.getXpos();
                offset.y = -sourceLayout.getYpos();
            } else {
                KShapeLayout sourceLayout = layoutSourceNode.getData(KShapeLayout.class);
                offset.x = sourceLayout.getInsets().getLeft();
                offset.y = sourceLayout.getInsets().getTop();
            }
            
            boolean pointDeliver = viewModelEdgeLayout.getSourcePoint().eDeliver();
            viewModelEdgeLayout.getSourcePoint().eSetDeliver(false);
            
            checkAndCopyPoint(layoutEdgeLayout.getSourcePoint(),
                    viewModelEdgeLayout.getSourcePoint(), layoutSourceNode,
                    layoutEdge.getSourcePort(), ((KEdge) viewModelEdgeLayout.eContainer())
                            .getSource().getData(KRendering.class), offset);
            
            viewModelEdgeLayout.getSourcePoint().eSetDeliver(pointDeliver);
        }

        // transfer the bend points, reusing any existing KPoint instances
        ListIterator<KPoint> originBendIter = (viewModel2LayoutGraph ? viewModelEdgeLayout
                : layoutEdgeLayout).getBendPoints().listIterator();
        ListIterator<KPoint> destBendIter = (viewModel2LayoutGraph ? layoutEdgeLayout
                : viewModelEdgeLayout).getBendPoints().listIterator();
        while (originBendIter.hasNext()) {
            KPoint originPoint = originBendIter.next();
            KPoint destPoint;
            if (destBendIter.hasNext()) {
                destPoint = destBendIter.next();
            } else {
                destPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                destBendIter.add(destPoint);
            }
            destPoint.setPos(originPoint.getX(), originPoint.getY());
        }
        // remove any superfluous points
        while (destBendIter.hasNext()) {
            destBendIter.next();
            destBendIter.remove();
        }

        if (viewModelEdgeLayout.getTargetPoint() == null) {
            viewModelEdgeLayout.setTargetPoint(KLayoutDataFactory.eINSTANCE.createKPoint());
        }
        if (viewModel2LayoutGraph) {
            // transfer the target point without checking
            KPoint targetPoint = viewModelEdgeLayout.getTargetPoint();
            layoutEdgeLayout.getTargetPoint().setPos(targetPoint.getX(), targetPoint.getY());
        } else {            
            KNode layoutSourceNode = layoutEdge.getSource();
            KNode layoutTargetNode = layoutEdge.getTarget();
            KVector offset = new KVector();
            
            if (layoutSourceNode.getParent() == layoutTargetNode.getParent()) {
                // The source and target are on the same level, so just subtract the target position.
                KShapeLayout targetLayout = layoutTargetNode.getData(KShapeLayout.class);
                offset.x = -targetLayout.getXpos();
                offset.y = -targetLayout.getYpos();
            } else {
                // The source and target are on different levels, so transform coordinate system.
                KNode referenceNode = layoutSourceNode;
                if (!KimlUtil.isDescendant(layoutTargetNode, layoutSourceNode)) {
                    referenceNode = referenceNode.getParent();
                }
                KimlUtil.toAbsolute(offset, referenceNode);
                KimlUtil.toRelative(offset, layoutTargetNode.getParent());
                KShapeLayout targetLayout = layoutTargetNode.getData(KShapeLayout.class);
                offset.x -= targetLayout.getXpos();
                offset.y -= targetLayout.getYpos();
            }
            boolean pointDeliver = viewModelEdgeLayout.getTargetPoint().eDeliver();
            viewModelEdgeLayout.getTargetPoint().eSetDeliver(false);

            checkAndCopyPoint(layoutEdgeLayout.getTargetPoint(),
                    viewModelEdgeLayout.getTargetPoint(), layoutTargetNode,
                    layoutEdge.getTargetPort(), ((KEdge) viewModelEdgeLayout.eContainer())
                            .getTarget().getData(KRendering.class), offset);
            
            viewModelEdgeLayout.getTargetPoint().eSetDeliver(pointDeliver);
        }

        // reactivate notifications & fire a notification
        //  bringing the observing diagram controller to update the displayed diagram
        viewModelEdgeLayout.eSetDeliver(deliver);
        if (!viewModel2LayoutGraph) {
            viewModelEdgeLayout.eNotify(new ENotificationImpl(
                    (InternalEObject) viewModelEdgeLayout, Notification.SET,
                    KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS, null, null));
        }

    }
    
    /**
     * Check whether the given source point lies on the boundary of the corresponding node or
     * port and transfer the corrected position to the target point.
     * 
     * @param originPoint the point from which to take the position
     * @param destinationPoint the point to which to copy the anchored position
     * @param node the corresponding node
     * @param port the corresponding port, or {@code null}
     * @param rendering the rendering of the corresponding node
     * @param offset the offset that must be added to the source point in order to make it
     *          relative to the given node
     */
    private static void checkAndCopyPoint(final KPoint originPoint, final KPoint destinationPoint,
            final KNode node, final KPort port, final KRendering rendering, final KVector offset) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        KVector p = originPoint.createVector();
        if (port == null) {
            p.add(offset);
            AnchorUtil.anchorPoint(p, nodeLayout.getWidth(), nodeLayout.getHeight(),
                    rendering);
        } else {
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            offset.translate(-portLayout.getXpos(), -portLayout.getYpos());
            p.add(offset);
            AnchorUtil.anchorPoint(p, portLayout.getWidth(), portLayout.getHeight(),
                    port.getData(KRendering.class));
        }
        destinationPoint.applyVector(p.sub(offset));
    }

    /**
     * {@inheritDoc}
     */
    public void undoLayout(final LayoutMapping<KGraphElement> mapping) {
        throw new UnsupportedOperationException(
                "Undo is not supported by the KLighD KRendering layout manager.");
    }

}
