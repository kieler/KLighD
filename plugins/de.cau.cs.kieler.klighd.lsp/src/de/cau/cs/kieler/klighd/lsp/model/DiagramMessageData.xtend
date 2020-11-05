/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019,2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.model

import de.cau.cs.kieler.klighd.krendering.KImage
import org.eclipse.xtend.lib.annotations.Data

/**
 * Data sent to the client for setting the available syntheses.
 * 
 * @author nre
 */
@Data
class SetSynthesesActionData {
    /**
     * The ID of the synthesis
     */
    String id
    /**
     * The displayable name of the synthesis.
     */
    String displayName
}

/**
 * Data of a {@link KImage} used to uniquely identify the image.
 * 
 * @author nre
 */
@Data
class ImageData {
    /**
     * Name of the bundle where the image can be found. Note that the imagePath is relative to this bundle. 
     */
    String bundleName
    
    /**
     * The path to the image on the filesystem relative to the bundleName (and its according position).
     */
    String imagePath
    
    /**
     * Creates a new {@code ImageData} object using the data of the given image.
     * 
     * @param image The image to create the new instance from.
     * @return The new {@code ImageData} instance.
     */
    static def ImageData of(KImage image) {
        return new ImageData(image.bundleName, image.imagePath)
    }
}