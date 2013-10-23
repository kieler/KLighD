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

import java.awt.geom.Rectangle2D;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.base.Strings;

import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.util.RGBGradient;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * The KLighD-specific {@link PNode} implementation for displaying text strings supporting
 * {@link org.eclipse.swt.graphics.TextStyle TextStyles}.<br>
 * It is inspired by the Piccolo2D {@link edu.umd.cs.piccolox.swt.PSWTText PSWTText} and is
 * tailored/extended to those features required by KLighD.<br>
 * <br>
 * It enables proper view-model-tracing by preserving the related {@link KText} view model element
 * and implementing {@link ITracingElement}.<br>
 * <br>
 * <b>Note:</b> All <code>invalidate...</code> and <code>repaint</code> calls are deactivated in
 * order to avoid superfluous repaint activities. The repaint events are fired by
 * {@link #addChild(PNode)}/{@link #removeChild(PNode)} in case of rendering changes, by
 * {@link #setBounds(double, double, double, double)} in case of layout changes, and in case of pure
 * style changes by the {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement} rendering
 * controllers ({@link de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController
 * #updateStyles() AbstractKGERenderingController#updateStyles()}) after all rendering and style
 * changes are performed.
 * 
 * @author chsch
 */
public class KlighdStyledText extends PNode implements ITracingElement<KText> {

    private static final long serialVersionUID = -4463204146476543138L;

    private KText kText = null;

    private String text = "";
    
    private FontData fontData = null;
    
    private int penAlpha = KlighdConstants.ALPHA_FULL_OPAQUE;
    private RGB penColor = KlighdConstants.BLACK;
    
    private int paintAlpha = KlighdConstants.ALPHA_FULL_OPAQUE;
    private RGB paint = null;
    private RGBGradient paintGradient = null;
    
    private int underlining = KlighdConstants.NO_FONT_UNDERLINING;
    private RGB underlineColor = KlighdConstants.BLACK;
    private boolean strikeout = false;
    private RGB strikeoutColor = KlighdConstants.BLACK;

    /**
     * Constructor taking the related {@link KText} view model element.
     * 
     * @param theKText
     *            The KText view model element containing the string to be displayed.
     */
    public KlighdStyledText(final KText theKText) {
        this(theKText.getText(), KlighdConstants.DEFAULT_FONT);
        this.kText = theKText;
    }

    /**
     * Constructor taking the initial text.
     * 
     * @param theText
     *            The initial text.
     */
    public KlighdStyledText(final String theText) {
        this(theText, KlighdConstants.DEFAULT_FONT);
    }

    /**
     * Constructor taking the initial text and font configuration.
     * 
     * @param theText
     *            The initial text.
     * @param theFont
     *            The SWT {@link FontData} configuration for this text component.
     */
    public KlighdStyledText(final String theText, final FontData theFont) {
        this.text = theText;
        this.setFont(theFont != null ? theFont : KlighdConstants.DEFAULT_FONT);
    }

    /**
     * Get the text that is displayed by this node.
     * @return The text that is displayed.
     */
    public String getText() {
        return text;
    }

    /**
     * Updates the text string to be displayed by this node.<br>
     * <code>theText</code> may be empty or contain line breaks.
     * 
     * @param theText
     *            The text string to be displayed.
     */
    public void setText(final String theText) {
        this.text = theText;
        updateBounds();
    }

    /**
     * {@inheritDoc}
     */
    public KText getGraphElement() {
        return this.kText;
    }

    /**
     * Provides the currently set pen color.<br>
     * It's currently used in order configure the text widget enabling the cursor-based text
     * selection, see
     * {@link de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer.KlighdTextInputHandler
     * PiccoloViewer.KlighdTextInputHandler}.
     * 
     * @return the current pen color {@link RGB}.
     */
    public RGB getPenColor() {
        return penColor;
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
        // Object oldPaint = penColor;
        penColor = color;
        // repaint();
        // firePropertyChange(PText.PROPERTY_CODE_TEXT_PAINT, PROPERTY_PAINT, oldPaint, penColor);
    }

    /**
     * Sets the current pen color alpha.
     * 
     * @param alpha
     *            use this alpha.
     */
    public void setPenAlpha(final int alpha) {
        penAlpha = alpha;
        // repaint();
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
     * Sets the current pen paint.
     * 
     * @param color
     *            use this color.
     */
    public void setPaint(final RGB color) {
        if (paint != null && paint.equals(color)) {
            return;
        }        
        // Object oldPaint = penPaint;
        paintGradient = null;
        paint = color;
        // repaint();
        // firePropertyChange(PROPERTY_CODE_PAINT, PROPERTY_PAINT, oldPaint, penPaint);
    }

    /**
     * Sets the current pen paint alpha.
     * 
     * @param alpha
     *            use this alpha.
     */
    public void setPaintAlpha(final int alpha) {
        paintAlpha = alpha;
        // repaint();
    }
    
    /**
     * Set the paint used to paint this node, which may be null.
     * 
     * @param newPaint paint that this node should use when painting itself.
     */
    public void setPaint(final RGBGradient newPaint) {        
        if (paintGradient != null && paintGradient.equals(newPaint)) {
            return;
        }
        // Object oldPaint = null;
        // if (penPaintGradient != null) {
        //      oldPaint = penPaintGradient;   
        // } else if (penPaint != null) {
        //      oldPaint = penPaint;
        paint = null;
        // }
        paintGradient = newPaint;
        // repaint();
        // firePropertyChange(PROPERTY_CODE_PAINT, PROPERTY_PAINT, oldPaint, penPaintGradient);
    }

    /**
     * Provides the currently set {@link FontData}.<br>
     * It's currently used in order configure the text widget enabling the cursor-based text
     * selection, see
     * {@link de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer.KlighdTextInputHandler
     * PiccoloViewer.KlighdTextInputHandler}.
     * 
     * @return the current {@link FontData}
     */
    public FontData getFontData() {
        return fontData;
    }

    /**
     * Configures the text node width a {@link FontData}.
     * 
     * @param theFont
     *            the desired {@link FontData}, must not be <code>null<code>
     */
    public void setFont(final FontData theFont) {
        this.fontData = theFont;
        this.updateBounds();
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
        // repaint();
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
        // repaint();
    }

    /**
     * Updates the bounds of <code>this</code> node based on the current {@link #text} and
     * {@link #fontData} by delegating to {@link PlacementUtil#estimateTextSize(FontData, String)}.
     */
    protected void updateBounds() {
        this.setBounds(PlacementUtil.estimateTextSize(this.fontData, this.text).toRectangle2D());
    }
    
    /**
     * A singleton {@link Rectangle2D} that is used for drawing the text background by means of
     * {@link KlighdSWTGraphics#fill(java.awt.Shape)}.
     */
    private static final Rectangle2D BACKGROUND = new Rectangle2D.Double();

    @Override
    public void paint(final PPaintContext ppc) {
        if (Strings.isNullOrEmpty(this.text)) {
            return;
        }

        final KlighdSWTGraphics graphics = (KlighdSWTGraphics) ppc.getGraphics();

        final int currentAlpha = graphics.getAlpha();
        final float currentAlphaFloat = (float) currentAlpha;

        if (this.paintGradient != null) {
            graphics.setFillPattern(this.paintGradient, getBounds());
        } else if (this.paint != null) {
            graphics.setAlpha((int) (paintAlpha
                    * (currentAlphaFloat / KlighdConstants.ALPHA_FULL_OPAQUE)));
            graphics.setFillColor(this.paint);
        }

        if (this.paint != null || this.paintGradient != null) {
            BACKGROUND.setFrame(0, 0, getWidth(), getHeight());
            graphics.setLineAttributes(KlighdConstants.DEFAULT_LINE_ATTRIBUTES);
            graphics.fill(BACKGROUND);
        }

        graphics.setUnderline(underlining, underlineColor);
        graphics.setStrikeout(strikeout, strikeoutColor);
        
        if (this.penColor != null) {
            graphics.setAlpha(
                    (int) (penAlpha * (currentAlphaFloat / KlighdConstants.ALPHA_FULL_OPAQUE)));
            graphics.setStrokeColor(this.penColor);

        } else {
            graphics.setStrokeColor(KlighdConstants.BLACK);
        }
        
        if (fontData != null) {
            graphics.setFont(fontData);
        } else {
            graphics.setFont(KlighdConstants.DEFAULT_FONT);
        }

        graphics.drawText(text);
        
        graphics.setAlpha(currentAlpha);
    }
}
