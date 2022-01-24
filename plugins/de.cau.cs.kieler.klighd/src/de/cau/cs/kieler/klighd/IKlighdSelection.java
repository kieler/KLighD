/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
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

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

/**
 * A common interface of KLighD-specific implementations of {@link ISelection}.
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
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
     * {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElements} and
     * {@link de.cau.cs.kieler.klighd.krendering.KText KTexts}.
     * 
     * @return an {@link Iterator} of the selected
     *         {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElements} and
     *         {@link de.cau.cs.kieler.klighd.krendering.KText KTexts}
     */
    Iterator<EObject> diagramElementsIterator();
}
