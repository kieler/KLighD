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
package de.cau.cs.kieler.klighd.ui.view.menu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IMemento;

import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.internal.ISynthesis;
import de.cau.cs.kieler.klighd.ui.view.DiagramView;
import de.cau.cs.kieler.klighd.ui.view.controller.ViewUpdateControllerFactory;

/**
 * This sub-menu handles different available synthesis for models including general syntheses
 * implementing {@link ISelectableGeneralSynthesis}.
 * <p>
 * {@link ISelectableGeneralSynthesis} are defined in extension point evaluated by
 * {@link ViewUpdateControllerFactory}
 * 
 * @author als
 * @kieler.design 2015-06-22 proposed
 * @kieler.rating 2015-06-22 proposed yellow
 *
 */
@SuppressWarnings("restriction")
public class SynthesisSelectionMenu extends MenuManager {

    /**
     * Map of all selections made until now.
     * <p>
     * The model classes are saved as class name because this menu should be saved in a memento.
     */
    private final HashMap<String, String> selections = new HashMap<String, String>();

    /** Class of the model currently associated with the menu configuration. */
    private Class<? extends Object> modelClass;

    /** The related {@link DiagramView}. */
    private final DiagramView diagramView;

    // -- Constructor
    // -------------------------------------------------------------------------

    /**
     * Default Constructor.
     * 
     * @param diagramView
     *            The parent diagram view of this menu.
     */
    public SynthesisSelectionMenu(final DiagramView diagramView) {
        this.diagramView = diagramView;
        this.setMenuText("Syntheses");
    }

    // -- State
    // -------------------------------------------------------------------------

    /**
     * Copies the internal state of another {@link SynthesisSelectionMenu}.
     * 
     * @param source
     *            the {@link SynthesisSelectionMenu} to copy from
     */
    public void copy(final SynthesisSelectionMenu source) {
        selections.putAll(source.selections);
    }

    /**
     * Clears all made selections.
     */
    public void clear() {
        selections.clear();
    }

    /**
     * Saves the current state into a memento.
     * 
     * @param memento
     *            the memento
     */
    public void saveState(final IMemento memento) {
        for (Entry<String, String> entry : selections.entrySet()) {
            memento.putString(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Loads saved state form a memento.
     * 
     * @param memento
     *            the memento
     */
    public void loadState(final IMemento memento) {
        for (String key : memento.getAttributeKeys()) {
            selections.put(key, memento.getString(key));
        }
    }

    // -- Menu Update
    // -------------------------------------------------------------------------

    /**
     * Updates this menu according to given model.
     * 
     * @param model
     *            The model
     */
    public void update(final Object model) {
        if (model != null) {
            KlighdDataManager kdm = KlighdDataManager.getInstance();
            Class<? extends Object> newModelClass = model.getClass();
            String newModelClassName = newModelClass.getCanonicalName();
            if (!newModelClass.equals(modelClass)) {
                // If model type changed
                removeAll();

                LinkedHashMap<String, Action> actionMap = new LinkedHashMap<String, Action>();

                // Add model specific synthesis
                for (ISynthesis synthesis : kdm.getAvailableSyntheses(newModelClass)) {
                    String id = kdm.getSynthesisID(synthesis);
                    actionMap.put(id, createAction(newModelClassName, id));
                }

                // Set selected synthesis item checked
                if (getItems().length > 0) {
                    String selectedID = selections.get(newModelClassName);
                    if (selectedID != null) {
                        actionMap.get(selections.get(newModelClassName)).setChecked(true);
                    } else {
                        String id = kdm.getSynthesisID(
                                kdm.getAvailableSyntheses(newModelClass).iterator().next());
                        actionMap.get(id).setChecked(true);
                        selections.put(newModelClassName, id);
                    }
                }
                this.updateAll(false);
            }
            modelClass = newModelClass;
        } else {
            removeAll();
            modelClass = null;
        }
    }

    /**
     * Creates a new action item and adds it to this menu.
     * <p>
     * The action selects the given is as synthesis for the given class.
     * 
     * @param className
     *            the class name this item chooses for
     * @param id
     *            the synthesis id to set
     * @return the created action
     */
    private Action createAction(final String className, final String id) {
        // Parse name from id
        String name = id;
        if (id.contains(".") && !id.endsWith(".")) {
            name = id.substring(id.lastIndexOf('.') + 1);
        }
        // create item
        Action action = new Action(name, IAction.AS_RADIO_BUTTON) {

            @Override
            public void run() {
                if (isChecked()) {
                    selections.put(className, id);
                    diagramView.updateDiagram();
                }
            }
        };
        action.setToolTipText(id);

        // Add new action to this menu
        add(action);
        return action;
    }

    // -- Synthesis
    // -------------------------------------------------------------------------

    /**
     * Returns the ID of synthesis which should be used for the given model.
     * 
     * @param model
     *            the model
     * @return The synthesis ID or null if no matching synthesis is available
     */
    public String getSynthesis(final Object model) {
        if (model != null) {
            String modelClassName = model.getClass().getCanonicalName();
            if (selections.containsKey(modelClassName)) {
                return selections.get(modelClassName);
            } else {
                // In case of no selection take first available synthesis
                KlighdDataManager kdm = KlighdDataManager.getInstance();
                Iterable<ISynthesis> availableSyntheses =
                        kdm.getAvailableSyntheses(model.getClass());
                if (availableSyntheses != null) {
                    Iterator<ISynthesis> iterator = availableSyntheses.iterator();
                    if (iterator.hasNext()) {
                        return kdm.getSynthesisID(iterator.next());
                    }
                }
            }
        }
        return null;
    }

}
