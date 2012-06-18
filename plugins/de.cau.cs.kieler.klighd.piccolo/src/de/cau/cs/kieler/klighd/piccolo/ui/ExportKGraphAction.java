/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.ui;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.ProgressMonitorAdapter;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.service.LayoutOptionManager;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.krendering.viewer.PiccoloViewer;

/**
 * An action which invokes the 'export-kgraph' dialog and performs the save for Piccolo.
 * Copied+modified from {@link de.cau.cs.kieler.klighd.piccolo.ui.SaveAsImageAction}.
 * 
 * @author chsch
 */
public class ExportKGraphAction extends Action {

    /** the associated Piccolo viewer. */
    private PiccoloViewer viewer;

    /**
     * Constructs the 'save-as-image' action.
     * 
     * @param viewer
     *            the associated Piccolo viewer
     * @param text
     *            the user-friendly text for the action
     */
    public ExportKGraphAction(final PiccoloViewer viewer, final String text) {
        super(text);
        this.viewer = viewer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        // open the dialog to receive the required user input
        ExportKGraphDialog dialog = new ExportKGraphDialog(viewer.getCanvas().getShell());
        int code = dialog.open();
        if (code == Dialog.OK) {
            try {
                URI fileURI = URI
                        .createPlatformResourceURI(dialog.getFilePath().toOSString(), true);

                ResourceSet set = new ResourceSetImpl();
                Resource resource = set.createResource(fileURI);

                IDiagramLayoutManager<?> layoutManager =
                        EclipseLayoutInfoService.getInstance().getManager(null, viewer);
                LayoutMapping<?> layoutMapping = layoutManager.buildLayoutGraph(null, viewer);
                new LayoutOptionManager().configure(layoutMapping, new ProgressMonitorAdapter(
                        new NullProgressMonitor()));

                KNode kgraph = layoutMapping.getLayoutGraph();
                KimlUtil.persistDataElements(kgraph);
                resource.getContents().add(kgraph);

                resource.save(Collections.emptyMap());
                resource.unload();

            } catch (IOException exception) {
                Status myStatus = new Status(IStatus.WARNING, KlighdPiccoloPlugin.PLUGIN_ID,
                        Messages.ExportKGraphAction_export_kgraph_error, exception);
                StatusManager.getManager().handle(myStatus,
                        StatusManager.BLOCK | StatusManager.SHOW);
            }
        }
    }
}
