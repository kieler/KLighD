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
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * The interface for viewers on incrementally updated models.<br>
 * <br>
 * All method calls on this interface have to be made from the UI thread.
 * 
 * @author mri
 * @author chsch
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
     * Returns the {@link ContextViewer} containing this viewer.
     * 
     * @return the {@link ContextViewer}
     */
    ContextViewer getContextViewer();
    
    /**
     * Sets the input model for this viewer.
     * 
     * @param model
     *            the input model
     */
    void setModel(T model);

    /**
     * Sets the input model for this viewer.<br>
     * <br>
     * If synchronization is enabled his model is incrementally updated by KLighD. The
     * implementation of the viewer should utilize a listener mechanic on the model to keep the
     * visualization in sync.
     * 
     * @param model
     *            the input model
     * @param sync
     *            true if the viewer should synchronize the visualization with the model; false else
     */
    void setModel(T model, boolean sync);

    /**
     * Returns the input model currently set for this viewer.
     * 
     * @return the input model or null if no input model is set
     */
    T getModel();
    
    /**
     * Returns a content outline page for this viewer.
     * 
     * @return a content outline page
     */
    IContentOutlinePage getOutlinePage();

    /**
     * Sets whether to record layout changes in the model instead of instantly applying them to the
     * visualization.<br>
     * <br>
     * Setting the recording status to {@code false} applies all recorded layout changes.
     * 
     * @param recording
     *            true if layout changes should be recorded; false else
     */
    void setRecording(final boolean recording);

    /**
     * Instructs the viewer to perform 'zoom to fit' after layout has been applied.
     * 
     * @param zoomToFit
     *            true if 'zoom to fit' shall be performed.
     * @author chsch
     */
    void setZoomToFit(final boolean zoomToFit);

    /**
     * Sets the given selection of diagram elements as current selection.
     * 
     * @param diagramElements
     *            the diagram elements
     */
    void setSelection(Iterable<KGraphElement> diagramElements);

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
    void select(Iterable<KGraphElement> diagramElements);

    /**
     * Removes the given diagram elements from the current selection if possible.
     * 
     * @param diagramElements
     *            the diagram elements
     */
    void unselect(Iterable<KGraphElement> diagramElements);

    /**
     * Reveals the representation of the given semantic element over the specified duration.
     * 
     * @param semanticElement
     *            the semantic element to center on
     * @param duration
     *            the duration over which the panning animation is done
     */
    void reveal(Object semanticElement, int duration);
    
    /**
     * Reveals the given diagram element over the specified duration.
     * 
     * @param diagramElement
     *            the diagram element
     * @param duration
     *            the duration
     */
    void reveal(KGraphElement diagramElement, int duration);

    /**
     * Centers on the representation of the given semantic element over the specified duration.
     * 
     * @param semanticElement
     *            the semantic element to center on
     * @param duration
     *            the duration over which the panning animation is done
     */
    void centerOn(Object semanticElement, int duration);
    
    /**
     * Centers on the given diagram element over the specified duration.
     * 
     * @param diagramElement
     *            the diagram element
     * @param duration
     *            the duration over which the panning animation is done
     */
    void centerOn(KGraphElement diagramElement, int duration);
    
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
     * Performs a zoom-to-fit over the specified duration.
     * 
     * @param duration
     *            the duration
     */
    void zoomToFit(int duration);

    /**
     * Collapses the representation of the given element. Note that there must exist related element
     * <-> diagram element tracking information in the {@link TransformationContexts}.
     * 
     * @author chsch
     * 
     * @param semanticElement
     *            the semantic element to be expanded
     */
    void collapse(Object semanticElement);
    
    /**
     * Expands the given representation element.
     * 
     * @param diagramElement the diagram element to be expanded
     */
    void collapse(KNode diagramElement);

    /**
     * Expands the representation of the given element, i.e. populates it with children and changes
     * its rendering if necessary. Note that there must exist related element <-> diagram element
     * tracking information in the {@link TransformationContexts}.
     * 
     * @author chsch
     * 
     * @param semanticElement
     *            the semantic element to be expanded
     */
    void expand(Object semanticElement);
    
    /**
     * Expands the given representation element.
     * 
     * @param diagramElement the diagram element to be expanded
     */
    void expand(KNode diagramElement);

    /**
     * Expands the representation of the given element if collapsed and vice versa. Note that there
     * must exist related element <-> diagram element tracking information in the
     * {@link TransformationContexts}.
     * 
     * @author chsch
     * 
     * @param semanticElement
     *            the semantic element to be expanded
     */
    void toggleExpansion(Object semanticElement);
    
    /**
     * Toggles the expansion state of the given representation element. If it is expanded, it gets
     * collapsed, and vice versa.
     * 
     * @param diagramElement
     *            the diagram element to be expanded
     */
    void toggleExpansion(KNode diagramElement);

    /**
     * Hides the representation of the given {@link Object} from the diagram. In combination with
     * {@link #show(Object)} this method can be used for changing the diagram's amount of detail
     * without changing the view model.
     * 
     * @param semanticElement
     *            the semantic element to be hidden
     */
    void hide(Object semanticElement);
    
    /**
     * Hides the given {@link KGraphElement} from the diagram. In combination with
     * {@link #show(KGraphElement)} this method can be used for changing the diagram's amount of
     * detail without changing the view model.
     * 
     * @param diagramElement
     *            the diagram element to be hidden
     */
    void hide(KGraphElement diagramElement);
    
    /**
     * Shows the representation of the given {@link Object} in the diagram. In combination with
     * {@link #hide(Object)} this method can be used for changing the diagram's amount of detail
     * without changing the view model.
     * 
     * @param semanticElement
     *            the semantic element to show up
     */
    void show(Object semanticElement);
    
    /**
     * Shows the given {@link KGraphElement} from the diagram. In combination with
     * {@link #hide(KGraphElement)} this method can be used for changing the diagram's amount of
     * detail without changing the view model.
     * 
     * @param diagramElement
     *            the diagram element to show up
     */
    void show(KGraphElement diagramElement);
    
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
