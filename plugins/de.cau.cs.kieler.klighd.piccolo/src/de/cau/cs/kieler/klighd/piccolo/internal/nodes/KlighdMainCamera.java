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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;

/**
 * This specialized {@link PCamera} type describes the diagram root cameras.<br>
 * It is not intended to be used for any other purpose! 
 * 
 * @author chsch
 */
public class KlighdMainCamera extends PCamera {

    private static final long serialVersionUID = -1769999483311436492L;
    
    /**
     * Constructor.<br>
     * It is flagged 'package protected' as this class is not supposed to be instantiated outside
     * it's package.
     */
    KlighdMainCamera() {
        super();
    }

    /**
     * Getter.
     * 
     * @return the currently displayed {@link INode}
     */
    public INode getDisplayedINode() {
        final PLayer res = this.getLayer(0);
        if (res instanceof INode) {
            return (INode) res;
        } else {
            return null;
        }
    }
}
