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
 * @author chsch
 */
class KlighdProjectContributor implements IProjectFactoryContributor {

    var KlighdProjectInfo projectInfo

    new(KlighdProjectInfo projectInfo) {
        this.projectInfo = projectInfo
    }

    override def contributeFiles(IProject project, IFileCreator fileWriter) {
        contributeBuildProperties(fileWriter)
        contributePluginExtensions(fileWriter)
        contributeJDTprefs(fileWriter);

        if (projectInfo.createXtendFile) {
            contributeXtendTransformationFile(fileWriter)
        } else {
            contributeJavaTransformationFile(fileWriter)
        }
        
        if (projectInfo.createMenuContribution) {
            contributeCommandHandler(fileWriter);
        }
    }
    
    /**
     * The generation of the JDT project preferences prevents any issues due to Eclipse'
     *  incompatibility with Java 1.7.<br>
     * The content has simply been copied from an instance of plug-in project with "project specific
     *  settings" using "compliance from execution environment 'J2SE-1.5' on the 'Java Build Path'".
     */
    def private contributeJDTprefs(IFileCreator fileWriter) {
        '''
        eclipse.preferences.version=1
        org.eclipse.jdt.core.compiler.codegen.inlineJsrBytecode=enabled
        org.eclipse.jdt.core.compiler.codegen.targetPlatform=1.5
        org.eclipse.jdt.core.compiler.codegen.unusedLocal=preserve
        org.eclipse.jdt.core.compiler.compliance=1.5
        org.eclipse.jdt.core.compiler.debug.lineNumber=generate
        org.eclipse.jdt.core.compiler.debug.localVariable=generate
        org.eclipse.jdt.core.compiler.debug.sourceFile=generate
        org.eclipse.jdt.core.compiler.problem.assertIdentifier=error
        org.eclipse.jdt.core.compiler.problem.enumIdentifier=error
        org.eclipse.jdt.core.compiler.source=1.5
        '''.writeToFile(fileWriter, KlighdWizardSetup.SETTINGS_FOLDER + '/'
                + KlighdWizardSetup.JDT_PREFS_FILE);
    }

    def private contributeXtendTransformationFile(IFileCreator fileWriter) {

        '''
            package «projectInfo.transformationPackage»
            
            import javax.inject.Inject
            
            import de.cau.cs.kieler.core.kgraph.KNode
            import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KPortExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KLabelExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions            
            import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
            
            import «projectInfo.sourceModelClassFullyQualified»
            
            class «projectInfo.transformationName» extends AbstractDiagramSynthesis<«projectInfo.sourceModelClassSimple»> {
                
                @Inject
                extension KNodeExtensions
                
                @Inject
                extension KEdgeExtensions
                
                @Inject
                extension KPortExtensions
                
                @Inject
                extension KLabelExtensions
                
                @Inject
                extension KRenderingExtensions
                
                @Inject
                extension KContainerRenderingExtensions
                
                @Inject
                extension KPolylineExtensions
                
                @Inject
                extension KColorExtensions
                
                override KNode transform(«projectInfo.sourceModelClassSimple» model) {
                    val root = model.createNode().putToLookUpWith(model);
                    
                    // Your dsl element <-> diagram figure mapping goes here!!
                    
                    return root;
                }
                
            }
        '''.writeToFile(fileWriter, getTransformationPath() + ".xtend")

    }

