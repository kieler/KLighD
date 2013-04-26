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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.DeferredUpdateManager;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IClippingStrategy;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.UpdateManager;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphicsImpl;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A Piccolo2D node implementation wrapping a Draw2d figure.
 *
 * @author msp, chsch
 */
public class Draw2DNode extends PNode {

    /** The serial version UID. */
    private static final long serialVersionUID = -1948310925725969628L;
    
    /** The figure that is displayed by this node. */
    private Figure figure;
    
    /**
     * This update manager is in charge of propagating repaint requests the Piccolo nodes if
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
            {
                this.setClippingStrategy(new IClippingStrategy() {
                    public Rectangle[] getClip(final IFigure childFigure) {
                        Rectangle bounds = childFigure.getBounds();
                        // some experimental extension of the clip area in
                        //  order to avoid artifacts of partially clipped
                        //  polylines and polygones of the childFigure
                        //  (the actual figure to be depicted)
                        // SUPPRESS CHECKSTYLE NEXT 2 MagicNumber
                        Rectangle extBounds = new Rectangle(bounds.x - 20, bounds.y - 20,
                                bounds.width + 40, bounds.height + 40);
                        return new Rectangle[] { extBounds };
                    }
                });
            }
            
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
            public void setBounds(final Rectangle r) {
                super.setBounds(r);
            }
            
            @Override
            public void paintFigure(final Graphics theGraphics) {
                super.paintFigure(theGraphics);
            }
        };
        
        this.figure.add(theFigure);
        this.figure.setLayoutManager(new StackLayout());
    }
    
    /**
     * {@inheritDoc}<br>
     * <br>
     * This implementation adapts the bounds of the draw2d figure and delegates to the
     * super implementation as required.
     */
    public boolean setBounds(final double x, final double y, final double width, final double height) {
        this.figure.setBounds(new Rectangle((int) (x), (int) (y), (int) (width), (int) (height)));
        this.figure.revalidate();
        return super.setBounds(x, y, width, height);
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
            final String msg = "KLighD: Error occurred while drawing the diagram figure "
                    + this.figure.getClass().getName();
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, throwable),
                    StatusManager.LOG);
        }
    }

    /**
     * A custom {@link UpdateManager} that might realize the escalating of repaint requests to the
     * wrapping Piccolo nodes.
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
            //  to the Piccolo2D node in future (TODO).
            return null;
        }
    }
}
