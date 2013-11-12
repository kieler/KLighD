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
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;

/**
 * The KLighD viewer can be embedded into a SWT component and is able to accept any type of input
 * model. When a model is set as input it tries to find a visualization using the light diagram
 * services.
 * 
 * XXX what is this used for? seems just like a dumb wrapper for ContextViewer
 * 
 * @author mri
 */
public class KlighdViewer extends AbstractViewer<Object> {

    /** the context viewer used to visualize models. */
    private ContextViewer contextViewer;

    /**
     * Constructs a KLighD viewer into a given parent composite.
     * 
     * @param parent
     *            the parent composite
     */
    public KlighdViewer(final Composite parent) {
        contextViewer = new ContextViewer(parent, null, null);
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
    public void setModel(final Object model, final boolean sync) {
        ViewContext viewContext = LightDiagramServices.createViewContext(model);
        if (viewContext != null) {
            contextViewer.setModel(viewContext);
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
    public IContentOutlinePage getOutlinePage() {
        return contextViewer.getOutlinePage();
    }

    /**
     * {@inheritDoc}
     */
    public void startRecording() {
        contextViewer.startRecording();
    }
    
    /**
     * {@inheritDoc}
     */
    public void stopRecording(final ZoomStyle zoomStyle,
            final int animationTime) {
        contextViewer.stopRecording(zoomStyle, animationTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void zoomToLevel(final float zoomLevel, final int duration) {
        contextViewer.zoomToLevel(zoomLevel, duration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reveal(final KGraphElement diagramObject, final int duration) {
        contextViewer.reveal(diagramObject, duration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void centerOn(final KGraphElement diagramElement, final int duration) {
        contextViewer.centerOn(diagramElement, duration);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void centerOn(final Object semanticElement, final int duration) {
        contextViewer.centerOn(semanticElement, duration);
    }

}
