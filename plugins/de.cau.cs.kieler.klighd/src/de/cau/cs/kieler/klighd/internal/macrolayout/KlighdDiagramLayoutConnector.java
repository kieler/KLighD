/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.internal.macrolayout;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.elk.core.klayoutdata.KEdgeLayout;
import org.eclipse.elk.core.klayoutdata.KInsets;
import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.core.klayoutdata.KLayoutDataFactory;
import org.eclipse.elk.core.klayoutdata.KLayoutDataPackage;
import org.eclipse.elk.core.klayoutdata.KPoint;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.service.IDiagramLayoutConnector;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KGraphElement;
import org.eclipse.elk.graph.KGraphPackage;
import org.eclipse.elk.graph.KLabel;
import org.eclipse.elk.graph.KLabeledGraphElement;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.KPort;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.IPropertyHolder;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.KGraphSwitch;
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

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.ILayoutRecorder;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory;
import de.cau.cs.kieler.klighd.krendering.KRenderingRef;
import de.cau.cs.kieler.klighd.labels.KlighdLabelProperties;
import de.cau.cs.kieler.klighd.labels.LabelManagementResult;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;
import de.cau.cs.kieler.klighd.util.KlighdPredicates;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.util.RenderingContextData;

/**
 * A diagram layout connector for KLighD viewers that supports instances of {@link KNode}, as well as
 * the parts and viewers provided by KLighD.
 * 
 * <p>If the {@link KNode} instances have attached {@code KRendering} data the manager uses them to
 * compute the node insets as well as the minimal node size.</p>
 * 
 * <p><b>Note:</b> During the {@link #applyLayout(LayoutMapping)} phase layout data that have been
 * scaled according to a corresponding {@link LayoutOptions#SCALE_FACTOR} are normalized to scaling
 * <code>1.0f</code>, since the scaling is implemented on figure level by means of affine
 * transforms. In addition, the scale adjustment need not be reverted before the subsequent layout
 * run.</p>
 *
 * @author mri
 * @author chsch
 * @author msp
 * @author cds
 */
public class KlighdDiagramLayoutConnector implements IDiagramLayoutConnector {

    /**
     * Defines the possible transfer modes used to layout the graph.
     */
    private static enum EdgeLayoutTransferMode {
        /** Model transfered from the model to layout graph. */
        VIEW_MODEL_TO_LAYOUT_GRAPH,
        /** Layout graph to view model without adjustments. */
        LAYOUT_GRAPH_TO_VIEW_MODEL,
        /** Layout graph to view model with adjustments. */
        LAYOUT_GRAPH_TO_VIEW_MODEL_ADJUSTMENT
    }

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
    private static final IProperty<List<de.cau.cs.kieler.klighd.kgraph.KEdge>> EDGES =
            new Property<>("krendering.layout.edges");
    /** edges that have been excluded from the layout. */
    private static final IProperty<List<de.cau.cs.kieler.klighd.kgraph.KEdge>> EXCLUDED_EDGES =
            new Property<>("krendering.layout.excludedEdges");
    /**
     * A property that is used to tell KIML about the workbench part this layout manager is
     * responsible for. Note that this property is not referred to by KIML immediately, it rather
     * filters given property definitions by their value types and looks for one of
     * {@link IWorkbenchPart}.
     */
    private static final IProperty<IWorkbenchPart> WORKBENCH_PART = new Property<IWorkbenchPart>(
            "klighd.layout.workbenchPart");

    /**
     * Static predicate definition avoiding the recurring creation and disposal of instances of the
     * filter predicate.
     */
    private static final Predicate<de.cau.cs.kieler.klighd.kgraph.KNode> NODE_FILTER =
            Predicates.and(
                    RenderingContextData.IS_ACTIVE,
                    KlighdPredicates.kgePropertyPredicate(CoreOptions.NO_LAYOUT, false, true));
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Layout Graph Building
    
    /**
     * {@inheritDoc}
     */
    @Override
    public LayoutMapping buildLayoutGraph(final IWorkbenchPart workbenchPart, final Object diagramPart) {
        final de.cau.cs.kieler.klighd.kgraph.KNode viewModel;
        final ViewContext viewContext;

        // search for the root node
        if (diagramPart instanceof de.cau.cs.kieler.klighd.kgraph.KNode) {
            viewModel = (de.cau.cs.kieler.klighd.kgraph.KNode) diagramPart;
            viewContext = null;
        } else if (diagramPart instanceof ViewContext) {
            viewContext = (ViewContext) diagramPart;
            viewModel = viewContext.getViewModel();
        } else if (diagramPart instanceof IViewer) {
            viewContext = ((IViewer) diagramPart).getViewContext();
            viewModel = viewContext.getViewModel();
        } else if (workbenchPart instanceof IDiagramWorkbenchPart) {
            viewContext = ((IDiagramWorkbenchPart) workbenchPart).getViewer().getViewContext();
            viewModel = viewContext.getViewModel();
        } else  {
            viewContext = null;
            viewModel = null;
        }

        // if no root node could be found
        if (viewModel == null) {
            throw new UnsupportedOperationException(
                    "Not supported by the KLighD KRendering layout manager: Workbench part "
                            + workbenchPart + ", diagram part " + diagramPart);
        }

        final boolean performSizeEstimation = viewContext == null
                ? true : !viewContext.getProperty(KlighdSynthesisProperties.SUPPRESS_SIZE_ESTIMATION);

        // create the mapping
        final LayoutMapping mapping = buildLayoutGraph(
                viewModel, performSizeEstimation, workbenchPart);

        if (viewContext != null) {
            mapping.setProperty(WORKBENCH_PART, viewContext.getDiagramWorkbenchPart());
            
            // remember the layout recorder if any
            mapping.setProperty(KlighdInternalProperties.RECORDER, viewContext.getLayoutRecorder());
        }
        
        return mapping;
    }

