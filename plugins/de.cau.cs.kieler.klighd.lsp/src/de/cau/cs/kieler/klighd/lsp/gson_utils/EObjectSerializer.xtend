/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019-2021 by
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
package de.cau.cs.kieler.klighd.lsp.gson_utils

import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder
import de.cau.cs.kieler.klighd.kgraph.KInsets
import de.cau.cs.kieler.klighd.kgraph.impl.EMapPropertyHolderImpl
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
import de.cau.cs.kieler.klighd.krendering.KRenderingRef
import de.cau.cs.kieler.klighd.krendering.KStyle
import de.cau.cs.kieler.klighd.krendering.KXPosition
import de.cau.cs.kieler.klighd.krendering.KYPosition
import de.cau.cs.kieler.klighd.lsp.utils.KGraphMappingUtil
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.lang.reflect.Type
import java.util.HashMap
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.impl.EObjectImpl

/**
 * Serializer that serializes any sub class of {@link EMapPropertyHolder} via reflection while ignoring fields of the
 * class EMapPropertyHolder itself and its super classes. It serializes only specific properties from
 * {@link EMapPropertyHolder#getProperties}, if they are the properties {@link KlighdProperties.CALCULATED_BOUNDS},
 * {@link KlighdProperties.CALCULATED_BOUNDS_MAP}, {@link KlighdProperties.CALCULATED_ROTATION}, or
 * {@link KlighdProperties.CALCULATED_ROTATION_MAP}.
 * These fields are needed for rendering on any client.
 * 
 * @author nre
 */
class EObjectSerializer implements JsonSerializer<EObject> {
    
    override serialize(EObject source, Type typeOfSrc, JsonSerializationContext context) {
        var Class<? extends EObject> class = source.class
            if (shouldSkipClass(class)) {
                return JsonNull.INSTANCE
            }
        val jsonObject = new JsonObject
        // run over all super classes (until the class equals EMapPropertyHolder or EObject, but not all the way until Object!)
        while (class !== EMapPropertyHolderImpl && class !== EObjectImpl) {
            for (field : class.declaredFields) {
                if (specialHandling(field)) {
                    handle(field)
                } else if (!shouldSkipField(field)) {
                    field.accessible = true
                    // Add all fields of this class and its super classes if their content is not null.
                    val content = field.get(source)
                    if (content !== null) {
                        jsonObject.add(field.name, context.serialize(content))
                    }
                }
            }
            // Add a "type" field for all classes that would be ambiguous otherwise.
            if (needsTypeField(class)) {
                if (jsonObject.get("type") === null) {
                    jsonObject.add("type", context.serialize(class.simpleName))
                }
            }
            // This cast is safe, because when the class equals EObject, the while loop will exit.
            class = class.superclass as Class<? extends EObject>
        }
        
        // A more efficient testing of properties depending on the sources class.
        if (KRendering.isAssignableFrom(source.class)) {
            val propertyHolder = source as KRendering
            
            var properties = propertyHolder.allProperties;
            var blackList = KlighdDataManager.instance.blacklistedProperties;
            var whiteList = KlighdDataManager.instance.whitelistedProperties;
            var HashMap<String, Object> copiedPropertyMap = newHashMap
    
            for (propertyKVPair : properties.entrySet()) {
                if (!KGraphMappingUtil.containsPropertyWithId(blackList, propertyKVPair.key.id) 
                    && (propertyKVPair.key.id.startsWith("de.cau.cs.kieler.klighd")
                        || propertyKVPair.key.id.startsWith("klighd")
                        || propertyKVPair.key.id.startsWith("org.eclipse.elk")
                        || KGraphMappingUtil.containsPropertyWithId(whiteList, propertyKVPair.key.id)
                    )
                ) {
                    copiedPropertyMap.put(propertyKVPair.key.id, propertyKVPair.value)
                }
            }

            jsonObject.add("properties", context.serialize(copiedPropertyMap))
        }
        return jsonObject
    }
    
    /**
     * Returns if a {@link Field} should be skipped during serialization.
     * 
     * @param f The field to be checked.
     */
    def shouldSkipField(Field f) {
        return Modifier.isStatic(f.modifiers)
            || KRenderingRef      .isAssignableFrom(f.declaringClass) && (f.getName().equals("rendering"))
            || KRendering         .isAssignableFrom(f.declaringClass) && (f.getName().equals("placementData"))
            || KContainerRendering.isAssignableFrom(f.declaringClass) && (f.getName().equals("childPlacement")) 
    }
    
    /**
     * Returns if a {@link Class} should be skipped during serialization.
     * 
     * @param c The class to be checked.
     */
    def shouldSkipClass(Class<? extends EObject> c) {
        return KInsets.isAssignableFrom(c)
    }
    
    /**
     * Returns if a {@link Class} could have ambiguous types and need an additional "type" field to be added during
     * serialization.
     * 
     * @param c The class to be checked.
     */
    def needsTypeField(Class<? extends EObject> c) {
        return KRendering       .isAssignableFrom(c)
            || KRenderingLibrary.isAssignableFrom(c)
            || KStyle           .isAssignableFrom(c)
            || KXPosition       .isAssignableFrom(c)
            || KYPosition       .isAssignableFrom(c)
    }
    
    // TODO: Special handling of default values
    def specialHandling(Field f) {
        return false
    }
    
    def handle(Field f) {
        
    }
}