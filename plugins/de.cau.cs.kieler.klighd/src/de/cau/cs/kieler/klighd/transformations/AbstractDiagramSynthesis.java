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
package de.cau.cs.kieler.klighd.transformations;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;

/**
 * This is a specialized {@link AbstractTransformation} with target model type {@link KNode}.<br>
 * <br>
 * Its aim is to simplify and shrink the class declaration parts of diagram synthesis
 * transformations creating KGraph/KRendering specifications. Please see also the documentation of
 * {@link AbstractTransformation}.
 * 
 * @param <S>
 *            Type of the model to be visualized
 * 
 * @author chsch
 */
public abstract class AbstractDiagramSynthesis<S> extends AbstractTransformation<S, KNode> {
    
    /**
     * This constant expression is a convenience handle to easy the access to the
     * {@link KRenderingFactory} in derivatives of this class, i.e. concrete diagram syntheses.
     */
    protected static final KRenderingFactory RENDERING_FACTORY = KRenderingFactory.eINSTANCE; 
}
