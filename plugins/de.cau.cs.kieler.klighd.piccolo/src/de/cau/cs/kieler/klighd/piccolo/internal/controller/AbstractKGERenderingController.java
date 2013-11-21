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
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.impl.IPropertyToObjectMapImpl;
import de.cau.cs.kieler.core.krendering.KAreaPlacementData;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KPlacement;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPointPlacementData;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KRenderingUtil;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.IStyleModifier.StyleModificationContext;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.GridPlacementUtil;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IGraphElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KDecoratorNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.util.PiccoloPlacementUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.util.PiccoloPlacementUtil.Decoration;
import de.cau.cs.kieler.klighd.piccolo.internal.util.Styles;
import de.cau.cs.kieler.klighd.util.CrossDocumentContentAdapter;
import de.cau.cs.kieler.klighd.util.KlighdPredicates;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.ModelingUtil;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PPath;

/**
 * The abstract base class for controllers that manages the transformation of a dedicated
 * {@link KGraphElement}'s KRendering data to Piccolo2D nodes and the synchronization of those Piccolo2D
 * nodes with the KRendering specification.
 * 
 * @author mri
 * @author chsch
 * 
 * @param <S>
 *            the type of the underlying graph element
 * @param <T>
 *            the type of the Piccolo2D node representing the graph element
 */
