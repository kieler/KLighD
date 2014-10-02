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

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Point;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;

/**
 * Example export hook. Draws a border around the diagram, prints the author and a "TOP SECRET"
 * watermark.
 *
 * Registration is to be done in plugin.xml like
 * <pre>
 * {@code
 * <extension
 *       point="de.cau.cs.kieler.klighd.piccolo.exportHooks">
 *    <exportHook
 *          class="de.cau.cs.kieler.klighd.piccolo.export.AuthorConfidentialExportHook"
 *          id="de.cau.cs.kieler.klighd.piccolo.export.AuthorConfidentialExportHook"
 *          supportedFormats="bmp, jpeg, png, svg, pdf">
 *    </exportHook>
 * </extension>}
 * </pre>
 *
 * @author csp
 */
public class AuthorConfidentialExportHook implements IExportHook {

    private static final int BORDER_LINE_WIDTH = 1;
    private static final int BOTTOM_MARGIN = 30;
    private static final int TOP_LEFT_RIGHT_MARGIN = 20;
    private static final int BORDER_PADDING = 5;
    private static final int WATERMARK_ALPHA = 100;
    private static final double WATERMARK_PADDING_FACTOR = 0.8;

    /**
     * {@inheritDoc}
     */
    public AffineTransform drawPreDiagram(final KlighdSWTGraphicsEx graphics, final Rectangle2D bounds) {

        final double innerWidth = bounds.getWidth() - (2 * TOP_LEFT_RIGHT_MARGIN);
        final double innerHeight = bounds.getHeight() - (TOP_LEFT_RIGHT_MARGIN + BOTTOM_MARGIN);
        final double scaleX = innerWidth / bounds.getWidth();
        final double scaleY = innerHeight / bounds.getHeight();

        final AffineTransform transform = new AffineTransform();
        transform.translate(TOP_LEFT_RIGHT_MARGIN, TOP_LEFT_RIGHT_MARGIN);
        transform.scale(scaleX, scaleY);
        return transform;
    }

    /**
     * {@inheritDoc}
     */
    public void drawPostDiagram(final KlighdSWTGraphicsEx graphics, final Rectangle2D bounds) {

        final AffineTransform transform = graphics.getTransform();
        final double innerWidth = bounds.getWidth() - (2 * TOP_LEFT_RIGHT_MARGIN);
        final double innerHeight = bounds.getHeight() - (TOP_LEFT_RIGHT_MARGIN + BOTTOM_MARGIN);

        // make border
        graphics.setLineAttributes(new LineAttributes(BORDER_LINE_WIDTH));
        graphics.setStrokeColor(KlighdConstants.BLACK);
        graphics.draw(new Rectangle2D.Double(
                TOP_LEFT_RIGHT_MARGIN - BORDER_PADDING, TOP_LEFT_RIGHT_MARGIN - BORDER_PADDING,
                innerWidth + 2 * BORDER_PADDING, innerHeight + 2 * BORDER_PADDING));

        // draw author
        graphics.transform(AffineTransform.getTranslateInstance(TOP_LEFT_RIGHT_MARGIN,
                bounds.getHeight() - TOP_LEFT_RIGHT_MARGIN));
        graphics.setFont(new FontData());
        graphics.drawText("Author: Max Mustermann");

        // draw confidential

        // font
        final FontData font = new FontData();
        font.setStyle(SWT.BOLD);
        graphics.setFont(font);
        graphics.setAlpha(WATERMARK_ALPHA);

        // size & scale
        final String confidential = "TOP SECRET";
        final Point size = graphics.getGC().textExtent(confidential);

        final double scale = Math.sqrt(
                Math.pow(innerWidth, 2) + Math.pow(innerHeight, 2)) / size.x * WATERMARK_PADDING_FACTOR;

        size.x *= scale;
        size.y *= scale;

        // transformations
        graphics.setTransform(transform);
        graphics.transform(
                AffineTransform.getTranslateInstance(
                        bounds.getWidth() / 2d - size.x / 2d, bounds.getHeight() / 2d - size.y / 2d));
        graphics.transform(
                AffineTransform.getRotateInstance(innerWidth, -innerHeight, size.x / 2d, size.y / 2d));
        graphics.transform(
                AffineTransform.getScaleInstance(scale, scale));
        graphics.drawText(confidential);
    }
}
