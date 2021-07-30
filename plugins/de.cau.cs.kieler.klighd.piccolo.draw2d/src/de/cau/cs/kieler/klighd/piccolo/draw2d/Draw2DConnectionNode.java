/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
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
package de.cau.cs.kieler.klighd.piccolo.draw2d;

import java.awt.geom.Point2D;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.UpdateManager;
import org.eclipse.draw2d.geometry.PointList;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.klighd.Klighd;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccolo;
import de.cau.cs.kieler.klighd.piccolo.draw2d.Draw2DNode.WrappingUpdateManager;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KCustomConnectionFigureNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.piccolo.internal.util.Styles;

/**
 * A Piccolo2D node implementation wrapping a Draw2d connections.<br>
 * <br>
 * Note: This is a very experimental implementation that have not been tested.
 * 
 * @author chsch
 */
public class Draw2DConnectionNode extends KCustomConnectionFigureNode {

    /** The serial version UID. */
    private static final long serialVersionUID = -2181523653415457063L;

    /** The connection figure that is displayed by this node. */
    private Connection figure;
    
    /**
     * This update manager is in charge of propagating repaint requests the Piccolo nodes if
     * necessary.
     */
    private UpdateManager updateManager;
    
    /** The required GraphicsAdapter providing the necessary "drawing" API. */
    private GraphicsAdapter graphics = new GraphicsAdapter();
    
    /**
     * Create a Draw2D node with the given figure.
     * 
     * @param theFigure a Draw2D figure
     */
    public Draw2DConnectionNode(final Connection theFigure) {
        this.graphics = new GraphicsAdapter();
        this.updateManager = new WrappingUpdateManager(this);        
        this.figure = new PolylineConnection() {
            {
                this.setOutline(false);
                this.setFill(false);
            }
            
            @Override
            public void setPoints(final PointList pts) {
                @SuppressWarnings("unchecked")
                final
                List<IFigure> children = this.getChildren();
                for (final Connection child : Iterables.filter(children, Connection.class)) {
                    child.setPoints(pts);
                }
            }
            
            @Override
            public UpdateManager getUpdateManager() {
                return Draw2DConnectionNode.this.updateManager;
            }
        };
        this.figure.add(theFigure);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPoints(final Point2D[] points) {
        final int[] iPoints = new int[2 * points.length];
        int i = 0;
        for (final Point2D point : points) {
            // SUPPRESS CHECKSTYLE NEXT 2 MagicNumber: Rounding!
            iPoints[2 * i++] = (int) (point.getX() + 0.5);
            iPoints[2 * i++] = (int) (point.getY() + 0.5);
        }
        this.figure.setPoints(new PointList(iPoints));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void applyStyles(final Styles styles) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final KlighdPaintContext kpc) {

        this.graphics.setKlighdSWTGraphics((KlighdSWTGraphicsEx) kpc.getKlighdGraphics());
        try {
            figure.paint(this.graphics);
        } catch (final Throwable throwable) {
            final String msg = "KLighD: Error occurred while drawing the custom connection figure "
                    + this.figure.getClass().getName();
            Klighd.log(new Status(IStatus.ERROR, KlighdPiccolo.PLUGIN_ID, msg, throwable));
        }
    }
}
