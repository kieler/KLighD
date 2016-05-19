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

import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtext.ui.editor.XtextEditor;

import de.cau.cs.kieler.klighd.ui.view.DiagramView;
import de.cau.cs.kieler.klighd.ui.view.KlighdViewPlugin;

/**
 * Manages the different {@link AbstractViewUpdateController} instances for the {@link DiagramView}
 * and provides correct instantiation.
 * <p>
 * This class evaluates the controller extension point.
 * <p>
 * This class is a singleton.
 * 
 * @author als
 * @kieler.design 2015-06-22 proposed
 * @kieler.rating 2015-06-22 proposed yellow
 *
 */
public final class ViewUpdateControllerFactory {

    // -- Extension Point CONSTANTS --
    /** Identifier of the extension point for controllers. */
    public static final String EXTP_ID_CONTROLLER = "de.cau.cs.kieler.klighd.ui.view.controller";

    /** Identifier of the extension point for controllers. */
    public static final String EXTP_ID_EDITOR = "de.cau.cs.kieler.klighd.ui.view.editor";

    /** Name of the 'controller' element. */
    private static final String ELEMENT_CONTROLLER = "controller";

    /** Name of the 'editor' element. */
    private static final String ELEMENT_EDITOR = "editor";

    /** Name of the 'class' attribute in the extension points. */
    private static final String ATTRIBUTE_CLASS = "class";

    /** Name of the 'id' attribute in the extension points. */
    private static final String ATTRIBUTE_EDITOR = "editorID";

    /** Name of the 'id' attribute in the extension points. */
    private static final String ATTRIBUTE_CONTROLLER = "controllerID";

    // -- SINGLETON --

    /** The singleton instance. */
    private static ViewUpdateControllerFactory instance;

    /**
     * Creates the singleton and initializes it with the data from the extension point.
     */
    static {
        instance = new ViewUpdateControllerFactory();
        // load the data from the extension points
        try {
            instance.loadControllerExtension();
            instance.loadEditorExtension();
            // Set default editor
            instance.defaultControllerMapping.put(IEditingDomainProvider.class,
                    "de.cau.cs.kieler.klighd.ui.view.controllers.EcoreXtextSaveUpdateController");
            instance.defaultControllerMapping.put(XtextEditor.class,
                    "de.cau.cs.kieler.klighd.ui.view.controllers.EcoreXtextSaveUpdateController");
        } catch (final Exception e) {
            StatusManager.getManager()
                    .handle(new Status(IStatus.ERROR, KlighdViewPlugin.PLUGIN_ID,
                            ViewUpdateControllerFactory.class.getName()
                                    + ": Unexptected failure while loading registered controllers.",
                            e));
        }
    }

    /**
     * Returns the singleton instance.
     *
     * @return the singleton
     */
    public static ViewUpdateControllerFactory getInstance() {
        return instance;
    }

    /**
     * A private constructor to prevent instantiation.
     */
    private ViewUpdateControllerFactory() {
    }

    // -- ATTRIBUES --
    /**
     * The mapping of IDs to the corresponding classes of {@link AbstractViewUpdateController} .
     */
    private final HashMap<String, Class<? extends AbstractViewUpdateController>> idControllerMapping =
            new HashMap<String, Class<? extends AbstractViewUpdateController>>();
    /** The mapping of editor-IDs to the corresponding controller IDs. */
    private final HashMap<String, String> editorControllerMapping = new HashMap<String, String>();
    /** The mapping of editor classes to the corresponding default controller IDs. */
    private final HashMap<Class<?>, String> defaultControllerMapping = new HashMap<Class<?>, String>();
    
    // -- Extension Point Parsing
    // -------------------------------------------------------------------------

    /**
     * Loads the registered {@link AbstractViewUpdateController} from the extension point and builds
     * up the {@link #controllerClasses}.
     */
    private void loadControllerExtension() {
        final IConfigurationElement[] extensions =
                Platform.getExtensionRegistry().getConfigurationElementsFor(EXTP_ID_CONTROLLER);

        for (final IConfigurationElement element : extensions) {
            if (ELEMENT_CONTROLLER.equals(element.getName())) {
                // initialize controller class from the extension point
                try {
                    AbstractViewUpdateController controller = (AbstractViewUpdateController) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (controller == null) {
                        throw new NullPointerException("Cannot retrive id or class");
                    }
                    idControllerMapping.put(controller.getID(), controller.getClass());
                } catch (final Exception exception) {
                    StatusManager.getManager()
                            .handle(new Status(IStatus.ERROR, KlighdViewPlugin.PLUGIN_ID,
                                    this.getClass().getName()
                                            + ": Error while parsing controller extension point",
                                    exception));
                }
            }
        }
    }

