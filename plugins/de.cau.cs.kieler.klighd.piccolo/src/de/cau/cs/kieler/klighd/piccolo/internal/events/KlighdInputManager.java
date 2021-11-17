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

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PInputManager;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PDimension;

/**
 * Specialized {@link PInputManager} that replaces the evaluation of events from the SWT event
 * listeners installed on the {@link de.cau.cs.kieler.klighd.piccolo.internal.KlighdCanvas
 * KlighdCanvas} based on AWT's event type codes by an evaluation based on the codes of
 * {@link org.eclipse.swt.SWT SWT}. This way {@link org.eclipse.swt.SWT#MouseDoubleClick
 * SWT#MouseDoubleClick}, {@link org.eclipse.swt.SWT#MouseHover SWT#MouseHover}, and
 * {@link org.eclipse.swt.SWT#Gesture SWT#Gesture} events are supported, too, which have no
 * corresponding counterparts in AWT.<br>
 * <br>
 * Fortunately, {@link PInputManager} is a subclass of
 * {@link edu.umd.cs.piccolo.event.PBasicInputEventHandler PBasicInputEventHandler} so we can
 * delegate to a {@link KlighdBasicInputEventHandler} while performing
 * {@link #processEvent(PInputEvent, int)}. This {@link KlighdBasicInputEventHandler} "delegates"
 * back to <code>this</code> instance while calling the particular event handling methods.<br>
 * <br>
 * This class implements {@link IKlighdInputEventHandlerEx} in order to properly support the above
 * mentioned SWT events, see {@link KlighdBasicInputEventHandler}.<br>
 * <br>
 * <b>Note:</b> the local override of {@link #checkForMouseEnteredAndExited(PInputEvent)} omits the
 * notification of {@link MouseEvent#MOUSE_ENTERED} and {@link MouseEvent#MOUSE_EXITED} events with
 * the same event data attached. Since the event type dispatch in the employed {@link #helper} (
 * {@link KlighdBasicInputEventHandler}) relies on the SWT event type being taken from the event
 * data rather than the provided AWT constants (e.g. the above mentioned ones) the event are
 * eventually evaluated twice. This results in a big confusion and disturbs, e.g., the panning.
 *
 * @author chsch
 */
public class KlighdInputManager extends PInputManager implements IKlighdInputEventHandlerEx {

    //SUPPRESS CHECKSTYLE NEXT 30 MagicNumber

    /**
     * The "mouse double click" event type. Such events are fired if the mouse has been double
     * clicked.
     */
    public static final int MOUSE_DOUBLE_CLICKED = 8 + MouseEvent.MOUSE_FIRST;

    /**
     * The "mouse single or multi click" event type. Such events are fired if the mouse has been
     * clicked once regardless of whether further clicks follow within the double click period.
     */
    public static final int MOUSE_SINGLE_OR_MULTI_CLICKED = 9 + MouseEvent.MOUSE_FIRST;

    /**
     * The "mouse hovered" event type. Such events are fired if the mouse stays at a position for a
     * while.
     */
    public static final int MOUSE_HOVERED = 10 + MouseEvent.MOUSE_FIRST;

    /**
     * The "mouse horizontal wheel" event type. Such events are fired if the mouse' horizontal wheel
     * is rotated.
     */
    public static final int MOUSE_HORIZONTAL_WHEEL = 11 + MouseEvent.MOUSE_FIRST;

    /**
     * The "mouse gesture" event type. Such events are treated as mouse event since AWT doesn't
     * provide any gesture support yet.
     */
    public static final int MOUSE_GESTURE = 12 + MouseEvent.MOUSE_FIRST;

    /**
     * Specialization of {@link PInputEvent} changing the behavior of {@link #getCamera()} to always
     * return the root camera (
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera KlighdMainCamera}).
     * This implies (desired) different behavior of {@link #getDelta()} and {@link #getPosition()}.
     * 
     */
    public static class KlighdInputEvent extends PInputEvent {

        /**
         * Constructor.
         *
         * @param inputManager
         *            source of <code>this</code> {@link KlighdInputEvent}
         * @param event
         *            underlying Swing {@link InputEvent}
         */
        public KlighdInputEvent(final PInputManager inputManager, final InputEvent event) {
            super(inputManager, event);
        }

