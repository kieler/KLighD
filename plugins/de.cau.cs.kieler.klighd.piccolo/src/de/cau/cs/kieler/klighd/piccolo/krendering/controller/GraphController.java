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
package de.cau.cs.kieler.klighd.piccolo.krendering.controller;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.util.KGraphSwitch;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.krendering.DiagramLayoutManager;
import de.cau.cs.kieler.klighd.piccolo.activities.ApplySmartBoundsActivity;
import de.cau.cs.kieler.klighd.piccolo.krendering.ApplyBendPointsActivity;
import de.cau.cs.kieler.klighd.piccolo.krendering.ILabeledGraphElement;
import de.cau.cs.kieler.klighd.piccolo.krendering.INode;
import de.cau.cs.kieler.klighd.piccolo.krendering.KChildAreaNode;
import de.cau.cs.kieler.klighd.piccolo.krendering.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.krendering.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.krendering.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.krendering.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.krendering.KPortNode;
import de.cau.cs.kieler.klighd.piccolo.util.NodeUtil;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * The class which controls the transformation of a KGraph with attached KRendering data to Piccolo
 * nodes and the synchronization of these Piccolo nodes with the KGraph model.
 * 
 * TODO assess mri's assumption of performing changes in the PNode structure in the display thread,
 * since I couldn't see this in combination width KLighD. However, the KLighD view initialization
 * calls are performed in the display thread...
 * 
 * @author mri, chsch
 */
public class GraphController {

    /**
     * Property name of edge layout listeners updating the edge node.
     * Listeners are attached to edge nodes via this name allowing to detach the from the
     * edge layout if the edge is removed from the KGraph.
     * 
     * @author chsch
     */
    private static final String K_EDGE_LAYOUT_LISTENER = "KEdgeLayoutListener";

    /** the property for the Piccolo representation of a node. */
    public static final IProperty<PNode> REP = new Property<PNode>("klighd.piccolo.prepresentation");

    /**
     * the property for identifying whether a node has been populated. If a node is populated, child
     * node have been created once.
     */
    private static final IProperty<Boolean> POPULATED = new Property<Boolean>("klighd.populated",
            false);
    /**
     * the property for identifying whether a node is currently active. If a node is active, it is
     * visible.
     */
    // Review:
    // activate the subgraph
    // this is probably crucial in case the structure
    // has been changed during an incremental update
    // the activity flag is also important
    // in case of inter level edges in combination with
    // lazy loading/collapsing+expanding
    private static final IProperty<Boolean> ACTIVE = new Property<Boolean>("klighd.active", false);
    /** the property for remembering the edge sync adapter on a node. */
    private static final IProperty<AdapterImpl> EDGE_SYNC_ADAPTER = new Property<AdapterImpl>(
            "klighd.edgeSyncAdapter");

    /** the attribute key for the rendering. */
    private static final Object RENDERING_KEY = new Object();
    /** the attribute key for the edge offset listeners. */
    private static final Object EDGE_OFFSET_LISTENER_KEY = new Object();
    /** the attribute key for the nodes listed by edge offset listeners. */
    private static final Object EDGE_OFFSET_LISTENED_KEY = new Object();

    /** the Piccolo node representing the top node in the graph. */
    private KNodeTopNode topNode;

    /** whether to sync the representation with the graph model. */
    private boolean sync = false;
    /** whether to record layout changes. */
    // Right now: will be set to true by the DiagramLayoutManager.
    private boolean record = false;

    /** the layout changes to graph elements while recording. */
    private Map<Object, Object> recordedChanges = Maps.newLinkedHashMap();

    /**
     * Constructs a graph controller for the given graph. The Piccolo nodes created for the graph
     * will be parented by the specified parent node.
     * 
     * @param graph
     *            the graph
     * @param parent
     *            the parent Piccolo node
     * @param sync
     *            true if the visualization should be synchronized with the graph; false else
     * 
     *            review hint: setting to false will prevent the application of automatic layout
     */
    public GraphController(final KNode graph, final PNode parent, final boolean sync) {
        // Review: kgraph nodes maintain context information,
        // these information is removed by this statement,
        // mainly needed if textual kgraph editor is used
        // shall be obsolete if an adequate update strategy is available
        resetGraphElement(graph);
        this.topNode = new KNodeTopNode(graph);
        parent.addChild(topNode);
        this.sync = sync;
    }

    /**
     * Returns the node representing the graph of this controller.
     * 
     * @return the graph
     */
    public KNode getGraph() {
        return topNode.getGraphElement();
    }

    /**
     * Returns whether the represenation is synchronized with the graph.
     * 
     * @return true if the representation is synchronized with the graph; false else
     */
    public boolean getSync() {
        return sync;
    }

