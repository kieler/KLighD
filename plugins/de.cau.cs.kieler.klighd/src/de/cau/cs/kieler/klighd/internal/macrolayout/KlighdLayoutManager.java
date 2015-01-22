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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.BiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.util.KGraphSwitch;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.service.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.service.LayoutMapping;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.ILayoutRecorder;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;
import de.cau.cs.kieler.klighd.util.KlighdPredicates;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.util.RenderingContextData;

/**
 * A diagram layout manager for KLighD viewers that supports instances of {@link KNode}, as well as
 * the parts and viewers provided by KLighD.<br>
 * <br>
 * If the {@link KNode} instances have attached {@code KRendering} data the manager uses them to
 * compute the node insets as well as the minimal node size.<br>
 * <br>
 * <b>Note:</b> During the {@link #applyLayout(LayoutMapping)} phase layout data that have been
 * scaled according to a corresponding {@link LayoutOptions#SCALE_FACTOR} are normalized to scaling
 * <code>1.0f</code>, since the scaling is implemented on figure level by means of affine
 * transforms. In addition, the scale adjustment need not be reverted before the subsequent layout
 * run.
 *
 * @author mri
 * @author chsch
 * @author msp
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class KlighdLayoutManager implements IDiagramLayoutManager<KGraphElement> {

    /**
     * A dummy value used in fired {@link Notification Notifications} indicating a completed update
     * of a {@link KLayoutData} instance.
     */
    public static final Object LAYOUT_DATA_CHANGED_VALUE = new Object();

    /**
     * A dummy value used in fired {@link Notification Notifications} indicating a completed update
     * of a {@link KLayoutData} instance.
     */
    public static final Object LAYOUT_DATA_UNCHANGED_VALUE = new Object();

    /** the list of edges found in the graph. */
    private static final IProperty<List<KEdge>> EDGES = new Property<List<KEdge>>(
            "krendering.layout.edges");
    /** edges that have been excluded from the layout. */
    private static final IProperty<List<KEdge>> EXCLUDED_EDGES = new Property<List<KEdge>>(
            "krendering.layout.excludedEdges");

    /**
     * A property that is used to tell KIML about the workbench part this layout manager is
     * responsible for. Note that this property is not referred to by KIML immediately, it rather
     * filters given property definitions by their value types and looks for one of
     * {@link IWorkbenchPart}.
     */
    private static final IProperty<IWorkbenchPart> WORKBENCH_PART = new Property<IWorkbenchPart>(
            "klighd.layout.workbenchPart");

    /** the property layout configurator. */
    private final KGraphPropertyLayoutConfig propertyLayoutConfig = new KGraphPropertyLayoutConfig();

    /**
     * {@inheritDoc}
     */
    public IMutableLayoutConfig getDiagramConfig() {
        return propertyLayoutConfig;
    }

    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object object) {
        // KGraph instances are supported
        //  Tests here for KGraphElement rather than KNode since this method e.g. invoked while
        //  populating the layout view, which provides also port, edge, and label properties.
        if (object instanceof KGraphElement) {
            return true;
        } else if (object instanceof ViewContext) {
            return true;
        } else if (object instanceof IViewer) {
            return true;
        } else if (object instanceof IDiagramWorkbenchPart) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutMapping<KGraphElement> buildLayoutGraph(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        final KNode graph;
        ILayoutRecorder recorder = null;
        final ViewContext viewContext;

        // search for the root node

        if (diagramPart instanceof KNode) {
            graph = (KNode) diagramPart;
            viewContext = null;
        } else if (diagramPart instanceof ViewContext) {
            viewContext = (ViewContext) diagramPart;
            graph = viewContext.getViewModel();
        } else if (diagramPart instanceof IViewer) {
            viewContext = ((IViewer) diagramPart).getViewContext();
            graph = viewContext.getViewModel();
        } else if (workbenchPart instanceof IDiagramWorkbenchPart) {
            viewContext = ((IDiagramWorkbenchPart) workbenchPart).getViewer().getViewContext();
            graph = viewContext.getViewModel();
        } else  {
            viewContext = null;
            graph = null;
        }

        // if no root node could be found
        if (graph == null) {
            throw new UnsupportedOperationException(
                    "Not supported by the KLighD KRendering layout manager: Workbench part "
                            + workbenchPart + ", diagram part " + diagramPart);
        }

        // create the mapping
        final LayoutMapping<KGraphElement> mapping =
                buildLayoutGraph(graph,
                        !viewContext.getProperty(KlighdSynthesisProperties.SUPPRESS_SIZE_ESTIMATION));
        mapping.setProperty(EclipseLayoutConfig.ACTIVATION, false);
        if (viewContext != null) {
            mapping.setProperty(WORKBENCH_PART, viewContext.getDiagramWorkbenchPart());
        }

        // remember the layout recorder if any
        if (viewContext != null) {
            recorder = viewContext.getLayoutRecorder();

            if (recorder != null) {
                mapping.setProperty(KlighdInternalProperties.RECORDER, recorder);
            }
        }
        return mapping;
    }

    /**
     * Builds a layout graph from the given graph.
     *
     * @param graph
     *            the graph to build the layout graph from
     * @param performSizeEstimation
     *            whether the size of nodes & labels should be automatically estimated.
     * @return the layout graph mapping
     */
    public LayoutMapping<KGraphElement> buildLayoutGraph(final KNode graph,
            final boolean performSizeEstimation) {
        final LayoutMapping<KGraphElement> mapping = new LayoutMapping<KGraphElement>();
        mapping.setProperty(EDGES, new LinkedList<KEdge>());

        // set the parent element
        mapping.setParentElement(graph);

        final KNode layoutGraph = KimlUtil.createInitializedNode();
        final KShapeLayout layoutGraphShapeLayout = layoutGraph.getData(KShapeLayout.class);
        final KShapeLayout graphShapeLayout = graph.getData(KShapeLayout.class);
        if (graphShapeLayout != null) {
            transferShapeLayout(graphShapeLayout, layoutGraphShapeLayout, false, false);
        }

        mapping.getGraphMap().put(layoutGraph, graph);
        mapping.setLayoutGraph(layoutGraph);

        // traverse the children of the layout root
        processNodes(mapping, graph, layoutGraph, performSizeEstimation);
        // transform all connections in the selected area
        processConnections(mapping, performSizeEstimation);

        return mapping;
    }

    /**
     * Static predicate definition avoiding the recurring creation and disposal of instances of the
     * filter predicate.
     */
    private static final Predicate<KNode> NODE_FILTER = Predicates.and(
            RenderingContextData.IS_ACTIVE,
            KlighdPredicates.kgePropertyPredicate(LayoutOptions.NO_LAYOUT, false, true));

    /**
     * Processes all child nodes of the given parent node.
     *
     * @param mapping
     *            the layout mapping
     * @param parent
     *            the parent node
     * @param layoutParent
     *            the layout parent node
     * @param suppressSizeEstimation
     *            whether the size of nodes & labels should be automatically estimated.
     */
    private void processNodes(final LayoutMapping<KGraphElement> mapping,
            final KNode parent, final KNode layoutParent, final boolean suppressSizeEstimation) {
        // iterate through the parent's active children and put copies in the layout graph;
        //  a child is active if it contains RenderingContextData and the 'true' value wrt.
        //  the property KlighdConstants.ACTIVE, see the predicate definition above
        // furthermore, all nodes that have the LAYOUT_IGNORE property set are ignored
        for (final KNode node : Iterables.filter(parent.getChildren(), NODE_FILTER)) {
            createNode(mapping, node, layoutParent, suppressSizeEstimation);
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
     * @param performSizeEstimation
     *            whether the size of the node, its labels, and its attached ports' and edges'
     *            labels should be automatically estimated.
     */
    private void createNode(final LayoutMapping<KGraphElement> mapping, final KNode node,
            final KNode layoutParent, final boolean performSizeEstimation) {
        final KNode layoutNode = KimlUtil.createInitializedNode();
        // set the node layout
        // initialize with defaultLayout and try to get specific layout attached to the node
        final KShapeLayout layoutLayout = layoutNode.getData(KShapeLayout.class);
        final KShapeLayout nodeLayout = node.getData(KShapeLayout.class);

        // first check whether children of 'node' shall be taken into account at all.
        // note that all KNodes of a view model are set 'POPULATED' except the
        //  explicitly (e.g. initially) collapsed ones.
        // note the special implementation of 'IS_POPULATED' in case no RenderingContextData
        //  are attached to 'node'; the predicate returns 'true' in that particular case.
        // this is required for applying layout to view models that aren't shown by a viewer
        //  and whose (compound) nodes are not tagged to be 'populated'. This may happen in
        //  batch tests, for example.
        final boolean isPopulated = RenderingContextData.IS_POPULATED.apply(node);

        // determine the corresponding rendering
        final Predicate<KRendering> filter = isPopulated ? KlighdPredicates.isExpandedRendering()
                : Predicates.not(KlighdPredicates.isExpandedRendering());
        // apply the class-based filter, than the above defined 'filter'
        //  if none is found, just take the first KRendering in the 'data' list
        final KRendering displayedRendering = Iterators.find(
                Iterators.filter(node.getData().iterator(), KRendering.class),
                filter, node.getData(KRendering.class));

        // consider 'node' a compound node if it is populated AND has active children
        //  will be false if all children are inactive and not added to the layout graph later on
        final boolean isCompoundNode =
                isPopulated && Iterables.any(node.getChildren(), RenderingContextData.IS_ACTIVE);
        
        final Bounds size;
        if (nodeLayout != null) {
            // there is layoutData attached to the node,
            // so take that as node layout instead of the default-layout
            transferShapeLayout(nodeLayout, layoutLayout, false, false);

            // In the following the minimal width and height of the node is determined, which
            //  is used as a basis for the size estimation (necessary for grid-based micro layouts).

            // We start with standard minimal bounds given in the related constant.
            Bounds minSize = Bounds.of(KlighdConstants.MINIMAL_NODE_BOUNDS);
            // check the definition of the minimal size property
            final boolean minNodeSizeIsSet = nodeLayout.getProperties().containsKey(
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
            final boolean deliver = nodeLayout.eDeliver();
            nodeLayout.eSetDeliver(false);
            nodeLayout.setProperty(KlighdProperties.MINIMAL_NODE_SIZE,
                    new KVector(minSize.getWidth(), minSize.getHeight()));
            nodeLayout.eSetDeliver(deliver);

            // if a rendering definition is given ...
            if (displayedRendering != null) {

                // ... calculate the minimal required size based on the determined 'minSize' bounds
                if (performSizeEstimation) {
                    size = Bounds.max(minSize, PlacementUtil.estimateSize(displayedRendering, minSize));
                } else {
                    size = minSize;
                }

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
            } else {
                size = minSize;
            }
        } else {
            size = null;
        }

        // set insets if available
        final KInsets layoutInsets = layoutLayout.getInsets();

        PlacementUtil.calculateInsets(displayedRendering, layoutInsets, size);

        layoutParent.getChildren().add(layoutNode);
        mapping.getGraphMap().put(layoutNode, node);

        // process ports
        for (final KPort port : Iterables.filter(node.getPorts(), RenderingContextData.IS_ACTIVE)) {
            createPort(mapping, port, layoutNode, performSizeEstimation);
        }

        // process labels
        for (final KLabel label : Iterables.filter(node.getLabels(), RenderingContextData.IS_ACTIVE)) {
            createLabel(mapping, label, layoutNode, performSizeEstimation);
        }

        // process the child as new parent
        if (isCompoundNode) {
            processNodes(mapping, node, layoutNode, performSizeEstimation);
        }

        // store all the edges to process them later
        final List<KEdge> edges = mapping.getProperty(EDGES);
        Iterables.addAll(edges,
                Iterables.filter(node.getOutgoingEdges(), RenderingContextData.IS_ACTIVE));
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
     * @param estimateLabelSizes
     *            if <code>true</code> the minimal sizes of the attached {@link KLabel KLabels} will
     *            be estimated     */
    private void createPort(final LayoutMapping<KGraphElement> mapping, final KPort port,
            final KNode layoutNode, final boolean estimateLabelSizes) {
        final KPort layoutPort = KimlUtil.createInitializedPort();

        // set the port layout
        final KShapeLayout layoutLayout = layoutPort.getData(KShapeLayout.class);
        final KShapeLayout portLayout = port.getData(KShapeLayout.class);
        if (portLayout != null) {
            transferShapeLayout(portLayout, layoutLayout, false, false);
        }

        layoutPort.setNode(layoutNode);
        mapping.getGraphMap().put(layoutPort, port);

        // process labels
        for (final KLabel label : port.getLabels()) {
            createLabel(mapping, label, layoutPort, estimateLabelSizes);
        }
    }

    /**
     * Processes all edges collected while processing nodes.
     *
     * @param mapping
     *            the layout mapping
     * @param estimateLabelSizes
     *            if <code>true</code> the minimal sizes of the attached {@link KLabel KLabels} will
     *            be estimated
     */
    private void processConnections(final LayoutMapping<KGraphElement> mapping,
            final boolean estimateLabelSizes) {
        final BiMap<KGraphElement, KGraphElement> graphMap = mapping.getGraphMap().inverse();

        // iterate through the list of collected edges
        final List<KEdge> edges = mapping.getProperty(EDGES);
        for (final KEdge edge : edges) {

            final KEdgeLayout layout = edge.getData(KEdgeLayout.class);
            if (layout == null || layout.getProperty(LayoutOptions.NO_LAYOUT)) {
                List<KEdge> excludedEdges = mapping.getProperty(EXCLUDED_EDGES);
                if (excludedEdges == null) {
                    excludedEdges = new LinkedList<KEdge>();
                    mapping.setProperty(EXCLUDED_EDGES, excludedEdges);
                }
                excludedEdges.add(edge);
                continue;
            }

            final KNode layoutSource = (KNode) graphMap.get(edge.getSource());
            final KNode layoutTarget = (KNode) graphMap.get(edge.getTarget());

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
                        layoutTargetPort, estimateLabelSizes);
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
     * @param estimateLabelSizes
     *            if <code>true</code> the minimal sizes of the attached {@link KLabel KLabels} will
     *            be estimated
     */
    private void createEdge(final LayoutMapping<KGraphElement> mapping, final KEdge edge,
            final KNode layoutSource, final KNode layoutTarget, final KPort layoutSourcePort,
            final KPort layoutTargetPort, final boolean estimateLabelSizes) {
        final KEdge layoutEdge = KimlUtil.createInitializedEdge();

        // set the edge layout
        final KEdgeLayout layoutLayout = layoutEdge.getData(KEdgeLayout.class);
        final KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        if (edgeLayout != null) {
            transferEdgeLayout(edgeLayout, layoutLayout, true);
        }

        // make sure to clear old junction points
        // the new layouter might not calculate any and we don't want
        // any floating junction points in the diagram
        edgeLayout.setProperty(LayoutOptions.JUNCTION_POINTS, null);

        // delete the old EDGE_ROUTING return value
        // this is allowed as the EDGE_ROUTING directive to the layouter
        //  must be set on the parent of the KNode with the outgoing edge
        edgeLayout.setProperty(LayoutOptions.EDGE_ROUTING, null);

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
        for (final KLabel label : Iterables.filter(edge.getLabels(), RenderingContextData.IS_ACTIVE)) {
            createLabel(mapping, label, layoutEdge, estimateLabelSizes);
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
     * @param estimateSize
     *            if <code>true</code> the minimal size of the {@link KLabel} will be estimated
     */
    private void createLabel(final LayoutMapping<KGraphElement> mapping, final KLabel label,
            final KLabeledGraphElement layoutLabeledElement, final boolean estimateSize) {
        final KLabel layoutLabel = KimlUtil.createInitializedLabel(layoutLabeledElement);

        // set the label layout
        final KShapeLayout layoutLayout = layoutLabel.getData(KShapeLayout.class);
        final KShapeLayout labelLayout = label.getData(KShapeLayout.class);

        if (labelLayout != null) {
            transferShapeLayout(labelLayout, layoutLayout, false, false);

            // integrate the minimal estimated label size based on the updated layoutLayout
            // - manipulating the labelLayout may cause immediate glitches in the diagram
            // (through the listeners)
            final KRendering rootRendering = label.getData(KRendering.class);

            if (estimateSize && rootRendering != null) {
                // calculate the minimal size need for the rendering ...
                final Bounds minSize = PlacementUtil.estimateTextSize(label);

                final float minWidth = minSize.getWidth() > layoutLayout.getWidth()
                        ? minSize.getWidth() : layoutLayout.getWidth();
                final float minHeight = minSize.getHeight() > layoutLayout.getHeight()
                        ? minSize.getHeight() : layoutLayout.getHeight();

                // ... and update the node size if it exceeds its size
                layoutLayout.setSize(minWidth, minHeight);
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
        // get the animation recorder if anyone has been attached above ...
        final ILayoutRecorder recorder = mapping.getProperty(KlighdInternalProperties.RECORDER);

        // ... and apply the layout
        if (recorder != null) {
            final IViewer viewer = (IViewer) recorder;
            if (viewer.getControl() != null && viewer.getControl().isDisposed()) {
                return;
            }
            recorder.startRecording();
            applyLayout(mapping);
            recorder.stopRecording(animationTime);

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
    private void applyLayout(final LayoutMapping<KGraphElement> mapping) {
        final Set<Entry<KGraphElement, KGraphElement>> elementMappings =
                mapping.getGraphMap().entrySet();

        // apply the layout of all mapped layout elements back to the associated element
        for (final Entry<KGraphElement, KGraphElement> elementMapping : elementMappings) {
            final KGraphElement layoutElement = elementMapping.getKey();
            final KGraphElement element = elementMapping.getValue();

            new KGraphSwitch<Boolean>() {
                @Override
                public Boolean caseKNode(final KNode layoutNode) {
                    final KShapeLayout layoutLayout = layoutNode.getData(KShapeLayout.class);
                    final KShapeLayout nodeLayout = element.getData(KShapeLayout.class);
                    if (nodeLayout != null) {
                        transferShapeLayout(layoutLayout, nodeLayout, true, true);
                        nodeLayout.setProperty(INITIAL_NODE_SIZE, false);

                        // transfer the scale factor value since KIML might have reset it
                        //  to 1f in case scaling was not supported in the particular configuration
                        // and the figure scaling will be set according this property setting
                        nodeLayout.setProperty(LayoutOptions.SCALE_FACTOR,
                                layoutLayout.getProperty(LayoutOptions.SCALE_FACTOR));
                    }
                    return true;
                }

                @Override
                public Boolean caseKEdge(final KEdge layoutEdge) {
                    final KEdgeLayout layoutLayout = layoutEdge.getData(KEdgeLayout.class);
                    final KEdgeLayout edgeLayout = element.getData(KEdgeLayout.class);
                    if (edgeLayout != null) {
                        transferEdgeLayout(edgeLayout, layoutLayout, false);
                    }
                    return true;
                }

                @Override
                public Boolean caseKPort(final KPort layoutPort) {
                    final KShapeLayout layoutLayout = layoutPort.getData(KShapeLayout.class);
                    final KShapeLayout portLayout = element.getData(KShapeLayout.class);
                    if (portLayout != null) {
                        transferShapeLayout(layoutLayout, portLayout, false, true);
                        portLayout.setProperty(KlighdProperties.LAYOUT_PORT_SIDE,
                                layoutLayout.getProperty(LayoutOptions.PORT_SIDE));
                    }
                    return true;
                }

                @Override
                public Boolean caseKLabel(final KLabel layoutLabel) {
                    final KShapeLayout layoutLayout = layoutLabel.getData(KShapeLayout.class);
                    final KShapeLayout labelLayout = element.getData(KShapeLayout.class);
                    if (labelLayout != null) {
                        transferShapeLayout(layoutLayout, labelLayout, false, true);
                    }
                    return true;
                }
            } /**/.doSwitch(layoutElement);
        }

        // process the edges that have been excluded from layout
        final List<KEdge> excludedEdges = mapping.getProperty(EXCLUDED_EDGES);
        if (excludedEdges != null) {
            for (final KEdge edge : excludedEdges) {
                handleExcludedEdge(edge);
            }
        }
    }

    /**
     * Transfers the source shape layout to the target shape layout.
     *
     * @param sourceShapeLayout
     *            the source shape layout
     * @param targetShapeLayout
     *            the target shape layout
     * @param copyInsets
     *            <code>true</code> if insets shall be copied
     * @param adjustScaling
     *            if <code>true</code> the <code>sourceShapeLayout</code>'s data will be adjusted
     *            s.t. the scaling of the corresponding node will be reverted to 100%, since the
     *            scaling is implemented by means of affine transforms on figure level.
     */
    private void transferShapeLayout(final KShapeLayout sourceShapeLayout,
            final KShapeLayout targetShapeLayout, final boolean copyInsets,
            final boolean adjustScaling) {
        // Attention: Layout options are transfered by the {@link KGraphPropertyLayoutConfig}

        // do not notify listeners about any change on the displayed KGraph in order
        //  to avoid unnecessary diagram refresh cycles
        final boolean deliver = targetShapeLayout.eDeliver();
        targetShapeLayout.eSetDeliver(false);
        targetShapeLayout.resetModificationFlag();

        if (adjustScaling) {
            final KGraphPackage pack = KGraphPackage.eINSTANCE;
            final EObject container = sourceShapeLayout.eContainer();
            final float scale;

            if (pack.getKNode().isInstance(container)) {
                scale = sourceShapeLayout.getProperty(LayoutOptions.SCALE_FACTOR);
                targetShapeLayout.setPos(sourceShapeLayout.getXpos(), sourceShapeLayout.getYpos());
                targetShapeLayout.setSize(sourceShapeLayout.getWidth() / scale,
                        sourceShapeLayout.getHeight() / scale);

            } else if (pack.getKPort().isInstance(container)
                    || pack.getKLabel().isInstance(container)) {
                scale = ((KGraphElement) container.eContainer()).getData(KLayoutData.class).getProperty(
                                LayoutOptions.SCALE_FACTOR);
                targetShapeLayout.setPos(sourceShapeLayout.getXpos() / scale,
                        sourceShapeLayout.getYpos() / scale);
                targetShapeLayout.setSize(sourceShapeLayout.getWidth() / scale,
                        sourceShapeLayout.getHeight() / scale);

                final KVector anchor = targetShapeLayout.getProperty(LayoutOptions.PORT_ANCHOR);
                if (anchor != null) {
                    anchor.x /= scale;
                    anchor.y /= scale;
                }
            }

        } else {
            targetShapeLayout.setPos(sourceShapeLayout.getXpos(), sourceShapeLayout.getYpos());
            targetShapeLayout.setSize(sourceShapeLayout.getWidth(), sourceShapeLayout.getHeight());
        }

        // reactivate notifications & fire a notification
        //  bringing the observing diagram controller to update the displayed diagram
        targetShapeLayout.eSetDeliver(deliver);
        if (deliver) {

            // for efficiency reasons just fire a single notification with values indicating
            //  whether actually some change occurred in the shape layout
            // the information whether 'no change' happened is required for, e.g., updating the
            //  visibility of elements after the parent KNode has been expanded
            final Object newValue = targetShapeLayout.isModified()
                    ? LAYOUT_DATA_CHANGED_VALUE : LAYOUT_DATA_UNCHANGED_VALUE;

            targetShapeLayout.eNotify(new ENotificationImpl((InternalEObject) targetShapeLayout,
                    Notification.SET, KLayoutDataPackage.eINSTANCE.getKShapeLayout_Xpos(),
                    null, newValue));
        }

        if (copyInsets) {
            final KInsets sourceInsets = sourceShapeLayout.getInsets();
            final KInsets targetInsets = targetShapeLayout.getInsets();

            targetInsets.setLeft(sourceInsets.getLeft());
            targetInsets.setRight(sourceInsets.getRight());
            targetInsets.setTop(sourceInsets.getTop());
            targetInsets.setBottom(sourceInsets.getBottom());
        }
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
    private void transferEdgeLayout(final KEdgeLayout viewModelEdgeLayout,
            final KEdgeLayout layoutEdgeLayout, final boolean viewModel2LayoutGraph) {

        final KEdge viewModelEdge = (KEdge) viewModelEdgeLayout.eContainer();
        final KEdge layoutEdge = (KEdge) layoutEdgeLayout.eContainer();

        // do not notify listeners about any change on the displayed KGraph in order
        //  to avoid unnecessary diagram refresh cycles
        final boolean deliver = viewModelEdgeLayout.eDeliver();
        viewModelEdgeLayout.eSetDeliver(false);

        // copy all properties from the layoutEdgeLayout to the viewModelEdgeLayout,
        //  esp. the concrete EDGE_ROUTING and the JUNCTION_POINTS
        // the viewModel2LayoutGraph case this statement will have no effect
        viewModelEdgeLayout.copyProperties(layoutEdgeLayout);

        if (viewModelEdgeLayout.getSourcePoint() == null) {
            viewModelEdgeLayout.setSourcePoint(KLayoutDataFactory.eINSTANCE.createKPoint());
        }
        if (viewModel2LayoutGraph) {
            // transfer the source point without checking
            final KPoint sourcePoint = viewModelEdgeLayout.getSourcePoint();
            layoutEdgeLayout.getSourcePoint().setPos(sourcePoint.getX(), sourcePoint.getY());
        } else {
            final KNode layoutSourceNode = layoutEdge.getSource();
            final KVector offset = new KVector();

            // this flag indicates the requirement of the adjusting the port position
            //  wrt. the scaling factor being associated with the port's parent node
            boolean adjustPortPosition = false;

            // If the target is a descendant of the source, the edge's source point is already
            // relative to the source node's position.
            if (!KimlUtil.isDescendant(layoutEdge.getTarget(), layoutSourceNode)) {
                final KShapeLayout sourceLayout = layoutSourceNode.getData(KShapeLayout.class);
                offset.x = -sourceLayout.getXpos();
                offset.y = -sourceLayout.getYpos();
            } else {
                adjustPortPosition = true;
                final KShapeLayout sourceLayout = layoutSourceNode.getData(KShapeLayout.class);
                offset.x = sourceLayout.getInsets().getLeft();
                offset.y = sourceLayout.getInsets().getTop();
            }

            final boolean pointDeliver = viewModelEdgeLayout.getSourcePoint().eDeliver();
            viewModelEdgeLayout.getSourcePoint().eSetDeliver(false);

            checkAndCopyPoint(layoutEdgeLayout.getSourcePoint(),
                    viewModelEdgeLayout.getSourcePoint(), layoutSourceNode,
                    layoutEdge.getSourcePort(),
                    viewModelEdge.getSource().getData(KRendering.class),
                    viewModelEdge.getSourcePort() == null ? null
                    : viewModelEdge.getSourcePort().getData(KRendering.class),
                    offset, adjustPortPosition);

            viewModelEdgeLayout.getSourcePoint().eSetDeliver(pointDeliver);
        }

        // transfer the bend points, reusing any existing KPoint instances
        final ListIterator<KPoint> originBendIter = (viewModel2LayoutGraph ? viewModelEdgeLayout
                : layoutEdgeLayout).getBendPoints().listIterator();
        final ListIterator<KPoint> destBendIter = (viewModel2LayoutGraph ? layoutEdgeLayout
                : viewModelEdgeLayout).getBendPoints().listIterator();
        while (originBendIter.hasNext()) {
            final KPoint originPoint = originBendIter.next();
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
            final KPoint targetPoint = viewModelEdgeLayout.getTargetPoint();
            layoutEdgeLayout.getTargetPoint().setPos(targetPoint.getX(), targetPoint.getY());
        } else {
            final KNode layoutSourceNode = layoutEdge.getSource();
            final KNode layoutTargetNode = layoutEdge.getTarget();
            final KVector offset = new KVector();

            // this flag indicates the requirement of the adjusting the port position
            //  wrt. the scaling factor being associated with the port's parent node
            boolean adjustPortPosition = false;

            if (layoutSourceNode.getParent() == layoutTargetNode.getParent()) {
                // The source and target are on the same level, so just subtract the target position.
                final KShapeLayout targetLayout = layoutTargetNode.getData(KShapeLayout.class);
                offset.x = -targetLayout.getXpos();
                offset.y = -targetLayout.getYpos();
            } else {
                // The source and target are on different levels, so transform coordinate system.
                KNode referenceNode = layoutSourceNode;
                if (!KimlUtil.isDescendant(layoutTargetNode, layoutSourceNode)) {
                    adjustPortPosition = true;
                    referenceNode = referenceNode.getParent();
                }
                KimlUtil.toAbsolute(offset, referenceNode);
                KimlUtil.toRelative(offset, layoutTargetNode.getParent());
                final KShapeLayout targetLayout = layoutTargetNode.getData(KShapeLayout.class);
                offset.x -= targetLayout.getXpos();
                offset.y -= targetLayout.getYpos();
            }
            final boolean pointDeliver = viewModelEdgeLayout.getTargetPoint().eDeliver();
            viewModelEdgeLayout.getTargetPoint().eSetDeliver(false);

            checkAndCopyPoint(layoutEdgeLayout.getTargetPoint(),
                    viewModelEdgeLayout.getTargetPoint(), layoutTargetNode,
                    layoutEdge.getTargetPort(),
                    viewModelEdge.getTarget().getData(KRendering.class),
                    viewModelEdge.getTargetPort() == null ? null
                    : viewModelEdge.getTargetPort().getData(KRendering.class),
                    offset, adjustPortPosition);

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
     * Handle the given edge that was excluded from layout. Set its source and
     * target points to appropriate positions on the border of the respective elements.
     *
     * @param edge an excluded edge
     */
    private void handleExcludedEdge(final KEdge edge) {
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        boolean deliver;
        if (edgeLayout == null) {
            deliver = true;
            edgeLayout = KLayoutDataFactory.eINSTANCE.createKEdgeLayout();
            edge.getData().add(edgeLayout);
            edgeLayout.eSetDeliver(false);
        } else {
            deliver = edgeLayout.eDeliver();
            edgeLayout.eSetDeliver(false);
            edgeLayout.getBendPoints().clear();
        }

        final KNode sourceNode = edge.getSource();
        final KNode targetNode = edge.getTarget();
        final KPort sourcePort = edge.getSourcePort();
        final KPort targetPort = edge.getTargetPort();
        final boolean targetInSource = KimlUtil.isDescendant(targetNode, sourceNode);

        // determine the source point
        final KShapeLayout sourceLayout = sourceNode.getData(KShapeLayout.class);
        final KVector sourcePoint = toElementBorder(sourceNode, sourcePort, targetNode, targetPort);
        if (targetInSource) {
            if (sourceLayout.getInsets() != null) {
                sourcePoint.add(-sourceLayout.getInsets().getLeft(),
                        -sourceLayout.getInsets().getTop());
            }
        } else {
            sourcePoint.add(sourceLayout.getXpos(), sourceLayout.getYpos());
        }
        KPoint sourceKPoint = edgeLayout.getSourcePoint();
        if (sourceKPoint == null) {
            sourceKPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
            edgeLayout.setSourcePoint(sourceKPoint);
        }
        sourceKPoint.applyVector(sourcePoint);

        // determine the target point
        final KShapeLayout targetLayout = targetNode.getData(KShapeLayout.class);
        final KVector targetPoint = toElementBorder(targetNode, targetPort, sourceNode, sourcePort);
        targetPoint.add(targetLayout.getXpos(), targetLayout.getYpos());
        if (targetInSource) {
            KimlUtil.toAbsolute(targetPoint, targetNode.getParent());
            KimlUtil.toRelative(targetPoint, sourceNode);
        } else if (sourceNode.getParent() != targetNode.getParent()) {
            KimlUtil.toAbsolute(targetPoint, targetNode.getParent());
            KimlUtil.toRelative(targetPoint, sourceNode.getParent());
        }
        KPoint targetKPoint = edgeLayout.getTargetPoint();
        if (targetKPoint == null) {
            targetKPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
            edgeLayout.setTargetPoint(targetKPoint);
        }
        targetKPoint.applyVector(targetPoint);

        // notify the listeners
        edgeLayout.eSetDeliver(deliver);
        edgeLayout.eNotify(new ENotificationImpl((InternalEObject) edgeLayout, Notification.SET,
                KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS, null, null));
    }

    /**
     * Check whether the given source point lies on the boundary of the corresponding node or port
     * and transfer the corrected position to the target point.
     *
     * @param originPoint
     *            the point from which to take the position
     * @param destinationPoint
     *            the point to which to copy the anchored position
     * @param node
     *            the corresponding node
     * @param port
     *            the corresponding port, or {@code null}
     * @param nodeRendering
     *            the rendering of the corresponding node
     * @param portRendering
     *            the rendering of the corresponding port, or {@code null}
     * @param offset
     *            the offset that must be added to the source point in order to make it relative to
     *            the given node
     * @param adjustPortPos
     *            if <code>true</code> the port position will be adjusted by the scale factory
     *            applied to its parent node
     */
    // SUPPRESS CHECKSTYLE NEXT Parameter
    private void checkAndCopyPoint(final KPoint originPoint, final KPoint destinationPoint,
            final KNode node, final KPort port, final KRendering nodeRendering,
            final KRendering portRendering, final KVector offset, final boolean adjustPortPos) {

        KVector p = originPoint.createVector();
        final KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        final float scale = node.getData(KShapeLayout.class).getProperty(LayoutOptions.SCALE_FACTOR);

        if (port == null) {
            p.add(offset);
            p = AnchorUtil.nearestBorderPoint(p, nodeLayout.getWidth(), nodeLayout.getHeight(),
                    nodeRendering, scale);
        } else {
            final KShapeLayout portLayout = port.getData(KShapeLayout.class);

            if (adjustPortPos) {
                offset.add(-portLayout.getXpos() / scale, -portLayout.getYpos() / scale);
            } else {
                offset.add(-portLayout.getXpos(), -portLayout.getYpos());
            }

            p.add(offset);
            p = AnchorUtil.nearestBorderPoint(p, portLayout.getWidth(), portLayout.getHeight(),
                    portRendering, scale);
        }

        destinationPoint.applyVector(p.sub(offset));
    }

    /**
     * Find a point that lies close to the intersection of the direct line from the
     * given center element to the remote element with the center element's border.
     *
     * @param centerNode the center node
     * @param centerPort the center port, or {@code null}
     * @param remoteNode the remote node
     * @param remotePort the remote port, or {@code null}
     * @return the intersection with the center element's border
     */
    private KVector toElementBorder(final KNode centerNode, final KPort centerPort,
            final KNode remoteNode, final KPort remotePort) {

        final KShapeLayout remoteNodeLayout = remoteNode.getData(KShapeLayout.class);
        KVector point = new KVector();
        if (remotePort == null) {
            point.x = remoteNodeLayout.getWidth() / 2;
            point.y = remoteNodeLayout.getHeight() / 2;
        } else {
            final KShapeLayout remotePortLayout = remotePort.getData(KShapeLayout.class);
            point.x = remotePortLayout.getXpos() + remotePortLayout.getWidth() / 2;
            point.y = remotePortLayout.getYpos() + remotePortLayout.getHeight() / 2;
        }
        point.add(remoteNodeLayout.getXpos(), remoteNodeLayout.getYpos());
        if (centerNode.getParent() != remoteNode.getParent()) {
            KimlUtil.toAbsolute(point, remoteNode.getParent());
            KimlUtil.toRelative(point, centerNode.getParent());
        }

        final KShapeLayout centerNodeLayout = centerNode.getData(KShapeLayout.class);
        point.add(-centerNodeLayout.getXpos(), -centerNodeLayout.getYpos());
        if (centerPort == null) {
            point = AnchorUtil.collideTowardsCenter(
                    point,
                    centerNodeLayout.getWidth(),
                    centerNodeLayout.getHeight(),
                    centerNode.getData(KRendering.class));
        } else {
            final KShapeLayout centerPortLayout = centerPort.getData(KShapeLayout.class);
            point.add(-centerPortLayout.getXpos(), -centerPortLayout.getYpos());
            point = AnchorUtil.collideTowardsCenter(
                    point,
                    centerPortLayout.getWidth(),
                    centerPortLayout.getHeight(),
                    centerPort.getData(KRendering.class));
            point.add(centerPortLayout.getXpos(), centerPortLayout.getYpos());
        }

        return point;
    }

    /**
     * {@inheritDoc}
     */
    public void undoLayout(final LayoutMapping<KGraphElement> mapping) {
        throw new UnsupportedOperationException(
                "Undo is not supported by the KLighD KRendering layout manager.");
    }

}
