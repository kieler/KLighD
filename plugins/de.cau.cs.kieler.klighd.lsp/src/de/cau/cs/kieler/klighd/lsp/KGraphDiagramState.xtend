/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import com.google.inject.Singleton
import de.cau.cs.kieler.klighd.IViewer
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.internal.ISynthesis
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.krendering.KImage
import de.cau.cs.kieler.klighd.krendering.KText
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
 * Singleton class to map a graph id (String) found in SGraphs to their various parts needed for handling KGraph models
 * 
 * @author nre
 */
@Singleton
class KGraphDiagramState {
    
    /**
     * A map mapping the id of a graph to the {@link ViewContext} containing that graph.
     */
    Map<String, ViewContext> kGraphContexts = new HashMap
    
    /**
     * A map that contains a key-value pair for each KGraphElement and its translated SModelElement counterpart.
     * Convenient for finding a specific key KGraphElement faster.
     * Mapped by the url this map belongs to.
     */
    Map<String, Map<KGraphElement, SModelElement>> kGraphToSModelElementMap = new HashMap
    
    /**
     * A list containing all {@link KImage}s from the source KGraph.
     * Mapped by the url this map belongs to.
     */
    Map<String, List<KImage>> images = new HashMap
    
    /**
     * A list containing all texts from the source KGraph in Sprotty labels.
     * Mapped by the url this map belongs to.
     */
    Map<String, List<SKLabel>> texts = new HashMap
    
    /**
     * A map containing all KTexts from the source KGraph under the key of their id.
     * Mapped by the url this map belongs to.
     */
    Map<String, Map<String, KText>> textMapping = new HashMap
    
    /**
     * Contains the model of the currently drawn snapshot for the url of the model, if available.
     */
    Map<String, Object> snapshotModelMapping = new HashMap
    
    /**
     * Contains the layout configurator for the url of the model.
     */
    Map<String, LayoutConfigurator> layoutConfigMapping = new HashMap
    
    /**
     * Contains the current synthesis ID for the url of the model.
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
     * Map containing all recently used {@link SynthesisOption} and current value
     */
    Map<SynthesisOption, Object> recentSynthesisOptions = new HashMap
    
    /**
     * A map to map the sprotty client id to the uri leading to the resource.
     */
    Map<String, String> uriStringMap = new HashMap
    
    // ------------ Methods to access or modify the fields -------------
    
    /**
     * Getter to access the value stored in the kGraphContext map.
     * 
     * @param key The key to access the value in the map.
     */
    def ViewContext getKGraphContext(String key) {
        kGraphContexts.get(URLDecoder.decode(key, "UTF-8"))
    }
    
    /**
     * Put method to put a new value in the kGraphConcext map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    def putKGraphContext(String key, ViewContext value) {
        kGraphContexts.put(URLDecoder.decode(key, "UTF-8"), value)
    }
    
    /**
     * Getter to access the value stored in the kGraphToSModelElement map.
     * 
     * @param key The key to access the value in the map.
     */
    def Map<KGraphElement, SModelElement> getKGraphToSModelElementMap(String key) {
        kGraphToSModelElementMap.get(key)
    }
    
    /**
     * Put method to put a new value in the kGraphToSModelElement map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    def putKGraphToSModelElementMap(String key, Map<KGraphElement, SModelElement> value) {
        kGraphToSModelElementMap.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the images map.
     * 
     * @param key The key to access the value in the map.
     */
    def List<KImage> getImages(String key) {
        images.get(key)
    }
    
    /**
     * Put method to put a new value in the images map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    def putImages(String key, List<KImage> value) {
        images.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the texts map.
     * 
     * @param key The key to access the value in the map.
     */
    def List<SKLabel> getTexts(String key) {
        texts.get(key)
    }
    
    /**
     * Put method to put a new value in the texts map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    def putTexts(String key, List<SKLabel> value) {
        texts.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the textMapping map.
     * 
     * @param key The key to access the value in the map.
     */
    def Map<String, KText> getTextMapping(String key) {
        textMapping.get(key)
    }
    
    /**
     * Put method to put a new value in the textMapping map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    def putTextMapping(String key, Map<String, KText> value) {
        textMapping.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the uriString map.
     * 
     * @param key The key to access the value in the map.
     */
    def String getURIString(String key) {
        uriStringMap.get(key)
    }
    
    /**
     * Put method to put a new value in the uriString map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    def putURIString(String clientId, String uri) {
        uriStringMap.put(clientId, uri)
    }
    
    /**
     * Getter to access the value stored in the snapshotModel map.
     * 
     * @param key The key to access the value in the map.
     */
    def Object getSnapshotModel(String key) {
        snapshotModelMapping.get(key)
    }
    
    /**
     * Put method to put a new value in the uriString map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    def putSnapshotModel(String key, Object value) {
        snapshotModelMapping.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the layoutConfig mapping.
     * 
     * @param key They key to access the value in the map.
     */
    def getLayoutConfig(String key) {
        var configurator = layoutConfigMapping.get(key)
        if (configurator === null) {
            configurator = new LayoutConfigurator
            layoutConfigMapping.put(key, configurator)
        }
        return configurator
    }
    
    /**
     * Put method to put a new value in the layoutConfig mapping.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    def putLayoutConfig(String key, LayoutConfigurator value) {
        layoutConfigMapping.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the synthesisId mapping.
     * 
     * @param key They key to access the value in the map.
     */
    def getSynthesisId(String key) {
        synthesisIdMapping.get(key)
    }
    
    /**
     * Put method to put a new value in the synthesisId mapping.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    def putSynthesisId(String key, String value) {
        synthesisIdMapping.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the viewer map.
     * 
     * @param key The key to access the value in the map.
     */
    def IViewer getViewer() {
        return viewer
    }
    
    /**
     * Put method to put a new value in the viewer map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    def putViewer(IViewer value) {
        viewer = value
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
     * Default put method for the {@code recentSynthesisOptions} map.
     */
    def putRecentSynthesisOption(SynthesisOption option, Object value) {
        recentSynthesisOptions.put(option, value)
    }
    
    /**
     * Default getter.
     */
    def getRecentSynthesisOptions() {
        return recentSynthesisOptions
    }
    
    /**
     * Removes the key for this client ID from all stored maps. Should be called when the diagram view is closed.
     * 
     * @param clientId The client ID of the diagram server for that no map should store any data anymore.
     */
    def remove(String clientId) {
        val key = uriStringMap.get(clientId)
        if (key !== null) {
            kGraphContexts.remove(URLDecoder.decode(key, "UTF-8"))
            kGraphToSModelElementMap.remove(key)
            texts.remove(key)
            textMapping.remove(key)
            snapshotModelMapping.remove(key)
            layoutConfigMapping.remove(key)
            synthesisIdMapping.remove(key)
            viewer = null
            uriStringMap.remove(clientId)
        }
    }
    
}