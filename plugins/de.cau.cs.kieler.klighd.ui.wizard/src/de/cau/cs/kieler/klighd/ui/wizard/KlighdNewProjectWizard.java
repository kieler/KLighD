/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.ui.wizard;

import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.ui.wizard.IProjectInfo;
import org.eclipse.xtext.ui.wizard.XtextNewProjectWizard;

import com.google.inject.Inject;

/**
 * New wizard for KlighD projects that leverages existing infrastructure provided by Xtext's
 * {@link XtextNewProjectWizard}.
 * 
 * @author uru
 */
public class KlighdNewProjectWizard extends XtextNewProjectWizard {

    private KlighdNewProjectCreationPage mainPage;

    /**
     * @param creator
     *            class responsible to create the actual project
     */
    @Inject
    public KlighdNewProjectWizard(final IProjectCreator creator) {
        super(creator);
        setWindowTitle(JavaUIMessages.KlighdNewProjectWizard_WizardTitle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        super.addPages();
        // we just use one wizard page
        mainPage = new KlighdNewProjectCreationPage("mainPage"); //$NON-NLS-1$
        addPage(mainPage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IProjectInfo getProjectInfo() {

        KlighdProjectInfo projectInfo = new KlighdProjectInfo();

        // gather all information required to create the project
        projectInfo.setProjectName(mainPage.getProjectName());
        projectInfo.setProjectLocation(mainPage.getLocationPath().append(projectInfo.getProjectName()));
        projectInfo.setTransformationName(mainPage.getTransformationName());
        projectInfo.setTransformationPackage(mainPage.getTransformationPackage());
        projectInfo.setSourceModelClassFullyQualified(mainPage.getSourceModel());
        projectInfo.setCreateXtendFile(mainPage.isCreateXtendFile());
        projectInfo.setExecutionEnvironment(mainPage.getExecEnvironment());
        projectInfo.setCreateMenuContribution(mainPage.isCreateMenuContribution());
        projectInfo.setUseFileEnding(mainPage.isUseFileEnding());
        projectInfo.setFileEnding(mainPage.getFileEnding());

        return projectInfo;
    }
}
