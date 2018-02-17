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
import de.cau.cs.kieler.klighd.krendering.KContainerRendering;
import de.cau.cs.kieler.klighd.krendering.KForeground;
import de.cau.cs.kieler.klighd.krendering.KPolyline;
import de.cau.cs.kieler.klighd.krendering.KPosition;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KXPosition;
import de.cau.cs.kieler.klighd.krendering.KYPosition;
import de.cau.cs.kieler.klighd.labels.decoration.EdgeLabelStyleModifier.Visibility;
import de.cau.cs.kieler.klighd.labels.decoration.LabelDecorationConfigurator.LayoutMode;

/**
 * Decorator that adds lines or brackets to a label's rendering. Depending on the
 * {@link LayoutMode layout mode}, the label is either surrounded by lines to its left or right, or
 * to its top or bottom, or both. In the latter case, the lines will dynamically adapt to changes
 * in the layout direction.
 * 
 * @author cds
 */
public final class LinesDecorator extends AbstractDecoratorRenderingProvider {

    /////////////////////////////////////////////////////////////////////////////////////////////
    // Variables
    
    /** Color to use for our lines. */
    private Color lineColor = new Color();
    /** Whether we should draw brackets instead of simple lines. */
    private boolean drawBrackets = false;

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Setup

    /**
     * Use create methods.
     */
    private LinesDecorator() {
    }
    
    /**
     * Create a new instance.
     * 
     * @return new instance.
     */
    public static LinesDecorator create() {
        return new LinesDecorator();
    }
    
    /**
     * Configures the given color to be used for the lines. Defaults to black.
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
    public LinesDecorator withColor(final int r, final int g, final int b, final int a) {
        lineColor.r = r;
        lineColor.g = g;
        lineColor.b = b;
        lineColor.a = a;
        
        return this;
    }
    
    /**
     * Configures the given color to be used for the lines. Defaults to black.
     * 
     * @param color
     *            the color to be used for the lines.
     * @return this decorator for method chaining.
     */
    public LinesDecorator withColor(final java.awt.Color color) {
        lineColor.r = color.getRed();
        lineColor.g = color.getGreen();
        lineColor.b = color.getBlue();
        lineColor.a = color.getAlpha();
        
        return this;
    }
    
    /**
     * Sets whether we should draw brackets or simple lines. We default to simple lines.
     * 
     * @param brackets
     *            {@code true} if brackets should be drawn.
     * @return this decorator for method chaining.
     */
    public LinesDecorator withBrackets(final boolean brackets) {
        this.drawBrackets = brackets;
        
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
        
        ElkPadding padding = new ElkPadding();
        
        if (topBottom) {
            // Setup padding
            padding.top = 2;
            padding.bottom = 3;
            
            KRendering topLine = createTopLine();
            container.getChildren().add(topLine);
            
            KRendering bottomLine = createBottomLine();
            container.getChildren().add(bottomLine);
            
            if (layoutMode == LayoutMode.BOTH) {
                EdgeLabelStyleModifier.install(topLine, Visibility.SEGMENT_VERTICAL);
                EdgeLabelStyleModifier.install(bottomLine, Visibility.SEGMENT_VERTICAL);
            }
        }
        
        if (leftRight) {
            // Setup padding
            padding.left = 2;
            padding.right = 2;
            padding.bottom = Math.max(padding.bottom, 1);
            
            KRendering leftLine = createLeftLine();
            container.getChildren().add(leftLine);
            
            KRendering rightLine = createRightLine();
            container.getChildren().add(rightLine);
            
            if (layoutMode == LayoutMode.BOTH) {
                EdgeLabelStyleModifier.install(leftLine, Visibility.SEGMENT_HORIZONTAL);
                EdgeLabelStyleModifier.install(rightLine, Visibility.SEGMENT_HORIZONTAL);
            }
        }
        
        return padding;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    // Direction-specific

    private KRendering createLeftLine() {
        KPolyline line = createInitializedPolyline();
        
        // Line points
        if (drawBrackets) {
            addPoint(line, right(0, 0), top(0, 0));
        }
        
        addPoint(line, left(0, 0), top(0, 0));
        addPoint(line, left(0, 0), bottom(0, 0));
        
        if (drawBrackets) {
            addPoint(line, right(0, 0), bottom(0, 0));
        }
        
        // Setup placement
        setPlacement(line, left(0, 0), top(0, 0), left(3, 0), bottom(0, 0));
        
        return line;
    }

    private KRendering createRightLine() {
        KPolyline line = createInitializedPolyline();
        
        // Line points
        if (drawBrackets) {
            addPoint(line, left(0, 0), top(0, 0));
        }
        
        addPoint(line, right(0, 0), top(0, 0));
        addPoint(line, right(0, 0), bottom(0, 0));
        
        if (drawBrackets) {
            addPoint(line, left(0, 0), bottom(0, 0));
        }
        
        // Setup placement
        setPlacement(line, right(3, 0), top(0, 0), right(0, 0), bottom(0, 0));
        
        return line;
    }

    private KRendering createTopLine() {
        KPolyline line = createInitializedPolyline();
        
        // Line points
        if (drawBrackets) {
            addPoint(line, left(0, 0), bottom(0, 0));
        }
        
        addPoint(line, left(0, 0), top(0, 0));
        addPoint(line, right(0, 0), top(0, 0));
        
        if (drawBrackets) {
            addPoint(line, right(0, 0), bottom(0, 0));
        }
        
        // Setup placement
        setPlacement(line, left(0, 0), top(0, 0), right(0, 0), top(3, 0));
        
        return line;
    }

    private KRendering createBottomLine() {
        KPolyline line = createInitializedPolyline();
        
        // Line points
        if (drawBrackets) {
            addPoint(line, left(0, 0), top(0, 0));
        }
        
        addPoint(line, left(0, 0), bottom(0, 0));
        addPoint(line, right(0, 0), bottom(0, 0));
        
        if (drawBrackets) {
            addPoint(line, right(0, 0), top(0, 0));
        }
        
        // Setup placement
        setPlacement(line, left(0, 0), bottom(3, 0), right(0, 0), bottom(0, 0));
        
        return line;
    }
    
    // CHECKSTYLEON MagicNumber


    /////////////////////////////////////////////////////////////////////////////////////////////
    // Line Utility Methods
    
    /**
     * Creates a polyline initialized with the settings necessary for all polylines.
     */
    private KPolyline createInitializedPolyline() {
        KPolyline line = R_FACTORY.createKPolyline();
        
        // Line color
        KForeground fg = R_FACTORY.createKForeground();
        line.getStyles().add(fg);
        fg.setColor(lineColor.r, lineColor.g, lineColor.b, lineColor.a);
        
        return line;
    }
    
    /**
     * Adds a point to a polyline.
     */
    private void addPoint(final KPolyline line, final KXPosition<?> xPos,
            final KYPosition<?> yPos) {
        
        KPosition pos = R_FACTORY.createKPosition();
        pos.setX(xPos);
        pos.setY(yPos);
        line.getPoints().add(pos);
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
