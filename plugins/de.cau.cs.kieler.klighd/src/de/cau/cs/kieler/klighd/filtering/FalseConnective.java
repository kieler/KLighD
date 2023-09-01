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
 * A False Connective always evaluates to false.
 * 
 * @author mka
 *
 */
public class FalseConnective extends NullaryConnective {

    protected static final String NAME = "FALSE";
    
    /**
     * Constructor for unnamed rule.
     */
    public FalseConnective() {
        this(null, null);
    }
    
    /**
     * Constructor for unnamed rule with default value.
     * @param defaultValue
     */
    public FalseConnective(Boolean defaultValue) {
        this(defaultValue, null);
    }
    
    /**
     * Constructor for named rule.
     * @param ruleName
     */
    public FalseConnective(String ruleName) {
        this(null, ruleName);
    }
    
    /**
     * Constructor for named rule with default value.
     * @param defaultValue
     * @param ruleName
     */
    public FalseConnective(Boolean defaultValue, String ruleName) {
        super(defaultValue, ruleName);
        this.name = NAME;
    }

}
