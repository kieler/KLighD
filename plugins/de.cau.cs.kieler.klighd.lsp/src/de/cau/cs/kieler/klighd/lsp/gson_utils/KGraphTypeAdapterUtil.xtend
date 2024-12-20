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
package de.cau.cs.kieler.klighd.lsp.gson_utils

import com.google.gson.GsonBuilder
import com.google.inject.Injector
import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.lsp.ISprottyActionHandler
import de.cau.cs.kieler.klighd.lsp.model.CheckedImagesAction
import de.cau.cs.kieler.klighd.lsp.model.ClientColorPreferencesAction
import de.cau.cs.kieler.klighd.lsp.model.PerformActionAction
import de.cau.cs.kieler.klighd.lsp.model.RefreshDiagramAction
import de.cau.cs.kieler.klighd.lsp.model.RefreshLayoutAction
import de.cau.cs.kieler.klighd.lsp.model.RequestDiagramPieceAction
import de.cau.cs.kieler.klighd.lsp.model.SetSynthesisAction
import java.awt.geom.Point2D
import java.util.ServiceLoader
import org.eclipse.emf.ecore.EObject
import org.eclipse.sprotty.server.json.ActionTypeAdapter

/**
 * Static util class to configure needed gson type adapters for KGraph serialization.
 * 
 * @author nre, sdo
 */
class KGraphTypeAdapterUtil {
    def static GsonBuilder configureGson(GsonBuilder gsonBuilder, Injector injector) {
        gsonBuilder
        .registerTypeAdapterFactory(
            new ActionTypeAdapter.Factory => [
                // General Sprotty actions
                addActionKind(CheckedImagesAction.KIND, CheckedImagesAction)
                addActionKind(PerformActionAction.KIND, PerformActionAction)
                addActionKind(SetSynthesisAction.KIND, SetSynthesisAction)
                addActionKind(RefreshDiagramAction.KIND, RefreshDiagramAction)
                addActionKind(RefreshLayoutAction.KIND, RefreshLayoutAction)
                addActionKind(ClientColorPreferencesAction.KIND, ClientColorPreferencesAction)
                
                // Load all registered action handlers and add their actions.
                ServiceLoader.load(ISprottyActionHandler, KlighdDataManager.getClassLoader()).forEach[handler |
                    val handlerInstance = injector.getInstance(handler.class)
                    handlerInstance.supportedMessages.keySet.forEach[kind |
                        addActionKind(kind, handlerInstance.supportedMessages.get(kind))
                    ]                    
                ]
                
                // Incremental topdown actions
                addActionKind(RequestDiagramPieceAction.KIND, RequestDiagramPieceAction)
                
                
            ]
        )
        .registerTypeAdapter(Point2D, new Point2DTypeAdapter)
        .registerTypeHierarchyAdapter(EObject, new EObjectSerializer)
        .registerTypeAdapter(SynthesisOption, new SynthesisOptionSerializer)
    }
}