    /**
     * Loads the registered editor association from the extension point and builds up the
     * {@link #editorControllerMapping}.
     */
    private void loadEditorExtension() {
        final IConfigurationElement[] extensions =
                Platform.getExtensionRegistry().getConfigurationElementsFor(EXTP_ID_EDITOR);

        for (final IConfigurationElement element : extensions) {
            if (ELEMENT_EDITOR.equals(element.getName())) {
                // initialize controller classes from the extension point
                try {
                    String editor = element.getAttribute(ATTRIBUTE_EDITOR);
                    String controller = element.getAttribute(ATTRIBUTE_CONTROLLER);
                    if (editor == null || controller == null) {
                        throw new NullPointerException("Cannot retrive id or class");
                    }
                    if (idControllerMapping.containsKey(controller)) {
                        editorControllerMapping.put(editor, controller);
                    } else {
                        // Log error if controller id is not registered
                        StatusManager.getManager().handle(
                                new Status(IStatus.ERROR, KlighdViewPlugin.PLUGIN_ID,
                                        this.getClass().getName() + ": Given controller id ["
                                                + controller + "] is not registered"),
                                StatusManager.LOG);
                    }
                } catch (final Exception exception) {
                    StatusManager.getManager()
                            .handle(new Status(IStatus.ERROR, KlighdViewPlugin.PLUGIN_ID,
                                    this.getClass().getName()
                                            + ": Error while parsing editor extension point",
                                    exception));
                }
            }
        }
    }

    // -- Controller Access
    // -------------------------------------------------------------------------

    /**
     * Indicates if the given editor has a handling controller and thus should be displayed in a
     * {@link DiagramView}.
     * 
     * @param editor
     *            The EditorPart
     * @return true if controller is associated otherwise false
     */
    public static boolean isHandledEditor(final IEditorPart editor) {
        if (editor != null) {
            // Test if any controller is registered for the editor ID
            if (ViewUpdateControllerFactory.getInstance().editorControllerMapping
                    .containsKey(editor.getEditorSite().getId())) {
                return true;
            } else {
                // Test if any default controller can handle this editor
                for (Class<?> supportedEditorClass : ViewUpdateControllerFactory
                        .getInstance().defaultControllerMapping.keySet()) {
                    if (supportedEditorClass.isInstance(editor)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns the ID of the controller responsible for handling the given editor.
     * 
     * @param editor
     *            The EditorPart
     * @return the controller id or null if no controller is associated
     */
    public static String getHandlingControllerID(final IEditorPart editor) {
        if (editor != null) {
            // Find controller registered for the editor ID
            String id = ViewUpdateControllerFactory.getInstance().editorControllerMapping
                    .get(editor.getEditorSite().getId());
            if (id == null) {
                // Test if any default controller can handle this editor
                for (Entry<Class<?>, String> editorClassIdEntry : ViewUpdateControllerFactory
                        .getInstance().defaultControllerMapping.entrySet()) {
                    if (editorClassIdEntry.getKey().isInstance(editor)) {
                        return editorClassIdEntry.getValue();
                    }
                }
            } else {
                return id;
            }
        }
        return null;
    }

    /**
     * Creates an instance of the controller with the given id for the given {@link DiagramView}.
     * 
     * @param controllerID
     *            The ID of the controller
     * @param diagramView
     *            The {@link DiagramView}
     * @return The new instance of null if the controller does not exist
     */
    public static AbstractViewUpdateController getNewInstance(final String controllerID,
            final DiagramView diagramView) {
        if (controllerID != null && diagramView != null) {
            ViewUpdateControllerFactory mucm = ViewUpdateControllerFactory.getInstance();
            Class<? extends AbstractViewUpdateController> clazz =
                    mucm.idControllerMapping.get(controllerID);
            if (clazz != null) {
                try {
                    AbstractViewUpdateController newInstance = clazz.newInstance();
                    newInstance.initialize(diagramView);
                    return newInstance;
                } catch (Exception e) {
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, KlighdViewPlugin.PLUGIN_ID,
                                    ViewUpdateControllerFactory.class.getName()
                                            + ": Cannot instanciate controller",
                                    e),
                            StatusManager.LOG);
                }
            }
        }
        return null;
    }

}
