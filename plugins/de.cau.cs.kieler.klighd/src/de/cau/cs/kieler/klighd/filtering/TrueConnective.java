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
 * A True Connective always evaluates to true.
 * 
 * @author mka
 *
 */
public class TrueConnective extends NullaryConnective {
    
    protected static final String NAME = "TRUE";
    
    /**
     * Constructor for unnamed rule.
     */
    public TrueConnective() {
        this(null, null);
    }
    
    /**
     * Constructor for unnamed rule with default value.
     * @param defaultValue
     */
    public TrueConnective(Boolean defaultValue) {
        this(defaultValue, null);
    }
    
    /**
     * Constructor for named rule.
     * @param ruleName
     */
    public TrueConnective(String ruleName) {
        this(null, ruleName);
    }
    
    /**
     * Constructor for named rule with default value.
     * @param defaultValue
     * @param ruleName
     */
    public TrueConnective(Boolean defaultValue, String ruleName) {
        super(defaultValue, ruleName);
        this.name = NAME;
    }

}
