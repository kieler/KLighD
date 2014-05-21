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
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.GestureListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Display;

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
        MouseWheelListener, DragDetectListener, GestureListener {
    
    /**
     * Dedicated event type constant, reuses {@link SWT#Iconify} in hope that choice will never lead
     * to any conflict...
     */
    public static final int MouseClick = SWT.Iconify; // SUPPRESS CHECKSTYLE Name

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
        this.canvas.sendInputEventToInputManager(new KlighdMouseEvent(e, SWT.MouseEnter),
                java.awt.event.MouseEvent.MOUSE_ENTERED);
    }

    /**
     * {@inheritDoc}
     */
    public void mouseExit(final MouseEvent e) {
        this.canvas.sendInputEventToInputManager(new KlighdMouseEvent(e, SWT.MouseExit),
                java.awt.event.MouseEvent.MOUSE_EXITED);
    }

    /**
     * {@inheritDoc}
     */
    public void mouseHover(final MouseEvent e) {
        this.canvas.sendInputEventToInputManager(new KlighdMouseEvent(e, SWT.MouseHover),
                KlighdInputManager.MOUSE_HOVERED);
    }

    /**
     * {@inheritDoc}
     */
    public void mouseMove(final MouseEvent e) {
        this.canvas.sendInputEventToInputManager(new KlighdMouseEvent(e, SWT.MouseMove),
                java.awt.event.MouseEvent.MOUSE_MOVED);
    }

    /**
     * {@inheritDoc}
     */
    public void mouseDown(final MouseEvent e) {

        lastSingleClickConfig[0] = Boolean.FALSE;

        if (Platform.getOS().equals(Platform.OS_WIN32)) {
            canvas.setFocus();
        }

        this.canvas.sendInputEventToInputManager(new KlighdMouseEvent(e, SWT.MouseDown),
                java.awt.event.MouseEvent.MOUSE_PRESSED);
    }

    private boolean[] lastSingleClickConfig = new boolean[] { Boolean.FALSE };

    private long doubleClickDeadLine = System.currentTimeMillis();
    private boolean doubleClicked = false;

    /**
     * {@inheritDoc}
     */
    public void mouseUp(final MouseEvent e) {
        final long currentTime = System.currentTimeMillis();
        
        this.canvas.sendInputEventToInputManager(new KlighdMouseEvent(e, SWT.MouseUp),
                java.awt.event.MouseEvent.MOUSE_RELEASED);

        if (doubleClicked && currentTime < doubleClickDeadLine) {
            // i.e. a doubleClick event occurred and it did before the deadline
            // suppress the propagation of the mouse up
            doubleClicked = false;
            return;
        } 
        
        final Display display = this.canvas.getDisplay();
        final int doubleClickTime = display.getDoubleClickTime();
        
        final boolean[] singleClick = new boolean[] { Boolean.TRUE };
        lastSingleClickConfig = singleClick;
        
        display.timerExec(doubleClickTime, new Runnable() {

            public void run() {
                if (singleClick[0]) {
                    KlighdMouseEventListener.this.canvas.sendInputEventToInputManager(
                            new KlighdMouseEvent(e, MouseClick),
                            java.awt.event.MouseEvent.MOUSE_CLICKED);
                }
            }
        });

        doubleClickDeadLine = System.currentTimeMillis() + doubleClickTime;
    }

    /**
     * {@inheritDoc}
     */
    public void mouseDoubleClick(final MouseEvent e) {
        doubleClicked = true;
        this.canvas.sendInputEventToInputManager(new KlighdMouseEvent(e, SWT.MouseDoubleClick),
                KlighdInputManager.MOUSE_DOUBLE_CLICKED);
    }
    
    /**
     * {@inheritDoc}
     */
    public void dragDetected(final DragDetectEvent e) {
        this.canvas.sendInputEventToInputManager(new KlighdMouseEvent(e, SWT.DragDetect),
                java.awt.event.MouseEvent.MOUSE_DRAGGED);
    }

    /**
     * {@inheritDoc}
     */
    public void gesture(final GestureEvent e) {
        this.canvas.sendInputEventToInputManager(new KlighdGestureEvent(e, SWT.Gesture),
                        KlighdInputManager.MOUSE_GESTURE);
    }
    
    /**
     * {@inheritDoc}
     */
    public void mouseScrolled(final MouseEvent e) {
        this.canvas.sendInputEventToInputManager(new KlighdMouseWheelEvent(e,
                SWT.MouseVerticalWheel, MouseWheelEvent.WHEEL_UNIT_SCROLL),
                java.awt.event.MouseEvent.MOUSE_WHEEL);
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
            super(dummySrc, 0, me.time, 0, me.x, me.y, 0, false, me.button > BUTTON3 ? 0 : me.button);
            // the BUTTON3 check has been introduced as the super constructor will throw an exception
            //  in such cases
            this.mouseEvent = me;
            this.eventType = type;
            this.helper = new KlighdEventHelper(me);
        }

        /**
         * {@inheritDoc}
         */
        public MouseEvent getEvent() {
            return this.mouseEvent;
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
        public String paramString() {
            final StringBuffer str = new StringBuffer();

            switch(eventType) {
              case SWT.MouseDown:
                  str.append("MouseDown");
                  break;
              case SWT.MouseUp:
                  str.append("MouseUp");
                  break;
              case SWT.MouseEnter:
                  str.append("MouseEnter");
                  break;
              case SWT.MouseExit:
                  str.append("MouseExit");
                  break;
              case SWT.MouseMove:
                  str.append("MouseMove");
                  break;
              case SWT.MouseWheel:
                  str.append("MouseWheel");
                  break;
              case MouseClick:
                  str.append("MouseClick");
                  break;
              case SWT.MouseDoubleClick:
                  str.append("MouseDoubleClick");
                  break;
               default:
                  str.append("unknown type");
            }

            // (x,y) coordinates
            str.append(",(").append(getX()).append(",").append(getY()).append(")");
            str.append(",absolute(").append(getXOnScreen()).append(",").append(getYOnScreen())
                    .append(")");

            str.append(",button=").append(getButton());

            if (getModifiers() != 0) {
                str.append(",modifiers=").append(getMouseModifiersText(getModifiers()));
            }

            if (getModifiersEx() != 0) {
                str.append(",extModifiers=").append(getModifiersExText(getModifiers()));
            }

            str.append(",clickCount=").append(getClickCount());

            return str.toString(); 
        }
        
        /**
         * Provides the MouseEventType translated into values of {@link Trigger}.
         * 
         * @return the event {@link Trigger} or <code>null</code> if event type is not supported.
         */
        public Trigger getTrigger() {
            if (eventType == MouseClick) {
                if (mouseEvent.button == 1) {
                    return Trigger.SINGLECLICK;
                } else if (mouseEvent.button == 2) {
                    return Trigger.MIDDLE_SINGLECLICK;
                }
            }
            if (eventType == SWT.MouseDoubleClick
                    && mouseEvent.button == 1) {
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