    /**
     * Sets whether to record layout changes in the graph instead of instantly applying them to the
     * associated Piccolo nodes.<br>
     * <br>
     * Setting the recording status to {@code false} applies all recorded layout changes.
     * 
     * @param recording
     *            true if layout changes should be recorded; false else
     * 
     * @author mri, chsch
     */
    public void setRecording(final boolean recording) {
        if (record && !recording) {
            // apply recorded layout changes
            handleRecordedChanges();
        }
        record = recording;
    }

    /**
     * Initializes the graph controller.
     */
    public void initialize() {
        // expand the top node
        addExpansionListener(topNode);
        RenderingContextData.get(topNode.getGraphElement()).setProperty(ACTIVE, true);
        topNode.getChildArea().setExpanded(true);
    }

    /**
     * Expandes the representation of the given node.
     * 
     * @param node
     *            the node
     */
    public void expand(final KNode node) {
        INode nodeRep = RenderingContextData.get(node).getProperty(INode.NODE_REP);
        if (nodeRep != null) {
            // set the child area to expanded
            nodeRep.getChildArea().setExpanded(true);
        }
    }

    /**
     * Collapses the representation of the given node.
     * 
     * @param node
     *            the node
     */
    public void collapse(final KNode node) {
        INode nodeRep = RenderingContextData.get(node).getProperty(INode.NODE_REP);
        if (nodeRep != null) {
            nodeRep.getChildArea().setExpanded(false);
        }
    }

    /**
     * Adds a listener on the expansion of the child area of the given node representation.
     * 
     * @param nodeNode
     *            the node representation
     */
    private void addExpansionListener(final INode nodeNode) {
        KChildAreaNode childAreaNode = nodeNode.getChildArea();
        if (childAreaNode != null) {
            final KNode node = nodeNode.getGraphElement();
            final RenderingContextData data = RenderingContextData.get(node);
            childAreaNode.addPropertyChangeListener(KChildAreaNode.PROPERTY_EXPANSION,
                    new PropertyChangeListener() {
                        public void propertyChange(final PropertyChangeEvent event) {
                            if ((Boolean) event.getNewValue()) {
                                // populate the node if necessary
                                if (!data.getProperty(POPULATED)) {
                                    // if children nodes have never been created in the past
                                    // create them right now!
                                    handleChildren(nodeNode);
                                    data.setProperty(POPULATED, true);
                                } else {
                                    // activate the subgraph
                                    // this is probably crucial in case the structure
                                    // has been changed during an incremental update
                                    // the activity flag is also important
                                    // in case of inter-level edges in combination with
                                    // lazy loading/collapsing+expanding
                                    if (data.getProperty(ACTIVE)) {
                                        for (KNode child : node.getChildren()) {
                                            activateSubgraph(child);
                                        }
                                    }
                                }
                            } else {
                                // deactivate the subgraph if necessary
                                if (data.getProperty(ACTIVE)) {
                                    for (KNode child : node.getChildren()) {
                                        deactivateSubgraph(child);
                                    }
                                }
                            }
                        }
                    });
        }
    }

