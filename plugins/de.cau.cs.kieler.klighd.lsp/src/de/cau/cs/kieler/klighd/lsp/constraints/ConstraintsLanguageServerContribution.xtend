/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.constraints

import com.google.inject.Injector

/**
 * @author jet, cos
 *
 */
class ConstraintsLanguageServerContribution implements ILanguageServerContribution {
    
    override getLanguageServerExtension(Injector injector) {
        return injector.getInstance(ConstraintsLanguageServerExtension)
    }
    
}