    /**
     * Builds a layout graph from the given graph.
     *
     * @param viewModel
     *            the graph to build the layout graph from
     * @param performSizeEstimation
     *            whether the size of nodes & labels should be automatically estimated.
     * @param workbenchPart
     *            the workbenchPart in which the layout takes place, if any
     * @return the layout graph mapping
     */
    public LayoutMapping buildLayoutGraph(final de.cau.cs.kieler.klighd.kgraph.KNode viewModel,
            final boolean performSizeEstimation, final IWorkbenchPart workbenchPart) {
        
        final LayoutMapping mapping = new LayoutMapping(workbenchPart);
        mapping.setProperty(EDGES, new LinkedList<de.cau.cs.kieler.klighd.kgraph.KEdge>());

        // set the parent element
        mapping.setParentElement(viewModel);

        final KNode layoutGraph = ElkUtil.createInitializedNode();
        final KShapeLayout layoutGraphShapeLayout = layoutGraph.getData(KShapeLayout.class);
        transferShapeLayout(viewModel, layoutGraphShapeLayout, false, false);

        mapping.getGraphMap().put(layoutGraph, viewModel);
        mapping.setLayoutGraph(layoutGraph);

        // traverse the children of the layout root
        processNodes(mapping, viewModel, layoutGraph, performSizeEstimation);
        // transform all connections in the selected area
        processConnections(mapping, performSizeEstimation);

        return mapping;
    }
    
