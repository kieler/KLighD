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

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.text.krendering.KRenderingTransientValueService;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * A KGraph specific {@link org.eclipse.xtext.serializer.sequencer.ITransientValueService}.
 * Prevents serializer from trying to dump out eOpposite relations and
 * {@link de.cau.cs.kieler.core.properties.IProperty} data.
 * 
 * @author chsch
 * @kieler.design proposed 2012-11-01 chsch
 * @kieler.rating proposed yellow 2012-11-01 chsch
 */
@SuppressWarnings("restriction")
// chsch: I don't understand the 'internal' visibility state of TransientValueService
//  as this appears to be the one being established in future.
//  Currently the xtext guys propose to specialize the one of the parse tree constructor API
//  that I don't want to use anymore. Expect/Hope that this will change in future.
public class KGraphTransientValueService extends KRenderingTransientValueService {

    @Override
    public ListTransient isListTransient(final EObject semanticObject,
            final EStructuralFeature feature) {
        // the whole lists of nodes' incoming edges, IProperties, ...
        if (feature == KGraphPackage.eINSTANCE.getKNode_IncomingEdges()
             || feature == KGraphPackage.eINSTANCE.getEMapPropertyHolder_Properties()
             || (feature == KGraphPackage.eINSTANCE.getEMapPropertyHolder_PersistentEntries()
                  // ... and persisted entries of non-KLayoutData/KTexts
                  //     (used for testing purposes) ...    
                  && !(KLayoutDataPackage.eINSTANCE.getKShapeLayout().isInstance(semanticObject)
                        || KLayoutDataPackage.eINSTANCE.getKEdgeLayout().isInstance(semanticObject)
                        || KRenderingPackage.eINSTANCE.getKText().isInstance(semanticObject)))) {
            // must not be serialized as they either do not have related elements in the concrete syntax,
            //  or are no EObjects (IProperties).
            //  
            return ListTransient.YES;
        }
        if (feature == KGraphPackage.eINSTANCE.getEMapPropertyHolder_PersistentEntries()) {
            return ListTransient.SOME;
        }
        if (feature == KGraphPackage.eINSTANCE.getKGraphElement_Data()) {
            return ListTransient.SOME;
        }
        return super.isListTransient(semanticObject, feature);
    }

    @Override
    public boolean isValueInListTransient(final EObject semanticObject, final int index,
            final EStructuralFeature feature) {
        
        if (feature == KGraphPackage.eINSTANCE.getEMapPropertyHolder_PersistentEntries()) {            
            KGraphData data = (KGraphData) semanticObject;
            return data.getPersistentEntries().get(index).getKey().endsWith("piccolo.controller");
        }
        if (feature == KGraphPackage.eINSTANCE.getKGraphElement_Data()) {
            EObject eo = ((KGraphElement) semanticObject).getData().get(index);
            if (eo instanceof KRendering
                    || eo instanceof KRenderingFactory
                    || eo instanceof KShapeLayout
                    || eo instanceof KEdgeLayout) {
                return false;
            }
            // chsch suppress e.g. RenderingContextData (extending KGraphDataImpl) of the klighd bundle
            return true;
        }
        return super.isValueInListTransient(semanticObject, index, feature);
    }

    @Override
    public ValueTransient isValueTransient(final EObject semanticObject,
            final EStructuralFeature feature) {
        // suppress the serialization of empty KInsets objects
        if (feature == KLayoutDataPackage.eINSTANCE.getKShapeLayout_Insets()) {
            KInsets insets = ((KShapeLayout) semanticObject).getInsets();
            return insets == null
                    || (insets.getLeft() == 0 && insets.getRight() == 0 && insets.getTop() == 0 && insets
                            .getBottom() == 0) ? ValueTransient.YES : ValueTransient.NO;
        }
        return super.isValueTransient(semanticObject, feature);
    }
}
