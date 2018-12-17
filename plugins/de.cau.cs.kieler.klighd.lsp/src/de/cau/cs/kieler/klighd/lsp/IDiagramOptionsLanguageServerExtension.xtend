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
import de.cau.cs.kieler.klighd.lsp.model.SetOptionParam
import java.util.List
import java.util.concurrent.CompletableFuture
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest
import org.eclipse.lsp4j.jsonrpc.services.JsonSegment

/**
 * Interface describing methods needed for functionality of sending and receiving messages for diagram options.
 * 
 * @author nir
 */
@JsonSegment('keith')
public interface IDiagramOptionsLanguageServerExtension {
    
    /**
     * Method called by a client to get a list of all {@link SynthesisOption}s defined for a diagram handled by 
     * the {@link KGraphLanguageServerExtension}.
     * 
     * @param param Defines the {@code param.path} to the source model of that diagram and {@code param.waitForDiagram}
     * indicates if a diagram generation was issued at the same time so this getOptions call may wait for that.
     * @return A list of all synthesis options, if the diagram for {@code param.path} is opened, {@code null} otherwise.
     */
    @JsonRequest('getOptions')
    public def CompletableFuture<List<SynthesisOption>> getOptions(GetOptionParam param)
    
    /**
     * Method called by a client to set the {@link SynthesisOption}s of the diagram resolved by {@code param.uri} to 
     * the options given in {@code param.synthesisOption}. Also updates the diagram.
     * 
     * @param param The {@code uri} to resolve the diagram and the new options given by {@code synthesisOption}.
     * @return "OK", if the diagram was found and the new options have been set, "ERR" otherwise.
     */
    @JsonRequest('setOptions')
    public def CompletableFuture<String> setOptions(SetOptionParam param)
}