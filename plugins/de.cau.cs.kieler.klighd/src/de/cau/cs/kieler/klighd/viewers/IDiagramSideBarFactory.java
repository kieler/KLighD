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
package de.cau.cs.kieler.klighd.viewers;

import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * @author chsch
 *
 */
public interface IDiagramSideBarFactory {

    /**
     * 
     * @param parent a
     * @param diagramContainer b
     * @param layoutConfig c
     */
    void createSideBar(final Composite parent, final Composite diagramContainer,
            final VolatileLayoutConfig layoutConfig, final ViewContext viewContext);
    
    /**
     * @param fitSpace
     */
    void updateOptions(final boolean fitSpace);
    
    /**
     * 
     * @param diagramComposite
     * @param viewContext
     * @param fitSpace
     */
    void updateOptions(final Composite diagramComposite, final ViewContext viewContext,
            final String viewId, final boolean fitSpace);
    
    void resetLayoutOptionsToDefaults();
    
    /**
     * 
     */
    void dispose();
}
