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
 * Semantic filter rules describe a filter that can be evaluated to include only elements tagged with certain tags.
 * The rules are constructed through the combination of filter rules with logical connectives.
 * 
 * @author mka
 *
 */
public abstract class SemanticFilterRule {
    
    private String ruleName;
    
    /**
     * Basic constructor
     */
    public SemanticFilterRule() {
        
    }
    
    /**
     * Constructor that takes a rule name. The rule name is used to identify rules and distinguish them from one another.
     * @param ruleName
     */
    public SemanticFilterRule(String ruleName) {
        this.ruleName = ruleName;
    }
    
    /**
     * Returns the rule name.
     * @return the rule name
     */
    public String getRuleName() { 
        return this.ruleName;
    }

}
