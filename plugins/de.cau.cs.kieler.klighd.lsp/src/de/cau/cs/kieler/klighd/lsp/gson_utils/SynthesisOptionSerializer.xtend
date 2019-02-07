/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.gson_utils

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import de.cau.cs.kieler.klighd.SynthesisOption
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.lang.reflect.Type

/**
 * Custom Gson serializer for {@link SynthesisOption}.
 * It differs from the default serialization in that it adds a "sourceHash" field containing the hash value of the Java
 * object for identifying different options with the same name and type.
 * Also alters the serialization of the values and the initialValue of SynthesisOption if they are Enums: In that case
 * the values are serialized by their toString representation.
 * 
 * @author nre
 */
class SynthesisOptionSerializer implements JsonSerializer<SynthesisOption> {
    
    override serialize(SynthesisOption source, Type typeOfSrc, JsonSerializationContext context) {
        val jsonObject = new JsonObject
        for (field : SynthesisOption.declaredFields) {
            if (!shouldSkipField(field)) {
                field.accessible = true
                // Add all fields of this class if their content is not null.
                val content = field.get(source)
                if (content !== null) {
                    jsonObject.add(field.name, context.serialize(content))
                }
            }
        }
        
        // Add the hash value
        jsonObject.add("sourceHash", new JsonPrimitive(System.identityHashCode(source)))
        
        // Add the last two yet missed fields.
        var JsonElement values
        if (source.values !== null &&  !source.values.empty) {
            values = new JsonArray
            if (source.values.head instanceof Enum) {
                // Use the toString representation for serialization of Enums.
                for (value : source.values) {
                    (values as JsonArray).add(context.serialize(value.toString))
                }
            } else {
                // Use the default serialization otherwise.
                for (value : source.values) {
                    (values as JsonArray).add(context.serialize(value))
                }
            }
            jsonObject.add("values", values)
        }
        
        var JsonElement initialValue
        if (source.initialValue !== null) {
            if (source.initialValue instanceof Enum) {
                // Use the toString representation for serialization of Enums.
                initialValue = context.serialize(source.initialValue.toString)
            } else {
                // Use the default serialization otherwise.
                initialValue = context.serialize(source.initialValue)
            }
            jsonObject.add("initialValue", initialValue)
        }
        return jsonObject
    }
    
    def shouldSkipField(Field f) {
        return Modifier.isStatic(f.modifiers)
        || SynthesisOption.isAssignableFrom(f.declaringClass) && 
            (f.name.equals("values") || f.name.equals("initialValue"))
    }    
}