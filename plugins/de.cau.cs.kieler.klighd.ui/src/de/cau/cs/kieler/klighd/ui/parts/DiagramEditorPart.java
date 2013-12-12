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
package de.cau.cs.kieler.klighd.ui.parts;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.KlighdPreferences;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.IDiagramOutlinePage;
import de.cau.cs.kieler.klighd.internal.ILayoutConfigProvider;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;
import de.cau.cs.kieler.klighd.ui.DiagramViewManager;
import de.cau.cs.kieler.klighd.ui.internal.options.DiagramSideBar;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * An editor which is able to display models in light-weight diagrams.
 *
 * @author msp
 * @author uru
 */
public class DiagramEditorPart extends EditorPart implements IDiagramWorkbenchPart,
        ILayoutConfigProvider {
    
    /** the resource set managed by this editor part. */
    private ResourceSet resourceSet;

    /** the model represented by this editor part. */
    private Object model;

    /** the viewer for this editor part. */
    private ContextViewer viewer;

    /** the dirty status of the editor. */
    private boolean dirty;

    /** the global, common toolbar for all editors. */
    private IToolBarManager toolBar;

    /** a zoomToFit toolbar button exclusively for one instance of this editor part. */
    private ActionContributionItem zoomToFitItem;
    private Action zoomToFitAction;
    private ActionContributionItem zoomToFocusItem;
    private Action zoomToFocusAction;

    /** a zoomToOne button. */
    private ActionContributionItem zoomToOneItem;

    /**
     * Creates a diagram editor part.
     */
    public DiagramEditorPart() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
        setSite(site);
        setInput(input);
        loadModel();
        registerResourceChangeListener();
    }

    private DiagramSideBar sideBar;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        // set the part name
        setPartName(getEditorInput().getName());
        
        // add buttons to the editor toolbar
        // requires non-null 'viewer' field
        if (toolBar == null) {
            toolBar = this.getEditorSite().getActionBars().getToolBarManager();
            createButtons();
            this.getEditorSite().getWorkbenchWindow().getPartService()
            .addPartListener(toolBarListener);
        }
        
        // introduce a new Composite that accommodates the visualized content
        Composite diagramComposite = new Composite(parent, SWT.NONE);
        diagramComposite.setLayout(new FillLayout());
        
        // create a context viewer
        viewer = new ContextViewer(diagramComposite);
        
        // create a view context carrying all data required for building up the diagram
        final ViewContext viewContext =
                new ViewContext(this, model).configure(configureKlighdProperties());

        // create the options pane
        sideBar = DiagramSideBar.createSideBar(parent, diagramComposite, viewContext);
        
        if (viewContext != null) {
            viewer.setModel(viewContext);
            
            // do an initial update of the view context
            LightDiagramServices.updateViewContext(viewContext, model);
            
            // register this editor part in the DiagramViewManager in order to
            //  obtain it based in the ViewContext, e.g. for performing the layout,
            //  see e.g. LightDiagramServices#layoutDiagram(IDiagramWorkbenchPart,
            //          de.cau.cs.kieler.klighd.IViewer, boolean, boolean, java.util.List)
            DiagramViewManager.getInstance().registerView(this);
            
            if (requiresInitialLayout(viewContext)) {
                // in order to avoid flickering we set the viewer's control
                //  (the canvas) invisible, the canvas of a potentially created outline
                //  page is invisible after initialization, too.
                viewer.getControl().setVisible(false);
                
                // it is important to wait with the layout call until the #createPartControl
                // method has finished and the widget toolkit has applied proper bounds
                // to the parent composite via a Composite#layout call. 
                // Otherwise a possible zoomToFit after the layout will fail since the
                // view bounds are empty and no 'view area' to which to zoom can be
                // determined. The async call here hopefully assures this.
                Display.getCurrent().asyncExec(new Runnable() {
                    public void run() {
                        LightDiagramServices.layoutDiagram(viewContext, false, false);
                        
                        // now the editor's and outline page's canvas can be set visible
                        viewer.getControl().setVisible(true);
                        if (currentOutlinePage != null) {
                            currentOutlinePage.setVisible(true);
                        }
                    }
                });
            } else if (currentOutlinePage != null) {
                currentOutlinePage.setVisible(true);
            }

            sideBar.updateOptions(diagramComposite, viewer.getViewContext(), false);

            // since no initial selection is set in the view context/context viewer implementation,
            //  define some here by selection the root of the view model representing the diagram canvas!
            viewer.resetSelectionTo(viewContext.getViewModel());
        } else {
            viewer.setModel("The selected file does not contain any supported model.", false);
        }
        
        // register the context viewer as selection provider on the workbench
        getSite().setSelectionProvider(viewer);
        
        // the initialization of the context menu is done in PiccoloViewer#addContextMenu()
    }
    
    /**
     * Tester that decides on the need for computing the diagram layout while opening the diagram.<br>
     * May be overridden by subclasses.
     * 
     * @param viewContext
     *            provides context data that might be incorporated in the decision
     * @return true if the layout shall be (re-) computed while opening the diagram.
     */
    public boolean requiresInitialLayout(final ViewContext viewContext) {
        final KNode viewModel = (KNode) viewContext.getViewModel();
        final KShapeLayout diagramLayout = viewModel.getData(KShapeLayout.class);
        
        return diagramLayout.getWidth() == 0 && diagramLayout.getHeight() == 0;
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
        DiagramViewManager.getInstance().unregisterViewContexts(this);
        unregisterResourceChangeListener();
        getEditorSite().getWorkbenchWindow().getPartService().removePartListener(toolBarListener);
        
        if (this.sideBar != null) {
            this.sideBar.dispose();
        }
        
        super.dispose();
    }
    
    private IDiagramOutlinePage currentOutlinePage = null;
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    public Object getAdapter(final Class type) {
        if (type == IContentOutlinePage.class && viewer instanceof IDiagramOutlinePage.Provider) {

            currentOutlinePage = ((IDiagramOutlinePage.Provider) viewer).getDiagramOutlinePage();
            if (currentOutlinePage != null) {
                // if the main canvas is visible we can assume the presence of a properly
                //  initialized and arrange diagram (see #createPartControl() above),
                // otherwise leave the outline canvas invisible, thus...
                currentOutlinePage.setVisible(viewer.getControl().isVisible()
                        || !requiresInitialLayout(viewer.getViewContext()));
                return currentOutlinePage;
            }
        }
        return super.getAdapter(type);
    }

    /**
     * {@inheritDoc}
     */
    public ContextViewer getContextViewer() {
        return viewer;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getPartId() {
        return "diagramEditor:" + getEditorInput().toString();
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
    @Override
    public boolean isDirty() {
        return dirty;
    }
    
    /**
     * Set the dirty status of the editor.
     * 
     * @param dirty the new dirty status
     */
    public void setDirty(final boolean dirty) {
        this.dirty = dirty;
        firePropertyChange(IEditorPart.PROP_DIRTY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSave(final IProgressMonitor monitor) {
        try {
            
            // stop listening so the resource saving won't cause a model update
            unregisterResourceChangeListener();
            
            // save all opened resources
            for (Resource resource : resourceSet.getResources()) {
                resource.save(Collections.emptyMap());
            }
            
            // restart listening to future resource updates
            registerResourceChangeListener();
            
            setDirty(false);
        } catch (IOException exception) {
            throw new WrappedException(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSaveAs() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Load a model from the editor input. The result is put into {@link #model}.
     * 
     * @throws PartInitException if loading the model fails
     */
    protected void loadModel() throws PartInitException {
        // get a URI or an input stream from the editor input
        URI uri = null;
        InputStream inputStream = null;
        IEditorInput input = getEditorInput();
        if (input instanceof IFileEditorInput) {
            IFile file = ((IFileEditorInput) input).getFile();
            uri = URI.createPlatformResourceURI(file.getFullPath().toString(), false);
        } else if (input instanceof IURIEditorInput) {
            java.net.URI inputUri = ((IURIEditorInput) input).getURI();
            uri = URI.createURI(inputUri.toString());
        } else if (input instanceof IPathEditorInput) {
            IPath path = ((IPathEditorInput) input).getPath();
            uri = URI.createFileURI(path.toString());
        } else if (input instanceof IStorageEditorInput) {
            try {
                IStorage storage = ((IStorageEditorInput) input).getStorage();
                inputStream = storage.getContents();
            } catch (CoreException exception) {
                throw new PartInitException("An error occurred while accessing the storage.",
                        exception);
            }
        } else {
            throw new PartInitException("The given editor input is not supported.");
        }
        
        Resource resource;
        try {
            resourceSet = new ResourceSetImpl();
            configureResourceSet(resourceSet);
            if (inputStream != null) {
                // load a stream-based resource
                uri = URI.createFileURI("temp.xmi");
                resource = resourceSet.createResource(uri);
                resource.load(inputStream, Collections.EMPTY_MAP);
            } else {
                // load a URI-based resource
                resource = resourceSet.getResource(uri, true);
            }
        } catch (IOException exception) {
            throw new PartInitException("An error occurred while loading the resource.", exception);
        } catch (WrappedException exception) {
            throw new PartInitException("An error occurred while loading the resource.",
                    exception.getCause());
        }
        
        if (resource.getContents().isEmpty()) {
            throw new PartInitException("The resource is empty.");
        }
        // default behavior: get the first element in the resource
        model = resource.getContents().get(0);
    }
    
    /**
     * Configures the given resource set. The default implementation does nothing.
     * 
     * @param set the resource set to be configured.
     */
    protected void configureResourceSet(final ResourceSet set) {
        
    }

    /**
     * Returns a configuration for the KLighD view. Override this method to use a custom configuration.
     * The default implementation configures KLighD to use the simple update strategy.
     * 
     * @return KLighD configuration.
     */
    protected IPropertyHolder configureKlighdProperties() {
        MapPropertyHolder props = new MapPropertyHolder();
        props.setProperty(KlighdSynthesisProperties.REQUESTED_UPDATE_STRATEGY, SimpleUpdateStrategy.ID);
        return props;
    }

    /**
     * Update the viewed model using the given resource.
     * 
     * @param resource a resource
     */
    private void updateModel(final Resource resource) {
        if (resource.isLoaded()) {
            resource.unload();
            try {
                resource.load(Collections.EMPTY_MAP);

                // default behavior: get the first element in the resource
                model = resource.getContents().get(0);
                
                ViewContext viewContext = viewer.getViewContext();
                LightDiagramServices.updateViewContext(viewContext, model);
            } catch (IOException exception) {
                IStatus status = new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                        "Failed to update " + resource.getURI().toString(), exception);
                StatusManager.getManager().handle(status);
            }
        }
    }
    
    /**
     * Register the resource change listener.
     */
    private void registerResourceChangeListener() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener,
                IResourceChangeEvent.POST_CHANGE);
    }
    
    /**
     * Unregister the resource change listener.
     */
    private void unregisterResourceChangeListener() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);
    }
    
    /**
     * A resource change listener that updates the editor when resources are removed or changed.
     */
    private IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {
        public void resourceChanged(final IResourceChangeEvent event) {
            try {
                event.getDelta().accept(new IResourceDeltaVisitor() {
                    public boolean visit(final IResourceDelta delta) {
                        if (delta.getResource().getType() == IResource.FILE) {
                            if (delta.getKind() == IResourceDelta.REMOVED
                                    || delta.getKind() == IResourceDelta.CHANGED
                                    && delta.getFlags() != IResourceDelta.MARKERS) {
                                // this won't work if the resource was loaded from an absolute path
                                final Resource resource = resourceSet.getResource(
                                        URI.createPlatformResourceURI(
                                        delta.getFullPath().toString(), true), false);
                                if (resource != null) {
                                    getSite().getShell().getDisplay().asyncExec(new Runnable() {
                                        public void run() {
                                            if (delta.getKind() == IResourceDelta.REMOVED) {
                                                // a required resource was removed, so close the editor
                                                getSite().getPage().closeEditor(
                                                        DiagramEditorPart.this, false);
                                            } else {
                                                updateModel(resource);
                                            }
                                        }
                                    });
                                }
                            }
                        }
    
                        return true;
                    }
                });
            } catch (CoreException exception) {
                StatusManager.getManager().handle(exception, KlighdPlugin.PLUGIN_ID);
            }
        }
    };
          
    /**
     * Add the buttons to the tool bar.
     */
    private void createButtons() {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();

        // zoom to fit
        zoomToFitAction = new Action("Toggle Zoom to Fit", IAction.AS_CHECK_BOX) {
            // Constructor
            {
                setImageDescriptor(KlighdPlugin
                        .getImageDescriptor("icons/kieler-zoomtofit.gif"));
                final ViewContext vc =
                        DiagramEditorPart.this.getContextViewer().getViewContext();
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
                final ViewContext vc =
                        DiagramEditorPart.this.getContextViewer().getViewContext();
                if (vc != null) {
                    vc.setZoomStyle(ZoomStyle.create(this.isChecked(), false));

                    // perform zoom to fit upon activation of the toggle button
                    if (this.isChecked()) {
                        // uncheck the zoom to focus button
                        zoomToFocusAction.setChecked(false);

                        LightDiagramServices.layoutAndZoomDiagram(DiagramEditorPart.this);
                    }

                }
            }
        };
        zoomToFitAction.setId("de.cau.cs.kieler.klighd.editor.zoomToFit.h" + hashCode());
        // create the contribution item
        zoomToFitItem = new ActionContributionItem(zoomToFitAction);
        
        // zoom to focus
        zoomToFocusAction = new Action("Toggle Zoom to Focus", IAction.AS_CHECK_BOX) {
            // Constructor
            {
                setImageDescriptor(KlighdPlugin
                        .getImageDescriptor("icons/kieler-zoomtofocus.gif"));
                final ViewContext vc = DiagramEditorPart.this.getContextViewer().getViewContext();
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
                final ViewContext vc = DiagramEditorPart.this.getContextViewer().getViewContext();
                if (vc != null) {
                    vc.setZoomStyle(ZoomStyle.create(false, this.isChecked()));

                    // perform zoom to focus upon activation of the toggle button
                    if (this.isChecked()) {
                        LightDiagramServices.layoutAndZoomDiagram(DiagramEditorPart.this);
                        
                        // uncheck the zoom to fit button
                        zoomToFitAction.setChecked(false);
                    }

                }
            }
        };
        zoomToFocusAction.setId("de.cau.cs.kieler.klighd.editor.zoomToFocus.h" + hashCode());
        zoomToFocusItem = new ActionContributionItem(zoomToFocusAction);
        
        // zoom to one ...
        Action zoomToOne = new Action("Zoom to Original Size", IAction.AS_PUSH_BUTTON) {
            {
                setImageDescriptor(KlighdPlugin
                        .getImageDescriptor("icons/kieler-zoomtoone.gif"));
            }
            @Override
            public void run() {
                DiagramEditorPart.this.getContextViewer().zoomToLevel(1,
                        KlighdConstants.DEFAULT_ANIMATION_TIME);
            }
        };
        zoomToOne.setId("de.cau.cs.kieler.klighd.editor.zoomToOne.h" + hashCode());
        zoomToOneItem = new ActionContributionItem(zoomToOne);
    }

    /**
     * For edit parts only one common toolbar exists, hence we have to remove the buttons of one
     * editor as soon as a different editor is activated and add our own buttons.
     * 
     * TODO fix this. When closing an editor, the toolbar of the new editor is greyed out.
     * The toolbar itself and its items are enabled however. This might be due
     * to the partClosed event being invoked after the partActivate event.
     */
    private IPartListener toolBarListener = new IPartListener() {

        public void partOpened(final IWorkbenchPart part) {
        }

        public void partDeactivated(final IWorkbenchPart part) {
            remove(part);
        }

        public void partClosed(final IWorkbenchPart part) {
            remove(part);
        }

        public void partBroughtToTop(final IWorkbenchPart part) {
        }

        public void partActivated(final IWorkbenchPart part) {
            if (part.equals(DiagramEditorPart.this)) {
                toolBar.add(zoomToFitItem);
                toolBar.add(zoomToFocusItem);
                toolBar.add(zoomToOneItem);
                toolBar.update(true);
            }
        }
        
        private void remove(final IWorkbenchPart part) {
            if (part.equals(DiagramEditorPart.this)) {
                toolBar.remove(zoomToFitItem);
                toolBar.remove(zoomToFocusItem);
                toolBar.remove(zoomToOneItem);
                toolBar.update(true);
            }
        }
    };
}
