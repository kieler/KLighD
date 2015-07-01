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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels;

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
     * A label management algorithm is active for a given label and has modified its text. The new text
     * can be retrieved from the label's text property in the layout graph.
     */
    MANAGED_MODIFIED;
    
}
