/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2024 by
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
package de.cau.cs.kieler.klighd.structurebasedediting;

/**
 * Data class for structure editing message to be send from client to server.
 */
public class StructureBasedEditingMessage {

    String label;
    String kind;
    Boolean mergable;
    InputType[] inputs;

    public StructureBasedEditingMessage(String label, String kind, Boolean mergable, InputType[] inputs) {
        this.label = label;
        this.kind = kind;
        this.mergable = mergable;
        this.inputs = inputs;
    }
}
