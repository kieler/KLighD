/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo;

import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera.KlighdPickPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.NodeDisposeListener;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.PNode;
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

    private boolean outlineInvisible = false;
    private boolean exportedImageInvisible = false;
    private boolean printoutInvisible = false;

    private float lowerScaleBound = 0;
    private float upperScaleBound = -1;

    /**
     * Constructor.
     */
    public KlighdNode() {
        this.addPropertyChangeListener(NodeDisposeListener.DISPOSE, new NodeDisposeListener(this));
    }

    /**
     * Sets zoom scale dependent visibility bounds of <code>this</code> {@link KlighdNode}.
     *
     * @param lowerBound
     *            the lower visibility bound, default is the (unreachable) scale of zero
     * @param upperBound
     *            the upper visibility bound, default is -1 denoting no upper bound
     */
    protected void setVisibilityBounds(final float lowerBound, final float upperBound) {
        this.lowerScaleBound = lowerBound;
        this.upperScaleBound = upperBound;
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
     *            the KlighdPaintContext providing the required information
     *
     * @return <code>true</code> if this (pseudo) figure should not be drawn on the diagram being
     *         drawn in the given <code>diagramScale</code>
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
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        // do nothing
    }

    /**
     * Provides the permission of the corresponding
     * {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement}/{@link KRendering} to be
     * selected.
     *
     * @return <code>true</code> if the corresponding
     *         {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement}/{@link KRendering}
     *         is allowed to be selected, <code>false</code> otherwise.
     */
    public abstract boolean isSelectable();


    /**
     * A common abstract class of {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath
     * KlighdPath}, {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdImage KlighdImage},
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText KlighdStyledText}, and
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KCustomFigureNode KCustomFigureNode}
     * serving the purpose of avoiding code clones.<br>
     * This class cares about tracking the corresponding {@link KRendering} element, contributing
     * semantic model data into drawn (vector graphic) images, and determining the visibility the
     * figure wrt. the diagram zoom scale while drawing the diagram.
     */
    public static class KlighdFigureNode<T extends KRendering> extends KlighdNode implements
            IKlighdFigureNode {

        private static final long serialVersionUID = -3975636790695588901L;

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

            setVisibilityBounds(
                    rendering.getProperty(KlighdProperties.VISIBILITY_SCALE_LOWER_BOUND).floatValue(),
                    rendering.getProperty(KlighdProperties.VISIBILITY_SCALE_UPPER_BOUND).floatValue());
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
        @Override
        public boolean isSelectable() {
            return false;
        }

        /**
         * {@inheritDoc}<br>
         * <br>
         * KLighD just contributes a visibility check in this method.
         */
        @Override
        protected boolean pickAfterChildren(final PPickPath pickPath) {
            final KlighdPickPath kpp = (KlighdPickPath) pickPath;

            // first test whether this figure is visible at all
            if (isNotVisibleOn(kpp.getCameraZoomScale())) {
                return false;
            } else {
                return super.pickAfterChildren(pickPath);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void paint(final PPaintContext paintContext) {
            final KlighdPaintContext kpc = (KlighdPaintContext) paintContext;

            // first test whether this figure shall be drawn at all
            if (isNotVisibleOn(kpc)) {
                return;
            }

            this.paint((KlighdPaintContext) paintContext);
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
