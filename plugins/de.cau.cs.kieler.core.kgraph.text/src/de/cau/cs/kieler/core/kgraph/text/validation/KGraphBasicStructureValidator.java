/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kgraph.text.validation;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.xtext.validation.Check;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;

/**
 * An {@link org.eclipse.emf.ecore.EValidator EValidator} for basic structural analysis of KGraphs.
 * 
 * Currently, it only checks the uniqueness of node and port ids.
 * 
 * @author chsch 
 * @author csp
 */
public class KGraphBasicStructureValidator extends AbstractKGraphJavaValidator {

    
    /** Error message for duplicate ids. */
    private static final String DUPLICATE_ID_ERROR = "Duplicate id";
    /** Error message for possible duplicate ids. */
    private static final String DUPLICATE_ID_WARNING = "Possible duplicate id";
    
    /** Internally used to mark a node as duplicate. */
    private static final String CONSEQUENTIAL_ERROR = "knode invalid";
    /** Attribute of the KIdentifier. */
    private static final EAttribute ID_REF = KLayoutDataPackage.eINSTANCE.getKIdentifier_Id();

    /**
     * Check whether all nodes have unique ids and for each node, all contained ports have unique
     * ids.
     * 
     * @param node
     *            the node to check the ids on. Only the root node is processed as the method
     *            descends into all child nodes.
     */
    @Check
    public void checkUniqueIds(KNode node) {
        if (node.getParent() != null) {
            return;
        }
        /*
         * A map to remember used ids.
         * The keyset contains ids of already checked nodes.
         * The value to a given node id contains a list of already checked port ids inside this node.
         */
        Multimap<String, String> idMap = HashMultimap.create();
        
        final Iterator<KGraphElement> kges = Iterators.filter(node.eAllContents(), KGraphElement.class);
        while (kges.hasNext()) {
            KGraphElement kge = kges.next();
            KIdentifier kIdentifier = kge.getData(KIdentifier.class);
            if (kIdentifier == null) {
                // If the identifier is null, we can't check the uniqueness.
                continue;
            }
            String id = kIdentifier.getId();
            if (kge instanceof KNode) {
                if (idMap.containsKey(id)) {
                    // If the map already contains the id, mark the node as duplicate to distinguish
                    // between definite and possible duplicate port ids later.
                    idMap.put(id, CONSEQUENTIAL_ERROR);
                    
                    error(DUPLICATE_ID_ERROR, kIdentifier, ID_REF);
                } else {
                    // Add the id to the map to mark it as used.
                    idMap.put(id, null);
                }
            } else if (kge instanceof KPort) {
                // Get the id of the parent node.
                String parentId = ((KPort) kge).getNode().getData(KIdentifier.class).getId();
                // Get the list of used port ids for this node.
                Collection<String> ids = idMap.get(parentId);
                if (ids.contains(id)) {
                    if (ids.contains(CONSEQUENTIAL_ERROR)) {
                        // If the port id is used and the node is marked as duplicate, mark this port as
                        // possible duplicate. Renaming the node may resolve the duplicate port.
                        warning(DUPLICATE_ID_WARNING, kIdentifier, ID_REF);
                    } else {
                        // If the port id is used but the node id is unique, the port id is a duplicate.
                        error(DUPLICATE_ID_ERROR, kIdentifier, ID_REF);
                    }
                } else {
                    // Mark the port id as used.
                    idMap.put(parentId, id);
                }
            }
        }
        
    }

}
