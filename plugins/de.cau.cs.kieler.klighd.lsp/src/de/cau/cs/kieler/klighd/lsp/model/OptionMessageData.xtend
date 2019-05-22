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

import de.cau.cs.kieler.klighd.DisplayedActionData
import de.cau.cs.kieler.klighd.SynthesisOption
import java.util.List
import org.eclipse.xtend.lib.annotations.Data

// This is a collection of data classes needed for communication with the client.

/**
 * Data class to hold the parameters and results of the keith/diagramOptions/getOptions message.
 * 
 * @author nre
 */
@Data
class GetOptionParam {
    /**
     * The {@code uri} pointing towards the resource of which the {@link SynthesisOption}s should be sent back.
     */
    String uri
}

/**
 * Data class to hold the result of a keith/diagramOptions/getOptions message.
 * Includes a list of the synthesisOptions with their current value, a list of the layout options and a list of the
 * actions performable for the current diagram.
 * 
 * @author nre
 */
@Data
class GetOptionsResult {
    /**
     * The list of all displayed synthesis options for the diagram for the given URI.
     */
    List<ValuedSynthesisOption> valuedSynthesisOptions
    
    /**
     * The list of the ui data for layout options for the diagram for the given URI.
     */
    List<LayoutOptionUIData> layoutOptions
     
    /**
     * The list of all displayed actions for the diagram for the given URI.
     */
    List<DisplayedActionData> actions
}

/**
 * Data class to hold the parameters of the keith/diagramOptions/sendSynthesisOptions message.
 * 
 * @author nre
 */
@Data
class SetSynthesisOptionsParam {
    /**
     * The {@code uri} pointing towards the resource that should update its synthesis options.
     */
    String uri
    
    /**
     * A list of the new {@link SynthesisOptionValue}s that should be set.
     */
    List<SynthesisOptionValue> synthesisOptions
}

/**
 * Data class to hold the parameters of the keith/diagramOptions/sendLayoutOptions message.
 * 
 * @author nre
 */
 @Data
 class SetLayoutOptionsParam {
    /**
     * The {@code uri} pointing towards the resource that should update its layout options.
     */
    String uri
     
    /**
     * A list of the new {@link LayoutOptionValue}s that should be set.
     */
    List<LayoutOptionValue> layoutOptions 
 }

/**
 * Data class to hold the parameters of the keith/diagramOptions/performAction message.
 * 
 * @author nre
 */
@Data
class PerformActionParam {
    /**
     * The {@code uri} pointing towards the resource that should perform this action.
     */
     String uri
    
    /**
     * The id of the action that should be performed.
     */
    String actionId
}

/**
 * Data class to hold the identifying {@code sourceHash} for a {@link SynthesisOption} and a {@code currentValue} of
 * that option.
 * 
 * @author nre
 */
@Data
class SynthesisOptionValue {
    /**
     * Value that the {@link SynthesisOption} should currently has.
     */
    Object currentValue
    
    /**
     * The identifying hash value of the {@SynthesisOption} this value belongs to.
     * The hash for the synthesis option can be checked via System.identityHashCode(synthesisOption) for every synthesis
     * option to search where this value belongs to.
     */
    int sourceHash
}

/**
 * Data class to hold the identifier for a layout option and a {@code currentValue} of that option.
 */
@Data
class LayoutOptionValue {
    /**
     * Identifier of the layout option.
     */
    String optionId
    /**
     * The new value of this option.
     */
    Object value
}

/**
 * Data class to hold a synthesisOption and its current value in one convenient object.
 */
@Data
class ValuedSynthesisOption {
    /**
     * The synthesisOption.
     */
     
    SynthesisOption synthesisOption
    /**
     * The current value of the synthesisOption.
     */
    Object currentValue
}