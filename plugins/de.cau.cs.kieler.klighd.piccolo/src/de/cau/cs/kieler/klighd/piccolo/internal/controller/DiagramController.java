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
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingUtil;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.macrolayout.KlighdLayoutManager;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.ApplyBendPointsActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.ApplySmartBoundsActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.FadeEdgeInActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.FadeNodeInActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.IStartingAndFinishingActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IInternalKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KChildAreaNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeAbstractNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KPortNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.NodeDisposeListener;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.util.Iterables2;
import de.cau.cs.kieler.klighd.util.KlighdPredicates;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.ModelingUtil;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * Overall manager of KGraph+KRendering+KLayoutData-based diagrams.<br>
 * Instances of this class are in charge of adding, updating, and removing representatives of
 * {@link KGraphElement KGraphElements} to/in/from its {@link KNodeTopNode}, our internal Piccolo2d
 * root element.<br>
 * <br>
 * The construction of the particular figures described in terms KRendering data is than delegated
 * to {@link KNodeRenderingController KNodeRenderingControllers}, {@link KPortRenderingController
 * KPortRenderingControllers}, {@link KEdgeRenderingController KEdgeRenderingControllers}, and
 * {@link KLabelRenderingController KLabelRenderingControllers}.
 *
 * @author mri
 * @author chsch
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class DiagramController {

    /** the property for the Piccolo2D representation of a node. */
    static final IProperty<KNodeAbstractNode> REP = new Property<KNodeAbstractNode>(
            "klighd.piccolo.representation");

    /** the property for the Piccolo2D representation of an edge. */
    private static final IProperty<KEdgeNode> EDGE_REP = new Property<KEdgeNode>(
            "klighd.piccolo.representation");

    /** the property for the Piccolo2D representation of a port. */
    private static final IProperty<KPortNode> PORT_REP = new Property<KPortNode>(
            "klighd.piccolo.representation");

    /** the property for the Piccolo2D representation of a label. */
    private static final IProperty<KLabelNode> LABEL_REP = new Property<KLabelNode>(
            "klighd.piccolo.representation");

    /** the Piccolo2D node representing the top node in the graph. */
    private final KNodeTopNode topNode;

    /** the main camera that determines the actually drawn picture. */
    private final KlighdMainCamera canvasCamera;

    /** the zoom controller implementing the zoom functionalities. */
    private final DiagramZoomController zoomController;

    /** whether to sync the representation with the graph model. */
    private final boolean sync;

    /** whether edges are drawn before nodes, i.e. nodes have priority over edges. */
    private final boolean edgesFirst;

    /** whether to record layout changes, will be set to true by the KlighdLayoutManager. */
    private boolean record = false;

    /** the layout changes to graph elements while recording. */
    private final Map<IKGraphElementNode, Object> recordedChanges = Maps.newLinkedHashMap();


    /**
     * Constructs a diagram controller for the given KGraph.
     *
     * @param graph
     *            the diagram describing KGraph rooted by a {@link KNode}
     * @param camera
     *            the {@link KlighdMainCamera} to be used
     * @param sync
     *            true if the visualization should be synchronized with the graph; false otherwise<br>
     *            <b>Hint</b>: setting to false will prevent the application of automatic layout
     * @param edgesFirst
     *            determining whether edges are drawn before nodes, i.e. nodes have priority over
     *            edges
     */
    public DiagramController(final KNode graph, final KlighdMainCamera camera, final boolean sync,
            final boolean edgesFirst) {
        DiagramControllerHelper.resetGraphElement(graph);

        this.sync = sync;
        this.edgesFirst = edgesFirst;

        this.canvasCamera = camera;

        this.topNode = new KNodeTopNode(graph, edgesFirst);
        final RenderingContextData contextData = RenderingContextData.get(graph);
        contextData.setProperty(REP, topNode);

        this.zoomController = new DiagramZoomController(topNode, canvasCamera, this);

        canvasCamera.getRoot().addChild(topNode);
        canvasCamera.setDisplayedKNodeNode(topNode);

        addExpansionListener(topNode);

        contextData.setProperty(KlighdInternalProperties.ACTIVE, true);

        topNode.setExpanded(true);
//        addChildren(topNode);
    }

    /**
     * Returns the root of the represented graph.
     *
     * @return the root node
     */
    public KNodeTopNode getNode() {
        return topNode;
    }

    /**
     * Returns whether the representation is synchronized with the graph.
     *
     * @return true if the representation is synchronized with the graph; false else
     */
    public boolean getSync() {
        return sync;
    }

    /**
     * Returns the employed root camera.
     *
     * @return the employed root camera.
     */
    public KlighdMainCamera getMainCamera() {
        return canvasCamera;
    }

    /**
     * Returns the employed zoom controller.
     *
     * @return the employed zoom controller.
     */
    public DiagramZoomController getZoomController() {
        return zoomController;
    }

    /**
     * Returns the 'recording' state of <code>this</code> controller.
     *
     * @return <code>true</code> if <code>this</code> controller is in recording mode.
     */
    public boolean isRecording() {
        return record;
    }

    /**
     * Starts to record layout changes in the model instead of instantly applying them to the
     * visualization.<br>
     * <br>
     * Executing {@link #stopRecording(ZoomStyle, KNode, int)} applies all recorded layout changes.
     *
     * @see de.cau.cs.kieler.klighd.internal.ILayoutRecorder#startRecording()
     *      ILayoutRecorder#startRecording()
     */
    public void startRecording() {
        record = true;
    }

    /**
     * @param zoomStyle
     *            the style used to zoom, e.g. zoom to fit or zoom to focus
     * @param focusNode
     *            the {@link KNode} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS}, is ignored otherwise
     * @param animationTime
     *            duration of the animated layout
     *
     * @see de.cau.cs.kieler.klighd.internal.ILayoutRecorder#stopRecording(ZoomStyle, KNode, int)
     *      ILayoutRecorder#stopRecording(ZoomStyle, KNode, int)
     */
    public void stopRecording(final ZoomStyle zoomStyle, final KNode focusNode,
            final int animationTime) {
        if (record) {
            record = false;

            handleRecordedChanges(zoomStyle, focusNode, animationTime);
        }
    }

    /**
     * Returns the Piccolo2D representation for the given diagram element.
     *
     * @param diagramElement
     *            the diagram element
     * @return the Piccolo2D representation
     */
    public IKGraphElementNode getRepresentation(final KGraphElement diagramElement) {
        return RenderingContextData.get(diagramElement).getProperty(REP);
    }

    /**
     * Returns the Piccolo2D representation for the given diagram element.
     *
     * @param diagramElement
     *            the diagram element
     * @return the Piccolo2D representation
     */
    private KNodeAbstractNode getKNodeRepresentation(final KNode diagramElement) {
        return RenderingContextData.get(diagramElement).getProperty(REP);
    }

    /**
     * Collapses the representation of the given node.
     *
     * @param node
     *            the node
     */
    public void collapse(final KNode node) {
        final KNodeAbstractNode nodeRep = getKNodeRepresentation(node);
        if (nodeRep != null) {
            nodeRep.setExpanded(false);
        }

        zoomController.setFocusNode(node);
    }

    /**
     * Expands the representation of the given node.
     *
     * @param node
     *            the node
     */
    public void expand(final KNode node) {
        final KNodeAbstractNode nodeRep = getKNodeRepresentation(node);
        if (nodeRep != null) {
            nodeRep.setExpanded(true);
        }

        zoomController.setFocusNode(node);
    }

    /**
     * @param node
     *            the node
     * @return true if this node is expanded.
     */
    public boolean isExpanded(final KNode node) {
        final KNodeAbstractNode nodeRep = getKNodeRepresentation(node);
        if (nodeRep != null) {
            return nodeRep.isExpanded();
        }
        return false;
    }

    /**
     * Changes the representation of the given node.
     *
     * @param node
     *            the node
     */
    public void toggleExpansion(final KNode node) {
        final KNodeAbstractNode nodeRep = getKNodeRepresentation(node);
        if (nodeRep != null) {
            nodeRep.toggleExpansion();
        }

        zoomController.setFocusNode(node);
    }

    /**
     * Provides the visibility state of the given diagram element, assuming the parent
     * {@link KGraphElement} is visible. A node is said to be visible if it is drawn in the
     * currently depicted diagram excerpt (viewport). <br>
     * <br>
     * Note that a recursive visibility check along the containment hierarchy is done only if
     * <code>checkContainment</code> is <code>true</code>. Otherwise that is omitted for performance
     * reasons. Thus, given the nested diagram nodes A contains B contains C with A collapsed this
     * method may return <code>true</code> for C if <code>checkContainment</code> is
     * <code>false</code>.
     *
     * @param diagramElement
     *            a {@link KGraphElement}
     * @param checkContainment
     *            whether the parent (containment) hierarchy is to be checked, too
     * @return <code>true</code> if the {@link KGraphElement} <code>diagramElement</code> is
     *         visible, <code>false</code> otherwise.
     */
    public boolean isVisible(final KGraphElement diagramElement, final boolean checkContainment) {
        final PNode p = (PNode) getRepresentation(diagramElement);

        // first check whether 'diagramElement' is represented by any figure (PNode)
        //  that is contained by any other figure (and thus hopefully contained in the figure tree)
        if (p == canvasCamera.getDisplayedKNodeNode()) {
            return true;
        } else if (p == null || p.getParent() == null) {
            return false;
        }

        // check whether the lower visibility scale bound is exceeded
        final float viewScale = (float) canvasCamera.getViewTransformReference().getScaleX();
        final float lowerBound = diagramElement.getData(KLayoutData.class)
                .getProperty(KlighdProperties.VISIBILITY_SCALE_LOWER_BOUND).floatValue();

        if (viewScale < lowerBound) {
            return false;
        }

        // the upper visibility scale bound is not checked because I think it is unlikely that a
        //  label or any other kgraph element is masked if the diagram scale exceeds a certain value

        if (checkContainment) {
            if (!NodeUtil.isDisplayed(p, canvasCamera)) {
                // this way we check whether p is (transitively) part of the diagram's currently
                //  visible PNode network
                return false;
            }
        }

        final KNodeAbstractNode clip = getClipNode();
        final PBounds camBounds = canvasCamera.getViewBounds();
        final PBounds elemFullBounds = NodeUtil.clipRelativeGlobalBoundsOf(p, clip);
        return elemFullBounds != null && elemFullBounds.intersects(camBounds);
    }

    /**
     * Hides the given {@link KGraphElement} from the diagram by removing the related
     * {@link IKGraphElementNode} from the network of {@link PNode PNodes}. In combination with
     * {@link #show(KGraphElement)} this method can be used for changing the diagram's amount of
     * detail without changing the view model.
     *
     * @param diagramElement
     *            the {@link KGraphElement} to hide from the diagram
     */
    public void hide(final KGraphElement diagramElement) {
        final KGraphElement parent = (KGraphElement) diagramElement.eContainer();
        if (parent == null) {
            return;
        }

        remove(diagramElement, false);
    }

    /**
     * Shows the given {@link KGraphElement} from the diagram by (re-) adding a related
     * {@link IKGraphElementNode} to the network of {@link PNode PNodes}. In combination with
     * {@link #hide(KGraphElement)} this method can be used for changing the diagram's amount of
     * detail without changing the view model.
     *
     * @param diagramElement
     *            the {@link KGraphElement} to (re-) show in the diagram
     */
    public void show(final KGraphElement diagramElement) {
        final KGraphElement parent = (KGraphElement) diagramElement.eContainer();
        if (parent == null) {
            return;
        }

        add(diagramElement, true);
    }

    private static final String INVALID_CLIP_NODE_ERROR_MSG =
            "KLighD: Diagram shall be clipped to KNode XX that is (currently) not depicted in the"
            + " diagram right now. Make sure that it is contained by expanding its full parent"
            + " node hierarchy and/or calling IViewer.show(XX);";

    /**
     * Limits the visible elements of the diagram to the content of the given {@link KNode} without
     * causing any change on the view model. Hence, this method can be used for changing the
     * diagram's amount of detail without changing the view model.<br>
     * The clip can be reset to the whole diagram by calling <code>clip((KNode) null)</code>.
     *
     * @param diagramElement
     *            the diagram element to which the diagram view is to be limited, may be
     *            <code>null</code>
     */
    public void clip(final KNode diagramElement) {
        final KNodeAbstractNode node =
                (diagramElement == null) ? topNode : getKNodeRepresentation(diagramElement);

        if (node == null) {
            throw new RuntimeException(INVALID_CLIP_NODE_ERROR_MSG.replace("XX",
                    diagramElement.toString()));
        }

        final KNodeAbstractNode currentRootNode = canvasCamera.getDisplayedKNodeNode();
        if (currentRootNode == node) {
            return;
        }

        if (node.getRoot() == null) {
            throw new RuntimeException(INVALID_CLIP_NODE_ERROR_MSG.replace("XX",
                    diagramElement.toString()));
        }

        canvasCamera.exchangeDisplayedKNodeNode(node);
        zoomController.setFocusNode(diagramElement);
    }

    private KNodeAbstractNode getClipNode() {
        return canvasCamera.getDisplayedKNodeNode();
    }

    /**
     * Provides the currently set diagram clip.
     *
     * @return the {@link KNode} that is currently clipped.
     */
    public KNode getClip() {
        final KNodeAbstractNode node = getClipNode();
        return node.getViewModelElement();
    }

    /* --------------------------------------------- */
    /* internal part */
    /* --------------------------------------------- */

    void recordChange(final IKGraphElementNode node, final Object change) {
        recordedChanges.put(node, change);
    }

    private final Set<AbstractKGERenderingController<?, ?>> dirtyDiagramElements = Sets.newHashSet();

    void scheduleRenderingUpdate(final AbstractKGERenderingController<?, ?> controller) {
        renderingUpdater.cancel();
        synchronized (dirtyDiagramElements) {
            dirtyDiagramElements.add(controller);
        }
        renderingUpdater.schedule(1);
    }

    private final Job renderingUpdater = new Job("KLighD DiagramElementUpdater") {
        {
            this.setSystem(true);
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor) {
            if (Display.getCurrent() == null && PlatformUI.isWorkbenchRunning()) {
                PlatformUI.getWorkbench().getDisplay().asyncExec(this.diagramUpdateRunnable);
            } else {
                this.diagramUpdateRunnable.run();
            }
            return Status.OK_STATUS;
        }

        private final Runnable diagramUpdateRunnable = new Runnable() {

            /**
             * {@inheritDoc}
             */
            public void run() {
                final Set<AbstractKGERenderingController<?, ?>> copy;
                synchronized (dirtyDiagramElements) {
                    copy = ImmutableSet.copyOf(dirtyDiagramElements);
                    dirtyDiagramElements.clear();
                }
                for (final AbstractKGERenderingController<?, ?> ctrl : copy) {
                    ctrl.updateRenderingInUi();
                }
            }
        };
    };


    /**
     * Applies the recorded layout changes by creating appropriate activities.
     */
    private void handleRecordedChanges(final ZoomStyle zoomStyle, final KNode focusNode,
            final int animationTime) {

        // create activities to apply all recorded changes
        for (final Map.Entry<IKGraphElementNode, ?> recordedChange : recordedChanges.entrySet()) {
            // create the activity to apply the change
            PInterpolatingActivity activity;
            final PNode shapeNode;
            if (recordedChange.getKey() instanceof KEdgeNode) {
                // edge layout changed

                final KEdgeNode edgeNode = (KEdgeNode) recordedChange.getKey();
                shapeNode = edgeNode;

                // the following case is still to be implemented!
                // if (recordedChange.getValue() == KlighdLayoutManager.LAYOUT_DATA_UNCHANGED_VALUE) {

                @SuppressWarnings("unchecked")
                final Pair<Point2D[], Point2D[]> value =
                        (Pair<Point2D[], Point2D[]>) recordedChange.getValue();
                final Point2D[] bends = value.getFirst();
                final Point2D[] junctions = value.getSecond();

                if (!edgeNode.getVisible()) {
                    // the visibility is set to false for newly introduced edges in #addEdge
                    //  for avoiding unnecessary flickering and indicating to fade it in
                    activity = new FadeEdgeInActivity(edgeNode, bends, junctions,
                            animationTime > 0 ? animationTime : 1);
                } else {
                    activity = new ApplyBendPointsActivity(edgeNode, bends, junctions,
                            animationTime > 0 ? animationTime : 1);
                }
            } else {
                // shape layout changed
                shapeNode = (PNode) recordedChange.getKey();
                final PBounds bounds;

                // check whether an actual bounds change occurred, and if so get the new bounds
                if (recordedChange.getValue() == KlighdLayoutManager.LAYOUT_DATA_UNCHANGED_VALUE) {
                    bounds = null;
                } else {
                    bounds = (PBounds) recordedChange.getValue();
                }

                final float scale;
                if (shapeNode instanceof KNodeNode) {
                    scale = ((KNodeNode) shapeNode).getViewModelElement().getData(KShapeLayout.class)
                                    .getProperty(LayoutOptions.SCALE_FACTOR);
                } else {
                    scale = 1f;
                }

                if (!shapeNode.getVisible()) {
                    // the visibility is set to false for newly introduced elemen       ts in #addNode,
                    //  #addPort, and #addLabel for avoiding unnecessary flickering and indicating
                    //  to fade it in
                    // note the special behavior of FadeNodeInActivity if 'bounds' is 'null',
                    //  i.e. 'LAYOUT_DATA_UNCHANGED_VALUE' was notified
                    activity = new FadeNodeInActivity(shapeNode, bounds,
                            scale, animationTime > 0 ? animationTime : 1);
                } else if (bounds == null) {
                    continue;

                } else {
                    activity = new ApplySmartBoundsActivity(shapeNode, bounds,
                            scale, animationTime > 0 ? animationTime : 1);
                }
            }
            if (animationTime > 0) {
                // schedule the activity
                NodeUtil.schedulePrimaryActivity(shapeNode, activity);
            } else {
                // unschedule a currently running primary activity on the node if any
                NodeUtil.unschedulePrimaryActivity(shapeNode);
                // instantly apply the activity without scheduling it
                ((IStartingAndFinishingActivity) activity).activityStarted();
                ((IStartingAndFinishingActivity) activity).activityFinished();
            }
        }
        recordedChanges.clear();

        // apply a proper zoom handling if requested
        getZoomController().zoom(zoomStyle, focusNode, animationTime);
    }

    /**
     * Adds a listener on the expansion of the child area of the given node representation.
     *
     * @param nodeNode
     *            the node representation
     */
    private void addExpansionListener(final KNodeAbstractNode nodeNode) {
        final KChildAreaNode childAreaNode = nodeNode.getChildAreaNode();
        if (childAreaNode != null) {
            final KNode node = nodeNode.getViewModelElement();

            ((PNode) nodeNode).addPropertyChangeListener(IKNodeNode.PROPERTY_EXPANSION,
                    new PropertyChangeListener() {
                        public void propertyChange(final PropertyChangeEvent event) {
                            if ((Boolean) event.getNewValue()) {
                                // i.e. the child area of node 'nodeNode' has been expanded ...
                                addChildren(nodeNode);
                            } else {
                                // i.e. the child area of node 'nodeNode' has been collapsed ...
                                removeChildren(node);
                            }

                            if (nodeNode instanceof KNodeTopNode) {
                                // we're done :-)
                                // (root KNodes might also contain KRenderings, e.g. in case mouse
                                // actions are associated with the canvas or due to custom lazy
                                // loading mechanisms)
                                return;
                            }

                            // in case distinct 'expanded' and/or 'collapsed' KRendering definitions
                            //  are given the rendering needs to be updated/exchanged after changing the
                            //  expansion state, so ...
                            if (Iterables.any(Iterables.filter(node.getData(), KRendering.class),
                                    KlighdPredicates.isCollapsedOrExpandedRendering())) {
                                nodeNode.getRenderingController().updateRenderingInUi();
                            }
                        }
                    });
        }
    }

    /**
     * Internal convenience method for adding representations of {@link KGraphElement
     * KGraphElements}. The addition requires the existence of a representation of the
     * <code>element</code>'s container {@link KGraphElement}.
     *
     * @param element
     *            the {@link KGraphElement} to be represented
     * @param forceShow
     *            if <code>true</code> add <code>element</code> to the diagram regardless of its
     *            {@link KlighdProperties#SHOW} property value
     */
    private void add(final KGraphElement element, final boolean forceShow) {
        if (element.eContainer() == null) {
            return;
        }

        final IKGraphElementNode parentRep =
                getRepresentation((KGraphElement) element.eContainer());

        switch (element.eClass().getClassifierID()) {
        case KGraphPackage.KNODE:
            if (parentRep != null) {
                addNode((KNodeAbstractNode) parentRep, (KNode) element, forceShow);
            }
            break;
        case KGraphPackage.KPORT:
            if (parentRep != null) {
                addPort((KNodeNode) parentRep, (KPort) element, forceShow);
            }
            break;
        case KGraphPackage.KLABEL:
            if (parentRep != null) {
                addLabel((IInternalKGraphElementNode.IKLabeledGraphElementNode<?>) parentRep,
                        (KLabel) element, forceShow);
            }
            break;
        case KGraphPackage.KEDGE:
            addEdge((KEdge) element, forceShow);
            break;
        }
    }

    /**
     * Internal convenience method for removing representations of {@link KGraphElement
     * KGraphElements}.
     *
     * @param element
     *            the {@link KGraphElement} to be removed from the diagram
     * @param releaseControllers
     *            flag indicating whether controller instances shall be removed from lookup tables
     *            because the element actually has been removed from the view model rather than just
     *            hidden
     */
    private void remove(final KGraphElement element, final boolean releaseControllers) {
        if (element.eContainer() == null) {
            return;
        }

        switch (element.eClass().getClassifierID()) {
        case KGraphPackage.KNODE:
            removeNode((KNode) element, releaseControllers);
            break;
        case KGraphPackage.KPORT:
            removePort((KPort) element, releaseControllers);
            break;
        case KGraphPackage.KEDGE:
            removeEdge((KEdge) element, releaseControllers);
            break;
        case KGraphPackage.KLABEL:
            removeLabel((KLabel) element, releaseControllers);
            break;
        }
    }


    /**
     * Filter predicate returning <code>true</code> for those <code>kge</code>'s whose corresponding
     * KlighdProperties.SHOW property value evaluates to <code>true</code>, <code>false</code> else.
     */
    private static final Predicate<KGraphElement> NON_HIDDEN_KGE_FILTER =
            new Predicate<KGraphElement>() {
        public boolean apply(final KGraphElement kge) {
            final KGraphData data = kge.getData(KLayoutDataPackage.eINSTANCE.getKLayoutData());
            return data.getProperty(KlighdProperties.SHOW);
        }
    };

    /**
     * Handles the children of the parent node.
     *
     * @param parentNode
     *            the parent structure node representing a KNode
     */
    private void addChildren(final KNodeAbstractNode parentNode) {
        final KNode parent = parentNode.getViewModelElement();

        // create the nodes
        for (final KNode child : parent.getChildren()) {
            addNode(parentNode, child, false);
        }

        RenderingContextData.get(parent).setProperty(KlighdInternalProperties.POPULATED, true);

        if (sync) {
            // remove any existing children sync adapters, which may be out-of-date
            ModelingUtil.removeAdapters(parent, CHILDREN_SYNC_ADAPTERS);

            // add an adapter on the node's children
            parent.eAdapters().add(new ChildrenSyncAdapter(parentNode));
        }
    }

    /**
     * Adds a representation for the node to the given parent.
     *
     * @param parent
     *            the parent node
     * @param node
     *            the node
     * @param forceShow
     *            if <code>true</code> add <code>element</code> to the diagram regardless of its
     *            {@link KlighdProperties#SHOW} property value
     */
    private void addNode(final KNodeAbstractNode parent, final KNode node, final boolean forceShow) {
        final RenderingContextData contextData = RenderingContextData.get(node);
        final KNodeAbstractNode nodeRep = contextData.getProperty(REP);

        KNodeNode nodeNode;
        if (nodeRep instanceof KNodeTopNode) {
            // if the node is the current top-node something went wrong
            throw new RuntimeException("The top-node can never be made a child node");
        } else {
            nodeNode = (KNodeNode) nodeRep;
        }

        // only add the representation if it is not added already
        //  note that this condition implies that invisible children's children
        //  that are still contained in there parent will break the recursion
        //  of this method
        if (nodeNode != null && nodeNode.getParent() != null) {
            return;
        }

        final int expand;

        // if there is no Piccolo2D representation of the node create it
        if (nodeNode == null) {
            final KGraphData data = node.getData(KLayoutDataPackage.eINSTANCE.getKLayoutData());
            if (!forceShow && !data.getProperty(KlighdProperties.SHOW)) {
                contextData.setProperty(KlighdInternalProperties.ACTIVE, false);
                return;
            }

            nodeNode = new KNodeNode(node, edgesFirst);
            contextData.setProperty(REP, nodeNode);

            updateRendering(nodeNode);

            addExpansionListener(nodeNode);

            expand = data == null || data.getProperty(KlighdProperties.EXPAND) ?  1 : 0;
            // in case the EXPAND property is not set the default value 'true' is returned

        } else {
            // touch the expansion state, see the methods javadoc for details
            expand = 2;
        }

        if (record && isAutomaticallyArranged(node)) {
            // this avoids flickering and denotes the application of fade-in,
            //  see #handleRecordedChanges()
            nodeNode.setVisible(false);
        }

        // declare the node 'ACTIVE' just yet, it is required by 'handleEdges'
        contextData.setProperty(KlighdInternalProperties.ACTIVE, true);

        updateLayout(nodeNode);
        handleEdges(nodeNode);
        handlePorts(nodeNode);
        handleLabels(nodeNode, node);

        // add the node to its parents
        parent.getChildAreaNode().addNode(nodeNode);

        // perform expansion strictly AFTER adding 'nodeNode' to its parent as the edge offset adjustment
        //  logic requires that (for registration of an transform change listener on the parents row)
        switch (expand) {
        case 1:
            nodeNode.setExpanded(true);
            break;
        case 2:
            // touch the expansion state, see the methods javadoc for details
            nodeNode.touchExpanded();
            break;
        default:
            // e.g. case 0: don't expand
        }
    }


    /**
     * Handles the children of the parent node.
     *
     * @param parent
     *            the parent KNode
     */
    private void removeChildren(final KNode parent) {
        for (final KNode child : parent.getChildren()) {
            removeNode(child, false);
        }
        RenderingContextData.get(parent).setProperty(KlighdInternalProperties.POPULATED, false);

        if (sync) {
            // remove any existing children sync adapters, which may be out-of-date
            ModelingUtil.removeAdapters(parent, CHILDREN_SYNC_ADAPTERS);
        }
    }

    /**
     * Removes the representation for <code>node</code> from its parent.<br>
     * <code>node</code> is not marked collapsed, this way the memory of the expand-collapse-state
     * of nested nodes is preserved.
     *
     * @param node
     *            the node
     * @param releaseControllers
     *            flag indicating whether controller instances shall be removed from lookup tables
     *            because the element actually has been removed from the view model rather than just
     *            hidden
     */
    private void removeNode(final KNode node, final boolean releaseControllers) {
        final RenderingContextData contextData = RenderingContextData.get(node);
        final KNodeAbstractNode nodeRep = contextData.getProperty(REP);

        if (nodeRep == null) {
            return;
        }

        final KNodeNode nodeNode;
        if (nodeRep instanceof KNodeTopNode) {
            // if the node is the current top-node something went wrong
            throw new RuntimeException("The top-node can never be removed from a parent node");
        } else {
            nodeNode = (KNodeNode) nodeRep;
        }

        if (nodeNode.getParent() == null) {
            // nodeNode is not contained in the PNode tree since, e.g., it has been removed already
            return;
        }

        // remove all incoming edges
        for (final KEdge incomingEdge : node.getIncomingEdges()) {
            removeEdge(incomingEdge, releaseControllers);
        }

        // remove all outgoing edges
        for (final KEdge outgoingEdge : node.getOutgoingEdges()) {
            removeEdge(outgoingEdge, releaseControllers);
        }

        // remove the node representation from the containing child area
        nodeNode.removeFromParent();
        contextData.setProperty(KlighdInternalProperties.ACTIVE, false);

        // recursively dispose all attached SWT Resources
        NodeDisposeListener.disposePNode(nodeNode);

        if (releaseControllers) {
            // detach the synchronization adapters
            ModelingUtil.removeAdapters(node, NODE_ADAPTERS);
            // release the objects kept in mind
            nodeNode.getRenderingController().removeAllPNodeControllers();
            // release the node rendering controller
            nodeNode.setRenderingController(null);
            // release the child (label)nodes
            nodeNode.removeAllChildren();
            // release the node representation from the node's renderingContextData
            contextData.setProperty(REP, null);
        }
    }

    private static final Predicate<Object> NODE_ADAPTERS = KlighdPredicates.instanceOf(
            ImmutableList.<Class<?>>of(KGEShapeLayoutPNodeUpdater.class, ChildrenSyncAdapter.class,
                    EdgeSyncAdapter.class, PortSyncAdapter.class, LabelSyncAdapter.class));

    /**
     * Adds representations for edges attached to the corresponding node.
     *
     * @param nodeNode
     *            the node representation
     */
    private void handleEdges(final KNodeNode nodeNode) {
        final KNode node = nodeNode.getViewModelElement();

        // add all incoming edges
        for (final KEdge incomingEdge : node.getIncomingEdges()) {
            addEdge(incomingEdge, false);
        }

        // add all outgoing edges
        for (final KEdge outgoingEdge : node.getOutgoingEdges()) {
            addEdge(outgoingEdge, false);
        }

        if (sync) {
            // remove any existing edge sync adapters, which may be out-of-date
            ModelingUtil.removeAdapters(node, EDGE_SYNC_ADAPTERS);

            // add the adapter
            node.eAdapters().add(new EdgeSyncAdapter());
        }
    }

    /**
     * Adds a representation for the edge to the appropriate child area.
     *
     * @param edge
     *            the edge
     * @param forceShow
     *            if <code>true</code> add <code>edge</code> to the diagram regardless of its
     *            {@link KlighdProperties#SHOW} property value
     */
    private void addEdge(final KEdge edge, final boolean forceShow) {
        final RenderingContextData contextData = RenderingContextData.get(edge);
        KEdgeNode edgeNode = contextData.getProperty(EDGE_REP);

        // only add a representation if none is added already
        if (edgeNode != null && edgeNode.getParent() != null) {
            return;
        }

        final KNode source = edge.getSource();
        final KNode target = edge.getTarget();

        final boolean sourceAndTargetActive = source != null && target != null
                && RenderingContextData.get(source).getProperty(KlighdInternalProperties.ACTIVE)
                && RenderingContextData.get(target).getProperty(KlighdInternalProperties.ACTIVE);

        if (!sourceAndTargetActive) {
            return;
        }

        // if there is no Piccolo2D representation for the node create it
        if (edgeNode == null) {
            if (!forceShow && !NON_HIDDEN_KGE_FILTER.apply(edge)) {
                contextData.setProperty(KlighdInternalProperties.ACTIVE, false);
                return;
            }

            edgeNode = new KEdgeNode(edge);
            contextData.setProperty(EDGE_REP, edgeNode);

            updateRendering(edgeNode);
        }

        // note that 'edgeNode' is not contained in any parent and, thus, fires no events
        if (record && isAutomaticallyArranged(edge)) {
            // this avoids flickering and denotes the application of fade-in,
            //  see #handleRecordedChanges()
            edgeNode.setVisible(false);
        }

        // it is still not contained ...
        updateLayout(edgeNode);
        handleLabels(edgeNode, edge);

        // the following is needed in case of interlevel edges:
        //  edges ending in an outer child area will be clipped by the inner childArea;
        //  the clipping is generally intended and is realized by KChildAreaNode

        // find and set the parent of the edge, i.e. add it into the figure tree
        DiagramControllerHelper.updateEdgeParent(edgeNode);
        contextData.setProperty(KlighdInternalProperties.ACTIVE, true);

        // update the offset of the edge layout to the containing child area
        DiagramControllerHelper.updateEdgeOffset(edgeNode);
    }

    /**
     * Removes the representation for the edge from its parent.
     *
     * @param edge
     *            the edge
     * @param releaseControllers
     *            flag indicating whether controller instances shall be removed from lookup tables
     *            because the element actually has been removed from the view model rather than just
     *            hidden
     */
    private void removeEdge(final KEdge edge, final boolean releaseControllers) {
        final RenderingContextData contextData = RenderingContextData.get(edge);
        final KEdgeNode edgeNode = contextData.getProperty(EDGE_REP);
        if (edgeNode == null) {
            return;
        }

        // remove the edge offset listeners
        DiagramControllerHelper.removeEdgeOffsetListener(edgeNode);

        // remove the edge representation from the containing child area
        edgeNode.removeFromParent();
        contextData.setProperty(KlighdInternalProperties.ACTIVE, false);

        // recursively dispose all attached SWT Resources
        NodeDisposeListener.disposePNode(edgeNode);

        // due to #removeNode() this method might be performed multiple times so:
        if (releaseControllers && edgeNode.getRenderingController() != null) {
            // detach the synchronization adapters
            ModelingUtil.removeAdapters(edge, EDGE_ADAPTERS);
            // release the objects kept in mind
            edgeNode.getRenderingController().removeAllPNodeControllers();
            // release the node rendering controller
            edgeNode.setRenderingController(null);
            // release the child (label)nodes
            edgeNode.removeAllChildren();
            // release the edge representation from the edge's renderingContextData
            contextData.setProperty(EDGE_REP, null);
        }
    }

    private static final Predicate<Object> EDGE_ADAPTERS = KlighdPredicates.instanceOf(
            ImmutableList.<Class<?>>of(KEdgeLayoutEdgeNodeUpdater.class, LabelSyncAdapter.class));


    /**
     * Adds representations for the ports attached to the node to the node's representation.
     *
     * @param nodeNode
     *            the node representation
     */
    private void handlePorts(final KNodeNode nodeNode) {
        final KNode node = nodeNode.getViewModelElement();

        // create the ports
        for (final KPort port : node.getPorts()) {
            addPort(nodeNode, port, false);
        }

        if (sync) {
            // remove any existing port sync adapters, which may be out-of-date
            ModelingUtil.removeAdapters(node, PORT_SYNC_ADAPTERS);

            // add the adapter
            node.eAdapters().add(new PortSyncAdapter(nodeNode));
        }
    }

    /**
     * Adds a representation for the port to the given parent.
     *
     * @param parent
     *            the parent node
     * @param port
     *            the port
     * @param forceShow
     *            if <code>true</code> add <code>port</code> to the diagram regardless of its
     *            {@link KlighdProperties#SHOW} property value
     */
    private void addPort(final KNodeNode parent, final KPort port, final boolean forceShow) {
        final RenderingContextData contextData = RenderingContextData.get(port);
        KPortNode portNode = contextData.getProperty(PORT_REP);

        if (portNode != null && portNode.getParent() != null) {
            return;
        }

        // if there is no Piccolo2D representation of the port create it
        if (portNode == null) {
            if (!forceShow && !NON_HIDDEN_KGE_FILTER.apply(port)) {
                contextData.setProperty(KlighdInternalProperties.ACTIVE, false);
                return;
            }

            portNode = new KPortNode(port);
            contextData.setProperty(PORT_REP, portNode);

            updateRendering(portNode);
        }

        if (record && isAutomaticallyArranged(port)) {
            // this avoids flickering and denotes the application of fade-in,
            //  see #handleRecordedChanges()
            portNode.setVisible(false);
        }

        updateLayout(portNode);
        handleLabels(portNode, port);

        // add the port
        parent.addPort(portNode);
        contextData.setProperty(KlighdInternalProperties.ACTIVE, true);
    }

    /**
     * Removes the representation for the port from its parent.
     *
     * @param port
     *            the port
     * @param releaseControllers
     *            flag indicating whether controller instances shall be removed from lookup tables
     *            because the element actually has been removed from the view model rather than just
     *            hidden
     */
    private void removePort(final KPort port, final boolean releaseControllers) {
        final RenderingContextData contextData = RenderingContextData.get(port);
        final KPortNode portNode = contextData.getProperty(PORT_REP);
        if (portNode == null) {
            return;
        }

        // remove the port representation from the containing node
        portNode.removeFromParent();
        contextData.setProperty(KlighdInternalProperties.ACTIVE, false);

        // recursively dispose all attached SWT Resources
        NodeDisposeListener.disposePNode(portNode);

        if (releaseControllers) {
            // detach the synchronization adapters
            ModelingUtil.removeAdapters(port, PORT_ADAPTERS);
            // release the objects kept in mind
            portNode.getRenderingController().removeAllPNodeControllers();
            // release the node rendering controller
            portNode.setRenderingController(null);
            // release the child (label)nodes
            portNode.removeAllChildren();
            // release the port representation from the port's renderingContextData
            contextData.setProperty(PORT_REP, null);
        }
    }

    private static final Predicate<Object> PORT_ADAPTERS = KlighdPredicates.instanceOf(
            ImmutableList.<Class<?>>of(KGEShapeLayoutPNodeUpdater.class, LabelSyncAdapter.class));


    /**
     * Adds representations for the labels attached to the labeled element to the labeled node.
     *
     * @param labeledNode
     *            the labeled node
     * @param labeledElement
     *            the labeled element
     */
    private void handleLabels(final IInternalKGraphElementNode.IKLabeledGraphElementNode<?> labeledNode,
            final KLabeledGraphElement labeledElement) {
        for (final KLabel label : labeledElement.getLabels()) {
            addLabel(labeledNode, label, false);
        }

        if (sync) {
            // remove any existing label sync adapters, which may be out-of-date
            ModelingUtil.removeAdapters(labeledElement, LABEL_SYNC_ADAPTERS);

            // add an adapter on the labeled element's labels
            labeledElement.eAdapters().add(new LabelSyncAdapter(labeledNode));
        }
    }

    /**
     * Adds a representation for the label to the given labeled node.
     *
     * @param labeledNode
     *            the labeled node
     * @param label
     *            the label
     * @param forceShow
     *            if <code>true</code> add <code>label</code> to the diagram regardless of its
     *            {@link KlighdProperties#SHOW} property value
     */
    private void addLabel(
            final IInternalKGraphElementNode.IKLabeledGraphElementNode<?> labeledNode,
            final KLabel label, final boolean forceShow) {
        final RenderingContextData contextData = RenderingContextData.get(label);
        KLabelNode labelNode = contextData.getProperty(LABEL_REP);

        if (labelNode != null && labelNode.getParent() != null) {
            return;
        }

        // if there is no Piccolo2d representation of the label create it
        if (labelNode == null) {
            if (!forceShow && !NON_HIDDEN_KGE_FILTER.apply(label)) {
                contextData.setProperty(KlighdInternalProperties.ACTIVE, false);
                return;
            }

            labelNode = new KLabelNode(label);
            contextData.setProperty(LABEL_REP, labelNode);

            updateRendering(labelNode);
        }

        if (record && isAutomaticallyArranged(label)) {
            // this avoids flickering and denotes the application of fade-in,
            //  see #handleRecordedChanges()
            labelNode.setVisible(false);
        }

        updateLayout(labelNode);

        labelNode.setText(label.getText());

        if (sync) {
            // remove any existing text sync adapters, which may be out-of-date
            ModelingUtil.removeAdapters(label, TEXT_SYNC_ADAPTERS);

            // add an adapter on the node's ports
            label.eAdapters().add(new TextSyncAdapter(labelNode));
        }

        // add the label
        labeledNode.addLabel(labelNode);
        contextData.setProperty(KlighdInternalProperties.ACTIVE, true);
    }

    /**
     * Removes the representation for the label from its parent.
     *
     * @param label
     *            the label
     * @param releaseControllers
     *            flag indicating whether controller instances shall be removed from lookup tables
     *            because the element actually has been removed from the view model rather than just
     *            hidden
     */
    private void removeLabel(final KLabel label, final boolean releaseControllers) {
        final RenderingContextData contextData = RenderingContextData.get(label);
        final KLabelNode labelNode = contextData.getProperty(LABEL_REP);
        if (labelNode == null) {
            return;
        }

        // remove the label representation from the containing node
        labelNode.removeFromParent();
        contextData.setProperty(KlighdInternalProperties.ACTIVE, false);

        // recursively dispose all attached SWT Resources
        NodeDisposeListener.disposePNode(labelNode);

        if (releaseControllers) {
            // detach the synchronization adapters
            ModelingUtil.removeAdapters(label, LABEL_ADAPTERS);
            // release the objects kept in mind
            labelNode.getRenderingController().removeAllPNodeControllers();
            // release the node rendering controller
            labelNode.setRenderingController(null);
            // release the label representation from the label's renderingContextData
            contextData.setProperty(LABEL_REP, null);
        }
    }

    private static final Predicate<Object> LABEL_ADAPTERS = KlighdPredicates.instanceOf(
            ImmutableList.<Class<?>>of(KGEShapeLayoutPNodeUpdater.class, TextSyncAdapter.class));


    private boolean isAutomaticallyArranged(final KGraphElement element) {
        KLayoutData layoutData = this.topNode.getViewModelElement().getData(KLayoutData.class);
        if (layoutData == null || layoutData.getProperty(LayoutOptions.NO_LAYOUT)) {
            return false;
        }
        layoutData = element.getData(KLayoutData.class);
        if (layoutData != null && layoutData.getProperty(LayoutOptions.NO_LAYOUT)) {
            return false;
        }
        final KNode container = ModelingUtil.eContainerOfType(element, KNode.class);
        layoutData = container == null ? null : container.getData(KLayoutData.class);
        if (layoutData != null && layoutData.getProperty(LayoutOptions.NO_LAYOUT)) {
            return false;
        }
        return true;
    }


    /**
     * Installs a change listener being in charge of updating <code>nodeNode</code>'s coordinates
     * according to changes in the corresponding {@link KShapeLayout} if synchronization is enabled.<br>
     * <br>
     * Besides, updates the bounds and translation of the node representation according to the
     * corresponding {@link KShapeLayout}.
     *
     * @param nodeNode
     *            the node representation
     */
    private void updateLayout(final KNodeNode nodeNode) {
        if (sync) {
            final KNode node = nodeNode.getViewModelElement();

            // remove the currently installed adapter if any; existing adapters get outdated if
            //  a) 'node' has been moved within the view model
            //  b) 'node' has been moved from one view model into another one
            ModelingUtil.removeAdapters(node, SHAPE_LAYOUT_SYNC_ADAPTERS);

            // register adapter on the node to stay in sync
            node.eAdapters().add(new KGEShapeLayoutPNodeUpdater(nodeNode, this));
        }

        final KShapeLayout shapeLayout = nodeNode.getViewModelElement().getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applyBounds(nodeNode, shapeLayout);
        }
    }

    /**
     * Installs a change listener being in charge of updating <code>portNode</code>'s coordinates
     * according to changes in the corresponding {@link KShapeLayout} if synchronization is enabled.<br>
     * <br>
     * Besides, updates the bounds and translation of the port representation according to the
     * corresponding {@link KShapeLayout}.
     *
     * @param portNode
     *            the port representation
     */
    private void updateLayout(final KPortNode portNode) {

        if (sync) {
            final KPort port = portNode.getViewModelElement();

            // remove the currently installed adapter if any; existing adapters get outdated if
            //  a) 'port' has been moved within the view model
            //  b) 'port' has been moved from one view model into another one
            ModelingUtil.removeAdapters(port, SHAPE_LAYOUT_SYNC_ADAPTERS);

            // register adapter on the port to stay in sync
            port.eAdapters().add(new KGEShapeLayoutPNodeUpdater(portNode, this));
        }

        final KShapeLayout shapeLayout = portNode.getViewModelElement().getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applyBounds(portNode, shapeLayout);
        }
    }

    /**
     * Installs a change listener being in charge of updating <code>labelNode</code>'s coordinates
     * according to changes in the corresponding {@link KShapeLayout} if synchronization is enabled.<br>
     * <br>
     * Besides, updates the bounds and translation of the label representation according to the
     * corresponding {@link KShapeLayout}.
     *
     * @param labelNode
     *            the label representation
     */
    private void updateLayout(final KLabelNode labelNode) {

        if (sync) {
            final KLabel label = labelNode.getViewModelElement();

            // remove the currently installed adapter if any; existing adapters get outdated if
            //  a) 'label' has been moved within the view model
            //  b) 'label' has been moved from one view model into another one
            ModelingUtil.removeAdapters(label, SHAPE_LAYOUT_SYNC_ADAPTERS);

            // register adapter on the label to stay in sync
            label.eAdapters().add(new KGEShapeLayoutPNodeUpdater(labelNode, this));
        }

        final KShapeLayout shapeLayout = labelNode.getViewModelElement().getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applyBounds(labelNode, shapeLayout);
        }
    }

    /**
     * Installs a change listener being in charge of updating <code>edgeRep</code>'s coordinates
     * according to changes in the corresponding {@link KEdgeLayout} if synchronization is enabled.<br>
     * <br>
     * Besides, updates the bounds and translation of the edge representation according to the
     * corresponding {@link KEdgeLayout}.
     *
     * @param edgeRep
     *            the edge representation
     */
    private void updateLayout(final KEdgeNode edgeRep) {

        if (sync) {
            final KEdge edge = edgeRep.getViewModelElement();

            // remove the currently installed adapter if any; existing adapters get outdated if
            //  a) 'edge' has been moved within the view model
            //  b) 'edge' has been moved from one view model into another one
            ModelingUtil.removeAdapters(edge, EDGE_LAYOUT_SYNC_ADAPTERS);

            // register adapter on the edge to stay in sync
            edge.eAdapters().add(new KEdgeLayoutEdgeNodeUpdater(edgeRep, this));
        }

        final KEdge edge = edgeRep.getViewModelElement();
        final KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        if (edgeLayout != null) {
            final KRendering rendering = KRenderingUtil.dereference(edge.getData(KRendering.class));
            final boolean renderedAsPolyline = rendering instanceof KPolyline
                    && !(rendering instanceof KSpline);

            edgeRep.setBendPoints(KEdgeLayoutEdgeNodeUpdater.getBendPoints(edgeLayout,
                    renderedAsPolyline));
            edgeRep.setJunctionPoints(KEdgeLayoutEdgeNodeUpdater.getJunctionPoints(edgeLayout));
        }
    }

    /**
     * Updates the rendering of the node.
     *
     * @param nodeRep
     *            the node representation
     */
    private void updateRendering(final KNodeNode nodeRep) {
        KNodeRenderingController renderingController = nodeRep.getRenderingController();
        if (renderingController == null) {
            // the new rendering controller is attached to nodeRep in the constructor of
            //  AbstractRenderingController
            renderingController = new KNodeRenderingController(nodeRep);
            // nodeRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(this, sync);
        } else {
            renderingController.internalUpdateRendering();
        }
    }

    /**
     * Updates the rendering of the port.
     *
     * @param portRep
     *            the port representation
     */
    private void updateRendering(final KPortNode portRep) {
        KPortRenderingController renderingController = portRep.getRenderingController();
        if (renderingController == null) {
            // the new rendering controller is attached to nodeRep in the constructor of
            //  AbstractRenderingController
            renderingController = new KPortRenderingController(portRep);
            // portRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(this, sync);
        } else {
            renderingController.internalUpdateRendering();
        }
    }

    /**
     * Updates the rendering of the label.
     *
     * @param label
     *            the label representation
     */
    private void updateRendering(final KLabelNode labelRep) {
        KLabelRenderingController renderingController = labelRep.getRenderingController();
        if (renderingController == null) {
            // the new rendering controller is attached to nodeRep in the constructor of
            //  AbstractRenderingController
            renderingController = new KLabelRenderingController(labelRep);
            // labelRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(this, sync);
        } else {
            renderingController.internalUpdateRendering();
        }
    }

    /**
     * Updates the rendering of the edge.
     *
     * @param edgeRep
     *            the edge representation
     */
    private void updateRendering(final KEdgeNode edgeRep) {
        KEdgeRenderingController renderingController = edgeRep.getRenderingController();
        if (renderingController == null) {
            // the new rendering controller is attached to nodeRep in the constructor of
            //  AbstractRenderingController
            renderingController = new KEdgeRenderingController(edgeRep);
            // edgeRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(this, sync);
        } else {
            renderingController.internalUpdateRendering();
        }
    }


    // ---------------------------------------------------------------------------------- //
    //  Layout data synchronization

    private static final Predicate<Object> SHAPE_LAYOUT_SYNC_ADAPTERS
            = Predicates.instanceOf(KGEShapeLayoutPNodeUpdater.class);

    private static final Predicate<Object> EDGE_LAYOUT_SYNC_ADAPTERS
            = Predicates.instanceOf(KEdgeLayoutEdgeNodeUpdater.class);

    // implementations of layout sync adapters are externalized into dedicated classes

    // ---------------------------------------------------------------------------------- //
    //  KGraphElement data synchronization

    private static final Predicate<Object> CHILDREN_SYNC_ADAPTERS
            = Predicates.instanceOf(ChildrenSyncAdapter.class);

    private static final String UI_REQUIRED_ERROR_MSG =
            "KLighD: Adding & Removing XX is not allowed by any non-UI thead. "
            + "Put your executions into an instance of 'Runnable' and call "
            + "'PlatformUI.getWorkbench().getDisplay().(a)syncExec(Runnable)'!";

    private static final String UI_REQUIRED_ERROR_MSG_NODES =
            UI_REQUIRED_ERROR_MSG.replaceFirst("XX", "KNodes");

    /**
     * A dedicated specialization of {@link AdapterImpl} allowing 'instanceof' tests.
     * (in contrast to an anonymous subclass)
     */
    private final class ChildrenSyncAdapter extends AdapterImpl {
        private final KNodeAbstractNode nodeRep;

        private ChildrenSyncAdapter(final KNodeAbstractNode theNodeRep) {
            this.nodeRep = theNodeRep;
        }

        @Override
        public void notifyChanged(final Notification notification) {

            if (notification.getFeatureID(KNode.class) == KGraphPackage.KNODE__CHILDREN) {
                if (UIExecRequired()) {
                    throw new RuntimeException(UI_REQUIRED_ERROR_MSG_NODES);
                }

                switch (notification.getEventType()) {
                case Notification.ADD: {
                    final KNode addedNode = (KNode) notification.getNewValue();
                    addNode(nodeRep, addedNode, false);
                    break;
                }
                case Notification.ADD_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KNode> addedNodes = (List<KNode>) notification.getNewValue();

                    for (final KNode addedNode : addedNodes) {
                        addNode(nodeRep, addedNode, false);
                    }
                    break;
                }
                case Notification.REMOVE: {
                    final KNode removedNode = (KNode) notification.getOldValue();
                    removeNode(removedNode, true);

                    // Removing all contained nodes is required to remove all outgoing or
                    //  incoming edges, as in case of interlevel ones their representing
                    //  KEdgeNodes are attached to one of n's parent representatives, which might
                    //  be one of removedNode's parent representatives.
                    for (final KNode n : Iterables2.toIterable(Iterators.filter(
                            removedNode.eAllContents(), KNode.class))) {
                        removeNode(n, true);
                    }
                    break;
                }
                case Notification.REMOVE_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KNode> removedNodes = (List<KNode>) notification.getOldValue();

                    for (final KNode removedNode : removedNodes) {
                        removeNode(removedNode, true);

                        // Removing all contained nodes is required to remove all outgoing or
                        //  incoming edges, as in case of interlevel ones their representing
                        //  KEdgeNodes are attached to one of n's parent representatives, which might
                        //  be one of removedNode's parent representatives.
                        for (final KNode n : Iterables2.toIterable(Iterators.filter(
                                removedNode.eAllContents(), KNode.class))) {
                            removeNode(n, true);
                        }
                    }
                    break;
                }
                default:
                    break;
                }
            }
        }
    }


    private static final Predicate<Object> EDGE_SYNC_ADAPTERS
            = Predicates.instanceOf(EdgeSyncAdapter.class);

    private static final String UI_REQUIRED_ERROR_MSG_EDGES =
            UI_REQUIRED_ERROR_MSG.replaceFirst("XX", "KEdges");

    /**
     * A dedicated specialization of {@link AdapterImpl} allowing 'instanceof' tests.
     * (in contrast to an anonymous subclass)
     */
    private final class EdgeSyncAdapter extends AdapterImpl {

        @Override
        public void notifyChanged(final Notification notification) {
            final int featureId = notification.getFeatureID(KNode.class);

            if (featureId == KGraphPackage.KNODE__OUTGOING_EDGES
                    || featureId == KGraphPackage.KNODE__INCOMING_EDGES) {
                if (UIExecRequired()) {
                    throw new RuntimeException(UI_REQUIRED_ERROR_MSG_EDGES);
                }

                final boolean releaseChildrenAndControllers =
                        featureId == KGraphPackage.KNODE__OUTGOING_EDGES;

                switch (notification.getEventType()) {
                case Notification.ADD: {
                    final KEdge addedEdge = (KEdge) notification.getNewValue();
                    addEdge(addedEdge, false);
                    break;
                }
                case Notification.ADD_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KEdge> addedEdges = (List<KEdge>) notification.getNewValue();

                    for (final KEdge addedEdge : addedEdges) {
                        addEdge(addedEdge, false);
                    }
                    break;
                }
                case Notification.REMOVE: {
                    final KEdge removedEdge = (KEdge) notification.getOldValue();
                    removeEdge(removedEdge, releaseChildrenAndControllers);
                    break;
                }
                case Notification.REMOVE_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KEdge> removedEdges = (List<KEdge>) notification.getOldValue();

                    for (final KEdge removedEdge : removedEdges) {
                        removeEdge(removedEdge, releaseChildrenAndControllers);
                    }
                    break;
                }
                default:
                    break;
                }
            }
        }
    }


    private static final Predicate<Object> PORT_SYNC_ADAPTERS
            = Predicates.instanceOf(PortSyncAdapter.class);

    private static final String UI_REQUIRED_ERROR_MSG_PORTS =
            UI_REQUIRED_ERROR_MSG.replaceFirst("XX", "KPorts");

    /**
     * A dedicated specialization of {@link AdapterImpl} allowing 'instanceof' tests.
     * (in contrast to an anonymous subclass)
     */
    private final class PortSyncAdapter extends AdapterImpl {
        private final KNodeNode nodeRep;

        private PortSyncAdapter(final KNodeNode theNodeRep) {
            this.nodeRep = theNodeRep;
        }

        @Override
        public void notifyChanged(final Notification notification) {
            if (notification.getFeatureID(KNode.class) == KGraphPackage.KNODE__PORTS) {
                if (UIExecRequired()) {
                    throw new RuntimeException(UI_REQUIRED_ERROR_MSG_PORTS);
                }

                switch (notification.getEventType()) {
                case Notification.ADD: {
                    final KPort addedPort = (KPort) notification.getNewValue();
                    addPort(nodeRep, addedPort, false);
                    break;
                }
                case Notification.ADD_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KPort> addedPorts = (List<KPort>) notification.getNewValue();

                    for (final KPort addedPort : addedPorts) {
                        addPort(nodeRep, addedPort, false);
                    }
                    break;
                }
                case Notification.REMOVE: {
                    final KPort removedPort = (KPort) notification.getOldValue();
                    removePort(removedPort, true);
                    break;
                }
                case Notification.REMOVE_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KPort> removedPorts = (List<KPort>) notification.getOldValue();

                    for (final KPort removedPort : removedPorts) {
                        removePort(removedPort, true);
                    }
                    break;
                }
                default:
                    break;
                }
            }
        }
    }


    private static final Predicate<Object> LABEL_SYNC_ADAPTERS
            = Predicates.instanceOf(LabelSyncAdapter.class);

    private static final String UI_REQUIRED_ERROR_MSG_LABELS =
            UI_REQUIRED_ERROR_MSG.replaceFirst("XX", "KLabels");

    /**
     * A dedicated specialization of {@link AdapterImpl} allowing 'instanceof' tests.
     * (in contrast to an anonymous subclass)
     */
    private final class LabelSyncAdapter extends AdapterImpl {
        private final IInternalKGraphElementNode.IKLabeledGraphElementNode<?> labeledNode;

        private LabelSyncAdapter(
                final IInternalKGraphElementNode.IKLabeledGraphElementNode<?> theLabeledNode) {
            this.labeledNode = theLabeledNode;
        }

        @Override
        public void notifyChanged(final Notification notification) {

            if (notification.getFeatureID(KLabeledGraphElement.class)
                    == KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS) {
                if (UIExecRequired()) {
                    throw new RuntimeException(UI_REQUIRED_ERROR_MSG_LABELS);
                }

                switch (notification.getEventType()) {
                case Notification.ADD: {
                    final KLabel addedLabel = (KLabel) notification.getNewValue();
                    addLabel(labeledNode, addedLabel, false);
                    break;
                }
                case Notification.ADD_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KLabel> addedLabels = (List<KLabel>) notification.getNewValue();

                    for (final KLabel addedLabel : addedLabels) {
                        addLabel(labeledNode, addedLabel, false);
                    }
                    break;
                }
                case Notification.REMOVE: {
                    final KLabel removedLabel = (KLabel) notification.getOldValue();
                    removeLabel(removedLabel, true);
                    break;
                }
                case Notification.REMOVE_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KLabel> removedLabels = (List<KLabel>) notification
                            .getOldValue();

                    for (final KLabel removedLabel : removedLabels) {
                        removeLabel(removedLabel, true);
                    }
                    break;
                }
                default:
                    break;
                }
            }
        }
    }


    private static final Predicate<Object> TEXT_SYNC_ADAPTERS
            = Predicates.instanceOf(TextSyncAdapter.class);

    /**
     * A dedicated specialization of {@link AdapterImpl} allowing 'instanceof' tests.
     * (in contrast to an anonymous subclass)
     */
    private final class TextSyncAdapter extends AdapterImpl {
        private final KLabelNode labelRep;

        private TextSyncAdapter(final KLabelNode theLabelRep) {
            this.labelRep = theLabelRep;
        }

        @Override
        public void notifyChanged(final Notification notification) {
            if (notification.getFeatureID(KLabel.class) == KGraphPackage.KLABEL__TEXT) {

                switch (notification.getEventType()) {
                case Notification.SET:
                    if (UIExecRequired()) {
                        // Since changing the label text is no structural modification
                        //  we support the automatic switching the Display thread here!
                        // (several potentially concurrent modifications of the
                        //  diagram's structure might lead to chaos...)
                        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                            public void run() {
                                labelRep.setText(notification.getNewStringValue());
                            }
                        });
                    } else {
                        labelRep.setText(notification.getNewStringValue());
                    }
                    break;
                default:
                    break;
                }
            }
        }
    }


    // ---------------------------------------------------------------------------------- //
    //  Helper methods

    /**
     * Checks whether the delegation to the Display thread is required for performing UI-relevant
     * executions.
     *
     * @return <code>true</code> if an Eclipse workbench is available and the current thread is not
     *         the UI thread ( {@link Display#getCurrent()} <code>== null</code>) indicating that
     *         calling {@link PlatformUI#getWorkbench()}.{@link org.eclipse.ui.IWorkbench#getDisplay()
     *         getDisplay()}.{@link Display#asyncExec(Runnable)
     *         asyncExec(Runnable)}/{@link Display#syncExec(Runnable) syncExec(Runnable)} is required.
     */
    public static boolean UIExecRequired() { // SUPPRESS CHECKSTYLE MethodName
        return PlatformUI.isWorkbenchRunning() && Display.getCurrent() == null;
    }
}
