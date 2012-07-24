/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kgraph.text.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.text.krendering.serializer.KRenderingTransientValueService;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;

/**
 * A KGraph specific {@link org.eclipse.xtext.serializer.sequencer.ITransientValueService}.
 * Prevents serializer from trying to dump out eOpposite relations and
 * {@link de.cau.cs.kieler.core.properties.IProperty} data.
 * 
 * @author chsch
 */
@SuppressWarnings("restriction")
public class KGraphTransientValueService extends KRenderingTransientValueService {

    @Override
    public ListTransient isListTransient(final EObject semanticObject,
            final EStructuralFeature feature) {
        // the whole lists of nodes' incoming edges, IProperties, ...
        if (feature == KGraphPackage.eINSTANCE.getKNode_IncomingEdges()
             || feature == KGraphPackage.eINSTANCE.getEMapPropertyHolder_Properties()
             || (feature == KGraphPackage.eINSTANCE.getEMapPropertyHolder_PersistentEntries()
                  // ... and persisted entries of non-KLayoutData ...    
                  && !(KLayoutDataPackage.eINSTANCE.getKShapeLayout().isInstance(semanticObject)
                        || KLayoutDataPackage.eINSTANCE.getKEdgeLayout().isInstance(semanticObject)))) {
            // must not be serialized as they either do not have related elements in the concrete syntax,
            //  or are no EObjects (IProperties).
            //  
            return ListTransient.YES;
        }
        return super.isListTransient(semanticObject, feature);
    }

    @Override
    public boolean isValueInListTransient(final EObject semanticObject, final int index,
            final EStructuralFeature feature) {
        return super.isValueInListTransient(semanticObject, index, feature);
    }

    @Override
    public ValueTransient isValueTransient(final EObject semanticObject,
            final EStructuralFeature feature) {
        return super.isValueTransient(semanticObject, feature);
    }
}
