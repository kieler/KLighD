/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018,2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import com.google.gson.JsonElement
import com.google.inject.Singleton
import de.cau.cs.kieler.klighd.IViewer
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.internal.ISynthesis
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.krendering.KImage
import de.cau.cs.kieler.klighd.krendering.KText
import de.cau.cs.kieler.klighd.lsp.model.ImageData
import de.cau.cs.kieler.klighd.lsp.model.SKLabel
import java.net.URLDecoder
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.elk.core.LayoutConfigurator
import org.eclipse.sprotty.SModelElement

/**
 * Singleton class to map a URI identifying a graph to their various parts needed for handling KGraph models
 * 
 * @author nre
 */
@Singleton
class KGraphDiagramState {
    
    /**
     * A map mapping the URI identifying a graph to the {@link ViewContext} containing that graph.
     */
    Map<String, ViewContext> kGraphContexts = new HashMap
    
    /**
     * A map that contains a key-value pair for each KGraphElement and its translated SModelElement counterpart.
     * Convenient for finding a specific key KGraphElement faster.
     * Mapped by the URI this map belongs to.
     */
    Map<String, Map<KGraphElement, SModelElement>> kGraphToSModelElementMap = new HashMap
    
    /**
     * A map that contains a key-value pair for each ID of a graph element and the {@link KGraphElement} it identifies.
     * Mapped by the URI this map belongs to.
     */
    Map<String, Map<String, KGraphElement>> idToKGraphElementMap = new HashMap
    
    /**
     * A set containing the image data for all {@link KImage}s from the source KGraph.
     * Mapped by the URI this map belongs to.
     */
    Map<String, Set<ImageData>> imageData = new HashMap
    
    /**
     * A list containing all texts from the source KGraph in Sprotty labels.
     * Mapped by the URI this map belongs to.
     */
    Map<String, List<SKLabel>> texts = new HashMap
    
    /**
     * A map containing all KTexts from the source KGraph under the key of their id.
     * Mapped by the URI this map belongs to.
     */
    Map<String, Map<String, KText>> textMapping = new HashMap
    
    /**
     * Contains the model of the currently drawn snapshot for the URI of the model, if available.
     */
    Map<String, Object> snapshotModelMapping = new HashMap
    
    /**
     * Contains the layout configurator for the URI of the model.
     */
    Map<String, LayoutConfigurator> layoutConfigMapping = new HashMap
    
    /**
     * Contains the current synthesis ID for the URI of the model.
     */
    Map<String, String> synthesisIdMapping = new HashMap

    /**
     * Contains the {@link IViewer} displaying diagrams.
     */
    IViewer viewer = null
    
    /**
     * Set containing the used {@link ISynthesis}.
     */
    Set<ISynthesis> usedSyntheses = new HashSet
    
    /**
     * Map containing all recently used {@link SynthesisOption}s and their current values.
     */
    Map<SynthesisOption, Object> recentSynthesisOptions = new HashMap
    
    /**
     * The options predefined by the client that should be used during syntheses and layout.
     */
    JsonElement clientOptions
    
    /**
     * A map to map the Sprotty client id to the URI leading to the resource.
     */
    Map<String, String> uriStringMap = new HashMap
    
    /**
     * Contains the diagram piece request manager for the URI of the model.
     */
    Map<String, KGraphDiagramPieceRequestManager> diagramPieceRequestManagerMap = new HashMap
    
    // ------------ Methods to access or modify the fields -------------
    
    /**
     * Getter to access the diagram piece request manager for the given URI.
     * 
     * @param uri The identifying URI of the graph to access the value in the map.
     */
    def KGraphDiagramPieceRequestManager getDiagramPieceRequestManager(String uri) {
        diagramPieceRequestManagerMap.get(uri)
    }
    
