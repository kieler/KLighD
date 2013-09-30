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
package de.cau.cs.kieler.klighd.ui.wizard;

import org.eclipse.xtext.ui.wizard.IProjectInfo;

import com.google.common.base.Strings;

/**
 * An container for information required to create a new KlighD project.
 * 
 * @author uru
 */
public class KlighdProjectInfo implements IProjectInfo {

	private String projectName;
	private String transformationPackage;
	private String transformationName;
	private String sourceModelClassFullyQualified;
	private boolean createXtendFile = false;

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
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
	public void setTransformationName(String transformationName) {
		this.transformationName = transformationName;
	}

	public void setSourceModelClassFullyQualified(
			String sourceModelClassFullyQualified) {
		this.sourceModelClassFullyQualified = sourceModelClassFullyQualified;
	}

	public String getSourceModelClassFullyQualified() {
		return sourceModelClassFullyQualified;
	}

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
	public void setTransformationPackage(String transformationPackage) {
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
	public void setCreateXtendFile(boolean createXtendFile) {
		this.createXtendFile = createXtendFile;
	}
}
