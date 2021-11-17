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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.lsp

import org.eclipse.sprotty.xtext.DiagramServerFactory

/**
 * The factory for creating diagram servers for KLighD purposes.
 * 
 * @author nre
 */
class KGraphDiagramServerFactory extends DiagramServerFactory {
    override getDiagramTypes() {
        #['keith-diagram']
    }
}