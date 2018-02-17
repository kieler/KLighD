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
package de.cau.cs.kieler.klighd.labels.decoration;

import org.eclipse.elk.core.math.ElkPadding;

import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.krendering.KBackground;
import de.cau.cs.kieler.klighd.krendering.KContainerRendering;
import de.cau.cs.kieler.klighd.krendering.KForeground;
import de.cau.cs.kieler.klighd.krendering.KPolygon;
import de.cau.cs.kieler.klighd.krendering.KPosition;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KXPosition;
import de.cau.cs.kieler.klighd.krendering.KYPosition;
import de.cau.cs.kieler.klighd.labels.decoration.EdgeLabelStyleModifier.Visibility;
import de.cau.cs.kieler.klighd.labels.decoration.LabelDecorationConfigurator.LayoutMode;

/**
 * Surrounds labels with directional arrows the point to the edge head.
 * 
 * @author cds
 */
public final class DirectionalArrowsDecorator extends AbstractDecoratorRenderingProvider {

    /////////////////////////////////////////////////////////////////////////////////////////////
    // Variables
    
    /** Color to use for our arrows. */
    private Color arrowColor = new Color();

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Setup

    /**
     * Use create methods.
     */
    private DirectionalArrowsDecorator() {
    }
    
    /**
     * Create a new instance.
     * 
     * @return new instance.
     */
    public static DirectionalArrowsDecorator create() {
        return new DirectionalArrowsDecorator();
    }
    
    /**
     * Configures the given color to be used for the arrows. Defaults to black.
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
    public DirectionalArrowsDecorator withColor(final int r, final int g, final int b, final int a) {
        arrowColor.r = r;
        arrowColor.g = g;
        arrowColor.b = b;
        arrowColor.a = a;
        
        return this;
    }
    
    /**
     * Configures the given color to be used for the arrows. Defaults to black.
     * 
     * @param color
     *            the color to be used for the arrows.
     * @return this decorator for method chaining.
     */
    public DirectionalArrowsDecorator withColor(final java.awt.Color color) {
        arrowColor.r = color.getRed();
        arrowColor.g = color.getGreen();
        arrowColor.b = color.getBlue();
        arrowColor.a = color.getAlpha();
        
        return this;
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // IDecoratorRenderingProvider
    
    // CHECKSTYLEOFF MagicNumber
    // We're using magic numbers for coordinates.

    @Override
    public ElkPadding createDecoratorRendering(final KContainerRendering container,
            final KLabel label, final LayoutMode layoutMode) {
        
        boolean topBottom = layoutMode != LayoutMode.HORIZONTAL;
        boolean leftRight = layoutMode != LayoutMode.VERTICAL;
        
        if (topBottom) {
            container.getChildren().add(createUpArrow());
            container.getChildren().add(createDownArrow());
        }
        
        if (leftRight) {
            container.getChildren().add(createLeftArrow());
            container.getChildren().add(createRightArrow());
        }
        
        // We're on the outside of society
        return new ElkPadding();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    // Direction-specific

    private KRendering createLeftArrow() {
        KPolygon arrow = createInitializedPolygon();
        
        // Arrow points
        addPoint(arrow, right(0, 0), top(-4, 0.5f));
        addPoint(arrow, right(0, 0), top(4, 0.5f));
        addPoint(arrow, left(0, 0), top(0, 0.5f));
        addPoint(arrow, right(0, 0), top(-4, 0.5f));

        // Setup placement and visibility
        setPlacement(arrow, left(-4, 0), top(0, 0), left(0, 0), bottom(0, 0));
        EdgeLabelStyleModifier.install(arrow, Visibility.DIRECTION_LEFT);
        
        return arrow;
    }

    private KRendering createRightArrow() {
        KPolygon arrow = createInitializedPolygon();
        
        // Arrow points
        addPoint(arrow, left(0, 0), top(-4, 0.5f));
        addPoint(arrow, left(0, 0), top(4, 0.5f));
        addPoint(arrow, right(0, 0), top(0, 0.5f));
        addPoint(arrow, left(0, 0), top(-4, 0.5f));

        // Setup placement and visibility
        setPlacement(arrow, right(0, 0), top(0, 0), right(-4, 0), bottom(0, 0));
        EdgeLabelStyleModifier.install(arrow, Visibility.DIRECTION_RIGHT);
        
        return arrow;
    }

    private KRendering createUpArrow() {
        KPolygon arrow = createInitializedPolygon();
        
        // Arrow points
        addPoint(arrow, left(-4, 0.5f), bottom(0, 0));
        addPoint(arrow, left(4, 0.5f), bottom(0, 0));
        addPoint(arrow, right(0, 0.5f), top(0, 0));
        addPoint(arrow, left(-4, 0.5f), bottom(0, 0));
        
        // Setup placement and visibility
        setPlacement(arrow, left(0, 0), top(-4, 0), right(0, 0), top(0, 0));
        EdgeLabelStyleModifier.install(arrow, Visibility.DIRECTION_UP);
        
        return arrow;
    }

    private KRendering createDownArrow() {
        KPolygon arrow = createInitializedPolygon();
        
        // Arrow points
        addPoint(arrow, left(-4, 0.5f), top(0, 0));
        addPoint(arrow, left(4, 0.5f), top(0, 0));
        addPoint(arrow, right(0, 0.5f), bottom(0, 0));
        addPoint(arrow, left(-4, 0.5f), top(0, 0));

        // Setup placement and visibility
        setPlacement(arrow, left(0, 0), bottom(0, 0), right(0, 0), bottom(-4, 0));
        EdgeLabelStyleModifier.install(arrow, Visibility.DIRECTION_DOWN);
        
        return arrow;
    }
    
    // CHECKSTYLEON MagicNumber


    /////////////////////////////////////////////////////////////////////////////////////////////
    // Line Utility Methods
    
    /**
     * Creates a polyline initialized with the settings necessary for all polylines.
     */
    private KPolygon createInitializedPolygon() {
        KPolygon poly = R_FACTORY.createKPolygon();
        
        // Color
        KBackground bg = R_FACTORY.createKBackground();
        poly.getStyles().add(bg);
        bg.setColor(arrowColor.r, arrowColor.g, arrowColor.b, arrowColor.a);
        
        KForeground fg = R_FACTORY.createKForeground();
        poly.getStyles().add(fg);
        fg.setColor(arrowColor.r, arrowColor.g, arrowColor.b, arrowColor.a);
        
        return poly;
    }
    
    /**
     * Adds a point to a polyline.
     */
    private void addPoint(final KPolygon poly, final KXPosition<?> xPos,
            final KYPosition<?> yPos) {
        
        KPosition pos = R_FACTORY.createKPosition();
        pos.setX(xPos);
        pos.setY(yPos);
        poly.getPoints().add(pos);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    // Internals
    
    /**
     * Simple data holder class for color values.
     */
    private static class Color {
        // CHECKSTYLEOFF MagicNumber
        private int r = 0;
        private int g = 0;
        private int b = 0;
        private int a = 255;
    }

}
