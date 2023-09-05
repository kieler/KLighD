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
 * A binary connective C takes two filter rules R1 and R2 as operands and creates the new rule R1 C R2.
 * @author mka
 * @author tik
 *
 */
public abstract class BinaryConnective extends SemanticFilterRule {
    
    protected String name;
    protected SemanticFilterRule leftOperand;
    protected SemanticFilterRule rightOperand;
    
    /**
     * {@inheritDoc}
     */
    public BinaryConnective() {
        
    }
    
    /**
     * {@inheritDoc}
     */
    public BinaryConnective(Boolean defaultValue) {
        super(defaultValue);
    }
    
    /**
     * {@inheritDoc}
     */
    public BinaryConnective(String ruleName) {
        super(ruleName);
    }
    
    /**
     * {@inheritDoc}
     */
    public BinaryConnective(Boolean defaultValue, String ruleName) {
        super(defaultValue, ruleName);
    }
    
    /**
     * Returns a string representation of the rule of the form 'C(R1, R2)'.
     * @return the rule string
     */
    public String toString() {
        return name + "(" + leftOperand + ", " + rightOperand + ")";
    }

}
