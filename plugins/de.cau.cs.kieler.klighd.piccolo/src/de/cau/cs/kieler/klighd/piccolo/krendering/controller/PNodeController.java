/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
/**
 * 
 */
package de.cau.cs.kieler.klighd.piccolo.krendering.controller;

import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KRotation;
import de.cau.cs.kieler.core.krendering.LineCap;
import de.cau.cs.kieler.core.krendering.LineStyle;
import de.cau.cs.kieler.core.krendering.UnderlineStyle;
import de.cau.cs.kieler.klighd.piccolo.nodes.PAlignmentNode.HAlignment;
import de.cau.cs.kieler.klighd.piccolo.nodes.PAlignmentNode.VAlignment;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * The abstract base class for Piccolo node controllers.<br />
 * <br />
 * A node controller is a facade on a specific Piccolo node, providing a generic interface to set
 * the bounds and other parameters of the node.
 * 
 * @author mri
 * 
 * @param <T>
 *            the type of the associated node
 */
public abstract class PNodeController<T extends PNode> {

    /** the controller's node. */
    private T node;

    /**
     * Constructs a controller for a given Piccolo node.
     * 
     * @param node
     *            the Piccolo node
     */
    public PNodeController(final T node) {
        this.node = node;
    }

    /**
     * Returns the associated node.
     * 
     * @return the node
     */
    public T getNode() {
        return node;
    }
    
    /**
     * Sets the bounds of the associated node.
     * 
     * @param bounds
     *            the bounds
     */
    public abstract void setBounds(final PBounds bounds);

    
    /**
     * Sets the foreground color of the associated node.
     * 
     * @param color
     *            the foreground color
     */
    public void setForegroundColor(final RGB color) {
        // do nothing
    }
    
    /**
     * Sets the background color of the associated node.
     * 
     * @param color
     *            the background color
     */
    public void setBackgroundColor(final RGB color) {
        // do nothing
    }

    /**
     * Sets the line width of the associated node.
     * 
     * @param lineWidth
     *            the line width
     */
    public void setLineWidth(final float lineWidth) {
        // do nothing
    }

    /**
     * Sets the line visibility of the associated node.
     * 
     * @param lineAlpha
     *            the line alpha
     */
    public void setLineAlpha(final int lineAlpha) {
        // do nothing
    }

    /**
     * Sets the filled status of the associated node.
     * 
     * @param backgroundAlpha
     *            the background alpha
     */
    public void setBackgroundAlpha(final int backgroundAlpha) {
        // do nothing
    }

    /**
     * Sets the line style of the associated node.
     * 
     * @param lineStyle
     *            the line style
     */
    public void setLineStyle(final LineStyle lineStyle) {
        // do nothing
    }
    
    /**
     * Sets the line cap style of the associated node.
     * 
     * @param lineCap
     *            the line cap style
     */
    public void setLineCap(final LineCap lineCap) {
        // do nothing
    }

    /**
     * Sets the rotation of the associated node.
     * 
     * @param rotation
     *            the rotation
     */
    public void setRotation(final float rotation) {
        // do nothing
    }

    /**
     * Sets the horizontal alignment of the associated node.
     * 
     * @param alignment
     *            the horizontal alignment
     */
    public void setHorizontalAlignment(final HAlignment alignment) {
        // do nothing
    }

    /**
     * Sets the vertical alignment of the associated node.
     * 
     * @param alignment
     *            the vertical alignment
     */
    public void setVerticalAlignment(final VAlignment alignment) {
        // do nothing
    }

    /**
     * Sets the font name of the associated node (most likely some kind of text).
     * 
     * @param fontName
     *            the font name
     */
    public void setFontName(final String fontName) {
        // do nothing
    }

    /**
     * Sets the font size of the associated node (most likely some kind of text).
     * 
     * @param fontSize
     *            the font size
     */
    public void setFontSize(final int fontSize) {
        // do nothing
    }

    /**
     * Sets the italic property for the associated node (most likely some kind of text).
     * 
     * @param italic
     *            the italic property
     */
    public void setItalic(final boolean italic) {
        // do nothing
    }