    /**
     * Handles the children of the parent node.
     * 
     * @param parentNode
     *            the parent structure node representing a KNode
     */
    private void handleChildren(final INode parentNode) {
        KNode parent = parentNode.getGraphElement();

        // create the nodes
        for (KNode child : parent.getChildren()) {
            addNode(parentNode, child);
        }

        if (sync) {
            installChildrenSyncAdapter(parentNode);
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
        INode nodeRep = RenderingContextData.get(node).getProperty(INode.NODE_REP);

        KNodeNode nodeNode;
        if (nodeRep instanceof KNodeTopNode) {
            // if the node is the current top-node something went wrong
            throw new RuntimeException("The top-node can never be made a child node");
        } else {
            nodeNode = (KNodeNode) nodeRep;
        }

        // only add the representation if it is not added already
        if (nodeNode == null || nodeNode.getParent() == null) {
            // if there is no Piccolo representation for the node create it
            if (nodeNode == null) {
                nodeNode = new KNodeNode(node, parent);
                updateLayout(nodeNode);
                updateRendering(nodeNode);
                handlePorts(nodeNode);
                handleLabels(nodeNode, node);
                addExpansionListener(nodeNode);

                // add the node
                parent.getChildArea().addNode(nodeNode);

                // TODO only temporary auto-expand
                nodeNode.getChildArea().setExpanded(true);
            } else {
                // add the node
                parent.getChildArea().addNode(nodeNode);
            }

            // activate the subgraph specified by the node
            if (RenderingContextData.get(parent.getGraphElement()).getProperty(ACTIVE)) {
                activateSubgraph(node);
            }
        }

        return nodeNode;
    }

    /**
     * Removes the representation for the node from its parent.
     * 
     * @param node
     *            the node
     */
    private void removeNode(final KNode node) {
        INode nodeRep = RenderingContextData.get(node).getProperty(INode.NODE_REP);
        if (nodeRep != null) {
            KNodeNode nodeNode;
            if (nodeRep instanceof KNodeTopNode) {
                // if the node is the current top-node something went wrong
                throw new RuntimeException("The top-node can never be removed from a parent node");
            } else {
                nodeNode = (KNodeNode) nodeRep;
            }

            // deactivate the subgraph
            deactivateSubgraph(node);

            // remove the node representation from the containing child area
            nodeNode.removeFromParent();
        }
    }

    /**
     * Adds the representations of all edges and marks all nodes as visualized in the subgraph
     * specified by the given node.
     * 
     * @param node
     *            the node
     */
    private void activateSubgraph(final KNode node) {
        RenderingContextData contextData = RenderingContextData.get(node);

        // mark the node as active
        contextData.setProperty(ACTIVE, true);

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

        // proceed recursively if the node is expanded
        if (node.getChildren().size() > 0) {
            INode nodeNode = contextData.getProperty(INode.NODE_REP);
            if (nodeNode != null && nodeNode.getChildArea().isExpanded()) {
                for (KNode child : node.getChildren()) {
                    activateSubgraph(child);
                }
            }
        }
    }

    /**
     * Removes the representations of all edges and marks all nodes as not visualized in the
     * subgraph specified by the given node.
     * 
     * @param node
     *            the node
     */
    private void deactivateSubgraph(final KNode node) {
        // mark the node as inactive
        RenderingContextData.get(node).setProperty(ACTIVE, false);

        if (sync) {
            uninstallEdgeSyncAdapter(node);
        }

        // remove all incoming edges
        for (KEdge incomingEdge : node.getIncomingEdges()) {
            removeEdge(incomingEdge);
        }

        // remove all outgoing edges
        for (KEdge outgoingEdge : node.getOutgoingEdges()) {
            removeEdge(outgoingEdge);
        }

        // proceed recursively
        for (KNode child : node.getChildren()) {
            deactivateSubgraph(child);
        }
    }

    /**
     * Adds a representation for the edge to the appropriate child area.
     * 
     * @param edge
     *            the edge
     */
    private void addEdge(final KEdge edge) {
        KEdgeNode edgeRep = RenderingContextData.get(edge).getProperty(KEdgeNode.EDGE_REP);
        // only add the representation if it is not added already
        if (edgeRep == null || edgeRep.getParent() == null) {
            KNode source = edge.getSource();
            KNode target = edge.getTarget();
            if (source != null && target != null
                    && RenderingContextData.get(source).getProperty(ACTIVE)
                    && RenderingContextData.get(target).getProperty(ACTIVE)) {
                // if there is no Piccolo representation for the edge create it
                if (edgeRep == null) {
                    edgeRep = new KEdgeNode(edge);
                    updateLayout(edgeRep);
                    updateRendering(edgeRep);
                    handleLabels(edgeRep, edge);
                }

                // the following is needed in case of interlevel edges:
                // edges ending in an outer child area will be clipped
                // by the inner childArea;
                // the clipping is generally intended and is realized by
                // KChildAreaNode

                // find and set the parent for the edge
                updateEdgeParent(edgeRep);

                // update the offset of the edge layout to the containing child area
                updateEdgeOffset(edgeRep);
            }
        }
    }

    /**
     * Removes the representation for the edge from its parent.
     * 
     * @param edge
     *            the edge
     */
    private void removeEdge(final KEdge edge) {
        KEdgeNode edgeNode = RenderingContextData.get(edge).getProperty(KEdgeNode.EDGE_REP);
        if (edgeNode != null) {
            // remove the edge offset listeners
            removeEdgeOffsetListener(edgeNode);

            // remove KEdgeLayoutAdapter
            // chsch: added this for performance reasons to get rid of out-dated edges and edge
            // listeners
            final KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            final Object edgeLayoutAdapter = edgeNode.getAttribute(K_EDGE_LAYOUT_LISTENER);
            if (edgeLayout != null && edgeLayoutAdapter != null) {
                edgeLayout.eAdapters().remove(edgeLayoutAdapter);
            }

            // remove the edge representation from the containing child area
            edgeNode.removeFromParent();
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
            installPortSyncAdapter(nodeNode);
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
        KPortNode portNode = RenderingContextData.get(port).getProperty(KPortNode.PORT_REP);

        // if there is no Piccolo representation for the port create it
        if (portNode == null) {
            portNode = new KPortNode(port);
            updateLayout(portNode);
            updateRendering(portNode);
            handleLabels(portNode, port);
        }

        // add the port
        parent.addPort(portNode);
    }

    /**
     * Removes the representation for the port from its parent.
     * 
     * @param port
     *            the port
     */
    private void removePort(final KPort port) {
        KPortNode portNode = RenderingContextData.get(port).getProperty(KPortNode.PORT_REP);
        if (portNode != null) {
            // remove the port representation from the containing node
            portNode.removeFromParent();
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
        KLabelNode labelNode = RenderingContextData.get(label).getProperty(KLabelNode.LABEL_REP);

        // if there is no Piccolo representation for the label create it
        if (labelNode == null) {
            labelNode = new KLabelNode(label);
            labelNode.setText(label.getText());
            updateLayout(labelNode);
            updateRendering(labelNode);

            if (sync) {
                installTextSyncAdapter(labelNode);
            }
        }

        // add the label
        labeledNode.addLabel(labelNode);
    }

    /**
     * Removes the representation for the label from its parent.
     * 
     * @param label
     *            the label
     */
    private void removeLabel(final KLabel label) {
        KLabelNode labelNode = RenderingContextData.get(label).getProperty(KLabelNode.LABEL_REP);
        if (labelNode != null) {
            // remove the label representation from the containing node
            labelNode.removeFromParent();
        }
    }

    /**
     * Applies the recorded layout changes by creating appropriate activities.
     */
    private void handleRecordedChanges() {
        // get the duration for applying the layout
        KShapeLayout shapeLayout = topNode.getGraphElement().getData(KShapeLayout.class);
        int duration;
        if (shapeLayout != null) {
            duration = shapeLayout.getProperty(DiagramLayoutManager.APPLY_LAYOUT_DURATION);
        } else {
            duration = 0;
        }

        // create activities to apply all recorded changes
        for (Map.Entry<Object, Object> recordedChange : recordedChanges.entrySet()) {
            // create the activity to apply the change
            PInterpolatingActivity activity;
            final PNode shapeNode;
            if (recordedChange.getKey() instanceof KEdgeNode) {
                // edge layout changed
                KEdgeNode edgeNode = (KEdgeNode) recordedChange.getKey();
                shapeNode = edgeNode;
                Point2D[] bends = (Point2D[]) recordedChange.getValue();
                activity = new ApplyBendPointsActivity(edgeNode, bends, duration > 0 ? duration : 1);
            } else {
                // shape layout changed
                shapeNode = (PNode) recordedChange.getKey();
                PBounds bounds = (PBounds) recordedChange.getValue();
                activity = new ApplySmartBoundsActivity(shapeNode, bounds, duration > 0 ? duration
                        : 1);
            }

            if (duration > 0) {
                // schedule the activity
                NodeUtil.schedulePrimaryActivity(shapeNode, activity);
            } else {
                // unschedule a currently running primary activity on the node if any
                NodeUtil.unschedulePrimaryActivity(shapeNode);
                // instantly apply the activity without scheduling it
                activity.setRelativeTargetValue(1.0f);
            }
        }
        recordedChanges.clear();
    }

    /**
     * Updates the bounds and translation of the node representation according to the
     * {@code KShapeLayout} of the wrapped node.
     * 
     * @param nodeNode
     *            the node representation
     */
    private void updateLayout(final KNodeNode nodeNode) {
        // Puts the KShapeLayout coordinates in the KNodeNode,
        // installs the change listener that are in charge of
        // updating the coordinates after applying the automatic layout.
        KNode node = nodeNode.getGraphElement();
        KShapeLayout shapeLayout = node.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applySmartBounds(nodeNode, shapeLayout.getXpos(), shapeLayout.getYpos(),
                    shapeLayout.getWidth(), shapeLayout.getHeight());

            if (sync) {
                installLayoutSyncAdapter(nodeNode);
            }
        }
    }

    /**
     * Updates the bounds and translation of the port representation according to the
     * {@code KShapeLayout} of the wrapped port.
     * 
     * @param portNode
     *            the port representation
     */
    private void updateLayout(final KPortNode portNode) {
        KPort port = portNode.getGraphElement();
        KShapeLayout shapeLayout = port.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applySmartBounds(portNode, shapeLayout.getXpos(), shapeLayout.getYpos(),
                    shapeLayout.getWidth(), shapeLayout.getHeight());

            if (sync) {
                installLayoutSyncAdapter(portNode);
            }
        }
    }

    /**
     * Updates the bounds and translation of the label representation according to the
     * {@code KShapeLayout} of the wrapped label.
     * 
     * @param labelNode
     *            the label representation
     */
    private void updateLayout(final KLabelNode labelNode) {
        KLabel label = labelNode.getGraphElement();
        KShapeLayout shapeLayout = label.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applySmartBounds(labelNode, shapeLayout.getXpos(), shapeLayout.getYpos(),
                    shapeLayout.getWidth(), shapeLayout.getHeight());

            if (sync) {
                installLayoutSyncAdapter(labelNode);
            }
        }
    }

