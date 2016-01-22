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

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPartConstants;

/**
 * An adapter to listen for the save event of an editor.
 * 
 * @author als
 * @kieler.design 2015-09-29 proposed
 * @kieler.rating 2015-09-29 proposed yellow
 *
 */
public class EditorSaveAdapter implements IPropertyListener {

    /**
     * The listener interface for notifications of the {@link EditorSaveAdapter}.
     * 
     * @author als
     */
    public interface EditorSafeListener {
        /**
         * The event handler for the editor save event.
         * 
         * @param editor
         *            the saved editor
         */
        void onEditorSaved(IEditorPart editor);
    }

    /** The listener to be notified by this adapter. */
    private EditorSafeListener listener;
    /** The current editor this adapter is listening to. */
    private IEditorPart editor;

    /**
     * Creates an adapter for the given listener. The adapter must be activated on an editor to
     * start fire events.
     * 
     * @param listener
     *            the listener listening for events of this adapter
     */
    public EditorSaveAdapter(final EditorSafeListener listener) {
        this.listener = listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChanged(final Object source, final int propId) {
        if (editor != null) {
            IEditorPart sourceEditor = (IEditorPart) source;
            if (propId == IWorkbenchPartConstants.PROP_DIRTY && !sourceEditor.isDirty()) {
                // dirty flag changed and editor is not dirty -> saved
                listener.onEditorSaved(sourceEditor);
            }
        }
    }

    /**
     * Starts listening on the given editor.
     *
     * @param newEditor
     *            the editor
     */
    public void activate(final IEditorPart newEditor) {
        if (newEditor != null) {
            if (this.editor != null) {
                this.editor.removePropertyListener(this);
            }
            this.editor = newEditor;
            newEditor.addPropertyListener(this);
        }
    }

    /**
     * Stops the listening on the current editor.
     */
    public void deactivate() {
        if (editor != null) {
            editor.removePropertyListener(this);
        }
    }

}
