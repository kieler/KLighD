/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2021 by
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
package de.cau.cs.kieler.klighd.lsp.utils

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import java.util.Map
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.sprotty.SModelElement
import org.eclipse.sprotty.xtext.tracing.XtextTrace
import org.eclipse.sprotty.xtext.tracing.XtextTraceProvider
import org.eclipse.xtext.resource.XtextResource

/**
 * Trace provider that lazily traces elements only when a trace is requested.
 * 
 * @author nre
 */
class LazyTraceProvider extends XtextTraceProvider {
    
    /**
     * Stores data for the generation of diagrams.
     */
    @Inject
    protected KGraphDiagramState diagramState

    override protected void doFindSModelElement(SModelElement root, Map<URI, EObject> uri2container,
        (EObject, SModelElement)=>void result) {
        synchronized (diagramState) {
            val s2k = diagramState.getKGraphToSModelElementMap(root.id)?.inverse
            doFindSModelElement2(root, s2k, uri2container, result)
        }
    }
    
    /**
     * Finds all SModelElements matching to some trace in {@code uri2container} and applies {@code result} on all
     * matches. Additionally generates the trace in the first place, if if was not generated before.
     */
    protected def void doFindSModelElement2(SModelElement element, Map<SModelElement, KGraphElement> s2k,
        Map<URI, EObject> uri2container, (EObject, SModelElement)=>void result) {
        // If there is no trace yet, try to fill it now.
        if (element.trace === null) {
            val kElement = s2k.get(element)
            if (kElement !== null) {
                traceFromKGraph(element, kElement)
            }
        }
        
        if (element.trace !== null) {
            val trace = new XtextTrace(element.trace)
            val candidate = uri2container.get(trace.elementURI)
            if (candidate !== null)
                result.apply(candidate, element)
        }
        element.children?.forEach [
            doFindSModelElement2(s2k, uri2container, result)
        ]
    }
    
    /**
     * Generates a trace for the {@code kElement}'s source EObject on the {@code sElement}. 
     * The kElement must be synthesized by a KLighD synthesis before and must have its source EObject stored in the 
     * {@link KlighdInternalProperties#MODEL_ELEMENT} property.
     * 
     * @param sElement The SModelElement that needs a trace to its model element.
     * @param kElement The KGraphElement that was generated from some model element.
     */
    def void traceFromKGraph(SModelElement sElement, KGraphElement kElement) {
        // The real model element that can be traced is the EObject that got synthesized in the
        // {@link KGraphDiagramGenerator#translateModel} function. That model element has to be stored in the properties
        // during the synthesis. Otherwise the tracing will not work.
        val modelElement = kElement.properties.get(KlighdInternalProperties.MODEL_ELEMENT)
        if (modelElement instanceof EObject) {
            if (modelElement.eResource instanceof XtextResource) {
                trace(sElement, modelElement)
            }
        }
    }

}
