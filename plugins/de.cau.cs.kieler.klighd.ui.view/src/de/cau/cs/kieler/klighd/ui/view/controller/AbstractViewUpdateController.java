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
package de.cau.cs.kieler.klighd.ui.view.controller;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;

import de.cau.cs.kieler.klighd.ui.view.DiagramView;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;

/**
 * 
 * The controller handles the update of the displayed model.
 * <p>
 * The controller is supposed to connect the {@link DiagramView} to a specific editor type.
 * Primarily the controller should listen for changes in the editor, retrieve the editor's content,
 * translate it into a model the the {@link DiagramView} can synthesize a diagram from and deliver
 * it to the {@link DiagramView} by calling {@link AbstractViewUpdateController#updateModel(Object)}
 * <p>
 * A controller must be registered via the extension point:
 * de.cau.cs.kieler.klighd.ui.view.controller<br>
 * To associate a specific editor with the controller, the editor ID must be registered in the
 * extension point: de.cau.cs.kieler.klighd.ui.view.editor<br>
 * It is also possible to register a controller as fallback controller for a class of editors but the
 * association with an specific editor ID will always be more dominant.
 * <p>
 * The implementation of a controller must provide an ID via {@link #getID()} and react on
 * {@link #onActivate(IEditorPart)} by adding some kind of change listener to the editor and calling
 * {@link #updateModel(Object)} to show the current model. On all further changes which should cause
 * an updated of the diagram, the controller should call {@link #updateModel(Object)}, iff
 * {@link DiagramView#isLinkedWithActiveEditor()} returns true, otherwise the controller must
 * preserve the model when the view was last linked and do not update the diagram. When
 * {@link #onDeactivate()} is invoked the controller should stop listening to the editor and wait
 * until its reactivation. All invocations of {@link #updateModel(Object)} will be ignored. When the
 * controller is ordered to {@link #refresh()} it should reload the model form the editor and invoke
 * {@link #updateModel(Object)}, ignoring {@link DiagramView#isLinkedWithActiveEditor()} state.
 * <p>
 * The controller can override {@link #addContributions(IToolBarManager, IMenuManager)} to provide
 * editor specific items to the menu or toolbar. <br>
 * If the controller overrides {@link #selectionChanged(SelectionChangedEvent)} it can react to
 * changes in the selection of diagram elements. <br>
 * If the controller wants to react to the actual update of the diagram it should override
 * {@link #onDiagramUpdate(Object, KlighdSynthesisProperties)}.
 * <p>
 * If the controller has an internal state influencing its behavior, the controller should override
 * the following methods:
 * <li>{@link #saveState(IMemento)}</li>
 * <li>{@link #loadState(IMemento)}</li>
 * <li>{@link #reset()}</li>
 * <li>{@link #copy(AbstractViewUpdateController)}</li>
 * </ul>
 * 
 * @author als
 * @kieler.design 2016-06-28 KI-124 uru, nbw, cds
 * @kieler.rating 2015-06-29 proposed yellow
 *
 */
public abstract class AbstractViewUpdateController implements ISelectionChangedListener {

    /** The related {@link DiagramView}. */
    private DiagramView diagramView;
    /**
     * The editor this controller is activated for. Also indicates if this controller is active and
     * should update the {@link DiagramView}.
     */
    private IEditorPart activeEditor;
    /** The current model. */
    private Object currentModel;
    /** The current properties. */
    private KlighdSynthesisProperties currentProperties;

    /**
     * Default Constructor.
     * <p>
     * All extending classes need a 0-argument constructor for correct instantiation.
     */
    public AbstractViewUpdateController() {
    }

    /**
     * Initializes this controller. This method is called once by the
     * {@link ViewUpdateControllerFactory} and must not be invoked again afterwards.
     * 
     * @param parentDiagramView
     *            the {@link DiagramView}}
     */
    public void initialize(final DiagramView parentDiagramView) {
        if (parentDiagramView == null) {
            throw new IllegalArgumentException("Cannot initialze Controller without View");
        }
        if (diagramView != null) {
            throw new IllegalStateException("Controller is already initialized");
        }
        diagramView = parentDiagramView;
    }

    /**
     * Returns the ID of this controller.
     * <p>
     * This must be consistent with the ID used in the registration in the extension point.
     * 
     * @return the ID
     */
    public abstract String getID();

    // -- Activation
    // -------------------------------------------------------------------------

