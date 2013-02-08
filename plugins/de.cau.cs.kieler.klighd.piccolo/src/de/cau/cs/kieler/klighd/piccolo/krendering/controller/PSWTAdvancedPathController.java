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

import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.core.krendering.LineCap;
import de.cau.cs.kieler.core.krendering.LineStyle;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath;


/**
 * A node controller for the {@code PSWTAdvancedPath}.
 * 
 * @author mri, chsch
 */
public abstract class PSWTAdvancedPathController extends PNodeController<PSWTAdvancedPath> {

    /**
     * Constructs a node controller for a {@code PSWTAdvancedPath}.
     * 
     * @param node
     *            the path
     */
    public PSWTAdvancedPathController(final PSWTAdvancedPath node) {
        super(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundColor(final RGB color) {
        getNode().setStrokeColor(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundColor(final RGB color) {
        getNode().setPaint(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidth(final float lineWidth) {
        getNode().setLineWidth(lineWidth);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineAlpha(final int lineAlpha) {
        if (lineAlpha == 0) {
            getNode().setStrokeColor(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundAlpha(final int backgroundAlpha) {
        if (backgroundAlpha == 0) {
            getNode().setPaint((RGB) null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineStyle(final LineStyle lineStyle) {
        getNode().setLineStyle(lineStyle);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineCap(final LineCap lineCapStyle) {
        getNode().setLineCapStyle(lineCapStyle);
    }

    /**
     * {@inheritDoc}
     */
    public void setRotation(final float rotation) {
        getNode().setRotation(Math.toRadians(rotation));
    }
    
}
