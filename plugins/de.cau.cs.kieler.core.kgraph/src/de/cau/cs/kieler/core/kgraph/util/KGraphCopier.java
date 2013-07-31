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
package de.cau.cs.kieler.core.kgraph.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.cau.cs.kieler.core.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IPropertyHolder;

/**
 * A copier that knows how to copy KGraphs with their properties. The default copier defined in
 * {@link EcoreUtil.Copier} doesn't know how to copy properties set on {@link EMapPropertyHolder}s. This
 * copier does.
 * 
 * <p>To copy a KGraph with this copier, use the following code (either to copy one KGraph or multiple
 * KGraphs):</p>
   *<pre>
   *  Copier copier = new KGraphCopier();
   *  KNode result = copier.copy(knode);
   *  Collection results = copier.copyAll(knodes);
   *  copier.copyReferences();
   *</pre>
 * 
 * @author cds
 */
public class KGraphCopier extends EcoreUtil.Copier {
    
    /** Serialization ID. */
    private static final long serialVersionUID = -3223272563941763388L;
    
    
    /**
     * Creates a new instance.
     */
    public KGraphCopier() {
        super();
    }
    
    
    /**
     * Copies the given KNode. This is a convenience method that saves you the trouble of having to
     * typecast.
     * 
     * @param node the node to copy.
     * @return copy of the node.
     */
    public KNode copy(final KNode node) {
        return (KNode) super.copy(node);
    }

    @Override
    protected void copyContainment(final EReference eReference, final EObject eObject,
            final EObject copyEObject) {
        
        // Check if we have an EMapPropertyHolder and want to copy its properties
        if (eObject instanceof EMapPropertyHolder && eReference.getName().equals("properties")) {
            IPropertyHolder propertyHolder = (IPropertyHolder) eObject;
            IPropertyHolder copyPropertyHolder = (IPropertyHolder) copyEObject;
            
            copyPropertyHolder.copyProperties(propertyHolder);
        } else {
            super.copyContainment(eReference, eObject, copyEObject);
        }
    }
    
}
