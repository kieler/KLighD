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
 * Numeric constant for building expressions.
 * 
 * @author mka
 *
 */
public class NumericConstantConnective extends NullaryConnective implements NumericResult {
    
protected static final String NAME = "CONST";
private Double num;
    
    /**
     * Constructor for unnamed rule.
     * @param num
     */
    public NumericConstantConnective(Double num) {
        this(null, null, num);
    }
    
    /**
     * Constructor for unnamed rule with default value.
     * @param defaultValue
     * @param num
     */
    public NumericConstantConnective(Boolean defaultValue, Double num) {
        this(defaultValue, null, num);
    }
    
    /**
     * Constructor for named rule.
     * @param ruleName
     * @param num
     */
    public NumericConstantConnective(String ruleName, Double num) {
        this(null, ruleName, num);
    }
    
    /**
     * Constructor for named rule with default value.
     * @param defaultValue
     * @param ruleName
     * @param num
     */
    public NumericConstantConnective(Boolean defaultValue, String ruleName, Double num) {
        super(defaultValue, ruleName);
        this.name = NAME;
        this.num = num;
    }

}
