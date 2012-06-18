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
package de.cau.cs.kieler.klighd.piccolo.krendering.controller;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KBackgroundColor;
import de.cau.cs.kieler.core.krendering.KBackgroundVisibility;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KCustomRendering;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KFontBold;
import de.cau.cs.kieler.core.krendering.KFontItalic;
import de.cau.cs.kieler.core.krendering.KFontName;
import de.cau.cs.kieler.core.krendering.KFontSize;
import de.cau.cs.kieler.core.krendering.KForegroundColor;
import de.cau.cs.kieler.core.krendering.KForegroundVisibility;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KHorizontalAlignment;
import de.cau.cs.kieler.core.krendering.KImage;
import de.cau.cs.kieler.core.krendering.KLineStyle;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KPlacement;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KStackPlacement;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KVerticalAlignment;
import de.cau.cs.kieler.core.krendering.util.KRenderingSwitch;
import de.cau.cs.kieler.core.model.notify.CrossDocumentContentAdapter;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klighd.piccolo.krendering.util.PlacementUtil;
import de.cau.cs.kieler.klighd.piccolo.krendering.util.PlacementUtil.Decoration;
import de.cau.cs.kieler.klighd.piccolo.krendering.util.PlacementUtil.GridPlacer;
import de.cau.cs.kieler.klighd.piccolo.nodes.PAlignmentNode;
import de.cau.cs.kieler.klighd.piccolo.nodes.PAlignmentNode.HAlignment;
import de.cau.cs.kieler.klighd.piccolo.nodes.PAlignmentNode.VAlignment;
import de.cau.cs.kieler.klighd.piccolo.nodes.PEmptyNode;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath.LineStyle;
import de.cau.cs.kieler.klighd.piccolo.util.NodeUtil;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolox.swt.PSWTText;

/**
 * The abstract base class for classes which control the transformation of KRendering data to
 * Piccolo nodes and the synchronization of these Piccolo nodes with the KRendering model.
 * 
 * @author mri
 * 
 * @param <S>
 *            the type of the underlying graph element
 * @param <T>
 *            the type of the Piccolo node representing the graph element
 */
public abstract class AbstractRenderingController<S extends KGraphElement, T extends PNode> {

    /** the property for a rendering node's controller. */
    private static final IProperty<Map<Object, PNodeController<?>>> CONTROLLER =
            new Property<Map<Object, PNodeController<?>>>(
                    "de.cau.cs.kieler.klighd.piccolo.controller");
    /** the property for a rendering reference key. */
    private static final IProperty<Map<Object, Object>> KEY = new Property<Map<Object, Object>>(
            "de.cau.cs.kieler.klighd.piccolo.key");

    /** the graph element which rendering is controlled by this controller. */
    private S element;
    /** the rendering currently in use by this controller. */
    private KRendering currentRendering;

    /** the Piccolo node representing the node. */
    private T repNode;
    /** the Piccolo node representing the rendering. */
    private PNode renderingNode = null;

    /** the adapter currently installed on the rendering. */
    private CrossDocumentContentAdapter renderingAdapter = null;
    /** the element adapter currently installed on the element. */
    private AdapterImpl elementAdapter = null;

    /** the map of properties used by this controller mapped on all mappings under that property. */
    private Map<Object, List<Pair<IPropertyHolder, Object>>> mappedProperties = Maps.newHashMap();

    /** whether to synchronize the rendering with the model. */
    private boolean syncRendering = false;

    /**
     * Constructs a rendering controller.
     * 
     * @param element
     *            the graph element for this controller
     * @param repNode
     *            the Piccolo node representing the graph element
     */
    public AbstractRenderingController(final S element, final T repNode) {
        this.element = element;
        this.repNode = repNode;
    }
    
    /**
     * Returns the underlying graph element.
     * 
     * @return the graph element
     */
    public S getGraphElement() {
        return element;
    }

    /**
     * Returns the Piccolo node representing the underlying graph element.
     * 
     * @return the Piccolo node
     */
    public T getRepresentation() {
        return repNode;
    }

    /**
     * Returns the rendering currently managed by this controller.
     * 
     * @return the rendering
     */
    public KRendering getCurrentRendering() {
        return currentRendering;
    }

    /**
     * Initializes the rendering controller.
     * 
     * @param sync
     *            true if the rendering should be synchronized with the model; false else
     */
    public void initialize(final boolean sync) {
        syncRendering = sync;

        // do the initial update of the rendering
        updateRendering();
    }

