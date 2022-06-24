/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 by
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
package de.cau.cs.kieler.klighd.filtering;

/**
 * @author mka
 *
 */
public class SemanticFilterTag extends SemanticFilterRule {
    
    private String tag;
    
    public SemanticFilterTag(String tag) {
        this.tag = tag;
    }
    
    public SemanticFilterTag(String tag, String ruleName) {
        super(ruleName);
        this.tag = tag;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public String toString() {
        return this.tag;
    }

}
