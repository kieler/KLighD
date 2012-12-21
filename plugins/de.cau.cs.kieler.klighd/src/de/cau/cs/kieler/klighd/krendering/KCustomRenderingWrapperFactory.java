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
package de.cau.cs.kieler.klighd.krendering;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.spi.RegistryContributor;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.KlighdPlugin;

/**
 * A factory enabling the decoupling of KLighD viewer implementations and figures bridging to other
 * viewing frameworks while explicit dependencies to those frameworks are outsourced. For example,
 * the Draw2dNode, which is a special PNode (Piccolo2d) wrapping Draw2d figures is registered
 * via this factory. Thus the klighd.piccolo bundle doesn't need a dependency to draw2d, only the
 * contributing bundle or fragment of Draw2dNode does.  
 * 
 * @author chsch
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public final class KCustomRenderingWrapperFactory {
    
    /** The related extension point's id. */
    private static final String EXTENSION_POINT_ID = "de.cau.cs.kieler.klighd.customFigureWrapper";
    /** The related extensions' name. */
    private static final String EXTENSION_NAME = "wrapper";
    /** The wrapped type field's name. */
    private static final String FIGURE_CLASS_ENTRY_NAME = "figureClass";
    /** The wrapper type field's name. */
    private static final String WRAPPER_CLASS_ENTRY_NAME = "wrapperClass";
    
    private static KCustomRenderingWrapperFactory instance = null;
    
    /** 
     * The wrapper look-up dictionary:
     * The keys are the wrapped types, the values are the concrete wrapping implementations. 
     */
    private final Map<Class<?>, Class<?>> typeWrapperMap = Maps.newHashMap();

    /**
     * Provides the single instance of the class.
     * Initializes a new one if not already done.  
     * 
     * @return the singleton
     */
    public static KCustomRenderingWrapperFactory getInstance() {
        if (instance == null) {
            instance = new KCustomRenderingWrapperFactory();
        }
        return instance;
    }
    
    /**
     * Hidden default constructor: examines the 'customFigureWrapper' extension point.
     */
    private KCustomRenderingWrapperFactory() {
        // get the extensions
        final IConfigurationElement[] configurations = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTENSION_POINT_ID);

        // filter them, retain the ones named like EXTENSION_NAME
        //  this is actually not necessary right now but might be
        //  if the extension point definition will be extended in future
        Iterable<IConfigurationElement> wrappers = new Iterable<IConfigurationElement>() {
            public Iterator<IConfigurationElement> iterator() {
                return Iterators.filter(Iterators.forArray(configurations),
                        new Predicate<IConfigurationElement>() {
                            public boolean apply(final IConfigurationElement input) {
                                return input.getName().equals(EXTENSION_NAME);
                            }
                        });
            }
        };

        // examine the extensions and register the data in the 'typeWrapperMap'
        for (IConfigurationElement element : wrappers) {
            Class<?> figureClass = null;
            Class<?> wrapperClass = null;
            Bundle host = null;
            try {

                // there is only that one implementation of IContributor and since it's providing
                //  more data the returned contributor is casted without a type check
                host = Platform.getBundle(((RegistryContributor) element.getContributor()).getName());
                figureClass = host.loadClass(element.getAttribute(FIGURE_CLASS_ENTRY_NAME));
                wrapperClass = host.loadClass(element.getAttribute(WRAPPER_CLASS_ENTRY_NAME));
                this.registerWrapper(figureClass, wrapperClass);

            } catch (InvalidRegistryObjectException e) {
                // I hope this will never happen ;-)
                final String msg = "An extension of " + EXTENSION_POINT_ID + " in " + host
                        + " could not be examined properly and appears to be invalid in some way.";
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, e),
                        StatusManager.LOG);
            } catch (ClassNotFoundException e) {
                String msg;
                if (figureClass == null) {
                    msg = "Failed to load figure class "
                            + element.getAttribute(FIGURE_CLASS_ENTRY_NAME) + ".";
                } else {
                    msg = "Failed to load wrapper class "
                            + element.getAttribute(WRAPPER_CLASS_ENTRY_NAME) + ".";
                }
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, e),
                        StatusManager.LOG);
            }
        }
    }
    
    /**
     * Allows to registers a special implementation of a renderings framework's basic figure type
     * that is dedicated to integrate renderings of the different type 'renderingType'.
     * 
     * @param renderingType
     *            the type of the custom renderings
     * @param wrapperType
     *            the wrapper type enabling the integration and drawing of renderingType
     */
    public void registerWrapper(final Class<?> renderingType, final Class<?> wrapperType) {
        this.typeWrapperMap.put(renderingType, (Class<?>) wrapperType);
    }
    
    /**
     * Provides an instance of the wrapping figure type for the given 'renderingType'
     * initialized with a new instance of 'renderingType'.
     * 
     * @param <T>
     *            the expected type to be returned while calling this method, avoids a cast at the
     *            calling position
     * @param renderingType
     *            the required figure type
     * @param frameworkType
     *            the figure type required by the rendering framework
     * @return an instance of a wrapping {@link PNode} type.
     */
    @SuppressWarnings("unchecked")
    public <T> T getWrapperInstance(final Class<?> renderingType, final Class<T> frameworkType) {
        // if the custom figure can be handled by the rendering framework without wrapping...
        if (frameworkType.isAssignableFrom(renderingType)) {
            try {
                // ... create an instance and return it
                return (T) renderingType.newInstance();
            } catch (Exception e) {
                final String msg = "KLighD: An error occured while instantiating the custom figure "
                        + "type " + renderingType.getName();
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, e),
                        StatusManager.LOG);
                return null;
            }
        }
        
        // otherwise look in the map for fitting wrapper   
        Map.Entry<Class<?>, Class<?>> entry = Iterables.getFirst(
                Maps.filterEntries(this.typeWrapperMap, new Predicate<Entry<Class<?>, Class<?>>>() {
                    public boolean apply(final Entry<Class<?>, Class<?>> entry) {
                        return entry.getKey().isAssignableFrom(renderingType)
                                && frameworkType.isAssignableFrom(entry.getValue());
                    }
                }).entrySet(), null);
        
        if (entry != null) {
            try {
                // due to the 2nd part of the filter condition
                //  the cast in the next line is generally valid  
                return (T) entry.getValue().getConstructor(entry.getKey())
                        .newInstance(renderingType.newInstance());
            } catch (Exception e) {
                final String msg = "An error occured while instantiating the requested wrapper "
                        + "figure for the given figure type " + renderingType.getName();
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, e),
                        StatusManager.LOG);
            }
        }
        return null;
    }
    
    /**
     * Provides an instance of the wrapping figure type for the given 'renderingTypeName'
     * initialized with a new instance of rendering type.
     * Delegates to {@link #getWrapperInstance(Class, Class)}.
     * 
     * @param <T>
     *            the expected type to be returned while calling this method, avoids a cast at the
     *            calling position
     * @param bundleName
     *            the bundle's name to load the figure class from
     * @param renderingTypeName
     *            the required figure type's name
     * @param frameworkType
     *            the figure type required by the rendering framework
     * @return an instance of a wrapping {@link PNode} type.
     */
    public <T> T getWrapperInstance(final String bundleName, final String renderingTypeName,
            final Class<T> frameworkType) {

        // get the bundle, trim the leading and ending quotation marks
        Bundle bundle = Platform.getBundle(bundleName.replace("\"", ""));
        if (bundle == null) {
            return null;
        }
        
        Class<?> clazz = null;
        try {
            // load the figure class 
            clazz = bundle.loadClass(renderingTypeName);
        } catch (ClassNotFoundException e) {
            final String msg = "KLighD: Error occurred while loading the custom rendering class "
                    + renderingTypeName + " in bundle " + bundleName; 
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, e),
                    StatusManager.LOG);
            return null;
        }
        
        return this.getWrapperInstance(clazz, frameworkType);
    }
}
