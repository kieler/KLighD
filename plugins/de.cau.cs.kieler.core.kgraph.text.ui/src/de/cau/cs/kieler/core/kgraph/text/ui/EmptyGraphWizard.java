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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * The new-wizard for creating empty KGraphs.
 *
 * @author msp
 */
public class EmptyGraphWizard extends Wizard implements INewWizard {

    /** the selection this wizard is invoked on. */
    private IStructuredSelection selection;
    /** the new file page. */
    private WizardNewFileCreationPage newFilePage;
    
    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench, final IStructuredSelection theselection) {
        this.selection = theselection;
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
        newFilePage.createNewFile();
        return true;
    }

}
