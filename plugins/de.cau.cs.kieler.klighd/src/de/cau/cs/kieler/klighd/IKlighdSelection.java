/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

/**
 * A common interface of KLighD-specific implementations of {@link ISelection}.
 * 
 * @author chsch
 */
public interface IKlighdSelection extends ISelection {

    /**
     * Provides the {@link IViewer} of the corresponding diagram.
     *   
     * @return the {@link IViewer} of the corresponding diagram.
     */
    IViewer getViewer();

    /**
     * Provides the {@link ViewContext} of corresponding diagram.
     * 
     * @return the {@link ViewContext} of corresponding diagram
     */
    ViewContext getViewContext();

    /**
     * Provides an {@link Iterator} of the selected
     * {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElements} and
     * {@link de.cau.cs.kieler.core.krendering.KText KTexts}.
     * 
     * @return an {@link Iterator} of the selected
     *         {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElements} and
     *         {@link de.cau.cs.kieler.core.krendering.KText KTexts}
     */
    Iterator<EObject> diagramElementsIterator();
}
