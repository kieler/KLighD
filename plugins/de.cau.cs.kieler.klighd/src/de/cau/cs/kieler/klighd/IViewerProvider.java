/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import org.eclipse.swt.widgets.Composite;

/**
 * Classes implementing this interface provide a viewer for a model class.
 * 
 * @author mri
 */
public interface IViewerProvider {

    /**
     * Returns a viewer for models of the provided model class attached to the given composite.
     * 
     * @param parent
     *            the parent composite
     * @return a viewer for the supported model types
     */
    IViewer<?> createViewer(Composite parent);

    /**
     * Returns the class of the models supported by the viewer provided by this viewer provider.
     * 
     * @return the class of the supported models
     */
    Class<?> getModelClass();

}
