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
 * An Or Connective takes two rules R1 and R2 and evaluates to true
 * iff
 * R1 or R2 evaluate to true.
 * 
 * @author mka
 *
 */
public class OrConnective extends BinaryConnective {
    protected static final String NAME = "OR";
    
    /**
     * Constructor for unnamed rule.
     * @param leftOperand left operand
     * @param rightOperand right operand
     */
    public OrConnective(SemanticFilterRule leftOperand, SemanticFilterRule rightOperand) {
        this.name = NAME;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }
    
    /**
     * Constructor for named rule.
     * @param leftOperand left operand
     * @param rightOperand right operand
     * @param ruleName rule name
     */
    public OrConnective(SemanticFilterRule leftOperand, SemanticFilterRule rightOperand, String ruleName) {
        super(ruleName);
        this.name = NAME;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

}
