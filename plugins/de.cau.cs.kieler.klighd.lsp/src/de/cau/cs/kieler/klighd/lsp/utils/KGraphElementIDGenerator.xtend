/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2023 by
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
package de.cau.cs.kieler.klighd.lsp.utils

import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KPolyline
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingRef
import java.util.HashMap
import java.util.Map
import org.eclipse.xtend.lib.annotations.Accessors

import static extension de.cau.cs.kieler.klighd.lsp.utils.SprottyProperties.*

/**
 * Class for generating unique IDs for any {@link KGraphElement}. Use a single instance of this and call getId() for all
 * the elements you need IDs for. IDs will be unique, based on the position in their parent graph element.
 * 
 * @author nre
 */
class KGraphElementIdGenerator {
    
    /**
     * Internal map to remember the ID for all {@link KGraphElement}s for that IDs already have been generated.
     */
    Map<KGraphElement, String> elementToIdMap
    
    /**
     * Map to remember the {@link KGraphElement} for all IDs for that IDs already have been generated.
     */
    @Accessors(PUBLIC_GETTER)
    Map<String, KGraphElement> idToElementMap
    
    int danglingElements = 0
    
    /**
     * Dangling elements are prefixed with this in their ID.
     */
    static final String DANGLING = "dangling"
    
    /**
     * The character used to separate levels of hierarchy in the ID of {@link KGraphElement}s or unnamed elements.
     */
    public static final char ID_SEPARATOR = '$'
    
    /**
     * The character used to indicate a {@link KNode} in the ID of a {@link KGraphElement}.
     */
    public static final char NODE_SEPARATOR = 'N'
    
    /**
     * The character used to indicate a {@link KEdge} in the ID of a {@link KGraphElement}.
     */
    public static final char EDGE_SEPARATOR = 'E'
    
    /**
     * The character used to indicate a {@link KPort} in the ID of a {@link KGraphElement}.
     */
    public static final char PORT_SEPARATOR = 'P'
    
    /**
     * The character used to indicate a {@link KLabel} in the ID of a {@link KGraphElement}.
     */
    public static final char LABEL_SEPARATOR = 'L'
    
    new() {
        elementToIdMap = new HashMap
        idToElementMap = new HashMap
    }
    
    /**
     * Generates a unique ID for any {@link KGraphElement}. Returns the same ID for the element if called a second time.
     * 
     * @param element The graph element to generate the id for.
     * @return The id string or empty string if the element is null
     */
    def String getId(KGraphElement element) {
        if (element === null) {
            return ""
        }
        var String id = null
        
        // if the ID was already calculated, use that
        if (elementToIdMap.get(element) !== null) {
            return elementToIdMap.get(element)
        }
        
        // the root node is just called $root
        val parent = element.eContainer as KGraphElement
        var String parentId = null
        if (parent === null) {
            id = ID_SEPARATOR + 'root'
            if (idToElementMap.get(id) !== null) {
                // The graph already contains a root node, this is a connected node dangling without a parent and will
                // therefore not be displayed in any graph. Generate a unique ID anyway.
                id = ID_SEPARATOR + DANGLING + danglingElements++
            }
            elementToIdMap.put(element, id)
            idToElementMap.put(id, element)
            return id
        }
        parentId = getId(parent)
        
        // use a prefix depending on the class of the element + the {@link KIdentifier} as id if an identifier is
        // defined, otherwise make up a new id based on the position in the model hierarchy with a Separator not
        // appearing in the {@link KIdentifier} (the ID Separator).
        var String elementId = null
        
        val identifier = element.data.filter(KIdentifier)
        var char elementSeparator
        var int index
        
        switch (element) {
            KNode: {
                elementSeparator = NODE_SEPARATOR
                index = element.parent.children.indexOf(element)
            }
            KEdge: {
                elementSeparator = EDGE_SEPARATOR
                index = element.source.outgoingEdges.indexOf(element)
            }
            KLabel: {
                elementSeparator = LABEL_SEPARATOR
                index = element.parent.labels.indexOf(element)
            }
            KPort: {
                elementSeparator = PORT_SEPARATOR
                index = element.node.ports.indexOf(element)
            }
            default: {
                throw new IllegalArgumentException("Can not generate an id for element of type " + element.class)
            }
        }
        
        if (identifier.empty) {
            elementId = "" + ID_SEPARATOR + elementSeparator + index
        } else {
            elementId = elementSeparator + identifier.head.id
        }
        
        val baseId = parentId + ID_SEPARATOR + elementId
        // If the KIdentifier is not unique between its siblings, make the ID unique with a counter in the end.
        id = baseId
        for (var cnt = 2; idToElementMap.containsKey(id); cnt++) {
            id = baseId + ID_SEPARATOR + ID_SEPARATOR + "copy" + cnt
        }
        elementToIdMap.put(element, id)
        idToElementMap.put(id, element)
        return id
    }
    
}

