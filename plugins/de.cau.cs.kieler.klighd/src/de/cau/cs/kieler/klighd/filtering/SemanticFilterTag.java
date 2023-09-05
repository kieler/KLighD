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
 * Semantic filter tags serve both as tags on graph elements to provide semantic meaning in a diagram that can not 
 * be conveyed otherwise and as an atomic filter rule that can be combined with other rules to construct more complex
 * rules.
 * 
 * When using a tag in numeric expressions, keep in mind that not defining the number results in it defaulting to 0.
 * It's best practice to always set the number explicitly, if one can be assigned.
 * 
 * @author mka
 * @author tik
 *
 */
public class SemanticFilterTag extends SemanticFilterRule implements NumericResult {
    
    private String tag;
    /** If unset, defaults to 0. */
    private Double num;
    
    /**
     * Constructor for tag.
     * @param tag string identifier
     */
    public SemanticFilterTag(String tag) {
        this.tag = tag;
    }
    
    /**
     * Constructor for tag with a number.
     * @param tag string identifier
     * @param num the number
     */
    public SemanticFilterTag(String tag, Double num) {
        this.tag = tag;
        this.num = num;
    }
    
    /**
     * Constructor for tag as a semantic filter rule.
     * Here, an additional rule name is required as this is later used to differentiate between multiple rules.
     * @param tag string identifier of the tag the rule applies to
     * @param ruleName string identifier of the rule itself, can be anything
     */
    public SemanticFilterTag(String tag, String ruleName) {
        super(ruleName);
        this.tag = tag;
    }
    
    /**
     * Constructor for tag with a number as a semantic filter rule.
     * Here, an additional rule name is required as this is later used to differentiate between multiple rules.
     * @param tag string identifier of the tag the rule applies to
     * @param num the number
     * @param ruleName string identifier of the rule itself, can be anything
     */
    public SemanticFilterTag(String tag, Double num, String ruleName) {
        super(ruleName);
        this.tag = tag;
        this.num = num;
    }
    
    /**
     * Returns the tag.
     * @return the tag
     */
    public String getTag() {
        return this.tag;
    }
    
    /**
     * Returns the num.
     * @return the num
     */
    public Double getNum() {
        return this.num;
    }

    /**
     * Returns a string representation of the rule/tag.
     * @return the rule string
     */
    public String toString() {
        return "(tag=" + this.tag + ", num=" + this.num + ")";
    }

}
