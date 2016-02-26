/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.util.List;

import de.cau.cs.kieler.klighd.krendering.KArc;
import de.cau.cs.kieler.klighd.krendering.KChildArea;
import de.cau.cs.kieler.klighd.krendering.KCustomRendering;
import de.cau.cs.kieler.klighd.krendering.KEllipse;
import de.cau.cs.kieler.klighd.krendering.KImage;
import de.cau.cs.kieler.klighd.krendering.KPolygon;
import de.cau.cs.kieler.klighd.krendering.KPolyline;
import de.cau.cs.kieler.klighd.krendering.KRectangle;
import de.cau.cs.kieler.klighd.krendering.KRenderingRef;
import de.cau.cs.kieler.klighd.krendering.KRoundedBendsPolyline;
import de.cau.cs.kieler.klighd.krendering.KRoundedRectangle;
import de.cau.cs.kieler.klighd.krendering.KSpline;
import de.cau.cs.kieler.klighd.krendering.KStyle;
import de.cau.cs.kieler.klighd.krendering.KText;
import de.cau.cs.kieler.klighd.krendering.util.KRenderingSwitch;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode;

/**
 * @author chsch
 */
class PNodeConstructionKRenderingSwitch extends KRenderingSwitch<PNodeController<?>> {

    /**
     * Standard constructor.
     *
     * @param theController
     *            the controller that uses this switch and that is delegated to while calling
     *            further methods
     */
    PNodeConstructionKRenderingSwitch(final AbstractKGERenderingController<?, ?> theController) {
        this.controller = theController;
    }

    private AbstractKGERenderingController<?, ?> controller;
    private List<KStyle> childPropagatedStyles = null;
    private IKlighdNode parent = null;
    private Bounds initialBounds = null;

    /**
     * A.
     *
     * @param theChildPropagatedStyles
     *            propagated style definitions
     * @param theParent
     *            the parent {@link IKlighdNode} to attach the created children to
     * @param theInitialBounds
     *            the available area to be covered by the child {@link IKlighdNode.IKlighdFigureNode}
     */
    public void configure(final List<KStyle> theChildPropagatedStyles, final IKlighdNode theParent,
            final Bounds theInitialBounds) {
        this.childPropagatedStyles = theChildPropagatedStyles;
        this.parent = theParent;
        this.initialBounds = theInitialBounds;
    }

    // Ellipse
    @Override
    public PNodeController<?> caseKEllipse(final KEllipse ellipse) {
        return KGERenderingControllerHelper.createEllipse(controller, ellipse,
                childPropagatedStyles, parent, initialBounds);
    }

    // Rectangle
    @Override
    public PNodeController<?> caseKRectangle(final KRectangle rect) {
        return KGERenderingControllerHelper.createRectangle(controller, rect,
                childPropagatedStyles, parent, initialBounds);
    }

    // Rounded Rectangle
    @Override
    public PNodeController<?> caseKRoundedRectangle(final KRoundedRectangle rect) {
        return KGERenderingControllerHelper.createRoundedRectangle(controller, rect,
                childPropagatedStyles, parent, initialBounds);
    }

    // Arc
    @Override
    public PNodeController<?> caseKArc(final KArc arc) {
        return KGERenderingControllerHelper.createArc(controller, arc, childPropagatedStyles,
                parent, initialBounds);
    }

    // Spline
    @Override
    public PNodeController<?> caseKSpline(final KSpline spline) {
        return KGERenderingControllerHelper.createLine(controller, spline, childPropagatedStyles,
                parent, initialBounds);
    }

    // Polyline
    @Override
    public PNodeController<?> caseKPolyline(final KPolyline polyline) {
        return KGERenderingControllerHelper.createLine(controller, polyline, childPropagatedStyles,
                parent, initialBounds);
    }

    // RoundedBendPolyline
    @Override
    public PNodeController<?> caseKRoundedBendsPolyline(final KRoundedBendsPolyline polyline) {
        return KGERenderingControllerHelper.createLine(controller, polyline, childPropagatedStyles,
                parent, initialBounds);
    }

    // Polygon
    @Override
    public PNodeController<?> caseKPolygon(final KPolygon polygon) {
        return KGERenderingControllerHelper.createPolygon(controller, polygon,
                childPropagatedStyles, parent, initialBounds);
    }

    // Text
    @Override
    public PNodeController<?> caseKText(final KText text) {
        return KGERenderingControllerHelper.createText(controller, text, childPropagatedStyles,
                parent, initialBounds);
    };

    // Rendering Reference
    @Override
    public PNodeController<?> caseKRenderingRef(final KRenderingRef renderingReference) {
        return KGERenderingControllerHelper.createRenderingReference(controller,
                renderingReference, childPropagatedStyles, parent, initialBounds);
    }

    // Image
    @Override
    public PNodeController<?> caseKImage(final KImage image) {
        return KGERenderingControllerHelper.createImage(controller, image, childPropagatedStyles,
                parent, initialBounds);
    }

    // Custom Rendering
    @Override
    public PNodeController<?> caseKCustomRendering(final KCustomRendering rendering) {
        return KGERenderingControllerHelper.createCustomRendering(controller, rendering,
                childPropagatedStyles, parent, initialBounds);
    }

    // Child Area
    @Override
    public PNodeController<?> caseKChildArea(final KChildArea childArea) {
        return controller.createChildArea(parent, childArea, initialBounds);
    }

}