/**
 * Class for generating unique IDs for any {@link KRendering}. IDs will be unique, based on the position in their
 * parent graph element / rendering.
 * 
 * @author nre
 */
class KRenderingIdGenerator {
    
    /**
     * The character used to separate levels of hierarchy in the ID of {@link KRendering}s.
     */
    public static final char ID_SEPARATOR = '$'
    
    /**
     * The character used to indicate a child {@link KRendering} in the ID of another {@link KRendering}.
     */
    public static final char RENDERING_SEPERATOR = 'R'
    
    /**
     * The character used to indicate a junction point rendering in the ID of a {@link KPolyline}.
     */
    public static final char JUNCTION_POINT_SEPARATOR = 'J'
    
    /**
     * Generates a new unique ID for this rendering (if necessary) and all child elements of this rendering (if any)
     * and puts it in the {@link SprottyProperties#RENDERING_ID} property. This ID can be used for uniquely identifying
     * renderings between systems.
     * 
     * @param rendering The rendering that should currently get an ID.
     * @param parentId The ID of the parent to be added as the ID's prefix.
     * @param renderingIndex The index of this rendering in relation to the parent.
     */
    static def void generateIdsRecursive(KRendering rendering, String parentId, int renderingIndex) {
        if (rendering === null) {
            return
        }
        // KRenderingRefs need the ID of their reference to be identifiable on different systems.
        if (rendering instanceof KRenderingRef) {
            rendering.renderingId = rendering.rendering.renderingId
            return
        }
        
        // Generate a new ID based on the parent rendering's ID.
        rendering.renderingId = parentId
            + ID_SEPARATOR + RENDERING_SEPERATOR
            + renderingIndex
        if (rendering instanceof KPolyline) {
            // Special case for KPolyline: It has a junctionPointRendering that also needs an ID.
            // Use a new separator and think of this as a new rendering hierarchy with possible children.
            val junctionPointRendering = rendering.junctionPointRendering
            if (junctionPointRendering !== null) {
                junctionPointRendering.renderingId = rendering.renderingId
                + ID_SEPARATOR + JUNCTION_POINT_SEPARATOR
            } 
        }
        if (rendering instanceof KContainerRendering) {
            // Each KContainerRendering has child renderings that also need new IDs.
            for (var int i = 0; i < rendering.children.size; i++) {
                generateIdsRecursive(rendering.children.get(i), rendering.renderingId, i)
            }
        }
    }
    
    /**
     * Finds the {@link KRendering} in the data of the given {@code element} matching the {@code id} if the IDs have
     * been previously generated by {@link KRenderingIdGenerator#generateIdsRecursive}.
     * 
     * @param element The element to search the rendering in.
     * @param id The ID to look for.
     * @return The {@link KRendering} with the given ID.
     */
    static def findRenderingById(KGraphElement element, String id) {
        val ids = id.split("\\" + ID_SEPARATOR + "\\" + ID_SEPARATOR + "\\" + ID_SEPARATOR).get(1).split("\\" + ID_SEPARATOR)
        // Every rendering ID is built hierarchically, separated by the RENDERING_SEPERATOR symbol.
        
        val renderings = element.data.filter(KRendering) + element.data.filter(KRenderingRef)
        var rendering = renderings.findFirst [
            id !== null && it.renderingId !== null && id.startsWith(it.renderingId)
        ]
        if (rendering === null) {
            throw new IllegalArgumentException("Misformed ID or element")
        }
        if (rendering instanceof KRenderingRef) {
            // We only support actions on the ref itself, not the rendering it is referencing, as that has no real
            // connection back to what it is attached to anymore.
            return rendering
        }
        for (var i = 1; i < ids.size; i++) {
            var KRendering nextRendering = null
            if (rendering instanceof KContainerRendering) {
                // Look for the id in the child renderings
                nextRendering = rendering.children.findFirst[ id.startsWith(it.renderingId) ]
                if (nextRendering === null && rendering instanceof KPolyline) {
                    // Special case for KPolyline renderings, as they might have another rendering for junction points.
                    nextRendering = (rendering as KPolyline).junctionPointRendering
                }
            }
            if (nextRendering === null) {
                throw new IllegalArgumentException("Misformed rendering ID: " + id)
            }
            rendering = nextRendering
        }
        return rendering
    }
    
}