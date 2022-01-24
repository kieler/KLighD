/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
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
package de.cau.cs.kieler.klighd.krendering.extensions

import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
import de.cau.cs.kieler.klighd.krendering.KRenderingRef
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KGraphElement

/**
 * Extension that allow a convenient use of the {@link KRenderingLibrary} mechanism.
 * 
 * @author uru
 * 
 * @containsExtensions
 */
class KLibraryExtensions {

    /** Rendering factory used to instantiate KRendering instances. */
    extension KRenderingFactory = KRenderingFactory::eINSTANCE

    /**
     * Retrieves the rendering library valid for the given node. The library must
     * be located in the node's topmost ancestor. If that ancestor does not yet
     * have a rendering library attached, one is generated.
     * 
     * @example
     * val library = node.library
     * 
     * @extensionCategory library
     */
    def KRenderingLibrary getLibrary(KNode node) {
        var parent = node
        while (parent.parent !== null) {
            parent = parent.getParent()
        }

        var library = parent.getData(typeof(KRenderingLibrary))
        if (library === null) {
            library = createKRenderingLibrary()
            parent.data.add(library)
        }

        return library
    }

    /** 
     * Retrieves the rendering element that has been identified with the {@code id} string from the
     * given rendering library.
     * 
     * @example
     * val libJunction = edge.source.getFromLibrary("ren_junction")
     * 
     * @extensionCategory library
     */
    def KRenderingRef getFromLibrary(KRenderingLibrary library, String id) {
        val rendering = library.renderings.findFirst[r|r.id == id] as KRendering

        if (rendering !== null) {
            val ref = createKRenderingRef()
            ref.rendering = rendering
            return ref
        }
    }

    /** 
     * Retrieves the rendering element that has been identified with the {@code id} string from the
     * rendering library valid for the given node.
     * 
     * @example
     * val libJunction = edge.source.getFromLibrary("ren_junction")
     * 
     * @extensionCategory library
     */
    def KRenderingRef getFromLibrary(KNode node, String id) {
        val library = node.library
        return library.getFromLibrary(id)
    }

    /**
     * Identifies the passed <code>rendering</code> element with the passed <code>id</code> within
     * the KRendering library. Afterwards the rendering can be retrieved from the library using the
     * {@code getFromLibrary} method. 
     * 
     * @example
     * edge.source.library.addToLibrary("ren_junction", 
     *  createKRoundedRectangle => [ rr |
     *   rr.background = color
     *   rr.foreground = color
     *   rr.cornerWidth = 2
     *   rr.cornerHeight = 2
     *   rr.setPointPlacementData(LEFT, 0, 0, TOP, 0, 0,
     *      H_CENTRAL, V_CENTRAL, 0, 0, 10, 10)
     *  ]
     * )  
     * 
     * @extensionCategory library
     */
    def KRenderingRef addToLibrary(KRenderingLibrary library, String id, KRendering rendering) {
        rendering.id = id
        library.renderings.add(rendering)

        val ref = createKRenderingRef()
        ref.rendering = rendering
        return ref
    }

    /**
     * Identifies the passed <code>rendering</code> element with the passed <code>id</code> within
     * the KRendering library valid for the given node. Afterwards the rendering can be retrieved from
     * the library using the {@code getFromLibrary} method. 
     * 
     * @example
     * edge.source.addToLibrary("ren_junction", 
     *  createKRoundedRectangle => [ rr |
     *   rr.background = color
     *   rr.foreground = color
     *   rr.cornerWidth = 2
     *   rr.cornerHeight = 2
     *   rr.setPointPlacementData(LEFT, 0, 0, TOP, 0, 0,
     *      H_CENTRAL, V_CENTRAL, 0, 0, 10, 10)
     *  ]
     * )  
     * 
     * @extensionCategory library
     */
    def KRenderingRef addToLibrary(KNode node, String id, KRendering rendering) {
        val library = node.library
        return library.addToLibrary(id, rendering)
    }

    def KRenderingRef addRenderingRef(KGraphElement kge, KRendering rendering) {
        createKRenderingRef => [ ref |
            ref.rendering = rendering
            kge.data += ref
        ]
    }    

}
