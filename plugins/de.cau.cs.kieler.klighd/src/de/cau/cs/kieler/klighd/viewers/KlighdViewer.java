/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * The KLighD viewer can be embedded into a SWT component and is able to accept any type of input
 * model. When a model is set as input it tries to find a visualization using the light diagram
 * services. This class is intended to wrap the {@link ContextViewer}, it is, however, not clear
 * whether it is actually helpful in order to form a clean API.  
 * 
 * @author mri
 * @author chsch
 */
public class KlighdViewer extends AbstractViewer<Object> implements IViewer<Object> {

    /** the context viewer used to visualize models. */
    private ContextViewer contextViewer;

    /**
     * Constructs a KLighD viewer into a given parent composite.
     * 
     * @param parent
     *            the parent composite
     */
    public KlighdViewer(final Composite parent) {
        contextViewer = new ContextViewer(parent, null);
    }
    
    /**
     * Release all resources that were allocated for this viewer.
     */
    public void dispose() {
        contextViewer.dispose();
    }

    /**
     * Returns the context viewer utilized by this viewer.
     * 
     * @return the context viewer.
     */
    public ContextViewer getContextViewer() {
        return contextViewer;
    }
    
    /**
     * {@inheritDoc}
     */
    public Control getControl() {
        return contextViewer.getControl();
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(final Object model) {
        this.setModel(model, false);
    }
    
    /**
     * {@inheritDoc}
     */
    public void setModel(final Object model, final boolean sync) {
        ViewContext viewContext = LightDiagramServices.createViewContext(model);
        if (viewContext != null) {
            contextViewer.setModel(viewContext, sync);
            LightDiagramServices.updateViewContext(viewContext, model);
        } else {
            contextViewer.setModel("Could not find a visualization for the model.", false);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public Object getModel() {
        return contextViewer.getModel();
    }

    /**
     * {@inheritDoc}
     */
    public ViewContext getViewContext() {
        return contextViewer.getViewContext();
    }

    /**
     * {@inheritDoc}
     */
    public void reveal(final KGraphElement diagramElement, final int duration) {
        contextViewer.reveal(diagramElement, duration);
    }

    /**
     * {@inheritDoc}
     */
    public void centerOn(final KGraphElement diagramElement, final int duration) {
        contextViewer.centerOn(diagramElement, duration);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExpanded(final KNode diagramElement) {
        return contextViewer.isExpanded(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void collapse(final KNode diagramElement) {
        contextViewer.collapse(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void expand(final KNode diagramElement) {
        contextViewer.expand(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void toggleExpansion(final KNode diagramElement) {
        contextViewer.toggleExpansion(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void hide(final KGraphElement diagramElement) {
        contextViewer.hide(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void show(final KGraphElement diagramElement) {
        contextViewer.show(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void clip(final KNode diagramElement) {
        contextViewer.clip(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public KNode getClip() {
        return contextViewer.getClip();
    }
}
