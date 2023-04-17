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
package de.cau.cs.kieler.klighd.structuredEditMsg;

import java.util.Map;

import de.cau.cs.kieler.klighd.filtering.SemanticFilterTag;
import de.cau.cs.kieler.klighd.structuredEditMsg.StructuredEditMsg;
import java.util.HashMap;
import java.util.List;

/**
 * Is used as a property and holds all information on what tags support what editing actions.
 * 
 * @author felixj
 */
public class StructuredEditOptions {
    Map<SemanticFilterTag, StructuredEditMsg[]> options;

    public StructuredEditOptions(Map<SemanticFilterTag, StructuredEditMsg[]> map) {
        options = map;
    }
}
