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
package de.cau.cs.kieler.klighd.lsp.utils

import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KPolyline
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KStyleHolder
import io.typefox.sprotty.api.SModelElement
import java.util.HashMap
import java.util.Map
import java.util.Random

/**
 * Class for generating unique IDs for any KGraphElement. Use a single instance of this and call getId() for all the 
 * elements you need IDs for to guarantee uniqueness.
 * 
 * @author nir
 */
public class KGraphElementIDGenerator {
    /**
     * Internal map to remember the ID for all {@link KGraphElement}s for that IDs already have been generated.
     */
    private Map<KGraphElement, String> elementToIdMap
    
    /**
     * Internal map to remember the {@link KGraphElement} for all IDs for that IDs already have been generated.
     */
    private Map<String, KGraphElement> idToElementMap
    
    /**
     * A random value used to generate unique IDs for elements without a name.
     * This causes that the same object will get different IDs over multiple updates (prevents morphing of not matching
     * objects into each other). Unnamed elements therefore cannot morph on updated models. Name your elements!
     */
    private int randomOffset // TODO: maybe exchange the random value for the hash of the object
    
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
        
        val r = new Random
        randomOffset = r.nextInt
    }
    
    /**
     * generates a unique ID for any {@link KGraphElement}. Returns the same ID for the element if called a second time.
     */
    public def String getId(KGraphElement element) {
        var String id = null
        
        // if the ID was already calculated, use that
        if (elementToIdMap.get(element) !== null) {
            return elementToIdMap.get(element)
        }
        
        // the root node is just called $root
        val parent = element.eContainer as KGraphElement
        var String parentId = null
        if (parent !== null) {
            parentId = getId(parent)
        } else {
            return ID_SEPARATOR + 'root'
        }
        
        // use a prefix depending on the class of the element + the {@link KIdentifier} as id if an identifier is
        // defined, otherwise make up a new id based on the position in the model hierarchy with a Separator not
        // appearing in the {@link KIdentifier} (the ID Separator).
        var String elementId = null
        
        val identifier = element.data.filter(KIdentifier)
        var char elementSeparator
        var int parentOffset
        
        switch (element) {
            KNode: {
                parentOffset = (parent as KNode).children.indexOf(element)
                elementSeparator = NODE_SEPARATOR
            }
            KEdge: {
                parentOffset = (parent as KNode).outgoingEdges.indexOf(element)
                elementSeparator = EDGE_SEPARATOR
            }
            KLabel: {
                parentOffset = (parent as KLabeledGraphElement).labels.indexOf(element)
                elementSeparator = LABEL_SEPARATOR
            }
            KPort: { 
                parentOffset = (parent as KNode).ports.indexOf(element)
                elementSeparator = PORT_SEPARATOR
            }
            default: {
                throw new IllegalArgumentException("Can not generate an id for element of type " + element.class)
            }
        }
        
        if (identifier.empty) {
            elementId = "" + ID_SEPARATOR + elementSeparator + (parentOffset + randomOffset)
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
    
    /**
     * Utility method to find a {@link KGraphElement} from a map between KGraphElements and {@link SModelElement}s by
     * the ID stored in the target SModelElement.
     * 
     * @param k2sMap The map between KGraphElements and SModelElements.
     * @param id The unique ID of one SModelElement in the map.
     * @return The KGraphElement that is the key in the given map with a matching ID in its value.
     */
    public static def findElementById(Map<KGraphElement, SModelElement> k2sMap, String id) {
        // TODO: maybe store the map also the other way, so this can be done more efficiently.
        // Or have a map from IDs to their originating KGraphElements.
        val filteredMap = k2sMap.filter [ kge, sme |
            id.equals(sme.id)
        ]
        return filteredMap.keySet.findFirst[true]
    }
}

public class KRenderingIDGenerator {
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
     * Generates a new unique ID for all child elements of this rendering (if any) and writes it in their ID fields.
     * Assumes, that the given rendering already has a unique id not containing the character '$'.
     */
    public static def void generateIdsRecursive(KStyleHolder rendering) {
        if (rendering !== null) {
            generateIdsRecursive(rendering, null)
        }
    }
    
    /**
     * Generates a new unique ID for this rendering and all its child renderings and writes it in their ID fields.
     * Use different {@code renderingNumber}s if an element has multiple {@link KRendering}s.
     */
    public static def void generateIdsRecursive(KStyleHolder rendering, int renderingNumber) {
        if (rendering !== null) {
            rendering.id = "rendering" + ID_SEPARATOR + RENDERING_SEPERATOR + renderingNumber
            generateIdsRecursive(rendering, null)
        }
    }
    
    private static def void generateIdsRecursive(KStyleHolder rendering, KContainerRendering parentRendering) {
        if (rendering === null) {
            return
        }
        if (parentRendering !== null && !rendering.equals(parentRendering)) {
            // generate a new ID based on the parent rendering's ID and the index of the rendering inside its parent
            rendering.id = parentRendering.id 
                + ID_SEPARATOR + RENDERING_SEPERATOR 
                + parentRendering.children.indexOf(rendering)
        }
        if (rendering instanceof KPolyline) {
            // Special case for KPolyline: It has a junctionPointRendering that also needs an ID.
            // use a new separator and think of this as a new rendering hierarchy with possible children
            val junctionPointRendering = rendering.junctionPointRendering
            if (junctionPointRendering !== null) {
                junctionPointRendering.id = rendering.id
                + ID_SEPARATOR + JUNCTION_POINT_SEPARATOR
            } 
        }
        if (rendering instanceof KContainerRendering) {
            // each KContainerRendering has child Renderings that also need new IDs
            for (childRendering : rendering.children) {
                generateIdsRecursive(childRendering, rendering)
            }
        }
    }
    
    /**
     * Finds the {@link KRendering} in the data of the given {@code element} matching the {@code id} if the IDs have
     * been previously generated by {@link KRenderingIDGenerator#generateIdsRecursive}.
     * 
     * @param element The element to search the rendering in.
     * @param id The ID to look for.
     * @return The {@link KRendering} with the given ID.
     */
    public static def findRenderingById(KGraphElement element, String id) {
        val ids = id.split("\\" + KRenderingIDGenerator.ID_SEPARATOR)
        if (ids.size < 2) {
            // Every rendering ID starts with "rendering[ID_SEPARATOR][RENDERING_SEPARATOR][...]",
            // so ids should have a length of at least two.
            throw new IllegalArgumentException("Misformed ID")
        }
        val renderings = element.data.filter(KRendering) // TODO: could also be contained in a KRenderingLibrary
        // TODO: There could also be multiple renderings in the element, check for the currently displayed rendering.
        var rendering = renderings.findFirst[
            it.id.equals(ids.get(0) + KRenderingIDGenerator.ID_SEPARATOR + ids.get(1))
        ]
        if (rendering === null) {
            throw new IllegalArgumentException("Misformed ID or element")
        }
        for (var i = 2; i < ids.size; i++) {
            if (ids.get(i).startsWith("" + KRenderingIDGenerator.RENDERING_SEPERATOR)) {
                rendering = (rendering as KContainerRendering).children.findFirst[ id.startsWith(it.id) ]
            } else if (ids.get(i).startsWith("" + KRenderingIDGenerator.JUNCTION_POINT_SEPARATOR)) {
                rendering = (rendering as KPolyline).junctionPointRendering
            } else {
                throw new IllegalArgumentException("Misformed ID")
            }
        }
        return rendering
    }
}