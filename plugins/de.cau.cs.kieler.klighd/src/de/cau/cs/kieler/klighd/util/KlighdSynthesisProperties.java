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

    /** property denoting a desired viewer provider. */
    public static final IProperty<String> REQUESTED_VIEWER_PROVIDER = new Property<String>(
            "klighd.viewerProvider");

    /** property denoting a desired diagram synthesis. */
    public static final IProperty<String> REQUESTED_DIAGRAM_SYNTHESIS = new Property<String>(
            "klighd.synthesis");

    /** property denoting a desired update strategy. */
    public static final IProperty<String> REQUESTED_UPDATE_STRATEGY = new Property<String>(
            "klighd.updateStrategy");

    /** property denoting a desired side bar handling. */
    public static final IProperty<SideBarHandling> REQUESTED_SIDE_BAR_HANDLING =
            new Property<SideBarHandling>("", SideBarHandling.UNDEFINED);

    /**
     * Defines the possible diagram side bar initialization options. 
     */
    public static enum SideBarHandling {
        /** Forces the diagram viewer to show the side bar. */
        EXPAND,
        /** Forces the diagram viewer to hide the side bar. */
        COLLAPSE,
        /** The initialization of the side bar is done according to the related preference setting. */
        UNDEFINED
    }

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
     * @param propertyHolders
     *            a variable number of {@link IPropertyHolder IPropertyHolders} allowing providing
     *            configurations
     * 
     * @return a new instance of {@link KlighdSynthesisProperties}.
     */
    public static KlighdSynthesisProperties newInstance(final IPropertyHolder... propertyHolders) {
        if (propertyHolders == null || propertyHolders.length == 0) {
            return new KlighdSynthesisProperties();
            
        } else {
            final KlighdSynthesisProperties sp = new KlighdSynthesisProperties();
            
            for (IPropertyHolder p : propertyHolders) {
                sp.copyProperties(p);
            }
            
            return sp;
        }
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
    
    /**
     * Configures the {@link de.cau.cs.kieler.klighd.IViewer IViewer} to be employed by means of the
     * id it is registered via KLighD's 'extensions' extension point.
     * 
     * @param id
     *            the id the desired {@link de.cau.cs.kieler.klighd.IViewer IViewer}
     * @return <code>this<code> {@link KlighdSynthesisProperties} object.
     */
    public KlighdSynthesisProperties useViewer(final String id) {
        this.setProperty(REQUESTED_VIEWER_PROVIDER, id);
        return this;
    }
    
    /**
     * Configures the initial visibility of the diagram side bar in the diagram viewer to be opened.
     * 
     * @return <code>this<code> {@link KlighdSynthesisProperties} object.
     */
    public KlighdSynthesisProperties expandSideBar() {
        this.setProperty(REQUESTED_SIDE_BAR_HANDLING, SideBarHandling.EXPAND);
        return this;
    }

    /**
     * Configures the initial invisibility of the diagram side bar in the diagram viewer to be
     * opened.
     * 
     * @return <code>this<code> {@link KlighdSynthesisProperties} object.
     */
    public KlighdSynthesisProperties collapseSideBar() {
        this.setProperty(REQUESTED_SIDE_BAR_HANDLING, SideBarHandling.COLLAPSE);
        return this;
    }
}
