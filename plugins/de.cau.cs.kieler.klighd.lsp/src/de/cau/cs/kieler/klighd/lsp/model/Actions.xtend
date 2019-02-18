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

import io.typefox.sprotty.api.Action
import io.typefox.sprotty.api.ElementAndBounds
import io.typefox.sprotty.api.SModelRoot
import java.util.List
import java.util.function.Consumer
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.EqualsHashCode
import org.eclipse.xtend.lib.annotations.ToString

/**
 * Sent from the server to the client to request bounds for the given texts. The texts are rendered
 * invisibly so the bounds can derived from the DOM. The response is a {@link ComputedTextBoundsAction}.
 * 
 * @author nir
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
public class RequestTextBoundsAction implements Action {
    public static val KIND = 'requestTextBounds'
    private String kind = KIND
    
    private SModelRoot textDiagram
    
    public new() {}
    public new(Consumer<RequestTextBoundsAction> initializer) {
        initializer.accept(this)
    }
    
    /**
     * Constructor to call when creating this. The {@code textDiagram} should contain a sprotty Diagram with all texts,
     * whose bounds should be requested.
     */
    public new(SModelRoot textDiagram) {
        this.textDiagram = textDiagram
    }
}

/**
 * Sent from the client to the server to transmit the result of text bounds computation as a response
 * to a {@link RequestTextBoundsAction}.
 * 
 * @author nir
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
public class ComputedTextBoundsAction implements Action {
    public static val KIND = 'computedTextBounds'
    private String kind = KIND
    
    private List<ElementAndBounds> bounds
    
    public new() {}
    public new(Consumer<ComputedTextBoundsAction> initializer) {
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
public class PerformActionAction implements Action {
    public static val KIND = 'performAction'
    private String kind = KIND
    
    private String actionId
    private String kGraphElementId
    private String kRenderingId
    
    public new() {}
    public new(Consumer<PerformActionAction> initializer) {
        initializer.accept(this)
    }
}