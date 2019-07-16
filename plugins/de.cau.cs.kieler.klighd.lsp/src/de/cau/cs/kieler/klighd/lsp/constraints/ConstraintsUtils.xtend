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
import de.cau.cs.kieler.klighd.kgraph.KNode
import org.eclipse.elk.alg.layered.options.LayeredOptions

/**
 * Provides a set of utility methods that is used in the constraints package.
 * 
 * @author jet, cos
 * 
 */
class ConstraintsUtils {

    /**
     * Returns the Resource that is specified by a given uri.
     * @param uri The uri that points to the resource.
     * @param injector The injector that is used for getting the resource.
     * @return The Resource
     */
    def static getResourceFromUri(String uri, Injector injector) {

        // The XTextResourceSet is only able to find the file if the prefix file:// is cut away.
        var fileUri = URLDecoder.decode(uri, "UTF-8");
        if (fileUri.startsWith("file://")) {
            fileUri = fileUri.substring(7)
        }

        return injector.getInstance(XtextResourceSet).getResource(URI.createFileURI(fileUri), true)
    }

    /*
     * Several quality of life methods for faster retrieval and setting of constraints 
     * without typing the IProperty enums again and again
     */
    /**
     * Returns the value of the position constraint that is set on the node.
     * @param node the instance of KNode of which you want the constraint value
     */
    def static getPosConstraint(KNode node) {
        return node.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
    }

    /**
     * Returns the value of the layer constraint that is set on the node.
     * @param node the instance of KNode of which you want the constraint value
     */
    def static getLayerConstraint(KNode node) {
        return node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)
    }

    /**
     * Sets the value of the position constraint that is set on the node.
     * @param node the instance of KNode of which you want the constraint value
     */
    def static setPosConstraint(KNode node, int pos) {
        node.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, pos)
    }

    /**
     * Sets the value of the layer constraint that is set on the node.
     * @param node the instance of KNode that should get the constraint.
     * @param layer the value for the layer constraint
     */
    def static setLayerConstraint(KNode node, int layer) {
        node.setProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, layer)
    }

    /**
     * Sets the value of the layer constraint to null.
     * This procedure effectively deletes the constraint from the node.
     * @param node the instance of KNode of which the layer constraint is set to null
     */
    def static nullifyLayerConstraint(KNode node) {
        node.setProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, null)
    }

    /**
     * Sets the value of the position constraint to null.
     * This procedure effectively deletes the constraint from the node.
     * @param node the instance of KNode of which the position constraint is set to null.
     */
    def static nullifyPosConstraint(KNode node) {
        node.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, null)

    }
}
