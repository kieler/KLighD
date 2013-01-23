/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.krendering.controller;

import java.awt.Color;
import java.awt.Font;

import de.cau.cs.kieler.klighd.KlighdConstants;

import edu.umd.cs.piccolox.swt.PSWTText;

/**
 * A node controller for the {@code PSWTText}.
 * 
 * @author mri
 */
public abstract class PSWTTextController extends PNodeController<PSWTText> {

    /** the name of the currently used font. */
    private String fontName = KlighdConstants.DEFAULT_FONT_NAME;
    /** the currently used font size. */
    private int fontSize = KlighdConstants.DEFAULT_FONT_SIZE;
    /** the currently used font style. */
    private int fontStyle = KlighdConstants.DEFAULT_FONT_STYLE;

    /**
     * Constructs a node controller for a {@code PSWTText}.
     * 
     * @param node
     *            the text
     */
    public PSWTTextController(final PSWTText node) {
        super(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundColor(final Color color) {
        getNode().setPenColor(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundColor(final Color color) {
        getNode().setBackgroundColor(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineAlpha(final int lineAlpha) {
        if (lineAlpha == 0) {
            getNode().setPenColor(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundAlpha(final int backgroundAlpha) {
        if (backgroundAlpha == 0) {
            getNode().setBackgroundColor(null);
            getNode().setTransparent(true);
        } else {
            getNode().setTransparent(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setRotation(final float rotation) {
        getNode().setRotation(Math.toRadians(rotation));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontName(final String fontName) {
        this.fontName = fontName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontSize(final int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setItalic(final boolean italic) {
        if (italic) {
            fontStyle |= Font.ITALIC;
        } else {
            fontStyle &= ~Font.ITALIC;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBold(final boolean bold) {
        if (bold) {
            fontStyle |= Font.BOLD;
        } else {
            fontStyle &= ~Font.BOLD;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyChanges() {
        Font font = new Font(fontName, fontStyle, fontSize);
        getNode().setFont(font);
    }

}
