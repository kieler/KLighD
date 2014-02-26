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
package de.cau.cs.kieler.klighd.piccolo.svg;

import java.awt.geom.Rectangle2D;
import java.lang.reflect.Constructor;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.KlighdPlugin;

/**
 * Singleton class that takes care of loading all svg generators from the {@code svgGenerators}
 * extension point. A new instance of a registered generator can be retrieved using the
 * {@link #createGraphics(String, Rectangle2D, boolean)} method.
 * 
 * @author uru
 */
public final class SVGGeneratorManager {

    /**
     * Id of the svgGenerators extension point.
     */
    public static final String EXTP_ID_SVGGENERATORS =
            "de.cau.cs.kieler.klighd.piccolo.svg.svgGenerators";

    private static SVGGeneratorManager instance;

    private Map<String, String> generatorsMap = Maps.newHashMap();

    private SVGGeneratorManager() {
        // singleton
    }

    static {
        instance = new SVGGeneratorManager();

        instance.loadSvgGenerators();
    }

    /**
     * @return the instance
     */
    public static SVGGeneratorManager getInstance() {
        return instance;
    }

    /**
     * 
     * @param id
     *            the id of the svg generator, most probably specified in
     *            {@link de.cau.cs.kieler.klighd.KlighdConstants KlighdConstants}.
     * @param bounds
     *            the expected bounds of the svg being generated.
     * @param textAsShapes
     *            whether texts should be transformed to paths.
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     * @throws IllegalArgumentException
     *             when the graphics object cannot be created.
     * @return a instance of a svg generator initialized with the passed parameters.
     */
    @SuppressWarnings("unchecked")
    public KlighdAbstractSVGGraphics createGraphics(final String id, final Rectangle2D bounds,
            final boolean textAsShapes, final boolean embedFonts) {
        final String graphicsClass = generatorsMap.get(id);

        try {
            final Class<? extends KlighdAbstractSVGGraphics> clazz =
                    (Class<? extends KlighdAbstractSVGGraphics>) Class.forName(graphicsClass);
            
            KlighdAbstractSVGGraphics graphics = null;
            try {

                final Constructor<? extends KlighdAbstractSVGGraphics> constr =
                        clazz.getDeclaredConstructor(Rectangle2D.class, Boolean.class,
                                Boolean.class);
                graphics = constr.newInstance(bounds, textAsShapes, embedFonts);

            } catch (NoSuchMethodException e0) {
                // nothing
            }
            
            if (graphics != null) {
                return graphics;
            }
            
            try {
                final Constructor<? extends KlighdAbstractSVGGraphics> constr =
                        clazz.getDeclaredConstructor(Rectangle2D.class, Boolean.class);
                graphics = constr.newInstance(bounds, textAsShapes);
                
            } catch (NoSuchMethodException e1) {
                // nothing
            }
            
            if (graphics != null) {
                return graphics;
            }
            
            throw new IllegalArgumentException("Could not instantiate svg graphics object for id "
                    + id + " because of a missing constructor with signature "
                    + "(Rectangle2D, Boolean) or (Reactangle2D, Boolean, Boolean)");
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Could not instantiate svg graphics object for id "
                    + id, e);
        }
    }

    private void loadSvgGenerators() {
        // read the extension point
        IConfigurationElement[] extensions =
                Platform.getExtensionRegistry().getConfigurationElementsFor(EXTP_ID_SVGGENERATORS);
        for (IConfigurationElement element : extensions) {
            try {
                // store the generator
                String id = element.getAttribute("id");
                String graphics = element.getAttribute("class");

                generatorsMap.put(id, graphics);
            } catch (Exception e) {
                reportError(EXTP_ID_SVGGENERATORS, element, e);
            }

        }
    }

    private static void reportError(final String extensionPoint,
            final IConfigurationElement element, final Exception exception) {
        String message =
                "Extension point " + extensionPoint + ": Invalid entry in element "
                        + element.getName() + ", contributed by "
                        + element.getContributor().getName();
        IStatus status = new Status(IStatus.WARNING, KlighdPlugin.PLUGIN_ID, 0, message, exception);
        Bundle kp = Platform.getBundle(KlighdPlugin.PLUGIN_ID);
        Platform.getLog(kp).log(status);
    }
}
