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
package de.cau.cs.kieler.core.kgraph.text.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;

/**
 * The new-wizard for creating empty KGraphs.
 * 
 * @author msp
 * @author uru
 */
public class EmptyGraphWizard extends Wizard implements INewWizard {

    /** the selection this wizard is invoked on. */
    private IStructuredSelection selection;
    /** the new file page. */
    private WizardNewFileCreationPage newFilePage;
    /** the current workbench. */
    private IWorkbench workbench;

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench theworkbench, final IStructuredSelection theselection) {
        this.selection = theselection;
        this.workbench = theworkbench;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        newFilePage = new WizardNewFileCreationPage("createEmptyGraphPage", selection);
        newFilePage.setTitle("Create Empty KGraph");
        newFilePage.setDescription("Select a name for a new KGraph file with no content.");
        newFilePage.setFileExtension("kgt"); //$NON-NLS-1$
        newFilePage.setAllowExistingResources(true);
        addPage(newFilePage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {

        // try to show the new file wherever possible and open it in an editor
        // code copied from {@link org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard}

        IWorkbenchWindow dw = workbench.getActiveWorkbenchWindow();
        IFile file = newFilePage.createNewFile();

        BasicNewFileResourceWizard.selectAndReveal(file, dw);

        // Open editor on new file.
        try {
            if (dw != null) {
                IWorkbenchPage page = dw.getActivePage();
                if (page != null) {
                    IDE.openEditor(page, file, true);
                }
            }
        } catch (PartInitException e) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KGraphUiModule.PLUGIN_ID, e.getMessage(), e),
                    StatusManager.SHOW);
        }

        return true;
    }

}
