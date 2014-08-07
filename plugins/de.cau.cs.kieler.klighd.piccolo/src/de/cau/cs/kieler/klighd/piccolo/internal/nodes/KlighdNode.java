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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.KlighdSemanticDiagramData;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * Common base class of KLighD-specific {@link PNode PNodes}.
 * 
 * @author chsch
 */
public class KlighdNode extends PNode {

    private static final long serialVersionUID = 6876586117083105843L;

    // SUPPRESS CHECKSTYLE NEXT 3 Visibility -- fields are package protected in order to be accessed
    //  for initialization, see inner sub classes
    float lowerScaleBound = 0;
    float upperScaleBound = -1;

    /**
     * Constructor.
     */
    public KlighdNode() {
        this.addPropertyChangeListener(NodeDisposeListener.DISPOSE, new NodeDisposeListener(this));
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
     * @param diagramScale the diagram scale factor to be applied
     * 
     * @return <code>true</code> if this (pseudo) figure should not be drawn on the diagram being
     *         drawn in the given <code>diagramScale</code>
     */
    public boolean isNotVisibleOn(final double diagramScale) {
        final double upperBound = getUpperVisibilityBound();
        return diagramScale < getLowerVisibilityBound()
                || upperBound != -1 && diagramScale > upperBound;
    }
    
    /**
     * {@inheritDoc}
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
     * A.
     * 
     * @author chsch
     */
    public abstract static class KlighdGraphNode<T extends KGraphElement> extends KlighdNode implements
            IGraphElement<T> {

        private static final long serialVersionUID = -5577703758022742813L;

        private T graphElement;

        /**
         * Constructs a corresponding Piccolo2D node representing the given {@link KGraphElement}.
         * 
         * @param element
         *            the {@link KGraphElement}
         */
        public KlighdGraphNode(final T element) {
            this.graphElement = element;
            
            final KLayoutData layoutData = element.getData(KLayoutData.class);
            if (layoutData != null) {
                this.lowerScaleBound = layoutData.getProperty(
                        KlighdProperties.VISIBILITY_SCALE_LOWER_BOUND).floatValue();
                this.upperScaleBound = layoutData.getProperty(
                        KlighdProperties.VISIBILITY_SCALE_UPPER_BOUND).floatValue();
            }
        }

        /**
         * {@inheritDoc}
         */
        public T getGraphElement() {
            return graphElement;
        }


        /**
         * {@inheritDoc}<br>
         * <br>
         * KLighD contributes a visibility check in this method.<br>
         * Note that the labels of labeled kgraph elements are currently skipped, too, if the
         * element itself is masked.
         */
        @Override
        public void fullPaint(final PPaintContext paintContext) {
            final KlighdPaintContext kpc = (KlighdPaintContext) paintContext;
            if (isNotVisibleOn(kpc.getCameraZoomScale())) {
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
                final KlighdSemanticDiagramData sd = getGraphElement().getData(KLayoutData.class)
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

    /**
     * B.
     * 
     * @author chsch
     */
    public static class KlighdFigureNode<T extends KRendering> extends KlighdNode implements
            ITracingElement<T> {

        private static final long serialVersionUID = -3975636790695588901L;

        private T rendering;

        /**
         * Provides the {@link KRendering} element being represented by this {@link KlighdFigureNode}.
         * 
         * @return the {@link KRendering} element being represented by this {@link KlighdFigureNode}
         */
        public T getRendering() {
            return rendering;
        }

        /**
         * Configures the {@link KRendering} element being represented by this {@link KlighdFigureNode}.
         * 
         * @param rendering
         *            the {@link KRendering} element being represented by this {@link KlighdFigureNode}
         */
        public void setRendering(final T rendering) {
            this.rendering = rendering;
            
            if (rendering != null) {
                this.lowerScaleBound = rendering.getProperty(
                        KlighdProperties.VISIBILITY_SCALE_LOWER_BOUND).floatValue();
                this.upperScaleBound = rendering.getProperty(
                        KlighdProperties.VISIBILITY_SCALE_UPPER_BOUND).floatValue();
            }
        }

        /**
         * {@inheritDoc}
         */
        public T getGraphElement() {
            return rendering;
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
