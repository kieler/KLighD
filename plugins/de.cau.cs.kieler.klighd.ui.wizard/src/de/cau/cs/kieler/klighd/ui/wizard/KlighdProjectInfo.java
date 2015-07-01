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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.wizard;

import org.eclipse.core.runtime.IPath;
import org.eclipse.xtext.ui.wizard.IProjectInfo;

import com.google.common.base.Strings;

/**
 * An container for information required to create a new KlighD project.
 * 
 * @author uru
 */
public class KlighdProjectInfo implements IProjectInfo {

    private String projectName;
    private IPath projectLocation;
    private String transformationPackage;
    private String transformationName;
    private String sourceModelClassFullyQualified;
    private boolean createXtendFile = false;
    private String executionEnvironment;
    private boolean createMenuContribution = false;
    private boolean useFileEnding = false;
    private String fileEnding;

    /**
     * {@inheritDoc}
     */
    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }

    /**
     * {@inheritDoc}
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @return the projectLocation
     */
    public IPath getProjectLocation() {
        return projectLocation;
    }

    /**
     * @param projectLocation the projectLocation to set
     */
    public void setProjectLocation(final IPath projectLocation) {
        this.projectLocation = projectLocation;
    }

    /**
     * @return the transformationName
     */
    public String getTransformationName() {
        return transformationName;
    }

    /**
     * @param transformationName
     *            the transformationName to set
     */
    public void setTransformationName(final String transformationName) {
        this.transformationName = transformationName;
    }

    /**
     * @param sourceModelClassFullyQualified
     *            fully qualified name of the source model
     */
    public void setSourceModelClassFullyQualified(final String sourceModelClassFullyQualified) {
        this.sourceModelClassFullyQualified = sourceModelClassFullyQualified;
    }

    /**
     * @return the fully qualified name of the source model
     */
    public String getSourceModelClassFullyQualified() {
        return sourceModelClassFullyQualified;
    }

    /**
     * @return simple name of the source model
     */
    public String getSourceModelClassSimple() {
        if (Strings.isNullOrEmpty(sourceModelClassFullyQualified)) {
            return "";
        }
        return sourceModelClassFullyQualified.substring(
                sourceModelClassFullyQualified.lastIndexOf(".") + 1,
                sourceModelClassFullyQualified.length());
    }

    /**
     * @return the transformationPackage
     */
    public String getTransformationPackage() {
        return transformationPackage;
    }

    /**
     * @param transformationPackage
     *            the transformationPackage to set
     */
    public void setTransformationPackage(final String transformationPackage) {
        this.transformationPackage = transformationPackage;
    }

    /**
     * @return the createXtendFile
     */
    public boolean isCreateXtendFile() {
        return createXtendFile;
    }

    /**
     * @param createXtendFile
     *            the createXtendFile to set
     */
    public void setCreateXtendFile(final boolean createXtendFile) {
        this.createXtendFile = createXtendFile;
    }

    /**
     * @return the chosen execution environment's id
     */
    public String getExecutionEnvironment() {
        return executionEnvironment;
    }

    /**
     * @param executionEnvironment
     *            the chosen execution environment's id
     */
    public void setExecutionEnvironment(final String executionEnvironment) {
        this.executionEnvironment = executionEnvironment;
    }

    /**
     * @return the createMenuContribution
     */
    public boolean isCreateMenuContribution() {
        return createMenuContribution;
    }

    /**
     * @param createMenuContribution
     *            whether to create menu contributions
     */
    public void setCreateMenuContribution(final boolean createMenuContribution) {
        this.createMenuContribution = createMenuContribution;
    }
    
    /**
     * @return whether to use the file ending for menu contributions
     */
    public boolean isUseFileEnding() {
        return useFileEnding;
    }
    
    /**
     * 
     * @param useFileEnding 
     *          whether to use the file ending for menu contributions
     */
    public void setUseFileEnding(final boolean useFileEnding) {
        this.useFileEnding = useFileEnding;
    }

    /**
     * @return  the file ending
     */
    public String getFileEnding() {
        return fileEnding;
    }
    
    /**
     * @param fileEnding 
     *          the file ending to be used for menu contributions
     */
    public void setFileEnding(final String fileEnding) {
        this.fileEnding = fileEnding;
    }
    
}
