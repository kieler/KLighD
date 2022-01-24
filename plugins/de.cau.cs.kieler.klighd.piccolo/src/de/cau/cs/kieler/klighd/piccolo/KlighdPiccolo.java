/*
 * KLighD - KIELER Lightweight Diagrams
 *
 * https://github.com/Kieler/KLighD
 *
 * Copyright 2020 by TypeFox GmbH (typefox.io) and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
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
