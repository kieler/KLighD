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
package de.cau.cs.kieler.core.krendering.extensions

import de.cau.cs.kieler.core.krendering.KColor
import de.cau.cs.kieler.core.krendering.KRenderingUtil

/**
 * 
 * @author chsch
 * @author ssm
 * @author uru
 *  
 * @containsExtensions
 */
class KColorExtensions {

    /**
     * Convenient creation of color objects. Allows several names (red, blue, black, etc) 
     * and hex strings (#00ff00). 
     * 
     * @example
     * rectangle.background = "black".color
     * rectangle.foreground = "#00ff00".color
     * 
     * @extensionCategory style
     */
    def KColor getColor(String name) {
        return KRenderingUtil.getColor(name)
    }
    
}