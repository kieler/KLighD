/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2022 by
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
package de.cau.cs.kieler.klighd.lsp.model

import de.cau.cs.kieler.klighd.krendering.KImage
import java.util.List
import java.util.Set
import java.util.function.Consumer
import org.eclipse.sprotty.Action
import org.eclipse.sprotty.RequestAction
import org.eclipse.sprotty.ResponseAction
import org.eclipse.sprotty.SModelElement
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.EqualsHashCode
import org.eclipse.xtend.lib.annotations.ToString

/**
 * Sent from the server to the client to send a list of all available syntheses for the current model.
 * 
 * @author nre
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class SetSynthesesAction implements Action {
    public static val KIND = 'setSyntheses'
    String kind = KIND
    
    List<SetSynthesesActionData> syntheses
    
     new(List<SetSynthesesActionData> syntheses) {
        this.syntheses = syntheses
    }
}

/**
 * Sent from the server to the client to check if the {@link KImage}s provided in the message are cached or if they need
 * to be sent to the client again.
 * 
 * @author nre
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class CheckImagesAction implements RequestAction<CheckedImagesAction> {
    public static val KIND = 'checkImages'
    String kind = KIND
    
    Set<ImageData> images
    String requestId
    
    new(Set<ImageData> imageData) {
        this.images = imageData
    }
}

/**
 * Sent from the server to the client to store images in base64 format needed for rendering on the client.
 * 
 * @author nre
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class StoreImagesAction implements Action {
    public static val KIND = 'storeImages'
    String kind = KIND
    
    List<Pair<Pair<String, String>, String>> images
    
    new(List<Pair<Pair<String, String>, String>> images) {
        this.images = images
    }
}

/**
 * Action message from the server to update the diagram options widget on the client.
 * 
 * @author nre
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class UpdateDiagramOptionsAction implements Action {
    public static val KIND = 'updateOptions'
    String kind = KIND
    
    /**
     * The list of all displayed synthesis options with their current values.
     */
    List<ValuedSynthesisOption> valuedSynthesisOptions
    
    /**
     * The list of the UI data for layout options.
     */
    List<LayoutOptionUIData> layoutOptions
     
    /**
     * The list of all displayed actions.
     */
    List<DisplayedActionUIData> actions
    
    /**
     * The uri for identifying the model these options are for.
     */
    String modelUri
    
    new() {}
    new(Consumer<UpdateDiagramOptionsAction> initializer) {
        initializer.accept(this)
    }
    
    /**
     * Constructor to call when creating this.
     */
    new(List<ValuedSynthesisOption> valuedSynthesisOptions, List<LayoutOptionUIData> layoutOptions,
        List<DisplayedActionUIData> actions, String modelUri) {
        this.valuedSynthesisOptions = valuedSynthesisOptions
        this.layoutOptions = layoutOptions
        this.actions = actions
        this.modelUri = modelUri 
    }
    
}

/**
 * Sent from the client to the server to inform it whether images need to be sent to the client before accepting the next diagram.
 * 
 * @author nre
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class CheckedImagesAction implements ResponseAction {
    public static val KIND = 'checkedImages'
    String kind = KIND
    
    List<Pair<String, String>> notCached
    String responseId

    new() {}
    new(Consumer<CheckedImagesAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Sent from the client to the server to request a new diagram with the given synthesis.
 * 
 * @author nre
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class SetSynthesisAction implements Action {
    public static val KIND = 'setSynthesis'
    String kind = KIND
    
    String id
    
    new() {}
    new(Consumer<SetSynthesisAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Sent from the client to the server to request a KlighD action to be performed on the current model.
 * 
 * @author nre
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class PerformActionAction implements Action {
    public static val KIND = 'performAction'
    String kind = KIND
    
    String actionId
    String kGraphElementId
    String kRenderingId
    int revision
    
    new() {}
    new(Consumer<PerformActionAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Refreshes the diagram.
 * 
 * @author sdo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class RefreshDiagramAction implements Action {
    public static val KIND = 'refreshDiagram'
    String kind = KIND
    
    new() {}
    new(Consumer<RefreshDiagramAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Refreshes the layout.
 * 
 * @author sdo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class RefreshLayoutAction implements Action {
    public static val KIND = 'refreshLayout'
    String kind = KIND
    
    new() {}
    new(Consumer<RefreshLayoutAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Sent from client to request a certain piece of the diagram.
 * 
 * @author mka
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
public class RequestDiagramPieceAction implements RequestAction<SetDiagramPieceAction> {
    public static val KIND = 'requestDiagramPiece'
    String kind = KIND
    
    String modelElementId
    String requestId
    
    new() {}
    new(String modelElementId) {
        this.modelElementId = modelElementId
    }
    
    new(Consumer<RequestDiagramPieceAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Response to {@link RequestDiagramPieceAction}. Contains the requested SModelElement.
 * 
 * @author mka
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
public class SetDiagramPieceAction implements ResponseAction {
    public static val KIND = 'setDiagramPiece'
    String kind = KIND
    
    SModelElement diagramPiece
    String responseId
    
    new() {}
    new(Consumer<SetDiagramPieceAction> initializer) {
        initializer.accept(this)
    }
    
    new(SModelElement diagramPiece) {
        this.diagramPiece = diagramPiece
    }
}


