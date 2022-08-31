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
package de.cau.cs.kieler.klighd.filtering.parser;

import de.cau.cs.kieler.klighd.filtering.SemanticFilterRule;
import de.cau.cs.kieler.klighd.filtering.SemanticFilterRuleUtil;
import de.cau.cs.kieler.klighd.filtering.parser.SemanticFilterRuleParser.InvalidSyntaxException;

/**
 * Util for easily parsing rules.
 * 
 * @author tik
 */
public abstract class SemanticFilterRuleParserUtil {

    /** Instance of parser. Just one is enough. */
    private static final SemanticFilterRuleParser PARSER = new SemanticFilterRuleParser();

    /**
     * Parses a semantic filter rule expression and returns the {@link SemanticFilterRule}.
     * 
     * @param ruleString
     *            The semantic filter rule expression
     * @return The semantic filter rule
     * @see SemanticFilterRuleParser#parse(String)
     * @throws InvalidSyntaxException
     */
    public static SemanticFilterRule parse(String ruleString) throws InvalidSyntaxException {
        return PARSER.parse(ruleString);
    }

    /**
     * Parses a semantic filter rule expression and returns the {@link SemanticFilterRule} with
     * given ruleName.
     * 
     * @param ruleString
     *            The semantic filter rule expression
     * @param ruleName
     *            The rule name
     * @return The semantic filter rule
     * @see SemanticFilterRuleParser#parse(String)
     * @throws InvalidSyntaxException
     */
    public static SemanticFilterRule parse(String ruleString, String ruleName)
            throws InvalidSyntaxException {
        return SemanticFilterRuleUtil.addRuleName(ruleName, PARSER.parse(ruleString));
    }

    /**
     * Tries parsing a semantic filter rule expression. Returns the {@link SemanticFilterRule} or
     * {@code null} if the syntax is invalid.
     * 
     * @param ruleString
     *            The semantic filter rule expression
     * @return The semantic filter rule or {@code null} if the syntax is invalid
     * @see SemanticFilterRuleParser#parse(String)
     */
    public static SemanticFilterRule tryParse(String ruleString) {
        try {
            return parse(ruleString);
        } catch (InvalidSyntaxException e) {
            return null;
        }
    }

    /**
     * Tries parsing a semantic filter rule expression. Returns the {@link SemanticFilterRule} or
     * {@code null} if the syntax is invalid.
     * 
     * @param ruleString
     *            The semantic filter rule expression
     * @param ruleName
     *            The rule name
     * @return The semantic filter rule or {@code null} if the syntax is invalid
     * @see SemanticFilterRuleParser#parse(String)
     */
    public static SemanticFilterRule tryParse(String ruleString, String ruleName) {
        try {
            return parse(ruleString, ruleName);
        } catch (InvalidSyntaxException e) {
            return null;
        }
    }
}
