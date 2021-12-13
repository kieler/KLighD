/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
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
package de.cau.cs.kieler.klighd;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Interface for workbench parts implemented by KLighD.
 *
 * @author msp
 * @author chsch
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public interface IDiagramWorkbenchPart extends IWorkbenchPart {

    /**
     * Returns the id associated with this {@link IDiagramWorkbenchPart}.<br>
     * If <code>this</code> part is a {@link org.eclipse.ui.part.ViewPart ViewPart}
     * this method returns the view's secondary id.
     *
     * @return the id associated with this {@link IDiagramWorkbenchPart}.
     */
    String getPartId();

    /**
     * Returns the viewer associated with this diagram workbench part.
     *
     * @return the viewer
     */
    IViewer getViewer();

    /**
     * Returns the {@link ViewContext} associated with this diagram workbench part.
     *
     * @return the associated {@link ViewContext}
     */
    ViewContext getViewContext();

    /**
     * Extended interface for editor parts implemented by KLighD.
     */
    public interface IDiagramEditorPart extends IDiagramWorkbenchPart, IEditorPart {

        /**
         * Set the dirty status of the editor.
         *
         * @param dirty the new dirty status
         */
        void setDirty(boolean dirty);
    }
}
