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
package de.cau.cs.kieler.klighd.ui.wizard

import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.xtext.ui.util.IProjectFactoryContributor
import org.eclipse.xtext.ui.util.IProjectFactoryContributor.IFileCreator

/**
 * Class contains all contributions for a new KlighD project.
 * 
 * @author uru
 */
class KlighdProjectContributor implements IProjectFactoryContributor {

	var KlighdProjectInfo projectInfo

	new(KlighdProjectInfo projectInfo) {
		this.projectInfo = projectInfo
	}

	override def contributeFiles(IProject project, IFileCreator fileWriter) {
		contributeBuildProperties(fileWriter)
		contributePluginExtensions(fileWriter)

		if (projectInfo.createXtendFile) {
			contributeXtendTransformationFile(fileWriter)
		} else {
			contributeJavaTransformationFile(fileWriter)
		}
	}

	def private contributeXtendTransformationFile(IFileCreator fileWriter) {

		'''
			package «projectInfo.transformationPackage»
			
			import de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis
			import «projectInfo.sourceModelClassFullyQualified»
			
			class «projectInfo.transformationName» extends AbstractDiagramSynthesis<«projectInfo.sourceModelClassSimple»> {
				
				override transform(«projectInfo.sourceModelClassSimple» model) {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
			}
		'''.writeToFile(fileWriter, getTransformationPath() + ".xtend")

	}

	def private contributeJavaTransformationFile(IFileCreator fileWriter) {

		'''
			package «projectInfo.transformationPackage»;
			
			import de.cau.cs.kieler.core.kgraph.KNode;
			import de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis;
			import «projectInfo.sourceModelClassFullyQualified»;
			
			public class «projectInfo.transformationName» extends AbstractDiagramSynthesis<«projectInfo.sourceModelClassSimple»> {
			
			    public KNode transform(final «projectInfo.sourceModelClassSimple» model) {
			        throw new UnsupportedOperationException("TODO: auto-generated method stub");
			    }
			}
		'''.writeToFile(fileWriter, getTransformationPath() + ".java")

	}

	def private getTransformationPath() {
		KlighdWizardSetup.SRC_FOLDER + projectInfo.transformationPackage.replace(".", "/") + "/" +
			projectInfo.transformationName
	}

	def private contributeBuildProperties(IFileCreator fileWriter) {
		'''
			source.. = «IF projectInfo.createXtendFile»xtend-gen/,\«ENDIF»
			          src/
			bin.includes = META-INF/,\
					plugin.xml,\
					     .
		'''.writeToFile(fileWriter, "build.properties")
	}

	def private contributePluginExtensions(IFileCreator fileWriter) {
		'''
			<?xml version="1.0" encoding="UTF-8"?>
			<?eclipse version="3.4"?>
			<plugin>
			   <extension
			         point="de.cau.cs.kieler.klighd.diagramSyntheses">
			   <diagramSynthesis
			         class="de.cau.cs.kieler.klighd.transformations.GuiceBasedTransformationFactory:«projectInfo.
				transformationPackage + "." + projectInfo.transformationName»"
			         id="«projectInfo.transformationPackage + "." + projectInfo.transformationName»">
			   </diagramSynthesis>
			   </extension>
			
			</plugin> 
		'''.writeToFile(fileWriter, "plugin.xml")
	}

	def protected IFile writeToFile(CharSequence chrSeq, IFileCreator fCreator, String fileName) {
		return fCreator.writeToFile(chrSeq, fileName);
	}
}
