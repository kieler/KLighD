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
package de.cau.cs.kieler.klighd.piccolo.nodes;

import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.krendering.KTextUtil;
import de.cau.cs.kieler.klighd.piccolo.krendering.ITracingElement;

/**
 * An extend variant of {@link PSWTText} preserving the information on its KText source definition.
 * It enables proper view-model-tracing with text fields in compound figures.
 * 
 * @author chsch
 */
public class PSWTTracingText extends PSWTStyledText implements ITracingElement<KText> {

    /** The default serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The traced source element. */
    private KText kText = null;

    /**
     * PSWTTraceableText constructor taking the related KText instance.
     * 
     * @param theKText The KText rendering model.
     */
    public PSWTTracingText(final KText theKText) {
        super(KTextUtil.getTextLines(theKText));
        this.kText = theKText;
    }

    /**
     * {@inheritDoc}
     */
    public KText getGraphElement() {
        return this.kText;
    }
}