    def private contributeJavaTransformationFile(IFileCreator fileWriter) {

        '''
            package «projectInfo.transformationPackage»;
            
            import de.cau.cs.kieler.core.kgraph.KNode;
            import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis;
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
        
        val beginning =
        '''
        <?xml version="1.0" encoding="UTF-8"?>
        <?eclipse version="3.4"?>
        <plugin>
           <extension
                 point="de.cau.cs.kieler.klighd.diagramSyntheses">
           <diagramSynthesis
                 class="de.cau.cs.kieler.klighd.syntheses.GuiceBasedSynthesisFactory:«projectInfo.
            transformationPackage + "." + projectInfo.transformationName»"
                 id="«projectInfo.transformationPackage + "." + projectInfo.transformationName»">
           </diagramSynthesis>
           </extension>
        '''              
        val menuContribution =
        '''
            <extension
                  point="org.eclipse.ui.commands">
               <category
                     description="«projectInfo.sourceModelClassSimple» diagrams"
                     id="«projectInfo.projectName».«projectInfo.sourceModelClassSimple»Diagrams"
                     name="«projectInfo.sourceModelClassSimple»Diagrams">
               </category>
               <command
                     categoryId="«projectInfo.projectName».«projectInfo.sourceModelClassSimple»Diagrams"
                     defaultHandler="«projectInfo.transformationPackage + ".OpenDiagramHandler"»"
                     description="Primitive helper command that opens KLighD-based «projectInfo.sourceModelClassSimple» diagrams."
                     id="«projectInfo.projectName».open«projectInfo.sourceModelClassSimple»Diagram"
                     name="Open «projectInfo.sourceModelClassSimple» diagram">
               </command>
            </extension>
            
            <extension
                  point="org.eclipse.ui.menus">
               <menuContribution
                     locationURI="popup:org.eclipse.ui.popup.any?after=additions">
                  <command
                        commandId="«projectInfo.projectName».open«projectInfo.sourceModelClassSimple»Diagram"
                        label="Open «projectInfo.sourceModelClassSimple» diagram"
                        icon="IMG_KLIGHD"
                        style="push">
                     <visibleWhen
                           checkEnabled="false">
                        <iterate>
                           <instanceof
                                 value="«projectInfo.sourceModelClassFullyQualified»">
                           </instanceof>
                        </iterate>
                     </visibleWhen>
                  </command>
               </menuContribution>
            </extension>
        '''

        val end =               
        '''
        </plugin>
        ''';
        
        (beginning
           + (if (projectInfo.createMenuContribution) menuContribution)
           + end).writeToFile(fileWriter, "plugin.xml")
    }
    
    def private contributeCommandHandler(IFileCreator fileWriter) {
        '''
        package «projectInfo.transformationPackage»;
        
        import org.eclipse.core.commands.AbstractHandler;
        import org.eclipse.core.commands.ExecutionEvent;
        import org.eclipse.core.commands.ExecutionException;
        import org.eclipse.jface.dialogs.MessageDialog;
        import org.eclipse.jface.viewers.ISelection;
        import org.eclipse.jface.viewers.IStructuredSelection;
        import org.eclipse.ui.handlers.HandlerUtil;
        
        import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
        import de.cau.cs.kieler.klighd.views.DiagramViewManager;
        
        /**
         * A simple handler for opening diagrams.
         */
        public class OpenDiagramHandler extends AbstractHandler {
        
            /**
             * {@inheritDoc}
             */
            public Object execute(final ExecutionEvent event) throws ExecutionException {
                final ISelection selection = HandlerUtil.getCurrentSelection(event);
                
                if (selection instanceof IStructuredSelection) {
                    final IStructuredSelection sSelection  = (IStructuredSelection) selection;
                    
                    DiagramViewManager.getInstance().createView(
                            "«projectInfo.transformationPackage».«projectInfo.sourceModelClassSimple»Diagram", "«projectInfo.sourceModelClassSimple» Diagram", sSelection.getFirstElement(), KlighdSynthesisProperties.newInstance());
                } else {
                    MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "Unsupported element",
                            "KLighD diagram synthesis is unsupported for the current selection "
                                    + selection.toString() + ".");
                }
                return null;
            }
        }
        '''.writeToFile(fileWriter,  KlighdWizardSetup.SRC_FOLDER + projectInfo.transformationPackage.replace(".", "/") + "/OpenDiagramHandler.java");
        
    } 

    def protected IFile writeToFile(CharSequence chrSeq, IFileCreator fCreator, String fileName) {
        return fCreator.writeToFile(chrSeq, fileName);
    }
}
