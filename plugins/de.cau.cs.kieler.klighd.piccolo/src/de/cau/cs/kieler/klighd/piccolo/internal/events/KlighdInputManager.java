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

import java.awt.event.MouseEvent;

import edu.umd.cs.piccolo.PInputManager;
import edu.umd.cs.piccolo.event.PInputEvent;

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
     * The delegate event handler employed for replacing the AWT event code-based event evaluation.
     */
    private KlighdBasicInputEventHandler helper = new KlighdBasicInputEventHandler(this);


    // the overriding method replacing the original event evaluation

    /**
     * {@inheritDoc}
     */
    @Override
    public void processEvent(final PInputEvent event, final int type) {
        helper.processEvent(event, type);
    }
    
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

    // overrides of some existing mouse handling methods for consolidating the behavior
    //  as we need it, also wrt. the proper delay single click and double click handling
    
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
        this.dispatchEventToListener(event, MouseEvent.MOUSE_PRESSED, getMouseFocus());
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
