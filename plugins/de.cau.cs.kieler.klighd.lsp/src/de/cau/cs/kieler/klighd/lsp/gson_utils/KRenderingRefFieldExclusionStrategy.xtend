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
import de.cau.cs.kieler.klighd.krendering.KRenderingRef

/**
 * A gson exclusion strategy to ignore the {@code rendering} field by {@link KRenderingRef#getRendering} during
 * serialization, since it is already serialized in the
 * {@link de.cau.cs.kieler.klighd.krendering.KRenderingLibrary KRenderingLibrary}.
 * 
 * @author nir
 */
public class KRenderingRefFieldExclusionStrategy implements ExclusionStrategy {
    override shouldSkipField(FieldAttributes f) {
        return KRenderingRef.isAssignableFrom(f.declaringClass) && (f.getName().equals("rendering"))
    }
    override shouldSkipClass(Class<?> clazz) {
        return false
    }
}