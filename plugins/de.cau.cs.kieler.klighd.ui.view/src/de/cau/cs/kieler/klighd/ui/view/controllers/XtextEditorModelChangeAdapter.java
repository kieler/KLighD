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

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextModelListener;

/**
 * An adapter to listen for the change event of an xtext editor.
 * 
 * @author als
 * @kieler.design 2015-09-29 proposed
 * @kieler.rating 2015-09-29 proposed yellow
 *
 */
public class XtextEditorModelChangeAdapter implements IXtextModelListener {

    /**
     * The listener interface for notifications of the {@link XtextEditorModelChangeAdapter}.
     * 
     * @author als
     */
    public interface XtextChangeListener {
        /**
         * The event handler for the editor change event.
         * 
         * @param editor
         *            the editor of the changed model
         * @param resource
         *            the models resource
         */
        void onModelChanged(final XtextEditor editor, final XtextResource resource);
    }

    /** The listener to be notified by this adapter. */
    private XtextChangeListener listener;
    /** The current editor this adapter is listening to. */
    private XtextEditor editor;

    /**
     * Creates an adapter for the given listener. The adapter must be activated on an editor to
     * start fire events.
     * 
     * @param listener
     *            the listener listening for events of this adapter
     */
    public XtextEditorModelChangeAdapter(final XtextChangeListener listener) {
        this.listener = listener;
    }

    /**
     * Starts listening on the given editor.
     *
     * @param newEditor
     *            the editor
     */
    public void activate(final XtextEditor newEditor) {
        if (newEditor != null) {
            this.editor = newEditor;
            newEditor.getDocument().addModelListener(this);
        }
    }

    /**
     * Stops the listening on the current editor.
     */
    public void deactivate() {
        if (editor != null) {
            editor.getDocument().removeModelListener(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modelChanged(final XtextResource resource) {
        if (!EditorUtil.hasErrorMarkers(resource) && editor != null) {
            listener.onModelChanged(editor, resource);
        }
    }
}
