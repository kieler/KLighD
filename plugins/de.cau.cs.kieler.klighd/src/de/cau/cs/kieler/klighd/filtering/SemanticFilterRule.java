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
 * Complex example filters are provided in the comments of the source code below.
 * 
 * @author mka
 * @author tik
 *
 */
public abstract class SemanticFilterRule {
    
    /** The rule name is used to identify rules and distinguish them from one another. */
    private String ruleName;
    /** The default value is used to indicate whether the semantic filter should be on or off by default. */
    private Boolean defaultValue;
    
    /**
     * Basic constructor
     */
    public SemanticFilterRule() {
        this(null, null);
    }
    
    /**
     * Constructor that takes a default value.
     * The default value is used to indicate whether the semantic filter should be on or off by default.
     * @param defaultValue the default value
     */
    public SemanticFilterRule(Boolean defaultValue) {
        this(defaultValue, null);
    }
    
    /**
     * Constructor that takes a rule name.
     * The rule name is used to identify rules and distinguish them from one another.
     * @param ruleName the rule name
     */
    public SemanticFilterRule(String ruleName) {
        this(null, ruleName);
    }
    
    /**
     * Constructor that takes a rule name and default value.
     * The default value is used to indicate whether the semantic filter should be on or off by default.
     * The rule name is used to identify rules and distinguish them from one another.
     * @param defaultValue the default value
     * @param ruleName the rule name
     */
    public SemanticFilterRule(Boolean defaultValue, String ruleName) {
        this.defaultValue = defaultValue;
        this.ruleName = ruleName;
    }
    
    /**
     * Returns the rule name.
     * @return the rule name
     */
    public String getRuleName() { 
        return this.ruleName;
    }
    
    /**
     * Returns the default value.
     * @return the default value
     */
    public boolean getDefaultValue() { 
        return this.defaultValue;
    }
    
//    /**
//     * Rule to exclude elements that are either initial XOR final states.
//     * 
//     * @example
//     */
//    public static final SemanticFilterRule NO_INITIAL_XOR_FINAL_STATE =
//            new AndConnective(SCChartsSemanticFilterTags.STATE,
//                    new LogicEqualConnective(SCChartsSemanticFilterTags.INITIAL,
//                            SCChartsSemanticFilterTags.FINAL),
//                    "Filter Initial XOR Final States");
//
//    /**
//     * Rule to only include elements that are either initial states or regions.
//     * 
//     * @example
//     */
//    public static final SemanticFilterRule ONLY_INITIAL_STATES_OR_REGIONS =
//            new IfThenElseConnective(SCChartsSemanticFilterTags.STATE,
//                    SCChartsSemanticFilterTags.INITIAL, SCChartsSemanticFilterTags.REGION,
//                    "Filter Everything but Initial States or Regions");
//
//    /** Rule to only include elements that have at least 3 declarations. */
//    public static final SemanticFilterRule ONLY_AT_LEAST_3_DECLARATIONS =
//            new GreaterEqualsConnective(SCChartsSemanticFilterTags.DECLARATIONS(null),
//                    new NumericConstantConnective(3.0),
//                    "Filter Elements with less than 3 Declarations");
//
//    /**
//     * Rule to only include elements that are initial, final or a region.
//     * 
//     * @example
//     */
//    public static final SemanticFilterRule ONLY_INITIAL_OR_FINAL_OR_REGION = SemanticFilterRuleUtil
//            .getBigOrConnective("Filter Everything but Initial, Final or Regions",
//                    SCChartsSemanticFilterTags.INITIAL, SCChartsSemanticFilterTags.FINAL,
//                    SCChartsSemanticFilterTags.REGION);
//
//    /**
//     * Rule to exclude every element.
//     * 
//     * @example
//     */
//    public static final SemanticFilterRule NO_EVERYTHING = new FalseConnective("Filter Everything");
//
//    /**
//     * Rule to only include every element.
//     * 
//     * @example
//     */
//    public static final SemanticFilterRule ONLY_EVERYTHING = new TrueConnective("Filter Nothing");
//
//    /**
//     * Rule to only include elements with more input/output declarations than the total number of declarations.
//     * 
//     * @example
//     */
//    public static final SemanticFilterRule ONLY_MORE_IO_THAN_DECLARATIONS = new GreaterThanConnective(
//            new NumericAdditionConnective(SCChartsSemanticFilterTags.INPUT_DECLARATIONS(null),
//                    SCChartsSemanticFilterTags.OUTPUT_DECLARATIONS(null)),
//            SCChartsSemanticFilterTags.DECLARATIONS(null),
//            "Filter Elements with #IO Declarations <= #Declarations");
}
