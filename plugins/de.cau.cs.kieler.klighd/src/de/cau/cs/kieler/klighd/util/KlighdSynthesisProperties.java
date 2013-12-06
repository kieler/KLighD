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

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
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

    /** the property for a viewer provider. */
    public static final IProperty<String> REQUESTED_VIEWER_PROVIDER = new Property<String>(
            "klighd.viewerProvider");

    /** the property for a path of transformations (can contain gaps). */
    public static final IProperty<String> REQUESTED_DIAGRAM_SYNTHESIS = new Property<String>(
            "klighd.synthesis");

    /** the property for an update strategy. */
    public static final IProperty<String> REQUESTED_UPDATE_STRATEGY = new Property<String>(
            "klighd.updateStrategy");
    

    /**
     * Immutable singleton instance of {@link KlighdSynthesisProperties}.  
     */
    private static final KlighdSynthesisProperties EMPTY_CONFIG = new KlighdSynthesisProperties() {
        private static final long serialVersionUID = 8047045626356247605L;
        
        private final String msg =
                "KLighD: Empty KlighdSynthesisProperties config must not be changed.";
        
        public <T> void setProperty(final IProperty<? super T> property, final T value) {
            throw new UnsupportedOperationException(msg);
        }
        
        public void copyProperties(final IPropertyHolder other) {
            throw new UnsupportedOperationException(msg);
        }
    };
    
    /**
     * Factory method.
     * 
     * @return an immutable empty instance of {@link KlighdSynthesisProperties}.
     */
    public static KlighdSynthesisProperties emptyConfig() {
        return EMPTY_CONFIG;
    }
    
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
        this.setProperty(REQUESTED_UPDATE_STRATEGY, SimpleUpdateStrategy.ID);
        return this;
    }
}
