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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.IViewerEventListener;
import de.cau.cs.kieler.klighd.util.KlighdColor;

/**
 * An abstract base class for viewers which provides an implementation for the handling of listeners
 * and an empty implementation for advanced functionality.
 * 
 * @author mri
 * 
 * @param <T>
 *            the type of the model this viewer accepts
 */
public abstract class AbstractViewer<T> implements IViewer<T> {

    /** the listeners registered on this viewer. */
    private Set<IViewerEventListener> listeners = new LinkedHashSet<IViewerEventListener>();

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
    public void reveal(final Object diagramObject, final int duration) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void centerOn(final Object diagramObject, final int duration) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void addEventListener(final IViewerEventListener listener) {
        listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void removeEventListener(final IViewerEventListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifies the registered listeners about the occurrence of a selection event.
     * 
     * @param selectedElements
     *            the selected elements
     */
    protected void notifyListenersSelection(final Collection<?> selectedElements) {
        for (IViewerEventListener listener : listeners) {
            listener.selected(this, selectedElements);
        }
    }

}

