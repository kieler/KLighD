/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.freehep;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.text.CharacterIterator;

import org.eclipse.swt.graphics.FontMetrics;

/**
 * An AWT wrapper for a SWT {@link FontMetrics} instance used by the {@link SemanticSVGGraphics2D}.
 * 
 * We implement the methods that are used and raise exceptions for currently unsupported methods.
 * These might be implemented in the future.
 * 
 * @author uru
 */
public class PseudoAWTFontMetrics extends java.awt.FontMetrics {

    private static final long serialVersionUID = 5535613848528577178L;

    private FontMetrics swtFm;

    /**
     * @param font
     */
    protected PseudoAWTFontMetrics(final Font font, final FontMetrics swtFm) {
        super(font);
        this.swtFm = swtFm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAscent() {
        return swtFm.getAscent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDescent() {
        return swtFm.getDescent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return swtFm.getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLeading() {
        return swtFm.getLeading();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font getFont() {
        return super.getFont();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontRenderContext getFontRenderContext() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxAscent() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxDescent() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxAdvance() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int charWidth(int codePoint) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int charWidth(char ch) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int stringWidth(String str) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int charsWidth(char[] data, int off, int len) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int bytesWidth(byte[] data, int off, int len) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getWidths() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasUniformLineMetrics() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LineMetrics getLineMetrics(String str, Graphics context) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LineMetrics getLineMetrics(String str, int beginIndex, int limit, Graphics context) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LineMetrics getLineMetrics(char[] chars, int beginIndex, int limit, Graphics context) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LineMetrics getLineMetrics(CharacterIterator ci, int beginIndex, int limit,
            Graphics context) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getStringBounds(String str, Graphics context) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getStringBounds(String str, int beginIndex, int limit, Graphics context) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getStringBounds(char[] chars, int beginIndex, int limit, Graphics context) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getStringBounds(CharacterIterator ci, int beginIndex, int limit,
            Graphics context) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getMaxCharBounds(Graphics context) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

}
