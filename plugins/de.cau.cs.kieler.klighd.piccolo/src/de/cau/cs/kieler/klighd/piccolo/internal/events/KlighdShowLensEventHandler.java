/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.events;

import java.awt.geom.Point2D;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.KlighdPreferences;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * An input event handler that puts, moves, and removes a magnifying lens on the corresponding
 * diagram's canvas if ALT + CTRL/CMD keys are pressed (and released respectively). 
 * 
 * @author chsch
 */
public class KlighdShowLensEventHandler extends KlighdBasicInputEventHandler {
    
    private static final IPreferenceStore STORE = KlighdPlugin.getDefault().getPreferenceStore();
    
    private final PCamera mainCamera;
    private final PCamera lensCamera;

    /**
     * Constructor.
     * 
     * @param canvasCamera
     *            the {@link PCamera} employed in the corresponding diagram canvas for drawing the
     *            diagram.
     */
    public KlighdShowLensEventHandler(final PCamera canvasCamera) {
        this.mainCamera = canvasCamera;
        this.lensCamera = new PCamera();
        this.lensCamera.setPickable(false);
        
        final KlighdPath path = new KlighdPath() {
            private static final long serialVersionUID = -4353599895034123565L;
            
            // SUPPRESS CHECKSTYLE NEXT 20 MagicNumber

            {
                final int width = STORE.getInt(KlighdPreferences.MAGNIFICATION_LENS_WIDTH);
                final int height = STORE.getInt(KlighdPreferences.MAGNIFICATION_LENS_HEIGHT);
                this.setVisible(false);
                this.setPathToRoundRectangle(-width / 2, -height / 2, width, height, 20, 20);
                this.setLineWidth(2);
                this.setPaint(new RGB(240, 240, 255));
                this.addChild(lensCamera);
                mainCamera.addChild(this);
            }
            
            @Override
            protected void layoutChildren() {
                final PBounds bounds = getBounds();
                bounds.setRect(bounds.x + 5, bounds.y + 5, bounds.width - 10, bounds.height - 10);
                
                for (Object child : getChildrenReference()) {
                    ((PNode) child).setBounds(bounds);
                }
            }            
        };

        final IPropertyChangeListener prefListener = new IPropertyChangeListener() {
            // SUPPRESS CHECKSTYLE NEXT 15 MagicNumber

            public void propertyChange(final PropertyChangeEvent event) {
                if (KlighdPreferences.MAGNIFICATION_LENS_WIDTH.equals(event.getProperty())) {
                    final float width = STORE.getInt(KlighdPreferences.MAGNIFICATION_LENS_WIDTH);
                    final float height = (float) path.getHeight();
                    path.setPathToRoundRectangle(-width / 2, -height / 2, width, height, 20, 20);
                }
                if (KlighdPreferences.MAGNIFICATION_LENS_HEIGHT.equals(event.getProperty())) {
                    final float width = (float) path.getWidth();
                    final int height = STORE.getInt(KlighdPreferences.MAGNIFICATION_LENS_HEIGHT);
                    path.setPathToRoundRectangle(-width / 2, -height / 2, width, height, 20, 20);
                }
            }
        };

        STORE.addPropertyChangeListener(prefListener);

        ((KlighdCanvas) canvasCamera.getComponent()).addDisposeListener(new DisposeListener() {
            
            public void widgetDisposed(final DisposeEvent e) {
                STORE.removePropertyChangeListener(prefListener);
                e.widget.removeDisposeListener(this);
            }
        });
    }
    
    private boolean lensVisible = false;
    
    private PAffineTransform createViewTransform(final PInputEvent event) {
        final Point2D pos = event.getPosition();
        final PAffineTransform viewTransform = new PAffineTransform();
        
        // SUPPRESS CHECKSTYLE NEXT MagicNumber -- the preference unit is percent        
        float scale = STORE.getFloat(KlighdPreferences.MAGNIFICATION_LENS_SCALE) / 100f;

        viewTransform.scale(scale, scale);
        viewTransform.translate(-pos.getX(), -pos.getY());
        return viewTransform;
    }
    
    private static final int CTRL_CMD = KlighdKeyEventListener.OS_MACOSX ? SWT.COMMAND : SWT.CTRL;
    
    @Override
    public void keyPressed(final PInputEvent event) {
        if (lensVisible) {
            return;
        } else if (event.isControlDown() && (event.getKeyCode() & SWT.ALT) != 0
                || event.isAltDown() && (event.getKeyCode() & CTRL_CMD) != 0) {
            event.setHandled(true);
            lensCamera.addLayer(mainCamera.getLayer(0));
            lensCamera.getParent().setOffset(event.getCanvasPosition());            
            lensCamera.setViewTransform(createViewTransform(event));

            
            lensCamera.setVisible(true);
            lensVisible = true;
            lensCamera.getParent().setVisible(true);
        }
    }

    @Override
    public void keyReleased(final PInputEvent event) {
        if (lensVisible
                && ((event.getKeyCode() & SWT.ALT) != 0 || (event.getKeyCode() & CTRL_CMD) != 0)) {
            event.setHandled(true);
            lensCamera.getParent().setVisible(false);
            lensVisible = false;
            lensCamera.getLayersReference().clear();
        }
    }
    
    @Override
    public void mouseMoved(final PInputEvent event) {
        if (lensVisible) {
            event.setHandled(true);
            lensCamera.getParent().setOffset(event.getCanvasPosition());            
            lensCamera.setViewTransform(createViewTransform(event));
        }
    }
}
