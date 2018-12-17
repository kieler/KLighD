/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.gson_utils

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import de.cau.cs.kieler.klighd.kgraph.KInsets

/**
 * A Gson exclusion strategy to ignore all {@link KInsets} classes during serialization that are not needed
 * for the rendering on the client side.
 * 
 * @author nir
 */
public class InsetExclusionStrategy implements ExclusionStrategy {
    override shouldSkipField(FieldAttributes f) {
        return false
    }
    override shouldSkipClass(Class<?> clazz) {
        return KInsets.isAssignableFrom(clazz)
    }
}