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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.core.krendering.Underline;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.krendering.util.Styles;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTStyledText;
import de.cau.cs.kieler.klighd.piccolo.util.RGBGradient;

/**
 * A node controller for the {@code PSWTText}.
 * 
 * @author mri, chsch
 */
public abstract class PSWTTextController extends PNodeController<PSWTStyledText> {

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
    public PSWTTextController(final PSWTStyledText node) {
        super(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundColor(final RGB color, final int alpha) {
        getNode().setPenAlpha(alpha);
        getNode().setPenColor(color);
    }

    /**
     * {@inheritDoc}
     */
    public void setForegroundGradient(final RGBGradient gradient) {
        getNode().setPenColor(gradient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundColor(final RGB color, final int alpha) {
        getNode().setPenPaintAlpha(alpha);
        getNode().setPenPaint(color);
    }
   /**
     * {@inheritDoc}
     */
    public void setBackgroundGradient(final RGBGradient gradient) {
        getNode().setPaint(gradient);
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void setLineAlpha(final int lineAlpha) {
//        if (lineAlpha == 0) {
//            getNode().setPenColor((RGB) null);
//        }
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void setBackgroundAlpha(final int backgroundAlpha) {
//        if (backgroundAlpha == 0) {
//            getNode().setBackgroundColor(null);
//            getNode().setTransparent(true);
//        } else {
//            getNode().setTransparent(false);
//        }
//    }

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
            fontStyle |= SWT.ITALIC;
        } else {
            fontStyle &= ~SWT.ITALIC;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBold(final boolean bold) {
        if (bold) {
            fontStyle |= SWT.BOLD;
        } else {
            fontStyle &= ~SWT.BOLD;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void setUnderline(final Underline underline, final RGB color) {
        if (underline != null) {
            switch (underline) {
            case SINGLE:
                getNode().setUnderline(SWT.UNDERLINE_SINGLE, color);
                break;
            case DOUBLE:
                getNode().setUnderline(SWT.UNDERLINE_DOUBLE, color);
                break;
            case ERROR:
                getNode().setUnderline(SWT.UNDERLINE_ERROR, color);
                break;
            case SQUIGGLE:
                getNode().setUnderline(SWT.UNDERLINE_SQUIGGLE, color);
                break;
            case LINK:
                getNode().setUnderline(SWT.UNDERLINE_LINK, color);
                break;
            case NONE:
            default:
                getNode().setUnderline(KlighdConstants.NO_FONT_UNDERLINING, color);
            }
        } else {
            getNode().setUnderline(KlighdConstants.NO_FONT_UNDERLINING, KlighdConstants.BLACK);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStrikeout(final boolean strikeout, final RGB color) {
        getNode().setStrikeout(strikeout, color);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void applyChanges(final Styles styles) {
        super.applyChanges(styles);
        getNode().setFont(new FontData(fontName, fontSize, fontStyle));
    }

}
