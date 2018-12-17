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
import de.cau.cs.kieler.klighd.krendering.KStyleHolder
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
    private Map<KGraphElement, String> idMap
    
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
        idMap = new HashMap
        
        val r = new Random
        randomOffset = r.nextInt
    }
    
    /**
     * generates a unique ID for any {@link KGraphElement}. Returns the same ID for the element if called a second time.
     */
    public def String getId(KGraphElement element) {
        var String id = null
        
        // if the ID was already calculated, use that
        if (idMap.get(element) !== null) {
            return idMap.get(element)
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
        
        switch (element) {
            KNode: {
                if (identifier.empty) {
                    val parentOffset = (parent as KNode).children.indexOf(element)
                    elementId = "" + ID_SEPARATOR + NODE_SEPARATOR + (parentOffset + randomOffset)
                } else {
                    elementId = NODE_SEPARATOR + identifier.head.id
                }
            }
            KEdge: {
                if (identifier.empty) {
                    val parentOffset = (parent as KNode).outgoingEdges.indexOf(element)
                    elementId = "" + ID_SEPARATOR + EDGE_SEPARATOR + (parentOffset + randomOffset)
                } else {
                    elementId = EDGE_SEPARATOR + identifier.head.id
                }
            }
            KLabel: {
                if (identifier.empty) {
                    val parentOffset = (parent as KLabeledGraphElement).labels.indexOf(element)
                    elementId = "" + ID_SEPARATOR + LABEL_SEPARATOR + (parentOffset + randomOffset)
                } else {
                    elementId = LABEL_SEPARATOR + identifier.head.id
                }
            }
            KPort: {
                if (identifier.empty) {
                    val parentOffset = (parent as KNode).ports.indexOf(element)
                    elementId = "" + ID_SEPARATOR + PORT_SEPARATOR + (parentOffset + randomOffset)
                } else {
                    elementId = PORT_SEPARATOR + identifier.head.id
                }
            }
            default: {
                throw new IllegalArgumentException("Can not generate an id for element of type " + element.class)
            }
        }
        
        id = parentId + ID_SEPARATOR + elementId
        idMap.put(element, id)
        return id
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
}