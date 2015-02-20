/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo;

import org.eclipse.emf.ecore.EObject;

/**
 * Common interface of all KLighD-specific {@link edu.umd.cs.piccolo.PNode PNodes} representing view
 * model elements.
 *
 * @author chsch
 */
public interface IKlighdNode {

    /**
     * @return the view model element ({@link de.cau.cs.kieler.core.kgraph.KGraphElement
     *         KGraphElement} or {@link de.cau.cs.kieler.core.krendering.KRendering KRendering})
     *         this {@link IKlighdNode} corresponds to.
     */
    EObject getViewModelElement();

    /**
     * Provides the permission of the corresponding
     * {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement}/
     * {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} to be selected.
     *
     * @return <code>true</code> if the corresponding
     *         {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement}/
     *         {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} is allowed to be
     *         selected, <code>false</code> otherwise.
     */
    boolean isSelectable();

}
