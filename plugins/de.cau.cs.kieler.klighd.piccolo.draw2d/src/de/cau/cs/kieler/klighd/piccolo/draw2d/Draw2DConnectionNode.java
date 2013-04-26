/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
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
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.draw2d.Draw2DNode.WrappingUpdateManager;
import de.cau.cs.kieler.klighd.piccolo.krendering.KCustomConnectionFigureNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A Piccolo node implementation wrapping a Draw2d connections.<br>
 * <br>
 * Note: This is a very experimental implement that have not been tested.
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
            
            public void setPoints(final PointList pts) {
                @SuppressWarnings("unchecked")
                List<IFigure> children = (List<IFigure>) this.getChildren();
                for (Connection child : Iterables.filter(children, Connection.class)) {
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
        int[] iPoints = new int[2 * points.length];
        int i = 0;
        for (Point2D point : points) {
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
    protected void paint(final PPaintContext paintContext) {
        this.graphics.setKlighdSWTGraphics((KlighdSWTGraphicsImpl) paintContext.getGraphics());
        try {
            figure.paint(this.graphics);
        } catch (Throwable throwable) {
            final String msg = "KLighD: Error occurred while drawing the custom connection figure "
                    + this.figure.getClass().getName();
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, throwable),
                    StatusManager.LOG);
        }
    }
}
