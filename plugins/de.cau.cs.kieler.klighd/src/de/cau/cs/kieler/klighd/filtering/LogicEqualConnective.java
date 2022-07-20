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
 * An Equal Connective takes two rules R1 and R2 and evaluates to true
 * iff
 * R1 and R2 evaluate to true or R1 and R2 evaluate to false.
 * 
 * @author tik
 *
 */
public class LogicEqualConnective extends BinaryConnective {
    protected static final String NAME = "LOGICEQUAL";
    
    /**
     * Constructor for unnamed rule.
     * @param leftOperand left operand
     * @param rightOperand right operand
     */
    public LogicEqualConnective(SemanticFilterRule leftOperand, SemanticFilterRule rightOperand) {
        this(leftOperand, rightOperand, null, null);
    }
    
    /**
     * Constructor for unnamed rule with default value.
     * @param leftOperand left operand
     * @param rightOperand right operand
     * @param defaultValue the default value
     */
    public LogicEqualConnective(SemanticFilterRule leftOperand, SemanticFilterRule rightOperand, Boolean defaultValue) {
        this(leftOperand, rightOperand, defaultValue, null);
    }
    
    /**
     * Constructor for named rule.
     * @param leftOperand left operand
     * @param rightOperand right operand
     * @param ruleName rule name
     */
    public LogicEqualConnective(SemanticFilterRule leftOperand, SemanticFilterRule rightOperand, String ruleName) {
        this(leftOperand, rightOperand, null, ruleName);
    }
    
    /**
     * Constructor for named rule with default value.
     * @param leftOperand left operand
     * @param rightOperand right operand
     * @param defaultValue the default value
     * @param ruleName rule name
     */
    public LogicEqualConnective(SemanticFilterRule leftOperand, SemanticFilterRule rightOperand, Boolean defaultValue, String ruleName) {
        super(defaultValue, ruleName);
        this.name = NAME;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }
    
}
