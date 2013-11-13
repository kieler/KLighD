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
import java.awt.event.InputEvent;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TypedEvent;

import de.cau.cs.kieler.klighd.piccolo.internal.events.IKlighdInputEventHandlerEx.IKlighdInputEvent;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;

/**
 * Custom key listener implementation that is supposed to avoid the translation of SWT events into
 * AWT ones. To that end, it contributes own an {@link java.awt.event.KeyEvent} type that wraps the
 * original events.
 * 
 * @author chsch
 */
public class KlighdKeyEventListener implements KeyListener {

    private static final boolean OS_MACOSX = Platform.getOS().equals(Platform.OS_MACOSX);
    
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
        public TypedEvent getEvent() {
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
        
        private int button = 0;
        private int stateMask = 0;        
        
        /**
         * Constructor.
         * 
         * @param ge
         */
        public KlighdEventHelper(final GestureEvent ge) {
            this.stateMask = ge.stateMask;
        }
        
        /**
         * Constructor.
         * 
         * @param ke
         */
        public KlighdEventHelper(final KeyEvent ke) {
            this.stateMask = ke.stateMask;
        }

        /**
         * Constructor.
         * 
         * @param me
         */
        public KlighdEventHelper(final MouseEvent me) {
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

        public int getModifiers() {
            int modifiers = 0;

            if ((stateMask & SWT.ALT) != 0) {
                modifiers = modifiers | InputEvent.ALT_MASK;
            }
            if ((stateMask & SWT.CONTROL) != 0) {
                if (!OS_MACOSX) {
                    modifiers = modifiers | InputEvent.CTRL_MASK;
                }
            }
            if ((stateMask & SWT.SHIFT) != 0) {
                modifiers = modifiers | InputEvent.SHIFT_MASK;
            }
            if ((stateMask & SWT.COMMAND) != 0) {
                if (OS_MACOSX) {
                    modifiers = modifiers | InputEvent.CTRL_MASK;
                }
            }
            if (button == 1 || (stateMask & SWT.BUTTON1) != 0) {
                modifiers = modifiers | InputEvent.BUTTON1_MASK;
            }
            if (button == 2 || (stateMask & SWT.BUTTON2) != 0) {
                modifiers = modifiers | InputEvent.BUTTON2_MASK;
            }
            if (button == 3 || (stateMask & SWT.BUTTON3) != 0) { // SUPPRESS CHECKSTYLE MagicNumber
                modifiers = modifiers | InputEvent.BUTTON3_MASK;
            }

            return modifiers;
        }

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
            if (button == 1 || (stateMask & SWT.BUTTON1) != 0) {
                modifiers = modifiers | InputEvent.BUTTON1_DOWN_MASK;
            }
            if (button == 2 || (stateMask & SWT.BUTTON2) != 0) {
                modifiers = modifiers | InputEvent.BUTTON2_DOWN_MASK;
            }
            if (button == 3 || (stateMask & SWT.BUTTON3) != 0) { // SUPPRESS CHECKSTYLE MagicNumber
                modifiers = modifiers | InputEvent.BUTTON3_DOWN_MASK;
            }
            
            return modifiers;
        }
    }    
}
