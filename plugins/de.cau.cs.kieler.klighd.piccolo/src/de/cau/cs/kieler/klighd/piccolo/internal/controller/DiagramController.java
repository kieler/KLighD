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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.ApplyBendPointsActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.ApplySmartBoundsActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.FadeEdgeInActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.FadeNodeInActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.activities.IStartingAndFinishingActivity;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.ILabeledGraphElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.INode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KChildAreaNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KPortNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.util.Iterables2;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.util.LimitedKGraphContentAdapter;
import de.cau.cs.kieler.klighd.util.ModelingUtil;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;
import edu.umd.cs.piccolo.util.PBounds;


/**
 * The class which controls the transformation of a KGraph with attached KRendering data to Piccolo
 * nodes and the synchronization of these Piccolo nodes with the KGraph model.
 * 
 * @author mri
 * @author chsch
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class DiagramController {

    /**
     * Property name of edge layout listeners updating the edge node. Listeners are attached to edge
     * nodes via this name allowing to detach the from the edge layout if the edge is removed from
     * the KGraph.
     * 
     * @author chsch
     */
    private static final String K_EDGE_LAYOUT_LISTENER = "KEdgeLayoutListener";

    /** the property for the Piccolo representation of a node. */
    public static final IProperty<PNode> REP = new Property<PNode>("klighd.piccolo.prepresentation");
    
    /** the property for remembering the edge sync adapter on a node. */
    private static final IProperty<AdapterImpl> CHILDREN_SYNC_ADAPTER = new Property<AdapterImpl>(
            "klighd.childrenSyncAdapter");

    /** the property for remembering the edge sync adapter on a node. */
    private static final IProperty<AdapterImpl> EDGE_SYNC_ADAPTER = new Property<AdapterImpl>(
            "klighd.edgeSyncAdapter");

    /** the attribute key for the edge offset listeners. */
    private static final Object EDGE_OFFSET_LISTENER_KEY = new Object();
    /** the attribute key for the nodes listed by edge offset listeners. */
    private static final Object EDGE_OFFSET_LISTENED_KEY = new Object();

    /** the Piccolo node representing the top node in the graph. */
    private KNodeTopNode topNode;

    /** whether to sync the representation with the graph model. */
    private boolean sync = false;

    /** whether to record layout changes, will be set to true by the KlighdLayoutManager. */
    private boolean record = false;

    /** whether to perform 'zoom to fit' while applying the layout. */
    private boolean zoomToFit = false;

    /** the layout changes to graph elements while recording. */
    private Map<PNode, Object> recordedChanges = Maps.newLinkedHashMap();

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
    public DiagramController(final KNode graph, final PNode parent, final boolean sync) {
        resetGraphElement(graph);
        this.topNode = new KNodeTopNode(graph);
        RenderingContextData.get(graph).setProperty(INode.NODE_REP, topNode);
        parent.addChild(topNode);
        this.sync = sync;
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
     * Instructs controller to perform 'zoom to fit' after the layout has been applied.
     * 
     * @param zoomToFit
     *            true if 'zoom to fit' should be applied.
     * @author chsch
     */
    public void setZoomToFit(final boolean zoomToFit) {
        this.zoomToFit = zoomToFit;
    }

    /**
     * Initializes the graph controller.
     */
    public void initialize() {
        addExpansionListener(topNode);

        RenderingContextData.get(topNode.getGraphElement()).setProperty(KlighdInternalProperties.ACTIVE,
                true);

        // expand the top node
        topNode.getChildArea().setExpanded(true);
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
            // set the child area to be collapsed
            nodeRep.getChildArea().setExpanded(false);
        }
    }

    /**
     * Expands the representation of the given node.
     * 
     * @param node
     *            the node
     */
    public void expand(final KNode node) {
        INode nodeRep = RenderingContextData.get(node).getProperty(INode.NODE_REP);
        if (nodeRep != null) {
            // set the child area to be expanded
            nodeRep.getChildArea().setExpanded(true);
        }
    }
    
    /**
     * @param node
     *            the node
     * @return true if this node is expanded.
     */
    public boolean isExpanded(final KNode node) {
        INode nodeRep = RenderingContextData.get(node).getProperty(INode.NODE_REP);
        if (nodeRep != null) {
            return nodeRep.getChildArea().isExpanded();
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
        INode nodeRep = RenderingContextData.get(node).getProperty(INode.NODE_REP);
        if (nodeRep != null) {
            // set the child area to expanded
            nodeRep.getChildArea().setExpanded(!nodeRep.getChildArea().isExpanded());
        }
    }

    /**
     * 
     * @param duration
     *            time to animate
     */
    public void zoomToFit(final int duration) {
        if (topNode.getParent() instanceof PLayer) {
            KShapeLayout topNodeLayout = topNode.getGraphElement().getData(KShapeLayout.class);
            // chsch: I don't like this exploit of implicit knowledge!
            // Would an API change be reasonable here?
            // (leads to worse class structure, less encapsulation)
            PCamera camera = ((PLayer) topNode.getParent()).getCamera(0);
            PBounds newBounds = new PBounds(topNodeLayout.getXpos(), topNodeLayout.getYpos(),
                    topNodeLayout.getWidth(), topNodeLayout.getHeight());
            camera.animateViewToCenterBounds(newBounds, true, duration);
        }
    }

    /* --------------------------------------------- */
    /* internal part */
    /* --------------------------------------------- */

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
                                nodeNode.getRenderingController().initialize(true);
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
    private void addChildren(final INode parentNode) {
        KNode parent = parentNode.getGraphElement();

        if (parent.getChildren().isEmpty()) {
            // Look whether a URI is attached to the node's shape layout
            //  this currently indicates externalized child elements that
            //  are to be loaded and translated lazily, which has to be done now!
            URI uri = parent.getData(KShapeLayout.class)
                    .getProperty(KlighdProperties.CHILD_URI);
            
            KNode result = null;
            if (uri != null) {
                try {
                    Resource res = new ResourceSetImpl().getResource(uri, true);
                    EObject model = res.getContents().get(0);
                    ViewContext vc = LightDiagramServices.getInstance().createViewContext(model,
                        new KlighdSynthesisProperties().useSimpleUpdateStrategy());
                    LightDiagramServices.getInstance().updateViewContext(vc, model);
                    res.unload();
                    result = (KNode) vc.getViewModel();
                } catch (Exception e) {
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, "Lazy-loading failed"));
                }
            }
            if (result != null && !result.getChildren().isEmpty()) {
                result = result.getChildren().get(0);
                parent.getChildren().addAll(result.getChildren());
            }
        }

        // create the nodes
        for (KNode child : parent.getChildren()) {
            addNode(parentNode, child);
        }

        RenderingContextData.get(parent).setProperty(KlighdInternalProperties.POPULATED, true);

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
        //  note that this condition implies that invisible children's children
        //  that are still contained in there parent will break the recursion
        //  of this method
        if (nodeNode == null || nodeNode.getParent() == null) {
            // if there is no Piccolo representation for the node create it
            if (nodeNode == null) {
                nodeNode = new KNodeNode(node, parent);
                RenderingContextData.get(node).setProperty(INode.NODE_REP, nodeNode);
                if (record && isAutomaticallyArranged(node)) {
                    // this avoids flickering and denotes the application of fade-in,
                    //  see #handleRecordedChanges()
                    nodeNode.setVisible(false);
                }
                updateLayout(nodeNode);
                updateRendering(nodeNode);
                handlePorts(nodeNode);
                handleLabels(nodeNode, node);
                addExpansionListener(nodeNode);

                // add the node
                parent.getChildArea().addNode(nodeNode);
                RenderingContextData.get(node).setProperty(KlighdInternalProperties.ACTIVE, true);

                KGraphData data = node.getData(KLayoutDataPackage.eINSTANCE.getKShapeLayout());
                boolean expand = data == null || data.getProperty(KlighdProperties.EXPAND);
                // in case the EXPAND property is not set the default value 'true' is returned
                nodeNode.getChildArea().setExpanded(expand);
                
            } else {
                if (record && isAutomaticallyArranged(node)) {
                    nodeNode.setVisible(false);
                }

                // add the node
                parent.getChildArea().addNode(nodeNode);
                RenderingContextData.get(node).setProperty(KlighdInternalProperties.ACTIVE, true);

                // touch the expansion state, see the methods javadoc for details
                nodeNode.getChildArea().touchExpanded();
            }

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
            removeNode(child);
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

            // remove the node representation from the containing child area
            nodeNode.removeFromParent();
            RenderingContextData.get(node).setProperty(KlighdInternalProperties.ACTIVE, false);
            
            // release the objects kept in mind
            nodeNode.getRenderingController().removeAllPNodeControllers();
            // release the node rendering controller
            // nodeNode.setRenderingController(null);
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
        if (edgeRep == null) {
            KNode source = edge.getSource();
            KNode target = edge.getTarget();
            if (source != null && target != null
                    && RenderingContextData.get(source).getProperty(KlighdInternalProperties.ACTIVE)
                    && RenderingContextData.get(target).getProperty(KlighdInternalProperties.ACTIVE)) {
                // if there is no Piccolo representation for the edge create it
                if (edgeRep == null) {
                    edgeRep = new KEdgeNode(edge);
                    if (record && isAutomaticallyArranged(edge)) {
                        // this avoids flickering and denotes the application of fade-in,
                        //  see #handleRecordedChanges()
                        edgeRep.setVisible(false);
                    }
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
        } else {
            
            // find and set the parent for the edge
            updateEdgeParent(edgeRep);

            // update the offset of the edge layout to the containing child area
            updateEdgeOffset(edgeRep);

            if (record && isAutomaticallyArranged(edge)) {
                edgeRep.setVisible(false);
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
            
            // due to #deactivateSubgraph() this method will be performed multiple times so: 
            if (edgeNode.getRenderingController() != null) {
                // release the objects kept in mind
                edgeNode.getRenderingController().removeAllPNodeControllers();
                // release the node rendering controller
                edgeNode.setRenderingController(null);
            }
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
            if (record && isAutomaticallyArranged(port)) {
                // this avoids flickering and denotes the application of fade-in,
                //  see #handleRecordedChanges()
                portNode.setVisible(false);
            }
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
            // release the objects kept in mind
            portNode.getRenderingController().removeAllPNodeControllers();
            // release the node rendering controller
            portNode.setRenderingController(null);
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
            if (record) {
                // this avoids flickering and denotes the application of fade-in,
                //  see #handleRecordedChanges()
                labelNode.setVisible(false);
            }
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
            if (labelNode.getRenderingController() != null) {
                // TODO (chsch) Why may the rendering controller be 'null' here? 
                // release the objects kept in mind
                labelNode.getRenderingController().removeAllPNodeControllers();
                // release the node rendering controller
                labelNode.setRenderingController(null);
            }
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
            duration = shapeLayout.getProperty(KlighdInternalProperties.APPLY_LAYOUT_DURATION);
        } else {
            duration = 0;
        }

        // create activities to apply all recorded changes
        for (Map.Entry<PNode, Object> recordedChange : recordedChanges
                .entrySet()) {
            // create the activity to apply the change
            PInterpolatingActivity activity;
            final PNode shapeNode;
            if (recordedChange.getKey() instanceof KEdgeNode) {
                // edge layout changed
                KEdgeNode edgeNode = (KEdgeNode) recordedChange.getKey();
                shapeNode = edgeNode;
                Point2D[] bends = (Point2D[]) recordedChange.getValue();                
                // Point2D[] curBends = (Point2D[]) edgeNode.getBendPoints();                

                if (!edgeNode.getVisible()) {
                    // the visibility is set to false for newly introduced edges in #addEdge
                    //  for avoiding unnecessary flickering and indicating to fade it in
                    activity = new FadeEdgeInActivity(edgeNode, bends, duration > 0 ? duration : 1);
                } else {
                    activity = new ApplyBendPointsActivity(edgeNode, bends, duration > 0 ? duration : 1);
                }
            } else {
                // shape layout changed
                shapeNode = (PNode) recordedChange.getKey();
                PBounds bounds = (PBounds) recordedChange.getValue();
                
                if (!shapeNode.getVisible()) {
                    // the visibility is set to false for newly introduced edges in #addNode,
                    //  #addPort, and #addLabel for avoiding unnecessary flickering and indicating
                    //  to fade it in
                    activity = new FadeNodeInActivity(shapeNode, bounds,
                            duration > 0 ? duration : 1);
                } else { 
                    activity = new ApplySmartBoundsActivity(shapeNode, bounds,
                            duration > 0 ? duration : 1);
                }
            }
            if (duration > 0) {
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

        if (this.zoomToFit) {
            zoomToFit(duration);
        }
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
        KRendering rendering = edge.getData(KRendering.class);
        boolean renderedAsPolyline = rendering instanceof KPolyline
                && !(rendering instanceof KSpline);

        if (edgeLayout != null) {
            Point2D[] bendPoints = getBendPoints(edgeLayout, renderedAsPolyline);
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
        KNodeRenderingController renderingController = nodeRep.getRenderingController();
        if (renderingController == null) {
            // the new rendering controller is attached to nodeRep in the constructor of
            //  AbstractRenderingController
            renderingController = new KNodeRenderingController(nodeRep);
            nodeRep.setChildArea(renderingController.getChildAreaNode());
            // nodeRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(sync);
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
        KLabelRenderingController renderingController = labelRep.getRenderingController();
        if (renderingController == null) {
            // the new rendering controller is attached to nodeRep in the constructor of
            //  AbstractRenderingController
            renderingController = new KLabelRenderingController(labelRep);
            // labelRep.addAttribute(RENDERING_KEY, renderingController);
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
        KEdgeRenderingController renderingController = edgeRep.getRenderingController();
        if (renderingController == null) {
            // the new rendering controller is attached to nodeRep in the constructor of
            //  AbstractRenderingController
            renderingController = new KEdgeRenderingController(edgeRep);
            // edgeRep.addAttribute(RENDERING_KEY, renderingController);
            renderingController.initialize(sync);
        } else {
            renderingController.internalUpdateRendering();
        }
    }


    // ---------------------------------------------------------------------------------- //
    //  Layout data synchronization

    /**
     * Installs an adapter on the represented node to synchronize new shape layouts with specified
     * layout.
     * 
     * @param nodeRep
     *            the node representation
     */
    private void installLayoutSyncAdapter(final KNodeNode nodeRep) {
        final KNode node = nodeRep.getGraphElement();

        // register adapter on the node to stay in sync
        node.eAdapters().add(new KGEShapeLayoutPNodeUpdater(nodeRep));
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

        // register adapter on the port to stay in sync
        port.eAdapters().add(new KGEShapeLayoutPNodeUpdater(portRep));
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
        
        // register adapter on the label to stay in sync
        label.eAdapters().add(new KGEShapeLayoutPNodeUpdater(labelRep));
    }

    /**
     * A specialized {@link LimitedKGraphContentAdapter}, which is in charge of synchronizing the
     * position the PNode that represents of the {@link KGraphElement} that this adapter is attached
     * to. It is intended to be attached to {@link KNode KNodes}, {@link KPort KPorts}, and
     * {@link KLabel KLabels}.<br>
     * <br>
     * Due to the fact that EMF Compare's standard mergers replace whole instances of
     * {@link KShapeLayout} when some of the attribute values have changed, such updaters are
     * attached to the {@link KGraphElement}. They propagate themselves to the available
     * {@link KShapeLayout KShapeLayouts} or those that are added afterwards.
     * 
     * @author chsch
     */
    private class KGEShapeLayoutPNodeUpdater extends LimitedKGraphContentAdapter {
        
        public KGEShapeLayoutPNodeUpdater(final PNode theRepNode) {
            super(KShapeLayout.class);
            this.nodeRep = theRepNode;
        }
        
        private PNode nodeRep = null;
        
        @Override 
        public void notifyChanged(final Notification notification) {
            super.notifyChanged(notification);

            switch (notification.getEventType()) {
            case Notification.ADD:
            case Notification.SET:
                // good cases - continue executing this method
                break;                
            case Notification.ADD_MANY:
            case Notification.MOVE:
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
            case Notification.REMOVING_ADAPTER:
            case Notification.RESOLVE:
            case Notification.UNSET:
            default:
                // uninteresting cases - stop executing here
                return;
            }
            
            KShapeLayout shL = null;
            if (notification.getNotifier() instanceof KNode
                    && notification.getNewValue() instanceof KShapeLayout) {
                shL = (KShapeLayout) notification.getNewValue();

            } else if (notification.getNotifier() instanceof KShapeLayout
                    && notification.getNewValue() instanceof Number) {
                
                switch (((EStructuralFeature) notification.getFeature()).getFeatureID()) {
                case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                    break;
                default:
                    return;
                }
                
                shL = (KShapeLayout) notification.getNotifier();
                
            } else {
                return;
            }
            
            if (record) {
                recordedChanges.put(nodeRep, getBounds(shL));
                return;

            } else {
                final Point2D offset = nodeRep.getOffset();
                
                switch (notification.getFeatureID(KShapeLayout.class)) {
                case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS: {
                    double oldX = offset.getX();
                    double newX = shL.getXpos();
                    if (newX != oldX) {
                        nodeRep.setOffset(newX, offset.getY());
                    }
                    break;
                }
                case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS: {
                    double oldY = offset.getY();
                    double newY = shL.getYpos();
                    if (newY != oldY) {
                        nodeRep.setOffset(offset.getX(), newY);
                    }
                    break;
                }
                case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH: {
                    double oldWidth = nodeRep.getWidth();
                    double newWidth = shL.getWidth();
                    if (oldWidth != newWidth) {
                        nodeRep.setWidth(newWidth);
                    }
                    break;
                }
                case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT: {
                    double oldHeight = nodeRep.getHeight();
                    double newHeight = shL.getHeight();
                    if (oldHeight != newHeight) {
                        nodeRep.setHeight(newHeight);
                    }
                    break;
                }
                default:
                    break;
                }

                final AbstractKGERenderingController<?, ?> controller = NodeUtil.asIGraphElement(
                        nodeRep).getRenderingController();
                if (controller != null) {
                    controller.modifyStyles();
                }
            }
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
        final KRendering rendering = edge.getData(KRendering.class);
        final boolean renderedAsPolyline = rendering instanceof KPolyline
                && !(rendering instanceof KSpline);

        // register adapter on the edge to stay in sync
        edge.eAdapters().add(new LimitedKGraphContentAdapter(KEdgeLayout.class) {

            public void notifyChanged(final Notification notification) {
                super.notifyChanged(notification);

                Object notifier = notification.getNotifier();
                int featureId = notification.getFeatureID(KEdgeLayout.class);
                if (notifier instanceof KEdgeLayout
                        && (featureId == KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS
                                || featureId == KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT 
                                || featureId == KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT)
                        || notifier instanceof KPoint
                        && (featureId == KLayoutDataPackage.KPOINT__X 
                                || featureId == KLayoutDataPackage.KPOINT__Y)) {

                    KEdgeLayout edL = edge.getData(KEdgeLayout.class);
                    // check if a edge layout is exists
                    if (edL != null) {
                        if (record) {
                            recordedChanges.put(edgeRep, getBendPoints(edL, renderedAsPolyline));
                        } else {
                            edgeRep.setBendPoints(getBendPoints(edL, renderedAsPolyline));
                        }
                    }
                }
            }
        });
    }


    // ---------------------------------------------------------------------------------- //
    //  KGraphElement data synchronization

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
                        removeNode(removedNode);

                        // Removing all contained nodes is required to remove all outgoing or
                        //  incoming edges, as in case of interlevel ones their representing
                        //  KEdgeNodes are attached to one of n's parent representatives, which might
                        //  be one of removedNode's parent representatives.
                        for (KNode n : Iterables2.toIterable(Iterators.filter(
                                removedNode.eAllContents(), KNode.class))) {
                            removeNode(n);
                        }
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KNode> removedNodes = (List<KNode>) notification.getOldValue();

                        for (KNode removedNode : removedNodes) {
                            removeNode(removedNode);

                            // Removing all contained nodes is required to remove all outgoing or
                            //  incoming edges, as in case of interlevel ones their representing
                            //  KEdgeNodes are attached to one of n's parent representatives, which might
                            //  be one of removedNode's parent representatives.
                            for (KNode n : Iterables2.toIterable(Iterators.filter(
                                    removedNode.eAllContents(), KNode.class))) {
                                removeNode(n);
                            }
                        }
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
     * Uninstalls the children synchronization adapter on a node.
     * 
     * @param node
     *            the node
     */
    private void uninstallChildrenSyncAdapter(final KNode node) {
        Adapter childrenSyncAdapter = RenderingContextData.get(node).getProperty(CHILDREN_SYNC_ADAPTER);
        if (childrenSyncAdapter != null) {
            node.eAdapters().remove(childrenSyncAdapter);
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
                        removeEdge(removedEdge);
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KEdge> removedEdges = (List<KEdge>) notification.getOldValue();

                        for (KEdge removedEdge : removedEdges) {
                            removeEdge(removedEdge);
                        }
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
                        removePort(removedPort);
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KPort> removedPorts = (List<KPort>) notification.getOldValue();

                        for (KPort removedPort : removedPorts) {
                            removePort(removedPort);
                        }
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
                        removeLabel(removedLabel);
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KLabel> removedLabels = (List<KLabel>) notification
                                .getOldValue();

                        for (KLabel removedLabel : removedLabels) {
                            removeLabel(removedLabel);
                        }
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
                        labelRep.setText(node.getText());
                        break;
                    default:
                        break;
                    }
                }
            }
        });
    }


    // ---------------------------------------------------------------------------------- //
    //  Helper methods

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
     * @param renderedAsPolyline
     *            true if the edge is rendered by a polyline, causes approximation of the bend
     *            points if the layouter returned spline-based ones
     * @return the bend points
     */
    private static Point2D[] getBendPoints(final KEdgeLayout edgeLayout,
            final boolean renderedAsPolyline) {

        // chsch: the following 8 lines for approximating spline connections are mainly taken
        // from de.cau.cs.kieler.kiml.gmf.GmfLayoutEditPolicy#getBendPoints()
        KVectorChain bendPoints = edgeLayout.createVectorChain();

        // for connections that support splines the control points are passed without change
        boolean layoutedAsSpline = edgeLayout.getProperty(LayoutOptions.EDGE_ROUTING)
                == EdgeRouting.SPLINES;
        // in other cases an approximation is used // SUPPRESS CHECKSTYLE NEXT MagicNumber
        if (renderedAsPolyline && layoutedAsSpline && bendPoints.size() >= 4) {
            bendPoints = KielerMath.approximateSpline(bendPoints);
        }

        // build the bend point array
        Point2D[] points = new Point2D[bendPoints.size()];
        int i = 0;
        for (KVector bend : bendPoints) {
            points[i++] = new Point2D.Double(bend.x, bend.y);
        }
        return points;
    }

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
            INode commonParentNode = RenderingContextData.get(commonParent).getProperty(
                    INode.NODE_REP);
            if (commonParentNode != null) {
                KChildAreaNode childAreaNode = commonParentNode.getChildArea();
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
        final PNode edgeNodeParent = edgeNode.getParent();
        if (edgeNodeParent != null) {
            KEdge edge = edgeNode.getGraphElement();
            // chsch: change due to KIELER-1988; // SUPPRESS CHECKSTYLE NEXT 3 LineLength
            // edges uses different reference points as indicated by
            // http://rtsys.informatik.uni-kiel.de/~kieler/files/documentation/klayoutdata-reference-points.png
            // see page
            // http://rtsys.informatik.uni-kiel.de/confluence/display/KIELER/KLayoutData+Meta+Model
            INode sourceParentNode = RenderingContextData.get(determineReferenceNodeOf(edge))
                    .getProperty(INode.NODE_REP);
            final KChildAreaNode relativeChildArea = sourceParentNode.getChildArea();

            // chsch: The following listener updates the offset of the edge depending the parent
            // nodes.
            // It is attached to all parent nodes that are part of the containment hierarchy,
            // i.e. PSWTAdvancedPaths, KChildAreas, KNodeNodes, ...!
            // The listener is sensitive to changes to the 'transform' of those elements.
            // It is important, in case of the change of a parent KNode's rendering,
            // that its related KChildAreaNode is contained in any other PNode!
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
     * http://rtsys.informatik
     * .uni-kiel.de/~kieler/files/documentation/klayoutdata-reference-points.png see page
     * http://rtsys.informatik.uni-kiel.de/confluence/display/KIELER/KLayoutData+Meta+Model.
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
}
