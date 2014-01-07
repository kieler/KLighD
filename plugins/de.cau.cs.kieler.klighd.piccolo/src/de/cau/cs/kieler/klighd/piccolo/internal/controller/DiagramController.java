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
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
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
import de.cau.cs.kieler.klighd.util.LimitedKGraphContentAdapter;
import de.cau.cs.kieler.klighd.util.ModelingUtil;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PDimension;

/**
 * Overall manager of KGraph+KRendering-based diagrams.<br>
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

    /**
     * Property name of edge layout listeners updating the edge node. Listeners are attached to edge
     * nodes via this name allowing to detach them from the edge layout if the edge is removed from
     * the KGraph.
     * 
     * @author chsch
     */
    private static final String K_EDGE_LAYOUT_LISTENER = "KEdgeLayoutListener";

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
    private final KNodeTopNode topNode;
    
    /** the main camera that determines the actually drawn picture. */
    private final KlighdMainCamera canvasCamera;

    /** whether to sync the representation with the graph model. */
    private boolean sync = false;

    /** whether to record layout changes, will be set to true by the KlighdLayoutManager. */
    private boolean record = false;

    /** type of zoom style applied after layout. */
    private ZoomStyle zoomStyle = ZoomStyle.NONE;
    
    /** duration of a possible animation. */
    private int animationTime = 0;
    
    private KNode focusNode = null;
    
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
        
        canvasCamera.getRoot().addChild(topNode);
        canvasCamera.setDisplayedNode(topNode);
        
        // updateLayout(topNode);
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
     * Starts to record layout changes in the model instead of instantly applying them to the
     * visualization.<br>
     * <br>
     * Executing {@link #stopRecording(ZoomStyle, int)} applies all recorded layout changes.
     */
    public void startRecording() {
        record = true;
    }

    /**
     * @param theZoomStyle
     *            the style used to zoom, eg zoom to fit or zoom to focus
     * @param theAnimationTime
     *            duration of the animated layout
     * 
     * @see de.cau.cs.kieler.klighd.IViewer IViewer#stopRecording(ZoomStyle, int)
     */
    public void stopRecording(final ZoomStyle theZoomStyle, final int theAnimationTime) {
        if (record) {
            zoomStyle = theZoomStyle;
            animationTime = theAnimationTime;

            record = false;

            // apply recorded layout changes
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
        
        focusNode = node;
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
        
        focusNode = node;
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
        
        focusNode = node;
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
        switch (style) {
        case ZOOM_TO_FIT:
            zoomToFit(duration);
            break;
        case ZOOM_TO_FOCUS:
            KNode focus = focusNode != null ? focusNode : topNode.getGraphElement();
            zoomToFocus(focus, duration);
            break;
        default:
            // nothing
        }
    }
    
    /**
     * @param duration
     *            time to animate
     */
    private void zoomToFit(final int duration) {
        final KNode displayedKNode = this.canvasCamera.getDisplayedINode().getGraphElement(); 
        final KShapeLayout topNodeLayout = displayedKNode.getData(KShapeLayout.class);
        
        if (topNodeLayout == null) {
            String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to fit' as the topNode's layout data are unavailable. "
                    + "This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }
        
        final PBounds newBounds = new PBounds(topNodeLayout.getXpos(), topNodeLayout.getYpos(),
                            topNodeLayout.getWidth(), topNodeLayout.getHeight());

        includePortAndLabelBounds(newBounds, displayedKNode);
        
        if (this.canvasCamera.getBoundsReference().isEmpty()) {
            // this case occurs while initializing the DiagramEditorPart
            //  since the whole diagram building and layout is performed within 'createPartControl()'
            // at that time, the (widget) layout of the KlighdCanvas has not been performed and,
            //  thus, the root pnodes' bounds are empty
            // this setting will be replaced by 'setBounds()' in KlighdCanvas (inherited)  
            this.canvasCamera.setBounds(newBounds);
        } else {
            this.canvasCamera.animateViewToCenterBounds(newBounds, true, duration);
        }
    }
    
    /**
     * This method checks for ports and labels of the given <code>node</code> and increases the
     * given <code>nodeBounds</code> accordingly.
     */
    private Rectangle2D includePortAndLabelBounds(final Rectangle2D nodeBounds, final KNode node) {
        double maxX = nodeBounds.getWidth();
        double maxY = nodeBounds.getHeight();
        
        // these min values are <= 0 at all times!
        double minX = 0;
        double minY = 0;

        boolean includedElement = false;
        
        for (KGraphElement element : Iterables.concat(node.getPorts(), node.getLabels())) {
            final KShapeLayout pL = element.getData(KShapeLayout.class);
            if (pL.getXpos() < minX) {
                minX = pL.getXpos();
            }
            if (pL.getYpos() < minY) {
                minY = pL.getYpos();
            }
            if (pL.getXpos() + pL.getWidth() > maxX) {
                maxX = pL.getXpos() + pL.getWidth();
            }
            if (pL.getYpos() + pL.getHeight() > maxY) {
                maxY = pL.getYpos() + pL.getHeight();
            }
            includedElement = true;
        }
        
        if (includedElement) {
            nodeBounds.setRect(nodeBounds.getX() + minX, nodeBounds.getY() + minY, maxX - minX,
                    maxY - minY);
        } else {
            final KInsets insets = node.getData(KShapeLayout.class).getInsets();
            nodeBounds.setRect(nodeBounds.getX() + insets.getLeft(),
                    nodeBounds.getY() + insets.getTop(),
                    maxX - insets.getLeft() - insets.getRight(),
                    maxY - insets.getTop() - insets.getBottom());
        }

        return nodeBounds;
    }
    
    /**
     * 
     * @param focus
     *            the desired focus bounds
     * @param duration
     *            duration of the animation
     */
    private void zoomToFocus(final KNode focus, final int duration) {
        final KShapeLayout shapeLayout = focus.getData(KShapeLayout.class);
        final PBounds newBounds = new PBounds(shapeLayout.getXpos(), shapeLayout.getYpos(),
                shapeLayout.getWidth(), shapeLayout.getHeight());

        // we need the bounds in view coordinates (absolute), hence for
        // a knode add the translations of all parent nodes
        KNode parent = focus.getParent();
        while (parent != null) {
            KShapeLayout parentLayout = parent.getData(KShapeLayout.class);
            newBounds.moveBy(parentLayout.getXpos(), parentLayout.getYpos());
            parent = parent.getParent();
        }

        zoomToFocus(newBounds, duration);
    }

    /**
     * 
     * @param focus
     *            the desired focus bounds
     * @param duration
     *            duration of the animation
     */
    private void zoomToFocus(final PBounds focus, final int duration) {
        final PBounds viewBounds = canvasCamera.getViewBounds();

        // check if we need to scale the view in order for the view to
        // contain the whole focus
        boolean scale = viewBounds.getWidth() < focus.getWidth()
                || viewBounds.getHeight() < focus.getHeight();

        // fetch bounds of the whole diagram
        final KShapeLayout topNodeLayout = topNode.getGraphElement().getData(KShapeLayout.class);
        
        final PBounds newBounds = new PBounds(topNodeLayout.getXpos(), topNodeLayout.getYpos(),
                topNodeLayout.getWidth(), topNodeLayout.getHeight());
        
        boolean fullyContains = viewBounds.getWidth() > newBounds.getWidth()
                && viewBounds.getHeight() > newBounds.getHeight();

        // if the viewport can fully accomodate the diagram, we perform zoom to fit 
        if (fullyContains) {
            canvasCamera.animateViewToCenterBounds(newBounds, true, duration);
        } else {
            canvasCamera.animateViewToCenterBounds(focus, scale, duration);
        }
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
        final KShapeLayout topNodeLayout = topNode.getGraphElement().getData(KShapeLayout.class);

        if (topNodeLayout == null) {
            final String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to one' as the topNode's layout data are unavailable. "
                    + "This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }

        final PBounds nodeBounds = new PBounds(topNodeLayout.getXpos(), topNodeLayout.getYpos(),
                        topNodeLayout.getWidth(), topNodeLayout.getHeight());

        // it would be possible to use PCamera#scaleViewAboutPoint(scale, x, y), 
        // however this method does not allow for animation
        
        // calculate the bound as they would be if scaled by the new factor
        final PBounds origBounds = canvasCamera.getViewBounds();
        final double oldZoomLevel = canvasCamera.getViewTransformReference().getScale();
        
        final PBounds newBounds = new PBounds(origBounds.x, origBounds.y,
                origBounds.width * oldZoomLevel / newZoomLevel,
                origBounds.height * oldZoomLevel / newZoomLevel);

        // add the necessary translation
        final double normalizedWidth = origBounds.width * oldZoomLevel;
        final double normalizedHeight = origBounds.height * oldZoomLevel;
        final double transX = (origBounds.width - normalizedWidth / newZoomLevel) / 2f;
        final double transY = (origBounds.height - normalizedHeight / newZoomLevel) / 2f;
        
        newBounds.moveBy(transX, transY);

        // make sure at least some of the diagram is visible after zooming to scale 1
        final PDimension dim = newBounds.deltaRequiredToContain(nodeBounds);
        newBounds.moveBy(dim.width, dim.height);

        // perform the animation
        canvasCamera.animateViewToCenterBounds(newBounds, true, duration);
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
                                nodeNode.getRenderingController().initialize(true);
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
        INode nodeRep = RenderingContextData.get(node).getProperty(REP);

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
                RenderingContextData.get(node).setProperty(REP, nodeNode);
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
                parent.getChildAreaNode().addNode(nodeNode);
                RenderingContextData.get(node).setProperty(KlighdInternalProperties.ACTIVE, true);

                KGraphData data = node.getData(KLayoutDataPackage.eINSTANCE.getKLayoutData());
                boolean expand = data == null || data.getProperty(KlighdProperties.EXPAND);
                // in case the EXPAND property is not set the default value 'true' is returned
                nodeNode.getChildAreaNode().setExpanded(expand);
                
            } else {
                if (record && isAutomaticallyArranged(node)) {
                    nodeNode.setVisible(false);
                }

                // add the node
                parent.getChildAreaNode().addNode(nodeNode);
                RenderingContextData.get(node).setProperty(KlighdInternalProperties.ACTIVE, true);

                // touch the expansion state, see the methods javadoc for details
                nodeNode.getChildAreaNode().touchExpanded();
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
        INode nodeRep = RenderingContextData.get(node).getProperty(REP);
        if (nodeRep != null) {
            KNodeNode nodeNode;
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
                
            if (sync) {
                uninstallEdgeSyncAdapter(node);
            }

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
                // release the node representation from the node's renderingContextData
                RenderingContextData.get(node).setProperty(REP, null);
            }
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
        // only add the representation if it is not added already
        if (edgeNode == null || edgeNode.getParent() == null) {
            if (edgeNode == null) {
                KNode source = edge.getSource();
                KNode target = edge.getTarget();
                if (source != null
                        && target != null
                        && RenderingContextData.get(source).getProperty(
                                KlighdInternalProperties.ACTIVE)
                        && RenderingContextData.get(target).getProperty(
                                KlighdInternalProperties.ACTIVE)) {
                    // if there is no Piccolo representation for the edge create it
                    if (edgeNode == null) {
                        edgeNode = new KEdgeNode(edge);
                        RenderingContextData.get(edge).setProperty(EDGE_REP, edgeNode);
                        if (record && isAutomaticallyArranged(edge)) {
                            // this avoids flickering and denotes the application of fade-in,
                            //  see #handleRecordedChanges()
                            edgeNode.setVisible(false);
                        }
                        updateLayout(edgeNode);
                        updateRendering(edgeNode);
                        handleLabels(edgeNode, edge);
                    }

                    // the following is needed in case of interlevel edges:
                    //  edges ending in an outer child area will be clipped by the inner childArea;
                    //  the clipping is generally intended and is realized by KChildAreaNode

                    // find and set the parent for the edge
                    updateEdgeParent(edgeNode);
                    RenderingContextData.get(edge).setProperty(KlighdInternalProperties.ACTIVE,
                            true);

                    // update the offset of the edge layout to the containing child area
                    updateEdgeOffset(edgeNode);
                }
            } else {
                if (record && isAutomaticallyArranged(edge)) {
                    edgeNode.setVisible(false);
                }

                // see comments above
                // find and set the parent for the edge
                updateEdgeParent(edgeNode);
                RenderingContextData.get(edge).setProperty(KlighdInternalProperties.ACTIVE, true);

                // update the offset of the edge layout to the containing child area
                updateEdgeOffset(edgeNode);
            }
        }
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
            RenderingContextData.get(edge).setProperty(KlighdInternalProperties.ACTIVE, false);
            
            // due to #removeNode() this method might be performed multiple times so: 
            if (releaseControllers && edgeNode.getRenderingController() != null) {
                // release the objects kept in mind
                edgeNode.getRenderingController().removeAllPNodeControllers();
                // release the node rendering controller
                edgeNode.setRenderingController(null);
                // release the edge representation from the edge's renderingContextData
                RenderingContextData.get(edge).setProperty(EDGE_REP, null);
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
        KPortNode portNode = RenderingContextData.get(port).getProperty(PORT_REP);

        // if there is no Piccolo representation for the port create it
        if (portNode == null || portNode.getParent() == null) {
            if (portNode == null) {
                portNode = new KPortNode(port);
                RenderingContextData.get(port).setProperty(PORT_REP, portNode);
                
                updateLayout(portNode);
                updateRendering(portNode);
                handleLabels(portNode, port);
            }

            if (record && isAutomaticallyArranged(port)) {
                // this avoids flickering and denotes the application of fade-in,
                //  see #handleRecordedChanges()
                portNode.setVisible(false);
            }

            // add the port
            parent.addPort(portNode);
            RenderingContextData.get(port).setProperty(KlighdInternalProperties.ACTIVE, true);
        }
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
        if (portNode != null) {
            // remove the port representation from the containing node
            portNode.removeFromParent();
            RenderingContextData.get(port).setProperty(KlighdInternalProperties.ACTIVE, false);

            if (releaseControllers) {
                // release the objects kept in mind
                portNode.getRenderingController().removeAllPNodeControllers();
                // release the node rendering controller
                portNode.setRenderingController(null);
                // release the port representation from the port's renderingContextData
                RenderingContextData.get(port).setProperty(PORT_REP, null);
            }
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

        // if there is no Piccolo representation for the label create it
        if (labelNode == null || labelNode.getParent() == null) {
            if (labelNode == null) {
                labelNode = new KLabelNode(label);
                RenderingContextData.get(label).setProperty(LABEL_REP, labelNode);
                
                labelNode.setText(label.getText());
                updateLayout(labelNode);
                updateRendering(labelNode);

                if (sync) {
                    installTextSyncAdapter(labelNode);
                }
            }

            if (record && isAutomaticallyArranged(label)) {
                // this avoids flickering and denotes the application of fade-in,
                //  see #handleRecordedChanges()
                labelNode.setVisible(false);
            }
            
            // add the label
            labeledNode.addLabel(labelNode);
            RenderingContextData.get(label).setProperty(KlighdInternalProperties.ACTIVE, true);
        }
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
        if (labelNode != null) {
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
    }


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
                
                if (!shapeNode.getVisible()) {
                    // the visibility is set to false for newly introduced edges in #addNode,
                    //  #addPort, and #addLabel for avoiding unnecessary flickering and indicating
                    //  to fade it in
                    activity = new FadeNodeInActivity(shapeNode, bounds,
                            animationTime > 0 ? animationTime : 1);
                } else { 
                    activity = new ApplySmartBoundsActivity(shapeNode, bounds,
                            animationTime > 0 ? animationTime : 1);
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
     * Updates the bounds and translation of the node representation according to the
     * {@code KShapeLayout} of the wrapped node.
     * 
     * @param nodeNode
     *            the node representation
     */
    @SuppressWarnings("unused")
    private void updateLayout(final KNodeTopNode nodeNode) {
        // Puts the KShapeLayout coordinates in the KNodeTopNode,
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
    private void installLayoutSyncAdapter(final KNodeTopNode nodeRep) {
        final KNode node = nodeRep.getGraphElement();

        // register adapter on the node to stay in sync
        node.eAdapters().add(new KGEShapeLayoutPNodeUpdater(nodeRep));
    }

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
            
            final KShapeLayout shL;
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
                            recordedChanges.put(edgeRep, Pair.of(
                                    getBendPoints(edL, renderedAsPolyline), getJunctionPoints(edL)));
                        } else {
                            edgeRep.setBendPoints(getBendPoints(edL, renderedAsPolyline));
                            edgeRep.setJunctionPoints(getJunctionPoints(edL));
                            
                            final KEdgeRenderingController controller = edgeRep.getRenderingController();
                            if (controller != null) {
                                controller.modifyStyles();
                            }
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
                        removeEdge(removedEdge, true);
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KEdge> removedEdges = (List<KEdge>) notification.getOldValue();

                        for (KEdge removedEdge : removedEdges) {
                            removeEdge(removedEdge, true);
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
     * Returns an array of junction points from the given {@code KEdgeLayout}.
     * 
     * @param edgeLayout
     *            the edge layout
     * @return the junction points or an empty Point2D[] if none exist
     */
    private static Point2D[] getJunctionPoints(final KEdgeLayout edgeLayout) {

        final KVectorChain junctionPoints = edgeLayout.getProperty(LayoutOptions.JUNCTION_POINTS);
        
        if (junctionPoints == null || junctionPoints.isEmpty()) {
            return new Point2D[0];
        }
        
        // build the bend point array
        Point2D[] points = new Point2D[junctionPoints.size()];
        int i = 0;
        for (KVector bend : junctionPoints) {
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
