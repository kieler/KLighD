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
import java.awt.event.MouseWheelEvent;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.GestureListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.TypedEvent;

import de.cau.cs.kieler.core.krendering.Trigger;
import de.cau.cs.kieler.klighd.piccolo.internal.events.IKlighdInputEventHandlerEx.IKlighdInputEvent;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdKeyEventListener.KlighdEventHelper;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;

/**
 * Custom mouse and gesture listener implementation that is supposed to avoid the translation of SWT
 * events into AWT ones. To that end, it contributes own {@link java.awt.event.MouseEvent}
 * {@link java.awt.event.MouseWheelEvent} types that wrap the original events.
 * 
 * @author chsch
 */
public class KlighdMouseEventListener implements MouseListener, MouseMoveListener, MouseTrackListener,
        MouseWheelListener, GestureListener {

    private KlighdCanvas canvas = null;
    
    /**
     * Constructor.
     * 
     * @param theCanvas
     *          the canvas it delegates the events to
     */
    public KlighdMouseEventListener(final KlighdCanvas theCanvas) {
        this.canvas = theCanvas;
    }


    /**
     * {@inheritDoc}
     */
    public void mouseEnter(final MouseEvent e) {
        this.canvas
                .getRoot()
                .getDefaultInputManager()
                .processEventFromCamera(new KlighdMouseEvent(e, SWT.MouseEnter),
                        java.awt.event.MouseEvent.MOUSE_ENTERED, this.canvas.getCamera());
    }

    /**
     * {@inheritDoc}
     */
    public void mouseExit(final MouseEvent e) {
        this.canvas
                .getRoot()
                .getDefaultInputManager()
                .processEventFromCamera(new KlighdMouseEvent(e, SWT.MouseExit),
                        java.awt.event.MouseEvent.MOUSE_EXITED, this.canvas.getCamera());
    }

    /**
     * {@inheritDoc}
     */
    public void mouseHover(final MouseEvent e) {
        this.canvas
                .getRoot()
                .getDefaultInputManager()
                .processEventFromCamera(new KlighdMouseEvent(e, SWT.MouseHover),
                        KlighdInputManager.MOUSE_HOVERED, this.canvas.getCamera());
    }

    /**
     * {@inheritDoc}
     */
    public void mouseMove(final MouseEvent e) {
        this.canvas
                .getRoot()
                .getDefaultInputManager()
                .processEventFromCamera(new KlighdMouseEvent(e, SWT.MouseMove),
                        java.awt.event.MouseEvent.MOUSE_CLICKED, this.canvas.getCamera());
    }

    /**
     * {@inheritDoc}
     */
    public void mouseDoubleClick(final MouseEvent e) {
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
        if (Platform.getOS().equals(Platform.OS_WIN32)) {
            canvas.setFocus();
        }
        this.canvas
                .getRoot()
                .getDefaultInputManager()
                .processEventFromCamera(new KlighdMouseEvent(e, SWT.MouseDown),
                        java.awt.event.MouseEvent.MOUSE_PRESSED, this.canvas.getCamera());
    }

    /**
     * {@inheritDoc}
     */
    public void mouseUp(final MouseEvent e) {
        this.canvas
                .getRoot()
                .getDefaultInputManager()
                .processEventFromCamera(new KlighdMouseEvent(e, SWT.MouseUp),
                        java.awt.event.MouseEvent.MOUSE_RELEASED, this.canvas.getCamera());
    }

    /**
     * {@inheritDoc}
     */
    public void gesture(final GestureEvent e) {
        this.canvas
                .getRoot()
                .getDefaultInputManager()
                .processEventFromCamera(new KlighdGestureEvent(e, SWT.Gesture),
                        KlighdInputManager.MOUSE_GESTURE, this.canvas.getCamera());
    }
    
    /**
     * {@inheritDoc}
     */
    public void mouseScrolled(final MouseEvent e) {
        this.canvas
                .getRoot()
                .getDefaultInputManager()
                .processEventFromCamera(
                        new KlighdMouseWheelEvent(e, SWT.MouseVerticalWheel,
                                MouseWheelEvent.WHEEL_UNIT_SCROLL),
                        java.awt.event.MouseEvent.MOUSE_WHEEL, this.canvas.getCamera());

    }


    /**
     * Custom {@link java.awt.event.MouseEvent} that wraps the original {@link MouseEvent SWT MouseEvent}
     * and can be pushed trough Piccolo's runtime.
     * 
     * @author chsch
     */
    public static class KlighdMouseEvent extends java.awt.event.MouseEvent implements IKlighdInputEvent {
        
        private static final long serialVersionUID = 4690767684494461534L;

        private static Component dummySrc = new Component() {
            private static final long serialVersionUID = 2109584415310636543L;
        };

        private MouseEvent mouseEvent = null;
        private int eventType = SWT.None;
        
        private KlighdEventHelper helper;
        
        /**
         * Constructor.
         * 
         * @param me
         *            the SWT mouse event
         * @param type
         *            the event type
         */
        public KlighdMouseEvent(final MouseEvent me, final int type) {
            super(dummySrc, 0, me.time, 0, me.x, me.y, 0, false, me.button);
            this.mouseEvent = me;
            this.eventType = type;
            this.helper = new KlighdEventHelper(me);
        }

        /**
         * {@inheritDoc}
         */
        public TypedEvent getEvent() {
            return this.mouseEvent;
        }
        
        /**
         * {@inheritDoc}
         */
        public int getEventType() {
            return this.eventType;
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
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int getModifiers() {
            return this.helper == null ? 0 : this.helper.getModifiers();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int getModifiersEx() {
            return this.helper == null ? 0 : this.helper.getModifiersEx();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isAltDown() {
            return helper.isAltDown();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isControlDown() {
            return helper.isControlDown();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isMetaDown() {
            return helper.isMetaDown();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isShiftDown() {
            return helper.isShiftDown();
        }
    }
    
    /**
     * Custom {@link java.awt.event.MouseEvent} that wraps an {@link GestureEvent SWT GestureEvent}
     * and can be pushed trough Piccolo's runtime. Need this wrapping
     * {@link java.awt.event.MouseEvent} since AWT doesn't provide any gesture-specific event.<br>
     * In addition, the constructor of its super class {@link java.awt.event.InputEvent} is package
     * protected.
     * 
     * @author chsch
     */
    public static class KlighdGestureEvent extends java.awt.event.MouseEvent implements
            IKlighdInputEvent {
        
        private static final long serialVersionUID = 2711517281987328193L;

        private static Component dummySrc = new Component() {
            private static final long serialVersionUID = 2609348390438849160L;
        };
        
        private GestureEvent gestureEvent = null;
        private int eventType = SWT.None;
        
        private KlighdEventHelper helper;

        /**
         * Constructor.
         * 
         * @param ge
         *            the SWT mouse event
         * @param type
         *            the SWT event type
         */
        public KlighdGestureEvent(final GestureEvent ge, final int type) {
            super(dummySrc, 0, ge.time, 0, ge.x, ge.y, 0, false);
            this.gestureEvent = ge;
            this.eventType = type;
            this.helper = new KlighdEventHelper(ge);
        }

        /**
         * {@inheritDoc}
         */
        public TypedEvent getEvent() {
            return this.gestureEvent;
        }
        
        /**
         * {@inheritDoc}
         */
        public int getEventType() {
            return this.eventType;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int getModifiers() {
            return this.helper == null ? 0 : this.helper.getModifiers();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int getModifiersEx() {
            return this.helper == null ? 0 : this.helper.getModifiersEx();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isAltDown() {
            return helper.isAltDown();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isControlDown() {
            return helper.isControlDown();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isMetaDown() {
            return helper.isMetaDown();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isShiftDown() {
            return helper.isShiftDown();
        }
    }
    
    /**
     * Custom {@link java.awt.event.MouseWheelEvent} that wraps the original {@link MouseEvent SWT
     * MouseEvent} and can be pushed trough Piccolo's runtime.
     * 
     * @author mri
     */
    public static class KlighdMouseWheelEvent extends java.awt.event.MouseWheelEvent implements
            IKlighdInputEvent {
        
        private static final long serialVersionUID = -2094261280867051699L;

        private static Component fakeSrc = new Component() {
            private static final long serialVersionUID = 6935771461423211368L;
        };

        /** Event being wrapped. */
        private MouseEvent mouseEvent;
        private int eventType = SWT.None;
        
        private KlighdEventHelper helper;

        /**
         * Constructor.
         * 
         * @param me
         *            the {@link SWT} {@link MouseEvent}
         * @param type
         *            the event type
         * @param scrollType
         *            the scroll type
         */
        public KlighdMouseWheelEvent(final MouseEvent me, final int type, final int scrollType) {
            // uru: my touchpad fires loads of me.count == 0,
            //  hence with the old version, way too many 1's are passed.
            super(fakeSrc, 0, me.time, 0, me.x, me.y, 1, false, scrollType, me.count,
                    me.count < 0 ? -1 : (me.count > 0 ? 1 : 0));
            //super(fakeSrc, 0, me.time, 0, me.x, me.y, 1, false, scrollType, me.count,
            //        me.count < 0 ? -1 : 1);
            this.mouseEvent = me;
            this.eventType = type;
            this.helper = new KlighdEventHelper(me);
        }
        
        /**
         * {@inheritDoc}
         */
        public TypedEvent getEvent() {
            return mouseEvent;
        }
        
        /**
         * {@inheritDoc}
         */
        public int getEventType() {
            return eventType;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int getModifiers() {
            return this.helper == null ? 0 : this.helper.getModifiers();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int getModifiersEx() {
            return this.helper == null ? 0 : this.helper.getModifiersEx();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isAltDown() {
            return helper.isAltDown();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isControlDown() {
            return helper.isControlDown();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isMetaDown() {
            return helper.isMetaDown();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isShiftDown() {
            return helper.isShiftDown();
        }
    }
}
