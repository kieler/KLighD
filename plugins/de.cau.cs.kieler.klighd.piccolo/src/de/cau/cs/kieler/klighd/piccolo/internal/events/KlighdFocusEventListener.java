/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.events;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;

import de.cau.cs.kieler.klighd.piccolo.internal.KlighdCanvas;

/**
 * 
 * @author chsch
 */
public class KlighdFocusEventListener implements FocusListener {

    private KlighdCanvas canvas = null;
    
    /**
     * Constructor.
     * 
     * @param theCanvas
     *          the canvas it delegates the events to
     */
    public KlighdFocusEventListener(final KlighdCanvas theCanvas) {
        this.canvas = theCanvas;
    }

    /**
     * {@inheritDoc}
     */
    public void focusGained(final FocusEvent e) {
        this.canvas.getRoot().getDefaultInputManager()
                .setKeyboardFocus(this.canvas.getCamera().pick(0, 0, 1));
    }

    /**
     * {@inheritDoc}
     */
    public void focusLost(final FocusEvent e) {
        // this method seems to be not needed, yet
    }
}
