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
package de.cau.cs.kieler.klighd;

import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klighd.util.KlighdColor;

/**
 * The interface for viewers on unrestricted models.
 * 
 * @author mri
 * 
 * @param <T>
 *            the type of the model this viewer accepts
 */
public interface IViewer<T> {

    /**
     * Returns the control used by this viewer.
     * 
     * @return the control
     */
    Control getControl();

    /**
     * Sets the input model for this viewer.
     * 
     * @param model
     *            the input model
     */
    void setModel(T model);

    /**
     * Sets a highlighting effect on the given diagram elements using the specified foreground and
     * background color and factor for the line width.
     * 
     * @param diagramElements
     *            the diagram elements
     * @param foreground
     *            the foreground color or null for no change
     * @param background
     *            the background color or null for no change
     * @param lineWidthFactor
     *            the factor the line width is increased by (1.0f for no change)
     */
    void setHighlight(Object[] diagramElements, KlighdColor foreground, KlighdColor background,
            float lineWidthFactor);

    /**
     * Removes the highlighting effect from the given diagram elements if any.
     * 
     * @param diagramElements
     *            the diagram elements
     */
    void removeHighlight(Object[] diagramElements);

    /**
     * Sets the given selection of diagram elements as current selection.
     * 
     * @param diagramElements
     *            the diagram elements
     */
    void setSelection(Object[] diagramElements);

    /**
     * Clears the current selection.
     */
    void clearSelection();

    /**
     * Adds the given diagram elements to the current selection if possible.
     * 
     * @param diagramElements
     *            the diagram elements
     */
    void select(Object[] diagramElements);

    /**
     * Removes the given diagram elements from the current selection if possible.
     * 
     * @param diagramElements
     *            the diagram elements
     */
    void unselect(Object[] diagramElements);

    /**
     * Reveals the given diagram element over the specified duration.
     * 
     * @param diagramElement
     *            the diagram element
     * @param duration
     *            the duration
     */
    void reveal(Object diagramElement, int duration);

    /**
     * Centers on the given diagram element over the specified duration.
     * 
     * @param diagramElement
     *            the diagram element
     * @param duration
     *            the duration
     */
    void centerOn(Object diagramElement, int duration);

    /**
     * Zooms to the given zoom level over the specified duration.
     * 
     * @param zoomLevel
     *            the zoom level
     * @param duration
     *            the duration
     */
    void zoom(float zoomLevel, int duration);

    /**
     * Peforms a zoom-to-fit over the specified duration.
     * 
     * @param duration
     *            the duration
     */
    void zoomToFit(int duration);

    /**
     * Adds an event listener to the viewer.
     * 
     * @param listener
     *            the event listener
     */
    void addEventListener(IViewerEventListener listener);

    /**
     * Removes an event listener from the viewer.
     * 
     * @param listener
     *            the event listener
     */
    void removeEventListener(IViewerEventListener listener);

}
