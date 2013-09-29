package de.cau.cs.kieler.klighd.ui.wizard

import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.xtext.ui.util.IProjectFactoryContributor
import org.eclipse.xtext.ui.util.IProjectFactoryContributor.IFileCreator

class KlighdProjectContributor implements IProjectFactoryContributor {

	val SRC_FOLDER = "src/"

	KlighdProjectInfo projectInfo

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
			import «projectInfo.clazz.canonicalName»
			
			class «projectInfo.transformationName» extends AbstractDiagramSynthesis<«projectInfo.clazz.simpleName»> {
				
				override transform(«projectInfo.clazz.simpleName» model) {
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
			import «projectInfo.clazz.canonicalName»;
			
			public class «projectInfo.transformationName» extends AbstractDiagramSynthesis<«projectInfo.clazz.simpleName»> {
			
			    public KNode transform(final «projectInfo.clazz.simpleName» model) {
			        throw new UnsupportedOperationException("TODO: auto-generated method stub");
			    }
			}
			'''.writeToFile(fileWriter, getTransformationPath() + ".java")

	}

	def private getTransformationPath() {
		SRC_FOLDER + projectInfo.transformationPackage.replace(".", "/") + "/" + projectInfo.transformationName
	}

	def private contributeBuildProperties(IFileCreator fileWriter) {
		'''
			source.. = src/,\
			          src-gen/,\
			          xtend-gen/
			bin.includes = META-INF/,\
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
			         class="de.cau.cs.kieler.klighd.transformations.GuiceBasedTransformationFactory:«projectInfo.transformationPackage + "." + projectInfo.transformationName»"
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
