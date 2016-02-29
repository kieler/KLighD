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

import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;

import de.cau.cs.kieler.klighd.ui.view.DiagramView;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;

/**
 * 
 * Abstract controller for the {@link DiagramView}.
 * <p>
 * The controller handles the update of the displayed model.
 * 
 * @author als
 * @kieler.design 2015-06-29 proposed
 * @kieler.rating 2015-06-29 proposed yellow
 *
 */
// CHECKSTYLEOFF HiddenField
public abstract class AbstractViewUpdateController implements ISelectionChangedListener {

    /** The related {@link DiagramView}. */
    private DiagramView diagramView;
    /** Indicated if this controller is active and should update the {@link DiagramView}. */
    private IEditorPart editor;
    /** The current model. */
    private Object model;
    /** The current properties. */
    private KlighdSynthesisProperties properties;
    
    /**
     * Default Constructor.
     * <p>
     * All extending classes need a 0-argument constructor for correct instantiation.
     */
    public AbstractViewUpdateController() {
    }

    /**
     * Initializes this controller. This method is called once by the
     * {@link ViewUpdateControllerFactory} and must not be call again afterwards.
     * 
     * @param diagramView
     *            the {@link DiagramView}}
     */
    public void initialize(final DiagramView diagramView) {
        if (diagramView == null) {
            throw new IllegalArgumentException("Cannot initialze Controller without View");
        }
        if (this.diagramView != null) {
            throw new IllegalStateException("Controller is already initialized");
        }
        this.diagramView = diagramView;
    }

    /**
     * Returns the ID of this controller.
     * <p>
     * This must be consistent to the ID used in the registration in the extension point.
     * 
     * @return the ID
     */
    public abstract String getID();

    // -- Initialization
    // -------------------------------------------------------------------------

    /**
     * Copies all preferences from the source controller into this controller.
     * 
     * @param source
     *            the source controller
     */
    public abstract void copy(final AbstractViewUpdateController source);

    /**
     * Resets all properties to default values.
     */
    public abstract void reset();

    /**
     * Saves configuration into a memento.
     * 
     * @param memento
     *            configuration store
     */
    public abstract void saveState(IMemento memento);

    /**
     * Loads saved configuration form a memento.
     * 
     * @param memento
     *            saved configuration
     */
    public abstract void loadState(IMemento memento);

    // -- Activation
    // -------------------------------------------------------------------------

    /**
     * Activates this controller for the given {@link IEditorPart}.
     * 
     * @param editor
     *            the associated editor
     */
    public final void activate(final IEditorPart editor) {
        if (this.diagramView == null) {
            throw new IllegalStateException("Controller is not initialized");
        }
        if (editor != null) {
            this.editor = editor;
            onActivate(editor);
        } else {
            throw new NullPointerException("Cannot activate UpdateController without editor");
        }
    }

    /**
     * Deactivates this controller.
     */
    public final void deactivate() {
        onDeactivate();
        this.editor = null;
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
     * Returns is this controller is currently active and can update die {@link DiagramView}.
     * 
     * @return active state
     */
    public final boolean isActive() {
        return editor != null;
    }

    // -- Update
    // -------------------------------------------------------------------------

    /**
     * Perform an update of the {@link DiagramView} with the update model and properties.
     * 
     * @param model
     *            the model may be null to show no model
     * @param properties
     *            the properties for the synthesis of the model, may be to use standard
     *            configuration
     */
    protected final void updateModel(final Object model,
            final KlighdSynthesisProperties properties) {
        this.model = model;
        this.properties = properties;
        diagramView.updateDiagram();
    }

    /**
     * @see updateModel(Object model, KlighdSynthesisProperties properties)
     * 
     * @param model
     *            the model may be null to show no model
     */
    protected final void updateModel(final Object model) {
        updateModel(model, properties);
    }

    /**
     * The updated model.
     * 
     * @return the model or null if no model available
     */
    public final Object getModel() {
        return model;
    }

    /**
     * The synthesis properties to use when displaying the model. Returns never null.
     * 
     * @return the properties
     */
    public final KlighdSynthesisProperties getProperties() {
        if (properties != null) {
            return properties;
        } else {
            return new KlighdSynthesisProperties();
        }
    }

    /**
     * Returns the layout configuration for the diagram.
     * 
     * @return configuration or null
     */
    public LayoutConfigurator getLayoutConfig() {
        return null;
    }

    // -- Events
    // -------------------------------------------------------------------------

    /**
     * Invoked when the {@link DiagramView} finished updating the displayed diagram.
     * 
     * @param model
     *            displayed model
     * @param properties
     *            used properties
     */
    public abstract void onDiagramUpdate(Object model, KlighdSynthesisProperties properties);

    /**
     * Invoked when the related {@link DiagramView} is disposed.
     */
    public abstract void onDispose();

    // -- View
    // -------------------------------------------------------------------------

    /**
     * Adds the controller related actions to the menu and toolbar.
     * 
     * @param toolBar
     *            the Toolbar
     * @param menu
     *            the Menu
     */
    public abstract void addContributions(IToolBarManager toolBar, IMenuManager menu);

    // -- Getter
    // -------------------------------------------------------------------------

    /**
     * Returns the related editor.
     * 
     * @return The related editor or null if this controller is not active
     */
    public IEditorPart getEditor() {
        return editor;
    }

    /**
     * Returns the {@link DiagramView} of this controller.
     * 
     * @return The related {@link DiagramView}
     */
    public DiagramView getDiagramView() {
        return diagramView;
    }

}
