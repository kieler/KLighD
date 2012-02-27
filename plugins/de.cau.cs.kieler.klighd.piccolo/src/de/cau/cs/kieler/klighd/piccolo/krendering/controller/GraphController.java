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
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.util.KGraphSwitch;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
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
import edu.umd.cs.piccolo.util.PAffineTransform;

/**
 * The class which controls the transformation of a KGraph with attached KRendering data to Piccolo
 * nodes and the synchronization of these Piccolo nodes with the KGraph model.
 * 
 * @author mri
 */
public class GraphController {

    /** the property for identifying whether a node has been populated. */
    private static final IProperty<Boolean> POPULATED = new Property<Boolean>("klighd.populated",
            false);

    /** the attribute key for the rendering. */
    private static final Object RENDERING_KEY = new Object();

    /** the Piccolo node representing the top node in the graph. */
    private KNodeTopNode topNode;

    /** whether to sync the representation with the graph model. */
    private boolean sync = false;
    /** whether to record layout changes. */
    private boolean record = false;

    /** the set of nodes with changed layout while recording. */
    private Set<KNodeNode> recordedNodes = Sets.newLinkedHashSet();
    /** the set of ports with changed layout while recording. */
    private Set<KPortNode> recordedPorts = Sets.newLinkedHashSet();
    /** the set of labels with changed layout while recording. */
    private Set<KLabelNode> recordedLabels = Sets.newLinkedHashSet();
    /** the set of edges with changed layout while recording. */
    private Set<KEdgeNode> recordedEdges = Sets.newLinkedHashSet();

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
     */
    public GraphController(final KNode graph, final PNode parent, final boolean sync) {
        resetGraphElement(graph);
        this.topNode = new KNodeTopNode(graph);
        parent.addChild(topNode);
        this.sync = sync;
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
     * Starts recording layout changes in the graph.
     */
    public void startRecording() {
        record = true;
    }

    /**
     * Stops recording layout changes in the graph.
     */
    public void stopRecording() {
        record = false;
        // TODO handle recorded layout changes
    }

    /**
     * Initializes the graph controller.
     */
    public void initialize() {
        // expand the top node
        expand(topNode.getWrapped());
    }

    /**
     * Expandes the Piccolo node representing the given node.
     * 
     * @param node
     *            the node
     */
    public void expand(final KNode node) {
        RenderingContextData data = RenderingContextData.get(node);
        INode nodeRep = data.getProperty(INode.NODE_REP);
        if (nodeRep != null) {
            boolean populated = data.getProperty(POPULATED);
            // populate the node if necessary
            if (!populated) {
                handleChildren(nodeRep);
                data.setProperty(POPULATED, true);
            }

            // set the child area to expanded
            nodeRep.getChildArea().setExpanded(true);
        }
    }

    /**
     * Collapses the Piccolo node representing the given node.
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
     * Handles the children of the parent node.
     * 
     * @param parentNode
     *            the parent node
     */
    private void handleChildren(final INode parentNode) {
        KNode parent = parentNode.getWrapped();

        // create the nodes
        for (KNode child : parent.getChildren()) {
            addNode(parentNode, child);
        }

        // create the edges
        for (KNode child : parent.getChildren()) {
            for (KEdge edge : child.getOutgoingEdges()) {
                handleEdge(edge);
            }
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
     */
    private void addNode(final INode parent, final KNode node) {
        INode nodeRep = RenderingContextData.get(node).getProperty(INode.NODE_REP);

        KNodeNode nodeNode;
        if (nodeRep instanceof KNodeTopNode) {
            // if the node is the current top-node something went wrong
            throw new RuntimeException("The top-node can never be made a child node");
        } else {
            nodeNode = (KNodeNode) nodeRep;
        }

        // if there is no Piccolo representation for the node create it
        if (nodeNode == null) {
            nodeNode = new KNodeNode(node, parent);
            updateLayout(nodeNode);
            updateRendering(nodeNode);
            handlePorts(nodeNode);
            // TODO handle labels
            
            if (sync) {
                installLayoutSyncAdapter(nodeNode);
            }
        }

        // add the node
        parent.getChildArea().addNode(nodeNode);
    }

    /**
     * Removes the representation for the node from its parent.
     * 
     * @param node
     *            the node
     */
    private void removeNode(final KNode node) {
        INode nodeRep = RenderingContextData.get(node).getProperty(INode.NODE_REP);

        KNodeNode nodeNode;
        if (nodeRep instanceof KNodeTopNode) {
            // if the node is the current top-node something went wrong
            throw new RuntimeException("The top-node can never be removed from a parent node");
        } else {
            nodeNode = (KNodeNode) nodeRep;
        }

        // remove the node representation from the containing child area
        nodeNode.removeFromParent();
    }

    /**
     * Adds representations for the ports attached to the node to the node's representation.
     * 
     * @param nodeNode
     *            the node representation
     */
    private void handlePorts(final KNodeNode nodeNode) {
        KNode node = nodeNode.getWrapped();
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
            
            if (sync) {
                installLayoutSyncAdapter(portNode);
            }
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

        // remove the port representation from the containing node
        portNode.removeFromParent();
    }

    /**
     * Adds a representation for the label to the given labeled node.
     * 
     * @param labeledNode
     *            the labeled node
     * @param label
     *            the label
     */
    private void addLabel(final ILabeledGraphElement labeledNode, final KLabel label) {
        KLabelNode labelNode = RenderingContextData.get(label).getProperty(KLabelNode.LABEL_REP);

        // if there is no Piccolo representation for the label create it
        if (labelNode == null) {
            labelNode = new KLabelNode(label);
            updateLayout(labelNode);
            updateRendering(labelNode);
            
            if (sync) {
                installLayoutSyncAdapter(labelNode);
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

        // remove the label representation from the containing node
        labelNode.removeFromParent();
    }

    /**
     * Handles the creation of the given edge if necessary.
     * 
     * @param edge
     *            the edge
     */
    private void handleEdge(final KEdge edge) {
        RenderingContextData data = RenderingContextData.get(edge);
        KEdgeNode edgeRep = data.getProperty(KEdgeNode.EDGE_REP);

        // if there is no Piccolo representation for the edge create it
        if (edgeRep == null) {
            edgeRep = new KEdgeNode(edge);
            updateLayout(edgeRep);
            updateRendering(edgeRep);
            
            if (sync) {
                installLayoutSyncAdapter(edgeRep);
            }
        }

        // find and set the parent for the edge
        updateEdgeParent(edgeRep);

        // update the offset of the edge layout to the containg child area
        // TODO implement this + cross hierarchy sync
    }

    /**
     * Updates the bounds and translation of the node representation according to the
     * {@code KShapeLayout} of the wrapped node.
     * 
     * @param nodeRep
     *            the Piccolo node representation
     */
    private void updateLayout(final KNodeNode nodeRep) {
        KNode node = nodeRep.getWrapped();
        KShapeLayout shapeLayout = node.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applySmartBounds(nodeRep, shapeLayout.getXpos(), shapeLayout.getYpos(),
                    shapeLayout.getWidth(), shapeLayout.getHeight());
        }
    }

    /**
     * Updates the bounds and translation of the port representation according to the
     * {@code KShapeLayout} of the wrapped port.
     * 
     * @param portRep
     *            the Piccolo port representation
     */
    private void updateLayout(final KPortNode portRep) {
        KPort port = portRep.getWrapped();
        KShapeLayout shapeLayout = port.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applySmartBounds(portRep, shapeLayout.getXpos(), shapeLayout.getYpos(),
                    shapeLayout.getWidth(), shapeLayout.getHeight());
        }
    }
    
    /**
     * Updates the bounds and translation of the label representation according to the
     * {@code KShapeLayout} of the wrapped label.
     * 
     * @param labelRep
     *            the Piccolo label representation
     */
    private void updateLayout(final KLabelNode labelRep) {
        KLabel label = labelRep.getWrapped();
        KShapeLayout shapeLayout = label.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            NodeUtil.applySmartBounds(labelRep, shapeLayout.getXpos(), shapeLayout.getYpos(),
                    shapeLayout.getWidth(), shapeLayout.getHeight());
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
        KEdge edge = edgeRep.getWrapped();
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        if (edgeLayout != null) {
            Point2D[] bendPoints = getBendPoints(edgeLayout);
            edgeRep.setBendPoints(bendPoints);
        }
    }

    /**
     * Updates the rendering of the node.
     * 
     * @param nodeRep
     *            the node representation
     */
    private void updateRendering(final KNodeNode nodeRep) {
        RenderingController renderingController = (RenderingController) nodeRep
                .getAttribute(RENDERING_KEY);
        if (renderingController == null) {
            renderingController = new RenderingController(nodeRep);
            nodeRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(sync);
        } else {
            renderingController.updateRendering();
        }
    }

    /**
     * Updates the rendering of the port.
     * 
     * @param portRep
     *            the port representation
     */
    private void updateRendering(final KPortNode portRep) {
        RenderingController renderingController = (RenderingController) portRep
                .getAttribute(RENDERING_KEY);
        if (renderingController == null) {
            renderingController = new RenderingController(portRep);
            portRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(sync);
        } else {
            renderingController.updateRendering();
        }
    }
    
    /**
     * Updates the rendering of the label.
     * 
     * @param label
     *            the label representation
     */
    private void updateRendering(final KLabelNode labelRep) {
        RenderingController renderingController = (RenderingController) labelRep
                .getAttribute(RENDERING_KEY);
        if (renderingController == null) {
            renderingController = new RenderingController(labelRep);
            labelRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(sync);
        } else {
            renderingController.updateRendering();
        }
    }

    /**
     * Updates the rendering of the edge.
     * 
     * @param edgeRep
     *            the edge representation
     */
    private void updateRendering(final KEdgeNode edgeRep) {
        RenderingController renderingController = (RenderingController) edgeRep
                .getAttribute(RENDERING_KEY);
        if (renderingController == null) {
            renderingController = new RenderingController(edgeRep);
            edgeRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(sync);
        } else {
            renderingController.updateRendering();
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
        KNode node = nodeRep.getWrapped();
        final KShapeLayout shapeLayout = node.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            // register adapter on the shape layout to stay in sync
            shapeLayout.eAdapters().add(new AdapterImpl() {
                public void notifyChanged(final Notification notification) {
                    MonitoredOperation.runInUI(new Runnable() {
                        public void run() {
                            if (record) {
                                switch (notification.getFeatureID(KShapeLayout.class)) {
                                case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                                case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                                case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                                case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                                    recordedNodes.add(nodeRep);
                                    break;
                                }
                            } else {
                                switch (notification.getFeatureID(KShapeLayout.class)) {
                                case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS: {
                                    PAffineTransform transform = nodeRep
                                            .getTransformReference(true);
                                    double oldX = transform.getTranslateX();
                                    nodeRep.translate(shapeLayout.getXpos() - oldX, 0);
                                    break;
                                }
                                case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS: {
                                    PAffineTransform transform = nodeRep
                                            .getTransformReference(true);
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
                                }
                            }
                        }
                    }, false);
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
        KPort node = portRep.getWrapped();
        final KShapeLayout shapeLayout = node.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            // register adapter on the shape layout to stay in sync
            shapeLayout.eAdapters().add(new AdapterImpl() {
                public void notifyChanged(final Notification notification) {
                    MonitoredOperation.runInUI(new Runnable() {
                        public void run() {
                            if (record) {
                                switch (notification.getFeatureID(KShapeLayout.class)) {
                                case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                                case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                                case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                                case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                                    recordedPorts.add(portRep);
                                    break;
                                }
                            } else {
                                switch (notification.getFeatureID(KShapeLayout.class)) {
                                case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS: {
                                    PAffineTransform transform = portRep
                                            .getTransformReference(true);
                                    double oldX = transform.getTranslateX();
                                    portRep.translate(shapeLayout.getXpos() - oldX, 0);
                                    break;
                                }
                                case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS: {
                                    PAffineTransform transform = portRep
                                            .getTransformReference(true);
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
                                }
                            }
                        }
                    }, false);
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
        KLabel node = labelRep.getWrapped();
        final KShapeLayout shapeLayout = node.getData(KShapeLayout.class);
        if (shapeLayout != null) {
            // register adapter on the shape layout to stay in sync
            shapeLayout.eAdapters().add(new AdapterImpl() {
                public void notifyChanged(final Notification notification) {
                    MonitoredOperation.runInUI(new Runnable() {
                        public void run() {
                            if (record) {
                                switch (notification.getFeatureID(KShapeLayout.class)) {
                                case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                                case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                                case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                                case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                                    recordedLabels.add(labelRep);
                                    break;
                                }
                            } else {
                                switch (notification.getFeatureID(KShapeLayout.class)) {
                                case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS: {
                                    PAffineTransform transform = labelRep
                                            .getTransformReference(true);
                                    double oldX = transform.getTranslateX();
                                    labelRep.translate(shapeLayout.getXpos() - oldX, 0);
                                    break;
                                }
                                case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS: {
                                    PAffineTransform transform = labelRep
                                            .getTransformReference(true);
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
                                }
                            }
                        }
                    }, false);
                }
            });
        }
    }

    /**
     * Installs an adapter on the represented edge to synchronize the representation with the
     * specified layout.
     * 
     * @param edgeRep
     *            the edge representation
     */
    private void installLayoutSyncAdapter(final KEdgeNode edgeRep) {
        KEdge edge = edgeRep.getWrapped();
        final KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        if (edgeLayout != null) {
            // register adapter on the edge layout to stay in sync
            edgeLayout.eAdapters().add(new EContentAdapter() {
                public void notifyChanged(final Notification notification) {
                    super.notifyChanged(notification);
                    Object notifier = notification.getNotifier();
                    int featureId = notification.getFeatureID(KEdgeLayout.class);
                    if ((notifier instanceof KEdgeLayout
                            && (featureId == KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS
                            || featureId == KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT
                            || featureId == KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT))
                            || notifier instanceof KPoint) {
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                if (record) {
                                    recordedEdges.add(edgeRep);
                                } else {
                                    updateLayout(edgeRep);
                                }
                            }
                        }, false);
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
        KNode node = nodeRep.getWrapped();
        // add an adapter on the node's children
        node.eAdapters().add(new AdapterImpl() {
            public void notifyChanged(final Notification notification) {
                if (notification.getFeatureID(KNode.class) == KGraphPackage.KNODE__CHILDREN) {
                    switch (notification.getEventType()) {
                    case Notification.ADD: {
                        final KNode addedNode = (KNode) notification.getNewValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                addNode(nodeRep, addedNode);
                            }
                        }, false);
                        break;
                    }
                    case Notification.ADD_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KNode> addedNodes = (List<KNode>) notification.getNewValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                for (KNode addedNode : addedNodes) {
                                    addNode(nodeRep, addedNode);
                                }
                            }
                        }, false);
                        break;
                    }
                    case Notification.REMOVE: {
                        final KNode removedNode = (KNode) notification.getOldValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                removeNode(removedNode);
                            }
                        }, false);
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KNode> removedNodes = (List<KNode>) notification.getOldValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                for (KNode removedNode : removedNodes) {
                                    removeNode(removedNode);
                                }
                            }
                        }, false);
                        break;
                    }
                    }
                }
            }
        });
    }
    
    /**
     * Installs an adapter on the represented node to synchronize the  ports of the representation
     * with the specified ports in the model.
     * 
     * @param nodeRep
     *            the node representation
     */
    private void installPortSyncAdapter(final KNodeNode nodeRep) {
        KNode node = nodeRep.getWrapped();
        // add an adapter on the node's ports
        node.eAdapters().add(new AdapterImpl() {
            public void notifyChanged(final Notification notification) {
                if (notification.getFeatureID(KNode.class) == KGraphPackage.KNODE__PORTS) {
                    switch (notification.getEventType()) {
                    case Notification.ADD: {
                        final KPort addedPort = (KPort) notification.getNewValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                addPort(nodeRep, addedPort);
                            }
                        }, false);
                        break;
                    }
                    case Notification.ADD_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KPort> addedPorts = (List<KPort>) notification.getNewValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                for (KPort addedPort : addedPorts) {
                                    addPort(nodeRep, addedPort);
                                }
                            }
                        }, false);
                        break;
                    }
                    case Notification.REMOVE: {
                        final KPort removedPort = (KPort) notification.getOldValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                removePort(removedPort);
                            }
                        }, false);
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KPort> removedPorts = (List<KPort>) notification.getOldValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                for (KPort removedPort : removedPorts) {
                                    removePort(removedPort);
                                }
                            }
                        }, false);
                        break;
                    }
                    }
                }
            }
        });
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
        points[i++] = new Point2D.Float(edgeLayout.getSourcePoint().getX(), edgeLayout
                .getSourcePoint().getY());
        for (KPoint bend : edgeLayout.getBendPoints()) {
            points[i++] = new Point2D.Float(bend.getX(), bend.getY());
        }
        points[i] = new Point2D.Float(edgeLayout.getTargetPoint().getX(), edgeLayout
                .getTargetPoint().getY());
        return points;
    }

    /**
     * Finds the parent node for edge and adds the edge to that nodes child area.
     * 
     * @param edgeRep
     *            the edge representation
     * @return the parent node representation
     */
    private INode updateEdgeParent(final KEdgeNode edgeRep) {
        KEdge edge = edgeRep.getWrapped();
        KNode source = edge.getSource();
        KNode target = edge.getTarget();
        if (source != null && target != null) {
            KNode commonParent = findLowestCommonAncestor(source, target);
            INode commonParentNode = RenderingContextData.get(commonParent).getProperty(
                    INode.NODE_REP);
            if (commonParentNode != null) {
                KChildAreaNode childAreaNode = commonParentNode.getChildArea();
                childAreaNode.addEdge(edgeRep);
                return commonParentNode;
            } else {
                // this should never happen
                throw new RuntimeException("Parent representation for edge is null");
            }
        }
        return null;
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
