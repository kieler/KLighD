/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2023-2024 by
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
 * color, that may be set by a frontend. Alternatively, this preference sets a general color theme kind instead of
 * pre-defined colors.
 * 
 * @author nre
 */
public class ColorPreferences {

//    The kind of color theme to let the synthesis pick its own color palette.
    private ColorThemeKind kind;
    
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
        this(null, null, null, null);
    }

    /**
     * Creates a new ColorPreferences with the given colors, or the default colors if some parameters are {@code null}.
     * 
     * @param kind
     * @param foreground
     * @param background
     * @param highlight
     */
    public ColorPreferences(ColorThemeKind kind, KColor foreground, KColor background, KColor highlight) {
        this.kind = kind;
        if (this.kind == null) {
            this.kind = ColorThemeKind.LIGHT;
        }
        if (foreground == null) {
            this.foreground = KRenderingFactory.eINSTANCE.createKColor();
            if (this.kind == ColorThemeKind.LIGHT || this.kind == ColorThemeKind.HIGH_CONTRAST_LIGHT) {
                this.foreground.setRed(0);
                this.foreground.setGreen(0);
                this.foreground.setBlue(0);
            } else { // dark theme
                // D4D4D4
                this.foreground.setRed(212);
                this.foreground.setGreen(212);
                this.foreground.setBlue(212);
            }
        } else {
            this.foreground = foreground;
        }
        if (background == null) {
            this.background = KRenderingFactory.eINSTANCE.createKColor();
            if (this.kind == ColorThemeKind.LIGHT || this.kind == ColorThemeKind.HIGH_CONTRAST_LIGHT) {
                this.background.setRed(255);
                this.background.setGreen(255);
                this.background.setBlue(255);
            } else { // dark theme
                // 1E1E1E
                this.background.setRed(30);
                this.background.setGreen(30);
                this.background.setBlue(30);
            }
        } else {
            this.background = background;
        }
        if (highlight == null) {
            this.highlight = KRenderingFactory.eINSTANCE.createKColor();
            if (this.kind == ColorThemeKind.LIGHT || this.kind == ColorThemeKind.HIGH_CONTRAST_LIGHT) {
                // 005FB8
                this.highlight.setRed(0);
                this.highlight.setGreen(95);
                this.highlight.setBlue(184);
            } else { // dark theme
                // 0078D4
                this.highlight.setRed(0);
                this.highlight.setGreen(120);
                this.highlight.setBlue(212);
            }
        } else {
            this.highlight = highlight;
        }
    }

    /**
     * @return the theme kind
     */
    public ColorThemeKind getKind() {
        return kind;
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
