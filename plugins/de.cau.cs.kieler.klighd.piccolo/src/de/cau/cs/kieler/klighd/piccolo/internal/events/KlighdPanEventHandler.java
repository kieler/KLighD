/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.events;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Widget;

import de.cau.cs.kieler.klighd.KlighdPreferences;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PPanEventHandler;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PDimension;

/**
 * A specialization of {@link PPanEventHandler} with some customizations in the configuration and
 * non-configurable code. The latter requires some method overriding.
 * 
 * @author chsch
 */
public class KlighdPanEventHandler extends PPanEventHandler {

    /**
     * Constructor.
     * 
     * @param widget
     *            an SWT {@link Widget} corresponding to the current diagram, required only for
     *            reacting on its disposal in order to cleanup installed change listeners
     */
    public KlighdPanEventHandler(final Widget widget) {
        super();
        setAutopan(KlighdPreferences.isAdvancedPanningMode());

        KlighdPreferences.registerPrefChangeListener(widget, new IPropertyChangeListener() {

            public void propertyChange(final PropertyChangeEvent event) {
                if (KlighdPreferences.ADVANCED_PANNING_MODE.equals(event.getProperty())) {
                    setAutopan(KlighdPreferences.isAdvancedPanningMode());
                }
            }
        });
    }
    
    @Override
    protected void pan(final PInputEvent event) {
        // The reason for overriding this method is the replacement of 'event.getCamera()' by
        //  'event.getTopCamera()'.

        final PCamera cam = event.getTopCamera();
        final PDimension delta = new PDimension();
        
        final Point2D cp = event.getInputManager().getCurrentCanvasPosition();
        final Point2D lcp = event.getInputManager().getLastCanvasPosition();
//        final KlighdCanvas c = (KlighdCanvas) event.getComponent();
        
//        if (lcp.getX() < c.getSize().x && cp.getX() < c.getSize().x) {
            delta.width = cp.getX() - lcp.getX();
            delta.height = cp.getY() - lcp.getY();

            event.getPath().canvasToLocal(delta, cam);
            cam.localToView(delta);
            cam.translateView(delta.width, delta.height);
//        }
        
        
    }

    
    @Override
    protected void dragActivityStep(final PInputEvent event) {
        // This method is only required in case 'autopan' is set to 'true'.
        // The reason for overriding it is the replacement of
        //  'event.getCamera()' by 'event.getTopCamera()'.
        if (!getAutopan()) {
            return;
        }

        final PCamera c = event.getTopCamera();
        final PBounds b = c.getBoundsReference();
        final Point2D l = event.getPositionRelativeTo(c);
        final int outcode = b.outcode(l);
        final PDimension delta = new PDimension();
        
        // SUPPRESS CHECKSTYLE NEXT 15 MagicNumber

        if ((outcode & Rectangle2D.OUT_TOP) != 0) {
            delta.height = validatePanningSpeed(-1.0 - 0.5 * Math.abs(l.getY() - b.getY()));
        } else if ((outcode & Rectangle2D.OUT_BOTTOM) != 0) {
            delta.height = validatePanningSpeed(1.0 + 0.5 * Math.abs(
                    l.getY() - (b.getY() + b.getHeight())));
        }

        if ((outcode & Rectangle2D.OUT_RIGHT) != 0) {
            delta.width = validatePanningSpeed(1.0 + 0.5 * Math.abs(
                    l.getX() - (b.getX() + b.getWidth())));
        } else if ((outcode & Rectangle2D.OUT_LEFT) != 0) {
            delta.width = validatePanningSpeed(-1.0 - 0.5 * Math.abs(l.getX() - b.getX()));
        }

        c.localToView(delta);

        if (delta.width != 0 || delta.height != 0) {
            c.translateView(delta.width, delta.height);
        }
    }
}
