/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.view;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.progress.UIJob;

/**
 * Listens to the workspace and manages the editor for the associated {@link DiagramView}.
 * 
 * @author als
 * @kieler.design 2015-09-29 proposed
 * @kieler.rating 2015-09-29 proposed yellow
 * 
 */
class DiagramViewEditorAdapter implements IPartListener2 {

    /** The job name for delayed view initialization. */
    private static final String INIT_JOB_NAME = "Initializing Diagram";
    /** The related diagram view. */
    private final DiagramView diagramView;

    /**
     * Create a new listener handling events for the given {@link DiagramView}.
     * <p>
     *
     * The diagram view must be already created because the adapter will register itself immediately
     * on the views page.
     * 
     * @param diagramView
     *            The associated {@link DiagramView}
     */
    DiagramViewEditorAdapter(final DiagramView diagramView) {
        this.diagramView = diagramView;
    }
    
    /**
     * Activated this adapter.
     */
    public void activate() {
        diagramView.getSite().getPage().addPartListener(this);
    }
    
    /**
     * Deactivated this adapter.
     */
    public void deactivate() {
        diagramView.getSite().getPage().removePartListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void partOpened(final IWorkbenchPartReference partRef) {
        IWorkbenchPart part = partRef.getPart(false);
        // Initialize primary view with current active editor
        if (part != null && part == diagramView) {
            if (diagramView.isLinkedWithActiveEditor()) {
                // update to active editor (delayed to prevent klighd init errors)
                new UIJob(INIT_JOB_NAME) {

                    @Override
                    public IStatus runInUIThread(final IProgressMonitor monitor) {
                        diagramView.setEditor(diagramView.getSite().getPage().getActiveEditor());
                        return Status.OK_STATUS;
                    }
                }.schedule(2);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void partClosed(final IWorkbenchPartReference partRef) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void partActivated(final IWorkbenchPartReference partRef) {
        IWorkbenchPart part = partRef.getPart(false);
        if (diagramView.isLinkedWithActiveEditor() && part instanceof IEditorPart) {
            diagramView.setEditor((IEditorPart) part);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void partBroughtToTop(final IWorkbenchPartReference partRef) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void partDeactivated(final IWorkbenchPartReference partRef) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void partHidden(final IWorkbenchPartReference partRef) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void partVisible(final IWorkbenchPartReference partRef) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void partInputChanged(final IWorkbenchPartReference partRef) {
    }
}
