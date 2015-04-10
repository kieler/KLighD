/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.piccolo.KlighdNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.KlighdSemanticDiagramData;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * A common abstract class of {@link KEdgeNode}, {@link KPortNode}, and {@link KLabelNode} serving
 * the purpose of avoiding code clones. Due to the inheritance of {@link edu.umd.cs.piccolo.PLayer
 * PLayer} {@link KNodeNode} and {@link KNodeTopNode} cannot inherit this class, which I regret very
 * much.<br>
 * This class cares about tracking the corresponding
 * {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} element, contributing semantic
 * model data into drawn (vector graphic) images, and determining the visibility the pseudo figure
 * wrt. the diagram zoom scale while drawing the diagram.
 * 
 * @author chsch
 * 
 * @param <T>
 *            the concrete type of the {@link KGraphElement}
 */
public abstract class KGraphElementNode<T extends KGraphElement> extends KlighdNode implements
        IInternalKGraphElementNode<T> {

    private static final long serialVersionUID = -5577703758022742813L;

    private static final Number DEFAULT_SCALE_LB =
            KlighdProperties.VISIBILITY_SCALE_LOWER_BOUND.getDefault();

    private static final Number DEFAULT_SCALE_UB =
            KlighdProperties.VISIBILITY_SCALE_UPPER_BOUND.getDefault();

    /**
     * Initializes the visibility settings of <code>kgeNode</code> and returns it, if
     * <code>kgeNode</code> is non-<code>null</code>. Otherwise, if <code>kgeNode</code> is
     * <code>null</code> and visibility settings are configured in <code>layoutData</code>, a new
     * instance of {@link KlighdNode} is created and initialized with those visibility settings. If
     * no visibility settings are configured in <code>layoutData</code> and <code>kgeNode</code> is
     * <code>null</code>, <code>null</code> is returned.
     *
     * @param layoutData
     *            the {@link KLayoutData} to be assessed for visibility settings, should not be
     *            <code>null</code>
     * @param kgeNode
     *            the {@link KGraphElementNode} to be configured with <code>layoutData</code>'s
     *            visibility settings, maybe <code>null</code>
     * @return <code>null</code> if <code>layoutData</code> is <code>null</code>,
     *         <code>kgeNode</code>, or a new instance of {@link KlighdNode}, if
     *         <code>kgeNode</code> is <code>null</code> and <code>layoutData</code> contains
     *         visibility settings
     */
    protected static KlighdNode evaluateVisibilityDefinitions(
            final KLayoutData layoutData, final KGraphElementNode<?> kgeNode) {

        if (layoutData == null) {
            return null;
        }

        final Number scaleLB =
                layoutData.getProperty(KlighdProperties.VISIBILITY_SCALE_LOWER_BOUND).floatValue();

        if (kgeNode != null) {
            kgeNode.setVisibilityBounds(scaleLB.floatValue(), DEFAULT_SCALE_UB.floatValue());
            return kgeNode;

        } else if (scaleLB == DEFAULT_SCALE_LB) {
            return null;

        } else {
            return new KlighdNode() {
                private static final long serialVersionUID = 1L;

                /* Constructor */ {
                    this.setVisibilityBounds(scaleLB.floatValue(), DEFAULT_SCALE_UB.floatValue());
                }

                public EObject getViewModelElement() {
                    return null;
                }

                @Override
                public boolean isSelectable() {
                    return false;
                }
            };
        }
    }

    private T graphElement;

    /**
     * Constructs a corresponding Piccolo2D node representing the given {@link KGraphElement}.
     * 
     * @param element
     *            the {@link KGraphElement}
     */
    public KGraphElementNode(final T element) {
        this.graphElement = element;

        evaluateVisibilityDefinitions(element.getData(KLayoutData.class), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisibilityBounds(final float lowerBound, final float upperBound) {
        super.setVisibilityBounds(lowerBound, upperBound);
    }

    /**
     * {@inheritDoc}
     */
    public T getViewModelElement() {
        return graphElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSelectable() {
        return KlighdProperties.isSelectable(getViewModelElement());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScale(final double scale) {
        final double curScale = getScale();

        if (scale == curScale) {
            return;
        } else if (scale == 0) {
            throw new RuntimeException("Can't set scale to 0");
        }
        scale(scale / curScale);
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * KLighD contributes a visibility check in this method.<br>
     * Note that the labels of labeled kGraph elements are currently skipped, too, if the
     * element itself is masked.
     */
    @Override
    public boolean fullPick(final PPickPath pickPath) {
        final PCamera topCam = pickPath.getTopCamera();

        // first test whether this figure is visible at all
        //  we shamelessly assume that scaleX == scaleY ;-)
        if (isNotVisibleOn(topCam.getViewTransformReference().getScaleX())) {
            return false;
        }

        return super.fullPick(pickPath);
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * KLighD contributes a visibility check in this method.<br>
     * Note that the labels of labeled kGraph elements are currently skipped, too, if the
     * element itself is masked.
     */
    @Override
    public void fullPaint(final PPaintContext paintContext) {
        final KlighdPaintContext kpc = (KlighdPaintContext) paintContext;
        if (isNotVisibleOn(kpc)) {
            return;
        }
        super.fullPaint(paintContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        final KlighdPaintContext kpc = (KlighdPaintContext) paintContext;

        if (kpc.isAddSemanticData()) {
            final KlighdSemanticDiagramData sd = getViewModelElement().getData(KLayoutData.class)
                    .getProperty(KlighdProperties.SEMANTIC_DATA);
            kpc.getKlighdGraphics().startGroup(sd);
        }
       
        super.paint(paintContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintAfterChildren(final PPaintContext paintContext) {
        super.paintAfterChildren(paintContext);

        final KlighdPaintContext kpc = (KlighdPaintContext) paintContext;
        if (kpc.isAddSemanticData()) {
            kpc.getKlighdGraphics().endGroup();
        }
    }
}