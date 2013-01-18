/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.views;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.formats.GraphFormatData;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.ITransformationHandler;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.klighd.KlighdPlugin;

/**
 * An action for exporting graphs from a KLighD view.
 * 
 * @author mri
 * @author msp
 */
public class ExportAction extends Action {
    
    private DiagramViewPart viewPart;
    
    /**
     * Create an export action.
     * 
     * @param viewPart the view part
     */
    public ExportAction(final DiagramViewPart viewPart) {
        super("Export Graph...");
        this.viewPart = viewPart;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        Object activeModel = viewPart.getContextViewer().getActiveViewer().getModel();
        if (activeModel instanceof KNode) {
        
            // open the export dialog
            Shell shell = viewPart.getSite().getShell();
            ExportDialog exportDialog = new ExportDialog(shell);
            int code = exportDialog.open();
            if (code == Dialog.OK) {
                try {
                    KNode inputGraph = (KNode) activeModel;
                    
                    // get the selected configuration
                    GraphFormatData formatData = exportDialog.getFormat();
                    String filePath = exportDialog.getExportFile();
                    boolean isWorkspacePath = exportDialog.isExportWorkspacePath();
                    
                    // perform the export
                    String exportGraph = performExport(inputGraph, formatData.getHandler());
                    
                    // write the export file
                    Writer writer = createWriter(filePath, isWorkspacePath);
                    writer.write(exportGraph);
                    writer.close();
                    
                } catch (Throwable exception) {
                    Status status = new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                            "Exporting the current graph failed", exception);
                    StatusManager.getManager().handle(status,
                            StatusManager.LOG | StatusManager.SHOW);
                }
            }
        } else {
            Status status = new Status(IStatus.INFO, KlighdPlugin.PLUGIN_ID,
                    "The current view does not contain any graph.");
            StatusManager.getManager().handle(status, StatusManager.SHOW);
        }
    }
    
    /**
     * Perform a graph export using the given transformation handler.
     * 
     * @param graph a graph
     * @param transHandler the transformation handler
     * @return a serialized transformed graph
     */
    private <T> String performExport(final KNode graph, final ITransformationHandler<T> transHandler) {
        TransformationData<KNode, T> transData = new TransformationData<KNode, T>();
        transData.setSourceGraph(graph);
        IGraphTransformer<KNode, T> transformer = transHandler.getExporter();
        transformer.transform(transData);
        return transHandler.serialize(transData);
    }
    
    /**
     * Creates a writer to a file that is located in the workspace or in the file system.
     * 
     * @param path the file path
     * @param isWorkspacePath true if the file path is relative to the workspace
     * @return the new writer
     * @throws IOException thrown when the file could not be opened
     */
    private static Writer createWriter(final String path,
            final boolean isWorkspacePath) throws IOException {
        if (isWorkspacePath) {
            // workspace path
            IPath filePath = new Path(path);
            URI fileURI = URI.createPlatformResourceURI(filePath.toOSString(), true);
            URIConverter uriConverter = new ExtensibleURIConverterImpl();
            OutputStream outputStream = uriConverter.createOutputStream(fileURI);
            return new OutputStreamWriter(outputStream);
        } else {
            // file system path
            File file = new File(path);
            return new FileWriter(file);
        }
    }
    
}
