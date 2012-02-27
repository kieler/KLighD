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

import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath.LineStyle;

/**
 * A node controller for the {@code PSWTAdvancedPath}.
 * 
 * @author mri
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
    public void setForegroundColor(final Color color) {
        getNode().setStrokeColor(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundColor(final Color color) {
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
    public void setLineVisible(final boolean lineVisible) {
        if (!lineVisible) {
            getNode().setStrokeColor(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFilled(final boolean filled) {
        if (!filled) {
            getNode().setPaint(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineStyle(final LineStyle lineStyle) {
        getNode().setLineStyle(lineStyle);
    }

}
