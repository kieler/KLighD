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
package de.cau.cs.kieler.klighd.util;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;

/**
 * Convenience class for easily composing diagram synthesis customizations, e.g. requested
 * transformations, update strategies, ...<br>
 * <br>
 * To be continued ... :-
 * 
 * @author chsch
 */
public class KlighdSynthesisProperties extends MapPropertyHolder {

    private static final long serialVersionUID = -5635072164749313580L;

    /**
     * Factory method.
     * 
     * @return a new instance of {@link KlighdSynthesisProperties}.
     */
    public static KlighdSynthesisProperties newInstance() {
        return new KlighdSynthesisProperties();
    }
    
    /**
     * Configures a 'use simple update strategy' setting.
     * 
     * @return <code>this<code> {@link KlighdSynthesisProperties} object.
     */
    public KlighdSynthesisProperties useSimpleUpdateStrategy() {
        this.setProperty(LightDiagramServices.REQUESTED_UPDATE_STRATEGY, SimpleUpdateStrategy.ID);
        return this;
    }
}
