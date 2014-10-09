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
import java.awt.geom.Rectangle2D;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.LineAttributes;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;

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
 *          supportedFormats="bmp, jpeg, png, svg, pdf, printout">
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
    public void setViewContext(final ViewContext viewContext) {
    }

    /**
     * {@inheritDoc}
     */
    public AffineTransform drawPreDiagram(final KlighdSWTGraphics graphics, final Rectangle2D bounds) {

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
    public void drawPostDiagram(final KlighdSWTGraphics graphics, final Rectangle2D bounds) {

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
        graphics.setFont(new FontData("Arial", KlighdConstants.DEFAULT_FONT_SIZE,
                KlighdConstants.DEFAULT_FONT_STYLE_SWT));
        graphics.drawText("Author: Max Mustermann");

    }

    /**
     * {@inheritDoc}
     */
    public void drawPostDiagramTile(final KlighdSWTGraphics graphics, final Rectangle2D bounds) {
        // draw confidential
        final double innerWidth = bounds.getWidth() - (2 * TOP_LEFT_RIGHT_MARGIN);
        final double innerHeight = bounds.getHeight() - (TOP_LEFT_RIGHT_MARGIN + BOTTOM_MARGIN);

        // font
        final FontData font = new FontData();
        font.setStyle(SWT.BOLD);
        graphics.setFont(font);
        graphics.setAlpha(WATERMARK_ALPHA);

        // size & scale
        final String confidential = "Confidential";
        final Rectangle size =
                PlacementUtil.estimateTextSize(font, confidential).setBoundsOf(new Rectangle());

        final double scale = Math.sqrt(Math.pow(innerWidth, 2) + Math.pow(innerHeight, 2))
                / size.width * WATERMARK_PADDING_FACTOR;

        size.width *= scale;
        size.height *= scale;

        // transformations
        graphics.transform(AffineTransform.getTranslateInstance(
                bounds.getWidth() / 2d - size.width / 2d, bounds.getHeight() / 2d - size.height / 2d));
        graphics.transform(AffineTransform.getRotateInstance(
                innerWidth, -innerHeight, size.width / 2d, size.height / 2d));
        graphics.transform(AffineTransform.getScaleInstance(scale, scale));

        graphics.drawText(confidential);
    }
}
