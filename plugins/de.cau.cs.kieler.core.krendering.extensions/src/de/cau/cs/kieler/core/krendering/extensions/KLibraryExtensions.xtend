/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering.extensions

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.KRenderingLibrary
import de.cau.cs.kieler.core.krendering.KRenderingRef
import de.cau.cs.kieler.core.krendering.ViewSynthesisShared

/**
 * Extension that allow a convenient use of the {@link KRenderingLibrary} mechanism.
 * 
 * @author uru
 * 
 * @containsExtensions
 */
@ViewSynthesisShared
class KLibraryExtensions {

    /** Rendering factory used to instantiate KRendering instances. */
    public val renderingFactory = KRenderingFactory::eINSTANCE

    /**
     * 
     */
    def KRenderingLibrary getLibrary(KNode node) {
        var parent = node
        while (parent.parent != null) {
            parent = parent.getParent()
        }

        var library = parent.getData(typeof(KRenderingLibrary))
        if (library == null) {
            library = renderingFactory.createKRenderingLibrary()
            parent.data.add(library)
        }

        return library
    }

    /** 
     * Retrieves the rendering element from the library that has been identified with
     * the <code>id</code> string.  
     * 
     * @example
     * val libJunction = edge.source.getFromLibrary("ren_junction")
     * 
     * @extensionCategory library
     */
    def KRenderingRef getFromLibrary(KRenderingLibrary library, String id) {
        val rendering = library.renderings.findFirst[r|r.id == id] as KRendering

        if (rendering != null) {
            val ref = renderingFactory.createKRenderingRef()
            ref.rendering = rendering
            return ref
        }
    }


    def KRenderingRef getFromLibrary(KNode node, String id) {
        val library = node.library
        return library.getFromLibrary(id)
    }

    /**
     * Identifies the passed <code>rendering</code> element with the passed <code>id</code> within the KRendering library.
     * Afterwards the rendering can be retrieved from the library using the <code>getFromLibrary</code> method. 
     * 
     * @example
     * edge.source.addToLibrary("ren_junction", 
     *  renderingFactory.createKRoundedRectangle => [ rr |
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

        val ref = renderingFactory.createKRenderingRef()
        ref.rendering = rendering
        return ref
    }

    def KRenderingRef addToLibrary(KNode node, String id, KRendering rendering) {
        val library = node.library
        return library.addToLibrary(id, rendering)
    }

}
