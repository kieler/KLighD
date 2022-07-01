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
 * An IfThenElse Connective takes three rules R1, R2 and R3 and evaluates to true
 * iff
 * R1 and R2 evaluate to true or R1 evaluates to false and R3 evaluates to true.
 * 
 * @author tik
 *
 */
public class IfThenElseConnective extends TernaryConnective {
    protected static final String NAME = "IFTHENELSE";
    
    /**
     * Constructor for unnamed rule.
     * @param firstOperand first operand
     * @param secondOperand second operand
     * @param thirdOperand third operand
     */
    public IfThenElseConnective(SemanticFilterRule firstOperand, SemanticFilterRule secondOperand,
            SemanticFilterRule thirdOperand) {
        this.name = NAME;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.thirdOperand = thirdOperand;
    }
    
    /**
     * Constructor for named rule.
     * @param firstOperand first operand
     * @param secondOperand second operand
     * @param thirdOperand third operand
     * @param ruleName rule name
     */
    public IfThenElseConnective(SemanticFilterRule firstOperand, SemanticFilterRule secondOperand,
            SemanticFilterRule thirdOperand, String ruleName) {
        super(ruleName);
        this.name = NAME;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.thirdOperand = thirdOperand;
    }

}
