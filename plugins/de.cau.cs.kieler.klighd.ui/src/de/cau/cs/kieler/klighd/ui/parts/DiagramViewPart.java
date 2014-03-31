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
package de.cau.cs.kieler.klighd.ui.parts;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ResourceTransfer;
import org.eclipse.ui.part.ViewPart;

import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.KlighdPreferences;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.ILayoutConfigProvider;
import de.cau.cs.kieler.klighd.ui.DiagramViewManager;
import de.cau.cs.kieler.klighd.ui.internal.options.DiagramSideBar;
import de.cau.cs.kieler.klighd.ui.internal.viewers.UiContextViewer;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * A view which is able to display models in light-weight diagrams.
 * 
 * @author mri
 * @author chsch
 * @author msp
 * @author uru
 */
public class DiagramViewPart extends ViewPart implements IDiagramWorkbenchPart, ILayoutConfigProvider {

    /** The id this {@link ViewPart} is registered with in the extension point. */
    public static final String VIEW_ID = "de.cau.cs.kieler.klighd.ui.parts.DiagramViewPart";

    /** Action identifier for resetting the layout options in the side bar. */
    public static final String ACTION_ID_RESET_LAYOUT_OPTIONS = VIEW_ID + ".resetLayoutOptions";

    /** the default name for this view. */
    public static final String DEFAULT_NAME = "Light Diagram";

    /** the viewer for this view part. */
    private ContextViewer viewer;

    /**
     * Tracks whether this {@link ViewPart} is disposed. Since the {@link DiagramViewManager} tracks
     * created views it needs to test whether those views are still alive.
     */
    private boolean disposed = false;
    
    /**
     * Action to reset the layout options.
     */
    private IAction resetLayoutOptionsAction;
    
    private DiagramSideBar sideBar;
    
    private Composite diagramComposite;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {        
        
        // introduce a new Composite that accommodates the visualized content
        this.diagramComposite = new Composite(parent, SWT.NONE);
        this.diagramComposite.setLayout(new FillLayout());
        
        // create the context viewer
        viewer = new UiContextViewer(diagramComposite, this);
        
        // add buttons to the view toolbar
        //  requires non-null 'viewer' field 
        addButtons();
        
        // put some default actions into the view menu
        fillViewMenu(getViewSite().getActionBars().getMenuManager());
        
        // install a drop handler for the view (XXX this could be omitted)
        installDropHandler(parent);

        viewer.setModel("No model selected.", false);
        
        // listen to any changes of the diagram area's size and re-zoom the diagram if  
        // a zoom style is defined
        // note that it is enough to register the listener on the composite containing the sidebar
        // as this is resized simultaneously with the main window
        diagramComposite.addControlListener(diagramAreaListener);
        
        // the configuration of the context menu, selection provider,
        //  and UI (key binding) context activation is done in the UiContextViewer
    }
    
    /**
     * Sets the {@link ViewContext} to be used by this view part.<br>
     * Note that this method may be called multiple times in life of a part instance.
     * 
     * @param viewContext the {@link ViewContext} to be displayed
     */
    public void setViewContext(final ViewContext viewContext) {
        // create the options pane
        if (sideBar == null) {
            sideBar = DiagramSideBar.createSideBar(
                    diagramComposite.getParent(), diagramComposite, viewContext);
        }

        this.getViewer().getContextViewer().setModel(viewContext);
    }
    
    /**
     * {@inheritDoc}
     */
    public ILayoutConfig getLayoutConfig() {
        return this.sideBar != null ? this.sideBar.getLayoutConfig() : null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();

        if (!diagramComposite.isDisposed()) {
            diagramComposite.removeControlListener(diagramAreaListener);
        }
        
        if (this.sideBar != null) {
            this.sideBar.dispose();
        }

        disposed = true;
        DiagramViewManager.getInstance().unregisterViewContexts(this);
    }
    
    /**
     * 
     * @param fitSpace a;
     */
    public void updateOptions(final boolean fitSpace) {
        this.sideBar.updateOptions(diagramComposite, this.viewer.getViewContext(), fitSpace);
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
    }
    
    private Action zoomToFitAction;
    private Action zoomToFocusAction;