public abstract class AbstractKGERenderingController
    <S extends KGraphElement, T extends IGraphElement<S>> {

    /**
     * A map that tracks the {@link PNodeController PNodeControllers} that are deployed to manage
     * the {@link PNode PNodes} representing the {@link KRendering} structure over the life cycle of
     * the diagram.<br>
     * The map is populated while initializing/updating the rendering representation (i.e. the
     * {@link PNode PNodes} of a {@link KGraphElement}. Entries are removed when {@link KRendering}
     * objects are removed from their containers, see {@link #installRenderingSyncAdapter()}.<br>
     * The map is cleared if the whole {@link KGraphElement} is removed and this controller is
     * disposed, see references of {@link #removeAllPNodeControllers()}.
     */
    private final Multimap<KRendering, PNodeController<? extends PNode>> pnodeControllers
            = ArrayListMultimap.create();
    
    /**
     * This attribute key is used to let the PNodes be aware of their related KRenderings in their
     * attributes list. It is used in the KlighdActionEventHandler, for example.
     */
    public static final Object ATTR_KRENDERING = new Object();

    /** the graph element whose rendering is controlled by this controller. */
    private S element;
    
    /** the rendering currently in use by this controller. */
    private KRendering currentRendering;

    /** the Piccolo2D node representing the node. */
    private T repNode;
    
    /** the Piccolo2D node representing the rendering. */
    private PNode renderingNode = null;

    /** the adapter currently installed on the rendering. */
    private CrossDocumentContentAdapter renderingDeepAdapter = null;
    
    /**
     * An adapter on the graph element that is supposed to react on changes in the 'data' field. It
     * is sensitive to additions, exchanges, and removals of top level {@link KRendering} data.
     */
    private Adapter elementAdapter = null;

    /** whether to synchronize the rendering with the model. */
    private boolean syncRendering = false;

    /**
     * A flag indicating the availability of {@link KStyle KStyles} with valid modifier ids in
     * {@link #currentRendering}.
     */
    private boolean modifiableStylesPresent = false;
    
    /**
     * A flag indicating the availability of {@link KStyle KStyles} with {@link KStyle#isSelection()}
     * returns <code>true<code>.
     */
    private boolean selectionStylesPresent = false;
    
    /**
     * Constructs a rendering controller.
     * 
     * @param element
     *            the graph element for this controller
     * @param repNode
     *            the Piccolo2D node representing the graph element
     */
    public AbstractKGERenderingController(final S element, final T repNode) {
        this.element = element;
        this.repNode = repNode;
        this.repNode.setRenderingController(this);
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
     * Returns the Piccolo2D node representing the underlying graph element.
     * 
     * @return the Piccolo2D node
     */
    public T getRepresentation() {
        return repNode;
    }

    /**
     * Determines and returns the rendering currently managed by this controller.<br>
     * As a side effect this method puts the returned rendering into the 'currentRendering' field.
     * 
     * @return the rendering
     */
    public KRendering getCurrentRendering() {
        if (this.element instanceof KNode) {
            Iterable<KRendering> renderings = Iterables.filter(element.getData(), KRendering.class);
            
//            // in case the node to be depicted has no children (yet - might be added lazily)
//            // look for a rendering marked as 'collapsed' one,
//            //  and if none exists simply take the first one
//            if (((KNode) element).getChildren().isEmpty()) {   
//                currentRendering = Iterables.getFirst(
//                        Iterables.filter(renderings, IS_COLLAPSED_RENDERING),
//                        Iterables.getFirst(renderings, null));
//            } else
                
            // in case the node to be depicted has children and is populated,
            //  i.e. children are depicted in the diagram
            // look for a rendering marked as 'expanded' one,
            //  and if none exists take the first one that is not marked as 'collapsed' one
            if (RenderingContextData.get(this.element).getProperty(
                    KlighdInternalProperties.POPULATED)) {
                currentRendering = Iterables.getFirst(
                        Iterables.filter(renderings, IS_EXPANDED_RENDERING),
                        Iterables.getFirst(Iterables.filter(renderings,
                                Predicates.not(IS_COLLAPSED_RENDERING)), null));

            // in case the node to be depicted has children and is not populated,
            //  i.e. no children are visible in the diagram
            // look for a rendering marked as 'collapsed' one,
            //  and if none exists take the first one that is not marked as 'expanded' one
            } else {
                currentRendering = Iterables.getFirst(
                        Iterables.filter(renderings, IS_COLLAPSED_RENDERING),
                        Iterables.getFirst(Iterables.filter(renderings,
                                Predicates.not(IS_EXPANDED_RENDERING)), null));
            }

        } else {
            currentRendering = element.getData(KRendering.class);
        }
        
        if (currentRendering == null) {
            currentRendering = createDefaultRendering();
        }
        
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
     * Getter.
     * 
     * @return the selection state of the current root rendering
     */
    protected boolean isSelected() {
        return currentRendering == null
                ? false : currentRendering.getProperty(KlighdInternalProperties.SELECTED);
    }
    
    
    /**
     * Convenience etter.
     * 
     * @param kText
     *            the {@link KText} element to check for selection.
     * 
     * @return the selection state of the given {@link KText} rendering
     */
    private boolean isSelected(final KText kText) {
        return kText == null ? false : kText.getProperty(KlighdInternalProperties.SELECTED);
    }
    
    
    /**
     * Fires a run of the {@link de.cau.cs.kieler.klighd.IStyleModifier IStyleModifiers} referenced
     * by the {@link KStyle KStyles} attached to this {@link KGraphElement}'s rendering and updates
     * the diagram figure, both if and only if {@link KStyles} with valid modifiers are present in
     * this {@link KGraphElement}'s rendering.
     */
    public void modifyStyles() {
        if (modifiableStylesPresent) {
            updateStyles();
        }
    };
    

    // ---------------------------------------------------------------------------------- //
    //  internal part
    
    /**
     * A predicate used to identify the KRendering of a KNode in case the node is collapsed.
     * This predicate is also used by the {@link DiagramController} and thus marked
     * 'package protected' (no modifier).
     */
    static final Predicate<KRendering> IS_COLLAPSED_RENDERING = new Predicate<KRendering>() {
        public boolean apply(final KRendering rendering) {
            return rendering.getProperty(KlighdProperties.COLLAPSED_RENDERING);
        }
    };
    
    /**
     * A predicate used to identify the KRendering of a KNode in case the node is expanded.
     * This predicate is also used by the {@link DiagramController} and thus marked
     * 'package protected' (no modifier).
     */
    static final Predicate<KRendering> IS_EXPANDED_RENDERING = new Predicate<KRendering>() {
        public boolean apply(final KRendering rendering) {
            return rendering.getProperty(KlighdProperties.EXPANDED_RENDERING);
        }
    };

    /**
     * Updates the rendering by removing the current rendering and evaluating the rendering data
     * attached to the graph element.
     */
    private void updateRendering() {
        // remove the rendering adapter
        if (currentRendering != null) {
            unregisterElementAdapter();
            unregisterRenderingAdapter();
            removeAllPNodeControllers();
        }

        // remove the rendering node
        if (renderingNode != null) {
            removeListeners(renderingNode);
            renderingNode.removeFromParent();
            renderingNode = null;
        }

        // get the current rendering
        //  this call updates the 'currentRendering' field
        getCurrentRendering();
        
        // reset that flag as potentially available styles with a modifier might be removed now
        modifiableStylesPresent = false;

        // update the rendering
        renderingNode = internalUpdateRendering();

        // install rendering adapter if sync is enabled
        if (syncRendering) {
            // register an adapter on the element (KGE) to stay in sync
            registerElementAdapter();

            if (currentRendering != null) {
                // register an adapter on the rendering to stay in sync
                installRenderingSyncAdapter();
            }
        }
    }

    /**
     * Creates default rendering corresponding to the type of {@link #element}.
     * 
     * @return a default {@link KRendering}
     */
    protected abstract KRendering createDefaultRendering();
    
    /**
     * Performs the actual update of the rendering.
     * 
     * @return the Piccolo2D node representing the current rendering
     */
    protected abstract PNode internalUpdateRendering();

    /**
     * Registers an adapter on the graph element to react on changes in its graph data feature.
     * This on is sensitive to additions, exchanges, and removals of {@link KRendering} data.
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
                            updateRenderingInUi();
                        }
                        break;
                    default:
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
     * Registers an adapter on the current rendering to react on changes.
     */
    private void installRenderingSyncAdapter() {
        // register adapter on the rendering to stay in sync
        renderingDeepAdapter = new CrossDocumentContentAdapter() {

            protected boolean shouldAdapt(final EStructuralFeature feature) {
                // follow the rendering feature of the KRenderingRef
                return feature.getFeatureID() == KRenderingPackage.KRENDERING_REF__RENDERING
                        || feature.getFeatureID() == KRenderingPackage.KSTYLE_REF__STYLE_HOLDER;
            }

            public void notifyChanged(final Notification msg) {
                super.notifyChanged(msg);

                // iProperties and mappings are now in the update scope but we do not need them for
                // rendering
                if (msg.getNotifier() instanceof IPropertyToObjectMapImpl
                        || msg.getNewValue() instanceof IProperty<?>
                        || msg.getOldValue() instanceof IProperty<?>
                        || msg.getNewValue() instanceof Map.Entry<?, ?>
                        || msg.getOldValue() instanceof Map.Entry<?, ?>) {
                    final Map.Entry<?, ?> entry;
                    if (msg.getNotifier() instanceof IPropertyToObjectMapImpl) {
                        entry = (Map.Entry<?, ?>) msg.getNotifier();
                    } else if (msg.getNewValue() instanceof Map.Entry<?, ?>) {
                        entry = (Map.Entry<?, ?>) msg.getNewValue();
                    } else if (msg.getOldValue() instanceof Map.Entry<?, ?>) {
                        entry = (Map.Entry<?, ?>) msg.getOldValue();
                    } else {
                        entry = null;
                    }
                    if (entry != null && entry.getKey() == KlighdInternalProperties.SELECTED) {
                        updateStylesInUi();
                    }
                    return;
                }
                
                Iterable<KRendering> allRemovedRenderings = Collections.emptyList();
                switch (msg.getEventType()) {
                case Notification.REMOVE_MANY:
                    Iterable<?> removed = (Iterable<?>) msg.getOldValue();
                    if (Iterables.any(removed, Predicates.instanceOf(KStyle.class))) {
                        updateStylesInUi();
                        return;
                    }
                    
                    final Iterable<KRendering> removedRenderings = Iterables.filter(
                            removed, KRendering.class);
                    
                    allRemovedRenderings = Iterables.concat(Iterables.transform(
                            removedRenderings, new Function<KRendering, Iterable<KRendering>>() {
                                public Iterable<KRendering> apply(final KRendering r) {
                                    return ModelingUtil.selfAndEAllContentsOfSameType(r);
                                }
                            }));
                    // there is no break here by intention !
                    
                case Notification.REMOVE:
                    if (msg.getOldValue() instanceof KStyle) {
                        updateStylesInUi();
                        return;
                    }
                    
                    if (msg.getOldValue() instanceof KRendering) {
                        allRemovedRenderings = ModelingUtil
                                .selfAndEAllContentsOfSameType((KRendering) msg.getOldValue());
                    }
                    for (KRendering r : allRemovedRenderings) {
                        removePNodeController(r);
                    }
                    // there is no break here by intention !
                    
                case Notification.UNSET:
                case Notification.SET:
                case Notification.MOVE:
                case Notification.ADD:
                case Notification.ADD_MANY:

                    // Attention: Don't add 'newValue == null' as this will forbid to remove
                    // styles!!

                    // handle style changes
                    if (msg.getNotifier() instanceof KStyle || msg.getNotifier() instanceof KColor) {
                        updateStylesInUi();
                        return;
                    }

                    // handle new, moved and removed styles
                    // Caution: Due to multi-inheritance of the KRendering class (interface)
                    // KRenderingPackage.KRENDERING__STYLES differs from
                    // KRenderingPackage.KSTYLE_HOLDER__STYLES !!
                    if (msg.getNotifier() instanceof KRendering
                            && msg.getFeatureID(KRendering.class)
                               == KRenderingPackage.KRENDERING__STYLES) {
                        updateStylesInUi();
                        return;
                    }

                    // handle other changes by reevaluating the rendering
                    updateRenderingInUi();
                    break;
                default:
                    break;
                }
            }
        };

        // add the adapter to the rendering
        currentRendering.eAdapters().add(renderingDeepAdapter);
    }

    /**
     * Unregisters the adapter currently installed on the rendering.
     */
    private void unregisterRenderingAdapter() {
        if (currentRendering != null && renderingDeepAdapter != null) {
            currentRendering.eAdapters().remove(renderingDeepAdapter);
            renderingDeepAdapter = null;
        }
    }

    /**
     * A little helper reducing the 'syncExec' calls if possible.
     * 
     * @param r
     *            the runnable to be performed in the UI context.
     */
    private static void runInUI(final Runnable r) {
        if (Display.getCurrent() != null) {
            r.run();
        } else {
            PlatformUI.getWorkbench().getDisplay().syncExec(r);
        }
    }

    /**
     * A re-usable {@link Runnable} to be executed in UI context wrapping {@link #updateRendering()}.
     */
    private Runnable updateRenderingRunnable = new Runnable() {
        public void run() {
            updateRendering();
        }
    };

    /**
     * A short convenience method for invoking {@link #updateRendering()} in UI context.
     */
    private void updateRenderingInUi() {
        runInUI(this.updateRenderingRunnable);
    }

    /**
     * A re-usable {@link Runnable} to be executed in UI context wrapping {@link #updateStyles()}.
     */
    private Runnable updateStylesRunnable = new Runnable() {
        public void run() {
            updateStyles();
        }
    };

    /**
     * A short convenience method for invoking {@link #updateStyles()} in UI context.
     */
    private void updateStylesInUi() {
        runInUI(this.updateStylesRunnable);
    }


    /* ----------------------------------------------------------------------------------- 
     * The style evaluation methods: 
     * ----------------------------------------------------------------------------------- */

    /**
     * Updates the styles of the {@link PNode PNodes} representing {@link #currentRendering}.
     */
    private void updateStyles() {
        // reset that flag as potentially available styles with a modifier might be removed now
        modifiableStylesPresent = false;
        
        final boolean isSelected = this.isSelected();
        if (isSelected) {
            final Iterator<KRendering> renderings = Iterators.filter(
                    KRenderingUtil.selfAndAllChildren(this.currentRendering),
                    KlighdPredicates.notInstanceOf(KText.class));
            final Iterator<KStyle> styles = Iterators.concat(Iterators.transform(renderings,
                    new Function<KRendering, Iterator<KStyle>>() {
                        public Iterator<KStyle> apply(final KRendering rendering) {
                            return rendering.getStyles().iterator();
                        }
                    }));
            
            selectionStylesPresent = Iterators.any(styles, KlighdPredicates.isSelection());
        }

        // update using the recursive method
        updateStyles(currentRendering, isSelected, Collections.<KStyle>emptyList());

        // in case styles of a detached KRendering are modified, e.g. if selection highlighting
        //  is removed from renderings that are not part of the diagram in the meantime
        //  'null' values may occur here 
        for (PNodeController<?> nodeController : getPNodeController(currentRendering)) {
            final PNode node = nodeController.getNode();
            if (node != null) {
                node.invalidatePaint();
            }
        }
    }
    
    /**
     * Recursively updates the styles of the {@link PNode PNodes} representing <code>rendering</code>.
     */
    private void updateStyles(final KRendering rendering, final boolean isSelected,
            final List<KStyle> propagatedStyles) {

        final Collection<PNodeController<?>> controllers = getPNodeController(rendering);
        if (controllers == null || controllers.isEmpty()) {
            // in case 'rendering' is not represented by any node and, thus, no pnodeController exists,
            //  stop here
            return;
        }

        if (rendering instanceof KRenderingRef) {
            // update referenced rendering
            KRenderingRef renderingRef = (KRenderingRef) rendering;

            // get the referenced rendering
            KRendering referencedRendering = renderingRef.getRendering();

            // proceed recursively with the referenced rendering
            updateStyles(referencedRendering, isSelected,
                    Lists.newLinkedList(Iterables.concat(rendering.getStyles(), propagatedStyles)));
            
            return;
        }
        
        processModifiableStyles(rendering.getStyles());
        
        // determine the styles for this rendering
        final Styles styles = prepareStylesRecord(rendering, propagatedStyles, isSelected);
        
        // apply the styles to the rendering
        for (PNodeController<?> controller : controllers) {
            controller.applyChanges(styles);
        }
        
        if (rendering instanceof KContainerRendering) {
            List<KStyle> childPropagatedStyles = null;
            
            // update children
            final KContainerRendering container = (KContainerRendering) rendering;            
            if (container.getChildren().size() > 0) {
                // determine the styles for propagation to child nodes
                 childPropagatedStyles = determinePropagationStyles(
                         rendering.getStyles(), propagatedStyles);

                // propagate to all children
                for (KRendering child : container.getChildren()) {
                    updateStyles(child, isSelected, childPropagatedStyles);
                }
            }
            
            if (rendering instanceof KPolyline) {
                final KPolyline polyline = (KPolyline) rendering;
                
                KRendering jpr = polyline.getJunctionPointRendering();
                if (jpr != null) {

                    if (childPropagatedStyles == null) {
                        childPropagatedStyles = determinePropagationStyles(
                                rendering.getStyles(), propagatedStyles);
                    }
                    
                    updateStyles(jpr, isSelected, childPropagatedStyles);
                }
            }
        }
    }
    
    /**
     * Prepares a {@link Styles} with those styles to be applied to the Piccolo2D node representing
     * <code>rendering</code>.
     */
    private Styles prepareStylesRecord(final KRendering rendering, final List<KStyle> propagatedStyles,
            final boolean isSelected) {
        final Styles styles = new Styles();
        
        if (rendering instanceof KText) {
            styles.deriveStyles(rendering, propagatedStyles, isSelected((KText) rendering), false, null);
        } else {
            styles.deriveStyles(rendering, propagatedStyles, isSelected,
                    !this.selectionStylesPresent, this.currentRendering);
        }
        return styles;
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
//        propagationStyles.addAll(propagatedStyles);
        for (KStyle style : Iterables.concat(propagatedStyles, renderingStyles)) {
            if (style.isPropagateToChildren()) {
                propagationStyles.add(style);
            }
        }
        return propagationStyles;
    }

    /** 
     * A filter that lets only styles with a valid modifier id pass.  
     */
    private static final Predicate<KStyle> MODIFIED_STYLE_FILTER = new Predicate<KStyle>() {
        public boolean apply(final KStyle style) {
            return !Strings.isNullOrEmpty(style.getModifierId())
                    && KlighdDataManager.getInstance()
                        .getStyleModifierById(style.getModifierId()) != null;
        }
    };
    
    private final StyleModificationContext singletonModContext = new StyleModificationContext();

    private void processModifiableStyles(final List<KStyle> styles) {
        final Iterable<KStyle> localModifiedStyles = Iterables.filter(styles,
                MODIFIED_STYLE_FILTER);
        modifiableStylesPresent |= localModifiedStyles.iterator().hasNext();

        final KLayoutData layoutData = getGraphElement().getData(KLayoutData.class);
        if (layoutData == null) {
            return;
        }
        
        boolean deliver;
        for (KStyle s: localModifiedStyles) {
            deliver  = s.eDeliver();
            s.eSetDeliver(false);
            KlighdDataManager.getInstance().getStyleModifierById(s.getModifierId()).modify(
                    singletonModContext.configure(s, layoutData));
            s.eSetDeliver(deliver);
        }
    }


    /* ----------------------------------------------------------------------------------- 
     *
     * The PNode creation methods: 
     * They're called from sub the classes of this one, i.e. the KNodeRenderingController,
     *  KPortRenderingController, KEdgeRenderingController, and KLabelRenderinController.
     *
     * ----------------------------------------------------------------------------------- */

    /**
     * Creates the Piccolo2D nodes for a list of renderings inside a parent Piccolo2D node for the given
     * placement.
     * 
     * @param children
     *            the list of children
     * @param placement
     *            the placement
     * @param styles
     *            the styles propagated to the children
     * @param parent
     *            the parent Piccolo2D node
     */
    protected void handleChildren(final List<KRendering> children, final KPlacement placement,
            final List<KStyle> styles, final PNode parent) {
        if (placement instanceof KGridPlacement) {
            // in case of grid-based child placement ...
            handleGridPlacementRendering((KGridPlacement) placement, children, styles, parent);
        } else {
            // otherwise, i.e. in case of point-based and area-based child placement ...
            for (final KRendering rendering : children) {
                handleAreaAndPointPlacementRendering(rendering, styles, parent);
            }
        }
    }

    /**
     * Creates the Piccolo2D node for a rendering inside a parent Piccolo2D node using point- or
     * area-based child placement.
     * 
     * @param rendering
     *            the rendering
     * @param parent
     *            the parent Piccolo2D node
     * @return the Piccolo2D node representing the rendering
     */
    protected PNode handleAreaAndPointPlacementRendering(final KRendering rendering,
            final PNode parent) {
        return handleAreaAndPointPlacementRendering(rendering, Collections.<KStyle>emptyList(), parent);
    }

    /**
     * Creates the Piccolo2D node for a rendering inside a parent Piccolo2D node using point- or
     * area-based child placement.
     * 
     * @param rendering
     *            the rendering
     * @param styles
     *            the styles propagated to the children
     * @param parent
     *            the parent Piccolo2D node
     * @return the Piccolo2D node representing the rendering
     */
    protected PNode handleAreaAndPointPlacementRendering(final KRendering rendering,
            final List<KStyle> styles, final PNode parent) {
        final KPlacementData pcd = KRenderingUtil.getPlacementData(rendering);
        final KAreaPlacementData pad = KRenderingUtil.asAreaPlacementData(pcd);
        final KPointPlacementData ppd = KRenderingUtil.asPointPlacementData(pcd);
        final boolean pointPlacement = ppd != null;

        final Bounds bounds;
        if (pointPlacement) {
            bounds = PlacementUtil.evaluatePointPlacement(ppd,
                    PlacementUtil.estimateSize(rendering, Bounds.of(0, 0)),
                    parent.getBoundsReference());
        } else {
            // determine the initial bounds
            bounds = PlacementUtil.evaluateAreaPlacement(pad, parent.getBoundsReference());
        }

        // create the rendering and receive its controller
        final PNodeController<?> controller = createRendering(rendering, styles, parent, bounds);

        // add a listener on the parent's bounds
        if (pointPlacement) {
            addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                    new PropertyChangeListener() {
                        public void propertyChange(final PropertyChangeEvent e) {
                            Bounds bounds = null;
                            bounds = PlacementUtil.evaluatePointPlacement(ppd,
                                    PlacementUtil.estimateSize(rendering, Bounds.of(0, 0)),
                                    parent.getBoundsReference());
                            // use the controller to apply the new bounds
                            controller.setBounds(bounds);
                        }
                    });
        } else {
            addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                    new PropertyChangeListener() {
                        public void propertyChange(final PropertyChangeEvent e) {
                            Bounds bounds = null;
                            // calculate the new bounds of the rendering
                            bounds = PlacementUtil.evaluateAreaPlacement(pad,
                                    parent.getBoundsReference());
                            // use the controller to apply the new bounds
                            controller.setBounds(bounds);
                        }
                    });
        }

        return controller.getNode();
    }

    /**
     * Creates the Piccolo2D nodes for a list of renderings inside a parent Piccolo2D node using grid
     * placement.
     * 
     * @param gridPlacement
     *            the grid placement
     * @param renderings
     *            the renderings
     * @param styles
     *            the styles propagated to the children
     * @param parent
     *            the parent Piccolo2D node
     */
    protected void handleGridPlacementRendering(final KGridPlacement gridPlacement,
            final List<KRendering> renderings, final List<KStyle> styles, final PNode parent) {
        if (renderings.size() == 0) {
            return;
        }

        // calculate the bounds
        final Bounds parentBounds = new Bounds(parent.getBoundsReference());
        final Bounds[] elementBounds = GridPlacementUtil.evaluateGridPlacement(gridPlacement,
                renderings, parentBounds);

        // create the renderings and collect the controllers
        final PNodeController<?>[] controllers = new PNodeController<?>[renderings.size()];
        
        for (int i = 0; i < renderings.size(); i++) {
            controllers[i] = createRendering(renderings.get(i), styles, parent, elementBounds[i]);
        }

        // add a listener on the parent's bounds
        addListener(PNode.PROPERTY_BOUNDS, parent, controllers[0].getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds of the rendering
                        final Bounds parentBounds = Bounds.of(parent.getBoundsReference());
                        final Bounds[] bounds = GridPlacementUtil.evaluateGridPlacement(
                                gridPlacement, renderings, parentBounds);

                        // use the controllers to apply the new bounds
                        int i = 0;
                        for (PNodeController<?> controller : controllers) {
                            if (bounds[i] != null) {
                                controller.setBounds(bounds[i++]);
                                controller.getNode().setVisible(true);
                            } else {
                                controller.getNode().setVisible(false);
                            }
                        }
                    }
                });
    }

    /**
     * Creates the Piccolo2D node for a rendering inside a parent Piccolo2D node representing a polyline
     * using decorator placement.
     * 
     * @param rendering
     *            the rendering
     * @param styles
     *            the styles propagated to the children
     * @param parent
     *            the parent Piccolo2D node representing a polyline
     * @return the Piccolo2D node representing the rendering
     */
    protected PNode handleDecoratorPlacementRendering(final KRendering rendering,
            final List<KStyle> styles, final KlighdPath parent) {
        // determine the initial bounds and rotation
        final Decoration decoration = PiccoloPlacementUtil.evaluateDecoratorPlacement(
                PiccoloPlacementUtil.getDecoratorPlacementData(rendering), parent);

        // create an empty node for the decorator
        final KDecoratorNode decorator = new KDecoratorNode(rendering);

        // NodeUtil.applyTranslation(decorator, decoration.getOrigin());
        parent.addChild(decorator);

        // create the rendering and receive its controller
        final PNodeController<?> controller = createRendering(rendering, styles, decorator,
                decoration.getBounds());
        decorator.setRepresentationNode(controller.getNode());

        // apply the initial rotation
        decorator.setRotation(decoration.getRotation());

        // let the decorator be pickable
        decorator.setPickable(true);

        // add a listener on the parent's path
        addListener(PPath.PROPERTY_PATH, parent, controller.getNode(),
                new PropertyChangeListener() {

                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds and rotation for the rendering
                        Decoration decoration = PiccoloPlacementUtil.evaluateDecoratorPlacement(
                                PiccoloPlacementUtil.getDecoratorPlacementData(rendering), parent);

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
     * Creates the Piccolo2D node representing the rendering inside the given parent with initial
     * bounds.
     * 
     * @param rendering
     *            the rendering
     * @param parent
     *            the parent Piccolo2D node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo2D node
     */
    protected PNodeController<?> createRendering(final KRendering rendering, final PNode parent,
            final Bounds initialBounds) {
        return this.createRendering(rendering, Collections.<KStyle>emptyList(), parent, initialBounds);
    }

    private PNodeConstructionKRenderingSwitch kSwitch = new PNodeConstructionKRenderingSwitch(this);

    /**
     * Creates the Piccolo2D node representing the rendering inside the given parent with initial
     * bounds.
     * 
     * @param rendering
     *            the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering
     * @param parent
     *            the parent Piccolo2D node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo2D node
     */
    protected PNodeController<?> createRendering(final KRendering rendering,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {

        final List<KStyle> renderingStyles = rendering.getStyles();
        
        processModifiableStyles(renderingStyles);

        // determine the styles for propagation to child nodes
        final List<KStyle> childPropagatedStyles = determinePropagationStyles(renderingStyles,
                propagatedStyles);

        // create the rendering and return its controller
        kSwitch.configure(childPropagatedStyles, parent, initialBounds);
        final PNodeController<?> controller = kSwitch.doSwitch(rendering);
        
        // determine the styles for this rendering
        final Styles styles = prepareStylesRecord(rendering, propagatedStyles, this.isSelected()); 
        
        // set the styles for the created rendering node using the controller
        controller.applyChanges(styles);

        // remember the KRendering-controller pair in the controller's 'pnodeControllers' map 
        addPNodeController(rendering, controller);

        // remember the KRendering element in the PNode
        controller.getNode().addAttribute(ATTR_KRENDERING, rendering);
        
        // in case an action is attached to the KRendering make the node pickable
        //  this is only done in the PNode initialization as adding and removing actions later in life
        //  of a KRendering/PNode is considered unlikely and thus not supported yet 
        if (!rendering.getActions().isEmpty()) {
            controller.getNode().setPickable(true);
        }
        return controller;
    }
    
    /**
     * Configures the Piccolo2D node for the given {@code KChildArea}.<br>
     * <br>
     * <b>CAUTION:</b> This method is overloaded by {@link KNodeRenderingController}.
     * 
     * @param parent
     *            the parent Piccolo2D node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo2D node
     */
    protected PNodeController<?> createChildArea(final PNode parent,
            final Bounds initialBounds) {
        throw new RuntimeException(
                "Child area found in graph element which does not support a child area: "
                        + getGraphElement());
    }

    /**
     * Sets default values for the given Piccolo2D node used as representation for a rendering.
     * 
     * @param node
     *            the Piccolo2D node
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
        List<Pair<String, PropertyChangeListener>> listeners
          = (List<Pair<String, PropertyChangeListener>>) node.getAttribute(PROPERTY_LISTENER_KEY);
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
        List<Pair<String, PropertyChangeListener>> listeners
          = (List<Pair<String, PropertyChangeListener>>) node.getAttribute(PROPERTY_LISTENER_KEY);
        if (listeners != null && node.getParent() != null) {
            for (Pair<String, PropertyChangeListener> pair : listeners) {
                node.getParent().removePropertyChangeListener(pair.getFirst(), pair.getSecond());
            }
        }
    }

    /**
     * Remember a KRendering-controller pair in the controller's 'pnodeControllers' map.
     * 
     * @param key
     *            the {@link KRendering} serving as the key
     * @param value
     *            the {@link PNodeController} to be related to the <code>key</code> {@link KRendering}
     */
    void addPNodeController(final KRendering key, final PNodeController<?> value) {
        this.pnodeControllers.put(key, value);
    }

    /**
     * Returns the {@link PNodeController} value for the given {@link KRendering} key.
     * 
     * @param key
     *            the {@link KRendering} key
     * @return the related {@link PNodeController} value
     */
    public Collection<PNodeController<?>> getPNodeController(final KRendering key) {
        return this.pnodeControllers.get(key);
    }

    /**
     * Removes the key-value pair in {@link #pnodeControllers} map.
     * 
     * @param key
     *            the key
     */
    private void removePNodeController(final KRendering key) {
        this.pnodeControllers.removeAll(key);
    }
    
    /**
     * Release all key-value mappings in order to let unused objects be garbage collected.
     */
    public void removeAllPNodeControllers() {
        this.pnodeControllers.clear();
    }
}
