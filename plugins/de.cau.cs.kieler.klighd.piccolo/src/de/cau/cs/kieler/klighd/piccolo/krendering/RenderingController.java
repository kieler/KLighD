/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
/**
 * 
 */
package de.cau.cs.kieler.klighd.piccolo.krendering;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.krendering.KBackgroundColor;
import de.cau.cs.kieler.core.krendering.KBackgroundVisibility;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KDirectPlacementData;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KFontBold;
import de.cau.cs.kieler.core.krendering.KFontItalic;
import de.cau.cs.kieler.core.krendering.KFontName;
import de.cau.cs.kieler.core.krendering.KFontSize;
import de.cau.cs.kieler.core.krendering.KForegroundColor;
import de.cau.cs.kieler.core.krendering.KForegroundVisibility;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KHorizontalAlignment;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KLineStyle;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KPlacement;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPolylinePlacementData;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KStackPlacement;
import de.cau.cs.kieler.core.krendering.KStackPlacementData;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KVerticalAlignment;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.krendering.util.KRenderingSwitch;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klighd.piccolo.nodes.PAlignmentNode;
import de.cau.cs.kieler.klighd.piccolo.nodes.PAlignmentNode.HAlignment;
import de.cau.cs.kieler.klighd.piccolo.nodes.PAlignmentNode.VAlignment;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath.LineStyle;
import de.cau.cs.kieler.klighd.piccolo.util.NodeUtil;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolox.swt.PSWTText;

/**
 * The class which controls the transformation to Piccolo nodes and synchronization with the model
 * of KRendering data attached to nodes.
 * 
 * @author mri
 */
public class RenderingController {

    /** the property for caching propagated styles in renderings. */
    private static final IProperty<List<KStyle>> PROPAGATED_STYLES = new Property<List<KStyle>>(
            "de.cau.cs.kieler.klighd.piccolo.propagatedStyles", new LinkedList<KStyle>());
    /** the property for a rendering node's controller. */
    private static final IProperty<PNodeController<?>> CONTROLLER = new Property<PNodeController<?>>(
            "de.cau.cs.kieler.klighd.piccolo.controller");

    /** the graph element which rendering is controlled by this controller. */
    private KLabeledGraphElement element;
    /** the rendering currently in use by this controller. */
    private KRendering currentRendering;

    /** the Piccolo node representing the node. */
    private PNode repNode;
    /** the Piccolo node representing the rendering. */
    private PNode renderingNode = null;
    /** the Piccolo node representing the child area. */
    private KChildAreaNode childAreaNode = null;

    /** the adapter currently installed on the rendering. */
    private AdapterImpl renderingAdapter = null;

    /**
     * Constructs a rendering controller for a node.
     * 
     * @param node
     *            the Piccolo node representing a graph node
     */
    public RenderingController(final KNodeNode node) {
        this.element = node.getWrapped();
        this.repNode = node;
        this.childAreaNode = new KChildAreaNode(node);
        initializeRenderingNode(childAreaNode);
    }

    /**
     * Constructs a rendering controller for an edge.
     * 
     * @param edge
     *            the Piccolo node representing a graph edge
     */
    public RenderingController(final KEdgeNode edge) {
        this.element = edge.getWrapped();
        this.repNode = edge;
    }

    /**
     * Initializes the rendering controller.
     */
    public void initialize() {
        // do the initial update of the rendering
        updateRendering();

        // register an adapter on the element to stay in sync
        registerElementAdapter();
    }

    /**
     * Updates the rendering by removing the current rendering and evaluating the rendering data
     * attached to the graph element.
     */
    public void updateRendering() {
        // remove the rendering adapter
        if (currentRendering != null) {
            unregisterRenderingAdapter();
        }

        // remove the rendering node
        if (renderingNode != null) {
            removeListener(renderingNode);
            renderingNode.removeFromParent();
            renderingNode = null;
        }

        // detach child area
        if (childAreaNode != null) {
            childAreaNode.removeFromParent();
        }

        // evaluate the rendering data
        currentRendering = element.getData(KRendering.class);
        if (currentRendering != null) {
            if (repNode instanceof KNodeNode || repNode instanceof KPortNode) {
                // controller manages a node or a port
                renderingNode = handleDirectPlacementRendering(currentRendering,
                        new ArrayList<KStyle>(0), repNode);
            } else if (repNode instanceof KEdgeNode) {
                // controller manages an edge
                renderingNode = handleEdgeRendering(currentRendering, (KEdgeNode) repNode);
            }

            // register an adapter on the element to stay in sync
            registerRenderingAdapter();
        } else {
            if (repNode instanceof KNodeNode || repNode instanceof KPortNode) {
                // controller manages a node or a port
                renderingNode = createDefaultNodeRendering(repNode);
            } else if (repNode instanceof KEdgeNode) {
                // controller manages an edge
                renderingNode = createDefaultEdgeRendering((KEdgeNode) repNode);
            }
        }

        // make sure the child area is attached to something in case of a node
        if (repNode instanceof KNodeNode && childAreaNode.getParent() == null) {
            createDefaultChildArea(renderingNode);
        }
    }

