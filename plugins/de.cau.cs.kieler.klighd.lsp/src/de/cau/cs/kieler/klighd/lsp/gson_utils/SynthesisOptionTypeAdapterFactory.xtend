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

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import de.cau.cs.kieler.klighd.SynthesisOption
import com.google.gson.JsonNull

/**
 * Type adapter that adds fields to SynthesisOption objects during serialization. It adds the hash value of the object
 * under the field name 'sourceHash' so it can be uniquely identified.
 * 
 * @author nir
 */
public class SynthesisOptionTypeAdapterFactory extends CustomizedTypeAdapterFactory<SynthesisOption> {
    new() {
        super(SynthesisOption)
    }
    
    override protected void beforeWrite(SynthesisOption source, JsonElement toSerialize) {
        if (!(toSerialize instanceof JsonNull) && source !== null) {
            val json = toSerialize.getAsJsonObject()
            json.add("sourceHash", new JsonPrimitive(System.identityHashCode(source)))
        }
    }
    
    override protected void afterRead(JsonElement deserialized) {
        val json = deserialized.getAsJsonObject()
        json.remove("sourceHash")
    }
}