/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2023 by
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
package de.cau.cs.kieler.klighd.structuredEditMsg;

/**
 * @author felixj
 *
 */
public class InputType {
    String field;
    String type_of_Input;
    String label;
    public InputType(String field, String type_of_Input, String label) {
        this.field = field;
        this.type_of_Input = type_of_Input;
        this.label = label;
    }
    
}
