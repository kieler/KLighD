/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.graph.layout;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.BiMap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.LayoutOptionManager;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.piccolo.activities.ZoomActivity;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphEdge;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphNode;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphObject;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphParent;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphPort;
import de.cau.cs.kieler.klighd.piccolo.graph.Insets;
import de.cau.cs.kieler.klighd.piccolo.krendering.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A diagram layout manager for Piccolo diagrams which utilize the interfaces from the
 * {@code de.cau.cs.kieler.klighd.piccolo.graph} package to outline the graph structure.
 * 
 * @author mri
 */
public class PiccoloDiagramLayoutManager implements IDiagramLayoutManager<IGraphObject> {

    /** the list of edges found in the graph. */
    public static final IProperty<List<IGraphEdge>> EDGES = new Property<List<IGraphEdge>>(
            "piccolo.edges");
    
    /** the list of edges containing the "noLayout=true" option found in the graph.
     * @author chsch
     * */
    public static final IProperty<List<IGraphEdge>> NO_LAYOUT_EDGES = new Property<List<IGraphEdge>>(
            "piccolo.noLayoutedges");

    /** the Piccolo attribute layout config. */
    private ILayoutConfig attributeLayoutConfig = new PiccoloAttributeLayoutConfig();

    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object object) {
        if (object instanceof DiagramViewPart) {
            DiagramViewPart view = (DiagramViewPart) object;
            return view.getViewer().getActiveViewer() instanceof PiccoloViewer;
        } else if (object instanceof ContextViewer) {
            ContextViewer contextViewer = (ContextViewer) object;
            return contextViewer.getActiveViewer() instanceof PiccoloViewer;
        } else {
            return object instanceof PiccoloViewer || object instanceof IGraphParent;
        }
    }

    /**
     * {@inheritDoc}
     */
    public LayoutMapping<IGraphObject> buildLayoutGraph(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        // choose the layout root object
        IGraphParent layoutRoot = null;
        if (diagramPart instanceof IGraphParent) {
            layoutRoot = (IGraphParent) diagramPart;
        }

        if (layoutRoot == null) {
            // find the active viewer
            PiccoloViewer viewer = null;
            if (diagramPart instanceof PiccoloViewer) {
                viewer = (PiccoloViewer) diagramPart;
            } else if (diagramPart instanceof ContextViewer) {
                ContextViewer contextViewer = (ContextViewer) diagramPart;
                IViewer<?> activeViewer = contextViewer.getActiveViewer();
                if (activeViewer instanceof PiccoloViewer) {
                    viewer = (PiccoloViewer) activeViewer;
                }
            }
            if (viewer == null) {
                if (workbenchPart instanceof DiagramViewPart) {
                    DiagramViewPart view = (DiagramViewPart) workbenchPart;
                    ContextViewer contextViewer = view.getViewer();
                    IViewer<?> activeViewer = contextViewer.getActiveViewer();
                    if (activeViewer instanceof PiccoloViewer) {
                        viewer = (PiccoloViewer) activeViewer;
                    }
                }
            }

//            if (viewer != null) {
//                // retrieve the layout root from the viewer
//                if (viewer.getDiagramContext() != null) {
//                    layoutRoot = viewer.getDiagramContext().getRootNode();
//                }
//            }
        }

        if (layoutRoot == null) {
            throw new UnsupportedOperationException(
                    "Not supported by this layout manager: Workbench part " + workbenchPart
                            + ", diagram part " + diagramPart);
        }

        // create the mapping
        LayoutMapping<IGraphObject> mapping = buildLayoutGraph(layoutRoot);
        mapping.setProperty(LayoutOptionManager.USE_ECLIPSE_LAYOUT_CONFIG, false);

        // add the Piccolo layout config
        mapping.getLayoutConfigs().add(attributeLayoutConfig);

        return mapping;
    }

    /**
     * Creates the actual mapping given the root object for the layout.
     * 
     * @param layoutRoot
     *            the root object
     * @return the layout mapping
     */
    private LayoutMapping<IGraphObject> buildLayoutGraph(final IGraphParent layoutRoot) {
        LayoutMapping<IGraphObject> mapping = new LayoutMapping<IGraphObject>();
        mapping.setProperty(EDGES, new LinkedList<IGraphEdge>());
        mapping.setProperty(NO_LAYOUT_EDGES, new LinkedList<IGraphEdge>());

        // set the parent element
        mapping.setParentElement(layoutRoot);

        KNode topNode = KimlUtil.createInitializedNode();
        KShapeLayout shapeLayout = topNode.getData(KShapeLayout.class);
        PBounds rootBounds = layoutRoot.getRelativeBounds();
        if (layoutRoot instanceof IGraphNode) {
            // start with a specific node as root for layout
            shapeLayout.setPos((float) rootBounds.x, (float) rootBounds.y);
        }
        shapeLayout.setSize((float) rootBounds.width, (float) rootBounds.height);
        mapping.getGraphMap().put(topNode, layoutRoot);
        mapping.setLayoutGraph(topNode);

        // traverse the children of the layout root
        buildLayoutGraphRecursively(mapping, layoutRoot, topNode);
        // transform all connections in the selected area
        processConnections(mapping);

        return mapping;
    }

    private void buildLayoutGraphRecursively(final LayoutMapping<IGraphObject> mapping,
            final IGraphParent parent, final KNode layoutParent) {
        // iterate through the children of the element
        for (IGraphNode node : parent.getChildren()) {
            if (shallBeLayouted(node)) {
                // chsch: test added
                createNode(mapping, node, parent, layoutParent);
            }
        }
    }

    private void createNode(final LayoutMapping<IGraphObject> mapping, final IGraphNode node,
            final IGraphParent parent, final KNode parentLayoutNode) {
        KNode childLayoutNode = KimlUtil.createInitializedNode();

        // set location and size
        PBounds bounds = node.getRelativeBounds();
        KShapeLayout nodeLayout = childLayoutNode.getData(KShapeLayout.class);
        nodeLayout.setXpos((float) bounds.x);
        nodeLayout.setYpos((float) bounds.y);
        nodeLayout.setSize((float) bounds.width, (float) bounds.height);

        // set insets if available
        Insets insets = node.getInsets();
        if (insets != null) {
            KInsets layoutInsets = nodeLayout.getInsets();
            layoutInsets.setLeft((float) insets.getLeft());
            layoutInsets.setTop((float) insets.getTop());
            layoutInsets.setRight((float) insets.getRight());
            layoutInsets.setBottom((float) insets.getBottom());
        }

        parentLayoutNode.getChildren().add(childLayoutNode);
        mapping.getGraphMap().put(childLayoutNode, node);

        // process ports
        for (IGraphPort port : node.getPorts()) {
            createPort(mapping, port, node, childLayoutNode);
        }

        // process the child as new parent
        buildLayoutGraphRecursively(mapping, node, childLayoutNode);

        // store all the edges to process them later
        for (IGraphEdge edge : node.getOutgoingEdges()) {
            // chsch: test added
            if (shallBeLayouted(edge)) {
                mapping.getProperty(EDGES).add(edge);
            } else {
                mapping.getProperty(NO_LAYOUT_EDGES).add(edge);
            }
        }

        // TODO node label
    }    
    
    /**
     * A preliminary solution for excluding elements from layout according to msp's ideas.
     * Open question: How deal with such elements if they shall by modified during applyLayout, though?
     * 
     * @author chsch
     */
    private boolean shallBeLayouted(final IGraphObject edge) {
        List<IProperty<?>> options = Arrays.asList(PiccoloAttributeLayoutConfig
                .getAffectedOptions((PNode) edge));
        for (IProperty<?> option : options) {
            LayoutOptionData<?> data = LayoutDataService.getInstance().getOptionDataBySuffix(
                    option.getId());
            if (data != null && data.getId().equals(LayoutOptions.NO_LAYOUT_ID)) {
                Object value = edge.getAttribute(data.getId());
                if (value == null) {
                    value = edge.getAttribute(data.getId().substring(
                            data.getId().lastIndexOf(".") + 1));
                }
                if (value != null && value.equals("true")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void createPort(final LayoutMapping<IGraphObject> mapping, final IGraphPort port,
            final IGraphNode node, final KNode layoutNode) {
        KPort layoutPort = KimlUtil.createInitializedPort();
        layoutPort.setNode(layoutNode);

        // set the port's layout, relative to the node position
        KShapeLayout portLayout = layoutPort.getData(KShapeLayout.class);
        PBounds bounds = port.getRelativeBounds();
        portLayout.setPos((float) bounds.x, (float) bounds.y);
        portLayout.setSize((float) bounds.width, (float) bounds.height);

        mapping.getGraphMap().put(layoutPort, port);

        // TODO port label
    }

    private void processConnections(final LayoutMapping<IGraphObject> mapping) {
        BiMap<IGraphObject, KGraphElement> graphMap = mapping.getGraphMap().inverse();

        // iterate through the list of collected edges
        for (IGraphEdge edge : mapping.getProperty(EDGES)) {
            KEdge layoutEdge = KimlUtil.createInitializedEdge();

            // find source and target (and ports)
            KNode layoutSource = (KNode) graphMap.get(edge.getSource());
            KNode layoutTarget = (KNode) graphMap.get(edge.getTarget());
            KPort layoutSourcePort = (KPort) graphMap.get(edge.getSourcePort());
            KPort layoutTargetPort = (KPort) graphMap.get(edge.getTargetPort());
            if (layoutSource == null || layoutTarget == null) {
                continue;
            }

            // set layout data
            layoutEdge.setSource(layoutSource);
            layoutEdge.setTarget(layoutTarget);
            if (layoutSourcePort != null) {
                layoutEdge.setSourcePort(layoutSourcePort);
            }
            if (layoutTargetPort != null) {
                layoutEdge.setTargetPort(layoutTargetPort);
            }

            // transfer bend points
            // FIXME does not take compound nodes into account yet
            KEdgeLayout edgeLayout = layoutEdge.getData(KEdgeLayout.class);
            List<Point2D> bends = edge.getBends();
            Point2D sourcePoint = bends.get(0);
            Point2D targetPoint = bends.get(bends.size() - 1);
            edgeLayout.getSourcePoint().setPos((float) sourcePoint.getX(),
                    (float) sourcePoint.getY());
            edgeLayout.getTargetPoint().setPos((float) targetPoint.getX(),
                    (float) targetPoint.getY());
            for (int i = 1; i < bends.size() - 1; ++i) {
                Point2D bendPoint = bends.get(i);
                KPoint layoutBendPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                layoutBendPoint.setPos((float) bendPoint.getX(), (float) bendPoint.getY());
                edgeLayout.getBendPoints().add(layoutBendPoint);
            }

            mapping.getGraphMap().put(layoutEdge, edge);

            // TODO edge labels
        }
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final LayoutMapping<IGraphObject> mapping, final boolean zoomToFit,
            final int animationTime) {
        // apply the layout using a Piccolo activity
        ApplyLayoutActivity applyLayoutActivity = new ApplyLayoutActivity(mapping, animationTime);
        if (animationTime > 0) {
            if (mapping.getParentElement() instanceof PNode) {
                // schedule the activity
                PNode rootNode = (PNode) mapping.getParentElement();
                rootNode.addActivity(applyLayoutActivity);
            }
        } else {
            // instantly apply the layout
            applyLayoutActivity.apply();
        }
        if (zoomToFit) {
            applyZoomToFit(mapping, animationTime);
        }
    }

    /**
     * Schedules a zoom activity which performs the zoom-to-fit using the information from the given
     * layout mapping.
     * 
     * @param mapping
     *            the layout mapping
     * @param animation
     *            time the time over which to apply the zoom-to-fit
     */
    @SuppressWarnings("unchecked")
    private void applyZoomToFit(final LayoutMapping<IGraphObject> mapping, final int animationTime) {
        if (mapping.getParentElement() instanceof PNode) {
            PNode parentNode = (PNode) mapping.getParentElement();
            // find the root node
            PRoot rootNode = parentNode.getRoot();
            if (rootNode != null) {
                // find the camera
                PCamera camera = null;
                for (PNode child : (List<PNode>) rootNode.getChildrenReference()) {
                    if (child instanceof PCamera) {
                        camera = (PCamera) child;
                        break;
                    }
                }
                // schedule a zoom activity if a camera has been found
                if (camera != null) {
                    // compute the required scale
                    KShapeLayout shapeLayout = mapping.getLayoutGraph().getData(KShapeLayout.class);
                    PBounds bounds = camera.getViewBounds();
                    PAffineTransform transform = camera.getViewTransformReference();
                    double normalizedWidth = bounds.width * transform.getScale();
                    double normalizedHeight = bounds.height * transform.getScale();
                    double scaleX = normalizedWidth / shapeLayout.getWidth();
                    double scaleY = normalizedHeight / shapeLayout.getHeight();
                    double scale = Math.min(scaleX, scaleY);
                    // schedule a zoom activity to apply the scaling
                    ZoomActivity zoomActivity = new ZoomActivity(camera, scale, animationTime);
                    if (animationTime > 0) {
                        parentNode.addActivity(zoomActivity);
                    } else {
                        // instantly apply the zoom-to-fit
                        zoomActivity.apply();
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public IMutableLayoutConfig getLayoutConfig() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void undoLayout(final LayoutMapping<IGraphObject> mapping) {
        throw new UnsupportedOperationException("Undo is not supported by the Piccolo layout manager.");
    }

}
