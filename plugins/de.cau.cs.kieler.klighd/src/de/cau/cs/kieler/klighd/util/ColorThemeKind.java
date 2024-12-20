/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2024 by
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

/**
 * Kinds of color themes, as an enum similar to VS Code's ColorThemeKind.
 * 
 * @author nre
 */
public enum ColorThemeKind {
    /**
     * Light color theme with light backgrounds and darker writing
     */
    LIGHT,
    /**
     * Dark color theme with dark backgrounds and lighter writing
     */
    DARK,
    /**
     * Light color theme with a higher contrast.
     */
    HIGH_CONTRAST_LIGHT,
    /**
     * Dark color theme with a higher contrast.
     */
    HIGH_CONTRAST_DARK
}
