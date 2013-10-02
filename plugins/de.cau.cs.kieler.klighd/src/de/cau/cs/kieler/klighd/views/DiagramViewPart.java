/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.klighd.views;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.ResourceTransfer;
import org.eclipse.ui.part.ViewPart;

import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.internal.preferences.KlighdPreferences;
import de.cau.cs.kieler.klighd.triggers.KlighdResourceDropTrigger;
import de.cau.cs.kieler.klighd.triggers.KlighdResourceDropTrigger.KlighdResourceDropState;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * A view which is able to display models in light-weight diagrams.
 * 
 * @author mri
 * @author chsch
 * @author msp
 */
public class DiagramViewPart extends ViewPart implements IDiagramWorkbenchPart {

    /** the default name for this view. */
    public static final String DEFAULT_NAME = "Light Diagram";
    /** the action identifier prefix for permanent menu contributions. */
    public static final String PERMANENT_ACTION_PREFIX = "klighd.action";
    
    /** the viewer for this view part. */
    private ContextViewer viewer;
    
    /**
     * Tracks whether this {@link ViewPart} is disposed. Since the {@link DiagramViewManager} tracks
     * created views it needs to test whether those views are still alive.
     */
    private boolean disposed = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {        
        // add buttons to the view toolbar 
        addButtons();
        
        // create the context viewer
        viewer = new ContextViewer(parent, getViewSite().getSecondaryId(), this);
        
        // put some default actions into the view menu
        fillViewMenu(getViewSite().getActionBars().getMenuManager());
        
        // install a drop handler for the view (XXX this could be omitted)
        installDropHandler(parent);
        viewer.setModel("No model selected.", false);
        
        // register the context viewer as selection provider on the workbench
        getSite().setSelectionProvider(viewer);
        
        // the initialization of the context menu is done in PiccoloViewer#addContextMenu()
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();
        viewer.dispose();
        disposed = true;
        DiagramViewManager.getInstance().unregisterViewContexts(this);
    }
    
    /**
     * Returns true if this {@link ViewPart} is disposed. Since the {@link DiagramViewManager}
     * tracks created views it needs to test whether those views are still alive.
     * 
     * @return whether {@link #dispose()} has been called on this {@link ViewPart}
     */
    public boolean isDisposed() {
        return disposed;
    }
    
    /**
     * Fill the view menu with some contributions.
     * 
     * @param menuManager the menu manager
     */
    private void fillViewMenu(final IMenuManager menuManager) {
        Action exportAction = new ExportAction(this);
        exportAction.setId(PERMANENT_ACTION_PREFIX + ".export");
        menuManager.add(exportAction);
    }

    /**
     * Add the buttons to the tool bar.
     */
    private void addButtons() {
        final IToolBarManager toolBar = this.getViewSite().getActionBars().getToolBarManager();
        toolBar.add(new Action("Refresh diagram", KlighdPlugin
                .getImageDescriptor("icons/full/elcl16/refresh.gif")) {
            public void run() {
                DiagramViewManager.getInstance().updateView(viewer.getViewPartId());
            }
        });

        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();

        // toggle zoom to fit behavior
        toolBar.add(new Action("Toggle Zoom to Fit", IAction.AS_CHECK_BOX) {
            {
                setImageDescriptor(KimlUiPlugin
                        .getImageDescriptor("icons/menu16/kieler-zoomtofit.gif"));
                setChecked(preferenceStore.getBoolean(KlighdPreferences.ZOOM_TO_FIT));

                // register a listener to adapt to changes within the preference pages
                preferenceStore.addPropertyChangeListener(new IPropertyChangeListener() {

                    public void propertyChange(final PropertyChangeEvent event) {
                        if (event.getProperty().equals(KlighdPreferences.ZOOM_TO_FIT)) {
                            setChecked((Boolean) event.getNewValue());
                        }
                    }
                });
            }

            @Override
            public void run() {
                preferenceStore.setValue(KlighdPreferences.ZOOM_TO_FIT, this.isChecked());
            }
        });

        // automatic layout button
        toolBar.add(new Action("Arrange", IAction.AS_DROP_DOWN_MENU) {

            private Menu menu;
            {
                setImageDescriptor(KimlUiPlugin
                        .getImageDescriptor("icons/menu16/kieler-arrange.gif"));
                setMenuCreator(new IMenuCreator() {

                    public Menu getMenu(final Menu parent) {
                        // not used
                        return null;
                    }

                    public Menu getMenu(final Control parent) {
                        // create the menu
                        if (menu == null) {
                            menu = new Menu(parent);
                            ActionContributionItem item =
                                    new ActionContributionItem(new Action("Force Zoom to Fit") {
                                        public void run() {
                                            boolean animate =
                                                    preferenceStore
                                                            .getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
                                            // force zoom to fit
                                            LightDiagramServices.getInstance().layoutDiagram(
                                                    DiagramViewPart.this, animate, true);
                                        };
                                    });
                            item.fill(menu, -1);
                        }
                        return menu;
                    }

                    public void dispose() {
                        if (menu != null) {
                            menu.dispose();
                        }
                    }
                });
            }

            public void run() {
                LightDiagramServices.getInstance().layoutDiagram(DiagramViewPart.this);
            }
        });
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    /**
     * {@inheritDoc}
     */
    public ContextViewer getContextViewer() {
        return viewer;
    }

    /**
     * Sets a new name for the view.
     * 
     * @param name
     *            the name
     */
    public void setName(final String name) {
        setPartName(name);
    }

    /**
     * Installs a handler for dropping resources on the view.
     */
    private void installDropHandler(final Composite parent) {
        DropTarget target = new DropTarget(parent, DND.DROP_COPY | DND.DROP_DEFAULT);
        final ResourceTransfer resourceTransfer = ResourceTransfer.getInstance();
        target.setTransfer(new Transfer[] { resourceTransfer });
        target.addDropListener(new DropTargetListener() {

            public void drop(final DropTargetEvent event) {
                KlighdResourceDropTrigger trigger = KlighdResourceDropTrigger.getInstance();
                if (trigger != null) {
                    if (resourceTransfer.isSupportedType(event.currentDataType)
                            && event.data instanceof IResource[]) {
                        IResource[] resources = (IResource[]) event.data;
                        if (resources.length > 0) {
                            KlighdResourceDropState state = new KlighdResourceDropState(
                                    getViewSite().getSecondaryId(), resources[0]);
                            trigger.trigger(state);
                        }
                    }
                }
            }

            public void dropAccept(final DropTargetEvent event) {
                // do nothing
            }

            public void dragOperationChanged(final DropTargetEvent event) {
                if (event.detail == DND.DROP_DEFAULT) {
                    if ((event.operations & DND.DROP_COPY) != 0) {
                        event.detail = DND.DROP_COPY;
                    } else {
                        event.detail = DND.DROP_NONE;
                    }
                }
            }

            public void dragEnter(final DropTargetEvent event) {
                if (event.detail == DND.DROP_DEFAULT) {
                    if ((event.operations & DND.DROP_COPY) != 0) {
                        event.detail = DND.DROP_COPY;
                    } else {
                        event.detail = DND.DROP_NONE;
                    }
                }
            }

            public void dragOver(final DropTargetEvent event) {
                // do nothing
            }

            public void dragLeave(final DropTargetEvent event) {
                // do nothing
            }

        });
    }    
}