/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.view;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.elk.graph.KNode;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.SynthesisOption;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.ISynthesis;
import de.cau.cs.kieler.klighd.ui.parts.DiagramViewPart;
import de.cau.cs.kieler.klighd.ui.view.controller.AbstractViewUpdateController;
import de.cau.cs.kieler.klighd.ui.view.controller.ViewUpdateControllerFactory;
import de.cau.cs.kieler.klighd.ui.view.menu.SynthesisSelectionMenu;
import de.cau.cs.kieler.klighd.ui.view.model.ErrorModel;
import de.cau.cs.kieler.klighd.ui.view.model.MessageModel;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * 
 * This {@link DiagramViewPart} displays a diagram related to an open editor.
 * <p>
 * The retrieval of the model and the update of the diagram is handled by a {@link AbstractViewUpdateController}.
 * 
 * @author als
 * @kieler.design 2015-06-22 proposed
 * @kieler.rating 2015-06-22 proposed yellow
 *
 */
@SuppressWarnings("restriction")
public final class DiagramView extends DiagramViewPart implements ISelectionChangedListener {

    // -- CONSTANTS --

    /** Viewer ID. **/
    public static final String ID = "de.cau.cs.kieler.klighd.ui.view.diagram";

    /** UI job prefix. **/
    private static final String UPDATE_JOB = "Updating Diagram";

    // -- GUI (Model) texts --

    private static final String NO_MODEL = "No model in related editor";
    private static final String NO_MODEL_LINKED = "No model in active editor";
    private static final String UPDATE_DIAGRAM_EXCEPTION = "Displaying diagram failed!";
    private static final String NO_SYNTHESIS = "No Synthesis available!";
    private static final String DIAGRAM_IS_NULL = "Diagram is null or empty. Inernal KLighD error.";

    // -- Icons --
    /** The icon for refreshing view content. */
    private static final ImageDescriptor REFRESH_ICON =
            KlighdPlugin.getImageDescriptor("icons/full/elcl16/refresh.gif");
    /** The icon for layout view content. */
    private static final ImageDescriptor ARRANGE_ICON =
            KlighdViewPlugin.getImageDescriptor("icons/full/menu16/arrange.gif");
    /** The icon for linking the view with the current editor. */
    private static final ImageDescriptor LINK_ICON = AbstractUIPlugin
            .imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/elcl16/synced.gif");
    /** The icon for forking a view. */
    private static final ImageDescriptor FORK_ICON =
            KlighdViewPlugin.getImageDescriptor("icons/full/etool16/fork.png");

    // -- Instance list --

    /** List of all open {@link DiagramView}s. */
    private static List<DiagramView> views = new ArrayList<DiagramView>();

    // -- ATTRIBUTES --

    /** The views root composite. */
    private Composite viewComposite;

    /** Active related editor. */
    private IEditorPart editor;

    /** Stores used synthesis options. */
    private final Map<SynthesisOption, Object> recentSynthesisOptions =
            Collections.synchronizedMap(Maps.newHashMap());

    /** Stores displayed syntheses. */
    private final Set<ISynthesis> usedSyntheses = Collections.synchronizedSet(Sets.newHashSet());

    /** The responsible controller performing model updates. */
    private AbstractViewUpdateController controller = null;

    /** The already instantiated controllers. */
    private final Map<String, AbstractViewUpdateController> controllers =
            new HashMap<String, AbstractViewUpdateController>();

    /** The adapter listening on open, closed and activated editors. */
    private final DiagramViewEditorAdapter editorAdapter;

    // -- Toolbar --
    /** The toolbar manager. */
    private IToolBarManager toolBarManager;

    /** The action for reloading displayed model. */
    private final Action refreshAction;
    /** The action for performing. */
    private final Action layoutAction;
    /** The action for forking view. */
    private final Action forkAction;
    /** The action for linking with the active editor. */
    private final Action linkCheckAction;

    // -- Menu --
    /** The menu manger. */
    private IMenuManager menuManager;

    /** The action for reseting this view. */
    private final Action resetAction;
    /** The action for toggling selection highlighting in the editor. */
    private final Action synchronizeSelectionCheckAction;
    private static final boolean SYNCHRONIZE_SELECTION_CHECK_ACTION_DEFAULT_STATE = true;

    /** The menu and controller handling the selection of available synthesis. */
    private final SynthesisSelectionMenu synthesisSelection = new SynthesisSelectionMenu(this);

