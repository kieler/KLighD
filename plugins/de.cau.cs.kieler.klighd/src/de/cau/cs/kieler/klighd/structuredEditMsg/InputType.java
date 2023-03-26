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
 * This class holds the keyinformation how information should be retrived. The field is the name of
 * a variable that should be filled The type differentiates how information should be aquired (can
 * be String, SelectSource, SelectTarget) The label is the text that should be displayed to the user
 * and explaines what should be entered.
 * 
 * @author felixj
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
