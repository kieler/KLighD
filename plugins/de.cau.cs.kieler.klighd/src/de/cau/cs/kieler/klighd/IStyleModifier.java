/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

/**
 * The interface for classes implementing a style modifier updating
 * {@link de.cau.cs.kieler.core.krendering.KStyle KStyles} after the automatic layout application.
 * This way we can realize specialties like the layout dependent visibility of SyncCharts region
 * separator lines.<br>
 * <br>
 * {@link #modify(StyleModificationContext)} is supposed to return {@code true} if a modification
 * has been performed, and {@code false} otherwise.
 * 
 * @author akoc, chsch
 */
public interface IStyleModifier {
    
    /**
     * Modifies a {@link de.cau.cs.kieler.core.krendering.KStyle KStyle} contributed in the
     * {@code context}.
     * 
     * @param context
     *            a compound structure providing the style to be modified and further convenient
     *            data.
     * @return true if a modification has been performed, false otherwise.
     */
    boolean modify(final StyleModificationContext context);

}