        @Override
        public PCamera getCamera() {
            return getTopCamera();
        }

        // the following 2 overrides are not necessary because of the above 'getCamera()' change;
        // I however kept them for indicating explicitly the change's (intended) consequences

        @Override
        public PDimension getDelta() {
            // refer to the main camera rather than any hidden helper ones ("bottomCamera(s)")
            final PCamera camera = getTopCamera();
            return (PDimension) camera.localToView(getDeltaRelativeTo(camera));
        }

        @Override
        public Point2D getPosition() {
            // refer to the main camera rather than any hidden helper ones ("bottomCamera(s)")
            final PCamera camera = getTopCamera();
            return camera.localToView(getPositionRelativeTo(camera));
        }
    }


    // "register" the modified KlighdInputEvent type
    
    @Override
    protected KlighdInputEvent createInputEvent(final InputEvent event) {
        // overriding method injecting the above declared KlighdInputEvent type.
        return new KlighdInputEvent(this, event);
    }

    // overriding method replacing the original event evaluation by delegating to the
    //  SWT event type based event handling helper

    /**
     * The delegate event handler employed for replacing the AWT event code-based event evaluation.
     */
    private KlighdBasicInputEventHandler helper = new KlighdBasicInputEventHandler(this);

    /**
     * {@inheritDoc}
     */
    @Override
    public void processEvent(final PInputEvent event, final int type) {
        helper.processEvent(event, type);
    }


    // overrides of some existing mouse handling methods for consolidating the behavior to
    //  our needs, also wrt. the proper delayed single click and double click handling

    /**
     * {@inheritDoc}
     */
    @Override
    protected void checkForMouseEnteredAndExited(final PInputEvent event) {
        // suppress original implementation in this case as it disturbs e.g, the panning,
        //  see class documentation for more details;
        // may be reactivated in future in case exiting/entering of PNodes shall be observed
        //  requires proper evaluation of the MOUSE_ENTERED & MOUSE_EXITED constants
    }


    // if truly required the reference of 'getMouseFocus()' in 'mouseReleased' (which reveals
    //  the pickPath configured in 'mousePressed') can be altered;
    // however, verify and test all implemented inputEventHandlers afterwards!
    
    // btw.: I just reverted mri's modification of the subsequent 2 methods' super implementations
    //  and I don't see the problem he needed to work around.
    // That, however, could have resulted from the magic being done in the Piccolo2D guys' mouse
    //  input source implementation, see PSWTCanvas.MouseInputSource

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final PInputEvent event) {
        setMouseFocus(getMouseOver());
        this.dispatchEventToListener(event, MouseEvent.MOUSE_PRESSED, getMouseFocus());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final PInputEvent event) {
        this.dispatchEventToListener(event, MouseEvent.MOUSE_RELEASED, getMouseFocus());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(final PInputEvent event) {
        this.dispatchEventToListener(event, MouseEvent.MOUSE_CLICKED, getMouseFocus());
    }

    // the additional handler methods according to KlighdInputEventHandlerEx

    /**
     * {@inheritDoc}
     */
    public void mouseDoubleClicked(final PInputEvent event) {
        this.dispatchEventToListener(event, MOUSE_DOUBLE_CLICKED, getMouseFocus());
    }

    /**
     * {@inheritDoc}
     */
    public void mouseSingleOrMultiClicked(final PInputEvent event) {
        this.dispatchEventToListener(event, MOUSE_SINGLE_OR_MULTI_CLICKED, getMouseFocus());
    }

    /**
     * {@inheritDoc}
     */
    public void mouseHovered(final PInputEvent event) {
        this.dispatchEventToListener(event, MOUSE_HOVERED, getMouseOver());
    }

    /**
     * {@inheritDoc}
     */
    public void mouseHorizontalWheelRotated(final PInputEvent event) {
        this.dispatchEventToListener(event, MOUSE_HORIZONTAL_WHEEL, getMouseOver());
    }

    /**
     * {@inheritDoc}
     */
    public void gesture(final PInputEvent event) {
        this.dispatchEventToListener(event, MOUSE_GESTURE, getMouseOver());
    }
}
