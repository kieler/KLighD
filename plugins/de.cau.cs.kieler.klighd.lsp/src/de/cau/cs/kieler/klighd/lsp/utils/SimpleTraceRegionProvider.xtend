/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018,2019 by
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

import org.eclipse.emf.ecore.EObject
import org.eclipse.sprotty.xtext.tracing.TextRegionProvider
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.util.TextRegion

/**
 * If Sprotty's text region provider does not find any significant region, this will instead try to highlight the text
 * for the entire element that was clicked, not just its most significant part.
 * 
 * @author nre
 */
class SimpleTraceRegionProvider extends TextRegionProvider {
    /**
     * Returns the text region of the entire definition of this {@link EObject}, if no more specific region is found.
     */
    override TextRegion getSignificantRegion(EObject element) {
        val significantRegion = super.getSignificantRegion(element)
        if (significantRegion !== null) {
            return significantRegion
        }
        return NodeModelUtils.findActualNodeFor(element).toTextRegion
    }
}