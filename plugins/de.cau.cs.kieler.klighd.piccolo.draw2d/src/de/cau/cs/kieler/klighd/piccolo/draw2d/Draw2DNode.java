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
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.KlighdPlugin;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.swt.SWTGraphics2D;

/**
 * A Piccolo node implementation wrapping a Draw2D figure.
 *
 * @author msp, chsch
 */
public class Draw2DNode extends PNode {

    /** The serial version UID. */
    private static final long serialVersionUID = -1948310925725969628L;
    
    /** The figure that is displayed by this node. */
    private Figure figure;
    
    /** The required GraphicsAdapter providing the necessary "drawing" API. */
    private GraphicsAdapter graphics = new GraphicsAdapter();
    
    /**
     * Create a Draw2D node with the given figure.
     * 
     * @param figure a Draw2D figure
     */
    public Draw2DNode(final Figure figure) {
        this.figure = figure;
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
        // GraphicsAdapter graphics = new GraphicsAdapter((SWTGraphics2D) paintContext.getGraphics());
        // safe some object creations by re-using the GraphicsAdapter
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
     * {@inheritDoc}
     */
    public void fullPaint(final PPaintContext paintContext) {
        super.fullPaint(paintContext);
    }
}
