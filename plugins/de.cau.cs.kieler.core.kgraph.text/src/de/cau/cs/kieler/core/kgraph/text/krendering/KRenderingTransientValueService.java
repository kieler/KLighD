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
package de.cau.cs.kieler.core.kgraph.text.krendering;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.serializer.sequencer.TransientValueService;

/**
 * A KRendering specific {@link org.eclipse.xtext.serializer.sequencer.ITransientValueService}.
 * Prevents serializer from trying to dump out eOpposite relations and
 * {@link de.cau.cs.kieler.core.properties.IProperty} data.
 * 
 * Since all current needs are covered by the {@link TransientValueService}, the methods
 * just delegate to the super ones.
 * 
 * @author chsch
 * @kieler.design proposed 2012-11-01 chsch
 * @kieler.rating proposed yellow 2012-11-01 chsch
 */
@SuppressWarnings("restriction")
// chsch: Don't understand the 'internal' visibility state of TransientValueService
//  as this appears to be the one being established in future.
//  Currently the xtext guys propose to specialice the one of the parse tree constructor API
//  that I don't want to use anymore. Expect/Hope that this will change in future.
public class KRenderingTransientValueService extends TransientValueService {

    @Override
    public ListTransient isListTransient(final EObject semanticObject,
            final EStructuralFeature feature) {
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
