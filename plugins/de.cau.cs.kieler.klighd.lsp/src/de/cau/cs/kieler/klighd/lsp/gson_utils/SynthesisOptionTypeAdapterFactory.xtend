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

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonPrimitive
import de.cau.cs.kieler.klighd.SynthesisOption

/**
 * Type adapter that adds fields to SynthesisOption objects during serialization. It adds the hash value of the object
 * under the field name 'sourceHash' so it can be uniquely identified.
 * Also handles a consistent translation to string for the names of option values.
 * 
 * @author nir
 */
public class SynthesisOptionTypeAdapterFactory extends CustomizedTypeAdapterFactory<SynthesisOption> {
    new() {
        super(SynthesisOption)
    }
    
    override protected void beforeWrite(SynthesisOption source, JsonElement toSerialize) {
        if (!(toSerialize instanceof JsonNull) && source !== null) {
            // Add the hash value
            val json = toSerialize.getAsJsonObject()
            json.add("sourceHash", new JsonPrimitive(System.identityHashCode(source)))
            
            // rename enums
            if (source.values !== null &&  !source.values.empty && source.values.head instanceof Enum) {
                // TODO: don't remove the field here, use an exclusion strategy instead and add them manually here.
                // Remove the values and add them back in with their string representation.
                json.remove("values")
                val values = new JsonArray
                for (value : source.values) {
                    values.add(new JsonPrimitive(value.toString))
                }
                json.add("values", values)
                // Repeat the same for the initial value
                json.remove("initialValue")
                val initialValue = new JsonPrimitive(source.initialValue.toString)
                json.add("initialValue", initialValue)
            }
        }
    }
    
    override protected void afterRead(JsonElement deserialized) {
        val json = deserialized.getAsJsonObject()
        json.remove("sourceHash")
    }
}