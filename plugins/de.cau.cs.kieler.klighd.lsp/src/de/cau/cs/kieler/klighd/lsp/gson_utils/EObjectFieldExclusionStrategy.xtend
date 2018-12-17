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
import org.eclipse.emf.ecore.EObject

/**
 * A gson exclusion strategy to ignore all 'e[...]' fields during serialization so that for example no containment 
 * gets serialized, which would cause possible endless loops.
 * The other fields are also not needed for diagram generation on client side.
 * 
 * @author nir
 */
public class EObjectFieldExclusionStrategy implements ExclusionStrategy { 
    override shouldSkipField(FieldAttributes f) {
        return EObject.isAssignableFrom(f.declaringClass) && (
            f.getName().equals("eContainer") || 
            f.getName().equals("eProperties") ||
            f.getName().equals("eAdapters") ||
            f.getName().equals("eFlags") ||
            f.getName().equals("eContainerFeatureID") ||
            f.getName().equals("persistentEntries")
        )
    }
    override shouldSkipClass(Class<?> clazz) {
        return false
    }
}