    /**
     * Add the buttons to the tool bar.
     */
    private void addButtons() {
        final IToolBarManager toolBar = getViewSite().getActionBars().getToolBarManager();
        toolBar.add(new Action("Refresh diagram", KlighdPlugin
                .getImageDescriptor("icons/full/elcl16/refresh.gif")) {
            public void run() {
                DiagramViewManager.getInstance().updateView(DiagramViewPart.this.getPartId());
            }
        });
        
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();

        // toggle zoom to fit behavior
        zoomToFitAction = new Action("Toggle Zoom to Fit", IAction.AS_CHECK_BOX) {
            // Constructor
            {
                setImageDescriptor(KlighdPlugin
                        .getImageDescriptor("icons/kieler-zoomtofit.gif"));
                final ViewContext vc = DiagramViewPart.this.getViewer().getViewContext();
                if (vc != null) {
                    setChecked(vc.isZoomToFit());
                } else {
                    ZoomStyle style = ZoomStyle.valueOf(
                            preferenceStore.getString(KlighdPreferences.ZOOM_STYLE));
                    setChecked(style == ZoomStyle.ZOOM_TO_FIT);
                }
            }

            @Override
            public void run() {
                final ViewContext vc = DiagramViewPart.this.getViewer().getViewContext();
                if (vc != null) {
                    vc.setZoomStyle(ZoomStyle.create(this.isChecked(), false));

                    // perform zoom to fit upon activation of the toggle button
                    if (this.isChecked()) {
                        LightDiagramServices.layoutAndZoomDiagram(DiagramViewPart.this);
                        
                        zoomToFocusAction.setChecked(false);
                    }

                }
            }
        };
        toolBar.add(zoomToFitAction);
        
        zoomToFocusAction = new Action("Toggle Zoom to Focus", IAction.AS_CHECK_BOX) {
            // Constructor
            {
                setImageDescriptor(KlighdPlugin
                        .getImageDescriptor("icons/kieler-zoomtofocus.gif"));
                final ViewContext vc = DiagramViewPart.this.getViewer().getViewContext();
                if (vc != null) {
                    setChecked(vc.isZoomToFocus());
                } else {
                    ZoomStyle style = ZoomStyle.valueOf(
                            preferenceStore.getString(KlighdPreferences.ZOOM_STYLE));
                    setChecked(style == ZoomStyle.ZOOM_TO_FOCUS);
                }
            }

            @Override
            public void run() {
                final ViewContext vc = DiagramViewPart.this.getViewer().getViewContext();
                if (vc != null) {
                    vc.setZoomStyle(ZoomStyle.create(false, this.isChecked()));

                    // perform zoom to focus upon activation of the toggle button
                    if (this.isChecked()) {
                        LightDiagramServices.layoutAndZoomDiagram(DiagramViewPart.this);
                        
                        // uncheck the zoom to fit button
                        zoomToFitAction.setChecked(false);
                    }

                }
            }
        };
        toolBar.add(zoomToFocusAction);

        toolBar.add(new Action("Zoom to Original Size", IAction.AS_PUSH_BUTTON) {
            {
                setImageDescriptor(KlighdPlugin
                        .getImageDescriptor("icons/kieler-zoomtoone.gif"));
            }
            @Override
            public void run() {
                DiagramViewPart.this.getViewer().zoomToLevel(1, 
                        KlighdConstants.DEFAULT_ANIMATION_TIME);
            }
        });
        
        // automatic layout button
        toolBar.add(new Action("Arrange", IAction.AS_PUSH_BUTTON) {
            // Constructor
            {
                setImageDescriptor(KimlUiPlugin
                        .getImageDescriptor("icons/menu16/kieler-arrange.gif"));
            }

            public void run() {
                LightDiagramServices.layoutDiagram(DiagramViewPart.this);
            }
        });
        
        // reset the layout options set over the side pane
        IMenuManager menu = getViewSite().getActionBars().getMenuManager();
        resetLayoutOptionsAction = new Action("Reset Layout Options") {
            public void run() {
                sideBar.resetLayoutOptionsToDefaults();
            }
        };
        resetLayoutOptionsAction.setId(ACTION_ID_RESET_LAYOUT_OPTIONS);
        menu.add(resetLayoutOptionsAction);
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
    public String getPartId() {
        return this.getViewSite().getSecondaryId();
    }
    
    /**
     * {@inheritDoc}
     */
    public IViewer<?> getViewer() {
        return viewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated Use {@link #getViewer()}.
     */
    public ContextViewer getContextViewer() {
        return viewer;
    }
    
    /**
     * Retrieve an action with the specified identifier.
     * 
     * @param id an action identifier
     * @return the corresponding action
     */
    public IAction getAction(final String id) {
        if (id.equals(ACTION_ID_RESET_LAYOUT_OPTIONS)) {
            return resetLayoutOptionsAction;
        }
        return null;
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
                if (resourceTransfer.isSupportedType(event.currentDataType)
                        && event.data instanceof IResource[]) {
                    IResource[] resources = (IResource[]) event.data;
                    if (resources.length > 0) {
                        KlighdPlugin.getTrigger().triggerDrop(getViewSite().getSecondaryId(),
                                resources[0]);
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
    
    /**
     * Listens to resize changes and triggers a re-layout of the diagram in case a zoom style is
     * defined.
     */
    private ControlListener diagramAreaListener = new ControlListener() {

        public void controlResized(final ControlEvent e) {
            if (KlighdPreferences.isZoomOnWorkbenchpartChange()) {
            // assure that the composite's size is settled before we execute the layout
                Display.getCurrent().asyncExec(new Runnable() {
                    public void run() {
                        if (!DiagramViewPart.this.getViewer().getControl().isDisposed()
                                && DiagramViewPart.this.getViewer().getControl().isVisible()) {
                            LightDiagramServices.zoomDiagram(DiagramViewPart.this);
                        }
                    }
                });
            }
        }

        public void controlMoved(final ControlEvent e) {
        }
    };
}
