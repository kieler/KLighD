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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.triggers.KlighdStatusTrigger;
import de.cau.cs.kieler.klighd.triggers.KlighdStatusTrigger.KlighdStatusState;

/**
 * A singleton manager for creating, updating and closing diagram views. All methods in this class
 * have to be called in the UI thread.
 * 
 * @author mri
 * @author chsch
 */
public final class DiagramViewManager implements IPartListener {

    /** the property for the diagram id. */
    public static final IProperty<String> DIAGRAM_ID = new Property<String>("klighd.diagramId");
    /** the property for the view id. */
    public static final IProperty<String> VIEW_ID = new Property<String>("klighd.viewId");

    /** the primary identifier for the diagram view as specified in the view extension. */
    private static final String PRIMARY_VIEW_ID = "de.cau.cs.kieler.klighd.lightDiagramView";

    /** the singleton instance. */
    private static DiagramViewManager instance = new DiagramViewManager();

    /** whether the manager is currently registered as a part listener. */
    private boolean registered = false;

    /** the mapping of workbench parts on associated view contexts. */
    // Note: list instead of single value to enable multi-context views
    private Map<IDiagramWorkbenchPart, List<ViewContext>> partContextMapping = Maps.newHashMap();
    /** the mapping of diagram view identifiers on workbench parts. */
    private Map<String, IDiagramWorkbenchPart> idPartMapping = Maps.newHashMap();
    /** the mapping of diagram view identifiers on view contexts. */
    private Map<String, ViewContext> idContextMapping = Maps.newHashMap();

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton instance
     */
    public static DiagramViewManager getInstance() {
        return instance;
    }

    /**
     * A private constructor to prevent instantiation.
     */
    private DiagramViewManager() {
        // do nothing
        // chsch: does this the trick as well as at the other places? We'll see...
        registerPartListener();
    }

    /**
     * Returns the diagram view with the given identifier if available. Does not create any views.
     * 
     * @param id
     *            the diagram view identifier (can be null for the default view)
     * @return the diagram view or null if no view with the given identifier exists
     */
    public DiagramViewPart getView(final String id) {
        IDiagramWorkbenchPart view = idPartMapping.get(id);
        if (view != null && view instanceof DiagramViewPart) {
            if (((DiagramViewPart) view).isDisposed()) {
                // actually this branch should not be taken due to DiagramViewPart#dispose();
                // however, there're still those ugly exceptions after a view is closed and
                //  tried to be re-opened...
                this.unregisterViewContexts(view);
                return null;
            } else {
                return (DiagramViewPart) view;
            }
        } else {
            return null;
        }
    }

    
    /**
     * Returns the diagram editor part with the given identifier if available. Does not create any views.
     * 
     * @param id
     *            the diagram view identifier (can be null for the default view)
     * @return the diagram editor part or null if no view with the given identifier exists
     */
    public DiagramEditorPart getEditor(final String id) {
        IDiagramWorkbenchPart part = idPartMapping.get(id);
        if (part != null && part instanceof DiagramEditorPart) {
            return (DiagramEditorPart) part;
        } else {
            return null;
        }
    }

    
    /**
     * Updates the diagram view with the given identifier.<br>
     * <br>
     * The model has to be of the type of the old model, i.e. the view context associated with the
     * identifier must support the model.
     * 
     * @param id
     *            the diagram identifier (can be null for the default view)
     * @return the view with the identifier or null on failure
     * 
     * @author chsch
     */
    public IDiagramWorkbenchPart updateView(final String id) {
        return updateView(id, null, null, null);
    }
    
    
    /**
     * Updates the diagram view with the given identifier.<br>
     * <br>
     * The model has to be of the type of the old model, i.e. the view context associated with the
     * identifier must support the model.
     * 
     * @param id
     *            the diagram identifier (can be null for the default view)
     * @param propertyHolder
     *            the property holder containing properties for the view context or null to attach
     *            no properties
     * @return the view with the identifier or null on failure
     * 
     * @author chsch
     */
    public IDiagramWorkbenchPart updateView(final String id, final IPropertyHolder propertyHolder) {
        return updateView(id, null, null, propertyHolder);
    }
    
    
    /**
     * Updates the diagram view with the given identifier with a specified name and model.<br>
     * <br>
     * The model has to be of the type of the old model, i.e. the view context associated with the
     * identifier must support the model.
     * 
     * @param id
     *            the diagram identifier (can be null for the default view)
     * @param name
     *            the name (can be null if the name of the view should remain unchanged)
     * @param model
     *            the model (can be null if the displayed model should remain unchanged)
     * @param propertyHolder
     *            the property holder containing properties for the view context or null to attach
     *            no properties
     * @return the view with the identifier or null on failure
     */
    public IDiagramWorkbenchPart updateView(final String id, final String name, final Object model,
            final IPropertyHolder propertyHolder) {
        // register the manager as part listener if necessary
        registerPartListener();

        // get the view
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        ViewContext viewContext = idContextMapping.get(id);
        
        IDiagramWorkbenchPart diagramView = getView(id);
        
        if (diagramView != null) {
            // set the view name
            if (name != null) {
                ((DiagramViewPart) diagramView).setName(name);
            }
        } else {
            diagramView = getEditor(id);
            if (diagramView == null) {
                return null;
            }
        }
        
        // 'diagramView' is supposed to be non-null here
        if (viewContext == null) {
            viewContext = diagramView.getContextViewer().getCurrentViewContext();
            if (viewContext == null) {
                return null;
            }
        }
        
        // update the view context
        Object currentInputModel = viewContext.getInputModel(); 
        if (model != null || currentInputModel != null) {
            page.bringToTop(diagramView);
            // update the view context and viewer
            Object theModel = (model != null ? model : currentInputModel);
            
            viewContext.getViewer().startRecording();
            if (!LightDiagramServices.updateViewContext(viewContext, theModel,
                    propertyHolder)) {
                return null;
            }
            LightDiagramServices.layoutDiagram(viewContext);
        }
        
        
        // trigger the update status
        KlighdStatusState state =
                new KlighdStatusState(KlighdStatusState.Status.UPDATE, id, viewContext,
                        viewContext.getViewer());
        if (KlighdStatusTrigger.getInstance() != null) {
            KlighdStatusTrigger.getInstance().trigger(state);
        }
        
        return diagramView;
    }
    

