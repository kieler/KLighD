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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.geom.Point2D;

/**
 * Abstract class of all Piccolo nodes that are supposed to wrap connection figures of other 2D
 * drawing frameworks. It is used to distinguish connection nodes, which require a method
 * {@link #setPoints(Point2D[])}, from non-connection node. (see e.g.
 * {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.KEdgeRenderingController
 * KEdgeRenderingController})
 * 
 * @author chsch
 */
public abstract class KCustomConnectionFigureNode extends KCustomFigureNode {

    private static final long serialVersionUID = -7812579176308866285L;

    /**
     * Method to define the start, bend, and end points of the connection figure.
     * 
     * @param points
     *            an Array containing the start, bend, and end points of the connection figure.
     */
    public abstract void setPoints(final Point2D[] points);

}
