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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.klighd.krendering.KShadow;
import de.cau.cs.kieler.klighd.krendering.LineCap;
import de.cau.cs.kieler.klighd.krendering.LineJoin;
import de.cau.cs.kieler.klighd.krendering.LineStyle;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.util.RGBGradient;
import de.cau.cs.kieler.klighd.piccolo.internal.util.Styles;

/**
 * A specialized {@link PNodeController} dedicated to instances of {@link KlighdPath}.
 * 
 * @author mri, chsch
 */
public abstract class KlighdPathController extends PNodeController<KlighdPath> {

    /**
     * Constructs a node controller for a {@code PSWTAdvancedPath}.
     * 
     * @param node
     *            the path
     */
    public KlighdPathController(final KlighdPath node) {
        super(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundColor(final RGB color, final int alpha) {
        getNode().setStrokeAlpha(alpha);
        getNode().setStrokeColor(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundGradient(final RGBGradient gradient) {
        getNode().setStrokeColor(gradient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundColor(final RGB color, final int alpha) {
        getNode().setPaintAlpha(alpha);
        getNode().setPaint(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundGradient(final RGBGradient gradient) {
        getNode().setPaint(gradient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidth(final float lineWidth) {
        getNode().getLineAttributes().width = lineWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineStyle(final LineStyle lineStyle, final float[] dashPattern,
            final float dashOffset) {
        final LineAttributes lineAttributes = getNode().getLineAttributes();

        lineAttributes.dash = null;
        lineAttributes.dashOffset = dashOffset;

        switch (lineStyle) {
        case SOLID:
            lineAttributes.style = SWT.LINE_SOLID;
            break;
        case DASH:
            lineAttributes.style = SWT.LINE_DASH;
            break;
        case DOT:
            lineAttributes.style = SWT.LINE_DOT;
            break;
        case DASHDOT:
            lineAttributes.style = SWT.LINE_DASHDOT;
            break;
        case DASHDOTDOT:
            lineAttributes.style = SWT.LINE_DASHDOTDOT;
            break;
        case CUSTOM:
            lineAttributes.style = SWT.LINE_CUSTOM;
            lineAttributes.dash = dashPattern;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineCap(final LineCap lineCapStyle) {
        final LineAttributes lineAttributes = getNode().getLineAttributes();
        switch (lineCapStyle) {
        case CAP_FLAT:
            lineAttributes.cap = SWT.CAP_FLAT;
            break;
        case CAP_ROUND:
            lineAttributes.cap = SWT.CAP_ROUND;
            break;
        case CAP_SQUARE:
            lineAttributes.cap = SWT.CAP_SQUARE;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineJoin(final LineJoin lineJoin, final float miterLimit) {
        final LineAttributes lineAttributes = getNode().getLineAttributes();
        switch (lineJoin) {
        case JOIN_MITER:
            lineAttributes.join = SWT.JOIN_MITER;
            lineAttributes.miterLimit = miterLimit;
            break;
        case JOIN_ROUND:
            lineAttributes.join = SWT.JOIN_ROUND;
            break;
        case JOIN_BEVEL:
            lineAttributes.join = SWT.JOIN_BEVEL;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShadow(final KShadow shadow) {
        if (shadow != null) {
            getNode().setShadow(toRGB(shadow.getColor()), shadow.getXOffset(), shadow.getYOffset());
        } else {
            getNode().setShadow(toRGB(null), 0, 0);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void applyChanges(final Styles styles) {
        super.applyChanges(styles);

        // this simply flushes the internal update of the line attributes
        getNode().flushAttributes();
    }
}
