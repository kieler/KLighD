/******************************************************************************
 * Copyright (c) 2005, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package de.cau.cs.kieler.klighd.ui.printing.actions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.ui.printing.util.JPSDiagramPrinter;
import de.cau.cs.kieler.klighd.ui.printing.util.JPSDiagramPrinterHelper;

/**
 * Enhanced printing. The doPrint() method will invoke a dialog prompting the user to choose options
 * for printing. The user will be able to choose from printing diagrams of the current type. If
 * possible, the IFile path of the appicable diagrams will be displayed to the user, when prompting
 * the user to select a diagram for printing. If the diagram does not correspond to an IFile, its
 * part name will be used as the next choice.
 * 
 * This class implements the IPrintActionHelper interface that can be passed into Print Preview,
 * enabling the print action from there.
 * 
 * @author Wayne Diu, wdiu
 */
public class PrintActionHelper {

    /**
     * Show the print dialog and print
     * 
     * @param IWorkbenchPart
     *            the workbenchPart containing the diagram to print
     */

    public void doPrint(IWorkbenchPart workbenchPart) {
        IDiagramWorkbenchPart diagramPart = null;

        if (workbenchPart instanceof IDiagramWorkbenchPart) {
            diagramPart = (IDiagramWorkbenchPart) workbenchPart;
        } else {
            throw new IllegalArgumentException("Invalid IWorkbenchPart.");
        }

//        IDiagramGraphicalViewer viewer = diagramPart.getDiagramGraphicalViewer(); 
//        RootEditPart rootEP = (viewer == null)?  null : viewer.getRootEditPart();
//        
//        //splitting the instanceof checks for readability, DiagramRootEditPart implements IDiagramPreferenceSupport 
//        
//        //try to get actual preferences, if not then use default of PreferencesHint.USE_DEFAULTS
//        PreferencesHint preferencesHint = (rootEP instanceof IDiagramPreferenceSupport) ? ((IDiagramPreferenceSupport) rootEP)
//            .getPreferencesHint()
//            : PreferencesHint.USE_DEFAULTS;
//        
//        //get actual map mode, default is MapModeUtil.getMapMode()
//        IMapMode mapMode = (rootEP instanceof DiagramRootEditPart) ? ((DiagramRootEditPart) rootEP)
//                .getMapMode()
//                : MapModeUtil.getMapMode();             
//                            
//        if (Platform.getOS().startsWith(Platform.OS_WIN32) 
//                && Platform.getOSArch().equals(Platform.ARCH_X86)) {
//            DiagramPrinterUtil.printWithSettings(diagramPart,
//                    createDiagramMap(), new RenderedDiagramPrinter(
//                            preferencesHint, mapMode));
//        } else {
        JPSDiagramPrinterHelper.getDiagramPrinterHelper().printWithSettings(diagramPart,
                createDiagramMap(), new JPSDiagramPrinter());
//        }
    }

    /**
     * Return a Map with diagram name String as key and Diagram as value All entries in the map
     * correspond to open editors.
     * 
     * @return Map with diagram name String as key and Diagram as value All entries in the map
     *         correspond to open editors with the diagramEditor's id.
     */
    protected Map<String, IViewer> createDiagramMap() {

        Map<String, IViewer> diagramMap = new HashMap<String, IViewer>();

        // get all diagram editors with the matching id
//        for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
//            for (IWorkbenchPage page : window.getPages()) {
//                for (IEditorReference editor : page.getEditorReferences()) {
//                    editor.
//                }
//            }
//        }
        List<IWorkbenchPart> diagramEditors =
                Collections.singletonList(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                        .getActivePage().getActivePart());

        Iterator<IWorkbenchPart> it = diagramEditors.iterator();
        while (it.hasNext()) {
            Object obj = it.next();

            if (obj instanceof IDiagramWorkbenchPart) {
                IDiagramWorkbenchPart dEditor = (IDiagramWorkbenchPart) obj;

//                String diagramName = null;
//
//                IEditorInput editorInput = dEditor.get
//
//                // try to be more descriptive and get the IFile path which includes the project
//                IFile file = (IFile) (editorInput.getAdapter(IFile.class));
//                if (file != null) {
//                    diagramName = file.getFullPath().toOSString();
//                } else {
//                    // otherwise we can only get the editor title or part name
//                    diagramName = dEditor.getPartName();
//
//                    if (diagramName == null) {
//                        diagramName = dEditor.getTitle();
//                    }
//                }
//
//                if (diagramName == null) {
//                    // the last choice is to use the actual name of the diagram
//                    // this has to exist!
//                    diagramName = dEditor.getDiagram().getName();
//                }

                diagramMap.put(dEditor.getTitle(), dEditor.getViewer());

            }
        }
        return diagramMap;
    }
}
