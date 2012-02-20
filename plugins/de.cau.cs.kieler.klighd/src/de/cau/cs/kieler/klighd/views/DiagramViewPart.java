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
package de.cau.cs.kieler.klighd.views;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ResourceTransfer;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.triggers.KlighdResourceDropTrigger;
import de.cau.cs.kieler.klighd.triggers.KlighdResourceDropTrigger.KlighdResourceDropState;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * A view which is able to display models in light-weight diagrams.
 * 
 * @author mri
 */
public class DiagramViewPart extends ViewPart {

    /** the default name for this view. */
    public static final String DEFAULT_NAME = "Light Diagram";

    /** the viewer for this view part. */
    private ContextViewer viewer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        addLayoutButton();
        // create a context viewer
        viewer = new ContextViewer(parent, getViewSite().getSecondaryId());
        // install a drop handler for the view
        installDropHandler(parent);
        viewer.setModel("No model selected.");
        // register the context viewer as selection provider on the workbench
        getSite().setSelectionProvider(viewer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    /**
     * Returns the context viewer represented by this view part.
     * 
     * @return the context viewer
     */
    public ContextViewer getViewer() {
        return viewer;
    }

    /**
     * Sets a new name for the view.
     * 
     * @param name
     *            the name
     */
    public void setName(final String name) {
        setPartName(name);
    }

    /**
     * Installs a handler for dropping resources on the view.
     */
    private void installDropHandler(final Composite parent) {
        DropTarget target = new DropTarget(parent, DND.DROP_COPY | DND.DROP_DEFAULT);
        final ResourceTransfer resourceTransfer = ResourceTransfer.getInstance();
        target.setTransfer(new Transfer[] { resourceTransfer });
        target.addDropListener(new DropTargetListener() {

            public void drop(final DropTargetEvent event) {
                KlighdResourceDropTrigger trigger = KlighdResourceDropTrigger.getInstance();
                if (trigger != null) {
                    if (resourceTransfer.isSupportedType(event.currentDataType)
                            && event.data instanceof IResource[]) {
                        IResource[] resources = (IResource[]) event.data;
                        if (resources.length > 0) {
                            KlighdResourceDropState state =
                                    new KlighdResourceDropState(getViewSite().getSecondaryId(),
                                            resources[0]);
                            trigger.trigger(state);
                        }
                    }
                }
            }

            public void dropAccept(final DropTargetEvent event) {
                // do nothing
            }

            public void dragOperationChanged(final DropTargetEvent event) {
                if (event.detail == DND.DROP_DEFAULT) {
                    if ((event.operations & DND.DROP_COPY) != 0) {
                        event.detail = DND.DROP_COPY;
                    } else {
                        event.detail = DND.DROP_NONE;
                    }
                }
            }

            public void dragEnter(final DropTargetEvent event) {
                if (event.detail == DND.DROP_DEFAULT) {
                    if ((event.operations & DND.DROP_COPY) != 0) {
                        event.detail = DND.DROP_COPY;
                    } else {
                        event.detail = DND.DROP_NONE;
                    }
                }
            }

            public void dragOver(final DropTargetEvent event) {
                // do nothing
            }

            public void dragLeave(final DropTargetEvent event) {
                // do nothing
            }

        });
    }

    private void addLayoutButton() {
        final DiagramViewPart view = this;
        Action layout = new Action("Layout", KimlUiPlugin
                .getImageDescriptor("icons/menu16/kieler-arrange.gif")) {
            public void run() {
                try {
                    DiagramLayoutEngine layoutEngine = DiagramLayoutEngine.INSTANCE;
                    layoutEngine.layout(view, null, true, false, false, true, null);
                } catch (UnsupportedOperationException e) {
                    StatusManager.getManager().handle(
                            new Status(IStatus.WARNING, KlighdPlugin.PLUGIN_ID,
                                    "Performing automatic layout on view content of "
                                            + view.getPartName() + " failed with an Exception.", e));
                }
            }
        };
        // getViewSite().getActionBars().getMenuManager().add(layout);
        getViewSite().getActionBars().getToolBarManager().add(layout);
    }

}