    /**
     * Activates this controller for the given {@link IEditorPart}.
     * 
     * @param editor
     *            the associated editor
     */
    public final void activate(final IEditorPart editor) {
        if (diagramView == null) {
            throw new IllegalStateException("Controller is not initialized");
        }
        if (editor == null) {
            throw new NullPointerException("Cannot activate UpdateController without editor");
        }
        activeEditor = editor;
        onActivate(editor);
    }

    /**
     * Deactivates this controller.
     */
    public final void deactivate() {
        onDeactivate();
        activeEditor = null;
    }

    /**
     * Invoked when the controller is activated for as specific {@link IEditorPart}. A controller
     * will not be activated twice without a deactivation in between.
     * <p>
     * Usually the controller should read the model form the new editor and update the
     * {@link DiagramView}.
     * 
     * @param editor
     *            new editor
     */
    public abstract void onActivate(IEditorPart editor);

    /**
     * Invoked when the controller is deactivated. This method may be invoked multiple times without
     * activation in between.
     * <p>
     * Usually the controller should stop listening to the editor and free all related resources.
     */
    public abstract void onDeactivate();

    /**
     * Returns true if this controller is currently active and can update the {@link DiagramView},
     * false otherwise.
     * 
     * @return active state
     */
    public final boolean isActive() {
        return activeEditor != null;
    }

    // -- Update
    // -------------------------------------------------------------------------

    /**
     * Performs an update of the {@link DiagramView} with the given model and properties.
     * 
     * @param model
     *            the model, may be null to show no model
     * @param properties
     *            the properties for the synthesis of the model, may be null to use standard
     *            configuration
     */
    protected final void updateModel(final Object model,
            final KlighdSynthesisProperties properties) {
        currentModel = model;
        currentProperties = properties;
        diagramView.updateDiagram();
    }

    /**
     * @see updateModel(Object model, KlighdSynthesisProperties properties)
     * 
     * @param model
     *            the model may be null to show no model
     */
    protected final void updateModel(final Object model) {
        updateModel(model, currentProperties);
    }

    /**
     * The updated model.
     * 
     * @return the model or null if no model available
     */
    public final Object getModel() {
        return currentModel;
    }

    /**
     * The synthesis properties to use when displaying the model.
     * 
     * @return the properties, never null
     */
    public final KlighdSynthesisProperties getProperties() {
        if (currentProperties != null) {
            return currentProperties;
        } else {
            return new KlighdSynthesisProperties();
        }
    }
    
    /**
     * Reloads the model from the editor and updates the diagram.
     */
    public abstract void refresh();

    // -- Diagram View Callbacks
    // -------------------------------------------------------------------------

    /**
     * Adds the controller related actions to the menu and toolbar.
     * 
     * @param toolBar
     *            the toolbar
     * @param menu
     *            the menu
     */
    public void addContributions(final IToolBarManager toolBar, final IMenuManager menu) {
    }

    /**
     * Invoked when the related {@link DiagramView} is disposed.
     */
    public void onDispose() {
    }

    /**
     * Invoked when the {@link DiagramView} finished updating the displayed diagram.
     * 
     * @param model
     *            displayed model
     * @param properties
     *            used properties
     */
    public void onDiagramUpdate(final Object model, final KlighdSynthesisProperties properties) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
    }

    // -- Internal state
    // -------------------------------------------------------------------------

    /**
     * Copies the internal state from the source controller into this controller.
     * 
     * @param source
     *            the source controller
     */
    public void copy(final AbstractViewUpdateController source) {
    }

    /**
     * Resets the internal state to default values.
     */
    public void reset() {
    }

    /**
     * Saves configuration into a memento.
     * 
     * @param memento
     *            configuration store
     */
    public void saveState(final IMemento memento) {
    }

    /**
     * Loads saved configuration form a memento.
     * 
     * @param memento
     *            saved configuration
     */
    public void loadState(final IMemento memento) {
    }

    // -- Getter
    // -------------------------------------------------------------------------

    /**
     * Returns the related editor.
     * 
     * @return The related editor or null if this controller is not active
     */
    public IEditorPart getEditor() {
        return activeEditor;
    }

    /**
     * Returns the {@link DiagramView} of this controller.
     * 
     * @return The related {@link DiagramView}, never null
     */
    public DiagramView getDiagramView() {
        return diagramView;
    }

}
