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
package de.cau.cs.kieler.klighd.structurebasedediting;

import java.util.Map;

import de.cau.cs.kieler.klighd.filtering.SemanticFilterTag;

/**
 * Property that holds the information about support structured editing messages and what elements support which message.
 */
public class StructureBasedEditingOptions {
    Map<SemanticFilterTag, StructureBasedEditingMessage[]> options;

    public StructureBasedEditingOptions(Map<SemanticFilterTag, StructureBasedEditingMessage[]> map) {
        options = map;
    }
}