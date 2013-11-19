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

import java.awt.geom.Rectangle2D;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.DeferredUpdateManager;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.UpdateManager;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KCustomFigureNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.Styles;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A Piccolo2D node implementation wrapping a Draw2d figure.
 *
 * @author msp
 * @author chsch
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class Draw2DNode extends KCustomFigureNode {

    /** The serial version UID. */
    private static final long serialVersionUID = -1948310925725969628L;
    
    /** The figure that is displayed by this node. */
    private Figure figure;
    
    /**
     * This update manager is in charge of propagating repaint requests the Piccolo2D nodes if
     * necessary.
     */
    private UpdateManager updateManager;
    
    /** The required GraphicsAdapter providing the necessary "drawing" API. */
    private GraphicsAdapter graphics;
    
    /**
     * Create a Draw2D node with the given figure.
     * 
     * @param theFigure a Draw2D figure
     */
    public Draw2DNode(final Figure theFigure) {
        this.graphics = new GraphicsAdapter();
        this.updateManager = new WrappingUpdateManager(this);   
        this.figure = new Figure() {

            @Override
            public UpdateManager getUpdateManager() {
                return Draw2DNode.this.updateManager;
            }

            @Override
            public boolean useLocalCoordinates() {
                // the displacement of this figure is realized by the wrapping PNodes
                return true;
            }

            @Override
            public void paintFigure(final Graphics theGraphics) {
                // this overriding method is just for debugging purposes
                super.paintFigure(theGraphics);
            }
        };
        
        this.figure.add(theFigure);
        this.figure.setLayoutManager(new StackLayout());
    }
    
    private final Rectangle2D singletonRectDouble = new Rectangle2D.Double();
    private final Rectangle singletonRectDraw2d = new Rectangle();
    
    /**
     * {@inheritDoc}<br>
     * <br>
     * This implementation adapts the bounds of the draw2d figure and delegates to the
     * super implementation as required.
     */
    public boolean setBounds(final double x, final double y, final double width, final double height) {
        // convert the bounds to integer-based ones by means of the smart method
        //  RectangularShape#getBounds() and store them in the Draw2d Rectangle 'singletonRectDraw2d'
        this.singletonRectDouble.setRect(x, y, width, height);
        final java.awt.Rectangle intRect = this.singletonRectDouble.getBounds();
        this.singletonRectDraw2d.setBounds(intRect.x, intRect.y, intRect.width, intRect.height);
        
        // now put them into the custom Draw2d figure and re-validate it
        this.figure.setBounds(this.singletonRectDraw2d);
        this.figure.revalidate();
        
        return super.setBounds(x, y, width, height);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void applyStyles(final Styles styles) {
        // TODO
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        // paintContext.pushClip(getBounds());
        
        this.graphics.setKlighdSWTGraphics((KlighdSWTGraphicsEx) paintContext.getGraphics());
        try {
            figure.paint(this.graphics);
        } catch (Throwable throwable) {
            final String msg = "KLighD: Error occurred while drawing the diagram figure "
                    + this.figure.getClass().getName();
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, throwable),
                    StatusManager.LOG);
        }
        
        // paintContext.popClip(null);
    }

    /**
     * A custom {@link UpdateManager} that might realize the escalating of repaint requests to the
     * wrapping Piccolo2D nodes.
     * 
     * @author chsch
     */
    static final class WrappingUpdateManager extends DeferredUpdateManager {
        
        @SuppressWarnings("unused")
        private PNode wrappingPNode;
        
        WrappingUpdateManager(final PNode wrapper) {
            super();
            this.wrappingPNode = wrapper;
        }
        
        @Override
        public void queueWork() {
            // the construction with this fake update manager appear to do not work correctly
            //  in combination with the queuing of the 'validate bounds' tasks
            //  (bounds have not been correctly propagated to children)
            // bridging the decoupling, and thus validating the compound figure at once, works
            //  as desired
            super.performUpdate();
        }
        
        @Override
        protected Graphics getGraphics(final Rectangle region) {
            // We do not allow the update manager to directly access the
            //  graphics object, redraw requests shall be somehow escalated
            //  to the Piccolo2D node in future if necessary (TODO).
            return null;
        }
    }
}
