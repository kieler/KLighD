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
package de.cau.cs.kieler.klighd.piccolo.viewer;

import java.awt.Component;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.MouseWheelListener;

import de.cau.cs.kieler.core.krendering.Trigger;

import edu.umd.cs.piccolox.swt.PSWTCanvas;

/**
 * Custom mouse listener implementation that is supposed to avoid the translation of SWT to AWT events.
 * To that end, it contributes an own {@link java.awt.event.MouseEvent} type.
 * 
 * @author chsch
 */
public class KlighdMouseEventListener implements MouseListener, MouseMoveListener, MouseTrackListener,
        MouseWheelListener {
        
    private PSWTCanvas canvas = null;
    
    /**
     * Constructor.
     * 
     * @param theCanvas
     *          the canvas it delegates the events to
     */
    public KlighdMouseEventListener(final PSWTCanvas theCanvas) {
        this.canvas = theCanvas;
    }

    /**
     * {@inheritDoc}
     */
    public void mouseScrolled(final MouseEvent e) {

    }

    /**
     * {@inheritDoc}
     */
    public void mouseEnter(final MouseEvent e) {

    }

    /**
     * {@inheritDoc}
     */
    public void mouseExit(final MouseEvent e) {

    }

    /**
     * {@inheritDoc}
     */
    public void mouseHover(final MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseMove(final MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseDoubleClick(final MouseEvent e) {
        // initial implementation experiment
        this.canvas
                .getRoot()
                .getDefaultInputManager()
                .processEventFromCamera(new KlighdMouseEvent(e, SWT.MouseDoubleClick),
                        java.awt.event.MouseEvent.MOUSE_CLICKED, this.canvas.getCamera());

    }

    /**
     * {@inheritDoc}
     */
    public void mouseDown(final MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseUp(final MouseEvent e) {
    }
    
    /**
     * Custom java.awt.event.MouseEvent that wraps the original {@link MouseEvent SWT MouseEvent}
     * and can be pushed trough Piccolo's runtime.
     * 
     * @author chsch
     */
    public static class KlighdMouseEvent extends java.awt.event.MouseEvent {
        
        private static final long serialVersionUID = 4690767684494461534L;

        private MouseEvent mouseEvent = null;
        private int eventType = SWT.None;
        
        private static Component dummySrc = new Component() {
            private static final long serialVersionUID = 2109584415310636543L;
        };
        
        /**
         * Constructor.
         * 
         * @param me the SWT mouse event
         * @param type the event type 
         */
        public KlighdMouseEvent(final MouseEvent me, final int type) {
            super(dummySrc, type, me.time, 0, me.x, me.y, 0, false, me.button);
            this.mouseEvent = me;
            this.eventType = type;
        }

        /**
         * Getter.
         * 
         * @return the original SWT mouse event
         */
        public MouseEvent getMouseEvent() {
            return mouseEvent;
        }
        
        /**
         * Getter.
         * 
         * @return the original SWT event type
         */
        public int getEventType() {
            return eventType;
        }
        
        /**
         * Provides the MouseEventType translated into values of {@link Trigger}.
         * 
         * TODO: supports currently only double clicks, further events are still to be translated.
         * 
         * @return the event {@link Trigger}.
         */
        public Trigger getTrigger() {
            if (eventType == SWT.MouseDoubleClick) {
                return Trigger.DOUBLECLICK;
            }
            return null;
        }
    }
}