    /**
     * Updates the bend points of the edge representation according to the {@code KEdgeLayout} of
     * the wrapped edge.
     * 
     * @param edgeRep
     *            the edge representation
     */
    private void updateLayout(final KEdgeNode edgeRep) {
        KEdge edge = edgeRep.getGraphElement();
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        if (edgeLayout != null) {
            Point2D[] bendPoints = getBendPoints(edgeLayout);
            edgeRep.setBendPoints(bendPoints);

            if (sync) {
                installLayoutSyncAdapter(edgeRep);
            }
        }
    }

    /**
     * Updates the rendering of the node.
     * 
     * @param nodeRep
     *            the node representation
     */
    private void updateRendering(final KNodeNode nodeRep) {
        KNodeRenderingController renderingController = (KNodeRenderingController) nodeRep
                .getAttribute(RENDERING_KEY);
        if (renderingController == null) {
            renderingController = new KNodeRenderingController(nodeRep);
            nodeRep.setChildArea(renderingController.getChildAreaNode());
            nodeRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(sync);
        } else {
            renderingController.internalUpdateRendering();
            // TODO renderingController.updateRendering(); // see AbstractRenderingController l.188
        }
    }

    /**
     * Updates the rendering of the port.
     * 
     * @param portRep
     *            the port representation
     */
    private void updateRendering(final KPortNode portRep) {
        KPortRenderingController renderingController = (KPortRenderingController) portRep
                .getAttribute(RENDERING_KEY);
        if (renderingController == null) {
            renderingController = new KPortRenderingController(portRep);
            portRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(sync);
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
        KLabelRenderingController renderingController = (KLabelRenderingController) labelRep
                .getAttribute(RENDERING_KEY);
        if (renderingController == null) {
            renderingController = new KLabelRenderingController(labelRep);
            labelRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(sync);
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
        KEdgeRenderingController renderingController = (KEdgeRenderingController) edgeRep
                .getAttribute(RENDERING_KEY);
        if (renderingController == null) {
            renderingController = new KEdgeRenderingController(edgeRep);
            edgeRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(sync);
        } else {
            renderingController.internalUpdateRendering();
        }
    }

    /**
     * Installs an adapter on the represented node to synchronize the representation with the
     * specified layout.
     * 
     * @param nodeRep
     *            the node representation
     */
    private void installLayoutSyncAdapter(final KNodeNode nodeRep) {
        final KNode node = nodeRep.getGraphElement();
        final KShapeLayout shapeLayout = node.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            // register adapter on the shape layout to stay in sync
            shapeLayout.eAdapters().add(new AdapterImpl() {
                public void notifyChanged(final Notification notification) {

                    if (record) {
                        switch (notification.getFeatureID(KShapeLayout.class)) {
                        case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                        case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                        case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                        case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                            recordedChanges.put(nodeRep, getBounds(shapeLayout));
                            break;
                        default:
                            break;
                        }
                    } else {

                        // each operation on PNodes have to be executed in the UI
                        // otherwise it will have no effect
                        // MonitoredOperation.runInUI(new Runnable() {
                        // public void run() {
                        switch (notification.getFeatureID(KShapeLayout.class)) {
                        case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS: {
                            PAffineTransform transform = nodeRep.getTransformReference(true);
                            double oldX = transform.getTranslateX();
                            nodeRep.translate(shapeLayout.getXpos() - oldX, 0);
                            break;
                        }
                        case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS: {
                            PAffineTransform transform = nodeRep.getTransformReference(true);
                            double oldY = transform.getTranslateY();
                            nodeRep.translate(0, shapeLayout.getYpos() - oldY);
                            break;
                        }
                        case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH: {
                            nodeRep.setWidth(shapeLayout.getWidth());
                            break;
                        }
                        case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT: {
                            nodeRep.setHeight(shapeLayout.getHeight());
                            break;
                        }
                        default:
                            break;
                        }
                        // }
                        // }, true);
                    }
                }
            });
        }
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
        final KShapeLayout shapeLayout = port.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            // register adapter on the shape layout to stay in sync
            shapeLayout.eAdapters().add(new AdapterImpl() {
                public void notifyChanged(final Notification notification) {
                    if (record) {
                        switch (notification.getFeatureID(KShapeLayout.class)) {
                        case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                        case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                        case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                        case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                            recordedChanges.put(portRep, getBounds(shapeLayout));
                            break;
                        default:
                            break;
                        }
                    } else {
                        // MonitoredOperation.runInUI(new Runnable() {
                        // public void run() {
                        switch (notification.getFeatureID(KShapeLayout.class)) {
                        case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS: {
                            PAffineTransform transform = portRep.getTransformReference(true);
                            double oldX = transform.getTranslateX();
                            portRep.translate(shapeLayout.getXpos() - oldX, 0);
                            break;
                        }
                        case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS: {
                            PAffineTransform transform = portRep.getTransformReference(true);
                            double oldY = transform.getTranslateY();
                            portRep.translate(0, shapeLayout.getYpos() - oldY);
                            break;
                        }
                        case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH: {
                            portRep.setWidth(shapeLayout.getWidth());
                            break;
                        }
                        case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT: {
                            portRep.setHeight(shapeLayout.getHeight());
                            break;
                        }
                        default:
                            break;
                        }
                        // }
                        // }, true);
                    }
                }
            });
        }
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
        final KShapeLayout shapeLayout = label.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            // register adapter on the shape layout to stay in sync
            shapeLayout.eAdapters().add(new AdapterImpl() {
                public void notifyChanged(final Notification notification) {
                    if (record) {
                        switch (notification.getFeatureID(KShapeLayout.class)) {
                        case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                        case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                        case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                        case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                            recordedChanges.put(labelRep, getBounds(shapeLayout));
                            break;
                        default:
                            break;
                        }
                    } else {
                        // MonitoredOperation.runInUI(new Runnable() {
                        // public void run() {
                        switch (notification.getFeatureID(KShapeLayout.class)) {
                        case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS: {
                            PAffineTransform transform = labelRep.getTransformReference(true);
                            double oldX = transform.getTranslateX();
                            labelRep.translate(shapeLayout.getXpos() - oldX, 0);
                            break;
                        }
                        case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS: {
                            PAffineTransform transform = labelRep.getTransformReference(true);
                            double oldY = transform.getTranslateY();
                            labelRep.translate(0, shapeLayout.getYpos() - oldY);
                            break;
                        }
                        case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH: {
                            labelRep.setWidth(shapeLayout.getWidth());
                            break;
                        }
                        case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT: {
                            labelRep.setHeight(shapeLayout.getHeight());
                            break;
                        }
                        default:
                            break;
                        }
                        // }
                        // }, true);
                    }
                }
            });
        }
    }

    /**
     * Installs an adapter on the represented edge to synchronize the representation with the
     * specified layout.
     * 
     * @author chsch: Method massively changed.
     * 
     * @param edgeRep
     *            the edge representation
     */
    private void installLayoutSyncAdapter(final KEdgeNode edgeRep) {
        final KEdge edge = edgeRep.getGraphElement();
        final KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        if (edgeLayout != null) {
            // register adapter on the edge layout to stay in sync
            edgeLayout.eAdapters().add(new EContentAdapter() {

                public void notifyChanged(final Notification notification) {
                    super.notifyChanged(notification);

                    Object notifier = notification.getNotifier();
                    int featureId = notification.getFeatureID(KEdgeLayout.class);
                    if (notifier instanceof KEdgeLayout
                            && (featureId == KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS
                                    || featureId == KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT
                                    || featureId == KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT)) {

                        if (record) {
                            recordedChanges.put(edgeRep, getBendPoints(edgeLayout));
                        } else {
                            edgeRep.setBendPoints(getBendPoints(edgeLayout));
                            // original:
                            // updateLayout(edgeRep);
                        }
                    }
                }
            });
        }
    }

    /**
     * Installs an adapter on the represented node to synchronize the children of the representation
     * with the specified children in the model.
     * 
     * @param nodeRep
     *            the node representation
     */
    private void installChildrenSyncAdapter(final INode nodeRep) {
        KNode node = nodeRep.getGraphElement();
        // add an adapter on the node's children
        node.eAdapters().add(new AdapterImpl() {
            public void notifyChanged(final Notification notification) {
                if (notification.getFeatureID(KNode.class) == KGraphPackage.KNODE__CHILDREN) {
                    switch (notification.getEventType()) {
                    case Notification.ADD: {
                        final KNode addedNode = (KNode) notification.getNewValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                addNode(nodeRep, addedNode);
                            }
                        });
                        break;
                    }
                    case Notification.ADD_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KNode> addedNodes = (List<KNode>) notification.getNewValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                for (KNode addedNode : addedNodes) {
                                    addNode(nodeRep, addedNode);
                                }
                            }
                        });
                        break;
                    }
                    case Notification.REMOVE: {
                        final KNode removedNode = (KNode) notification.getOldValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                removeNode(removedNode);
                            }
                        });
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KNode> removedNodes = (List<KNode>) notification.getOldValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                for (KNode removedNode : removedNodes) {
                                    removeNode(removedNode);
                                }
                            }
                        });
                        break;
                    }
                    default:
                        break;
                    }
                }
            }
        });
    }

    /**
     * Installs an adapter on the node to synchronize the incoming and outgoing edges of the
     * representation with the specified incoming and outgoing edges in the model.
     * 
     * @param node
     *            the node
     */
    private void installEdgeSyncAdapter(final KNode node) {
        RenderingContextData data = RenderingContextData.get(node);

        // remove the currently installed adapter if any
        AdapterImpl edgeSyncAdapter = data.getProperty(EDGE_SYNC_ADAPTER);
        if (edgeSyncAdapter != null) {
            node.eAdapters().remove(edgeSyncAdapter);
        }

        // create an adapter on the node's edges
        edgeSyncAdapter = new AdapterImpl() {
            public void notifyChanged(final Notification notification) {
                int featureId = notification.getFeatureID(KNode.class);
                if (featureId == KGraphPackage.KNODE__OUTGOING_EDGES
                        || featureId == KGraphPackage.KNODE__INCOMING_EDGES) {
                    switch (notification.getEventType()) {
                    case Notification.ADD: {
                        final KEdge addedEdge = (KEdge) notification.getNewValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                addEdge(addedEdge);
                            }
                        });
                        break;
                    }
                    case Notification.ADD_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KEdge> addedEdges = (List<KEdge>) notification.getNewValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                for (KEdge addedEdge : addedEdges) {
                                    addEdge(addedEdge);
                                }
                            }
                        });
                        break;
                    }
                    case Notification.REMOVE: {
                        final KEdge removedEdge = (KEdge) notification.getOldValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                removeEdge(removedEdge);
                            }
                        });
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KEdge> removedEdges = (List<KEdge>) notification.getOldValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                for (KEdge removedEdge : removedEdges) {
                                    removeEdge(removedEdge);
                                }
                            }
                        });
                        break;
                    }
                    default:
                        break;
                    }
                }
            }
        };

        // remember the adapter
        data.setProperty(EDGE_SYNC_ADAPTER, edgeSyncAdapter);

        // add the adapter
        node.eAdapters().add(edgeSyncAdapter);
    }

    /**
     * Uninstalls the edge synchronization adapter on a node.
     * 
     * @param node
     *            the node
     */
    private void uninstallEdgeSyncAdapter(final KNode node) {
        Adapter edgeSyncAdapter = RenderingContextData.get(node).getProperty(EDGE_SYNC_ADAPTER);
        if (edgeSyncAdapter != null) {
            node.eAdapters().remove(edgeSyncAdapter);
        }
    }

    /**
     * Installs an adapter on the represented node to synchronize the ports of the representation
     * with the specified ports in the model.
     * 
     * @param nodeRep
     *            the node representation
     */
    private void installPortSyncAdapter(final KNodeNode nodeRep) {
        KNode node = nodeRep.getGraphElement();
        // add an adapter on the node's ports
        node.eAdapters().add(new AdapterImpl() {
            public void notifyChanged(final Notification notification) {
                if (notification.getFeatureID(KNode.class) == KGraphPackage.KNODE__PORTS) {
                    switch (notification.getEventType()) {
                    case Notification.ADD: {
                        final KPort addedPort = (KPort) notification.getNewValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                addPort(nodeRep, addedPort);
                            }
                        });
                        break;
                    }
                    case Notification.ADD_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KPort> addedPorts = (List<KPort>) notification.getNewValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                for (KPort addedPort : addedPorts) {
                                    addPort(nodeRep, addedPort);
                                }
                            }
                        });
                        break;
                    }
                    case Notification.REMOVE: {
                        final KPort removedPort = (KPort) notification.getOldValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                removePort(removedPort);
                            }
                        });
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KPort> removedPorts = (List<KPort>) notification.getOldValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                for (KPort removedPort : removedPorts) {
                                    removePort(removedPort);
                                }
                            }
                        });
                        break;
                    }
                    default:
                        break;
                    }
                }
            }
        });
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
        // add an adapter on the labeled element's labels
        labeledElement.eAdapters().add(new AdapterImpl() {
            public void notifyChanged(final Notification notification) {
                if (notification.getFeatureID(KLabeledGraphElement.class)
                        == KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS) {
                    switch (notification.getEventType()) {
                    case Notification.ADD: {
                        final KLabel addedLabel = (KLabel) notification.getNewValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                addLabel(labeledNode, addedLabel);
                            }
                        });
                        break;
                    }
                    case Notification.ADD_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KLabel> addedLabels = (List<KLabel>) notification.getNewValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                for (KLabel addedLabel : addedLabels) {
                                    addLabel(labeledNode, addedLabel);
                                }
                            }
                        });
                        break;
                    }
                    case Notification.REMOVE: {
                        final KLabel removedLabel = (KLabel) notification.getOldValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                removeLabel(removedLabel);
                            }
                        });
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KLabel> removedLabels = (List<KLabel>) notification
                                .getOldValue();
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                for (KLabel removedLabel : removedLabels) {
                                    removeLabel(removedLabel);
                                }
                            }
                        });
                        break;
                    }
                    default:
                        break;
                    }
                }
            }
        });
    }

    /**
     * Installs an adapter on the represented label to synchronize the text of the representation
     * with the specified text in the model.
     * 
     * @param nodeRep
     *            the node representation
     */
    private void installTextSyncAdapter(final KLabelNode labelRep) {
        final KLabel node = labelRep.getGraphElement();
        // add an adapter on the node's ports
        node.eAdapters().add(new AdapterImpl() {
            public void notifyChanged(final Notification notification) {
                if (notification.getFeatureID(KLabel.class) == KGraphPackage.KLABEL__TEXT) {
                    switch (notification.getEventType()) {
                    case Notification.SET:
                        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                            public void run() {
                                labelRep.setText(node.getText());
                            }
                        });
                        break;
                    default:
                        break;
                    }
                }
            }
        });
    }

    /**
     * Returns bounds from the given {@code KShapeLayout}.
     * 
     * @param shapeLayout
     *            the shape layout
     * @return the bounds
     */
    private static PBounds getBounds(final KShapeLayout shapeLayout) {
        PBounds bounds = new PBounds();
        bounds.setRect(shapeLayout.getXpos(), shapeLayout.getYpos(), shapeLayout.getWidth(),
                shapeLayout.getHeight());
        return bounds;
    }

    /**
     * Returns an array of bend points from the given {@code KEdgeLayout}.
     * 
     * @param edgeLayout
     *            the edge layout
     * @return the bend points
     */
    private static Point2D[] getBendPoints(final KEdgeLayout edgeLayout) {
        // build the bend point array
        Point2D[] points = new Point2D[edgeLayout.getBendPoints().size() + 2];
        int i = 0;
        points[i++] = new Point2D.Double(edgeLayout.getSourcePoint().getX(), edgeLayout
                .getSourcePoint().getY());
        for (KPoint bend : edgeLayout.getBendPoints()) {
            points[i++] = new Point2D.Double(bend.getX(), bend.getY());
        }
        points[i] = new Point2D.Double(edgeLayout.getTargetPoint().getX(), edgeLayout
                .getTargetPoint().getY());
        return points;
    }

    /**
     * Finds the parent node for the edge representation and adds the edge to that node
     * representations child area.
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
            INode commonParentNode = RenderingContextData.get(commonParent).getProperty(
                    INode.NODE_REP);
            if (commonParentNode != null) {
                KChildAreaNode childAreaNode = commonParentNode.getChildArea();
                childAreaNode.addEdge(edgeRep);
            }
        }
    }

    /**
     * Updates the offset of the edge representation.
     * 
     * @param edgeNode
     *            the edge representation
     */
    public void updateEdgeOffset(final KEdgeNode edgeNode) {
        final PNode edgeNodeParent = edgeNode.getParent();
        if (edgeNodeParent != null) {
            KEdge edge = edgeNode.getGraphElement();
            KNode source = edge.getSource();
            INode sourceParentNode = RenderingContextData.get(source.getParent()).getProperty(
                    INode.NODE_REP);
            final KChildAreaNode relativeChildArea = sourceParentNode.getChildArea();

            // the listener that updates the offset
            PropertyChangeListener listener = new PropertyChangeListener() {
                public void propertyChange(final PropertyChangeEvent arg0) {
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
     * Returns the lowest common ancestor to both given nodes.
     * 
     * @param initialNode1
     *            the first node
     * @param initialNode2
     *            the second node
     * @return the lowest common ancestor
     */
    private static KNode findLowestCommonAncestor(final KNode initialNode1, final KNode initialNode2) {
        KNode node1 = initialNode1.getParent();
        while (node1 != null) {
            KNode node2 = initialNode2.getParent();
            while (node2 != null) {
                if (node1 == node2) {
                    // common ancestor found
                    return node1;
                }
                node2 = node2.getParent();
            }
            node1 = node1.getParent();
        }

        // no common ancestor
        return null;
    }

    /**
     * Removes all rendering context data from the given graph element and all child elements.
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
