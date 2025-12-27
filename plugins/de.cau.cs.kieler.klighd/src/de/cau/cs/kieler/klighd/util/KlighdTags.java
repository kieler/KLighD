/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2025 by
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

import de.cau.cs.kieler.klighd.filtering.SemanticFilterTag;

/**
 * Predefined semantic tags for Klighd.
 * 
 * @author mka
 *
 */
public class KlighdTags {
    
    /**
     * Hidden constructor to prevent instantiation.
     */
    private KlighdTags() {
    }
    
    /**
     * Determines whether a sub-KRendering can be interpreted as a title for a node.
     * By default no rendering is seen as a title.
     */
    public static final SemanticFilterTag IS_NODE_TITLE = new SemanticFilterTag("isNodeTitle");

}
