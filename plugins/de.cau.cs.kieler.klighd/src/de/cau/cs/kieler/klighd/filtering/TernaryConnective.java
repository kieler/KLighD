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
 * A ternary connective C takes three filter rules R1, R2 and R3 as operands and creates the new rule C R1 R2 R3.
 * 
 * @author tik
 *
 */
public abstract class TernaryConnective extends SemanticFilterRule {
    
    protected String name;
    protected SemanticFilterRule firstOperand;
    protected SemanticFilterRule secondOperand;
    protected SemanticFilterRule thirdOperand;
    
    /**
     * {@inheritDoc}
     */
    public TernaryConnective() {
        
    }
    
    /**
     * {@inheritDoc}
     */
    public TernaryConnective(String ruleName) {
        super(ruleName);
    }
    
    /**
     * Returns a string representation of the rule of the form 'C(R1, R2, R3)'.
     * @return the rule string
     */
    public String getString() {
        return name + "(" + firstOperand + ", " + secondOperand + ", " + thirdOperand + ")";
    }
}
