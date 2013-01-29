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
package de.cau.cs.kieler.klighd.piccolo.krendering.viewer;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolox.swt.PSWTCanvas;

/**
 * A content outline page for the Piccolo viewer.
 *
 * @author msp
 */
public class PiccoloOutlinePage implements IContentOutlinePage {
    
    /** the canvas used for drawing. */
    private PSWTCanvas canvas;
    /** the graph layer to display. */
    private PLayer graphLayer;

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        canvas = new PSWTCanvas(parent, SWT.NONE);
        canvas.setDoubleBuffered(false);
        setContent(graphLayer);
    }

    /**
     * {@inheritDoc}
     */
    public Control getControl() {
        return canvas;
    }

    /**
     * {@inheritDoc}
     */
    public void setFocus() {
        canvas.setFocus();
    }
    
    /**
     * Update the content of the outline page.
     * 
     * @param newLayer the graph layer to display
     */
    public void setContent(final PLayer newLayer) {
        if (canvas != null) {
            if (this.graphLayer != null) {
                this.graphLayer.getRoot().removeChild(canvas.getCamera());
                this.graphLayer.removeCamera(canvas.getCamera());
            }
            PCamera camera = new PCamera();
            newLayer.getRoot().addChild(camera);
            camera.addLayer(newLayer);
            canvas.setCamera(camera);
        }
        this.graphLayer = newLayer;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        // nothing to do here
    }

    /**
     * {@inheritDoc}
     */
    public void setActionBars(final IActionBars actionBars) {
        // no action to register
    }

    /**
     * {@inheritDoc}
     */
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
        // selection is not supported by this outline page
    }

    /**
     * {@inheritDoc}
     */
    public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
        // selection is not supported by this outline page
    }

    /**
     * {@inheritDoc}
     */
    public ISelection getSelection() {
        // selection is not supported by this outline page
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setSelection(final ISelection selection) {
        // selection is not supported by this outline page
    }

}
