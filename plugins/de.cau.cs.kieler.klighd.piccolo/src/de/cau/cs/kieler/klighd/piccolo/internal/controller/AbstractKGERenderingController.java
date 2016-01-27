/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import static com.google.common.collect.Iterables.any;
import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.getFirst;
import static com.google.common.collect.Iterables.transform;

import java.awt.geom.Rectangle2D;
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

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.impl.IPropertyToObjectMapImpl;
import de.cau.cs.kieler.core.krendering.KAreaPlacementData;
import de.cau.cs.kieler.core.krendering.KChildArea;
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
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKlighdFigureNode;
import de.cau.cs.kieler.klighd.piccolo.KlighdNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IInternalKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.NodeDisposeListener;
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
import edu.umd.cs.piccolo.util.PPickPath;

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
    <S extends KGraphElement, T extends IInternalKGraphElementNode<S>> {

    /**
     * Locally used enumeration to express that the managed
     * {@link de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKGraphElementNode IKGraphElementNode}
     * shall be moved to first or last position in its container's list of children, or not at all.
     * Movement is only relevant for edges (in case of orthogonal edge routing) to have highlighted
     * ones on top and de-highlighted ones behind.
     */
    enum ElementMovement {
        NONE,
        ON_TOP,
        BACKWARD
    }

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
    private final Multimap<KRendering, PNodeController<?>> pnodeControllers = ArrayListMultimap.create();

    private DiagramController diagramController;

    /** the graph element whose rendering is controlled by this controller. */
    private S element;

    /** the rendering currently in use by this controller. */
    private KRendering currentRendering;

    /** the Piccolo2D node representing the node. */
    private T repNode;

    /** the Piccolo2D node representing the rendering. */
    private IKlighdFigureNode renderingNode = null;

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
     * Determines and returns the {@link KRendering} currently managed by <code>this</code>
     * controller.<br>
     * As a side effect this method puts the returned rendering into the 'currentRendering' field.
     *
     * @return the (potentially) updated {@link KRendering} managed by <code>this</code> controller
     */
    public KRendering getCurrentRendering() {
        if (this.element instanceof KNode) {
            final Iterable<KRendering> renderings =
                    filter(element.getData(), KRendering.class);

            // in case the node to be depicted is tagged as 'populated',
            //  i.e. children are depicted in the diagram ...
            if (RenderingContextData.get(this.element).getProperty(
                    KlighdInternalProperties.POPULATED)) {
                // ... look for a rendering tagged as 'expanded', ...
                currentRendering = getFirst(
                        filter(renderings, KlighdPredicates.isExpandedRendering()), null);

                // ... and if none exists ...
                if (currentRendering == null) {
                    // ... take the first one that is not marked as 'collapsed' one
                    currentRendering = getFirst(filter(renderings,
                            Predicates.not(KlighdPredicates.isCollapsedRendering())), null);
                }

            } else {
                // in case the node to be depicted is tagged as 'not populated',
                //  i.e. no children are visible in the diagram
                // look for a rendering marked as 'collapsed' one, ...
                currentRendering = getFirst(
                        filter(renderings, KlighdPredicates.isCollapsedRendering()), null);

                // ... and if none exists ...
                if (currentRendering == null) {
                    // ... take the first one that is not marked as 'expanded' one
                    currentRendering = getFirst(filter(renderings,
                            Predicates.not(KlighdPredicates.isExpandedRendering())), null);
                }
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
     * Returns the {@link KRendering} currently managed by <code>this</code> controller without any
     * side effect.
     *
     * @return the {@link KRendering} currently managed by <code>this</code> controller
     */
    public KRendering getCurrentRenderingReference() {
        return this.currentRendering;
    }

    /**
     * Initializes the rendering controller.
     *
     * @param diagCtrl
     *            the overall {@link DiagramController} referenced for scheduling rendering updates
     * @param sync
     *            true if the rendering should be synchronized with the model; false else
     */
    public void initialize(final DiagramController diagCtrl, final boolean sync) {
        this.diagramController = diagCtrl;
        this.syncRendering = sync;

        // do the initial update of the rendering
        updateRendering();
    }


    /**
     * Fires a run of the {@link de.cau.cs.kieler.klighd.IStyleModifier IStyleModifiers} referenced
     * by the {@link KStyle KStyles} attached to this {@link KGraphElement}'s rendering and updates
     * the diagram figure, both if and only if {@link KStyles} with valid modifiers are present in
     * this {@link KGraphElement}'s rendering.
     */
    public void modifyStyles() {
        if (modifiableStylesPresent) {
            scheduleStylesUpdate(ElementMovement.NONE);
        }
    };


    // ---------------------------------------------------------------------------------- //
    //  internal part

    /**
     * Updates the rendering by removing the current rendering and evaluating the rendering data
     * attached to the graph element.
     */
    void updateRendering() {
        // remove the rendering adapter
        if (currentRendering != null) {
            unregisterElementAdapter();
            unregisterRenderingAdapter();
            removeAllPNodeControllers();
        }

        // remove the rendering node
        if (renderingNode != null) {
            final PNode asPNode = renderingNode.asPNode();
            renderingNode = null;
            
            removeListeners(asPNode);
            asPNode.removeFromParent();

            // dispose the SWT Resources employed by the out-dated pnodes
            NodeDisposeListener.disposePNode(asPNode);
        }

        // get the current rendering
        //  this call updates the 'currentRendering' field
        getCurrentRendering();

        // reset that flag as potentially available styles with a modifier might be removed now
        modifiableStylesPresent = false;

        // update the rendering
        renderingNode = internalUpdateRendering().getTransformedNode();

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
     * @return the {@link PNodeController} managing the Piccolo2D node
     *         ({@link KlighdNode.KlighdFigureNode}) that represents {@link #currentRendering}
     */
    protected abstract PNodeController<?> internalUpdateRendering();

    /**
     * Registers an adapter on the graph element to react on changes in its graph data feature.
     * This on is sensitive to additions, exchanges, and removals of {@link KRendering} data.
     */
    private void registerElementAdapter() {
        elementAdapter = new ElementAdapter();
        element.eAdapters().add(elementAdapter);
    }

    private static final Predicate<Object> IS_KRENDERING = Predicates.instanceOf(KRendering.class);

    private static final Predicate<Object> IS_KSTYLE = Predicates.instanceOf(KStyle.class);

    /**
     * An adapter on the graph element to react on changes in its graph data feature.
     * This on is sensitive to additions, exchanges, and removals of {@link KRendering} data.
     *
     * @author chsch
     */
    private class ElementAdapter extends AdapterImpl {
        @Override
        public void notifyChanged(final Notification msg) {
            if (msg.getFeature() != KGraphPackage.Literals.KGRAPH_ELEMENT__DATA) {
                return;
            }

            switch (msg.getEventType()) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                final Object newVal = msg.getNewValue();
                if (newVal instanceof KRendering
                        || newVal instanceof Iterable<?> && any((Iterable<?>) newVal, IS_KRENDERING)) {
                    break;
                }
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                final Object oldVal = msg.getOldValue();
                if (oldVal instanceof KRendering
                        || oldVal instanceof Iterable<?> && any((Iterable<?>) oldVal, IS_KRENDERING)) {
                    break;
                }
            default:
                return;
            }

            final KRendering rendering = getCurrentRenderingReference();
            if (rendering != getCurrentRendering()) {
                // a rendering has been added or removed
                scheduleRenderingUpdate();
            }
        }
    }

    /**
     * Unregisters the adapter currently installed on the element.
     */
    void unregisterElementAdapter() {
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

            @Override
            protected boolean shouldAdapt(final EStructuralFeature feature) {
                // follow the rendering feature of the KRenderingRef
                return feature.getFeatureID() == KRenderingPackage.KRENDERING_REF__RENDERING
                        || feature.getFeatureID() == KRenderingPackage.KSTYLE_REF__STYLE_HOLDER;
            }

            @Override
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
                        scheduleStylesUpdate((Boolean) entry.getValue()
                                ? ElementMovement.ON_TOP : ElementMovement.BACKWARD);
                    }
                    return;
                }

                Iterable<KRendering> allRemovedRenderings = Collections.emptyList();
                switch (msg.getEventType()) {
                case Notification.REMOVE_MANY:
                    final Iterable<?> removed = (Iterable<?>) msg.getOldValue();

                    if (any(removed, IS_KSTYLE)) {
                        scheduleStylesUpdate(ElementMovement.BACKWARD);
                        return;
                    }

                    final Iterable<KRendering> removedRenderings = filter(removed, KRendering.class);

                    allRemovedRenderings = concat(transform(
                            removedRenderings, new Function<KRendering, Iterable<KRendering>>() {
                                public Iterable<KRendering> apply(final KRendering r) {
                                    return ModelingUtil.selfAndEAllContentsOfSameType(r);
                                }
                            }));
                    // there is no break here by intention !

                case Notification.REMOVE:
                    if (msg.getOldValue() instanceof KStyle) {
                        scheduleStylesUpdate(ElementMovement.BACKWARD);
                        return;
                    }

                    if (msg.getOldValue() instanceof KRendering) {
                        allRemovedRenderings = ModelingUtil
                                .selfAndEAllContentsOfSameType((KRendering) msg.getOldValue());
                    }
                    for (final KRendering r : allRemovedRenderings) {
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
                        scheduleStylesUpdate(ElementMovement.ON_TOP);
                        return;
                    }

                    // handle new, moved and removed styles
                    if (msg.getFeature() == KRenderingPackage.Literals.KSTYLE_HOLDER__STYLES) {
                        scheduleStylesUpdate(ElementMovement.ON_TOP);
                        return;
                    }

                    // handle other changes by reevaluating the rendering
                    scheduleRenderingUpdate();
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
     * Schedules a re-evaluation of this' KGE's rendering.<br>
     * The scheduling allows to collect a bunch of changes within some time and apply them in one
     * run, which is desirable in combination with the new EMF compare-based incremental update.<br>
     * <br>
     * In addition, this automatically realizes the switching to the UI thread.
     */
    private void scheduleRenderingUpdate() {
        diagramController.scheduleRenderingUpdate(this);
    }

    /**
     * Schedules a re-evaluation of this' KGE's rendering's styles.<br>
     * The scheduling allows to collect a bunch of changes within some time and apply them in one
     * run, which is desirable if multiple changes arrive after each other, which is common case.<br>
     * <br>
     * In addition, this automatically realizes the switching to the UI thread.
     */
    private void scheduleStylesUpdate(final ElementMovement movement) {
        diagramController.scheduleStylesUpdate(this, movement);
    }

    /* -----------------------------------------------------------------------------------
     * The style evaluation methods:
     * ----------------------------------------------------------------------------------- */

    /**
     * Updates the styles of the {@link PNode PNodes} representing {@link #currentRendering}.
     */
    void updateStyles(final ElementMovement movement) {
        updateStyles();

        if (movement == ElementMovement.ON_TOP) {
            moveToFront();
        } else if (movement == ElementMovement.BACKWARD) {
            moveToBack();
        }
    }

    /**
     * Empty method hook to be overridden by {@link KEdgeRenderingController} in order to bring
     * highlighted edges to front. Method is not supposed to be overridden by other sub classes.
     */
    protected void moveToFront() {
    }

    /**
     * Empty method hook to be overridden by {@link KEdgeRenderingController} in order to move
     * de-highlighted edges to backward. Method is not supposed to be overridden by other sub
     * classes.<br>
     * <br>
     * TODO: this feature is not implemented by {@link KEdgeRenderingController} yet, as just moving
     * edges on removal of styles to back is not sufficient in case of multiple highlightings at
     * same time. IMO there's some highlighting priority required.
     */
    protected void moveToBack() {
    }


    /** returns <code>true</code> for all kRenderings, except kTexts that are selectable. */
    private static final Predicate<KRendering> SELECTION_HIGHLIGHTING_RENDERINGS_FILTER =
            Predicates.not(Predicates.<KRendering>and(
                    // see the corresponding distinction in #prepareStylesRecord(...), as well!
                    KlighdPredicates.instanceOf(KText.class), KlighdPredicates.isSelectable()));

    /** returns an {@link Iterator} of the provided kRendering's styles list. */
    private static final Function<KRendering, Iterator<KStyle>> TO_STYLES =
            new Function<KRendering, Iterator<KStyle>>() {
                public Iterator<KStyle> apply(final KRendering rendering) {
                    return rendering.getStyles().iterator();
                }
            };

    /**
     * Updates the styles of the {@link PNode PNodes} representing {@link #currentRendering}.
     */
    private void updateStyles() {
        // reset that flag as potentially available styles with a modifier might be removed now
        modifiableStylesPresent = false;

        final boolean isSelected = this.isSelected();
        if (isSelected) {
            // check for the presence of any 'selection'-flagged style deeply in the
            //  'currentRendering' by composing an iterator visiting 'currentRendering' and
            //  all of its children and children's children ...
            final Iterator<KRendering> renderings = Iterators.filter(
                    KRenderingUtil.selfAndAllChildren(this.currentRendering),
                    SELECTION_HIGHLIGHTING_RENDERINGS_FILTER);

            // ... and inspecting their attached kStyles;
            final Iterator<KStyle> styles = Iterators.concat(Iterators.transform(renderings, TO_STYLES));

            // visit the styles lazily, stop if a kStyle with the 'selection' flag == true is found
            selectionStylesPresent = Iterators.any(styles, KlighdPredicates.isSelection());
        }

        // update using the recursive method
        updateStyles(currentRendering, isSelected, Collections.<KStyle>emptyList());

        // validate all figures representing 'currentRendering'
        //  (should actually be only one, as we don't allow recursive renderingRefs)
        for (final PNodeController<?> nodeController : getPNodeController(currentRendering)) {
            final PNode node = nodeController.getTransformedPNode();
            // in case styles of a detached KRendering are modified, e.g. if selection highlighting
            //  is removed from renderings that are not part of the diagram in the meantime
            //  'null' values may occur here
            if (node != null) {
                node.validateFullPaint();
            }
        }
    };

    /**
     * Getter.
     *
     * @return the selection state of the current root rendering
     */
    private boolean isSelected() {
        return currentRendering == null
                ? false : currentRendering.getProperty(KlighdInternalProperties.SELECTED);
    }

    /**
     * Recursively updates the styles of the {@link PNode PNodes} representing <code>rendering</code>.
     */
    private void updateStyles(final KRendering rendering, final boolean isSelected,
            final List<KStyle> propagatedStyles) {

        updateStyles(getPNodeController(rendering), rendering, isSelected, propagatedStyles);
    }

    /**
     * Recursively updates the styles of the {@link PNode PNodes} representing <code>rendering</code>.
     * @return
     */
    private void updateStyles(final Collection<PNodeController<?>> controllers,
            final KRendering rendering, final boolean isSelected,
            final List<KStyle> propagatedStyles) {

        if (controllers == null || controllers.isEmpty()) {
            // in case 'rendering' is not represented by any node and, thus, no pnodeController exists,
            //  stop here
            return;
        }

        if (rendering instanceof KRenderingRef) {
            // update referenced rendering
            final KRenderingRef renderingRef = (KRenderingRef) rendering;

            // get the referenced rendering
            final KRendering referencedRendering = renderingRef.getRendering();

            // proceed recursively with the referenced rendering
            updateStyles(controllers, referencedRendering, isSelected,
                    Lists.newLinkedList(concat(rendering.getStyles(), propagatedStyles)));

            return;
        }

        processModifiableStyles(rendering.getStyles());

        // determine the styles for this rendering
        final Styles styles = prepareStylesRecord(rendering, propagatedStyles, isSelected);

        // apply the styles to the rendering
        for (final PNodeController<?> controller : controllers) {
            controller.applyChanges(styles);
        }

        if (rendering instanceof KContainerRendering) {
            List<KStyle> childPropagatedStyles = null;

            // update children
            final KContainerRendering container = (KContainerRendering) rendering;
            if (container.getChildren().size() > 0) {
                // determine the styles for propagation to child nodes
                 childPropagatedStyles = determinePropagationStyles(
                         rendering.getStyles(), propagatedStyles, false);

                // propagate to all children
                for (final KRendering child : container.getChildren()) {
                    updateStyles(child, isSelected, childPropagatedStyles);
                }
            }

            if (rendering instanceof KPolyline) {
                final KPolyline polyline = (KPolyline) rendering;

                final KRendering jpr = polyline.getJunctionPointRendering();
                if (jpr != null) {

                    if (childPropagatedStyles == null) {
                        childPropagatedStyles = determinePropagationStyles(
                                rendering.getStyles(), propagatedStyles, false);
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

        if (rendering instanceof KText && KlighdProperties.isSelectable((KText) rendering)) {
            // this branch is only taken if the (text) rendering is selectable itself
            //  in this case no selection styles are applied to further kRenderings being part of
            //  'currentRendering'
            // see also the corresponding distinction in
            //  #updateStyles() / SELECTION_HIGHLIGHTING_RENDERINGS_FILTER!
            styles.deriveStyles(rendering, propagatedStyles, isSelected((KText) rendering), false, null);
        } else {
            // this branch is taken for all kRenderings and kTexts if there selectability is suppressed
            //  such kTexts treated like all of the other kRenderings
            styles.deriveStyles(rendering, propagatedStyles, isSelected,
                    !this.selectionStylesPresent, KRenderingUtil.dereference(this.currentRendering));
        }
        return styles;
    }

    /**
     * Convenience getter.
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
     * Returns the list of styles propagated to children of the rendering with the given rendering
     * styles and propagated styles.
     *
     * @param renderingStyles
     *            the rendering styles
     * @param propagatedStyles
     *            the propagated styles
     * @param isRenderingRef
     *            indicates whether the {@link KRendering} being handled is a {@link KRenderingRef}
     * @return the list of styles for propagation to the children of the rendering
     */
    protected List<KStyle> determinePropagationStyles(final List<KStyle> renderingStyles,
            final List<KStyle> propagatedStyles, final boolean isRenderingRef) {
        final List<KStyle> result = Lists.newLinkedList();

        for (final KStyle style : isRenderingRef ? propagatedStyles : concat(
                propagatedStyles, renderingStyles)) {
            if (style.isPropagateToChildren()) {
                result.add(style);
            }
        }

        if (isRenderingRef) {
            result.addAll(renderingStyles);
        }

        return result;
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
        final Iterable<KStyle> localModifiedStyles = filter(styles, MODIFIED_STYLE_FILTER);
        modifiableStylesPresent |= localModifiedStyles.iterator().hasNext();

        final KLayoutData layoutData = getGraphElement().getData(KLayoutData.class);
        if (layoutData == null) {
            return;
        }

        boolean deliver;
        for (final KStyle s: localModifiedStyles) {
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
            final List<KStyle> styles, final IKlighdNode parent) {
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
    protected PNodeController<?> handleAreaAndPointPlacementRendering(final KRendering rendering,
            final IKlighdNode parent) {
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
    protected PNodeController<?> handleAreaAndPointPlacementRendering(final KRendering rendering,
            final List<KStyle> styles, final IKlighdNode parent) {
        final KPlacementData pcd = KRenderingUtil.getPlacementData(rendering);
        final KAreaPlacementData pad = KRenderingUtil.asAreaPlacementData(pcd);
        final KPointPlacementData ppd = KRenderingUtil.asPointPlacementData(pcd);
        final boolean pointPlacement = ppd != null;

        final Bounds bounds;
        if (pointPlacement) {
            bounds = PlacementUtil.evaluatePointPlacement(ppd,
                    PlacementUtil.estimateSize(rendering, Bounds.of(0, 0)),
                    parent.getAssignedBounds());
        } else {
            // determine the initial bounds
            bounds = PlacementUtil.evaluateAreaPlacement(pad, parent.getAssignedBounds());
        }

        // create the rendering and receive its controller
        final PNodeController<?> controller = createRendering(rendering, styles, parent, bounds);

        // add a listener on the parent's bounds
        if (pointPlacement) {
            addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                    new PropertyChangeListener() {
                        public void propertyChange(final PropertyChangeEvent e) {
                            final Bounds bounds = PlacementUtil.evaluatePointPlacement(
                                    ppd, PlacementUtil.estimateSize(rendering, Bounds.of(0, 0)),
                                    parent.getAssignedBounds());

                            // use the controller to apply the new bounds
                            controller.setBounds(bounds);
                        }
                    });
        } else {
            addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                    new PropertyChangeListener() {
                        public void propertyChange(final PropertyChangeEvent e) {
                            final Bounds bounds = PlacementUtil.evaluateAreaPlacement(
                                    pad, parent.getAssignedBounds());

                            // use the controller to apply the new bounds
                            controller.setBounds(bounds);
                        }
                    });
        }

        return controller;
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
            final List<KRendering> renderings, final List<KStyle> styles, final IKlighdNode parent) {
        if (renderings.size() == 0) {
            return;
        }

        // calculate the bounds
        final Bounds parentBounds = new Bounds(parent.getAssignedBounds());
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
                        final Bounds parentBounds = Bounds.of(parent.getAssignedBounds());
                        final Bounds[] bounds = GridPlacementUtil.evaluateGridPlacement(
                                gridPlacement, renderings, parentBounds);

                        // use the controllers to apply the new bounds
                        int i = 0;
                        for (final PNodeController<?> controller : controllers) {
                            if (bounds[i] != null) {
                                controller.setBounds(bounds[i++]);
                                controller.getPNode().setVisible(true);
                            } else {
                                controller.getPNode().setVisible(false);
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
    protected IKlighdFigureNode handleDecoratorPlacementRendering(final KRendering rendering,
            final List<KStyle> styles, final KlighdPath parent) {
        // determine the initial bounds and rotation
        final Decoration decoration = PiccoloPlacementUtil.evaluateDecoratorPlacement(
                PiccoloPlacementUtil.getDecoratorPlacementData(rendering), parent);

        // create an empty node for the decorator
        final KlighdDecoratorNode decorator = new KlighdDecoratorNode(rendering);

        // NodeUtil.applyTranslation(decorator, decoration.getOrigin());
        parent.addChild((IKlighdFigureNode) decorator);

        // create the rendering and receive its controller
        final PNodeController<?> controller = createRendering(rendering, styles, decorator,
                decoration.getBounds());

        // apply the initial rotation
        decorator.setRotation(decoration.getRotation());

        // add a listener on the parent's path
        addListener(PPath.PROPERTY_PATH, parent, controller.getNode(),
                new PropertyChangeListener() {

                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds and rotation for the rendering
                        final Decoration decoration = PiccoloPlacementUtil.evaluateDecoratorPlacement(
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
     * Dedicated {@link PNode} type wrapping edge decorator figures.<br>
     */
    private static class KlighdDecoratorNode extends KlighdNode.KlighdFigureNode<KRendering> {

        private static final long serialVersionUID = -2824069198134013044L;

        /**
         * Standard constructor.
         *
         * @param theRendering
         *            the rendering being represented by this node.
         */
        public KlighdDecoratorNode(final KRendering theRendering) {
            this.setRendering(theRendering);
            this.setPickable(true);
        }

        /**
         * {@inheritDoc}.<br>
         * <br>
         * KlighdDecoratorNode state greedy picking as it is unlikely that they contain nested
         * pickable elements like text fields.
         */
        @Override
        protected boolean pick(final PPickPath pickPath) {
            return true;
        }
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
    protected PNodeController<?> createRendering(final KRendering rendering, final IKlighdNode parent,
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
            final List<KStyle> propagatedStyles, final IKlighdNode parent, final Bounds initialBounds) {

        final boolean isRenderingRef =
                rendering.eClass() == KRenderingPackage.eINSTANCE.getKRenderingRef();

        final List<KStyle> renderingStyles = rendering.getStyles();

        processModifiableStyles(renderingStyles);

        // determine the styles for propagation to child nodes
        final List<KStyle> childPropagatedStyles =
                determinePropagationStyles(renderingStyles, propagatedStyles, isRenderingRef);

        // create the rendering and return its controller
        kSwitch.configure(childPropagatedStyles, parent, initialBounds);
        final PNodeController<?> controller = kSwitch.doSwitch(rendering);

        // determine the styles for this rendering
        final Styles styles = prepareStylesRecord(rendering, propagatedStyles, this.isSelected());

        if (!isRenderingRef) {
            // set the styles for the created rendering node using the controller
            controller.applyChanges(styles);
        }

        // remember the KRendering-controller pair in the controller's 'pnodeControllers' map
        addPNodeController(rendering, controller);

        // in case an action is attached to the KRendering make the node pickable
        //  this is only done in the PNode initialization as adding and removing actions later in life
        //  of a KRendering/PNode is considered unlikely and thus not supported yet
        if (!rendering.getActions().isEmpty()) {
            controller.getPNode().setPickable(true);
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
     * @param childArea
     *            the {@link KChildArea} to be represented, may be <code>null</code> if no explicit
     *            child area is defined
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo2D node
     */
    protected PNodeController<?> createChildArea(final IKlighdNode parent, final KChildArea childArea,
            final Bounds initialBounds) {
        throw new RuntimeException(
                "Child area found in graph element which does not support a child area: "
                        + getGraphElement());
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
    protected void addListener(final String property, final IKlighdNode parent,
            final IKlighdNode.IKlighdFigureNode node, final PropertyChangeListener listener) {
        
        parent.asPNode().addPropertyChangeListener(property, listener);
        
        final PNode nodeAsPNode = node.asPNode();        
        @SuppressWarnings("unchecked")
        List<Object> listeners = (List<Object>) nodeAsPNode.getAttribute(PROPERTY_LISTENER_KEY);
        if (listeners == null) {
            listeners = Lists.newLinkedList();
            nodeAsPNode.addAttribute(PROPERTY_LISTENER_KEY, listeners);
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
        final List<Pair<String, PropertyChangeListener>> listeners =
                (List<Pair<String, PropertyChangeListener>>) node.getAttribute(PROPERTY_LISTENER_KEY);
        if (listeners != null && node.getParent() != null) {
            for (final Pair<String, PropertyChangeListener> pair : listeners) {
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
