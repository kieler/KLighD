/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.model

import de.cau.cs.kieler.klighd.SynthesisOption
import java.util.List
import org.eclipse.xtend.lib.annotations.Data

/**
 * Data class to hold the parameters of the kCharts/getOptions message.
 * 
 * @author stu114054
 */
@Data
class GetOptionParam {
    /**
     * The {@code uri} pointing towards the resource of which the {@link SynthesisOption}s should be sent back.
     */
    String uri
    
    /**
     * Indicates if a request for a diagram was issued at the same time, that this getOptions request could wait for.
     */
    boolean waitForDiagram
}

/**
 * Data class to hold the parameters of the kCharts/sendOptions message.
 * 
 * @author stu114054
 */
@Data
class SetOptionParam {
    /**
     * The {@code uri} pointing towards the resource that should update its options
     */
    String uri
    
    /**
     * A list of the new {@link SynthesisOptionValue}s that should be set.
     */
    List<SynthesisOptionValue> synthesisOptions
}

/**
 * Data class to hold the identifying {@code name} for a {@link SynthesisOption} and a {@currentValue} of that option.
 * 
 * @author stu114054
 */
@Data
class SynthesisOptionValue {
    /**
     * Name of the {@link SynthesisOption}
     */
    String name
    
    /**
     * Value that the {@link SynthesisOption} should currently have.
     */
    Object currentValue
}