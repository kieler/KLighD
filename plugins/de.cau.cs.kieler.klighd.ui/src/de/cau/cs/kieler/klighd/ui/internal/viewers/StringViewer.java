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
package de.cau.cs.kieler.klighd.ui.internal.viewers;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.KlighdTreeSelection;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * A viewer for string messages.
 * 
 * chsch: TODO class and its interaction with the framework must be reviewed!!
 * 
 * @author mri
 * @author chsch
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
    public String getModel() {
        return message;
    }

    /**
     * {@inheritDoc}
     */
    public ViewContext getViewContext() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(final String model) {
        this.setModel(model, false);
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(final String model, final boolean sync) {
        if (model == null) {
            return;
        }
        message = model;
    }

    
    // remaining methods of the IViewer interface
    //  their application is not reasonable for this viewer

    /**
     * {@inheritDoc}
     */
    public ContextViewer getContextViewer() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void reveal(final Object semanticElement, final int duration) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void reveal(final KGraphElement diagramElement, final int duration) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void centerOn(final Object semanticElement, final int duration) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void centerOn(final KGraphElement diagramElement, final int duration) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void zoomToLevel(final float zoomLevel, final int duration) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void zoom(final ZoomStyle style, final int duration) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExpanded(final Object semanticElement) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExpanded(final KNode diagramElement) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void collapse(final Object semanticElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void collapse(final KNode diagramElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void expand(final Object semanticElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void expand(final KNode diagramElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void toggleExpansion(final Object semanticElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void toggleExpansion(final KNode diagramElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void hide(final Object semanticElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void hide(final KGraphElement diagramElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void show(final Object semanticElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void show(final KGraphElement diagramElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void clip(final Object semanticElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void clip(final KNode diagramElement) {
        // do nothing
    }
    
    /**
     * {@inheritDoc}
     */
    public KNode getClip() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public KlighdTreeSelection getSelection() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final Object semanticElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final KGraphElement diagramElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final KText diagramElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOfSemanticElements(final Set<Object> semanticElements) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOfDiagramElements(final Set<? extends EObject> diagramElements) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final Object diagramElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final KGraphElement diagramElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final KText diagramElement) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void resetSelectionToSemanticElements(final Iterable<? extends Object> semanticElements) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void resetSelectionToDiagramElements(final Iterable<? extends EObject> diagramElements) {
        // do nothing
    }
}
