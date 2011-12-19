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

import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.IViewerEventListener;
import de.cau.cs.kieler.klighd.util.KlighdColor;

/**
 * A viewer for string messages.
 * 
 * @author mri
 */
public class StringViewer implements IViewer<String> {

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
    public void setModel(final String model) {
        if (model == null) {
            return;
        }
        synchronized (message) {
            message = model;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setHighlight(final Object[] diagramElements, final KlighdColor foreground,
            final KlighdColor background, final float lineWidthFactor) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void removeHighlight(final Object[] diagramElements) {
        // do nothing
    }
    
    /**
     * {@inheritDoc}
     */
    public void setSelection(final Object[] diagramElements) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void clearSelection() {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void select(final Object[] diagramElements) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void unselect(final Object[] diagramElements) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void zoom(final float zoomLevel, final int duration) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void zoomToFit(final int duration) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void reveal(final Object diagramElement, final int duration) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void centerOn(final Object diagramElement, final int duration) {
        // do nothing
    }
    
    /**
     * {@inheritDoc}
     */
    public void addEventListener(final IViewerEventListener listener) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void removeEventListener(final IViewerEventListener listener) {
        // do nothing
    }

}
