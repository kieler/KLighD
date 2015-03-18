/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.handlers;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.impl.KGraphDataImpl;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.KlighdTreeSelection;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.ModelingUtil;

/**
 * Handler to store the view model KGraph (or a subgraph) of a KlighD view to a file. This class is
 * mainly intended to support debugging purposes.
 * 
 * @author uru
 */
@SuppressWarnings("restriction")
public class KlighdSaveKGraphHandler extends AbstractHandler {

    private static final String KGRAPH_FILE_EXTENSION = ".kgx";
    
    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final KlighdTreeSelection selection;

        // in case this handler is invoked via a context menu,
        // the activeMenuSelection (ISources#ACTIVE_MENU_SELECTION_NAME) is available
        ISelection s = HandlerUtil.getActiveMenuSelection(event);

        if (s instanceof KlighdTreeSelection) {
            // if it's a KLighD selection (it is supposed to be, we're fine :-)
            selection = (KlighdTreeSelection) s;

        } else if (s == null) {
            // if no activeMenuSelection is set, the handler may be called by the main menu,
            // toolbar, or a key binding; refer to the global selection in that case
            s = HandlerUtil.getCurrentSelectionChecked(event);

            if (s instanceof KlighdTreeSelection) {
                // again if it's a KLighD selection (it is supposed to be, we're fine :-)
                selection = (KlighdTreeSelection) s;

            } else {
                // something really strange must have happened
                return null;
            }
        } else {
            // something really strange must have happened
            return null;
        }

        KNode subgraph = null;
        if (selection.isEmpty()) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID,
                            "Please select something to export."), StatusManager.SHOW);
            return null;
        } else if (selection.size() == 1 && selection.getFirstElement() instanceof KNode) {
            // If exactly one element is selected and it is a knode
            // we suppose the user wanted to select that subgraph
            subgraph = (KNode) selection.getFirstElement();
        } 
        
        // Otherwise we export the whole view model graph
        final ViewContext viewContext = selection.getViewContext();
        if (viewContext == null) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID,
                            "Could not determine ViewContext."), StatusManager.SHOW);
        }

        final Shell shell = Display.getCurrent().getActiveShell();
        final SaveAsDialog fd = new SaveAsDialog(shell);

        try {
            // show a save as dialog
            final int success = fd.open();
            if (shell != null && success == SaveAsDialog.OK) {
                // retrieve selected
                String file = fd.getResult().toOSString().toString();
                if (fd.getResult().getFileExtension() == null) {
                    file += KGRAPH_FILE_EXTENSION;
                }
                ResourceSet rs = new ResourceSetImpl();
                Resource r = rs.createResource(URI.createPlatformResourceURI(file, true));

                // We do not want to tamper with the original model, thus create a copy.
                // Also, we copy the whole graph because we must be able to 
                // collect rendering libraries that might exist on higher levels
                Copier copier = new Copier();
                KNode copy = (KNode) copier.copy(viewContext.getViewModel());
                copier.copyReferences();
                
                KNode exportGraph = copy;
                if (subgraph != null) {

                    // the new root's position is at 0,0 now
                    KNode subgraphCopy = (KNode) copier.get(subgraph);
                    KShapeLayout sl = subgraphCopy.getData(KShapeLayout.class);
                    sl.setPos(0, 0);

                    // move all rendering libraries that we can find to the newly promoted root
                    Iterable<KRenderingLibrary> libs =
                            ModelingUtil.eAllContentsOfType(copy, KRenderingLibrary.class);

                    for (KRenderingLibrary lib : libs) {
                        // move the libs to the new root
                        subgraphCopy.getData().add(lib);
                    }
                    
                    // we wrap the subgraph into a new psuedo root node 
                    // to retain external ports 
                    KNode newRoot = KimlUtil.createInitializedNode();
                    newRoot.getChildren().add(subgraphCopy);
                    // give the root a proper dimension
                    KShapeLayout rsl = newRoot.getData(KShapeLayout.class);
                    rsl.setWidth(sl.getWidth());
                    rsl.setHeight(sl.getHeight());
                    
                    // expand the subgraph by default
                    KLayoutData ld = subgraphCopy.getData(KLayoutData.class);
                    ld.setProperty(KlighdInternalProperties.POPULATED, true);
                    ld.setProperty(KlighdProperties.EXPAND, true);
                    
                    exportGraph = newRoot;
                }
                
                // remove transient klighd state
                // care: do not iterate over the elements of the 'copy' as the subgraph
                // was already removed from its original containmet
                @SuppressWarnings("unchecked")
                Iterator<KGraphElement> kgeIt =
                        (Iterator<KGraphElement>) (Iterator<?>) ModelingUtil.selfAndEAllContentsOfType2(
                                copier.get(subgraph), KGraphElement.class);
                try {
                    while (kgeIt.hasNext()) {
                        KGraphElement kge = kgeIt.next();
                        Iterator<KGraphData> dataIt = kge.getData().iterator();
                        while (dataIt.hasNext()) {
                            KGraphData d = dataIt.next();
                            // RenderingContextData (explicit type information not available here)
                            if (d.getClass().equals(KGraphDataImpl.class)) {
                                // remember whether a node was expanded / collapsed
                                if (kge instanceof KNode && kge != exportGraph) {
                                        kge.getData(KLayoutData.class).setProperty(
                                                KlighdProperties.EXPAND,
                                                d.getProperty(KlighdInternalProperties.POPULATED));
                                }
                                dataIt.remove();
                            }
                        }
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                
                // persist layout options and friends
                KimlUtil.persistDataElements((KNode) copy);
                
                r.getContents().add(exportGraph);
                Map<String, Object> saveOpts = Maps.newHashMap();

                if (subgraph != null) {
                    // we have to drop several elements if only a subgraph 
                    // is exported ... just let the emf deal with it.
                    // FIXME use constants
                    saveOpts.put("PROCESS_DANGLING_HREF", "DISCARD");
                }
                r.save(saveOpts);

            }
        } catch (IOException e) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID,
                            "Could not write the KGraph to selected resource.", e),
                    StatusManager.SHOW);
        } catch (Exception e) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID, "Error occurred.", e),
                    StatusManager.SHOW);
        }

        return null;
    }
}
