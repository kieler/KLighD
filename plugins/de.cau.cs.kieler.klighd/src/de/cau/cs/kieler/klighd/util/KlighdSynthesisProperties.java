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

import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klighd.SynthesisOption;
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
            new Property<SideBarHandling>("klighd.sideBarHandling", SideBarHandling.UNDEFINED);

    /** property denoting the support of selecting multiple diagram elements. */
    public static final IProperty<Boolean> MULTI_SELECTION = new Property<Boolean>(
            "klighd.multiSelection", true);

    /** property denoting pre-definition of diagram {@link SynthesisOption} values. */
    public static final IProperty<Map<SynthesisOption, Object>> SYNTHESIS_OPTION_CONFIG =
            new Property<Map<SynthesisOption, Object>>("klighd.synthesisOptionConfig");
    
    /** property denoting a desired zoom buttons handling. */
    public static final IProperty<ZoomConfigButtonsHandling> REQUESTED_ZOOM_CONFIG_BUTTONS_HANDLING =
            new Property<ZoomConfigButtonsHandling>("klighd.zoomConfigButtonsHandling",
                    ZoomConfigButtonsHandling.UNDEFINED);

    /** property denoting whether to suppress the automatic size estimation of KNodes. */
    public static final IProperty<Boolean> SUPPRESS_SIZE_ESTIMATION = new Property<Boolean>(
            "klighd.suppressSizeEstimation", false);
    
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
     * Defines the possible zoom buttons visibility options. 
     */
    public static enum ZoomConfigButtonsHandling {
        /** Forces the diagram viewer to show the zoom buttons. */
        SHOW,
        /** Forces the diagram viewer to hide the zoom buttons. */
        HIDE,
        /** The visibility of the zoom buttons is set according to the related preference setting. */
        UNDEFINED
    }
    
    /**
     * Immutable singleton instance of {@link KlighdSynthesisProperties}.  
     */
    private static final KlighdSynthesisProperties EMPTY_CONFIG = new KlighdSynthesisProperties() {
        private static final long serialVersionUID = 8047045626356247605L;
        
        private final String msg =
                "KLighD: Empty KlighdSynthesisProperties config must not be changed.";
        
        @Override
        public <T> void setProperty(final IProperty<? super T> property, final T value) {
            throw new UnsupportedOperationException(msg);
        }
        
        @Override
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
     * @deprecated use {@link #create(IPropertyHolder...)}
     * 
     * @return a new instance of {@link KlighdSynthesisProperties}.
     */
    public static KlighdSynthesisProperties newInstance(final IPropertyHolder... propertyHolders) {
        return create(propertyHolders);
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
    public static KlighdSynthesisProperties create(final IPropertyHolder... propertyHolders) {
        if (propertyHolders == null || propertyHolders.length == 0) {
            return new KlighdSynthesisProperties();
            
        } else {
            final KlighdSynthesisProperties sp = new KlighdSynthesisProperties();
            
            for (final IPropertyHolder p : propertyHolders) {
                sp.copyProperties(p);
            }
            
            return sp;
        }
    }
    
    /**
     * Sets a property value and returns <code>this</code> {@link IPropertyHolder} for convenience.
     * 
     * @param <T> type of property
     * @param property the property to set
     * @param value the new value
     * 
     * @see IPropertyHolder#setProperty(IProperty, Object)
     *
     * @return <code>this<code> {@link KlighdSynthesisProperties} object.
     */
    public <T> KlighdSynthesisProperties setProperty2(final IProperty<? super T> property,
            final T value) {
        super.setProperty(property, value);
        return this;
    }
    
    /**
     * Configures the {@link de.cau.cs.kieler.klighd.IUpdateStrategy IUpdateStrategy} to be employed
     * by means of the id it is registered via KLighD's 'extensions' extension point.
     * 
     * @param id
     *            the id the desired {@link de.cau.cs.kieler.klighd.IUpdateStrategy IUpdateStrategy}
     * @return <code>this<code> {@link KlighdSynthesisProperties} object.
     */
    public KlighdSynthesisProperties useUpdateStrategy(final String id) {
        this.setProperty(REQUESTED_UPDATE_STRATEGY, id);
        return this;
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
     * Configures the {@link de.cau.cs.kieler.klighd.IViewerProvider IViewerProvider} to be employed
     * by means of the id it is registered via KLighD's 'extensions' extension point.
     * 
     * @param id
     *            the id the desired {@link de.cau.cs.kieler.klighd.IViewerProvider IViewerProvider}
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

    /**
     * Configures the diagram viewer's support for selecting multiple diagram elements.
     * 
     * @return <code>this<code> {@link KlighdSynthesisProperties} object.
     */
    public KlighdSynthesisProperties suppressMultiSelection() {
        this.setProperty(MULTI_SELECTION, false);
        return this;
    }

    /**
     * Configures diagram {@link SynthesisOption} values beyond the default value definitions.
     * 
     * @param option
     *            the singleton {@link SynthesisOption} object
     * @param value
     *            the value to be configured for <code>option</code>
     * @return <code>this<code> {@link KlighdSynthesisProperties} object.
     */
    public KlighdSynthesisProperties configureSynthesisOptionValue(
            final SynthesisOption option, final Object value) {
        Map<SynthesisOption, Object> optionConfig = this.getProperty(SYNTHESIS_OPTION_CONFIG);
        if (optionConfig == null) {
            optionConfig = Maps.newHashMap();
            this.setProperty(SYNTHESIS_OPTION_CONFIG, optionConfig);
        }
        optionConfig.put(option, value);
        return this;
    }


    /**
     * Configures diagram {@link SynthesisOption} values beyond the default value definitions.
     * 
     * @param options
     *            a {@link Map} of singleton {@link SynthesisOption} objects and their desired
     *            values
     * @return <code>this<code> {@link KlighdSynthesisProperties} object.
     */
    public KlighdSynthesisProperties configureSynthesisOptionValues(
            final Map<SynthesisOption, Object> options) {
        Map<SynthesisOption, Object> optionConfig = this.getProperty(SYNTHESIS_OPTION_CONFIG);
        if (optionConfig == null) {
            optionConfig = Maps.newHashMap();
            this.setProperty(SYNTHESIS_OPTION_CONFIG, optionConfig);
        }
        optionConfig.putAll(options);
        return this;
    }
}
