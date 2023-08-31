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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
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
        if (getDiagramView().isLinkedWithActiveEditor()) {
            updateModel(EditorUtil.readModelFromXtextEditor(editor));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        updateModel(EditorUtil.readModelFromXtextEditor((XtextEditor) getEditor()));
    }

}
