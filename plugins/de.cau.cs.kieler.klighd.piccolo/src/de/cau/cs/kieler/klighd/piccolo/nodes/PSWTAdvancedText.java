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
package de.cau.cs.kieler.klighd.piccolo.nodes;

import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.swt.PSWTText;
import edu.umd.cs.piccolox.swt.SWTGraphics2D;

/**
 * A specialization of the Piccolo text node which supports alignment.
 * 
 * @author mri
 */
public class PSWTAdvancedText extends PSWTText {

    private static final long serialVersionUID = 5166550763238006168L;

    /**
     * The possible alignments.
     */
    public enum Alignment {
        /** left aligned. */
        LEFT,
        /** centered. */
        CENTER,
        /** right aligned. */
        RIGHT
    }

    /** the text alignment. */
    private Alignment alignment = Alignment.LEFT;
    
    /**
     * Constructs an aligned text.
     */
    public PSWTAdvancedText() {
        super();
        greekColor = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintAsText(final PPaintContext ppc) {
        // proceed as normal if no reference for the alignment is available
        if (getParent() == null) {
            super.paintAsText(ppc);
        }
        final SWTGraphics2D sg2 = (SWTGraphics2D) ppc.getGraphics();
        // calculate offset depending on the alignment
        double offset = 0.0;
        switch (alignment) {
        case CENTER:
            double d = getParent().getBounds().getWidth() - getWidth() - 2 * padding;
            offset = d / 2 - getTransformReference(true).getTranslateX();
            break;
        case RIGHT:
            d = getParent().getBounds().getWidth() - getWidth() - 2 * padding;
            offset = d - getTransformReference(true).getTranslateX();
            break;
        }
        sg2.translate(offset, 0);
        super.paintAsText(ppc);
        sg2.translate(-offset, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintAsGreek(final PPaintContext ppc) {
        if (lines.size() > 0 && ((String) lines.get(0)).length() > 0) {
            super.paintAsGreek(ppc);
        }
    }

    /**
     * Sets the text alignment.
     * 
     * @param align
     *            the alignment
     */
    public void setAlignment(final Alignment align) {
        alignment = align;
    }

    /**
     * Returns the text alignment.
     * 
     * @return the text alignment
     */
    public Alignment getAlignment() {
        return alignment;
    }

}
