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
 * A NOT Connective takes a rule R and evaluates to true iff R evaluates to false.
 * 
 * @author mka
 *
 */
public class NegationConnective extends UnaryConnective {
    
    /**
     * Constructor for unnamed rule.
     * @param operand the operand
     */
    public NegationConnective(SemanticFilterRule operand) {
        this.name = "NOT";
        this.operand = operand;
    }
    
    /**
     * Constructor for named rule.
     * @param operand the operand
     * @param ruleName rule name
     */
    public NegationConnective(SemanticFilterRule operand, String ruleName) {
        super(ruleName);
        this.name = "NOT";
        this.operand = operand;
    }

}
