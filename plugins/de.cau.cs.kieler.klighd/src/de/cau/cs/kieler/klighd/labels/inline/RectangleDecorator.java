/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.labels.inline;

import org.eclipse.elk.core.math.ElkPadding;

import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.krendering.KBackground;
import de.cau.cs.kieler.klighd.krendering.KContainerRendering;
import de.cau.cs.kieler.klighd.krendering.KForeground;
import de.cau.cs.kieler.klighd.krendering.KRectangle;
import de.cau.cs.kieler.klighd.labels.inline.InlineLabelConfigurator.LayoutMode;

/**
 * Decorator that uses a rectangle as big as the final label. The rectangle can have a background
 * color as well as an optional border color.
 * 
 * @author cds
 */
public final class RectangleDecorator extends AbstractDecoratorRenderingProvider {

    /////////////////////////////////////////////////////////////////////////////////////////////
    // Variables
    
    /** Background color for created rectangles. */
    private Color backgroundColor = new Color();
    /** Border color for created rectangles, if a border is desired. */
    private Color borderColor = null;

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Setup

    /**
     * Use create methods.
     */
    private RectangleDecorator() {
    }
    
    /**
     * Create a new instance.
     * 
     * @return new instance.
     */
    public static RectangleDecorator create() {
        return new RectangleDecorator();
    }
    
    /**
     * Configures the given color to be used for rectangle backgrounds. By default, a slightly
     * translucent white is used.
     * 
     * @param r
     *            red component, between 0 and 255.
     * @param g
     *            green component, between 0 and 255.
     * @param b
     *            blue component, between 0 and 255.
     * @param a
     *            alpha component, between 0 and 255 (0 is transparent, 255 is solid).
     * @return this decorator for method chaining.
     */
    public RectangleDecorator withBackground(final int r, final int g, final int b, final int a) {
        backgroundColor.r = r;
        backgroundColor.g = g;
        backgroundColor.b = b;
        backgroundColor.a = a;
        
        return this;
    }
    
    /**
     * Configures the given color to be used for rectangle backgrounds. By default, a slightly
     * translucent white is used.
     * 
     * @param color
     *            the color to be used for the background.
     * @return this decorator for method chaining.
     */
    public RectangleDecorator withBackground(final java.awt.Color color) {
        backgroundColor.r = color.getRed();
        backgroundColor.g = color.getGreen();
        backgroundColor.b = color.getBlue();
        backgroundColor.a = color.getAlpha();
        
        return this;
    }
    
    /**
     * Configures the given color to be used for rectangle border. By default, no border is added.
     * 
     * @param r
     *            red component, between 0 and 255.
     * @param g
     *            green component, between 0 and 255.
     * @param b
     *            blue component, between 0 and 255.
     * @param a
     *            alpha component, between 0 and 255 (0 is transparent, 255 is solid).
     * @return this decorator for method chaining.
     */
    public RectangleDecorator withBorder(final int r, final int g, final int b, final int a) {
        if (borderColor == null) {
            borderColor = new Color();
        }
        
        borderColor.r = r;
        borderColor.g = g;
        borderColor.b = b;
        borderColor.a = a;
        
        return this;
    }
    
    /**
     * Configures the given color to be used for rectangle border. By default, no border is added.
     * 
     * @param color
     *            the color to be used for the border.
     * @return this decorator for method chaining.
     */
    public RectangleDecorator withBorder(final java.awt.Color color) {
        if (borderColor == null) {
            borderColor = new Color();
        }

        borderColor.r = color.getRed();
        borderColor.g = color.getGreen();
        borderColor.b = color.getBlue();
        borderColor.a = color.getAlpha();
        
        return this;
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // IDecoratorRenderingProvider

    @Override
    public ElkPadding createDecoratorRendering(final KContainerRendering container,
            final KLabel label, final LayoutMode layoutMode) {
        
        KRectangle rectangle = R_FACTORY.createKRectangle();
        container.getChildren().add(rectangle);
        
        // Background color
        KBackground background = R_FACTORY.createKBackground();
        rectangle.getStyles().add(background);
        background.setColor(
                backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        
        // Border
        KForeground border = R_FACTORY.createKForeground();
        rectangle.getStyles().add(border);
        
        if (borderColor != null) {
            border.setColor(borderColor.r, borderColor.g, borderColor.b, borderColor.a);
        } else {
            border.setColor(0, 0, 0, 0);
        }
        
        // Padding
        // CHECKSTYLEOFF MagicNumber
        ElkPadding padding = new ElkPadding();
        if (borderColor != null) {
            // If we have a border, the padding does not depend on the layout direction
            padding.set(2, 2, 3, 2);
        } else {
            boolean horizontalPadding = layoutMode != LayoutMode.VERTICAL;
            boolean verticalPadding = layoutMode != LayoutMode.HORIZONTAL;

            padding.set(
                    verticalPadding ? 2 : 0,
                    horizontalPadding ? 2 : 0,
                    verticalPadding ? 3 : 0,
                    horizontalPadding ? 2 : 0);
        }
        // CHECKSTYLEON MagicNumber
        
        return padding;
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Internals
    
    /**
     * Simple data holder class for color values.
     */
    private static class Color {
        // CHECKSTYLEOFF MagicNumber
        private int r = 255;
        private int g = 255;
        private int b = 255;
        private int a = 220;
    }

}