    /**
     * Sets the bold property for the associated node (most likely some kind of text).
     * 
     * @param bold
     *            the bold property
     */
    public void setBold(final boolean bold) {
        // do nothing
    }
    
    /**
     * Sets the underlining property for the associated node (most likely some kind of text).
     * 
     * @param underline
     *            the underline property
     */
    public void setUnderline(final UnderlineStyle underline) {
        // do nothing
    }
    
    /**
     * Applies changes to the associated node.<br>
     * <br>
     * This can be used to apply several style changes at once to prevent costly redundancy.
     * 
     * @param styles A compound {@link Styles} field to infer the data from. 
     */
    public void applyChanges(final AbstractRenderingController<?, ?>.Styles styles) {
        // apply foreground styles
        if (styles.foreground != null) {
            int alphaValue = styles.foreground.getAlpha();
            KColor color = styles.foreground.getColor();
            if (color != null) {
                this.setForegroundColor(toRGB(color));
            }
            setLineAlpha(alphaValue);
        }

        // apply background color
        if (styles.background != null) {
            KColor color = styles.background.getColor();
            int alphaValue = styles.background.getAlpha();
            if (color != null) {
                this.setBackgroundColor(toRGB(color));
            }
            this.setBackgroundAlpha(alphaValue);
        }

        // apply line width
        if (styles.lineWidth != null) {
            this.setLineWidth(styles.lineWidth.getLineWidth());
        }

        // apply line style
        if (styles.lineStyle != null) {
            this.setLineStyle(styles.lineStyle.getLineStyle());
        } else {
            this.setLineStyle(LineStyle.SOLID);
        }
        
        // apply line cap style
        if (styles.lineCap != null) {
            this.setLineCap(styles.lineCap.getLineCap());
        } else {
            this.setLineCap(LineCap.CAP_FLAT);
        }

        // apply rotation
        if (styles.rotation != null) {
            KRotation rotation = styles.rotation;
            this.setRotation(rotation.getRotation());
        }

        // apply horizontal alignment
        if (styles.horizontalAlignment != null) {
            switch (styles.horizontalAlignment.getHorizontalAlignment()) {
            case LEFT:
                this.setHorizontalAlignment(HAlignment.LEFT);
                break;
            case RIGHT:
                this.setHorizontalAlignment(HAlignment.RIGHT);
                break;
            case CENTER:
            default:
                this.setHorizontalAlignment(HAlignment.CENTER);
                break;
            }
        }

        // apply vertical alignment
        if (styles.verticalAlignment != null) {
            switch (styles.verticalAlignment.getVerticalAlignment()) {
            case TOP:
                this.setVerticalAlignment(VAlignment.TOP);
                break;
            case BOTTOM:
                this.setVerticalAlignment(VAlignment.BOTTOM);
                break;
            case CENTER:
            default:
                this.setVerticalAlignment(VAlignment.CENTER);
                break;
            }
        }

        // apply font name
        if (styles.fontName != null) {
            this.setFontName(styles.fontName.getName());
        }

        // apply font size
        if (styles.fontSize != null) {
            this.setFontSize(styles.fontSize.getSize());
        }

        // apply the italic property
        if (styles.italic != null) {
            this.setItalic(styles.italic.isItalic());
        }

        // apply the bold property
        if (styles.bold != null) {
            this.setBold(styles.bold.isBold());
        }
        
        // apply the underlined property
        if (styles.underlined != null) {
            this.setUnderline(styles.underlined.getUnderlineStyle());
        } else {
            this.setUnderline(null); // for the moment!
        }
        
    }

    /**
     * Convenience transformation converting a {@link KColor} into an {@link RGB}.
     * 
     * TODO Install same caching in order to avoid unnecessary object creation.
     * 
     * @param color
     *            the {@link KColor} to be converted
     * @return the {@link RGB}
     */
    public RGB toRGB(final KColor color) {
        return new RGB(color.getRed(), color.getGreen(), color.getBlue());
    }

}
