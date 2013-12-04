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

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.ZoomStyle;

/**
 * An abstract base class for concrete KGraph/KRendering viewers. It provides implementations of
 * those methods concrete viewers are not in charge of implementing, e.g. the source model related
 * ones ({@link Object}-based) and the selection related ones. The view model related methods (
 * {@link KGraphElement}/{@link de.cau.cs.kieler.core.kgraph.KNode KNode}/{@link KText}-based ones)
 * must be implemented by concrete viewers.
 * 
 * @author mri
 * @author chsch
 * 
 * @param <T>
 *            the type of the model this viewer accepts
 */
public abstract class AbstractViewer<T> implements IViewer<T> {

    /**
     * {@inheritDoc}
     */
    public void setModel(final T model) {
        this.setModel(model, false);
    }

    /**
     * {@inheritDoc}
     */
    public IContentOutlinePage getOutlinePage() {
        return null;
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

    /**
     * {@inheritDoc}
     */
    public void zoomToLevel(final float zoomLevel, final int duration) {
        getContextViewer().zoomToLevel(zoomLevel, duration);
    }

    /**
     * {@inheritDoc}
     */
    public void zoom(final ZoomStyle style, final int duration) {
        getContextViewer().zoom(style, duration);
    }


    /* ----------------------------- */
    /*   the view manipulation API   */
    /* ----------------------------- */

    /**
     * {@inheritDoc}
     */
    public boolean isExpanded(final Object semanticElement) {
        return getContextViewer().isExpanded(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void collapse(final Object semanticElement) {
        getContextViewer().collapse(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void expand(final Object semanticElement) {
        getContextViewer().expand(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleExpansion(final Object semanticElement) {
        getContextViewer().toggleExpansion(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void hide(final Object semanticElement) {
        getContextViewer().hide(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void show(final Object semanticElement) {
        getContextViewer().show(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void clip(final Object semanticElement) {
        getContextViewer().clip(semanticElement);
    }


    /**
     * {@inheritDoc}
     */
    public void reveal(final Object semanticElement, final int duration) {
        getContextViewer().reveal(semanticElement, duration);
    }
    
    /**
     * {@inheritDoc}
     */
    public void centerOn(final Object semanticElement, final int duration) {
        getContextViewer().centerOn(semanticElement, duration);
    }
    

    /* ---------------------------------------------------------- */
    /*   the selection setting API                                */
    /*    it is completely implemented by the ContextViewer,      */
    /*    no implementations of this class need to implement it!  */
    /* ---------------------------------------------------------- */

    /**
     * {@inheritDoc}
     */
    public KlighdTreeSelection getSelection() {
        return getContextViewer().getSelection();
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final Object semanticElement) {
        getContextViewer().toggleSelectionOf(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final KGraphElement diagramElement) {
        getContextViewer().toggleSelectionOf(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final KText diagramElement) {
        getContextViewer().toggleSelectionOf(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOfSemanticElements(final Set<Object> diagramElements) {
        getContextViewer().toggleSelectionOfSemanticElements(diagramElements);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOfDiagramElements(final Set<? extends EObject> diagramElements) {
        getContextViewer().toggleSelectionOfDiagramElements(diagramElements);
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final Object semanticElement) {
        getContextViewer().resetSelectionTo(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final KGraphElement diagramElement) {
        getContextViewer().resetSelectionTo(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final KText diagramElement) {
        getContextViewer().resetSelectionTo(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionToSemanticElements(final Iterable<? extends Object> diagramElements) {
        getContextViewer().resetSelectionToSemanticElements(diagramElements);
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionToDiagramElements(final Iterable<? extends EObject> diagramElements) {
        getContextViewer().resetSelectionToDiagramElements(diagramElements);
    }
}

