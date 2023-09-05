/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 - 2023 by
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
 * A Numeric Addition Connective takes two numeric operands and evaluates
 * to their sum.
 * 
 * Operands must implement {@link NumericResult} and be a {@link SemanticFilterTag}. This connective is an internal
 * connective and should never be treated as a rule and thus does not need a name.
 * 
 * @author mka
 *
 */
public class NumericAdditionConnective extends BinaryConnective implements NumericResult {
    protected static final String NAME = "NUMERICADDITION";
    
    /**
     * Constructor for numeric addition connective.
     * @param leftOperand left operand
     * @param rightOperand right operand
     */
    public NumericAdditionConnective(NumericResult leftOperand, NumericResult rightOperand) {
        super();
        this.name = NAME;
        this.leftOperand = (SemanticFilterRule) leftOperand;
        this.rightOperand = (SemanticFilterRule) rightOperand;
    }

}
