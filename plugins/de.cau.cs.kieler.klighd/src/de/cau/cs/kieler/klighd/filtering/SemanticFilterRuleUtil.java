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
 * Util for easily getting some rules.
 * 
 * @author tik, mka
 *
 */
public abstract class SemanticFilterRuleUtil {

    /**
     * Constructs an unnamed rule with all operands connected via OR. If no operands are given, this
     * is always false.
     * 
     * @param operands
     *            The operands.
     * @return The SemanticFilterRule.
     */
    public static SemanticFilterRule getBigOrConnective(SemanticFilterRule... operands) {
        return getBigOrConnective(null, operands);
    }

    /**
     * Constructs a named rule with all operands connected via OR. If no operands are given, this is
     * always false.
     * 
     * @param ruleName
     *            The rule name.
     * @param operands
     *            The operands.
     * @return The SemanticFilterRule.
     */
    public static SemanticFilterRule getBigOrConnective(String ruleName,
            SemanticFilterRule... operands) {
        if (operands.length <= 0) {
            // No rules, automatically evaluate to false
            return new FalseConnective();
        } else if (operands.length <= 1) {
            // Just one rule, true iff rule is true
            // Add ruleName instead of just using first operand twice to avoid double evaluation
            return addRuleName(ruleName, operands[0]);
        }
        OrConnective bigOr = new OrConnective(operands[0], operands[1], ruleName);
        for (int i = 2; i < operands.length; i++) {
            bigOr.rightOperand = new OrConnective(bigOr.rightOperand, operands[i]);
        }
        return bigOr;
    }

    /**
     * Constructs an unnamed rule with all operands connected via AND. If no operands are given,
     * this is always false.
     * 
     * @param operands
     *            The operands.
     * @return The SemanticFilterRule.
     */
    public static SemanticFilterRule getBigAndConnective(SemanticFilterRule... operands) {
        return getBigAndConnective(null, operands);
    }

    /**
     * Constructs a named rule with all operands connected via AND. If no operands are given, this
     * is always false.
     * 
     * @param ruleName
     *            The rule name.
     * @param operands
     *            The operands.
     * @return The SemanticFilterRule.
     */
    public static SemanticFilterRule getBigAndConnective(String ruleName,
            SemanticFilterRule... operands) {
        if (operands.length <= 0) {
            // No rules, automatically evaluate to false
            return new FalseConnective();
        } else if (operands.length <= 1) {
            // Just one rule, true iff rule is true
            // Add ruleName instead of just using first operand twice to avoid double evaluation
            return addRuleName(ruleName, operands[0]);
        }
        // Use first operand twice in case of just one rule, e.g. true iff rule is true
        AndConnective bigAnd = new AndConnective(operands[0], operands[1], ruleName);
        for (int i = 2; i < operands.length; i++) {
            bigAnd.rightOperand = new AndConnective(bigAnd.rightOperand, operands[i]);
        }
        return bigAnd;
    }

    /**
     * Constructs a new named rule from a given potentially unnamed rule.
     * 
     * @param ruleName
     *            The rule name.
     * @param rule
     *            The rule.
     * @return A named rule.
     */
    public static SemanticFilterRule addRuleName(String ruleName, SemanticFilterRule rule) {
        return new IdentityConnective((rule), ruleName);
    }
}
