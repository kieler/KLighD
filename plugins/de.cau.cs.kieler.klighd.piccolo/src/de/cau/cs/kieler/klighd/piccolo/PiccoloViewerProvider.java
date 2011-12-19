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
package de.cau.cs.kieler.klighd.piccolo;

import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.IViewerProvider;

/**
 * A viewer provider for Piccolo nodes or lists of Piccolo nodes.
 * 
 * @author mri
 */
public class PiccoloViewerProvider implements IViewerProvider {

    /** the identifier for this viewer provider as specified in the extension point. */
    public static final String ID = "de.cau.cs.kieler.klighd.piccolo.piccoloViewer";
    
    /**
     * {@inheritDoc}
     */
    public IViewer<?> createViewer(final Composite parent) {
        return new PiccoloViewer(parent);
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getModelClass() {
        return PiccoloDiagramContext.class;
    }

}
