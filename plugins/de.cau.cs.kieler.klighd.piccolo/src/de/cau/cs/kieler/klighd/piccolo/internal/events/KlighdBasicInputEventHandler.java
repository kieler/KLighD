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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;

import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

/**
 * Specialization of {@link PBasicInputEventHandler} that replaces the event evaluation based on
 * AWT's event type codes by an evaluation based on SWT's event codes. To this end it contributes
 * the override method {@link #processEvent(PInputEvent, int)}.<br>
 * <br>
 * This class can be subclassed in order to react on certain event. Alternatively, it can be
 * instantiated in order to wrap another {@link PBasicInputEventHandler}, e.g. in case that handler
 * is a {@link PDragSequenceEventHandler}.<br>
 * <br>
 * <b>Note:</b> <em>Right mouse button events are not forwarded to
 * {@link PDragSequenceEventHandlers}.</em>
 *
 * @author chsch
 */
public class KlighdBasicInputEventHandler extends PBasicInputEventHandler implements
        IKlighdInputEventHandlerEx {

    /**
     * The actual event handler in case <code>this</code> one is used as a wrapper.
     */
    private final PBasicInputEventHandler delegate;

    /**
     * A flag indicating whether right mouse button events shall be forwarded, this must not be done
     * to {@link PDragSequenceEventHandler PDragSequenceEventHandlers} (at least on OSX).
     */
    private final boolean forwardRightMouseButtonEvents;

    /**
     * Constructor. Is protected as it is to be called by subclasses only.
     */
    protected KlighdBasicInputEventHandler() {
        this.setEventFilter(null);

        this.delegate = this;
        this.forwardRightMouseButtonEvents = true;
    }

    /**
     * Constructor.
     *
     * @param theDelegate
     *            a delegate input event handler to be (re-)used by this one, must not be
     *            <code>null</code>.
     */
    public KlighdBasicInputEventHandler(final PBasicInputEventHandler theDelegate) {
        this.setEventFilter(null);

        if (theDelegate == null) {
            final String msg = "KLighD Piccolo viewer: "
                    + "Constructor KlighdBasicInputEventHandler(PBasicInputEventHandler) "
                    + "is being called with a 'null' argument!";
            throw new IllegalArgumentException(msg);
        }
        theDelegate.setEventFilter(null);

        this.delegate = theDelegate;

        // at least on OSX 'MouseDown' events with button = 3 are sent to the diagram canvas,
        //  but no corresponding 'MouseUp' events, maybe because the context menu widget gets
        //  the event focus
        // therefore, PDragSequenceEventHandlers must not be blessed with button 3 events
        //  as they track the symmetry of 'mousePressed(...)' and 'mouseReleased(...)' events
        this.forwardRightMouseButtonEvents = !(theDelegate instanceof PDragSequenceEventHandler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processEvent(final PInputEvent event, final int type) {
        if (!(event.getSourceSwingEvent() instanceof IKlighdInputEvent)) {
            return;
        }

        final IKlighdInputEvent kEvent = (IKlighdInputEvent) event.getSourceSwingEvent();

        switch (kEvent.getEventType()) {

        case SWT.DragDetect:
            // is currently not helpful as only a single event at the beginning of the dragging is sent
            //  might be used in future for initiating the dragging
            break;
        case SWT.FocusIn:
            delegate.keyboardFocusGained(event);
            break;

        case SWT.FocusOut:
            delegate.keyboardFocusLost(event);
            break;

        case SWT.KeyDown:
            delegate.keyPressed(event);
            break;

        case SWT.KeyUp:
            delegate.keyReleased(event);
            break;

        case SWT.MouseDown:
            // button 3 is reserved for the context menu popUp so suppress reactions on that button
            if (forwardRightMouseButtonEvents            // SUPPRESS CHECKSTYLE NEXT MagicNumber
                    || ((MouseEvent) kEvent.getEvent()).button != 3) {
                delegate.mousePressed(event);
            }
            break;

        case SWT.MouseUp:
            // button 3 is reserved for the context menu popUp so suppress reactions on that button
            if (forwardRightMouseButtonEvents            // SUPPRESS CHECKSTYLE NEXT MagicNumber
                    || ((MouseEvent) kEvent.getEvent()).button != 3) {
                delegate.mouseReleased(event);
            }
            break;

        case SWT.MouseMove:
            if ((((MouseEvent) kEvent.getEvent()).stateMask & SWT.BUTTON1) != 0) {
                // since SWT doesn't distinguish a dedicated 'drag' event similar to the move we have
                //  to test for a pressed button ourselves;
                // could be improved using SWT.DragDetected in future
                delegate.mouseDragged(event);
            } else {
                delegate.mouseMoved(event);
            }
            break;

        case SWT.MouseEnter:
            delegate.mouseEntered(event);
            break;

        case SWT.MouseExit:
            delegate.mouseExited(event);
            break;

        case KlighdMouseEventListener.MouseClick:
            delegate.mouseClicked(event);
            break;

        case SWT.MouseDoubleClick:
            if (delegate instanceof IKlighdInputEventHandlerEx) {
                ((IKlighdInputEventHandlerEx) delegate).mouseDoubleClicked(event);
            }
            break;

        case KlighdMouseEventListener.MouseSingleOrMultiClick:
            if (delegate instanceof IKlighdInputEventHandlerEx) {
                ((IKlighdInputEventHandlerEx) delegate).mouseSingleOrMultiClicked(event);
            }
            break;

        case SWT.MouseHover:
            if (delegate instanceof IKlighdInputEventHandlerEx) {
                ((IKlighdInputEventHandlerEx) delegate).mouseHovered(event);
            }
            break;

        case SWT.MouseVerticalWheel:
            delegate.mouseWheelRotated(event);
            break;

        case SWT.MouseHorizontalWheel:
            if (delegate instanceof IKlighdInputEventHandlerEx) {
                ((IKlighdInputEventHandlerEx) delegate).mouseHorizontalWheelRotated(event);
            }
            break;

        case SWT.Gesture:
            if (delegate instanceof IKlighdInputEventHandlerEx) {
                ((IKlighdInputEventHandlerEx) delegate).gesture(event);
            }
            break;

        default:
            final String msg = "KLighD Piccolo binding: "
                    + "Unsupported input event occured a KLighD diagram.";
            throw new RuntimeException(msg);
        }

    }


    // empty implementations of KlighdInputEventHandlerEx's enabling the creations of
    //  instances of this class, e.g., for wrapping another PBasicInputEventHandler,
    //  as well as subclasses without implementing them

    /**
     * {@inheritDoc}
     */
    public void mouseDoubleClicked(final PInputEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseSingleOrMultiClicked(final PInputEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseHovered(final PInputEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseHorizontalWheelRotated(final PInputEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    public void gesture(final PInputEvent event) {
    }
}
