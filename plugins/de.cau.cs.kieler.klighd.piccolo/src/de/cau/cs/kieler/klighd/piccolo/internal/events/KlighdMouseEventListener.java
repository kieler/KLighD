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

import java.awt.Component;
import java.awt.event.MouseWheelEvent;

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
import org.eclipse.swt.widgets.Display;

import de.cau.cs.kieler.core.krendering.Trigger;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.events.IKlighdInputEventHandlerEx.IKlighdInputEvent;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdKeyEventListener.KlighdEventHelper;

/**
 * Custom mouse and gesture listener implementation that is supposed to avoid the translation of SWT
 * events into AWT ones. To that end, it contributes own {@link java.awt.event.MouseEvent}
 * {@link java.awt.event.MouseWheelEvent} types that wrap the original events.
 *
 * @author chsch
 */
public class KlighdMouseEventListener implements MouseListener, MouseMoveListener, MouseTrackListener,
        MouseWheelListener, DragDetectListener, GestureListener {

    /** Constant value of the left mouse button id. */
    public static final int LEFT_BUTTON = 1;

    /** Constant value of the mid mouse button id. */
    public static final int MIDDLE_BUTTON = 2;

    /** Constant value of the right mouse button id. */
    public static final int RIGHT_BUTTON = 3;

    /**
     * Dedicated event type constant, reuses {@link SWT#Iconify} in hope that the choice will never
     * lead to any conflict...
     */
    public static final int MouseClick = SWT.Iconify; // SUPPRESS CHECKSTYLE Name

    /**
     * Dedicated event type constant, reuses {@link SWT#Deiconify} in hope that the choice will
     * never lead to any conflict...
     */
    public static final int MouseSingleOrMultiClick = SWT.Deiconify; // SUPPRESS CHECKSTYLE Name

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

        if (KlighdPlugin.IS_WINDOWS) {
            canvas.setFocus();
        }

        this.canvas.sendInputEventToInputManager(new KlighdMouseEvent(e, SWT.MouseDown),
                java.awt.event.MouseEvent.MOUSE_PRESSED);
    }

    private boolean[] lastSingleClickConfig = new boolean[] { Boolean.FALSE };

    /**
     * {@inheritDoc}
     */
    public void mouseUp(final MouseEvent e) {
        // notify a 'MouseUp' each time
        this.canvas.sendInputEventToInputManager(new KlighdMouseEvent(e, SWT.MouseUp),
                java.awt.event.MouseEvent.MOUSE_RELEASED);

        // fortunately SWT provides the number of mouse button events
        //  that occurred within the system wide mouse double click period

        // if this is the 2nd or a number higher mouseUp event ...
        if (e.count != 1) {
            lastSingleClickConfig[0] = Boolean.FALSE;
            // ... stop here, the remaining part is valid for the 1st mouseUp only!
            return;
        }

        // at the 1st occurrence of a mouse up notify furthermore a
        //  'MouseSingleOrMultiClick' event without any delay; subsequent mouseUp
        //  within the system wide mouse double click period are ignored

        this.canvas.sendInputEventToInputManager(new KlighdMouseEvent(e, MouseSingleOrMultiClick),
                KlighdInputManager.MOUSE_SINGLE_OR_MULTI_CLICKED);

        // besides schedule a timer that notifies a MouseClick event if no more
        //  mouseUp occurs within the double click time
        // this way both a diagram action triggered by a single click and a double click
        //  triggered one may be associated with the same diagram element and properly distinguished
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
    }

    /**
     * {@inheritDoc}
     */
    public void mouseDoubleClick(final MouseEvent e) {
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
         *            the SWT event type
         */
        public KlighdMouseEvent(final MouseEvent me, final int type) {
            super(dummySrc, 0, me.time, 0, me.x, me.y, 0, false, me.button > BUTTON3 ? 0 : me.button);
            // the BUTTON3 check has been introduced as the super constructor will throw an exception
            //  in such cases
            this.mouseEvent = me;
            this.eventType = type;
            this.helper = new KlighdEventHelper(me, eventType == SWT.MouseDown);
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
              case MouseSingleOrMultiClick:
                  str.append("SingleOrMultiMouseClick");
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
                switch (mouseEvent.button) {
                case LEFT_BUTTON:
                    return Trigger.SINGLECLICK;
                case MIDDLE_BUTTON:
                    return Trigger.MIDDLE_SINGLECLICK;
                }

            } else if (eventType == SWT.MouseDoubleClick) {
                switch (mouseEvent.button) {
                case LEFT_BUTTON:
                    return Trigger.DOUBLECLICK;
                case MIDDLE_BUTTON:
                    return Trigger.MIDDLE_DOUBLECLICK;
                }
            } else if (eventType == MouseSingleOrMultiClick) {
                switch (mouseEvent.button) {
                case LEFT_BUTTON:
                    return Trigger.SINGLE_OR_MULTICLICK;
                case MIDDLE_BUTTON:
                    return Trigger.MIDDLE_SINGLE_OR_MULTICLICK;
                }
            }
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getButton() {
            // since the SWT button ids are equal to the AWT button ids just ...
            return super.getButton();
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
        public GestureEvent getEvent() {
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
            this.helper = new KlighdEventHelper(me, false);
        }

        /**
         * {@inheritDoc}
         */
        public MouseEvent getEvent() {
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
