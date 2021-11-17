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
package de.cau.cs.kieler.klighd.krendering.extensions

import de.cau.cs.kieler.klighd.krendering.KColor
import de.cau.cs.kieler.klighd.krendering.KRenderingUtil

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