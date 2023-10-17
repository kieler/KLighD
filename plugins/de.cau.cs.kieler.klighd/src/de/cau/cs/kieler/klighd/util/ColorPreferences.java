/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2023 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.util;

import de.cau.cs.kieler.klighd.krendering.KColor;
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory;

/**
 * Color preferences for KLighD syntheses. May provide information about the foreground, background, and a highlighting
 * color, that may be set by a frontend.
 * 
 * @author nre
 */
public class ColorPreferences {

    /**
     * The foreground color, to be used e.g. for texts, lines, etc., or as a reference.
     */
    private KColor foreground;
    
    /**
     * The background color to be used as the background, fill color, etc., or as a reference.
     */
    private KColor background;
    
    /**
     * A special highlighting color to be used for special renderings, or as a reference.
     */
    private KColor highlight;
    
    /**
     * Default constructor for color preferences, setting foreground and highlight to black, background to white.
     */
    public ColorPreferences() {
        foreground = KRenderingFactory.eINSTANCE.createKColor();
        foreground.setRed(0);
        foreground.setGreen(0);
        foreground.setBlue(0);
        
        background = KRenderingFactory.eINSTANCE.createKColor();
        background.setRed(255);
        background.setGreen(255);
        background.setBlue(255);

        highlight = KRenderingFactory.eINSTANCE.createKColor();
        highlight.setRed(0);
        highlight.setGreen(0);
        highlight.setBlue(0);
    }

    /**
     * Creates a new ColorPreferences with the given colors, or the default colors if some parameters are {@code null}.
     * 
     * @param foreground
     * @param background
     * @param highlight
     */
    public ColorPreferences(KColor foreground, KColor background, KColor highlight) {
        if (foreground == null) {
            foreground = KRenderingFactory.eINSTANCE.createKColor();
            foreground.setRed(0);
            foreground.setGreen(0);
            foreground.setBlue(0);
        } else {
            this.foreground = foreground;
        }
        if (background == null) {
            background = KRenderingFactory.eINSTANCE.createKColor();
            background.setRed(255);
            background.setGreen(255);
            background.setBlue(255);
        } else {
            this.background = background;
        }
        if (highlight == null) {
            highlight = KRenderingFactory.eINSTANCE.createKColor();
            highlight.setRed(0);
            highlight.setGreen(0);
            highlight.setBlue(0);
        } else {
            this.highlight = highlight;
        }
    }
    
    /**
     * @return the foreground color
     */
    public KColor getForeground() {
        return foreground;
    }

    /**
     * @return the background color
     */
    public KColor getBackground() {
        return background;
    }

    /**
     * @return the highlight color
     */
    public KColor getHighlight() {
        return highlight;
    }
}
