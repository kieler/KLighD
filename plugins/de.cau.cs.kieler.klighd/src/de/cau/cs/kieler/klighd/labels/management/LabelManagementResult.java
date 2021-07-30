/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
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
package de.cau.cs.kieler.klighd.labels.management;

/**
 * Enumeration of different outcomes of label management for a given label. Depending on the actual
 * outcome, KLighD will decide what to do with the label.
 * 
 * @author cds
 */
public enum LabelManagementResult {

    /**
     * No label management algorithm active for a given label.
     */
    UNMANAGED,

    /**
     * A label management algorithm is active for a given label, but has not modified its text.
     */
    MANAGED_UNMODIFIED,

    /**
     * A label management algorithm is active for a given label and has modified its text. The new
     * text can be retrieved from the label's text property in the layout graph.
     */
    MANAGED_MODIFIED;

}