    /**
     * Put method to set the diagram piece request manager for a URI.
     * 
     * @param uri The identifying URI of the graph to access the map.
     * @param requestManager The diagram piece request manager to be stored.
     */
    def putDiagramPieceRequestManager(String uri, KGraphDiagramPieceRequestManager requestManager) {
        diagramPieceRequestManagerMap.put(uri, requestManager)
    }
    /**
     * Getter to access the value stored in the kGraphContext map.
     * 
     * @param uri The identifying URI of the graph to access the value in the map.
     */
    def ViewContext getKGraphContext(String uri) {
        kGraphContexts.get(uri)
    }
    
    /**
     * Put method to put a new value in the kGraphContext map.
     * 
     * @param uri The identifying URI of the graph to access the map.
     * @param value The value to be stored in the map.
     */
    def putKGraphContext(String uri, ViewContext value) {
        kGraphContexts.put(uri, value)
    }
    
    /**
     * Getter to access the value stored in the kGraphToSModelElement map.
     * 
     * @param uri The identifying URI of the graph to access the value in the map.
     */
    def Map<KGraphElement, SModelElement> getKGraphToSModelElementMap(String uri) {
        kGraphToSModelElementMap.get(uri)
    }
    
    /**
     * Put method to put a new value in the kGraphToSModelElement map.
     * 
     * @param uri The identifying URI of the graph to access the map.
     * @param value The value to be stored in the map.
     */
    def putKGraphToSModelElementMap(String uri, Map<KGraphElement, SModelElement> value) {
        kGraphToSModelElementMap.put(uri, value)
    }
    
    /**
     * Getter to access the value stored in the idToKGraphElement map.
     * 
     * @param uri The identifying URI of the graph to access the value in the map.
     */
    def Map<String, KGraphElement> getIdToKGraphMap(String uri) {
        idToKGraphElementMap.get(uri)
    }
    
    /**
     * Put method to put a new value in the idToKGraphElement map.
     * 
     * @param uri The identifying URI of the graph to access the map.
     * @param value The value to be stored in the map.
     */
    def putIdToKGraphElementMap(String uri, Map<String, KGraphElement> value) {
        idToKGraphElementMap.put(uri, value)
    }
    
    /**
     * Getter to access the value stored in the imageData map.
     * 
     * @param uri The identifying URI of the graph to access the value in the map.
     */
    def Set<ImageData> getImageData(String uri) {
        imageData.get(uri)
    }
    
    /**
     * Put method to put a new value in the imageData map.
     * 
     * @param uri The identifying URI of the graph to access the map.
     * @param value The value to be stored in the map.
     */
    def putImageData(String uri, Set<ImageData> value) {
        imageData.put(uri, value)
    }
    
    /**
     * Getter to access the value stored in the texts map.
     * 
     * @param uri The identifying URI of the graph to access the value in the map.
     */
    def List<SKLabel> getTexts(String uri) {
        texts.get(uri)
    }
    
    /**
     * Put method to put a new value in the texts map.
     * 
     * @param uri The identifying URI of the graph to access the map.
     * @param value The value to be stored in the map.
     */
    def putTexts(String uri, List<SKLabel> value) {
        texts.put(uri, value)
    }
    
    /**
     * Getter to access the value stored in the textMapping map.
     * 
     * @param uri The identifying URI of the graph to access the value in the map.
     */
    def Map<String, KText> getTextMapping(String uri) {
        textMapping.get(uri)
    }
    
    /**
     * Put method to put a new value in the textMapping map.
     * 
     * @param uri The identifying URI of the graph to access the map.
     * @param value The value to be stored in the map.
     */
    def putTextMapping(String uri, Map<String, KText> value) {
        textMapping.put(uri, value)
    }
    
    /**
     * Getter to access the value stored in the snapshotModel map.
     * 
     * @param uri The identifying URI of the graph to access the value in the map.
     */
    def Object getSnapshotModel(String uri) {
        snapshotModelMapping.get(uri)
    }
    
    /**
     * Put method to put a new value in the uriString map.
     * 
     * @param uri The identifying URI of the graph to access the map.
     * @param value The value to be stored in the map.
     */
    def putSnapshotModel(String uri, Object value) {
        snapshotModelMapping.put(uri, value)
    }
    
