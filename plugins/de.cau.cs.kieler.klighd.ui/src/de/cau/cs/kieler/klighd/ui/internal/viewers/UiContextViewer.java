/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.viewers;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPartSite;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.IDiagramOutlinePage;
import de.cau.cs.kieler.klighd.ui.internal.Messages;
import de.cau.cs.kieler.klighd.ui.internal.SaveAsImageAction;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * A specialized {@link ContextViewer} being in charge of providing UI contributions like context
 * menu and propagation of the selection into the platform.<br>
 * It is used by the {@link de.cau.cs.kieler.klighd.ui.parts.DiagramEditorPart DiagramEditorPart}
 * and {@link de.cau.cs.kieler.klighd.ui.parts.DiagramViewPart DiagramViewPart}.
 * 
 * @author chsch
 */
public class UiContextViewer extends ContextViewer implements ISelectionProvider,
        IDiagramOutlinePage.Provider {

    private Composite diagramContainer;
    
    /**
     * Constructor.
     * 
     * @param parent
     *            the parent {@link Composite} the diagram canvas is to be attached to
     * @param part
     *            the {@link IDiagramWorkbenchPart} this {@link ContextViewer} is attached to (is
     *            only required for setting the selection provider in time!)
     */
    public UiContextViewer(final Composite parent, final IDiagramWorkbenchPart part) {
        super(parent);
        diagramContainer = parent;

        // register this selection provider in the current work bench part site;
        // this must be done within 'createPartControl()' of 'part',
        //  which is why this registration is done here rather than in 'setModel(...)'
        part.getSite().setSelectionProvider(this);

        // initialize with the display of an empty string
        showMessage("");
    }
    
    /**
     * {@inheritDoc}
     */
    public synchronized void setModel(final Object model, final boolean sync) {
        // if the model is a view context adapt the viewer to the given context if possible
        if (model instanceof ViewContext) {

            super.setModel(model, sync);

            // create the context menu
            final MenuManager menuManager = new MenuManager();

            final Control control = getControl();
            if (control != null) {
                // this test is just for safety purposes, should never be null
                // be careful: associating the menu with 'diagramContainer' does not work
                //  as it is completely covered by the active viewer's canvas!
                control.setMenu(menuManager.createContextMenu(control));
            }

            final Action saveAsImageAction =
                    new SaveAsImageAction(this, Messages.UiContextViewer_save_as_image_text);
            menuManager.add(saveAsImageAction);
            
            final IWorkbenchPartSite site = ((ViewContext) model).getDiagramWorkbenchPart().getSite();

            // register the context menu in the current work bench part site
            //  this enables the population with entries contributed via extension points
            site.registerContextMenu(menuManager, this);

        } else if (model instanceof String) {
            // if the model is a string show it
            showMessage((String) model);
            
            // provide no selection in this case!
        }
    }

    /**
     * Shows the given message.
     * 
     * @param message
     *            the message
     */
    private synchronized void showMessage(final String message) {
        final StringViewer stringViewer;
        
        if (!(getActiveViewer() instanceof StringViewer)) {
            stringViewer = new StringViewer(diagramContainer); 
            setViewer(stringViewer);            
        } else {
            stringViewer = (StringViewer) getActiveViewer();
        }
        
        stringViewer.setModel(message, false);
    }
}
