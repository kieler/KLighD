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

import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;

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

    /**
     * Instances of this class comprises various useful data required for performing things in diagrams.
     * @author chsch
     */
    public static class StyleModificationContext {

        private KStyle style;
        private KLayoutData layoutData;
        
        
        /**
         * Standard Constructor.
         */
        public StyleModificationContext() {
        }

        /**
         * Convenience method for configuring <code>this</code> StyleModificationContext.
         * 
         * @param theStyle
         *            the style to be modified
         * @param theLayoutData
         *            the layout data to be examined during the modification
         * @return <code>this</code> StyleModificationContext
         */
        public StyleModificationContext configure(final KStyle theStyle,
                final KLayoutData theLayoutData) {
            this.style = theStyle;
            this.layoutData = theLayoutData;
            return this;
        }
        
        /**
         * @return the {@link KStyle} to be modified
         */
        public KStyle getStyle() {
            return this.style;
        }
        
        /**
         * @return the {@link KLayoutData} of the {@link de.cau.cs.kieler.core.kgraph.KGraphElement
         *         KGraphElement} the {@link KStyle} to be modified belongs to
         */
        public KLayoutData getLayoutData() {
            return this.layoutData;
        }
    }
}
