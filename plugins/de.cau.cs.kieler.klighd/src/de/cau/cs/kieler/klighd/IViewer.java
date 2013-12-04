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

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.viewers.KlighdTreeSelection;

/**
 * The interface for viewers displaying diagrams of provided models.<br>
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
     * Starts to record layout changes in the model instead of instantly applying them to the
     * visualization.<br>
     * <br>
     * Executing {@link #stopRecording(ZoomStyle, int)} applies all recorded layout changes.
     */
    void startRecording();
    
    /**
     * Stops to record layout changes, initialized by {@link #startRecording()}.
     * 
     * @param zoomStyle
     *            the style used to zoom, eg zoom to fit or zoom to focus
     * @param animationTime
     *            duration of the animated layout
     */
    void stopRecording(final ZoomStyle zoomStyle, final int animationTime);

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
    void zoomToLevel(float zoomLevel, int duration);
    
    /**
     * Performs the specified zoom style over the specified duration.
     * 
     * @param style
     *            the desired zoom stlye
     * @param duration
     *            the duration
     */
    void zoom(ZoomStyle style, int duration);
    
    /**
     * Provides the expansion state of the given representation element.
     * 
     * @param semanticElement
     *            being visualized by a (hierarchic) {@link KNode}
     * @return true if the {@link KNode} related to <code>semanticElement</code> is expanded, false
     *         otherwise.
     */
    boolean isExpanded(Object semanticElement);
    
    /**
     * Provides the expansion state of the given diagram node.
     * 
     * @param diagramElement
     *            a (hierarchic) {@link KNode}
     * @return true if the {@link KNode} <code>diagramElement</code> is expanded, false otherwise.
     */
    boolean isExpanded(KNode diagramElement);

    /**
     * Collapses the representation of the given element. Note that there must exist related element
     * <-> diagram element tracking information in the {@link TransformationContexts}.
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
     * Limits the visible elements of the diagram to the content of the representation of the given
     * {@link Object} without causing any change on the view model. Hence, this method can be used
     * for changing the diagram's amount of detail without changing the view model.<br>
     * The clip can be reset to the whole diagram by calling <code>clip((Object) null)</code>.
     * 
     * @param semanticElement
     *            the semantic element to whose representation the diagram view is to be limited,
     *            may be <code>null</code>
     */
    void clip(Object semanticElement);
    
    /**
     * Limits the visible elements of the diagram to the content of the given {@link KNode} without
     * causing any change on the view model. Hence, this method can be used for changing the
     * diagram's amount of detail without changing the view model.<br>
     * The clip can be reset to the whole diagram by calling <code>clip((KNode) null)</code>.
     * 
     * @param diagramElement
     *            the diagram element to which the diagram view is to be limited, may be
     *            <code>null</code>
     */
    void clip(KNode diagramElement);
    
    // a getClipSemantic() is still absent, it's not clear whether that is really necessary
    
    /**
     * Provides the currently set diagram clip.
     * 
     * @return the {@link KNode} that is currently clipped.
     */
    KNode getClip();


    /* ----------------------------- */
    /*   the selection setting API   */
    /* ----------------------------- */

    
    /**
     * Provides the current {@link org.eclipse.jface.viewers.ISelection} provided by the diagram viewer.
     *  
     * @return the current {@link org.eclipse.jface.viewers.ISelection}
     */
    KlighdTreeSelection getSelection();
    
    /**
     * Adds or removes the representative of the provided element to/from the current selection
     * depending on its presence in the current selection.
     * 
     * @param semanticElement
     *            the element to add or remove
     */
    void toggleSelectionOf(Object semanticElement);
  
    /**
     * Adds or removes a diagram element to/from the current selection depending on its presence in
     * the current selection.
     * 
     * @param diagramElement
     *            the element to add or remove
     */
    void toggleSelectionOf(KGraphElement diagramElement);
 
    /**
     * Adds or removes a diagram element to/from the current selection depending on its presence in
     * the current selection.
     * 
     * @param diagramElement
     *            the element to add or remove
     */
    void toggleSelectionOf(KText diagramElement);
 
    /**
     * Adds or removes the representatives of the provided set of semantic elements to/from the
     * current selection depending on their particular presences in the current selection.
     * 
     * @param semanticElements
     *            the elements to add or remove
     */
    void toggleSelectionOfSemanticElements(Set<Object> semanticElements);

    /**
     * Adds or removes a set of diagram element to/from the current selection depending on their
     * particular presences in the current selection.
     * 
     * @param diagramElements
     *            the elements to add or remove
     */
    void toggleSelectionOfDiagramElements(Set<? extends EObject> diagramElements);

    /**
     * Dismisses the current selection and creates a new one containing the only the representative
     * of the provided element.
     * 
     * @param diagramElement
     *            the element to be put into the new selection
     */
    void resetSelectionTo(Object diagramElement);

    /**
     * Dismisses the current selection and creates a new one containing the provided diagram
     * element.
     * 
     * @param diagramElement
     *            the element to be put into the new selection
     */
    void resetSelectionTo(KGraphElement diagramElement);

    /**
     * Dismisses the current selection and creates a new one containing the provided diagram
     * element.
     * 
     * @param diagramElement
     *            the element to be put into the new selection
     */
    void resetSelectionTo(KText diagramElement);

    /**
     * Dismisses the current selection and creates a new one containing the representatives of the
     * provided elements.
     * 
     * @param semanticElements
     *            the elements to be put into the new selection
     */
    void resetSelectionToSemanticElements(Iterable<? extends Object> semanticElements);

    /**
     * Dismisses the current selection and creates a new one containing the provided diagram
     * elements.
     * 
     * @param diagramElements
     *            the elements to be put into the new selection
     */
    void resetSelectionToDiagramElements(Iterable<? extends EObject> diagramElements);
}
