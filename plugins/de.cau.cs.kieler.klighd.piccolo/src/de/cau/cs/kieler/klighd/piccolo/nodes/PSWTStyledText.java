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
package de.cau.cs.kieler.klighd.piccolo.nodes;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import de.cau.cs.kieler.klighd.KlighdConstants;

import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.swt.PSWTText;
import edu.umd.cs.piccolox.swt.SWTGraphics2D;

/**
 * A specialization of {@link PSWTText} supporting {@link org.eclipse.swt.graphics.TextStyle
 * TextStyles}.
 * 
 * @author chsch
 */
public class PSWTStyledText extends PSWTText {

    private static final long serialVersionUID = -4463204146476543138L;

    private FontData fontData = null;
    private RGB penColor = KlighdConstants.BLACK;
    private RGB penPaint = null;
    private RGB underlineColor = KlighdConstants.BLACK;
    private int underlining = KlighdConstants.NO_FONT_UNDERLINING;

    /**
     * PSWTStyledText constructor taking the initial text lines.
     * 
     * @param theLines
     *            The initial text.
     */
    public PSWTStyledText(final List<String> theLines) {
        this(theLines, KlighdConstants.DEFAULT_FONT);
    }

    /**
     * PSWTStyledText constructor taking the initial text lines and font configuration.
     * 
     * @param theLines
     *            The initial text.
     * @param theFont
     *            The SWT font configuration for this text component.
     */
    public PSWTStyledText(final List<String> theLines, final FontData theFont) {
        super(theLines, new SWTFontWrappingFont(theFont));
    }

    /**
     * Returns the current pen color.
     * 
     * @return current pen color
     */
    public RGB getSWTPenColor() {
        return penColor;
    }

    /**
     * Sets the current pen color.
     * 
     * @param color
     *            use this color.
     */
    public void setPenColor(final RGB color) {
        penColor = color;
        repaint();
    }

    /**
     * Returns the current pen color.
     * 
     * @return current pen color
     */
    public RGB getSWTPenPaint() {
        return penPaint;
    }

    /**
     * Sets the current pen paint.
     * 
     * @param color
     *            use this color.
     */
    public void setPenPaint(final RGB color) {
        penPaint = color;
        repaint();
    }

    /**
     * Configures the text node width a {@link Font}.
     * 
     * @param theFont
     *            the desired {@link Font}
     */
    public void setFont(final FontData theFont) {
        this.fontData = theFont;
        super.setFont(new SWTFontWrappingFont(theFont));
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated chsch: Don't use this setter within KLighD - use {@link #setFont(FontData)}
     *             instead!
     */
    public void setFont(final java.awt.Font aFont) {
        super.setFont(aFont);
    }

    /**
     * Configures the text node width a {@link Font}.
     * 
     * @param theUnderlining
     *            the related constant from {@link KlighdConstants} and {@link org.eclipse.swt.SWT SWT}
     */
    public void setUnderlining(final int theUnderlining) {
        this.underlining = theUnderlining;
    }

    @Override
    public void paint(final PPaintContext ppc) {
        if (lines.isEmpty()) {
            return;
        }

        final Graphics2D g2 = ppc.getGraphics();
        AffineTransform at = null;
        boolean translated = false;

        if (translateX != 0.0 || translateY != 0.0) {
            at = g2.getTransform();
            g2.translate(translateX, translateY);
            translated = true;
        }

        paintAsText(ppc);

        if (translated) {
            g2.setTransform(at);
        }
    }

    @Override
    public void paintAsText(final PPaintContext ppc) {
        final SWTGraphics2D sg2 = (SWTGraphics2D) ppc.getGraphics();

        if (!isTransparent()) {
            if (getSWTPenPaint() == null) {
                sg2.setBackground(KlighdConstants.WHITE);
            } else {
                sg2.setBackground(getSWTPenPaint());
            }

            sg2.fillRect(0, 0, getWidth(), getHeight());
        }

        sg2.translate(padding, padding);
        sg2.setUnderlining(underlining, underlineColor);
        
        if (penColor != null) {
            sg2.setColor(penColor);
        }        
        if (fontData != null) {
            sg2.setFont(fontData);
        }        

        double y = 0;
        final FontMetrics fontMetrics = sg2.getSWTFontMetrics();

        for (String line : lines) {
            if (line.length() != 0) {
                sg2.drawText(line, 0, y);
            }
            y += fontMetrics.getHeight();
        }

        sg2.translate(-padding, -padding);
    }

    /**
     * {@inheritDoc}.<br>
     * <br>
     * In this case the method is overridden in order to reveal the SWT font from the custom AWT
     * font in order avoid the lousy AWT/SWT font conversion.
     */
    protected SWTGraphics2D createtemporayGraphics(final GC gc) {
        return new SWTGraphics2D(gc, Display.getDefault()) {

            @Override
            public void setFont(final java.awt.Font theFont) {
                if (theFont instanceof SWTFontWrappingFont) {
                    this.setFont(((SWTFontWrappingFont) theFont).getSWTFontData());
                } else {
                    super.setFont(theFont);
                }
            }
        };
    }

    /**
     * 
     * @author chsch
     * 
     */
    private static class SWTFontWrappingFont extends java.awt.Font {

        private static final long serialVersionUID = 1L;

        private FontData fontData = null;

        public SWTFontWrappingFont(final FontData theFont) {
            super(Collections.<Attribute, Object>emptyMap());
            this.fontData = theFont;
        }

        public FontData getSWTFontData() {
            return this.fontData;
        }
    }

}
