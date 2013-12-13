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

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPartSite;

import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.IDiagramOutlinePage;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * 
 * @author chsch
 */
public class UiContextViewer extends ContextViewer implements ISelectionProvider,
        IDiagramOutlinePage.Provider {

    private Composite diagramContainer;
    
    private MenuManager menuManager;
    
    /**
     * Constructor.
     * 
     * @param parent a
     */
    public UiContextViewer(final Composite parent) {
        super(parent);
        diagramContainer = parent;
        
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
            menuManager = new MenuManager();

            final Control control = getControl();
            if (control != null) {
                // this test is just for safety purposes, should never be null
                // be careful: associating the menu with 'diagramContainer' does not work
                //  as it is completely covered by the active viewer's canvas!
                control.setMenu(menuManager.createContextMenu(control));
            }

            final IWorkbenchPartSite site = ((ViewContext) model).getDiagramWorkbenchPart().getSite();

            // register this selection provider in the current work bench part site 
            site.setSelectionProvider(this);

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
     * Getter.
     *
     * @return the {@link MenuManager} associated with <code>this</code> viewer.
     */
    public MenuManager getContextMenuManager() {
        return this.menuManager;
        // add the 'save-as-image' action
//        Action saveAsImageAction =
//                new SaveAsImageAction(this, Messages.PiccoloViewer_save_as_image_text);
//        menuManager.add(saveAsImageAction);
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
