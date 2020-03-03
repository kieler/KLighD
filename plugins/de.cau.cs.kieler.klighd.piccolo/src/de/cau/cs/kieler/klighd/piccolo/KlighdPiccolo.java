/*
 * KLighD - KIELER Lightweight Diagrams
 *
 * https://github.com/OpenKieler/KLighD
 *
 * Copyright 2020 by TypeFox GmbH (typefox.io) and others.
 *
 * This code is provided under the terms of the Eclipse Public License 1.0 (EPL-1.0).
 */
package de.cau.cs.kieler.klighd.piccolo;

import de.cau.cs.kieler.klighd.Klighd;

public class KlighdPiccolo {
    
    /** the plug-in ID. */
    public static final String PLUGIN_ID = Klighd.PLUGIN_ID + ".piccolo";
    
    /** The freeHEP-based SVG generator's id, requires the fragment "...klighd.piccolo.freehep". */
    public static final String GENERATOR_SVG_FREEHEP_EXTENDED =
            "de.cau.cs.kieler.klighd.piccolo.svggen.freeHEPExtended";

    /** The Batik-based SVG generator's id, requires the fragment "...klighd.piccolo.batik". */
    public static final String GENERATOR_SVG_BATIK =
            "de.cau.cs.kieler.klighd.piccolo.svggen.batik";
}
