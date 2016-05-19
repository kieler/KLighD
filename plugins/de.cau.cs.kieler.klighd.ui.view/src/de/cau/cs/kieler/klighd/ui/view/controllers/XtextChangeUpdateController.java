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
package de.cau.cs.kieler.klighd.ui.view.controllers;

import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorPart;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;

import de.cau.cs.kieler.klighd.ui.view.controller.AbstractViewUpdateController;

/**
 * Simple controller for XText editors which performs a diagram update when the model is changed.
 * 
 * @author als
 * @kieler.design 2015-09-30 proposed
 * @kieler.rating 2015-09-30 proposed yellow
 *
 */
public class XtextChangeUpdateController extends AbstractViewUpdateController
        implements XtextEditorModelChangeAdapter.XtextChangeListener {

    /** Controller ID. */
    private static final String ID =
            "de.cau.cs.kieler.klighd.ui.view.controllers.XtextChangeUpdateController";
    
    // CHECKSTYLEOFF VisibilityModifier
    /** The safe listener for the editor. */
    protected final XtextEditorModelChangeAdapter changeAdapter;

    /**
     * Default Constructor.
     */
    public XtextChangeUpdateController() {
        changeAdapter = new XtextEditorModelChangeAdapter(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getID() {
        return ID;
    }

    // -- Activation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActivate(final IEditorPart editor) {
        if (editor instanceof XtextEditor) {
            updateModel(EditorUtil.readModelFromXtextEditor((XtextEditor) editor));
            changeAdapter.activate((XtextEditor) editor);
        } else {
            throw new IllegalArgumentException(XtextChangeUpdateController.class.getName()
                    + "cannot handle editor inputs other than XtextEditor");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDeactivate() {
        changeAdapter.deactivate();
    }

    // -- Diagram Selection Change Event
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        XtextSelectionHighlighter.highlightSelection((XtextEditor) getEditor(),
                event.getSelection());
    }

    // -- Model change Listener
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void onModelChanged(final XtextEditor editor, final XtextResource resource) {
        updateModel(EditorUtil.readModelFromXtextEditor(editor));
    }

}
