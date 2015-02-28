/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;
import edu.umd.cs.piccolo.PLayer;

/**
 * A sightly extended {@link PLayer} that listens to {@link NodeDisposeListener#DISPOSE} notifications
 * and forwards them to its contained elements.
 *
 * @author chsch
 */
public class KlighdDisposingLayer extends PLayer {

    private static final long serialVersionUID = 4423173127127342353L;

    /**
     * Constructor.
     */
    public KlighdDisposingLayer() {
        this.setPickable(false);
        this.setChildrenPickable(true);
        this.addPropertyChangeListener(NodeDisposeListener.DISPOSE, new NodeDisposeListener(this));
    }
}
