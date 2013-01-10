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
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.UpdateManager;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.KlighdPlugin;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.swt.SWTGraphics2D;

/**
 * A Piccolo node implementation wrapping a Draw2d figure.
 * 
 * TODO: Implement the {@link WrappingUpdateManager}.
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
        this.updateManager = new WrappingUpdateManager();        
        this.figure = new Figure() {
            
            @Override
            public UpdateManager getUpdateManager() {
                return Draw2DNode.this.updateManager;
            }
        };
        this.figure.setLayoutManager(new StackLayout());
        this.figure.add(theFigure);
    }
    
    /**
     * {@inheritDoc}<br>
     * <br>
     * This implementation adapts the bounds of the draw2d figure and delegates to the
     * super implementation as required.
     */
    public boolean setBounds(final double x, final double y, final double width, final double height) {
        this.figure.setBounds(new Rectangle((int) (x), (int) (y), (int) (width), (int) (height)));
        return super.setBounds(x, y, width, height);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        this.graphics.setSWTGraphics2D((SWTGraphics2D) paintContext.getGraphics());
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
     * A custom {@link UpdateManager} realizing the escalating of repainting requests to the
     * Piccolo nodes.
     * 
     * TODO to be implemented
     * 
     * @author chsch
     */
    static class WrappingUpdateManager extends DeferredUpdateManager {
    }
}
