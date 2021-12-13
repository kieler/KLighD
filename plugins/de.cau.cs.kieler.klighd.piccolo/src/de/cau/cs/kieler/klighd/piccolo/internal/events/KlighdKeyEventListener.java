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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.piccolo.internal.events;

import static de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.LEFT_BUTTON;
import static de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.MIDDLE_BUTTON;
import static de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.RIGHT_BUTTON;

import java.awt.Component;
import java.awt.event.InputEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;

import de.cau.cs.kieler.klighd.Klighd;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.events.IKlighdInputEventHandlerEx.IKlighdInputEvent;

/**
 * Custom key listener implementation that is supposed to avoid the translation of SWT events into
 * AWT ones. To that end, it contributes own an {@link java.awt.event.KeyEvent} type that wraps the
 * original events.
 *
 * @author chsch
 */
public class KlighdKeyEventListener implements KeyListener {

    static final boolean OS_MACOSX = Klighd.IS_MACOSX;

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
        this.canvas.sendInputEventToInputManager(new KlighdKeyEvent(e, SWT.KeyDown),
                java.awt.event.KeyEvent.KEY_PRESSED);
    }

    /**
     * {@inheritDoc}
     */
    public void keyReleased(final KeyEvent e) {
        this.canvas.sendInputEventToInputManager(new KlighdKeyEvent(e, SWT.KeyUp),
                java.awt.event.KeyEvent.KEY_RELEASED);
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

        private static Component dummySrc = new Component() {
            private static final long serialVersionUID = -1814729194899793112L;
        };

        private KeyEvent keyEvent = null;
        private int eventType = SWT.None;

        private KlighdEventHelper helper;

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
            this.helper = new KlighdEventHelper(ke);
        }

        /**
         * {@inheritDoc}
         */
        public KeyEvent getEvent() {
            return this.keyEvent;
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
        public int getKeyCode() {
            return this.keyEvent.keyCode;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getKeyLocation() {
            return this.keyEvent.keyLocation;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getModifiers() {
            return  this.helper == null ? 0 : this.helper.getModifiers();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getModifiersEx() {
            return  this.helper == null ? 0 : this.helper.getModifiersEx();
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
     * A helper class being shared by the {@link KlighdKeyEvent},
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.KlighdGestureEvent
     * KlighdGestureEvent},
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.KlighdMouseEvent
     * KlighdMouseEvent}, and
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.
     * KlighdMouseWheelEvent KlighdMouseWheelEvent}. Introduced to avoid code clones and have the
     * implementations of the methods below at a single place.
     *
     * @author chsch
     */
    static class KlighdEventHelper {

        private final boolean mousePressed;
        private final int button;
        private final int stateMask;

        /**
         * Constructor.
         *
         * @param ge
         */
        KlighdEventHelper(final GestureEvent ge) {
            this.mousePressed = false;
            this.button = 0;
            this.stateMask = ge.stateMask;
        }

        /**
         * Constructor.
         *
         * @param ke
         */
        KlighdEventHelper(final KeyEvent ke) {
            this.mousePressed = false;
            this.button = 0;
            this.stateMask = ke.stateMask;
        }

        /**
         * Constructor.
         *
         * @param me
         * @param pressed
         *            <code>true</code> if the corresponding event is a mouse pressed event, the
         *            result will include the events <code>button</code> information in only that
         *            case, <code>false</code> by default
         */
        KlighdEventHelper(final MouseEvent me, final boolean pressed) {
            this.mousePressed = pressed;
            this.button = me.button;
            this.stateMask = me.stateMask;
        }


        public boolean isShiftDown() {
            return (stateMask & SWT.SHIFT) != 0;
        }

        public boolean isControlDown() {
            // chsch: since 'COMMAND' is typically used on OSX for functionality provided by
            // 'CTRL'-based key combinations in other OSs and Mac's 'CTRL' + click is hard linked
            // with the context menu call - thus Mac's 'CTRL' is not usable - I realized an
            // aliasing...
            if (OS_MACOSX) {
                return (stateMask & SWT.COMMAND) != 0;
            } else {
                return (stateMask & SWT.CONTROL) != 0;
            }
        }

        public boolean isMetaDown() {
            // chsch: the Meta Key is currently not supported by SWT, see
            // https://bugs.eclipse.org/bugs/show_bug.cgi?id=340711
            return false;
        }

        public boolean isAltDown() {
            return (stateMask & SWT.ALT) != 0;
        }

        /**
         * Provides the modifier descriptor according to {@link InputEvent#getModifiers()}.<br>
         * Since {@link InputEvent#BUTTON2_MASK} == {@link InputEvent#ALT_MASK} (which is likely to
         * be by intention in order to provides alternatives to buttons 2 and 3, e.g., for users
         * of older Apple one button mice) and {@link KlighdKeyEvent#isAltDown()},
         * {@link de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEvent#isAltDown()
         * KlighdMouseEvent.isAltDown()},
         * {@link de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseWheelEvent#isAltDown()
         * KlighdMouseWheelEvent.isAltDown()}, and {@link
         * de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.KlighdGestureEvent
         * #isAltDown() KlighdGestureEvent.isAltDown()} don't rely on this method (and those testing
         * shift and ctrl/cmd as well) I removed the setting of the SHIFT, ALT, and CTRL/CMD bits here!
         * 
         * @return the combined modifier descriptor according to the AWT JDK <= 1.3 bit masks
         */
        public int getModifiers() {
            int modifiers = 0;

            if ((mousePressed && button == LEFT_BUTTON) || (stateMask & SWT.BUTTON1) != 0) {
                modifiers = modifiers | InputEvent.BUTTON1_MASK;
            }
            if ((mousePressed && button == MIDDLE_BUTTON) || (stateMask & SWT.BUTTON2) != 0) {
                modifiers = modifiers | InputEvent.BUTTON2_MASK;
            }
            if ((mousePressed && button == RIGHT_BUTTON) || (stateMask & SWT.BUTTON3) != 0) {
                modifiers = modifiers | InputEvent.BUTTON3_MASK;
            }

            return modifiers;
        }

        /**
         * Provides the modifier descriptor according to {@link InputEvent#getModifiersEx()}.
         *
         * @return the combined modifier descriptor according to the AWT JDK >= 1.4 bit masks
         */
        public int getModifiersEx() {
            int modifiers = 0;

            if ((stateMask & SWT.ALT) != 0) {
                modifiers = modifiers | InputEvent.ALT_DOWN_MASK;
            }
            if ((stateMask & SWT.CONTROL) != 0) {
                if (!OS_MACOSX) {
                    modifiers = modifiers | InputEvent.CTRL_DOWN_MASK;
                }
            }
            if ((stateMask & SWT.SHIFT) != 0) {
                modifiers = modifiers | InputEvent.SHIFT_DOWN_MASK;
            }
            if ((stateMask & SWT.COMMAND) != 0) {
                if (OS_MACOSX) {
                    modifiers = modifiers | InputEvent.CTRL_MASK;
                }
            }
            if ((mousePressed && button == LEFT_BUTTON) || (stateMask & SWT.BUTTON1) != 0) {
                modifiers = modifiers | InputEvent.BUTTON1_DOWN_MASK;
            }
            if ((mousePressed && button == MIDDLE_BUTTON) || (stateMask & SWT.BUTTON2) != 0) {
                modifiers = modifiers | InputEvent.BUTTON2_DOWN_MASK;
            }
            if ((mousePressed && button == RIGHT_BUTTON) || (stateMask & SWT.BUTTON3) != 0) {
                modifiers = modifiers | InputEvent.BUTTON3_DOWN_MASK;
            }

            return modifiers;
        }
    }
}
