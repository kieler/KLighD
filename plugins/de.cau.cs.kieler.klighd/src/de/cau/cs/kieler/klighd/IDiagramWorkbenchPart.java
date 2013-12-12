/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * Interface for workbench parts implemented by KLighD.
 *
 * @author msp
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
     * Returns the viewer associated with this workbench part.
     * 
     * @return the viewer
     */
    ContextViewer getContextViewer();
}