    /**
     * Processes all child nodes of the given parent node.
     *
     * @param mapping
     *            the layout mapping
     * @param viewModelParent
     *            the parent node in the view model
     * @param layoutParent
     *            the layout parent node
     * @param performSizeEstimation
     *            whether the size of nodes & labels should be automatically estimated.
     */
    private void processNodes(final LayoutMapping mapping,
            final de.cau.cs.kieler.klighd.kgraph.KNode viewModelParent,
            final KNode layoutParent, final boolean performSizeEstimation) {
        
        // iterate through the parent's active children and put copies in the layout graph;
        //  a child is active if it contains RenderingContextData and the 'true' value wrt.
        //  the property KlighdConstants.ACTIVE, see the predicate definition above
        // furthermore, all nodes that have the LAYOUT_IGNORE property set are ignored
        for (final de.cau.cs.kieler.klighd.kgraph.KNode node
                : Iterables.filter(viewModelParent.getChildren(), NODE_FILTER)) {
            
            createNode(mapping, node, layoutParent, performSizeEstimation);
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
    private void createNode(final LayoutMapping mapping,
            final de.cau.cs.kieler.klighd.kgraph.KNode node,
            final KNode layoutParent, final boolean performSizeEstimation) {
        
        final KNode layoutNode = ElkUtil.createInitializedNode();
        // set the node layout
        // initialize with defaultLayout and try to get specific layout attached to the node
        final KShapeLayout layoutLayout = layoutNode.getData(KShapeLayout.class);

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
        
        // there is layoutData attached to the node,
        // so take that as node layout instead of the default-layout
        transferShapeLayout(node, layoutLayout, false, false);

        // In the following the minimal width and height of the node is determined, which
        //  is used as a basis for the size estimation (necessary for grid-based micro layouts).

        // We start with standard minimal bounds given in the related constant.
        Bounds minSize = Bounds.of(KlighdConstants.MINIMAL_NODE_BOUNDS);
        // check the definition of the minimal size property
        final boolean minNodeSizeIsSet = node.getProperties().containsKey(
                KlighdProperties.MINIMAL_NODE_SIZE);

        if (minNodeSizeIsSet) {
            // if the minimal node size is given in terms of the dedicated property, use its values
            minSize = Bounds.of(node.getProperty(KlighdProperties.MINIMAL_NODE_SIZE));
        } else if (!isCompoundNode || node.getProperty(INITIAL_NODE_SIZE)) {
            // otherwise, if the node is a non-compound one or the size is not yet modified by KIML
            //  take the component-wise maximum of the standard bounds and 'nodelayout's values
            minSize = Bounds.max(minSize, Bounds.of(node.getWidth(), node.getHeight()));
        }

        // explicitly store the determined minimal node size in the layout data of the node
        //  note that this information will be removed or overwritten by the update strategies
        final boolean deliver = node.eDeliver();
        node.eSetDeliver(false);
        node.setProperty(KlighdProperties.MINIMAL_NODE_SIZE,
                new KVector(minSize.getWidth(), minSize.getHeight()));
        node.eSetDeliver(deliver);

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
            node.setProperty(CoreOptions.NODE_SIZE_MIN_WIDTH, size.getWidth());
            node.setProperty(CoreOptions.NODE_SIZE_MIN_HEIGHT, size.getHeight());
            if (!isCompoundNode) {
                // in case of non-compound nodes the node size is usually taken from the layoutLayout
                layoutLayout.setSize(size.getWidth(), size.getHeight());
            }
        } else {
            size = minSize;
        }

        // set insets if available
        de.cau.cs.kieler.klighd.kgraph.KInsets insets =
                de.cau.cs.kieler.klighd.kgraph.KGraphFactory.eINSTANCE.createKInsets();
        PlacementUtil.calculateInsets(displayedRendering, insets, size);
        copyInsets(insets, layoutLayout.getInsets());

        layoutParent.getChildren().add(layoutNode);
        mapping.getGraphMap().put(layoutNode, node);

        // process ports
        for (final de.cau.cs.kieler.klighd.kgraph.KPort port
                : Iterables.filter(node.getPorts(), RenderingContextData.IS_ACTIVE)) {
            
            createPort(mapping, port, layoutNode, performSizeEstimation);
        }

        // process labels
        for (final de.cau.cs.kieler.klighd.kgraph.KLabel label
                : Iterables.filter(node.getLabels(), RenderingContextData.IS_ACTIVE)) {
            
            createLabel(mapping, label, layoutNode, performSizeEstimation, false);
        }

        // process the child as new parent
        if (isCompoundNode) {
            processNodes(mapping, node, layoutNode, performSizeEstimation);
        }

        // store all the edges to process them later
        final List<de.cau.cs.kieler.klighd.kgraph.KEdge> edges = mapping.getProperty(EDGES);
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
    private void createPort(final LayoutMapping mapping,
            final de.cau.cs.kieler.klighd.kgraph.KPort port,
            final KNode layoutNode, final boolean estimateLabelSizes) {
        
        final KPort layoutPort = ElkUtil.createInitializedPort();

        // set the port layout
        final KShapeLayout layoutLayout = layoutPort.getData(KShapeLayout.class);
        transferShapeLayout(port, layoutLayout, false, false);

        layoutPort.setNode(layoutNode);
        mapping.getGraphMap().put(layoutPort, port);

        // process labels
        for (final de.cau.cs.kieler.klighd.kgraph.KLabel label : port.getLabels()) {
            createLabel(mapping, label, layoutPort, estimateLabelSizes, false);
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
    private void processConnections(final LayoutMapping mapping,
            final boolean estimateLabelSizes) {
        
        final BiMap<Object, KGraphElement> graphMap = mapping.getGraphMap().inverse();

        // iterate through the list of collected edges
        final List<de.cau.cs.kieler.klighd.kgraph.KEdge> edges = mapping.getProperty(EDGES);
        for (final de.cau.cs.kieler.klighd.kgraph.KEdge edge : edges) {

            if (edge.getProperty(CoreOptions.NO_LAYOUT)) {
                List<de.cau.cs.kieler.klighd.kgraph.KEdge> excludedEdges =
                        mapping.getProperty(EXCLUDED_EDGES);
                
                if (excludedEdges == null) {
                    excludedEdges = new LinkedList<>();
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
    private void createEdge(final LayoutMapping mapping,
            final de.cau.cs.kieler.klighd.kgraph.KEdge edge,
            final KNode layoutSource, final KNode layoutTarget, final KPort layoutSourcePort,
            final KPort layoutTargetPort, final boolean estimateLabelSizes) {
        
        final KEdge layoutEdge = ElkUtil.createInitializedEdge();

        // set the edge layout
        final KEdgeLayout layoutLayout = layoutEdge.getData(KEdgeLayout.class);
        transferViewModelEdgeLayoutToLayoutEdge(edge, layoutLayout);

        // make sure to clear old junction points
        // the new layouter might not calculate any and we don't want
        // any floating junction points in the diagram
        edge.setProperty(CoreOptions.JUNCTION_POINTS, null);

        // delete the old EDGE_ROUTING return value
        // this is allowed as the EDGE_ROUTING directive to the layouter
        //  must be set on the parent of the KNode with the outgoing edge
        edge.setProperty(CoreOptions.EDGE_ROUTING, null);

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
        for (final de.cau.cs.kieler.klighd.kgraph.KLabel label
                : Iterables.filter(edge.getLabels(), RenderingContextData.IS_ACTIVE)) {
            
            createLabel(mapping, label, layoutEdge, estimateLabelSizes, true);
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
     * @param setFontLayoutOptions
     *            if <code>true</code> the layout options {@link LayoutOptions#FONT_NAME} and
     *            {@link LayoutOptions#FONT_SIZE} are set/updated on <code>kLabel</code>'s layout
     *            data as expected by Graphviz (dot) for properly sizing <i>edge</i> labels
     */
    private void createLabel(final LayoutMapping mapping,
            final de.cau.cs.kieler.klighd.kgraph.KLabel label,
            final KLabeledGraphElement layoutLabeledElement, final boolean estimateSize,
            final boolean setFontLayoutOptions) {
        
        final KLabel layoutLabel = ElkUtil.createInitializedLabel(layoutLabeledElement);

        // set the label layout
        final KShapeLayout layoutLayout = layoutLabel.getData(KShapeLayout.class);

        transferShapeLayout(label, layoutLayout, false, false);

        // integrate the minimal estimated label size based on the updated layoutLayout
        // - manipulating the labelLayout may cause immediate glitches in the diagram
        // (through the listeners)
        final KRendering rootRendering = label.getData(KRendering.class);

        if (rootRendering != null) {
            if (estimateSize) {
                // calculate the minimal size need for the rendering ...
                final Bounds minSize =
                        PlacementUtil.estimateTextSize(label, setFontLayoutOptions);
                
                final float minWidth = minSize.getWidth() > layoutLayout.getWidth()
                        ? minSize.getWidth()
                        : layoutLayout.getWidth();
                final float minHeight = minSize.getHeight() > layoutLayout.getHeight()
                        ? minSize.getHeight()
                        : layoutLayout.getHeight();
                
                // ... and update the node size if it exceeds its size
                layoutLayout.setSize(minWidth, minHeight);

            } else if (setFontLayoutOptions) {
                PlacementUtil.fontDataFor(label, true);
            }

            // attach a reference to the label's root rendering to the label so that our layout
            // algorithms know how to estimate text sizes.
            // TODO This doesn't work anymore, thereby breaking label management.
            //      Should probably become a layout option or something.
//            KRenderingRef rootRenderingRef = KRenderingFactory.eINSTANCE.createKRenderingRef();
//            rootRenderingRef.setRendering(rootRendering);
//            layoutLabel.getData().add(rootRenderingRef);
        }

        layoutLabel.setText(label.getText());

        mapping.getGraphMap().put(layoutLabel, label);
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Layout Application

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyLayout(final LayoutMapping mapping, final IPropertyHolder settings) {
        // get the animation recorder if anyone has been attached above ...
        final ILayoutRecorder recorder = mapping.getProperty(KlighdInternalProperties.RECORDER);

        // ... and apply the layout
        if (recorder != null) {
            final IViewer viewer = (IViewer) recorder;
            final boolean suppressEdgeAdjustment = viewer.getViewContext().getProperty(
                    KlighdSynthesisProperties.SUPPRESS_EDGE_ADJUSTMENT);
            if (viewer.getControl() != null && viewer.getControl().isDisposed()) {
                return;
            }
            recorder.startRecording();
            applyLayout(mapping, suppressEdgeAdjustment);
            recorder.stopRecording(calcAnimationTime(mapping, settings, false));
        } else {
            applyLayout(mapping, false);
        }
    }
    
    /**
     * Applies the computed layout back to the graph.
     *
     * @param mapping
     *            the layout mapping that was created by this manager
     * @param suppressEdgeAdjustment
     *            if true edge adjustment will be suppressed, if no
     *            edge adjustment will be done
     */
    private void applyLayout(final LayoutMapping mapping, final boolean suppressEdgeAdjustment) {
        final Set<Entry<KGraphElement, Object>> elementMappings = mapping.getGraphMap().entrySet();

        // apply the layout of all mapped layout elements back to the associated element
        for (final Entry<KGraphElement, Object> elementMapping : elementMappings) {
            final KGraphElement layoutElement = elementMapping.getKey();
            
            // Since we built the layout graph, we know that this must be a KGraphElement
            final de.cau.cs.kieler.klighd.kgraph.KGraphElement element =
                    (de.cau.cs.kieler.klighd.kgraph.KGraphElement) elementMapping.getValue();

            new KGraphSwitch<Boolean>() {
                @Override
                public Boolean caseKNode(final KNode layoutNode) {
                    final KShapeLayout layoutLayout = layoutNode.getData(KShapeLayout.class);
                    final de.cau.cs.kieler.klighd.kgraph.KNode node =
                            (de.cau.cs.kieler.klighd.kgraph.KNode) element;
                    
                    transferShapeLayout(layoutLayout, node, true, true);
                    node.setProperty(INITIAL_NODE_SIZE, false);

                    // transfer the scale factor value since KIML might have reset it
                    //  to 1f in case scaling was not supported in the particular configuration
                    // and the figure scaling will be set according this property setting
                    node.setProperty(CoreOptions.SCALE_FACTOR,
                            layoutLayout.getProperty(CoreOptions.SCALE_FACTOR));
                    return true;
                }

                @Override
                public Boolean caseKEdge(final KEdge layoutEdge) {
                    final KEdgeLayout layoutLayout = layoutEdge.getData(KEdgeLayout.class);
                    de.cau.cs.kieler.klighd.kgraph.KEdge edge =
                            (de.cau.cs.kieler.klighd.kgraph.KEdge) element;
                    
                    transferLayoutEdgeToViewModelEdgeLayout(layoutLayout, edge,
                            !suppressEdgeAdjustment);
                    return true;
                }

                @Override
                public Boolean caseKPort(final KPort layoutPort) {
                    final KShapeLayout layoutLayout = layoutPort.getData(KShapeLayout.class);
                    final de.cau.cs.kieler.klighd.kgraph.KPort port =
                            (de.cau.cs.kieler.klighd.kgraph.KPort) element;
                    
                    transferShapeLayout(layoutLayout, port, false, true);
                    port.setProperty(KlighdProperties.LAYOUT_PORT_SIDE,
                            layoutLayout.getProperty(CoreOptions.PORT_SIDE));
                    return true;
                }

                @Override
                public Boolean caseKLabel(final KLabel layoutLabel) {
                    final KShapeLayout layoutLayout = layoutLabel.getData(KShapeLayout.class);
                    final de.cau.cs.kieler.klighd.kgraph.KLabel label =
                            (de.cau.cs.kieler.klighd.kgraph.KLabel) element;
                    
                    transferShapeLayout(layoutLayout, label, false, true);

                    // if the label's text was changed during layout, remember the new text in a
                    // special property
                    LabelManagementResult managementResult =
                            layoutLayout.getProperty(KlighdLabelProperties.LABEL_MANAGEMENT_RESULT);
                    if (managementResult != LabelManagementResult.UNMANAGED) {
                        // TODO: This may in the future set the KText's text instead.
                        // However, doing so now doesn't do anything yet...
                        label.setProperty(KlighdLabelProperties.LABEL_TEXT_OVERRIDE,
                                    layoutLabel.getText());
                        String origLabelText = ((KLabel) element).getText();
                        
                        if (origLabelText.equals(layoutLabel.getText())) {
                            label.setProperty(KlighdProperties.TOOLTIP, null);
                        } else {
                            label.setProperty(KlighdProperties.TOOLTIP, origLabelText);
                        }
                    }
                    return true;
                }
            } /**/.doSwitch(layoutElement);
        }

        // process the edges that have been excluded from layout
        final List<de.cau.cs.kieler.klighd.kgraph.KEdge> excludedEdges =
                mapping.getProperty(EXCLUDED_EDGES);
        if (excludedEdges != null) {
            for (final de.cau.cs.kieler.klighd.kgraph.KEdge edge : excludedEdges) {
                handleExcludedEdge(edge);
            }
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Layout Information Transfer

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
    private void transferShapeLayout(
            final de.cau.cs.kieler.klighd.kgraph.KShapeLayout sourceShapeLayout,
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
                scale = sourceShapeLayout.getProperty(CoreOptions.SCALE_FACTOR);
                targetShapeLayout.setPos(sourceShapeLayout.getXpos(), sourceShapeLayout.getYpos());
                targetShapeLayout.setSize(sourceShapeLayout.getWidth() / scale,
                        sourceShapeLayout.getHeight() / scale);

            } else if (pack.getKPort().isInstance(container)
                    || pack.getKLabel().isInstance(container)) {
                scale = ((KGraphElement) container.eContainer()).getData(KLayoutData.class).getProperty(
                                CoreOptions.SCALE_FACTOR);
                targetShapeLayout.setPos(sourceShapeLayout.getXpos() / scale,
                        sourceShapeLayout.getYpos() / scale);
                targetShapeLayout.setSize(sourceShapeLayout.getWidth() / scale,
                        sourceShapeLayout.getHeight() / scale);

                final KVector anchor = targetShapeLayout.getProperty(CoreOptions.PORT_ANCHOR);
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
            copyInsets(sourceShapeLayout.getInsets(), targetShapeLayout.getInsets());
        }
    }
    
    private void copyInsets(final de.cau.cs.kieler.klighd.kgraph.KInsets sourceInsets,
            final KInsets targetInsets) {

        targetInsets.setLeft(sourceInsets.getLeft());
        targetInsets.setRight(sourceInsets.getRight());
        targetInsets.setTop(sourceInsets.getTop());
        targetInsets.setBottom(sourceInsets.getBottom());
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
    private void transferShapeLayout(
            final KShapeLayout sourceShapeLayout,
            final de.cau.cs.kieler.klighd.kgraph.KShapeLayout targetShapeLayout,
            final boolean copyInsets, final boolean adjustScaling) {

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
                scale = sourceShapeLayout.getProperty(CoreOptions.SCALE_FACTOR);
                targetShapeLayout.setPos(sourceShapeLayout.getXpos(), sourceShapeLayout.getYpos());
                targetShapeLayout.setSize(sourceShapeLayout.getWidth() / scale,
                        sourceShapeLayout.getHeight() / scale);

            } else if (pack.getKPort().isInstance(container)
                    || pack.getKLabel().isInstance(container)) {
                scale = ((KGraphElement) container.eContainer()).getData(KLayoutData.class).getProperty(
                                CoreOptions.SCALE_FACTOR);
                targetShapeLayout.setPos(sourceShapeLayout.getXpos() / scale,
                        sourceShapeLayout.getYpos() / scale);
                targetShapeLayout.setSize(sourceShapeLayout.getWidth() / scale,
                        sourceShapeLayout.getHeight() / scale);

                final KVector anchor = targetShapeLayout.getProperty(CoreOptions.PORT_ANCHOR);
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
            copyInsets(sourceShapeLayout.getInsets(), targetShapeLayout.getInsets());
        }
    }
    
    private void copyInsets(final KInsets sourceInsets,
            final de.cau.cs.kieler.klighd.kgraph.KInsets targetInsets) {

        targetInsets.setLeft(sourceInsets.getLeft());
        targetInsets.setRight(sourceInsets.getRight());
        targetInsets.setTop(sourceInsets.getTop());
        targetInsets.setBottom(sourceInsets.getBottom());
    }

    /**
     * Transfers information from one edge layout to the other.
     *
     * @param viewModelEdgeLayout
     *            the view model edge layout
     * @param layoutEdgeLayout
     *            the layout edge layout
     */
    private void transferViewModelEdgeLayoutToLayoutEdge(
            final de.cau.cs.kieler.klighd.kgraph.KEdge viewModelEdge,
            final KEdgeLayout layoutEdgeLayout) {

        final KEdge layoutEdge = (KEdge) layoutEdgeLayout.eContainer();

        // do not notify listeners about any change on the displayed KGraph in order
        //  to avoid unnecessary diagram refresh cycles
        final boolean deliver = viewModelEdge.eDeliver();
        viewModelEdge.eSetDeliver(false);

        if (viewModelEdge.getSourcePoint() == null) {
            viewModelEdge.setSourcePoint(
                    de.cau.cs.kieler.klighd.kgraph.KGraphFactory.eINSTANCE.createKPoint());
        }
        
        // transfer the source point without checking
        final de.cau.cs.kieler.klighd.kgraph.KPoint sourcePoint =
                viewModelEdge.getSourcePoint();
        layoutEdgeLayout.getSourcePoint().setPos(sourcePoint.getX(), sourcePoint.getY());

        // transfer the bend points, reusing any existing KPoint instances
        final ListIterator<de.cau.cs.kieler.klighd.kgraph.KPoint> originBendIter =
                viewModelEdge.getBendPoints().listIterator();
        final ListIterator<KPoint> destBendIter = layoutEdgeLayout.getBendPoints().listIterator();
        while (originBendIter.hasNext()) {
            final de.cau.cs.kieler.klighd.kgraph.KPoint originPoint = originBendIter.next();
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

        if (viewModelEdge.getTargetPoint() == null) {
            viewModelEdge.setTargetPoint(
                    de.cau.cs.kieler.klighd.kgraph.KGraphFactory.eINSTANCE.createKPoint());
        }
        
        // transfer the target point without checking
        final de.cau.cs.kieler.klighd.kgraph.KPoint targetPoint = viewModelEdge.getTargetPoint();
        layoutEdgeLayout.getTargetPoint().setPos(targetPoint.getX(), targetPoint.getY());

        // reactivate notifications & fire a notification
        //  bringing the observing diagram controller to update the displayed diagram
        viewModelEdge.eSetDeliver(deliver);
    }

    /**
     * Transfers information from one edge layout to the other.
     *
     * @param layoutEdgeLayout
     *            the layout edge layout
     * @param viewModelEdgeLayout
     *            the view model edge layout
     * @param adjustments
     */
    private void transferLayoutEdgeToViewModelEdgeLayout(final KEdgeLayout layoutEdgeLayout,
            final de.cau.cs.kieler.klighd.kgraph.KEdge viewModelEdge,
            final boolean adjustments) {

        final KEdge layoutEdge = (KEdge) layoutEdgeLayout.eContainer();

        // do not notify listeners about any change on the displayed KGraph in order
        //  to avoid unnecessary diagram refresh cycles
        final boolean deliver = viewModelEdge.eDeliver();
        viewModelEdge.eSetDeliver(false);

        if (viewModelEdge.getSourcePoint() == null) {
            viewModelEdge.setSourcePoint(
                    de.cau.cs.kieler.klighd.kgraph.KGraphFactory.eINSTANCE.createKPoint());
        }
        
        // we want to transfer the EDGE_ROUTING and JUNCTION_POINTS
        viewModelEdge.setProperty(CoreOptions.EDGE_TYPE,
                layoutEdgeLayout.getProperty(CoreOptions.EDGE_TYPE));
        viewModelEdge.setProperty(CoreOptions.JUNCTION_POINTS,
                layoutEdgeLayout.getProperty(CoreOptions.JUNCTION_POINTS));
        
        final KNode layoutSourceNode = layoutEdge.getSource();
        final KVector offset = new KVector();

        // this flag indicates the requirement of the adjusting the port position
        //  wrt. the scaling factor being associated with the port's parent node
        final boolean adjustSourcePortPosition;

        final boolean sourcePointDeliver = viewModelEdge.getSourcePoint().eDeliver();
        viewModelEdge.getSourcePoint().eSetDeliver(false);

        if (adjustments) {
            // If the target is a descendant of the source, the edge's source point is already
            // relative to the source node's position.
            if (!ElkUtil.isDescendant(layoutEdge.getTarget(), layoutSourceNode)) {
                adjustSourcePortPosition = false;
                final KShapeLayout sourceLayout = layoutSourceNode.getData(KShapeLayout.class);
                offset.x = -sourceLayout.getXpos();
                offset.y = -sourceLayout.getYpos();
            } else {
                adjustSourcePortPosition = true;
                final KShapeLayout sourceLayout = layoutSourceNode.getData(KShapeLayout.class);
                offset.x = sourceLayout.getInsets().getLeft();
                offset.y = sourceLayout.getInsets().getTop();
            }

            final KRendering portRendering = viewModelEdge.getSourcePort() == null
                    ? null
                    : viewModelEdge.getSourcePort().getData(KRendering.class);

            checkAndCopyPoint(layoutEdgeLayout.getSourcePoint(),
                    viewModelEdge.getSourcePoint(),
                    layoutSourceNode,
                    layoutEdge.getSourcePort(),
                    viewModelEdge.getSource().getData(KRendering.class),
                    portRendering,
                    offset,
                    adjustSourcePortPosition);
        } else {
            KPoint p = layoutEdgeLayout.getSourcePoint();
            viewModelEdge.getSourcePoint().setPos(p.getX(), p.getY());
        }
        viewModelEdge.getSourcePoint().eSetDeliver(sourcePointDeliver);

        // transfer the bend points, reusing any existing KPoint instances
        final ListIterator<KPoint> originBendIter = layoutEdgeLayout.getBendPoints().listIterator();
        final ListIterator<de.cau.cs.kieler.klighd.kgraph.KPoint> destBendIter =
                viewModelEdge.getBendPoints().listIterator();
        while (originBendIter.hasNext()) {
            final KPoint originPoint = originBendIter.next();
            de.cau.cs.kieler.klighd.kgraph.KPoint destPoint;
            if (destBendIter.hasNext()) {
                destPoint = destBendIter.next();
            } else {
                destPoint = de.cau.cs.kieler.klighd.kgraph.KGraphFactory.eINSTANCE.createKPoint();
                destBendIter.add(destPoint);
            }
            destPoint.setPos(originPoint.getX(), originPoint.getY());
        }
        // remove any superfluous points
        while (destBendIter.hasNext()) {
            destBendIter.next();
            destBendIter.remove();
        }

        if (viewModelEdge.getTargetPoint() == null) {
            viewModelEdge.setTargetPoint(
                    de.cau.cs.kieler.klighd.kgraph.KGraphFactory.eINSTANCE.createKPoint());
        }
        
        final KNode layoutTargetNode = layoutEdge.getTarget();
        offset.reset();

        // this flag indicates the requirement of the adjusting the port position
        //  wrt. the scaling factor being associated with the port's parent node
        final boolean adjustTargetPortPosition;

        final boolean targetPointDeliver = viewModelEdge.getTargetPoint().eDeliver();
        viewModelEdge.getTargetPoint().eSetDeliver(false);

        if (adjustments) {
            if (layoutSourceNode.getParent() == layoutTargetNode.getParent()) {
                adjustTargetPortPosition = false;
                // The source and target are on the same level, so just subtract the target
                // position.
                final KShapeLayout targetLayout = layoutTargetNode.getData(KShapeLayout.class);
                offset.x = -targetLayout.getXpos();
                offset.y = -targetLayout.getYpos();
            } else {
                // The source and target are on different levels, so transform coordinate
                // system.
                KNode referenceNode = layoutSourceNode;
                if (!ElkUtil.isDescendant(layoutTargetNode, layoutSourceNode)) {
                    adjustTargetPortPosition = true;
                    referenceNode = referenceNode.getParent();
                } else {
                    adjustTargetPortPosition = false;
                }
                ElkUtil.toAbsolute(offset, referenceNode);
                ElkUtil.toRelative(offset, layoutTargetNode.getParent());
                final KShapeLayout targetLayout = layoutTargetNode.getData(KShapeLayout.class);
                offset.x -= targetLayout.getXpos();
                offset.y -= targetLayout.getYpos();
            }

            checkAndCopyPoint(layoutEdgeLayout.getTargetPoint(),
                    viewModelEdge.getTargetPoint(),
                    layoutTargetNode,
                    layoutEdge.getTargetPort(),
                    viewModelEdge.getTarget().getData(KRendering.class),
                    viewModelEdge.getTargetPort() == null
                        ? null
                        : viewModelEdge.getTargetPort().getData(KRendering.class),
                    offset,
                    adjustTargetPortPosition);
        } else {
            KPoint p = layoutEdgeLayout.getTargetPoint();
            viewModelEdge.getTargetPoint().setPos(p.getX(), p.getY());
        }
        viewModelEdge.getTargetPoint().eSetDeliver(targetPointDeliver);

        // reactivate notifications & fire a notification
        //  bringing the observing diagram controller to update the displayed diagram
        viewModelEdge.eSetDeliver(deliver);
        viewModelEdge.eNotify(new ENotificationImpl(
                (InternalEObject) viewModelEdge, Notification.SET,
                KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS, null, null));
    }

    /**
     * Handle the given edge that was excluded from layout. Set its source and
     * target points to appropriate positions on the border of the respective elements.
     *
     * @param edge an excluded edge
     */
    private void handleExcludedEdge(final de.cau.cs.kieler.klighd.kgraph.KEdge edge) {
        boolean deliver = edge.eDeliver();
        edge.eSetDeliver(false);
        edge.getBendPoints().clear();

        final de.cau.cs.kieler.klighd.kgraph.KNode sourceNode = edge.getSource();
        final de.cau.cs.kieler.klighd.kgraph.KNode targetNode = edge.getTarget();
        final de.cau.cs.kieler.klighd.kgraph.KPort sourcePort = edge.getSourcePort();
        final de.cau.cs.kieler.klighd.kgraph.KPort targetPort = edge.getTargetPort();
        final boolean targetInSource = KGraphUtil.isDescendant(targetNode, sourceNode);

        // determine the source point
        final KVector sourcePoint = toElementBorder(sourceNode, sourcePort, targetNode, targetPort);
        if (targetInSource) {
            if (sourceNode.getInsets() != null) {
                sourcePoint.add(-sourceNode.getInsets().getLeft(),
                        -sourceNode.getInsets().getTop());
            }
        } else {
            sourcePoint.add(sourceNode.getXpos(), sourceNode.getYpos());
        }
        de.cau.cs.kieler.klighd.kgraph.KPoint sourceKPoint = edge.getSourcePoint();
        if (sourceKPoint == null) {
            sourceKPoint = de.cau.cs.kieler.klighd.kgraph.KGraphFactory.eINSTANCE.createKPoint();
            edge.setSourcePoint(sourceKPoint);
        }
        sourceKPoint.applyVector(sourcePoint);

        // determine the target point
        final KVector targetPoint = toElementBorder(targetNode, targetPort, sourceNode, sourcePort);
        targetPoint.add(targetNode.getXpos(), targetNode.getYpos());
        if (targetInSource) {
            KGraphUtil.toAbsolute(targetPoint, targetNode.getParent());
            KGraphUtil.toRelative(targetPoint, sourceNode);
        } else if (sourceNode.getParent() != targetNode.getParent()) {
            KGraphUtil.toAbsolute(targetPoint, targetNode.getParent());
            KGraphUtil.toRelative(targetPoint, sourceNode.getParent());
        }
        de.cau.cs.kieler.klighd.kgraph.KPoint targetKPoint = edge.getTargetPoint();
        if (targetKPoint == null) {
            targetKPoint = de.cau.cs.kieler.klighd.kgraph.KGraphFactory.eINSTANCE.createKPoint();
            edge.setTargetPoint(targetKPoint);
        }
        targetKPoint.applyVector(targetPoint);

        // notify the listeners
        edge.eSetDeliver(deliver);
        edge.eNotify(new ENotificationImpl((InternalEObject) edge, Notification.SET,
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
    private void checkAndCopyPoint(final KPoint originPoint,
            final de.cau.cs.kieler.klighd.kgraph.KPoint destinationPoint,
            final KNode node, final KPort port, final KRendering nodeRendering,
            final KRendering portRendering, final KVector offset, final boolean adjustPortPos) {

        KVector p = originPoint.createVector();
        final KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        final float scale = node.getData(KShapeLayout.class).getProperty(CoreOptions.SCALE_FACTOR);

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
    private KVector toElementBorder(final de.cau.cs.kieler.klighd.kgraph.KNode centerNode,
            final de.cau.cs.kieler.klighd.kgraph.KPort centerPort,
            final de.cau.cs.kieler.klighd.kgraph.KNode remoteNode,
            final de.cau.cs.kieler.klighd.kgraph.KPort remotePort) {

        KVector point = new KVector();
        if (remotePort == null) {
            point.x = remoteNode.getWidth() / 2;
            point.y = remoteNode.getHeight() / 2;
        } else {
            point.x = remotePort.getXpos() + remotePort.getWidth() / 2;
            point.y = remotePort.getYpos() + remotePort.getHeight() / 2;
        }
        point.add(remoteNode.getXpos(), remoteNode.getYpos());
        if (centerNode.getParent() != remoteNode.getParent()) {
            KGraphUtil.toAbsolute(point, remoteNode.getParent());
            KGraphUtil.toRelative(point, centerNode.getParent());
        }

        point.add(-centerNode.getXpos(), -centerNode.getYpos());
        if (centerPort == null) {
            point = AnchorUtil.collideTowardsCenter(
                    point,
                    centerNode.getWidth(),
                    centerNode.getHeight(),
                    centerNode.getData(KRendering.class));
        } else {
            point.add(-centerPort.getXpos(), -centerPort.getYpos());
            point = AnchorUtil.collideTowardsCenter(
                    point,
                    centerPort.getWidth(),
                    centerPort.getHeight(),
                    centerPort.getData(KRendering.class));
            point.add(centerPort.getXpos(), centerPort.getYpos());
        }

        return point;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Animation Time

    /**
     * Calculates animation time for the given graph size. If the viewer is not visible,
     * the animation time is 0.
     * 
     * @param mapping a mapping of the layout graph
     * @param config the layout configurator from which to read animation settings, or {@code null}
     * @param viewerNotVisible whether the diagram viewer is currently not visible
     * @return number of milliseconds to animate, or 0 if no animation is desired
     */
    private int calcAnimationTime(final LayoutMapping mapping, final IPropertyHolder settings,
            final boolean viewerNotVisible) {
        
        boolean animate = settings.getProperty(CoreOptions.ANIMATE);
        if (animate) {
            int minTime = settings.getProperty(CoreOptions.MIN_ANIM_TIME);
            if (minTime < 0) {
                minTime = 0;
            }
            int maxTime = settings.getProperty(CoreOptions.MAX_ANIM_TIME);
            if (maxTime < minTime) {
                maxTime = minTime;
            }
            int factor = settings.getProperty(CoreOptions.ANIM_TIME_FACTOR);
            if (factor > 0) {
                int graphSize = countNodes(mapping.getLayoutGraph());
                int time = minTime + (int) (factor * Math.sqrt(graphSize));
                return time <= maxTime ? time : maxTime;
            } else {
                return minTime;
            }
        }
        return 0;
    }

    /**
     * Counts the total number of children in the given node, including deep hierarchies.
     * 
     * @param node
     *            parent node
     * @return number of children and grandchildren in the given parent
     */
    private static int countNodes(final KNode node) {
        int count = 0;
        for (KNode child : node.getChildren()) {
            count += countNodes(child) + 1;
        }
        return count;
    }

}
