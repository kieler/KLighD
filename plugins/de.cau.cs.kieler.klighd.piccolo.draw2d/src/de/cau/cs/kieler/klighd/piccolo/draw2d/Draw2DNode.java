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
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.UpdateManager;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KCustomFigureNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.piccolo.internal.util.Styles;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;

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

    // some private fields for tracking the figure's original attributes
    //  in case those attributes are changed for highlighting purposes!
    private Color initialForeground = null;
    private Color initialBackground = null;
    private Float initialLineWidth = null;
    private Integer initialLineStyle = null;

    /**
     * Create a Draw2D node with the given figure.
     * 
     * @param theFigure
     *            a Draw2D figure
     */
    public Draw2DNode(final Figure theFigure) {
        if (theFigure == null) {
            final String msg = "KLighD draw2d binding: Constructor of Draw2DNode has "
                            + "been called with argument of null. This is not allowed!";
            throw new IllegalArgumentException(msg);
        }
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
        
        this.initialBackground = theFigure.getBackgroundColor();
        this.initialForeground = theFigure.getForegroundColor();
        
        if (theFigure instanceof Shape) {
            this.initialLineWidth = ((Shape) theFigure).getLineWidthFloat();
            this.initialLineStyle = ((Shape) theFigure).getLineStyle();
        }
    }

    private final Rectangle2D singletonRectDouble = new Rectangle2D.Double();
    private final Rectangle singletonRectDraw2d = new Rectangle();

    /**
     * {@inheritDoc}<br>
     * <br>
     * This implementation adapts the bounds of the draw2d figure and delegates to the super
     * implementation as required.
     */
    @Override
    public boolean setBounds(final double x, final double y, final double width, final double height) {
        // convert the bounds to integer-based ones by means of the smart method
        //  RectangularShape#getBounds() and store them in the Draw2d Rectangle 'singletonRectDraw2d'
        this.singletonRectDouble.setRect(x, y, width, height);
        final java.awt.Rectangle intRect = this.singletonRectDouble.getBounds();
        this.singletonRectDraw2d.setBounds(intRect.x, intRect.y, intRect.width, intRect.height);

        // now put them into the custom Draw2d figure and (re-)validate it
        this.figure.setBounds(this.singletonRectDraw2d);
        this.figure.validate();

        return super.setBounds(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyStyles(final Styles styles) {
        final IFigure drawnFigure = (IFigure) figure.getChildren().get(0);
        
        //apply background color
        if (styles.background != null) {
            if (drawnFigure.getBackgroundColor() != null
                    && drawnFigure.getBackgroundColor() != this.initialBackground) {
                drawnFigure.getBackgroundColor().dispose();
            }
            
            final KColor bgColor = styles.background.getColor();
            drawnFigure.setBackgroundColor(
                    new Color(null, bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue()));
        } else {
            if (drawnFigure.getBackgroundColor() != null
                    && drawnFigure.getBackgroundColor() != this.initialBackground) {
                drawnFigure.getBackgroundColor().dispose();
            }
            drawnFigure.setBackgroundColor(this.initialBackground);
        }
        
        //apply foreground color
        if (styles.foreground != null) {
            if (drawnFigure.getForegroundColor() != null
                    && drawnFigure.getForegroundColor() != this.initialForeground) {
                drawnFigure.getForegroundColor().dispose();
            }
            
            final KColor fgColor = styles.foreground.getColor();
            drawnFigure.setForegroundColor(
                    new Color(null, fgColor.getRed(), fgColor.getGreen(), fgColor.getBlue()));
        } else {
            if (drawnFigure.getForegroundColor() != null
                    && drawnFigure.getForegroundColor() != this.initialForeground) {
                drawnFigure.getForegroundColor().dispose();
            }
            drawnFigure.setForegroundColor(this.initialForeground);
        }
        
        //if figure is a shape we can configure more attributes
        if (drawnFigure instanceof Shape) {
            final Shape drawnShape = (Shape) drawnFigure;
            
            //set line style
            if (styles.lineWidth != null) {
                if (this.initialLineWidth == null) {
                    this.initialLineWidth = drawnShape.getLineWidthFloat();
                }
                
                drawnShape.setLineWidthFloat(styles.lineWidth.getLineWidth());
                
            } else {
                if (this.initialLineWidth != null
                        && this.initialLineWidth != drawnShape.getLineWidthFloat()) {
                    drawnShape.setLineWidthFloat(this.initialLineWidth);
                }
            }
            
            //set line style
            if (styles.lineStyle != null) {
                if (this.initialLineStyle == null) {
                    this.initialLineStyle = drawnShape.getLineStyle();
                }
                
                drawnShape.setLineStyle(styles.lineStyle.getLineStyle().getValue());
                
            } else {
                if (this.initialLineStyle != null
                        && this.initialLineStyle != drawnShape.getLineStyle()) {
                    drawnShape.setLineStyle(this.initialLineStyle);
                }
            }
        }

        invalidatePaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void repaintFrom(final PBounds localBounds, final PNode childOrThis) {
        if (childOrThis == this) {
            // this customization increases the bounding box to be repainted by 2px
            //  in each direction, which is sometimes necessary (maybe because of
            //  bad figure implementations? ... don't know)
            // it is applied if this method is called via 'repaint()';
            final int inset = -2;
            localBounds.inset(inset, inset);
        }
        super.repaintFrom(localBounds, childOrThis);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final KlighdPaintContext kpc) {
        // paintContext.pushClip(getBounds());
        
        this.graphics.setKlighdSWTGraphics((KlighdSWTGraphicsEx) kpc.getKlighdGraphics());
        try {
            figure.paint(this.graphics);
        } catch (final Throwable throwable) {
            final String msg =
                    "KLighD: Error occurred while drawing the diagram figure "
                            + this.figure.getChildren().get(0).getClass().getName();
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
            // in combination with the queuing of the 'validate bounds' tasks
            // (bounds have not been correctly propagated to children)
            // bridging the decoupling, and thus validating the compound figure at once, works
            // as desired

            // UPDATE (chsch):
            // since 'queueWork()' is called in DeferredUpdateManager#addInvalidFigure(IFigure)
            //  BEFORE the invalid figure is added to the 'invalidFigures' list invoking the
            //  update won't have any affect
            // thus I deactivated the following call and call 'figure.validate()' in 'setBounds(...)'
            //  above instead of 'figure.invalidate()'; considering this the whole update manager
            //  construction may be obsolete now...
            // super.performUpdate();
        }

        @Override
        protected Graphics getGraphics(final Rectangle region) {
            // We do not allow the update manager to directly access the graphics object.
            // Redrawing is escalated to the Piccolo2D nodes in 'setBounds(...)' above by calling
            //  'super.setBounds(...)'
            return null;
        }
    }
}
