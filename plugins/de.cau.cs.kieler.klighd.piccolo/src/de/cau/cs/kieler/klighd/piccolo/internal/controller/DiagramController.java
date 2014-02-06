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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
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
import de.cau.cs.kieler.core.kgraph.util.KGraphSwitch;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.ApplyBendPointsActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.ApplySmartBoundsActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.FadeEdgeInActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.FadeNodeInActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.IStartingAndFinishingActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IGraphElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.ILabeledGraphElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.INode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KChildAreaNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KPortNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.util.Iterables2;
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
    private static final IProperty<INode> REP = new Property<INode>(
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

    /** the attribute key for the edge offset listeners. */
    private static final Object EDGE_OFFSET_LISTENER_KEY = new Object();

    /** the attribute key for the nodes listed by edge offset listeners. */
    private static final Object EDGE_OFFSET_LISTENED_KEY = new Object();

    /** the Piccolo2D node representing the top node in the graph. */
    private final KNodeTopNode topNode;
    
    /** the main camera that determines the actually drawn picture. */
    private final KlighdMainCamera canvasCamera;

    /** the zoom controller implementing the zoom functionalities. */
    private final DiagramZoomController zoomController;
    
    /** whether to sync the representation with the graph model. */
    private boolean sync = false;

    /** whether to record layout changes, will be set to true by the KlighdLayoutManager. */
    private boolean record = false;

    /** type of zoom style applied after layout. */
    private ZoomStyle zoomStyle = ZoomStyle.NONE;
    
    /** duration of a possible animation. */
    private int animationTime = 0;
    
    /** the layout changes to graph elements while recording. */
    private Map<PNode, Object> recordedChanges = Maps.newLinkedHashMap();


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
     */
    public DiagramController(final KNode graph, final KlighdMainCamera camera, final boolean sync) {
        resetGraphElement(graph);

        this.sync = sync;
        this.canvasCamera = camera;

        this.topNode = new KNodeTopNode(graph);
        RenderingContextData.get(graph).setProperty(REP, topNode);

        this.zoomController = new DiagramZoomController(topNode, canvasCamera);

        canvasCamera.getRoot().addChild(topNode);
        canvasCamera.setDisplayedNode(topNode);

        addExpansionListener(topNode);

        RenderingContextData.get(topNode.getGraphElement()).setProperty(
                KlighdInternalProperties.ACTIVE, true);

        topNode.getChildAreaNode().setExpanded(true);
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
     * Executing {@link #stopRecording(ZoomStyle, int)} applies all recorded layout changes.
     * 
     * @see de.cau.cs.kieler.klighd.internal.ILayoutRecorder#startRecording()
     *      ILayoutRecorder#startRecording()
     */
    public void startRecording() {
        record = true;
    }

    /**
     * @param theZoomStyle
     *            the style used to zoom, e.g. zoom to fit or zoom to focus
     * @param theAnimationTime
     *            duration of the animated layout
     * 
     * @see de.cau.cs.kieler.klighd.internal.ILayoutRecorder#stopRecording(ZoomStyle, int)
     *      ILayoutRecorder#stopRecording(ZoomStyle, int)
     */
    public void stopRecording(final ZoomStyle theZoomStyle, final int theAnimationTime) {
        if (record) {
            zoomStyle = theZoomStyle;
            animationTime = theAnimationTime;

            record = false;

            handleRecordedChanges();
        }
    }

    /**
     * Collapses the representation of the given node.
     * 
     * @param node
     *            the node
     */
    public void collapse(final KNode node) {
        INode nodeRep = RenderingContextData.get(node).getProperty(REP);
        if (nodeRep != null) {
            nodeRep.getChildAreaNode().setExpanded(false);
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
        INode nodeRep = RenderingContextData.get(node).getProperty(REP);
        if (nodeRep != null) {
            nodeRep.getChildAreaNode().setExpanded(true);
        }
        
        zoomController.setFocusNode(node);
    }
    
    /**
     * @param node
     *            the node
     * @return true if this node is expanded.
     */
    public boolean isExpanded(final KNode node) {
        INode nodeRep = RenderingContextData.get(node).getProperty(REP);
        if (nodeRep != null) {
            return nodeRep.getChildAreaNode().isExpanded();
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
        INode nodeRep = RenderingContextData.get(node).getProperty(REP);
        if (nodeRep != null) {
            nodeRep.getChildAreaNode().toggleExpansion();
        }
        
        zoomController.setFocusNode(node);
    }

    /**
     * Hides the given {@link KGraphElement} from the diagram by removing the related
     * {@link IGraphElement} from the network of {@link PNode PNodes}. In combination with
     * {@link #show(KGraphElement)} this method can be used for changing the diagram's amount of
     * detail without changing the view model.
     * 
     * @param diagramElement
     *            the {@link KGraphElement} to hide from the diagram
     */
    public void hide(final KGraphElement diagramElement) {
        KGraphElement parent = (KGraphElement) diagramElement.eContainer();
        if (parent == null) {
            return;
        }

        remove(diagramElement, false);
    }
    
    /**
     * Shows the given {@link KGraphElement} from the diagram by (re-) adding a related
     * {@link IGraphElement} to the network of {@link PNode PNodes}. In combination with
     * {@link #hide(KGraphElement)} this method can be used for changing the diagram's amount of
     * detail without changing the view model.
     * 
     * @param diagramElement
     *            the {@link KGraphElement} to (re-) show in the diagram
     */
    public void show(final KGraphElement diagramElement) {
        KGraphElement parent = (KGraphElement) diagramElement.eContainer();
        if (parent == null) {
            return;
        }

        add(diagramElement);
    }
    
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
        final IGraphElement<KNode> node =
                (diagramElement == null) ? topNode : getRepresentation(diagramElement);
        
        final INode currentRootNode = canvasCamera.getDisplayedINode();
        if (currentRootNode != node) {
            canvasCamera.exchangeDisplayedNode((INode) node);
            zoomController.setFocusNode(diagramElement);
        }
    }
    
    /**
     * Provides the currently set diagram clip.
     * 
     * @return the {@link KNode} that is currently clipped.
     */
    public KNode getClip() {
        final INode node = canvasCamera.getDisplayedINode();
        return node.getGraphElement();
    }

    /**
     * Performs a zooming depending on the specified style.
     * 
     * @param style
     *            the desired style
     * @param duration
     *            time to animate
     */
    public void zoom(final ZoomStyle style, final int duration) {
        zoomController.zoom(style, duration);
    }

    /**
     * Sets the zoom level to {@code newZoomLevel}. A value below 1 results in smaller elements than
     * in the original diagram, a value greater than 1 in a bigger elements than in the original.
     * 
     * The method tries retain the center point, i.e., to center over the currently centered point,
     * however, it is assured that at least some parts of the underlying diagram are visible.
     * 
     * @param newZoomLevel
     *            the new zoom level
     * @param duration
     *            time to animate
     */
    public void zoomToLevel(final float newZoomLevel, final int duration) {
        zoomController.zoomToLevel(newZoomLevel, duration);
    }

    /**
     * Returns the Piccolo2D representation for the given diagram element.
     * 
     * @param <T> the concrete type of the diagramElement
     * @param diagramElement
     *            the diagram element
     * @return the Piccolo2D representation
     */
    public <T extends KGraphElement> IGraphElement<T> getRepresentation(final T diagramElement) {
        @SuppressWarnings("unchecked")
        final IGraphElement<T> result =
                (IGraphElement<T>) RenderingContextData.get(diagramElement).getProperty(REP);
        return result;
    }

    /* --------------------------------------------- */
    /* internal part */
    /* --------------------------------------------- */

    void recordChange(final PNode node, final Object change) {
        recordedChanges.put(node, change);
    }
    
    private Set<AbstractKGERenderingController<?, ?>> dirtyDiagramElements = Sets.newHashSet();

    void scheduleRenderingUpdate(final AbstractKGERenderingController<?, ?> controller) {
        renderingUpdater.cancel();
        synchronized (dirtyDiagramElements) {
            dirtyDiagramElements.add(controller);
        }
        renderingUpdater.schedule(1);
    }
    
    private final Job renderingUpdater = new Job("KLighD DiagramElementUpdater") {

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
                for (AbstractKGERenderingController<?, ?> ctrl : copy) {
                    ctrl.updateRenderingInUi();
                }
            }
        };
    };
    

    /**
     * Applies the recorded layout changes by creating appropriate activities.
     */
    private void handleRecordedChanges() {

        // create activities to apply all recorded changes
        for (Map.Entry<PNode, Object> recordedChange : recordedChanges.entrySet()) {
            // create the activity to apply the change
            PInterpolatingActivity activity;
            final PNode shapeNode;
            if (recordedChange.getKey() instanceof KEdgeNode) {
                // edge layout changed
                
                final KEdgeNode edgeNode = (KEdgeNode) recordedChange.getKey();
                shapeNode = edgeNode;
                
                @SuppressWarnings("unchecked")
                final Pair<Point2D[], Point2D[]> value =
                        (Pair<Point2D[], Point2D[]>) recordedChange.getValue();
                final Point2D[] bends = (Point2D[]) value.getFirst();
                final Point2D[] junctions = (Point2D[]) value.getSecond(); 

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
                PBounds bounds = (PBounds) recordedChange.getValue();

                final float scale;
                if (shapeNode instanceof KNodeNode) {
                    scale = ((KNodeNode) shapeNode).getGraphElement().getData(KShapeLayout.class)
                                    .getProperty(LayoutOptions.SCALE_FACTOR);
                } else {
                    scale = 1f;
                }

                if (!shapeNode.getVisible()) {
                    // the visibility is set to false for newly introduced edges in #addNode,
                    //  #addPort, and #addLabel for avoiding unnecessary flickering and indicating
                    //  to fade it in
                    activity = new FadeNodeInActivity(shapeNode, bounds,
                            scale, animationTime > 0 ? animationTime : 1);
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
        zoom(zoomStyle, animationTime);
    }

    /**
     * Adds a listener on the expansion of the child area of the given node representation.
     * 
     * @param nodeNode
     *            the node representation
     */
    private void addExpansionListener(final INode nodeNode) {
        KChildAreaNode childAreaNode = nodeNode.getChildAreaNode();
        if (childAreaNode != null) {
            final KNode node = nodeNode.getGraphElement();

            childAreaNode.addPropertyChangeListener(KChildAreaNode.PROPERTY_EXPANSION,
                    new PropertyChangeListener() {
                        public void propertyChange(final PropertyChangeEvent event) {
                            if ((Boolean) event.getNewValue()) {
                                // i.e. the child area of node 'nodeNode' has been expanded ...
                                addChildren(nodeNode);
                            } else {
                                // i.e. the child area of node 'nodeNode' has been collapsed ...
                                removeChildren(node);
                            }

                            // in case distinct 'expanded' and/or 'collapsed' KRendering definitions
                            //  are given the rendering needs to be updated/exchanged after changing the
                            //  expansion state, so ...
                            if (Iterables.any(Iterables.filter(node.getData(), KRendering.class),
                                    Predicates.or(
                                            AbstractKGERenderingController.IS_COLLAPSED_RENDERING,
                                            AbstractKGERenderingController.IS_EXPANDED_RENDERING))) {
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
     */
    private void add(final KGraphElement element) {
        if (element.eContainer() == null) {
            return;
        }
        
        final IGraphElement<?> parentRep = getRepresentation((KGraphElement) element.eContainer());
                                           
        switch (element.eClass().getClassifierID()) {
        case KGraphPackage.KNODE:
            if (parentRep != null) {
                addNode((KNodeNode) parentRep, (KNode) element);
            }
            break;
        case KGraphPackage.KPORT:
            if (parentRep != null) {
                addPort((KNodeNode) parentRep, (KPort) element);
            }
            break;
        case KGraphPackage.KLABEL:
            if (parentRep != null) {
                addLabel((ILabeledGraphElement<?>) parentRep, (KLabel) element);
            }
            break;
        case KGraphPackage.KEDGE:
            addEdge((KEdge) element);
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
     * Handles the children of the parent node.
     * 
     * @param parentNode
     *            the parent structure node representing a KNode
     */
    private void addChildren(final INode parentNode) {
        final KNode parent = parentNode.getGraphElement();

        // create the nodes
        for (KNode child : parent.getChildren()) {
            addNode(parentNode, child);
        }

        RenderingContextData.get(parent).setProperty(KlighdInternalProperties.POPULATED, true);

        if (sync) {
            installChildrenSyncAdapter(parentNode, parent);
        }
    }

    /**
     * Adds a representation for the node to the given parent.
     * 
     * @param parent
     *            the parent node
     * @param node
     *            the node
     * @return the created node representation
     */
    private KNodeNode addNode(final INode parent, final KNode node) {
        final INode nodeRep = RenderingContextData.get(node).getProperty(REP);

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
            return nodeNode;
        }
        
        // if there is no Piccolo2D representation of the node create it
        if (nodeNode == null) {
            nodeNode = new KNodeNode(node, parent);
            RenderingContextData.get(node).setProperty(REP, nodeNode);

            updateRendering(nodeNode);
            
            addExpansionListener(nodeNode);

            KGraphData data = node.getData(KLayoutDataPackage.eINSTANCE.getKLayoutData());
            boolean expand = data == null || data.getProperty(KlighdProperties.EXPAND);
            // in case the EXPAND property is not set the default value 'true' is returned
            nodeNode.getChildAreaNode().setExpanded(expand);
            
        } else {
            // touch the expansion state, see the methods javadoc for details
            nodeNode.getChildAreaNode().touchExpanded();
        }

        if (record && isAutomaticallyArranged(node)) {
            // this avoids flickering and denotes the application of fade-in,
            //  see #handleRecordedChanges()
            nodeNode.setVisible(false);
        }

        // declare the node 'ACTIVE' that is required by 'handleEdges' 
        RenderingContextData.get(node).setProperty(KlighdInternalProperties.ACTIVE, true);

        updateLayout(nodeNode);
        handleEdges(nodeNode);
        handlePorts(nodeNode);
        handleLabels(nodeNode, node);
        
        // add the node
        parent.getChildAreaNode().addNode(nodeNode);
        
        return nodeNode;
    }


    /**
     * Handles the children of the parent node.
     * 
     * @param parentNode
     *            the parent structure node representing a KNode
     */
    private void removeChildren(final KNode parentNode) {
        for (KNode child : parentNode.getChildren()) {
            removeNode(child, false);
        }
        RenderingContextData.get(parentNode).setProperty(KlighdInternalProperties.POPULATED, false);

        if (sync) {
            uninstallChildrenSyncAdapter(parentNode);
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
        final INode nodeRep = RenderingContextData.get(node).getProperty(REP);

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
            
        uninstallLayoutSyncAdapter(node);
        uninstallChildrenSyncAdapter(node);
        uninstallEdgeSyncAdapter(node);
        uninstallPortSyncAdapter(node);
        uninstallLabelSyncAdapter(node);

        // remove all incoming edges
        for (KEdge incomingEdge : node.getIncomingEdges()) {
            removeEdge(incomingEdge, releaseControllers);
        }

        // remove all outgoing edges
        for (KEdge outgoingEdge : node.getOutgoingEdges()) {
            removeEdge(outgoingEdge, releaseControllers);
        }

        // remove the node representation from the containing child area
        nodeNode.removeFromParent();
        RenderingContextData.get(node).setProperty(KlighdInternalProperties.ACTIVE, false);
        
        if (releaseControllers) {
            // release the objects kept in mind
            nodeNode.getRenderingController().removeAllPNodeControllers();
            // release the node rendering controller
            nodeNode.setRenderingController(null);
            // release the child (label)nodes
            nodeNode.removeAllChildren();
            // release the node representation from the node's renderingContextData
            RenderingContextData.get(node).setProperty(REP, null);
        }
    }


    /**
     * Adds representations for edges attached to the corresponding node.
     * 
     * @param nodeNode
     *            the node representation
     */
    private void handleEdges(final KNodeNode nodeNode) {
        KNode node = nodeNode.getGraphElement();

        // add all incoming edges
        for (KEdge incomingEdge : node.getIncomingEdges()) {
            addEdge(incomingEdge);
        }
        
        // add all outgoing edges
        for (KEdge outgoingEdge : node.getOutgoingEdges()) {
            addEdge(outgoingEdge);
        }
        
        if (sync) {
            installEdgeSyncAdapter(node);
        }
    }

    /**
     * Adds a representation for the edge to the appropriate child area.
     * 
     * @param edge
     *            the edge
     */
    private void addEdge(final KEdge edge) {
        KEdgeNode edgeNode = RenderingContextData.get(edge).getProperty(EDGE_REP);

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
            edgeNode = new KEdgeNode(edge);
            RenderingContextData.get(edge).setProperty(EDGE_REP, edgeNode);
            
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
        updateEdgeParent(edgeNode);
        RenderingContextData.get(edge).setProperty(KlighdInternalProperties.ACTIVE, true);
        
        // update the offset of the edge layout to the containing child area
        updateEdgeOffset(edgeNode);
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
        KEdgeNode edgeNode = RenderingContextData.get(edge).getProperty(EDGE_REP);
        if (edgeNode == null) {
            return;
        }

        // remove the edge offset listeners
        removeEdgeOffsetListener(edgeNode);

        uninstallLayoutSyncAdapter(edge);
        uninstallLabelSyncAdapter(edge);
        
        // remove the edge representation from the containing child area
        edgeNode.removeFromParent();
        RenderingContextData.get(edge).setProperty(KlighdInternalProperties.ACTIVE, false);
        
        // due to #removeNode() this method might be performed multiple times so: 
        if (releaseControllers && edgeNode.getRenderingController() != null) {
            // release the objects kept in mind
            edgeNode.getRenderingController().removeAllPNodeControllers();
            // release the node rendering controller
            edgeNode.setRenderingController(null);
            // release the child (label)nodes
            edgeNode.removeAllChildren();
            // release the edge representation from the edge's renderingContextData
            RenderingContextData.get(edge).setProperty(EDGE_REP, null);
        }
    }


    /**
     * Adds representations for the ports attached to the node to the node's representation.
     * 
     * @param nodeNode
     *            the node representation
     */
    private void handlePorts(final KNodeNode nodeNode) {
        KNode node = nodeNode.getGraphElement();

        // create the ports
        for (KPort port : node.getPorts()) {
            addPort(nodeNode, port);
        }

        if (sync) {
            installPortSyncAdapter(nodeNode, node);
        }
    }

    /**
     * Adds a representation for the port to the given parent.
     * 
     * @param parent
     *            the parent node
     * @param port
     *            the port
     */
    private void addPort(final KNodeNode parent, final KPort port) {
        KPortNode portNode = RenderingContextData.get(port).getProperty(PORT_REP);

        if (portNode != null && portNode.getParent() != null) {
            return;
        }
        
        // if there is no Piccolo2D representation of the port create it
        if (portNode == null) {
            portNode = new KPortNode(port);
            RenderingContextData.get(port).setProperty(PORT_REP, portNode);
            
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
        RenderingContextData.get(port).setProperty(KlighdInternalProperties.ACTIVE, true);
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
        KPortNode portNode = RenderingContextData.get(port).getProperty(PORT_REP);
        if (portNode == null) {
            return;
        }
        
        uninstallLayoutSyncAdapter(port);
        uninstallLabelSyncAdapter(port);
        
        // remove the port representation from the containing node
        portNode.removeFromParent();
        RenderingContextData.get(port).setProperty(KlighdInternalProperties.ACTIVE, false);

        if (releaseControllers) {
            // release the objects kept in mind
            portNode.getRenderingController().removeAllPNodeControllers();
            // release the node rendering controller
            portNode.setRenderingController(null);
            // release the child (label)nodes
            portNode.removeAllChildren();
            // release the port representation from the port's renderingContextData
            RenderingContextData.get(port).setProperty(PORT_REP, null);
        }
    }


    /**
     * Adds representations for the labels attached to the labeled element to the labeled node.
     * 
     * @param labeledNode
     *            the labeled node
     * @param labeledElement
     *            the labeled element
     */
    private void handleLabels(final ILabeledGraphElement<?> labeledNode,
            final KLabeledGraphElement labeledElement) {
        for (KLabel label : labeledElement.getLabels()) {
            addLabel(labeledNode, label);
        }

        if (sync) {
            installLabelSyncAdapter(labeledNode, labeledElement);
        }
    }

    /**
     * Adds a representation for the label to the given labeled node.
     * 
     * @param labeledNode
     *            the labeled node
     * @param label
     *            the label
     */
    private void addLabel(final ILabeledGraphElement<?> labeledNode, final KLabel label) {
        KLabelNode labelNode = RenderingContextData.get(label).getProperty(LABEL_REP);

        if (labelNode != null && labelNode.getParent() != null) {
            return;
        }

        // if there is no Piccolo2d representation of the label create it
        if (labelNode == null) {
            labelNode = new KLabelNode(label);
            RenderingContextData.get(label).setProperty(LABEL_REP, labelNode);

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
            installTextSyncAdapter(labelNode, label);
        }

        // add the label
        labeledNode.addLabel(labelNode);
        RenderingContextData.get(label).setProperty(KlighdInternalProperties.ACTIVE, true);
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
        KLabelNode labelNode = RenderingContextData.get(label).getProperty(LABEL_REP);
        if (labelNode == null) {
            return;
        }

        uninstallLayoutSyncAdapter(label);
        uninstallTextSyncAdapter(label);

        // remove the label representation from the containing node
        labelNode.removeFromParent();
        RenderingContextData.get(label).setProperty(KlighdInternalProperties.ACTIVE, false);

        if (releaseControllers) {
            // release the objects kept in mind
            labelNode.getRenderingController().removeAllPNodeControllers();
            // release the node rendering controller
            labelNode.setRenderingController(null);
            // release the label representation from the label's renderingContextData
            RenderingContextData.get(label).setProperty(LABEL_REP, null);
        }
    }


    private boolean isAutomaticallyArranged(final KGraphElement element) {
        KShapeLayout shapeLayout = this.topNode.getGraphElement().getData(KShapeLayout.class);
        if (shapeLayout == null || shapeLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
            return false;
        }
        shapeLayout = element.getData(KShapeLayout.class);
        if (shapeLayout != null && shapeLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
            return false;
        }
        final KNode container = ModelingUtil.eContainerOfType(element, KNode.class);
        shapeLayout = container == null ? null : container.getData(KShapeLayout.class);
        if (shapeLayout != null && shapeLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
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
            installLayoutSyncAdapter(nodeNode);
        }
 
        final KShapeLayout shapeLayout = nodeNode.getGraphElement().getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applySmartBounds(nodeNode, shapeLayout.getXpos(), shapeLayout.getYpos(),
                    shapeLayout.getWidth(), shapeLayout.getHeight());
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
            installLayoutSyncAdapter(portNode);
        }

        final KShapeLayout shapeLayout = portNode.getGraphElement().getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applySmartBounds(portNode, shapeLayout.getXpos(), shapeLayout.getYpos(),
                    shapeLayout.getWidth(), shapeLayout.getHeight());

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
            installLayoutSyncAdapter(labelNode);
        }

        final KShapeLayout shapeLayout = labelNode.getGraphElement().getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applySmartBounds(labelNode, shapeLayout.getXpos(), shapeLayout.getYpos(),
                    shapeLayout.getWidth(), shapeLayout.getHeight());
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
            installLayoutSyncAdapter(edgeRep);
        }

        final KEdge edge = edgeRep.getGraphElement();
        final KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        if (edgeLayout != null) {
            final KRendering rendering = edge.getData(KRendering.class);
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

    private static final Predicate<Object> INSTANCEOF_SHAPE_LAYOUT_SYNC_ADAPTER
            = Predicates.instanceOf(KGEShapeLayoutPNodeUpdater.class);

    /**
     * Installs an adapter on the represented node to synchronize new shape layouts with specified
     * layout.
     * 
     * @param nodeRep
     *            the node representation
     */
    private void installLayoutSyncAdapter(final KNodeNode nodeRep) {
        final KNode node = nodeRep.getGraphElement();

        // remove the currently installed adapter if any
        Iterables.removeIf(node.eAdapters(), INSTANCEOF_SHAPE_LAYOUT_SYNC_ADAPTER);
        
        // register adapter on the node to stay in sync
        node.eAdapters().add(new KGEShapeLayoutPNodeUpdater(nodeRep, this));
    }

    private void uninstallLayoutSyncAdapter(final KNode node) {
        // remove the currently installed adapter if any
        Iterables.removeIf(node.eAdapters(), INSTANCEOF_SHAPE_LAYOUT_SYNC_ADAPTER);
    }

    /**
     * Installs an adapter on the represented port to synchronize the representation with the
     * specified layout.
     * 
     * @param portRep
     *            the port representation
     */
    private void installLayoutSyncAdapter(final KPortNode portRep) {
        final KPort port = portRep.getGraphElement();

        // remove the currently installed adapter if any
        Iterables.removeIf(port.eAdapters(), INSTANCEOF_SHAPE_LAYOUT_SYNC_ADAPTER);
        
        // register adapter on the port to stay in sync
        port.eAdapters().add(new KGEShapeLayoutPNodeUpdater(portRep, this));
    }

    private void uninstallLayoutSyncAdapter(final KPort port) {
        // remove the currently installed adapter if any
        Iterables.removeIf(port.eAdapters(), INSTANCEOF_SHAPE_LAYOUT_SYNC_ADAPTER);
    }

    /**
     * Installs an adapter on the represented label to synchronize the representation with the
     * specified layout.
     * 
     * @param labelRep
     *            the label representation
     */
    private void installLayoutSyncAdapter(final KLabelNode labelRep) {
        final KLabel label = labelRep.getGraphElement();
        
        // remove the currently installed adapter if any
        Iterables.removeIf(label.eAdapters(), INSTANCEOF_SHAPE_LAYOUT_SYNC_ADAPTER);
        
        // register adapter on the label to stay in sync
        label.eAdapters().add(new KGEShapeLayoutPNodeUpdater(labelRep, this));
    }

    private void uninstallLayoutSyncAdapter(final KLabel label) {
        // remove the currently installed adapter if any
        Iterables.removeIf(label.eAdapters(), INSTANCEOF_SHAPE_LAYOUT_SYNC_ADAPTER);
    }


    private static final Predicate<Object> INSTANCEOF_EDGE_LAYOUT_SYNC_ADAPTER
            = Predicates.instanceOf(KEdgeLayoutEdgeNodeUpdater.class);

    /**
     * Installs an adapter on the represented edge to synchronize the representation with the
     * specified layout.
     *
     * @param edgeRep
     *            the edge representation
     */
    private void installLayoutSyncAdapter(final KEdgeNode edgeRep) {
        final KEdge edge = edgeRep.getGraphElement();

        // remove the currently installed adapter if any
        Iterables.removeIf(edge.eAdapters(), INSTANCEOF_EDGE_LAYOUT_SYNC_ADAPTER);

        // register adapter on the edge to stay in sync
        edge.eAdapters().add(new KEdgeLayoutEdgeNodeUpdater(edgeRep, this));
    }

    private void uninstallLayoutSyncAdapter(final KEdge edge) {
        // remove the currently installed adapter if any
        Iterables.removeIf(edge.eAdapters(), INSTANCEOF_EDGE_LAYOUT_SYNC_ADAPTER);
    }

    // ---------------------------------------------------------------------------------- //
    //  KGraphElement data synchronization

    /**
     * Installs an adapter on the represented node to synchronize the children of the representation
     * with the specified children in the model.
     * 
     * @param nodeRep
     *            the node representation
     * @param node
     *            the node
     */
    private void installChildrenSyncAdapter(final INode nodeRep, final KNode node) {
        // remove any existing children sync adapters, which may be out-of-date 
        Iterables.removeIf(node.eAdapters(), INSTANCEOF_CHILDREN_SYNC_ADAPTER);
        
        // add an adapter on the node's children
        node.eAdapters().add(new ChildrenSyncAdapter(nodeRep));
    }

    /**
     * Uninstalls the children synchronization adapter from a {@link KNode}.
     * 
     * @param node
     *            the node
     */
    private void uninstallChildrenSyncAdapter(final KNode node) {
        // remove the currently installed children sync adapters
        Iterables.removeIf(node.eAdapters(), INSTANCEOF_CHILDREN_SYNC_ADAPTER);
    }

    private static final Predicate<Object> INSTANCEOF_CHILDREN_SYNC_ADAPTER
            = Predicates.instanceOf(ChildrenSyncAdapter.class);

    /**
     * A dedicated specialization of {@link AdapterImpl} allowing 'instanceof' tests.
     */
    private final class ChildrenSyncAdapter extends AdapterImpl {
        private final INode nodeRep;
        
        private ChildrenSyncAdapter(final INode theNodeRep) {
            this.nodeRep = theNodeRep;
        }
        
        public void notifyChanged(final Notification notification) {

            if (notification.getFeatureID(KNode.class) == KGraphPackage.KNODE__CHILDREN) {
                switch (notification.getEventType()) {
                case Notification.ADD: {
                    final KNode addedNode = (KNode) notification.getNewValue();
                    addNode(nodeRep, addedNode);
                    break;
                }
                case Notification.ADD_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KNode> addedNodes = (List<KNode>) notification.getNewValue();

                    for (KNode addedNode : addedNodes) {
                        addNode(nodeRep, addedNode);
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
                    for (KNode n : Iterables2.toIterable(Iterators.filter(
                            removedNode.eAllContents(), KNode.class))) {
                        removeNode(n, true);
                    }
                    break;
                }
                case Notification.REMOVE_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KNode> removedNodes = (List<KNode>) notification.getOldValue();

                    for (KNode removedNode : removedNodes) {
                        removeNode(removedNode, true);

                        // Removing all contained nodes is required to remove all outgoing or
                        //  incoming edges, as in case of interlevel ones their representing
                        //  KEdgeNodes are attached to one of n's parent representatives, which might
                        //  be one of removedNode's parent representatives.
                        for (KNode n : Iterables2.toIterable(Iterators.filter(
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

    /**
     * Installs an adapter on the node to synchronize the incoming and outgoing edges of the
     * representation with the specified incoming and outgoing edges in the model.
     * 
     * @param node
     *            the node
     */
    private void installEdgeSyncAdapter(final KNode node) {
        // remove any existing edge sync adapters, which may be out-of-date 
        Iterables.removeIf(node.eAdapters(), INSTANCEOF_EDGE_SYNC_ADAPTER);

        // add the adapter
        node.eAdapters().add(new EdgeSyncAdapter());
    }
    
    /**
     * Uninstalls the edge synchronization adapters from a {@link KNode}.
     * 
     * @param node
     *            the node
     */
    private void uninstallEdgeSyncAdapter(final KNode node) {
        // remove the currently installed edge sync adapters
        Iterables.removeIf(node.eAdapters(), INSTANCEOF_EDGE_SYNC_ADAPTER);
    }

    private static final Predicate<Object> INSTANCEOF_EDGE_SYNC_ADAPTER
            = Predicates.instanceOf(EdgeSyncAdapter.class);

    /**
     * A dedicated specialization of {@link AdapterImpl} allowing 'instanceof' tests.
     */
    private final class EdgeSyncAdapter extends AdapterImpl {

        public void notifyChanged(final Notification notification) {
            int featureId = notification.getFeatureID(KNode.class);
            if (featureId == KGraphPackage.KNODE__OUTGOING_EDGES
                    || featureId == KGraphPackage.KNODE__INCOMING_EDGES) {
                final boolean releaseChildrenAndControllers =
                        featureId == KGraphPackage.KNODE__OUTGOING_EDGES;
                
                switch (notification.getEventType()) {
                case Notification.ADD: {
                    final KEdge addedEdge = (KEdge) notification.getNewValue();
                    addEdge(addedEdge);
                    break;
                }
                case Notification.ADD_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KEdge> addedEdges = (List<KEdge>) notification.getNewValue();

                    for (KEdge addedEdge : addedEdges) {
                        addEdge(addedEdge);
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

                    for (KEdge removedEdge : removedEdges) {
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


    /**
     * Installs an adapter on the represented node to synchronize the ports of the representation
     * with the specified ports in the model.
     * 
     * @param nodeRep
     *            the node representation
     * @param node
     *            the node
     */
    private void installPortSyncAdapter(final KNodeNode nodeRep, final KNode node) {
        // remove any existing port sync adapters, which may be out-of-date 
        Iterables.removeIf(node.eAdapters(), INSTANCEOF_PORT_SYNC_ADAPTER);

        // add the adapter
        node.eAdapters().add(new PortSyncAdapter(nodeRep));
    }
    
    /**
     * Uninstalls the port synchronization adapters from a {@link KNode}.
     * 
     * @param node
     *            the node
     */
    private void uninstallPortSyncAdapter(final KNode node) {
        // remove the currently installed port sync adapters
        Iterables.removeIf(node.eAdapters(), INSTANCEOF_PORT_SYNC_ADAPTER);
    }

    private static final Predicate<Object> INSTANCEOF_PORT_SYNC_ADAPTER
            = Predicates.instanceOf(PortSyncAdapter.class);

    /**
     * A dedicated specialization of {@link AdapterImpl} allowing 'instanceof' tests.
     */
    private final class PortSyncAdapter extends AdapterImpl {
        private final KNodeNode nodeRep;
        
        private PortSyncAdapter(final KNodeNode theNodeRep) {
            this.nodeRep = theNodeRep;
        }
        
        public void notifyChanged(final Notification notification) {
            if (notification.getFeatureID(KNode.class) == KGraphPackage.KNODE__PORTS) {

                switch (notification.getEventType()) {
                case Notification.ADD: {
                    final KPort addedPort = (KPort) notification.getNewValue();
                    addPort(nodeRep, addedPort);
                    break;
                }
                case Notification.ADD_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KPort> addedPorts = (List<KPort>) notification.getNewValue();

                    for (KPort addedPort : addedPorts) {
                        addPort(nodeRep, addedPort);
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

                    for (KPort removedPort : removedPorts) {
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


    /**
     * Installs an adapter on the labeled element to synchronize the labels of the representation
     * with the specified labels in the model.
     * 
     * @param labeledNode
     *            the labeled node
     * @param labeledElement
     *            the labeled element
     */
    private void installLabelSyncAdapter(final ILabeledGraphElement<?> labeledNode,
            final KLabeledGraphElement labeledElement) {

        // remove any existing label sync adapters, which may be out-of-date 
        Iterables.removeIf(labeledElement.eAdapters(), INSTANCEOF_LABEL_SYNC_ADAPTER);

        // add an adapter on the labeled element's labels
        labeledElement.eAdapters().add(new LabelSyncAdapter(labeledNode));
    }

    /**
     * Uninstalls the label synchronization adapters from a {@link KLabeledGraphElement}.
     * 
     * @param labeledElement
     *            the {@link KLabeledGraphElement}
     */
    private void uninstallLabelSyncAdapter(final KLabeledGraphElement labeledElement) {
        // remove the currently installed label sync adapters
        Iterables.removeIf(labeledElement.eAdapters(), INSTANCEOF_LABEL_SYNC_ADAPTER);
    }
    
    private static final Predicate<Object> INSTANCEOF_LABEL_SYNC_ADAPTER
            = Predicates.instanceOf(LabelSyncAdapter.class);

    /**
     * A dedicated specialization of {@link AdapterImpl} allowing 'instanceof' tests.
     */
    private final class LabelSyncAdapter extends AdapterImpl {
        private final ILabeledGraphElement<?> labeledNode;
        
        private LabelSyncAdapter(final ILabeledGraphElement<?> theLabeledNode) {
            this.labeledNode = theLabeledNode;
        }
        
        public void notifyChanged(final Notification notification) {
            
            if (notification.getFeatureID(KLabeledGraphElement.class)
                    == KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS) {
                
                switch (notification.getEventType()) {
                case Notification.ADD: {
                    final KLabel addedLabel = (KLabel) notification.getNewValue();
                    addLabel(labeledNode, addedLabel);
                    break;
                }
                case Notification.ADD_MANY: {
                    @SuppressWarnings("unchecked")
                    final List<KLabel> addedLabels = (List<KLabel>) notification.getNewValue();

                    for (KLabel addedLabel : addedLabels) {
                        addLabel(labeledNode, addedLabel);
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

                    for (KLabel removedLabel : removedLabels) {
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


    /**
     * Installs an adapter on the represented label to synchronize the text of the representation
     * with the specified text in the model.
     * 
     * @param nodeRep
     *            the node representation
     * @param label
     *            the label
     */
    private void installTextSyncAdapter(final KLabelNode labelRep, final KLabel label) {
        // remove any existing text sync adapters, which may be out-of-date 
        Iterables.removeIf(label.eAdapters(), INSTANCEOF_TEXT_SYNC_ADAPTER);

        // add an adapter on the node's ports
        label.eAdapters().add(new TextSyncAdapter(labelRep));
    }
    
    /**
     * Uninstalls the text synchronization adapter(s) from a {@link KLabel}.
     * 
     * @param label
     *            the {@link KLabel}
     */
    private void uninstallTextSyncAdapter(final KLabel label) {
        // remove the currently installed text sync adapters
        Iterables.removeIf(label.eAdapters(), INSTANCEOF_TEXT_SYNC_ADAPTER);
    }
    
    private static final Predicate<Object> INSTANCEOF_TEXT_SYNC_ADAPTER
            = Predicates.instanceOf(TextSyncAdapter.class);
    
    /**
     * A dedicated specialization of {@link AdapterImpl} allowing 'instanceof' tests.
     */
    private final class TextSyncAdapter extends AdapterImpl {
        private final KLabelNode labelRep;
        
        private TextSyncAdapter(final KLabelNode theLabelRep) {
            this.labelRep = theLabelRep;
        }

        public void notifyChanged(final Notification notification) {
            if (notification.getFeatureID(KLabel.class) == KGraphPackage.KLABEL__TEXT) {

                switch (notification.getEventType()) {
                case Notification.SET:
                    labelRep.setText(notification.getNewStringValue());
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
     * Finds the parent node for the edge representation and adds the edge to that node
     * representations child area. This is needed since the clipping property of
     * {@link KChildAreaNode}s will clip the edges. Hence they are located in the
     * {@link KChildAreaNode} of lowest common ancestor.
     * 
     * @param edgeRep
     *            the edge representation
     */
    private void updateEdgeParent(final KEdgeNode edgeRep) {
        KEdge edge = edgeRep.getGraphElement();
        KNode source = edge.getSource();
        KNode target = edge.getTarget();
        if (source != null && target != null) {
            KNode commonParent = findLowestCommonAncestor(source, target);
            INode commonParentNode = RenderingContextData.get(commonParent).getProperty(REP);
            if (commonParentNode != null) {
                KChildAreaNode childAreaNode = commonParentNode.getChildAreaNode();
                childAreaNode.addEdge(edgeRep);
            }
        }
    }

    /**
     * Updates the offset of the edge representation. Takes care about insets due to
     * {@link de.cau.cs.kieler.core.krendering.KPlacementData KPlacementData} and the relocation
     * performed in {@link #updateEdgeParent(KEdgeNode)}-
     * 
     * @param edgeNode
     *            the edge representation
     */
    private void updateEdgeOffset(final KEdgeNode edgeNode) {
        final KChildAreaNode edgeNodeParent = edgeNode.getParentChildArea();
        if (edgeNodeParent != null) {
            KEdge edge = edgeNode.getGraphElement();
            // chsch: change due to KIELER-1988; // SUPPRESS CHECKSTYLE NEXT 3 LineLength
            // edges uses different reference points as indicated by
            // http://rtsys.informatik.uni-kiel.de/~kieler/files/documentation/klayoutdata-reference-points.png
            // see page http://rtsys.informatik.uni-kiel.de/confluence/display/KIELER/KLayoutData+Meta+Model
            INode sourceParentNode = RenderingContextData.get(determineReferenceNodeOf(edge))
                    .getProperty(REP);
            final KChildAreaNode relativeChildArea = sourceParentNode.getChildAreaNode();

            // chsch: The following listener updates the offset of the edge depending the parent nodes.
            // It is attached to all parent nodes that are part of the containment hierarchy,
            //  i.e., KNodeNodes, KChildAreaNodes, KlighdPaths...!
            // The listener is sensitive to changes to the 'transform' of those elements.
            // It is important, in case of the change of a parent KNode's rendering,
            //  that its related KChildAreaNode is contained in any other PNode!
            PropertyChangeListener listener = new PropertyChangeListener() {

                // assumption: KChildAreaNodes in the containment hierarchy do not have an empty
                // 'parent' reference, otherwise an offset change has been performed on a
                // non-contained
                // child area. This must be avoided under all circumstances!
                public void propertyChange(final PropertyChangeEvent event) {

                    // calculate the offset
                    Point2D offset = new Point2D.Double(0, 0);
                    PNode currentNode = relativeChildArea;
                    while (currentNode != null && currentNode != edgeNodeParent) {
                        currentNode.localToParent(offset);
                        currentNode = currentNode.getParent();
                    }

                    // apply the offset
                    NodeUtil.applyTranslation(edgeNode, offset);
                }
            };

            // remember the listener
            edgeNode.addAttribute(EDGE_OFFSET_LISTENER_KEY, listener);

            // calculate the offset and register the update offset listener
            List<PNode> listenedNodes = Lists.newLinkedList();
            Point2D offset = new Point2D.Double(0, 0);
            PNode currentNode = relativeChildArea;
            while (currentNode != null && currentNode != edgeNodeParent) {
                currentNode.localToParent(offset);
                currentNode.addPropertyChangeListener(PNode.PROPERTY_TRANSFORM, listener);
                listenedNodes.add(currentNode);
                currentNode = currentNode.getParent();
            }

            // remember the listened nodes
            edgeNode.addAttribute(EDGE_OFFSET_LISTENED_KEY, listenedNodes);

            // apply the offset
            NodeUtil.applyTranslation(edgeNode, offset);
        }
    }

    /**
     * Removes all listeners used to update the edge representations offset from the associated
     * nodes.
     * 
     * @param edgeNode
     *            the edge representation
     */
    private void removeEdgeOffsetListener(final KEdgeNode edgeNode) {
        PropertyChangeListener listener = (PropertyChangeListener) edgeNode
                .getAttribute(EDGE_OFFSET_LISTENER_KEY);
        @SuppressWarnings("unchecked")
        List<PNode> listenedNodes = (List<PNode>) edgeNode.getAttribute(EDGE_OFFSET_LISTENED_KEY);
        if (listener != null && listenedNodes != null) {
            for (PNode listenedNode : listenedNodes) {
                listenedNode.removePropertyChangeListener(PNode.PROPERTY_TRANSFORM, listener);
            }
        }
        edgeNode.addAttribute(EDGE_OFFSET_LISTENER_KEY, null);
        edgeNode.addAttribute(EDGE_OFFSET_LISTENED_KEY, null);
    }

    /**
     * Needed as edge coordinates uses different reference nodes as indicated by
     * http://rtsys.informatik.uni-kiel.de/~kieler/files/documentation/klayoutdata-reference-points.png
     * see page http://rtsys.informatik.uni-kiel.de/confluence/display/KIELER/KLayoutData+Meta+Model.
     * 
     * @param edge
     *            the edge whose reference node is to be determined,
     * @return its reference node
     */
    private static KNode determineReferenceNodeOf(final KEdge edge) {
        // in case of a self loop, the reference node the source/target's parent
        if (edge.getSource() == edge.getTarget()) {
            return edge.getSource().getParent();
        }

        // determine whether the edge directs to an inner node
        KNode node = edge.getTarget();
        while (node != null && node != edge.getSource()) {
            node = node.getParent();
        }
        // if (node != null) holds, node == edge.getSource() holds and therefore the target node is
        // contained in the source node; in this case the source node's child area denotes the
        // reference point of the edge's coordinates, the child area of the source node's parent
        // otherwise, as indicated by the above mentioned illustration
        return node != null ? edge.getSource() : edge.getSource().getParent();
    }

    /**
     * Returns the lowest common ancestor to both given nodes.
     * 
     * @param source
     *            the first node
     * @param target
     *            the second node
     * @return the lowest common ancestor
     */
    private static KNode findLowestCommonAncestor(final KNode source, final KNode target) {
        if (source.getParent() == target.getParent()) {
            // this is the case in common graphs, e.g. state charts
            return source.getParent();
        } else if (target.getParent() == source) {
            // this is the case if (in data flow diagrams) the edge connects an input port
            //  of a composite node with an inner node 
            return source;
        } else if (source.getParent() == target) {
            // this is the case if (in data flow diagrams) the edge connects an inner node
            //  of a composite node with an output port of the latter one 
            return target;
        } else {
            final List<EObject> sourceParents = Lists.newArrayList(ModelingUtil.eAllContainers(source));
            final List<EObject> targetParents = Lists.newArrayList(ModelingUtil.eAllContainers(target));
            
            sourceParents.retainAll(targetParents);
            return (KNode) Iterables.getFirst(sourceParents, null);
        }
    }

    /**
     * Removes all rendering context data from the given graph element and all child elements.<br>
     * <br>
     * Review: knodes maintain context information, these information is removed by this statement,
     * mainly needed if textual kgraph editor is used shall be obsolete if an adequate update
     * strategy is available
     * 
     * @param element
     *            the graph element
     */
    private static void resetGraphElement(final KGraphElement element) {
        // remove rendering context data from the element
        RenderingContextData data = element.getData(RenderingContextData.class);
        if (data != null) {
            element.getData().remove(data);
        }

        // proceed recursively with child elements
        new KGraphSwitch<Boolean>() {
            public Boolean caseKNode(final KNode node) {
                for (KNode child : node.getChildren()) {
                    resetGraphElement(child);
                }
                for (KEdge edge : node.getOutgoingEdges()) {
                    resetGraphElement(edge);
                }
                for (KPort port : node.getPorts()) {
                    resetGraphElement(port);
                }
                for (KLabel label : node.getLabels()) {
                    resetGraphElement(label);
                }
                return true;
            }

            public Boolean caseKEdge(final KEdge edge) {
                for (KLabel label : edge.getLabels()) {
                    resetGraphElement(label);
                }
                return true;
            }

            public Boolean caseKPort(final KPort port) {
                for (KLabel label : port.getLabels()) {
                    resetGraphElement(label);
                }
                return true;
            }
        } /**/.doSwitch(element);
    }
}