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
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ResourceTransfer;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.IKlighdTrigger;
import de.cau.cs.kieler.klighd.internal.ILayoutConfigProvider;
import de.cau.cs.kieler.klighd.ui.DiagramViewManager;
import de.cau.cs.kieler.klighd.ui.internal.options.DiagramSideBar;
import de.cau.cs.kieler.klighd.ui.printing.PrintAction;
import de.cau.cs.kieler.klighd.ui.viewers.UiContextViewer;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * A view which is able to display models in lightweight diagrams.
 *
 * @author mri
 * @author chsch
 * @author msp
 * @author uru
 */
public class DiagramViewPart extends ViewPart implements IDiagramWorkbenchPart,
        ILayoutConfigProvider {

    /** The KIML UI plug-in's id, used for avoiding a hard dependency for just revealing the image. */
    public static final String KIML_UI_PLUGIN_ID = "de.cau.cs.kieler.kiml.ui";

    /** The id this {@link ViewPart} is registered with in the extension point. */
    public static final String VIEW_ID = "de.cau.cs.kieler.klighd.ui.parts.DiagramViewPart";

    /** Action identifier for resetting the layout options in the side bar. */
    public static final String ACTION_ID_RESET_LAYOUT_OPTIONS = VIEW_ID + ".resetLayoutOptions";

    /** the default name for this view. */
    public static final String DEFAULT_NAME = "KLighD Diagram";

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
     * Listens to resize changes and triggers a re-layout of the diagram in case a zoom style is
     * defined.
     */
    private final ControlListener diagramAreaListener =
            DiagramWorkbenchParts.createDiagramAreaChangeListener(this);

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {

        // introduce a new Composite that accommodates the visualized content
        this.diagramComposite = new Composite(parent, SWT.NONE);
        this.diagramComposite.setVisible(false);
        this.diagramComposite.setLayout(new FillLayout());

        // create the context viewer
        viewer = new UiContextViewer(diagramComposite, this);

        // add buttons to the view toolbar
        // requires non-null 'viewer' field
        addButtons();

        // put some default actions into the view menu
        fillViewMenu(getViewSite().getActionBars().getMenuManager());

        registerPrintSupport();

        // install a drop handler for the view (XXX this could be omitted)
        installDropHandler(parent);

        viewer.setModel("No model selected.", false);

        // listen to any changes of the diagram area's size and re-zoom the diagram if
        // a zoom style is defined
        // note that it is enough to register the listener on the composite containing the sidebar
        // as this is resized simultaneously with the main window
        diagramComposite.addControlListener(diagramAreaListener);

        // the configuration of the context menu, selection provider,
        // and UI (key binding) context activation is done in the UiContextViewer
    }

    /**
     * (Re-)Initializes the given {@link DiagramViewPart}.<br>
     * This involves the creation of a {@link ViewContext}, the execution the matching
     * implementation {@link de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
     * AbstractDiagramSynthesis}, the application of automatic layout. and the update of the diagram
     * side bar entries.
     *
     * @param name
     *            the name (can be null if the view should be created with the default name)
     * @param model
     *            the model (can be null if the view should be created without an initial model)
     * @param properties
     *            the property holder containing properties configurations or <code>null</code>
     */
    public void initialize(final Object model, final String name, final IPropertyHolder properties) {

        // set the view name
        if (name != null) {
            this.setName(name);
        }

        // let the light diagram service create a view context and register it
        final ViewContext viewContext = new ViewContext(this, model);
        if (properties != null) {
            viewContext.configure(properties);
        } else {
            viewContext.configure();
        }

        DiagramViewManager.getInstance().registerView(this);

        // create the options pane
        if (sideBar == null) {
            sideBar = DiagramSideBar.createSideBar(
                diagramComposite.getParent(), diagramComposite, viewContext);
        }

        this.viewer.setModel(viewContext);

        // do an initial update of the view context
        viewContext.getLayoutRecorder().startRecording();
        viewContext.update(model);

        // fill the options pane according to the the incorporated transformations
        this.updateOptions(false);

        // make the view visible without giving it the focus
        //  this must be done before applying the layout as otherwise
        //  the canvas size may not be determined - it is required for proper zooming
        this.getViewSite().getPage().bringToTop(this);

        LightDiagramServices.layoutDiagram(viewContext, false);

        // setting the diagram composite visible strictly after applying the initial layout avoids
        //  flickering and suppresses the DiagramAreaChangeListener from getting active too early
        this.diagramComposite.setVisible(true);

        // trigger the create success status
        KlighdPlugin.getTrigger().triggerStatus(IKlighdTrigger.Status.CREATE_SUCCESS, viewContext);
    }

    /**
     * {@inheritDoc}
     */
    public ViewContext getViewContext() {
        return viewer.getViewContext();
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
    public void resetLayoutConfig() {
        resetLayoutConfig(true);
    }

    /**
     * {@inheritDoc}
     */
    public void resetLayoutConfig(final boolean doLayout) {
        if (this.sideBar != null) {
            this.sideBar.resetLayoutOptionsToDefaults(doLayout);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        DiagramViewManager.getInstance().unregisterView(this);

        if (!diagramComposite.isDisposed()) {
            diagramComposite.removeControlListener(diagramAreaListener);
        }

        if (this.sideBar != null) {
            this.sideBar.dispose();
        }
        this.sideBar = null;

        final IViewSite viewSite = this.getViewSite();
        viewSite.setSelectionProvider(null);
        viewSite.getActionBars().setGlobalActionHandler(ActionFactory.PRINT.getId(), null);

        this.viewer = null;

        this.disposed = true;

        super.dispose();
    }

    /**
     * (Re-)Evaluates the diagram (synthesis) and layout options registered in the employed
     * {@link ViewContext} and populates the diagram side bar accordingly. For internal use only!
     *
     * @param fitSpace
     *            if <code>true</code> a {@link de.cau.cs.kieler.klighd.ZoomStyle#ZOOM_TO_FIT
     *            ZoomStyle#ZOOM_TO_FIT} will applied to the diagram in order to fit into the
     *            remaining space
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
     * @param menuManager
     *            the menu manager
     */
    protected void fillViewMenu(final IMenuManager menuManager) {
    }

    /**
     * Add the buttons to the tool bar.
     */
    protected void addButtons() {
        final IToolBarManager toolBar = getViewSite().getActionBars().getToolBarManager();
        toolBar.add(new Action("Refresh diagram", KlighdPlugin
                .getImageDescriptor("icons/full/elcl16/refresh.gif")) {

            @Override
            public void run() {
                DiagramViewManager.updateView(DiagramViewPart.this.getViewContext());
            }
        });

        // automatic layout button
        toolBar.add(new Action("Arrange", IAction.AS_PUSH_BUTTON) {
            // Constructor
            {
                final Bundle kimlUI = Platform.getBundle(KIML_UI_PLUGIN_ID);
                if (kimlUI != null) {
                    setImageDescriptor(ImageDescriptor.createFromURL(
                        kimlUI.getEntry("icons/menu16/kieler-arrange.gif")));
                }
            }

            @Override
            public void run() {
                LightDiagramServices.layoutDiagram(DiagramViewPart.this);
            }
        });

        // reset the layout options set over the side pane
        final IMenuManager menu = getViewSite().getActionBars().getMenuManager();
        resetLayoutOptionsAction = new Action("Reset Layout Options") {

            @Override
            public void run() {
                sideBar.resetLayoutOptionsToDefaults(true);
            }
        };
        resetLayoutOptionsAction.setId(ACTION_ID_RESET_LAYOUT_OPTIONS);
        menu.add(resetLayoutOptionsAction);
    }

    /**
     * Registers the default print support, may be overriden if necessary.
     */
    protected void registerPrintSupport() {

        // registers the print action by means of the action bars
        final IActionBars actions = this.getViewSite().getActionBars();
        if (actions != null) {
            actions.setGlobalActionHandler(ActionFactory.PRINT.getId(), new PrintAction(getViewer()));
        }
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
    public IViewer getViewer() {
        return viewer;
    }

    /**
     * Retrieve an action with the specified identifier.
     *
     * @param id
     *            an action identifier
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
        final DropTarget target = new DropTarget(parent, DND.DROP_COPY | DND.DROP_DEFAULT);
        final ResourceTransfer resourceTransfer = ResourceTransfer.getInstance();
        target.setTransfer(new Transfer[] { resourceTransfer });
        target.addDropListener(new DropTargetListener() {

            public void drop(final DropTargetEvent event) {
                if (resourceTransfer.isSupportedType(event.currentDataType)
                        && event.data instanceof IResource[]) {
                    final IResource[] resources = (IResource[]) event.data;
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
}
