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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.wizard.IProjectInfo;

/**
 * @author uru
 * 
 */
public class KlighdProjectInfo implements IProjectInfo {

    private String projectName = "de.cau.default.project";

    private String transformationPackage = "de.cau.cs.kieler.klighd.test.trans";
    private String transformationName = "MyTransformation";

//    private String className = "org.eclipse.ecore.EObject";
    private Class<?> clazz = EObject.class;

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

    /**
     * @return the clazz
     */
    public Class<?> getClazz() {
        return clazz;
    }
    
    /**
     * @param clazz the clazz to set
     */
    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
//    /**
//     * @return the className
//     */
//    public String getClassName() {
//        return className;
//    }
//
//    /**
//     * @param className
//     *            the className to set
//     */
//    public void setClassName(String className) {
//        this.className = className;
//    }

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
