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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.lsp.gson_utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.awt.geom.Point2D
import java.io.IOException

/**
 * Type adapter to correctly serialize {@link Point2D}.
 * This serializes only the {@code x} and {@code y} values from the point under keys named 'x' and 'y'.
 * Using this adapter prevents illegal reflective access by Gson to a java.base package object. 
 * 
 * @author nir
 */
class Point2DTypeAdapter extends TypeAdapter<Point2D>{
        
    override write(JsonWriter out, Point2D value) throws IOException {
        if (value === null) {
            out.nullValue
            return
        }
        
        out.beginObject
            out.name("x").value(value.x)
            out.name("y").value(value.y)
        out.endObject
    }

    override read(JsonReader in) throws IOException {
        return null; // only serialization is needed yet.
    }
}
