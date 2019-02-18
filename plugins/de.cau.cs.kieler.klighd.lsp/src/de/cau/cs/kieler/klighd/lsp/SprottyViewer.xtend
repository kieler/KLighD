/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import de.cau.cs.kieler.klighd.IViewerProvider
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.ZoomStyle
import de.cau.cs.kieler.klighd.internal.IDiagramOutlinePage
import de.cau.cs.kieler.klighd.internal.ILayoutRecorder
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.utils.SprottyProperties
import de.cau.cs.kieler.klighd.util.RenderingContextData
import de.cau.cs.kieler.klighd.viewers.AbstractViewer
import de.cau.cs.kieler.klighd.viewers.ContextViewer
import org.eclipse.swt.widgets.Composite

/**
 * A KlighD viewer to represent external sprotty diagram contexts.
 * Warning: Unimplemented methods of this viewer currently just output that they got called, but do not do anything.
 * Needs to be completed first.
 * 
 * @author nre
 */
class SprottyViewer extends AbstractViewer implements ILayoutRecorder,
    IDiagramOutlinePage.Provider { // TODO: Can this interface help with generating the diagram only once the LS is ready?
    
    /** The identifier of this viewer type as specified in the extension. */
    public static final String ID = "de.cau.cs.kieler.klighd.lsp.SprottyViewer"
    
    private ViewContext viewContext
    
    private KNode model
    
    public static class Provider implements IViewerProvider {
        override createViewer(ContextViewer parentViewer, Composite parent) {
            return new SprottyViewer()
        }
    }
    
    override setModel(KNode model, boolean sync) {
        this.model = model
    }
    
    override centerOn(KGraphElement diagramElement, int duration) {
        println("center on called")
        // do nothing.
    }
    
    override clip(KNode diagramElement) {
        println("clip called")
        // do nothing.
    }
    
    override collapse(KNode diagramElement) {
        // persist the state safe of model updates in the RenderingContextData.
        val contextData = RenderingContextData.get(diagramElement)
        contextData.setProperty(SprottyProperties.EXPANDED, false)
    }
    
    override expand(KNode diagramElement) {
        // persist the state safe of model updates in the RenderingContextData.
        val contextData = RenderingContextData.get(diagramElement)
        contextData.setProperty(SprottyProperties.EXPANDED, true)
    }
    
    override getClip() {
        println("get clip called")
        return null
    }
    
    override getContextViewer() {
        println("get context viewer called")
        return null
    }
    
    override getControl() {
        println("is Visible called")
        // TODO: Remove once KlighD has no more SWT dependency.
        // cannot return any SWT element, as there is none. Cannot create any either, as SWT always needs a parent
        // element.
        return null
    }
    
    override getScale(KNode diagramElement) {
        println("get scale called")
        return 0
    }
    
    def setViewContext(ViewContext vc) {
        this.viewContext = vc
    }
    
    override getViewContext() {
        return this.viewContext
    }
    
    override getZoomLevel() {
        println("is Visible called")
        return 0
    }
    
    override hide(KGraphElement diagramElement) {
        println("hide called")
        // do nothing.
    }
    
    override isDisplayed(KGraphElement diagramElement, boolean checkParents) {
        println("is displayed called")
        return false
    }
    
    override isExpanded(KNode diagramElement) {
        return RenderingContextData.get(diagramElement).getProperty(SprottyProperties.EXPANDED)
    }
    
    override isVisible(KGraphElement diagramElement, boolean checkParents) {
        println("is visible called")
        return false
    }
    
    override panDiagramToTopLeftCorner(int duration) {
        println("pan diagram to top left corner called")
        // do nothing.
    }
    
    override panToTopLeftCorner(KNode diagramElement, int duration) {
        println("pan to top left corner called")
        // do nothing.
    }
    
    override reveal(KGraphElement diagramElement, int duration) {
        println("reveal called")
        // do nothing.
    }
    
    override scale(KNode diagramElement, double factor) {
        println("scale called")
        // do nothing.
    }
    
    override show(KGraphElement diagramElement) {
        println("show called")
        // do nothing.
    }
    
    override toggleExpansion(KNode diagramElement) {
        println("toggle expansion called")
        // do nothing.
    }
    
    override zoomToFocus(KNode diagramElement, int duration) {
        println("zoom to focus called")
        // do nothing.
    }
    
    override startRecording() {
        println("start recording called")
        // do nothing.
    }
    
    override stopRecording(int animationTime) {
        println("stop recording called")
        // do nothing.
    }
    
    override stopRecording(ZoomStyle zoomStyle, KNode focusNode, int animationTime) {
        println("stop recording called")
        // do nothing.
    }
    
    override getDiagramOutlinePage() {
        println("get diagramOutlinePage called")
        return null
    }
    
}