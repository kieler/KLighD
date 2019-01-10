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

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder
import de.cau.cs.kieler.klighd.lsp.utils.BoundsProperties
import de.cau.cs.kieler.klighd.util.KlighdProperties

/**
 * Type adapter that adds fields to EMapPropertyHolder objects during serialization. The added fields are taken from
 * {@link EMapPropertyHolder#getProperties}, if it contains the properties {@link KlighdProperties.CALCULATED_BOUNDS},
 * {@link KlighdProperties.CALCULATED_BOUNDS_MAP}, {@link KlighdProperties.CALCULATED_ROTATION}, or
 * {@link KlighdProperties.CALCULATED_ROTATION_MAP}.
 * These fields should be used on any client as the bounds for their parent element respectively for the element stated
 * in the map.
 * 
 * @author nir
 */
public class EMapPropertyHolderTypeAdapterFactory extends CustomizedTypeAdapterFactory<EMapPropertyHolder> {
    var GsonBuilder gsonBuilder = null
    new(GsonBuilder gsonBuilder) {
        super(EMapPropertyHolder)
        this.gsonBuilder = gsonBuilder
    }
    
    override protected void beforeWrite(EMapPropertyHolder source, JsonElement toSerialize) {
        if (!toSerialize.isJsonObject) {
            return
        }
        val JsonObject data = toSerialize.asJsonObject
        
        // don't serialize persistentEntries. TODO: this is already excluded in the EObjectFieldExclusionStrategy
        // but still shows up some times objects. Find out why!
        data.remove("persistentEntries")
        
        // don't serialize the properties, but pick out what we really need
        data.remove("properties")
        // TODO: rewrite this to not create a new gson object here but rather take main one
        val gson = gsonBuilder.create
        if (source.hasProperty(BoundsProperties.CALCULATED_BOUNDS)) {
            data.add("calculatedBounds", gson.toJsonTree(
                source.getProperty(BoundsProperties.CALCULATED_BOUNDS)))
        }
        if (source.hasProperty(BoundsProperties.CALCULATED_BOUNDS_MAP)) {
            data.add("calculatedBoundsMap", gson.toJsonTree(
                source.getProperty(BoundsProperties.CALCULATED_BOUNDS_MAP)))
        }
        if (source.hasProperty(BoundsProperties.CALCULATED_DECORATION)) {
            data.add("calculatedDecoration", gson.toJsonTree(
                source.getProperty(BoundsProperties.CALCULATED_DECORATION)))
        }
        if (source.hasProperty(BoundsProperties.CALCULATED_DECORATION_MAP)) {
            data.add("calculatedDecorationMap", gson.toJsonTree(
                source.getProperty(BoundsProperties.CALCULATED_DECORATION_MAP)))
        }
        if (source.hasProperty(KlighdProperties.CALCULATED_TEXT_BOUNDS)) {
            data.add("calculatedTextBounds", gson.toJsonTree(
                source.getProperty(KlighdProperties.CALCULATED_TEXT_BOUNDS)))
        }
    }
    
    override protected void afterRead(JsonElement deserialized) {
        // do nothing. No properties are needed to be deserialized yet.
    }
}