    /**
     * Returns the Piccolo node representing the child area.
     * 
     * @return the Piccolo node representing the child area
     */
    public KChildAreaNode getChildAreaNode() {
        return childAreaNode;
    }

    /**
     * Registers an adapter on the current rendering to react on changes.
     */
    private void registerRenderingAdapter() {
        // register adapter on the rendering to stay in sync
        renderingAdapter = new EContentAdapter() {
            public void notifyChanged(final Notification msg) {
                super.notifyChanged(msg);
                switch (msg.getEventType()) {
                case Notification.SET:
                case Notification.UNSET:
                case Notification.MOVE:
                case Notification.ADD:
                case Notification.ADD_MANY:
                case Notification.REMOVE:
                case Notification.REMOVE_MANY:
                    // handle style changes
                    if (msg.getNotifier() instanceof KStyle) {
                        // exclude opposite relation
                        if (msg.getFeatureID(KStyle.class) == KRenderingPackage.KSTYLE__RENDERING) {
                            return;
                        }
                        KStyle style = (KStyle) msg.getNotifier();
                        final KRendering rendering = style.getRendering();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                // update the styles
                                updateStyles(rendering);
                            }
                        }, false);
                        return;
                    }

                    // handle new, moved and removed styles
                    if (msg.getNotifier() instanceof KRendering
                            && msg.getFeatureID(KRendering.class)
                            == KRenderingPackage.KRENDERING__STYLES) {
                        final KRendering rendering = (KRendering) msg.getNotifier();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                // update the styles
                                updateStyles(rendering);
                            }
                        }, false);
                        return;
                    }

                    // handle other changes by reevaluating the rendering
                    MonitoredOperation.runInUI(new Runnable() {
                        public void run() {
                            // update the rendering
                            updateRendering();
                        }
                    }, false);
                }
            }
        };

        // add the adapter to the rendering
        currentRendering.eAdapters().add(renderingAdapter);
    }

    /**
     * Unregisters the adapter currently installed on the rendering.
     */
    private void unregisterRenderingAdapter() {
        if (currentRendering != null && renderingAdapter != null) {
            currentRendering.eAdapters().remove(renderingAdapter);
            renderingAdapter = null;
        }
    }

    /**
     * Registers an adapter on the graph element to react on changes in its graph data feature.
     */
    private void registerElementAdapter() {
        element.eAdapters().add(new AdapterImpl() {
            public void notifyChanged(final Notification msg) {
                if (msg.getFeatureID(KGraphElement.class) == KGraphPackage.KGRAPH_ELEMENT__DATA) {
                    switch (msg.getEventType()) {
                    case Notification.ADD:
                    case Notification.ADD_MANY:
                    case Notification.REMOVE:
                    case Notification.REMOVE_MANY:
                        final KRendering rendering = element.getData(KRendering.class);
                        if (rendering != currentRendering) {
                            // a rendering has been added or removed
                            MonitoredOperation.runInUI(new Runnable() {
                                public void run() {
                                    // update the rendering
                                    updateRendering();
                                }
                            }, false);
                        }
                        break;
                    }
                }
            }
        });
    }

    /**
     * Updates the styles of the rendering.
     * 
     * @param rendering
     *            the rendering
     */
    private void updateStyles(final KRendering rendering) {
        List<KStyle> propagatedStyles = rendering.getProperty(PROPAGATED_STYLES);

        // update using the recursive method
        updateStyles(rendering, propagatedStyles);
    }

    private void updateStyles(final KRendering rendering, final List<KStyle> propagatedStyles) {
        PNodeController<?> controller = rendering.getProperty(CONTROLLER);
        List<KStyle> renderingStyles = rendering.getStyles();

        // set new propagated styles
        rendering.setProperty(PROPAGATED_STYLES, propagatedStyles);

        // determine the new styles for this rendering
        final Styles styles = deriveStyles(determineRenderingStyles(renderingStyles,
                propagatedStyles));

        // apply the styles to the rendering
        applyStyles(controller, styles);

        // update children with new propagated styles
        if (rendering instanceof KContainerRendering) {
            KContainerRendering container = (KContainerRendering) rendering;
            if (container.getChildren().size() > 0) {
                // determine the new styles for propagation to child nodes
                final List<KStyle> childPropagatedStyles = determinePropagationStyles(
                        renderingStyles, propagatedStyles);

                // propagate to all children if any propagation styles are available
                for (KRendering child : container.getChildren()) {
                    updateStyles(child, childPropagatedStyles);
                }
            }
        }
    }

    /**
     * Creates the Piccolo nodes for a list of renderings inside a parent Piccolo node for the given
     * placement.
     * 
     * @param children
     *            the list of children
     * @param placement
     *            the placement
     * @param styles
     *            the styles propagated to the children
     * @param parent
     *            the parent Piccolo node
     */
    private void handleChildren(final List<KRendering> children, final KPlacement placement,
            final List<KStyle> styles, final PNode parent) {
        if (placement == null) {
            // Direct Placement
            for (final KRendering rendering : children) {
                handleDirectPlacementRendering(rendering, styles, parent);
            }
        } else {
            new KRenderingSwitch<Boolean>() {
                // Grid Placement
                public Boolean caseKGridPlacement(final KGridPlacement object) {
                    // TODO implement this
                    return false;
                }

                // Stack Placement
                public Boolean caseKStackPlacement(final KStackPlacement object) {
                    for (final KRendering rendering : children) {
                        handleStackPlacementRendering(rendering, styles, parent);
                    }
                    return true;
                }
            } /**/.doSwitch(placement);
        }
    }

    /**
     * Creates the Piccolo node for a rendering inside a parent Piccolo node using direct placement.
     * 
     * @param rendering
     *            the rendering
     * @param styles
     *            the styles propagated to the children
     * @param parent
     *            the parent Piccolo node
     * @return the Piccolo node representing the rendering
     */
    private PNode handleDirectPlacementRendering(final KRendering rendering,
            final List<KStyle> styles, final PNode parent) {
        // determine the initial bounds
        final PBounds bounds = evaluateDirectPlacement(
                asDirectPlacementData(rendering.getPlacementData()), parent.getBoundsReference());

        // create the rendering and receive its controller
        final PNodeController<?> controller = createRendering(rendering, styles, parent, bounds);

        // add a listener on the parent's bounds
        addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds of the rendering
                        PBounds bounds = evaluateDirectPlacement(
                                asDirectPlacementData(rendering.getPlacementData()),
                                parent.getBoundsReference());
                        // use the controller to apply the new bounds
                        controller.setBounds(bounds);
                    }
                });

        return controller.getNode();
    }

    /**
     * Creates the Piccolo node for a rendering inside a parent Piccolo node using stack placement.
     * 
     * @param rendering
     *            the rendering
     * @param styles
     *            the styles propagated to the children
     * @param parent
     *            the parent Piccolo node
     * @return the Piccolo node representing the rendering
     */
    private PNode handleStackPlacementRendering(final KRendering rendering,
            final List<KStyle> styles, final PNode parent) {
        // determine the initial bounds
        final PBounds bounds = evaluateStackPlacement(
                asStackPlacementData(rendering.getPlacementData()), parent.getBoundsReference());

        // create the rendering and receive its controller
        final PNodeController<?> controller = createRendering(rendering, styles, parent, bounds);

        // add a listener on the parent's bounds
        addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds of the rendering
                        PBounds bounds = evaluateStackPlacement(
                                asStackPlacementData(rendering.getPlacementData()),
                                parent.getBoundsReference());
                        // use the controller to apply the new bounds
                        controller.setBounds(bounds);
                    }
                });

        return controller.getNode();
    }

    /**
     * Creates the Piccolo node for a rendering of a {@code KEdge} inside a parent Piccolo node.<br>
     * <br>
     * The rendering has to be a {@code KPolyline} or the method fails.
     * 
     * @param rendering
     *            the rendering
     * @param parent
     *            the parent Piccolo edge node
     * @return the Piccolo node representing the rendering
     */
    private PNode handleEdgeRendering(final KRendering rendering, final KEdgeNode parent) {
        // the rendering of an edge has to be a polyline or spline
        if (!(rendering instanceof KPolyline) || rendering instanceof KPolygon) {
            throw new RuntimeException("Non-polyline rendering attached to graph edge: " + element);
        }

        // create the rendering
        @SuppressWarnings("unchecked")
        final PNodeController<PSWTAdvancedPath> controller =
            (PNodeController<PSWTAdvancedPath>) createRendering(
                rendering, new ArrayList<KStyle>(0), parent, parent.getBoundsReference());
        controller.getNode().setPathToPolyline(parent.getBendPoints());
        parent.setRepresentationNode(controller.getNode());

        // add a listener on the parent's bend points
        addListener(KEdgeNode.PROPERTY_BEND_POINTS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        controller.getNode().setPathToPolyline(parent.getBendPoints());
                    }
                });

        return controller.getNode();
    }

    /**
     * Creates the Piccolo node representing the rendering inside the given parent with initial
     * bounds.
     * 
     * @param rendering
     *            the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    private PNodeController<?> createRendering(final KRendering rendering,
            final List<KStyle> propagatedStyles, final PNode parent, final PBounds initialBounds) {
        List<KStyle> renderingStyles = rendering.getStyles();

        // determine the styles for this rendering
        final Styles styles = deriveStyles(determineRenderingStyles(renderingStyles,
                propagatedStyles));

        // determine the styles for propagation to child nodes
        final List<KStyle> childPropagatedStyles = determinePropagationStyles(renderingStyles,
                propagatedStyles);

        // create the rendering and return its controller
        PNodeController<?> controller = new KRenderingSwitch<PNodeController<?>>() {
            // Ellipse
            public PNodeController<?> caseKEllipse(final KEllipse ellipse) {
                return createEllipse(ellipse, styles, childPropagatedStyles, parent, initialBounds);
            }

            // Rectangle
            public PNodeController<?> caseKRectangle(final KRectangle rect) {
                return createRectangle(rect, styles, childPropagatedStyles, parent, initialBounds);
            }

            // Rounded Rectangle
            public PNodeController<?> caseKRoundedRectangle(final KRoundedRectangle rect) {
                return createRoundedRectangle(rect, styles, childPropagatedStyles, parent,
                        initialBounds);
            }

            // Polyline
            public PNodeController<?> caseKPolyline(final KPolyline polyline) {
                return createPolyline(polyline, styles, childPropagatedStyles, parent,
                        initialBounds);
            }

            // public PNodeController<?> caseKArc(final KArc object) {};
            // public PNodeController<?> caseKPolygon(final KPolygon object) {};
            // public PNodeController<?> caseKImage(final KImage object) {};
            // public PNodeController<?> caseKCustomRendering(final KCustomRendering object) {};

            // Text
            public PNodeController<?> caseKText(final KText text) {
                return createText(text, styles, childPropagatedStyles, parent, initialBounds);
            };

            // Child Area
            public PNodeController<?> caseKChildArea(final KChildArea childArea) {
                return createChildArea(parent, initialBounds);
            }
        } /**/.doSwitch(rendering);

        // set the styles for the created rendering node using the controller
        applyStyles(controller, styles);

        // remember the propagated styles in the rendering
        rendering.setProperty(PROPAGATED_STYLES, propagatedStyles);
        // remember the controller in the rendering
        rendering.setProperty(CONTROLLER, controller);

        return controller;
    }

    /**
     * Creates a default rendering for nodes without attached rendering data.
     * 
     * @param parent
     *            the parent Piccolo node
     * @return the Piccolo node
     */
    private PNode createDefaultNodeRendering(final PNode parent) {
        // create the default rendering model
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KRectangle rect = factory.createKRectangle();
        KForegroundColor color = factory.createKForegroundColor();
        color.setRed(0);
        color.setGreen(0);
        color.setBlue(0);
        rect.getStyles().add(color);

        // create the rendering and return it
        return handleDirectPlacementRendering(rect, new ArrayList<KStyle>(0), parent);
    }

    /**
     * Creates a default rendering for edges without attached rendering data.
     * 
     * @param parentEdge
     *            the parent Piccolo edge node
     * @return the Piccolo node
     */
    private PNode createDefaultEdgeRendering(final KEdgeNode parentEdge) {
        // create the default rendering model
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KPolyline polyline = factory.createKPolyline();
        KForegroundColor color = factory.createKForegroundColor();
        color.setRed(0);
        color.setGreen(0);
        color.setBlue(0);
        polyline.getStyles().add(color);

        // create the rendering and return it
        return handleEdgeRendering(polyline, parentEdge);
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KEllipse}.
     * 
     * @param ellipse
     *            the ellipse rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    public PNodeController<PSWTAdvancedPath> createEllipse(final KEllipse ellipse,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds) {
        // create the ellipse
        final PSWTAdvancedPath path = PSWTAdvancedPath.createEllipse(0, 0,
                (float) initialBounds.width, (float) initialBounds.height);
        initializeRenderingNode(path);
        path.translate(initialBounds.x, initialBounds.y);
        parent.addChild(path);

        // handle children
        if (ellipse.getChildren().size() > 0) {
            handleChildren(ellipse.getChildren(), ellipse.getChildPlacement(), propagatedStyles,
                    path);
        }

        // return a controller for the ellipse
        return new PSWTAdvancedPathController(path) {
            public void setBounds(final PBounds bounds) {
                // apply the bounds
                getNode().setPathToEllipse(0, 0, (float) bounds.width, (float) bounds.height);
                NodeUtil.applyTranslation(getNode(), (float) bounds.x, (float) bounds.y);
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KRectangle}.
     * 
     * @param rect
     *            the rectangle rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    public PNodeController<PSWTAdvancedPath> createRectangle(final KRectangle rect,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds) {
        // create the rectangle
        final PSWTAdvancedPath path = PSWTAdvancedPath.createRectangle(0, 0,
                (float) initialBounds.width, (float) initialBounds.height);
        initializeRenderingNode(path);
        path.translate(initialBounds.x, initialBounds.y);
        parent.addChild(path);

        // handle children
        if (rect.getChildren().size() > 0) {
            handleChildren(rect.getChildren(), rect.getChildPlacement(), propagatedStyles, path);
        }

        // create a controller for the rectangle and return it
        return new PSWTAdvancedPathController(path) {
            public void setBounds(final PBounds bounds) {
                // apply the bounds
                getNode().setPathToRectangle(0, 0, (float) bounds.width, (float) bounds.height);
                NodeUtil.applyTranslation(getNode(), (float) bounds.x, (float) bounds.y);
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KRoundedRectangle}.
     * 
     * @param rect
     *            the rounded rectangle rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    public PNodeController<PSWTAdvancedPath> createRoundedRectangle(final KRoundedRectangle rect,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds) {
        // create the rounded rectangle
        final PSWTAdvancedPath path = PSWTAdvancedPath.createRoundRectangle(0, 0,
                (float) initialBounds.width, (float) initialBounds.height, rect.getCornerWidth(),
                rect.getCornerHeight());
        initializeRenderingNode(path);
        path.translate(initialBounds.x, initialBounds.y);
        parent.addChild(path);

        // handle children
        if (rect.getChildren().size() > 0) {
            handleChildren(rect.getChildren(), rect.getChildPlacement(), propagatedStyles, path);
        }

        // create a controller for the rounded rectangle and return it
        return new PSWTAdvancedPathController(path) {
            public void setBounds(final PBounds bounds) {
                // apply the bounds
                getNode().setPathToRoundRectangle(0, 0, (float) bounds.width,
                        (float) bounds.height, rect.getCornerWidth(), rect.getCornerHeight());
                NodeUtil.applyTranslation(getNode(), (float) bounds.x, (float) bounds.y);
            }
        };
    }

    /**
     * Creates a {@code PSWTText} representation for the {@code KText}.
     * 
     * @param text
     *            the text rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    public PNodeController<PSWTText> createText(final KText text, final Styles styles,
            final List<KStyle> propagatedStyles, final PNode parent, final PBounds initialBounds) {
        // create the text
        PSWTText textNode = new PSWTText(text.getText() != null ? text.getText() : "");
        initializeRenderingNode(textNode);

        // create the alignment node wrapping the text
        final PAlignmentNode alignmentNode = new PAlignmentNode();
        initializeRenderingNode(alignmentNode);
        alignmentNode.translate(initialBounds.x, initialBounds.y);
        alignmentNode.setBounds(0, 0, initialBounds.width, initialBounds.height);
        alignmentNode.addChild(textNode);
        alignmentNode.setHorizontalAlignment(textNode, HAlignment.CENTER);
        alignmentNode.setVerticalAlignment(textNode, VAlignment.CENTER);
        parent.addChild(alignmentNode);

        // handle children
        if (text.getChildren().size() > 0) {
            handleChildren(text.getChildren(), text.getChildPlacement(), propagatedStyles, textNode);
        }

        // create a controller for the text and return it
        return new PSWTTextController(textNode) {
            public void setBounds(final PBounds bounds) {
                NodeUtil.applySmartBounds(alignmentNode, bounds);
            }

            public void setHorizontalAlignment(final HAlignment alignment) {
                alignmentNode.setHorizontalAlignment(getNode(), alignment);
            }

            public void setVerticalAlignment(final VAlignment alignment) {
                alignmentNode.setVerticalAlignment(getNode(), alignment);
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KPolyline}.
     * 
     * @param polyline
     *            the polyline rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    public PNodeController<PSWTAdvancedPath> createPolyline(final KPolyline polyline,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds) {
        // create the polyline
        final PSWTAdvancedPath path = PSWTAdvancedPath.createPolyline(evaluatePolylinePlacement(
                asPolylinePlacementData(polyline.getPlacementData()), initialBounds));
        initializeRenderingNode(path);
        path.translate(initialBounds.x, initialBounds.y);
        parent.addChild(path);

        // handle children
        // TODO special polyline decorators
        // if (rect.getChildren().size() > 0) {
        // handleChildren(rect.getChildren(), rect.getChildPlacement(), propagatedStyles, path);
        // }

        // create a controller for the polyline and return it
        return new PSWTAdvancedPathController(path) {
            public void setBounds(final PBounds bounds) {
                // apply the bounds
                getNode().setPathToPolyline(
                        (evaluatePolylinePlacement(
                                asPolylinePlacementData(polyline.getPlacementData()), bounds)));
                NodeUtil.applyTranslation(getNode(), (float) bounds.x, (float) bounds.y);
            }
        };
    }

    /**
     * Configures the Piccolo node for the given {@code KChildArea}.
     * 
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    private PNodeController<?> createChildArea(final PNode parent, final PBounds initialBounds) {
        // only nodes can have a child area
        if (childAreaNode == null) {
            throw new RuntimeException("Invalid child area found in non-node graph element: "
                    + element);
        }

        // there can only be none or one child area
        if (childAreaNode.getParent() != null) {
            throw new RuntimeException("More then one child area found in graph element: "
                    + element);
        }

        // configure the child area
        NodeUtil.applySmartBounds(childAreaNode, initialBounds);

        parent.addChild(childAreaNode);

        // create a controller for the child area and return it
        return new PNodeController<PNode>(childAreaNode) {
            public void setBounds(final PBounds bounds) {
                // apply the bounds
                NodeUtil.applySmartBounds(getNode(), bounds);
            }
        };
    }

    /**
     * Creates the Piccolo node for the parent Piccolo node using direct placement.
     * 
     * @param parent
     *            the parent Piccolo node
     */
    private void createDefaultChildArea(final PNode parent) {
        // determine the initial bounds
        PBounds bounds = evaluateDirectPlacement(null, parent.getBoundsReference());

        // configure the child area
        final PNodeController<?> controller = createChildArea(parent, bounds);

        // add a listener on the parent's bounds
        addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds of the rendering
                        PBounds bounds = evaluateDirectPlacement(null, parent.getBoundsReference());
                        // use the controller to apply the new bounds
                        controller.setBounds(bounds);
                    }
                });
    }

    /**
     * Returns the bounds for a direct placement data in given parent bounds.
     * 
     * @param dpd
     *            the direct placement data
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    private PBounds evaluateDirectPlacement(final KDirectPlacementData dpd,
            final PBounds parentBounds) {
        if (dpd == null) {
            return new PBounds(0, 0, parentBounds.width, parentBounds.height);
        }

        // determine the top-left
        KPosition topLeft = dpd.getTopLeft();
        Point2D topLeftPoint = evaluateDirectPosition(topLeft, parentBounds);

        // determine the bottom-right
        KPosition bottomRight = dpd.getBottomRight();
        Point2D bottomRightPoint = evaluateDirectPosition(bottomRight, parentBounds);

        return new PBounds(topLeftPoint.getX(), topLeftPoint.getY(), bottomRightPoint.getX()
                - topLeftPoint.getX(), bottomRightPoint.getY() - topLeftPoint.getY());
    }

    /**
     * Returns the bounds for a stack placement data in given parent bounds.
     * 
     * @param spd
     *            the stack placement data
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    private PBounds evaluateStackPlacement(final KStackPlacementData spd, final PBounds parentBounds) {
        float width = (float) parentBounds.width;
        float height = (float) parentBounds.height;

        if (spd == null) {
            return new PBounds(0, 0, width, height);
        }

        return new PBounds(spd.getInsetLeft(), spd.getInsetTop(), width - spd.getInsetRight(),
                height - spd.getInsetBottom());
    }

    /**
     * Returns the bounds for a polyline placement data in given parent bounds.
     * 
     * @param spd
     *            the polyline placement data
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    private Point2D[] evaluatePolylinePlacement(final KPolylinePlacementData ppd,
            final PBounds parentBounds) {
        if (ppd == null) {
            return new Point2D[] { new Point2D.Float(0, 0) };
        }

        // evaluate the points of the polyline inside the parent bounds
        Point2D[] points = new Point2D[ppd.getPoints().size()];
        int i = 0;
        for (KPosition point : ppd.getPoints()) {
            points[i++] = evaluateDirectPosition(point, parentBounds);
        }

        return points;
    }

    /**
     * Evaluates a position inside given parent bounds.
     * 
     * @param position
     *            the position
     * @param parentBounds
     *            the parent bounds
     * @return the evaluated position
     */
    private Point2D.Float evaluateDirectPosition(final KPosition position,
            final PBounds parentBounds) {
        float width = (float) parentBounds.width;
        float height = (float) parentBounds.height;
        Point2D.Float point = new Point2D.Float();
        KXPosition xPos = position.getX();
        KYPosition yPos = position.getY();
        if (xPos instanceof KLeftPosition) {
            point.x = xPos.getAbsolute() + xPos.getRelative() * width;
        } else {
            point.x = width - xPos.getAbsolute() - xPos.getRelative() * width;
        }
        if (yPos instanceof KTopPosition) {
            point.y = yPos.getAbsolute() + yPos.getRelative() * height;
        } else {
            point.y = height - yPos.getAbsolute() - yPos.getRelative() * height;
        }
        return point;
    }

    /**
     * Returns the given placement data as direct placement data.
     * 
     * @param data
     *            the placement data
     * @return the direct placement data or null if the placement data is no direct placement data
     */
    private KDirectPlacementData asDirectPlacementData(final KPlacementData data) {
        if (data instanceof KDirectPlacementData) {
            return (KDirectPlacementData) data;
        }
        return null;
    }

    /**
     * Returns the given placement data as stack placement data.
     * 
     * @param data
     *            the placement data
     * @return the stack placement data or null if the placement data is no stack placement data
     */
    private KStackPlacementData asStackPlacementData(final KPlacementData data) {
        if (data instanceof KStackPlacementData) {
            return (KStackPlacementData) data;
        }
        return null;
    }

    /**
     * Returns the given placement data as polyline placement data.
     * 
     * @param data
     *            the placement data
     * @return the polyline placement data or null if the placement data is no polyline placement
     *         data
     */
    private KPolylinePlacementData asPolylinePlacementData(final KPlacementData data) {
        if (data instanceof KPolylinePlacementData) {
            return (KPolylinePlacementData) data;
        }
        return null;
    }

    /**
     * Sets default values for the given Piccolo node used as representation for a rendering.
     * 
     * @param node
     *            the Piccolo node
     */
    private void initializeRenderingNode(final PNode node) {
        node.setVisible(true);
        node.setPickable(false);
    }

    private static final Object PROPERTY_LISTENER_KEY = new Object();

    /**
     * Adds a listener for a child node on a parent node.
     * 
     * @param property
     *            the property to register the listener on
     * @param parent
     *            the parent node
     * @param node
     *            the child node
     * @param listener
     *            the listener
     */
    private void addListener(final String property, final PNode parent, final PNode node,
            final PropertyChangeListener listener) {
        parent.addPropertyChangeListener(property, listener);
        node.addAttribute(PROPERTY_LISTENER_KEY, new Pair<String, PropertyChangeListener>(property,
                listener));
    }

    /**
     * Removes a node as listener from its parent.
     * 
     * @param node
     *            the child node
     */
    private void removeListener(final PNode node) {
        @SuppressWarnings("unchecked")
        Pair<String, PropertyChangeListener> pair = (Pair<String, PropertyChangeListener>) node
                .getAttribute(PROPERTY_LISTENER_KEY);
        if (pair != null && node.getParent() != null) {
            node.getParent().removePropertyChangeListener(pair.getFirst(), pair.getSecond());
        }
    }

    /**
     * Returns the list of styles for the rendering with the given rendering styles and propagated
     * styles.
     * 
     * @param renderingStyles
     *            the rendering styles
     * @param propagatedStyles
     *            the propagated styles
     * @return the list of styles for the rendering
     */
    private List<KStyle> determineRenderingStyles(final List<KStyle> renderingStyles,
            final List<KStyle> propagatedStyles) {
        List<KStyle> combinedStyles = Lists.newLinkedList();
        combinedStyles.addAll(propagatedStyles);
        combinedStyles.addAll(renderingStyles);
        return combinedStyles;
    }

    /**
     * Returns the list of styles propagated to children of the rendering with the given rendering
     * styles and propagated styles.
     * 
     * @param renderingStyles
     *            the rendering styles
     * @param propagatedStyles
     *            the propagated styles
     * @return the list of styles for propagation to the children of the rendering
     */
    private List<KStyle> determinePropagationStyles(final List<KStyle> renderingStyles,
            final List<KStyle> propagatedStyles) {
        List<KStyle> propagationStyles = Lists.newLinkedList();
        for (KStyle style : renderingStyles) {
            if (style.isPropagateToChildren()) {
                propagationStyles.add(style);
            }
        }
        propagationStyles.addAll(propagatedStyles);
        return propagationStyles;
    }

    /**
     * Returns a styles container from a list of styles.
     * 
     * @param styleList
     *            the list of styles
     * @return the styles container
     */
    private Styles deriveStyles(final List<KStyle> styleList) {
        final Styles styles = new Styles();
        for (KStyle style : styleList) {
            new KRenderingSwitch<Boolean>() {
                // foreground color
                public Boolean caseKForegroundColor(final KForegroundColor fc) {
                    if (styles.foregroundColor == null) {
                        styles.foregroundColor = fc;
                    }
                    return true;
                }

                // background color
                public Boolean caseKBackgroundColor(final KBackgroundColor bc) {
                    if (styles.backgroundColor == null) {
                        styles.backgroundColor = bc;
                    }
                    return true;
                }

                // line width
                public Boolean caseKLineWidth(final KLineWidth lw) {
                    if (styles.lineWidth == null) {
                        styles.lineWidth = lw;
                    }
                    return true;
                }

                // foreground visibility
                public Boolean caseKForegroundVisibility(
                        final KForegroundVisibility foregorundVisibility) {
                    if (styles.foregroundVisibility == null) {
                        styles.foregroundVisibility = foregorundVisibility;
                    }
                    return true;
                }

                // background visibility
                public Boolean caseKBackgroundVisibility(
                        final KBackgroundVisibility backgroundVisibility) {
                    if (styles.backgroundVisibility == null) {
                        styles.backgroundVisibility = backgroundVisibility;
                    }
                    return true;
                }

                // line style
                public Boolean caseKLineStyle(final KLineStyle ls) {
                    if (styles.lineStyle == null) {
                        styles.lineStyle = ls;
                    }
                    return true;
                }

                // horizontal alignment
                public Boolean caseKHorizontalAlignment(final KHorizontalAlignment ha) {
                    if (styles.horizontalAlignment == null) {
                        styles.horizontalAlignment = ha;
                    }
                    return true;
                }

                // vertical alignment
                public Boolean caseKVerticalAlignment(final KVerticalAlignment va) {
                    if (styles.verticalAlignment == null) {
                        styles.verticalAlignment = va;
                    }
                    return true;
                }

                // font name
                public Boolean caseKFontName(final KFontName fontName) {
                    if (styles.fontName == null) {
                        styles.fontName = fontName;
                    }
                    return true;
                }

                // font size
                public Boolean caseKFontSize(final KFontSize fontSize) {
                    if (styles.fontSize == null) {
                        styles.fontSize = fontSize;
                    }
                    return true;
                }

                // italic
                public Boolean caseKFontItalic(final KFontItalic italic) {
                    if (styles.italic == null) {
                        styles.italic = italic;
                    }
                    return true;
                }

                // bold
                public Boolean caseKFontBold(final KFontBold bold) {
                    if (styles.bold == null) {
                        styles.bold = bold;
                    }
                    return true;
                }
            } /**/.doSwitch(style);
        }
        return styles;
    }

    /**
     * Applies the styles to the node associated with the given node controller.
     * 
     * @param controller
     *            the node controller
     * @param styles
     *            the styles
     */
    private void applyStyles(final PNodeController<?> controller, final Styles styles) {
        // apply foreground color
        if (styles.foregroundColor != null) {
            KColor color = styles.foregroundColor;
            controller.setForegroundColor(new Color(color.getRed(), color.getGreen(), color
                    .getBlue()));
        }

        // apply background color
        if (styles.backgroundColor != null) {
            KColor color = styles.backgroundColor;
            controller.setBackgroundColor(new Color(color.getRed(), color.getGreen(), color
                    .getBlue()));
        }

        // apply line width
        if (styles.lineWidth != null) {
            controller.setLineWidth(styles.lineWidth.getLineWidth());
        }

        // apply foreground visibility
        if (styles.foregroundVisibility != null) {
            KForegroundVisibility foregroundVisibility = styles.foregroundVisibility;
            controller.setLineVisible(foregroundVisibility.isVisible());
        }

        // apply background visibility
        if (styles.backgroundVisibility != null) {
            KBackgroundVisibility backgroundVisibility = styles.backgroundVisibility;
            controller.setFilled(backgroundVisibility.isVisible());
        }

        // apply line style
        if (styles.lineStyle != null) {
            switch (styles.lineStyle.getLineStyle()) {
            case DASH:
                controller.setLineStyle(LineStyle.DASH);
                break;
            case DASHDOT:
                controller.setLineStyle(LineStyle.DASHDOT);
                break;
            case DASHDOTDOT:
                controller.setLineStyle(LineStyle.DASHDOTDOT);
                break;
            case DOT:
                controller.setLineStyle(LineStyle.DOT);
                break;
            case SOLID:
            default:
                controller.setLineStyle(LineStyle.SOLID);
                break;
            }
        }

        // apply horizontal alignment
        if (styles.horizontalAlignment != null) {
            switch (styles.horizontalAlignment.getHorizontalAlignment()) {
            case LEFT:
                controller.setHorizontalAlignment(HAlignment.LEFT);
                break;
            case RIGHT:
                controller.setHorizontalAlignment(HAlignment.RIGHT);
                break;
            case CENTER:
            default:
                controller.setHorizontalAlignment(HAlignment.CENTER);
                break;
            }
        }

        // apply vertical alignment
        if (styles.verticalAlignment != null) {
            switch (styles.verticalAlignment.getVerticalAlignment()) {
            case TOP:
                controller.setVerticalAlignment(VAlignment.TOP);
                break;
            case BOTTOM:
                controller.setVerticalAlignment(VAlignment.BOTTOM);
                break;
            case CENTER:
            default:
                controller.setVerticalAlignment(VAlignment.CENTER);
                break;
            }
        }

        // apply font name
        if (styles.fontName != null) {
            controller.setFontName(styles.fontName.getName());
        }

        // apply font size
        if (styles.fontSize != null) {
            controller.setFontSize(styles.fontSize.getSize());
        }

        // apply the italic property
        if (styles.italic != null) {
            controller.setItalic(styles.italic.isItalic());
        }

        // apply the bold property
        if (styles.bold != null) {
            controller.setBold(styles.bold.isBold());
        }

        // give the controller the opportunity to apply styles bundled
        controller.applyChanges();

    }

    /**
     * A container class for the possible styles.
     */
    private class Styles {

        /** the foreground color. */
        private KColor foregroundColor = null;
        /** the background color. */
        private KColor backgroundColor = null;
        /** the line width. */
        private KLineWidth lineWidth = null;
        /** the foreground visibility. */
        private KForegroundVisibility foregroundVisibility = null;
        /** the background visibility. */
        private KBackgroundVisibility backgroundVisibility = null;
        /** the line style. */
        private KLineStyle lineStyle = null;
        /** the horizontal alignment. */
        private KHorizontalAlignment horizontalAlignment = null;
        /** the vertical alignment. */
        private KVerticalAlignment verticalAlignment = null;
        /** the font name. */
        private KFontName fontName = null;
        /** the font size. */
        private KFontSize fontSize = null;
        /** the font italic property. */
        private KFontItalic italic = null;
        /** the font bold property. */
        private KFontBold bold = null;

    }

}
