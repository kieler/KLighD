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
package de.cau.cs.kieler.klighd.views;

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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;
import de.cau.cs.kieler.klighd.util.Iterables2;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * An editor which is able to display models in light-weight diagrams.
 *
 * @author msp
 */
public class DiagramEditorPart extends EditorPart implements IDiagramWorkbenchPart {
    
    /** the resource set managed by this editor part. */
    private ResourceSet resourceSet;
    /** the model represented by this editor part. */
    private Object model;
    /** the viewer for this editor part. */
    private ContextViewer viewer;
    /** the dirty status of the editor. */
    private boolean dirty;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        // set the part name
        setPartName(getEditorInput().getName());
        
        // create a context viewer
        viewer = new ContextViewer(parent, "diagramEditor:" + getEditorInput().toString(), this);
        
        // create a view context for the viewer
        ViewContext viewContext = LightDiagramServices.getInstance().createViewContext(
                model, configureKlighdProperties());
        if (viewContext != null) {
            viewer.setModel(viewContext);
            // do an initial update of the view context
            LightDiagramServices.getInstance().updateViewContext(viewContext, model);
            
            DiagramViewManager.getInstance().registerView(this);
            
            if (requiresInitialLayout(viewContext)) {
                LightDiagramServices.layoutDiagram(viewContext, false, false);
            }
            viewer.updateOptions(false);

            // since no initial selection is set in the view context/context viewer implementation,
            // define some here by selection the root of the view model representing the diagram canvas!
            viewer.selection(null, Iterables2.singletonIterable((EObject) viewContext.getViewModel()));
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
    @Override
    public void dispose() {
        unregisterResourceChangeListener();
        
        if (viewer != null) {
            viewer.dispose();
        }
        
        super.dispose();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    public Object getAdapter(final Class type) {
        if (type == IContentOutlinePage.class) {
            IContentOutlinePage outlinePage = viewer.getOutlinePage();
            if (outlinePage != null) {
                return outlinePage;
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
        props.setProperty(LightDiagramServices.REQUESTED_UPDATE_STRATEGY, SimpleUpdateStrategy.ID);
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
                
                ViewContext viewContext = viewer.getCurrentViewContext();
                LightDiagramServices.getInstance().updateViewContext(viewContext, model);
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
            
}
