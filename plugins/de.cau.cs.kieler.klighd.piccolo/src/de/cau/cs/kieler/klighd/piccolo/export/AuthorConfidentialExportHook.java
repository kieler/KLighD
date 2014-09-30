/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.export;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * Example export hook. Draws a border around the diagram, prints the author and a "TOP SECRET"
 * watermark.
 * 
 * @author csp
 */
public class AuthorConfidentialExportHook implements IExportHook {

    /**
     * 
     */
    private static final int BORDER_LINE_WIDTH = 1;
    private static final int BOTTOM_MARGIN = 30;
    private static final int TOP_LEFT_RIGHT_MARGIN = 20;
    private static final int BORDER_PADDING = 5;
    private static final int WATERMARK_ALPHA = 100;
    private static final double WATERMARK_PADDING_FACTOR = 0.8;

    /**
     * {@inheritDoc}
     */
    public AffineTransform drawPreDiagram(final KlighdSWTGraphicsEx graphics, final PBounds bounds) {
//        graphics.transform(AffineTransform.getTranslateInstance(bounds.x, bounds.y));
        double innerWidth = bounds.width - (2 * TOP_LEFT_RIGHT_MARGIN);
        double innerHeight = bounds.height - (TOP_LEFT_RIGHT_MARGIN + BOTTOM_MARGIN);
        double scaleX = innerWidth / bounds.width;
        double scaleY = innerHeight / bounds.height;
        AffineTransform transform = new AffineTransform();
        transform.translate(TOP_LEFT_RIGHT_MARGIN, TOP_LEFT_RIGHT_MARGIN);
        transform.scale(scaleX, scaleY);
        return transform;
    }

    /**
     * {@inheritDoc}
     */
    public void drawPostDiagram(final KlighdSWTGraphicsEx graphics, final PBounds bounds) {
//        graphics.transform(AffineTransform.getTranslateInstance(bounds.x, bounds.y));
        AffineTransform transform = graphics.getTransform();
        double innerWidth = bounds.width - (2 * TOP_LEFT_RIGHT_MARGIN);
        double innerHeight = bounds.height - (TOP_LEFT_RIGHT_MARGIN + BOTTOM_MARGIN);

        // make border
        graphics.setLineAttributes(new LineAttributes(BORDER_LINE_WIDTH));
        graphics.setStrokeColor(new RGB(0, 0, 0));
        graphics.draw(new Rectangle(TOP_LEFT_RIGHT_MARGIN - BORDER_PADDING, TOP_LEFT_RIGHT_MARGIN
                - BORDER_PADDING, ((int) innerWidth) + 2 * BORDER_PADDING, ((int) innerHeight) + 2
                * BORDER_PADDING));

        // draw author
        graphics.transform(AffineTransform.getTranslateInstance(TOP_LEFT_RIGHT_MARGIN,
                bounds.height - TOP_LEFT_RIGHT_MARGIN));
        graphics.setFont(new FontData());
        graphics.drawText("Author: Max Mustermann");

        // draw confidential

        // font
        FontData font = new FontData();
        font.setStyle(SWT.BOLD);
        graphics.setFont(font);
        graphics.setAlpha(WATERMARK_ALPHA);

        // size & scale
        String confidential = "TOP SECRET";
        graphics.getGC().setFont(new Font(graphics.getDevice(), font));
        Point size = graphics.getGC().textExtent(confidential);
        double scale =
                Math.sqrt(Math.pow(innerWidth, 2) + Math.pow(innerHeight, 2)) / size.x
                        * WATERMARK_PADDING_FACTOR;
        size.x *= scale;
        size.y *= scale;

        // transformations
        graphics.setTransform(transform);
        graphics.transform(AffineTransform.getTranslateInstance(bounds.width / 2.0 - size.x / 2.0,
                bounds.height / 2.0 - size.y / 2.0));
        graphics.transform(AffineTransform.getRotateInstance(innerWidth, -innerHeight,
                size.x / 2.0, size.y / 2.0));
        graphics.transform(AffineTransform.getScaleInstance(scale, scale));
        graphics.drawText(confidential);
    }

}
