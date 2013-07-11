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
package de.cau.cs.kieler.klighd.piccolo.krendering.viewer;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

import edu.umd.cs.piccolox.swt.PSWTCanvas;

/**
 * Custom key listener implementation that is supposed to avoid the translation of SWT to AWT events.
 * 
 * TODO: initial stub, must be implemented
 * 
 * @author chsch
 */
public class KlighdKeyEventListener implements KeyListener {

    @SuppressWarnings("unused")
    private PSWTCanvas canvas = null;
    
    /**
     * Constructor.
     * 
     * @param theCanvas
     *          the canvas it delegates the events to
     */
    public KlighdKeyEventListener(final PSWTCanvas theCanvas) {
        this.canvas = theCanvas;
    }

    /**
     * {@inheritDoc}
     */
    public void keyPressed(final KeyEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    public void keyReleased(final KeyEvent e) {
    }

}
