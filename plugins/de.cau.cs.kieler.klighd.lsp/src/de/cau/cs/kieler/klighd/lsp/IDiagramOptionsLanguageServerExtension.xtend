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
package de.cau.cs.kieler.klighd.lsp

import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.lsp.model.GetOptionParam
import de.cau.cs.kieler.klighd.lsp.model.GetOptionsResult
import de.cau.cs.kieler.klighd.lsp.model.PerformActionParam
import de.cau.cs.kieler.klighd.lsp.model.SetLayoutOptionsParam
import de.cau.cs.kieler.klighd.lsp.model.SetSynthesisOptionsParam
import de.cau.cs.kieler.klighd.lsp.model.ValuedSynthesisOption
import java.util.concurrent.CompletableFuture
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest
import org.eclipse.lsp4j.jsonrpc.services.JsonSegment

/**
 * Interface describing methods needed for functionality of sending and receiving messages for diagram options.
 * 
 * @author nre
 */
@JsonSegment('keith/diagramOptions')
public interface IDiagramOptionsLanguageServerExtension {
    
    /**
     * Method called by a client to get a list of all {@link ValuedSynthesisOption}s defined for a diagram handled by 
     * the {@link KGraphLanguageServerExtension}.
     * 
     * @param param Defines the {@code param.path} to the source model of that diagram.
     * @return A list of all synthesis options with their current values, if the diagram for {@code param.path} is
     * opened, {@code null} otherwise.
     */
    @JsonRequest('getOptions')
    public def CompletableFuture<GetOptionsResult> getOptions(GetOptionParam param)
    
    /**
     * Method called by a client to set the {@link SynthesisOption}s of the diagram resolved by {@code param.uri} to 
     * the options given in {@code param.synthesisOptions}. Also updates the diagram.
     * 
     * @param param The {@code uri} to resolve the diagram and the new options given by {@code synthesisOption}.
     * @return "OK", if the diagram was found and the new options have been set, "ERR" otherwise.
     */
    @JsonRequest('setSynthesisOptions')
    public def CompletableFuture<String> setSynthesisOptions(SetSynthesisOptionsParam param)
    
    /**
     * Method called by a client to set the layout optionss of the diagram resolved by {@code param.uri} to 
     * the options given in {@code param.layoutOptions}. Also updates the layout of the diagram.
     * 
     * @param param The {@code uri} to resolve the diagram and the new options given by {@code synthesisOption}.
     * @return "OK", if the diagram was found and the new options have been set, "ERR" otherwise.
     */
    @JsonRequest('setLayoutOptions')
    public def CompletableFuture<String> setLayoutOptions(SetLayoutOptionsParam param)
    
    /**
     * Method called by a client to perform an action on the diagram resolved by {@code param.uri}. The diagram may be
     * consequently updated by this action.
     * 
     * @param param The {@code uri} to resolve the diagram and the {@code actionId} that identifies the action to be
     * executed.
     * @return "OK", if the diagram was found and the action has been executed, "ERR" otherwise.
     */
    @JsonRequest('performAction')
    public def CompletableFuture<String> performAction(PerformActionParam param)
}