    /**
     * Updates the rendering by removing the current rendering and evaluating the rendering data
     * attached to the graph element.
     */
    // Review: TODO make public/package protected
    private void updateRendering() {
        // remove the rendering adapter
        if (currentRendering != null) {
            unregisterElementAdapter();
            unregisterRenderingAdapter();
            removeMappedProperties(CONTROLLER);
            removeMappedProperties(KEY);
        }

        // remove the rendering node
        if (renderingNode != null) {
            removeListeners(renderingNode);
            renderingNode.removeFromParent();
            renderingNode = null;
        }
        
        // get the current rendering
        currentRendering = element.getData(KRendering.class);
        
        // update the rendering
        renderingNode = internalUpdateRendering();
        
        // install rendering adapter if sync is enabled
        if (syncRendering && currentRendering != null) {
            // register an adapter on the rendering to stay in sync
            registerRenderingAdapter();
        }
        
        // install element adapter if sync is enabled
        if (syncRendering) {
            // register an adapter on the element (KGE) to stay in sync
            registerElementAdapter();
        }
    }

    /**
     * Performs the actual update of the rendering.
     * 
     * @return the Piccolo node representing the current rendering
     */
    protected abstract PNode internalUpdateRendering();

    /**
     * Registers an adapter on the current rendering to react on changes.
     */
    private void registerRenderingAdapter() {
        // register adapter on the rendering to stay in sync
        renderingAdapter = new CrossDocumentContentAdapter() {

            protected boolean shouldAdapt(final EStructuralFeature feature) {
                // follow the rendering feature of the KRenderingRef
                return feature.getFeatureID() == KRenderingPackage.KRENDERING_REF__RENDERING;
            }

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
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                // update the styles
                                updateStyles();
                            }
                        }, true);
                        return;
                    }

                    // handle new, moved and removed styles
                    if (msg.getNotifier() instanceof KRendering
                            && msg.getFeatureID(KRendering.class)
                            == KRenderingPackage.KRENDERING__STYLES) {
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                // update the styles
                                updateStyles();
                            }
                        }, true);
                        return;
                    }

                    // handle other changes by reevaluating the rendering
                    MonitoredOperation.runInUI(new Runnable() {
                        public void run() {
                            // update the rendering
                            updateRendering();
                        }
                    }, true);
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
        elementAdapter = new AdapterImpl() {
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
                            }, true);
                        }
                        break;
                    }
                }
            }
        };
        element.eAdapters().add(elementAdapter);
    }
    
    /**
     * Unregisters the adapter currently installed on the element.
     */
    private void unregisterElementAdapter() {
        if (elementAdapter != null) {
            element.eAdapters().remove(elementAdapter);
            elementAdapter = null;
        }
    }

    /**
     * Updates the styles of the current rendering.
     */
    private void updateStyles() {
        // update using the recursive method
        updateStyles(currentRendering, null, new ArrayList<KStyle>(0), repNode);
    }

    private void updateStyles(final KRendering rendering, final Styles styles,
            final List<KStyle> propagatedStyles, final Object key) {
        PNodeController<?> controller = getMappedProperty(rendering, CONTROLLER, key);
        List<KStyle> renderingStyles = rendering.getStyles();

        // determine the styles for this rendering
        final Styles newStyles =
                deriveStyles(styles, determineRenderingStyles(renderingStyles, propagatedStyles));

        // apply the styles to the rendering
        applyStyles(controller, newStyles);

        if (rendering instanceof KContainerRendering) {
            // update children
            KContainerRendering container = (KContainerRendering) rendering;
            if (container.getChildren().size() > 0) {
                // determine the styles for propagation to child nodes
                final List<KStyle> childPropagatedStyles =
                        determinePropagationStyles(renderingStyles, propagatedStyles);

                // propagate to all children
                for (KRendering child : container.getChildren()) {
                    updateStyles(child, null, childPropagatedStyles, key);
                }
            }
        } else if (rendering instanceof KRenderingRef) {
            // update referenced rendering
            KRenderingRef renderingRef = (KRenderingRef) rendering;
            Object refKey = getMappedProperty(rendering, KEY, key);

            // get the referenced rendering
            KRendering referencedRendering = renderingRef.getRendering();

            // proceed recursively with the referenced rendering
            updateStyles(referencedRendering, newStyles, propagatedStyles, refKey);
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
     * @param key
     *            the key used to identify the current reference hierarchy
     */
    protected void handleChildren(final List<KRendering> children, final KPlacement placement,
            final List<KStyle> styles, final PNode parent, final Object key) {
        if (placement == null) {
            // Direct Placement
            for (final KRendering rendering : children) {
                handleDirectPlacementRendering(rendering, styles, parent, key);
            }
        } else {
            new KRenderingSwitch<Boolean>() {
                // Grid Placement
                public Boolean caseKGridPlacement(final KGridPlacement object) {
                    handleGridPlacementRendering(object, children, styles, parent, key);
                    return true;
                }

                // Stack Placement
                public Boolean caseKStackPlacement(final KStackPlacement object) {
                    for (final KRendering rendering : children) {
                        handleStackPlacementRendering(rendering, styles, parent, key);
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
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the Piccolo node representing the rendering
     */
    protected PNode handleDirectPlacementRendering(final KRendering rendering,
            final List<KStyle> styles, final PNode parent, final Object key) {
        // determine the initial bounds
        final PBounds bounds =
                PlacementUtil.evaluateDirectPlacement(
                        PlacementUtil.asDirectPlacementData(rendering.getPlacementData()),
                        parent.getBoundsReference());

        // create the rendering and receive its controller
        final PNodeController<?> controller =
                createRendering(rendering, styles, parent, bounds, key);

        // add a listener on the parent's bounds
        addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds of the rendering
                        PBounds bounds =
                                PlacementUtil.evaluateDirectPlacement(PlacementUtil
                                        .asDirectPlacementData(rendering.getPlacementData()),
                                        parent.getBoundsReference());
                        // use the controller to apply the new bounds
                        controller.setBounds(bounds);
                    }
                });

        return controller.getNode();
    }

    /**
     * Creates the Piccolo nodes for a list of renderings inside a parent Piccolo node using grid
     * placement.
     * 
     * @param gridPlacement
     *            the grid placement
     * @param renderings
     *            the renderings
     * @param styles
     *            the styles propagated to the children
     * @param parent
     *            the parent Piccolo node
     * @param key
     *            the key used to identify the current reference hierarchy
     */
    protected void handleGridPlacementRendering(final KGridPlacement gridPlacement,
            final List<KRendering> renderings, final List<KStyle> styles, final PNode parent,
            final Object key) {
        if (renderings.size() == 0) {
            return;
        }

        // collect the grid placement data
        final KGridPlacementData[] gpds = new KGridPlacementData[renderings.size()];
        int i = 0;
        for (KRendering rendering : renderings) {
            gpds[i++] = PlacementUtil.asGridPlacementData(rendering.getPlacementData());
        }

        // determine the initial bounds
        final GridPlacer gridPlacer = PlacementUtil.evaluateGridPlacement(gridPlacement, gpds);
        PBounds[] bounds = gridPlacer.evaluate(parent.getBoundsReference());

        // create the renderings and collect the controllers
        final PNodeController<?>[] controllers = new PNodeController<?>[renderings.size()];
        i = 0;
        for (KRendering rendering : renderings) {
            controllers[i] = createRendering(rendering, styles, parent, bounds[i], key);
            i++;
        }
        
        // add a listener on the parent's bounds
        addListener(PNode.PROPERTY_BOUNDS, parent, controllers[0].getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds of the rendering
                        PBounds[] bounds = gridPlacer.evaluate(parent.getBoundsReference());
                        // use the controllers to apply the new bounds
                        int i = 0;
                        for (PNodeController<?> controller : controllers) {
                            controller.setBounds(bounds[i++]);
                        }
                    }
                });
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
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the Piccolo node representing the rendering
     */
    protected PNode handleStackPlacementRendering(final KRendering rendering,
            final List<KStyle> styles, final PNode parent, final Object key) {
        // determine the initial bounds
        final PBounds bounds =
                PlacementUtil.evaluateStackPlacement(
                        PlacementUtil.asStackPlacementData(rendering.getPlacementData()),
                        parent.getBoundsReference());

        // create the rendering and receive its controller
        final PNodeController<?> controller =
                createRendering(rendering, styles, parent, bounds, key);

        // add a listener on the parent's bounds
        addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds of the rendering
                        PBounds bounds =
                                PlacementUtil.evaluateStackPlacement(PlacementUtil
                                        .asStackPlacementData(rendering.getPlacementData()), parent
                                        .getBoundsReference());
                        // use the controller to apply the new bounds
                        controller.setBounds(bounds);
                    }
                });

        return controller.getNode();
    }

    /**
     * Creates the Piccolo node for a rendering inside a parent Piccolo node representing a polyline
     * using decorator placement.
     * 
     * @param rendering
     *            the rendering
     * @param styles
     *            the styles propagated to the children
     * @param parent
     *            the parent Piccolo node representing a polyline
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the Piccolo node representing the rendering
     */
    protected PNode handleDecoratorPlacementRendering(final KRendering rendering,
            final List<KStyle> styles, final PSWTAdvancedPath parent, final Object key) {
        // determine the initial bounds and rotation
        final Decoration decoration = PlacementUtil.evaluateDecoratorPlacement(
                PlacementUtil.asDecoratorPlacementData(rendering.getPlacementData()), parent);

        // create an empty node for the decorator
        final PEmptyNode decorator = new PEmptyNode();

        // NodeUtil.applyTranslation(decorator, decoration.getOrigin());
        parent.addChild(decorator);

        // create the rendering and receive its controller
        final PNodeController<?> controller = createRendering(rendering, styles, decorator,
                decoration.getBounds(), key);

        // apply the initial rotation
        decorator.setRotation(decoration.getRotation());

        // add a listener on the parent's path
        addListener(PPath.PROPERTY_PATH, parent, controller.getNode(),
                new PropertyChangeListener() {

                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds and rotation for the rendering
                        Decoration decoration = PlacementUtil.evaluateDecoratorPlacement(
                                PlacementUtil
                                        .asDecoratorPlacementData(rendering.getPlacementData()),
                                parent);

                        // apply the new offset
                        decorator.setOffset(decoration.getOrigin().getX(), decoration.getOrigin()
                                .getY());

                        // use the controller to apply the new bounds
                        controller.setBounds(decoration.getBounds());
                        // apply the new rotation
                        decorator.setRotation(decoration.getRotation());
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
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<?> createRendering(final KRendering rendering,
            final List<KStyle> propagatedStyles, final PNode parent, final PBounds initialBounds,
            final Object key) {
        List<KStyle> renderingStyles = rendering.getStyles();

        // determine the styles for this rendering
        final Styles styles =
                deriveStyles(null, determineRenderingStyles(renderingStyles, propagatedStyles));

        // determine the styles for propagation to child nodes
        final List<KStyle> childPropagatedStyles =
                determinePropagationStyles(renderingStyles, propagatedStyles);

        // dispatch the rendering
        PNodeController<?> controller =
                createRendering(rendering, styles, childPropagatedStyles, parent, initialBounds,
                        key);

        // set the styles for the created rendering node using the controller
        applyStyles(controller, styles);

        // remember the controller in the related KRendering rendering
        setMappedProperty(rendering, CONTROLLER, key, controller);

        return controller;
    }

    /**
     * Creates the Piccolo node representing the rendering inside the given parent with initial
     * bounds and given styles.
     * 
     * @param rendering
     *            the rendering
     * @param styles
     *            the styles for the rendering
     * @param childPropagatedStyles
     *            the style propagated to the renderings children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<?> createRendering(final KRendering rendering, final Styles styles,
            final List<KStyle> childPropagatedStyles, final PNode parent,
            final PBounds initialBounds, final Object key) {
        // create the rendering and return its controller
        PNodeController<?> controller = new KRenderingSwitch<PNodeController<?>>() {
            // Ellipse
            public PNodeController<?> caseKEllipse(final KEllipse ellipse) {
                return createEllipse(ellipse, styles, childPropagatedStyles, parent, initialBounds,
                        key);
            }

            // Rectangle
            public PNodeController<?> caseKRectangle(final KRectangle rect) {
                return createRectangle(rect, styles, childPropagatedStyles, parent, initialBounds,
                        key);
            }

            // Rounded Rectangle
            public PNodeController<?> caseKRoundedRectangle(final KRoundedRectangle rect) {
                return createRoundedRectangle(rect, styles, childPropagatedStyles, parent,
                        initialBounds, key);
            }
            
            // Arc
            public PNodeController<?> caseKArc(final KArc arc) {
                return createArc(arc, styles, childPropagatedStyles, parent, initialBounds, key);
            }

            // Polyline
            public PNodeController<?> caseKPolyline(final KPolyline polyline) {
                return createPolyline(polyline, styles, childPropagatedStyles, parent,
                        initialBounds, key);
            }
            
            // Polygon
            public PNodeController<?> caseKPolygon(final KPolygon polygon) {
                return createPolygon(polygon, styles, childPropagatedStyles, parent, initialBounds,
                        key);
            }

            // Text
            public PNodeController<?> caseKText(final KText text) {
                return createText(text, styles, childPropagatedStyles, parent, initialBounds, key);
            };

            // Rendering Reference
            public PNodeController<?> caseKRenderingRef(final KRenderingRef renderingReference) {
                return createRenderingReference(renderingReference, styles, childPropagatedStyles,
                        parent, initialBounds, key);
            }
            
            // Image
            public PNodeController<?> caseKImage(final KImage object) {
                return null;
            }
            
            // Custom Rendering
            public PNodeController<?> caseKCustomRendering(final KCustomRendering object) {
                return null;
            }

            // Child Area
            public PNodeController<?> caseKChildArea(final KChildArea childArea) {
                return createChildArea(parent, initialBounds);
            }
            
        } /**/.doSwitch(rendering);
        return controller;
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
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<PSWTAdvancedPath> createEllipse(final KEllipse ellipse,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds, final Object key) {
        // create the ellipse
        final PSWTAdvancedPath path =
                PSWTAdvancedPath.createEllipse(0, 0, (float) initialBounds.width,
                        (float) initialBounds.height);
        initializeRenderingNode(path);
        path.translate(initialBounds.x, initialBounds.y);
        parent.addChild(path);

        // handle children
        if (ellipse.getChildren().size() > 0) {
            handleChildren(ellipse.getChildren(), ellipse.getChildPlacement(), propagatedStyles,
                    path, key);
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
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<PSWTAdvancedPath> createRectangle(final KRectangle rect,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds, final Object key) {
        // create the rectangle
        final PSWTAdvancedPath path =
                PSWTAdvancedPath.createRectangle(0, 0, (float) initialBounds.width,
                        (float) initialBounds.height);
        initializeRenderingNode(path);
        path.translate(initialBounds.x, initialBounds.y);
        parent.addChild(path);

        // handle children
        if (rect.getChildren().size() > 0) {
            handleChildren(rect.getChildren(), rect.getChildPlacement(), propagatedStyles, path,
                    key);
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
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<PSWTAdvancedPath> createRoundedRectangle(final KRoundedRectangle rect,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds, final Object key) {
        // create the rounded rectangle
        final PSWTAdvancedPath path =
                PSWTAdvancedPath
                        .createRoundRectangle(0, 0, (float) initialBounds.width,
                                (float) initialBounds.height, rect.getCornerWidth(),
                                rect.getCornerHeight());
        initializeRenderingNode(path);
        path.translate(initialBounds.x, initialBounds.y);
        parent.addChild(path);

        // handle children
        if (rect.getChildren().size() > 0) {
            handleChildren(rect.getChildren(), rect.getChildPlacement(), propagatedStyles, path,
                    key);
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
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KArc}.
     * 
     * @param arc
     *            the arc rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<PSWTAdvancedPath> createArc(final KArc arc,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds, final Object key) {
        // create the rounded rectangle
        final PSWTAdvancedPath path =
                PSWTAdvancedPath.createArc(0, 0, (float) initialBounds.width,
                        (float) initialBounds.height, arc.getStartAngle(), arc.getArcAngle());
        path.setPaint(null);
        initializeRenderingNode(path);
        path.translate(initialBounds.x, initialBounds.y);
        parent.addChild(path);

        // handle children
        if (arc.getChildren().size() > 0) {
            handleChildren(arc.getChildren(), arc.getChildPlacement(), propagatedStyles, path,
                    key);
        }

        // create a controller for the rounded rectangle and return it
        return new PSWTAdvancedPathController(path) {
            public void setBounds(final PBounds bounds) {
                // apply the bounds
                getNode().setPathToRoundRectangle(0, 0, (float) bounds.width,
                        (float) bounds.height, arc.getStartAngle(), arc.getArcAngle());
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
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<PSWTText> createText(final KText text, final Styles styles,
            final List<KStyle> propagatedStyles, final PNode parent, final PBounds initialBounds,
            final Object key) {
        // create the text
        PSWTText textNode = new PSWTText(text.getText() != null ? text.getText() : "");
        textNode.setGreekColor(null);
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
            handleChildren(text.getChildren(), text.getChildPlacement(), propagatedStyles,
                    textNode, key);
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
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<PSWTAdvancedPath> createPolyline(final KPolyline polyline,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds, final Object key) {
        // create the polyline
        final PSWTAdvancedPath path =
                PSWTAdvancedPath.createPolyline(PlacementUtil.evaluatePolylinePlacement(
                        PlacementUtil.asPolylinePlacementData(polyline.getPlacementData()),
                        initialBounds));
        initializeRenderingNode(path);
        path.translate(initialBounds.x, initialBounds.y);
        parent.addChild(path);

        // handle children
        if (polyline.getChildren().size() > 0) {
            List<KRendering> restChildren = Lists.newLinkedList();
            for (final KRendering rendering : polyline.getChildren()) {
                if (PlacementUtil.asDecoratorPlacementData(rendering.getPlacementData()) != null) {
                    handleDecoratorPlacementRendering(rendering, propagatedStyles, path, key);
                } else {
                    restChildren.add(rendering);
                }
            }
            
            // handle children without decorator placement data if any
            if (restChildren.size() > 0) {
                // create a proxy parent for the children without decorator placement data
                final PNode proxyParent = new PEmptyNode();
                path.addChild(proxyParent);
                NodeUtil.applySmartBounds(proxyParent, path.getBoundsReference());
                addListener(PNode.PROPERTY_BOUNDS, path, proxyParent, new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent arg0) {
                        NodeUtil.applySmartBounds(proxyParent, path.getBoundsReference());
                    }
                });
                
                handleChildren(restChildren, polyline.getChildPlacement(), propagatedStyles,
                        proxyParent, key);
            }
        }

        // create a controller for the polyline and return it
        return new PSWTAdvancedPathController(path) {
            public void setBounds(final PBounds bounds) {
                // apply the bounds
                getNode().setPathToPolyline(
                        (PlacementUtil.evaluatePolylinePlacement(
                                PlacementUtil.asPolylinePlacementData(polyline.getPlacementData()),
                                bounds)));
                NodeUtil.applyTranslation(getNode(), (float) bounds.x, (float) bounds.y);
            }
        };
    }
    
    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KPolygon}.
     * 
     * @param polygon
     *            the polygon rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<PSWTAdvancedPath> createPolygon(final KPolygon polygon,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds, final Object key) {
        // create the polygon
        final PSWTAdvancedPath path =
                PSWTAdvancedPath.createPolygon(PlacementUtil.evaluatePolylinePlacement(
                        PlacementUtil.asPolylinePlacementData(polygon.getPlacementData()),
                        initialBounds));
        initializeRenderingNode(path);
        path.translate(initialBounds.x, initialBounds.y);
        parent.addChild(path);
        
        // handle children
        if (polygon.getChildren().size() > 0) {
            List<KRendering> restChildren = Lists.newLinkedList();
            for (final KRendering rendering : polygon.getChildren()) {
                if (PlacementUtil.asDecoratorPlacementData(rendering.getPlacementData()) != null) {
                    handleDecoratorPlacementRendering(rendering, propagatedStyles, path, key);
                } else {
                    restChildren.add(rendering);
                }
            }
            
            // handle children without decorator placement data if any
            if (restChildren.size() > 0) {
                // create a proxy parent for the children without decorator placement data
                final PNode proxyParent = new PEmptyNode();
                path.addChild(proxyParent);
                NodeUtil.applySmartBounds(proxyParent, path.getBoundsReference());
                addListener(PNode.PROPERTY_BOUNDS, path, proxyParent, new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent arg0) {
                        NodeUtil.applySmartBounds(proxyParent, path.getBoundsReference());
                    }
                });
                
                handleChildren(restChildren, polygon.getChildPlacement(), propagatedStyles,
                        proxyParent, key);
            }
        }

        // create a controller for the polyline and return it
        return new PSWTAdvancedPathController(path) {
            public void setBounds(final PBounds bounds) {
                // apply the bounds
                getNode().setPathToPolygon(
                        (PlacementUtil.evaluatePolylinePlacement(
                                PlacementUtil.asPolylinePlacementData(polygon.getPlacementData()),
                                bounds)));
                NodeUtil.applyTranslation(getNode(), (float) bounds.x, (float) bounds.y);
            }
        };
    }

    /**
     * Creates a representation for the {@code KRenderingRef}.
     * 
     * @param renderingReference
     *            the rendering reference
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<?> createRenderingReference(final KRenderingRef renderingReference,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds, final Object key) {
        KRendering rendering = renderingReference.getRendering();
        if (rendering != null) {
            List<KStyle> renderingStyles = rendering.getStyles();

            // determine the styles for this rendering
            final Styles refStyles = deriveStyles(styles, renderingStyles);

            // determine the styles for propagation to child nodes
            final List<KStyle> childPropagatedStyles =
                    determinePropagationStyles(renderingStyles, propagatedStyles);

            // create a key for this reference
            Object refKey = new Object();
            setMappedProperty(renderingReference, KEY, key, refKey);

            // dispatch the rendering
            final PNodeController<?> controller =
                    createRendering(rendering, refStyles, childPropagatedStyles, parent,
                            initialBounds, refKey);

            // set the styles for the created rendering node using the controller
            applyStyles(controller, refStyles);

            // remember the controller in the rendering
            setMappedProperty(rendering, CONTROLLER, refKey, controller);

            // return a controller for the reference which sets the bounds of the referenced node
            return new PNodeController<PNode>(controller.getNode()) {
                public void setBounds(final PBounds bounds) {
                    controller.setBounds(bounds);
                }
            };
        } else {
            // create a dummy node
            return createDummy(parent, initialBounds);
        }
    }
    
    /**
     * Creates a representation for the {@code KImage}.
     * 
     * @param image
     *            the image rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<?> createImage(final KImage image, final Styles styles,
            final List<KStyle> propagatedStyles, final PNode parent, final PBounds initialBounds,
            final Object key) {
        // TODO implement this and return a real node controller
        return createDummy(parent, initialBounds);
    }
    
    /**
     * Creates a representation for the {@code KCustomRendering}.
     * 
     * @param customRendering
     *            the custom rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @param key
     *            the key used to identify the current reference hierarchy
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<?> createCustomRendering(final KCustomRendering customRendering,
            final Styles styles, final List<KStyle> propagatedStyles, final PNode parent,
            final PBounds initialBounds, final Object key) {
        // TODO implement this and return a real node controller
        return createDummy(parent, initialBounds);
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
    protected PNodeController<?> createChildArea(final PNode parent, final PBounds initialBounds) {
        throw new RuntimeException(
                "Child area found in graph element which does not support a child area: " + element);
    }
    
    /**
     * Creates a dummy node.
     * 
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    protected PNodeController<?> createDummy(final PNode parent, final PBounds initialBounds) {
        final PNode dummyChild = new PEmptyNode();
        NodeUtil.applySmartBounds(dummyChild, initialBounds);
        parent.addChild(dummyChild);
        return new PNodeController<PNode>(dummyChild) {
            public void setBounds(final PBounds bounds) {
                NodeUtil.applySmartBounds(dummyChild, bounds);
            }
        };
    }

    /**
     * Sets default values for the given Piccolo node used as representation for a rendering.
     * 
     * @param node
     *            the Piccolo node
     */
    protected void initializeRenderingNode(final PNode node) {
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
    protected void addListener(final String property, final PNode parent, final PNode node,
            final PropertyChangeListener listener) {
        parent.addPropertyChangeListener(property, listener);
        @SuppressWarnings("unchecked")
        List<Pair<String, PropertyChangeListener>> listeners =
                (List<Pair<String, PropertyChangeListener>>) node
                        .getAttribute(PROPERTY_LISTENER_KEY);
        if (listeners == null) {
            listeners = Lists.newLinkedList();
            node.addAttribute(PROPERTY_LISTENER_KEY, listeners);
        }
        listeners.add(new Pair<String, PropertyChangeListener>(property, listener));
    }

    /**
     * Removes a node as listener from its parent.
     * 
     * @param node
     *            the child node
     */
    protected void removeListeners(final PNode node) {
        @SuppressWarnings("unchecked")
        List<Pair<String, PropertyChangeListener>> listeners =
                (List<Pair<String, PropertyChangeListener>>) node
                        .getAttribute(PROPERTY_LISTENER_KEY);
        if (listeners != null && node.getParent() != null) {
            for (Pair<String, PropertyChangeListener> pair : listeners) {
                node.getParent().removePropertyChangeListener(pair.getFirst(), pair.getSecond());
            }
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
    protected List<KStyle> determineRenderingStyles(final List<KStyle> renderingStyles,
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
    protected List<KStyle> determinePropagationStyles(final List<KStyle> renderingStyles,
            final List<KStyle> propagatedStyles) {
        List<KStyle> propagationStyles = Lists.newLinkedList();
        propagationStyles.addAll(propagatedStyles);
        for (KStyle style : renderingStyles) {
            if (style.isPropagateToChildren()) {
                propagationStyles.add(style);
            }
        }
        return propagationStyles;
    }

    /**
     * Enhances a styles container with a list of styles.
     * 
     * @param styles
     *            the styles container or null to create an empty one
     * @param styleList
     *            the list of styles
     * @return the styles container
     */
    protected Styles deriveStyles(final Styles styles, final List<KStyle> styleList) {
        final Styles theStyles;
        if (styles != null) {
            theStyles = styles;
        } else {
            theStyles = new Styles();
        }
        for (KStyle style : styleList) {
            new KRenderingSwitch<Boolean>() {
                // foreground color
                public Boolean caseKForegroundColor(final KForegroundColor fc) {
                    if (theStyles.foregroundColor == null) {
                        theStyles.foregroundColor = fc;
                    }
                    return true;
                }

                // background color
                public Boolean caseKBackgroundColor(final KBackgroundColor bc) {
                    if (theStyles.backgroundColor == null) {
                        theStyles.backgroundColor = bc;
                    }
                    return true;
                }

                // line width
                public Boolean caseKLineWidth(final KLineWidth lw) {
                    if (theStyles.lineWidth == null) {
                        theStyles.lineWidth = lw;
                    }
                    return true;
                }

                // foreground visibility
                public Boolean caseKForegroundVisibility(
                        final KForegroundVisibility foregorundVisibility) {
                    if (theStyles.foregroundVisibility == null) {
                        theStyles.foregroundVisibility = foregorundVisibility;
                    }
                    return true;
                }

                // background visibility
                public Boolean caseKBackgroundVisibility(
                        final KBackgroundVisibility backgroundVisibility) {
                    if (theStyles.backgroundVisibility == null) {
                        theStyles.backgroundVisibility = backgroundVisibility;
                    }
                    return true;
                }

                // line style
                public Boolean caseKLineStyle(final KLineStyle ls) {
                    if (theStyles.lineStyle == null) {
                        theStyles.lineStyle = ls;
                    }
                    return true;
                }

                // horizontal alignment
                public Boolean caseKHorizontalAlignment(final KHorizontalAlignment ha) {
                    if (theStyles.horizontalAlignment == null) {
                        theStyles.horizontalAlignment = ha;
                    }
                    return true;
                }

                // vertical alignment
                public Boolean caseKVerticalAlignment(final KVerticalAlignment va) {
                    if (theStyles.verticalAlignment == null) {
                        theStyles.verticalAlignment = va;
                    }
                    return true;
                }

                // font name
                public Boolean caseKFontName(final KFontName fontName) {
                    if (theStyles.fontName == null) {
                        theStyles.fontName = fontName;
                    }
                    return true;
                }

                // font size
                public Boolean caseKFontSize(final KFontSize fontSize) {
                    if (theStyles.fontSize == null) {
                        theStyles.fontSize = fontSize;
                    }
                    return true;
                }

                // italic
                public Boolean caseKFontItalic(final KFontItalic italic) {
                    if (theStyles.italic == null) {
                        theStyles.italic = italic;
                    }
                    return true;
                }

                // bold
                public Boolean caseKFontBold(final KFontBold bold) {
                    if (theStyles.bold == null) {
                        theStyles.bold = bold;
                    }
                    return true;
                }
            } /**/.doSwitch(style);
        }
        return theStyles;
    }

    /**
     * Applies the styles to the node associated with the given node controller.
     * 
     * @param controller
     *            the node controller
     * @param styles
     *            the styles
     */
    protected void applyStyles(final PNodeController<?> controller, final Styles styles) {
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
     * Sets a value for a key in a given property holder using a specified property for a map type.
     * 
     * @param propertyHolder
     *            the property holder
     * @param property
     *            the property
     * @param key
     *            the key
     * @param value
     *            the value
     * @param <R>
     *            the value-type of the map
     */
    protected <R> void setMappedProperty(final IPropertyHolder propertyHolder,
            final IProperty<Map<Object, R>> property, final Object key, final R value) {
        Map<Object, R> map = propertyHolder.getProperty(property);
        if (map == null) {
            map = Maps.newHashMap();
            propertyHolder.setProperty(property, map);
        }
        map.put(key, value);
        
        // track this mapping
        List<Pair<IPropertyHolder, Object>> mappedPropertyList = mappedProperties.get(property);
        if (mappedPropertyList == null) {
            mappedPropertyList = Lists.newLinkedList();
            mappedProperties.put(property, mappedPropertyList);
        }
        mappedPropertyList.add(new Pair<IPropertyHolder, Object>(propertyHolder, key));
    }

    /**
     * Returns a value for a key in a given property holder using a specified property for a map
     * type.
     * 
     * @param propertyHolder
     *            the property holder
     * @param property
     *            the property
     * @param key
     *            the key
     * @param <R>
     *            the value-type of the map
     * @return the value
     */
    protected <R> R getMappedProperty(final IPropertyHolder propertyHolder,
            final IProperty<Map<Object, R>> property, final Object key) {
        Map<Object, R> map = propertyHolder.getProperty(property);
        if (map != null) {
            return map.get(key);
        }
        return null;
    }

    /**
     * Removes the key in a given property holder using a specified property for a map.
     * 
     * @param propertyHolder
     *            the property holder
     * @param property
     *            the property
     * @param key
     *            the key
     * @param <R>
     *            the value-type of the map
     */
    private <R> void removeMappedProperty(final IPropertyHolder propertyHolder,
            final IProperty<Map<Object, R>> property, final Object key) {
        Map<Object, R> map = propertyHolder.getProperty(property);
        if (map != null) {
            map.remove(key);
            if (map.isEmpty()) {
                propertyHolder.setProperty(property, null);
            }
        }
    }

    /**
     * Removes all mapped properties used in this controller from the associated property holders.
     * 
     * @param property
     *            the property
     * @param <R>
     *            the value-type of the map
     */
    private <R> void removeMappedProperties(final IProperty<Map<Object, R>> property) {
        List<Pair<IPropertyHolder, Object>> mappedPropertyList = mappedProperties.get(property);
        if (mappedPropertyList != null) {
            for (Pair<IPropertyHolder, Object> pair : mappedPropertyList) {
                removeMappedProperty(pair.getFirst(), property, pair.getSecond());
            }
            mappedProperties.remove(property);
        }
    }

    /**
     * A container class for the possible styles.
     */
    protected class Styles {

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
