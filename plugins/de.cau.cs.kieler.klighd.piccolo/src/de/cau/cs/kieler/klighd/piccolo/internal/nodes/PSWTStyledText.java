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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.util.RGBGradient;

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
    
    private int penAlpha = KlighdConstants.ALPHA_FULL_OPAQUE;
    private RGB penColor = KlighdConstants.BLACK;
    
    private int penPaintAlpha = KlighdConstants.ALPHA_FULL_OPAQUE;
    private RGB penPaint = null;
    private RGBGradient penPaintGradient = null;
    
    private int underlining = KlighdConstants.NO_FONT_UNDERLINING;
    private RGB underlineColor = KlighdConstants.BLACK;
    private boolean strikeout = false;
    private RGB strikeoutColor = KlighdConstants.BLACK;

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
        super(theLines, new SWTFontWrappingFont(theFont != null ? theFont
                : KlighdConstants.DEFAULT_FONT));
        this.fontData = theFont != null ? theFont : KlighdConstants.DEFAULT_FONT;
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
     * Returns the current pen color gradient.
     * 
     * @return current pen color gradient
     */
    public RGBGradient getSWTPenColorGradient() {
        return null;
    }

    /**
     * Sets the current pen color.
     * 
     * @param color
     *            use this color.
     */
    public void setPenColor(final RGB color) {
        if (penColor.equals(color)) {
            return;
        }        
//        Object oldPaint = penColor;
        penColor = color;
//        repaint();
//        firePropertyChange(PText.PROPERTY_CODE_TEXT_PAINT, PROPERTY_PAINT, oldPaint, penColor);
    }

    /**
     * Sets the current pen color alpha.
     * 
     * @param alpha
     *            use this alpha.
     */
    public void setPenAlpha(final int alpha) {
        penAlpha = alpha;
//        repaint();
    }

    private static final String TEXT_GRADIENT_MESSAGE = "KLighD (Piccolo2D): A color gradient has been"
            + " tried to set as pen color of a text field. This currently not supported by SWT."
            + " Thus, this setting will have no effect.";

    /**
     * Sets the current pen color gradient.<br>
     * <b>This is currently not supported by SWT!</b>
     * 
     * @param gradient gradient that this node should use drawing the text.
     */
    public void setPenColor(final RGBGradient gradient) {
        StatusManager.getManager().handle(
                new Status(IStatus.WARNING, KlighdPiccoloPlugin.PLUGIN_ID, TEXT_GRADIENT_MESSAGE),
                StatusManager.LOG);
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
     * Returns the paint gradient used while painting this node. This value may be null.
     * 
     * @return the paint gradient used while painting this node.
     */
    public RGBGradient getSWTPaintGradient() {
        return penPaintGradient;
    }

    /**
     * Sets the current pen paint.
     * 
     * @param color
     *            use this color.
     */
    public void setPenPaint(final RGB color) {
        if (penPaint != null && penPaint.equals(color)) {
            return;
        }        
//        Object oldPaint = penPaint;
        penPaintGradient = null;
        penPaint = color;
//        repaint();
//        firePropertyChange(PROPERTY_CODE_PAINT, PROPERTY_PAINT, oldPaint, penPaint);
    }

    /**
     * Sets the current pen paint alpha.
     * 
     * @param alpha
     *            use this alpha.
     */
    public void setPenPaintAlpha(final int alpha) {
        penPaintAlpha = alpha;
//        repaint();
    }
    
    /**
     * Set the paint used to paint this node, which may be null.
     * 
     * @param newPaint paint that this node should use when painting itself.
     */
    public void setPaint(final RGBGradient newPaint) {        
        if (penPaintGradient != null && penPaintGradient.equals(newPaint)) {
            return;
        }
//        Object oldPaint = null;
//        if (penPaintGradient != null) {
//            oldPaint = penPaintGradient;   
//        } else if (penPaint != null) {
//            oldPaint = penPaint;
            penPaint = null;
//        }
        penPaintGradient = newPaint;
//        repaint();
//        firePropertyChange(PROPERTY_CODE_PAINT, PROPERTY_PAINT, oldPaint, penPaintGradient);
    }

    /**
     * Configures the text node width a {@link Font}.
     * 
     * @param theFont
     *            the desired {@link Font}, must not be <code>null<code>
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
     * Augments the text node with an underline of the given type and color.
     * 
     * @param theUnderlining
     *            the related constant from {@link KlighdConstants} and {@link org.eclipse.swt.SWT SWT}
     * @param color
     *            the used color, maybe <code>null<code> (penColor is used in that case)
     */
    public void setUnderline(final int theUnderlining, final RGB color) {
        this.underlining = theUnderlining;
        this.underlineColor = color != null ? color : this.penColor;
//        repaint();
    }

    /**
     * Augments the text node with a strikeout of the given color.
     * 
     * @param theStrikeout
     *            whether to strike out
     * @param color
     *            the used color, maybe <code>null<code> (penColor is used in that case)
     */
    public void setStrikeout(final boolean theStrikeout, final RGB color) {
        this.strikeout = theStrikeout;
        this.strikeoutColor = color != null ? color : this.penColor;
//        repaint();
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
        final SWTGraphics2D g2 = (SWTGraphics2D) ppc.getGraphics();

        final int currentAlpha = g2.getAlpha();
        final float currentAlphaFloat = (float) currentAlpha;

        if (!isTransparent()) {
            RGB p = getSWTPenPaint();
            RGBGradient pg = getSWTPaintGradient();
            if (pg != null && g2 instanceof KlighdSWTGraphics) {
                ((KlighdSWTGraphics) g2).setBackgroundPattern(pg, getBounds());
                
            } else if (p != null) {
                g2.setAlpha((int) (penPaintAlpha
                        * (currentAlphaFloat / KlighdConstants.ALPHA_FULL_OPAQUE)));
                g2.setBackground(getSWTPenPaint());
            }

            if (p != null || pg != null) {
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        }

        g2.translate(padding, padding);
        g2.setUnderline(underlining, underlineColor);
        g2.setStrikeout(strikeout, strikeoutColor);
        
        RGB c = getSWTPenColor();
        RGBGradient cg = getSWTPenColorGradient();
        if (cg != null && g2 instanceof KlighdSWTGraphics) {
            ((KlighdSWTGraphics) g2).setPattern(cg, getBounds());
            
        } else if (c != null) {
            g2.setAlpha((int) (penAlpha * (currentAlphaFloat / KlighdConstants.ALPHA_FULL_OPAQUE)));
            g2.setColor(c);

        } else {
            g2.setColor(KlighdConstants.BLACK);
        }
        
        if (fontData != null) {
            g2.setFont(fontData);
        }        

        double y = 0;
        final FontMetrics fontMetrics = g2.getSWTFontMetrics();

        for (String line : lines) {
            if (line.length() != 0) {
                g2.drawText(line, 0, y);
            }
            y += fontMetrics.getHeight();
        }

        g2.translate(-padding, -padding);
        g2.setAlpha(currentAlpha);
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