    // -- Static View Access
    // -------------------------------------------------------------------------

    /**
     * Returns the list of {@link DiagramView} currently displaying the content of the given editor.
     * 
     * @param editor
     *            the editor to filter for
     * @return List of {@link DiagramView}s associated with editor
     */
    public static List<DiagramView> getDiagramViews(final IEditorPart editor) {
        if (editor != null) {
            return Lists.newArrayList(Iterables.filter(views, new Predicate<DiagramView>() {
                @Override
                public boolean apply(final DiagramView view) {
                    return editor.equals(view.getEditor());
                }
            }));
        }
        return Collections.emptyList();
    }

    /**
     * Returns the list of all {@link DiagramView}s.
     * 
     * @return List of all {@link DiagramView}s
     */
    public static List<DiagramView> getAllDiagramViews() {
        return Lists.newArrayList(views);
    }

    // -- Constructor and Initialization
    // -------------------------------------------------------------------------

    /**
     * Create an instance.
     */
    public DiagramView() {
        super();

        // Add this view to the active list of {@link DiagramView}s
        views.add(this);

        // Create editor adapter
        editorAdapter = new DiagramViewEditorAdapter(this);

        // Refresh Button
        refreshAction = new Action("Refresh diagram", IAction.AS_PUSH_BUTTON) {

            @Override
            public void run() {
                if (controller != null) {
                    controller.refresh();
                }
            }
        };
        refreshAction.setId("refreshAction");
        refreshAction.setImageDescriptor(REFRESH_ICON);

        // Automatic Layout Button
        layoutAction = new Action("Arrange diagram", IAction.AS_PUSH_BUTTON) {
            @Override
            public void run() {
                LightDiagramServices.layoutDiagram(DiagramView.this);
            }
        };
        layoutAction.setId("layoutAction");
        layoutAction.setImageDescriptor(ARRANGE_ICON);

        // Fork Button
        forkAction = new Action("Fork this view", IAction.AS_PUSH_BUTTON) {
            @Override
            public void run() {
                forkView();
            }
        };
        forkAction.setId("forkAction");
        forkAction.setToolTipText("Fork this view");
        forkAction.setImageDescriptor(FORK_ICON);

        // Link Button
        linkCheckAction = new Action("Link with editor", IAction.AS_CHECK_BOX) {
            @Override
            public void run() {
                if (isChecked()) {
                    IEditorPart newEditor = getViewSite().getPage().getActiveEditor();
                    if (newEditor != null && newEditor != editor) {
                        setEditor(newEditor);
                    }
                }
            }
        };
        linkCheckAction.setId("linkCheckAction");
        linkCheckAction.setToolTipText("Link with editor");
        linkCheckAction.setImageDescriptor(LINK_ICON);
        linkCheckAction.setChecked(true);

        // -- MENU --

        // Reset Layout Options Item
        resetAction = new Action("Reset this View") {
            @Override
            public void run() {
                resetLayoutConfig();
                reset();
                updateDiagram();
            }
        };
        resetAction.setId("resetAction");
        resetAction.setId(ACTION_ID_RESET_LAYOUT_OPTIONS);

        // Selection Highlighting Item
        synchronizeSelectionCheckAction =
                new Action("Synchronize Selection", IAction.AS_CHECK_BOX) {
                    @Override
                    public void run() {
                        if (getViewer() != null) {
                            if (isChecked()) {
                                ((ContextViewer) getViewer())
                                        .addSelectionChangedListener(DiagramView.this);
                            } else {
                                ((ContextViewer) getViewer())
                                        .removeSelectionChangedListener(DiagramView.this);
                            }
                        }
                    }
                };
        synchronizeSelectionCheckAction.setId("synchronizeSelectionCheckAction");
        synchronizeSelectionCheckAction.setToolTipText(
                "When enabled the diagram selection will be synchronized with the editor selection.");
        synchronizeSelectionCheckAction
                .setChecked(SYNCHRONIZE_SELECTION_CHECK_ACTION_DEFAULT_STATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final IViewSite site) throws PartInitException {
        super.init(site);
        editorAdapter.activate();
        if (isPrimaryView()) {
            // The primary view is loaded separately from the secondary (forked) views because it may be reopened
            loadSettings();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final IViewSite site, final IMemento memento) throws PartInitException {
        super.init(site, memento);
        if (isPrimaryView() && hasSettings()) {
            // The primary view is loaded separately from the secondary (forked) views because it may be reopened
            loadSettings();
        } else if (memento != null) {
            loadState(memento);
        }
        editorAdapter.activate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        super.createPartControl(parent);
        viewComposite = parent;

        IActionBars bars = getViewSite().getActionBars();
        toolBarManager = bars.getToolBarManager();
        menuManager = bars.getMenuManager();

        // Some events may occur before invocation of this method and need to be triggered again
        // (e.g. setEditor)
        if (controller == null && editor != null) {
            addContributions();
            updateController();
        } else if (editor == null) {
            addContributions();
        }

        // Register selection listener
        ((ContextViewer) getViewer()).addSelectionChangedListener(this);
        
        // Handle secondary view info
        if (!isPrimaryView()) {
            SecondaryViewInfoHelper.secondaryViewCreated(this, parent);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addButtons() {
        // Suppressing DiagramViewPart default buttons
    }

    // -- View
    // -------------------------------------------------------------------------

    /**
     * Adds the menu and toolbar contributions.
     */
    private void addContributions() {
        toolBarManager.add(refreshAction);
        toolBarManager.add(layoutAction);
        toolBarManager.add(forkAction);
        toolBarManager.add(linkCheckAction);

        menuManager.add(resetAction);
        menuManager.add(synchronizeSelectionCheckAction);
        menuManager.add(synthesisSelection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        // Save before disposing
        if (isPrimaryView()) {
            // The primary view is saved separately from the secondary (forked) views because it can be reopened
            saveSettings();
        }
        super.dispose();
        views.remove(this);
        editorAdapter.deactivate();
        for (AbstractViewUpdateController c : controllers.values()) {
            c.onDispose();
        }
        
        // Handle secondary view info
        if (!isPrimaryView()) {
            SecondaryViewInfoHelper.secondaryViewDisposed(this);
        }        
    }

    /**
     * Forks this view into second view with the same settings.
     */
    private void forkView() {
        try {
            // Create new View
            IViewPart newViewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                    .getActivePage().showView(ID, Long.toString(System.currentTimeMillis()),
                            IWorkbenchPage.VIEW_ACTIVATE);
            // Configure child view
            if (newViewPart instanceof DiagramView) {
                DiagramView newDiagramView = (DiagramView) newViewPart;
                // Menu
                newDiagramView.linkCheckAction.setChecked(false);
                newDiagramView.synchronizeSelectionCheckAction
                        .setChecked(synchronizeSelectionCheckAction.isChecked());
                newDiagramView.synchronizeSelectionCheckAction.run();
                // State
                storeCurrentSynthesisOptions();
                newDiagramView.synthesisSelection.copy(synthesisSelection);
                newDiagramView.recentSynthesisOptions.putAll(recentSynthesisOptions);
                newDiagramView.usedSyntheses.addAll(usedSyntheses);
                for (AbstractViewUpdateController updateController : controllers.values()) {
                    AbstractViewUpdateController controllerCopy = ViewUpdateControllerFactory
                            .getNewInstance(updateController.getID(), newDiagramView);
                    controllerCopy.copy(updateController);
                    newDiagramView.controllers.put(controllerCopy.getID(), controllerCopy);
                }
                newDiagramView.setEditor(editor);
            }
        } catch (PartInitException e) {
            StatusManager.getManager().handle(new Status(IStatus.ERROR, KlighdViewPlugin.PLUGIN_ID,
                    e.getMessage(), e.getCause()), StatusManager.SHOW);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveState(final IMemento memento) {
        try {
            super.saveState(memento);
            if (memento != null) {

                memento.putBoolean("isLinkedWithActiveEditor", isLinkedWithActiveEditor());

                memento.putBoolean("synchronizeSelection",
                        synchronizeSelectionCheckAction.isChecked());

                if (editor != null && !isLinkedWithActiveEditor()) {
                    IPersistableElement editorPersistable =
                            editor.getEditorInput().getPersistable();
                    if (editorPersistable != null) {
                        memento.putString("relatedEditor", editorPersistable.getFactoryId());
                        editorPersistable.saveState(memento.createChild("editorInput"));
                    }
                }

                storeCurrentSynthesisOptions();
                IMemento recentSynthesisOptionsMemento =
                        memento.createChild("recentSynthesisOptions");
                HashMap<ISynthesis, IMemento> synthesesMementos =
                        new HashMap<ISynthesis, IMemento>(usedSyntheses.size());
                for (ISynthesis synthesis : usedSyntheses) {
                    synthesesMementos.put(synthesis, recentSynthesisOptionsMemento.createChild(
                            KlighdDataManager.getInstance().getSynthesisID(synthesis)));
                }
                for (Entry<SynthesisOption, Object> optionEntry : recentSynthesisOptions
                        .entrySet()) {
                    SynthesisOption option = optionEntry.getKey();
                    Object value = optionEntry.getValue();
                    if (value != null && !value.equals(option.getInitialValue())) {
                        String id = "opt" + option.getName().hashCode();
                        IMemento synthesisMemento = null;
                        for (ISynthesis synthesis : usedSyntheses) {
                            if (synthesis.getDisplayedSynthesisOptions().contains(option)) {
                                synthesisMemento = synthesesMementos.get(synthesis);
                                break;
                            }
                        }
                        if (synthesisMemento != null) {
                            synthesisMemento.putString(id,
                                    SynthesisOptionsPersistence.serialize(option, value));
                        }
                    }
                }

                synthesisSelection.saveState(memento.createChild("synthesisSelection"));

                IMemento controllersMemento = memento.createChild("controllers");
                for (AbstractViewUpdateController c : controllers.values()) {
                    c.saveState(controllersMemento.createChild(c.getID()));
                }
            }
        } catch (Exception e) {
            StatusManager.getManager().handle(new Status(IStatus.WARNING,
                    KlighdViewPlugin.PLUGIN_ID, "Cannot save view state", e), StatusManager.LOG);
        }
    }

    /**
     * Loads saved configuration form a memento.
     * 
     * @param memento
     *            saved configuration
     */
    private void loadState(final IMemento memento) {
        try {
            Boolean linkWithEditorValue = memento.getBoolean("isLinkedWithActiveEditor");
            if (linkWithEditorValue != null) {
                if (linkCheckAction != null) {
                    linkCheckAction.setChecked(linkWithEditorValue);
                }
            }

            Boolean synchronizeSelectionValue = memento.getBoolean("synchronizeSelection");
            if (synchronizeSelectionValue != null) {
                if (synchronizeSelectionCheckAction != null) {
                    synchronizeSelectionCheckAction.setChecked(synchronizeSelectionValue);
                    synchronizeSelectionCheckAction.run();
                }
            }

            String editorInputFactory = memento.getString("relatedEditor");
            if (editorInputFactory != null) {
                IElementFactory elementFactory =
                        PlatformUI.getWorkbench().getElementFactory(editorInputFactory);
                IMemento editorInputMemento = memento.getChild("editorInput");
                if (elementFactory != null && editorInputMemento != null) {
                    IAdaptable editorInputElelemt =
                            elementFactory.createElement(editorInputMemento);
                    if (editorInputElelemt instanceof IEditorInput) {
                        IEditorInput editorInput = (IEditorInput) editorInputElelemt;
                        for (IWorkbenchWindow window : PlatformUI.getWorkbench()
                                .getWorkbenchWindows()) {
                            for (IWorkbenchPage page : window.getPages()) {
                                IEditorPart foundEditor = page.findEditor(editorInput);
                                if (foundEditor != null) {
                                    setEditor(foundEditor);
                                }
                            }
                        }
                    }
                }
            }

            IMemento synthesisOptionsMemento = memento.getChild("recentSynthesisOptions");
            if (synthesisOptionsMemento != null) {
                for (IMemento synthesisOptionMemento : synthesisOptionsMemento.getChildren()) {
                    if (synthesisOptionMemento.getChildren().length != 0
                            || synthesisOptionMemento.getAttributeKeys().length != 0) {
                        ISynthesis synthesis = KlighdDataManager.getInstance()
                                .getDiagramSynthesisById(synthesisOptionMemento.getType());
                        if (synthesis != null) {
                            usedSyntheses.add(synthesis);
                            HashMap<String, SynthesisOption> optionMap =
                                    new HashMap<String, SynthesisOption>(
                                            synthesis.getDisplayedSynthesisOptions().size());
                            for (SynthesisOption synthesisOption : synthesis
                                    .getDisplayedSynthesisOptions()) {
                                optionMap.put("opt" + synthesisOption.getName().hashCode(),
                                        synthesisOption);
                            }
                            for (String key : synthesisOptionMemento.getAttributeKeys()) {
                                SynthesisOption option = optionMap.get(key);
                                if (option != null) {
                                    Object value = SynthesisOptionsPersistence.parse(option,
                                            synthesisOptionMemento.getString(key));
                                    if (value != null) {
                                        recentSynthesisOptions.put(option, value);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            IMemento synthesisSelectionMemento = memento.getChild("synthesisSelection");
            if (synthesisSelectionMemento != null) {
                synthesisSelection.loadState(synthesisSelectionMemento);
            }

            IMemento controllersMemento = memento.getChild("controllers");
            if (controllersMemento != null) {
                for (IMemento controllerMemento : controllersMemento.getChildren()) {
                    String controllerID = controllerMemento.getType();
                    AbstractViewUpdateController newController = ViewUpdateControllerFactory
                            .getNewInstance(controllerID, this);
                    if (newController != null) {
                        newController.loadState(controllerMemento);
                        controllers.put(controllerID, newController);
                    }
                }
            }
        } catch (Exception e) {
            StatusManager.getManager().handle(new Status(IStatus.WARNING,
                    KlighdViewPlugin.PLUGIN_ID, "Cannot load view state", e), StatusManager.LOG);
        }
    }

    /**
     * Resets all configurations to default values.
     */
    private void reset() {
        synchronizeSelectionCheckAction
                .setChecked(SYNCHRONIZE_SELECTION_CHECK_ACTION_DEFAULT_STATE);
        synchronizeSelectionCheckAction.run();
        synthesisSelection.clear();
        if (getViewer() != null && getViewContext() != null) {
            ViewContext vc = getViewContext();
            for (SynthesisOption option : recentSynthesisOptions.keySet()) {
                vc.configureOption(option, option.getInitialValue());
            }
            updateOptions(false);
        }
        recentSynthesisOptions.clear();
        usedSyntheses.clear();
        for (AbstractViewUpdateController c : controllers.values()) {
            c.reset();
        }
    }

    /**
     * @return the synthesisSelectionMenu
     */
    public SynthesisSelectionMenu getSynthesisSelectionMenu() {
        return synthesisSelection;
    }
    
    // -- Primary View specific methods
    // -------------------------------------------------------------------------
    
    /**
     * Returns whether this view instance is the primary view. The primary view can only be opened
     * by the user and is not created by forking another view.
     * 
     * @return true if this this view instance is the primary view, false otherwise
     */
    public boolean isPrimaryView() {
        return this.getViewSite().getSecondaryId() == null;
    }

    /**
     * Returns whether saved settings exists or not.
     * 
     * @return true if saved settings exist, false otherwise
     */
    public boolean hasSettings() {
        return KlighdViewPlugin.getDefault().getDialogSettings().get(ID) != null;
    }

    /**
     * Saves the settings of this view in the dialog setting.<bR>
     * This should only performed for the primary view.
     */
    private void saveSettings() {
        try {
            XMLMemento memento = XMLMemento.createWriteRoot(ID);
            saveState(memento);
            StringWriter writer = new StringWriter();
            memento.save(writer);
            KlighdViewPlugin.getDefault().getDialogSettings().put(ID, writer.toString());
        } catch (Exception e) {
            StatusManager.getManager().handle(new Status(IStatus.WARNING,
                    KlighdViewPlugin.PLUGIN_ID, "Cannot save view settings", e), StatusManager.LOG);
        }
    }

    /**
     * Loads the settings for this view from the dialog setting.<bR>
     * This should only performed for the primary view.
     */
    private void loadSettings() {
        try {
            IDialogSettings dialogSettings = KlighdViewPlugin.getDefault().getDialogSettings();
            String settingsString = dialogSettings.get(ID);
            if (settingsString != null) {
                loadState(XMLMemento.createReadRoot(new StringReader(settingsString)));
            }
        } catch (Exception e) {
            StatusManager.getManager().handle(new Status(IStatus.WARNING,
                    KlighdViewPlugin.PLUGIN_ID, "Cannot save view settings", e), StatusManager.LOG);
        }
    }

    // -- Editor
    // -------------------------------------------------------------------------

    /**
     * Set associates this view with the given editor and notifies update controller.
     * 
     * @param editor
     *            editor or null to unset
     */
    void setEditor(final IEditorPart newEditor) {
        if (newEditor != null) {
            if (editor != newEditor) {
                // set as active editor
                editor = newEditor;

                updateController();
            }
        } else {
            editor = null;

            updateController();
        }
    }

    /**
     * Returns associated editor.
     * 
     * @return the editor
     */
    public IEditorPart getEditor() {
        return editor;
    }

    /**
     * Returns if this view is linked with the active editor.
     * 
     * @return true if linked
     */
    public boolean isLinkedWithActiveEditor() {
        return linkCheckAction != null && linkCheckAction.isChecked();
    }

    // -- Controller
    // -------------------------------------------------------------------------

    /**
     * Sets the responsible controller for the current editor.
     */
    private void updateController() {
        // If view is initialized
        if (toolBarManager != null && menuManager != null) {

            // Deactivate active editor
            if (controller != null) {
                controller.deactivate();
                controller = null;
                // remove contributions
                toolBarManager.removeAll();
                menuManager.removeAll();
                addContributions();
            }

            // Find new controller
            if (editor != null) {
                String responsibleControllerID =
                        ViewUpdateControllerFactory.getHandlingControllerID(editor);
                if (responsibleControllerID != null) {

                    // search for instantiated responsible controller
                    AbstractViewUpdateController alreadyInstantiatedController =
                            controllers.get(responsibleControllerID);
                    // create controller if necessary
                    if (alreadyInstantiatedController != null) {
                        controller = alreadyInstantiatedController;
                    } else {
                        controller = ViewUpdateControllerFactory
                                .getNewInstance(responsibleControllerID, this);
                        controllers.put(responsibleControllerID, controller);
                    }

                    // Update controller specific contributions
                    controller.addContributions(toolBarManager, menuManager);
                }
            }

            // Update toolbar and menu
            toolBarManager.update(false);
            menuManager.updateAll(false);
            // Update ActionBars important for correct toolbar layout
            getViewSite().getActionBars().updateActionBars();

            // Activate new controller
            if (controller != null) {
                controller.activate(editor);
            } else {
                // Since no controller is active no model message will be displayed
                updateDiagram();
            }
        }
    }

    // -- Synthesis Options
    // -------------------------------------------------------------------------

    /**
     * Stores the current synthesis options configured in the {@link ViewContext}.
     */
    private synchronized void storeCurrentSynthesisOptions() {
        if (this.getViewer() != null && this.getViewer().getViewContext() != null) {
            ViewContext viewContext = this.getViewer().getViewContext();
            HashSet<SynthesisOption> allUsedSyntheisOptions = new HashSet<SynthesisOption>();

            ISynthesis usedRootSynthesis = viewContext.getDiagramSynthesis();

            // Save used syntheses
            usedSyntheses.add(usedRootSynthesis);

            // Find all available synthesis options for the currently used syntheses
            allUsedSyntheisOptions.addAll(usedRootSynthesis.getDisplayedSynthesisOptions());
            for (ViewContext childVC : viewContext.getChildViewContexts(true)) {
                usedSyntheses.add(childVC.getDiagramSynthesis());
                allUsedSyntheisOptions
                        .addAll(childVC.getDiagramSynthesis().getDisplayedSynthesisOptions());
            }

            // Save used options
            for (SynthesisOption option : allUsedSyntheisOptions) {
                recentSynthesisOptions.put(option, viewContext.getOptionValue(option));
            }
        }
    }

    // -- Selection Handling
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        if (controller != null) {
            controller.selectionChanged(event);
        }
    }

    // -- Diagram Update
    // -------------------------------------------------------------------------

    /**
     * Updates the diagram.
     */
    public void updateDiagram() {
        if (controller != null) {
            updateDiagram(controller.getModel(), controller.getProperties());
        } else {
            KlighdSynthesisProperties properties = new KlighdSynthesisProperties();
            if (this.getViewer() != null && this.getViewer().getViewContext() != null) {
                properties.copyProperties(this.getViewContext());
            }
            updateDiagram(null, properties);
        }
    }

    /**
     * Updates displayed diagram in a dedicated job.
     * 
     * @param model
     *            the new model
     * @param properties
     *            the properties
     */
    private void updateDiagram(final Object model, final KlighdSynthesisProperties properties) {
        // Update SynthesisSelection
        synthesisSelection.update(model);

        // Adjust model
        Object displayModel = model;
        if (model == null) {
            if (isLinkedWithActiveEditor()) {
                displayModel = new MessageModel(NO_MODEL_LINKED);
            } else {
                displayModel = new MessageModel(NO_MODEL);
            }
        }
        final Object finalDisplayModel = displayModel;

        // Start update job
        new UIJob(UPDATE_JOB) {

            @Override
            public IStatus runInUIThread(final IProgressMonitor monitor) {
                doUpdateDiagram(finalDisplayModel, properties, controller, editor, false);
                return Status.OK_STATUS;
            }
        }.schedule();
    }

    /**
     * Performs the actual update on the diagram.
     * <p>
     * This method is intended to run in a separate UIJob.
     * 
     * @param model
     *            model to display
     * @param properties
     *            properties for configuration
     * @param usedController
     *            the controller related to this update
     * @param sourceEditor
     *            the editor related to the model
     * @param isErrorModel
     *            true indicates an re-invocation of update to show an error model concerning an
     *            error occurred in the actual update.
     */
    private void doUpdateDiagram(final Object model, final KlighdSynthesisProperties properties,
            final AbstractViewUpdateController usedController, final IEditorPart sourceEditor,
            final boolean isErrorModel) {
        try {
            // No updates if view is disposed
            if (isDisposed()) {
                return;
            }
            
            // Get correct synthesis
            String synthesisID = synthesisSelection.getSynthesis(model);
            if (synthesisID == null) {
                throw new NullPointerException(NO_SYNTHESIS);
            }

            // Set properties
            properties.setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS,
                    synthesisID);

            // Save previous synthesis options to restore later
            storeCurrentSynthesisOptions();

            // configure options
            properties.configureSynthesisOptionValues(recentSynthesisOptions);

            // Indicated if the model type changed against the current model
            boolean modelTypeChanged = false;
            ViewContext viewContext = null;
            if (this.getViewer() == null || this.getViewer().getViewContext() == null) {
                // if viewer or context does not exist always init view
                modelTypeChanged = true;
            } else {
                viewContext = this.getViewer().getViewContext();
                if (viewContext.getInputModel() == null
                        || viewContext.getInputModel().getClass() != model.getClass()) {
                    modelTypeChanged = true;
                }
                if (!KlighdDataManager.getInstance()
                        .getSynthesisID(viewContext.getDiagramSynthesis()).equals(synthesisID)) {
                    // In case the synthesis changed the sidebar should be updated
                    modelTypeChanged = true;
                }
            }

            // --Update diagram--

            // Success flag indicating a successful synthesis and diagram update
            boolean success = false;

            // If the type changed the view must be reinitialized to provide a correct ViewContext
            // otherwise the ViewContext can be simply updated
            if (modelTypeChanged) {

                // the (re)initialization case
                initialize(model, null, properties);
                success = true;

                // Update selection highlighting listener
                synchronizeSelectionCheckAction.run();

                // get newly create ViewContext
                viewContext = this.getViewer().getViewContext();

                // Register editor
                if (editor != null) {
                    viewContext.setSourceWorkbenchPart(editor);
                }

                // reset layout to resolve KISEMA-905
                resetLayoutConfig(false);
            } else {
                boolean hadChildContexts = !viewContext.getChildViewContexts(false).isEmpty();

                // Configure present view context
                viewContext.copyProperties(properties);

                // Register editor
                if (editor != null) {
                    viewContext.setSourceWorkbenchPart(editor);
                }

                // update case (keeps options and sidebar)
                success = LightDiagramServices.updateDiagram(this.getViewer().getViewContext(),
                        model, properties);

                // Update sidebar if the synthesis option changed due to child syntheses
                if (success && (!viewContext.getChildViewContexts(false).isEmpty()
                        || hadChildContexts)) {
                    this.updateOptions(true);
                }
            }

            // check if update really was successful
            KNode currentDiagram = viewContext.getViewModel();
            if (!success || currentDiagram == null
                    || (currentDiagram.getChildren().isEmpty() && !(model instanceof KNode))) {
                throw new NullPointerException(DIAGRAM_IS_NULL);
            } else {
                viewComposite.layout();
            }

            // Notify the controller about the successful update
            if (controller != null) {
                controller.onDiagramUpdate(model, properties);
            }
        } catch (Exception e) {
            if (!isErrorModel) {
                doUpdateDiagram(new ErrorModel(UPDATE_DIAGRAM_EXCEPTION, e), properties,
                        usedController, sourceEditor, true);
            } else {
                StatusManager.getManager().handle(new Status(IStatus.WARNING,
                        KlighdViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e),
                        StatusManager.SHOW);
            }

        }
    }

}