    /**
     * Getter to access the value stored in the layoutConfig mapping.
     * 
     * @param uri They identifying URI of the graph to access the value in the map.
     */
    def getLayoutConfig(String uri) {
        var configurator = layoutConfigMapping.get(uri)
        if (configurator === null) {
            configurator = new LayoutConfigurator
            layoutConfigMapping.put(uri, configurator)
        }
        return configurator
    }
    
    /**
     * Put method to put a new value in the layoutConfig mapping.
     * 
     * @param uri The identifying URI of the graph to access the map.
     * @param value The value to be stored in the map.
     */
    def putLayoutConfig(String uri, LayoutConfigurator value) {
        layoutConfigMapping.put(uri, value)
    }
    
    /**
     * Getter to access the value stored in the synthesisId mapping.
     * 
     * @param uri They identifying URI of the graph to access the value in the map.
     */
    def getSynthesisId(String uri) {
        synthesisIdMapping.get(uri)
    }
    
    /**
     * Put method to put a new value in the synthesisId mapping.
     * 
     * @param uri The identifying URI of the graph to access the map.
     * @param value The value to be stored in the map.
     */
    def putSynthesisId(String uri, String value) {
        synthesisIdMapping.put(uri, value)
    }
    
    /**
     * Getter to access the value stored in the uriString map.
     * 
     * @param clientId The clientId of the diagram view.
     */
    def String getURIString(String clientId) {
        uriStringMap.get(clientId)
    }
    
    /**
     * Put method to put a new value in the uriString map.
     * 
     * @param clientId The client ID of the diagram viewer.
     * @param uri The identifying URI of the graph to be stored in the map.
     */
    def putURIString(String clientId, String uri) {
        uriStringMap.put(clientId, uri)
    }
    
    /**
     * Getter to access the used viewer.
     */
    def IViewer getViewer() {
        return viewer
    }
    
    /**
     * Set method to set a new viewer.
     * 
     * @param viewer The new viewer.
     */
    def setViewer(IViewer viewer) {
        this.viewer = viewer
    }
    
    /**
     * Adds the given synthesis to the set of used syntheses.
     */
    def addUsedSynthesis(ISynthesis synthesis) {
        usedSyntheses.add(synthesis)
    }
    
    /**
     * Default getter.
     */
    def getUsedSyntheses() {
        return usedSyntheses
    }
    
    /**
     * Sets the options defined by the client.
     */
    def setClientOptions(JsonElement clientOptions) {
        this.clientOptions = clientOptions
    }
    
    /**
     * Returns all options defined by the client.
     */
    def getClientOptions() {
        return clientOptions
    }
    
    /**
     * Add an option with its current value to the recently used options to be retrievable later via
     * {@link #getRecentSynthesisOptions()}.
     */
    def addRecentSynthesisOption(SynthesisOption option, Object value) {
        recentSynthesisOptions.put(option, value)
    }
    
    /**
     * Returns all values of options recently used on this server added via
     * {@link #addRecentSynthesisOption(SynthesisOption, Object)}.
     */
    def getRecentSynthesisOptions() {
        return recentSynthesisOptions
    }
    
    /**
     * Removes the identifying URI string of the graph for this client ID from all stored maps. Should be called when
     * the diagram view is closed.
     * 
     * @param clientId The client ID of the diagram server for that no map should store any data anymore.
     */
    def remove(String clientId) {
        val uri = uriStringMap.get(clientId)
        if (uri !== null) {
            kGraphContexts.remove(URLDecoder.decode(uri, "UTF-8"))
            kGraphToSModelElementMap.remove(uri)
            idToKGraphElementMap.remove(uri)
            texts.remove(uri)
            textMapping.remove(uri)
            snapshotModelMapping.remove(uri)
            layoutConfigMapping.remove(uri)
            synthesisIdMapping.remove(uri)
            viewer = null
            uriStringMap.remove(clientId)
            diagramPieceRequestManagerMap.remove(uri)
        }
    }
    
}