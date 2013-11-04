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
package de.cau.cs.kieler.klighd.piccolo.internal.events;

import java.awt.Component;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.TypedEvent;

import de.cau.cs.kieler.klighd.piccolo.internal.events.IKlighdInputEventHandlerEx.IKlighdInputEvent;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;

/**
 * Custom mouse and gesture listener implementation that is supposed to avoid the translation of SWT
 * events into AWT ones. To that end, it contributes own an {@link java.awt.event.KeyEvent} type
 * that wraps the original events.
 * 
 * @author chsch
 */
public class KlighdKeyEventListener implements KeyListener {

    private KlighdCanvas canvas = null;
    
    /**
     * Constructor.
     * 
     * @param theCanvas
     *          the canvas it delegates the events to
     */
    public KlighdKeyEventListener(final KlighdCanvas theCanvas) {
        this.canvas = theCanvas;
    }

    /**
     * {@inheritDoc}
     */
    public void keyPressed(final KeyEvent e) {
        this.canvas
                .getRoot()
                .getDefaultInputManager()
                .processEventFromCamera(new KlighdKeyEvent(e, SWT.KeyDown),
                        java.awt.event.KeyEvent.KEY_PRESSED, this.canvas.getCamera());
    }

    /**
     * {@inheritDoc}
     */
    public void keyReleased(final KeyEvent e) {
        this.canvas
                .getRoot()
                .getDefaultInputManager()
                .processEventFromCamera(new KlighdKeyEvent(e, SWT.KeyUp),
                        java.awt.event.KeyEvent.KEY_RELEASED, this.canvas.getCamera());
    }


    /**
     * Custom {@link java.awt.event.KeyEvent} that wraps an {@link KeyEvent SWT KeyEvent}
     * and can be pushed trough Piccolo's runtime.
     * 
     * @author chsch
     */
    public static class KlighdKeyEvent extends java.awt.event.KeyEvent implements
            IKlighdInputEvent {
        
        private static final long serialVersionUID = -4510781224721252994L;
        
        private KeyEvent keyEvent = null;
        private int eventType = SWT.None;
        
        private static Component dummySrc = new Component() {
            private static final long serialVersionUID = -1814729194899793112L;
        };
        
        /**
         * Constructor.
         * 
         * @param ke
         *            the SWT {@link KeyEvent}
         * @param type
         *            the event type
         */
        public KlighdKeyEvent(final KeyEvent ke, final int type) {
            super(dummySrc, 0, ke.time, 0, ke.keyCode, ke.character);
            this.keyEvent = ke;
            this.eventType = type;
        }

        /**
         * {@inheritDoc}
         */
        public TypedEvent getEvent() {
            return this.keyEvent;
        }
        
        /**
         * {@inheritDoc}
         */
        public int getEventType() {
            return this.eventType;
        }
    }
}
