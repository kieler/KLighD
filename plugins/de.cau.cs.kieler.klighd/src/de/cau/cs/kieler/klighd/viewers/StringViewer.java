/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.viewers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.klighd.ZoomStyle;

/**
 * A viewer for string messages.
 * 
 * chsch: TODO class and its interaction with the framework must be reviewed!!
 * 
 * @author mri
 */
public class StringViewer extends AbstractViewer<String> {

    /** the canvas used to display the message. */
    private Canvas canvas = null;
    /** the currently displayed message. */
    private String message = "";

    /**
     * Constructs a string viewer.
     * 
     * @param parent
     *            the parent composite
     */
    public StringViewer(final Composite parent) {
        // add a canvas for displaying the message
        canvas = new Canvas(parent, SWT.NONE);
        canvas.addPaintListener(new PaintListener() {
            public void paintControl(final PaintEvent e) {
                synchronized (message) {
                    e.gc.drawString(message, 0, 0, true);
                }
            }
        });
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
    public ContextViewer getContextViewer() {
        // Doesn't track the parent viewer, yet.
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(final String model) {
        setModel(model, false);
    }
    
    /**
     * {@inheritDoc}
     */
    public String getModel() {
        return message;
    }
    
    /**
     * {@inheritDoc}
     */
    public void setModel(final String model, final boolean sync) {
        if (model == null) {
            return;
        }
        // chsch: is the synchronization actually needed? The way below it won't work right, anyway!
        // synchronized (message) {
            message = model;
        // }
    }

    /**
     * {@inheritDoc}
     */
    public IContentOutlinePage getOutlinePage() {
        // no outline required
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public void setRecording(final boolean recording) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void startRecording() {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void stopRecording(final ZoomStyle zoomStyle, final int animationTime) {
        // do nothing
    }
    
}
