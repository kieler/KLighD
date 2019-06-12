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

import java.net.URLDecoder
import org.eclipse.emf.common.util.URI
import com.google.inject.Injector
import org.eclipse.xtext.resource.XtextResourceSet

/**
 * Provides a set of utility methods that is used in the constraints package.
 * 
 * @author jet, cos
 * 
 */
class ConstraintsUtils {


    /**
     * Returns the Resource that is specified by a given uri.
     * 
     * @return The Resource
     */
    def static getResourceFromUri(String uri, Injector injector) {

        //The XTextResourceSet is only able to find the file if the prefix file:// is cut away
        var fileUri = URLDecoder.decode(uri, "UTF-8");
        if (fileUri.startsWith("file://")) {
            fileUri = fileUri.substring(7)
        }

        return injector.getInstance(XtextResourceSet).getResource(URI.createFileURI(fileUri), true)
    }
}
