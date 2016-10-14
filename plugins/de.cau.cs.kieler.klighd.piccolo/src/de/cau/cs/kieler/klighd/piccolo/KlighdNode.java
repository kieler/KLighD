/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo;

import static de.cau.cs.kieler.klighd.util.KlighdProperties.VISIBILITY_HEIGHT_LOWER_BOUND;
import static de.cau.cs.kieler.klighd.util.KlighdProperties.VISIBILITY_HEIGHT_UPPER_BOUND;
import static de.cau.cs.kieler.klighd.util.KlighdProperties.VISIBILITY_SCALE_LOWER_BOUND;
import static de.cau.cs.kieler.klighd.util.KlighdProperties.VISIBILITY_SCALE_UPPER_BOUND;
import static de.cau.cs.kieler.klighd.util.KlighdProperties.VISIBILITY_WIDTH_LOWER_BOUND;
import static de.cau.cs.kieler.klighd.util.KlighdProperties.VISIBILITY_WIDTH_UPPER_BOUND;

import java.awt.geom.Rectangle2D;
import java.util.List;

import org.eclipse.elk.graph.properties.IProperty;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera.KlighdPickPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.NodeDisposeListener;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * Common base class of KLighD-specific {@link PNode PNodes}.<br>
 * It enables, e.g., proper view-model-tracing by preserving the related
 * {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement}/ {@link KRendering} view model
 * element being accessible via {@link #getViewModelElement()}.<br>
 * <br>
 * Application-specific custom figures incorporated by means of
 * {@link de.cau.cs.kieler.core.krendering.KCustomRendering KCustomRenderings} may subclass
 * {@link KlighdNode} of {@link KlighdNode.KlighdFigureNode} if beneficial, otherwise rely on
 * {@link IKlighdNode}.
 *
 * @author chsch
 */
public abstract class KlighdNode extends PNode implements IKlighdNode {

    private static final long serialVersionUID = 6876586117083105843L;

    // for the sake of brevity the properties referenced below are statically imported

    /** Convenience constant for accessing the property value default here and in subclasses. */
    protected static final Number SCALE_LB_DEF = VISIBILITY_SCALE_LOWER_BOUND.getDefault();

    /** Convenience constant for accessing the property value default here and in subclasses. */
    protected static final Number SCALE_UB_DEF = VISIBILITY_SCALE_UPPER_BOUND.getDefault();

    /** Convenience constant for accessing the property value default here and in subclasses. */
    protected static final Number HEIGHT_LB_DEF = VISIBILITY_HEIGHT_LOWER_BOUND.getDefault();

    /** Convenience constant for accessing the property value default here and in subclasses. */
    protected static final Number HEIGHT_UB_DEF = VISIBILITY_HEIGHT_UPPER_BOUND.getDefault();

    /** Convenience constant for accessing the property value default here and in subclasses. */
    protected static final Number WIDTH_LB_DEF = VISIBILITY_WIDTH_LOWER_BOUND.getDefault();

    /** Convenience constant for accessing the property value default here and in subclasses. */
    protected static final Number WIDTH_UB_DEF = VISIBILITY_WIDTH_UPPER_BOUND.getDefault();

    private static final List<? extends IProperty<?>> VISIBILTY_DEFS = ImmutableList.of(
            VISIBILITY_SCALE_LOWER_BOUND, VISIBILITY_SCALE_UPPER_BOUND,
            VISIBILITY_HEIGHT_LOWER_BOUND, VISIBILITY_HEIGHT_UPPER_BOUND,
            VISIBILITY_WIDTH_LOWER_BOUND, VISIBILITY_WIDTH_UPPER_BOUND);

    /**
     * @param propertyConfig
     *            the {@link EMapPropertyHolder} to examine
     * @return <code>true</code> if the given {@link EMapPropertyHolder} contains any visibility
     *         settings, <code>false</code> otherwise
     */
    protected static boolean containsVisibilitySettings(final EMapPropertyHolder propertyConfig) {
        return Iterators.any(propertyConfig.getProperties().keySet().iterator(),
                Predicates.in(VISIBILTY_DEFS));
    }

    private boolean outlineInvisible = false;
    private boolean exportedImageInvisible = false;
    private boolean printoutInvisible = false;

    private double lowerScaleBound = 0;
    private double upperScaleBound = -1;

    private boolean lowerSizeBoundUndef = true;
    private boolean upperSizeBoundUndef = true;

    private float lowerHeightBound = 0;
    private float upperHeightBound = -1;

    private float lowerWidthBound = 0;
    private float upperWidthBound = -1;

    /**
     * Constructor.
     */
    public KlighdNode() {
        this.addPropertyChangeListener(NodeDisposeListener.DISPOSE, new NodeDisposeListener(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getAssignedBounds() {
        return getBoundsReference();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final PNode asPNode() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChild(final IKlighdNode child) {
        addChild(child.asPNode());
    }

    /**
     * Convenience method avoiding ambiguous call errors (IKlighdNode vs. PNode).
     * 
     * @param child the child to by added
     */
    public void addChild(final KlighdNode child) {
        addChild(child.asPNode());
    }


    /**
     * Sets zoom scale dependent visibility bounds of <code>this</code> {@link KlighdNode}.<br>
     * Is just for internal use in order to avoid casts to <code>float</code>.
     *
     * @param lowerBound
     *            the lower visibility bound, default is the (unreachable) scale of zero
     * @param upperBound
     *            the upper visibility bound, default is -1 denoting no upper bound
     */
    private void setScaleBasedVisibilityBounds(final double lowerBound, final double upperBound) {
        this.lowerScaleBound = lowerBound;
        this.upperScaleBound = upperBound;
    }

    /**
     * Sets zoom scale dependent visibility bounds of <code>this</code> {@link KlighdNode}.
     *
     * @param lowerBound
     *            the lower visibility bound, default is the (unreachable) scale of zero
     * @param upperBound
     *            the upper visibility bound, default is -1 denoting no upper bound
     */
    protected void setScaleBasedVisibilityBounds(final float lowerBound, final float upperBound) {
        this.lowerScaleBound = lowerBound;
        this.upperScaleBound = upperBound;
    }

    /**
     * Sets absolute height dependent visibility bounds of <code>this</code> {@link KlighdNode}.
     *
     * @param lowerBound
     *            the lower visibility bound in <code>px</code>, default is the height of zero
     * @param upperBound
     *            the upper visibility bound in <code>px</code>, default is -1 denoting no upper bound
     */
    protected void setHeightBasedVisibilityBounds(final float lowerBound, final float upperBound) {
        this.lowerHeightBound = lowerBound;
        this.upperHeightBound = upperBound;

        this.lowerSizeBoundUndef &= lowerBound == HEIGHT_LB_DEF.floatValue();
        this.upperSizeBoundUndef &= upperBound == HEIGHT_UB_DEF.floatValue();
    }

    /**
     * Sets absolute width dependent visibility bounds of <code>this</code> {@link KlighdNode}.
     *
     * @param lowerBound
     *            the lower visibility bound in <code>px</code>, default is the width of zero
     * @param upperBound
     *            the upper visibility bound in <code>px</code>, default is -1 denoting no upper bound
     */
    protected void setWidthBasedVisibilityBounds(final float lowerBound, final float upperBound) {
        this.lowerWidthBound = lowerBound;
        this.upperWidthBound = upperBound;

        this.lowerSizeBoundUndef &= lowerBound == WIDTH_LB_DEF.floatValue();
        this.upperSizeBoundUndef &= upperBound == WIDTH_UB_DEF.floatValue();
    }

    /**
     *
     * @param propertyConfig
     *            the {@link EMapPropertyHolder} providing the desired visibility configuration.
     */
    protected void setScaleAndSizeBasedVisibilityBounds(final EMapPropertyHolder propertyConfig) {
        if (propertyConfig == null || !containsVisibilitySettings(propertyConfig)) {
            return;
        }

        setScaleBasedVisibilityBounds(
                propertyConfig.getProperty(VISIBILITY_SCALE_LOWER_BOUND).floatValue(),
                propertyConfig.getProperty(VISIBILITY_SCALE_UPPER_BOUND).floatValue());

        setHeightBasedVisibilityBounds(
                propertyConfig.getProperty(VISIBILITY_HEIGHT_LOWER_BOUND).floatValue(),
                propertyConfig.getProperty(VISIBILITY_HEIGHT_UPPER_BOUND).floatValue());

        setWidthBasedVisibilityBounds(
                propertyConfig.getProperty(VISIBILITY_WIDTH_LOWER_BOUND).floatValue(),
                propertyConfig.getProperty(VISIBILITY_WIDTH_UPPER_BOUND).floatValue());
    }

    /**
     * Updates the scale based visibility config to match the absolute visibility settings in
     * context of the given <code>bounds</code>.
     *
     * @param bounds
     *            the current bounds of the corresponding {@link IKlighdNode}, either
     *            <code>this</code> node's or the corresponding
     *            {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode KNodeNode}'s
     *            bounds.
     */
    public void updateScaleBasedVisibilityBounds(final PBounds bounds) {
        if (upperSizeBoundUndef && lowerSizeBoundUndef || bounds == null || bounds.isEmpty()) {
            return;
        }

        final double lowerBound = lowerSizeBoundUndef ? lowerScaleBound : Math.min(
            lowerWidthBound == WIDTH_LB_DEF.floatValue()
                ? Integer.MAX_VALUE : lowerWidthBound / bounds.width,

            lowerHeightBound == HEIGHT_LB_DEF.floatValue()
                ? Integer.MAX_VALUE : lowerHeightBound / bounds.height);

        final double upperBound = upperSizeBoundUndef ? upperScaleBound : Math.max(
            upperWidthBound == WIDTH_UB_DEF.floatValue()
                ? WIDTH_UB_DEF.floatValue() : upperWidthBound / bounds.width,

            upperHeightBound == HEIGHT_UB_DEF.floatValue()
                ? HEIGHT_UB_DEF.floatValue() : upperHeightBound / bounds.height);

        setScaleBasedVisibilityBounds(lowerBound, upperBound);
    }

    /**
     * Sets zoom scale dependent visibility bounds of <code>this</code> {@link KlighdNode}.
     *
     * @param outline
     *            visibility in the outline view, independent of zoom scale dependent (in)visibility
     * @param exports
     *            visibility in image exports (raster and vector graphic exports)
     * @param printouts
     *            visibility on printouts (which apply a fixed zoom scale of one)
     */
    protected void setVisibilityOn(final boolean outline, final boolean exports,
            final boolean printouts) {
        this.outlineInvisible = outline;
        this.exportedImageInvisible = exports;
        this.printoutInvisible = printouts;
    }

    /**
     * @return <code>true</code> in order to suppress the drawing of this {@link KlighdNode} on the
     *         outline diagram, <code>false</code> in the normal case
     */
    public boolean isOutlineInvisible() {
        return outlineInvisible;
    }

    /**
     * @return <code>true</code> in order to suppress the drawing of this {@link KlighdNode} to
     *         exported diagram images, <code>false</code> in the normal case
     */
    public boolean isExportedImageInvisible() {
        return exportedImageInvisible;
    }

    /**
     * @return <code>true</code> in order to suppress the drawing of this {@link KlighdNode} to
     *         diagram printouts, <code>false</code> in the normal case
     */
    public boolean isPrintOutInvisible() {
        return printoutInvisible;
    }

    /**
     * Returns the declared lower bound of the diagram scale/zoom factor of which this (pseudo)
     * figure is sill visible, i.e. drawn on the canvas.
     *
     * @return the declared lower bound of the diagram scale/zoom factor of which this (pseudo)
     * figure is sill visible, i.e. drawn on the canvas.
     */
    public double getLowerVisibilityBound() {
        return lowerScaleBound;
    }

    /**
     * Returns the declared upper bound of the diagram scale/zoom factor of which this (pseudo)
     * figure is sill visible, i.e. drawn on the canvas.
     *
     * @return the declared upper bound of the diagram scale/zoom factor of which this (pseudo)
     * figure is sill visible, i.e. drawn on the canvas.
     */
    public double getUpperVisibilityBound() {
        return upperScaleBound;
    }


    /**
     * Decides whether a {@link KlighdNode} must not be drawn according to given scale-based
     * visibility definitions. (see
     * {@link de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses#setUpperVisibilityScaleBound(KRendering,
     * float) DiagramSyntheses#setUpperVisibilityScaleBound(KRendering, float)} and friends)
     *
     * @param kpc
     *            the {@link KlighdPaintContext} providing the required information
     *
     * @return <code>true</code> if this (pseudo) figure should not be drawn on the diagram being
     *         drawn in the given <code>diagramScale</code>, <code>false</code> otherwise.
     */
    public boolean isNotVisibleOn(final KlighdPaintContext kpc) {
        // this method must be as fast as possible in the 'main diagram case'
        //  therefore ...

        if (kpc.isMainDiagram()) {
            return isNotVisibleOn(kpc.getCameraZoomScale());

        } else if (kpc.isOutline()) {
            return isOutlineInvisible() || isNotVisibleOn(kpc.getCameraZoomScale());

        } else if (kpc.isImageExport()) {
            return isExportedImageInvisible() || isNotVisibleOn(kpc.getCameraZoomScale());

        } else if (kpc.isPrintout()) {
            return isPrintOutInvisible() || isNotVisibleOn(kpc.getCameraZoomScale());

        } else {
            // default case, should never by reached
            return false;
        }
    }

    /**
     * Decides whether a {@link KlighdNode} must not be picked according to given scale-based
     * visibility definitions. (see
     * {@link de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses#setUpperVisibilityScaleBound(KRendering,
     * float) DiagramSyntheses#setUpperVisibilityScaleBound(KRendering, float)} and friends)
     *
     * @param kpp
     *            the {@link KlighdPickPath} providing the required information
     * @return <code>true</code> if this (pseudo) figure should not be picked on the diagram being
     *         drawn in the given <code>diagramScale</code>, <code>false</code> otherwise.
     */
    public boolean isNotVisibleOn(final KlighdPickPath kpp) {
        return isNotVisibleOn(kpp.getCameraZoomScale());
    }


    /**
     * Decides whether a {@link KlighdNode} must not be drawn according to given scale-based
     * visibility definitions. (see
     * {@link de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses#setUpperVisibilityScaleBound(KRendering,
     * float) DiagramSyntheses#setUpperVisibilityScaleBound(KRendering, float)} and friends)
     *
     * @param diagramScale
     *            the diagram scale factor to be applied
     *
     * @return <code>true</code> if this (pseudo) figure should not be drawn on the diagram being
     *         drawn in the given <code>diagramScale</code>
     */
    public boolean isNotVisibleOn(final double diagramScale) {
        final double upperBound = getUpperVisibilityBound();
        return diagramScale < getLowerVisibilityBound()
                || upperBound != -1 && diagramScale >= upperBound;
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialization evaluates the occlusion of an element while picking it. It is required as
     * we're using the occlusion flag for implementing single figure/rendering invisibility and as
     * we don't use on {@link edu.umd.cs.piccolo.util.PPickPath#nextPickedNode()
     * PPickPath#nextPickedNode()} (since we don't expected
     * {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElements} occluding each other).
     */
    @Override
    public boolean getPickable() {
        return super.getPickable() && !getOccluded();
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialization just casts the result to {@link List} parameterized by {@link PNode}.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<? extends PNode> getChildrenReference() {
        return super.getChildrenReference();
    }

    private final PBounds tempRect = new PBounds();
    private boolean isValidatingPaint = false;

    // By means of the following two methods the behavior of validating the drawing,
    //  especially notifying the windows system on outdated diagram parts, is heavy changed.
    // In the super implementation each and every invalid diagram part is individually
    //  notified to the window system. This is reasonable if, for instance, just the coloring
    //  of a signal path is changed. For large diagrams, however, this wastes performance
    /// because the invalidation of each and every, e.g., edge decorator is notified to the
    //  window system.
    // The advantage of this approach is that only the truly out-dated parts can be
    //  individually requested to be repainted by the window system, see
    //  KlighdCanvas(PSWTCanvas).paintComponent(...).

    // To reduce the load of single repaint requests I employed 'TEMP_RECT' here and
    //  accumulate the children's repaint requests received during 'validateFullPaint'.
    // The bounding box approach, however, will potentially cause up-to-date diagram parts
    //  to be redrawn, as well, which mighty cause additional load while drawing.
    // One remark on the scenario of re-coloring signal paths: Our 'KlighdPath' and
    //  'KlighdStyledText' figures do not fire 'invalidate' notifications. This job is
    //  done by the corresponding rendering controller ('AbstractKGERenderingController')
    //  in the 'updateStyles' method by calling 'PNode.repaint()' (the rendering controller
    //  is in charge of listening to rendering & style changes on the view model).
    // Since the diagram is most likely not requested to 'validateFullPaint' at that time
    //  the original behavior is preserved.

    // Both methods occur exactly the same way in 'KlighdDisposingLayer'.

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateFullPaint() {
        isValidatingPaint = true;
        tempRect.resetToZero();

        super.validateFullPaint();

        isValidatingPaint = false;

        if (!tempRect.isEmpty()) {
            repaintFrom(tempRect, this);
        }
    }

    // Don't put further methods in between those two because of the common objective they
    //  are supposed to implement! Both occur exactly the same way in 'KlighdDisposingLayer'.

    /**
     * {@inheritDoc}
     */
    @Override
    public void repaintFrom(final PBounds localBounds, final PNode childOrThis) {
        if (isValidatingPaint) {
            if (childOrThis != this) {
                this.localToParent(localBounds);
            }
            tempRect.add(localBounds);
        } else {
            super.repaintFrom(localBounds, childOrThis);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        // skip the super implementations behavior and do nothing
    }


    /**
     * A common abstract class of {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath
     * KlighdPath}, {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdImage KlighdImage},
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText KlighdStyledText}, and
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KCustomFigureNode KCustomFigureNode}
     * serving the purpose of avoiding code clones.<br>
     * This class cares about tracking the corresponding {@link KRendering} element, contributing
     * semantic model data into drawn (vector graphic) images, and determining the visibility the
     * figure wrt. the diagram zoom scale while drawing the diagram.
     * 
     * @param <T> the concrete type describing the depicted figure
     */
    public static class KlighdFigureNode<T extends KRendering> extends KlighdNode implements
            IKlighdFigureNode {

        private static final long serialVersionUID = -3975636790695588901L;

        private boolean propagateVisibilityToChildren = false;

        /**
         * Standard constructor.
         */
        public KlighdFigureNode() {
            super();

            setVisible(true);
            setPickable(false);
        }

        /**
         * Constructor.
         *
         * @param rendering
         *            the {@link KRendering} element being represented by this {@link KlighdFigureNode}
         */
        public KlighdFigureNode(final T rendering) {
            this();
            setRendering(rendering);
        }

        private T rendering;

        /**
         * Configures the {@link KRendering} element being represented by this {@link KlighdFigureNode}.
         *
         * @param rendering
         *            the {@link KRendering} element being represented by this {@link KlighdFigureNode}
         */
        public void setRendering(final T rendering) {
            this.rendering = rendering;

            if (rendering == null) {
                return;
            }

            setVisibilityOn(
                    rendering.getProperty(KlighdProperties.OUTLINE_INVISIBLE).booleanValue(),
                    rendering.getProperty(KlighdProperties.EXPORTED_IMAGE_INVISIBLE).booleanValue(),
                    rendering.getProperty(KlighdProperties.PRINTOUT_INVISIBLE).booleanValue());

            setScaleAndSizeBasedVisibilityBounds(rendering);
        }

        /**
         * {@inheritDoc}
         */
        public T getViewModelElement() {
            return rendering;
        }

        /**
         * {@inheritDoc}
         */
        public boolean isSelectable() {
            return false;
        }

        /**
         * @return <code>true</code> if <code>this</code> {@link KlighdFigureNode
         *         KlighdFigureNode's} scale-based visibility bounds are to by applied to
         *         <code>this</code> {@link KlighdFigureNode KlighdFigureNode's} children, too,
         *         <code>false</code> otherwise.
         */
        protected boolean isPropagateVisibilityToChildren() {
            return propagateVisibilityToChildren;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void setScaleAndSizeBasedVisibilityBounds(
                final EMapPropertyHolder propertyConfig) {
            super.setScaleAndSizeBasedVisibilityBounds(propertyConfig);
            this.propagateVisibilityToChildren =
                    propertyConfig.getProperty(KlighdProperties.VISIBILITY_PROPAGATE_TO_CHILDREN);
        }

        /**
         * {@inheritDoc}<br>
         * <br>
         * To be more precise, {@link #invalidatePaint()} is required in case the styling of the
         * figure, e.g. the coloring, is to be changed. In case the (full) bounds of the figure are
         * affected, e.g. while changing a text figures font size or alignment,
         * {@link #invalidateFullBounds()} must be called (which is done by
         * {@link #setBounds(double, double, double, double)} automatically).<br>
         * <br>
         * This specialization of {@link PNode#invalidatePaint()} overrides the default
         * implementation s.t. it does not propagate the invalidation to the diagram's root figure
         * but just up to the parent {@link IKGraphElementNode}. The required corresponding calls of
         * {@link #validateFullPaint()} are triggered during the initialization of the figures (in
         * combination with applying the bounds due to corresponding {@link
         * de.cau.cs.kieler.core.krendering.KPlacementData KPlacementData}) anyway, and in case of
         * pure style changes by the corresponding
         * {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement's} rendering controllers
         * ({@link de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController
         * #updateStyles() AbstractKGERenderingController#updateStyles()}) after all rendering and
         * style changes are performed.
         */
        @Override
        public void invalidatePaint() {
            this.setPaintInvalid(true);

            PNode n = getParent();
            while (n != null && !n.getChildPaintInvalid() && !(n instanceof IKGraphElementNode)) {
                n.setChildPaintInvalid(true);
                n = n.getParent();
            }
        }

        /**
         * {@inheritDoc}<br>
         * <br>
         * {@link KlighdNode} just contributes a visibility check in this method.
         */
        @Override
        public boolean fullPick(final PPickPath pickPath) {
            // first test whether this figure's visibility bounds apply to children as well
            //  and if so perform the (in)visibility check
            // I can expect a 'KlighdPickPath' here, as our 'KlighdMainCamera' only such,
            //  and in other views like the outline or magnification lens no elements can be picked!
            if (isPropagateVisibilityToChildren() && isNotVisibleOn((KlighdPickPath) pickPath)) {
                return false;

            } else {
                return super.fullPick(pickPath);
            }
        }

        /**
         * {@inheritDoc}<br>
         * <br>
         * {@link KlighdNode} just contributes a visibility check in this method.
         */
        @Override
        protected boolean pickAfterChildren(final PPickPath pickPath) {
            // just return false if this figure is not visible at the zoom scale provided by 'pickPath'
            // I can expect a 'KlighdPickPath' here, as our 'KlighdMainCamera' only such,
            //  and in other views like the outline or magnification lens no elements can be picked!
            if (isNotVisibleOn((KlighdPickPath) pickPath)) {
                return false;
            } else {
                return super.pickAfterChildren(pickPath);
            }
        }

        /**
         * {@inheritDoc}<br>
         * <br>
         * {@link KlighdNode} contributes visibility checks in this method, and skips superfluous
         * method calls.
         */
        @Override
        public void fullPaint(final PPaintContext paintContext) {
            final KlighdPaintContext kpc = (KlighdPaintContext) paintContext;

            // first test whether this figure shall be drawn at all
            if (isPropagateVisibilityToChildren() && isNotVisibleOn(kpc)) {
                return;
            }

            if (getVisible() && fullIntersects(paintContext.getLocalClip())) {

                final PAffineTransform transform = getTransformReference(false);
                paintContext.pushTransform(transform);

                if (!getOccluded() && !isNotVisibleOn(kpc)) {
                    paint(kpc);
                }

                final int count = getChildrenCount();
                for (int i = 0; i < count; i++) {
                    final PNode each = getChildrenReference().get(i);
                    each.fullPaint(paintContext);
                }

                // removed the call of 'paintAfterChildren(...)
                //  as that method is (currently) not required for our figures

                paintContext.popTransform(transform);
            }
        }

        /**
         * Derivative of {@link #paint(PPaintContext)} requiring a {@link KlighdPaintContext}.
         *
         * @param paintContext
         *            the paint context to use for drawing the node
         * @see PNode#paint(PPaintContext)
         */
        protected void paint(final KlighdPaintContext paintContext) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected final void paint(final PPaintContext paintContext) {
            throw new UnsupportedOperationException(
                    "KLighD: The method 'paint(PPaintContext)' of 'edu.umd.cs.piccolo.PNode' "
                    + "is replaced by 'paint(KlighdPaintContext)' in "
                    + "'de.cau.cs.kieler.klighd.piccolo.KlighdNode.KlighdFigureNode'");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected final void paintAfterChildren(final PPaintContext paintContext) {
            throw new UnsupportedOperationException(
                    "KLighD: The method 'paintAfterChildren(PPaintContext)' of "
                    + "'edu.umd.cs.piccolo.PNode' currently not supported for subclasses of "
                    + "'de.cau.cs.kieler.klighd.piccolo.KlighdNode.KlighdFigureNode'");
        }

        /**
         * A convenience method to be re-used in the {@link #paint(PPaintContext)} methods of
         * concrete implementations of this class.
         *
         * @param kpc the {@link KlighdPaintContext} employed while drawing the diagram.
         */
        public void addSemanticData(final KlighdPaintContext kpc) {
            if (kpc.isAddSemanticData() && rendering != null) {
                kpc.getKlighdGraphics().addSemanticData(
                        rendering.getProperty(KlighdProperties.SEMANTIC_DATA));
            }
        }
    }
}
