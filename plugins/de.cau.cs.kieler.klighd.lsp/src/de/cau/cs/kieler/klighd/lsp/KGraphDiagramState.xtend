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
import de.cau.cs.kieler.klighd.krendering.KText
import de.cau.cs.kieler.klighd.lsp.model.SKLabel
import java.net.URLDecoder
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
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
public class KGraphDiagramState {
    
    /**
     * A map mapping the id of a graph to the {@link ViewContext} containing that graph.
     */
    private Map<String, ViewContext> kGraphContexts = new HashMap
    
    /**
     * A map that contains a key-value pair for each KGraphElement and its translated SModelElement counterpart.
     * Convenient for finding a specific key KGraphElement faster.
     * Mapped by the url this map belongs to.
     */
    private Map<String, Map<KGraphElement, SModelElement>> kGraphToSModelElementMap = new HashMap
    
    /**
     * A list containing all texts from the source KGraph in Sprotty labels.
     * Mapped by the url this map belongs to.
     */
    private Map<String, ArrayList<SKLabel>> texts = new HashMap
    
    /**
     * A map containing all KTexts from the source KGraph under the key of their id.
     * Mapped by the url this map belongs to.
     */
    private Map<String, Map<String, KText>> textMapping = new HashMap
    
    /**
     * Contains the model of the currently drawn snapshot for the url of the model, if available.
     */
    private Map<String, Object> snapshotModelMapping = new HashMap
    
    /**
     * Contains the layout configurator for the url of the model.
     */
    private Map<String, LayoutConfigurator> layoutConfigMapping = new HashMap
    
    /**
     * Contains the current synthesis ID for the url of the model.
     */
    private Map<String, String> synthesisIdMapping = new HashMap

    /**
     * Contains the {@link IViewer} displaying diagrams.
     */
    private IViewer viewer = null
    
    /**
     * Set containing the used {@link ISynthesis}.
     */
    private Set<ISynthesis> usedSyntheses = new HashSet
    
    /**
     * Map containing all recently used {@link SynthesisOption} and current value
     */
    private Map<SynthesisOption, Object> recentSynthesisOptions = new HashMap
    
    /**
     * A map to map the sprotty client id to the uri leading to the resource.
     */
    private Map<String, String> uriStringMap = new HashMap
    
    // ------------ Methods to access or modify the fields -------------
    
    /**
     * Getter to access the value stored in the kGraphContext map.
     * 
     * @param key The key to access the value in the map.
     */
    public def ViewContext getKGraphContext(String key) {
        kGraphContexts.get(URLDecoder.decode(key, "UTF-8"))
    }
    
    /**
     * Put method to put a new value in the kGraphConcext map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    public def putKGraphContext(String key, ViewContext value) {
        kGraphContexts.put(URLDecoder.decode(key, "UTF-8"), value)
    }
    
    /**
     * Getter to access the value stored in the kGraphToSModelElement map.
     * 
     * @param key The key to access the value in the map.
     */
    public def Map<KGraphElement, SModelElement> getKGraphToSModelElementMap(String key) {
        kGraphToSModelElementMap.get(key)
    }
    
    /**
     * Put method to put a new value in the kGraphToSModelElement map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    public def putKGraphToSModelElementMap(String key, Map<KGraphElement, SModelElement> value) {
        kGraphToSModelElementMap.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the texts map.
     * 
     * @param key The key to access the value in the map.
     */
    public def ArrayList<SKLabel> getTexts(String key) {
        texts.get(key)
    }
    
    /**
     * Put method to put a new value in the texts map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    public def putTexts(String key, ArrayList<SKLabel> value) {
        texts.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the textMapping map.
     * 
     * @param key The key to access the value in the map.
     */
    public def Map<String, KText> getTextMapping(String key) {
        textMapping.get(key)
    }
    
    /**
     * Put method to put a new value in the textMapping map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    public def putTextMapping(String key, Map<String, KText> value) {
        textMapping.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the uriString map.
     * 
     * @param key The key to access the value in the map.
     */
    public def String getURIString(String key) {
        uriStringMap.get(key)
    }
    
    /**
     * Put method to put a new value in the uriString map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    public def putURIString(String clientId, String uri) {
        uriStringMap.put(clientId, uri)
    }
    
    /**
     * Getter to access the value stored in the snapshotModel map.
     * 
     * @param key The key to access the value in the map.
     */
    public def Object getSnapshotModel(String key) {
        snapshotModelMapping.get(key)
    }
    
    /**
     * Put method to put a new value in the uriString map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    public def putSnapshotModel(String key, Object value) {
        snapshotModelMapping.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the layoutConfig mapping.
     * 
     * @param key They key to access the value in the map.
     */
    public def getLayoutConfig(String key) {
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
    public def putLayoutConfig(String key, LayoutConfigurator value) {
        layoutConfigMapping.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the synthesisId mapping.
     * 
     * @param key They key to access the value in the map.
     */
    public def getSynthesisId(String key) {
        synthesisIdMapping.get(key)
    }
    
    /**
     * Put method to put a new value in the synthesisId mapping.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    public def putSynthesisId(String key, String value) {
        synthesisIdMapping.put(key, value)
    }
    
    /**
     * Getter to access the value stored in the viewer map.
     * 
     * @param key The key to access the value in the map.
     */
    public def IViewer getViewer() {
        return viewer
    }
    
    /**
     * Put method to put a new value in the viewer map under the given key.
     * 
     * @param key The key to access the map.
     * @param value The value to be stored in the map.
     */
    public def putViewer(IViewer value) {
        viewer = value
    }
    
    /**
     * Adds the given synthesis to the set of used syntheses.
     */
    public def addUsedSynthesis(ISynthesis synthesis) {
        usedSyntheses.add(synthesis)
    }
    
    /**
     * Default getter.
     */
    public def getUsedSyntheses() {
        return usedSyntheses
    }
    
    /**
     * Default put method for the {@code recentSynthesisOptions} map.
     */
    public def putRecentSynthesisOption(SynthesisOption option, Object value) {
        recentSynthesisOptions.put(option, value)
    }
    
    /**
     * Default getter.
     */
    public def getRecentSynthesisOptions() {
        return recentSynthesisOptions
    }
    
    /**
     * Removes the key for this client ID from all stored maps. Should be called when the diagram view is closed.
     * 
     * @param clientId The client ID of the diagram server for that no map should store any data anymore.
     */
    public def remove(String clientId) {
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