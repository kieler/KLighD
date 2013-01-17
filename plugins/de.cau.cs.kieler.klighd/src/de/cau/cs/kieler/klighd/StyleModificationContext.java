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

import de.cau.cs.kieler.core.krendering.KStyle;

/**
 * 
 * @author akoc
 *
 */
public class StyleModificationContext {

    /**
     * 
     */
    private KStyle style;
    
    /**
     * Standard Constructor.
     * @param theStyle the style to be modified
     */
    public StyleModificationContext(final KStyle theStyle) {
        this.style = theStyle;
    }
    
    /**
     * @return the style to be modified
     */
    public KStyle getStyle() {
        return this.style;
    }
}
