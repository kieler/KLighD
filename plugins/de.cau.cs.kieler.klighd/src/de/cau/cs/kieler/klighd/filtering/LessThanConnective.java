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
 * A LessThan Connective takes one rule R and evaluates to true
 * iff
 * R.num < correspondingTag.num.
 * @author tik
 *
 */
public class LessThanConnective extends UnaryConnective {
    protected static final String NAME = "LESSTHAN";
    
    /**
     * Constructor for unnamed rule.
     * @param operand the operand
     */
    public LessThanConnective(NumericResult operand) {
        this(operand, null, null);
    }
    
    /**
     * Constructor for unnamed rule with default value.
     * @param operand the operand
     * @param defaultValue the default value
     */
    public LessThanConnective(NumericResult operand, Boolean defaultValue) {
        this(operand, defaultValue, null);
    }
    
    /**
     * Constructor for named rule.
     * @param operand the operand
     * @param ruleName rule name
     */
    public LessThanConnective(NumericResult operand, String ruleName) {
        this(operand, null, ruleName);
    }
    
    /**
     * Constructor for named rule with default value.
     * @param operand the operand
     * @param defaultValue the default value
     * @param ruleName rule name
     */
    public LessThanConnective(NumericResult operand, Boolean defaultValue, String ruleName) {
        super(defaultValue, ruleName);
        this.name = NAME;
        this.operand = (SemanticFilterRule) operand;
    }

}
