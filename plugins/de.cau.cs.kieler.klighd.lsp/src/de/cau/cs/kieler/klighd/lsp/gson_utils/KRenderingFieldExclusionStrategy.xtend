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
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KContainerRendering

/**
 * A Gson exclusion strategy to ignore the {@code placementData} field of {@link KRendering}s
 * and the {@code childPlacement} field of {@link KContainerRendering}s during
 * serialization, since they are only needed for the micro layout performed on the server.
 * 
 * @author nir
 */
public class KRenderingFieldExclusionStrategy implements ExclusionStrategy {
    override shouldSkipField(FieldAttributes f) {
        return KRendering.isAssignableFrom(f.declaringClass) && (f.getName().equals("placementData"))
            || KContainerRendering.isAssignableFrom(f.declaringClass) && (f.getName().equals("childPlacement")) 
    }
    override shouldSkipClass(Class<?> clazz) {
        return false
    }
}