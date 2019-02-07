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
import de.cau.cs.kieler.klighd.lsp.model.ComputedTextBoundsAction
import de.cau.cs.kieler.klighd.lsp.model.RequestTextBoundsAction
import io.typefox.sprotty.server.json.ActionTypeAdapter
import java.awt.geom.Point2D
import org.eclipse.emf.ecore.EObject

/**
 * Static util class to configure needed gson type adapters for KGraph serialization.
 * 
 * @author nir
 */
public class KGraphTypeAdapterUtil {
    
    public def static GsonBuilder configureGson(GsonBuilder gsonBuilder) {
        gsonBuilder
        .registerTypeAdapterFactory(
            new ActionTypeAdapter.Factory => [
                addActionKind(RequestTextBoundsAction.KIND, RequestTextBoundsAction)
                addActionKind(ComputedTextBoundsAction.KIND, ComputedTextBoundsAction)
            ]
        )
        .registerTypeAdapter(Point2D, new Point2DTypeAdapter)
        .registerTypeHierarchyAdapter(EObject, new EObjectSerializer)
        .registerTypeAdapterFactory(new SynthesisOptionTypeAdapterFactory())
    }
}