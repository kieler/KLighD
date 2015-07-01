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

import org.eclipse.swt.events.TypedEvent;

import edu.umd.cs.piccolo.event.PInputEvent;

/**
 * Internal interface declaring additional event handling methods beyond those of
 * {@link edu.umd.cs.piccolo.event.PBasicInputEventHandler PBasicInputEventHandler}.<br>
 * <br>
 * It is implemented by {@link KlighdBasicInputEventHandler} and {@link KlighdInputManager} and
 * enables reactions on {@link org.eclipse.swt.SWT#MouseDoubleClick SWT#MouseDoubleClick},
 * {@link org.eclipse.swt.SWT#MouseHover SWT#MouseHover}, and {@link org.eclipse.swt.SWT#Gesture
 * SWT#Gesture} events, for example.<br>
 * <br>
 * This interface is not intended to be implemented by clients!
 *
 * @author chsch
 */
public interface IKlighdInputEventHandlerEx {

    /**
     * Method is invoked when mouse button 1 or 2 has been double clicked.<br>
     * To be implemented by clients.
     *
     * @param event
     *            a corresponding {@link PInputEvent}, which has is usually created in
     *            {@link edu.umd.cs.piccolo.PInputManager#processInput()
     *            PInputManager#processInput()}; the source event is accessible via
     *            {@link PInputEvent#getSourceSwingEvent()}, it is supposed to by an instance of
     *            {@link KlighdInputManager.IKlighdInputEvent}
     */
    void mouseDoubleClicked(final PInputEvent event);

    /**
     * Method is invoked when mouse button 1 or 2 has been double clicked.<br>
     * To be implemented by clients.
     *
     * @param event
     *            a corresponding {@link PInputEvent}, which has is usually created in
     *            {@link edu.umd.cs.piccolo.PInputManager#processInput()
     *            PInputManager#processInput()}; the source event is accessible via
     *            {@link PInputEvent#getSourceSwingEvent()}, it is supposed to by an instance of
     *            {@link KlighdInputManager.IKlighdInputEvent}
     */
    void mouseSingleOrMultiClicked(final PInputEvent event);

    /**
     * Method is invoked when the mouse rests at a position for some time.<br>
     * To be implemented by clients.
     *
     * @param event
     *            a corresponding {@link PInputEvent}, which has is usually created in
     *            {@link edu.umd.cs.piccolo.PInputManager#processInput()
     *            PInputManager#processInput()}; the source event is accessible via
     *            {@link PInputEvent#getSourceSwingEvent()}, it is supposed to by an instance of
     *            {@link KlighdInputManager.IKlighdInputEvent}
     */
    void mouseHovered(final PInputEvent event);

    /**
     * Method is invoked when the horizontal mouse wheel is rotated.<br>
     * Use {@link edu.umd.cs.piccolo.event.PBasicInputEventHandler#mouseWheelRotated(PInputEvent)
     * PBasicInputEventHandler#mouseWheelRotated(PInputEvent)} for reacting on vertical rotation
     * event.<br>
     * To be implemented by clients.
     *
     * @param event
     *            a corresponding {@link PInputEvent}, which has is usually created in
     *            {@link edu.umd.cs.piccolo.PInputManager#processInput()
     *            PInputManager#processInput()}; the source event is accessible via
     *            {@link PInputEvent#getSourceSwingEvent()}, it is supposed to by an instance of
     *            {@link KlighdInputManager.IKlighdInputEvent}
     */
    void mouseHorizontalWheelRotated(final PInputEvent event);

    /**
     * Method is invoked when a gesture is performed on the track pad.<br>
     * To be implemented by clients.
     *
     * @param event
     *            a corresponding {@link PInputEvent}, which has is usually created in
     *            {@link edu.umd.cs.piccolo.PInputManager#processInput()
     *            PInputManager#processInput()}; the source event is accessible via
     *            {@link PInputEvent#getSourceSwingEvent()}, it is supposed to by an instance of
     *            {@link KlighdInputManager.IKlighdInputEvent}
     */
    void gesture(final PInputEvent event);


    /**
     * Common internal interface of the KLighD-specific input events
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdKeyEventListener KlighdKeyEvent}
     * ,
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.KlighdMouseEvent
     * KlighdMouseEvent},
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener
     * .KlighdMouseWheelEvent KlighdMouseWheelEvent}, and
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.KlighdGestureEvent
     * KlighdGestureEvent}.<br>
     * <br>
     * This interface is not intended to be implemented by clients.
     *
     * @author chsch
     */
    public interface IKlighdInputEvent {

        /**
         * Returns the {@link org.eclipse.swt.SWT SWT} event type.
         *
         * @return the event type
         */
        int getEventType();

        /**
         * Returns the {@link org.eclipse.swt.SWT SWT} event.
         *
         * @return the original {@link TypedEvent}
         */
        TypedEvent getEvent();
    }
}
