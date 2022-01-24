/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.krendering;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.Klighd;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.KlighdDataManager.CustomFigureWrapperDescriptor;
import de.cau.cs.kieler.klighd.KlighdPlugin;

/**
 * A factory enabling the decoupling of KLighD viewer implementations and figures bridging to other
 * viewing frameworks while explicit dependencies to those frameworks are outsourced. For example,
 * the Draw2dNode, which is a special PNode (Piccolo2d) wrapping Draw2d figures is registered
 * via this factory. Thus the klighd.piccolo bundle doesn't need a dependency to draw2d, only the
 * contributing bundle or fragment of Draw2dNode does.<br>
 * <br>
 * Note: The wrapper type wraps another type, if it provides a constructor with one parameter whose
 * type is compatible to the other type.
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public final class KCustomRenderingWrapperFactory {
    
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
    
    
    // ------------ Initialization part ------------ //
    
    /**
     * Hidden default constructor: examines the 'customFigureWrapper' extension point.
     */
    private KCustomRenderingWrapperFactory() {
        for (final CustomFigureWrapperDescriptor descriptor : KlighdDataManager.getInstance()
                .getCustomFigureWrapperDescriptors()) {
            Class<?> figureClass = null;
            Class<?> wrapperClass = null;

            try {
                if (Klighd.IS_PLATFORM_RUNNING) {
                    final Bundle host = Platform.getBundle(descriptor.contributor);
                    figureClass = host.loadClass(descriptor.customFigureType);
                    wrapperClass = host.loadClass(descriptor.wrapperType);

                } else {
                    figureClass = Klighd.class.getClassLoader().loadClass(descriptor.customFigureType);
                    wrapperClass = Klighd.class.getClassLoader().loadClass(descriptor.wrapperType);
                }

                this.registerWrapper(figureClass, wrapperClass);

            } catch (final ClassNotFoundException e) {
                final String msgSuffix = Klighd.IS_PLATFORM_RUNNING ?
                        "' via bundle '" + descriptor.contributor + "'." : "'.";
                
                final String msg;
                if (figureClass == null) {
                    msg = "Failed to load figure class '" + descriptor.customFigureType + msgSuffix;
                } else {
                    msg = "Failed to load wrapper class '" + descriptor.wrapperType + msgSuffix;
                }
                Klighd.log(new Status(IStatus.ERROR, Klighd.PLUGIN_ID, msg, e));
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
        this.typeWrapperMap.put(renderingType, wrapperType);
    }
    
    
    // ------------ Service part ------------ //
    
    /**
     * Provides an instance of the wrapping figure type 'T' for the given 'renderingTypeName'
     * initialized with a new instance of rendering type. The class 'renderingType' must provide a
     * default constructor, the class 'frameworkType' must provide a constructor with one parameter
     * of type 'renderingTypeName'.<br>
     * <br>
     * Delegates to {@link #getWrapperInstance(Class, Class)}.
     * 
     * @param <T>
     *            the expected type to be returned while calling this method, avoids a cast at the
     *            calling position
     * @param bundleName
     *            the bundle's name to load the figure class from
     * @param renderingTypeName
     *            the required figure type's name, must provide a default constructor
     * @param frameworkType
     *            the figure type required by the rendering framework
     * @return an instance of the wrapping frameworkType.
     */
    public <T> T getWrapperInstance(final String bundleName, final String renderingTypeName,
            final Class<T> frameworkType) {
        
        // no rendering type specified. This can be the case 
        // for partly exported view models, such as code-based figures
        if (Strings.isNullOrEmpty(renderingTypeName)) {
            final String msg =
                    "KLighD custom rendering wrapper factory: Rendering type name not specified.";
            Klighd.log(new Status(IStatus.ERROR, Klighd.PLUGIN_ID, msg));
            
            return null;
        }

        final Bundle bundle;
        if (Klighd.IS_PLATFORM_RUNNING) {
            // first get the denoted bundle by ... 
            if (!Strings.isNullOrEmpty(bundleName)) {
            
                // ... trimming the leading and trailing quotation marks and asking the platform or ...
                bundle = Platform.getBundle(bundleName.replace("\"", ""));
                if (bundle == null) {
                    final String msg = "KLighD custom rendering wrapper factory: Bundle named "
                            + bundleName + " was not found.";
                    Klighd.log(new Status(IStatus.ERROR, Klighd.PLUGIN_ID, msg));
                    
                    return null;
                }
            } else {
                // ... by taking the KLighD bundle if none is given
                bundle = KlighdPlugin.getDefault().getBundle();
            }
        } else {
            bundle = null;
        }

        Class<?> clazz = null;
        try {
            // load the figure class 
            clazz = bundle != null ? bundle.loadClass(renderingTypeName)
                    : Klighd.class.getClassLoader().loadClass(renderingTypeName);
        } catch (final ClassNotFoundException e) {
            final String msg = "KLighD custom rendering wrapper factory: Error occurred while"
                    + "loading the custom rendering class " + renderingTypeName
                    + ((bundle != null) ? (" in bundle " + bundleName) : "") + ".";
            Klighd.log(new Status(IStatus.ERROR, Klighd.PLUGIN_ID, msg, e));
            
            return null;
        }
        
        return this.getWrapperInstance(clazz, frameworkType);
    }


    /**
     * Provides an instance of the wrapping figure type for the given 'renderingType' initialized
     * with a new instance of 'renderingType'. The class 'renderingType' must provide a default
     * constructor, the class 'frameworkType' must provide a constructor with one parameter of type
     * 'renderingTypeName'.<br>
     * <br>
     * Delegates to {@link #getWrapperInstance(Class, Object, Class)}.
     * 
     * @param <S>
     *            the expected type to be returned while calling this method, avoids a cast at the
     *            calling position
     * @param <T>
     *            the expected type to be returned while calling this method, avoids a cast at the
     *            calling position
     * @param renderingType
     *            the required figure type, must provide a default constructor
     * @param frameworkType
     *            the figure type required by the rendering framework
     * @return an instance of the wrapping frameworkType.
     */
    public <S, T> T getWrapperInstance(final Class<S> renderingType, final Class<T> frameworkType) {
        final S figure = getInstance(renderingType);

        return figure == null ? null : this.getWrapperInstance(renderingType, figure, frameworkType);
    }
    
    
    /**
     * Provides an instance of the wrapping figure type 'T' for the given 'figure' object. The class
     * 'frameworkType' must provide a constructor with one parameter of type 'S'.<br>
     * <br>
     * Delegates to {@link #getWrapperInstance(Class, Object, Class)}.
     * 
     * @param <S>
     *            The type of the given figure object
     * @param <T>
     *            The type of the required wrapping figure (framework type)
     * 
     * @param figure
     *            The concrete figure instance to be wrapped
     * @param frameworkType
     *            The type of the required wrapping figure (framework type)
     * @return an instance of the wrapping figure of frameworkType containing 'figure'.
     */
    @SuppressWarnings("unchecked")
    public <S, T> T getWrapperInstance(final S figure, final Class<T> frameworkType) {
        return this.getWrapperInstance((Class<S>) figure.getClass(), figure, frameworkType);
    }
    

    /**
     * Checks whether a wrapping figure instance is required and instantiates a fitting one if
     * needed. The class 'renderingType' must provide a default constructor, the class
     * 'frameworkType' must provide a constructor with one parameter of type 'renderingTypeName'.<br>
     * <br>
     * Method assumes, that {code figure} is an instance of {@code renderingType} and not
     * {@code null}. In case a wrapping figure is not needed, i.e. {@code renderingType} is a sub
     * type of {@code frameworkType}, the above assumption is verified.
     * 
     * @param <S>
     *            the expected type to be returned while calling this method, avoids a cast at the
     *            calling position
     * @param <T>
     *            the expected type to be returned while calling this method, avoids a cast at the
     *            calling position
     * @param renderingType
     *            the required figure type, must provide a default constructor
     * @param figure
     *            the requested custom figure, must not be {@code null}
     * @param frameworkType
     *            the figure type required by the rendering framework
     * @return an instance of the wrapping frameworkType.
     */
    private <S, T> T getWrapperInstance(final Class<S> renderingType, final S figure,
            final Class<T> frameworkType) {
        
        // if the custom figure can be handled by the rendering framework without wrapping...
        if (frameworkType.isAssignableFrom(renderingType)) {
        
            // ... and is already instantiated just return it,
            if (renderingType.isInstance(figure)) {
                return frameworkType.cast(figure);
            }

            try {
                // ... create an instance, otherwise, and return that one
                return frameworkType.cast(getInstance(renderingType));
            } catch (final Exception e) {
                final String msg = "KLighD custom rendering wrapper factory: An error occured while "
                        + "instantiating the requested custom figure type "
                        + renderingType.getName() + ".";
                Klighd.log(new Status(IStatus.ERROR, Klighd.PLUGIN_ID, msg, e));
                
                return null;
            }
        }

        // otherwise look into the map for a fitting wrapper
        final Map.Entry<Class<?>, Class<?>> entry = Iterables.getFirst(
                Maps.filterEntries(this.typeWrapperMap, new Predicate<Entry<Class<?>, Class<?>>>() {
                    public boolean apply(final Entry<Class<?>, Class<?>> entry) {
                        return entry.getKey().isAssignableFrom(renderingType)
                                && frameworkType.isAssignableFrom(entry.getValue());
                    }
                }).entrySet(), null);

        if (entry != null) {
            try {
                // due to the 2nd part of the filter condition
                // the cast in the next line is generally valid
                return frameworkType.cast(
                        entry.getValue().getConstructor(entry.getKey()).newInstance(figure));
            } catch (final Exception e) {
                final String msg = "KLighD custom rendering wrapper factory: An exception occured while "
                        + "instantiating the required wrapper figure for the requested figure type "
                        + renderingType.getName() + ".";
                Klighd.log(new Status(IStatus.ERROR, Klighd.PLUGIN_ID, msg, e));
            }
        }
        return null;
    }

    private <S> S getInstance(Class<S> renderingType) {
        final Constructor<S> constructor;
        try {
            constructor = renderingType.getConstructor();

        } catch (final NoSuchMethodException | SecurityException e) {
            final String msg = "KLighD custom rendering wrapper factory: An exception occured while "
                    + "revealing the required no-arg constructor of the requested custom figure type "
                    + renderingType.getName() + ".";
            Klighd.handle(new Status(IStatus.ERROR, Klighd.PLUGIN_ID, msg, e));
            
            return null;
        }
        try {
            return constructor.newInstance();

        } catch (final InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            final String msg = "KLighD custom rendering wrapper factory: An exception occured while "
                    + "instantiating the requested custom figure type " + renderingType.getName() + ".";
            Klighd.log(new Status(IStatus.ERROR, Klighd.PLUGIN_ID, msg, e));

            return null;
        }
    }
}