    /**
     * Creates a diagram view with the given name and model under the specified identifier. <br>
     * <br>
     * chsch: refactored this method s.t. a view is opened only if a valid view context exists.
     * 
     * @param id
     *            the diagram identifier (can be null for the default view)
     * @param name
     *            the name (can be null if the view should be created with the default name)
     * @param model
     *            the model (can be null if the view should be created without an initial model)
     * @param propertyHolder
     *            the property holder containing properties for the view context or null to attach
     *            no properties
     * @return the view with the identifier (newly created or reused if it exists already) or null
     *         on failure
     */
    public DiagramViewPart createView(final String id, final String name, final Object model,
            final IPropertyHolder propertyHolder) {
        // register the manager as part listener if necessary
        registerPartListener();

        DiagramViewPart diagramView = null;

        // create a view context for the model
        if (model != null) {
            // let the light diagram service create a view context and register it
            ViewContext viewContext;
            if (propertyHolder != null) {
                viewContext = LightDiagramServices.createViewContext(model, propertyHolder);
            } else {
                viewContext = LightDiagramServices.createViewContext(model);
            }

            if (viewContext != null) {

                // make sure the view exists
                IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                IWorkbenchPage page = window.getActivePage();
                diagramView = getView(id);

                if (diagramView == null) {
                    // create the view and register it
                    try {
                        IViewPart view =
                                page.showView(PRIMARY_VIEW_ID, id, IWorkbenchPage.VIEW_VISIBLE);
                        if (view instanceof DiagramViewPart) {
                            diagramView = (DiagramViewPart) view;
                        } else {
                            throw new PartInitException(
                                    "KLighD: Creation of a KLighD DiagramViewPart failed.\n"
                                            + "This appears to be a heavy internal error!");
                        }
                        
                    } catch (PartInitException e) {
                        StatusManager.getManager()
                                .handle(new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, e
                                        .getMessage(), e));

                        // trigger the create failure status
                        KlighdStatusState state =
                                new KlighdStatusState(KlighdStatusState.Status.CREATE_FAILURE, id,
                                        null, null);
                        if (KlighdStatusTrigger.getInstance() != null) {
                            KlighdStatusTrigger.getInstance().trigger(state);
                        }
                        return null;
                    } catch (IllegalArgumentException e) {
                        StatusManager.getManager().handle(
                                new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                                        "Invalid KLighD view id:"
                                                + "must not be empty or contain any colons."));

                        // trigger the create failure status
                        KlighdStatusState state =
                                new KlighdStatusState(KlighdStatusState.Status.CREATE_FAILURE, id,
                                        null, null);
                        if (KlighdStatusTrigger.getInstance() != null) {
                            KlighdStatusTrigger.getInstance().trigger(state);
                        }
                        return null;
                    }
                } else {
                    // in case there is already a view with the given id
                    //  clean that one up by unregistering view contexts currently associated with it
                    //  as a new one will be registered below
                    unregisterViewContexts(diagramView);
                }

                // set the view name
                if (name != null) {
                    diagramView.setName(name);
                }

                registerViewContext(diagramView, id, viewContext);
                diagramView.getContextViewer().setModel(viewContext);

                // do an initial update of the view context
                viewContext.getViewer().startRecording();
                LightDiagramServices.updateViewContext(viewContext, model);
                
                LightDiagramServices.layoutDiagram(viewContext, false);

                // fill the options pane according to the the incorporated transformations
                diagramView.getContextViewer().updateOptions(viewContext.isZoomToFit());

                // make the view visible without giving it the focus
                page.bringToTop(diagramView);

                // trigger the create success status
                KlighdStatusState state =
                        new KlighdStatusState(KlighdStatusState.Status.CREATE_SUCCESS, id,
                                viewContext, viewContext.getViewer());
                if (KlighdStatusTrigger.getInstance() != null) {
                    KlighdStatusTrigger.getInstance().trigger(state);
                }
            }
        }
        return diagramView;
    }

    private void registerViewContext(final IDiagramWorkbenchPart view, final String id,
            final ViewContext context) {
        // remember the id in the view context
        context.setProperty(DIAGRAM_ID, id);
        // map the view to the view context
        List<ViewContext> viewContexts = partContextMapping.get(view);
        if (viewContexts == null) {
            viewContexts = new LinkedList<ViewContext>();
            partContextMapping.put(view, viewContexts);
        }
        viewContexts.add(context);
        // map the id to the view
        idPartMapping.put(id, view);
        idContextMapping.put(id, context);
    }
    
    /**
     * Allows {@link IDiagramWorkbenchPart}s that are not created via this manager to be registered,
     * e.g. {@link DiagramEditorPart DiagramEditorParts}. This is (currently) necessary in order to
     * let the side bar work correctly (diagram refresh), since those event listener call
     * {@link DiagramViewManager#updateView(String, IPropertyHolder)}.
     * 
     * Is package protected since it is only called from
     * {@link DiagramEditorPart#createPartControl(org.eclipse.swt.widgets.Composite)} at the moment.
     * 
     * @param part
     *            the {@link IDiagramWorkbenchPart} to register
     */
    void registerView(final IDiagramWorkbenchPart part) {
        final String id = part.getContextViewer().getViewPartId();
        this.idPartMapping.put(id, part);
        this.idContextMapping.put(id, part.getContextViewer().getCurrentViewContext());
    }

    /**
     * Is package protected in order to be callable from {@link DiagramViewPart#dispose()}.
     * 
     * @param view the view to close
     */
    void unregisterViewContexts(final IDiagramWorkbenchPart part) {
        // unmap the id from the view
        List<ViewContext> viewContexts = partContextMapping.get(part);
        if (viewContexts != null) {
            for (ViewContext viewContext : viewContexts) {
                String id = viewContext.getProperty(DIAGRAM_ID);
                idPartMapping.remove(id);
                idContextMapping.remove(id);

                // trigger the close status
                KlighdStatusState state =
                        new KlighdStatusState(KlighdStatusState.Status.CLOSE, id, viewContext,
                                viewContext.getViewer());
                if (KlighdStatusTrigger.getInstance() != null) {
                    KlighdStatusTrigger.getInstance().trigger(state);
                }
            }
        }
        // unmap the view from all contexts
        partContextMapping.remove(part);
    }

    /**
     * Closes the diagram view associated with the given id.
     * 
     * @param id
     *            the identifier of the view to be closed.
     * @return <code>true</code> if a view could be closed successfully, and <code>false</code>
     *         otherwise.
     * 
     * @author chsch
     */
    public boolean closeView(final String id) {
        if (id.equals("")) {
            return false;
        }
        try {
            final DiagramViewPart view;
            final IDiagramWorkbenchPart part = this.idPartMapping.get(id);
            if (part instanceof DiagramEditorPart) {
                return false;
            } else if (part instanceof DiagramViewPart) {
                view = (DiagramViewPart) part;
            } else {
                view = null;
            }
            
            if (view != null) {
                unregisterViewContexts(view);
                view.getSite().getPage().hideView(view);
                return true;
            } else {
                
                final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (window == null) {
                    return false;
                }
                
                final IWorkbenchPage page = window.getActivePage();
                IViewReference viewRef = page.findViewReference(PRIMARY_VIEW_ID, id);
                if (viewRef != null) {
                    page.hideView(viewRef);
                    return true;
                }
            }
            
        } catch (NullPointerException e) {
            /* do nothing */
        }
        return false;
    }

    /**
     * Registers the manager as a part listener on the active workbench window.
     */
    private void registerPartListener() {
        if (!registered) {
            registered = true;
            IWorkbenchPage page =
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

            if (page == null) {
                return;
            }

            // find existing views
            IViewReference[] viewReferences = page.getViewReferences();
            for (IViewReference viewReference : viewReferences) {
                if (viewReference.getId().equals(PRIMARY_VIEW_ID)) {
                    // SUPPRESS CHECKSTYLE PREVIOUS block
                    
                    // chsch: for the Moment, reset all existing views since there may
                    // occur errors during the (re-)initialization a further views
                    
                    // Update: deactivated the closing of existing view skeletons as
                    // the behavior seems to be changed in e4
                    // page.hideView(viewReference);

                    // DiagramViewPart view = (DiagramViewPart) viewReference.getView(false);
                    // // TODO this does not take multi view contexts into account yet
                    // idPartMapping.put(viewReference.getSecondaryId(), view);
                }
            }
            // register as a part listener
            page.addPartListener(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partClosed(final IWorkbenchPart part) {
        if (part instanceof DiagramViewPart) {
            DiagramViewPart diagramView = (DiagramViewPart) part;
            // unregister all view contexts on the view
            unregisterViewContexts(diagramView);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partOpened(final IWorkbenchPart part) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void partActivated(final IWorkbenchPart part) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void partDeactivated(final IWorkbenchPart part) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void partBroughtToTop(final IWorkbenchPart part) {
        // do nothing
    }

}
