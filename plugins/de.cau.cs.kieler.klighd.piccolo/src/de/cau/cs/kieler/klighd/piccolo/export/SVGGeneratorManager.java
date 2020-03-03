/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.export;

import static de.cau.cs.kieler.klighd.piccolo.KlighdPiccolo.GENERATOR_SVG_BATIK;
import static de.cau.cs.kieler.klighd.piccolo.KlighdPiccolo.GENERATOR_SVG_FREEHEP_EXTENDED;

import java.awt.geom.Rectangle2D;
import java.lang.reflect.Constructor;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

/**
 * Singleton class that takes care of loading SVG generating {@link KlighdAbstractSVGGraphics}
 * impls.
 *
 * @author uru, chsch
 */
public final class SVGGeneratorManager {

    public static final Map<String, String> GENERATOR_2_GRAPHICS = ImmutableMap.<String, String>builder()
            .put(GENERATOR_SVG_FREEHEP_EXTENDED, "de.cau.cs.kieler.klighd.piccolo.freehep.SemanticFreeHEPSVGGraphics")
            .put(GENERATOR_SVG_BATIK,            "de.cau.cs.kieler.klighd.piccolo.batik.BatikSVGGraphics")
            .build();

    private SVGGeneratorManager() {
        // singleton
    }

    /**
     * Instantiates the {@link KlighdAbstractSVGGraphics} registered with id {@code id} if known.
     * Throws an {@link IllegalArgumentException} providing some details on the failure if
     * instantiation fails.
     *
     * @param id
     *            the id of the svg generator.
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
    public static KlighdAbstractSVGGraphics createGraphics(final String id, final Rectangle2D bounds,
            final boolean textAsShapes, final boolean embedFonts) {
        return createGraphics(id, bounds, textAsShapes, embedFonts, null);
    }
    
    /**
     * Instantiates the {@link KlighdAbstractSVGGraphics} registered with id {@code id} if known.
     * Throws an {@link IllegalArgumentException} providing some details on the failure if
     * instantiation fails.
     *
     * @param id
     *            the id of the svg generator.
     * @param bounds
     *            the expected bounds of the svg being generated.
     * @param textAsShapes
     *            whether texts should be transformed to paths.
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     * @param description
     *            optional description to be inserted into the {@code desc} property of the
     *            generated SVG. Can be null.
     * @throws IllegalArgumentException
     *             when the graphics object cannot be created.
     * @return a instance of a svg generator initialized with the passed parameters.
     */
    public static KlighdAbstractSVGGraphics createGraphics(final String id, final Rectangle2D bounds,
            final boolean textAsShapes, final boolean embedFonts, final String description) {
        return createGraphics(id, bounds, textAsShapes, embedFonts, description, null, null);
    }
    
    /**
     * Instantiates the {@link KlighdAbstractSVGGraphics} registered with id {@code id} if known.
     * Throws an {@link IllegalArgumentException} providing some details on the failure if
     * instantiation fails.
     *
     * @param id
     *            the id of the svg generator.
     * @param bounds
     *            the expected bounds of the svg being generated.
     * @param textAsShapes
     *            whether texts should be transformed to paths.
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     * @param description
     *            optional description to be inserted into the {@code desc} property of the
     *            generated SVG. Can be null.
     * @param css
     *            optional css stylesheet file to be referenced in the svg file
     * @param additionalRootData
     *            optional String that can be inserted into the svg tag. Can be null
     * @throws IllegalArgumentException
     *             when the graphics object cannot be created.
     * @return a instance of a svg generator initialized with the passed parameters.
     */
    public static KlighdAbstractSVGGraphics createGraphics(final String id,
            final Rectangle2D bounds, final boolean textAsShapes, final boolean embedFonts,
            final String description, final String css, final String additionalRootData) {

        if (Strings.isNullOrEmpty(id)) {
            throw new IllegalArgumentException("Could not instantiate svg graphics object, the "
                    + "provided graphicsClass id is 'null' or empty.");
        }

        Class<? extends KlighdAbstractSVGGraphics> clazz = null;
        try {
            @SuppressWarnings("unchecked")
            final Class<? extends KlighdAbstractSVGGraphics> theClazz =
                    (Class<? extends KlighdAbstractSVGGraphics>) Class.forName(id);
            clazz = theClazz;
        } catch (final ClassNotFoundException e) {
            // nothing
        }

        if (clazz == null) {
            final String graphicsClass = GENERATOR_2_GRAPHICS.get(id);

            if (Strings.isNullOrEmpty(graphicsClass)) {
                throw new IllegalArgumentException("Could not instantiate svg graphics object, no "
                        + "graphics impl is associated with id " + id + ".");
            }

            try {
                @SuppressWarnings("unchecked")
                final Class<? extends KlighdAbstractSVGGraphics> theClazz =
                        (Class<? extends KlighdAbstractSVGGraphics>) Class.forName(graphicsClass);
                clazz = theClazz;
            } catch (final ClassNotFoundException e) {
                
                throw new IllegalArgumentException("Could not instantiate svg graphics object for id "
                        + id + ". Corresponding class " + graphicsClass + " could not be found");
            }
        }

        KlighdAbstractSVGGraphics graphics = null;
        try {

            final Constructor<? extends KlighdAbstractSVGGraphics> constr =
                    clazz.getDeclaredConstructor(Rectangle2D.class, Boolean.class, Boolean.class,
                            String.class, String.class, String.class);
            graphics = constr.newInstance(bounds, textAsShapes, embedFonts, description, css,
                    additionalRootData);

        } catch (final Exception e) {
            // nothing
        }

        if (graphics != null) {
            return graphics;
        }

        try {

            final Constructor<? extends KlighdAbstractSVGGraphics> constr =
                    clazz.getDeclaredConstructor(Rectangle2D.class, Boolean.class, Boolean.class);
            graphics = constr.newInstance(bounds, textAsShapes, embedFonts);

        } catch (final Exception e) {
            // nothing
        }

        if (graphics != null) {
            return graphics;
        }

        try {
            final Constructor<? extends KlighdAbstractSVGGraphics> constr =
                    clazz.getDeclaredConstructor(Rectangle2D.class, Boolean.class);
            graphics = constr.newInstance(bounds, textAsShapes);

        } catch (final Exception e) {
            // nothing
        }

        if (graphics != null) {
            return graphics;
        }

        throw new IllegalArgumentException("Could not instantiate svg graphics object for id " + id
                + " because of a missing constructor with signature "
                + "(Rectangle2D, Boolean), (Reactangle2D, Boolean, Boolean)"
                + "or (Reactangle2D, Boolean, Boolean, String)");
    }
}
