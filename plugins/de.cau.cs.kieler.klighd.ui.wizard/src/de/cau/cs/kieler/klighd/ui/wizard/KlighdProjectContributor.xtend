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
        contributePluginExtensions(fileWriter)
        contributeJDTprefs(fileWriter);

        if (projectInfo.createXtendFile) {
            contributeXtendTransformationFile(fileWriter)
        } else {
            contributeJavaTransformationFile(fileWriter)
        }
        
        if (projectInfo.createMenuContribution) {
            contributeCommandHandler(fileWriter);
            if (projectInfo.useFileEnding) {            
                contributeHandlerHelperClass(fileWriter);
            }
        }
    }
    
    /**
     * The generation of the JDT project preferences prevents any issues due to Eclipse'
     *  incompatibility with Java 1.7.<br>
     * The content has simply been copied from an instance of plug-in project with "project specific
     *  settings" using "compliance from execution environment 'J2SE-1.5' on the 'Java Build Path'".
     */
    def private contributeJDTprefs(IFileCreator fileWriter) {
        
        val version = switch (projectInfo.executionEnvironment) {
            case "JavaSE-1.6": "1.6"
            case "JavaSE-1.7": "1.7"
            case "JavaSE-1.8": "1.8"
            default: "1.5"
        }
        
        '''
            eclipse.preferences.version=1
            org.eclipse.jdt.core.compiler.codegen.inlineJsrBytecode=enabled
            org.eclipse.jdt.core.compiler.codegen.targetPlatform=«version»
            org.eclipse.jdt.core.compiler.codegen.unusedLocal=preserve
            org.eclipse.jdt.core.compiler.compliance=«version»
            org.eclipse.jdt.core.compiler.debug.lineNumber=generate
            org.eclipse.jdt.core.compiler.debug.localVariable=generate
            org.eclipse.jdt.core.compiler.debug.sourceFile=generate
            org.eclipse.jdt.core.compiler.problem.assertIdentifier=error
            org.eclipse.jdt.core.compiler.problem.enumIdentifier=error
            org.eclipse.jdt.core.compiler.source=«version»
        '''.writeToFile(fileWriter,
            KlighdWizardSetup.SETTINGS_FOLDER + '/' + KlighdWizardSetup.JDT_PREFS_FILE);
    }

    def private contributeXtendTransformationFile(IFileCreator fileWriter) {

        '''
            package «projectInfo.transformationPackage»
            
            import javax.inject.Inject
            
            import de.cau.cs.kieler.core.kgraph.KNode
            import de.cau.cs.kieler.core.krendering.KRenderingFactory
            import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KPortExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KLabelExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
            import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
            import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
            
            import static de.cau.cs.kieler.klighd.syntheses.DiagramLayoutOptions.*
            
            import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
            
            import «projectInfo.sourceModelClassFullyQualified»
            
            class «projectInfo.transformationName» extends AbstractDiagramSynthesis<«projectInfo.sourceModelClassSimple»> {
                
                @Inject extension KNodeExtensions
                @Inject extension KEdgeExtensions
                @Inject extension KPortExtensions
                @Inject extension KLabelExtensions
                @Inject extension KRenderingExtensions
                @Inject extension KContainerRenderingExtensions
                @Inject extension KPolylineExtensions
                @Inject extension KColorExtensions
                extension KRenderingFactory = KRenderingFactory.eINSTANCE
                
                
                override KNode transform(«projectInfo.sourceModelClassSimple» model) {
                    val root = model.createNode().associateWith(model);
                    
                    // Your dsl element <-> diagram figure mapping goes here!!
                    
                    // Notice the statically imported classes 'DiagramSyntheses' and 'DiagramLayoutOptions'
                    //  that contribute direct access to a couple of (layout) configurations
                    
                    return root;
                }
                
            }
        '''.writeToFile(fileWriter, getTransformationPath() + ".xtend")

    }

    def private contributeJavaTransformationFile(IFileCreator fileWriter) {

        '''
            package «projectInfo.transformationPackage»;
            
            import static de.cau.cs.kieler.klighd.syntheses.DiagramLayoutOptions.*;
            import static de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*;
            
            import de.cau.cs.kieler.core.kgraph.KNode;
            import de.cau.cs.kieler.kiml.util.KimlUtil;
            import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis;
            import «projectInfo.sourceModelClassFullyQualified»;
            
            public class «projectInfo.transformationName» extends AbstractDiagramSynthesis<«projectInfo.sourceModelClassSimple»> {
            
                public KNode transform(final «projectInfo.sourceModelClassSimple» model) {
                    final KNode root = KimlUtil.createInitializedNode();
                    associateWith(root, model);
                    
                    // Your dsl element <-> diagram figure mapping goes here!!
                    
                    // Notice the statically imported classes 'DiagramSyntheses' and 'DiagramLayoutOptions'
                    //  that contribute direct access to a couple of (layout) configurations
                    
                    return root;
                }
            }
        '''.writeToFile(fileWriter, getTransformationPath() + ".java")

    }

    def private getTransformationPath() {
        KlighdWizardSetup.SRC_FOLDER + projectInfo.transformationPackage.replace(".", "/") + "/" +
            projectInfo.transformationName
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
        
        var fileEndingCondition = ""
        var xtextEditorCondition = ""
        
        if (projectInfo.useFileEnding) {
            fileEndingCondition =
            '''
                <adapt type="org.eclipse.core.resources.IResource">
                    <test
                          forcePluginActivation="false"
                          property="org.eclipse.core.resources.extension"
                          value="«projectInfo.fileEnding»">
                    </test>
                </adapt>
            '''
            
            xtextEditorCondition =
            '''
                <and>
                   <with
                         variable="activeEditor">
                      <instanceof
                            value="org.eclipse.xtext.ui.editor.XtextEditor">
                      </instanceof>
                   </with>
                   <with
                         variable="activeEditorInput">
                      «fileEndingCondition»
                   </with>
                </and>
           '''
        }
             
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
                     locationURI="popup:org.eclipse.ui.popup.any?before=additions">
                  <command
                        commandId="«projectInfo.projectName».open«projectInfo.sourceModelClassSimple»Diagram"
                        label="Open «projectInfo.sourceModelClassSimple» diagram"
                        style="push">
                     <visibleWhen
                           checkEnabled="false">
                        <or>
                           «xtextEditorCondition»
                           <iterate ifEmpty="false" operator="or">
                              <or>
                                 <instanceof
                                     value="«projectInfo.sourceModelClassFullyQualified»">
                                 </instanceof>
                                 «fileEndingCondition»
                              </or>
                           </iterate>
                        </or>
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
        val xtextEditorSupport = if (projectInfo.useFileEnding) '''
            if (selection instanceof ITextSelection) {
                IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
                
                // because of the visibility expressions in the plugin.xml guarding the menu contributions
                //  we can conclude to have a selection stemming from an XtextEditor; thus...
                IDiagramWorkbenchPart diagramPart = DiagramViewManager.createView(
                        "«projectInfo.transformationPackage».«projectInfo.sourceModelClassSimple»Diagram", "«projectInfo.sourceModelClassSimple» Diagram",
                        XtextEditorUtil.getXtextModelAccessProxy(activeEditor));
            
                
                if (diagramPart != null) {
                    XtextEditorUtil.registerSelectionListener(activeEditor, diagramPart);
                }

            } else ''' else "";
        
        val importITextSelection = if (projectInfo.useFileEnding) '''
            import org.eclipse.jface.text.ITextSelection;
        ''';
        
        '''
        package «projectInfo.transformationPackage»;
        
        import org.eclipse.core.commands.AbstractHandler;
        import org.eclipse.core.commands.ExecutionEvent;
        import org.eclipse.core.commands.ExecutionException;
        import org.eclipse.core.resources.IFile;
        import org.eclipse.core.runtime.IStatus;
        import org.eclipse.core.runtime.Status;
        import org.eclipse.emf.common.util.URI;
        import org.eclipse.emf.ecore.resource.Resource;
        import org.eclipse.emf.ecore.resource.ResourceSet;
        import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
        import org.eclipse.emf.ecore.util.EcoreUtil;
        import org.eclipse.jface.dialogs.MessageDialog;
        «importITextSelection»import org.eclipse.jface.viewers.ISelection;
        import org.eclipse.jface.viewers.IStructuredSelection;
        import org.eclipse.ui.IEditorPart;
        import org.eclipse.ui.handlers.HandlerUtil;
        import org.eclipse.ui.statushandlers.StatusManager;
        
        import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
        import de.cau.cs.kieler.klighd.ui.DiagramViewManager;
        
        /**
         * A simple handler for opening diagrams.
         */
        public class OpenDiagramHandler extends AbstractHandler {
        
            /**
             * {@inheritDoc}
             */
            public Object execute(final ExecutionEvent event) throws ExecutionException {
                final ISelection selection = HandlerUtil.getCurrentSelection(event);

                «xtextEditorSupport»if (selection instanceof IStructuredSelection) {
                    final IStructuredSelection sSelection  = (IStructuredSelection) selection;

                    final Object firstElement = sSelection.getFirstElement();
                    final Object model;

                    if (firstElement instanceof IFile) {
                        try {
                            final ResourceSet rs = new ResourceSetImpl();
                            final Resource r = rs.getResource(URI.createPlatformResourceURI(
                                    ((IFile) firstElement).getFullPath().toString(), true), true);
                            EcoreUtil.resolveAll(r);

                            if (r.getContents().size() > 0) {
                                model = r.getContents().get(0);
                                r.unload();
                            } else {
                                r.unload();
                                return null;
                            }

                        } catch (Exception e) {
                            final String message = "Could not load selected file.";
                            StatusManager.getManager().handle(
                                    new Status(IStatus.ERROR, this.getClass().getCanonicalName(), message, e),
                                    StatusManager.SHOW);
                            return null;
                        }
                    } else {
                        model = firstElement;
                    }

                    DiagramViewManager.createView(
                            "«projectInfo.transformationPackage».«projectInfo.sourceModelClassSimple»Diagram", "«projectInfo.sourceModelClassSimple» Diagram", model);
                } else {
                    MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "Unsupported element",
                            "KLighD diagram synthesis is unsupported for the current selection "
                                    + selection.toString() + ".");
                }
                return null;
            }
        }
        '''.writeToFile(fileWriter, KlighdWizardSetup.SRC_FOLDER + projectInfo.transformationPackage.replace(".", "/") + "/OpenDiagramHandler.java");
        
    }
    
    def private contributeHandlerHelperClass(IFileCreator fileWriter) {
        '''
        package «projectInfo.transformationPackage»;
        
        import java.util.Map;
        
        import org.eclipse.emf.ecore.EObject;
        import org.eclipse.jface.viewers.ISelection;
        import org.eclipse.ui.IEditorPart;
        import org.eclipse.ui.IPartListener;
        import org.eclipse.ui.ISelectionListener;
        import org.eclipse.ui.IWorkbenchPage;
        import org.eclipse.ui.IWorkbenchPart;
        import org.eclipse.xtext.nodemodel.INode;
        import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
        import org.eclipse.xtext.resource.XtextResource;
        import org.eclipse.xtext.ui.editor.XtextEditor;
        import org.eclipse.xtext.util.concurrent.IUnitOfWork;
        
        import com.google.common.base.Function;
        import com.google.common.collect.Iterators;
        import com.google.common.collect.Maps;
        
        import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
        import de.cau.cs.kieler.klighd.IKlighdSelection;
        import de.cau.cs.kieler.klighd.ISourceProxy;
        import de.cau.cs.kieler.klighd.ui.parts.DiagramViewPart;
        
        public class XtextEditorUtil {
        
            /**
             * Factory method providing an implementation of {@link ISourceProxy} enabling the execution of
             * KLighD operations properly within Xtext's {@link IUnitOfWork IUnitOfWorks}.
             *
             * @param editorPart
             *            the {@link XtextEditor} whose model shall be accessed
             * @return the desired {@link ISourceProxy} implementation
             */
            public static ISourceProxy getXtextModelAccessProxy(final IEditorPart editorPart) {
                if (!(editorPart instanceof XtextEditor)) {
                    // if a non-Xtext editor is given don't to anything;
                    return null;
                }
        
                final XtextEditor xtextEditor = (XtextEditor) editorPart;
        
                // because Xtext editors protect the model (resource) by a transaction mechanism
                //  an instance of ISourceProxy is returned instead of returning the model directly
                return new ISourceProxy() {
        
                    // The implementation of 'execute(...)' implements Xtext's transaction mechanism;
                    // KLighD will invoke this method and provide a function wrapping the KLighD internal
                    //  functionality; the function's result must be returned by 'execute(...)' method
        
                    public <T> T execute(final Function<Object, T> function) {
                        if (xtextEditor.getDocument() == null) {
                            // stop if for some reason there's no document (shouldn't happen)
                            return null;
                        }
        
                        // perform a read operation on the model and execute KLighD's tasks
                        return xtextEditor.getDocument().readOnly(new IUnitOfWork<T, XtextResource>() {
        
                            public T exec(final XtextResource res) throws Exception {
                                // return just 'null' if there's no model within the resource
                                //  otherwise apply the function provided by KLighD
                                return res.getContents().isEmpty() ? null :
                                    function.apply(res.getContents().get(0));
                            }
                        });
                    }
                };
            }
        
            private static final Map<XtextEditor, ISelectionListener> selectionListeners = Maps.newHashMap();
        
        
            /**
             * Registers an {@link ISelectionListener} dedicated to {@code diagramPart}
             * in the Eclipse selection service. This selection listener listens for selections
             * in {@code diagramPart} and is in charge of highlighting the corresponding
             * declarations in {@code editorPart}, which is supposed to be an {@link XtextEditor}.<br>
             * <br>
             * The registered listeners are tracked and de-registered if {@code editorPart} is closed.
             *
             * @param editorPart
             *            is supposed to be a valid {@link XtextEditor}
             * @param diagramPart
             *            the diagram view part to be listened for selection changes
             */
            public static void registerSelectionListener(IEditorPart editorPart, IDiagramWorkbenchPart diagramPart) {
                if (!(editorPart instanceof XtextEditor)) {
                    return;
                }
        
                final XtextEditor xtextEditor = (XtextEditor) editorPart;
        
                // the selection listeners are installed corresponding to the 'xtextEditors'
                //  that provide the source (domain) models
                // if there is already a listener tracked with the given editor, we're done
                if (selectionListeners.containsKey(xtextEditor)) {
                    return;
                }
        
                // otherwise instantiate a new listener, ...
                final ISelectionListener selectionListener = new ISelectionListener() {
        
                    @Override
                    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
                        // first check for the correct selection type (just for safety purposes)
                        if (!(selection instanceof IKlighdSelection)) {
                            return;
                        }
        
                        final IKlighdSelection klighdSelection = (IKlighdSelection) selection;
        
                        // obtain the first selected diagram element and ask KLighD
                        //  (i.e. the diagram's ViewContext) for the corresponding source element
                        final Object selectedDomainElement = klighdSelection.getViewContext().getSourceElement(
                                Iterators.getNext(klighdSelection.diagramElementsIterator(), null));
        
                        // try to highlight the corresponding definition if 'selectedDomainElement' is
                        //  a valid model element, i.e. non null, for example
                        if (selectedDomainElement instanceof EObject) {
                            highlightSelection(xtextEditor, (EObject) selectedDomainElement);
                        }
                    }
                };
        
                // ... keep it in mind, ...
                selectionListeners.put(xtextEditor, selectionListener);
        
                // ... compose the partId from primary and secondary id as required by the selection service
                //  (the KLighD DiagramViewPart is a multi view; black magic ... *hoo*), ...
                final String partId = DiagramViewPart.VIEW_ID + ":" + diagramPart.getPartId();
        
                // ... and register the listener in the platform.
                final IWorkbenchPage diagramPartPage = diagramPart.getSite().getPage();
                diagramPartPage.addSelectionListener(partId, selectionListener);
        
                // For properly de-installing the selection listener we need to keep track of the editor. If
                //  gets closed the selection listener gets obsolete as there's nothing to highlight any more.
                // Thus, we register an IPartListener that listens for the closure of the editor.
                // It reveals the selection listener to be removed from the memory map, removes it from the
                //  platform (selection service), and finally de-installes itself.
        
                final IWorkbenchPage editorPartPage = xtextEditor.getSite().getPage();
                editorPartPage.addPartListener(new IPartListener() {
        
                    @Override
                    public void partClosed(IWorkbenchPart part) {
                        if (part == xtextEditor) {
                            final ISelectionListener l = selectionListeners.remove(xtextEditor);
                            diagramPartPage.removeSelectionListener(partId, l);
                            editorPartPage.removePartListener(this);
                        }
                    }
        
                    @Override
                    public void partOpened(IWorkbenchPart part) {
                    }
        
                    @Override
                    public void partDeactivated(IWorkbenchPart part) {
                    }
        
                    @Override
                    public void partBroughtToTop(IWorkbenchPart part) {
                    }
        
                    @Override
                    public void partActivated(IWorkbenchPart part) {
                    }
                });
            }
        
        
            private static void highlightSelection(XtextEditor xtextEditor, EObject element) {
        
                // ask 'xtextEditor' for the position of 'element's definition
                final Integer[] elementData = xtextEditor.getDocument().readOnly(
                        new IUnitOfWork<Integer[], XtextResource>() {
        
                            public Integer[] exec(final XtextResource state) throws Exception {
        
                                final INode node = NodeModelUtils.findActualNodeFor(element);
                                return node == null ? null
                                        : new Integer[] { node.getOffset(), node.getLength() };
                            }
                        });
        
                if (elementData == null) {
                    return;
                }
        
                // set the selection to that area
                xtextEditor.getInternalSourceViewer().setSelectedRange(elementData[0], elementData[1]);
                xtextEditor.getInternalSourceViewer().revealRange(elementData[0], elementData[1]);
        
                // bring the editor to foreground
                xtextEditor.getSite().getPage().bringToTop(xtextEditor);
            }
        }
        '''.writeToFile(fileWriter, KlighdWizardSetup.SRC_FOLDER + projectInfo.transformationPackage.replace(".", "/") + "/XtextEditorUtil.java");
    }

    def protected IFile writeToFile(CharSequence chrSeq, IFileCreator fCreator, String fileName) {
        return fCreator.writeToFile(chrSeq, fileName);
    }
}
