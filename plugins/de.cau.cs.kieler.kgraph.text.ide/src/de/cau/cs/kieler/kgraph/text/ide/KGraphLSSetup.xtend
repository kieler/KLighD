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
package de.cau.cs.kieler.kgraph.text.ide

import com.google.inject.Injector

/**
 * @author sdo
 *
 */
class KGraphLSSetup {
    def static Injector doLSSetup() {
        return KGraphIdeSetup.doSetup()
    }
}