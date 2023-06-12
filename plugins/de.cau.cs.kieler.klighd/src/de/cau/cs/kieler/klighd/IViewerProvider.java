/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
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

import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * The interface for classes which provide a viewer for a model class.
 * 
 * @author mri
 * @author chsch
 */
public interface IViewerProvider {

    /**
     * Returns a viewer for models of the provided model class attached to the given composite.
     * 
     * @param parentViewer
     *            the parent {@link ContextViewer}
     * @param parent
     *            the parent composite
     * @return a viewer for the supported model types
     */
    IViewer createViewer(ContextViewer parentViewer, Composite parent);
}
